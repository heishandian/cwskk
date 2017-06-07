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
	
	<script type="text/javascript" src="<%=basePath %>js/tools/jquery.md5.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/tools/jquery.base64.js"></script>
<body>
<form id="submitform">
	<div class="result-wrap">
		<div class="config-items">
            <div class="result-content" style="width:100%;height:100%">
					<table class="search-tab" width="100%">
						<tr>
						  <td width="25%">
						<%--	  串口选择：<input id="serialPort" name="serialPort" type="text" style="width:50%" value="1"/>--%>
						  </td>
						  <td width="40%">接收邮箱：<input id="email" name="email" type="text" style="width:50%" value="yaoliweb@foxmail.com"/></td>
						  <td width="35%">
							  <%--短信内容：<input id="messageContent" name="messageContent" type="text" style="width:50%" value=""/>--%>
						  </td>
						</tr>
						<tr>
						  <td></td>
						  <td><input class="btn" name="seachpoint" id="seachpoint" value="发送" type="submit" style="width:20%;height:30px" />
						  <td><input class="btn" name="test" id="test" value="测试" type="submit" style="width:20%;height:30px" />
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

	$("#test").click(function(){

	
	});
	
	
	$.validator.setDefaults({
		submitHandler: function() {
			var data = serializeForm($("#submitform"));
			delete data["seachpoint"];
			delete data["returnback"];
			var postdata = JSON.stringify(data);

			
			var postUrl = getContextPath()+"/es/emailSendTest.do";
			$.ajax({
			type:"POST",
			url:postUrl,
			dataType:"json",
			contentType:"application/json;charset=utf-8",
			data:postdata,
			success:function(data){
					if(data.key == "pass"){
						$.messager.alert("提示","测试短信发送成功","info");
					}else{
						$.messager.alert("提示","测试短信发送失败","info");
					}
				}
			});
			
		},

	});

	$("#submitform").validate({
		rules: {
			acceptEmail:{
				required:true
			}
		},
		messages: {
			acceptEmail:{
				required:"不能为空"
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


function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
</script>