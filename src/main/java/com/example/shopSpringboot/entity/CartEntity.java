package com.example.shopSpringboot.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
@Table(name = "carts")
public class CartEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AccountEntity user;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItemEntity> cartItemEntityList;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CartItemEntity> getCartItemEntityList() {
        return cartItemEntityList;
    }

    public void setCartItemEntityList(List<CartItemEntity> cartItemEntityList) {
        this.cartItemEntityList = cartItemEntityList;
    }

    public AccountEntity getUser() {
        return user;
    }

    public void setUser(AccountEntity user) {
        this.user = user;
    }


}
