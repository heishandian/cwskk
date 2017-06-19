package com.yaoli.dao;

import com.yaoli.beans.Goods_abnormal_type;

import java.util.List;

public interface GoodsAbnormalTypeMapper {
    List<Goods_abnormal_type> getAllGoods_abnormal_type();

    Goods_abnormal_type selectByName(String name);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods_abnormal_type record);

    int insertSelective(Goods_abnormal_type record);

    Goods_abnormal_type selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods_abnormal_type record);

    int updateByPrimaryKey(Goods_abnormal_type record);
}