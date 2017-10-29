package ua.nenya.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.nenya.dto.CategoryDTO;
import ua.nenya.dto.ProjectDTO;
import ua.nenya.exceptions.CategoryException;
import ua.nenya.service.CategoryService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/categories")
    public List<CategoryDTO> getAllCategories() {
        log.debug("REST request to get all Categories");
        return categoryService.getCategories();
    }

    @GetMapping("/categories-project/{projectId}")
    public ResponseEntity<CategoryDTO> getCategoryByProjectId(@PathVariable("projectId") Long projectId) {
        log.debug("REST request to get Category by Project Id:" + projectId);
        CategoryDTO categoryDTO = categoryService.getCategoryByProjectId(projectId);
        return ResponseEntity.ok().body(categoryDTO);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("categoryId") Long categoryId) {
        log.debug("REST request to get Category by Id:" + categoryId);
        CategoryDTO categoryDTO = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok().body(categoryDTO);
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        log.debug("REST request to create Category:" + categoryDTO);
        if (categoryDTO.getId() != null){
            throw new CategoryException("Category with not null id cannot be created, id: " + categoryDTO.getId());
        }
        CategoryDTO result = categoryService.saveCategory(categoryDTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/categories")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        log.debug("REST request to update Category:" + categoryDTO);
        if (categoryDTO.getId() == null){
            createCategory(categoryDTO);
        }
        CategoryDTO result = categoryService.saveCategory(categoryDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
        log.debug("REST request to delete Category with id: " + id);
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

}
