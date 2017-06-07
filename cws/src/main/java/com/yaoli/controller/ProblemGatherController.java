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
import com.yaoli.beans.ProblemGather;
import com.yaoli.service.IAreaService;
import com.yaoli.service.IProblemGatherService;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.ProblemGatherVO;

@Controller
@RequestMapping("/pgc")
public class ProblemGatherController {
	
	@Resource
	IAreaService iAreaService;
	
	@Resource
	IProblemGatherService iProblemGatherService;
	
	@RequestMapping("/gotoaddProblemGather.do")
	public String gotoProblemGather(Model model){
		List<Area> areas = iAreaService.getAllAreas();
		model.addAttribute("allAreas", areas);
		return "/problemGather/addProblemGather";
	}
	
	@RequestMapping("/addProblemGather.do")
	public void addProblemGather(@RequestBody ProblemGather problemGather,HttpServletRequest request,HttpServletResponse response)throws IOException{
		int count = iProblemGatherService.insertSelective(problemGather);
		
		JsonMessage jsonMessage = new JsonMessage();
		
		if(count == 1){
			jsonMessage.setKey("pass");
		}else {
			jsonMessage.setKey("error");
		}
		
		response.setContentType("html/json;charset=UTF8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
		
	}
	
	@RequestMapping("/gotoshowProblemGather.do")
	public String gotoshowProblemGather(Model model){
		List<Area> areas = iAreaService.getAllAreas();
		model.addAttribute("allAreas", areas);
		return "/problemGather/showProblemGather";
	}
	
	@RequestMapping("/showProblemGather.do")
	public void showProblemGather(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));

		String sewageid = String.valueOf(request.getParameter("sewageid")).equals("")?null:String.valueOf(request.getParameter("sewageid"));
		String areaid = String.valueOf(request.getParameter("areaid")).equals("")?null:String.valueOf(request.getParameter("areaid"));
		
		String operationnum = String.valueOf(request.getParameter("operationnum")).equals("")?null:String.valueOf(request.getParameter("operationnum"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("sewageid", sewageid);
		map.put("areaid", areaid);
		map.put("operationnum", operationnum);
		
		List<ProblemGatherVO> problemGatherVOs = iProblemGatherService.getProblemGatherVOsByCondition(map);
		
		int count = iProblemGatherService.getProblemGatherVOsCountByCondition(map);
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(problemGatherVOs);

		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	@RequestMapping("/ajaxLookDescription.do")
	public void ajaxLookDescription(HttpServletRequest request,HttpServletResponse response) throws IOException{
		ProblemGather problemGather = iProblemGatherService.selectByPrimaryKey(Integer.valueOf(request.getParameter("id")==null?"-1":request.getParameter("id")));
		
		JsonMessage jsonMessage = new JsonMessage();
		
		if(problemGather != null){
			jsonMessage.setKey("pass");
			jsonMessage.setData(problemGather);
		}else {
			jsonMessage.setKey("error");
		}
		
		response.setContentType("html/json;charset=UTF8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
	}

	@RequestMapping("/ajaxdeleteByid.do")
	public void ajaxdeleteByid(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer count = iProblemGatherService.deleteByPrimaryKey(Integer.valueOf(request.getParameter("id")==null?"-1":request.getParameter("id")));

		JsonMessage jsonMessage = new JsonMessage();

		if(count == 1){
			jsonMessage.setKey("pass");
		}else {
			jsonMessage.setKey("error");
		}

		response.setContentType("html/json;charset=UTF8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
	}
}
