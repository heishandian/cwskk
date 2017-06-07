package com.yaoli.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaoli.beans.DetectionData;
import com.yaoli.dao.DetectionDataMapper;
import com.yaoli.service.IDetectionDataService;
import com.yaoli.vo.DetectionDataVO;

@Service("detectionDataService")
public class DetectionDataServiceImpl implements IDetectionDataService{

	@Resource
	public DetectionDataMapper detectionDataMapper;
	
	@Override
	public DetectionData getLatestDetectionData(int sewageid) {
		DetectionData data = detectionDataMapper.getLatestDetectionData(sewageid);
		return data;
	}

	@Override
	public List<DetectionDataVO> gettop5info(int sewageid) {
		List<DetectionDataVO> list = detectionDataMapper.gettop5info(sewageid);
		return list;
	}

	@Override
	public List<DetectionData> getLatestSewageDetectionData() {
		return detectionDataMapper.getLatestSewageDetectionData();
	}

	@Override
	public List<DetectionData> getLatestSewageDetectionDataByAreaId(int areaid) {
		return detectionDataMapper.getLatestSewageDetectionDataByAreaId(areaid);
	}

	@Override
	public List<DetectionData> getDetectionDataRecord(Map<String, String> map) {
		return detectionDataMapper.getDetectionDataRecord(map);
	}

	@Override
	public int getDetectionDataRecordTotal(Map<String, String> map) {
		return detectionDataMapper.getDetectionDataRecordTotal(map);
	}

	@Override
	public List<DetectionDataVO> getStatisticDayWater(Map<String, String> map) {
		return detectionDataMapper.getStatisticDayWater(map);
	}

	@Override
	public List<DetectionDataVO> getStatisticDayWaterFromStatisticTable(
			Map<String, String> map) {
		return detectionDataMapper.getStatisticDayWaterFromStatisticTable(map);
	}

	@Override
	public List<DetectionDataVO> getStatisticMonthWater(Map<String, String> map) {
		return detectionDataMapper.getStatisticMonthWater(map);
	}

	@Override
	public List<DetectionDataVO> getStatisticYearWater(Map<String, String> map) {
		return detectionDataMapper.getStatisticYearWater(map);
	}

	@Override
	public DetectionData getDetectionDataBySewageidAndDatetime(Map<String, String> map) {
		return detectionDataMapper.getDetectionDataBySewageidAndDatetime(map);
	}

	@Override
	public DetectionData getMaxPreDetectionDataBySewageidAndDatetime(Map<String, String> map) {
		return detectionDataMapper.getMaxPreDetectionDataBySewageidAndDatetime(map);
	}

	@Override
	public int insertSelective(DetectionData record) {
		return detectionDataMapper.insert(record);
	}

}
