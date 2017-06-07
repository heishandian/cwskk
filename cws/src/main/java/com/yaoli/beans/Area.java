package com.yaoli.beans;

public class Area {
    private Integer id;

    private Integer pid;

    private String name;

    private String introduce;

    private Double coordinatex;

    private Double coordinatey;
    
    //是否是短信发送的地区
    private String issendarea;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getIssendarea() {
		return issendarea;
	}

	public void setIssendarea(String issendarea) {
		this.issendarea = issendarea;
	}
    
}