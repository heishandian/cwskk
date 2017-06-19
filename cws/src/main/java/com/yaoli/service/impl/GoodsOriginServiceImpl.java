package com.yaoli.service.impl;

import com.yaoli.beans.Goods_origin;
import com.yaoli.dao.GoodsOriginMapper;
import com.yaoli.service.IGoodsOriginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service(value = "goods_originService")
public class GoodsOriginServiceImpl implements IGoodsOriginService {
    @Resource
    private GoodsOriginMapper goods_originMapper;

    @Override
    public List<Goods_origin> getAllGoods_origin() {
        return goods_originMapper.getAllGoods_origin();
    }

    @Override
    public Goods_origin selectByName(String name) {
        return goods_originMapper.selectByName(name);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return goods_originMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Goods_origin record) {
        return goods_originMapper.insert(record);
    }

    @Override
    public int insertSelective(Goods_origin record) {
        return goods_originMapper.insertSelective(record);
    }

    @Override
    public Goods_origin selectByPrimaryKey(Integer id) {
        return goods_originMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Goods_origin record) {
        return goods_originMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Goods_origin record) {
        return goods_originMapper.updateByPrimaryKey(record);
    }
}