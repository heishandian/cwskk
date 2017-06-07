package com.yaoli.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.yaoli.beans.SysMenu;
import com.yaoli.beans.SysRole;
import com.yaoli.beans.SysRoleMenu;
import com.yaoli.service.ISysMenusService;
import com.yaoli.service.ISysRoleService;
import com.yaoli.service.ISysUserService;
import com.yaoli.util.JsonMessage;
import com.yaoli.util.SysPagingUtil;
import com.yaoli.vo.RoleForm;


@Controller
@RequestMapping("/role")
public class RoleController {
	private static Logger logger = Logger.getLogger(RoleController.class);
	@Resource
	private ISysRoleService irolesService;
	
	@Resource
	private ISysUserService iSysUserService;
	
	@Resource
	private ISysMenusService iSysMenusService;
	
	@RequestMapping("/role.do")
	public String showRole(HttpServletRequest request,Model model){
		int id = Integer.parseInt(request.getParameter("id"));
		SysRole role = irolesService.getRoleById(id);
		model.addAttribute("role", role);
		return "/showrole";
	}
	
	@RequestMapping("/getallroles.do")
	public void getAllRoles(HttpServletResponse response,HttpServletRequest request) throws IOException{
		String pageSize =String.valueOf(request.getParameter("rows"));
		String pageNum = String.valueOf(request.getParameter("page"));
		
		/*以下代码只是看request.getParameter中有哪些参数*/
/*		for(Iterator iter = request.getParameterMap().entrySet().iterator();iter.hasNext();){ 
	        Map.Entry element = (Map.Entry)iter.next(); 
	        Object strKey = element.getKey();
	        Object strObj = element.getValue();
	        System.out.println(strKey+"  "+strObj);
		}*/
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		List<SysRole> list= irolesService.getRolesByPaging(map);
		int count = irolesService.getTotalCount();
		
		SysPagingUtil sysPagingUtil = new SysPagingUtil();
		
		sysPagingUtil.setTotal(String.valueOf(count));
		sysPagingUtil.setRows(list);
		
		
		
		
		
/*		sysPagingUtil.setPageNumber(pageNum);//page 当前页
		
		sysPagingUtil.setPageSize(String.valueOf(count/Integer.valueOf(pageSize)));//total 一共多少页
		
		sysPagingUtil.setTotal(String.valueOf(count));//records 一共多少条记录
*/		
		//sysPagingUtil.setPageNumber(pageNum);

		String jsondata = JSON.toJSONString(sysPagingUtil);

		response.setContentType("html/json;charset=UTF-8");
		response.getWriter().write(jsondata);
	}
	
	@RequestMapping("/gotoshowrole.do")
	public String gotoShowRoles(){
		return "/role/showroles";
	}
	
	@RequestMapping("/gotoaddnewrole.do")
	public String gotoAddnewRole(Model model){
		model.addAttribute("isModify", "no");
		return "/role/addnewrole";
	}
	
	@RequestMapping("/addnewrole.do")
	public void addNewRole(@RequestBody RoleForm roleForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		SysRole sysRole = new SysRole();
		sysRole.setName(roleForm.getName());
		int roleCount = irolesService.insert(sysRole);
		
		if(roleCount != 1){
			throw new Exception("插入新角色失败");
		}
		
		//根据用户名获取用户id，用户查询
		SysRoleMenu sysRoleMenu = new SysRoleMenu();
		
		//循环插入信息，这里应该用mybatis内置的功能，今后优化
		for (int i = 0; i < roleForm.getMeunIds().size(); i++) {
			sysRoleMenu.setMenuid(roleForm.getMeunIds().get(i));
			sysRoleMenu.setRoleid(sysRole.getId());
			int count2 = irolesService.insertRoleMenu(sysRoleMenu);
			if(count2 != 1){
				throw new Exception("插入新角色失败");
			}
		}
		
		JsonMessage json = new JsonMessage();
		json.setKey("pass");
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(json));
	}
	
	@RequestMapping("/deleteselectedusers.do")
	public void deleteRoles(@RequestBody RoleForm roleForm,HttpServletResponse response,HttpServletRequest request) throws Exception{
		List<Integer> delRoleIdList = roleForm.getDelRoleIds();
		
		//已经绑定用户的角色id，即那些不能删除的角色
		List<Integer> hasBindingUserRoles = new ArrayList<Integer>();
		
		
		//规定 如果 某个角色已经绑定了某个用户 那么这个角色删除不了
		for (int i = 0; i < delRoleIdList.size(); i++) {
			//if(delRoleIdList.get(i)
			if (irolesService.getRoleBindingUserCount(delRoleIdList.get(i)) > 0) {
				hasBindingUserRoles.add(delRoleIdList.get(i));
				//表明该id不能删除
			}else{
				//可以删除该角色
				int count = irolesService.deleteByPrimaryKey(delRoleIdList.get(i));
				//也要删除 角色和菜单的关联 sys rolemenu表
				int countRoleMenu = irolesService.deleteRoleMenuByRoleId(delRoleIdList.get(i));
				if(count != 1 && countRoleMenu == 0){
					throw new Exception("删除角色失败");
				}
			}
		}
		
		JsonMessage jsonMessage = new JsonMessage();
		jsonMessage.setKey("pass");
		jsonMessage.setData(hasBindingUserRoles);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(jsonMessage));
	}
	
	@RequestMapping("/gotoupdatarole.do")
	public String gotoUpdateNewRole(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("isModify", "yes");
		Integer roleid = Integer.valueOf(request.getParameter("roleid"));
		SysRole sysRole = irolesService.getRoleById(roleid);
		model.addAttribute("role", sysRole);
		//model.addAttribute("loginname", request.getParameter("loginname"));
		return "/role/addnewrole";
	}
	
	/**
	 * 跟新用户角色的时候所能够看到的目录，只能分配自己能看到的目录。而不能分配所有目录
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getupdatauserrolesmenus.do")
	public void updatauserroles(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer roleid = Integer.valueOf(request.getParameter("roleid"));
		
		//SysUser sysUser = iSysUserService.getUserByLoginName(loginname);
		
		//查询 用户能看到的 目录
		List<SysMenu> userMenus = iSysMenusService.getMenusByRoleId(roleid);
		
		//String json1 = JSON.toJSONString(userMenus);
		
		//所有目录的数量肯定大于用户能看到的目录
		List<SysMenu> menus = iSysMenusService.getAllMenus();
		
		//String json2 = JSON.toJSONString(menus);
		
		for (SysMenu menu : menus) {
			for (SysMenu userMenu : userMenus) {
				if(menu.getId().toString().equals(userMenu.getId().toString())){
					menu.setChecked(true);
				}
			}
		}
		response.setContentType("text/html; charset=UTF-8");
		String json = JSON.toJSONString(menus);
		response.getWriter().write(json);
		
	}
	
	@RequestMapping("/updaterole.do")
	public void updaterole(@RequestBody RoleForm roleForm,HttpServletRequest request,HttpServletResponse response) throws Exception{
		//现获取角色id
		Integer roleid = Integer.valueOf(request.getParameter("roleid"));
		
		SysRole sysRole = new SysRole();
		sysRole.setId(roleid);
		sysRole.setName(roleForm.getName());
		
		int updatecount = irolesService.updateByPrimaryKeySelective(sysRole);
		//删除所有关联menuid
		int count = irolesService.deleteRoleMenuByRoleId(roleid);
		
		//根据用户名获取用户id，用户查询
		SysRoleMenu sysRoleMenu = new SysRoleMenu();
		
		//循环插入信息，这里应该用mybatis内置的功能，今后优化
		for (int i = 0; i < roleForm.getMeunIds().size(); i++) {
			sysRoleMenu.setMenuid(roleForm.getMeunIds().get(i));
			sysRoleMenu.setRoleid(roleid);
			int count2 = irolesService.insertRoleMenu(sysRoleMenu);
			if(count2 != 1){
				throw new Exception("更新新角色失败");
			}
		}
		
		JsonMessage json = new JsonMessage();
		json.setKey("pass");
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(JSON.toJSONString(json));
	}
}
