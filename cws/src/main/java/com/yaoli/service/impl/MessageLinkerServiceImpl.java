package com.yaoli.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaoli.beans.MessageLinker;
import com.yaoli.dao.MessageLinkerMapper;
import com.yaoli.service.IMessageLinkerService;

@Service("MessageLinkerServiceImpl")
public class MessageLinkerServiceImpl implements IMessageLinkerService {
    @Resource
    public MessageLinkerMapper messageLinkerMapper;

    public int insert(MessageLinker record) {
        return messageLinkerMapper.insert(record);
    }

    public int insertSelective(MessageLinker record) {
        return messageLinkerMapper.insertSelective(record);
    }

    public int updateByPrimaryKeySelective(MessageLinker record) {
        return messageLinkerMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(MessageLinker record) {
        return messageLinkerMapper.updateByPrimaryKey(record);
    }

    public int getTotalCount() {
        return messageLinkerMapper.getTotalCount();
    }

    public int getCountByParameterName(String parameterName) {
        return messageLinkerMapper.getCountByParameterName(parameterName);
    }

    public List<MessageLinker> selectByPaingAndCondition(Map<String, String> map) {
        return messageLinkerMapper.selectByPaingAndCondition(map);
    }

	@Override
	public int deleteMessagerLinkerByAreaid(String areaid) {
		return messageLinkerMapper.deleteMessagerLinkerByAreaid(areaid);
	}

	@Override
	public List<MessageLinker> getMessageLinkers(String areaid) {
		return messageLinkerMapper.getMessageLinkers(areaid);
	}



}