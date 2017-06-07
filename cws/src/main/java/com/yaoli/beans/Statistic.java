package com.yaoli.beans;

import java.util.Date;

public class Statistic {
    private Long id;

    private Integer sewageid;

    private Date testingtime;

    private Double waterflow;

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

    public Date getTestingtime() {
        return testingtime;
    }

    public void setTestingtime(Date testingtime) {
        this.testingtime = testingtime;
    }

    public Double getWaterflow() {
        return waterflow;
    }

    public void setWaterflow(Double waterflow) {
        this.waterflow = waterflow;
    }
}