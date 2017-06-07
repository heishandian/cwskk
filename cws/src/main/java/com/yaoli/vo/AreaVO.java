package com.yaoli.vo;

import java.util.List;

public class AreaVO {
    private Integer id;

    private Integer pid;

    private String name;

    private String introduce;

    private Double coordinatex;

    private Double coordinatey;
    
    //管理员id
    public String adminId;
    
    //管理名称
    public String adminName;
    
    //管理员手机号
    public String adminTelephone;
    
    public List<Integer> selectedIds;
    
	public List<Integer> getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(List<Integer> selectedIds) {
		this.selectedIds = selectedIds;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
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
		this.name = name;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminTelephone() {
		return adminTelephone;
	}

	public void setAdminTelephone(String adminTelephone) {
		this.adminTelephone = adminTelephone;
	}
}
