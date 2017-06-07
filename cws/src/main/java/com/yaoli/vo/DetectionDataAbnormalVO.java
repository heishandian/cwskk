package com.yaoli.vo;

import java.util.Date;

public class DetectionDataAbnormalVO {
    private Long detectionid;
    
    private Integer sewageid;
    
    private Integer areaid;
    
    private String name;
    
    private String operationnum;
    
    private Integer controlid;
    
    private Date testingtime;
    
    private double down;
    
    private double up;
    
    //探查到的参数
    private double detection;
    
    //水质参数 类型
    private String waterparametername;
    
    private Double detectionnum;
    
    private Byte detectionno;
    
    private Byte isrepaired;
    
    private String sewageName;
    
    public String getSewageName() {
		return sewageName;
	}

	public void setSewageName(String sewageName) {
		this.sewageName = sewageName;
	}

	private String detectionidAndDetectionNum;
    
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

	public String getDetectionidAndDetectionNum() {
		return detectionidAndDetectionNum;
	}

	public void setDetectionidAndDetectionNum(String detectionidAndDetectionNum) {
		this.detectionidAndDetectionNum = detectionidAndDetectionNum;
	}

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

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperationnum() {
		return operationnum;
	}

	public void setOperationnum(String operationnum) {
		this.operationnum = operationnum;
	}

	public Integer getControlid() {
		return controlid;
	}

	public void setControlid(Integer controlid) {
		this.controlid = controlid;
	}

	public Date getTestingtime() {
		return testingtime;
	}

	public void setTestingtime(Date testingtime) {
		this.testingtime = testingtime;
	}

	public double getDown() {
		return down;
	}

	public void setDown(double down) {
		this.down = down;
	}

	public double getUp() {
		return up;
	}

	public void setUp(double up) {
		this.up = up;
	}

	public double getDetection() {
		return detection;
	}

	public void setDetection(double detection) {
		this.detection = detection;
	}

	public String getWaterparametername() {
		return waterparametername;
	}

	public void setWaterparametername(String waterparametername) {
		this.waterparametername = waterparametername;
	}
    
    
}
