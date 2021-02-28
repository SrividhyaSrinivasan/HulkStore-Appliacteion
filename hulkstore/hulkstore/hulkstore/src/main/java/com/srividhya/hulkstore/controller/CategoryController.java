package com.srividhya.hulkstore.controller;

import com.srividhya.hulkstore.api.CategoryApi;
import com.srividhya.hulkstore.entity.Category;

import com.srividhya.hulkstore.exceptions.ItemExistException;
import com.srividhya.hulkstore.exceptions.ItemNotExistException;
import com.srividhya.hulkstore.service.CategoryService;

import com.srividhya.hulkstore.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class CategoryController implements CategoryApi {

    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseEntity<Object> addCategory(Category body) {
        Category categoryValue = null;
        try {
            categoryValue = categoryService.saveCategory(body);
        } catch (ItemExistException e) {
            return ResponseUtils.getItemExistResponse(e);
        }
        return new ResponseEntity<>(categoryValue, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> modifyCategory(Category body) {
        Category categoryValue = null;
        try {
            categoryValue = categoryService.updateCategory(body);
        } catch (ItemNotExistException e) {
            return ResponseUtils.getItemNotExistResponse(e);
        }

        return new ResponseEntity<>(categoryValue, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Object> findCategoryById(Long categoryId) {
        Category categoryValue = null;
        try {
            categoryValue = categoryService.findCategoryById(categoryId);
        } catch (ItemNotExistException e) {
            return ResponseUtils.getItemNotExistResponse(e);
        }

        return new ResponseEntity<>(categoryValue, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Category>> findAllCategories() {
        List<Category> categoryValue = categoryService.findAllCategories();
        return new ResponseEntity<>(categoryValue, HttpStatus.OK);
    }
}
