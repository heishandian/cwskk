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
	<table id="showForm" class="easyui-datagrid"  title="角色列表" style="width:100%;height:420px">

	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div>
			<a id="deleteUser" href="#" class="easyui-linkbutton">删选角色</a>
			<a id="addUser" href="<%=basePath %>role/gotoaddnewrole.do" class="easyui-linkbutton">增加角色</a>
			
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
$('#showForm').datagrid({  
    url:getContextPath()+'/role/getallroles.do',
    columns:[[
		{field:'areaid',title:'areaid',width:130,hidden:true},
		{field:'select',checkbox:true},
		{field:'name',title:'角色名称',width:130,align:'center'},
		{field:'modify',title:'操作',width:130,align:'center',formatter: function(value,row,index){
			return "<a href='"+getContextPath()+"/role/gotoupdatarole.do?roleid="+row.id+"'>修改</a>";
			
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
     
     var postUrl = getContextPath()+"/role/deleteselectedusers.do";
     
     var data = {"delRoleIds":selectIds};
     
     $.messager.confirm('Confirm','确定删除这些角色？',function(r){
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
								var message = "";
								if(returndata.data.length > 0){
									message = "角色：";
									for(var i=0;i< returndata.data.length;i++){
									     $.each(rows,function(index,data){
     										if(returndata.data[i] == data.id){
     											message = message + data.name + " ";
     										}
     									 });									
									}
									message = message + "不能被删除，因为还有用户关联到这些角色！";
								}
								if(rows.length > returndata.data.length){
									message = "删除成功！"+message;
								}
								$.messager.alert("提示",message,"info");
								//重新加载数据
								$("#showForm").datagrid("reload");
							}
						}
				}); 
    		}
	});
     

});
$("#resetPwd").click(function () {

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