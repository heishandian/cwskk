package com.yaoli.dao;

import com.yaoli.beans.Goods_abnormal;

public interface Goods_abnormalMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Goods_abnormal record);

    int insertSelective(Goods_abnormal record);

    Goods_abnormal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods_abnormal record);

    int updateByPrimaryKey(Goods_abnormal record);
}