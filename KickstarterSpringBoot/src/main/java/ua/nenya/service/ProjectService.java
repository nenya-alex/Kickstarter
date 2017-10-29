package ua.nenya.service;

import ua.nenya.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {

    ProjectDTO getProjectByReward(Long rewardId);

    List<ProjectDTO> getProjectsByCategoryId(Long categoryId);

    List<ProjectDTO> getAll();

    void deleteProject(Long projectId);

    ProjectDTO getProjectById(Long projectId);

    ProjectDTO saveProject(ProjectDTO projectDTO);

}
