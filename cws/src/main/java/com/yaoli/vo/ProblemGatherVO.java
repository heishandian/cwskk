package com.yaoli.vo;

import java.util.Date;

public class ProblemGatherVO {
    private Integer id;

    private String title;

    private String description;

    private String finder;

    private Date findtime;

    private Integer sewageid;
    
    private String sewagename;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFinder() {
		return finder;
	}

	public void setFinder(String finder) {
		this.finder = finder;
	}

	public Date getFindtime() {
		return findtime;
	}

	public void setFindtime(Date findtime) {
		this.findtime = findtime;
	}

	public Integer getSewageid() {
		return sewageid;
	}

	public void setSewageid(Integer sewageid) {
		this.sewageid = sewageid;
	}

	public String getSewagename() {
		return sewagename;
	}

	public void setSewagename(String sewagename) {
		this.sewagename = sewagename;
	}
    
}
