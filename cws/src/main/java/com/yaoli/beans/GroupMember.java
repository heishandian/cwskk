package com.yaoli.beans;

public class GroupMember {
    private Integer id;

    private String name;

    private String position;

    private String telephone;

    private Integer groupid;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public GroupMember(String name, String position, String telephone, Integer groupid) {
        this.name = name;
        this.position = position;
        this.telephone = telephone;
        this.groupid = groupid;
    }

    public GroupMember() {
    }
}