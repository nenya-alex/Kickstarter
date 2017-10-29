package ua.nenya.service;

import ua.nenya.dto.RewardDTO;

import java.util.List;

public interface RewardService {

    RewardDTO getReward(Long rewardId);

    List<RewardDTO> getProjectRewards(Long projectId);
}
