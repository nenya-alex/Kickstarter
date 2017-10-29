package ua.nenya.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.nenya.domain.Category;
import ua.nenya.dto.CategoryDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CategoryMapper {

    @Mapping(target = "createdById", source = "createdBy.id")
    @Mapping(target = "updatedById", source = "updatedBy.id")
    CategoryDTO categoryToCategoryDTO(Category category);

    List<CategoryDTO> categoriesToCategoryDTOs(List<Category> categories);

    @Mapping(target = "createdBy", source = "createdById")
    @Mapping(target = "updatedBy", source = "updatedById")
    Category categoryDtoToCategory(CategoryDTO categoryDTO);
}
