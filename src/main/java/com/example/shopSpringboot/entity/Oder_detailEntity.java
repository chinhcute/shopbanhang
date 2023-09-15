package com.example.shopSpringboot.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "oder_details")
public class Oder_detailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oder_id")
    private OrderEntity order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    @OneToOne(mappedBy = "oderDetail", cascade = CascadeType.ALL)
    private History history;
    private int quantity;
    private double totalAllAmount;

    public double getTotalAllAmount() {
        return totalAllAmount;
    }

    public void setTotalAllAmount(double totalAllAmount) {
        this.totalAllAmount = totalAllAmount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }
}
