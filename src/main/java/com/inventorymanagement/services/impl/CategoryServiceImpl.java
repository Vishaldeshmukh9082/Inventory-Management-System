package com.inventorymanagement.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventorymanagement.entities.Category;
import com.inventorymanagement.repositories.CategoryRepo;
import com.inventorymanagement.services.CategoryService;

import jakarta.transaction.Transactional;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo catRepo;

    @Override
    public Category AddCategory(Category cat) {
        Category savedCat = null;
        try {
            savedCat = catRepo.save(cat);
        } catch (Exception e) {
            savedCat = null;
        }

        return savedCat;
    }

    @Override
    public Category searchByCategoryId(Integer categoryId) {
        Category cat = null;
        try {
            cat = catRepo.findByCategoryId(categoryId);
        } catch (Exception e) {
            cat = null;
        }

        return cat;
    }

    @Override
    public List<Category> ListAllCategory() {
        List<Category> catList = catRepo.findAll();
        // try {
        //     catList = catRepo.findAll();
        // } catch (Exception e) {
        //     catList = null;
        // }

        return catList;

    }

    @Transactional
    @Override
    public Category editCategory(Integer catId, Category catDetail) {

        Category updatedCat = null;
        try {
            Category cat;
            cat = catRepo.getById(catId);

            System.out.println(cat.getCategoryId() + " data " + cat.getCategoryName());

            cat.setCategoryName(catDetail.getCategoryName());
            cat.setCategoryDescription(catDetail.getCategoryDescription());

            updatedCat = catRepo.save(cat);

            System.out.println(updatedCat.getCategoryName());
        } catch (Exception e) {
            updatedCat = null;
            System.out.println(e.getMessage());
        }

        return updatedCat;
    }

    @Override
    public Category findCategoryByName(String categoryName) {

        Category cat = catRepo.findByCategoryName(categoryName);

        return cat;
    }

    @Override
    public void deleteCategory(Integer catId) {
        try {
            catRepo.deleteById(catId);

        } catch (Exception e) {
        }
    }

}
