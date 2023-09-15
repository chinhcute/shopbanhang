package com.example.shopSpringboot.entity;

import com.example.shopSpringboot.Enum.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "role")
public class RoleEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(mappedBy = "userRoles")
    private Set<AccountEntity> accounts;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<AccountEntity> getUsers() {
        return accounts;
    }

    public void setUsers(Set<AccountEntity> users) {
        this.accounts = users;
    }

}
