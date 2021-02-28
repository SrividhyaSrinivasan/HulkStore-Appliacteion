package com.srividhya.hulkstore.api;

import com.srividhya.hulkstore.dto.OrderRequest;
import com.srividhya.hulkstore.entity.Product;

import com.srividhya.hulkstore.exceptions.ItemExistException;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface InventoryApi {

    //PUT to update the inventory count
    @RequestMapping(value = "/buyProduct/",
            produces = {  "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Object> buyProduct(@RequestBody OrderRequest body) ;

    //POST method to add to inventory
     @RequestMapping(value = "/addToInventory/",
          produces = {  "application/json" },
          method = RequestMethod.POST)
     ResponseEntity<Object> addToInventory(@RequestBody OrderRequest body) throws ItemExistException;

}
