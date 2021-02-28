package com.srividhya.hulkstore.repository;

import com.srividhya.hulkstore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long > {

    Product findByProductId(Long productId);
    List<Product> findByProductNameContaining(String productName);
    boolean existsByProductName(String productName);
    boolean existsByProductId(Long productId);

    // To get the inventory count to update on purchase of the product


}