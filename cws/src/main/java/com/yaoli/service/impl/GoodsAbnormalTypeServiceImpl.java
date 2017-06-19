package com.yaoli.service.impl;


import com.yaoli.beans.Goods_abnormal_type;
import com.yaoli.dao.GoodsAbnormalTypeMapper;
import com.yaoli.service.IGoodsAbnormalTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service(value = "goods_abnormal_typeService")
public class GoodsAbnormalTypeServiceImpl implements IGoodsAbnormalTypeService {
    @Resource
    private GoodsAbnormalTypeMapper goods_abnormal_typeMapper;
    @Override
    public List<Goods_abnormal_type> getAllGoods_abnormal_type() {
        return goods_abnormal_typeMapper.getAllGoods_abnormal_type();
    }

    @Override
    public Goods_abnormal_type selectByName(String name) {
        return goods_abnormal_typeMapper.selectByName(name);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return goods_abnormal_typeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Goods_abnormal_type record) {
        return goods_abnormal_typeMapper.insert(record);
    }

    @Override
    public int insertSelective(Goods_abnormal_type record) {
        return goods_abnormal_typeMapper.insertSelective(record);
    }

    @Override
    public Goods_abnormal_type selectByPrimaryKey(Integer id) {
        return goods_abnormal_typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Goods_abnormal_type record) {
        return goods_abnormal_typeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Goods_abnormal_type record) {
        return goods_abnormal_typeMapper.updateByPrimaryKey(record);
    }


}