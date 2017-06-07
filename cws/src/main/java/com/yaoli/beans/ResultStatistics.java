package com.yaoli.beans;

public class ResultStatistics {
    private Long id;

    private Integer sewageid;

    private String sewagename;

    private String year;

    private String month;

    private String day;

    private Double waterflow;

    private Float reducecod;

    private Float reducenh3n;

    private Float reducep;

    private Integer pumpruntime;

    private Float water;

    private Float cod;

    private Float nh3n;

    private Float p;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSewageid() {
        return sewageid;
    }

    public void setSewageid(Integer sewageid) {
        this.sewageid = sewageid;
    }

    public String getSewagename() {
        return sewagename;
    }

    public void setSewagename(String sewagename) {
        this.sewagename = sewagename == null ? null : sewagename.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day == null ? null : day.trim();
    }

    public Double getWaterflow() {
        return waterflow;
    }

    public void setWaterflow(Double waterflow) {
        this.waterflow = waterflow;
    }

    public Float getReducecod() {
        return reducecod;
    }

    public void setReducecod(Float reducecod) {
        this.reducecod = reducecod;
    }

    public Float getReducenh3n() {
        return reducenh3n;
    }

    public void setReducenh3n(Float reducenh3n) {
        this.reducenh3n = reducenh3n;
    }

    public Float getReducep() {
        return reducep;
    }

    public void setReducep(Float reducep) {
        this.reducep = reducep;
    }

    public Integer getPumpruntime() {
        return pumpruntime;
    }

    public void setPumpruntime(Integer pumpruntime) {
        this.pumpruntime = pumpruntime;
    }

    public Float getWater() {
        return water;
    }

    public void setWater(Float water) {
        this.water = water;
    }

    public Float getCod() {
        return cod;
    }

    public void setCod(Float cod) {
        this.cod = cod;
    }

    public Float getNh3n() {
        return nh3n;
    }

    public void setNh3n(Float nh3n) {
        this.nh3n = nh3n;
    }

    public Float getP() {
        return p;
    }

    public void setP(Float p) {
        this.p = p;
    }
}