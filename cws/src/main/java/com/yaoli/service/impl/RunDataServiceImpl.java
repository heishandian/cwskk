package com.yaoli.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaoli.beans.RunData;
import com.yaoli.beans.RunDataAbnormal;
import com.yaoli.dao.RunDataMapper;
import com.yaoli.service.IRunDataService;

@Service("RunDataServiceImpl")
public class RunDataServiceImpl implements IRunDataService {
    @Resource
    public RunDataMapper runDataMapper;

    public int deleteByPrimaryKey(Long id) {
        return runDataMapper.deleteByPrimaryKey(id);
    }

    public int insert(RunData record) {
        return runDataMapper.insert(record);
    }

    public int insertSelective(RunData record) {
        return runDataMapper.insertSelective(record);
    }

    public RunData selectByPrimaryKey(Long id) {
        return runDataMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(RunData record) {
        return runDataMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(RunData record) {
        return runDataMapper.updateByPrimaryKey(record);
    }

    public int getTotalCount() {
        return runDataMapper.getTotalCount();
    }

    public int getCountByParameterName(String parameterName) {
        return runDataMapper.getCountByParameterName(parameterName);
    }

    public List<RunData> selectByPaingAndCondition(Map<String, String> map) {
        return runDataMapper.selectByPaingAndCondition(map);
    }

	@Override
	public RunData getLatestRunData(int sewageid) {
		return runDataMapper.getLatestRunData(sewageid);
	}

	@Override
	public List<RunDataAbnormal> getLatestSewageRunDataAbnormal() {
		return runDataMapper.getLatestSewageRunDataAbnormal();
	}

	@Override
	public List<RunData> getLatestSewageRunData() {
		return runDataMapper.getLatestSewageRunData();
	}

	@Override
	public List<RunData> getLatestSewageRunDataByAreaid(int areaid) {
		return runDataMapper.getLatestSewageRunDataByAreaid(areaid);
	}

	@Override
	public List<RunData> getEquipmentRunDataRecord(Map<String, String> map) {
		return runDataMapper.getEquipmentRunDataRecord(map);
	}

	@Override
	public int getEquipmentRunDatasRecordTotal(Map<String, String> map) {
		return runDataMapper.getEquipmentRunDatasRecordTotal(map);
	}

}