package com.example.shopSpringboot.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class SearchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String search;
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private AccountEntity account;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
