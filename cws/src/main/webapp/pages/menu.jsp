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

<!DOCTYPE html>
<html>
<head>
	<title>导航树</title>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.2.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/ztree/zTreeStyle.css" />
	
	
	<script type="text/javascript" src="<%=basePath %>js/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/ztree/jquery.ztree.excheck-3.5.js"></script>	
	
</head>
<body>
<style type="text/css" >
* {
    background: none repeat scroll 0 0 transparent;
    border: 0 none;
    margin: 0;
    padding: 0;
    vertical-align: baseline;
	font-family:微软雅黑;
	overflow:hidden;
}
#menuControll{
	width:100%;
	position:relative;
	word-wrap:break-word;
	border-bottom:1px solid #065FB9;
	margin:0;
	padding:0 10px;
	font-size:14px;
	height:40px;
	line-height:40px;
	vertical-align:middle;
    background-image: -moz-linear-gradient(top,#EBEBEB, #BFBFBF);
    background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #EBEBEB),color-stop(1, 
#BFBFBF));
}
</style>
<div id="menuControll">
用户菜单【<a id='expand' href="#" onclick="expandOrclose()" style="color:#333333;text-decoration:none">折叠</a>】
</div>
<div id="treediv">
<ul id="menuTree" class="ztree"></ul>
</div>
</body>
<script type="text/javascript">
var treeObj;
var setting = {
	data: {
		simpleData: {
			enable: true
		}
	}
};
$(document).ready(function(){
	var postUrl = getContextPath()+"/system/getmenus.do";
	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		success:function(data){
			$.fn.zTree.init($("#menuTree"), setting, data.data);
			treeObj = $.fn.zTree.getZTreeObj("menuTree");
			treeObj.expandAll(true);
		}
	});
	adapterWindow();
});
function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
var isExpand = true;
function expandOrclose(){
	if(isExpand == true){
		treeObj.expandAll(false);
		$("#expand").html("展开");
		isExpand = false;
	}else{
		treeObj.expandAll(true);
		$("#expand").html("折叠");
		isExpand = true;
	}
	adapterWindow();
}

function adapterWindow(){
	var windowHeight = $(window).height();
	var titleHeight = $("#menuControll").outerHeight(true);
	var leftHeight = windowHeight - titleHeight;
	
	$("#treediv").css("height", leftHeight);
	$("#treediv").css("overflowY", "auto");
}
$(window).resize(function() {
  adapterWindow();
});
</script>
</html>