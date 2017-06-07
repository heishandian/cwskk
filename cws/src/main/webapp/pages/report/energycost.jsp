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
	
	<script type="text/javascript" src="<%=basePath %>js/login/tooltips.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/easyui-lang-zh_CN.js"></script>
	
	<script type="text/javascript" src="<%=basePath %>js/timeformat/timeformattool.js"></script>
	
	<script language="javascript" type="text/javascript" src="<%=basePath %>js/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>js/My97DatePicker/skin/WdatePicker.css">	

	<script type="text/javascript" src="<%=basePath %>js/adapterWindowJs/adapterwindow.js"></script>
<body>
	<!-- 能耗成本 -->
	<table id="showForm" class="easyui-datagrid"  title="能耗成本" style="width:100%;height:500px">
	
	</table>
	
	<div id="tb" style="padding:5px;height:auto;display:none">
		<div>
			请选择报表类型
	      	<select style="width:150px" name="reporttype" id="reporttype">
	      			<option value="1">能耗成本日报</option>
	      			<option value="2">能耗成本月报</option>
	      			<option value="3">能耗成本年报</option>
			</select>
			请选择日期：<input id="testingtime" type="text" style="width:120px" onfocus="new WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
	      	<select style="width:200px" name="areaid" id="areaid">
	      			<option value="">请选择区县</option>
					<c:forEach items="${allAreas}" var="item">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
			</select>
	      	<select style="width:200px" name="sewageid" id="sewageid">
	      			<option value="">请选择站点</option>
			</select>
			<a id="search" href="#" class="easyui-linkbutton">查找</a>

			<!-- <a id="downloadexcel" href="#" class="easyui-linkbutton">导出excel</a> -->
			
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
$('#showForm').datagrid({  
    columns:[[ //、、、、
		{field:'sewagename',title:'污水站点名称',align:'center',width:130},
		{field:'equipment1energycost',title:'曝气机能耗',align:'center',width:130,formatter: function(value,row,index){
				if(typeof(value)!="undefined"){
					return value.toFixed(2);
			    }
			}},
		{field:'equipment2energycost',title:'提升泵能耗',align:'center',width:130,formatter: function(value,row,index){
				if(typeof(value)!="undefined"){
					return value.toFixed(2);
			    }
			}},
		{field:'equipment3energycost',title:'回流泵能耗',align:'center',width:130,formatter: function(value,row,index){
				if(typeof(value)!="undefined"){
					return value.toFixed(2);
			    }
			}},
		{field:'equipment4energycost',title:'加药泵能耗',align:'center',width:130,formatter: function(value,row,index){
				if(typeof(value)!="undefined"){
					return value.toFixed(2);
			    }
			}},
		{field:'equipment5energycost',title:'排水泵能耗',align:'center',width:130,formatter: function(value,row,index){
			if(typeof(value)!="undefined"){
				return value.toFixed(2);
			}
		}},
		{field:'totalcost',title:'累计能耗',align:'center',width:130,formatter: function(value,row,index){
			if(typeof(value)!="undefined"){
				return value.toFixed(2);
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
$(document).ready(function(){
	$("#reporttype").change(function(){ 
		//getSewageByareaId();
		reporttypeChange();
	});
	$("#areaid").change(function(){ 
		getSewageByareaId();
	});
});
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
			$("#sewageid").append("<option value=''>请选择水污水站</option>");
	        $.each(data,function(key,item){
	        	var key = item.sewageid;
	        	var value= item.name;
	        	$("#sewageid").append("<option value='"+key+"'>"+value+"</option>");
	        });			
		}
	});
}
function reporttypeChange(){
	var reporttype = $("#reporttype").val();
	if(reporttype == 1){
		$("#testingtime").attr("onfocus","new WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})");
	}else if(reporttype == 2){
		$("#testingtime").attr("onfocus","new WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM',alwaysUseStartDate:true})");
	}else if(reporttype == 3){
		$("#testingtime").attr("onfocus","new WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy',alwaysUseStartDate:true})");
	}
}
$("#search").click(function () {
	if($("#testingtime").val() == ""){
		$.messager.confirm('信息','请您输入日期','info');
		return ;
	}

	var url = "";
	var reporttype = $("#reporttype").val();
	if(reporttype == 1){
		url = getContextPath()+"/report/energycostsday.do";
	}else if(reporttype == 2){
		url = getContextPath()+"/report/energycostsmonth.do";
	}else if(reporttype == 3){
		url = getContextPath()+"/report/energycostsyear.do";
	}
	
	
	$("#showForm").datagrid({
		"url":url,
		"queryParams":{
			testingtime:$("#testingtime").val(),
			sewageid:$("#sewageid").val(),
			areaid:$("#areaid").val()
		}	
	});
	$("#showForm").datagrid("load");
});

$("#downloadexcel").click(function () {
 	if($("#areaid").val() == -1 || $("#testingtime").val() == ""){
		show_err_msg('请您选择区县或者日期');
		return ;
	}

	var url = getContextPath()+"/report/getexcelexportbyday.do?areaid="+$("#areaid").val()+"&testingtime="+$("#testingtime").val();
	window.open(url,'_self');
});


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