package com.yaoli.dao;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.DetectionData;
import com.yaoli.vo.DetectionDataVO;

public interface DetectionDataMapper {
    int deleteByPrimaryKey(Long detectionid);

    int insert(DetectionData record);

    int insertSelective(DetectionData record);

    DetectionData selectByPrimaryKey(Long detectionid);

    int updateByPrimaryKeySelective(DetectionData record);

    int updateByPrimaryKey(DetectionData record);
    
    public DetectionData getLatestDetectionData(int sewageid);
    
    public List<DetectionDataVO> gettop5info(int sewageid);
    
    public List<DetectionData> getLatestSewageDetectionData();
    
    public List<DetectionData> getLatestSewageDetectionDataByAreaId(int areaid);
    
	//获取设备运行记录
	List<DetectionData> getDetectionDataRecord(Map<String, String> map);
	
	//获取设备运行记录总数
	int getDetectionDataRecordTotal(Map<String, String> map);
	
	//日处理水量
	public List<DetectionDataVO> getStatisticDayWater(Map<String, String> map);
	
	//获取以前的日处理水量 
	public List<DetectionDataVO> getStatisticDayWaterFromStatisticTable(Map<String, String> map);
	
	//获取每月的日处理水量
	public List<DetectionDataVO> getStatisticMonthWater(Map<String, String> map);
	
	//获取每年处理水量
	public List<DetectionDataVO> getStatisticYearWater(Map<String, String> map);

	//按照日期和站点id获取detection9
	DetectionData getDetectionDataBySewageidAndDatetime(Map<String, String> map);

	//按照日期和站点id获取非当天的最后一条数据detection9
	DetectionData getMaxPreDetectionDataBySewageidAndDatetime(Map<String, String> map);
}