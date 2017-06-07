package com.yaoli.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaoli.beans.DeviceDoc;
import com.yaoli.dao.DeviceDocMapper;
import com.yaoli.service.IDeviceDocService;

@Service("DeviceDocServiceImpl")
public class DeviceDocServiceImpl implements IDeviceDocService {
    @Resource
    public DeviceDocMapper deviceDocMapper;

    public int deleteByPrimaryKey(Long id) {
        return deviceDocMapper.deleteByPrimaryKey(id);
    }

    public int insert(DeviceDoc record) {
        return deviceDocMapper.insert(record);
    }

    public int insertSelective(DeviceDoc record) {
        return deviceDocMapper.insertSelective(record);
    }

    public DeviceDoc selectByPrimaryKey(Long id) {
        return deviceDocMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(DeviceDoc record) {
        return deviceDocMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(DeviceDoc record) {
        return deviceDocMapper.updateByPrimaryKey(record);
    }

    public int getTotalCount() {
        return deviceDocMapper.getTotalCount();
    }

    public int getCountByParameterName(String parameterName) {
        return deviceDocMapper.getCountByParameterName(parameterName);
    }

    public List<DeviceDoc> selectByPaingAndCondition(Map<String, String> map) {
        return deviceDocMapper.selectByPaingAndCondition(map);
    }

	@Override
	public int getPaingAndConditionTotalCountBySewageid(Map<String, String> map) {
		return deviceDocMapper.getPaingAndConditionTotalCountBySewageid(map);
	}

	@Override
	public List<DeviceDoc> getAllDeviceDocs() {
		return deviceDocMapper.getAllDeviceDocs();
	}
}