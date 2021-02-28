package com.srividhya.hulkstore.controller;


import com.srividhya.hulkstore.api.InventoryApi;
import com.srividhya.hulkstore.api.ProductApi;

import com.srividhya.hulkstore.dto.OrderRequest;
import com.srividhya.hulkstore.dto.OrderResponse;
import com.srividhya.hulkstore.entity.Product;

import com.srividhya.hulkstore.exceptions.ItemExistException;
import com.srividhya.hulkstore.exceptions.ItemNotExistException;

import com.srividhya.hulkstore.exceptions.OutOfStockException;
import com.srividhya.hulkstore.service.ProductService;
import com.srividhya.hulkstore.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController

public class ProductController implements ProductApi, InventoryApi {

    @Autowired
    private ProductService productService;

    @Override
    public ResponseEntity<Object> addProduct(Product body) {
        Product productValue = null;
        try {
            productValue = productService.saveProduct(body);
        } catch (ItemExistException e) {
            return ResponseUtils.getItemNotExistResponse(e);
        }
        return new ResponseEntity<>(productValue, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> modifyProduct(Product body) {
        Product productValue = null;
        try {
            productValue = productService.updateProduct(body);
        } catch (ItemNotExistException e) {
            return ResponseUtils.getItemNotExistResponse(e);
        }
        return new ResponseEntity<>(productValue, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> findProductById(Long productId) {
        Product productValue =  productService.findProductById(productId);

        return new ResponseEntity<>(productValue, HttpStatus.OK);
    }

    public ResponseEntity<List<Product>> findAllProducts(String name) {
        List<Product> productValue = null;
        if((name== null) || (name.isEmpty()) ) {
            productValue = productService.findAllProducts();
        }
        else{
             productValue = productService.findProductByName(name);
        }
        return new ResponseEntity<>(productValue, HttpStatus.OK);

    }
    //Inventory Related Operations - when product has been bought update the count
    @Override
    public ResponseEntity<Object> buyProduct(OrderRequest body)  {
        Product updatedProduct  = null;
        try {
            updatedProduct = productService.buyProduct(body);
        } catch (OutOfStockException e) {
            return ResponseUtils.getProductOutOfStockResponse(e);
        }
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setProductId(updatedProduct.getProductId());
        orderResponse.setProductName(updatedProduct.getProductName());
        orderResponse.setOrderSuccessMessage("Your Order has been Placed Successfully!");
        orderResponse.setAvailableProductCount(updatedProduct.getProductCount());
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> addToInventory(OrderRequest body){

        Product updatedProduct  = productService.addToInventory(body);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setProductId(updatedProduct.getProductId());
        orderResponse.setProductName(updatedProduct.getProductName());
        orderResponse.setOrderSuccessMessage("Your Order has been Added/Updated Successfully!");
        orderResponse.setAvailableProductCount(updatedProduct.getProductCount());
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);

    }

}

