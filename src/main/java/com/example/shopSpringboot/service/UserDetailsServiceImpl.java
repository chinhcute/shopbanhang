package com.example.shopSpringboot.service;

import com.example.shopSpringboot.Enum.UserStatus;
import com.example.shopSpringboot.entity.AccountEntity;
import com.example.shopSpringboot.entity.RoleEntity;
import com.example.shopSpringboot.repository.AccountRepository;
import com.example.shopSpringboot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AccountEntity account = accountRepository.findByEmailLikeAndStatusLike(email, UserStatus.ACTIVE);
        if (account == null) {
            throw new UsernameNotFoundException(email);
        }

        Set<RoleEntity> roleEntitySet = roleRepository.findByAccounts_Email(email);
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        if (!CollectionUtils.isEmpty(roleEntitySet)) {
            for (RoleEntity role : roleEntitySet) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRole().toString());
                grantedAuthoritySet.add(grantedAuthority);
            }
        }

        return new User(account.getEmail(), account.getPassword(), grantedAuthoritySet);
    }
}
