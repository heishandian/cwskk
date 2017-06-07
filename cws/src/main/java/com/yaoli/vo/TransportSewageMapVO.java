package com.yaoli.vo;

/**
 * 用于地图页面上显示的
 * @author will
 *
 */
public class TransportSewageMapVO {
	private int isAbnormal;
	
    private Integer sewageid;

    private Integer areaid;
    
    private String shortTitle;

    private String name;
    
    private Double coordinatex;

    private Double coordinatey;

	public int getIsAbnormal() {
		return isAbnormal;
	}

	public void setIsAbnormal(int isAbnormal) {
		this.isAbnormal = isAbnormal;
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
}
