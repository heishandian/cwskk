package com.yaoli.controller;

import com.alibaba.fastjson.JSON;
import com.yaoli.aspect.annotation.FormAddTokenAnnotation;
import com.yaoli.aspect.annotation.FormCheckTokenAnnotation;
import com.yaoli.beans.*;
import com.yaoli.service.IBreakdownService;
import com.yaoli.service.IBreakdownStateService;
import com.yaoli.service.ISysUserService;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.RequestParameterParse;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.util.excel.SimpleExcelView;
import com.yaoli.util.excel.object.ExcelContentObject;
import com.yaoli.vo.BreakdownVO;
import com.yaoli.vo.RequestParameterVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


/**
 * Created by will on 2016/12/12.
 */
@Controller
@RequestMapping("/fr")
public class BreakdownController {

    //物品状态
    //breakdownState 待检修、待返厂、返厂检修、报废、完成 用 0 1 2 3 4 表示
    private static ArrayList<String>  itemStateList = new ArrayList<String>();

    //物品名称
    private static ArrayList<String>  itemNameList = new ArrayList<String>();

    //来源
    private static ArrayList<String>  sourceIdList = new ArrayList<String>();

    //故障类型
    private static ArrayList<String>  breakdownTypeIdList = new ArrayList<String>();

    static {
        itemStateList.add("待检修");
        itemStateList.add("待返厂");
        itemStateList.add("返厂检修");
        itemStateList.add("报废");
        itemStateList.add("完成");

        //物品名称
        itemNameList.add("流量计");
        itemNameList.add("显示屏");
        itemNameList.add("路由器");
        itemNameList.add("大电源");
        itemNameList.add("小电源");
        itemNameList.add("控制板");

        sourceIdList.add("锡山");
        sourceIdList.add("江阴");
        sourceIdList.add("惠山");
        sourceIdList.add("江都");

        breakdownTypeIdList.add("设备损坏");
        breakdownTypeIdList.add("显示问题");
        breakdownTypeIdList.add("网络问题");
        breakdownTypeIdList.add("系统问题");
        breakdownTypeIdList.add("设备输出问题");
    }


    @Resource
    private IBreakdownService iBreakdownService;

    @Resource
    private IBreakdownStateService iBreakdownStateService;

    @Resource
    private ISysUserService iSysUserService;

    @RequestMapping("/gotoFaultStatistic.do")
    public ModelAndView gotoFaultStatistic(HttpServletResponse response, HttpServletRequest request, ModelAndView modelAndView){
        modelAndView.setViewName("/breakdown/faultStatistic");
        return modelAndView;
    }

    @RequestMapping("/gotoFaultReview.do")
    @FormAddTokenAnnotation
    public ModelAndView gotoFaultReview(HttpServletResponse response, HttpServletRequest request, ModelAndView modelAndView){
        modelAndView.setViewName("/breakdown/breakdownReview");
        return modelAndView;
    }

    @RequestMapping("/gotoFaultInput.do")
    @FormAddTokenAnnotation
    public ModelAndView gotoFaultInput(HttpServletResponse response, HttpServletRequest request, ModelAndView modelAndView){
        modelAndView.setViewName("/breakdown/addBreakdown");
        return modelAndView;
    }

    @RequestMapping("/gotoStateTransform.do")
    public ModelAndView gotoStateTransform(HttpServletResponse response, HttpServletRequest request, ModelAndView modelAndView){
        modelAndView.setViewName("/breakdown/stateTransform");
        return modelAndView;
    }

    @RequestMapping("/getFaultStatisticReportExcel.do")
    public ModelAndView getFaultStatisticReportExcel(HttpServletResponse response, HttpServletRequest request, ModelAndView modelAndView) throws Exception {
        RequestParameterVO vo = (RequestParameterVO)(new RequestParameterParse(RequestParameterVO.class,request).getParameterMap());

        String reporttype = vo.getReporttype();

        Map<String, String> map = new HashMap<String, String>();
        map.put("pageSize", vo.getRows());
        map.put("pageNum", vo.getPage());
        map.put("testingtime", vo.getTestingtime());
        map.put("sourceid", vo.getSourceid());
        map.put("reporttype", vo.getReporttype());

        List<BreakdownVO> listVO = iBreakdownService.getAllFaultStatisticReport(map);


        for(BreakdownVO breakdownVO : listVO){
            breakdownVO.setItemname(itemNameList.get(Integer.valueOf(breakdownVO.getItemname())));
        }


        ExcelContentObject content = new ExcelContentObject();

        //登录人
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = iSysUserService.getUserByLoginName((String)subject.getPrincipal());

        content.setAuthor(sysUser.getUsername());

        //设置制表时间
        content.setMakeTableTime(new DateTime());

        String temp = "";
        if(reporttype.equals("2")){
            temp = vo.getTestingtime()+"月度";
        }else{
            temp = vo.getTestingtime()+"年度";
        }

        //设置header
        content.setHeader(sourceIdList.get(Integer.valueOf(vo.getSourceid()))+temp+"物品故障统计报表");

        //设置第一列标题
        content.setMainTitle("物品名称");

        //设置第二列 后面的标题
        ArrayList<String> titles = new ArrayList<String>();
        titles.add("待检修数量");
        titles.add("在处理数量");
        titles.add("报废数量");
        titles.add("维修完成数量");
        content.setTitles(titles);

        //填充第一列数据
        ArrayList<String> mainData = new ArrayList<String>();
        ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();

        for(BreakdownVO vos : listVO){
            mainData.add(vos.getItemname());
            ArrayList<String> data = new ArrayList<String>();

            data.add(vos.getNumSongJian());

            data.add(vos.getNumZaiChuLi());

            data.add(vos.getNumBaoFei());

            data.add(vos.getNumWeiXiuWanChen());

            datas.add(data);

        }
        content.setMainData(mainData);
        content.setData(datas);

        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("view", content);

        //return new ModelAndView(new WarmingLogStatisticExcelView(), parameter);
        return new ModelAndView(new SimpleExcelView(), parameter);

    }

    @RequestMapping("/ajaxShowBreakdownDetails.do")
    public void ajaxShowBreakdownDetails(HttpServletResponse response, HttpServletRequest request, ModelAndView modelAndView) throws IOException {
        Integer id = request.getParameter("id") == null? null:Integer.valueOf(request.getParameter("id"));

        JsonMessage message = new JsonMessage();

        //获取到 breakdown
        Breakdown breakdown = iBreakdownService.selectByPrimaryKey(id);

        BreakdownState breakdownState = iBreakdownStateService.selectMaxStateIdByBreakdownId(id);

        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        //转换成 breakdown
        BreakdownVO breakdownVO = mapper.map(breakdown, BreakdownVO.class);

        //来源
        breakdownVO.setSource(sourceIdList.get(breakdown.getSourceid()));

        //物品名称
        breakdownVO.setItemname(itemNameList.get(Integer.valueOf(breakdown.getItemname())));

        //故障类型
        breakdownVO.setBreakdowntype(breakdownTypeIdList.get(Integer.valueOf(breakdown.getBreakdowntypeid())));

        //当前状态
        breakdownVO.setItemstate(itemStateList.get(Integer.valueOf(breakdownState.getItemstateid())));

        message.setData(breakdownVO);

        String jsondata = JSON.toJSONString(message);
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(jsondata);

    }


    @RequestMapping("/getFaultStatisticReport.do")
    public void getFaultStatisticReport(HttpServletResponse response, HttpServletRequest request, ModelAndView modelAndView) throws Exception {
        RequestParameterVO vo = (RequestParameterVO)(new RequestParameterParse(RequestParameterVO.class,request).getParameterMap());

        String reporttype = vo.getReporttype();

        Map<String, String> map = new HashMap<String, String>();
        map.put("pageSize", vo.getRows());
        map.put("pageNum", vo.getPage());
        map.put("testingtime", vo.getTestingtime());
        map.put("sourceid", vo.getSourceid());
        map.put("reporttype", vo.getReporttype());

        List<BreakdownVO> listVO = iBreakdownService.getFaultStatisticReport(map);
        int count = iBreakdownService.getFaultStatisticReportCount(map);

        for(BreakdownVO breakdownVO : listVO){
            breakdownVO.setItemname(itemNameList.get(Integer.valueOf(breakdownVO.getItemname())));
        }

        SysPagingUtil sysPagingUtil = new SysPagingUtil();
        sysPagingUtil.setTotal(String.valueOf(count));
        sysPagingUtil.setRows(listVO);

        String jsondata = JSON.toJSONString(sysPagingUtil);

        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(jsondata);

    }

    @RequestMapping("/ajaxShowBreakdownStateDetails.do")
    public void ajaxShowBreakdownStateDetails(HttpServletResponse response, HttpServletRequest request, ModelAndView modelAndView) throws IOException {
        Integer id = request.getParameter("id") == null? null:Integer.valueOf(request.getParameter("id"));

        JsonMessage message = new JsonMessage();

        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

        //根据breakdownid 获取所有的 states
        List<BreakdownState> breakdownStateList = iBreakdownStateService.getBreakdownStateListByBreakdownId(id);

        List<BreakdownVO> breakdownVO = new ArrayList<BreakdownVO>();

        for(BreakdownState vo : breakdownStateList){
            //转换成 breakdown
            BreakdownVO temp = mapper.map(vo, BreakdownVO.class);

            temp.setItemstate(itemStateList.get(Integer.valueOf(temp.getItemstateid())));

            breakdownVO.add(temp);
        }

        message.setData(breakdownVO);

        String jsondata = JSON.toJSONString(message);
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(jsondata);

    }



    @RequestMapping("/showFaultReview.do")
    public void showFaultReview(HttpServletResponse response, HttpServletRequest request) throws Exception {
        RequestParameterVO vo = (RequestParameterVO)(new RequestParameterParse(RequestParameterVO.class,request).getParameterMap());


        Map<String, String> map = new HashMap<String, String>();
        map.put("pageSize", vo.getRows());
        map.put("pageNum", vo.getPage());
        map.put("itemname", vo.getItemname());
        map.put("itemno", vo.getItemno());
        map.put("sourceid", vo.getSourceid());
        map.put("itemstateid", vo.getItemstateid());
        map.put("begintime", vo.getBegintime());
        map.put("endtime", vo.getEndtime());

        List<BreakdownVO> lists = iBreakdownService.showFaultReview(map);

        for(BreakdownVO breakdownVO:lists){

            //来源
            breakdownVO.setSource(sourceIdList.get(breakdownVO.getSourceid()));

            //物品名称
            breakdownVO.setItemname(itemNameList.get(Integer.valueOf(breakdownVO.getItemname())));

            //故障类型
            breakdownVO.setBreakdowntype(breakdownTypeIdList.get(Integer.valueOf(breakdownVO.getBreakdowntypeid())));

            //当前状态
            breakdownVO.setItemstate(itemStateList.get(Integer.valueOf(breakdownVO.getItemstateid())));
        }

        int count = iBreakdownService.showFaultReviewCount(map);

        SysPagingUtil sysPagingUtil = new SysPagingUtil();
        sysPagingUtil.setTotal(String.valueOf(count));
        sysPagingUtil.setRows(lists);

        String jsondata = JSON.toJSONString(sysPagingUtil);

        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(jsondata);
    }


    @RequestMapping("/addBreakdown.do")
    @FormCheckTokenAnnotation
    public void addBreakdown(@RequestBody BreakdownVO vo, HttpServletResponse response, HttpServletRequest request, ModelAndView modelAndView) throws IOException {
        JsonMessage message = new JsonMessage();

        if(request.getSession().getAttribute("formcheck").equals("pass")){

            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            //转换成 breakdown
            Breakdown breakdown = mapper.map(vo, Breakdown.class);

            //设置时间
            breakdown.setBreakdowntime(new Date());

            //返回breakdownId
            int breakDownId = iBreakdownService.insert(breakdown);

            BreakdownState breakdownState = new BreakdownState();
            breakdownState.setBreakdownid(breakdown.getId());
            breakdownState.setUpdatetime(new Date());
            breakdownState.setItemstateid(0);

            Subject subject = SecurityUtils.getSubject();
            //获取用户名
            String userLoginName = subject.getPrincipal().toString();
            //根据用户名获取用户id，用户查询
            SysUser sysUser = iSysUserService.getUserByLoginName(userLoginName);
            breakdownState.setOperatorid(sysUser.getId());


            int breakdownCount = iBreakdownStateService.insert(breakdownState);



            message.setKey("pass");

        }else{
            message.setKey("submited");
        }
        String jsondata = JSON.toJSONString(message);
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(jsondata);

    }

    @RequestMapping("/updateBreakdown.do")
    public void updateBreakdown(@RequestBody BreakdownVO vo, HttpServletResponse response, HttpServletRequest request, ModelAndView modelAndView) throws IOException {
        JsonMessage message = new JsonMessage();


        Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
        //转换成 breakdown
        Breakdown breakdown = mapper.map(vo, Breakdown.class);


        //更新 breakdown
        int breakDownId = iBreakdownService.updateByPrimaryKeySelective(breakdown);


//            list.add("待检修");
//            list.add("待返厂");
//            list.add("返厂检修");
//            list.add("报废");
//            list.add("完成");
//            如果 状态为 0 待检修

        BreakdownState bds = iBreakdownStateService.selectMaxStateIdByBreakdownId(vo.getId());

        //页面上的id > 数据库中的id
        //说明数据库中 不存在 所以可以直接插入
        if(vo.getItemstateid() > bds.getItemstateid()){

            BreakdownState breakdownState = new BreakdownState();
            breakdownState.setBreakdownid(breakdown.getId());
            breakdownState.setUpdatetime(new Date());
            //设置stateid
            breakdownState.setItemstateid(vo.getItemstateid());

            Subject subject = SecurityUtils.getSubject();
            //获取用户名
            String userLoginName = subject.getPrincipal().toString();
            //根据用户名获取用户id，用户查询
            SysUser sysUser = iSysUserService.getUserByLoginName(userLoginName);
            breakdownState.setOperatorid(sysUser.getId());

            int breakdownCount = iBreakdownStateService.insert(breakdownState);
        }

        message.setKey("pass");

        String jsondata = JSON.toJSONString(message);
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(jsondata);

    }


    @RequestMapping("/ajaxGetItemInfo.do")
    public void ajaxGetItemInfo(@RequestBody BreakdownVO vo, HttpServletResponse response, HttpServletRequest request) throws IOException {
        Breakdown breakdown = iBreakdownService.selectByItemno(vo.getItemno());

        JsonMessage message = new JsonMessage();
        if(breakdown != null){
            BreakdownState breakdownState = iBreakdownStateService.selectMaxStateIdByBreakdownId(breakdown.getId());

            Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
            //转换成 breakdown
            BreakdownVO result = mapper.map(breakdown, BreakdownVO.class);

            result.setItemstateid(breakdownState.getItemstateid());
            message.setKey("pass");
            message.setData(result);
        }else{
            message.setKey("unpass");
        }

        String jsondata = JSON.toJSONString(message);
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(jsondata);
    }

    /**
     *
     * @param response
     * @param request
     * @throws IOException
     */
    @RequestMapping("/ajaxGetItemState.do")
    public void ajaxGetItemState(HttpServletResponse response, HttpServletRequest request) throws IOException {
        JsonMessage message = new JsonMessage();
        message.setData(itemStateList);
        String jsondata = JSON.toJSONString(message);
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(jsondata);
    }

    @RequestMapping("/ajaxGetItemName.do")
    public void ajaxGetItemName(HttpServletResponse response, HttpServletRequest request) throws IOException {
        JsonMessage message = new JsonMessage();
        message.setData(itemNameList);
        String jsondata = JSON.toJSONString(message);
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(jsondata);
    }

    @RequestMapping("/ajaxGetSourceId.do")
    public void ajaxGetSourceId( HttpServletResponse response, HttpServletRequest request) throws IOException {
        JsonMessage message = new JsonMessage();
        message.setData(sourceIdList);
        String jsondata = JSON.toJSONString(message);
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(jsondata);
    }

    @RequestMapping("/ajaxGetTypeId.do")
    public void ajaxGetTypeId( HttpServletResponse response, HttpServletRequest request) throws IOException {
        JsonMessage message = new JsonMessage();
        message.setData(breakdownTypeIdList);
        String jsondata = JSON.toJSONString(message);
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(jsondata);
    }

}
