package com.example.shopSpringboot.entity;

import com.example.shopSpringboot.Enum.PaymentType;
import com.example.shopSpringboot.Enum.ProductStatus;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Vui lòng nhập họ và tên")
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate CreatedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ShippedDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @NotEmpty(message = "vui lòng nhập số điện thoại")
    @Pattern(regexp = "\\d{10}", message = "Vui lòng nhập lại , nhập 10 số !")
    private String phone;
    @NotEmpty(message = "Vui lòng nhập địa chỉ")
    private String ShippingAddress;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private AccountEntity user;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Oder_detailEntity> oderDetailEntityList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        CreatedDate = createdDate;
    }

    public LocalDate getShippedDate() {
        return ShippedDate;
    }

    public void setShippedDate(LocalDate shippedDate) {
        ShippedDate = shippedDate;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShippingAddress() {
        return ShippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        ShippingAddress = shippingAddress;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public AccountEntity getUser() {
        return user;
    }

    public void setUser(AccountEntity user) {
        this.user = user;
    }

    public List<Oder_detailEntity> getOderDetailEntityList() {
        return oderDetailEntityList;
    }

    public void setOderDetailEntityList(List<Oder_detailEntity> oderDetailEntityList) {
        this.oderDetailEntityList = oderDetailEntityList;
    }
}
