package com.yaoli.beans;

import java.util.Date;

public class Goods {
    private Integer id;

    private String name;

    private String number;

    private Integer goodsStatusId;

    private Integer goodsOriginId;

    private Date goodsStatusChangetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Integer getGoodsStatusId() {
        return goodsStatusId;
    }

    public void setGoodsStatusId(Integer goodsStatusId) {
        this.goodsStatusId = goodsStatusId;
    }

    public Integer getGoodsOriginId() {
        return goodsOriginId;
    }

    public void setGoodsOriginId(Integer goodsOriginId) {
        this.goodsOriginId = goodsOriginId;
    }

    public Date getGoodsStatusChangetime() {
        return goodsStatusChangetime;
    }

    public void setGoodsStatusChangetime(Date goodsStatusChangetime) {
        this.goodsStatusChangetime = goodsStatusChangetime;
    }

    public Goods(String name, String number, Integer goodsStatusId, Integer goodsOriginId, Date goodsStatusChangetime) {
        this.name = name;
        this.number = number;
        this.goodsStatusId = goodsStatusId;
        this.goodsOriginId = goodsOriginId;
        this.goodsStatusChangetime = goodsStatusChangetime;
    }

    public Goods(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public Goods() {

    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", goodsStatusId=" + goodsStatusId +
                ", goodsOriginId=" + goodsOriginId +
                ", goodsStatusChangetime=" + goodsStatusChangetime +
                '}';
    }
}