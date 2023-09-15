package com.example.shopSpringboot.repository;

import com.example.shopSpringboot.entity.FailedLoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FailedLoginAttemptRepository extends JpaRepository<FailedLoginAttempt, Integer> {

    FailedLoginAttempt findByAccount_Email(String email);
}
