package com.yaoli.innovation;

import java.util.List;

public abstract class Menu {
    private Integer id;

    private Integer pId;

    private String name;

    private String url;

    private String type;

    private Integer sortweight;
    
    private String target;
    
    private boolean checked;
    
    public abstract void addMenu(Menu menu);
    
    public abstract List<Menu> getChildren();
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSortweight() {
		return sortweight;
	}

	public void setSortweight(Integer sortweight) {
		this.sortweight = sortweight;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
    
    
}
