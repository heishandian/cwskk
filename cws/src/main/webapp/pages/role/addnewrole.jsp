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
	
	<script type="text/javascript" src="<%=basePath %>js/easyui/easyui_customize_validation.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/common_main.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main_main.css" />
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/ztree/zTreeStyle.css" />
	<script type="text/javascript" src="<%=basePath %>js/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/ztree/jquery.ztree.excheck-3.5.js"></script>
	

<body>
<div id="areaForm" class="easyui-panel" title="添加角色" style="width:auto;padding:30px 60px;height:700px">
<input type="hidden" id="isModify" value="${isModify}"></input>
<input type="hidden" id="roleid" value="${role.id}"></input>
<%-- <input type="hidden" id="loginname" value="${loginname}"></input> --%>
<form id="area">
		<div class="config-items">
			 <div class="result-content">
					<table class="insert-tab" width="100%">
							  <tbody>
							    <tr>
							      <th width="25%">角色名称</th>
							      <td width="75%">
							      	<input id="name" name="name" class="easyui-textbox" data-options="required:true" style="width:50%;height:30px" value="${role.name}">
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">角色菜单</th>
							      <td width="75%">
										<ul id="menuTree" class="ztree"></ul>
							      </td>
							    </tr>
							    <tr>
							      <th width="25%"></th>
							      <td width="75%">
										<span>
											<a href="#" class="easyui-linkbutton" style="width:50%;height:30px"  onclick="submitForm();">提交</a>
											<a href="#" class="easyui-linkbutton" style="width:50%;height:30px"  onclick="javascript:history.go(-1)">返回</a>
										</span>							      	
							      </td>
							    </tr>
							  </tbody>
					</table>			 	
			 </div>
        </div>



		
</form>
</div>
</body>
</html>

<script type="text/javascript">
var setting = {
	check: {
		enable: true,
		chkStyle: "checkbox",
		chkboxType: { "Y": "ps", "N": "ps" }
	},
	data: {
		simpleData: {
			enable: true
		}
	}
}
$(document).ready(function(){
	var postUrl = "";
	
	if($("#isModify").val() == "no"){
		//直接获取
		postUrl = getContextPath()+"/system/getallmenus.do";
	}else if($("#isModify").val() == "yes"){
		//更新获取
		postUrl = getContextPath()+"/role/getupdatauserrolesmenus.do?roleid="+$("#roleid").val();
	}
	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		success:function(data){
			$.fn.zTree.init($("#menuTree"), setting, data);
			var treeObj = $.fn.zTree.getZTreeObj("menuTree");
			treeObj.expandAll(true);
		}
		});
});


function submitForm(){
	if($("#areaForm").form('enableValidation').form('validate') == true){
		var treeObj = $.fn.zTree.getZTreeObj("menuTree");
		var nodes = treeObj.getCheckedNodes(true);
		if(nodes.length == 0){
			alert("请选择一个菜单");
			return ;
		}
		
		if($("#roleid").val() == 8){
			if(!window.confirm('您修改的是超级管理员，确定要提交么？')){
		       	return;
		    }
		}
		
		var nodeIds = [];
		
		for(var i = 0; i<nodes.length;i++){
			nodeIds.push(nodes[i].id);
		}

		var data = {name:$("#name").val(),meunIds:nodeIds};
		
		var postdata = JSON.stringify(data);
		
		var postUrl = "";
		if($("#isModify").val() == "yes"){
			postUrl = getContextPath()+"/role/updaterole.do?roleid="+$("#roleid").val();
		}else{
			postUrl = getContextPath()+"/role/addnewrole.do";
		}
		$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:postdata,
		success:function(data){
			if(data.key == "pass"){
				$.messager.alert("提示","你已经添加成功","info");
			}
		}
		
	});
	
	}
}
function serializeForm(form)
{
    var obj = {};
    $.each(form.serializeArray(), function (index)
    {
        if (obj[this['name']])
        {
            obj[this['name']] = obj[this['name']] + ',' + this['value'];
        }
        else
        {
            obj[this['name']] = this['value'];
        }
    }
    );
    return obj;
}
function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
</script>