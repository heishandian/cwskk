package com.yaoli.beans;

import java.util.Date;

public class RunDataAbnormal {
    private Integer runid;

    private Integer sewageid;

    private Date testingtime;

    private Byte equipmentstate;

    private Byte equipmentno;

    private Byte isrepaired;

    public Integer getRunid() {
        return runid;
    }

    public void setRunid(Integer runid) {
        this.runid = runid;
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

    public Byte getEquipmentstate() {
        return equipmentstate;
    }

    public void setEquipmentstate(Byte equipmentstate) {
        this.equipmentstate = equipmentstate;
    }

    public Byte getEquipmentno() {
        return equipmentno;
    }

    public void setEquipmentno(Byte equipmentno) {
        this.equipmentno = equipmentno;
    }

    public Byte getIsrepaired() {
        return isrepaired;
    }

    public void setIsrepaired(Byte isrepaired) {
        this.isrepaired = isrepaired;
    }
}