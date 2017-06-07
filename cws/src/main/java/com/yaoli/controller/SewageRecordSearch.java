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
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.Area;
import com.yaoli.service.IAreaService;
import com.yaoli.service.ISewageService;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.ControlMethod;
import com.yaoli.vo.SewageVO;

@Controller
@RequestMapping("/srs")
public class SewageRecordSearch {
	@Resource
	public IAreaService isAreaService;
	
	@Resource
	public ISewageService iSewageService;
	
	
	@RequestMapping("/gotosewagerecordsearch.do")
	public String gotosewagerecordsearch(Model model){
		List<Area> allAreas = isAreaService.getAllAreas();
		model.addAttribute("allAreas", allAreas);
		return "/sewagesearch/sewagerecordsearch";
	}
	
	@RequestMapping("/sewagerecordsearch.do")
	public void sewagerecordsearch(HttpServletRequest request,HttpServletResponse response) throws IOException{
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
		
		List<SewageVO> sewages = iSewageService.getSewagerecordsearch(map);
		
		List<ControlMethod> controlMethods = ControlMethod.getControlMethods();
		for (SewageVO sewageVO : sewages) {
			for (ControlMethod controlMethod : controlMethods) {
				if(sewageVO.getControlmethod().equals(controlMethod.getId())){
					sewageVO.setControlMethodName(controlMethod.getName());
					break;
				}
			}
		}
		
		int count = iSewageService.getSewagerecordsearchCount(map);
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(sewages);

		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
}
