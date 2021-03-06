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
	<!-- 维保日志 -->
	<table id="showForm" class="easyui-datagrid"  title="保养单列表" style="width:100%;height:420px">
	
	</table>
	
	<div id="tb" style="padding:5px;height:auto;display:none">
		<div>
	      	<select style="width:150px" name="areaid" id="areaid">
	      			<option value="">请选择区县</option>
					<c:forEach items="${allAreas}" var="item">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
			</select>
	      	<select style="width:150px" name="sewageid" id="sewageid">
	      			<option value="">请选择水污水站</option>
			</select>
			维修人员：<input id="repairman" type="text" style="width:120px"/>
			起始时间：<input id="begintime" type="text" style="width:120px" onfocus="new WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
			终止时间：<input id="endtime" type="text" style="width:120px" onClick="new WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>
			<a id="searchbtn" href="#" class="easyui-linkbutton">查找</a>
			<label id="sumTimes" ></label>
			
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
$(document).ready(function(){
	$("#areaid").change(function(){ 
		getSewageByareaId();
	});
	
	$("#showForm").datagrid({"onLoadSuccess":function(data){
            if (data.total == 0) {
                //添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
                $("#showForm").datagrid('appendRow', { sewageName:'<div style="text-align:center;color:red">没有相关记录！</div>' });
                $("#showForm").datagrid('mergeCells', { index: 0, field: 'sewageName', colspan:6});
            }
            //如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
            else $("#showForm").closest('div.datagrid-wrap').find('div.datagrid-pager').show();
	}});
	
	
});

$('#showForm').datagrid({  
    //url:getContextPath()+'/warming/getequipmentabnormal.do',
    columns:[[
		{field:'sewagename',title:'所属污水站点',width:100,align:'center'},
		{field:'operationnum',title:'运营编号',width:100,align:'center'},
		{field:'repairreason',title:'保养原因',width:80,align:'center'},
		{field:'repaircontent',title:'保养内容',width:290,align:'center'},
		{field:'consumematerial',title:'消耗材料',width:260,align:'center'},
		{field:'repairman',title:'维修人员',width:90,align:'center'},
		{field:'completetime',title:'维护时间',width:70,align:'center',formatter: function(value,row,index){
				if(typeof(row.completetime)!="undefined"){
					return new Date(row.completetime).format("yyyy-MM-dd");
			    }
			}},
		{field:'operation',title:'操作',width:40,align:'center',formatter: function(value,row,index){
			return "<a href='#' onclick='deleteByid("+row.id+")'>删除</a>";
		}}
    ]],
    //fitColumns:false,
    idField:"id",
    pagination:true,//显示分页  
    pageSize:20,//分页大小  
    pageList:[20,25,30],//每页的个数  
    toolbar: '#tb',
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

$("#searchbtn").click(function () {
	var url = getContextPath()+"/equiprepairrecord/getrepairedrecord.do";
	
	$("#showForm").datagrid({
		"url":url,
		"queryParams":{
			sewageid:$("#sewageid").val(),
			areaid:$("#areaid").val(),
			repairman:$("#repairman").val(),
			begintime:$("#begintime").val(),
			endtime:$("#endtime").val()
		}	
	});
	$("#showForm").datagrid("load");


	//获取维保日志次数
	var getUserLoginTimes = getContextPath()+"/equiprepairrecord/getEquipRepairedTimes.do";

	var sumTimes = $("#sumTimes").val();
	var postUrl = getUserLoginTimes
	var data = {
		sewageid:$("#sewageid").val(),
		areaid:$("#areaid").val(),
		repairman:$("#repairman").val(),
		begintime:$("#begintime").val(),
		endtime:$("#endtime").val()
	}

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

function deleteByid(id){
	var postUrl = getContextPath()+"/equiprepairrecord/ajaxdeleteByid.do?id="+id;

	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		async:false,
		success:function(){
			$.messager.alert("提示","删除成功","info");
		}
	});
	$("#searchbtn").trigger("click");
}
</script>