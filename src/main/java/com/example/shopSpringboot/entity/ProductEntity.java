package com.example.shopSpringboot.entity;





import com.fasterxml.jackson.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name ="products")

public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "vui lòng nhập tên sản phẩm")
    private String name;

    @Positive(message = "vui long nhập giá tiền")
    private double price;

    @NotEmpty(message = "vui lòng nhập chi tiết sản phẩm")
    @Column(length = 500)
    private String description;

    @PositiveOrZero(message = "vui lòng nhập lại số lượng")
    @NotNull(message = "vui lòng nhập số lượng")
    private int quantity;

    @Min(value = 0, message = "vui lòng nhập lại mã giảm giá")
    @NotNull(message = "vui lòng nhập mã giảm giá")
    private int discount;

    private double totalAmount;

    private int sold;
    @Lob
    private byte [] qr;


    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;


    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private ImageEntity image;
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_Id")
    private AccountEntity admin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")

    private List<ReviewEntity> reviewEntityList;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<CartItemEntity> cartItemEntityList;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public List<ReviewEntity> getReviewEntityList() {
        return reviewEntityList;
    }

    public void setReviewEntityList(List<ReviewEntity> reviewEntityList) {
        this.reviewEntityList = reviewEntityList;
    }

    public AccountEntity getAdmin() {
        return admin;
    }

    public void setAdmin(AccountEntity admin) {
        this.admin = admin;
    }

    public List<CartItemEntity> getCartItemEntityList() {
        return cartItemEntityList;
    }

    public void setCartItemEntityList(List<CartItemEntity> cartItemEntityList) {
        this.cartItemEntityList = cartItemEntityList;
    }

    public List<Oder_detailEntity> getOderDetailEntityList() {
        return oderDetailEntityList;
    }

    public void setOderDetailEntityList(List<Oder_detailEntity> oderDetailEntityList) {
        this.oderDetailEntityList = oderDetailEntityList;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public byte[] getQr() {
        return qr;
    }

    public void setQr(byte[] qr) {
        this.qr = qr;
    }
}
