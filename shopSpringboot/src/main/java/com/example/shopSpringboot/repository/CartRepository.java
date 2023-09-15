package com.example.shopSpringboot.repository;

import com.example.shopSpringboot.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    @Query(value = "SELECT * FROM accounts a join carts c on a.id = c.user_id \n" +
            "where a.email = ?1", nativeQuery = true)
    CartEntity findByCart(String email);
}
