package ua.nenya.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.nenya.dao.ProjectDao;
import ua.nenya.dao.RewardDao;
import ua.nenya.domain.Project;
import ua.nenya.domain.Reward;

@RestController
public class RewardRestController {

	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private RewardDao rewardDao;
	
	private static final Logger logger = LoggerFactory.getLogger(PaymentRestController.class);
	
	@RequestMapping(value = "/rewards/{projectId}", method = RequestMethod.GET)
	public List<Reward> getRewards(@PathVariable("projectId") Long projectId) {
		
		if (!projectDao.isProjectExistById(projectId)) {
			logger.error("Project with id "+projectId+" dosen't exist!");
		}
		return rewardDao.getRewardsByProjectId(projectId);
	}
	
	@RequestMapping(value = "/rewards/reward/{rewardId}", method = RequestMethod.GET)
	public Reward getReward(@PathVariable("rewardId") Long rewardId) {
		
		if (!rewardDao.isRewardExist(rewardId)) {
			logger.error("Reward with id "+rewardId+" dosen't exist!");			
		}
		
		return rewardDao.getRewardByRewardId(rewardId);
	}
	
	@RequestMapping(value = "/rewards/project/{rewardId}", method = RequestMethod.GET)
	public Project getProject(@PathVariable("rewardId") Long rewardId) {
		
		if (!rewardDao.isRewardExist(rewardId)) {
			logger.error("Reward with id "+rewardId+" dosen't exist!");			
		}
		
		return rewardDao.getProjectByRewardId(rewardId);
	}
	
}
