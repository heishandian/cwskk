<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
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
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/easyui/themes/icon.css">
	
	<script language="javascript" type="text/javascript" src="<%=basePath %>js/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>js/My97DatePicker/skin/WdatePicker.css">	
	
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/timeformat/timeformattool.js"></script>
	
	<script type="text/javascript" src="<%=basePath %>js/adapterWindowJs/adapterwindow.js"></script>
<body>
	<table id="showForm" class="easyui-datagrid" title="用户登录列表" style="width:100%;height:420px">
	</table>
	<div id="tb" style="padding:5px;height:auto;display:none">
		<div>
			用户登录名：<input id="username" type="text" style="width:120px"/>
			起始时间：<input id="begintime" type="text" style="width:120px" onfocus="new WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
			终止时间：<input id="endtime" type="text" style="width:120px" onClick="new WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
			<a id="search" href="#" class="easyui-linkbutton">查找</a>
			<label id="sumTimes" ></label>
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
$('#showForm').datagrid({  
    //url:getContextPath()+'/message/getmessages.do',
    columns:[[
		{field:'id',title:'id',width:130,hidden:true},
		{field:'username',title:'用户姓名',width:130,align:'center'},
		{field:'loginname',title:'用户登录名',width:130,align:'center'},
		{field:'userlogintime',title:'用户登录时间',width:150,align:'center',formatter: function(value,row,index){
				if(typeof(row.userlogintime)!="undefined"){
					return new Date(row.userlogintime).format("yyyy-MM-dd hh:mm:ss");
			    }
			}}
    ]],
    fitColumns:true,
    idField:"id",
    pagination:true,//显示分页  
    pageSize:20,//分页大小  
    pageList:[20,25,30],//每页的个数  
    toolbar: '#tb',
});


$("#search").click(function () {
/* 	if($("#begintime").val() == "" || $("#endtime").val() == "" ){
		$.messager.confirm('信息','请您输入起始时间和终止时间','info');
		return;
	} */
	var url = getContextPath()+"/userlogin/getUserLoginRecord.do";
	
	$("#showForm").datagrid({
		"url":url,
		"queryParams":{
			begintime:$("#begintime").val(),
			endtime:$("#endtime").val(),
			username:$("#username").val()
		}		
	});
	$("#showForm").datagrid("load");


	//获取用户登录多少次
	var getUserLoginTimes = getContextPath()+"/userlogin/getUserLoginTimes.do";

	var sumTimes = $("#sumTimes").val();
	var postUrl = getUserLoginTimes
	var data = {"begintime":$("#begintime").val(),endtime:$("#endtime").val(),username:$("#username").val()};

	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(data),
		async:true,
		success:function(data){
			$("#sumTimes").html(data);
		}
	});
});

function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
</script>