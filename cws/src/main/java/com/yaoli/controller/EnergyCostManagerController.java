package com.yaoli.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.Area;
import com.yaoli.service.IAreaService;
import com.yaoli.service.IStatisticDayService;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.SewageVOUtils;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.DetectionDataVO;
import com.yaoli.vo.SewageVO;
import com.yaoli.vo.StatisticDayVO;

/**
 * SewageEnergyCost -- sec
 * @author will
 *
 */
@Controller
@RequestMapping("/sec")
public class EnergyCostManagerController {
	
	@Resource
	private IAreaService iAreaService;
	
	@Resource
	private IStatisticDayService iStatisticDayService;

	@RequestMapping("/gotosewageenergycost.do")
	public String gotosewageenergycost(Model model){
		List<Area> areas = iAreaService.getAllAreas();
		model.addAttribute("areas", areas);
		return "/energyCostManager/energySearch";
	}
	
	@RequestMapping("/gotohistoryenergymonthsearch.do")
	public String gotohistoryenergymonthsearch(Model model){
		List<Area> areas = iAreaService.getAllAreas();
		model.addAttribute("areas", areas);
		return "/energyCostManager/energyCostMonth";
	}
	
	@RequestMapping("/gotohistoryenergyyearsearch.do")
	public String gotohistoryenergyyearsearch(Model model){
		List<Area> areas = iAreaService.getAllAreas();
		model.addAttribute("areas", areas);
		return "/energyCostManager/energyCostYear";
	}
	
	
	@RequestMapping("/sewageenergycost.do")
	public void sewageenergycost(@RequestBody DetectionDataVO detectionDataVO, HttpServletRequest request,HttpServletResponse response)throws IOException{
		Integer sewageid =detectionDataVO.getSewageid()==-1?null:detectionDataVO.getSewageid(); 
		DateTime testingtime =new DateTime(detectionDataVO.getTestingtime());
		String operationnum = detectionDataVO.getOperationnum().equals("")?null:detectionDataVO.getOperationnum();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("testingtime", testingtime.toString("yyyy-MM-dd"));
		map.put("sewageid", String.valueOf(sewageid));
		map.put("operationnum", operationnum);
		map.put("type", "day");
		
		List<StatisticDayVO> statisticDayVOs = null;
		
		statisticDayVOs = iStatisticDayService.getEnergycostsByDay(map);

		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setData(statisticDayVOs.get(0));
		
		String jsondata = JSON.toJSONString(jsonMessage);
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	/**
	 * 历史能耗月度查询
	 * @param detectionDataVO
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/historyenergymonthsearch.do")
	public void historyenergymonthsearch(@RequestBody DetectionDataVO detectionDataVO,HttpServletRequest request,HttpServletResponse response)throws IOException{
		Integer sewageid =detectionDataVO.getSewageid()==-1?null:detectionDataVO.getSewageid(); 
		DateTime testingtime =new DateTime(detectionDataVO.getTestingtime());
		String operationnum = detectionDataVO.getOperationnum().equals("")?null:detectionDataVO.getOperationnum();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("testingtime", testingtime.toString("yyyy-MM-dd"));
		map.put("sewageid", String.valueOf(sewageid));
		map.put("operationnum", operationnum);
		map.put("type", "day");
		
		List<StatisticDayVO> statisticDayVOs = null;
		
		statisticDayVOs = iStatisticDayService.gethistoryenergymonthsearch(map);

		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setData(statisticDayVOs);
		
		String jsondata = JSON.toJSONString(jsonMessage);
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	/**
	 * 年度变化曲线
	 * @param detectionDataVO
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/historyenergyyearsearch.do")
	public void historyenergyyearsearch(@RequestBody DetectionDataVO detectionDataVO,HttpServletRequest request,HttpServletResponse response)throws IOException{
		Integer sewageid =detectionDataVO.getSewageid()==-1?null:detectionDataVO.getSewageid(); 
		DateTime testingtime =new DateTime(detectionDataVO.getTestingtime());
		String operationnum = detectionDataVO.getOperationnum().equals("")?null:detectionDataVO.getOperationnum();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("testingtime", testingtime.toString("yyyy-MM-dd"));
		map.put("sewageid", String.valueOf(sewageid));
		map.put("operationnum", operationnum);
		map.put("type", "day");
		
		List<StatisticDayVO> statisticDayVOs = null;
		
		statisticDayVOs = iStatisticDayService.gethistoryenergyyearsearch(map);

		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setData(statisticDayVOs);
		
		String jsondata = JSON.toJSONString(jsonMessage);
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
}
