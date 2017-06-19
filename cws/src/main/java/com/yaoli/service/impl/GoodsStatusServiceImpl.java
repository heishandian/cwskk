package com.yaoli.service.impl;

import com.yaoli.beans.Goods_status;
import com.yaoli.dao.GoodsStatusMapper;
import com.yaoli.service.IGoodsStatusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service(value="goods_statusService")
public class GoodsStatusServiceImpl implements IGoodsStatusService {
    @Resource
    private GoodsStatusMapper goods_statusMapper;
    @Override
    public List<Goods_status> getAllGoods_status() {
        return goods_statusMapper.getAllGoods_status();
    }

    @Override
    public Goods_status selectByName(String name) {
        return goods_statusMapper.selectByName(name);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return goods_statusMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Goods_status record) {
        return goods_statusMapper.insert(record);
    }

    @Override
    public int insertSelective(Goods_status record) {
        return goods_statusMapper.insertSelective(record);
    }

    @Override
    public Goods_status selectByPrimaryKey(Integer id) {
        return goods_statusMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Goods_status record) {
        return goods_statusMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Goods_status record) {
        return goods_statusMapper.updateByPrimaryKey(record);
    }
}