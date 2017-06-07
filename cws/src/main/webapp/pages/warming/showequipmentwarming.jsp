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
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/easyui-lang-zh_CN.js"></script>
	
	<script type="text/javascript" src="<%=basePath %>js/timeformat/timeformattool.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/adapterWindowJs/adapterwindow.js"></script>

<body>
	<table id="showForm" class="easyui-datagrid"  title="设备故障报警" style="width:100%;height:420px">
	
	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div>
			<select id="areaid" name="areaid">
			</select>

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
		async : false,
		success : function(returndata) {
			$("#areaid").append("<option value='-1'>查找全部</option>");
		    $.each(returndata,function(index,data){
				var key = data.id;
	        	var value= data.name;
	        	$("#areaid").append("<option value='"+key+"'>"+value+"</option>");
			});
		}
	});
	$("#showForm").datagrid({"onLoadSuccess":function(data){
            if (data.total == 0) {
                //添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
                $("#showForm").datagrid('appendRow', { sewageName:'<div style="text-align:center;color:red">没有相关记录！</div>' });
                $("#showForm").datagrid('mergeCells', { index: 0, field: 'sewageName', colspan:4});
            }
            //如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
            else $("#showForm").closest('div.datagrid-wrap').find('div.datagrid-pager').show();
	}});
	
	
});

$('#showForm').datagrid({  
    //url:getContextPath()+'/warming/getequipmentabnormal.do',
    columns:[[
		{field:'runid',title:'runid',width:130,hidden:true},
		{field:'sewageName',title:'站点名称',width:130,align:'center'},
		{field:'testingtime',title:'检测时间',width:130,align:'center',formatter: function(value,row,index){
				if(typeof(row.testingtime)!="undefined"){
					return new Date(row.testingtime).format("yyyy-MM-dd hh:mm:ss");
			    }
			}},
		{field:'equipmentname',title:'故障设备名称',width:130,align:'center'},
		{field:'operationnum',title:'运营编号',width:130,align:'center'},
		{field:'lasttestingtime',title:'故障发生时间',width:130,align:'center',formatter: function(value,row,index){
			if(typeof(row.lasttestingtime)!="undefined"){
				return new Date(row.lasttestingtime).format("yyyy-MM-dd hh:mm:ss");
			}
		}},
    ]],
    fitColumns:true,
    idField:"runid",
    pagination:true,//显示分页  
    pageSize:20,//分页大小  
    pageList:[20,25,30],//每页的个数  
    toolbar: '#tb',
});

$("#search").click(function () {
	var url = getContextPath()+"/warming/getequipmentabnormal.do";
	$("#showForm").datagrid({
		"url":url,
		"queryParams":{
			areaid: $("#areaid").val()
		}	
	});
	$("#showForm").datagrid("load");
});
$("#resetPwd").click(function () {

});

function getequipmentstate(state){
	if(state == 1){
		return "运行";
	}else if(state == 2){
		return "停止";
	}else if(state == 3){
		return "<div style='text-align:center;color:red'>故障</div>";
	}
}



/*以下高度高度自适应*/
$('#showForm').datagrid('resize', {
	//width:function(){return document.body.clientWidth;},
	//height:function(){return document.body.clientHeight;},
});

function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
</script>