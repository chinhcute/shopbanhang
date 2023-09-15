package com.example.shopSpringboot.entity;

import javax.persistence.*;

@Entity
@Table(name = "historys")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_detailId")
    private Oder_detailEntity oderDetail;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Oder_detailEntity getOderDetail() {
        return oderDetail;
    }

    public void setOderDetail(Oder_detailEntity oderDetail) {
        this.oderDetail = oderDetail;
    }
}
