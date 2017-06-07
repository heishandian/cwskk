package com.yaoli.util;

/**
 * 用于分页
 * @author yaoli
 *
 */
public class SysPagingUtil {
	/*以下两个貌似没有用*/
	public String pageSize;

	public String pageNumber;
	
	public Object rows;
	
	public String total;
	
	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	
}
