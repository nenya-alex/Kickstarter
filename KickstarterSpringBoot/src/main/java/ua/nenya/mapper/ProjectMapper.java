package ua.nenya.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.nenya.domain.Category;
import ua.nenya.domain.Project;
import ua.nenya.dto.ProjectDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ProjectMapper {

    @Mapping(target = "availableAmount", expression = "java(getAvailableAmount(project))")
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "createdById", source = "createdBy.id")
    ProjectDTO projectToProjectDTO(Project project);

    List<ProjectDTO> projectsToProjectDTOs(List<Project> projects);

    @Mapping(target = "category", source = "categoryId")
    @Mapping(target = "createdBy", source = "createdById")
    Project projectDtoToProject(ProjectDTO projectDTO);

    default Category categoryFromId(Long id) {
        if (id == null) {
            return null;
        }
        Category category = new Category();
        category.setId(id);
        return category;
    }

    default  Long getAvailableAmount(Project project) {
        return project.getPayments().stream().mapToLong(payment -> payment.getAmount()).sum();
    }
}
