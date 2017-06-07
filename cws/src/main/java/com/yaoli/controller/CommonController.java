package com.yaoli.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {

	@RequiresPermissions("system:admin:view")
	@RequestMapping("/error.do")
	public String toErrorPage() {
		return "common/error";
	}

	@RequestMapping("/jq.do")
	public String todosentExistPage() {
		return "/warming/jq";
	}
	@RequiresPermissions("system:admin:view")
	@RequestMapping("/login.do")
	public String toLoginPage() {
		return "common/login";
	}
	
	@RequestMapping("/seehelp.do")
	public String seeHelp(){
		return "/other/help";
	}
	
	@RequestMapping("/contactus.do")
	public String gotoContactus(){
		return "/other/contactus";
	}

}
