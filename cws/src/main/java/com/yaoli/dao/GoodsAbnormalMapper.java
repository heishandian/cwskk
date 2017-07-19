package com.yaoli.dao;

import com.yaoli.beans.GoodsAbnormalSearchResult;
import com.yaoli.beans.Goods_abnormal;
import com.yaoli.vo.huangkai.AbnormalStasticSearchConditionVO;
import com.yaoli.vo.huangkai.AbnormalStasticSearchResultVO;
import com.yaoli.vo.huangkai.GoodsAbnormalSearchConditionVO;

import java.util.List;

public interface GoodsAbnormalMapper {
    Goods_abnormal selectByGoodsId(Integer id);

    List<AbnormalStasticSearchResultVO> selectAllAbnormalStatisticByPaging(AbnormalStasticSearchConditionVO abnormalStasticSearchCondition);

    List<GoodsAbnormalSearchResult> selectAllGoodsAbnormalRecordByPaging (GoodsAbnormalSearchConditionVO goodsAbnormalSearchCondition);

    int selectAbnormalRecordCount(GoodsAbnormalSearchConditionVO goodsAbnormalSearchCondition);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods_abnormal record);

    int insertSelective(Goods_abnormal record);

    Goods_abnormal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods_abnormal record);

    int updateByPrimaryKey(Goods_abnormal record);
}