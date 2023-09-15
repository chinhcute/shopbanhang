package com.example.shopSpringboot.confi;

import com.example.shopSpringboot.Enum.UserStatus;
import com.example.shopSpringboot.entity.AccountEntity;
import com.example.shopSpringboot.entity.FailedLoginAttempt;
import com.example.shopSpringboot.repository.AccountRepository;
import com.example.shopSpringboot.repository.FailedLoginAttemptRepository;
import com.example.shopSpringboot.service.LoginAttemptsServiceImpl;
import com.example.shopSpringboot.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LoginAttemptsServiceImpl loginAttemptsService;
    @Autowired
    private FailedLoginAttemptRepository failedLoginAttemptRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        Object credentials = authentication.getCredentials();
        if (credentials == null) {
            throw new BadCredentialsException("Credentials are missing.");
        }
        String password = credentials.toString();



        if (credentials == null || credentials.toString().isEmpty()) {
            throw new BadCredentialsException("Credentials are missing or empty.");
        }



        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        AccountEntity account = accountRepository.findByEmailLike(email);
        if (account == null) {
            throw new BadCredentialsException("Không có tài khoản này");
        }

        if (account.getStatus() == UserStatus.BLOCK && account.getFailedLoginAttempt() != null
                && account.getFailedLoginAttempt().getBlockedUntil().isAfter(LocalDateTime.now())) {
            throw new LockedException("Account is locked. Please try again later.");
        }
        if (account.getStatus() == UserStatus.BLOCK && encoder.matches(password, account.getPassword())
                && !account.getFailedLoginAttempt().getBlockedUntil().isAfter(LocalDateTime.now())) {
           loginAttemptsService.unblock(email);
        }

        if (!encoder.matches(password, account.getPassword())) {
            loginAttemptsService.recordFailedLoginAttempt(email);

            if (account.getFailedLoginAttempt() != null && account.getFailedLoginAttempt().getFailedAttempts() >= 2) {
                loginAttemptsService.blockAccount(email);
                throw new CredentialsExpiredException("Account is blocked due to too many failed login attempts.");
            }

            throw new DisabledException("Mật khẩu k đúng ");
        }


        FailedLoginAttempt failedLoginAttempt = failedLoginAttemptRepository.findByAccount_Email(email);
        if (failedLoginAttempt != null) {
            failedLoginAttemptRepository.delete(failedLoginAttempt);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);


        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }



    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

