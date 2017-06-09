package com.yaoli.dao;

import com.yaoli.beans.Goods_status;

import java.util.List;

public interface Goods_statusMapper {
    List<Goods_status> getAllGoods_status();

    int deleteByPrimaryKey(Integer id);

    int insert(Goods_status record);

    int insertSelective(Goods_status record);

    Goods_status selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods_status record);

    int updateByPrimaryKey(Goods_status record);
}