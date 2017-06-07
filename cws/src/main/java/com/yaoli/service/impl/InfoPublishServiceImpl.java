package com.yaoli.service.impl;

import com.yaoli.beans.InfoPublish;
import com.yaoli.dao.InfoPublishMapper;
import com.yaoli.service.IInfoPublishService;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.yaoli.vo.InfoPublishVO;
import org.springframework.stereotype.Service;

@Service("InfoPublishServiceImpl")
public class InfoPublishServiceImpl implements IInfoPublishService {
    @Resource
    public InfoPublishMapper infoPublishMapper;

    public int deleteByPrimaryKey(Integer id) {
        return infoPublishMapper.deleteByPrimaryKey(id);
    }

    public int insert(InfoPublish record) {
        return infoPublishMapper.insert(record);
    }

    public int insertSelective(InfoPublish record) {
        return infoPublishMapper.insertSelective(record);
    }

    public InfoPublish selectByPrimaryKey(Integer id) {
        return infoPublishMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(InfoPublish record) {
        return infoPublishMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(InfoPublish record) {
        return infoPublishMapper.updateByPrimaryKey(record);
    }

    public int getTotalCount() {
        return infoPublishMapper.getTotalCount();
    }

    public int getCountByParameterName(String parameterName) {
        return infoPublishMapper.getCountByParameterName(parameterName);
    }

    public List<InfoPublish> selectByPaingAndCondition(Map<String, String> map) {
        return infoPublishMapper.selectByPaingAndCondition(map);
    }

    public List<InfoPublishVO> getInfoPublisherVO(Map<String, String> map) {
        return infoPublishMapper.getInfoPublisherVO(map);
    }

    public int getInfoPublisherVOCount(Map<String, String> map) {
        return infoPublishMapper.getInfoPublisherVOCount(map);
    }

    public List<InfoPublishVO> getSearchNoticePublish(Map<String, String> map) {
        return infoPublishMapper.getSearchNoticePublish(map);
    }
}