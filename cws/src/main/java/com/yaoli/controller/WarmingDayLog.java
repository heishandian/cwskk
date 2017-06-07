package com.yaoli.controller;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.*;
import com.yaoli.service.IAreaService;
import com.yaoli.service.ISysUserService;
import com.yaoli.service.IWithoutElectricDataAbnormalService;
import com.yaoli.util.*;
import com.yaoli.util.excel.object.ExcelContentObject;
import com.yaoli.util.excel.SimpleExcelView;
import com.yaoli.vo.RequestParameterVO;
import com.yaoli.vo.WarmingDayLogVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by will on 2016/9/11.
 */
@Controller
@RequestMapping("/warmingdaylog")
public class WarmingDayLog {
    @Resource
    private IAreaService iAreaService;

    /**
     * 注意：为了简便，现在将 WarmingDayLogVO 放在与 WithoutElectricDataAbnormal 相关的service 和 xml中
     */
    @Resource
    private IWithoutElectricDataAbnormalService iWithoutElectricDataAbnormalService;

    @Resource
    private ISysUserService iSysUserService;

    @RequestMapping("/gotoWarmingDayLogSearch.do")
    public String gotoWarmingDayLogSearch(Model model){
        List<Area> areas = iAreaService.getAllAreas();
        model.addAttribute("allAreas",areas);
        return "/monitorManager/warmingDayLog";
    }

    @RequestMapping("/getWarmingDayLogSearch.do")
    public void getWarmingDayLogSearch(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String pageSize =String.valueOf(request.getParameter("rows"));
        String pageNum = String.valueOf(request.getParameter("page"));

        String sewageid = String.valueOf(request.getParameter("sewageid")).equals("")?null:String.valueOf(request.getParameter("sewageid"));
        String areaid = String.valueOf(request.getParameter("areaid")).equals("")?null:String.valueOf(request.getParameter("areaid"));
        String begintime = String.valueOf(request.getParameter("begintime")).equals("")?null:String.valueOf(request.getParameter("begintime"));
        String endtime = String.valueOf(request.getParameter("endtime")).equals("")?null:String.valueOf(request.getParameter("endtime"));
        String operationnum = String.valueOf(request.getParameter("operationnum")).equals("")?null:String.valueOf(request.getParameter("operationnum"));
        String sewageName = String.valueOf(request.getParameter("sewageName")).equals("")?null:String.valueOf(request.getParameter("sewageName"));
        String abnormaltype = String.valueOf(request.getParameter("abnormaltypeNO")).equals("")?null:String.valueOf(request.getParameter("abnormaltypeNO"));

        Map<String, String> map = new HashMap<String, String>();
        map.put("pageSize", pageSize);
        map.put("pageNum", pageNum);

        map.put("sewageid", sewageid == null ? null:sewageid.equals("-1") ? null : sewageid);
        map.put("areaid", areaid == null ? null:areaid.equals("-1") ? null : areaid);
        map.put("begintime", begintime);
        map.put("endtime", endtime);
        map.put("operationnum", operationnum);
        map.put("sewageName", sewageName);
        map.put("abnormaltype", abnormaltype);

        List<WarmingDayLogVO> vos  = iWithoutElectricDataAbnormalService.getWarmingDayLogsByCondition(map);

        int count = iWithoutElectricDataAbnormalService.getWarmingDayLogsCountByCondition(map);

        SysPagingUtil sysPagingUtil = new SysPagingUtil();
        sysPagingUtil.setTotal(String.valueOf(count));
        sysPagingUtil.setRows(vos);

        String jsondata = JSON.toJSONString(sysPagingUtil);

        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(jsondata);
    }

    @RequestMapping("/getWarmingDayLogExcel.do")
    public ModelAndView getWarmingDayLogExcel(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String abnormaltype = request.getParameter("abnormaltypeNO");
        String sewageid = String.valueOf(request.getParameter("sewageid")).equals("")?null:String.valueOf(request.getParameter("sewageid"));
        String areaid = String.valueOf(request.getParameter("areaid")).equals("")?null:String.valueOf(request.getParameter("areaid"));
        String begintime = String.valueOf(request.getParameter("begintime")).equals("")?null:String.valueOf(request.getParameter("begintime"));
        String endtime = String.valueOf(request.getParameter("endtime")).equals("")?null:String.valueOf(request.getParameter("endtime"));
        String operationnum = String.valueOf(request.getParameter("operationnum")).equals("")?null:String.valueOf(request.getParameter("operationnum"));
        String sewageName = String.valueOf(request.getParameter("sewageName")).equals("")?null:String.valueOf(request.getParameter("sewageName"));



        Map<String, String> map = new HashMap<String, String>();

        map.put("sewageid", sewageid == null ? null:sewageid.equals("-1") ? null : sewageid);
        map.put("areaid", areaid == null ? null:areaid.equals("-1") ? null : areaid);
        map.put("begintime", begintime);
        map.put("endtime", endtime);
        map.put("operationnum", operationnum);
        map.put("sewageName", sewageName);
        map.put("abnormaltype", abnormaltype);


        List<WarmingDayLogVO> vos  = iWithoutElectricDataAbnormalService.getWarmingDayLogsByConditionWithoutPaing(map);


        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("data", vos);

        Subject subject = SecurityUtils.getSubject();
        //设置登录的人
        SysUser sysUser = iSysUserService.getUserByLoginName((String)subject.getPrincipal());
        parameter.put("username", sysUser.getUsername());
        //设置制表时间
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        parameter.put("time", time);
        //设置报表名称
        parameter.put("reportname", "报警日志查询");

        return new ModelAndView(new WarmingDayLogExcelView(), parameter);
    }

    @RequestMapping("/gotoWarmingDayLogReport.do")
    public String gotoWarmingDayLogReport(HttpServletRequest request, HttpServletResponse response,Model model) throws ParseException {
        List<Area> areas = iAreaService.getAllAreas();
        model.addAttribute("allAreas",areas);
        return "/report/warmingDayLogReport";
    }

    @RequestMapping("/getWarmingDayLogReport.do")
    public void getWarmingDayLogReport(HttpServletRequest request, HttpServletResponse response,Model model) throws ParseException, IOException,Exception {
        String pageSize =String.valueOf(request.getParameter("rows"));
        String pageNum = String.valueOf(request.getParameter("page"));

        RequestParameterVO vo = (RequestParameterVO)(new RequestParameterParse(RequestParameterVO.class,request).getParameterMap());

        String testingtime = SewageVOUtils.isBlank(request.getParameter("testingtime")) ? null:request.getParameter("testingtime");
        String sewageid = String.valueOf(request.getParameter("sewageid")).equals("")?null:String.valueOf(request.getParameter("sewageid"));
        String areaid = String.valueOf(request.getParameter("areaid")).equals("")?null:String.valueOf(request.getParameter("areaid"));
        String reporttype = String.valueOf(request.getParameter("reporttype")).equals("")?null:String.valueOf(request.getParameter("reporttype"));

        Map<String, String> map = new HashMap<String, String>();
        map.put("pageSize", pageSize);
        map.put("pageNum", pageNum);
        map.put("testingtime", testingtime);
        map.put("sewageid", sewageid);
        map.put("areaid", areaid);
        map.put("reporttype", reporttype);

        List<WarmingDayLogVO> sarmingDayLogVO = iWithoutElectricDataAbnormalService.getWarmingLogReportByCondition(map);
        int count = iWithoutElectricDataAbnormalService.getWarmingLogReportCountByCondition(map);

        SysPagingUtil sysPagingUtil = new SysPagingUtil();
        sysPagingUtil.setTotal(String.valueOf(count));
        sysPagingUtil.setRows(sarmingDayLogVO);
        String jsondata = JSON.toJSONString(sysPagingUtil);
        response.setContentType("html/json;charset=UTF-8");
        response.getWriter().write(jsondata);
    }

    @RequestMapping("/getWarmingDayLogReportExcel.do")
    public ModelAndView getWarmingDayLogReportExcel(HttpServletRequest request, HttpServletResponse response,Model model) throws ParseException, IOException {
        String pageSize =String.valueOf(request.getParameter("rows"));
        String pageNum = String.valueOf(request.getParameter("page"));

        String testingtime = SewageVOUtils.isBlank(request.getParameter("testingtime")) ? null:request.getParameter("testingtime");
        String sewageid = String.valueOf(request.getParameter("sewageid")).equals("")?null:String.valueOf(request.getParameter("sewageid"));
        String areaid = String.valueOf(request.getParameter("areaid")).equals("")?null:String.valueOf(request.getParameter("areaid"));
        String reporttype = String.valueOf(request.getParameter("reporttype")).equals("")?null:String.valueOf(request.getParameter("reporttype"));

        Map<String, String> map = new HashMap<String, String>();
        map.put("pageSize", pageSize);
        map.put("pageNum", pageNum);
        map.put("testingtime", testingtime);
        map.put("sewageid", sewageid);
        map.put("areaid", areaid);
        map.put("reporttype", reporttype);

        List<WarmingDayLogVO> sarmingDayLogVO = iWithoutElectricDataAbnormalService.getWarmingLogReportByConditionWithOutPaging(map);


        ExcelContentObject content = new ExcelContentObject();

        //登录人
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = iSysUserService.getUserByLoginName((String)subject.getPrincipal());

        content.setAuthor(sysUser.getUsername());

        //设置制表时间
        content.setMakeTableTime(new DateTime());

        //设置header
        content.setHeader("报警日志报表");

        //设置第一列标题
        content.setMainTitle("污水站点名称");

        //设置第二列 后面的标题
        ArrayList<String> titles = new ArrayList<String>();
        titles.add("运营编号");
        titles.add("水质异常次数");
        titles.add("断电断线次数");
        titles.add("曝气机故障次数");
        titles.add("水泵故障次数");
        content.setTitles(titles);

        //填充第一列数据
        ArrayList<String> mainData = new ArrayList<String>();
        ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();

        for(WarmingDayLogVO vo : sarmingDayLogVO){
            mainData.add(vo.getSewageName());
            ArrayList<String> data = new ArrayList<String>();

            //运营编号
            data.add(vo.getOperationnum());

            //水质异常次数
            data.add(String.valueOf(vo.getDetection_count()));

            //断电断线次数
            data.add(String.valueOf(vo.getWithoutElectric_count()));

            //曝气机次数
            data.add(String.valueOf(vo.getEquip1_count()));

            //水泵次数
            data.add(String.valueOf(vo.getEquip25_count()));

            datas.add(data);

        }
        content.setMainData(mainData);
        content.setData(datas);

        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("view", content);

        //return new ModelAndView(new WarmingLogStatisticExcelView(), parameter);
        return new ModelAndView(new SimpleExcelView(), parameter);
    }
}
