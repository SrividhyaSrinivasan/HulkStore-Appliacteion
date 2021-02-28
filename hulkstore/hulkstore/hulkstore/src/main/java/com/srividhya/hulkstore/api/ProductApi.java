package com.srividhya.hulkstore.api;

import com.srividhya.hulkstore.entity.Product;
import com.srividhya.hulkstore.exceptions.ItemExistException;
import com.sun.istack.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface ProductApi {
    //POST method
    @RequestMapping(value = "/product/",
            produces = {  "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Object> addProduct(@RequestBody Product body) throws ItemExistException;

    //PUT
    @RequestMapping(value = "/product/{productId}",
            produces = {  "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Object> modifyProduct(@RequestBody Product body);

    //GET by id
    @RequestMapping(value = "/product/{productId}",
            produces = {  "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Product> findProductById(@NotNull @PathVariable("productId") Long productId);

    //GET ALL
    @RequestMapping(value = "/product/",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Product>> findAllProducts(@RequestParam(required = false) String name);

}
