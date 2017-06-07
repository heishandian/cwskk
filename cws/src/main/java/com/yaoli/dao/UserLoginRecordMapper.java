package com.yaoli.dao;

import java.util.List;
import java.util.Map;

import com.yaoli.beans.UserLoginRecord;
import com.yaoli.vo.UserLoginRecordVO;

public interface UserLoginRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserLoginRecord record);

    int insertSelective(UserLoginRecord record);

    UserLoginRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLoginRecord record);

    int updateByPrimaryKey(UserLoginRecord record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<UserLoginRecord> selectByPaingAndCondition(Map<String, String> map);
    
    List<UserLoginRecordVO> getUserLoginRecord(Map<String, String> map);
    
    int getUserLoginRecordTotal(Map<String, String> map);

    int getUserLoginTimes(Map<String, String> map);
}