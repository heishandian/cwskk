<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.yaoli.common.CustomPropertyConfigurer"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
<html>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">
	<!-- 必须添加这个 -->
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lro9rUlcpaFKtWd1F19DuPPs"></script>
	<script type="text/javascript" src="<%=basePath%>js/spin/spin.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/common_main.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main_main.css" />
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/map/gmap.js"></script>
    
    <script type="text/javascript" src="<%=basePath%>js/spin/spin.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/spin/spinconf.js"></script>


	<style type="text/css">
	body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
<body>
<!-- 系统表示号码 -->
<input type="hidden" id="systemno" value="<% Map<String, String> map = CustomPropertyConfigurer.getProperties();out.print(map.get("systemno")); %>"></input>
	<div class="search-content">
		<form action="#" method="post">
			<table class="search-tab">
				<tr>
					<!-- <th width="70">关键字:</th> -->
					<td><input class="common-text" placeholder="请输入地点简称或者运营号" name="keywords" id="keywords" value="" id="" type="text">
					</td>
					<td><input class="btn" name="seachpoint" id="seachpoint" value="搜索" type="button">
					</td>
					<td><select style="width:200px" name="searchlist_areaid" id="searchlist_areaid">
						<c:forEach items="${allAreas}" var="item">
							<option value="${item.id}">${item.name}</option>
						</c:forEach>
					</select>
					</td>
					<td><input class="btn" name="seachlist" id="seachlist" value="选定" type="button">
					<%-- <shiro:hasRole name="8"> --%>
						<input type="checkbox" id="showtruemap" name="showtruemap"><!-- 展示真实的页面 -->
					<%-- </shiro:hasRole> --%>
						<%--<input class="btn" name="zhuapai" id="zhuapai" value="图像抓拍" type="button" onclick="javascript:window.open('http://115.236.191.50:9090/SWS/pages/Country/Media/atuoLoginPicture.jsp?dbname=SWS_COUNTRY_TEST&username=admin')">--%>
					<label id="abnormalrate">设备故障率0.00%</label>
					
					<label id="waterrate">水质异常率0.00%</label>

					<label id="withoutElectric">断电断线率0.00%</label>
				</tr>
			</table>
		</form>
	</div>
<div id="allmap">
</div>
<div id = "waiting"></div>
</body>
</html>