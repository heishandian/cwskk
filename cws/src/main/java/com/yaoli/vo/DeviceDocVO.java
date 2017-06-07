package com.yaoli.vo;

import java.util.Date;

public class DeviceDocVO {
    private Long id;

    private Integer number;

    private String devicename;

    private String devicetype;

    private Date setuptime;

    private Date lastrepairtime;

    private Integer maintaincycleday;

    private Integer limityears;

    private Integer repairrecord;

    private Integer sewageid;
    
    private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDevicename() {
		return devicename;
	}

	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	public Date getSetuptime() {
		return setuptime;
	}

	public void setSetuptime(Date setuptime) {
		this.setuptime = setuptime;
	}

	public Date getLastrepairtime() {
		return lastrepairtime;
	}

	public void setLastrepairtime(Date lastrepairtime) {
		this.lastrepairtime = lastrepairtime;
	}

	public Integer getMaintaincycleday() {
		return maintaincycleday;
	}

	public void setMaintaincycleday(Integer maintaincycleday) {
		this.maintaincycleday = maintaincycleday;
	}

	public Integer getLimityears() {
		return limityears;
	}

	public void setLimityears(Integer limityears) {
		this.limityears = limityears;
	}

	public Integer getRepairrecord() {
		return repairrecord;
	}

	public void setRepairrecord(Integer repairrecord) {
		this.repairrecord = repairrecord;
	}

	public Integer getSewageid() {
		return sewageid;
	}

	public void setSewageid(Integer sewageid) {
		this.sewageid = sewageid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
}
