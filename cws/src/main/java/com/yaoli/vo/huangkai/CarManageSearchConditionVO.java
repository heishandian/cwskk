package com.yaoli.vo.huangkai;

/**
 * Created by kk on 2017/7/5.
 */
public class CarManageSearchConditionVO {
    String carnumber;
    String starttime;
    String endtime;
    Integer page;
    Integer rows;

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public CarManageSearchConditionVO() {
    }

    public CarManageSearchConditionVO(String carnumber, String starttime, String endtime, Integer page, Integer rows) {
        this.carnumber = carnumber;
        this.starttime = starttime;
        this.endtime = endtime;
        this.page = page;
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "CarManageSearchConditionVO{" +
                "carnumber='" + carnumber + '\'' +
                ", starttime='" + starttime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", page=" + page +
                ", rows=" + rows +
                '}';
    }
}
