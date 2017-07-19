package com.yaoli.beans;

import java.util.Date;

public class GroupWorkLoad {
    private Integer id;

    private Integer groupid;

    private Date time;

    private String inspection;

    private String equipmaintain;

    private String equipprotect;

    private String envirprotect;

    private String workassist;

    private String other;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getInspection() {
        return inspection;
    }

    public void setInspection(String inspection) {
        this.inspection = inspection == null ? null : inspection.trim();
    }

    public String getEquipmaintain() {
        return equipmaintain;
    }

    public void setEquipmaintain(String equipmaintain) {
        this.equipmaintain = equipmaintain == null ? null : equipmaintain.trim();
    }

    public String getEquipprotect() {
        return equipprotect;
    }

    public void setEquipprotect(String equipprotect) {
        this.equipprotect = equipprotect == null ? null : equipprotect.trim();
    }

    public String getEnvirprotect() {
        return envirprotect;
    }

    public void setEnvirprotect(String envirprotect) {
        this.envirprotect = envirprotect == null ? null : envirprotect.trim();
    }

    public String getWorkassist() {
        return workassist;
    }

    public void setWorkassist(String workassist) {
        this.workassist = workassist == null ? null : workassist.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    @Override
    public String toString() {
        return "GroupWorkLoad{" +
                "id=" + id +
                ", groupid=" + groupid +
                ", time=" + time +
                ", inspection='" + inspection + '\'' +
                ", equipmaintain='" + equipmaintain + '\'' +
                ", equipprotect='" + equipprotect + '\'' +
                ", envirprotect='" + envirprotect + '\'' +
                ", workassist='" + workassist + '\'' +
                ", other='" + other + '\'' +
                '}';
    }
}