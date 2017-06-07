<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	//设置无缓存
	response.addHeader("Progma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Cache-Control", "must-revalidate");
	//response.sendRedirect("http://localhost:8080/CWS/system/login.do");
%>

<!DOCTYPE html>
<html>
<head>
<title>首页</title>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css" />

	<!-- 消息弹出层引用css -->
	<link rel="stylesheet" href="<%=basePath%>css/popWindow/reveal.css">

<script type="text/javascript" src="<%=basePath%>js/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-ui-1.8.16.custom.min.js"></script>

	<!-- 消息弹出层引用js -->
	<script type="text/javascript" src="<%=basePath%>js/popWindow/jquery.reveal.js"></script>
<script type="text/javascript">
window.onscroll = function() {
	screenAdapter();
};
window.onresize = function() {
	screenAdapter();
};
$(document).ready(function(){
	screenAdapter();
});
function screenAdapter() {
	document.getElementById('navigator').style.height = document.documentElement.clientHeight - 150 + "px";
	document.getElementById('main').style.height = document.documentElement.clientHeight - 150 + "px";
}
</script>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<div id="logo"></div>
			<table style="border:1px">
				<tr style="height:38px;">
					<td></td>
					<td><!-- <br />时间 --></td>
				</tr>
				<tr style="height:50px;">
					<td width="450px" align="center" 
						style="vertical-align:middle;font-size:25px;align:left;text-shadow:Red;font-family:'黑体';">智慧型农村水环境治理监管综合系统</td>
					<td>
						<%-- <img src="<%=basePath%>images/sdnum_1.jpg"> --%>
						
						<a href="http://www.sunda.zj.cn/" style="cursor:pointer" target="blank"><img src="<%=basePath%>images/sdnum_1.jpg"></a>
						<a href="<%=basePath%>common/seehelp.do" style="cursor:pointer" target="MainFrame"><img src="<%=basePath%>images/sdnum_2.jpg"></a>
						<a href="<%=basePath%>common/contactus.do" style="cursor:pointer" target="MainFrame"><img src="<%=basePath%>images/sdnum_3.jpg"></a>
						<a href="<%=basePath%>system/outlogin.do" style="cursor:pointer"><img src="<%=basePath%>images/sdnum_4.jpg"></a>   	
					</td>
				</tr>
			</table>
		</div>


	</div>

		<div id="navigator">
			<iframe src="<%=basePath%>system/gotomenus.do"></iframe>
		</div>

		<div id="main">
			<iframe name="MainFrame" src="<%=basePath%>monitor/intomapmonitor.do" ></iframe>
		</div>

		<div id="footer">
			<p align="left">
			用户名：${user.username }
			<c:choose>
				<c:when test="${user.department !=null }">
					<c:out value="；部门：${user.department}"></c:out>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${user.telephone !=null }">
					<c:out value="；联系电话：${user.telephone}"></c:out>
				</c:when>
			</c:choose>

			</p>
		</div>

	<!-以下用于弹出框->
	<a id="popWindowHref" href="#" class="big-link" data-reveal-id="popWindow" data-animation="none" style="visibility:hidden" >
	</a>
	<div id="popWindow" class="reveal-modal">
		<%--<h1>消息</h1>--%>
		<p id="popContent"></p>
		<%--<a class="close-reveal-modal">&#215;</a>--%>
	</div>
</body>

<script type="text/javascript">
window.onload = function(){
	setTimeout(popWindowMessage,2500);
};

function popWindowMessage(){
	var postUrl = getContextPath()+"/infoPub/getSearchNoticePublish.do";
	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		success:function(data){
			if(data.length != 0){
				var content = "";
				for(var i = 0; i< data.length ; i++){
					content += (i+1) +":"+ data[i].publishcontent+"<br/>";
				}

				$("#popContent").html(content);
				$("#popWindowHref").trigger("click");
			}
		}
	});

}
function getContextPath() {
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0,index+1);
	return result;
}
</script>
</html>