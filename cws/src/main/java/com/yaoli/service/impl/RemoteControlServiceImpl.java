package com.yaoli.service.impl;

import com.yaoli.beans.RemoteControl;
import com.yaoli.dao.RemoteControlMapper;
import com.yaoli.service.IRemoteControlService;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("RemoteControlServiceImpl")
public class RemoteControlServiceImpl implements IRemoteControlService {
    @Resource
    public RemoteControlMapper remoteControlMapper;

    public int deleteByPrimaryKey(Integer id) {
        return remoteControlMapper.deleteByPrimaryKey(id);
    }

    public int insert(RemoteControl record) {
        return remoteControlMapper.insert(record);
    }

    public int insertSelective(RemoteControl record) {
        return remoteControlMapper.insertSelective(record);
    }

    public RemoteControl selectByPrimaryKey(Integer id) {
        return remoteControlMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(RemoteControl record) {
        return remoteControlMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(RemoteControl record) {
        return remoteControlMapper.updateByPrimaryKey(record);
    }

    public int getTotalCount() {
        return remoteControlMapper.getTotalCount();
    }

    public int getCountByParameterName(String parameterName) {
        return remoteControlMapper.getCountByParameterName(parameterName);
    }

    public List<RemoteControl> selectByPaingAndCondition(Map<String, String> map) {
        return remoteControlMapper.selectByPaingAndCondition(map);
    }
}