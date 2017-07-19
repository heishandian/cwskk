package com.yaoli.service.impl;

import com.yaoli.beans.GroupMember;
import com.yaoli.dao.GroupMemberMapper;
import com.yaoli.service.IWorkGroupMemberService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by kk on 2017/7/4.
 */
@Service("workGroupMemberService")
public class WorkGroupMemberServiceImpl implements IWorkGroupMemberService {
    @Resource
    private GroupMemberMapper groupMemberMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return groupMemberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GroupMember record) {
        return groupMemberMapper.insert(record);
    }

    @Override
    public int insertSelective(GroupMember record) {
        return groupMemberMapper.insertSelective(record);
    }

    @Override
    public GroupMember selectByPrimaryKey(Integer id) {
        return groupMemberMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GroupMember record) {
        return groupMemberMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GroupMember record) {
        return groupMemberMapper.updateByPrimaryKey(record);
    }
}
