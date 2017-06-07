package com.yaoli.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaoli.beans.SysParam;
import com.yaoli.dao.SysParamMapper;
import com.yaoli.service.ISysParamService;

@Service("SysParamServiceImpl")
public class SysParamServiceImpl implements ISysParamService {
    @Resource
    public SysParamMapper sysParamMapper;

    public int deleteByPrimaryKey(Integer id) {
        return sysParamMapper.deleteByPrimaryKey(id);
    }

    public int insert(SysParam record) {
        return sysParamMapper.insert(record);
    }

    public int insertSelective(SysParam record) {
        return sysParamMapper.insertSelective(record);
    }

    public SysParam selectByPrimaryKey(Integer id) {
        return sysParamMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(SysParam record) {
        return sysParamMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(SysParam record) {
        return sysParamMapper.updateByPrimaryKey(record);
    }

    public int getTotalCount() {
        return sysParamMapper.getTotalCount();
    }

    public int getCountByParameterName(String parameterName) {
        return sysParamMapper.getCountByParameterName(parameterName);
    }

    public List<SysParam> selectByPaingAndCondition(Map<String, String> map) {
        return sysParamMapper.selectByPaingAndCondition(map);
    }

	@Override
	public List<SysParam> getAbnormalType(String keyname) {
		return sysParamMapper.getAbnormalType(keyname);
	}

	public int updateAbnormalTypeToFalse() {
		return sysParamMapper.updateAbnormalTypeToFalse();
	}

	public int updateAbnormalTypeToTrueById(String id) {
		return sysParamMapper.updateAbnormalTypeToTrueById(id);
	}

	@Override
	public int updateIntervaltime(String intervaltime) {
		return sysParamMapper.updateIntervaltime(intervaltime);
	}

	@Override
	public List<SysParam> getAbnormalTypeForMessage() {
		return sysParamMapper.getAbnormalTypeForMessage();
	}

	@Override
	public int existSendMessageDay(Map<String, String> map) {
		return sysParamMapper.existSendMessageDay(map);
	}

	@Override
	public int getComPort() {
		return sysParamMapper.getComPort();
	}

	@Override
	public int updateMessagesendbegintime(String messagesendbegintime) {
		return sysParamMapper.updateMessagesendbegintime(messagesendbegintime);
	}

	@Override
	public int updateMessagesendendtime(String messagesendendtime) {
		return sysParamMapper.updateMessagesendendtime(messagesendendtime);
	}

}