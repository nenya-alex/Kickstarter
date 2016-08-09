package ua.nenya.dao;

import java.util.List;

import ua.nenya.domain.Project;
import ua.nenya.domain.Reward;

public interface RewardDao {
	Reward getRewardByRewardId(Long rewardId);

	Project getProjectByRewardId(Long rewardId);

	boolean isRewardExist(Long rewardId);
	
	List<Reward> getRewardsByProjectId(Long projectId);
}
