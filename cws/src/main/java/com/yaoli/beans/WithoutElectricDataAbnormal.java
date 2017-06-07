package com.yaoli.beans;

import java.util.Date;

public class WithoutElectricDataAbnormal {
    private Long id;

    private Integer sewageid;

    private Date testingtime;

    private Byte isrepaired;

    private Date lasttestingtime;

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

    public Byte getIsrepaired() {
        return isrepaired;
    }

    public void setIsrepaired(Byte isrepaired) {
        this.isrepaired = isrepaired;
    }

    public Date getLasttestingtime() {
        return lasttestingtime;
    }

    public void setLasttestingtime(Date lasttestingtime) {
        this.lasttestingtime = lasttestingtime;
    }
}