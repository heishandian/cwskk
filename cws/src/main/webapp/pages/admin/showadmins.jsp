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
	<script type="text/javascript" src="<%=basePath %>js/adapterWindowJs/adapterwindow.js"></script>
<body>
	<table id="showForm" class="easyui-datagrid" title="地区管理员列表" style="width:100%;height:420px">
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div>
			<a id="deleteRecord" href="#" class="easyui-linkbutton">删选中区域管理员</a>
			<a id="addRecord" href="<%=basePath %>admin/gotoaddnewadmin.do" class="easyui-linkbutton">增加区域管理员</a>
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
$('#showForm').datagrid({  
    url:getContextPath()+'/admin/getAdminsByPaing.do',
    columns:[[
		{field:'id',title:'id',width:130,hidden:true},
		{field:'select',checkbox:true},
		{field:'name',title:'姓名',width:130,align:'center'},
		{field:'telephone',title:'联系电话',width:130,align:'center'},
		{field:'address',title:'地址',width:130,align:'center'},
		{field:'email',title:'电子邮箱',width:130,align:'center'}
    ]],
    fitColumns:true,
    idField:"id",
    pagination:true,//显示分页  
    pageSize:20,//分页大小  
    pageList:[20,25,30],//每页的个数  
    toolbar: '#tb',
});


$("#deleteRecord").click(function () {
     var selectIds = []; 
     var rows =  $('#showForm').datagrid('getChecked');
     $.each(rows,function(index,data){
     	selectIds.push(data.id);
     });
     
     var postUrl = getContextPath()+"/admin/deleteseletedadmin.do";
     
     var data = {"selectedIds":selectIds};
     
     $.messager.confirm('消息','确定删除所选选择的管理员?',function(r){
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
								$.messager.confirm("消息","删除成功","Info");
								$("#showForm").datagrid("reload");
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
</script>