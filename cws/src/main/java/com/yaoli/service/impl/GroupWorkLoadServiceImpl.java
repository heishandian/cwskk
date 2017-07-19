package com.yaoli.service.impl;

import com.yaoli.beans.GroupWorkLoad;
import com.yaoli.dao.GroupWorkLoadMapper;
import com.yaoli.service.IGroupWorkLoadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by kk on 2017/7/4.
 */
@Service(value = "groupWorkLoadService")
public class GroupWorkLoadServiceImpl implements IGroupWorkLoadService{
   @Resource
   private GroupWorkLoadMapper groupWorkLoadMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return groupWorkLoadMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GroupWorkLoad record) {
        return groupWorkLoadMapper.insert(record);
    }

    @Override
    public int insertSelective(GroupWorkLoad record) {
        return groupWorkLoadMapper.insertSelective(record);
    }

    @Override
    public GroupWorkLoad selectByPrimaryKey(Integer id) {
        return groupWorkLoadMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GroupWorkLoad record) {
        return groupWorkLoadMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GroupWorkLoad record) {
        return groupWorkLoadMapper.updateByPrimaryKeySelective(record);
    }
}
