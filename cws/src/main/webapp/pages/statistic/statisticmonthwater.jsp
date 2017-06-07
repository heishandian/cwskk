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
	
	<script type="text/javascript" src="<%=basePath%>js/statistic/month.js"></script>
<body>
	<!-- 月处理水量 -->
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
								时间：<input id="testingtime" type="text" style="width:120px" onfocus="new WdatePicker({startDate:'%y-%M',dateFmt:'yyyy-MM',alwaysUseStartDate:true})"/>
								<a id="search" href="#" class="easyui-linkbutton">查找</a>
					      </td>
					    </tr>
					    <tr>
					      <td width="100%" height="100%" colspan="4">
					      		<div id="chart1" style="float:left;height:500px;width:1000px">
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
//温度
window.mychart1;

function initxdata(){
   	var arr = [];
   	var now = new Date();
   	var len = 31;
   	for(var i = 1;i<=len ; i++){
   		arr.push("-");
   	}
  	return arr;
}
function initxtoken(){
     var now = new Date();
     var res = [];
     var len = 31;
     while (len > 0) {
        res.unshift(len);
        len = len - 1 ;
     }
     return res
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
	
	initxtoken();
	chart1();
});
function ajaxGet5Info(){
	//var temp = [];
	var sewageid = $("#sewageid").val();
	var postUrl = getContextPath()+"/statistic/getstatisticmonthwater.do";
	var data = {"sewageid":sewageid,'testingtime':$("#testingtime").val()+'-01',operationnum:$("#operationnum").val()};


  	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(data),
		async:false,
		success:function(data){
			//如果是本月数据，平均水量字段无用，应该使用 总数量/当月几号计算，比如 今天是10号，那么 分母应该是10
			//如果是非本月数据，采用原始方法。

			var tonnage = data.tonnage;
			var avgWater = data.avgWater.toFixed(2);
			var data = data.statisticDayVOLists;

			//获取总水量

			var tempDate = new Date().format("yyyy-MM");

			var dateNum = parseInt(new Date().format("dd"));
			if(tempDate == $("#testingtime").val()){
				var sumWaterDate = 0.0;
				for(var j = 0; j < data.length; j++){
					sumWaterDate = parseFloat(sumWaterDate) + parseFloat((data[j].water).toFixed(2));
				}
				avgWater = sumWaterDate/dateNum;
			}


            var now = new Date();
            var flag = false;
            if(data.length == 0){
           		show_err_msg('暂无搜索结果');
            }
            //数据库中 00:00到00.59点表示0点的平均数据，以此类推

			//
			for(var i = 1;i <=31 ; i++){
				if(tempDate == $("#testingtime").val()){
					if(i <= dateNum ){
						mychart1.addData([
							[
								2,        // 系列索引
								avgWater, // 新增数据
								false,     // 新增数据是否从队列头部插入
								false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
							]
						]);
					}else{
						mychart1.addData([
							[
								2,        // 系列索引
								'-', // 新增数据
								false,     // 新增数据是否从队列头部插入
								false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
							]
						]);
					}
				}else {
					mychart1.addData([
						[
							2,        // 系列索引
							avgWater, // 新增数据
							false,     // 新增数据是否从队列头部插入
							false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
						]
					]);
				}
			}


			//begin for
            for(var i = 1;i<= 31;i++){
				//下面增加的是 设定值 需要每天显示一格
 	         	mychart1.addData([
			        [
			            1,        // 系列索引
			            tonnage, // 新增数据
			            false,     // 新增数据是否从队列头部插入
			            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
			        ]
			    ]);

            	flag = false;
            	for(var j = 0; j < data.length; j++){
            		var time = data[j].testingtime;
            		var day = parseInt((new Date(time).format("dd")));//(new Date(parseInt(time) / 1000).toLocaleString());//.replace(/年|月/g, "-").replace(/日/g, " ")).getDay();
            		if(i == (day)){ //时间是否相等

			        	//var date = data[j].testingtime;
			        	
			        	var water = data[j].water.toFixed(2);

					    mychart1.addData([
					        [
					            0,        // 系列索引
					            water, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);

//						if(tempDate == $("#testingtime").val() && i <= dateNum){
//							mychart1.addData([
//								[
//									2,        // 系列索引
//									avgWater, // 新增数据
//									false,     // 新增数据是否从队列头部插入
//									false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
//								]
//							]);
//						}

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
            	}
            }
			//end for

			console.log("asdfa");



		}
	});
}

$("#search").click(function () {
	if( ($("#sewageid").val() == -1 && $("#operationnum").val() == "")){
		show_err_msg('请您选择污水站点或者填写运行编号');
		return ;
	}
	if($("#testingtime").val() == ""){
		show_err_msg('请选择日期');
		return;
	}
	ajaxGet5Info();
});


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