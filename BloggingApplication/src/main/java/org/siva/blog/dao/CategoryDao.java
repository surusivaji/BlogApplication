package org.siva.blog.dao;

import java.util.List;

import org.siva.blog.entities.Category;

public interface CategoryDao {
	
	int addCategory(String name, String description);
	
	List<Category> getAllCategories();

}
