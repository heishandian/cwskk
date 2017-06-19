package com.yaoli.service.impl;

import com.yaoli.beans.Goods_abnormal;
import com.yaoli.dao.GoodsAbnormalMapper;
import com.yaoli.service.IGoodsAbnormalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by kk on 2017/6/13.
 */
@Service(value="goodsAbnormlService")
public class GoodsAbnormalServiceImpl implements IGoodsAbnormalService {
    @Resource
    private GoodsAbnormalMapper goodsAbnormalMapper;

    @Override
    public Goods_abnormal selectByGoodsId(Integer id) {
        return goodsAbnormalMapper.selectByGoodsId(id);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return goodsAbnormalMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Goods_abnormal record) {
        return goodsAbnormalMapper.insert(record);
    }

    @Override
    public int insertSelective(Goods_abnormal record) {
        return goodsAbnormalMapper.insertSelective(record);
    }

    @Override
    public Goods_abnormal selectByPrimaryKey(Integer id) {
        return goodsAbnormalMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Goods_abnormal record) {
        return goodsAbnormalMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Goods_abnormal record) {
        return goodsAbnormalMapper.updateByPrimaryKey(record);
    }
}
