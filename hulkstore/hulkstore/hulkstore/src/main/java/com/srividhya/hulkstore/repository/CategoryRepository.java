package com.srividhya.hulkstore.repository;

import com.srividhya.hulkstore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long > {

    Category findByCategoryId(Long categoryId);
    boolean existsByCategoryName(String categoryName);
    boolean existsByCategoryId(Long categoryId);

}