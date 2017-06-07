package com.yaoli.service;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.Sewage;
import com.yaoli.vo.SewageMonitorVO;
import com.yaoli.vo.SewageVO;

public interface ISewageService {
	public List<Sewage> getAllSewages();
	
    public List<Sewage> getSewagesByAreaId(int areaid);

	public List<Sewage> getSewagesBySearchNameOrId(String condition);
	
	public Sewage selectByPrimaryKey(Integer sewageid);
	
	public SewageMonitorVO getSewageMonitorVOBySewageId(Integer sewageid);
	
	public List<SewageVO> getSewagesByPaging(Map<String, String> map);
	
	public int getTotalSewageCount();
	
	public int insert(Sewage record);
	
	public int insertSelective(Sewage record);
	
	int deleteByPrimaryKey(Integer sewageid);
	
	public int getSewageCountLinktoAreaBy(Integer areaId);
	
    //获取最新的站点信息和该站点下面的水质信息
    public List<Sewage> getLatestSewageDetectionData();
    
    public Sewage getLatestSewageDetectionDataBySewageId(int sewageId);
    
    //获取水量总和 detection8字段
    /**
     * 用 statistic表来获取每日的统计流量 更为方便，此方法有缺陷 废弃使用
     * 使用StatisticService.getSewageDayTotalWaterFlow 方法
     * @param sewageId
     * @return
     */
    @Deprecated
    public double getSewageTotalWaterFlow(int sewageId);
    
    //获取断电断水
    public List<Sewage> getLatestSewageWithoutElectricAndWater();
    
    List<Sewage> getLatestSewageWithoutElectricAndWaterByAreaid(int areaid);
    
    
    //分页获得断电断线
    List<Sewage> getWithoutElectricAndWaterByPaingAndCondition(Map<String, String> map);
    
    //分页获得断电断线
    int getWithoutElectricAndWaterPaingAndConditionTotal(Map<String, String> map);
    
    //定时任务 获取 站点昨天是否有数据统计
    int getSewageHasRecordYesterday(int sewageid);
    
    int updateByPrimaryKeySelective(Sewage record);
    
    int updateByPrimaryKey(Sewage record);
    	
    int updateSewageByPrimaryKeyCondition(Sewage recored);
    
    List<Sewage> sewageWaterdayReport(Map<String, String> map);
    
    List<Sewage> sewageWatermonthReport(Map<String, String> map);
    
    List<Sewage> sewageWateryearReport(Map<String, String> map);
    
    int sewageWaterdayCount(Map<String, String> map);
    
    int sewageWatermonthCount(Map<String, String> map);
    
    int sewageWateryearCount(Map<String, String> map);
    
    int checkSewageIdExist(int sewageid);
    
    Sewage searchSewageidByOperatornum(String operationnum);
    
    
    List<SewageVO> getSewagerecordsearch(Map<String, String> record);
    
    int getSewagerecordsearchCount(Map<String, String> record);

    Double getSumHandleWater(Map<String,String> map);

    //一次性获得所有断电断线
    List<Sewage> getWithoutElectric(Map<String, String> map);


    List<Sewage> getSewageHandleExcel(Map<String, String> map);

    int getSewageHandleExcelCount(Map<String, String> map);

    Sewage getWithoutElectricBySewageid(Map<String,String> map);


}
