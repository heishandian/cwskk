package com.yaoli.vo.huangkai;

/**
 * Created by kk on 2017/6/29.
 */
public class ProductMessageSearchConditionVO {
    String serialnumber;
    String currentstatus;
    String recdepartment;

    public ProductMessageSearchConditionVO(String serialnumber, String equipmentsstatus, String recdepartment) {
        this.serialnumber = serialnumber;
        this.currentstatus = equipmentsstatus;
        this.recdepartment = recdepartment;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public String getEquipmentsstatus() {
        return currentstatus;
    }

    public String getRecdepartment() {
        return recdepartment;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public void setEquipmentsstatus(String equipmentsstatus) {
        this.currentstatus = equipmentsstatus;
    }

    public void setRecdepartment(String recdepartment) {
        this.recdepartment = recdepartment;
    }
}
