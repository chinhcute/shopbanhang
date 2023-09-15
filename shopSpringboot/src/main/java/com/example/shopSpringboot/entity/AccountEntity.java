package com.example.shopSpringboot.entity;

import com.example.shopSpringboot.Enum.Sex;

import com.example.shopSpringboot.Enum.UserStatus;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "accounts")
public class AccountEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, length = 100)
    private String email;

    private String password;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private String address;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "account_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<RoleEntity> userRoles;
    @OneToMany(mappedBy = "admin" , cascade =  CascadeType.ALL)
    private List<ProductEntity> productEntityList;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<BankEntity> bankEntityList;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CartEntity cart;
    @OneToMany(mappedBy = "user", cascade =  CascadeType.ALL)
    private List<OrderEntity> orderEntityList;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<ReviewEntity> reviewEntityList;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<SearchEntity> searchEntityList;
    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private FailedLoginAttempt failedLoginAttempt;

    public List<ReviewEntity> getReviewEntityList() {
        return reviewEntityList;
    }

    public void setReviewEntityList(List<ReviewEntity> reviewEntityList) {
        this.reviewEntityList = reviewEntityList;
    }

    public AccountEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<RoleEntity> userRoles) {
        this.userRoles = userRoles;
    }



    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<ProductEntity> getProductEntityList() {
        return productEntityList;
    }

    public void setProductEntityList(List<ProductEntity> productEntityList) {
        this.productEntityList = productEntityList;
    }

    public List<BankEntity> getBankEntityList() {
        return bankEntityList;
    }

    public void setBankEntityList(List<BankEntity> bankEntityList) {
        this.bankEntityList = bankEntityList;
    }

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }

    public List<OrderEntity> getOrderEntityList() {
        return orderEntityList;
    }

    public void setOrderEntityList(List<OrderEntity> orderEntityList) {
        this.orderEntityList = orderEntityList;
    }

    public List<SearchEntity> getSearchEntityList() {
        return searchEntityList;
    }

    public void setSearchEntityList(List<SearchEntity> searchEntityList) {
        this.searchEntityList = searchEntityList;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public FailedLoginAttempt getFailedLoginAttempt() {
        return failedLoginAttempt;
    }

    public void setFailedLoginAttempt(FailedLoginAttempt failedLoginAttempt) {
        this.failedLoginAttempt = failedLoginAttempt;
    }
}

