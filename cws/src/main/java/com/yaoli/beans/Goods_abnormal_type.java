package com.yaoli.beans;

public class Goods_abnormal_type {
    private Integer id;

    private String name;

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

    public Goods_abnormal_type(String name) {
        this.name = name;
    }
    public Goods_abnormal_type() {
        this.name = name;
    }
}