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
	
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.min.js"></script>
	
	<script language="javascript" type="text/javascript" src="<%=basePath %>js/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>js/My97DatePicker/skin/WdatePicker.css">	

	
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/timeformat/timeformattool.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/adapterWindowJs/adapterwindow.js"></script>
	
	<script type="text/javascript" src="<%=basePath %>js/login/tooltips.js"></script>
<body>
	<table id="showForm" class="easyui-datagrid"  title="故障日志记录" style="width:100%;height:420px">
	
	</table>
	
	<div id="tb" style="padding:5px;height:auto;display:none">
		<div>
			地区名称：
			<select id="areaid" name="areaid">
				<option value="-1">请选择区县</option>
			</select>
			污水站点名称：
			<select id="sewageid" name="sewageid">
				<option value="-1">请选择水污水站</option>
			</select>
			<label id="sewagecontrolid" style="display:none"></label>
			运行编号：<input id="operationnum" type="text" style="width:120px"/>
			起始时间：<input id="begintime" type="text" style="width:120px" onfocus="new WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
			终止时间：<input id="endtime" type="text" style="width:120px" onClick="new WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
			<a id="search" href="#" class="easyui-linkbutton">查找</a>
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
$(document).ready(function(){
	//设置地区选择
	$.ajax({
		type : "POST",
		url : getContextPath()+"/warming/ajaxgetareas.do",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		async : true,
		success : function(returndata) {
		    $.each(returndata,function(index,data){
				var key = data.id;
	        	var value= data.name;
	        	$("#areaid").append("<option value='"+key+"'>"+value+"</option>");
			});
		}
	});
	
	//在这里加载横向
	$("#showForm").datagrid({"onLoadSuccess":function(data){
            if (data.total == 0) {
                //添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
                //$("#showForm").datagrid('appendRow', { equipmentname:'<div style="text-align:center;color:red">没有相关记录！</div>' });
                //$("#showForm").datagrid('mergeCells', { index: 0, field: 'equipmentname', colspan:4});
                //alert("没有相关记录");
                show_err_msg("没有相关记录");
            }
            //如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
            else $("#showForm").closest('div.datagrid-wrap').find('div.datagrid-pager').show();
	}});
	
	$("#areaid").change(function(){ 
		getSewageByareaId();
	});
	$("#sewageid").change(function(){ 
		getSewageInfoBySewageid();
	});
});

$('#showForm').datagrid({
    columns:[[
		{field:'sewageid',title:'runid',hidden:true},//"operationnum":"JY12SD25T-074","sewageid":474
		{field:'equipmentname',title:'设备名称',width:130,align:'center'},
		{field:'equipmentstate',title:'故障情况',width:130,align:'center',formatter: function(value,row,index){
				return "故障";
			}},
		{field:'testingtime',title:'发生时间',width:130,align:'center',formatter: function(value,row,index){
				if(typeof(row.testingtime)!="undefined"){
					return new Date(row.testingtime).format("yyyy-MM-dd hh:mm:ss");
			    }
			}},
		{field:'isrepaired',title:'故障状态',width:130,align:'center',formatter: function(value,row,index){
				if(row.isrepaired == 0){
					return "未修复";
			    }else{
			    	return "已修复";
			    }
			}},
    ]],
	fitColumns:true,
    idField:"detectionid",
    pagination:true,//显示分页  
    pageSize:20,//分页大小  
    pageList:[20,25,30],//每页的个数  
    toolbar: '#tb'
});

//获取站点信息列表
function getSewageByareaId(){
	var areaId = $("#areaid").val();
	var postUrl = getContextPath()+"/monitor/ajaxgetsewagebyareaid.do";
	var data = {"areaid":areaId};
	
	$("#sewageid").empty();

  	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(data),
		async:false,
		success:function(data){
			$("#sewageid").append("<option value='-1'>请选择水污水站</option>");
	        $.each(data,function(key,item){
	        	var key = item.sewageid;
	        	var value= item.name;
	        	$("#sewageid").append("<option value='"+key+"'>"+value+"</option>");
	        });			
		}
	});
}
function getSewageInfoBySewageid(){
	var sewageid = $("#sewageid").val();
	if(sewageid == -1){
		return;
	}
	var postUrl = getContextPath()+"/monitor/ajaxgetsewagebysewageid.do";
	var data = {"sewageid":sewageid};
	
  	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(data),
		async:false,
		success:function(data){
			if(data.key == "pass"){
				$("#sewagecontrolid").html(data.message);
			}
		}
	});
}


$("#search").click(function () {
	if($("#sewageid").val() == -1 && $("#operationnum").val() == "" ){
		$.messager.confirm('信息','请您选择污水站点或者输入运行编号','info');
		return ;
	}
	if($("#begintime").val() == "" || $("#endtime").val() == "" ){
		$.messager.confirm('信息','请您输入起始时间和终止时间','info');
		return ;
	}

	var url = getContextPath()+"/search/showequipmentabnormalrecord.do";
	
	$("#showForm").datagrid({
		"url":url,
		"queryParams":{
			sewageid:$("#sewageid").val(),
			begintime:$("#begintime").val(),
			endtime:$("#endtime").val(),
			operationnum:$("#operationnum").val()
		}	
	});
	$("#showForm").datagrid("load");
});

function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
</script>