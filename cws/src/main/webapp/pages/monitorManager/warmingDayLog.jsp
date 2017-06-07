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
	<!-- 报警日志查询 -->
	<table id="showForm" class="easyui-datagrid"  title="报警日志查询" style="width:100%;height:500px">
	
	</table>
	
	<div id="tb" style="padding:5px;height:auto;display:none">
		<div>
			请选择故障类型
	      	<select style="width:150px" name="abnormaltypeNO" id="abnormaltypeNO">
	      			<option value="1">水质异常</option>
	      			<option value="2">设备故障</option>
	      			<option value="3">断电断线</option>
			</select>
	      	<select style="width:200px" name="areaid" id="areaid">
	      			<option value="">请选择区县</option>
					<c:forEach items="${allAreas}" var="item">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
			</select>
	      	<select style="width:200px" name="sewageid" id="sewageid">
	      			<option value="">请选择站点</option>
			</select><br/><br/>
			<label id="sewagecontrolid" style="display:none"></label>
			站点名称：<input id="sewageName" type="text" style="width:120px"/>
			运行编号：<input id="operationnum" type="text" style="width:120px"/>
			起始时间：<input id="begintime" type="text" style="width:120px" onfocus="new WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
			终止时间：<input id="endtime" type="text" style="width:120px" onClick="new WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
			<a id="search" href="#" class="easyui-linkbutton">查找</a>
			<a id="downloadexcel" href="#" class="easyui-linkbutton">导出报表</a>
			<label id="sumWater" ></label>

			<!-- <a id="downloadexcel" href="#" class="easyui-linkbutton">导出excel</a> -->
			
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
$('#showForm').datagrid({  
    columns:[[
		{field:'sewageName',title:'污水站点名称',align:'center',width:130},
		{field:'operationnum',title:'运营编号',align:'center',width:130},
		{field:'abnormalTypeName',title:'故障类型',align:'center',width:130},
		{field:'testingtime',title:'故障检测时间',align:'center',width:130,formatter: function(value,row,index){
			if(typeof(row.testingtime)!="undefined"){
				return new Date(row.testingtime).format("yyyy-MM-dd hh:mm:ss");
			}
			}},
		{field:'lasttestingtime',title:'最后一次故障发生时间',align:'center',width:130,formatter: function(value,row,index){
			if(typeof(row.lasttestingtime)!="undefined"){
				return new Date(row.lasttestingtime).format("yyyy-MM-dd hh:mm:ss");
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
$("#downloadexcel").click(function () {
	if($("#areaid").val() == -1 || $("#testingtime").val() == ""){
		show_err_msg('请您选择区县或者日期');
		return ;
	}

	var url = getContextPath()+"/warmingdaylog/getWarmingDayLogExcel.do?" +
			"abnormaltypeNO="+$("#abnormaltypeNO").val()+
			"&areaid="+$("#areaid").val()+
			"&sewageid="+$("#sewageid").val()+
			"&sewageName="+$("#sewageName").val()+
			"&operationnum="+$("#operationnum").val()+
			"&begintime="+$("#begintime").val()+
			"&endtime="+$("#endtime").val();
	window.open(url,'_self');
});


$("#search").click(function () {
	if($("#sewageName").val() == "" && $("#operationnum").val() == "" && $("#sewageid").val() == "-1"){
		$.messager.confirm('信息','请您选择站点或者输入站点名称或者输入运营编号','info');
		return ;
	}

	$("#showForm").datagrid({
		"url":getContextPath()+"/warmingdaylog/getWarmingDayLogSearch.do",
		"queryParams":{
			begintime:$("#begintime").val(),
			endtime:$("#endtime").val(),
			sewageid:$("#sewageid").val(),
			abnormaltypeNO:$("#abnormaltypeNO").val(),
			sewageName:$("#sewageName").val(),
			areaid:$("#areaid").val(),
			operationnum:$("#operationnum").val(),
		}	
	});
	$("#showForm").datagrid("load");
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