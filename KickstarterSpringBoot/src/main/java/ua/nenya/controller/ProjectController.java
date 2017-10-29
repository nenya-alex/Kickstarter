package ua.nenya.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nenya.dto.ProjectDTO;
import ua.nenya.exceptions.ProjectException;
import ua.nenya.service.ProjectService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController {

    private static final Logger log = LoggerFactory.getLogger(ProjectController.class);

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/projects")
    public ResponseEntity<ProjectDTO> createProject(@Valid @RequestBody ProjectDTO projectDTO) {
        log.debug("REST request to create Project:" + projectDTO);
        if (projectDTO.getId() != null){
            throw new ProjectException("Project with not null id cannot be created, id: " + projectDTO.getId());
        }
        ProjectDTO result = projectService.saveProject(projectDTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/projects")
    public ResponseEntity<ProjectDTO> updateProject(@Valid @RequestBody ProjectDTO projectDTO) {
        log.debug("REST request to update Project:" + projectDTO);
        if (projectDTO.getId() == null){
            createProject(projectDTO);
        }
        ProjectDTO result = projectService.saveProject(projectDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/projects/reward/{rewardId}")
    public ResponseEntity<ProjectDTO> getRewardProjects(@PathVariable("rewardId") Long rewardId) {
        log.debug("REST request to get Project by Reward with id:"+rewardId);
        return ResponseEntity.ok().body(projectService.getProjectByReward(rewardId));
    }

    @GetMapping("/projects")
    public List<ProjectDTO> getProjects() {
        log.debug("REST request to get all Projects");
        return projectService.getAll();
    }

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable("projectId") Long projectId) {
        log.debug("REST request to get Project with id: " + projectId);
        return ResponseEntity.ok().body(projectService.getProjectById(projectId));
    }

    @GetMapping("/projects/category/{categoryId}")
    public List<ProjectDTO> getCategoryProjects(@PathVariable("categoryId") Long categoryId) {
        log.debug("REST request to get Projects of category with id: " + categoryId);
        List<ProjectDTO> projects = projectService.getProjectsByCategoryId(categoryId);
        return projects;
    }

    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable("projectId") Long projectId) {
        log.debug("REST request to deleteCategory Project with id: " + projectId);
        projectService.deleteProject(projectId);
        return ResponseEntity.ok().build();
    }

}
