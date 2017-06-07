package com.yaoli.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaoli.beans.Statistic;
import com.yaoli.dao.StatisticMapper;
import com.yaoli.service.IStatisticService;

@Service("StatisticServiceImpl")
public class StatisticServiceImpl implements IStatisticService {
    @Resource
    public StatisticMapper statisticMapper;

    public int deleteByPrimaryKey(Long id) {
        return statisticMapper.deleteByPrimaryKey(id);
    }

    public int insert(Statistic record) {
        return statisticMapper.insert(record);
    }

    public int insertSelective(Statistic record) {
        return statisticMapper.insertSelective(record);
    }

    public Statistic selectByPrimaryKey(Long id) {
        return statisticMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Statistic record) {
        return statisticMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Statistic record) {
        return statisticMapper.updateByPrimaryKey(record);
    }

    public int getTotalCount() {
        return statisticMapper.getTotalCount();
    }

    public int getCountByParameterName(String parameterName) {
        return statisticMapper.getCountByParameterName(parameterName);
    }

    public List<Statistic> selectByPaingAndCondition(Map<String, String> map) {
        return statisticMapper.selectByPaingAndCondition(map);
    }

	@Override
	public int executeStatisticYesterday(int sewageid) {
		return statisticMapper.executeStatisticYesterday(sewageid);
	}

	@Override
	public double getSewageDayTotalWaterFlow(int sewageid) {
		return statisticMapper.getSewageDayTotalWaterFlow(sewageid);
	}
}