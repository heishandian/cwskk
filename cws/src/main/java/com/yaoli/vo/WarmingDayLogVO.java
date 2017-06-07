package com.yaoli.vo;

import java.util.Date;

public class WarmingDayLogVO {
    //水质异常次数
    private int detection_count;

    //断电断线次数
    private int withoutElectric_count;

    //设备1 曝气器故障次数
    private int equip1_count;

    //设备2-5 其它故障都属于水泵故障）水泵故障次数
    private int equip25_count;




    private String operationnum;
    //不正常的类型 1 水质异常  2 设备故障 3 断电断线
    private Integer abnormalType;

    //不正常的类型名称
    private String abnormalTypeName;

    //不正常的值
    private String abnormalTypeValue;

    private String sewageId;

    private String sewageName;

    private Long id;

    private Integer sewageid;

    private Date testingtime;

    private Byte isrepaired;

    private Date lasttestingtime;

    public Integer getAbnormalType() {
        return abnormalType;
    }

    public void setAbnormalType(Integer abnormalType) {
        this.abnormalType = abnormalType;
    }

    public String getAbnormalTypeName() {
        return abnormalTypeName;
    }

    public void setAbnormalTypeName(String abnormalTypeName) {
        this.abnormalTypeName = abnormalTypeName;
    }

    public String getAbnormalTypeValue() {
        return abnormalTypeValue;
    }

    public void setAbnormalTypeValue(String abnormalTypeValue) {
        this.abnormalTypeValue = abnormalTypeValue;
    }

    public String getSewageId() {
        return sewageId;
    }

    public void setSewageId(String sewageId) {
        this.sewageId = sewageId;
    }

    public String getSewageName() {
        return sewageName;
    }

    public void setSewageName(String sewageName) {
        this.sewageName = sewageName;
    }

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

    public String getOperationnum() {
        return operationnum;
    }

    public void setOperationnum(String operationnum) {
        this.operationnum = operationnum;
    }

    public int getDetection_count() {
        return detection_count;
    }

    public void setDetection_count(int detection_count) {
        this.detection_count = detection_count;
    }

    public int getWithoutElectric_count() {
        return withoutElectric_count;
    }

    public void setWithoutElectric_count(int withoutElectric_count) {
        this.withoutElectric_count = withoutElectric_count;
    }

    public int getEquip1_count() {
        return equip1_count;
    }

    public void setEquip1_count(int equip1_count) {
        this.equip1_count = equip1_count;
    }

    public int getEquip25_count() {
        return equip25_count;
    }

    public void setEquip25_count(int equip25_count) {
        this.equip25_count = equip25_count;
    }
}