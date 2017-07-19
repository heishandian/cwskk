package com.yaoli.service;

import com.yaoli.beans.GroupMember;

/**
 * Created by kk on 2017/7/4.
 */
public interface IWorkGroupMemberService {

    int deleteByPrimaryKey(Integer id);

    int insert(GroupMember record);

    int insertSelective(GroupMember record);

    GroupMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GroupMember record);

    int updateByPrimaryKey(GroupMember record);
}
