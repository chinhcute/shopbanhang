package com.example.shopSpringboot.repository;

import com.example.shopSpringboot.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, Integer> {
    @Query(value = "SELECT * FROM banks b join accounts a on a.id = b.account_id where a.email = ?1",
            nativeQuery = true)
    List<BankEntity> findByListBank(String email);
    @Query(value = "SELECT * FROM banks where banks.account_number = ?1", nativeQuery = true)
   BankEntity findByAccount_number(String account_number);
}
