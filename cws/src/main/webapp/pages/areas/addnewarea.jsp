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
	
	<script type="text/javascript" src="<%=basePath %>js/easyui/easyui_customize_validation.js"></script>
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/common_main.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main_main.css" />
	
	<link rel="stylesheet" href="<%=basePath %>css/jqueryvalidation/screen.css" type="text/css">
	<script type="text/javascript" src="<%=basePath %>js/jqueryvalidation/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jqueryvalidation/validationextend.js"></script>

<body>
<div id="areaForm" class="easyui-panel" title="添加区县" style="width:auto;padding:30px 60px;height:700px">
<input type="hidden" id="isModify" value="${not empty area ? 'yes':'no' }"/>
<form id="submitform">
		<div class="config-items">
			 <div class="result-content">
					<table class="insert-tab" width="100%">
							  <tbody>
							    <tr>
							      <th width="25%">地区名称</th>
							      <td width="75%">
							      	<input id="id" name="id" type="hidden"  style="width:50%;" value="${area.id}">
							      	<input id="name" name="name"  style="width:50%;" value="${area.name}">
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">管理员</th>
							      <td width="75%">
							      	<select style="width:50%" name="adminId" id="adminId">
											<c:choose>
												<c:when test="${area !=null }">
													<c:forEach items="${admins}" var="item">
														<c:choose>
															<c:when test="${adminArea.adminid == item.id }">
																<option value="${item.id}" selected="selected">${item.name}</option>
															</c:when>
															<c:otherwise>
																<option value="${item.id}">${item.name}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</c:when>
												<c:when test="${area == null }">
													<c:forEach items="${admins}" var="item">
														<option value="${item.id}">${item.name}</option>
													</c:forEach>
												</c:when>	
											</c:choose>
									</select>
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">地区纬度</th>
							      <td width="75%">
							      	<input id="coordinatex" name="coordinatex"   style="width:50%" value="${area.coordinatex}">
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">地区经度</th>
							      <td width="75%">
							      	<input id="coordinatey" name="coordinatey"   style="width:50%" value="${area.coordinatey}">
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">简介</th>
							      <td width="75%">
							      	<textarea id="introduce" name="introduce" rows="15"  style="width:500px;" value="${area.introduce}"></textarea>
							      </td>
							    </tr>
							    <tr>
							      <th width="25%"></th>
							      <td width="75%">
										<span>
											<input class="btn" name="seachpoint" id="seachpoint" value="提交" type="submit" style="width:25%;height:30px" />
											<input class="btn" name="returnback" id="returnback" value="返回" type="button" style="width:25%;height:30px" onclick="javascript:history.go(-1)"/>
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
			
			var postdata = JSON.stringify(data);
			var postUrl = "";
			if($("#isModify").val() == 'no'){
				postUrl = getContextPath()+"/areas/addnewarea.do";
			}else{
				postUrl = getContextPath()+"/areas/updatearea.do";
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

	});
	
	$("#submitform").validate({
		rules: {
			name:"required",
			adminId:"required",
			coordinatex:"required",
			coordinatey:"required"
		},
		messages: {
			name:"请输入地区名称",
			adminId:"请选择一个区域管理员",
			coordinatey:{
				required:"请输入纬度",
				number:"格式不正确"
			},
			coordinatex:{
				required:"请输入经度",
				number:"格式不正确"
			}
		}
	});	
});

function submitForm(){
	if($("#areaForm").form('enableValidation').form('validate') == true){

		var data = serializeForm($("#area"));
		var postdata = JSON.stringify(data);
		var postUrl = getContextPath()+"/areas/addnewarea.do";
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