package ua.nenya.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nenya.domain.Project;
import ua.nenya.domain.Reward;
import ua.nenya.dto.ProjectDTO;
import ua.nenya.exceptions.ProjectException;
import ua.nenya.exceptions.RewardException;
import ua.nenya.mapper.ProjectMapper;
import ua.nenya.repository.ProjectRepository;
import ua.nenya.repository.RewardRepository;
import ua.nenya.service.ProjectService;

import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private static final Logger log = LoggerFactory.getLogger(ProjectServiceImpl.class);

    private final RewardRepository rewardRepository;

    private final ProjectMapper projectMapper;

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(RewardRepository rewardRepository,
                              ProjectMapper projectMapper,
                              ProjectRepository projectRepository) {
        this.rewardRepository = rewardRepository;
        this.projectMapper = projectMapper;
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectDTO saveProject(ProjectDTO projectDTO) {
        log.debug("Request to saveCategory Project: " + projectDTO);
        Project project = projectMapper.projectDtoToProject(projectDTO);
        Project result = projectRepository.saveAndFlush(project);
        return projectMapper.projectToProjectDTO(result);
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectDTO getProjectByReward(Long rewardId) {
        log.debug("Request to get Project by Reward with id:"+rewardId);
        Reward reward = rewardRepository.findOne(rewardId);
        if (reward == null){
            throw new RewardException("Reward does not exist, id:" + rewardId);
        }
        return projectMapper.projectToProjectDTO(reward.getProject());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDTO> getProjectsByCategoryId(Long categoryId) {
        log.debug("Request to get all Projects of Category with id: ");
        List<Project> projects = projectRepository.findAllByCategoryId(categoryId);
        return projectMapper.projectsToProjectDTOs(projects);
    }

    @Override
    public List<ProjectDTO> getAll() {
        log.debug("Request to get all Projects");
        return projectMapper.projectsToProjectDTOs(projectRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectDTO getProjectById(Long projectId) {
        log.debug("Request to get Project with id: " + projectId);
        Project project = projectRepository.findOne(projectId);
        if (project == null){
            throw new ProjectException("Project does not exist, id:" + project);
        }
        return projectMapper.projectToProjectDTO(project);
    }

    @Override
    public void deleteProject(Long projectId) {
        log.debug("Request to deleteCategory Project with id: "+projectId);
        projectRepository.delete(projectId);
    }

}
