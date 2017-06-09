package com.yaoli.beans;

import java.util.Date;

public class Goods_abnormal {
    private Integer id;

    private Integer goodsId;

    private String description;

    private Integer goodsAbnormalTypeid;

    private String goodsAbnormalAnalyse;

    private Date goodsAbnormalTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getGoodsAbnormalTypeid() {
        return goodsAbnormalTypeid;
    }

    public void setGoodsAbnormalTypeid(Integer goodsAbnormalTypeid) {
        this.goodsAbnormalTypeid = goodsAbnormalTypeid;
    }

    public String getGoodsAbnormalAnalyse() {
        return goodsAbnormalAnalyse;
    }

    public void setGoodsAbnormalAnalyse(String goodsAbnormalAnalyse) {
        this.goodsAbnormalAnalyse = goodsAbnormalAnalyse == null ? null : goodsAbnormalAnalyse.trim();
    }

    public Date getGoodsAbnormalTime() {
        return goodsAbnormalTime;
    }

    public void setGoodsAbnormalTime(Date goodsAbnormalTime) {
        this.goodsAbnormalTime = goodsAbnormalTime;
    }
}