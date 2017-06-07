package com.yaoli.beans;

import java.util.Date;

public class Sewage {
    //仅仅为了方便 用于断电断线 最后的lasttestingtime字段
    private Date lasttestingtime;

	private Double equipment1power;
	
	private Double equipment2power;
	
	private Double equipment3power;
	
	private Double equipment4power;

    private Double equipment5power;
	
	private String videopuid;
	
	private String emissionStandard;
	
	private Double tonnage;
	
	private String videourl;
	
	private int isAbnormal;
	
    private Integer sewageid;

    private Integer areaid;

    private Integer controlid;

    private Integer administratorid;

    private String shortTitle;

    private String name;

    private String address;

    private Double coordinatex;

    private Double coordinatey;

    private String equipment1state;

    private String equipment2state;

    private String equipment3state;

    private String equipment4state;

    private String equipment5state;

    private Double detection1;

    private Double detection1ul;

    private Double detection1dl;

    private Double detection2;

    private Double detection2ul;

    private Double detection2dl;

    private Double detection3ul;

    private Double detection3;

    private Double detection3dl;

    private Double detection4;

    private Double detection4ul;

    private Double detection4dl;

    private Double detection5;

    private Double detection5ul;

    private Double detection5dl;

    private Double detection6;

    private Double detection6ul;

    private Double detection6dl;

    private String airSched;

    private String waterSched;

    private String mudSched;

    private String flowSched;

    private String backupSched;

    private String controlStrategy;

    private String deviceAlert;

    private Integer gratingdays;

    private Date confirmgratingtime;

    private Long action;

    private Double waterflow;

    private Double reducecod;

    private Double reducenh3n;

    private Double reducep;

    private String operationnum;

    private Integer controlmethod;

    private Double detection7;

    private Double detection8;

    private Double detection9;

    private Date updatetime;

    private Double detection10dl;

    private Double detection11dl;

    private Double detection12dl;

    private Double detection13dl;

    private Double detection14dl;

    private Double detection15dl;

    private Double detection16dl;

    private Double detection17dl;

    private Double detection18dl;

    private Double detection19dl;

    private Double detection20dl;

    private Double detection10ul;

    private Double detection11ul;

    private Double detection12ul;

    private Double detection13ul;

    private Double detection14ul;

    private Double detection15ul;

    private Double detection16ul;

    private Double detection17ul;

    private Double detection18ul;

    private Double detection19ul;

    private Double detection20ul;

    private Double detection7ul;

    private Double detection7dl;

    private Double detection8ul;

    private Double detection8dl;

    private Double detection9ul;

    private Double detection9dl;

    private Integer runtimeperiod1;

    private Integer stoptimeperiod1;

    private Integer runtimeperiod2;

    private Integer stoptimeperiod2;

    private Integer runtimeperiod3;

    private Integer stoptimeperiod3;

    private Integer runtimeperiod4;

    private Integer stoptimeperiod4;

    private Integer runtimeperiod5;

    private Integer stoptimeperiod5;

    private Integer runtimeperiod6;

    private Integer stoptimeperiod6;

    private Integer runtimeperiod7;

    private Integer stoptimeperiod7;

    private Integer runtimeperiod8;

    private Integer stoptimeperiod8;

    private Integer runtimeperiod9;

    private Integer stoptimeperiod9;

    private Integer runtimeperiod10;
    
    private Integer stoptimeperiod10;

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

    public Integer getControlid() {
        return controlid;
    }

    public void setControlid(Integer controlid) {
        this.controlid = controlid;
    }

    public Integer getAdministratorid() {
        return administratorid;
    }

    public void setAdministratorid(Integer administratorid) {
        this.administratorid = administratorid;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle == null ? null : shortTitle.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Double getCoordinatex() {
        return coordinatex;
    }

    public void setCoordinatex(Double coordinatex) {
        this.coordinatex = coordinatex;
    }

    public Double getCoordinatey() {
        return coordinatey;
    }

    public void setCoordinatey(Double coordinatey) {
        this.coordinatey = coordinatey;
    }

    public String getEquipment1state() {
        return equipment1state;
    }

    public void setEquipment1state(String equipment1state) {
        this.equipment1state = equipment1state == null ? null : equipment1state.trim();
    }

    public String getEquipment2state() {
        return equipment2state;
    }

    public void setEquipment2state(String equipment2state) {
        this.equipment2state = equipment2state == null ? null : equipment2state.trim();
    }

    public String getEquipment3state() {
        return equipment3state;
    }

    public void setEquipment3state(String equipment3state) {
        this.equipment3state = equipment3state == null ? null : equipment3state.trim();
    }

    public String getEquipment4state() {
        return equipment4state;
    }

    public void setEquipment4state(String equipment4state) {
        this.equipment4state = equipment4state == null ? null : equipment4state.trim();
    }

    public String getEquipment5state() {
        return equipment5state;
    }

    public void setEquipment5state(String equipment5state) {
        this.equipment5state = equipment5state == null ? null : equipment5state.trim();
    }

    public Double getDetection1() {
        return detection1;
    }

    public void setDetection1(Double detection1) {
        this.detection1 = detection1;
    }

    public Double getDetection1ul() {
        return detection1ul;
    }

    public void setDetection1ul(Double detection1ul) {
        this.detection1ul = detection1ul;
    }

    public Double getDetection1dl() {
        return detection1dl;
    }

    public void setDetection1dl(Double detection1dl) {
        this.detection1dl = detection1dl;
    }

    public Double getDetection2() {
        return detection2;
    }

    public void setDetection2(Double detection2) {
        this.detection2 = detection2;
    }

    public Double getDetection2ul() {
        return detection2ul;
    }

    public void setDetection2ul(Double detection2ul) {
        this.detection2ul = detection2ul;
    }

    public Double getDetection2dl() {
        return detection2dl;
    }

    public void setDetection2dl(Double detection2dl) {
        this.detection2dl = detection2dl;
    }

    public Double getDetection3ul() {
        return detection3ul;
    }

    public void setDetection3ul(Double detection3ul) {
        this.detection3ul = detection3ul;
    }

    public Double getDetection3() {
        return detection3;
    }

    public void setDetection3(Double detection3) {
        this.detection3 = detection3;
    }

    public Double getDetection3dl() {
        return detection3dl;
    }

    public void setDetection3dl(Double detection3dl) {
        this.detection3dl = detection3dl;
    }

    public Double getDetection4() {
        return detection4;
    }

    public void setDetection4(Double detection4) {
        this.detection4 = detection4;
    }

    public Double getDetection4ul() {
        return detection4ul;
    }

    public void setDetection4ul(Double detection4ul) {
        this.detection4ul = detection4ul;
    }

    public Double getDetection4dl() {
        return detection4dl;
    }

    public void setDetection4dl(Double detection4dl) {
        this.detection4dl = detection4dl;
    }

    public Double getDetection5() {
        return detection5;
    }

    public void setDetection5(Double detection5) {
        this.detection5 = detection5;
    }

    public Double getDetection5ul() {
        return detection5ul;
    }

    public void setDetection5ul(Double detection5ul) {
        this.detection5ul = detection5ul;
    }

    public Double getDetection5dl() {
        return detection5dl;
    }

    public void setDetection5dl(Double detection5dl) {
        this.detection5dl = detection5dl;
    }

    public Double getDetection6() {
        return detection6;
    }

    public void setDetection6(Double detection6) {
        this.detection6 = detection6;
    }

    public Double getDetection6ul() {
        return detection6ul;
    }

    public void setDetection6ul(Double detection6ul) {
        this.detection6ul = detection6ul;
    }

    public Double getDetection6dl() {
        return detection6dl;
    }

    public void setDetection6dl(Double detection6dl) {
        this.detection6dl = detection6dl;
    }

    public String getAirSched() {
        return airSched;
    }

    public void setAirSched(String airSched) {
        this.airSched = airSched == null ? null : airSched.trim();
    }

    public String getWaterSched() {
        return waterSched;
    }

    public void setWaterSched(String waterSched) {
        this.waterSched = waterSched == null ? null : waterSched.trim();
    }

    public String getMudSched() {
        return mudSched;
    }

    public void setMudSched(String mudSched) {
        this.mudSched = mudSched == null ? null : mudSched.trim();
    }

    public String getFlowSched() {
        return flowSched;
    }

    public void setFlowSched(String flowSched) {
        this.flowSched = flowSched == null ? null : flowSched.trim();
    }

    public String getBackupSched() {
        return backupSched;
    }

    public void setBackupSched(String backupSched) {
        this.backupSched = backupSched == null ? null : backupSched.trim();
    }

    public String getControlStrategy() {
        return controlStrategy;
    }

    public void setControlStrategy(String controlStrategy) {
        this.controlStrategy = controlStrategy == null ? null : controlStrategy.trim();
    }

    public String getDeviceAlert() {
        return deviceAlert;
    }

    public void setDeviceAlert(String deviceAlert) {
        this.deviceAlert = deviceAlert == null ? null : deviceAlert.trim();
    }

    public Integer getGratingdays() {
        return gratingdays;
    }

    public void setGratingdays(Integer gratingdays) {
        this.gratingdays = gratingdays;
    }

    public Date getConfirmgratingtime() {
        return confirmgratingtime;
    }

    public void setConfirmgratingtime(Date confirmgratingtime) {
        this.confirmgratingtime = confirmgratingtime;
    }

    public Long getAction() {
        return action;
    }

    public void setAction(Long action) {
        this.action = action;
    }

    public Double getWaterflow() {
        return waterflow;
    }

    public void setWaterflow(Double waterflow) {
        this.waterflow = waterflow;
    }

    public Double getReducecod() {
        return reducecod;
    }

    public void setReducecod(Double reducecod) {
        this.reducecod = reducecod;
    }

    public Double getReducenh3n() {
        return reducenh3n;
    }

    public void setReducenh3n(Double reducenh3n) {
        this.reducenh3n = reducenh3n;
    }

    public Double getReducep() {
        return reducep;
    }

    public void setReducep(Double reducep) {
        this.reducep = reducep;
    }

    public String getOperationnum() {
        return operationnum;
    }

    public void setOperationnum(String operationnum) {
        this.operationnum = operationnum == null ? null : operationnum.trim();
    }

    public Integer getControlmethod() {
        return controlmethod;
    }

    public void setControlmethod(Integer controlmethod) {
        this.controlmethod = controlmethod;
    }

    public Double getDetection7() {
        return detection7;
    }

    public void setDetection7(Double detection7) {
        this.detection7 = detection7;
    }

    public Double getDetection8() {
        return detection8;
    }

    public void setDetection8(Double detection8) {
        this.detection8 = detection8;
    }

    public Double getDetection9() {
        return detection9;
    }

    public void setDetection9(Double detection9) {
        this.detection9 = detection9;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Double getDetection10dl() {
        return detection10dl;
    }

    public void setDetection10dl(Double detection10dl) {
        this.detection10dl = detection10dl;
    }

    public Double getDetection11dl() {
        return detection11dl;
    }

    public void setDetection11dl(Double detection11dl) {
        this.detection11dl = detection11dl;
    }

    public Double getDetection12dl() {
        return detection12dl;
    }

    public void setDetection12dl(Double detection12dl) {
        this.detection12dl = detection12dl;
    }

    public Double getDetection13dl() {
        return detection13dl;
    }

    public void setDetection13dl(Double detection13dl) {
        this.detection13dl = detection13dl;
    }

    public Double getDetection14dl() {
        return detection14dl;
    }

    public void setDetection14dl(Double detection14dl) {
        this.detection14dl = detection14dl;
    }

    public Double getDetection15dl() {
        return detection15dl;
    }

    public void setDetection15dl(Double detection15dl) {
        this.detection15dl = detection15dl;
    }

    public Double getDetection16dl() {
        return detection16dl;
    }

    public void setDetection16dl(Double detection16dl) {
        this.detection16dl = detection16dl;
    }

    public Double getDetection17dl() {
        return detection17dl;
    }

    public void setDetection17dl(Double detection17dl) {
        this.detection17dl = detection17dl;
    }

    public Double getDetection18dl() {
        return detection18dl;
    }

    public void setDetection18dl(Double detection18dl) {
        this.detection18dl = detection18dl;
    }

    public Double getDetection19dl() {
        return detection19dl;
    }

    public void setDetection19dl(Double detection19dl) {
        this.detection19dl = detection19dl;
    }

    public Double getDetection20dl() {
        return detection20dl;
    }

    public void setDetection20dl(Double detection20dl) {
        this.detection20dl = detection20dl;
    }

    public Double getDetection10ul() {
        return detection10ul;
    }

    public void setDetection10ul(Double detection10ul) {
        this.detection10ul = detection10ul;
    }

    public Double getDetection11ul() {
        return detection11ul;
    }

    public void setDetection11ul(Double detection11ul) {
        this.detection11ul = detection11ul;
    }

    public Double getDetection12ul() {
        return detection12ul;
    }

    public void setDetection12ul(Double detection12ul) {
        this.detection12ul = detection12ul;
    }

    public Double getDetection13ul() {
        return detection13ul;
    }

    public void setDetection13ul(Double detection13ul) {
        this.detection13ul = detection13ul;
    }

    public Double getDetection14ul() {
        return detection14ul;
    }

    public void setDetection14ul(Double detection14ul) {
        this.detection14ul = detection14ul;
    }

    public Double getDetection15ul() {
        return detection15ul;
    }

    public void setDetection15ul(Double detection15ul) {
        this.detection15ul = detection15ul;
    }

    public Double getDetection16ul() {
        return detection16ul;
    }

    public void setDetection16ul(Double detection16ul) {
        this.detection16ul = detection16ul;
    }

    public Double getDetection17ul() {
        return detection17ul;
    }

    public void setDetection17ul(Double detection17ul) {
        this.detection17ul = detection17ul;
    }

    public Double getDetection18ul() {
        return detection18ul;
    }

    public void setDetection18ul(Double detection18ul) {
        this.detection18ul = detection18ul;
    }

    public Double getDetection19ul() {
        return detection19ul;
    }

    public void setDetection19ul(Double detection19ul) {
        this.detection19ul = detection19ul;
    }

    public Double getDetection20ul() {
        return detection20ul;
    }

    public void setDetection20ul(Double detection20ul) {
        this.detection20ul = detection20ul;
    }

    public Double getDetection7ul() {
        return detection7ul;
    }

    public void setDetection7ul(Double detection7ul) {
        this.detection7ul = detection7ul;
    }

    public Double getDetection7dl() {
        return detection7dl;
    }

    public void setDetection7dl(Double detection7dl) {
        this.detection7dl = detection7dl;
    }

    public Double getDetection8ul() {
        return detection8ul;
    }

    public void setDetection8ul(Double detection8ul) {
        this.detection8ul = detection8ul;
    }

    public Double getDetection8dl() {
        return detection8dl;
    }

    public void setDetection8dl(Double detection8dl) {
        this.detection8dl = detection8dl;
    }

    public Double getDetection9ul() {
        return detection9ul;
    }

    public void setDetection9ul(Double detection9ul) {
        this.detection9ul = detection9ul;
    }

    public Double getDetection9dl() {
        return detection9dl;
    }

    public void setDetection9dl(Double detection9dl) {
        this.detection9dl = detection9dl;
    }

    public Integer getRuntimeperiod1() {
        return runtimeperiod1;
    }

    public void setRuntimeperiod1(Integer runtimeperiod1) {
        this.runtimeperiod1 = runtimeperiod1;
    }

    public Integer getStoptimeperiod1() {
        return stoptimeperiod1;
    }

    public void setStoptimeperiod1(Integer stoptimeperiod1) {
        this.stoptimeperiod1 = stoptimeperiod1;
    }

    public Integer getRuntimeperiod2() {
        return runtimeperiod2;
    }

    public void setRuntimeperiod2(Integer runtimeperiod2) {
        this.runtimeperiod2 = runtimeperiod2;
    }

    public Integer getStoptimeperiod2() {
        return stoptimeperiod2;
    }

    public void setStoptimeperiod2(Integer stoptimeperiod2) {
        this.stoptimeperiod2 = stoptimeperiod2;
    }

    public Integer getRuntimeperiod3() {
        return runtimeperiod3;
    }

    public void setRuntimeperiod3(Integer runtimeperiod3) {
        this.runtimeperiod3 = runtimeperiod3;
    }

    public Integer getStoptimeperiod3() {
        return stoptimeperiod3;
    }

    public void setStoptimeperiod3(Integer stoptimeperiod3) {
        this.stoptimeperiod3 = stoptimeperiod3;
    }

    public Integer getRuntimeperiod4() {
        return runtimeperiod4;
    }

    public void setRuntimeperiod4(Integer runtimeperiod4) {
        this.runtimeperiod4 = runtimeperiod4;
    }

    public Integer getStoptimeperiod4() {
        return stoptimeperiod4;
    }

    public void setStoptimeperiod4(Integer stoptimeperiod4) {
        this.stoptimeperiod4 = stoptimeperiod4;
    }

    public Integer getRuntimeperiod5() {
        return runtimeperiod5;
    }

    public void setRuntimeperiod5(Integer runtimeperiod5) {
        this.runtimeperiod5 = runtimeperiod5;
    }

    public Integer getStoptimeperiod5() {
        return stoptimeperiod5;
    }

    public void setStoptimeperiod5(Integer stoptimeperiod5) {
        this.stoptimeperiod5 = stoptimeperiod5;
    }

    public Integer getRuntimeperiod6() {
        return runtimeperiod6;
    }

    public void setRuntimeperiod6(Integer runtimeperiod6) {
        this.runtimeperiod6 = runtimeperiod6;
    }

    public Integer getStoptimeperiod6() {
        return stoptimeperiod6;
    }

    public void setStoptimeperiod6(Integer stoptimeperiod6) {
        this.stoptimeperiod6 = stoptimeperiod6;
    }

    public Integer getRuntimeperiod7() {
        return runtimeperiod7;
    }

    public void setRuntimeperiod7(Integer runtimeperiod7) {
        this.runtimeperiod7 = runtimeperiod7;
    }

    public Integer getStoptimeperiod7() {
        return stoptimeperiod7;
    }

    public void setStoptimeperiod7(Integer stoptimeperiod7) {
        this.stoptimeperiod7 = stoptimeperiod7;
    }

    public Integer getRuntimeperiod8() {
        return runtimeperiod8;
    }

    public void setRuntimeperiod8(Integer runtimeperiod8) {
        this.runtimeperiod8 = runtimeperiod8;
    }

    public Integer getStoptimeperiod8() {
        return stoptimeperiod8;
    }

    public void setStoptimeperiod8(Integer stoptimeperiod8) {
        this.stoptimeperiod8 = stoptimeperiod8;
    }

    public Integer getRuntimeperiod9() {
        return runtimeperiod9;
    }

    public void setRuntimeperiod9(Integer runtimeperiod9) {
        this.runtimeperiod9 = runtimeperiod9;
    }

    public Integer getStoptimeperiod9() {
        return stoptimeperiod9;
    }

    public void setStoptimeperiod9(Integer stoptimeperiod9) {
        this.stoptimeperiod9 = stoptimeperiod9;
    }

    public Integer getRuntimeperiod10() {
        return runtimeperiod10;
    }

    public void setRuntimeperiod10(Integer runtimeperiod10) {
        this.runtimeperiod10 = runtimeperiod10;
    }

    public Integer getStoptimeperiod10() {
        return stoptimeperiod10;
    }

    public void setStoptimeperiod10(Integer stoptimeperiod10) {
        this.stoptimeperiod10 = stoptimeperiod10;
    }

	public int getIsAbnormal() {
		return isAbnormal;
	}

	public void setIsAbnormal(int isAbnormal) {
		this.isAbnormal = isAbnormal;
	}

	public String getVideourl() {
		return videourl;
	}

	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}

	public Double getTonnage() {
		return tonnage;
	}

	public void setTonnage(Double tonnage) {
		this.tonnage = tonnage;
	}

	public String getVideopuid() {
		return videopuid;
	}

	public void setVideopuid(String videopuid) {
		this.videopuid = videopuid;
	}

	public String getEmissionStandard() {
		return emissionStandard;
	}

	public void setEmissionStandard(String emissionStandard) {
		this.emissionStandard = emissionStandard;
	}

	public Double getEquipment1power() {
		return equipment1power;
	}

	public void setEquipment1power(Double equipment1power) {
		this.equipment1power = equipment1power;
	}

	public Double getEquipment2power() {
		return equipment2power;
	}

	public void setEquipment2power(Double equipment2power) {
		this.equipment2power = equipment2power;
	}

	public Double getEquipment3power() {
		return equipment3power;
	}

	public void setEquipment3power(Double equipment3power) {
		this.equipment3power = equipment3power;
	}

	public Double getEquipment4power() {
		return equipment4power;
	}

	public void setEquipment4power(Double equipment4power) {
		this.equipment4power = equipment4power;
	}

    public Date getLasttestingtime() {
        return lasttestingtime;
    }

    public void setLasttestingtime(Date lasttestingtime) {
        this.lasttestingtime = lasttestingtime;
    }

    public Double getEquipment5power() {
        return equipment5power;
    }

    public void setEquipment5power(Double equipment5power) {
        this.equipment5power = equipment5power;
    }
}