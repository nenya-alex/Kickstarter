package ua.nenya.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.nenya.dao.CategoryDao;
import ua.nenya.dao.ProjectDao;
import ua.nenya.domain.Project;

@RestController
public class ProjectRestController {
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private CategoryDao categoryDao;	
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectRestController.class);
	
	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public List<Project> getProjects() {
		return projectDao.getProjects();
	}
	
	@RequestMapping(value = "/project/{projectId}", method = RequestMethod.GET)
	public Project getProject(@PathVariable("projectId") Long projectId) {
		if (!projectDao.isProjectExistById(projectId)) {
			logger.error("Project with id "+projectId+" dosen't exist!");
			return new Project();
		}
		Project project = getProjectWithAvailableAmount(projectDao.getProjectByProjectId(projectId));
		
		return project;
	}
	
	private Project getProjectWithAvailableAmount(Project project) {
		Project resultProject = project;
		long sum = projectDao.getPaymentSum(project.getId());
		resultProject.setAvailableAmount(sum);
		return resultProject;
	}

	@RequestMapping(value = "/project/projects/{categoryId}", method = RequestMethod.GET)
	public List<Project> getProjectsByCategoryId(@PathVariable("categoryId") Long categoryId) {
		if (!categoryDao.isCategoryExistById(categoryId)) {
			logger.error("Category with id " + categoryId + " dosen't exist!");
			return new ArrayList<Project>();
		}
		List<Project> projects = getProjectsWithAvailableAmount(projectDao.getProjectsByCategoryId(categoryId));
		return projects;
	}
	
	private List<Project> getProjectsWithAvailableAmount(List<Project> projects) {
		List<Project> resultProjects = new ArrayList<>();
		for(Project it: projects){
			long sum = projectDao.getPaymentSum(it.getId());
			it.setAvailableAmount(sum);
			resultProjects.add(it);
		}
		return resultProjects;
	}
	
	@RequestMapping(value = "/project/{projectId}", method = RequestMethod.DELETE)
	public String deleteProject(@PathVariable("projectId") Long projectId) {
		if (!projectDao.isProjectExistById(projectId)) {
			logger.error("Project with id "+projectId+" dosen't exist!");
			return "failure";
		}
		Project project = projectDao.deleteProjectByProjectId(projectId);
		return "Project \""+project.getName() + "\" is deleted successfully";
	}
	
	@RequestMapping(value = "/project", consumes = "application/json", method = RequestMethod.POST)
	public Project addProject(@RequestBody Project project) {
		if(projectDao.isProjectExistByName(project.getName())){
			return new Project();
		}
		return projectDao.saveProject(project);
	}
	
}
