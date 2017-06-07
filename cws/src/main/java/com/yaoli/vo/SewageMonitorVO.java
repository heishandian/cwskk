package com.yaoli.vo;

import java.util.Date;


/**
 * 用于定义站点监控 界面属性类
 * @author will
 *
 */
public class SewageMonitorVO {
	private Double equipment1power;
	
	private Double equipment2power;
	
	private Double equipment3power;
	
	private Double equipment4power;
	
	private String videopuid;
	
	private String emissionStandard;
	//吨位
	private Double tonnage;
	
	public String videourl;
	
	//当前运行状态
	public String runStateM;
	
	//最后更新时间
	private Date lastUpdatetime;
	
	/*--------------------------------------------------------*/
	/*以下是四台设备相关的参数属性*/
	
	//设备名称
	public String equipment1Name;
	//设备的控制策略
	public String equipment1ControlStrategy;
	//设备的目前状态
	public String equipment1NowState;
	
	public String equipment2Name;
	public String equipment2ControlStrategy;
	public String equipment2NowState;
	
	public String equipment3Name;
	public String equipment3ControlStrategy;
	public String equipment3NowState;
	
	public String equipment4Name;
	public String equipment4ControlStrategy;
	public String equipment4NowState;
	
	public String equipment5Name;
	public String equipment5ControlStrategy;
	public String equipment5NowState;

	public String equipment6Name;
	public String equipment6ControlStrategy;
	public String equipment6NowState;

	public String equipment7Name;
	public String equipment7ControlStrategy;
	public String equipment7NowState;

	public String equipment8Name;
	public String equipment8ControlStrategy;
	public String equipment8NowState;

	public String equipment9Name;
	public String equipment9ControlStrategy;
	public String equipment9NowState;

	public String equipment10Name;
	public String equipment10ControlStrategy;
	public String equipment10NowState;

	public String equipment11Name;
	public String equipment11ControlStrategy;
	public String equipment11NowState;

	public String equipment12Name;
	public String equipment12ControlStrategy;
	public String equipment12NowState;

	public String equipment13Name;
	public String equipment13ControlStrategy;
	public String equipment13NowState;

	public String equipment14Name;
	public String equipment14ControlStrategy;
	public String equipment14NowState;

	public String equipment15Name;
	public String equipment15ControlStrategy;
	public String equipment15NowState;

	public String equipment16Name;
	public String equipment16ControlStrategy;
	public String equipment16NowState;

	public String equipment17Name;
	public String equipment17ControlStrategy;
	public String equipment17NowState;

	public String equipment18Name;
	public String equipment18ControlStrategy;
	public String equipment18NowState;

	public String equipment19Name;
	public String equipment19ControlStrategy;
	public String equipment19NowState;

	public String equipment20Name;
	public String equipment20ControlStrategy;
	public String equipment20NowState;

	public String equipment21Name;
	public String equipment21ControlStrategy;
	public String equipment21NowState;

	public String equipment22Name;
	public String equipment22ControlStrategy;
	public String equipment22NowState;

	public String equipment23Name;
	public String equipment23ControlStrategy;
	public String equipment23NowState;

	public String equipment24Name;
	public String equipment24ControlStrategy;
	public String equipment24NowState;

	public String equipment25Name;
	public String equipment25ControlStrategy;
	public String equipment25NowState;
	
	public String equipment1state;
	public String equipment2state;
	public String equipment3state;
	public String equipment4state;
	public String equipment5state;
	public String equipment6state;
	public String equipment7state;
	public String equipment8state;
	public String equipment9state;
	public String equipment10state;
	public String equipment11state;
	public String equipment12state;
	public String equipment13state;
	public String equipment14state;
	public String equipment15state;
	public String equipment16state;
	public String equipment17state;
	public String equipment18state;
	public String equipment19state;
	public String equipment20state;
	public String equipment21state;
	public String equipment22state;
	public String equipment23state;
	public String equipment24state;
	public String equipment25state;

	private Double detection1;
	private Double detection1dl;
	private Double detection1ul;
	private Double detection2;
	private Double detection2dl;
	private Double detection2ul;
	private Double detection3;
	private Double detection3dl;
	private Double detection3ul;
	private Double detection4;
	private Double detection4dl;
	private Double detection4ul;
	private Double detection5;
	private Double detection5dl;
	private Double detection5ul;
	private Double detection6;
	private Double detection6dl;
	private Double detection6ul;
	private Double detection7;
	private Double detection7dl;
	private Double detection7ul;
	private Double detection8;
	private Double detection8dl;
	private Double detection8ul;
	private Double detection9;
	private Double detection9dl;
	private Double detection9ul;
	private Double detection10;
	private Double detection10dl;
	private Double detection10ul;
	private Double detection11;
	private Double detection11dl;
	private Double detection11ul;
	private Double detection12;
	private Double detection12dl;
	private Double detection12ul;
	private Double detection13;
	private Double detection13dl;
	private Double detection13ul;
	private Double detection14;
	private Double detection14dl;
	private Double detection14ul;
	private Double detection15;
	/*设备属性结束*/
	/*--------------------------------------------------------*/
	
	
	/*--------------------------------------------------------*/
	/*仪表传感器相关参数命名*/
	//温度范围
	public String gaugeTempRange;
	//温度当前值
	public String gaugeTempNowValue;
	
	//PH
	public String gaugePhRange;
	public String gaugePhNowValue;
	
	//ORP
	public String gaugeOrpRange;
	public String gaugeOrpNowValue;
	
	//LS
	public String gaugeLsRange;
	public String gaugeLsNowValue;
	
	//DO
	public String gaugeDoRange;
	public String gaugeDoNowValue;
	
	//Flow值
	public String gaugeFlowRange;
	public String gaugeFlowNowRang;
	/*--------------------------------------------------------*/
	
	/*--------------------------------------------------------*/
	//日处理水量
	public String dayWater;
	
	//日消减COD
	public String dayCod;
	
	//日消减NH3-N
	public String dayNh3n;
	
	//日消减总量P
	public String dayP;
	/*-------------------------------------------------------*/
	
	/*-------------------------------------------------------*/
	//格栅状态
	public String geShanState;
	/*-------------------------------------------------------*/
	
	/*-------------------------------------------------------*/
	//吸膜泵
	public String xiHongBengState;
	/*-------------------------------------------------------*/
	
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

    private Date updatetime;

    private Double detection15dl;

    private Double detection16dl;

    private Double detection17dl;

    private Double detection18dl;

    private Double detection19dl;

    private Double detection20dl;

    private Double detection15ul;

    private Double detection16ul;

    private Double detection17ul;

    private Double detection18ul;

    private Double detection19ul;

    private Double detection20ul;

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
    
    
	public String getEquipment1state() {
		return equipment1state;
	}
	public void setEquipment1state(String equipment1state) {
		this.equipment1state = equipment1state;
	}
	public String getEquipment2state() {
		return equipment2state;
	}
	public void setEquipment2state(String equipment2state) {
		this.equipment2state = equipment2state;
	}
	public String getEquipment3state() {
		return equipment3state;
	}
	public void setEquipment3state(String equipment3state) {
		this.equipment3state = equipment3state;
	}
	public String getEquipment4state() {
		return equipment4state;
	}
	public void setEquipment4state(String equipment4state) {
		this.equipment4state = equipment4state;
	}
	public String getEquipment5state() {
		return equipment5state;
	}
	public void setEquipment5state(String equipment5state) {
		this.equipment5state = equipment5state;
	}
	public String getEquipment6state() {
		return equipment6state;
	}
	public void setEquipment6state(String equipment6state) {
		this.equipment6state = equipment6state;
	}
	public String getEquipment7state() {
		return equipment7state;
	}
	public void setEquipment7state(String equipment7state) {
		this.equipment7state = equipment7state;
	}
	public String getEquipment8state() {
		return equipment8state;
	}
	public void setEquipment8state(String equipment8state) {
		this.equipment8state = equipment8state;
	}
	public String getEquipment9state() {
		return equipment9state;
	}
	public void setEquipment9state(String equipment9state) {
		this.equipment9state = equipment9state;
	}
	public String getEquipment10state() {
		return equipment10state;
	}
	public void setEquipment10state(String equipment10state) {
		this.equipment10state = equipment10state;
	}
	public String getEquipment11state() {
		return equipment11state;
	}
	public void setEquipment11state(String equipment11state) {
		this.equipment11state = equipment11state;
	}
	public String getEquipment12state() {
		return equipment12state;
	}
	public void setEquipment12state(String equipment12state) {
		this.equipment12state = equipment12state;
	}
	public String getEquipment13state() {
		return equipment13state;
	}
	public void setEquipment13state(String equipment13state) {
		this.equipment13state = equipment13state;
	}
	public String getEquipment14state() {
		return equipment14state;
	}
	public void setEquipment14state(String equipment14state) {
		this.equipment14state = equipment14state;
	}
	public String getEquipment15state() {
		return equipment15state;
	}
	public void setEquipment15state(String equipment15state) {
		this.equipment15state = equipment15state;
	}
	public String getEquipment16state() {
		return equipment16state;
	}
	public void setEquipment16state(String equipment16state) {
		this.equipment16state = equipment16state;
	}
	public String getEquipment17state() {
		return equipment17state;
	}
	public void setEquipment17state(String equipment17state) {
		this.equipment17state = equipment17state;
	}
	public String getEquipment18state() {
		return equipment18state;
	}
	public void setEquipment18state(String equipment18state) {
		this.equipment18state = equipment18state;
	}
	public String getEquipment19state() {
		return equipment19state;
	}
	public void setEquipment19state(String equipment19state) {
		this.equipment19state = equipment19state;
	}
	public String getEquipment20state() {
		return equipment20state;
	}
	public void setEquipment20state(String equipment20state) {
		this.equipment20state = equipment20state;
	}
	public String getEquipment21state() {
		return equipment21state;
	}
	public void setEquipment21state(String equipment21state) {
		this.equipment21state = equipment21state;
	}
	public String getEquipment22state() {
		return equipment22state;
	}
	public void setEquipment22state(String equipment22state) {
		this.equipment22state = equipment22state;
	}
	public String getEquipment23state() {
		return equipment23state;
	}
	public void setEquipment23state(String equipment23state) {
		this.equipment23state = equipment23state;
	}
	public String getEquipment24state() {
		return equipment24state;
	}
	public void setEquipment24state(String equipment24state) {
		this.equipment24state = equipment24state;
	}
	public String getEquipment25state() {
		return equipment25state;
	}
	public void setEquipment25state(String equipment25state) {
		this.equipment25state = equipment25state;
	}
	public Double getDetection1() {
		return detection1;
	}
	public void setDetection1(Double detection1) {
		this.detection1 = detection1;
	}
	public Double getDetection2() {
		return detection2;
	}
	public void setDetection2(Double detection2) {
		this.detection2 = detection2;
	}
	public Double getDetection3() {
		return detection3;
	}
	public void setDetection3(Double detection3) {
		this.detection3 = detection3;
	}
	public Double getDetection4() {
		return detection4;
	}
	public void setDetection4(Double detection4) {
		this.detection4 = detection4;
	}
	public Double getDetection5() {
		return detection5;
	}
	public void setDetection5(Double detection5) {
		this.detection5 = detection5;
	}
	public Double getDetection6() {
		return detection6;
	}
	public void setDetection6(Double detection6) {
		this.detection6 = detection6;
	}
	public Double getDetection7() {
		return detection7;
	}
	public void setDetection7(Double detection7) {
		this.detection7 = detection7;
	}
	public Double getDetection7dl() {
		return detection7dl;
	}
	public void setDetection7dl(Double detection7dl) {
		this.detection7dl = detection7dl;
	}
	public Double getDetection7ul() {
		return detection7ul;
	}
	public void setDetection7ul(Double detection7ul) {
		this.detection7ul = detection7ul;
	}
	public Double getDetection8() {
		return detection8;
	}
	public void setDetection8(Double detection8) {
		this.detection8 = detection8;
	}
	public Double getDetection8dl() {
		return detection8dl;
	}
	public void setDetection8dl(Double detection8dl) {
		this.detection8dl = detection8dl;
	}
	public Double getDetection8ul() {
		return detection8ul;
	}
	public void setDetection8ul(Double detection8ul) {
		this.detection8ul = detection8ul;
	}
	public Double getDetection9() {
		return detection9;
	}
	public void setDetection9(Double detection9) {
		this.detection9 = detection9;
	}
	public Double getDetection9dl() {
		return detection9dl;
	}
	public void setDetection9dl(Double detection9dl) {
		this.detection9dl = detection9dl;
	}
	public Double getDetection9ul() {
		return detection9ul;
	}
	public void setDetection9ul(Double detection9ul) {
		this.detection9ul = detection9ul;
	}
	public Double getDetection10() {
		return detection10;
	}
	public void setDetection10(Double detection10) {
		this.detection10 = detection10;
	}
	public Double getDetection10dl() {
		return detection10dl;
	}
	public void setDetection10dl(Double detection10dl) {
		this.detection10dl = detection10dl;
	}
	public Double getDetection10ul() {
		return detection10ul;
	}
	public void setDetection10ul(Double detection10ul) {
		this.detection10ul = detection10ul;
	}
	public Double getDetection11() {
		return detection11;
	}
	public void setDetection11(Double detection11) {
		this.detection11 = detection11;
	}
	public Double getDetection11dl() {
		return detection11dl;
	}
	public void setDetection11dl(Double detection11dl) {
		this.detection11dl = detection11dl;
	}
	public Double getDetection11ul() {
		return detection11ul;
	}
	public void setDetection11ul(Double detection11ul) {
		this.detection11ul = detection11ul;
	}
	public Double getDetection12() {
		return detection12;
	}
	public void setDetection12(Double detection12) {
		this.detection12 = detection12;
	}
	public Double getDetection12dl() {
		return detection12dl;
	}
	public void setDetection12dl(Double detection12dl) {
		this.detection12dl = detection12dl;
	}
	public Double getDetection12ul() {
		return detection12ul;
	}
	public void setDetection12ul(Double detection12ul) {
		this.detection12ul = detection12ul;
	}
	public Double getDetection13() {
		return detection13;
	}
	public void setDetection13(Double detection13) {
		this.detection13 = detection13;
	}
	public Double getDetection13dl() {
		return detection13dl;
	}
	public void setDetection13dl(Double detection13dl) {
		this.detection13dl = detection13dl;
	}
	public Double getDetection13ul() {
		return detection13ul;
	}
	public void setDetection13ul(Double detection13ul) {
		this.detection13ul = detection13ul;
	}
	public Double getDetection14() {
		return detection14;
	}
	public void setDetection14(Double detection14) {
		this.detection14 = detection14;
	}
	public Double getDetection14dl() {
		return detection14dl;
	}
	public void setDetection14dl(Double detection14dl) {
		this.detection14dl = detection14dl;
	}
	public Double getDetection14ul() {
		return detection14ul;
	}
	public void setDetection14ul(Double detection14ul) {
		this.detection14ul = detection14ul;
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
	public String getXiHongBengState() {
		return xiHongBengState;
	}
	public void setXiHongBengState(String xiHongBengState) {
		this.xiHongBengState = xiHongBengState;
	}
	public String getDayWater() {
		return dayWater;
	}
	public String getGeShanState() {
		return geShanState;
	}
	public void setGeShanState(String geShanState) {
		this.geShanState = geShanState;
	}
	public void setDayWater(String dayWater) {
		this.dayWater = dayWater;
	}
	public String getDayCod() {
		return dayCod;
	}
	public void setDayCod(String dayCod) {
		this.dayCod = dayCod;
	}
	public String getDayNh3n() {
		return dayNh3n;
	}
	public void setDayNh3n(String dayNh3n) {
		this.dayNh3n = dayNh3n;
	}
	public String getDayP() {
		return dayP;
	}
	public void setDayP(String dayP) {
		this.dayP = dayP;
	}
	
	
	public String getRunStateM() {
		return runStateM;
	}
	public void setRunStateM(String runStateM) {
		this.runStateM = runStateM;
	}
	public String getEquipment1Name() {
		return equipment1Name;
	}
	public void setEquipment1Name(String equipment1Name) {
		this.equipment1Name = equipment1Name;
	}
	public String getEquipment1ControlStrategy() {
		return equipment1ControlStrategy;
	}
	public void setEquipment1ControlStrategy(String equipment1ControlStrategy) {
		this.equipment1ControlStrategy = equipment1ControlStrategy;
	}
	public String getEquipment1NowState() {
		return equipment1NowState;
	}
	public void setEquipment1NowState(String equipment1NowState) {
		this.equipment1NowState = equipment1NowState;
	}
	public String getEquipment2Name() {
		return equipment2Name;
	}
	public void setEquipment2Name(String equipment2Name) {
		this.equipment2Name = equipment2Name;
	}
	public String getEquipment2ControlStrategy() {
		return equipment2ControlStrategy;
	}
	public void setEquipment2ControlStrategy(String equipment2ControlStrategy) {
		this.equipment2ControlStrategy = equipment2ControlStrategy;
	}
	public String getEquipment2NowState() {
		return equipment2NowState;
	}
	public void setEquipment2NowState(String equipment2NowState) {
		this.equipment2NowState = equipment2NowState;
	}
	public String getEquipment3Name() {
		return equipment3Name;
	}
	public void setEquipment3Name(String equipment3Name) {
		this.equipment3Name = equipment3Name;
	}
	public String getEquipment3ControlStrategy() {
		return equipment3ControlStrategy;
	}
	public void setEquipment3ControlStrategy(String equipment3ControlStrategy) {
		this.equipment3ControlStrategy = equipment3ControlStrategy;
	}
	public String getEquipment3NowState() {
		return equipment3NowState;
	}
	public void setEquipment3NowState(String equipment3NowState) {
		this.equipment3NowState = equipment3NowState;
	}
	public String getEquipment4Name() {
		return equipment4Name;
	}
	public void setEquipment4Name(String equipment4Name) {
		this.equipment4Name = equipment4Name;
	}
	public String getEquipment4ControlStrategy() {
		return equipment4ControlStrategy;
	}
	public void setEquipment4ControlStrategy(String equipment4ControlStrategy) {
		this.equipment4ControlStrategy = equipment4ControlStrategy;
	}
	public String getEquipment4NowState() {
		return equipment4NowState;
	}
	public void setEquipment4NowState(String equipment4NowState) {
		this.equipment4NowState = equipment4NowState;
	}
	public String getGaugeTempRange() {
		return gaugeTempRange;
	}
	public void setGaugeTempRange(String gaugeTempRange) {
		this.gaugeTempRange = gaugeTempRange;
	}
	public String getGaugeTempNowValue() {
		return gaugeTempNowValue;
	}
	public void setGaugeTempNowValue(String gaugeTempNowValue) {
		this.gaugeTempNowValue = gaugeTempNowValue;
	}
	public String getGaugePhRange() {
		return gaugePhRange;
	}
	public void setGaugePhRange(String gaugePhRange) {
		this.gaugePhRange = gaugePhRange;
	}
	public String getGaugePhNowValue() {
		return gaugePhNowValue;
	}
	public void setGaugePhNowValue(String gaugePhNowValue) {
		this.gaugePhNowValue = gaugePhNowValue;
	}
	public String getGaugeOrpRange() {
		return gaugeOrpRange;
	}
	public void setGaugeOrpRange(String gaugeOrpRange) {
		this.gaugeOrpRange = gaugeOrpRange;
	}
	public String getGaugeOrpNowValue() {
		return gaugeOrpNowValue;
	}
	public void setGaugeOrpNowValue(String gaugeOrpNowValue) {
		this.gaugeOrpNowValue = gaugeOrpNowValue;
	}
	public String getGaugeLsRange() {
		return gaugeLsRange;
	}
	public void setGaugeLsRange(String gaugeLsRange) {
		this.gaugeLsRange = gaugeLsRange;
	}
	public String getGaugeLsNowValue() {
		return gaugeLsNowValue;
	}
	public void setGaugeLsNowValue(String gaugeLsNowValue) {
		this.gaugeLsNowValue = gaugeLsNowValue;
	}
	public String getGaugeDoRange() {
		return gaugeDoRange;
	}
	public void setGaugeDoRange(String gaugeDoRange) {
		this.gaugeDoRange = gaugeDoRange;
	}
	public String getGaugeDoNowValue() {
		return gaugeDoNowValue;
	}
	public void setGaugeDoNowValue(String gaugeDoNowValue) {
		this.gaugeDoNowValue = gaugeDoNowValue;
	}
	public String getGaugeFlowRange() {
		return gaugeFlowRange;
	}
	public void setGaugeFlowRange(String gaugeFlowRange) {
		this.gaugeFlowRange = gaugeFlowRange;
	}
	public String getGaugeFlowNowRang() {
		return gaugeFlowNowRang;
	}
	public void setGaugeFlowNowRang(String gaugeFlowNowRang) {
		this.gaugeFlowNowRang = gaugeFlowNowRang;
	}
	public String getEquipment5Name() {
		return equipment5Name;
	}
	public void setEquipment5Name(String equipment5Name) {
		this.equipment5Name = equipment5Name;
	}
	public String getEquipment5ControlStrategy() {
		return equipment5ControlStrategy;
	}
	public void setEquipment5ControlStrategy(String equipment5ControlStrategy) {
		this.equipment5ControlStrategy = equipment5ControlStrategy;
	}
	public String getEquipment5NowState() {
		return equipment5NowState;
	}
	public void setEquipment5NowState(String equipment5NowState) {
		this.equipment5NowState = equipment5NowState;
	}
	public String getEquipment6Name() {
		return equipment6Name;
	}
	public void setEquipment6Name(String equipment6Name) {
		this.equipment6Name = equipment6Name;
	}
	public String getEquipment6ControlStrategy() {
		return equipment6ControlStrategy;
	}
	public void setEquipment6ControlStrategy(String equipment6ControlStrategy) {
		this.equipment6ControlStrategy = equipment6ControlStrategy;
	}
	public String getEquipment6NowState() {
		return equipment6NowState;
	}
	public void setEquipment6NowState(String equipment6NowState) {
		this.equipment6NowState = equipment6NowState;
	}
	public String getEquipment7Name() {
		return equipment7Name;
	}
	public void setEquipment7Name(String equipment7Name) {
		this.equipment7Name = equipment7Name;
	}
	public String getEquipment7ControlStrategy() {
		return equipment7ControlStrategy;
	}
	public void setEquipment7ControlStrategy(String equipment7ControlStrategy) {
		this.equipment7ControlStrategy = equipment7ControlStrategy;
	}
	public String getEquipment7NowState() {
		return equipment7NowState;
	}
	public void setEquipment7NowState(String equipment7NowState) {
		this.equipment7NowState = equipment7NowState;
	}
	public String getEquipment8Name() {
		return equipment8Name;
	}
	public void setEquipment8Name(String equipment8Name) {
		this.equipment8Name = equipment8Name;
	}
	public String getEquipment8ControlStrategy() {
		return equipment8ControlStrategy;
	}
	public void setEquipment8ControlStrategy(String equipment8ControlStrategy) {
		this.equipment8ControlStrategy = equipment8ControlStrategy;
	}
	public String getEquipment8NowState() {
		return equipment8NowState;
	}
	public void setEquipment8NowState(String equipment8NowState) {
		this.equipment8NowState = equipment8NowState;
	}
	public String getEquipment9Name() {
		return equipment9Name;
	}
	public void setEquipment9Name(String equipment9Name) {
		this.equipment9Name = equipment9Name;
	}
	public String getEquipment9ControlStrategy() {
		return equipment9ControlStrategy;
	}
	public void setEquipment9ControlStrategy(String equipment9ControlStrategy) {
		this.equipment9ControlStrategy = equipment9ControlStrategy;
	}
	public String getEquipment9NowState() {
		return equipment9NowState;
	}
	public void setEquipment9NowState(String equipment9NowState) {
		this.equipment9NowState = equipment9NowState;
	}
	public String getEquipment10Name() {
		return equipment10Name;
	}
	public void setEquipment10Name(String equipment10Name) {
		this.equipment10Name = equipment10Name;
	}
	public String getEquipment10ControlStrategy() {
		return equipment10ControlStrategy;
	}
	public void setEquipment10ControlStrategy(String equipment10ControlStrategy) {
		this.equipment10ControlStrategy = equipment10ControlStrategy;
	}
	public String getEquipment10NowState() {
		return equipment10NowState;
	}
	public void setEquipment10NowState(String equipment10NowState) {
		this.equipment10NowState = equipment10NowState;
	}
	public String getEquipment11Name() {
		return equipment11Name;
	}
	public void setEquipment11Name(String equipment11Name) {
		this.equipment11Name = equipment11Name;
	}
	public String getEquipment11ControlStrategy() {
		return equipment11ControlStrategy;
	}
	public void setEquipment11ControlStrategy(String equipment11ControlStrategy) {
		this.equipment11ControlStrategy = equipment11ControlStrategy;
	}
	public String getEquipment11NowState() {
		return equipment11NowState;
	}
	public void setEquipment11NowState(String equipment11NowState) {
		this.equipment11NowState = equipment11NowState;
	}
	public String getEquipment12Name() {
		return equipment12Name;
	}
	public void setEquipment12Name(String equipment12Name) {
		this.equipment12Name = equipment12Name;
	}
	public String getEquipment12ControlStrategy() {
		return equipment12ControlStrategy;
	}
	public void setEquipment12ControlStrategy(String equipment12ControlStrategy) {
		this.equipment12ControlStrategy = equipment12ControlStrategy;
	}
	public String getEquipment12NowState() {
		return equipment12NowState;
	}
	public void setEquipment12NowState(String equipment12NowState) {
		this.equipment12NowState = equipment12NowState;
	}
	public String getEquipment13Name() {
		return equipment13Name;
	}
	public void setEquipment13Name(String equipment13Name) {
		this.equipment13Name = equipment13Name;
	}
	public String getEquipment13ControlStrategy() {
		return equipment13ControlStrategy;
	}
	public void setEquipment13ControlStrategy(String equipment13ControlStrategy) {
		this.equipment13ControlStrategy = equipment13ControlStrategy;
	}
	public String getEquipment13NowState() {
		return equipment13NowState;
	}
	public void setEquipment13NowState(String equipment13NowState) {
		this.equipment13NowState = equipment13NowState;
	}
	public String getEquipment14Name() {
		return equipment14Name;
	}
	public void setEquipment14Name(String equipment14Name) {
		this.equipment14Name = equipment14Name;
	}
	public String getEquipment14ControlStrategy() {
		return equipment14ControlStrategy;
	}
	public void setEquipment14ControlStrategy(String equipment14ControlStrategy) {
		this.equipment14ControlStrategy = equipment14ControlStrategy;
	}
	public String getEquipment14NowState() {
		return equipment14NowState;
	}
	public void setEquipment14NowState(String equipment14NowState) {
		this.equipment14NowState = equipment14NowState;
	}
	public String getEquipment15Name() {
		return equipment15Name;
	}
	public void setEquipment15Name(String equipment15Name) {
		this.equipment15Name = equipment15Name;
	}
	public String getEquipment15ControlStrategy() {
		return equipment15ControlStrategy;
	}
	public void setEquipment15ControlStrategy(String equipment15ControlStrategy) {
		this.equipment15ControlStrategy = equipment15ControlStrategy;
	}
	public String getEquipment15NowState() {
		return equipment15NowState;
	}
	public void setEquipment15NowState(String equipment15NowState) {
		this.equipment15NowState = equipment15NowState;
	}
	public String getEquipment16Name() {
		return equipment16Name;
	}
	public void setEquipment16Name(String equipment16Name) {
		this.equipment16Name = equipment16Name;
	}
	public String getEquipment16ControlStrategy() {
		return equipment16ControlStrategy;
	}
	public void setEquipment16ControlStrategy(String equipment16ControlStrategy) {
		this.equipment16ControlStrategy = equipment16ControlStrategy;
	}
	public String getEquipment16NowState() {
		return equipment16NowState;
	}
	public void setEquipment16NowState(String equipment16NowState) {
		this.equipment16NowState = equipment16NowState;
	}
	public String getEquipment17Name() {
		return equipment17Name;
	}
	public void setEquipment17Name(String equipment17Name) {
		this.equipment17Name = equipment17Name;
	}
	public String getEquipment17ControlStrategy() {
		return equipment17ControlStrategy;
	}
	public void setEquipment17ControlStrategy(String equipment17ControlStrategy) {
		this.equipment17ControlStrategy = equipment17ControlStrategy;
	}
	public String getEquipment17NowState() {
		return equipment17NowState;
	}
	public void setEquipment17NowState(String equipment17NowState) {
		this.equipment17NowState = equipment17NowState;
	}
	public String getEquipment18Name() {
		return equipment18Name;
	}
	public void setEquipment18Name(String equipment18Name) {
		this.equipment18Name = equipment18Name;
	}
	public String getEquipment18ControlStrategy() {
		return equipment18ControlStrategy;
	}
	public void setEquipment18ControlStrategy(String equipment18ControlStrategy) {
		this.equipment18ControlStrategy = equipment18ControlStrategy;
	}
	public String getEquipment18NowState() {
		return equipment18NowState;
	}
	public void setEquipment18NowState(String equipment18NowState) {
		this.equipment18NowState = equipment18NowState;
	}
	public String getEquipment19Name() {
		return equipment19Name;
	}
	public void setEquipment19Name(String equipment19Name) {
		this.equipment19Name = equipment19Name;
	}
	public String getEquipment19ControlStrategy() {
		return equipment19ControlStrategy;
	}
	public void setEquipment19ControlStrategy(String equipment19ControlStrategy) {
		this.equipment19ControlStrategy = equipment19ControlStrategy;
	}
	public String getEquipment19NowState() {
		return equipment19NowState;
	}
	public void setEquipment19NowState(String equipment19NowState) {
		this.equipment19NowState = equipment19NowState;
	}
	public String getEquipment20Name() {
		return equipment20Name;
	}
	public void setEquipment20Name(String equipment20Name) {
		this.equipment20Name = equipment20Name;
	}
	public String getEquipment20ControlStrategy() {
		return equipment20ControlStrategy;
	}
	public void setEquipment20ControlStrategy(String equipment20ControlStrategy) {
		this.equipment20ControlStrategy = equipment20ControlStrategy;
	}
	public String getEquipment20NowState() {
		return equipment20NowState;
	}
	public void setEquipment20NowState(String equipment20NowState) {
		this.equipment20NowState = equipment20NowState;
	}
	public String getEquipment21Name() {
		return equipment21Name;
	}
	public void setEquipment21Name(String equipment21Name) {
		this.equipment21Name = equipment21Name;
	}
	public String getEquipment21ControlStrategy() {
		return equipment21ControlStrategy;
	}
	public void setEquipment21ControlStrategy(String equipment21ControlStrategy) {
		this.equipment21ControlStrategy = equipment21ControlStrategy;
	}
	public String getEquipment21NowState() {
		return equipment21NowState;
	}
	public void setEquipment21NowState(String equipment21NowState) {
		this.equipment21NowState = equipment21NowState;
	}
	public String getEquipment22Name() {
		return equipment22Name;
	}
	public void setEquipment22Name(String equipment22Name) {
		this.equipment22Name = equipment22Name;
	}
	public String getEquipment22ControlStrategy() {
		return equipment22ControlStrategy;
	}
	public void setEquipment22ControlStrategy(String equipment22ControlStrategy) {
		this.equipment22ControlStrategy = equipment22ControlStrategy;
	}
	public String getEquipment22NowState() {
		return equipment22NowState;
	}
	public void setEquipment22NowState(String equipment22NowState) {
		this.equipment22NowState = equipment22NowState;
	}
	public String getEquipment23Name() {
		return equipment23Name;
	}
	public void setEquipment23Name(String equipment23Name) {
		this.equipment23Name = equipment23Name;
	}
	public String getEquipment23ControlStrategy() {
		return equipment23ControlStrategy;
	}
	public void setEquipment23ControlStrategy(String equipment23ControlStrategy) {
		this.equipment23ControlStrategy = equipment23ControlStrategy;
	}
	public String getEquipment23NowState() {
		return equipment23NowState;
	}
	public void setEquipment23NowState(String equipment23NowState) {
		this.equipment23NowState = equipment23NowState;
	}
	public String getEquipment24Name() {
		return equipment24Name;
	}
	public void setEquipment24Name(String equipment24Name) {
		this.equipment24Name = equipment24Name;
	}
	public String getEquipment24ControlStrategy() {
		return equipment24ControlStrategy;
	}
	public void setEquipment24ControlStrategy(String equipment24ControlStrategy) {
		this.equipment24ControlStrategy = equipment24ControlStrategy;
	}
	public String getEquipment24NowState() {
		return equipment24NowState;
	}
	public void setEquipment24NowState(String equipment24NowState) {
		this.equipment24NowState = equipment24NowState;
	}
	public String getEquipment25Name() {
		return equipment25Name;
	}
	public void setEquipment25Name(String equipment25Name) {
		this.equipment25Name = equipment25Name;
	}
	public String getEquipment25ControlStrategy() {
		return equipment25ControlStrategy;
	}
	public void setEquipment25ControlStrategy(String equipment25ControlStrategy) {
		this.equipment25ControlStrategy = equipment25ControlStrategy;
	}
	public String getEquipment25NowState() {
		return equipment25NowState;
	}
	public void setEquipment25NowState(String equipment25NowState) {
		this.equipment25NowState = equipment25NowState;
	}
	public Date getLastUpdatetime() {
		return lastUpdatetime;
	}
	public void setLastUpdatetime(Date lastUpdatetime) {
		this.lastUpdatetime = lastUpdatetime;
	}
	public int getIsAbnormal() {
		return isAbnormal;
	}
	public void setIsAbnormal(int isAbnormal) {
		this.isAbnormal = isAbnormal;
	}
	public Integer getAdministratorid() {
		return administratorid;
	}
	public void setAdministratorid(Integer administratorid) {
		this.administratorid = administratorid;
	}
	public String getAirSched() {
		return airSched;
	}
	public void setAirSched(String airSched) {
		this.airSched = airSched;
	}
	public String getWaterSched() {
		return waterSched;
	}
	public void setWaterSched(String waterSched) {
		this.waterSched = waterSched;
	}
	public String getMudSched() {
		return mudSched;
	}
	public void setMudSched(String mudSched) {
		this.mudSched = mudSched;
	}
	public String getFlowSched() {
		return flowSched;
	}
	public void setFlowSched(String flowSched) {
		this.flowSched = flowSched;
	}
	public String getBackupSched() {
		return backupSched;
	}
	public void setBackupSched(String backupSched) {
		this.backupSched = backupSched;
	}
	public String getDeviceAlert() {
		return deviceAlert;
	}
	public void setDeviceAlert(String deviceAlert) {
		this.deviceAlert = deviceAlert;
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
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
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

	public Double getDetection15() {
		return detection15;
	}

	public void setDetection15(Double detection15) {
		this.detection15 = detection15;
	}
}
