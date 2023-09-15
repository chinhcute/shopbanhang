package com.example.shopSpringboot.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "reviews")
public class ReviewEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double rating;

    @Size(min = 5, message = "Bình luận phải chứa ít nhất 5 kí tự.")
    private String comment;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private ProductEntity product;
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private AccountEntity account;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }
}
