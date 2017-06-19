package com.yaoli.service.impl;

import com.yaoli.beans.Goods;
import com.yaoli.dao.GoodsMapper;
import com.yaoli.service.IGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kk on 2017/6/9.
 */
@Service(value="goodsService")
public class GoodsServiceImpl implements IGoodsService{
    @Resource
    private GoodsMapper goodsMapper;
    @Override
    public List<String> getAllGoodsNames() {
        return goodsMapper.getAllGoodsName();
    }

    @Override
    public Goods selectGoodsByGoodsNameAndNumber(Goods goods) {
        return goodsMapper.selectGoodsByGoodsNameAndNumber(goods);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return goodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Goods record) {
        return goodsMapper.insert(record);
    }

    @Override
    public int insertSelective(Goods record) {
        return goodsMapper.insertSelective(record);
    }

    @Override
    public Goods selectByPrimaryKey(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Goods record) {
        return goodsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Goods record) {
        return goodsMapper.updateByPrimaryKey(record);
    }
}
