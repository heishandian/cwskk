package com.yaoli.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.SysMenu;
import com.yaoli.beans.SysUser;
import com.yaoli.service.ISysMenusService;
import com.yaoli.service.ISysUserService;
import com.yaoli.util.JsonMessage;

@Controller
@RequestMapping("/system")
public class MenuController {
	private static Logger logger = Logger.getLogger(MenuController.class);
	
	@Resource
	ISysUserService iSysUserService ;
	
	@Resource
	ISysMenusService iSysMenusService;
	
	@RequestMapping("/gotomenus.do")
	public String gotoMenus(){
		return "/menu";
	}
	
	@RequestMapping("/getmenus.do")
	public void getMenusByUser(HttpServletResponse response,HttpServletRequest request) throws IOException{
		Subject subject = SecurityUtils.getSubject();
		
		//获取用户名
		String userLoginName = subject.getPrincipal().toString();
		
		//根据用户名获取用户id，用户查询
		SysUser sysUser = iSysUserService.getUserByLoginName(userLoginName);
		
		//根据用户id获取用户的所有菜单
		List<SysMenu> sysMenus = iSysMenusService.getMenusByUserId(sysUser.getId());
		
		JsonMessage jsonMessage = new JsonMessage();
		
		if(sysMenus == null){
			jsonMessage.setKey("error");
		}else {
			jsonMessage.setKey("pass");
			//<rich:treeNode icon="/images/treeNode.png" iconLeaf="/images/treeLeaf.png" iconExpanded="/images/nolines_minus.gif" iconCollapsed="/images/nolines_plus.gif">
			//通过算法写成dtree需要的格式
        	//{ level:1, name:"用户管理"},
        	//{ level:2, name:"用户列表", ico:"images/icon_default.gif",link:"user_list.html"},
			//List<SysMenuVO> sysMenuVOs = new ArrayList<SysMenuVO>();
			//SysMenuVO sysMenuVO = null;
			
			//图片地址  components/dtree/img/page.gif
			String ico = "components/dtree/img/page.gif";
			String contextPath = request.getContextPath();
			
			for (SysMenu sysMenu : sysMenus) {
				if(sysMenu.getUrl() != null){
					sysMenu.setUrl(contextPath+sysMenu.getUrl());
					sysMenu.setTarget("MainFrame");
				}
			}
			jsonMessage.setData(sysMenus);
		}
		
		logger.debug(JSON.toJSONString(jsonMessage));
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
	}
	
	@RequestMapping("/getallmenus.do")
	public void getAllMenus(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<SysMenu> menus = iSysMenusService.getAllMenus();
		response.setContentType("text/html; charset=UTF-8");
		String json = JSON.toJSONString(menus);
		response.getWriter().write(json);
	}

	@RequestMapping("/gotoaddattachment.do")
	public String gotoaddattachment(HttpServletRequest request,HttpServletResponse response){
		return "/attachment/addattachment";
	}
	
	
	@RequestMapping("/addattachment.do")
	public void addattachment(HttpServletRequest request,HttpServletResponse response){

	}
}
