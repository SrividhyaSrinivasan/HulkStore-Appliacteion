package com.srividhya.hulkstore.api;

import com.srividhya.hulkstore.entity.Category;
import com.srividhya.hulkstore.exceptions.ItemExistException;
import com.sun.istack.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface CategoryApi {
    //POST method
    @RequestMapping(value = "/category/",
            produces = {  "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Object> addCategory(@RequestBody Category body) throws ItemExistException;

    //PUT
    @RequestMapping(value = "/category/{categoryId}",
            produces = {  "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Object> modifyCategory(@RequestBody Category body);

    //GET by id
    @RequestMapping(value = "/category/{categoryId}",
            produces = {  "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Object> findCategoryById(@NotNull @PathVariable("categoryId") Long categoryId);



    //GET ALL
    @RequestMapping(value = "/category/",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Category>> findAllCategories();
}
