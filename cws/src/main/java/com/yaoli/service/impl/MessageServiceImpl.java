package com.yaoli.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yaoli.beans.Message;
import com.yaoli.dao.MessageMapper;
import com.yaoli.service.IMessageService;

@Service("MessageServiceImpl")
public class MessageServiceImpl implements IMessageService {
    @Resource
    public MessageMapper messageMapper;

    public int deleteByPrimaryKey(Long id) {
        return messageMapper.deleteByPrimaryKey(id);
    }

    public int insert(Message record) {
        return messageMapper.insert(record);
    }

    public int insertSelective(Message record) {
        return messageMapper.insertSelective(record);
    }

    public Message selectByPrimaryKey(Long id) {
        return messageMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Message record) {
        return messageMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Message record) {
        return messageMapper.updateByPrimaryKey(record);
    }

    public int getTotalCount() {
        return messageMapper.getTotalCount();
    }

    public int getCountByParameterName(String parameterName) {
        return messageMapper.getCountByParameterName(parameterName);
    }

    public List<Message> selectByPaingAndCondition(Map<String, String> map) {
        return messageMapper.selectByPaingAndCondition(map);
    }

	@Override
	public List<Message> getRunDataAbnormalForMessage() {
		return messageMapper.getRunDataAbnormalForMessage();
	}

	@Override
	public List<Message> getDetectionAbnormalForMessage() {
		return messageMapper.getDetectionAbnormalForMessage();
	}

	@Override
	public int messageNeedToSend(Message message) {
		return messageMapper.messageNeedToSend(message);
	}

	@Override
	public List<Message> getWithoutElectricForMessage() {
		return messageMapper.getWithoutElectricForMessage();
	}

	@Override
	public int selectByPaingAndConditionCount(Map<String, String> map) {
		return messageMapper.selectByPaingAndConditionCount(map);
	}
}