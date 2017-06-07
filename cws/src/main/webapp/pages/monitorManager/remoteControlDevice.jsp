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
<div id="areaForm" class="easyui-panel" title="远程设备开关" style="width:auto;padding:30px 60px;height:auto">
<form id="submitform">
		<div class="config-items">
			 <div class="result-content">
					<table class="insert-tab" width="100%">
							  <tbody>
							    <tr>

							      <th width="25%">所属区县</th>
							      <td width="75%"  colspan="3">
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
							      <td width="75%"  colspan="3">
							      	<input type="hidden" name="hiddensewageid" id="hiddensewageid" value="">
							      	<select style="width:200px" name="sewageid" id="sewageid">
							      			<option value="">请选择水污水站</option>
									</select>
							      	<input id="searchoperatornum" name="searchoperatornum" type="text" style="width:20%"/>
							      	<input name="searchSewageidByOperatornum" id="searchSewageidByOperatornum" value="查询" type="button" style="width:10%" />
							      </td>
							    </tr>


							    <tr>
							      <th width="25%"><span style="font-weight:bold;font-size: medium">实时状态</span></th>
							      <td width="25%">


									  <table class="search-tab" width="100%">
										  <tr>
											  <td width="35%">曝气机：
												  <input id="realtime-equip1-close" type="radio" name="realtime-equip1" value="0" />关
												  <input id="realtime-equip1-open" type="radio" name="realtime-equip1" value="1" />开
											  </td>
										  </tr>
										  <tr>
											  <td>提升泵：
												  <input id="realtime-equip2-close" type="radio" name="realtime-equip2" value="0" />关
												  <input id="realtime-equip2-open"  type="radio" name="realtime-equip2" value="1" />开
											  </td>
										  </tr>
										  <tr>
											  <td>回流泵：
												  <input id="realtime-equip3-close" type="radio" name="realtime-equip3" value="0" />关
												  <input id="realtime-equip3-open" type="radio" name="realtime-equip3" value="1" />开
											  </td>
										  </tr>
										  <tr>
											  <td>排水泵：
												  <input id="realtime-equip4-close" type="radio" name="realtime-equip4" value="0"  />关
												  <input id="realtime-equip4-open" type="radio" name="realtime-equip4" value="1" />开
											  </td>
										  </tr>
										  <tr>
											  <td>加药泵：
												  <input id="realtime-equip5-close" type="radio" name="realtime-equip5" value="0"  />关
												  <input id="realtime-equip5-open" type="radio" name="realtime-equip5" value="1" />开
											  </td>
										  </tr>
									  </table>



							      </td>
									<th><span style="font-weight:bold;font-size: medium">远程控制</span></th>
									<td>

										<table class="search-tab" width="100%">
											<tr>
												<td width="35%">曝气机：
													<input id="equip1-close" type="radio" name="equip1" value="0" />关
													<input id="equip1-open" type="radio" name="equip1" value="1" />开
												</td>
											</tr>
											<tr>
												<td>提升泵：
													<input id="equip2-close" type="radio" name="equip2" value="0" />关
													<input id="equip2-open"  type="radio" name="equip2" value="1" />开
												</td>
											</tr>
											<tr>
												<td>回流泵：
													<input id="equip3-close" type="radio" name="equip3" value="0" />关
													<input id="equip3-open" type="radio" name="equip3" value="1" />开
												</td>
											</tr>
											<tr>
											<td>排水泵：
												<input id="equip4-close" type="radio" name="equip4" value="0"  />关
												<input id="equip4-open" type="radio" name="equip4" value="1" />开
											</td>
										</tr>
										<tr>
												<td>加药泵：
													<input id="equip5-close" type="radio" name="equip5" value="0"  />关
													<input id="equip5-open" type="radio" name="equip5" value="1" />开
												</td>
											</tr>
										</table>


									</td>
							    </tr>


							    <tr>
							      <th width="25%"></th>
									    <td width="75%"  colspan="3">
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

	//如果自己选择下拉 污水站点 那么就触发事件
	$("#sewageid").change(function(){
		changeRadio();
	});
	
	$("#searchSewageidByOperatornum").click(function(){ 
		searchSewageidByOperatornum();
	});
	$.validator.setDefaults({
		submitHandler: function() {




			var sewageid = $("#sewageid").val();
			var postUrl = getContextPath()+"/monitor/getWithoutElectricBySewageid.do";
			var sewageData = {"sewageid":sewageid};
			$.ajax({
				type: "POST",
				url: postUrl,
				dataType: "json",
				contentType: "application/json;charset=utf-8",
				data: JSON.stringify(sewageData),
				async: false,
				success: function (data) {
					if (data.key == "unpass") {
						$.messager.alert("提示", "站电断线，未能远程控制", "info");
					}else {

						//提交数据
						for(var i = 1;i <= 5 ; i++){
							if($("#equip"+i+"-open").prop("checked") == true){
								$("#realtime-equip"+i+"-open").prop('checked', true);
								$("#realtime-equip"+i+"-close").prop('checked', false);
							}else{
								$("#realtime-equip"+i+"-open").prop('checked', false);
								$("#realtime-equip"+i+"-close").prop('checked', true);
							}
						}


						var postdata = serializeForm($("#submitform"));
						delete postdata["areaid"];
						delete postdata["seachpoint"];
						delete postdata["returnback"];
						delete postdata["hiddensewageid"];
						delete postdata["hiddenareaid"];
						delete postdata["searchoperatornum"];
						delete postdata["searchSewageidByOperatornum"];

						delete postdata["realtime-equip1"];
						delete postdata["realtime-equip2"];
						delete postdata["realtime-equip3"];
						delete postdata["realtime-equip4"];
						delete postdata["realtime-equip5"];
						postdata = JSON.stringify(postdata);

						var postUrl = getContextPath()+"/remoteControl/updateRemoteControllDevice.do";
						$.ajax({
							type:"POST",
							url:postUrl,
							dataType:"json",
							contentType:"application/json;charset=utf-8",
							data:postdata,
							success:function(data){
								if(data.key == "pass"){
									$.messager.alert("提示","你已经提交修改","info");
									//$( "#submitform" ).validate().resetForm();
								}
							}
						});
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

function changeRadio(){
	var sewageid = $("#sewageid").val();
	var postUrl = getContextPath()+"/monitor/getWithoutElectricBySewageid.do";
	var data = {"sewageid":sewageid};
	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(data),
		async:false,
		success:function(data){
			if(data.key == "unpass"){
				$.messager.alert("提示","站电断线，未能远程控制","info");
			}else{
				//获取rundata表中的最新数据
				var postdata = JSON.stringify({sewageid:$("#sewageid").val()});

				var postUrl = getContextPath()+"/monitor/getRunDataBySewageid.do";
				$.ajax({
					type:"POST",
					url:postUrl,
					dataType:"json",
					contentType:"application/json;charset=utf-8",
					data:postdata,
					async:false,
					success:function(data){
						if(data.key == "pass"){
							for(var i = 1;i <= 5 ; i++){
								//equipment1state
								if(eval("data.data.equipment"+i+"state") == "1"){
									$("#realtime-equip"+i+"-open").prop('checked', true);
									$("#realtime-equip"+i+"-close").prop('checked', false);
								}else{
									$("#realtime-equip"+i+"-open").prop('checked', false);
									$("#realtime-equip"+i+"-close").prop('checked', true);
								}
							}
						}else{
							for(var i = 1;i <= 5 ; i++){
								$("#realtime-equip"+i+"-open").prop('checked', false);
								$("#realtime-equip"+i+"-close").prop('checked', false);
							}
						}
					}
				});

				//end


				//以下是获取remoteControl表中的数据
				var postdata = JSON.stringify({sewageid:$("#sewageid").val()});

				var postUrl = getContextPath()+"/remoteControl/getRemoteControllDeviceBySewageid.do";
				$.ajax({
					type:"POST",
					url:postUrl,
					dataType:"json",
					contentType:"application/json;charset=utf-8",
					data:postdata,
					async:false,
					success:function(data){
						if(data.key == "pass"){
							for(var i = 1;i <= 5 ; i++){
								if(eval("data.data.equip"+i) == "1"){
									$("#equip"+i+"-open").prop('checked', true);
									$("#equip"+i+"-close").prop('checked', false);
								}else{
									$("#equip"+i+"-open").prop('checked', false);
									$("#equip"+i+"-close").prop('checked', true);
								}
							}
						}else{
							for(var i = 1;i <= 5 ; i++){
								$("#equip"+i+"-open").prop('checked', false);
								$("#equip"+i+"-close").prop('checked', false);
							}
						}
					}
				});

				//$.messager.alert("提示","未找到相关站点","info");
			}
		}
	});


}

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

				//触发选择时间
				$("#sewageid").trigger("change");
			}else{
				$.messager.alert("提示","未找到相关站点","info");
			}
		}
	});
}

function checkSewageIsWithoutElectric(){


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