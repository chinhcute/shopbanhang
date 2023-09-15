package com.example.shopSpringboot.service;

import com.example.shopSpringboot.Enum.UserStatus;
import com.example.shopSpringboot.entity.AccountEntity;
import com.example.shopSpringboot.entity.FailedLoginAttempt;
import com.example.shopSpringboot.repository.AccountRepository;
import com.example.shopSpringboot.repository.FailedLoginAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoginAttemptsServiceImpl  {

    private static final int MAX_ATTEMPTS = 3;
    private static final long BLOCK_DURATION_MINUTES = 1;

    @Autowired
    private FailedLoginAttemptRepository failedLoginAttemptRepository;

    @Autowired
    private AccountRepository accountRepository;


    public void recordFailedLoginAttempt(String email) {
        FailedLoginAttempt failedLoginAttempt = failedLoginAttemptRepository.findByAccount_Email(email);
        AccountEntity account = accountRepository.findByEmailLike(email);
        if (failedLoginAttempt == null) {
            failedLoginAttempt = new FailedLoginAttempt();
            failedLoginAttempt.setAccount(account);
        }

        failedLoginAttempt.setFailedAttempts(failedLoginAttempt.getFailedAttempts() + 1);

        failedLoginAttemptRepository.save(failedLoginAttempt);
    }


    public void unblock(String email) {
        AccountEntity account = accountRepository.findByEmailLike(email);
        if (account != null) {
            account.setStatus(UserStatus.ACTIVE);
            accountRepository.save(account);
        }
        FailedLoginAttempt failedLoginAttempt = failedLoginAttemptRepository.findByAccount_Email(email);
        if (failedLoginAttempt != null) {
            failedLoginAttemptRepository.delete(failedLoginAttempt);
        }
    }


    public void blockAccount(String email) {
        AccountEntity account = accountRepository.findByEmailLike(email);
        if (account != null) {
            account.setStatus(UserStatus.BLOCK);
            accountRepository.save(account);
        }
        FailedLoginAttempt failedLoginAttempt = failedLoginAttemptRepository.findByAccount_Email(email);
        failedLoginAttempt.setBlockedUntil(LocalDateTime.now().plusMinutes(1));
        failedLoginAttemptRepository.save(failedLoginAttempt);
    }
}
