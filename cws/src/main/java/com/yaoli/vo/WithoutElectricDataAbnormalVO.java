package com.yaoli.vo;

import java.util.Date;

public class WithoutElectricDataAbnormalVO {

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

    public String getAbnormalTypeName() {
        return abnormalTypeName;
    }

    public void setAbnormalTypeName(String abnormalTypeName) {
        this.abnormalTypeName = abnormalTypeName;
    }

    public Integer getAbnormalType() {
        return abnormalType;
    }

    public void setAbnormalType(Integer abnormalType) {
        this.abnormalType = abnormalType;
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
}