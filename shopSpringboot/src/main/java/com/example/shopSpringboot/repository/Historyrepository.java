package com.example.shopSpringboot.repository;

import com.example.shopSpringboot.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Historyrepository extends JpaRepository<History , Integer> {
}
