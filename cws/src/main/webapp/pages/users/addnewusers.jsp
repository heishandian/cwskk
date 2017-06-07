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
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/common_main.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main_main.css" />

	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/easyui-lang-zh_CN.js"></script>

	<link rel="stylesheet" href="<%=basePath %>css/jqueryvalidation/screen.css" type="text/css"></link>
	<script type="text/javascript" src="<%=basePath %>js/jqueryvalidation/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jqueryvalidation/validationextend.js"></script>
<body>
<input type="hidden" id="isModify" value="${not empty user ? 'yes':'no' }"></input>
<div id="userForm" class="easyui-panel" title="${not empty user ? '修改':'添加新' }用户" style="width:100%;padding:30px 60px;">
<form id="submitform">
		<div class="config-items">
			 <div class="result-content">
					<table class="insert-tab" width="100%">
							  <tbody>
							    <tr>
							      <th width="25%">登录名:</th>
							      <td width="75%">
									<c:choose>
										<c:when test="${user != null}">
											<input id="id" name="id" type="hidden"  style="width:30%" value="${user.id }">
											${user.loginname }
										</c:when>
										<c:when test="${user == null }">
											<input id="loginusername" name="loginusername"  style="width:30%" value="${user.loginname }">
										</c:when>
									</c:choose>
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">姓名:</th>
							      <td width="75%">
							      	<input id="username"  name="username"   style="width:30%" value="${user.username }">
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">部门:</th>
							      <td width="75%">
							      	<input id="department" name="department" style="width:30%" value="${user.department }">
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">联系电话:</th>
							      <td width="75%">
							      	<input id="telephone" name="telephone" style="width:30%" value="${user.telephone }">
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">角色:</th>
							      <td width="75%">
							      	<select style="width:200px" name="roleid" id="roleid">
											<c:choose>
												<c:when test="${user != null}">
													<c:forEach items="${roles}" var="item">
														<c:choose>
															<c:when test="${role.id == item.id }">
																<option value="${item.id}" selected="selected">${item.name}</option>
															</c:when>
															<c:otherwise>
																<option value="${item.id}">${item.name}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</c:when>
												<c:when test="${user == null }">
													<c:forEach items="${roles}" var="item">
														<option value="${item.id}">${item.name}</option>
													</c:forEach>
												</c:when>
											</c:choose>
									</select>
							      </td>
							    </tr>
							    <tr>
							      <th width="25%"></th>
							      <td width="75%">
									<span>
										<input class="btn" name="seachpoint" id="seachpoint" value="提交" type="submit" style="width:25%;height:30px" />
										<input class="btn" name="returnback" id="returnback" value="返回" onclick="javascript:history.go(-1)" type="button" style="width:25%;height:30px" onclick="javascript:history.go(-1)"/>
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
			if($("#isModify").val() == "yes"){
				postUrl = getContextPath()+"/users/updateUser.do";
			}else{
				postUrl = getContextPath()+"/users/addnewusers.do";
			}
			$.ajax({
				type:"POST",
				url:postUrl,
				dataType:"json",
				contentType:"application/json;charset=utf-8",
				data:postdata,
				success:function(data){
					if(data.key == "pass"){
						$.messager.alert("提示","添加成功","info");
					}
				}

			});

		},

	});

	$("#submitform").validate({
		rules: {
			loginusername:{
				required:true,
				rangelength:[5,10],
				mustEnglish:true,
				checkedLoginNameExist:true
			},
			username: {
				required:true
			}
		},
		messages: {
			loginusername:{
				required:"请输入登录名",
				rangelength:"登录名长度为5-10",
				mustEnglish:"登录名必须是字母",
				checkedLoginNameExist:"登录名已经存在"
			},
			username: {
				required: "请输入用户姓名"
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