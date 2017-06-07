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

	<script type="text/javascript" src="<%=basePath%>js/waterTest/cod.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/waterTest/nh3n.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/waterTest/p.js"></script>

	<script type="text/javascript" src="<%=basePath %>js/login/tooltips.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/timeformat/timeformattool.js"></script>
	
		<!--历史记录绘图-->
<body>
	<div class="result-wrap">
		<div class="config-items">
            <div class="result-content" style="width:100%;height:100%">
					<table class="search-tab" width="100%">
					  <tbody>
					    <tr>
					      <td width="100%">
							地区名称：
							<select id="areaid" name="areaid">
								<option value="">请选择区县</option>
							</select>
							污水站点名称：
							<select id="sewageid" name="sewageid">
								<option value="-1">请选择水污水站</option>
							</select>
							  运行编号：<input id="operationnum" type="text" style="width:120px"/>
							  站点名称：<input id="sewagename" type="text" style="width:120px"/>

								<a id="search" href="#" class="easyui-linkbutton">查找</a>
								<!-- <a id="loadtabledata" href="#" class="easyui-linkbutton">查找</a> -->
					      </td>
					    </tr>
					    <tr>
					      <td width="100%" height="100%" colspan="4">
					      		<div id="chart1" style="float:left;height:250px;width:1000px">
								</div>  
					      </td>
					    </tr>
					    <tr>
					      <td width="100%" height="100%" colspan="4">
					      		<div id="chart2" style="float:left;height:250px;width:1000px">
								</div>  
					      </td>
					    </tr>
					    <tr>
					      <td width="100%" height="100%" colspan="4">
					      		<div id="chart3" style="float:left;height:250px;width:1000px">
								</div>  
					      </td>
					    </tr>
					  </tbody>
					</table>
				</div>
			</div>
		</div>
</body>
</html>
 	<script src="<%=basePath %>js/echarts/echarts.js"></script>
<script type="text/javascript">
//横轴坐标
window.xtokes = [];

window.mychart1;

window.mychart2;

window.mychart3;

window.res = null;

function initxdata(){
	var data = []
	for(var i= 0 ;i<res.length;i++){
		data.push('-');
	}
	return data;
}
function initxtoken(){
	//if(res == null){
	    res = [];
		var sewageid = $("#sewageid").val();
		var postUrl = getContextPath()+"/wtm/ajaxHistoryCurveXToken.do";
		var data = {"sewageid":sewageid};
		
		//设置地区选择
		$.ajax({
			type : "POST",
			url : postUrl,
			dataType : "json",
			contentType : "application/json;charset=utf-8",
			async : false,
			data:JSON.stringify(data),
			success : function(returndata) {
				returndata = returndata.data;
			    $.each(returndata,function(index,data){
		        	res.push(new Date(data.testingtime).format("yyyy-MM-dd"));
				});
			}
		});
		
	//}
	return res;
}


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
		getSewageByareaId();
	});
	$("#sewageid").change(function(){ 
		getSewageInfoBySewageid();
	});
});
function ajaxGet5Info(){
	//var temp = [];
	var sewageid = $("#sewageid").val();
	var postUrl = getContextPath()+"/wtm/getHistoryCurve.do";
	var data = {"sewageid":sewageid};
	
  	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(data),
		async:false,
		success:function(data){
			data = data.data;
		
            var now = new Date();
            var flag = false;
            if(data.length == 0){
           		show_err_msg('暂无搜索结果');
            }
            
            for(var i = 0;i< res.length;i++){
            	flag = false;
            	for(var j = 0; j < data.length; j++){
            		//if(res[i] == (data[j].day)){ //时间是否相等
					var temptime = new Date(data[j].testingtime).format("yyyy-MM-dd"); 
					if(res[i] ==  temptime){
			        	
			        	var incod = data[j].incod;
			        	var outcod = data[j].outcod;
			        	var innh3n = data[j].innh3n;
			        	var outnh3n = data[j].outnh3n;
			       		var inp = data[j].inp;
			        	var outp = data[j].outp;

					    mychart1.addData([
					        [
					            0,        // 系列索引
					            incod, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart1.addData([
					        [
					            1,        // 系列索引
					            outcod, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);	
					    mychart2.addData([
					        [
					            0,        // 系列索引
					            innh3n, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);	
					    mychart2.addData([
					        [
					            1,        // 系列索引
					            outnh3n, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);	
					    mychart3.addData([
					        [
					            0,        // 系列索引
					            inp, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);	
					    mychart3.addData([
					        [
					            1,        // 系列索引
					            outp, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);	
					    
					    flag = true;
					    break;
            		}
            	}
            	if(flag == false){
					    mychart1.addData([
					        [
					            0,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart1.addData([
					        [
					            1,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);	
					    mychart2.addData([
					        [
					            0,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart2.addData([
					        [
					            1,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);	
					    mychart3.addData([
					        [
					            0,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart3.addData([
					        [
					            1,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);	
            	}
            } 
		}
	});
}

$("#search").click(function () {
	//&& $("#operationnum").val() == "") 或者填写运行编号
	if( $("#sewageid").val() == -1 ){
		show_err_msg('请您选择污水站点');
		return ;
	}
	//初始化 表格
	loadtable();

	initdata();
});



function loadtable(){
	chart1();
	chart2();
	chart3();
}

function initdata(){
	timeTicket = setTimeout(function (){
		ajaxGet5Info();
	}, 1500);
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
