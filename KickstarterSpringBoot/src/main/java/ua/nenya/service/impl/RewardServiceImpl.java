package ua.nenya.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nenya.domain.Project;
import ua.nenya.domain.Reward;
import ua.nenya.dto.RewardDTO;
import ua.nenya.exceptions.ProjectException;
import ua.nenya.exceptions.RewardException;
import ua.nenya.mapper.RewardMapper;
import ua.nenya.repository.ProjectRepository;
import ua.nenya.repository.RewardRepository;
import ua.nenya.service.RewardService;

import java.util.List;

@Service
@Transactional
public class RewardServiceImpl implements RewardService {

    private static final Logger log = LoggerFactory.getLogger(RewardServiceImpl.class);

    private final RewardRepository rewardRepository;

    private final RewardMapper rewardMapper;

    private final ProjectRepository projectRepository;

    public RewardServiceImpl(RewardRepository rewardRepository,
                             RewardMapper rewardMapper,
                             ProjectRepository projectRepository) {
        this.rewardRepository = rewardRepository;
        this.rewardMapper = rewardMapper;
        this.projectRepository = projectRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public RewardDTO getReward(Long rewardId) {
        log.debug("Request to get Reward with id:"+rewardId);
        Reward reward = rewardRepository.findOne(rewardId);
        if (reward == null){
            throw new RewardException("Reward does not exist, id:" + reward);
        }
        return rewardMapper.rewardToRewardDTO(reward);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RewardDTO> getProjectRewards(Long projectId) {
        log.debug("Request to get all Rewards of Project with id:"+projectId);
        Project project = projectRepository.findOne(projectId);
        if (project == null){
            throw new ProjectException("Project does not exist, id:"+projectId);
        }
        return rewardMapper.rewardsToRewardDTOs(project.getRewards());
    }
}
