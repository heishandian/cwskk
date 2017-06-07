package com.yaoli.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yaoli.config.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.Area;
import com.yaoli.beans.Sewage;
import com.yaoli.service.IAreaService;
import com.yaoli.service.IDetectionDataAbnormalService;
import com.yaoli.service.IRunDataAbnormalService;
import com.yaoli.service.ISewageService;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.SewageVOUtils;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.DetectionDataAbnormalVO;
import com.yaoli.vo.RunDataAbnormalVO;

@Controller
@RequestMapping("/warming")
public class WarmingController {
	
	@Resource
	private IAreaService iAreaService;
	

	@Resource
	private IRunDataAbnormalService iRunDataAbnormalService;
	
	@Resource
	private IDetectionDataAbnormalService iDetectionDataAbnormalService;
	
	@Resource
	private ISewageService iSewageService;
	
	@RequestMapping("/gotoshowequipmentabnormal.do")
	public String gotoShowEquipmentAbnormal(HttpServletResponse response,HttpServletRequest request){
		return "/warming/showequipmentwarming";
	}
	
	@RequestMapping("/ajaxgetareas.do")
	public void ajaxGetAreas(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<Area> areas = iAreaService.getAllAreas();
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(areas));
	}
	

	/**
	 * 设备故障报警
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/getequipmentabnormal.do")
	public void getEquipmentAbnormal(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));

		String areaid = String.valueOf(request.getParameter("areaid")).equals("null")?null:String.valueOf(request.getParameter("areaid"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("areaid", areaid.equals("-1") ? null:areaid);
		
		List<RunDataAbnormalVO> runDataVOs = iRunDataAbnormalService.getEquipmentAbnormalByPagingAndConditionMap(map);
		int count = iRunDataAbnormalService.getEquipmentAbnormalByPagingAndConditionMapTotalCount(map);
		
		//Map<String, String> properties = CustomPropertyConfigurer.getProperties();
		for (RunDataAbnormalVO runDataAbnormalVO : runDataVOs) {
			runDataAbnormalVO.setEquipmentname(SystemConfig.equips.get("equipment"+runDataAbnormalVO.getEquipmentno()+"name"));
		}
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(runDataVOs);

		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	
	
	@RequestMapping("/gotohandleequipmentabnormal.do")
	public String gotoHandleEquipmentAbnormal(){
		return "/warming/showhandleequipmentabnormal";
	}
	
	/**
	 * 设备故障处理
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/handleequipmentabnormal.do")
	public void handleEquipmentAbnormal(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));

		String sewageid = SewageVOUtils.isBlank(request.getParameter("sewageid"))== true ? null:String.valueOf(request.getParameter("sewageid"));
		String begintime = SewageVOUtils.isBlank(request.getParameter("begintime"))== true ? null:String.valueOf(request.getParameter("begintime"));
		String endtime = SewageVOUtils.isBlank(request.getParameter("endtime"))== true ? null:String.valueOf(request.getParameter("endtime"));
		
		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("sewageid", sewageid);
		map.put("endtime", endtime);
		map.put("begintime", begintime);
		
		List<RunDataAbnormalVO> runDataVOs = iRunDataAbnormalService.selectByPaingAndCondition(map);
		int count = iRunDataAbnormalService.getHandlePagingAndConditionTotalCount(map);
		//Map<String, String> propertiesMap = CustomPropertyConfigurer.getProperties();
		for (RunDataAbnormalVO runDataAbnormalVO : runDataVOs) {
			runDataAbnormalVO.setEquipmentname(SystemConfig.equips.get("equipment"+runDataAbnormalVO.getEquipmentno()+"name"));
		}
		
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(runDataVOs);

		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	@RequestMapping("/updatehandleequipmentabnormal.do")
	public void updateHandleEquipmentAbnormal(@RequestBody List<RunDataAbnormalVO> runDataAbnormalVOs, HttpServletRequest request,HttpServletResponse response) throws Exception{
		JsonMessage jsonMessage = new JsonMessage();
		for (int i = 0; i < runDataAbnormalVOs.size(); i++) {
			String equipmentname = runDataAbnormalVOs.get(i).getEquipmentname();
			Long runid = runDataAbnormalVOs.get(i).getRunid();
			int count =iRunDataAbnormalService.updateHandleRunDataAbnormalByRunId(runDataAbnormalVOs.get(i).getRunid());
			if(count != 1){
				jsonMessage.setKey("error");
				String jsondata = JSON.toJSONString(jsonMessage);
				response.setContentType("html/json;charset=UTF-8");
				response.getWriter().write(jsondata);
				throw new Exception("更新rundataabnormal异常");
			}
		}
		
		jsonMessage.setKey("pass");
		String jsondata = JSON.toJSONString(jsonMessage);
		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	@RequestMapping("/gotoshowwaterwarming.do")
	public String gotoShowWaterWarming(){
		return "/warming/showwaterwarming";
	}
	
	/**
	 * 水质异常 
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping("/showwaterwarming.do")
	public void showWaterWariming(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));

		String areaid = String.valueOf(request.getParameter("areaid")).equals("null")?null:String.valueOf(request.getParameter("areaid"));
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);

		map.put("pageNum", pageNum);
		map.put("areaid", areaid.equals("-1") ? null:areaid);
		
		List<DetectionDataAbnormalVO> detectionDataAbnormalVOs = iDetectionDataAbnormalService.selectByPaingAndCondition(map);
		int count = iDetectionDataAbnormalService.getPaingAndConditionTotal(map);
		
		//Map<String, String> properties = CustomPropertyConfigurer.getProperties();
		
/*		for (DetectionDataAbnormalVO detectionDataAbnormalVO : detectionDataAbnormalVOs) {
			detectionDataAbnormalVO.setWaterparametername(properties.get("detection"+detectionDataAbnormalVO.getDetectionno()+"name"));
		}*/
		for (int i = 0; i < detectionDataAbnormalVOs.size(); i++) {
			DetectionDataAbnormalVO temp = detectionDataAbnormalVOs.get(i);
			Byte index = temp.getDetectionno();
			detectionDataAbnormalVOs.get(i).setWaterparametername(SystemConfig.detections.get("detection"+index.toString()+"name"));
		}
		
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(detectionDataAbnormalVOs);

		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	@RequestMapping("/gotoshowwithoutelectricwarming.do")
	public String gotoShowWithoutElectricwWarming(){
		return "/warming/showwithoutelectricwarming";
	}
	
	@RequestMapping("/getwithoutelectricwarming.do")
	public void getwithoutelectricwarming(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));

		String areaid = String.valueOf(request.getParameter("areaid")).equals("null")?null:String.valueOf(request.getParameter("areaid"));
		areaid = (areaid == null ? null : areaid.equals("-1") ? null:areaid );

		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		map.put("areaid", areaid);
		
		List<Sewage> withoutEletrictAndWater = iSewageService.getWithoutElectricAndWaterByPaingAndCondition(map);//断电断线 今天没有数据就是有问题
		int count = iSewageService.getWithoutElectricAndWaterPaingAndConditionTotal(map);
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(withoutEletrictAndWater);

		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
}
