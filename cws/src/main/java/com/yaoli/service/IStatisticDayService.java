package com.yaoli.service;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.StatisticDay;
import com.yaoli.vo.StatisticDayVO;

public interface IStatisticDayService {
    int deleteByPrimaryKey(Integer id);

    int insert(StatisticDay record);

    int insertSelective(StatisticDay record);

    StatisticDay selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StatisticDay record);

    int updateByPrimaryKey(StatisticDay record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<StatisticDay> selectByPaingAndCondition(Map<String, String> map);
    
    //分页获取 日统计量
    List<StatisticDayVO> getStatisticDayVOByCondition(Map<String, String> map);
    int getStatisticDayTotalByCondition(Map<String, String> map);
    
    //获取全部的日统计量
    List<StatisticDayVO> getStatisticDayVO(Map<String, String> map);
    int getStatisticDayTotal(Map<String, String> map);
    
    
    //分页获取月统计量
    List<StatisticDayVO> getStatisticMonthVOByCondition(Map<String, String> map);
    int getStatisticMonthTotalByCondition(Map<String, String> map);
    //获取全部的月统计两
    List<StatisticDayVO> getStatisticMonthVO(Map<String, String> map);
    
    
    //获取年统计量
    List<StatisticDayVO> getStatisticYearVOByCondition(Map<String, String> map);
    int getStatisticYearTotalByCondition(Map<String, String> map);
    //获取全部的月统计两
    List<StatisticDayVO> getStatisticYearVO(Map<String, String> map);
    
    // 站点运行记录 的 站点月报 统计数据 
    List<StatisticDayVO> getsewagerunrecordmonthreport(Map<String,String> map);
    
    // 站点运行记录 的 站点月报 统计数据 
    List<StatisticDayVO> getsewagerunrecordyearreport(Map<String,String> map);
    
    /**
     * 总水量
     * @param sewageid
     * @return
     */
    Double getSewageDayTotalWater(int sewageid);
    
    
    /**
     * 获取每个站点 的 整月31天 统计
     */
    List<StatisticDayVO> getSewageWaterStatisticMonth(Map<String, String> map);
    
    /**
     * 获取每个站点 的 一年的统计水量
     */
    List<StatisticDayVO> getSewageWaterStatisticYear(Map<String, String> map);
    
    /**
     * 获取能耗管理用量
     * @param map
     * @return
     */
    List<StatisticDayVO> getEnergycostsByCondition(Map<String, String> map);
    
    int getEnergycostsCountByCondition(Map<String, String> map);
    
    List<StatisticDayVO> getEnergycostsByDay(Map<String, String> map);
    
    List<StatisticDayVO> gethistoryenergymonthsearch(Map<String, String> map);
    
    //
    List<StatisticDayVO> gethistoryenergyyearsearch(Map<String, String> map);

    //按照日期和站点id获取水量
    StatisticDay getStatisticDayVOBySewageidAndDatetime(Map<String, String> map);

    //校正 水量
    int updateWaterBySewageidAndDatetime(Map<String, String> map);
}