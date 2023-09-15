package com.example.shopSpringboot.repository;

import com.example.shopSpringboot.entity.SearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<SearchEntity, Integer> {
    List<SearchEntity> findByAccount_email(String email);
}
