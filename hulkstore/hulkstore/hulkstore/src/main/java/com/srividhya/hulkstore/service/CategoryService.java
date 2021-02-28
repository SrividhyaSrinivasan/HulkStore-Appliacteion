package com.srividhya.hulkstore.service;

import com.srividhya.hulkstore.entity.Category;
import com.srividhya.hulkstore.exceptions.ItemExistException;
import com.srividhya.hulkstore.exceptions.ItemNotExistException;
import com.srividhya.hulkstore.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveCategory(Category category) throws ItemExistException {
        log.info("Inside saveCategory of CategoryService");
        boolean isCategoryExist = categoryRepository.existsByCategoryName(category.getCategoryName());
        if(isCategoryExist){
            throw new ItemExistException("Category With the Name Already Exist!");

        }
        else {
            return categoryRepository.save(category);
        }
    }

    public Category updateCategory(Category category) throws ItemNotExistException {
        log.info("Inside updateCategory of CategoryService");

        boolean isCategoryExist = categoryRepository.existsByCategoryId(category.getCategoryId());
        if(isCategoryExist){
            return categoryRepository.save(category);
        }
        else {
            throw new ItemNotExistException("Category With the Id Doesn't Exist!");
        }
    }

    public Category findCategoryById(Long categoryId) throws ItemNotExistException {
        log.info("Inside findCategoryById of CategoryService");
        Category category = categoryRepository.findByCategoryId(categoryId);

        if (category == null)  {
            throw new ItemNotExistException("Category With the Id Doesn't Exist!");
        }
        return category;
    }

    public List<Category> findAllCategories() {
        log.info("Inside findAllCategories of CategoryService");
        return categoryRepository.findAll();
    }
}