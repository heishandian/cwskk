package com.yaoli.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaoli.beans.RunDataAbnormal;
import com.yaoli.beans.RunDataAbnormal_back;
import com.yaoli.dao.RunDataAbnormalMapper;
import com.yaoli.service.IRunDataAbnormalService;
import com.yaoli.vo.RunDataAbnormalVO;

@Service("RunDataAbnormalServiceImpl")
public class RunDataAbnormalServiceImpl implements IRunDataAbnormalService {
    @Resource
    public RunDataAbnormalMapper runDataAbnormalMapper;

    public int deleteByPrimaryKey(Long id) {
        return runDataAbnormalMapper.deleteByPrimaryKey(id);
    }

    public int insert(RunDataAbnormal_back record) {
        return runDataAbnormalMapper.insert(record);
    }

    public int insertSelective(RunDataAbnormal_back record) {
        return runDataAbnormalMapper.insertSelective(record);
    }

    public RunDataAbnormal_back selectByPrimaryKey(Long id) {
        return runDataAbnormalMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(RunDataAbnormal_back record) {
        return runDataAbnormalMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(RunDataAbnormal_back record) {
        return runDataAbnormalMapper.updateByPrimaryKey(record);
    }

    public int getTotalCount() {
        return runDataAbnormalMapper.getTotalCount();
    }

    public int getCountByParameterName(String parameterName) {
        return runDataAbnormalMapper.getCountByParameterName(parameterName);
    }

    public List<RunDataAbnormalVO> selectByPaingAndCondition(Map<String, String> map){
    	return runDataAbnormalMapper.selectByPaingAndCondition(map);
    }

	@Override
	public int getHandlePagingAndConditionTotalCount(Map<String, String> map) {
		return runDataAbnormalMapper.getHandlePagingAndConditionTotalCount(map);
	}

	@Override
	public int updateHandleRunDataAbnormalByRunId(Long runid) {
		return runDataAbnormalMapper.updateHandleRunDataAbnormalByRunId(runid);
	}

	@Override
	public List<RunDataAbnormalVO> getEquipmentAbnormalByPagingAndConditionMap(
			Map<String, String> map) {
		return runDataAbnormalMapper.getEquipmentAbnormalByPagingAndConditionMap(map);
	}

	@Override
	public int getEquipmentAbnormalByPagingAndConditionMapTotalCount(
			Map<String, String> map) {
		return runDataAbnormalMapper.getEquipmentAbnormalByPagingAndConditionMapTotalCount(map);
	}

	@Override
	public List<RunDataAbnormal> getLatestSewageRunDataAbnormal() {
		return runDataAbnormalMapper.getLatestSewageRunDataAbnormal();
	}

	@Override
	public List<RunDataAbnormal> getRunDataAbnormalsForMessage() {
		return runDataAbnormalMapper.getRunDataAbnormalsForMessage();
	}


}