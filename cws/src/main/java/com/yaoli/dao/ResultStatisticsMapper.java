package com.yaoli.dao;

import java.util.Map;

import com.yaoli.beans.ResultStatistics;

public interface ResultStatisticsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResultStatistics record);

    int insertSelective(ResultStatistics record);

    ResultStatistics selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResultStatistics record);

    int updateByPrimaryKey(ResultStatistics record);
    
    public ResultStatistics getLatestResultStatisticsBySewageid(int sewageid);
    
    public ResultStatistics getResultStatisticsByDateAndSewageid(Map<String, String> conditionMap);
}