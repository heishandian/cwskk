package com.yaoli.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.Area;
import com.yaoli.beans.WaterTestManager;
import com.yaoli.service.IAreaService;
import com.yaoli.service.IWaterTestManagerService;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.WaterTestManagerVO;

@Controller
@RequestMapping("/wtm")
public class WaterTestManagerController {
	
	@Resource
	public IAreaService isAreaService;
	
	@Resource
	public IWaterTestManagerService iWaterTestManagerService;
	

	
	/**
	 * 进入化验结果录入
	 * @param model
	 * @return
	 */
	@RequestMapping("/gotoaddtestresult.do")
	public String gotoaddtestresult(Model model){
		List<Area> allAreas = isAreaService.getAllAreas();
		model.addAttribute("allAreas", allAreas);
		
		return "/watertestmanager/addtestresult";
	} 
	
	/**
	 * 进入历史记录查询
	 * @param model
	 * @return
	 */
	@RequestMapping("/gotosearchtesthistory.do")
	public String gotosearchtesthistory(Model model){
		List<Area> allAreas = isAreaService.getAllAreas();
		model.addAttribute("allAreas", allAreas);
		
		return "/watertestmanager/showsearchtesthistory";
	} 
	
	/**
	 * 进入历史记录绘图
	 */
	@RequestMapping("/gotosearchtesthistorycurve.do")
	public String gotosearchtesthistorycurve(Model model){
		List<Area> allAreas = isAreaService.getAllAreas();
		model.addAttribute("allAreas", allAreas);
		
		return "/watertestmanager/historyCurve";
	} 
	
	/**
	 * 增加历史记录
	 * @param record
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/addtestresult.do")
	public void addtestresult(@RequestBody WaterTestManager record,HttpServletRequest request,HttpServletResponse response) throws IOException{
		int count = iWaterTestManagerService.insertSelective(record);
		JsonMessage jsonMessage = new JsonMessage();
		if(count == 1){
			jsonMessage.setKey("pass");
		}
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
	}
	
	@RequestMapping("/searchtesthistory.do")
	public void searchtesthistory(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));

		String sewageid = String.valueOf(request.getParameter("sewageid")).equals("")?null:String.valueOf(request.getParameter("sewageid"));
		String areaid = String.valueOf(request.getParameter("areaid")).equals("")?null:String.valueOf(request.getParameter("areaid"));
		
		String begintime = String.valueOf(request.getParameter("begintime")).equals("")?null:String.valueOf(request.getParameter("begintime"));
		String endtime = String.valueOf(request.getParameter("endtime")).equals("")?null:String.valueOf(request.getParameter("endtime"));

		String operationnum = String.valueOf(request.getParameter("operationnum")).equals("")?null:String.valueOf(request.getParameter("operationnum"));

		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("sewageid", sewageid);
		map.put("areaid", areaid);
		map.put("begintime", begintime);
		map.put("endtime", endtime);
		map.put("operationnum", operationnum);
		
		List<WaterTestManagerVO> list = iWaterTestManagerService.getWaterTestManagerVOsByCondition(map);
		int count = iWaterTestManagerService.getWaterTestManagerVOsCountByCondition(map);
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(list);

		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	@RequestMapping("/ajaxHistoryCurveXToken.do")
	public void ajaxHistoryCurveXToken(@RequestBody WaterTestManager record,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String sewageid =String.valueOf(record.getSewageid());
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("sewageid", sewageid);
		
		List<WaterTestManagerVO> list = iWaterTestManagerService.getTop15WaterTestManager(map);

		JsonMessage jsondata = new JsonMessage();
		jsondata.setData(list);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsondata));
	}
	
	@RequestMapping("/getHistoryCurve.do")
	public void getHistoryCurve(@RequestBody WaterTestManager record,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String sewageid =String.valueOf(record.getSewageid());
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("sewageid", sewageid);
		
		List<WaterTestManagerVO> list = iWaterTestManagerService.getTop15WaterTestManager(map);

		JsonMessage jsondata = new JsonMessage();
		jsondata.setData(list);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsondata));
	}
}
