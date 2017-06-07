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

	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/common_main.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main_main.css" />


 	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/easyui/themes/icon.css">
	
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.min.js"></script>
	
	<script language="javascript" type="text/javascript" src="<%=basePath %>js/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>js/My97DatePicker/skin/WdatePicker.css">	

	<script type="text/javascript" src="<%=basePath %>js/login/tooltips.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/timeformat/timeformattool.js"></script>
	
	
	
	<link rel="stylesheet" href="<%=basePath %>css/jqueryvalidation/screen.css" type="text/css"></link>
	<script type="text/javascript" src="<%=basePath %>js/jqueryvalidation/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jqueryvalidation/validationextend.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/ztree/zTreeStyle.css" />
	<script type="text/javascript" src="<%=basePath %>js/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/ztree/jquery.ztree.excheck-3.5.js"></script>
<body>
<form id="submitform">
	<div class="result-wrap">
		<div class="config-items">
            <div class="result-content" style="width:100%;height:100%">
					<table class="search-tab" width="100%">
						  <tr>
						    <th width="40%">时间间隔(分钟)：</th>
						    <td width="60%">
						    	<input id="intervaltime" name="intervaltime" type="text" value="${model.value}"/>
						    </td>
						  </tr>
						  <tr>
						    <th width="40%">开始时间：</th>
						    <td width="60%">
						    	<!-- <input id="messagesendbegintime" name="messagesendbegintime" type="text" /> -->
						    	<input id="messagesendbegintime" type="text" onfocus="new WdatePicker({startDate:'00:00:00',dateFmt:'HH:mm:ss',alwaysUseStartDate:true})" value="${messagesendbegintime.value}">
						    </td>
						  </tr>
						  <tr>
						    <th width="40%">结束时间：</th>
						    <td width="60%">
						    	<input id="messagesendendtime" type="text" onfocus="new WdatePicker({startDate:'00:00:00',dateFmt:'HH:mm:ss',alwaysUseStartDate:true})" value="${messagesendendtime.value}">
						    </td>
						  </tr>
						  <tr>
						    <th>
						    	故障类型：
						    </th>
						    <td>
						    	<ul id="abnormaltype" class="ztree"></ul>
						    </td>
						  </tr>
						  <tr>
						    <th>
						    	发送地区选择：
						    </th>
						    <td>
						    	<ul id="area" class="ztree"></ul>
						    </td>
						  </tr>
						  <tr>
						    <th>
						    	
						    </th>
						    <td>
						    	<input class="btn" name="seachpoint" id="seachpoint" value="保存" type="submit" style="width:20%;height:30px" />
						    </td>
						  </tr>
					</table>
				</div>
			</div>
		</div>
</form>
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

	var postUrl1 = getContextPath()+"/message/getabnormaltype.do";
	$.ajax({
		type:"POST",
		url:postUrl1,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		success:function(data){
			$.fn.zTree.init($("#abnormaltype"), setting, data.data);
			var treeObj = $.fn.zTree.getZTreeObj("abnormaltype");
			treeObj.expandAll(true);
		}
	});
	
	var postUrl2 = getContextPath()+"/message/getsendarea.do";
	$.ajax({
		type:"POST",
		url:postUrl2,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		success:function(data){
			$.fn.zTree.init($("#area"), setting, data.data);
			var treeObj = $.fn.zTree.getZTreeObj("area");
			treeObj.expandAll(true);
		}
	});


	
	$.validator.setDefaults({
		submitHandler: function() {
/* 			var data = serializeForm($("#submitform"));
			delete data["seachpoint"];
			delete data["returnback"];
			var postdata = JSON.stringify(data); */
		
			var treeObj = $.fn.zTree.getZTreeObj("abnormaltype");
			var abnormaltypeNodes = treeObj.getCheckedNodes(true);
			
			var abnormaltypeIds = [];
			for(var i = 0; i<abnormaltypeNodes.length;i++){
				abnormaltypeIds.push(abnormaltypeNodes[i].id);
			}

			treeObj = $.fn.zTree.getZTreeObj("area");
			var areaNodes = treeObj.getCheckedNodes(true);
			
			var areaIds = [];
			for(var i = 0; i<areaNodes.length;i++){
				areaIds.push(areaNodes[i].id);
			}




			var data = {intervaltime:$("#intervaltime").val(),abnormaltypeIds:abnormaltypeIds,areaIds:areaIds,messagesendbegintime:$("#messagesendbegintime").val(),messagesendendtime:$("#messagesendendtime").val()};
			
			var postdata = JSON.stringify(data);
			
			var postUrl = getContextPath()+"/message/addmessagesetting.do";
			$.ajax({
			type:"POST",
			url:postUrl,
			dataType:"json",
			contentType:"application/json;charset=utf-8",
			data:postdata,
			success:function(data){
					if(data.key == "pass"){
						$.messager.alert("提示","你已经添加成功","info");
						$( "#submitform" ).validate().resetForm();
					}
				}
			});
		},
	});
	
	$("#submitform").validate({
		rules: {
			intervaltime:{
				required:true,
				digits:true
			}
		},
		messages: {
			intervaltime: {
				required:"必须输入一个整数",
				digits:"必须是整数"
			}
		}
	});	
	
	
	
});

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


//获取站点信息列表
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
			$("#sewageid").append("<option value='-1'>请选择水污水站</option>");
	        $.each(data,function(key,item){
	        	var key = item.sewageid;
	        	var value= item.name;
	        	$("#sewageid").append("<option value='"+key+"'>"+value+"</option>");
	        });			
		}
	});
}
function getSewageInfoBySewageid(){
	var sewageid = $("#sewageid").val();
	if(sewageid == -1){
		return;
	}
	var postUrl = getContextPath()+"/monitor/ajaxgetsewagebysewageid.do";
	var data = {"sewageid":sewageid};
	
  	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(data),
		async:false,
		success:function(data){
			if(data.key == "pass"){
				$("#sewagecontrolid").html(data.message);
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