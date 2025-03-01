package com.inventorymanagement.services;

import java.util.List;

import com.inventorymanagement.entities.Category;

public interface CategoryService {

    public Category AddCategory( Category cat);
  
    public Category searchByCategoryId( Integer categoryId);
    public List<Category> ListAllCategory();
    public Category editCategory(Integer catId,Category cat);
    public Category findCategoryByName(String categoryName);
    public void deleteCategory(Integer catId);
}
