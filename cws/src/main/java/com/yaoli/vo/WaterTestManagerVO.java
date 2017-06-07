package com.yaoli.vo;

import java.util.Date;

public class WaterTestManagerVO {
	private String reportNO;
	
	private String operationnum;
	
	private String areaname;
	
	private String sewagename;
	
	/**
	 * 控制ID
	 */
	private Integer controlid;
	
    private Integer id;

    private Integer sewageid;

    private Date testingtime;

    private Float outcod;

    private Float outnh3n;

    private Float outp;

    private Float incod;

    private Float innh3n;

    private Float inp;

	public String getOperationnum() {
		return operationnum;
	}

	public void setOperationnum(String operationnum) {
		this.operationnum = operationnum;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getSewagename() {
		return sewagename;
	}

	public void setSewagename(String sewagename) {
		this.sewagename = sewagename;
	}

	public Integer getControlid() {
		return controlid;
	}

	public void setControlid(Integer controlid) {
		this.controlid = controlid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Float getOutcod() {
		return outcod;
	}

	public void setOutcod(Float outcod) {
		this.outcod = outcod;
	}

	public Float getOutnh3n() {
		return outnh3n;
	}

	public void setOutnh3n(Float outnh3n) {
		this.outnh3n = outnh3n;
	}

	public Float getOutp() {
		return outp;
	}

	public void setOutp(Float outp) {
		this.outp = outp;
	}

	public Float getIncod() {
		return incod;
	}

	public void setIncod(Float incod) {
		this.incod = incod;
	}

	public Float getInnh3n() {
		return innh3n;
	}

	public void setInnh3n(Float innh3n) {
		this.innh3n = innh3n;
	}

	public Float getInp() {
		return inp;
	}

	public void setInp(Float inp) {
		this.inp = inp;
	}

	public String getReportNO() {
		return reportNO;
	}

	public void setReportNO(String reportNO) {
		this.reportNO = reportNO;
	}
}
