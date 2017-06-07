package com.yaoli.dao;

import com.yaoli.beans.RemoteControl;
import java.util.List;
import java.util.Map;

public interface RemoteControlMapper {
    int deleteByPrimaryKey(Integer sewageid);

    int insert(RemoteControl record);

    int insertSelective(RemoteControl record);

    RemoteControl selectByPrimaryKey(Integer sewageid);

    int updateByPrimaryKeySelective(RemoteControl record);

    int updateByPrimaryKey(RemoteControl record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<RemoteControl> selectByPaingAndCondition(Map<String, String> map);
}