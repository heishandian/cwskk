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
	
	<script type="text/javascript" src="<%=basePath %>js/login/tooltips.js"></script>
<body>
	<table id="showForm" class="easyui-datagrid"  title="设备档案" style="width:100%;height:420px">
	
	</table>
	
	<div id="tb" style="padding:5px;height:auto;display:none">
		<div>
			<input class="btn" name="seachpoint" id="seachpoint" value="添加设备" type="button" onclick="window.location.href='<%=basePath %>device/gotoadddevicedoc.do'">
	      	<select style="width:200px" name="areaid" id="areaid">
	      			<option value="">请选择区县</option>
					<c:forEach items="${allAreas}" var="item">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
			</select>
	      	<select style="width:200px" name="sewageid" id="sewageid">
	      			<option value="">请选择水污水站</option>
			</select>
			
			<a id="searchbtn" href="#" class="easyui-linkbutton">查找</a>
			
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
                show_err_msg('未找到记录');	
            }
	}});
	
	
});

$('#showForm').datagrid({  
    //url:getContextPath()+'/warming/getequipmentabnormal.do',
    columns:[[
		{field:'id',title:'runid',hidden:true},
		/* {field:'number',title:'编号',width:130,align:'center'}, */
		{field:'devicename',title:'设备名称',width:130,align:'center'},
		{field:'devicetype',title:'设备参数类型',width:130,align:'center'},
		{field:'setuptime',title:'安装时间',width:130,align:'center',formatter: function(value,row,index){
				if(typeof(row.setuptime)!="undefined"){
					return new Date(row.setuptime).format("yyyy-MM-dd");
			    }
			}},
		{field:'modify',title:'操作',width:130,align:'center',formatter: function(value,row,index){
			return "<a href='"+getContextPath()+"/device/gotoUpdateDeviceDoc.do?id="+row.id+"'>修改</a>&nbsp;&nbsp;"+
					" <a href='#' onclick='deleteDeviceDocById("+row.id+")'>删除</a>";
		}}
    ]],
    fitColumns:true,
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
/* 	if($("#sewageid").val() == -1 || $("#begintime").val() == "" || $("#endtime").val() == ""){
		$.messager.confirm('信息','请您输入查询条件','info');
		return ;
	} */

	var url = getContextPath()+"/device/getdevicedoc.do";
	
	$("#showForm").datagrid({
		"url":url,
		"queryParams":{
			sewageid:$("#sewageid").val(),
			areaid:$("#areaid").val()
		}	
	});
	$("#showForm").datagrid("load");
});

function deleteDeviceDocById(id){
	var deviceid = id;
	var postUrl = getContextPath()+"/device/deleteDeviceDocById.do";
	var data = {"id":deviceid};
	
  	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(data),
		async:false,
		success:function(data){
			if(data.key == 'pass'){
				$.messager.alert("提示","删除成功！","info");
				$("#showForm").datagrid("load");
			}
		}
	});


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