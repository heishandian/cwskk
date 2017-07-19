package com.yaoli.vo.huangkai;

import java.util.Date;

/**
 * Created by kk on 2017/6/22.
 */
public class GoodsAbnormalSearchConditionVO {
      String name;
      String number;
      String origin;
      String status;
      Date starttime;
      Date endtime;
      String page;
      String rows;

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getOrigin() {
        return origin;
    }

    public String getStatus() {
        return status;
    }

    public Date getStarttime() {
        return starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public String getPage() {
        return page;
    }

    public String getRows() {
        return rows;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "GoodsAbnormalSearchConditionVO{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", origin='" + origin + '\'' +
                ", status='" + status + '\'' +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", page='" + page + '\'' +
                ", rows='" + rows + '\'' +
                '}';
    }

    public GoodsAbnormalSearchConditionVO(String name, String number, String origin, String status, Date starttime, Date endtime, String page, String rows) {
        this.name = name;
        this.number = number;
        this.origin = origin;
        this.status = status;
        this.starttime = starttime;
        this.endtime = endtime;
        this.page = page;
        this.rows = rows;
    }
}
