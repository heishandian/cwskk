package com.yaoli.beans;

import java.util.Date;

public class DetectionDataAbnormal {
    private Long detectionid;

    private Integer sewageid;

    private Date testingtime;

    private Double detectionnum;

    private Byte detectionno;

    private Byte isrepaired;

    public Long getDetectionid() {
        return detectionid;
    }

    public void setDetectionid(Long detectionid) {
        this.detectionid = detectionid;
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

    public Double getDetectionnum() {
        return detectionnum;
    }

    public void setDetectionnum(Double detectionnum) {
        this.detectionnum = detectionnum;
    }

    public Byte getDetectionno() {
        return detectionno;
    }

    public void setDetectionno(Byte detectionno) {
        this.detectionno = detectionno;
    }

    public Byte getIsrepaired() {
        return isrepaired;
    }

    public void setIsrepaired(Byte isrepaired) {
        this.isrepaired = isrepaired;
    }
}