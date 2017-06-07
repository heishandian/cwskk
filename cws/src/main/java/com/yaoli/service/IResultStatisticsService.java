package com.yaoli.service;

import java.util.Map;

import com.yaoli.beans.ResultStatistics;

public interface IResultStatisticsService {
	public ResultStatistics getLatestResultStatisticsBySewageid(int sewageid);
	
	public ResultStatistics getResultStatisticsByDateAndSewageid(Map<String, String> conditionMap);
}
