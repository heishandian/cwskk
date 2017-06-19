package com.yaoli.vo.huangkai;

import java.util.Date;

/**
 * Created by kk on 2017/6/12.
 */
public class GoodsAbnormal {
    Integer goodsId;
    String goodsName;
    Integer goodsOriginId;
    String goodsNumber;
    String goodsOrigin;
    Integer abnormalTypeId;
    String abnormalType;
    String abnormalDescription;
    String abnormalAnalysis;
    Date abnormalTime;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsOriginId() {
        return goodsOriginId;
    }

    public void setGoodsOriginId(Integer goodsOriginId) {
        this.goodsOriginId = goodsOriginId;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getGoodsOrigin() {
        return goodsOrigin;
    }

    public void setGoodsOrigin(String goodsOrigin) {
        this.goodsOrigin = goodsOrigin;
    }

    public Integer getAbnormalTypeId() {
        return abnormalTypeId;
    }

    public void setAbnormalTypeId(Integer abnormalTypeId) {
        this.abnormalTypeId = abnormalTypeId;
    }

    public String getAbnormalType() {
        return abnormalType;
    }

    public void setAbnormalType(String abnormalType) {
        this.abnormalType = abnormalType;
    }

    public String getAbnormalDescription() {
        return abnormalDescription;
    }

    public void setAbnormalDescription(String abnormalDescription) {
        this.abnormalDescription = abnormalDescription;
    }

    public Date getAbnormalTime() {
        return abnormalTime;
    }

    public void setAbnormalTime(Date abnormalTime) {
        this.abnormalTime = abnormalTime;
    }

    public String getAbnormalAnalysis() {
        return abnormalAnalysis;
    }

    public void setAbnormalAnalysis(String abnormalAnalysis) {
        this.abnormalAnalysis = abnormalAnalysis;
    }
}
