package ua.nenya.service;

import ua.nenya.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getCategories();

    CategoryDTO getCategoryByProjectId(Long projectId);

    CategoryDTO getCategoryById(Long categoryId);

    void deleteCategory(Long id);

    CategoryDTO saveCategory(CategoryDTO categoryDTO);
}
