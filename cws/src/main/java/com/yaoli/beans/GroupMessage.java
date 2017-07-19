package com.yaoli.beans;

public class GroupMessage {
    private Integer id;

    private String name;

    private Integer areaid;

    private String leader;

    private String telephone;

    private String cartype;

    private String carnumber;

    private String region;

    private Integer numberofstation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader == null ? null : leader.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getCartype() {
        return cartype;
    }

    public void setCartype(String cartype) {
        this.cartype = cartype == null ? null : cartype.trim();
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber == null ? null : carnumber.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public Integer getNumberofstation() {
        return numberofstation;
    }

    public void setNumberofstation(Integer numberofstation) {
        this.numberofstation = numberofstation;
    }

    public GroupMessage(String name, Integer areaid, String leader, String telephone, String cartype, String carnumber, String region, Integer numberofstation) {
        this.name = name;
        this.areaid = areaid;
        this.leader = leader;
        this.telephone = telephone;
        this.cartype = cartype;
        this.carnumber = carnumber;
        this.region = region;
        this.numberofstation = numberofstation;
    }

    public GroupMessage() {
    }
}