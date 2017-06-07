package com.yaoli.dao;


import java.util.List;
import java.util.Map;

import com.yaoli.beans.Sewage;
import com.yaoli.vo.SewageVO;

public interface SewageMapper {
    int deleteByPrimaryKey(Integer sewageid);

    int insert(Sewage record);

    int insertSelective(Sewage record);

    Sewage selectByPrimaryKey(Integer sewageid);

    int updateByPrimaryKeySelective(Sewage record);

    int updateByPrimaryKey(Sewage record);
    
    public List<Sewage> getAllSewages();
    
    public List<Sewage> getSewagesByAreaId(int areaid);
    
    public List<Sewage> getSewagesBySearchNameOrId(String condition);
    
    public List<SewageVO> getSewagesByPaging(Map<String, String> map);
    
    public int getTotalSewageCount();
    
    public int getSewageCountLinktoAreaBy(Integer areaId);
    
    //获取最新的站点信息和该站点下面的水质信息
    public List<Sewage> getLatestSewageDetectionData();
    
    //根据sewageId获取最新站点水质信息
    public Sewage getLatestSewageDetectionDataBySewageId(int sewageId);
    
    //获取当值站点 流量总和
    public double getSewageTotalWaterFlow(int sewageId);
    
    public List<Sewage> getLatestSewageWithoutElectricAndWater();
    
    public List<Sewage> getLatestSewageWithoutElectricAndWaterByAreaid(int areaid);
    
    //分页获得断电断线
    List<Sewage> getWithoutElectricAndWaterByPaingAndCondition(Map<String, String> map);
    
    //分页获得断电断线
    int getWithoutElectricAndWaterPaingAndConditionTotal(Map<String, String> map);
    
    //定时任务 获取 站点昨天是否有数据统计
    int getSewageHasRecordYesterday(int sewageid);
    
    int updateSewageByPrimaryKeyCondition(Sewage sewage);
    
    /**
     * 以下三个是获取污水日，月，年 统计
     * @return
     */
    List<Sewage> sewageWaterdayReport(Map<String, String> map);
    
    List<Sewage> sewageWatermonthReport(Map<String, String> map);
    
    List<Sewage> sewageWateryearReport(Map<String, String> map);
    
    /**
     * 一下三个是获取以上的 数量
     * @return
     */
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