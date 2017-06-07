<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.yaoli.common.CustomPropertyConfigurer"%>
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
	<script type="text/javascript" src="<%=basePath %>js/jqueryvalidation/validationextend.js"></script>

<body>
<div id="addsewagediv" class="easyui-panel" title="添加污水站点" style="width:auto;padding:30px 60px;height:100%">
<input type="hidden" id="isModify" value="${not empty sewage ? 'yes':'no' }"/>
<form id="submitform">
		<div class="config-items">
			 <div class="result-content">
					<table class="insert-tab" width="100%">
							  <tbody>
							    <tr>
							      <th width="20%">所属地区</th>
							      <td width="30%">
							      	<select style="width:60%" name="areaid" id="areaid">
							      			<option value="">请选择区县</option>
											<c:choose>
												<c:when test="${sewage !=null }">
													<c:forEach items="${allAreas}" var="item">
														<c:choose>
															<c:when test="${sewage.areaid == item.id }">
																<option value="${item.id}" selected="selected">${item.name}</option>
															</c:when>
															<c:otherwise>
																<option value="${item.id}">${item.name}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</c:when>
												<c:when test="${sewage == null }">
													<c:forEach items="${allAreas}" var="item">
														<option value="${item.id}">${item.name}</option>
													</c:forEach>
												</c:when>	
											</c:choose>
									</select>
							      </td>
							      <!-- 已经在区县配置中设置 -->
							      <th width="20%"><!-- 管理员 --></th>
							      <td width="30%">
							      </td>
							    </tr>
							    <tr>
							      <th width="20%">站点ID</th> 
							      <td width="30%">
		      						<c:choose>
										<c:when test="${sewage != null}">
											<c:out value="${sewage.sewageid}"></c:out>
											<input id="sewageid" name="sewageid" type="hidden" style="width:60%" value="${sewage.sewageid}"></input>
										</c:when>
										<c:otherwise>
											<input id="sewageid" name="sewageid" type="text" style="width:60%" ></input>
										</c:otherwise>
									</c:choose>
									不可更改
							      </td>
							      <th width="20%">控制ID</th>
							      <td width="30%">
							      	<input id="controlid" name="controlid" type="text"  style="width:60%" value="${not empty sewage ? sewage.controlid:null }">
							      </td>
							    </tr>


							    <tr>
							      <th width="20%">简称</th>
							      <td width="30%">
							      	<input id="shortTitle" name="shortTitle"  type="text"   style="width:60%" value="${not empty sewage ? sewage.shortTitle:null }">
							      </td>
							      <th width="20%">名称</th>
							      <td width="30%">
							      	<input id="name" name="name" type="text"   style="width:60%" value="${not empty sewage ? sewage.name:null }">
							      </td>
							    </tr>
							    <tr>
							      <th width="20%">运营编号</th>
							      <td width="30%">
							      	<input id="operationnum" name="operationnum" type="text"   style="width:60%" value="${not empty sewage ? sewage.operationnum:null }">
							      </td>
							      <th width="20%">地址</th>
							      <td width="30%">
							      	<input id="address" name="address" type="text"   style="width:60%" value="${not empty sewage ? sewage.address:null }">
							      </td>
							    </tr>
							    <tr>
							      <th width="20%">纬度</th>
							      <td width="30%">
							      	<input id="coordinatey" name="coordinatey" type="text"  style="width:60%" value="${not empty sewage ? sewage.coordinatey:null }">
							      </td>
							      <th width="20%">经度</th>
							      <td width="30%">
							      	<input id="coordinatex" name="coordinatex" type="text"  style="width:60%" value="${not empty sewage ? sewage.coordinatex:null }">
							      </td>
							    </tr>



							    <tr>
							      <th width="20%">T(温度)上限</th>
							      <td width="30%">
							      	<input id="detection1ul" name="detection1ul" type="text"    style="width:60%" value="${not empty sewage ? sewage.detection1ul:'30' }">
							      </td>
							      <th width="20%">T(温度)下限</th>
							      <td width="30%">
							      	<input id="detection1dl" name="detection1dl" type="text"   style="width:60%" value="${not empty sewage ? sewage.detection1dl:'-10' }">
							      </td>
							    </tr>
							    <tr>
							      <th width="20%">PH上限</th>
							      <td width="30%">
							      	<input id="detection2ul" name="detection2ul" type="text"   style="width:60%" value="${not empty sewage ? sewage.detection2ul:'9.6' }">
							      </td>
							      <th width="20%">PH下限</th>
							      <td width="30%">
							      	<input id="detection2dl" name="detection2dl" type="text"   style="width:60%" value="${not empty sewage ? sewage.detection2dl:'6' }">
							      </td>
							    </tr>
							    <tr>
							      <th width="20%">ORP上限</th>
							      <td width="30%">
							      	<input id="detection3ul" name="detection3ul" type="text"   style="width:60%" value="${not empty sewage ? sewage.detection3ul:'700' }">
							      </td>
							      <th width="20%">ORP下限</th>
							      <td width="30%">
							      	<input id="detection3dl" name="detection3dl" type="text"   style="width:60%" value="${not empty sewage ? sewage.detection3dl:'-700' }">
							      </td>
							    </tr>

							    <tr>
							      <th width="20%">DO上限</th>
							      <td width="30%">
							      	<input id="detection5ul" name="detection5ul" type="text"   style="width:60%" value="${not empty sewage ? sewage.detection5ul:'15' }">
							      </td>
							      <th width="20%">DO下限</th>
							      <td width="30%">
							      	<input id="detection5dl" name="detection5dl" type="text"   style="width:60%" value="${not empty sewage ? sewage.detection5dl:'2' }">
							      </td>
							    </tr>




 							    <tr>
							      <th width="20%">日消减COD值</th>
							      <td width="30%">
							      	<input id="reducecod" name="reducecod" type="text"   style="width:60%" value="${not empty sewage ? sewage.reducecod:'200' }">
							      </td>
							      <th width="20%">日消减NH3-N值</th>
							      <td width="30%">
							      	<input id="reducenh3n" name="reducenh3n" type="text"   style="width:60%" value="${not empty sewage ? sewage.reducenh3n:'30' }">
							      </td>
							    </tr>
							    
 							    <tr>
							      <th width="20%">日消减P值</th>
							      <td width="30%">
							      	<input id="reducep" name="reducep" type="text"   style="width:60%" value="${not empty sewage ? sewage.reducep:'8' }">
							      </td>
							      <th width="20%"></th>
							      <td width="30%">
							      	
							      </td>
							    </tr>
							    
 							    <tr>
							      <th width="20%">视频puid</th>
							      <td width="30%">
							      	<input id="videopuid" name="videopuid" type="text"   style="width:60%" value="${not empty sewage ? sewage.videopuid:null }">
							      </td>
							      <th width="20%">吨位</th>
							      <td width="30%">
							      	<input id="tonnage" name="tonnage" type="text"   style="width:60%" value="${not empty sewage ? sewage.tonnage:'10' }">
							      </td>
							    </tr>
 							    <tr>
<%--							      <th width="20%">视频puid</th>
							      <td width="30%">
							      	<input id="videopuid" name="videopuid" type="text"   style="width:60%" value="${not empty sewage ? sewage.videopuid:null }">
							      </td>--%>
							      <th width="20%">排放标准</th>
							      <td width="30%">
							      	<input id="emissionStandard" name="emissionStandard" type="text"   style="width:60%" value="${not empty sewage ? sewage.emissionStandard:'一级B' }">
							      </td>
								<th width="20%"></th>
								<td width="30%">

								</td>
							    </tr>
							    

								 
								 
							    <!-- 注意以下只有在江都系统的时候才显示 -->
								<% 
								%>

											<tr>
										      <th width="20%">风机运行时间设置</th>
										      <td width="30%">
										      	<input id="runtimeperiod1" name="runtimeperiod1" type="text"   style="width:60%" value="${not empty sewage ? sewage.runtimeperiod1:null }">
										      </td>
										      <th width="20%">风机停止时间设置</th>
										      <td width="30%">
										      	<input id="stoptimeperiod1" name="stoptimeperiod1" type="text"   style="width:60%" value="${not empty sewage ? sewage.stoptimeperiod1:null }">
										      </td>
										    </tr>
										    
										    <tr>
										      <th width="20%">混合液回流泵运行时间设置</th>
										      <td width="30%">
										      	<input id="runtimeperiod2" name="runtimeperiod2" type="text"   style="width:60%" value="${not empty sewage ? sewage.runtimeperiod2:null }">
										      </td>
										      <th width="20%">混合液回流泵停止时间设置</th>
										      <td width="30%">
										      	<input id="stoptimeperiod2" name="stoptimeperiod2" type="text"   style="width:60%" value="${not empty sewage ? sewage.stoptimeperiod2:null }">
										      </td>
										    </tr>
										    							    
										    <tr>
										      <th width="20%">污泥回流泵运行时间设置</th>
										      <td width="30%">
										      	<input id="runtimeperiod3" name="runtimeperiod3" type="text"   style="width:60%" value="${not empty sewage ? sewage.runtimeperiod3:null }">
										      </td>
										      <th width="20%">污泥回流泵停止时间设置</th>
										      <td width="30%">
										      	<input id="stoptimeperiod3" name="stoptimeperiod3" type="text"   style="width:60%" value="${not empty sewage ? sewage.stoptimeperiod3:null }">
										      </td>
										    </tr>
										    
										    <tr>
										      <th width="20%">电磁阀1运行时间设置</th>
										      <td width="30%">
										      	<input id="runtimeperiod4" name="runtimeperiod4" type="text"   style="width:60%" value="${not empty sewage ? sewage.runtimeperiod4:null }">
										      </td>
										      <th width="20%">电磁阀1停止时间设置</th>
										      <td width="30%">
										      	<input id="stoptimeperiod4" name="stoptimeperiod4" type="text"   style="width:60%" value="${not empty sewage ? sewage.stoptimeperiod4:null }">
										      </td>
										    </tr>
										    
										    <tr>
										      <th width="20%">电磁阀2运行时间设置</th>
										      <td width="30%">
										      	<input id="runtimeperiod5" name="runtimeperiod5" type="text"   style="width:60%" value="${not empty sewage ? sewage.runtimeperiod5:null }">
										      </td>
										      <th width="20%">电磁阀2停止时间设置</th>
										      <td width="30%">
										      	<input id="stoptimeperiod5" name="stoptimeperiod5" type="text"   style="width:60%" value="${not empty sewage ? sewage.stoptimeperiod5:null }">
										      </td>
										    </tr>
										    
										 	<tr>
										      <th width="20%">电磁阀3运行时间设置</th>
										      <td width="30%">
										      	<input id="runtimeperiod6" name="runtimeperiod6" type="text"   style="width:60%" value="${not empty sewage ? sewage.runtimeperiod6:null }">
										      </td>
										      <th width="20%">电磁阀3停止时间设置</th>
										      <td width="30%">
										      	<input id="stoptimeperiod6" name="stoptimeperiod6" type="text"   style="width:60%" value="${not empty sewage ? sewage.stoptimeperiod6:null }">
										      </td>
										    </tr>
										    
										 	<tr>
										      <th width="20%">电磁阀4运行时间设置</th>
										      <td width="30%">
										      	<input id="runtimeperiod7" name="runtimeperiod7" type="text"   style="width:60%" value="${not empty sewage ? sewage.runtimeperiod7:null }">
										      </td>
										      <th width="20%">电磁阀4停止时间设置</th>
										      <td width="30%">
										      	<input id="stoptimeperiod7" name="stoptimeperiod7" type="text"   style="width:60%" value="${not empty sewage ? sewage.stoptimeperiod7:null }">
										      </td>
										    </tr>
								<%
								%>
							    
							    
							    <tr>
							      <th width="20%"></th>
							      <td width="70%" colspan="3">
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
				postUrl = getContextPath()+"/monitor/addnewsewage.do";
			}else{
				postUrl = getContextPath()+"/monitor/updatesewage.do";
			}
			
			$.ajax({
			type:"POST",
			url:postUrl,
			dataType:"json",
			contentType:"application/json;charset=utf-8",
			data:postdata,
			success:function(data){
					if(data.key == "pass"){
						if($("#isModify").val() == 'yes'){
							$.messager.alert("提示","你已经添加成功","info");
						}else{
							$.messager.alert("提示","保存成功","info");
						}

					}
				}
			});
		}

	});
	
	$("#submitform").validate({
		rules: {
			areaid:"required",
			sewageid:{
				required:true,
				digits:true
			},
			name:"required",
			shortTitle:"required",
			operationnum:"required",
			coordinatey:{
				required:true,
				number:true
			},
			coordinatex:{
				required:true,
				number:true
			},
			detection1ul:"number",
			detection2ul:"number",
			detection3ul:"number",
			detection5ul:"number",
			runtimeperiod1:"number",
			runtimeperiod2:"number",
			runtimeperiod3:"number",
			runtimeperiod4:"number",
			runtimeperiod5:"number",
			runtimeperiod6:"number",
			stoptimeperiod1:"number",
			stoptimeperiod2:"number",
			stoptimeperiod3:"number",
			stoptimeperiod4:"number",
			stoptimeperiod5:"number",
			stoptimeperiod6:"number",
			sewageid:{
				required:true,
				checkedSewageIdExist:true
			}
		},
		messages: {
			areaid:"请选择一个地区",
			sewageid: {
				required: "请输入编号",
				digits:"必须输入整数"
			},
			name:"请输入名称",
			shortTitle:"请输入简称",
			operationnum:"请输入运营编号",
			coordinatey:{
				required:"请输入纬度",
				number:"格式不正确"
			},
			coordinatex:{
				required:"请输入经度",
				number:"格式不正确"
			},
			detection1ul:"格式不正确",
			detection2ul:"格式不正确",
			detection3ul:"格式不正确",
			detection5ul:"格式不正确",
			runtimeperiod1:"格式不正确",
			runtimeperiod2:"格式不正确",
			runtimeperiod3:"格式不正确",
			runtimeperiod4:"格式不正确",
			runtimeperiod5:"格式不正确",
			runtimeperiod6:"格式不正确",
			stoptimeperiod1:"格式不正确",
			stoptimeperiod2:"格式不正确",
			stoptimeperiod3:"格式不正确",
			stoptimeperiod4:"格式不正确",
			stoptimeperiod5:"格式不正确",
			stoptimeperiod6:"格式不正确",
			sewageid:{
				required:"请输入id",
				checkedSewageIdExist:"该id已经存在"
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