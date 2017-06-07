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
	<!-- <link rel="stylesheet" type="text/css" href="../demo.css"> -->
	
	
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/easyui-lang-zh_CN.js"></script>

<body>
	<!--加上 class="easyui-datagrid" 会导致两次请求 -->
	<table id="showSewages" class="easyui-datagrid" title="污水站点列表" style="width:100%;height:420px">

	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div>
<!-- 			Date From: <input class="easyui-datebox" style="width:80px">
			To: <input class="easyui-datebox" style="width:80px">
			Language: 
			<input class="easyui-combobox" style="width:100px"
					url="data/combobox_data.json"
					valueField="id" textField="text">
			<a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a> -->
			<a id="deleteUser" href="#" class="easyui-linkbutton">删选中污水站点</a>
			<a id="addUser" href="<%=basePath %>monitor/gotoaddsewage.do" class="easyui-linkbutton">增加污水站点</a>
			
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
$('#showSewages').datagrid({  
    url:getContextPath()+'/monitor/getsewages.do',
    columns:[[
		{field:'sewageid',title:'sewageid',width:130,hidden:true},
		{field:'select',checkbox:true},
		{field:'name',title:'名称',width:130,align:'center'},
		{field:'areaName',title:'所属地区',width:130,align:'center'},
		{field:'admin',title:'管理员',width:130,align:'center'},
		{field:'shortTitle',title:'简称',width:130,align:'center'},
		{field:'coordinatex',title:'经度',width:130,align:'center'},
		{field:'coordinatey',title:'纬度',width:130,align:'center'},
		{field:'modify',title:'操作',width:130,align:'center',formatter: function(value,row,index){
			return "<a href='"+getContextPath()+"/monitor/gotomodifysewage.do?sewageid="+row.sewageid+"'>修改</a>";
			
		}}
    ]],
    fitColumns:true,
    idField:"sewageid",
    pagination:true,//显示分页  
    pageSize:20,//分页大小  
    pageList:[20,25,30],//每页的个数  
    toolbar: '#tb',
});



$("#deleteUser").click(function () {
     var selectIds = []; 
     var rows =  $('#showSewages').datagrid('getChecked');
     $.each(rows,function(index,data){
     	selectIds.push(data.sewageid);
     });
     var postUrl = getContextPath()+"/monitor/deleteselectedsewages.do";
     var data = {"selectIds":selectIds};
     $.messager.confirm('Confirm','确定要删除这些站点么么?',function(r){
    	if (r){
			     $.ajax({
						type : "POST",
						url : postUrl,
						dataType : "json",
						contentType : "application/json;charset=utf-8",
						data : JSON.stringify(data),
						async : false,
						success : function(returndata) {
							if(returndata.key == "pass"){
								//重新加载数据
								$.messager.confirm("消息","删除站点成功","info");
								$("#showSewages").datagrid("reload");
							}
						}
				}); 
    		}
	});

});

function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}

function adapter(){
	$('#showSewages').datagrid('resize', {
		width:$(window).width()*0.984,
		height:$(window).height()*0.98
	});
}
$(window).resize(function() {
	adapter();
});

$(document).ready(function (){
	adapter();
});
</script>