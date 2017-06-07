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
	
	<script language="javascript" type="text/javascript" src="<%=basePath %>js/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>js/My97DatePicker/skin/WdatePicker.css">	
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/common_main.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main_main.css" />
	
	<link rel="stylesheet" href="<%=basePath %>css/jqueryvalidation/screen.css" type="text/css"></link>
	<script type="text/javascript" src="<%=basePath %>js/jqueryvalidation/jquery.validate.js"></script>
	
<body>
<div id="areaForm" class="easyui-panel" title="添加通知发布" style="width:auto;padding:30px 60px;height:auto">
	<input type="hidden" id="token" value="${token}" />
	<form id="submitform">
		<div class="config-items">
			 <div class="result-content">
					<table class="insert-tab" width="100%">
							  <tbody>

							  <tr>
								  <th width="40%">开始时间：</th>
								  <td width="60%">
									  <input name="begintime" id="begintime" type="text" onfocus="new WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" >
								  </td>
							  </tr>
							  <tr>
								  <th width="40%">结束时间：</th>
								  <td width="60%">
									  <input name="endtime" id="endtime" type="text" onfocus="new WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" >
								  </td>
							  </tr>

							    <tr>
							      <th width="40%">内容：</th>
							      <td width="60%">
							      	<textarea id="publishcontent" name="publishcontent" rows="10"  style="width:60%"></textarea>
							      </td>
							    </tr>

							    <tr>
							      <th width="40%"></th>
							      <td width="60%">
										<span>
											
											<input class="btn" name="seachpoint" id="seachpoint" value="提交" type="submit" style="width:25%;height:30px" />
											<input class="btn" name="returnback" id="returnback" value="返回" type="button" style="width:25%;height:30px" onclick="javascript:history.go(-1)"/>
											
											<!-- <input class="submit" type="submit" value="提交 "></input> -->
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
$(document).ready(function(){
	$.validator.setDefaults({
		submitHandler: function() {
			var data = serializeForm($("#submitform"));
			delete data["seachpoint"];
			delete data["returnback"];
			//delete data["begintime"];
			//delete data["endtime"];

			var postdata = JSON.stringify(data);
			
			var postUrl = getContextPath()+"/infoPub/addNoticePublish.do?token="+$("#token").val();
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
					}else if(data.key == "submited") {
						$.messager.alert("提示","请不要重复提交","info");
						$( "#submitform" ).validate().resetForm();
					}
				}
			});
			
		},

	});

	$("#submitform").validate({
		rules: {
			begintime:"required",
			endtime:"required",
			publishcontent:"required"

		},
		messages: {
			begintime:"请输入开始时间",
			endtime:"请输入结束时间",
			publishcontent:"请输入内容"
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