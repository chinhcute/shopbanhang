package com.example.shopSpringboot.repository;

import com.example.shopSpringboot.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItemEntity, Integer> {
}
