package com.yaoli.service;

import com.yaoli.beans.RemoteControl;
import java.util.List;
import java.util.Map;

public interface IRemoteControlService {
    int deleteByPrimaryKey(Integer id);

    int insert(RemoteControl record);

    int insertSelective(RemoteControl record);

    RemoteControl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RemoteControl record);

    int updateByPrimaryKey(RemoteControl record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<RemoteControl> selectByPaingAndCondition(Map<String, String> map);
}