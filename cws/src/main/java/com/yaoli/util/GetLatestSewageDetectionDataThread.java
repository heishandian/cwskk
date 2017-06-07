package com.yaoli.util;

import java.util.List;

import com.yaoli.beans.DetectionData;
import com.yaoli.service.IDetectionDataService;

public class GetLatestSewageDetectionDataThread extends Thread{
	private IDetectionDataService iDetectionDataService;
	
	private List<DetectionData> detectionDatas;
	
	private Integer areaid;
	
	private DetectionData detectionData;
	
	private Integer sewageid;
	
	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}

	public DetectionData getDetectionData() {
		return detectionData;
	}

	public void setDetectionData(DetectionData detectionData) {
		this.detectionData = detectionData;
	}

	public Integer getSewageid() {
		return sewageid;
	}

	public void setSewageid(Integer sewageid) {
		this.sewageid = sewageid;
	}

	public IDetectionDataService getiDetectionDataService() {
		return iDetectionDataService;
	}

	public void setiDetectionDataService(IDetectionDataService iDetectionDataService) {
		this.iDetectionDataService = iDetectionDataService;
	}

	public List<DetectionData> getDetectionDatas() {
		return detectionDatas;
	}

	public void setDetectionDatas(List<DetectionData> detectionDatas) {
		this.detectionDatas = detectionDatas;
	}

	@SuppressWarnings("unused")
	private GetLatestSewageDetectionDataThread(){};
	
	public GetLatestSewageDetectionDataThread(IDetectionDataService iDetectionDataService,List<DetectionData> getDetectionDatas){
		this.detectionDatas = getDetectionDatas;
		this.iDetectionDataService = iDetectionDataService;
	}
	
	public GetLatestSewageDetectionDataThread(IDetectionDataService iDetectionDataService,List<DetectionData> getDetectionDatas,Integer areaid){
		this.detectionDatas = getDetectionDatas;
		this.iDetectionDataService = iDetectionDataService;
		this.areaid = areaid;
	}
	
	public GetLatestSewageDetectionDataThread(IDetectionDataService iDetectionDataService,DetectionData getDetectionData,Integer sewageid){
		this.detectionData = getDetectionData;
		this.iDetectionDataService = iDetectionDataService;
		this.sewageid = sewageid;
	}
	
	@Override
	public void run() {
		if(sewageid != null){
			detectionData = iDetectionDataService.getLatestDetectionData(this.sewageid);
		}else {
			if(areaid != null){
				detectionDatas = iDetectionDataService.getLatestSewageDetectionDataByAreaId(this.areaid);
			}else {
				detectionDatas = iDetectionDataService.getLatestSewageDetectionData();
			}
		}
	}
}
