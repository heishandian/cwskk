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
						  <td rowspan="5" width="30%">区县
						  	<select id="areaid" name="areaid">
								<option value="">请选择区县</option>
							</select>
						  </td>
						  <td width="35%">联系人1：<input id="linker1" name="linker1" type="text" style="width:50%"/></td>
						  <td width="35%">号码1：<input id="linkertel1" name="linkertel1" type="text" style="width:50%"/></td>
						</tr>
						<tr>
						 <td>联系人2：<input id="linker2" name="linker2" type="text" style="width:50%"/></td>
						 <td>号码2：<input id="linkertel2" name="linkertel2" type="text" style="width:50%"/></td>
						</tr>
						<tr>
						  <td>联系人3：<input id="linker3" name="linker3" type="text" style="width:50%"/></td>
						  <td>号码3：<input id="linkertel3" name="linkertel3" type="text" style="width:50%"/></td>
						</tr>
						<tr>
						 <td>联系人4：<input id="linker4" name="linker4" type="text" style="width:50%"/></td>
						 <td>号码4：<input id="linkertel4" name="linkertel4" type="text" style="width:50%"/></td>
						</tr>
						<tr>
						 <td>联系人5：<input id="linker5" name="linker5" type="text" style="width:50%"/></td>
						 <td>号码5：<input id="linkertel5" name="linkertel5"  type="text" style="width:50%"/></td>
						</tr>
						<tr>
						  <td></td>
						  <td><input class="btn" name="seachpoint" id="seachpoint" value="保存" type="submit" style="width:20%;height:30px" />
						  </td>
						  <td></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
</form>
</body>
</html>

<script type="text/javascript">
$(document).ready(function(){
	//设置地区选择
	$.ajax({
		type : "POST",
		url : getContextPath()+"/warming/ajaxgetareas.do",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		async : true,
		success : function(returndata) {
		    $.each(returndata,function(index,data){
				var key = data.id;
	        	var value= data.name;
	        	$("#areaid").append("<option value='"+key+"'>"+value+"</option>");
			});
		}
	});
	
	$("#areaid").change(function(){ 
		getlinkerInfo();
	});
	
	$.validator.setDefaults({
		submitHandler: function() {
			var data = serializeForm($("#submitform"));
			delete data["seachpoint"];
			delete data["returnback"];
			var postdata = JSON.stringify(data);

			
			var postUrl = getContextPath()+"/message/addlinkers.do";
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
			areaid:"required",
			linker1:{
				maxlength:5,
			},
			linkertel1: {
				isMobile:true
			},
			linker2:{
				maxlength:5,
			},
			linkertel2: {
				isMobile:true
			},
			linker3:{
				maxlength:5,
			},
			linkertel3: {
				isMobile:true
			},
			linker3:{
				maxlength:5,
			},
			linkertel4: {
				isMobile:true
			},
			linker4:{
				maxlength:5,
			},
			linkertel5: {
				isMobile:true
			},
			linker5:{
				maxlength:5,
			}
		},
		messages: {
			areaid:"请选择区县",
			linker1:{
				maxlength:"长度不能超过5"
			},
			linker2:{
				maxlength:"长度不能超过5"
			},
			linker3:{
				maxlength:"长度不能超过5"
			},
			linker4:{
				maxlength:"长度不能超过5"
			},
			linker5:{
				maxlength:"长度不能超过5"
			},
			linkertel1: {
				isMobile: "手机号码格式不正确"
			},
			linkertel2: {
				isMobile: "手机号码格式不正确"
			},
			linkertel3: {
				isMobile: "手机号码格式不正确"
			},
			linkertel4: {
				isMobile: "手机号码格式不正确"
			},
			linkertel5: {
				isMobile: "手机号码格式不正确"
			}
		}
	});	
});

function getlinkerInfo(){
	var areaId = $("#areaid").val();
	if(areaId == ""){
		return;
	}
	var data = {"areaid":areaId};
	$.ajax({
		type : "POST",
		url : getContextPath()+"/message/getlinkers.do",
		dataType : "json",
		data:JSON.stringify(data),
		contentType : "application/json;charset=utf-8",
		async : true,
		success : function(returndata) {
			for(var i = 1;i<=5; i++){
				if(typeof eval("returndata.linker"+i) != 'undefined'){
					$("#linker"+i).val(eval("returndata.linker"+i));
				}else{
					$("#linker"+i).val("");
				}
				if(typeof eval("returndata.linkertel"+i) != 'undefined'){
					$("#linkertel"+i).val(eval("returndata.linkertel"+i));
				}else{
					$("#linkertel"+i).val("");
				}
			}
		}
	});	
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