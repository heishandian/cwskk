package com.yaoli.vo;

import java.util.List;

import com.yaoli.beans.Sewage;

/**
 * 用于传输数据
 * @author will
 *
 */
public class SewageVO{
	private Double equipment5power;
	private Double equipment5energycost;

	private Double equipment1energycost;
	private Double equipment2energycost;
	private Double equipment3energycost;
	private Double equipment4energycost;
	
	private Double equipment1power;
	
	private Double equipment2power;
	
	private Double equipment3power;
	
	private Double equipment4power;

	private String controlMethodName;
	
	private String videopuid;
	
	private String emissionStandard;
	
	private Double tonnage;
	
	public String videourl;
	
	//所属地区名称
	public String areaName;
	
	public List<Sewage> sewages;
	
	//所属管理员 名称
	public String admin;

	//所属管理员 名称 建议不要用admin
	public String adminName;
	
	//所属管理员id
	public int adminid;
	
	/**
	 * 前台地点简称或者运行编号
	 */
	public String searchNameOrId;
	
	/**
	 * 前台地点简称或者运行编号的搜索的结果
	 * @return
	 */
	public List<Sewage> searchResult;
	
	//保存前台用于删除的站点id
	public List<Integer> selectIds;
	
	

	private Integer sewageid;

    private Integer areaid;

    private Integer controlid;

    private String shortTitle;

    private String name;

    private String address;

    private Double coordinatex;

    private Double coordinatey;

    private Double detection1ul;

    private Double detection1dl;

    private Double detection2ul;

    private Double detection2dl;

    private Double detection3ul;

    private Double detection3dl;

    private Double detection4ul;

    private Double detection4dl;

    private Double detection5ul;

    private Double detection5dl;

    private Double detection6ul;

    private Double detection6dl;
    
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

    private String controlStrategy;

    private Float reducecod;

    private Float reducenh3n;

    private Float reducep;
    
    private Float waterflow;

    private String operationnum;

    private Integer controlmethod;
    
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

	public List<Integer> getSelectIds() {
		return selectIds;
	}

	public void setSelectIds(List<Integer> selectIds) {
		this.selectIds = selectIds;
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

	public Integer getControlid() {
		return controlid;
	}

	public void setControlid(Integer controlid) {
		this.controlid = controlid;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Double getDetection3dl() {
		return detection3dl;
	}

	public void setDetection3dl(Double detection3dl) {
		this.detection3dl = detection3dl;
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

	public String getControlStrategy() {
		return controlStrategy;
	}

	public void setControlStrategy(String controlStrategy) {
		this.controlStrategy = controlStrategy;
	}

	public Float getReducecod() {
		return reducecod;
	}

	public void setReducecod(Float reducecod) {
		this.reducecod = reducecod;
	}

	public Float getReducenh3n() {
		return reducenh3n;
	}

	public void setReducenh3n(Float reducenh3n) {
		this.reducenh3n = reducenh3n;
	}

	public Float getReducep() {
		return reducep;
	}

	public void setReducep(Float reducep) {
		this.reducep = reducep;
	}

	public Float getWaterflow() {
		return waterflow;
	}

	public void setWaterflow(Float waterflow) {
		this.waterflow = waterflow;
	}

	public String getOperationnum() {
		return operationnum;
	}

	public void setOperationnum(String operationnum) {
		this.operationnum = operationnum;
	}

	public Integer getControlmethod() {
		return controlmethod;
	}

	public void setControlmethod(Integer controlmethod) {
		this.controlmethod = controlmethod;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public int getAdminid() {
		return adminid;
	}

	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}

	
	
	
/*	public int sewageId;
	
	public int areaId;
	
	public double coordinateX;
	
	public double coordinateY;*/
	


	public List<Sewage> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<Sewage> searchResult) {
		this.searchResult = searchResult;
	}

	public String getSearchNameOrId() {
		return searchNameOrId;
	}

	public void setSearchNameOrId(String searchNameOrId) {
		this.searchNameOrId = searchNameOrId;
	}


	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public List<Sewage> getSewages() {
		return sewages;
	}

	public void setSewages(List<Sewage> sewages) {
		this.sewages = sewages;
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

	public String getControlMethodName() {
		return controlMethodName;
	}

	public void setControlMethodName(String controlMethodName) {
		this.controlMethodName = controlMethodName;
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

	public Double getEquipment1energycost() {
		return equipment1energycost;
	}

	public void setEquipment1energycost(Double equipment1energycost) {
		this.equipment1energycost = equipment1energycost;
	}

	public Double getEquipment2energycost() {
		return equipment2energycost;
	}

	public void setEquipment2energycost(Double equipment2energycost) {
		this.equipment2energycost = equipment2energycost;
	}

	public Double getEquipment3energycost() {
		return equipment3energycost;
	}

	public void setEquipment3energycost(Double equipment3energycost) {
		this.equipment3energycost = equipment3energycost;
	}

	public Double getEquipment4energycost() {
		return equipment4energycost;
	}

	public void setEquipment4energycost(Double equipment4energycost) {
		this.equipment4energycost = equipment4energycost;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Double getEquipment5power() {
		return equipment5power;
	}

	public void setEquipment5power(Double equipment5power) {
		this.equipment5power = equipment5power;
	}

	public Double getEquipment5energycost() {
		return equipment5energycost;
	}

	public void setEquipment5energycost(Double equipment5energycost) {
		this.equipment5energycost = equipment5energycost;
	}
}
