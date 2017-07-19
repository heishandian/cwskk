package com.yaoli.beans;

public class Devicemessage {
    private Integer id;

    private String serialnumber;

    private String assemblytime;

    private String assemblers;

    private String testtime;

    private String testers;

    private String entrytime;

    private String recdepartment;

    private String deliverytime;

    private String recpeople;

    private String installtime;

    private String installpeople;

    private String controllid;

    private String proaffiliation;

    private String installsite;

    private String currentstatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber == null ? null : serialnumber.trim();
    }

    public String getAssemblytime() {
        return assemblytime;
    }

    public void setAssemblytime(String assemblytime) {
        this.assemblytime = assemblytime;
    }

    public String getAssemblers() {
        return assemblers;
    }

    public void setAssemblers(String assemblers) {
        this.assemblers = assemblers == null ? null : assemblers.trim();
    }

    public String getTesttime() {
        return testtime;
    }

    public void setTesttime(String testtime) {
        this.testtime = testtime;
    }

    public String getTesters() {
        return testers;
    }

    public void setTesters(String testers) {
        this.testers = testers == null ? null : testers.trim();
    }

    public String getEntrytime() {
        return entrytime;
    }

    public void setEntrytime(String entrytime) {
        this.entrytime = entrytime == null ? null : entrytime.trim();
    }

    public String getRecdepartment() {
        return recdepartment;
    }

    public void setRecdepartment(String recdepartment) {
        this.recdepartment = recdepartment == null ? null : recdepartment.trim();
    }

    public String getDeliverytime() {
        return deliverytime;
    }

    public void setDeliverytime(String deliverytime) {
        this.deliverytime = deliverytime;
    }

    public String getRecpeople() {
        return recpeople;
    }

    public void setRecpeople(String recpeople) {
        this.recpeople = recpeople == null ? null : recpeople.trim();
    }

    public String getInstalltime() {
        return installtime;
    }

    public void setInstalltime(String installtime) {
        this.installtime = installtime;
    }

    public String getInstallpeople() {
        return installpeople;
    }

    public void setInstallpeople(String installpeople) {
        this.installpeople = installpeople == null ? null : installpeople.trim();
    }

    public String getControllid() {
        return controllid;
    }

    public void setControllid(String controllid) {
        this.controllid = controllid == null ? null : controllid.trim();
    }

    public String getProaffiliation() {
        return proaffiliation;
    }

    public void setProaffiliation(String proaffiliation) {
        this.proaffiliation = proaffiliation == null ? null : proaffiliation.trim();
    }

    public String getInstallsite() {
        return installsite;
    }

    public void setInstallsite(String installsite) {
        this.installsite = installsite == null ? null : installsite.trim();
    }

    public String getCurrentstatus() {
        return currentstatus;
    }

    public void setCurrentstatus(String currentstatus) {
        this.currentstatus = currentstatus == null ? null : currentstatus.trim();
    }

    @Override
    public String toString() {
        return "Devicemessage{" +
                "id=" + id +
                ", serialnumber='" + serialnumber + '\'' +
                ", assemblytime=" + assemblytime +
                ", assemblers='" + assemblers + '\'' +
                ", testtime=" + testtime +
                ", testers='" + testers + '\'' +
                ", entrytime='" + entrytime + '\'' +
                ", recdepartment='" + recdepartment + '\'' +
                ", deliverytime=" + deliverytime +
                ", recpeople='" + recpeople + '\'' +
                ", installtime=" + installtime +
                ", installpeople='" + installpeople + '\'' +
                ", controllid='" + controllid + '\'' +
                ", proaffiliation='" + proaffiliation + '\'' +
                ", installsite='" + installsite + '\'' +
                ", currentstatus='" + currentstatus + '\'' +
                '}';
    }
}