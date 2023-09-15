package com.example.shopSpringboot.repository;

import com.example.shopSpringboot.entity.Oder_detailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<Oder_detailEntity, Integer> {
}
