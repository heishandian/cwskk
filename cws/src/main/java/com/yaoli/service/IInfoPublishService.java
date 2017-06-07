package com.yaoli.service;

import com.yaoli.beans.InfoPublish;
import com.yaoli.vo.InfoPublishVO;

import java.util.List;
import java.util.Map;

public interface IInfoPublishService {
    int deleteByPrimaryKey(Integer id);

    int insert(InfoPublish record);

    int insertSelective(InfoPublish record);

    InfoPublish selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InfoPublish record);

    int updateByPrimaryKey(InfoPublish record);

    int getTotalCount();

    int getCountByParameterName(String parameterName);

    List<InfoPublish> selectByPaingAndCondition(Map<String, String> map);

    List<InfoPublishVO> getInfoPublisherVO(Map<String, String> map);

    int getInfoPublisherVOCount(Map<String, String> map);

    List<InfoPublishVO> getSearchNoticePublish(Map<String, String> map);
}