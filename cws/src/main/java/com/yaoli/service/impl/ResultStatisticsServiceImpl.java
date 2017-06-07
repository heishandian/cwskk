package com.yaoli.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaoli.beans.ResultStatistics;
import com.yaoli.dao.ResultStatisticsMapper;
import com.yaoli.service.IResultStatisticsService;

@Service("resultStatisticsService")
public class ResultStatisticsServiceImpl implements IResultStatisticsService{
	@Resource
	public ResultStatisticsMapper resultStatisticsMapper;
	
	@Override
	public ResultStatistics getLatestResultStatisticsBySewageid(int sewageid) {
		ResultStatistics resultStatistics = resultStatisticsMapper.getLatestResultStatisticsBySewageid(sewageid);
		return resultStatistics;
	}

	@Override
	public ResultStatistics getResultStatisticsByDateAndSewageid(
			Map<String, String> conditionMap) {
		//根据month添加month_a  day添加day_m
		ResultStatistics resultStatistics = resultStatisticsMapper.getResultStatisticsByDateAndSewageid(conditionMap);
		return resultStatistics;
	}
	
}
