package com.yaoli.dao;

import com.yaoli.beans.Goods;

import java.util.List;

public interface GoodsMapper {
    List<Goods> getAllGoods();

    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}