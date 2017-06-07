package com.yaoli.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yaoli.config.SystemConfig;
import com.yaoli.config.siteFunction.ImplDetect_1_to_5;
import com.yaoli.config.siteFunction.ImplDetect_1_to_5_10_to_15;
import com.yaoli.config.siteFunction.ImplEquip_1_to_5;
import com.yaoli.config.siteFunction.ImplEquip_6_to_21;
import com.yaoli.util.excel.ComplexExcelView;
import com.yaoli.util.excel.SimpleExcelView;
import com.yaoli.util.excel.object.ExcelContentObject;
import com.yaoli.util.excel.object.SubExcelContentObject;
import com.yaoli.vo.ReportVO;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.Area;
import com.yaoli.beans.Sewage;
import com.yaoli.beans.SysUser;
import com.yaoli.service.IAreaService;
import com.yaoli.service.ISewageService;
import com.yaoli.service.IStatisticDayService;
import com.yaoli.service.ISysUserService;
import com.yaoli.util.ExcelView;
import com.yaoli.util.SewageVOUtils;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.StatisticDayVO;
import com.yaoli.vo.TableTitleVO;

@Controller
@RequestMapping("/report")
/*
 * 非常重要
* iStatisticDayService.getStatisticMonthVO(map);
* iStatisticDayService.getStatisticMonthVOByCondition(map);
* 
* 这两个方法用的同一个sql，
* 其中第一个方法 没有分页，用于 设备运行记录 和 水质检测记录，查找某个区县下面所有的站点，返回多条记录
* 	      第二个方法 用于站点运行记录，查找单个站点记录
*/
public class ReportController {
	private static Logger logger = Logger.getLogger(ReportController.class);
	@Resource
	private IAreaService iAreaService;
	
	@Resource
	private IStatisticDayService iStatisticDayService;
	
	@Resource
	private ISewageService iSewageService;
	
	@Resource
	private ISysUserService iSysUserService;
	
	@RequestMapping("/gotoquxiandayreport.do")
	public String gotoquxiandayReport(Model model){
		List<Area> areas = iAreaService.getAllAreas();
		model.addAttribute("allAreas", areas);
		return "/report/quxiandayreport";
	}
	
	@RequestMapping("/gotoquxianmonthreport.do")
	public String gotoquxianMonthReport(Model model){
		List<Area> areas = iAreaService.getAllAreas();
		model.addAttribute("allAreas", areas);
		return "/report/quxianmonthreport";
	}
	
	@RequestMapping("/gotoquxianyearreport.do")
	public String gotoquxianYearReport(Model model){
		List<Area> areas = iAreaService.getAllAreas();
		model.addAttribute("allAreas", areas);
		return "/report/quxianyearreport";
	}
	
	@RequestMapping("/getxtoken.do")
	public void getXtoken(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//Map<String, String> map = CustomPropertyConfigurer.getProperties();
		
		//String systemno = map.get("systemno");
		List<List<TableTitleVO>> lists = new ArrayList<List<TableTitleVO>>();
		//if(systemno.equals("1")){
		if(SystemConfig.site instanceof ImplEquip_6_to_21 && SystemConfig.site instanceof  ImplDetect_1_to_5){
			List<TableTitleVO> tableTitleVOsTitle = new ArrayList<TableTitleVO>();
/*			TableTitleVO tableTitleTestingTime = new TableTitleVO();
			tableTitleTestingTime.setField("sewagename");
			tableTitleTestingTime.setTitle("污水站点");
			tableTitleTestingTime.setRowspan(2);
			tableTitleVOsTitle.add(tableTitleTestingTime);*/
			
			TableTitleVO tableTitleVO;
			for (int i = 8; i <= 21; i++) {
				tableTitleVO = new TableTitleVO();
				String key = "equipment"+i+"name";
				String value =
						SystemConfig.equips.get(key);
				tableTitleVO.setField(key);
				tableTitleVO.setTitle(value);
				tableTitleVO.setColspan(2);
				tableTitleVOsTitle.add(tableTitleVO);
			}
			
			
			
			//运行时间，故障时间
			List<TableTitleVO> tableTitleVOsProperties = new ArrayList<TableTitleVO>();
			TableTitleVO tableTitleVOproperty;
			for (int i = 8; i <= 21; i++) {
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("equip"+i+"normaltime");
				tableTitleVOproperty.setTitle("运行时间");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOproperty.setWidth(75);
				tableTitleVOsProperties.add(tableTitleVOproperty);
				
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("equip"+i+"abnormaltime");
				tableTitleVOproperty.setTitle("故障时间");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOproperty.setWidth(75);
				tableTitleVOsProperties.add(tableTitleVOproperty);
			}
			
			//--------------以上是设备统计，下面开始水质--------------------------
			for (int i = 1; i <= 5; i++) {
				if(i != 4){
					tableTitleVO = new TableTitleVO();
					String key = "detection"+i+"name";
					String value = SystemConfig.detections.get(key);
					tableTitleVO.setField(key);
					tableTitleVO.setTitle(value);
					tableTitleVO.setColspan(3);
					tableTitleVOsTitle.add(tableTitleVO);
				}
			}
/*			tableTitleVO = new TableTitleVO();
			tableTitleVO.setField("temp");
			tableTitleVO.setTitle("&nbsp;&nbsp;");
			tableTitleVO.setRowspan(2);
			tableTitleVOsTitle.add(tableTitleVO);*/
			
			for (int i = 1; i <= 5; i++) {
				if(i != 4){
					tableTitleVOproperty = new TableTitleVO();
					tableTitleVOproperty.setField("detection"+i+"max");
					tableTitleVOproperty.setTitle("最大");
					tableTitleVOproperty.setRowspan(1);
					tableTitleVOproperty.setWidth(75);
					//tableTitleVOproperty.setFormatter("function(value,row,index){if(typeof(row.dection"+i+"max)!='undefined'){return dection"+i+"max.toFixed(2);}}");
					tableTitleVOsProperties.add(tableTitleVOproperty);
					
					tableTitleVOproperty = new TableTitleVO();
					tableTitleVOproperty.setField("detection"+i+"min");
					tableTitleVOproperty.setTitle("最小");
					tableTitleVOproperty.setRowspan(1);
					tableTitleVOproperty.setWidth(75);
					tableTitleVOsProperties.add(tableTitleVOproperty);
					
					tableTitleVOproperty = new TableTitleVO();
					tableTitleVOproperty.setField("detection"+i+"avg");
					tableTitleVOproperty.setTitle("平均");
					tableTitleVOproperty.setRowspan(1);
					tableTitleVOproperty.setWidth(75);
					tableTitleVOsProperties.add(tableTitleVOproperty);	
				}
			}
			
			lists.add(tableTitleVOsTitle);
			lists.add(tableTitleVOsProperties);
			
			
			//下面添加水质
		}
		
		
		//0表示 是老系统
		//if(systemno.equals("0")){
		if(SystemConfig.site instanceof ImplEquip_1_to_5 && SystemConfig.site instanceof ImplDetect_1_to_5){

			List<TableTitleVO> tableTitleVOsTitle = new ArrayList<TableTitleVO>();
/*			TableTitleVO tableTitleTestingTime = new TableTitleVO();
			tableTitleTestingTime.setField("sewagename");
			tableTitleTestingTime.setTitle("污水站点");
			tableTitleTestingTime.setRowspan(2);
			tableTitleVOsTitle.add(tableTitleTestingTime);*/
			
			TableTitleVO tableTitleVO;
			for (int i = 1; i <= 4; i++) {
				tableTitleVO = new TableTitleVO();
				String key = "equipment"+i+"name";
				String value = SystemConfig.equips.get(key);
				tableTitleVO.setField(key);
				tableTitleVO.setTitle(value);
				tableTitleVO.setColspan(2);
				tableTitleVOsTitle.add(tableTitleVO);
			}
			
			
			
			//运行时间，故障时间
			List<TableTitleVO> tableTitleVOsProperties = new ArrayList<TableTitleVO>();
			TableTitleVO tableTitleVOproperty;
			for (int i = 1; i <= 4; i++) {
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("equip"+i+"normaltime");
				tableTitleVOproperty.setTitle("运行时间");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOproperty.setWidth(75);
				tableTitleVOsProperties.add(tableTitleVOproperty);
				
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("equip"+i+"abnormaltime");
				tableTitleVOproperty.setTitle("故障时间");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOproperty.setWidth(75);
				tableTitleVOsProperties.add(tableTitleVOproperty);
			}
			
			//--------------以上是设备统计，下面开始水质--------------------------
			for (int i = 1; i <= 5; i++) {
				tableTitleVO = new TableTitleVO();
				String key = "detection"+i+"name";
				String value = SystemConfig.detections.get(key);
				tableTitleVO.setField(key);
				tableTitleVO.setTitle(value);
				tableTitleVO.setColspan(3);
				tableTitleVOsTitle.add(tableTitleVO);
			}
/*			tableTitleVO = new TableTitleVO();
			tableTitleVO.setField("temp");
			tableTitleVO.setTitle("&nbsp;&nbsp;");
			tableTitleVO.setRowspan(2);
			tableTitleVOsTitle.add(tableTitleVO);*/
			
			for (int i = 1; i <= 5; i++) {
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("detection"+i+"max");
				tableTitleVOproperty.setTitle("最大");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOproperty.setWidth(75);
				//tableTitleVOproperty.setFormatter("function(value,row,index){if(typeof(row.dection"+i+"max)!='undefined'){return dection"+i+"max.toFixed(2);}}");
				tableTitleVOsProperties.add(tableTitleVOproperty);
				
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("detection"+i+"min");
				tableTitleVOproperty.setTitle("最小");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOproperty.setWidth(75);
				tableTitleVOsProperties.add(tableTitleVOproperty);
				
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("detection"+i+"avg");
				tableTitleVOproperty.setTitle("平均");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOproperty.setWidth(75);
				tableTitleVOsProperties.add(tableTitleVOproperty);
			}

			
			lists.add(tableTitleVOsTitle);
			lists.add(tableTitleVOsProperties);
			
			
			//下面添加水质
		}
		String jsons = JSON.toJSONString(lists);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(jsons);
	}
	
	/**
	 * 获取区县的日统计
	 * @param response
	 * @param request
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@RequestMapping("/getquxianstatisticbycondition.do")
	public void getQuxianStatisticByCondition(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		
		Integer areaid = request.getParameter("areaid").equals("")?null:Integer.valueOf(request.getParameter("areaid"));
		
		
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		
		Date testingtime = request.getParameter("testingtime").equals("")?null:formater.parse(request.getParameter("testingtime"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("areaid", areaid==null?null:String.valueOf(areaid));
		map.put("testingtime", formater.format(testingtime));
		
	
		
		List<StatisticDayVO> statisticDayVOs = null;
		int count = 0;
		
		statisticDayVOs = iStatisticDayService.getStatisticDayVOByCondition(map);
		count = iStatisticDayService.getStatisticDayTotalByCondition(map);
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(statisticDayVOs);
		String jsondata = JSON.toJSONString(sysPagingUtil);
		response.setContentType("html/json;charset=UTF-8");
		logger.debug(jsondata);
		response.getWriter().write(jsondata);
	}
	
	/**
	 * 导出日统计数据
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/getexcelexportbyday.do")
	public ModelAndView getExcelExportByDay(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		Integer areaid = request.getParameter("areaid").equals("")?null:Integer.valueOf(request.getParameter("areaid"));
		
		//excel类型 1表示设备运行记录 2表示水质检测记
		String exceltype = request.getParameter("exceltype");
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date testingtime = request.getParameter("testingtime").equals("")?null:formater.parse(request.getParameter("testingtime"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("areaid", areaid==null?null:String.valueOf(areaid));
		map.put("testingtime", formater.format(testingtime));
		
		List<StatisticDayVO> statisticDayVOs = null;
		int count = 0;
		
		statisticDayVOs = iStatisticDayService.getStatisticDayVO(map);
		count = iStatisticDayService.getStatisticDayTotal(map);


		//填充数据
		SubExcelContentObject content = new SubExcelContentObject();

		//登录人
		Subject subject = SecurityUtils.getSubject();
		SysUser sysUser = iSysUserService.getUserByLoginName((String)subject.getPrincipal());

		content.setAuthor(sysUser.getUsername());

		//设置制表时间
		content.setMakeTableTime(new DateTime());


		//设置地区
		Area area = iAreaService.selectByPrimaryKey(areaid);

		//设置header
		content.setHeader(area.getName()+"设备运行报表");

		//设置第一列标题
		content.setMainTitle("污水站点");

		//设置父标题
		ArrayList<String> titles = new ArrayList<String>();
		titles.add("曝气机");
		titles.add("污水泵");
		titles.add("回流泵");
		titles.add("排水泵");
		titles.add("加药泵");
		content.setTitles(titles);

		//设置子标题
		ArrayList<String> subTitles = new ArrayList<String>();
		subTitles.add("运行时间");
		subTitles.add("故障时间");
		content.setSubTitles(subTitles);

		//填充第一列数据
		ArrayList<String> mainData = new ArrayList<String>();
		ArrayList<ArrayList<ArrayList<String>>> subData = new ArrayList<ArrayList<ArrayList<String>>>();


		//垃圾代码多
		ArrayList<ArrayList<String>> equip1 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> equip2 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> equip3 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> equip4 = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> equip5 = new ArrayList<ArrayList<String>>();

		ArrayList<String> equip1_runTime = new ArrayList<String>();
		ArrayList<String> equip1_stopTime = new ArrayList<String>();
		ArrayList<String> equip2_runTime = new ArrayList<String>();
		ArrayList<String> equip2_stopTime = new ArrayList<String>();
		ArrayList<String> equip3_runTime = new ArrayList<String>();
		ArrayList<String> equip3_stopTime = new ArrayList<String>();
		ArrayList<String> equip4_runTime = new ArrayList<String>();
		ArrayList<String> equip4_stopTime = new ArrayList<String>();
		ArrayList<String> equip5_runTime = new ArrayList<String>();
		ArrayList<String> equip5_stopTime = new ArrayList<String>();

		for(StatisticDayVO vo : statisticDayVOs){
			//站点名称
			mainData.add(vo.getSewagename());

			equip1_runTime.add(vo.getEquip1normaltime() == null ? "0":vo.getEquip1normaltime().toString());
			equip1_stopTime.add(vo.getEquip1normaltime() == null ? "0":vo.getEquip1normaltime().toString());

			equip2_runTime.add(vo.getEquip2normaltime() == null ? "0":vo.getEquip2normaltime().toString());
			equip2_stopTime.add(vo.getEquip2normaltime() == null ? "0":vo.getEquip2normaltime().toString());

			equip3_runTime.add(vo.getEquip3normaltime() == null ? "0":vo.getEquip3normaltime().toString());
			equip3_stopTime.add(vo.getEquip3normaltime()== null ? "0":vo.getEquip3normaltime().toString());

			equip4_runTime.add(vo.getEquip4normaltime() == null ? "0":vo.getEquip4normaltime().toString());
			equip4_stopTime.add(vo.getEquip4normaltime()== null ? "0":vo.getEquip4normaltime().toString());

			equip5_runTime.add(vo.getEquip4normaltime() == null ? "0":vo.getEquip5normaltime().toString());
			equip5_stopTime.add(vo.getEquip4normaltime() == null ? "0":vo.getEquip5normaltime().toString());

		}

		equip1.add(equip1_runTime);
		equip1.add(equip1_stopTime);

		equip2.add(equip2_runTime);
		equip2.add(equip2_stopTime);

		equip3.add(equip3_runTime);
		equip3.add(equip3_stopTime);

		equip4.add(equip4_runTime);
		equip4.add(equip4_stopTime);

		equip5.add(equip5_runTime);
		equip5.add(equip5_stopTime);

		subData.add(equip1);
		subData.add(equip2);
		subData.add(equip3);
		subData.add(equip4);
		subData.add(equip5);

		content.setMainData(mainData);
		content.setSubData(subData);

		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("view", content);












//		Map<String, Object> parameter = new HashMap<String, Object>();
//		parameter.put("statisticDayVOs", statisticDayVOs);
//		parameter.put("count", count);
//		parameter.put("exceltype", exceltype);










		
//		//设置地区
//		Area area = iAreaService.selectByPrimaryKey(areaid);
//		parameter.put("areaname", area.getName());
//		Subject subject = SecurityUtils.getSubject();
//		//设置登录的人
//		SysUser sysUser = iSysUserService.getUserByLoginName((String)subject.getPrincipal());
//		parameter.put("username", sysUser.getUsername());
//		//设置制表时间
//		Date date=new Date();
//		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String time=format.format(date);
//		parameter.put("time", time);
//		//设置报表名称
//		parameter.put("reportname", "设备运行日报");
		
		return new ModelAndView(new ComplexExcelView(), parameter);
	}
	/**
	 * 获取区县的月统计
	 * @param response
	 * @param request
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@RequestMapping("/getquxianstatisticmonthbycondition.do")
	public void getQuxianStatisticMonthByCondition(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		
		Integer areaid = request.getParameter("areaid").equals("")?null:Integer.valueOf(request.getParameter("areaid"));
		
		String testingtime = request.getParameter("testingtime").equals("")?null:request.getParameter("testingtime");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("areaid", areaid==null?null:String.valueOf(areaid));
		map.put("testingtime", testingtime);
		
		List<StatisticDayVO> statisticDayVOs = null;
		int count = 0;
		
		statisticDayVOs = iStatisticDayService.getStatisticMonthVOByCondition(map);
		count = iStatisticDayService.getStatisticMonthTotalByCondition(map);
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(statisticDayVOs);
		String jsondata = JSON.toJSONString(sysPagingUtil);
		response.setContentType("html/json;charset=UTF-8");
		logger.debug(jsondata);
		response.getWriter().write(jsondata);
	}
	
	/**
	 * 导出区县所有的月统计数据
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/getexcelexportbymonth.do")
	public ModelAndView getExcelExportByMonth(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		Integer areaid = request.getParameter("areaid").equals("")?null:Integer.valueOf(request.getParameter("areaid"));
		String testingtime = request.getParameter("testingtime").equals("")?null:request.getParameter("testingtime");

		//excel类型 1表示设备运行记录 2表示水质检测记
		String exceltype = request.getParameter("exceltype");
		Map<String, String> map = new HashMap<String, String>();
		map.put("areaid", areaid==null?null:String.valueOf(areaid));
		map.put("testingtime", testingtime);
		
		List<StatisticDayVO> statisticDayVOs = null;
		
		statisticDayVOs = iStatisticDayService.getStatisticMonthVO(map);
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("statisticDayVOs", statisticDayVOs);
		parameter.put("exceltype", exceltype);
		
		//设置地区
		Area area = iAreaService.selectByPrimaryKey(areaid);
		parameter.put("areaname", area.getName());
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
		parameter.put("reportname", "设备运行日报");
		
		return new ModelAndView(new ExcelView(), parameter); 
	}
	
	/**
	 * 获取区县的年统计
	 * @param response
	 * @param request
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@RequestMapping("/getquxianstatisticyearbycondition.do")
	public void getQuxianStatisticYearByCondition(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		
		Integer areaid = request.getParameter("areaid").equals("")?null:Integer.valueOf(request.getParameter("areaid"));
		
		String testingtime = request.getParameter("testingtime").equals("")?null:request.getParameter("testingtime");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("areaid", areaid==null?null:String.valueOf(areaid));
		map.put("testingtime", testingtime);
		
		List<StatisticDayVO> statisticDayVOs = null;
		int count = 0;
		
		statisticDayVOs = iStatisticDayService.getStatisticYearVOByCondition(map);
		count = iStatisticDayService.getStatisticYearTotalByCondition(map);
		
		//equip6normaltime equip6abnormaltime
		//equip6normaltime
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(statisticDayVOs);
		String jsondata = JSON.toJSONString(sysPagingUtil);
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	/**
	 * 导出区县所有的年统计数据
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/getexcelexportbyyear.do")
	public ModelAndView getExcelExportByYear(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		Integer areaid = request.getParameter("areaid").equals("")?null:Integer.valueOf(request.getParameter("areaid"));
		String testingtime = request.getParameter("testingtime").equals("")?null:request.getParameter("testingtime");
		
		//excel类型 1表示设备运行记录 2表示水质检测记
		String exceltype = request.getParameter("exceltype");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("areaid", areaid==null?null:String.valueOf(areaid));
		map.put("testingtime", testingtime);
		
		List<StatisticDayVO> statisticDayVOs = null;
		
		statisticDayVOs = iStatisticDayService.getStatisticYearVO(map);
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("statisticDayVOs", statisticDayVOs);
		parameter.put("exceltype", exceltype);
		
		//设置地区
		Area area = iAreaService.selectByPrimaryKey(areaid);
		parameter.put("areaname", area.getName());
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
		parameter.put("reportname", "设备运行日报");
		
		return new ModelAndView(new ExcelView(), parameter); 
	}
	
	@RequestMapping("/gotosewagewaterdayreport.do")
	public String gotosewagewaterdayreport(){
		return "/report/sewagewaterdayreport";
	}
	
	@RequestMapping("/gotosewagewatermonthreport.do")
	public String gotosewagewatermonthreport(){
		return "/report/sewagewatermonthreport";
	}
	
	@RequestMapping("/gotosewagewateryearreport.do")
	public String gotosewagewateryearreport(){
		return "/report/sewagewateryearreport";
	}
	
	/**
	 * 污水处理日报
	 * @param response
	 * @param request
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("/sewagewaterdayreport.do")
	public void sewageWaterdayReport(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		
		String testingtime = SewageVOUtils.isBlank(request.getParameter("testingtime")) ? null:request.getParameter("testingtime");
		String sewageid = String.valueOf(request.getParameter("sewageid")).equals("")?null:String.valueOf(request.getParameter("sewageid"));
		String areaid = String.valueOf(request.getParameter("areaid")).equals("")?null:String.valueOf(request.getParameter("areaid"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("testingtime", testingtime);
		map.put("sewageid", sewageid);
		map.put("areaid", areaid);
		
		List<Sewage> sewageList = null;
		int count = 0;

		sewageList = iSewageService.sewageWaterdayReport(map);
		count = iSewageService.sewageWaterdayCount(map);

		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(sewageList);
		String jsondata = JSON.toJSONString(sysPagingUtil);
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	@RequestMapping("/sewagewatermonthreport.do")
	public void sewageWatermonthReport(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		
		String testingtime = SewageVOUtils.isBlank(request.getParameter("testingtime")) ? null:request.getParameter("testingtime");
		String sewageid = String.valueOf(request.getParameter("sewageid")).equals("")?null:String.valueOf(request.getParameter("sewageid"));
		String areaid = String.valueOf(request.getParameter("areaid")).equals("")?null:String.valueOf(request.getParameter("areaid"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("testingtime", testingtime);
		map.put("sewageid", sewageid);
		map.put("areaid", areaid);
		
		List<Sewage> sewageList = null;
		int count = 0;
		
		sewageList = iSewageService.sewageWatermonthReport(map);
		count = iSewageService.sewageWatermonthCount(map);

		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(sewageList);
		String jsondata = JSON.toJSONString(sysPagingUtil);
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	
	@RequestMapping("/sewagewateryearreport.do")
	public void sewageWateryearReport(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		
		String testingtime = SewageVOUtils.isBlank(request.getParameter("testingtime")) ? null:request.getParameter("testingtime");
		String sewageid = String.valueOf(request.getParameter("sewageid")).equals("")?null:String.valueOf(request.getParameter("sewageid"));
		String areaid = String.valueOf(request.getParameter("areaid")).equals("")?null:String.valueOf(request.getParameter("areaid"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("testingtime", testingtime);
		map.put("sewageid", sewageid);
		map.put("areaid", areaid);
		
		List<Sewage> sewageList = null;
		int count = 0;
		
		sewageList = iSewageService.sewageWateryearReport(map);
		count = iSewageService.sewageWateryearCount(map);

		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(sewageList);
		String jsondata = JSON.toJSONString(sysPagingUtil);
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	@RequestMapping("/gotorundatareport.do")
	public String gotorundatareport(Model model){
		List<Area> areas = iAreaService.getAllAreas();
		model.addAttribute("allAreas", areas);
		return "/report/rundatareport";
	}
	
	@RequestMapping("/gotowaterdatareport.do")
	public String gotowaterdatareport(Model model){
		List<Area> areas = iAreaService.getAllAreas();
		model.addAttribute("allAreas", areas);
		return "/report/waterdatareport";
	}
	
	/**
	 * 设备运行记录 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getrundataXtoken.do")
	public void getrundataXtoken(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//Map<String, String> map = CustomPropertyConfigurer.getProperties();
		
		//String systemno = map.get("systemno");
		List<List<TableTitleVO>> lists = new ArrayList<List<TableTitleVO>>();
		//江都系统
		//if(systemno.equals("1")){
		if(SystemConfig.site instanceof ImplEquip_6_to_21 && SystemConfig.site instanceof  ImplDetect_1_to_5){
			List<TableTitleVO> tableTitleVOsTitle = new ArrayList<TableTitleVO>();
/*			TableTitleVO tableTitleTestingTime = new TableTitleVO();
			tableTitleTestingTime.setField("sewagename");
			tableTitleTestingTime.setTitle("污水站点");
			tableTitleTestingTime.setRowspan(2);
			tableTitleVOsTitle.add(tableTitleTestingTime);*/
			
			TableTitleVO tableTitleVO;
			for (int i = 8; i <= 21; i++) {
				tableTitleVO = new TableTitleVO();
				String key = "equipment"+i+"name";
				String value = SystemConfig.equips.get(key);
				tableTitleVO.setField(key);
				tableTitleVO.setTitle(value);
				tableTitleVO.setColspan(2);
				tableTitleVOsTitle.add(tableTitleVO);
			}
			
			
			
			//运行时间，故障时间
			List<TableTitleVO> tableTitleVOsProperties = new ArrayList<TableTitleVO>();
			TableTitleVO tableTitleVOproperty;
			for (int i = 8; i <= 21; i++) {
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("equip"+i+"normaltime");
				tableTitleVOproperty.setTitle("运行时间");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOproperty.setWidth(75);
				tableTitleVOsProperties.add(tableTitleVOproperty);
				
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("equip"+i+"abnormaltime");
				tableTitleVOproperty.setTitle("故障时间");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOproperty.setWidth(75);
				tableTitleVOsProperties.add(tableTitleVOproperty);
			}
			
			//--------------以上是设备统计，下面开始水质--------------------------
			for (int i = 1; i <= 5; i++) {
				if(i != 4){
					tableTitleVO = new TableTitleVO();
					String key = "detection"+i+"name";
					String value = SystemConfig.detections.get(key);
					tableTitleVO.setField(key);
					tableTitleVO.setTitle(value);
					tableTitleVO.setColspan(3);
					tableTitleVOsTitle.add(tableTitleVO);
				}
			}
/*			tableTitleVO = new TableTitleVO();
			tableTitleVO.setField("temp");
			tableTitleVO.setTitle("&nbsp;&nbsp;");
			tableTitleVO.setRowspan(2);
			tableTitleVOsTitle.add(tableTitleVO);*/
			
			for (int i = 1; i <= 5; i++) {
				if(i != 4){
					tableTitleVOproperty = new TableTitleVO();
					tableTitleVOproperty.setField("detection"+i+"max");
					tableTitleVOproperty.setTitle("最大");
					tableTitleVOproperty.setRowspan(1);
					tableTitleVOproperty.setWidth(75);
					//tableTitleVOproperty.setFormatter("function(value,row,index){if(typeof(row.dection"+i+"max)!='undefined'){return dection"+i+"max.toFixed(2);}}");
					tableTitleVOsProperties.add(tableTitleVOproperty);
					
					tableTitleVOproperty = new TableTitleVO();
					tableTitleVOproperty.setField("detection"+i+"min");
					tableTitleVOproperty.setTitle("最小");
					tableTitleVOproperty.setRowspan(1);
					tableTitleVOproperty.setWidth(75);
					tableTitleVOsProperties.add(tableTitleVOproperty);
					
					tableTitleVOproperty = new TableTitleVO();
					tableTitleVOproperty.setField("detection"+i+"avg");
					tableTitleVOproperty.setTitle("平均");
					tableTitleVOproperty.setRowspan(1);
					tableTitleVOproperty.setWidth(75);
					tableTitleVOsProperties.add(tableTitleVOproperty);	
				}
			}
			
			lists.add(tableTitleVOsTitle);
			lists.add(tableTitleVOsProperties);
			
			
			//下面添加水质
		}
		
		
		//0表示 是老系统 2表示面源系统
		//if(systemno.equals("0") || systemno.equals("2")){
		if(SystemConfig.site instanceof  ImplDetect_1_to_5){
			List<TableTitleVO> tableTitleVOsTitle = new ArrayList<TableTitleVO>();
/*			TableTitleVO tableTitleTestingTime = new TableTitleVO();
			tableTitleTestingTime.setField("sewagename");
			tableTitleTestingTime.setTitle("污水站点");
			tableTitleTestingTime.setRowspan(2);
			tableTitleVOsTitle.add(tableTitleTestingTime);*/
			
			TableTitleVO tableTitleVO;
			for (int i = 1; i <= 5; i++) {
				tableTitleVO = new TableTitleVO();
				String key = "equipment"+i+"name";
				String value = SystemConfig.equips.get(key);
				tableTitleVO.setField(key);
				tableTitleVO.setTitle(value);
				tableTitleVO.setColspan(2);
				tableTitleVOsTitle.add(tableTitleVO);
			}
			
			
			
			//运行时间，故障时间
			List<TableTitleVO> tableTitleVOsProperties = new ArrayList<TableTitleVO>();
			TableTitleVO tableTitleVOproperty;
			for (int i = 1; i <= 5; i++) {
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("equip"+i+"normaltime");
				tableTitleVOproperty.setTitle("运行时间");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOproperty.setWidth(75);
				tableTitleVOsProperties.add(tableTitleVOproperty);
				
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("equip"+i+"abnormaltime");
				tableTitleVOproperty.setTitle("故障时间");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOproperty.setWidth(75);
				tableTitleVOsProperties.add(tableTitleVOproperty);
			}
			
			//--------------以上是设备统计，下面开始水质-------------------------

			
			lists.add(tableTitleVOsTitle);
			lists.add(tableTitleVOsProperties);
			
			
			//下面添加水质
		}
		String jsons = JSON.toJSONString(lists);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(jsons);
	}
	
	/**
	 * 水质检测记录 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getwaterdataXtoken.do")
	public void getwaterdataXtoken(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//Map<String, String> map = CustomPropertyConfigurer.getProperties();
		
		//String systemno = map.get("systemno");
		List<List<TableTitleVO>> lists = new ArrayList<List<TableTitleVO>>();
		//江都系统
		//if(systemno.equals("1")){
		if(SystemConfig.site instanceof ImplEquip_6_to_21 && SystemConfig.site instanceof  ImplDetect_1_to_5){
			List<TableTitleVO> tableTitleVOsTitle = new ArrayList<TableTitleVO>();
			
			TableTitleVO tableTitleVO;
			for (int i = 8; i <= 21; i++) {
				tableTitleVO = new TableTitleVO();
				String key = "equipment"+i+"name";
				String value = SystemConfig.equips.get(key);
				tableTitleVO.setField(key);
				tableTitleVO.setTitle(value);
				tableTitleVO.setColspan(2);
				tableTitleVOsTitle.add(tableTitleVO);
			}
			
			
			
			//运行时间，故障时间
			List<TableTitleVO> tableTitleVOsProperties = new ArrayList<TableTitleVO>();
			TableTitleVO tableTitleVOproperty;
			for (int i = 8; i <= 21; i++) {
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("equip"+i+"normaltime");
				tableTitleVOproperty.setTitle("运行时间");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOproperty.setWidth(75);
				tableTitleVOsProperties.add(tableTitleVOproperty);
				
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("equip"+i+"abnormaltime");
				tableTitleVOproperty.setTitle("故障时间");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOproperty.setWidth(75);
				tableTitleVOsProperties.add(tableTitleVOproperty);
			}
			
			//--------------以上是设备统计，下面开始水质--------------------------
			for (int i = 1; i <= 5; i++) {
				if(i != 4){
					tableTitleVO = new TableTitleVO();
					String key = "detection"+i+"name";
					String value = SystemConfig.detections.get(key);
					tableTitleVO.setField(key);
					tableTitleVO.setTitle(value);
					tableTitleVO.setColspan(3);
					tableTitleVOsTitle.add(tableTitleVO);
				}
			}
/*			tableTitleVO = new TableTitleVO();
			tableTitleVO.setField("temp");
			tableTitleVO.setTitle("&nbsp;&nbsp;");
			tableTitleVO.setRowspan(2);
			tableTitleVOsTitle.add(tableTitleVO);*/
			
			for (int i = 1; i <= 5; i++) {
				if(i != 4){
					tableTitleVOproperty = new TableTitleVO();
					tableTitleVOproperty.setField("detection"+i+"max");
					tableTitleVOproperty.setTitle("最大");
					tableTitleVOproperty.setRowspan(1);
					tableTitleVOproperty.setWidth(75);
					//tableTitleVOproperty.setFormatter("function(value,row,index){if(typeof(row.dection"+i+"max)!='undefined'){return dection"+i+"max.toFixed(2);}}");
					tableTitleVOsProperties.add(tableTitleVOproperty);
					
					tableTitleVOproperty = new TableTitleVO();
					tableTitleVOproperty.setField("detection"+i+"min");
					tableTitleVOproperty.setTitle("最小");
					tableTitleVOproperty.setRowspan(1);
					tableTitleVOproperty.setWidth(75);
					tableTitleVOsProperties.add(tableTitleVOproperty);
					
					tableTitleVOproperty = new TableTitleVO();
					tableTitleVOproperty.setField("detection"+i+"avg");
					tableTitleVOproperty.setTitle("平均");
					tableTitleVOproperty.setRowspan(1);
					tableTitleVOproperty.setWidth(75);
					tableTitleVOsProperties.add(tableTitleVOproperty);	
					
					
				}
			}
			
			lists.add(tableTitleVOsTitle);
			lists.add(tableTitleVOsProperties);
		}
		
		//老系统 注释不要删除！！！
		//if(systemno.equals("0")){
		if(SystemConfig.site instanceof ImplEquip_1_to_5 && SystemConfig.site instanceof ImplDetect_1_to_5){
			List<TableTitleVO> tableTitleVOsTitle = new ArrayList<TableTitleVO>();
/*			TableTitleVO tableTitleTestingTime = new TableTitleVO();
			tableTitleTestingTime.setField("sewagename");
			tableTitleTestingTime.setTitle("污水站点");
			tableTitleTestingTime.setRowspan(2);
			tableTitleVOsTitle.add(tableTitleTestingTime);*/
			
			TableTitleVO tableTitleVO;
/*			for (int i = 1; i <= 4; i++) {
				tableTitleVO = new TableTitleVO();
				String key = "equipment"+i+"name";
				String value = map.get(key);
				tableTitleVO.setField(key);
				tableTitleVO.setTitle(value);
				tableTitleVO.setColspan(2);
				tableTitleVOsTitle.add(tableTitleVO);
			}*/
			
			
			
			//运行时间，故障时间
			List<TableTitleVO> tableTitleVOsProperties = new ArrayList<TableTitleVO>();
			TableTitleVO tableTitleVOproperty;
			/*for (int i = 1; i <= 4; i++) {
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("equip"+i+"normaltime");
				tableTitleVOproperty.setTitle("运行时间");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOproperty.setWidth(75);
				tableTitleVOsProperties.add(tableTitleVOproperty);
				
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("equip"+i+"abnormaltime");
				tableTitleVOproperty.setTitle("故障时间");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOproperty.setWidth(75);
				tableTitleVOsProperties.add(tableTitleVOproperty);
			}
			*/
			//--------------以上是设备统计，下面开始水质--------------------------
			for (int i = 1; i <= 5; i++) {
				if(i != 4){
					tableTitleVO = new TableTitleVO();
					String key = "detection"+i+"name";
					String value = SystemConfig.detections.get(key);
					tableTitleVO.setField(key);
					tableTitleVO.setTitle(value);
					tableTitleVO.setColspan(3);
					tableTitleVOsTitle.add(tableTitleVO);
				}
			}
/*			tableTitleVO = new TableTitleVO();
			tableTitleVO.setField("temp");
			tableTitleVO.setTitle("&nbsp;&nbsp;");
			tableTitleVO.setRowspan(2);
			tableTitleVOsTitle.add(tableTitleVO);*/
			
			for (int i = 1; i <= 5; i++) {
				if( i != 4){
					tableTitleVOproperty = new TableTitleVO();
					tableTitleVOproperty.setField("detection"+i+"max");
					tableTitleVOproperty.setTitle("最大");
					tableTitleVOproperty.setRowspan(1);
					tableTitleVOproperty.setWidth(75);
					//tableTitleVOproperty.setFormatter("function(value,row,index){if(typeof(row.dection"+i+"max)!='undefined'){return dection"+i+"max.toFixed(2);}}");
					tableTitleVOsProperties.add(tableTitleVOproperty);
					
					tableTitleVOproperty = new TableTitleVO();
					tableTitleVOproperty.setField("detection"+i+"min");
					tableTitleVOproperty.setTitle("最小");
					tableTitleVOproperty.setRowspan(1);
					tableTitleVOproperty.setWidth(75);
					tableTitleVOsProperties.add(tableTitleVOproperty);
					
					tableTitleVOproperty = new TableTitleVO();
					tableTitleVOproperty.setField("detection"+i+"avg");
					tableTitleVOproperty.setTitle("平均");
					tableTitleVOproperty.setRowspan(1);
					tableTitleVOproperty.setWidth(75);
					tableTitleVOsProperties.add(tableTitleVOproperty);
					

				}
			}
			
			lists.add(tableTitleVOsTitle);
			lists.add(tableTitleVOsProperties);
		}
		
		//面源系统
		//if(systemno.equals("2")){
		if(SystemConfig.site instanceof ImplEquip_1_to_5 && SystemConfig.site instanceof ImplDetect_1_to_5_10_to_15){
			List<TableTitleVO> tableTitleVOsTitle = new ArrayList<TableTitleVO>();
			
			TableTitleVO tableTitleVO;
			
			//运行时间，故障时间
			List<TableTitleVO> tableTitleVOsProperties = new ArrayList<TableTitleVO>();
			TableTitleVO tableTitleVOproperty;
			//--------------下面开始水质--------------------------
			for (int i = 1; i <= 5; i++) {
				if(i != 4){
					tableTitleVO = new TableTitleVO();
					String key = "detection"+i+"name";
					String value = SystemConfig.detections.get(key);
					tableTitleVO.setField(key);
					tableTitleVO.setTitle(value);
					tableTitleVO.setColspan(3);
					tableTitleVOsTitle.add(tableTitleVO);
				}
			}
			for (int i = 10; i <= 15; i++) {
				tableTitleVO = new TableTitleVO();
				String key = "detection"+i+"name";
				String value = SystemConfig.detections.get(key);
				tableTitleVO.setField(key);
				tableTitleVO.setTitle(value);
				tableTitleVO.setColspan(3);
				tableTitleVOsTitle.add(tableTitleVO);
			}
/*			tableTitleVO = new TableTitleVO();
			tableTitleVO.setField("temp");
			tableTitleVO.setTitle("&nbsp;&nbsp;");
			tableTitleVO.setRowspan(2);
			tableTitleVOsTitle.add(tableTitleVO);*/
			
			for (int i = 1; i <= 5; i++) {
				if( i != 4){
					tableTitleVOproperty = new TableTitleVO();
					tableTitleVOproperty.setField("detection"+i+"max");
					tableTitleVOproperty.setTitle("最大");
					tableTitleVOproperty.setRowspan(1);
					//tableTitleVOproperty.setFormatter("function(value,row,index){if(typeof(row.dection"+i+"max)!='undefined'){return dection"+i+"max.toFixed(2);}}");
					tableTitleVOsProperties.add(tableTitleVOproperty);
					
					tableTitleVOproperty = new TableTitleVO();
					tableTitleVOproperty.setField("detection"+i+"min");
					tableTitleVOproperty.setTitle("最小");
					tableTitleVOproperty.setRowspan(1);
					tableTitleVOsProperties.add(tableTitleVOproperty);
					
					tableTitleVOproperty = new TableTitleVO();
					tableTitleVOproperty.setField("detection"+i+"avg");
					tableTitleVOproperty.setTitle("平均");
					tableTitleVOproperty.setRowspan(1);
					tableTitleVOsProperties.add(tableTitleVOproperty);
				}
			}
			for (int i = 10; i <= 15; i++) {
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("detection"+i+"max");
				tableTitleVOproperty.setTitle("最大");
				tableTitleVOproperty.setRowspan(1);
				//tableTitleVOproperty.setFormatter("function(value,row,index){if(typeof(row.dection"+i+"max)!='undefined'){return dection"+i+"max.toFixed(2);}}");
				tableTitleVOsProperties.add(tableTitleVOproperty);
				
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("detection"+i+"min");
				tableTitleVOproperty.setTitle("最小");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOsProperties.add(tableTitleVOproperty);
				
				tableTitleVOproperty = new TableTitleVO();
				tableTitleVOproperty.setField("detection"+i+"avg");
				tableTitleVOproperty.setTitle("平均");
				tableTitleVOproperty.setRowspan(1);
				tableTitleVOsProperties.add(tableTitleVOproperty);
			}
			lists.add(tableTitleVOsTitle);
			lists.add(tableTitleVOsProperties);
		}
		
		String jsons = JSON.toJSONString(lists);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(jsons);
	}
	
	/**
	 * 站点运行记录报表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/gotosewagerunrecord.do")
	public String gotosewagerunrecord(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<Area> areas = iAreaService.getAllAreas();
		model.addAttribute("allAreas", areas);
		return "/report/sewagerunrecord";
	}
	
	/**
	 * 根据站点sewageid 获取站点设备运行记录的月统计
	 * 
	 * iStatisticDayService.getStatisticMonthVO(map);
	 * iStatisticDayService.getStatisticMonthVOByCondition(map);
	 * 
	 * 这两个方法用的同一个sql，
	 * 其中第一个方法 没有分页，用于 设备运行记录 和 水质检测记录，查找某个区县下面所有的站点，返回多条记录
	 * 	      第二个方法 用于站点运行记录，查找单个站点记录
	 * 
	 * @param response
	 * @param request
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@RequestMapping("/getQuxianStatisticMonthByConditionAndSewageid.do")
	public void getQuxianStatisticMonthByConditionAndSewageid(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
		String testingtime = request.getParameter("testingtime").equals("")?null:request.getParameter("testingtime");
		
		Integer areaid = request.getParameter("areaid").equals("")?null:Integer.valueOf(request.getParameter("areaid"));
		Integer sewageid = request.getParameter("sewageid").equals("")?null:Integer.valueOf(request.getParameter("sewageid"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("areaid", areaid==null?null:String.valueOf(areaid));
		map.put("sewageid", sewageid==null?null:String.valueOf(sewageid));
		map.put("testingtime", testingtime);
		
		List<StatisticDayVO> statisticDayVOs = null;
		
		statisticDayVOs = iStatisticDayService.getStatisticMonthVO(map);
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setRows(statisticDayVOs);
		String jsondata = JSON.toJSONString(sysPagingUtil);
		response.setContentType("html/json;charset=UTF-8");
		logger.debug(jsondata);
		response.getWriter().write(jsondata);
	}
	
	/**
	 * 根据站点sewageid 获取站点设备运行记录的年统计
	 * @param response
	 * @param request
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("/getQuxianStatisticYearByConditionAndSewageid.do")
	public void getQuxianStatisticYearByConditionAndSewageid(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
		String testingtime = request.getParameter("testingtime").equals("")?null:request.getParameter("testingtime");
		
		Integer areaid = request.getParameter("areaid").equals("")?null:Integer.valueOf(request.getParameter("areaid"));
		Integer sewageid = request.getParameter("sewageid").equals("")?null:Integer.valueOf(request.getParameter("sewageid"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("areaid", areaid==null?null:String.valueOf(areaid));
		map.put("sewageid", sewageid==null?null:String.valueOf(sewageid));
		map.put("testingtime", testingtime);
		
		List<StatisticDayVO> statisticDayVOs = null;
		
		statisticDayVOs = iStatisticDayService.getStatisticYearVO(map);
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setRows(statisticDayVOs);
		String jsondata = JSON.toJSONString(sysPagingUtil);
		response.setContentType("html/json;charset=UTF-8");
		logger.debug(jsondata);
		response.getWriter().write(jsondata);
	}
	
	
	@RequestMapping("/gotosewagewaterhandle.do")
	public String gotosewagewaterhandle(HttpServletResponse response,HttpServletRequest request,Model model) throws IOException, ParseException{
		List<Area> areas = iAreaService.getAllAreas();
		model.addAttribute("allAreas", areas);
		return "/report/sewagewaterhandle";
	}

	/**
	 * 获取站点运行记录的 月 图像
	 * @throws IOException 
	 */
	@RequestMapping("/getsewagerundataMonthPicBycondition.do")
	public void getsewagerundataMonthPicBycondition(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String testingtime = request.getParameter("testingtime").equals("")?null:request.getParameter("testingtime");
		
		Integer areaid = request.getParameter("areaid").equals("")?null:Integer.valueOf(request.getParameter("areaid"));
		Integer sewageid = request.getParameter("sewageid").equals("")?null:Integer.valueOf(request.getParameter("sewageid"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("areaid", areaid==null?null:String.valueOf(areaid));
		map.put("sewageid", sewageid==null?null:String.valueOf(sewageid));
		map.put("testingtime", testingtime);
		
		List<StatisticDayVO> statisticDayVOs = null;
		
		statisticDayVOs = iStatisticDayService.getsewagerunrecordmonthreport(map);
		
		String jsondata = JSON.toJSONString(statisticDayVOs);
		response.setContentType("html/json;charset=UTF-8");
		logger.debug(jsondata);
		response.getWriter().write(jsondata);
	}
	
	/**
	 * 获取站点运行记录的 年 图像
	 * @throws IOException 
	 */
	@RequestMapping("/getsewagerundataYearPicBycondition.do")
	public void getsewagerundataYearPicBycondition(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String testingtime = request.getParameter("testingtime").equals("")?null:request.getParameter("testingtime");
		Integer areaid = request.getParameter("areaid").equals("")?null:Integer.valueOf(request.getParameter("areaid"));
		Integer sewageid = request.getParameter("sewageid").equals("")?null:Integer.valueOf(request.getParameter("sewageid"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("areaid", areaid==null?null:String.valueOf(areaid));
		map.put("sewageid", sewageid==null?null:String.valueOf(sewageid));
		map.put("testingtime", testingtime);
		
		List<StatisticDayVO> statisticDayVOs = null;
		
		statisticDayVOs = iStatisticDayService.getsewagerunrecordyearreport(map);
		
		String jsondata = JSON.toJSONString(statisticDayVOs);
		response.setContentType("html/json;charset=UTF-8");
		logger.debug(jsondata);
		response.getWriter().write(jsondata);
	}
	
	
	/**
	 * 进入能耗成本报表
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("/gotoenergycosts.do")
	public String gotoenergycosts(HttpServletResponse response,HttpServletRequest request,Model model) throws IOException, ParseException{
		List<Area> areas = iAreaService.getAllAreas();
		model.addAttribute("allAreas", areas);
		return "/report/energycost";
	}
	
	/**
	 * 能耗成本 日报
	 * @param response
	 * @param request
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("/energycostsday.do")
	public void energycosts(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		
		String testingtime = SewageVOUtils.isBlank(request.getParameter("testingtime")) ? null:request.getParameter("testingtime");
		String sewageid = String.valueOf(request.getParameter("sewageid")).equals("")?null:String.valueOf(request.getParameter("sewageid"));
		String areaid = String.valueOf(request.getParameter("areaid")).equals("")?null:String.valueOf(request.getParameter("areaid"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("testingtime", testingtime);
		map.put("sewageid", sewageid);
		map.put("areaid", areaid);
		map.put("type", "day");
		
		List<StatisticDayVO> statisticDayVOs = null;
		int count = 0;
		
		statisticDayVOs = iStatisticDayService.getEnergycostsByCondition(map);
		count = iStatisticDayService.getEnergycostsCountByCondition(map);

		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(statisticDayVOs);
		String jsondata = JSON.toJSONString(sysPagingUtil);
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	/**
	 * 能耗成本 月报
	 * @param response
	 * @param request
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("/energycostsmonth.do")
	public void energycostsmonth(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		
		String testingtime = SewageVOUtils.isBlank(request.getParameter("testingtime")) ? null:request.getParameter("testingtime");
		String sewageid = String.valueOf(request.getParameter("sewageid")).equals("")?null:String.valueOf(request.getParameter("sewageid"));
		String areaid = String.valueOf(request.getParameter("areaid")).equals("")?null:String.valueOf(request.getParameter("areaid"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("testingtime", testingtime);
		map.put("sewageid", sewageid);
		map.put("areaid", areaid);
		map.put("type", "month");
		
		List<StatisticDayVO> statisticDayVOs = null;
		int count = 0;
		
		statisticDayVOs = iStatisticDayService.getEnergycostsByCondition(map);
		count = iStatisticDayService.getEnergycostsCountByCondition(map);

		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(statisticDayVOs);
		String jsondata = JSON.toJSONString(sysPagingUtil);
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	/**
	 * 能耗成本 年报
	 * @param response
	 * @param request
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("/energycostsyear.do")
	public void energycostsdayyear(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		
		String testingtime = SewageVOUtils.isBlank(request.getParameter("testingtime")) ? null:request.getParameter("testingtime");
		String sewageid = String.valueOf(request.getParameter("sewageid")).equals("")?null:String.valueOf(request.getParameter("sewageid"));
		String areaid = String.valueOf(request.getParameter("areaid")).equals("")?null:String.valueOf(request.getParameter("areaid"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("testingtime", testingtime);
		map.put("sewageid", sewageid);
		map.put("areaid", areaid);
		map.put("type", "year");
		
		List<StatisticDayVO> statisticDayVOs = null;
		int count = 0;
		
		statisticDayVOs = iStatisticDayService.getEnergycostsByCondition(map);
		count = iStatisticDayService.getEnergycostsCountByCondition(map);

		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(statisticDayVOs);
		String jsondata = JSON.toJSONString(sysPagingUtil);
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}

	/**
	 * 污水处理报表 NO56 获取处理水量的总和，有一个bug
	 * @param response
	 * @param request
	 * @throws IOException
	 * @throws ParseException
     */
	@RequestMapping("/getSumHandleWater.do")
	public void getSumHandleWater(@RequestBody ReportVO reportVO, HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException{

		Map<String, String> map = new HashMap<String, String>();
		map.put("testingtime", reportVO.getTestingtime().equals("") ? null : reportVO.getTestingtime());
		map.put("sewageid", reportVO.getSewageid().equals("") ? null : reportVO.getSewageid());
		map.put("areaid", reportVO.getAreaid().equals("")  ? null : reportVO.getAreaid());
		map.put("reporttype",reportVO.getReporttype().equals("") ? null: reportVO.getReporttype());

		Double sumWater = iSewageService.getSumHandleWater(map);

		DecimalFormat df = new DecimalFormat("######0.00");
		String jsondata = JSON.toJSONString("总水量："+String.valueOf(sumWater==null?0.00:df.format((double)sumWater)));
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}


	/**
	 * 污水处理报表 导出报表 下载excel
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/getSewageHandleExcel.do")
	public ModelAndView getSewageHandleExcel(HttpServletRequest request,HttpServletResponse response) throws ParseException{
		Integer areaid = request.getParameter("areaid").equals("")?null:Integer.valueOf(request.getParameter("areaid"));

		String reporttype = request.getParameter("reporttype");
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date testingtime = request.getParameter("testingtime").equals("")?null:formater.parse(request.getParameter("testingtime"));
		String sewageid = String.valueOf(request.getParameter("sewageid")).equals("")?null:String.valueOf(request.getParameter("sewageid"));

		Map<String, String> map = new HashMap<String, String>();
		map.put("areaid", areaid==null?null:String.valueOf(areaid));
		map.put("testingtime", formater.format(testingtime));
		map.put("sewageid", sewageid);
		map.put("reporttype",reporttype);

		List<Sewage> sewageList;
		//int count = 0;

		//根据不同的报表类型 获取数据

		sewageList = iSewageService.getSewageHandleExcel(map);
		//count = iSewageService.getSewageHandleExcelCount(map);


/*		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("reportname", "污水处理日报");*/


		ExcelContentObject content = new ExcelContentObject();

		//登录人
		Subject subject = SecurityUtils.getSubject();
		SysUser sysUser = iSysUserService.getUserByLoginName((String)subject.getPrincipal());


		content.setAuthor(sysUser.getUsername());

		//设置制表时间
		content.setMakeTableTime(new DateTime());

		String temp = "污水处理";
		if(reporttype.equals("1")){
			temp = temp + "日报";
		}else if(reporttype.equals("2")){
			temp = temp + "月报";
		}else{
			temp = temp+"年报";
		}

		//设置header
		content.setHeader(temp);

		//设置第一列标题
		content.setMainTitle("站点名称");

		//设置第二列 后面的标题
		ArrayList<String> titles = new ArrayList<String>();
		titles.add("处理水量");
		titles.add("削减COD量");
		titles.add("削减NH3-N量");
		titles.add("削减总P量");
		content.setTitles(titles);

		//填充第一列数据
		ArrayList<String> mainData = new ArrayList<String>();
		ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();

		DecimalFormat df=(DecimalFormat) DecimalFormat.getInstance();
		df.setMaximumFractionDigits(2);

		for(Sewage vos : sewageList){
			mainData.add(vos.getName());
			ArrayList<String> data = new ArrayList<String>();

			data.add(String.valueOf(df.format(vos.getWaterflow())));

			data.add(String.valueOf(df.format(vos.getReducecod())));

			data.add(String.valueOf(df.format(vos.getReducenh3n())));

			data.add(String.valueOf(df.format(vos.getReducep())));

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
