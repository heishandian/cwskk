<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>登录页面</title>
	<link href="<%=basePath %>css/newMainPageCss/style.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>css/newMainPageCss/button.css" rel="stylesheet" type="text/css" />
</head>

<body>


<div class="main">
	<div class="denglu">
		<div class="text" style="font-size:13px;"></div>
		<div class="dlk">
			<table border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<a href="http://www.sundaicws.com:8083/CWS_HS/system/index.do" target="_blank" class="myButton">惠山项目</a>
					</td>
					<td>
						<a href="http://61.147.198.178:8082/CWS_JD/system/index.do" target="_blank" class="myButton">江都项目</a>
					</td>

				</tr>
				<tr>
					<td>
						<a href="http://www.sundaicws.com:8083/CWS_XS/system/index.do" target="_blank" class="myButton">锡山项目</a>
					</td>
					<td>
						<a href="http://www.sundaicws.com:8082/CWS_HJ/system/index.do" target="_blank" class="myButton">邗江项目</a>
					</td>
				</tr>
				<tr>
					<td>
						<a href="http://www.sundaicws.com:8083/CWS_JY/system/index.do" target="_blank" class="myButton">江阴项目</a>
					</td>
					<td>
						<a href="http://www.sundaicws.com:8082/CWS_MY/system/index.do" target="_blank" class="myButton">面源项目</a>
					</td>
				</tr>
				<tr>
					<td>
						<a href="http://www.sundaicws.com:8083/CWS_SZ/system/index.do" target="_blank" class="myButton">苏州项目</a>
					</td>
					<td>
						<%--<a href="http://58.215.202.186:8082/CWS_WX/system/index.do" target="_blank" class="myButton">无锡项目</a>--%>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>



</body>
</html>
