package com.yaoli.dao;

import com.yaoli.beans.GroupWorkLoad;

import java.util.List;

public interface GroupWorkLoadMapper {
    List<GroupWorkLoad> selectByGroupId(Integer groupId);

    int deleteByPrimaryKey(Integer id);

    int insert(GroupWorkLoad record);

    int insertSelective(GroupWorkLoad record);

    GroupWorkLoad selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupWorkLoad record);

    int updateByPrimaryKey(GroupWorkLoad record);
}