package com.example.shopSpringboot.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "failed_login_attempts")
public class FailedLoginAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int failedAttempts;


    private LocalDateTime blockedUntil;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AccountEntity account;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void setFailedAttempts(int failedAttempts) {
        this.failedAttempts = failedAttempts;
    }

    public LocalDateTime getBlockedUntil() {
        return blockedUntil;
    }

    public void setBlockedUntil(LocalDateTime blockedUntil) {
        this.blockedUntil = blockedUntil;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }
}
