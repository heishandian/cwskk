package com.yaoli.service.impl;

import com.yaoli.beans.GroupMessage;
import com.yaoli.dao.GroupMessageMapper;
import com.yaoli.service.IWorkGroupMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kk on 2017/7/4.
 */
@Service(value="workGroupMessageService")
public class WorkGroupMessageServiceImpl implements IWorkGroupMessageService{
    @Resource
    private GroupMessageMapper groupMessageMapper;

    @Override
    public List<GroupMessage> selectAllGroupMessage() {
        return groupMessageMapper.selectAllGroupMessage();
    }

    @Override
    public List<GroupMessage> selectByAreaId(Integer areaid) {
        return groupMessageMapper.selectByAreaId(areaid);
    }

    @Override
    public GroupMessage selectByGroupName(String groupName) {
        return groupMessageMapper.selectByGroupName(groupName);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return groupMessageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GroupMessage record) {
        return groupMessageMapper.insert(record);
    }

    @Override
    public int insertSelective(GroupMessage record) {
        return groupMessageMapper.insertSelective(record);
    }

    @Override
    public GroupMessage selectByPrimaryKey(Integer id) {
        return groupMessageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GroupMessage record) {
        return groupMessageMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GroupMessage record) {
        return groupMessageMapper.updateByPrimaryKey(record);
    }
}
