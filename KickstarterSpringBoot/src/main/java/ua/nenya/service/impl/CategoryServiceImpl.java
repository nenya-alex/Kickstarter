package ua.nenya.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.nenya.domain.Category;
import ua.nenya.domain.Project;
import ua.nenya.dto.CategoryDTO;
import ua.nenya.exceptions.CategoryException;
import ua.nenya.exceptions.ProjectException;
import ua.nenya.mapper.CategoryMapper;
import ua.nenya.repository.CategoryRepository;
import ua.nenya.repository.ProjectRepository;
import ua.nenya.service.CategoryService;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    private final ProjectRepository projectRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               CategoryMapper categoryMapper,
                               ProjectRepository projectRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.projectRepository = projectRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> getCategories() {
        log.debug("Request to get all Categories");
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.categoriesToCategoryDTOs(categories);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDTO getCategoryByProjectId(Long projectId) {
        log.debug("Request to get Category by project id:"+projectId);
        Project project = projectRepository.findOne(projectId);
        if (project == null){
            throw new ProjectException("Project does not exist, id:"+projectId);
        }
        return categoryMapper.categoryToCategoryDTO(project.getCategory());
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDTO getCategoryById(Long categoryId) {
        log.debug("Request to get Category by id:"+categoryId);
        Category category = categoryRepository.findOne(categoryId);
        if (category == null){
            throw new CategoryException("Category does not exist, id:"+categoryId);
        }
        return categoryMapper.categoryToCategoryDTO(category);
    }

    @Override
    public void deleteCategory(Long id) {
        log.debug("Request to delete Category by id:" + id);
        categoryRepository.delete(id);
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        log.debug("Request to save Category: " + categoryDTO);
        Category category = categoryMapper.categoryDtoToCategory(categoryDTO);
        Category result = categoryRepository.saveAndFlush(category);
        return categoryMapper.categoryToCategoryDTO(result);
    }
}
