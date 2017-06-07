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

	<script type="text/javascript" src="<%=basePath %>js/layer_v3.0.1/layer.js"></script>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:10px;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:10px;font-weight:normal;padding:5px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-glis{font-size:10px}
	</style>

	<style type="text/css">
		.tb  {border-collapse:collapse;border-spacing:0;border:none;text-align: center}
		.tb td{font-family:Arial, sans-serif;font-size:10px;padding:10px 5px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;}
		.tb th{font-family:Arial, sans-serif;font-size:10px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:0px;overflow:hidden;word-break:normal;}
		.tb .tb-glis{font-size:10px}
	</style>
<body>
	<!-- 故障溯源 -->
	<table id="showForm" class="easyui-datagrid"  title="故障溯源" style="width:100%;height:500px">
	
	</table>
	
	<div id="tb" style="padding:5px;height:auto;display:none">
		<div>
			物品名称：
	      	<select style="width:150px" name="itemname" id="itemname">
	      			<option value="">请选择物品名称</option>
			</select>
			物品编码：<input id="itemno" name="itemno" type="text" style="width:120px"/>
	      	来源：<select style="width:200px" name="sourceid" id="sourceid">
	      			<option value="">请选择来源</option>
			</select><br/><br/>
			起始时间：<input id="begintime" type="text" style="width:120px" onfocus="new WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
			终止时间：<input id="endtime" type="text" style="width:120px" onClick="new WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
			状态：<select style="width:200px" name="itemstateid" id="itemstateid">
				<option value="">请选择状态</option>
			</select>

			<a id="search" href="#" class="easyui-linkbutton">查找</a>

			<%--<a id="downloadexcel" href="#" class="easyui-linkbutton">导出报表</a>--%>
			<label id="totalTime" ></label>

			<!-- <a id="downloadexcel" href="#" class="easyui-linkbutton">导出excel</a> -->
			
		</div>
	</div>
	<div id="infoDiv" style="display: none">
			<table class="tg" width="100%">
				<tr>
					<td class="tg-031e" width="35%">物品名称：</td>
					<td class="tg-031e" width="65%">
						<label id="itemname_label"></label>
					</td>
				</tr>
				<tr>
					<td class="tg-031e" width="35%">物品编码：</td>
					<td class="tg-031e" width="65%">
						<label id="itemno_label"></label>
					</td>
				</tr>
				<tr>
					<td class="tg-031e" width="35%">来源：</td>
					<td class="tg-031e" width="65%">
						<label id="sourceid_label"></label>
					</td>
				</tr>
				<tr>
					<td class="tg-031e" width="35%">当前状态：</td>
					<td class="tg-031e" width="65%">
						<label id="itemstateid_label"></label>
					</td>
				</tr>
				<tr>
					<td class="tg-031e" width="35%">故障发生时间：</td>
					<td class="tg-031e" width="65%">
						<label id="breakdowntime_label"></label>
					</td>
				</tr>
				<tr>
					<td class="tg-031e" width="35%">故障描述：</td>
					<td class="tg-031e" width="65%">
					<label id="breakdowndescription_label"></label>
					</td>
				</tr>
				<tr>
					<td class="tg-031e" width="35%">故障类型：</td>
					<td class="tg-031e" width="65%">
					<label id="breakdowntypeid_label"></label>
					</td>
				</tr>
				<tr>
					<td class="tg-031e" width="35%">处理状态：</td>
					<td class="tg-031e" width="65%">
						<table class="tb" id="stateTable" width="100%">
							<tr>
								<th class="tb-glis" width="35%">状态名称</th>
								<th class="tb-031e" width="65%">状态时间</th>
							</tr>
						</table>
					</td>
				</tr>
		</table>
	</div>

</body>
</html>

<script type="text/javascript">
$('#showForm').datagrid({  
    columns:[[
		{field:'source',title:'来源',align:'center',width:100},
		{field:'itemname',title:'物品名称',align:'center',width:100},
		{field:'itemno',title:'物品编码',align:'center',width:100},
		{field:'breakdowntype',title:'故障类型',align:'center',width:100},

		{field:'breakdowntime',title:'故障时间',align:'center',width:150,formatter: function(value,row,index){
			if(typeof(row.breakdowntime)!="undefined"){
				return new Date(row.breakdowntime).format("yyyy-MM-dd hh:mm:ss");
			}
			}},

		{field:'breakdowndescription',title:'故障描述',align:'center',width:200},
		{field:'itemstate',title:'当前状态',align:'center',width:130},
		{field:'updatetime',title:'当前状态时间',align:'center',width:150,formatter: function(value,row,index){
			if(typeof(row.updatetime)!="undefined"){
				return new Date(row.updatetime).format("yyyy-MM-dd hh:mm:ss");
			}
		}},
		{field:'operation',title:'操作',align:'center',width:130,formatter: function(value,row,index){
			return "<a href='#' onclick='getDetails("+row.id+")'>查看详情</a>";

		}},
    ]],
    fitColumns:true,
    idField:"id",
    pagination:true,//显示分页  
    pageSize:20,//分页大小  
    pageList:[20,25,30],//每页的个数  
    toolbar: '#tb',
});

//初始化
$(document).ready(function(){
	getItemName();
	getSourceId();
	setItemState();
});

//http://layer.layui.com/
//弹出层插件 弹窗

function getDetails(id){
	var postUrl1 = getContextPath()+"/fr/ajaxShowBreakdownDetails.do?id="+id;
	$.ajax({
		type:"POST",
		url:postUrl1,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		success:function(data){
			var breakdownInfo = data.data;
			$("#itemname_label").html(breakdownInfo.itemname);
			$("#itemno_label").html(breakdownInfo.itemno);
			$("#sourceid_label").html(breakdownInfo.source);
			$("#itemstateid_label").html(breakdownInfo.itemstate);
			$("#breakdowntime_label").html(new Date(breakdownInfo.breakdowntime).format("yyyy-MM-dd hh:mm:ss"));
			$("#breakdowndescription_label").html(breakdownInfo.breakdowndescription);
			$("#breakdowntypeid_label").html(breakdownInfo.breakdowntype);


			//删除没有用的行数
			$("#stateTable tr:gt(0)").remove();


			var postUrl2 = getContextPath()+"/fr/ajaxShowBreakdownStateDetails.do?id="+id;
			$.ajax({
				type:"POST",
				url:postUrl2,
				dataType:"json",
				contentType:"application/json;charset=utf-8",
				success:function(data){
					var breakdownStateInfo = data.data;

					$.each(breakdownStateInfo, function(index,val) {

						$("#stateTable").append("<tr><td class='tb-031e' width=‘30%>"+val.itemstate+"</td><td class='tb-031e' width=’100%>"+
								new Date(val.updatetime).format("yyyy-MM-dd hh:mm:ss")
								+"</td></tr>");
					});
					var html = $("#infoDiv").html();
					//alert();
					//页面层
					layer.open({
						type: 1,
						skin: 'layui-layer-rim', //加上边框
						area: ['500px', '360px'], //宽高
						content: html
					});
				}
			});
		}
	});


}

$("#search").click(function () {
	if($("#sewageName").val() == "" && $("#operationnum").val() == "" && $("#sewageid").val() == "-1"){
		$.messager.confirm('信息','请您选择站点或者输入站点名称或者输入运营编号','info');
		return ;
	}


	$("#showForm").datagrid({
		"url":getContextPath()+"/fr/showFaultReview.do",
		"queryParams":{
			itemname:$("#itemname").val(),
			itemno:$("#itemno").val(),
			sourceid:$("#sourceid").val(),
			begintime:$("#begintime").val(),
			endtime:$("#endtime").val(),
			itemstateid:$("#itemstateid").val(),
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

function getItemName()
{
	var postUrl = getContextPath()+"/fr/ajaxGetItemName.do";
	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		success:function(data){
			data = data.data;
			$.each(data, function(index,val) {
				$("#itemname").append("<option value='"+index+"'>"+val+"</option>");
			});
		}
	});
}

function getSourceId()
{
	var postUrl = getContextPath()+"/fr/ajaxGetSourceId.do";
	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		success:function(data){
			data = data.data;
			$.each(data, function(index,val) {
				$("#sourceid").append("<option value='"+index+"'>"+val+"</option>");
			});
		}
	});
}

function setItemState()
{
	var postUrl = getContextPath()+"/fr/ajaxGetItemState.do";
	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		success:function(data){
			data = data.data;
			$.each(data, function(index,val) {
				$("#itemstateid").append("<option value='"+index+"'>"+val+"</option>");

			});
		}
	});
}
</script>