package ua.nenya.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ua.nenya.dao.CategoryDao;
import ua.nenya.domain.Category;



@RestController
public class CategoriesRestController {

	@Autowired
	private CategoryDao categoryDao;

	private static final Logger logger = LoggerFactory.getLogger(CategoriesRestController.class);

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public List<Category> getAllCategories() {
		return categoryDao.getCategories();
	}
	
	@RequestMapping(value = "/category/project/{projectId}", method = RequestMethod.GET)
	public Category getCategoryByProjectId(@PathVariable("projectId") Long projectId) {
		return categoryDao.getCategoryByProjectId(projectId);
	}

	@RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
	public Category getCategory(@PathVariable("categoryId") Long categoryId) {
		if (!categoryDao.isCategoryExistById(categoryId)) {
			logger.error("Category with id " + categoryId + " dosen't exist!");
			return new Category();
		}
		Category category = categoryDao.getCategoryByCategoryId(categoryId);
		return category;
	}                                      
}
