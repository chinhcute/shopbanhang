package com.example.shopSpringboot.repository;


import com.example.shopSpringboot.Enum.UserStatus;
import com.example.shopSpringboot.entity.AccountEntity;
import com.example.shopSpringboot.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    AccountEntity findByEmailLike(String email);
    AccountEntity findByEmailAndPassword(String email , String password);
    AccountEntity findByEmailLikeAndStatusLike(String email, UserStatus status);

    List<AccountEntity> findByEmail( String email);



}
