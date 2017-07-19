package com.yaoli.service;

import com.yaoli.beans.GroupMessage;

import java.util.List;

/**
 * Created by kk on 2017/7/4.
 */
public interface IWorkGroupMessageService {
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
