package com.example.shopSpringboot.service;

import com.example.shopSpringboot.Enum.Role;

import com.example.shopSpringboot.Enum.UserStatus;
import com.example.shopSpringboot.entity.AccountEntity;
import com.example.shopSpringboot.entity.RoleEntity;
import com.example.shopSpringboot.repository.AccountRepository;
import com.example.shopSpringboot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;
    public AccountEntity saveAccount(AccountEntity accountEntity) {
        AccountEntity account = new AccountEntity();

        account.setEmail(accountEntity.getEmail());

        Set<RoleEntity> roles = roleRepository.findByRole(Role.ROLE_USER.name());
        if (roles.isEmpty()){
            RoleEntity role = new RoleEntity();
            role.setRole(Role.ROLE_USER);
            roleRepository.save(role);
            Set<RoleEntity> roleEntitySet = new HashSet<>();
            roleEntitySet.add(role);
            account.setUserRoles(roleEntitySet);
        }else {
            account.setUserRoles(roles);
        }


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(accountEntity.getPassword());
        account.setPassword(encodedPassword);
        account.setStatus(UserStatus.ACTIVE);
        account.setBirthday(accountEntity.getBirthday());
        account.setSex(accountEntity.getSex());
        account.setAddress(accountEntity.getAddress());

        return accountRepository.save(account);
    }
    public AccountEntity saveAdmin(AccountEntity accountEntity) {
        AccountEntity account = new AccountEntity();

        account.setEmail(accountEntity.getEmail());




        Set<RoleEntity> roles = roleRepository.findByRole(Role.ROLE_ADMIN.name());
        if (roles.isEmpty()){
            RoleEntity role = new RoleEntity();
            role.setRole(Role.ROLE_ADMIN);
            roleRepository.save(role);
            Set<RoleEntity> roleEntitySet = new HashSet<>();
            roleEntitySet.add(role);
            account.setUserRoles(roleEntitySet);
        }else {
            account.setUserRoles(roles);
        }


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(accountEntity.getPassword());
        account.setPassword(encodedPassword);
        account.setStatus(UserStatus.ACTIVE);
        account.setBirthday(accountEntity.getBirthday());
        account.setSex(accountEntity.getSex());
        account.setAddress(accountEntity.getAddress());

        return accountRepository.save(account);
    }
}
