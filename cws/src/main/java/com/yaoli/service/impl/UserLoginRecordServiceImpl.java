package com.yaoli.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaoli.beans.UserLoginRecord;
import com.yaoli.dao.UserLoginRecordMapper;
import com.yaoli.service.IUserLoginRecordService;
import com.yaoli.vo.UserLoginRecordVO;

@Service("UserLoginRecordServiceImpl")
public class UserLoginRecordServiceImpl implements IUserLoginRecordService {
    @Resource
    public UserLoginRecordMapper userLoginRecordMapper;

    public int deleteByPrimaryKey(Integer id) {
        return userLoginRecordMapper.deleteByPrimaryKey(id);
    }

    public int insert(UserLoginRecord record) {
        return userLoginRecordMapper.insert(record);
    }

    public int insertSelective(UserLoginRecord record) {
        return userLoginRecordMapper.insertSelective(record);
    }

    public UserLoginRecord selectByPrimaryKey(Integer id) {
        return userLoginRecordMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(UserLoginRecord record) {
        return userLoginRecordMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(UserLoginRecord record) {
        return userLoginRecordMapper.updateByPrimaryKey(record);
    }

    public int getTotalCount() {
        return userLoginRecordMapper.getTotalCount();
    }

    public int getCountByParameterName(String parameterName) {
        return userLoginRecordMapper.getCountByParameterName(parameterName);
    }

    public List<UserLoginRecord> selectByPaingAndCondition(Map<String, String> map) {
        return userLoginRecordMapper.selectByPaingAndCondition(map);
    }

	@Override
	public List<UserLoginRecordVO> getUserLoginRecord(Map<String, String> map) {
		return userLoginRecordMapper.getUserLoginRecord(map);
	}

	@Override
	public int getUserLoginRecordTotal(Map<String, String> map) {
		return userLoginRecordMapper.getUserLoginRecordTotal(map);
	}

    @Override
    public int getUserLoginTimes(Map<String, String> map) {
        return userLoginRecordMapper.getUserLoginTimes(map);
    }
}