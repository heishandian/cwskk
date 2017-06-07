package com.yaoli.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaoli.beans.ProblemGather;
import com.yaoli.dao.ProblemGatherMapper;
import com.yaoli.service.IProblemGatherService;
import com.yaoli.vo.ProblemGatherVO;

@Service("ProblemGatherServiceImpl")
public class ProblemGatherServiceImpl implements IProblemGatherService {
    @Resource
    public ProblemGatherMapper problemGatherMapper;

    public int deleteByPrimaryKey(Integer id) {
        return problemGatherMapper.deleteByPrimaryKey(id);
    }

    public int insert(ProblemGather record) {
        return problemGatherMapper.insert(record);
    }

    public int insertSelective(ProblemGather record) {
        return problemGatherMapper.insertSelective(record);
    }

    public ProblemGather selectByPrimaryKey(Integer id) {
        return problemGatherMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(ProblemGather record) {
        return problemGatherMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(ProblemGather record) {
        return problemGatherMapper.updateByPrimaryKey(record);
    }

    public int getTotalCount() {
        return problemGatherMapper.getTotalCount();
    }

    public int getCountByParameterName(String parameterName) {
        return problemGatherMapper.getCountByParameterName(parameterName);
    }

    public List<ProblemGather> selectByPaingAndCondition(Map<String, String> map) {
        return problemGatherMapper.selectByPaingAndCondition(map);
    }

	@Override
	public List<ProblemGatherVO> getProblemGatherVOsByCondition(
			Map<String, String> map) {
		return problemGatherMapper.getProblemGatherVOsByCondition(map);
	}

	@Override
	public int getProblemGatherVOsCountByCondition(Map<String, String> map) {
		return problemGatherMapper.getProblemGatherVOsCountByCondition(map);
	}
}