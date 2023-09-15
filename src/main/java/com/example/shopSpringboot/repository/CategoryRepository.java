package com.example.shopSpringboot.repository;

import com.example.shopSpringboot.entity.CategoryEntity;
import com.example.shopSpringboot.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity findByName (String name);
    CategoryEntity findById(int id);
    @Query(value = "SELECT * FROM categorys\n" +
            "LIMIT ?1", nativeQuery = true)
    List<CategoryEntity> findByLimit(int number);
    @Query(value = "SELECT c.name FROM categorys c \n" +
            "            JOIN products p ON c.id = p.category_id \n" +
            "            GROUP BY c.name \n" +
            "            ORDER BY SUM(p.sold) DESC",nativeQuery = true)
    List<String> findCategoryNamesOrderByTotalSoldDesc();



}
