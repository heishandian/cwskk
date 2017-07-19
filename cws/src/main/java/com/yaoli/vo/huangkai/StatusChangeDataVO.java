package com.yaoli.vo.huangkai;

/**
 * Created by kk on 2017/6/20.
 */
public class StatusChangeDataVO {
    Integer currentGoodsId;
    String goodsName;
    String goodsNumber;
    Integer currentStateId;
    String currentState;
    Integer changeStatusId;
    String changeStatus;
    Integer abnormalId;
    String abnormalAnalysis;

    public Integer getCurrentGoodsId() {
        return currentGoodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public Integer getCurrentStateId() {
        return currentStateId;
    }

    public String getCurrentState() {
        return currentState;
    }

    public Integer getChangeStatusId() {
        return changeStatusId;
    }

    public String getChangeStatus() {
        return changeStatus;
    }

    public Integer getAbnormalId() {
        return abnormalId;
    }

    public String getAbnormalAnalysis() {
        return abnormalAnalysis;
    }

    public void setCurrentGoodsId(Integer currentGoodsId) {
        this.currentGoodsId = currentGoodsId;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public void setCurrentStateId(Integer currentStateId) {
        this.currentStateId = currentStateId;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public void setChangeStatusId(Integer changeStatusId) {
        this.changeStatusId = changeStatusId;
    }

    public void setChangeStatus(String changeStatus) {
        this.changeStatus = changeStatus;
    }

    public void setAbnormalId(Integer abnormalId) {
        this.abnormalId = abnormalId;
    }

    public void setAbnormalAnalysis(String abnormalAnalysis) {
        this.abnormalAnalysis = abnormalAnalysis;
    }

    @Override
    public String toString() {
        return "StatusChangeDataVO{" +
                "currentGoodsId=" + currentGoodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsNumber='" + goodsNumber + '\'' +
                ", currentStateId=" + currentStateId +
                ", currentState='" + currentState + '\'' +
                ", changeStatusId=" + changeStatusId +
                ", changeStatus='" + changeStatus + '\'' +
                ", abnormalId='" + abnormalId + '\'' +
                ", abnormalAnalysis='" + abnormalAnalysis + '\'' +
                '}';
    }
}
