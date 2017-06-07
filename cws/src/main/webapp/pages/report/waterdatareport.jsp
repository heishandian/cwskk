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
	<!-- 水质监测报表 -->
	<table id="showForm" class="easyui-datagrid"  style="width:100%;height:500px">
	
	</table>

	<div id="tb" style="padding:5px;height:auto;display:none">
		<div>
			请选择报表类型
	      	<select style="width:100px" name="reporttype" id="reporttype">
	      			<option value="1">区县日报</option>
	      			<option value="2">区县月报</option>
	      			<option value="3">区县年报</option>
			</select>
			请选择日期：<input id="testingtime" type="text" style="width:120px" onfocus="new WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
	      	<select style="width:200px" name="areaid" id="areaid">
	      			<option value="-1">请选择区县</option>
					<c:forEach items="${allAreas}" var="item">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
			</select>
			<a id="searchbtn" href="#" class="easyui-linkbutton">查找</a>
			<a id="downloadexcel" href="#" class="easyui-linkbutton">导出报表</a>
		</div>
	</div>
</body>
</html>


<script type="text/javascript">
$(document).ready(function(){
/* 	$("#areaid").change(function(){ 
		getSewageByareaId();
	}); */
	$("#reporttype").change(function(){ 
		//getSewageByareaId();
		reporttypeChange();
	});
	$("#showForm").datagrid({"onLoadSuccess":function(data){
            if (data.total == 0) {
            	show_err_msg('暂无搜索结果');
            }
            //如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
            else $("#showForm").closest('div.datagrid-wrap').find('div.datagrid-pager').show();
	}});
	
	//以下是获取标题
	var columns = [];
	var inner =[]
	$.ajax({
		type : "POST",
		url : getContextPath()+"/report/getwaterdataXtoken.do",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		async : false,
		success : function(returndata) {
			for(var i = 0; i < returndata[1].length;i++){
				var name = returndata[1][i].field;
				if(name.substring(0,6) == "detect"){
					returndata[1][i].formatter = detectionfun;
				}
				if(name.substring(0,5) == "equip"){
					returndata[1][i].formatter = equipmentfun;
				}
			}
			$('#showForm').datagrid({
				columns:returndata
			});
		}
	});
	
});

equipmentfun = function(value,row,index)
{

	if(typeof(value)=='undefined'){
		return "0";
	}
	if(typeof(value)!='undefined'){
		return value;
	}
}

detectionfun = function(value,row,index)
{
	if(typeof(value)=='undefined'){
		return "0.00";
	}
	if(typeof(value)!='undefined'){
		return value.toFixed(2);
	}
}

$('#showForm').datagrid({
	frozenColumns:[[
		{field:'sewagename',title:'污水站点',align:'cente·r',width:100}
	]],
    idField:"id",
    pagination:true,//显示分页  
    pageSize:20,//分页大小  
    fitColumns:true,
    pageList:[20,40,60],//每页的个数  
    toolbar: '#tb',
});

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

$("#searchbtn").click(function () {
 	if($("#areaid").val() == -1 || $("#testingtime").val() == ""){
		show_err_msg('请您选择区县或者日期');
		return ;
	}
	var url = "";
	var reporttype = $("#reporttype").val();
	if(reporttype == 1){
		url = getContextPath()+"/report/getquxianstatisticbycondition.do";
	}else if(reporttype == 2){
		url = getContextPath()+"/report/getquxianstatisticmonthbycondition.do";
	}else if(reporttype == 3){
		url = getContextPath()+"/report/getquxianstatisticyearbycondition.do";
	}
	
	$("#showForm").datagrid({
		"url":url,
		"queryParams":{
			areaid:$("#areaid").val(),
			testingtime:$("#testingtime").val()
		}	
	});
	$("#showForm").datagrid("load");
});

$("#downloadexcel").click(function () {
 	if($("#areaid").val() == -1 || $("#testingtime").val() == ""){
		show_err_msg('请您选择区县或者日期');
		return ;
	}

	var url = "";
	var reporttype = $("#reporttype").val();
	var exceltype = 2; //excel类型 1表示设备运行记录 2表示水质检测记，本jsp是水质检测记录
	if(reporttype == 1){
		url = getContextPath()+"/report/getexcelexportbyday.do?areaid="+$("#areaid").val()+"&testingtime="+$("#testingtime").val()+"&exceltype=2";
	}else if(reporttype == 2){
		url = getContextPath()+"/report/getexcelexportbymonth.do?areaid="+$("#areaid").val()+"&testingtime="+$("#testingtime").val()+"&exceltype=2";
	}else if(reporttype == 3){
		url = getContextPath()+"/report/getexcelexportbyyear.do?areaid="+$("#yearareaid").val()+"&testingtime="+$("#yeartestingtime").val()+"&exceltype=2";
	}
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