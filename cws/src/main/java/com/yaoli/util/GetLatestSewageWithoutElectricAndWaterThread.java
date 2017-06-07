package com.yaoli.util;

import java.util.List;

import com.yaoli.beans.Sewage;
import com.yaoli.service.ISewageService;

public class GetLatestSewageWithoutElectricAndWaterThread extends Thread{
	private List<Sewage> withoutEletrictAndWater;
	
	private ISewageService iSewageService;
	
	private Integer areaid;
	
	@SuppressWarnings("unused")
	private GetLatestSewageWithoutElectricAndWaterThread(){}
	
	public GetLatestSewageWithoutElectricAndWaterThread(ISewageService iSewageService,List<Sewage> sewages){
		this.iSewageService = iSewageService;
		this.withoutEletrictAndWater = sewages;
	}
	
	public GetLatestSewageWithoutElectricAndWaterThread(ISewageService iSewageService,List<Sewage> sewages,Integer areaid){
		this.iSewageService = iSewageService;
		this.withoutEletrictAndWater = sewages;
		this.areaid = areaid;
	}
	
	
	@Override
	public void run() {
		if(areaid != null){
			withoutEletrictAndWater = iSewageService.getLatestSewageWithoutElectricAndWaterByAreaid(this.areaid);
		}else {
			withoutEletrictAndWater = iSewageService.getLatestSewageWithoutElectricAndWater();
		}
	}

	public List<Sewage> getWithoutEletrictAndWater() {
		return withoutEletrictAndWater;
	}

	public void setWithoutEletrictAndWater(List<Sewage> withoutEletrictAndWater) {
		this.withoutEletrictAndWater = withoutEletrictAndWater;
	}

	public ISewageService getiSewageService() {
		return iSewageService;
	}

	public void setiSewageService(ISewageService iSewageService) {
		this.iSewageService = iSewageService;
	}

	public Integer getAreaid() {
		return areaid;
	}

	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	
}
