package com.yaoli.service;

import com.yaoli.beans.GoodsAbnormalSearchResult;
import com.yaoli.beans.Goods_abnormal;
import com.yaoli.vo.huangkai.AbnormalStasticSearchConditionVO;
import com.yaoli.vo.huangkai.AbnormalStasticSearchResultVO;
import com.yaoli.vo.huangkai.GoodsAbnormalSearchConditionVO;

import java.util.List;

/**
 * Created by kk on 2017/6/13.
 */
public interface IGoodsAbnormalService {
    List<AbnormalStasticSearchResultVO> selectAllAbnormalStatisticByPaging(AbnormalStasticSearchConditionVO abnormalStasticSearchCondition);


    List<GoodsAbnormalSearchResult> selectAllGoodsAbnormalRecordByPaging (GoodsAbnormalSearchConditionVO goodsAbnormalSearchCondition);

    int selectAbnormalRecordCount(GoodsAbnormalSearchConditionVO goodsAbnormalSearchCondition);

    Goods_abnormal selectByGoodsId(Integer id);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods_abnormal record);

    int insertSelective(Goods_abnormal record);

    Goods_abnormal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods_abnormal record);

    int updateByPrimaryKey(Goods_abnormal record);
}
