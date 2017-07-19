package com.yaoli.beans;

import java.math.BigDecimal;

public class CarManage {
    private Integer id;

    private Integer gorupid;

    private String carnumber;

    private String time;

    private BigDecimal startkm;

    private BigDecimal endkm;

    private String driver;

    private String application;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGorupid() {
        return gorupid;
    }

    public void setGorupid(Integer gorupid) {
        this.gorupid = gorupid;
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber == null ? null : carnumber.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BigDecimal getStartkm() {
        return startkm;
    }

    public void setStartkm(BigDecimal startkm) {
        this.startkm = startkm;
    }

    public BigDecimal getEndkm() {
        return endkm;
    }

    public void setEndkm(BigDecimal endkm) {
        this.endkm = endkm;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver == null ? null : driver.trim();
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application == null ? null : application.trim();
    }

    @Override
    public String toString() {
        return "CarManage{" +
                "id=" + id +
                ", gorupid=" + gorupid +
                ", carnumber='" + carnumber + '\'' +
                ", time='" + time + '\'' +
                ", startkm=" + startkm +
                ", endkm=" + endkm +
                ", driver='" + driver + '\'' +
                ", application='" + application + '\'' +
                '}';
    }
}