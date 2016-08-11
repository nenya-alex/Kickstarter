package ua.nenya.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import ua.nenya.dao.ProjectDao;
import ua.nenya.dao.QuestionDao;
import ua.nenya.domain.Question;

@RestController
public class QuestionRestController {
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private QuestionDao questionDao;
	
	private static final Logger logger = LoggerFactory.getLogger(QuestionRestController.class);
	
	@RequestMapping(value = "/question/{projectId}", method = RequestMethod.GET)
	public List<Question> getQuestionsByProjectId(@PathVariable("projectId") Long projectId) {
		if (!projectDao.isProjectExistById(projectId)) {
			logger.error("Project with id "+projectId+" dosen't exist!");
			return new ArrayList<Question>();
		}
		
		return questionDao.getQuestions(projectId);
	}
	
	@RequestMapping(value = "/question/add/{projectId}", method = RequestMethod.POST)
	public RedirectView addQuestion(@PathVariable("projectId") Long projectId, 
			@RequestParam("name") String name) {
		
		RedirectView redirectView = new RedirectView();
		String url = "";
		
		if (!projectDao.isProjectExistById(projectId)) {
			logger.error("Project with id "+projectId+" dosen't exist!");
			url = "/error.html";
			redirectView.setUrl(url);
		    return redirectView;
		}
		Question question = new Question();
		question.setProject(projectDao.getProjectByProjectId(projectId));
		question.setName(name);
		questionDao.writeQuestionInProject(question);
		url = "/project.html?projectId="+projectId;
	    redirectView.setUrl(url);
	    return redirectView;
	}
	
}
