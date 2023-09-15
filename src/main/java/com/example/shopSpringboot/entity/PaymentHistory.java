package com.example.shopSpringboot.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "paymentHistorys")
public class PaymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double money;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate day_trading;
    private String Content;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private BankEntity bank;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public LocalDate getDay_trading() {
        return day_trading;
    }

    public void setDay_trading(LocalDate day_trading) {
        this.day_trading = day_trading;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public BankEntity getBank() {
        return bank;
    }

    public void setBank(BankEntity bank) {
        this.bank = bank;
    }
}
