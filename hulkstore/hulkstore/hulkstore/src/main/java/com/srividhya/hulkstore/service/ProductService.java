package com.srividhya.hulkstore.service;

import com.srividhya.hulkstore.dto.OrderRequest;
import com.srividhya.hulkstore.entity.Product;
import com.srividhya.hulkstore.exceptions.ItemExistException;
import com.srividhya.hulkstore.exceptions.ItemNotExistException;
import com.srividhya.hulkstore.exceptions.OutOfStockException;
import com.srividhya.hulkstore.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Integer.sum;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) throws ItemExistException {
        log.info("Inside saveProduct of ProductService");
        boolean isCategoryExist = productRepository.existsByProductName(product.getProductName());
        if(isCategoryExist){
            throw new ItemExistException("Product With the Name Already Exist!");

        }
        else {
            return productRepository.save(product);
        }
    }

    public Product updateProduct(Product product) throws ItemNotExistException {
        log.info("Inside updateProduct of productService");

        boolean isProductExist = productRepository.existsByProductId(product.getProductId());
        if(isProductExist){
            return productRepository.save(product);
        }
        else {
            throw new ItemNotExistException("product With the Id Doesn't Exist!");
        }
    }

    public Product findProductById(Long productId) {

        log.info("Inside findProductById of ProductService");
        return productRepository.findByProductId(productId);
    }

    public List<Product> findAllProducts() {
        log.info("Inside findAllProduct of ProductService");
        return productRepository.findAll();
    }

    public List<Product> findProductByName(String productName){

        log.info("Inside productByName of ProductService ");
        return productRepository.findByProductNameContaining(productName);
    }
    // Inventory count update on purchase of a product

    public Product buyProduct(OrderRequest orderRequest) throws OutOfStockException {
        log.info("Inside updateCount of productService");

        //count from the input
        Integer orderCount = orderRequest.getProductCount();
        log.info("input count:" +orderCount.toString());

        // count from the database
         Product dbProduct= productRepository.findByProductId(orderRequest.getProductId());
         Integer stockCount = dbProduct.getProductCount();
         log.info("db count:" +stockCount.toString());

         Integer newStockCount = stockCount - orderCount;
        log.info("newStockCount count:" +newStockCount.toString());


        if (newStockCount >= 0) {
            //Updating the Stock Count in the db
            dbProduct.setProductCount(newStockCount);
            return productRepository.save(dbProduct);

        } else {
            throw new OutOfStockException("Product Out Of Stock!");

        }

    }

    public Product addToInventory(OrderRequest orderRequest) {
        log.info("Inside addToInventory of productService");

        //count from the input
        Integer inputInventoryCount = orderRequest.getProductCount();
        log.info("AddInventory count:" +inputInventoryCount.toString());

        // count from the database
        Product dbInventory= productRepository.findByProductId(orderRequest.getProductId());
        Integer dbInventoryCount = dbInventory.getProductCount();
        log.info("DB Inventory count:" +dbInventoryCount.toString());

        Integer newInventoryCount = inputInventoryCount + dbInventoryCount;
        log.info("newStockAddInventoryCount count:" +newInventoryCount.toString());
        dbInventory.setProductCount(newInventoryCount);
        return productRepository.save(dbInventory);
    }
}
