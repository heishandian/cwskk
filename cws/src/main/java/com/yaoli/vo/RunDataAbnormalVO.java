package com.yaoli.vo;

import java.util.Date;

public class RunDataAbnormalVO {
	private Date lasttestingtime;

	//------添加运营编号字段-
	private String operationnum;

    private Long runid;

    private Integer sewageid;

    private Date testingtime;

    //-----------以下字段废弃----------------//
    private Byte equipment1state;

    private Byte equipment2state;

    private Byte equipment3state;

    private Byte equipment4state;

    private Byte equipment5state;

    private Byte equipment1isrepaired;

    private Byte equipment2isrepaired;

    private Byte equipment3isrepaired;

    private Byte equipment4isrepaired;

    private Byte equipment5isrepaired;
    //------------以上字段废弃-----------------//
    
    private String equipmentname;
    
    private Byte isrepaired;
    
    private String sewageName;
    
    private String runidAndEquipmentName;
    
    private int  controlmethod;
    
    private Byte equipmentstate;
    
    private Byte equipmentno;

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

	public int getControlmethod() {
		return controlmethod;
	}

	public void setControlmethod(int controlmethod) {
		this.controlmethod = controlmethod;
	}

	public String getRunidAndEquipmentName() {
		return runidAndEquipmentName;
	}

	public void setRunidAndEquipmentName(String runidAndEquipmentName) {
		this.runidAndEquipmentName = runidAndEquipmentName;
	}

	public String getEquipmentname() {
		return equipmentname;
	}

	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}

	public Byte getIsrepaired() {
		return isrepaired;
	}

	public void setIsrepaired(Byte isrepaired) {
		this.isrepaired = isrepaired;
	}

	public Long getRunid() {
		return runid;
	}

	public void setRunid(Long runid) {
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

	public Byte getEquipment1state() {
		return equipment1state;
	}

	public void setEquipment1state(Byte equipment1state) {
		this.equipment1state = equipment1state;
	}

	public Byte getEquipment2state() {
		return equipment2state;
	}

	public void setEquipment2state(Byte equipment2state) {
		this.equipment2state = equipment2state;
	}

	public Byte getEquipment3state() {
		return equipment3state;
	}

	public void setEquipment3state(Byte equipment3state) {
		this.equipment3state = equipment3state;
	}

	public Byte getEquipment4state() {
		return equipment4state;
	}

	public void setEquipment4state(Byte equipment4state) {
		this.equipment4state = equipment4state;
	}

	public Byte getEquipment5state() {
		return equipment5state;
	}

	public void setEquipment5state(Byte equipment5state) {
		this.equipment5state = equipment5state;
	}

	public Byte getEquipment1isrepaired() {
		return equipment1isrepaired;
	}

	public void setEquipment1isrepaired(Byte equipment1isrepaired) {
		this.equipment1isrepaired = equipment1isrepaired;
	}

	public Byte getEquipment2isrepaired() {
		return equipment2isrepaired;
	}

	public void setEquipment2isrepaired(Byte equipment2isrepaired) {
		this.equipment2isrepaired = equipment2isrepaired;
	}

	public Byte getEquipment3isrepaired() {
		return equipment3isrepaired;
	}

	public void setEquipment3isrepaired(Byte equipment3isrepaired) {
		this.equipment3isrepaired = equipment3isrepaired;
	}

	public Byte getEquipment4isrepaired() {
		return equipment4isrepaired;
	}

	public void setEquipment4isrepaired(Byte equipment4isrepaired) {
		this.equipment4isrepaired = equipment4isrepaired;
	}

	public Byte getEquipment5isrepaired() {
		return equipment5isrepaired;
	}

	public void setEquipment5isrepaired(Byte equipment5isrepaired) {
		this.equipment5isrepaired = equipment5isrepaired;
	}

	public String getSewageName() {
		return sewageName;
	}

	public void setSewageName(String sewageName) {
		this.sewageName = sewageName;
	}

	public String getOperationnum() {
		return operationnum;
	}

	public void setOperationnum(String operationnum) {
		this.operationnum = operationnum;
	}

	public Date getLasttestingtime() {
		return lasttestingtime;
	}

	public void setLasttestingtime(Date lasttestingtime) {
		this.lasttestingtime = lasttestingtime;
	}
}
