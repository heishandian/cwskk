package com.yaoli.vo.huangkai;

/**
 * Created by kk on 2017/6/22.
 */
public class GoodsAbnormalSearchVO {
    String goodsName;
    String goodsNumber;
    String origin;
    String status;
    String starttime;
    String endtime;

    public String getGoodsName() {
        return goodsName;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getStatus() {
        return status;
    }

    public String getStarttime() {
        return starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    @Override
    public String toString() {
        return "GoodsAbnormalSearchVO{" +
                "goodsName='" + goodsName + '\'' +
                ", goodsNumber='" + goodsNumber + '\'' +
                ", origin='" + origin + '\'' +
                ", status='" + status + '\'' +
                ", starttime='" + starttime + '\'' +
                ", endtime='" + endtime + '\'' +
                '}';
    }
}
