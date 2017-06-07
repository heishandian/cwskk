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
<div id="areaForm" class="easyui-panel" title="状态变换" style="width:auto;padding:30px 60px;height:auto">
	<input type="hidden" id="token" value="${token}" />
	<form id="submitform">
		<div class="config-items">
			 <div class="result-content">
					<table class="insert-tab" width="100%">
							  <tbody>
							  <input type="hidden" id="id" value="" />
							  <tr>
								  <th width="40%">物品名称：</th>
								  <td width="60%">
									  <select id="itemname" name="itemname">
										  <option value="">请选择名称</option>
									  </select>
								  </td>
							  </tr>

							  <tr>
								  <th width="40%">物品编码：</th>
								  <td width="60%">
									  <input name="itemno" id="itemno" type="text" >
									  <input name="searchByitemno" id="searchByitemno" value="查询" type="button" style="width:10%" />
								  </td>
							  </tr>

							  <tr>
								  <th width="40%">来源：</th>
								  <td width="60%">
									  <select id="sourceid" name="sourceid">
										  <option value="">请选择来源</option>
									  </select>
								  </td>
							  </tr>

							  <tr>
								  <th width="40%">当前状态：</th>
								  <td width="60%">
									  <select id="itemstateid" name="itemstateid">
										  <option value="">状态</option>
									  </select>
								  </td>
							  </tr>

							    <tr>
							      <th width="40%">故障描述：</th>
							      <td width="60%">
							      	<textarea id="breakdowndescription" name="breakdowndescription" rows="5"  style="width:50%"></textarea>
							      </td>
							    </tr>

							  <tr>
								  <th width="40%">故障类型：</th>
								  <td width="60%">
									  <select id="breakdowntypeid" name="breakdowntypeid">
										  <option value="">请选择故障类型</option>
									  </select>
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

	$("#searchByitemno").click(function(){
		searchByitemno();
	});

	$.validator.setDefaults({
		submitHandler: function() {
			var data = serializeForm($("#submitform"));
			delete data["seachpoint"];
			delete data["returnback"];

			data["id"] = $("#id").val();
			//delete data["begintime"];
			//delete data["endtime"];

			var postdata = JSON.stringify(data);
			
			var postUrl = getContextPath()+"/fr/updateBreakdown.do?token="+$("#token").val();
			$.ajax({
				type:"POST",
				url:postUrl,
				dataType:"json",
				contentType:"application/json;charset=utf-8",
				data:postdata,
				success:function(data){
					if(data.key == "pass"){
						$.messager.alert("提示","修改成功","info");
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
			itemName:"required",
			itemNO:"required",
			sourceId:"required",
			breakdownDescription:"required",
			breakdownTypeId:"required"
		},
		messages: {
			itemName:"请输入物品名称",
			itemNO:"请输入物品编号",
			sourceId:"请选择来源",
			breakdownDescription:"请输入描述",
			breakdownTypeId:"请选择故障类型"
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

function searchByitemno(){
	var data = {"itemno":$("#itemno").val()};
	var postUrl = getContextPath()+"/fr/ajaxGetItemInfo.do";
	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(data),
		success:function(data){
			if(data.key == "pass"){
				data = data.data;
				setItemName(data.itemname);

				setSourceId(data.sourceid);

				setTypeId(data.breakdowntypeid);

				setItemState(data.itemstateid);

				$("#breakdowndescription").val(data.breakdowndescription);

				$("#id").val(data.id);

			}else{
				$.messager.alert("提示","未找到编号","info");
			}
		}
	});
}


function setItemState(itemstateid)
{
	var postUrl = getContextPath()+"/fr/ajaxGetItemState.do";
	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		success:function(data){
			data = data.data;
			$("#itemstateid").empty();
			$.each(data, function(index,val) {
				if(itemstateid == index){
					$("#itemstateid").append("<option value='"+index+"' selected='selected'>"+val+"</option>");
				}else{
					$("#itemstateid").append("<option value='"+index+"'>"+val+"</option>");
				}

			});
		}
	});
}

function setItemName(itemname)
{
	var postUrl = getContextPath()+"/fr/ajaxGetItemName.do";
	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		success:function(data){
			data = data.data;
			$("#itemname").empty();
			$.each(data, function(index,val) {
				if(itemname == index){
					$("#itemname").append("<option value='"+index+"' selected='selected'>"+val+"</option>");
				}else{
					$("#itemname").append("<option value='"+index+"'>"+val+"</option>");
				}

			});
		}
	});
}

function setSourceId(sourceid)
{
	var postUrl = getContextPath()+"/fr/ajaxGetSourceId.do";
	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		success:function(data){
			data = data.data;
			$("#sourceid").empty();
			$.each(data, function(index,val) {
				if(sourceid == index){
					$("#sourceid").append("<option value='"+index+"' selected='selected'>"+val+"</option>");
				}else{
					$("#sourceid").append("<option value='"+index+"'>"+val+"</option>");
				}
			});
		}
	});
}

function setTypeId(breakdowntypeid)
{
	var postUrl = getContextPath()+"/fr/ajaxGetTypeId.do";
	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		success:function(data){
			data = data.data;
			$("#breakdowntypeid").empty();
			$.each(data, function(index,val) {
				if(breakdowntypeid == index){
					$("#breakdowntypeid").append("<option value='"+index+"' selected='selected'>"+val+"</option>");
				}else{
					$("#breakdowntypeid").append("<option value='"+index+"'>"+val+"</option>");
				}
			});
		}
	});
}
</script>