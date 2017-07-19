package com.yaoli.dao;

import com.yaoli.beans.GroupMessage;

import java.util.List;

public interface GroupMessageMapper {
    List<GroupMessage> selectAllGroupMessage();

    List<GroupMessage> selectByAreaId(Integer areaid);

    GroupMessage selectByGroupName(String groupName);

    int deleteByPrimaryKey(Integer id);

    int insert(GroupMessage record);

    int insertSelective(GroupMessage record);

    GroupMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupMessage record);

    int updateByPrimaryKey(GroupMessage record);
}