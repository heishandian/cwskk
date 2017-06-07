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
	
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.datagrid.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.linkbutton.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.panel.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.resizable.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/adapterWindowJs/adapterwindow.js"></script>
<body>
	<!--加上 class="easyui-datagrid" 会导致两次请求 -->
	<table id="showForm" class="easyui-datagrid"  title="用户列表" style="width:100%;height:420px">

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
			<a id="deleteUser" href="#" class="easyui-linkbutton">删除选中用户</a>
			<a id="addUser" href="<%=basePath %>users/gotoaddnewusers.do" class="easyui-linkbutton">增加新用户</a>
			<a id="resetPwd" href="#" class="easyui-linkbutton">重置密码</a>
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
$('#showForm').datagrid({  
    url:getContextPath()+'/users/getuserdata.do',
    columns:[[
		{field:'id',title:'id',width:130,hidden:true},
		{field:'select',checkbox:true},
		{field:'loginname',title:'用户登录名',width:130,align:'center'},
		{field:'username',title:'用户姓名',width:130,align:'center'},
		{field:'rolename',title:'用户角色',width:130,align:'center'},
		{field:'department',title:'用户所在部门',width:130,align:'center'},
		{field:'telephone',title:'用户联系方式',width:130,align:'center'},
		{field:'modify',title:'操作',width:130,align:'center',formatter: function(value,row,index){
			return "<a href='"+getContextPath()+"/users/gotoupdateUser.do?userid="+row.id+"'>修改</a>";
		}}
    ]],
    fitColumns:true,
    idField:"id",
    pagination:true,//显示分页  
    pageSize:20,//分页大小  
    pageList:[20,25,30],//每页的个数  
    toolbar: '#tb',
});
$("#deleteUser").click(function () {
     var selectIds = []; 
     var rows =  $('#showForm').datagrid('getChecked');
     $.each(rows,function(index,data){
     	selectIds.push(data.id);
     });
     
     var postUrl = getContextPath()+"/users/deleteselectedusers.do";
     
     var data = {"selectIds":selectIds};
     
     $.messager.confirm('Confirm','确实想删除这些用户么?',function(r){
    	if (r){
			     $.ajax({
						type : "POST",
						url : postUrl,
						dataType : "json",
						contentType : "application/json;charset=utf-8",
						data : JSON.stringify(data),
						async : false,
						success : function(returndata) {
							//alert(returndata.key);
							if(returndata.key == "pass"){
								//重新加载数据
								$.messager.alert("提示","删除成功","info");
								$("#showForm").datagrid("reload");
							}
						}
				}); 
    		}
	});
     

});
$("#resetPwd").click(function () {
        //alert("resetPwd我被点到了！");
        ///users/resetuserpwd.do
     var selectIds = []; 
     var rows =  $('#showForm').datagrid('getChecked');
     $.each(rows,function(index,data){
     	selectIds.push(data.id);
     });
     //$.messager.alert("提示","删除成功","info");
    //var rows =  $('#showForm').datagrid('getChecked');
    if(rows.length == 0){
    	$.messager.confirm('Confirm','您没有选择用户，确定重置所有用户密码么？',function(r){
    		if (r){
    			var data = {"resetAllUserPwdFlag":"yes"};
    			resetUserPwd(data);
    		}
    	});
    }else{
    	//ajax请求 选中用户
   			var data = {"resetAllUserPwdFlag":"no","selectIds":selectIds};
  			resetUserPwd(data);   	
    }
     
});

function resetUserPwd(data){
	var postUrl = getContextPath()+"/users/resetuserpwd.do";
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
				$.messager.alert("提示","重置密码成功","info");
			}
		}
	});
}


function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
</script>