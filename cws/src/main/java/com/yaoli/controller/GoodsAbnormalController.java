package com.yaoli.controller;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.*;
import com.yaoli.service.impl.*;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.util.excel.hungkai.GoodsAbnormalStasticExcelView;
import com.yaoli.vo.huangkai.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by kk on 2017/6/8. /goods_abnoral/gotogoods_abnoralinputpage.do
 */
@Controller
@RequestMapping(value = {"/goodsabnoral"})
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

    @RequestMapping(value = {"/gotogoodsabnoralinputpage.do"})
    public String gotoGoodsAbnoralInputPage() {
        return "huangkai/goodsabnormal/goodsabnormal";
    }

    @RequestMapping(value = {"/gotostatuschangepage.do"})
    public String gotoStatusChangePage() {
        return "huangkai/goodsabnormal/statuschange";
    }

    @RequestMapping(value = {"/gotogoodsabnormalsearchpage.do"})
    public String gotoGoodsAbnormalPage() {
        return "huangkai/goodsabnormal/goodsabnormalsearch";
    }

    @RequestMapping(value = {"/gotogoodsabnormalstatisticspage.do"})
    public String gotoGoodsAbnormalStatisticsPage(HashMap<String, Object> modle) {
        List<Goods_origin> goodsOrigins = goodsOriginServiceImpl.getAllGoods_origin();
        modle.put("goodsOrigins", goodsOrigins);
        return "huangkai/goodsabnormal/goodsabnormalstatistics";
    }

    @RequestMapping(value = {"/getgoodsabnormalpageselectdata.do"})
    public void getGoodsAbnormalPageSelectData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.从数据库中获取物品名称、物品编码、物品来源、故障类型
        List<String> goodsNames = goodsServiceImpl.getAllGoodsNames();
        List<Goods_origin> goods_origins = goodsOriginServiceImpl.getAllGoods_origin();
        List<Goods_status> goods_status = goodsStatusServiceImpl.getAllGoods_status();
        List<Goods_abnormal_type> goods_abnormal_types = goodsAbnormalTypeServiceImpl.getAllGoods_abnormal_type();

        //2.将这些数据封装成JSON格式发到goodsabnormal.jsp页面
        Map<String, Object> selectData = new HashMap<String, Object>();
        selectData.put("goods", goodsNames);
        selectData.put("goods_origins", goods_origins);
        selectData.put("goods_status", goods_status);
        selectData.put("goods_abnormal_types", goods_abnormal_types);
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(selectData));
    }

    @RequestMapping(value = {"/getstatuschangepageselectdata.do"})
    public void getStatusChangePageSelectData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> goodsNames = goodsServiceImpl.getAllGoodsNames();
        List<Goods_status> goods_status = goodsStatusServiceImpl.getAllGoods_status();
        Map<String, Object> selectData = new HashMap<String, Object>();
        selectData.put("goods", goodsNames);
        selectData.put("goods_status", goods_status);
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(selectData));
    }


    @RequestMapping(value = {"/isgoodsnameandnumberuniqueness.do"})
    public void isGoodsNameAndNumberUniqueness(HttpServletRequest request, HttpServletResponse response, @RequestBody Goods goods) throws IOException {
        System.out.println(goods);
        Goods currentGoods = goodsServiceImpl.selectGoodsByGoodsNameAndNumber(goods);//根据物品名和编码获取物品信息
        String result = null;
        if (currentGoods == null) {
            result = "inexistence";
        } else {
            result = "existence";
        }
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setKey(result);
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(jsonMessage));
    }

    @RequestMapping(value = "/insertgoodsabnormal.do")
    @Transactional
    public void insertGoodsAbnormal(HttpServletRequest request, HttpServletResponse response, @RequestBody GoodsAbnormalVO goodsAbnormal) throws IOException {
        //1. 先检查数据库中物品的来源和状态，以及故障类型，如果数据库中不存在，则新增
        //新增物品来源
        String result = null;
        try {
            if (goodsAbnormal.getGoodsOriginId() == 0) {//插入新的物品来源
                //查看数据库中是否已经存在

                Goods_origin goods_origin_old = goodsOriginServiceImpl.selectByName(goodsAbnormal.getGoodsOrigin());
                if (goods_origin_old == null) { //不存在则新增
                    Goods_origin goods_origin_insert = new Goods_origin(goodsAbnormal.getGoodsOrigin());
                    goodsOriginServiceImpl.insert(goods_origin_insert);
                }
            }

            if (goodsAbnormal.getAbnormalTypeId() == 0) {//插入新的故障类型
                Goods_abnormal_type goods_abnormal_type_old = goodsAbnormalTypeServiceImpl.selectByName(goodsAbnormal.getAbnormalType());
                if (goods_abnormal_type_old == null) {
                    Goods_abnormal_type goods_abnormal_type_insert = new Goods_abnormal_type(goodsAbnormal.getAbnormalType());//获取故障类型,创建对象；
                    goodsAbnormalTypeServiceImpl.insert(goods_abnormal_type_insert);//向数据库中插入新的数据类型；
                }
            }

            Goods_origin goodsorigin = goodsOriginServiceImpl.selectByName(goodsAbnormal.getGoodsOrigin());
            Goods_status goodsstatus = goodsStatusServiceImpl.selectByName("待检修");
            Goods goods_insert = new Goods(goodsAbnormal.getGoodsName(), goodsAbnormal.getGoodsNumber(), goodsstatus.getId(), goodsorigin.getId(), new Date());
            goodsServiceImpl.insert(goods_insert); // 插入物品记录

            //3.新增故障信息
            Goods_abnormal_type goods_abnormal_type_select = goodsAbnormalTypeServiceImpl.selectByName(goodsAbnormal.getAbnormalType());
            Goods goods_select = goodsServiceImpl.selectGoodsByGoodsNameAndNumber(goods_insert);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date abnormalTime = simpleDateFormat.parse(goodsAbnormal.getAbnormalTime());
            Goods_abnormal goods_abnormal = new Goods_abnormal(goods_select.getId(), goodsAbnormal.getAbnormalDescription(),
                    goods_abnormal_type_select.getId(), goodsAbnormal.getAbnormalAnalysis(), abnormalTime);
            goodsAbnormalServiceImpl.insert(goods_abnormal);
            result = "success";
        } catch (Exception e) {
            //e.printStackTrace();
            result = "failure";
        } finally {
            JsonMessage jsonMessage = new JsonMessage();
            jsonMessage.setKey(result);
            response.setContentType("html/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(jsonMessage));
        }
    }

    @RequestMapping(value = "/getgoodscurrentstatus.do")
    public void getGoodsCurrentState(HttpServletRequest request, HttpServletResponse response, @RequestBody Goods goodsNameAndNumber) throws IOException {
        Goods currentGoods = goodsServiceImpl.selectGoodsByGoodsNameAndNumber(goodsNameAndNumber);//根据物品名和编码获取物品信息
        JsonMessage jsonMessage = new JsonMessage();
        if (currentGoods == null) {
            jsonMessage.setKey("error");
            jsonMessage.setMessage("物品编码输入错误");
        } else {
            Goods_status currentGoodsStatus = goodsStatusServiceImpl.selectByPrimaryKey(currentGoods.getGoodsStatusId());
            List goodsAndStatus = new LinkedList();
            Goods_abnormal goods_abnormal = goodsAbnormalServiceImpl.selectByGoodsId(currentGoods.getId());
            goodsAndStatus.add(currentGoodsStatus);
            goodsAndStatus.add(goods_abnormal);
            jsonMessage.setKey("success");
            jsonMessage.setData(goodsAndStatus);
        }
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(JSON.toJSONString(jsonMessage)));
    }

    @RequestMapping(value = "/statuschange.do")
    public void statusChange(HttpServletRequest request, HttpServletResponse response, @RequestBody StatusChangeDataVO statusChangeData) throws IOException {
        //根据物品的名称和编号，去改变物品的状态,并记录更新时间
        String result = "success";
        try {
            Goods newGoodsStatus = new Goods();
            newGoodsStatus.setName(statusChangeData.getGoodsName());
            newGoodsStatus.setNumber(statusChangeData.getGoodsNumber());
            newGoodsStatus.setGoodsStatusChangetime(new Date());
            newGoodsStatus.setGoodsStatusId(statusChangeData.getChangeStatusId());
            goodsServiceImpl.updateByPrimaryKey(newGoodsStatus);

            //2. 更改故障分析
            Goods_abnormal goods_abnormal = new Goods_abnormal();
            goods_abnormal.setId(statusChangeData.getAbnormalId());
            goods_abnormal.setGoodsAbnormalAnalyse(statusChangeData.getAbnormalAnalysis());
            goodsAbnormalServiceImpl.updateByPrimaryKey(goods_abnormal);
        } catch (Exception e) {
            result = "failure";
            e.printStackTrace();
        }
        response.getWriter().write(JSON.toJSONString(JSON.toJSONString(result)));
    }


    @RequestMapping(value = "/abnormalsearch.do")
    public void abnormalSearch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1.获取全部查询条件
        String name = String.valueOf(request.getParameter("name")).equals("") ? null : String.valueOf(request.getParameter("name"));
        String number = String.valueOf(request.getParameter("number")).equals("") ? null : String.valueOf(request.getParameter("number"));
        String origin = String.valueOf(request.getParameter("origin")).equals("") ? null : String.valueOf(request.getParameter("origin"));
        String status = String.valueOf(request.getParameter("status")).equals("") ? null : String.valueOf(request.getParameter("status"));
        String starttime = String.valueOf(request.getParameter("starttime")).equals("") ? null : String.valueOf(request.getParameter("starttime"));
        String endtime = String.valueOf(request.getParameter("endtime")).equals("") ? null : String.valueOf(request.getParameter("endtime"));

        //获取页码和每一页条数
        String page = String.valueOf(request.getParameter("page"));
        String rows = String.valueOf(request.getParameter("rows"));

        //根据查询条件查询物品故障记录
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date start_time = null;
        Date end_time = null;
        if (starttime != null) {
            start_time = simpleDateFormat.parse(starttime);
        }
        if (endtime != null) {
            end_time = simpleDateFormat.parse(endtime);
        }
        GoodsAbnormalSearchConditionVO goodsAbnormalSearchCondition
                = new GoodsAbnormalSearchConditionVO(name, number, origin, status, start_time, end_time, page, rows);
        List<GoodsAbnormalSearchResult> goodsAbnormalSearchResults
                = goodsAbnormalServiceImpl.selectAllGoodsAbnormalRecordByPaging(goodsAbnormalSearchCondition);//获取满足条件的记录
        int counts = goodsAbnormalServiceImpl.selectAbnormalRecordCount(goodsAbnormalSearchCondition);//获取总条数
        SysPagingUtil sysPagingUtil = new SysPagingUtil();//包装结果发送给easyUI网格
        sysPagingUtil.setTotal(String.valueOf(counts));
        sysPagingUtil.setRows(goodsAbnormalSearchResults);
        String jsondata = JSON.toJSONString(sysPagingUtil);
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(jsondata);
    }

    @RequestMapping(value = "/abnormalstasticsearch.do")
    public Object abnormalStasticSearch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String type = String.valueOf(request.getParameter("type"));
        String time = String.valueOf(request.getParameter("time"));
        String flag = String.valueOf(request.getParameter("flag"));
        String origin = null;
        if (type.equals("download")) {
             origin = new String(request.getParameter("origin").getBytes("iso8859-1"), "utf-8");
        } else {
             origin = String.valueOf(request.getParameter("origin"));
        }
        String page = String.valueOf(request.getParameter("page"));
        String rows = String.valueOf(request.getParameter("rows"));
        if ("-请选择来源-".equals(origin)) {
            origin = null;
        }
        AbnormalStasticSearchConditionVO abnormalStasticSearchConditionVO = new AbnormalStasticSearchConditionVO(time, flag, origin, page, rows);
        System.out.println(abnormalStasticSearchConditionVO);
        List<AbnormalStasticSearchResultVO> list = goodsAbnormalServiceImpl.selectAllAbnormalStatisticByPaging(abnormalStasticSearchConditionVO);
        List<GoodsAbnormalStatisticResult> result = new ArrayList<GoodsAbnormalStatisticResult>();
        Set<String> names = new HashSet<String>();
        Iterator<AbnormalStasticSearchResultVO> it = list.iterator();
        AbnormalStasticSearchResultVO temp = null;
        while (it.hasNext()) {
            names.add(it.next().getName());
        }
        Iterator<String> namesIterator = names.iterator();
        int processingamountstemp = 0;
        while (namesIterator.hasNext()) {
            GoodsAbnormalStatisticResult goodsAbnormalStatisticResult = new GoodsAbnormalStatisticResult();
            String goodsName = namesIterator.next();
            for (int i = 0; i < list.size(); i++) {
                temp = list.get(i);
                if (goodsName.equals(temp.getName())) {
                    goodsAbnormalStatisticResult.setName(temp.getName());
                    if ("待检修".equals(temp.getStatus())) {
                        goodsAbnormalStatisticResult.setAwaitdetectionamounts(temp.getTotal());
                    } else if ("待返厂".equals(temp.getStatus()) || "返厂检修".equals(temp.getStatus())) {//待返厂和返厂检修的数量和为在处理数量
                        processingamountstemp += list.get(i).getTotal();
                    } else if ("报废".equals(temp.getStatus())) {
                        goodsAbnormalStatisticResult.setScrapedamounts(temp.getTotal());
                    } else if ("完成".equals(temp.getStatus())) {
                        goodsAbnormalStatisticResult.setCompletedamounts(temp.getTotal());
                    }
                }
            }
            goodsAbnormalStatisticResult.setProcessingamounts(processingamountstemp);
            processingamountstemp = 0;
            result.add(goodsAbnormalStatisticResult);
        }
        if (type.equals("search")) {//这里为查询
            SysPagingUtil sysPagingUtil = new SysPagingUtil();//包装结果发送给easyUI网格
            sysPagingUtil.setTotal(String.valueOf(names.size()));
            sysPagingUtil.setRows(result);
            String jsondata = JSON.toJSONString(sysPagingUtil);
            response.setContentType("html/json;charset=UTF-8");
            response.getWriter().write(jsondata);
            return null;
        } else {//这里为下载Excel
            Map<String, Object> downloadData = new HashMap<String, Object>();
            downloadData.put("data", result);
            downloadData.put("flag", flag);
            downloadData.put("time", time);
            downloadData.put("origin", origin);
            return new ModelAndView(new GoodsAbnormalStasticExcelView(), downloadData);
        }

    }


}
