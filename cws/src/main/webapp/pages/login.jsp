<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %> 
<%@ page language="java" import="com.yaoli.common.CustomPropertyConfigurer"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	//设置无缓存
	response.addHeader("Progma","no-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires",0);
	response.setHeader("Pragma", "no-cache");   
	response.setHeader("Cache-Control", "no-store");  
	response.setHeader("Cache-Control", "must-revalidate");
%>
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>

<meta charset="utf-8">
<title>登录</title>

<!-- CSS -->

<link rel="stylesheet" href="<%=basePath %>css/supersized.css">
<link rel="stylesheet" href="<%=basePath %>css/login.css">
<link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
<script src="<%=basePath %>js/login/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/login/tooltips.js"></script>
<script type="text/javascript" src="<%=basePath %>js/login/login.js"></script>
</head>

<body>

<div class="page-container">
	<div class="main_box">
		<div class="login_box">
			<div class="login_logo">
				<h1>用户登录</h1>
			</div>
			<div class="login_form">
				<form action="#" id="login_form" method="post">
					<div class="form-group">
						<label for="j_username" class="t">账　号：</label> 
						<input id="loginusername" value="" name="loginusername" type="text" class="form-control x319 in" 
						autocomplete="off">
					</div>
					<div class="form-group">
						<label for="j_password" class="t">密　码：</label> 
						<input id="loginuserpwd" value="" name="loginuserpwd" type="password" 
						class="password form-control x319 in">
					</div>
					<div class="form-group">
						<label for="j_captcha" class="t">验证码：</label>
						 <input id="logincode" name="logincode" type="text" class="form-control x164 in" value="">

						<img id="authCodeImg" class="m" alt="点击更换" title="点击更换"  src="" onclick="randomImg()"/>
						<a class="" href="javascript:;" onclick="randomImg()" >看不清</a>
					</div>
<!-- 					<div class="form-group">                                                                                                             
						<label class="t"></label>
						<label for="j_remember" class="m">	
					</div> -->
					<div class="form-group space">
						<label class="t"></label>　　　
						<button type="button"  id="submit_btn" class="btn btn-primary btn-lg">&nbsp;登&nbsp;录&nbsp </button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button"  id="reset_btn" class="btn btn-primary btn-lg" value="&nbsp;重&nbsp;置&nbsp"></input>
					</div>
					
				</form>
			</div>
				<% 
				Map<String, String> map = CustomPropertyConfigurer.getProperties();
				String systemno = map.get("systemno");
				%>
				
		      	<% 
				if(systemno.equals("1")){// 面源系统才使用一下图像
				%>
					<div>
					<p style="margin:auto;text-align:center;">
						<a href="http://www.miitbeian.gov.cn/publish/query/indexFirst.action" >网站备案号：苏ICP备16028655号-1</a>
					</p>
						
					</div>
				<%
				}
				%>

		</div>

	</div>

</div>

<!-- Javascript -->

<script src="<%=basePath %>js/login/supersized.3.2.7.min.js"></script>
<script src="<%=basePath %>js/login/supersized-init.js"></script>
<%-- <script src="<%=basePath %>js/login/scripts.js"></script> --%>
<%-- <script type="text/javascript" src="<%=basePath %>js/common/commonFunc.js"></script> --%>
<!-- ${baseUrlStatic} -->
<script type="text/javascript" src="<%=basePath %>js/system/login.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#reset_btn").on('click',function(){
		$("#loginusername").val("");
		$("#loginuserpwd").val("");
		$("#logincode").val("");
	});
	
});
</script>
</body>
</html>