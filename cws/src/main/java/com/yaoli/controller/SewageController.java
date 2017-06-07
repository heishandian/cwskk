package com.yaoli.controller;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yaoli.config.SystemConfig;
import com.yaoli.config.siteFunction.*;
import com.yaoli.util.*;
import com.yaoli.vo.*;
import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.Area;
import com.yaoli.beans.DetectionData;
import com.yaoli.beans.RunData;
import com.yaoli.beans.Sewage;
import com.yaoli.service.IAreaService;
import com.yaoli.service.IDetectionDataAbnormalService;
import com.yaoli.service.IDetectionDataService;
import com.yaoli.service.IResultStatisticsService;
import com.yaoli.service.IRunDataAbnormalService;
import com.yaoli.service.IRunDataService;
import com.yaoli.service.ISewageService;
import com.yaoli.service.IStatisticDayService;
import com.yaoli.service.IStatisticService;

@Controller
@RequestMapping("/monitor")
public class SewageController {
	private static Logger logger = Logger.getLogger(SewageController.class);

	@Resource
	public IAreaService isAreaService;

	@Resource
	public ISewageService iSewageService;

	@Resource
	public IDetectionDataService iDetectionDataService;

	//结果统计不需要了
	@Resource
	public IResultStatisticsService iResultStatisticsService;

	@Resource
	public IDetectionDataAbnormalService iDetectionDataAbnormalService;

	@Resource
	public IRunDataService iRunDataService;

	public List<Sewage> cacheSewages;

	@Resource
	public IRunDataAbnormalService iRunDataAbnormalService;

	@Resource
	public IStatisticService iStatisticService;

	@Resource
	private IAreaService iAreaService;

	@Resource
	private IStatisticDayService iStatisticDayService;

	/**
	 * 实时监控 进入地图
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/intomapmonitor")
	public String mapMonitor(HttpServletRequest request, Model model) {
		List<Area> allAreas = isAreaService.getAllAreas();
		model.addAttribute("allAreas", allAreas);
		return "/monitor/gmap";
	}

	/**
	 * 站点监控 站点监控主界面
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InterruptedException
	 */
	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getallsewages.do")
	public void getAllSewages(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Sewage> sewages = iSewageService.getAllSewages();

		//以下替换成线程查询
		/*
		long begin1 = System.currentTimeMillis();
		List<DetectionData> detectionDatas = iDetectionDataService.getLatestSewageDetectionData();//水质异常问题
		long end1 = System.currentTimeMillis();
		logger.info("进入地图 查询水质耗时:"+(end1-begin1));

		long begin2 = System.currentTimeMillis();
		List<RunData> runDatas  = iRunDataService.getLatestSewageRunData();//获取最新的运行情况 可能是昨天的 数据，从而显示的是故障
		long end2 = System.currentTimeMillis();
		logger.info("进入地图 查询设备时:"+(end2-begin2));

		long begin3 = System.currentTimeMillis();
		List<Sewage> withoutEletrictAndWater = iSewageService.getLatestSewageWithoutElectricAndWater();//断电断线 今天到目前为止没有数据就是断电断线
		long end3 = System.currentTimeMillis();
		logger.info("进入地图 查询断线断线耗时:"+(end3-begin3));
		*/

		/**
		 * 以下通过线程的方式来获取最新的数据
		 */
		long begin4 = System.currentTimeMillis();
		List<Sewage> withoutEletrictAndWater2 = new ArrayList<Sewage>();
		GetLatestSewageWithoutElectricAndWaterThread getLatestSewageWithoutElectricAndWaterThread2 = new GetLatestSewageWithoutElectricAndWaterThread(iSewageService, withoutEletrictAndWater2);
		getLatestSewageWithoutElectricAndWaterThread2.start();

		List<DetectionData> detectionDatas2 = new ArrayList<DetectionData>();
		GetLatestSewageDetectionDataThread getLatestSewageDetectionDataThread = new GetLatestSewageDetectionDataThread(iDetectionDataService,detectionDatas2);
		getLatestSewageDetectionDataThread.start();

		List<RunData> runDatas2 = new ArrayList<RunData>();
		GetLatestSewageRunDataThread getLatestSewageRunDataThread2 = new GetLatestSewageRunDataThread(iRunDataService, runDatas2);
		getLatestSewageRunDataThread2.start();


		getLatestSewageWithoutElectricAndWaterThread2.join();
		getLatestSewageDetectionDataThread.join();
		getLatestSewageRunDataThread2.join();
		long end4 = System.currentTimeMillis();
		logger.info("进入地图 使用线程的时间为:"+(end4-begin4));

		for (Sewage sewage : sewages) {
			//以下三个循环不可以改变顺序，断电断线<水质问题<设备故障
			for (Sewage ew : getLatestSewageWithoutElectricAndWaterThread2.getWithoutEletrictAndWater()) {
				if(ew.getSewageid().equals(sewage.getSewageid())){
					sewage.setIsAbnormal(3);//断线断线
					break;
				}
			}
			for (DetectionData detectionData : getLatestSewageDetectionDataThread.getDetectionDatas()) {
				if(sewage.getSewageid().toString().equals(detectionData.getSewageid().toString())){
					if(SewageVOUtils.detectiondataisAbnormal(sewage, detectionData)==true){
						sewage.setIsAbnormal(1);//表示水质有问题
						break;
					}
				}
			}
			for (RunData runData : getLatestSewageRunDataThread2.getRunDatas()) {
				if(sewage.getSewageid().equals(runData.getSewageid())){
					if (SewageVOUtils.rundataisAbnormal(runData)) {
						sewage.setIsAbnormal(2);//表示设备有问题
						break;
					}
				}
			}
		}

		long begin5 = System.currentTimeMillis();

		Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
		List<TransportSewageMapVO> transportSewageMapVOs = new ArrayList<TransportSewageMapVO>();

		TransportSewageMapVO transportSewageMapVO = new TransportSewageMapVO();
		for (Sewage sewage : sewages) {
			transportSewageMapVO = mapper.map(sewage, TransportSewageMapVO.class);
			transportSewageMapVOs.add(transportSewageMapVO);
		}
		long end5 = System.currentTimeMillis();
		logger.info("进入地图 转换对象时间:"+(end5-begin5));

		String jsons = JSON.toJSONString(transportSewageMapVOs);
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Content-Encoding", "gzip");
		response.setCharacterEncoding("UTF-8");

		byte [] a = GZIP.compress(jsons.getBytes("UTF-8"));
		response.setContentLength(a.length);

		response.getOutputStream().write(a);
	}


	/**
	 * 实时监控  选择areaid后  根据areaid获取该区域下的所有站点信息
	 *
	 * 类似于getAllSewages
	 * @param sewageVO
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	@RequestMapping("/getsewagesbyareaid.do")
	public void getSewagesByAreaId(@RequestBody SewageVO sewageVO, HttpServletRequest request, HttpServletResponse response) throws IOException, IllegalArgumentException, SecurityException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<Sewage> sewages = iSewageService.getSewagesByAreaId(sewageVO.getAreaid());

/*		List<DetectionData> detectionDatas = iDetectionDataService.getLatestSewageDetectionDataByAreaId(sewageVO.getAreaid());
		List<RunData> runDatas  = iRunDataService.getLatestSewageRunDataByAreaid(sewageVO.getAreaid());
		List<Sewage> withoutEletrictAndWater =  iSewageService.getLatestSewageWithoutElectricAndWaterByAreaid(sewageVO.getAreaid());*/

		/**
		 * 以下通过线程的方式来获取最新的数据
		 */
		long begin4 = System.currentTimeMillis();
		List<Sewage> withoutEletrictAndWater2 = new ArrayList<Sewage>();
		GetLatestSewageWithoutElectricAndWaterThread getLatestSewageWithoutElectricAndWaterThread2 = new GetLatestSewageWithoutElectricAndWaterThread(iSewageService, withoutEletrictAndWater2,sewageVO.getAreaid());
		getLatestSewageWithoutElectricAndWaterThread2.start();

		List<DetectionData> detectionDatas2 = new ArrayList<DetectionData>();
		GetLatestSewageDetectionDataThread getLatestSewageDetectionDataThread = new GetLatestSewageDetectionDataThread(iDetectionDataService,detectionDatas2,sewageVO.getAreaid());
		getLatestSewageDetectionDataThread.start();

		List<RunData> runDatas2 = new ArrayList<RunData>();
		GetLatestSewageRunDataThread getLatestSewageRunDataThread2 = new GetLatestSewageRunDataThread(iRunDataService, runDatas2 ,sewageVO.getAreaid());
		getLatestSewageRunDataThread2.start();
		//end 区域结束


		try {
			getLatestSewageWithoutElectricAndWaterThread2.join();
			getLatestSewageDetectionDataThread.join();
			getLatestSewageRunDataThread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long end4 = System.currentTimeMillis();
		logger.info("进入地图 使用线程的时间为:"+(end4-begin4));

		for (Sewage sewage : sewages) {
			//以下三个循环不可以改变顺序，断电断线<水质问题<设备故障
			for (Sewage ew : getLatestSewageWithoutElectricAndWaterThread2.getWithoutEletrictAndWater()) {
				if(ew.getSewageid().equals(sewage.getSewageid())){
					sewage.setIsAbnormal(3);//断线断线
					break;
				}
			}
			for (DetectionData detectionData : getLatestSewageDetectionDataThread.getDetectionDatas()) {
				if(sewage.getSewageid().toString().equals(detectionData.getSewageid().toString())){
					if(SewageVOUtils.detectiondataisAbnormal(sewage, detectionData)==true){
						sewage.setIsAbnormal(1);//表示水质有问题
						break;
					}
				}
			}
			for (RunData runData : getLatestSewageRunDataThread2.getRunDatas()) {
				if(sewage.getSewageid().equals(runData.getSewageid())){
					if (SewageVOUtils.rundataisAbnormal(runData)) {
						sewage.setIsAbnormal(2);//表示设备有问题
						break;
					}
				}
			}
		}

		Area area = isAreaService.selectByPrimaryKey(sewageVO.getAreaid());

		SewageVO sewage = new SewageVO();

		sewage.setAreaName(area.getName());

		if(area.getCoordinatex() != null){
			sewage.setCoordinatex(area.getCoordinatex());
			sewage.setCoordinatey(area.getCoordinatey());
		}
		sewage.setSewages(sewages);

		String jsons = JSON.toJSONString(sewage);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(jsons);
	}

	/**
	 * 实时监控 根据地点或者运营编号搜索
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getSewagesBySearchNameOrId.do")
	public void getSewagesByAreaNameOrYunyinNO(@RequestBody SewageVO sewageVO,HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<Sewage> sewages = iSewageService.getSewagesBySearchNameOrId(sewageVO.getSearchNameOrId());

		if(cacheSewages == null){
			cacheSewages = iSewageService.getAllSewages();
		}

		sewageVO.setSewages(cacheSewages);

		sewageVO.setSearchResult(sewages);

		String jsons = JSON.toJSONString(sewageVO);

		response.setContentType("text/html; charset=UTF-8");

		response.getWriter().write(jsons);
	}

	/**
	 * 实时监控 获取站点的运行信息，即在地图上点击图标，弹出站点信息
	 * @param sewageVO
	 * @param request
	 * @param response
	 * @throws Exception
	 * @throws IllegalAccessException
	 */
	@RequestMapping("/getlatestdetectiondata.do")
	public void getLatestDetectionData(@RequestBody SewageVO sewageVO,HttpServletRequest request,HttpServletResponse response)throws IllegalAccessException, Exception{
		int id = sewageVO.getSewageid();

		//获取该站点的信息
		Sewage sewage = iSewageService.selectByPrimaryKey(id);

/*		//获取该站点最新水质信息
		DetectionData detectionData = iDetectionDataService.getLatestDetectionData(id);

		//获取该站点最新设备信息
		RunData runData = iRunDataService.getLatestRunData(id); */
		//先进行初始化
		DetectionData detectionData = new DetectionData();
		RunData runData = new RunData();

		GetLatestSewageDetectionDataThread getLatestSewageDetectionDataThread = new GetLatestSewageDetectionDataThread(iDetectionDataService,detectionData,id);
		getLatestSewageDetectionDataThread.start();

		GetLatestSewageRunDataThread getLatestSewageRunDataThread = new GetLatestSewageRunDataThread(iRunDataService, runData,id);
		getLatestSewageRunDataThread.start();

		getLatestSewageDetectionDataThread.join();
		getLatestSewageRunDataThread.join();
		//end

		detectionData = getLatestSewageDetectionDataThread.getDetectionData();
		runData = getLatestSewageRunDataThread.getRunData();

		Double sewageDayTotalWaterFlow = iStatisticDayService.getSewageDayTotalWater(id)==null?0.00:iStatisticDayService.getSewageDayTotalWater(id);

		Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();

		SewageMonitorVO sewageMonitorVO = mapper.map(sewage, SewageMonitorVO.class);

		//设置设备名称
		SewageVOUtils.setEquipmentName(sewageMonitorVO,runData);

		boolean rundataIsAbnormal = SewageVOUtils.rundataisAbnormal(runData);

		boolean detectionDataIsAbnormal = SewageVOUtils.detectiondataisAbnormal(sewage, detectionData);

		if(rundataIsAbnormal == true){
			sewageMonitorVO.setRunStateM("设备故障");
		}
		if(detectionDataIsAbnormal == true){
			sewageMonitorVO.setRunStateM("水质异常");
		}

		/*用于保存访问信息*/
		this.sewageMonitorId = sewageVO.getSewageid();
		String html = SewageVOUtils.getSewageHTML2(request.getContextPath(),sewageMonitorVO,detectionData,runData,sewage,sewageDayTotalWaterFlow);

		JsonMessage jsonMessage  = new JsonMessage();
		jsonMessage.setMessage(html);
		String jsons = JSON.toJSONString(jsonMessage);
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(jsons);
	}

























	//---------------------------------以上是实时监控-------------------------------------//
































	/**
	 * 该私有全局变量是用于保存之前访问的污水站 信息
	 * 而不能通过连接后跟?sewagid=xx来实现
	 */
	private int sewageMonitorId = -1;

	/**
	 * 站点监控
	 * @param request
	 * @param response
	 */
	@RequestMapping("/sewagemonitor.do")
	public String sewageMonitor(HttpServletRequest request,HttpServletResponse response,Model model){
		List<Area> areaList= isAreaService.getAllAreas();
		model.addAttribute("areaList", areaList);

		//用于保存 sewageid 和 areaid,张老师提出的要求
		if(this.sewageMonitorId != -1){
			Sewage sewage = iSewageService.selectByPrimaryKey(this.sewageMonitorId);
			model.addAttribute("sewageid", this.sewageMonitorId);
			model.addAttribute("areaid", sewage.getAreaid());
		}


		//老系统 苏州 惠山系统
		//if(systemNO.equals("0")){
		if(SystemConfig.site instanceof ImplEquip_1_to_5 && SystemConfig.site instanceof ImplDetect_1_to_5){
			return "/monitor/sewagemonitor_e_1_to_5_and_d_1_to_5";
		}

		//江都的系统
		//if(systemNO.equals("1")){
		if(SystemConfig.site instanceof ImplEquip_6_to_21 && SystemConfig.site instanceof  ImplDetect_1_to_5){
			return "/monitor/sewagemonitor_e_6_to_21_and_d_1_to_5";
		}

		//如果系统参数是2，表明是面源系统
		//if(systemNO.equals("2")){
		if(SystemConfig.site instanceof ImplEquip_1_to_5 && SystemConfig.site instanceof ImplDetect_1_to_5_10_to_15){
			return "/monitor/sewagemonitor_e_1_to_5_and_d_1_to_5_10_to_15";
		}

		//如果系统参数是5，表明是邗江系统
		if(SystemConfig.site instanceof ImplEquip_1_to_21 && SystemConfig.site instanceof ImplDetect_1_to_5){
			return "/monitor/sewagemonitor_e_1_to_5_and_d_1_to_5";
		}

		return "/monitor/sewagemonitor";
	}

	@RequestMapping("/ajaxgetsewagebyareaid.do")
	public void ajaxGetSewagesByAreaId(@RequestBody SewageVO sewageVO,HttpServletResponse response,HttpServletRequest request) throws IOException{
		int areaid = sewageVO.getAreaid()==null ? -1:sewageVO.getAreaid();
		List<Sewage> sewages = iSewageService.getSewagesByAreaId(areaid);
		String json = JSON.toJSONString(sewages);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}

	@RequestMapping("/ajaxGetSewageRunInfoSewageId.do")
	public void ajaxGetSewageRunInfoSewageId(@RequestBody SewageVO sewageVO,HttpServletRequest request,HttpServletResponse response) {
		//站点Id ajaxGetSewageRunInfoSewageId
		int sewageid = sewageVO.getSewageid();

		/*获取站点信息*/
		SewageMonitorVO sewageMonitorVO = iSewageService.getSewageMonitorVOBySewageId(sewageid);

		RunData latestRunDataInfo = iRunDataService.getLatestRunData(sewageid);

		DetectionData latestDetectionData = iDetectionDataService.getLatestDetectionData(sewageid);

		//获取某个站点的当天水量
		Double water = iStatisticDayService.getSewageDayTotalWater(sewageid);

		Class<?> rundataClass = null;
		Class<?> sewageMonitorVoClass = null;
		Class<?> detectionDataClass = null;
		try {
			rundataClass = Class.forName("com.yaoli.beans.RunData");
			sewageMonitorVoClass = Class.forName("com.yaoli.vo.SewageMonitorVO");
			detectionDataClass = Class.forName("com.yaoli.beans.DetectionData");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		/**模板**/
//		if(SystemConfig.site instanceof ImplEquip_1_to_5 ){
//
//		}else if(SystemConfig.site instanceof ImplEquip_6_to_21){
//
//		}
//
//		if(SystemConfig.site instanceof ImplDetect_1_to_5){
//
//		}else if(SystemConfig.site instanceof ImplDetect_1_to_5_10_to_14){
//
//		}

		//进行状态初始化，如果出现故障和水质问题或者其他，就会覆盖
		sewageMonitorVO.setRunStateM("运行正常");
		//注意顺序不能颠倒
		//异常情况优先等级：断电断线》设备故障》水质异常》水量不足

		//面源站点不需要水量不足
		if(!(SystemConfig.site instanceof ImplEquip_1_to_5) && !(SystemConfig.site instanceof ImplDetect_1_to_5_10_to_15)){
			if(water == null || water == 0.0){
				sewageMonitorVO.setRunStateM("水量不足");
			}
		}


		/**
		 * 设备运行信息
		 */
		if(SystemConfig.site instanceof ImplEquip_1_to_5 ){
			try {
				if(latestRunDataInfo != null){
					for (int i = 1; i <= 5; i++) {
						Method method = rundataClass.getDeclaredMethod("getEquipment"+i+"state"); //getEquipment11state
						Object stateObject = method.invoke(latestRunDataInfo);
						int state = stateObject == null ? -1:Integer.valueOf(stateObject.toString());
						if(state != -1){//表示 state不为空，即有该数据;并且下面进行赋值
							if(state == 3) {
								sewageMonitorVO.setRunStateM("运行故障");
								break;
							}
						}
					}
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}else if(SystemConfig.site instanceof ImplEquip_6_to_21){
			try {
				if(latestRunDataInfo != null){
					for (int i = 6; i <= 21; i++) {
						Method method = rundataClass.getDeclaredMethod("getEquipment"+i+"state"); //getEquipment11state
						Object stateObject = method.invoke(latestRunDataInfo);
						int state = stateObject == null ? -1:Integer.valueOf(stateObject.toString());
						if(state != -1){//表示 state不为空，即有该数据;并且下面进行赋值
							if(i == 6 || i== 7){//这里表明如果是液位计高高或者底底的话，表明运行故障，具体i 表示的什么可以参照开发前必读
								if(state == 8 || state == 1){
									sewageMonitorVO.setRunStateM("运行故障");
									break;
								}
							}else {//如果是其他设备
								if(state == 3){
									sewageMonitorVO.setRunStateM("运行故障");
									break;
								}
							}
						}
					}
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}

		if(latestRunDataInfo == null){
			sewageMonitorVO.setRunStateM("未检测到设备运行状态");
		}
		/**
		 * 水质信息
		 */
		if(SystemConfig.site instanceof ImplDetect_1_to_5){
			if(latestDetectionData != null){
				for (int i = 1; i <= 5; i++) {
					if(i != 6 && i != 4){//流量、液位不需要比较
						setDetectionInfo(i,sewageMonitorVoClass,detectionDataClass,sewageMonitorVO,latestDetectionData);
					}
				}
			}else {
				sewageMonitorVO.setRunStateM("未检测到水质状态");
			}
		}else if(SystemConfig.site instanceof ImplDetect_1_to_5_10_to_15){
			if(latestDetectionData != null){
				for (int i = 1; i <= 6; i++) {
					if(i != 6 && i != 4){//流量、液位不需要比较
						setDetectionInfo(i,sewageMonitorVoClass,detectionDataClass,sewageMonitorVO,latestDetectionData);
					}
				}
				for (int i = 10; i <= 15; i++) {
					//在这里添加不进行判断的地方
					setDetectionInfo(i,sewageMonitorVoClass,detectionDataClass,sewageMonitorVO,latestDetectionData);
				}
			}
		}
		if(latestDetectionData == null){
			sewageMonitorVO.setRunStateM("未检测到水质状态");
		}

		if(latestDetectionData == null && latestRunDataInfo == null){
			sewageMonitorVO.setRunStateM("未检测到设备和水质状态");
		}

		//设置最后更新时间
		if(latestDetectionData != null){
			sewageMonitorVO.setLastUpdatetime(latestDetectionData.getTestingtime());
		}else {
			if(latestDetectionData == null && latestRunDataInfo == null){
				sewageMonitorVO.setLastUpdatetime(null);
			}
		}


		Double sewageDayTotalWaterFlow = iStatisticDayService.getSewageDayTotalWater(sewageid)==null?0.00:iStatisticDayService.getSewageDayTotalWater(sewageid);//iStatisticService.getSewageDayTotalWaterFlow(id);
		sewageMonitorVO.setDayWater(String.valueOf(new java.text.DecimalFormat("0.00").format(sewageDayTotalWaterFlow)));
		if(sewageMonitorVO.getReducenh3n() != null){
			sewageMonitorVO.setReducenh3n(Double.valueOf(new java.text.DecimalFormat("0.00").format(sewageDayTotalWaterFlow*sewageMonitorVO.getReducenh3n())));
		}
		if(sewageMonitorVO.getReducep() != null){
			sewageMonitorVO.setReducep(Double.valueOf(new java.text.DecimalFormat("0.00").format(sewageDayTotalWaterFlow*sewageMonitorVO.getReducep())));
		}
		if(sewageMonitorVO.getReducecod() != null){
			sewageMonitorVO.setReducecod(Double.valueOf(new java.text.DecimalFormat("0.00").format(sewageDayTotalWaterFlow*sewageMonitorVO.getReducecod())));
		}

		try {
			if(sewageMonitorVO.getVideopuid() != null && !sewageMonitorVO.getVideopuid().equals("")){
				VideoUrlTokenVO vo = new VideoUrlTokenVO();
				vo.setPassword("admin54321");
				vo.setUid("admin");
				vo.setEpid("wxsd");
				vo.setPuid(sewageMonitorVO.getVideopuid());
				vo.setPostUrl("http://61.160.70.100:9580/nmc/rest/realstream?sign=");

				vo.setDateString(String.valueOf(System.currentTimeMillis()/1000L));

				GetVideoUrlTokenUtil util = new GetVideoUrlTokenUtil();
				util.setVideoUrlTokenVO(vo);

				//获取url
				String url = util.getURL();
				sewageMonitorVO.setVideourl(url);
			}
		}finally {
			String json = JSON.toJSONString(sewageMonitorVO);
			response.setContentType("text/html;charset=UTF-8");
			try {
				response.getWriter().write(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	private void setDetectionInfo(int i,Class<?> sewageMonitorVoClass,Class<?> detectionDataClass,SewageMonitorVO sewageMonitorVO,DetectionData latestDetectionData){
		try {
			//设置 detection的下限 等
			Method getDetectiondl = sewageMonitorVoClass.getDeclaredMethod("getDetection"+i+"dl");
			Double detectiondlValue = getDetectiondl.invoke(sewageMonitorVO) == null?null:Double.valueOf(getDetectiondl.invoke(sewageMonitorVO).toString());


			//设置 detectiondl上限 等
			Method getDetectionul = sewageMonitorVoClass.getDeclaredMethod("getDetection"+i+"ul");
			Double detectionulValue = getDetectionul.invoke(sewageMonitorVO) == null?null:Double.valueOf(getDetectionul.invoke(sewageMonitorVO).toString());

			Method getDetection = detectionDataClass.getDeclaredMethod("getDetection"+i);
			Double detection = getDetection.invoke(latestDetectionData) == null?null:Double.valueOf(getDetection.invoke(latestDetectionData).toString());

			//将detection的值放入到 sewageMonitorVO 中
			if(detection != null){
				Field sewageMonitorVOgetDetectionField = sewageMonitorVoClass.getDeclaredField("detection"+i);
				sewageMonitorVOgetDetectionField.setAccessible(true);
				sewageMonitorVOgetDetectionField.set(sewageMonitorVO, detection);
			}

			if(detectionulValue != null && detectiondlValue !=null){
				if(detectiondlValue > detection || detection > detectionulValue){
					sewageMonitorVO.setRunStateM("水质异常");
				}
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}



	@RequestMapping("/ajaxgetlatestdetectiondata.do")
	public void ajaxGetLatestDetectionData(@RequestBody SewageVO sewageVO,HttpServletRequest request,HttpServletResponse response)throws IOException{
		int sewageid = sewageVO.getSewageid();
		DetectionData detectionData = iDetectionDataService.getLatestDetectionData(sewageid);
		String json = JSON.toJSONString(detectionData);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}

	@RequestMapping("/ajaxget5info.do")
	public void ajaxget5info(@RequestBody SewageVO sewageVO ,HttpServletRequest request, HttpServletResponse response) throws IOException{
		int sewageid = sewageVO.getSewageid();
		List<DetectionDataVO> detectionDataList = iDetectionDataService.gettop5info(sewageid);
		String json = JSON.toJSONString(detectionDataList);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json);
	}


	@RequestMapping("/gotoshowsewages.do")
	public String gotosewages(){
		return "/areas/showsewages";
	}

	@RequestMapping("/getsewages.do")
	public void showSewages(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));

		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);

		List<SewageVO> sewageVOList = iSewageService.getSewagesByPaging(map);


		int count = iSewageService.getTotalSewageCount();

		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(sewageVOList);

		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}

	@RequestMapping("/gotoaddsewage.do")
	public String gotoAddNewSewage(Model model){
		List<Area> areas = iAreaService.getAllAreas();
		model.addAttribute("allAreas", areas);
		//一下用于老系统中添加控制方式，江都系统不需要
		model.addAttribute("controlmethod", ControlMethod.getControlMethods());

		//如果系统参数是0，表明是老系统(无锡)
		if(SystemConfig.site instanceof ImplEquip_1_to_5 && SystemConfig.site instanceof ImplDetect_1_to_5){
			return "/areas/addnewsewage_wx";
		}

		//如果系统参数是1，表明是江都的系统
		else if(SystemConfig.site instanceof ImplEquip_6_to_21 && SystemConfig.site instanceof ImplDetect_1_to_5){
			return "/areas/addnewsewage_jd";
		}

		//如果系统参数是2，表明是面源系统
		else if(SystemConfig.site instanceof ImplEquip_1_to_5 && SystemConfig.site instanceof ImplDetect_1_to_5_10_to_15){
			return "/areas/addnewsewage_my";
		}

		//如果系统参数是5，表明是邗江系统,使用无锡
		else if(SystemConfig.site instanceof ImplEquip_1_to_21 && SystemConfig.site instanceof ImplDetect_1_to_5){
			return "/areas/addnewsewage_wx";
		}
		else {//其他使用无锡
			return "/areas/addnewsewage_wx";
		}

	}

	@RequestMapping("/addnewsewage.do")
	public void addNewSewage(@RequestBody SewageVO sewageVO ,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
		//转换成Sewage
		Sewage sewage = mapper.map(sewageVO, Sewage.class);
		int count = iSewageService.insertSelective(sewage);

		if(count != 1){
			throw new Exception("添加污水站点失败");
		}
		JsonMessage jsondata = new JsonMessage();
		jsondata.setKey("pass");
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsondata));
	}

	@RequestMapping("/updatesewage.do")
	public void updateSewage(@RequestBody SewageVO sewageVO ,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Mapper mapper = DozerBeanMapperSingletonWrapper.getInstance();
		//转换成Sewage
		Sewage sewage = mapper.map(sewageVO, Sewage.class);
		int count = iSewageService.updateByPrimaryKeySelective(sewage);
		JsonMessage jsondata = new JsonMessage();
		if(count != 1){
			throw new Exception("更新污水站点失败");
		}

		//在这里发送
		//JiangDu
		//if(SystemConfig.site instanceof ImplEquip_6_to_21 && SystemConfig.site instanceof  ImplDetect_1_to_5){
		if(SystemConfig.site.toString().equals("JD")){
			try {
				SendUpdateSewageIdTaskClient.sendSewageid(sewage.getSewageid());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		jsondata.setKey("pass");
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsondata));
	}

	@RequestMapping("/deleteselectedsewages.do")
	public void deleteSelectedSewages(@RequestBody SewageVO sewageVO,HttpServletResponse response,HttpServletRequest request) throws Exception{
		List<Integer> idList = sewageVO.getSelectIds();
		for (int i = 0; i < idList.size(); i++) {
			int count = iSewageService.deleteByPrimaryKey(idList.get(i));
			//if(count != 1){
			//throw new Exception("删除污水站点失败！");
			//}
		}
		JsonMessage jsondata = new JsonMessage();
		jsondata.setKey("pass");
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsondata));
	}

	@RequestMapping("/ajaxgetsewagebysewageid.do")
	public void ajaxGetSewageBySewageid(@RequestBody Sewage sewage,HttpServletResponse response,HttpServletRequest request) throws IOException{
		Sewage sewage2 = iSewageService.selectByPrimaryKey(sewage.getSewageid());
		JsonMessage jsondata = new JsonMessage();
		if(sewage2!=null){
			jsondata.setKey("pass");
			jsondata.setMessage(sewage2.getControlmethod().toString());
		}else {
			jsondata.setKey("null");
		}

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsondata));
	}

	@RequestMapping("/gotomodifysewage.do")
	public String modifySewage(HttpServletResponse response,HttpServletRequest request,Model model){
		List<Area> areas = iAreaService.getAllAreas();
		model.addAttribute("allAreas", areas);


		String sewageid = SewageVOUtils.isBlank(request.getParameter("sewageid")) ? null:request.getParameter("sewageid");
		Sewage sewage = iSewageService.selectByPrimaryKey(Integer.valueOf(sewageid));
		model.addAttribute("sewage", sewage);
		//一下用于老系统中添加控制方式，江都系统不需要
		model.addAttribute("controlmethod", ControlMethod.getControlMethods());
		return "/areas/addnewsewage";
	}

	@RequestMapping("/checkSewageIdExist.do")
	public void checkSewageIdExist(@RequestBody Sewage sewage,HttpServletResponse response,HttpServletRequest request) throws IOException{
		int count = iSewageService.checkSewageIdExist(sewage.getSewageid());
		JsonMessage jsondata = new JsonMessage();
		if(count == 0){
			jsondata.setKey("pass");
		}else {
			jsondata.setKey("error");
		}

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsondata));
	}

	@RequestMapping("/gotoshowvideo.do")
	public String gotoshowvideo(){
		return "/video/newvideo";
	}

	@RequestMapping("/searchSewageidByOperatornum.do")
	public void searchSewageidByOperatornum(@RequestBody Sewage sewage,HttpServletResponse response,HttpServletRequest request) throws IOException{
		Sewage sewage2 = iSewageService.searchSewageidByOperatornum(sewage.getOperationnum());
		JsonMessage jsondata = new JsonMessage();
		if(sewage2!=null){
			jsondata.setKey("pass");
			jsondata.setData(sewage2);
		}else {
			jsondata.setKey("null");
		}

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsondata));
	}

	@RequestMapping("/getWithoutElectricBySewageid")
	public void getWithoutElectricBySewageid(@RequestBody Sewage sewage,HttpServletResponse response,HttpServletRequest request) throws IOException{
		Map<String,String> map = new HashMap<String, String>();
		map.put("sewageid",sewage.getSewageid().toString());
		Sewage sewage2 = iSewageService.getWithoutElectricBySewageid(map);
		JsonMessage jsondata = new JsonMessage();
		if(sewage2 != null){//如果不为空，那么说明断电断线，就不能pass
			jsondata.setKey("unpass");
		}else {
			jsondata.setKey("pass");
		}

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsondata));
	}

	@RequestMapping("/getRunDataBySewageid")
	public void getRunDataBySewageid(@RequestBody Sewage sewage,HttpServletResponse response,HttpServletRequest request) throws IOException{
		RunData latestRunDataInfo = iRunDataService.getLatestRunData(sewage.getSewageid());
		JsonMessage jsondata = new JsonMessage();
		if(latestRunDataInfo == null){//说明断电断线
			jsondata.setKey("unpass");
		}else {
			jsondata.setKey("pass");
			jsondata.setData(latestRunDataInfo);
		}

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsondata));
	}

	/**
	 * 江都 站点监控 中获取流量
	 * @param sewage
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/getEveryMonthWater.do")
	public void getEveryMonthWater(@RequestBody Sewage sewage,HttpServletResponse response,HttpServletRequest request) throws IOException{
		Map<String, String> map = new HashMap<String, String>();
		map.put("sewageid", String.valueOf(sewage.getSewageid()));

		DateTime date = new DateTime();
		map.put("testingtime", date.toString("yyyy-MM-dd"));


		List<StatisticDayVO> statisticDayVOs = iStatisticDayService.getSewageWaterStatisticMonth(map);
		JsonMessage jsondata = new JsonMessage();
		if(statisticDayVOs == null){//说明断电断线
			jsondata.setKey("unpass");
		}else {
			jsondata.setKey("pass");
			jsondata.setData(statisticDayVOs);
		}
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsondata, SerializerFeature.WriteNullNumberAsZero));
	}

	@RequestMapping("/getMapConfig.do")
	public void getMapConfig(HttpServletRequest request,HttpServletResponse response) throws IOException{

		String jsons = JSON.toJSONString(SystemConfig.siteMapConfig);

		response.setContentType("text/html; charset=UTF-8");

		response.getWriter().write(jsons);
	}
}
