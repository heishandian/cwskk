package com.yaoli.util;

import java.util.List;

import com.yaoli.beans.RunData;
import com.yaoli.service.IRunDataService;

public class GetLatestSewageRunDataThread extends Thread{
	
	private List<RunData> runDatas;
	
	private IRunDataService iRunDataService;
	
	private Integer areaid;
	
	private Integer sewageid;
	
	private RunData runData;
	
	public Integer getSewageid() {
		return sewageid;
	}

	public void setSewageid(Integer sewageid) {
		this.sewageid = sewageid;
	}

	public RunData getRunData() {
		return runData;
	}

	public void setRunData(RunData runData) {
		this.runData = runData;
	}

	public List<RunData> getRunDatas() {
		return runDatas;
	}

	public void setRunDatas(List<RunData> runDatas) {
		this.runDatas = runDatas;
	}

	public IRunDataService getiRunDataService() {
		return iRunDataService;
	}

	public void setiRunDataService(IRunDataService iRunDataService) {
		this.iRunDataService = iRunDataService;
	}
	
	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	@SuppressWarnings("unused")
	private GetLatestSewageRunDataThread(){}
	
	public GetLatestSewageRunDataThread(IRunDataService iRunDataService,List<RunData> runDatas ,Integer areaid){
		this.runDatas = runDatas;
		this.iRunDataService = iRunDataService;
		this.areaid = areaid;
	}
	
	public GetLatestSewageRunDataThread(IRunDataService iRunDataService,List<RunData> runDatas){
		this.runDatas = runDatas;
		this.iRunDataService = iRunDataService;
	}
	
	public GetLatestSewageRunDataThread(IRunDataService iRunDataService,RunData runDatas,Integer sewageid){
		this.runData = runDatas;
		this.iRunDataService = iRunDataService;
		this.sewageid = sewageid;
	}
	
	@Override
	public void run() {
		if(sewageid != null){
			runData = iRunDataService.getLatestRunData(this.sewageid);
		}else {
			if(areaid != null){
				runDatas = iRunDataService.getLatestSewageRunDataByAreaid(this.areaid);
			}else {
				runDatas = iRunDataService.getLatestSewageRunData();
			}
		}
	}
}
