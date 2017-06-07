package com.yaoli.beans;

import java.util.Date;

public class DeviceDoc {
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

    
    
    public Integer getLimityears() {
		return limityears;
	}

	public void setLimityears(Integer limityears) {
		this.limityears = limityears;
	}

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
        this.devicename = devicename == null ? null : devicename.trim();
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype == null ? null : devicetype.trim();
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
}