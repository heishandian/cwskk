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
<div id="areaForm" class="easyui-panel" title="问题提交" style="width:auto;padding:30px 60px;height:auto">
<form id="submitform">
		<div class="config-items">
			 <div class="result-content">
					<table class="insert-tab" width="100%">
							  <tbody>
							    <tr>

							      <th width="25%">所属区县</th>
							      <td width="75%">
								 	<input type="hidden" name="hiddenareaid" id="hiddenareaid" value="">
							      	<select style="width:200px" name="areaid" id="areaid">
							      			<option value="-1">请选择区县</option>
											<c:forEach items="${allAreas}" var="item">
												<option value="${item.id}">${item.name}</option>
											</c:forEach>
									</select>

							      </td>
							    </tr>
							    <tr>
							      <th width="25%">污水站点</th>
							      <td width="75%">
							      	<input type="hidden" name="hiddensewageid" id="hiddensewageid" value="">
							      	<select style="width:200px" name="sewageid" id="sewageid">
							      			<option value="">请选择水污水站</option>
									</select>
							      	<input id="searchoperatornum" name="searchoperatornum" type="text" style="width:20%"/>
							      	<input name="searchSewageidByOperatornum" id="searchSewageidByOperatornum" value="查询" type="button" style="width:10%" />
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">问题标题</th>
							      <td width="75%">
							      	<input id="title" name="title" type="text" style="width:50%"/>
							      </td>
							    </tr>

							    <tr>
							      <th width="25%">问题描述</th>
							      <td width="75%">
							      	<textarea id="description" name="description" rows="8"  style="width:50%"></textarea>
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">发现人员</th>
							      <td width="75%">
							      	<input id="finder" name="finder" type="text" style="width:50%"/>
							      </td>
							    </tr>
							    <tr>
							      <th width="25%">发现时间</th>
							      <td width="75%">
							      	<input id="findtime" name="findtime" type="text" style="width:50%" onfocus="new WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"/>
							      </td>
							    </tr>
							    <tr>
							      <th width="25%"></th>
							      <td width="75%">
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
	$("#areaid").change(function(){ 
		getSewageByareaId();
	});
	
	$("#searchSewageidByOperatornum").click(function(){ 
		searchSewageidByOperatornum();
	});
	$.validator.setDefaults({
		submitHandler: function() {
			var data = serializeForm($("#submitform"));
			delete data["areaid"];  
			delete data["seachpoint"];
			delete data["returnback"];
			delete data["hiddensewageid"];
			delete data["hiddenareaid"];
			delete data["searchoperatornum"];
			delete data["searchSewageidByOperatornum"];
			var postdata = JSON.stringify(data);
			
			
			var postUrl = getContextPath()+"/pgc/addProblemGather.do";
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
			sewageid:"required"
		},
		messages: {
			sewageid:"请选择一个地区"
		}
	});	
	
});
function searchSewageidByOperatornum(){
	var operationnum = $("#searchoperatornum").val();
	var postUrl = getContextPath()+"/monitor/searchSewageidByOperatornum.do";
	var data = {"operationnum":operationnum};
  	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(data),
		async:false,
		success:function(data){
			if(data.key == "pass"){
				$("#areaid").val(data.data.areaid);
				
				if($("#areaid").val()!=""){
					var temp_value = $("#hiddenareaid").val(); 
					$("#areaid").children("option").each(function(){
						  var value = $(this).val();  
			              if(temp_value == value){  
			                    $(this).attr("selected","selected");  
			              }  
			        });
			        
 			        $("#areaid").trigger("change");
			        $("#sewageid").val(data.data.sewageid);
			        $("#hiddensewageid").val(data.data.sewageid);
			        
					if($("#sewageid").val()!=""){
						var temp_value = $("#hiddensewageid").val(); 
						$("#sewageid").children("option").each(function(){
							  var value = $(this).val();  
					          if(temp_value == value){  
					               $(this).attr("selected","selected");  
					          }  
					    });
					}
				}
			}else{
				$.messager.alert("提示","未找到相关站点","info");
			}
		}
	});
}

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
			$("#sewageid").append("<option value=''>请选择水污水站</option>");
	        $.each(data,function(key,item){
	        	var key = item.sewageid;
	        	var value= item.name;
	        	$("#sewageid").append("<option value='"+key+"'>"+value+"</option>");
	        });			
		}
	});
}

function submitForm(){
/* 	if($("#areaForm").form('enableValidation').form('validate') == true){

		var data = serializeForm($("#area"));
		var postdata = JSON.stringify(data);
		
		var postUrl = getContextPath()+"/admin/addnewadmin.do";
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
	
	} */
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