package com.yaoli.controller;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.*;
import com.yaoli.service.impl.*;
import com.yaoli.util.JsonMessage;
import com.yaoli.vo.huangkai.GoodsAbnormal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by kk on 2017/6/8. /goods_abnoral/gotogoods_abnoralinputpage.do
 */
@Controller
@RequestMapping(value={"/goodsabnoral"})
public class GoodsAbnormalController {
    @Autowired
    private GoodsServiceImpl goodsServiceImpl;
    @Autowired
    private GoodsAbnormalTypeServiceImpl goodsAbnormalTypeServiceImpl;
    @Autowired
    private GoodsOriginServiceImpl goodsOriginServiceImpl;
    @Autowired
    private GoodsStatusServiceImpl goodsStatusServiceImpl;
    @Autowired
    private GoodsAbnormalServiceImpl goodsAbnormalServiceImpl;

    @RequestMapping(value={"/gotogoodsabnoralinputpage.do"})
    public String gotogoodsabnoralinputpage(){
        return "huangkai/goodsabnormal";
    }

    @RequestMapping(value={"/gotostatuschangepage.do"})
    public String gotostatuschangepage(){
        return "huangkai/statuschange";
    }

   @RequestMapping(value={"/getgoodsabnormalpageselectdata.do"})
    public void getGoodsAbnormalPageSelectData(HttpServletRequest request, HttpServletResponse response)throws IOException{
       //1.从数据库中获取物品名称、物品编码、物品来源、故障类型
       List<String> goodsNames = goodsServiceImpl.getAllGoodsNames();
       List<Goods_origin> goods_origins = goodsOriginServiceImpl.getAllGoods_origin();
       List<Goods_status> goods_status = goodsStatusServiceImpl.getAllGoods_status();
       List<Goods_abnormal_type> goods_abnormal_types= goodsAbnormalTypeServiceImpl.getAllGoods_abnormal_type();

       //2.将这些数据封装成JSON格式发到goodsabnormal.jsp页面
       Map<String,Object> selectData = new HashMap<String,Object>();
       selectData.put("goods",goodsNames);
       selectData.put("goods_origins",goods_origins);
       selectData.put("goods_status",goods_status);
       selectData.put("goods_abnormal_types",goods_abnormal_types);
       response.setContentType("text/html; charset=UTF-8");
       response.getWriter().write(JSON.toJSONString(selectData));
    }

    @RequestMapping(value={"/getstatuschangepageselectdata.do"})
    public void getStatusChangePageSelectData(HttpServletRequest request, HttpServletResponse response)throws IOException{
        List<String> goodsNames = goodsServiceImpl.getAllGoodsNames();
        List<Goods_status> goods_status = goodsStatusServiceImpl.getAllGoods_status();
        Map<String,Object> selectData = new HashMap<String,Object>();
        selectData.put("goods",goodsNames);
        selectData.put("goods_status",goods_status);
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(selectData));
    }

    @RequestMapping(value="insertgoodsabnormal.do")
    public void insertGoodsAbnormal(HttpServletRequest request, HttpServletResponse response ,@RequestBody GoodsAbnormal goodsAbnormal)throws IOException {
        //1. 先检查数据库中物品的来源和状态，以及故障类型，如果数据库中不存在，则新增
        //新增物品来源
        if (goodsAbnormal.getGoodsOriginId() == 0) {//插入新的物品来源
            //查看数据库中是否已经存在
            Goods_origin goods_origin_old = goodsOriginServiceImpl.selectByName(goodsAbnormal.getGoodsOrigin());
            if (goods_origin_old == null) { //不存在则新增
                Goods_origin goods_origin_insert = new Goods_origin(goodsAbnormal.getGoodsOrigin());
                goodsOriginServiceImpl.insert(goods_origin_insert);
            }

        }

      /*  //新增物品状态，这里作为保留，因为新增的时候要将物品的状态置为 “待检测”
        if(goodsAbnormal.getAbnormalTypeId() == 0){
            Goods_status goods_status = new Goods_status();
        }*/

        if (goodsAbnormal.getAbnormalTypeId() == 0){//插入新的故障类型
              Goods_abnormal_type goods_abnormal_type_old = goodsAbnormalTypeServiceImpl.selectByName(goodsAbnormal.getAbnormalType());
              if ( goods_abnormal_type_old== null) {
                  Goods_abnormal_type goods_abnormal_type_insert = new Goods_abnormal_type(goodsAbnormal.getAbnormalType());//获取故障类型,创建对象；
                  goodsAbnormalTypeServiceImpl.insert(goods_abnormal_type_insert);//向数据库中插入新的数据类型；
              }
        }

        //2.新增物品信息
        System.out.println(goodsAbnormal.getGoodsOrigin());
        Goods_origin goodsorigin = goodsOriginServiceImpl.selectByName(goodsAbnormal.getGoodsOrigin());
        Goods_status goodsstatus = goodsStatusServiceImpl.selectByName("待检修");
        Goods goods_insert = new Goods(goodsAbnormal.getGoodsName(),goodsAbnormal.getGoodsNumber(), goodsstatus.getId(),goodsorigin.getId(),new Date());
        goodsServiceImpl.insert(goods_insert); // 插入物品记录

        //3.新增故障信息
        Goods_abnormal_type goods_abnormal_type_select = goodsAbnormalTypeServiceImpl.selectByName(goodsAbnormal.getAbnormalType());
        Goods goods_select = goodsServiceImpl.selectGoodsByGoodsNameAndNumber(goods_insert);
        Goods_abnormal goods_abnormal = new Goods_abnormal(goods_select.getId(),goodsAbnormal.getAbnormalDescription(),
                goods_abnormal_type_select.getId(),goodsAbnormal.getAbnormalAnalysis(),new Date());
        goodsAbnormalServiceImpl.insert(goods_abnormal);
    }


    @RequestMapping(value="getgoodscurrentstatus.do")
    public void getGoodsCurrentState(HttpServletRequest request, HttpServletResponse response ,@RequestBody Goods goodsNameAndNumber)throws IOException {
        System.out.println(goodsNameAndNumber.getName());
        System.out.println(goodsNameAndNumber.getNumber());
        Goods currentGoods = goodsServiceImpl.selectGoodsByGoodsNameAndNumber(goodsNameAndNumber);//获取当前物品的状态
        JsonMessage jsonMessage = new JsonMessage();
        response.setContentType("html/json;charset=UTF-8");
        if (currentGoods == null ) {
            jsonMessage.setKey("error");
            jsonMessage.setMessage("物品编码输入错误");
            response.getWriter().write(JSON.toJSONString(jsonMessage));
        } else {
                Goods_status currentGoodsStatus = goodsStatusServiceImpl.selectByPrimaryKey(currentGoods.getGoodsStatusId());
            List goodsAndStatus = new LinkedList();
            Goods_abnormal goods_abnormal = goodsAbnormalServiceImpl.selectByGoodsId(currentGoods.getId());
            goodsAndStatus.add(currentGoodsStatus);
            goodsAndStatus.add(goods_abnormal);
            System.out.println(goods_abnormal);
            jsonMessage.setKey("success");
            jsonMessage.setData(goodsAndStatus);
            response.getWriter().write(JSON.toJSONString(JSON.toJSONString(jsonMessage)));
        }
    }

    @RequestMapping(value="getgoodsabnormal.do")
    public void getGoodsAbnormal(HttpServletRequest request, HttpServletResponse response,@RequestBody Goods goods )throws IOException {

        //  System.out.println(goods);
        //goodsAbnormalServiceImpl.

    }
}
