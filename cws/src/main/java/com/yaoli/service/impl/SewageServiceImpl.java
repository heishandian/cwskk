package com.yaoli.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaoli.beans.Sewage;
import com.yaoli.dao.SewageMapper;
import com.yaoli.dao.SewageMonitorVOMapper;
import com.yaoli.service.ISewageService;
import com.yaoli.vo.SewageMonitorVO;
import com.yaoli.vo.SewageVO;

@Service("sewageService")
public class SewageServiceImpl implements ISewageService {


	@Resource
	public SewageMapper sewageMapper;

	@Resource
	public SewageMonitorVOMapper sewageMonitorVOMapper;

	/**
	 * 得到所有的站点
	 *
	 * @return
	 */
	@Override
	public List<Sewage> getAllSewages() {
		List<Sewage> sewages = sewageMapper.getAllSewages();
		return sewages;
	}

	/**
	 * 根据的地区号码查询站点
	 */
	@Override
	public List<Sewage> getSewagesByAreaId(int areaid) {
		List<Sewage> sewages = sewageMapper.getSewagesByAreaId(areaid);
		return sewages;
	}

	/**
	 * 根据运营号码或者地点简称查询
	 */
	@Override
	public List<Sewage> getSewagesBySearchNameOrId(String condition) {
		List<Sewage> sewages = sewageMapper.getSewagesBySearchNameOrId(condition);
		return sewages;
	}

	@Override
	public Sewage selectByPrimaryKey(Integer sewageid) {
		Sewage sewage = sewageMapper.selectByPrimaryKey(sewageid);
		return sewage;
	}

	public SewageMonitorVO getSewageMonitorVOBySewageId(Integer sewageid) {
		SewageMonitorVO sewageMonitorVO = sewageMonitorVOMapper.getSewageMonitorVOBySewageId(sewageid);
		return sewageMonitorVO;
	}

	@Override
	public List<SewageVO> getSewagesByPaging(Map<String, String> map) {
		List<SewageVO> sewageVOs = sewageMapper.getSewagesByPaging(map);
		return sewageVOs;
	}

	@Override
	public int getTotalSewageCount() {
		return sewageMapper.getTotalSewageCount();
	}

	@Override
	public int insert(Sewage record) {
		return sewageMapper.insert(record);
	}

	@Override
	public int insertSelective(Sewage record) {
		return sewageMapper.insertSelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer sewageid) {
		return sewageMapper.deleteByPrimaryKey(sewageid);
	}

	@Override
	public int getSewageCountLinktoAreaBy(Integer areaId) {
		return sewageMapper.getSewageCountLinktoAreaBy(areaId);
	}

	@Override
	public List<Sewage> getLatestSewageDetectionData() {
		return sewageMapper.getLatestSewageDetectionData();
	}

	@Override
	public Sewage getLatestSewageDetectionDataBySewageId(int sewageId) {
		return sewageMapper.getLatestSewageDetectionDataBySewageId(sewageId);
	}

	@Deprecated
	@Override
	public double getSewageTotalWaterFlow(int sewageId) {
		return sewageMapper.getSewageTotalWaterFlow(sewageId);
	}

	@Override
	public List<Sewage> getLatestSewageWithoutElectricAndWater() {
		return sewageMapper.getLatestSewageWithoutElectricAndWater();
	}

	@Override
	public List<Sewage> getLatestSewageWithoutElectricAndWaterByAreaid(int areaid) {
		return sewageMapper.getLatestSewageWithoutElectricAndWaterByAreaid(areaid);
	}

	@Override
	public List<Sewage> getWithoutElectricAndWaterByPaingAndCondition(
			Map<String, String> map) {
		return sewageMapper.getWithoutElectricAndWaterByPaingAndCondition(map);
	}

	@Override
	public int getWithoutElectricAndWaterPaingAndConditionTotal(
			Map<String, String> map) {
		return sewageMapper.getWithoutElectricAndWaterPaingAndConditionTotal(map);
	}

	@Override
	public int getSewageHasRecordYesterday(int sewageid) {
		return sewageMapper.getSewageHasRecordYesterday(sewageid);
	}

	@Override
	public int updateByPrimaryKeySelective(Sewage record) {
		return sewageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Sewage record) {
		return sewageMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateSewageByPrimaryKeyCondition(Sewage recored) {
		return sewageMapper.updateSewageByPrimaryKeyCondition(recored);
	}

	@Override
	public List<Sewage> sewageWaterdayReport(Map<String, String> map) {
		return sewageMapper.sewageWaterdayReport(map);
	}

	@Override
	public List<Sewage> sewageWatermonthReport(Map<String, String> map) {
		return sewageMapper.sewageWatermonthReport(map);
	}

	@Override
	public List<Sewage> sewageWateryearReport(Map<String, String> map) {
		return sewageMapper.sewageWateryearReport(map);
	}

	@Override
	public int sewageWaterdayCount(Map<String, String> map) {
		return sewageMapper.sewageWaterdayCount(map);
	}

	@Override
	public int sewageWatermonthCount(Map<String, String> map) {
		return sewageMapper.sewageWatermonthCount(map);
	}

	@Override
	public int sewageWateryearCount(Map<String, String> map) {
		return sewageMapper.sewageWateryearCount(map);
	}

	@Override
	public int checkSewageIdExist(int sewageid) {
		return sewageMapper.checkSewageIdExist(sewageid);
	}

	@Override
	public Sewage searchSewageidByOperatornum(String operationnum) {
		return sewageMapper.searchSewageidByOperatornum(operationnum);
	}

	@Override
	public List<SewageVO> getSewagerecordsearch(Map<String, String> record) {
		return sewageMapper.getSewagerecordsearch(record);
	}

	@Override
	public int getSewagerecordsearchCount(Map<String, String> record) {
		return sewageMapper.getSewagerecordsearchCount(record);
	}

	@Override
	public Double getSumHandleWater(Map<String, String> map) {
		return sewageMapper.getSumHandleWater(map);
	}

	//一次性获得所有断电断线，用于每天8点检查所有的断电断线 站点，然后插入到数据库中
	@Override
	public List<Sewage> getWithoutElectric(Map<String, String> map) {
		return sewageMapper.getWithoutElectric(map);
	}

	@Override
	public List<Sewage> getSewageHandleExcel(Map<String, String> map){
		return sewageMapper.getSewageHandleExcel(map);
	}

	@Override
	public int getSewageHandleExcelCount(Map<String, String> map) {
		return sewageMapper.getSewageHandleExcelCount(map);
	}

	@Override
	public Sewage getWithoutElectricBySewageid(Map<String, String> map) {
		return sewageMapper.getWithoutElectricBySewageid(map);
	}


}