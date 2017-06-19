package com.yaoli.service;

import com.yaoli.beans.Goods_abnormal;

/**
 * Created by kk on 2017/6/13.
 */
public interface IGoodsAbnormalService {
    Goods_abnormal selectByGoodsId(Integer id);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods_abnormal record);

    int insertSelective(Goods_abnormal record);

    Goods_abnormal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods_abnormal record);

    int updateByPrimaryKey(Goods_abnormal record);
}
