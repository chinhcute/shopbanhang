package com.example.shopSpringboot.repository;

import com.example.shopSpringboot.Enum.Role;
import com.example.shopSpringboot.entity.AccountEntity;
import com.example.shopSpringboot.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    Set<RoleEntity> findByAccounts_Email(String email);
    @Query(value = "SELECT * FROM role where role.role = ?1", nativeQuery = true)
    Set<RoleEntity> findByRole(String role);
}
