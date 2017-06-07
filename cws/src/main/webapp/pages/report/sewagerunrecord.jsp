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
	
	<script type="text/javascript" src="<%=basePath %>js/login/tooltips.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/easyui/easyui-lang-zh_CN.js"></script>
	
	<script type="text/javascript" src="<%=basePath %>js/timeformat/timeformattool.js"></script>
	
	<script language="javascript" type="text/javascript" src="<%=basePath %>js/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>js/My97DatePicker/skin/WdatePicker.css">	

	<script type="text/javascript" src="<%=basePath %>js/sewagerunrecord_month/equipment1month.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/sewagerunrecord_month/equipment2month.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/sewagerunrecord_month/equipment3month.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/sewagerunrecord_month/equipment4month.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/sewagerunrecord_month/equipment5month.js"></script>
	
	<script type="text/javascript" src="<%=basePath %>js/sewagerunrecord_month/detection1month.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/sewagerunrecord_month/detection2month.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/sewagerunrecord_month/detection3month.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/sewagerunrecord_month/detection4month.js"></script>

	<script type="text/javascript" src="<%=basePath %>js/sewagerunrecord_year/equipment1year.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/sewagerunrecord_year/equipment2year.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/sewagerunrecord_year/equipment3year.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/sewagerunrecord_year/equipment4year.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/sewagerunrecord_year/equipment5year.js"></script>
	
	<script type="text/javascript" src="<%=basePath %>js/sewagerunrecord_year/detection1year.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/sewagerunrecord_year/detection2year.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/sewagerunrecord_year/detection3year.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/sewagerunrecord_year/detection4year.js"></script>
<body>
	<!-- 站点运行记录 -->
	<table id="showForm" class="easyui-datagrid"  style="width:100%;height:114px">
	
	</table>
	
	<table id="watershowForm" class="easyui-datagrid"  style="width:100%;height:78px">
	
	</table>
	<div id="tb" style="padding:5px;height:auto;display:none">
		<div>
			请选择报表类型
	      	<select style="width:100px" name="reporttype" id="reporttype">
	      			<option value="2">站点月报</option>
	      			<option value="3">站点年报</option>
			</select>
			请选择日期：<input id="testingtime" type="text" style="width:120px" onfocus="new WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM',alwaysUseStartDate:true})"/>
	      	<select style="width:200px" name="areaid" id="areaid">
	      			<option value="-1">请选择区县</option>
					<c:forEach items="${allAreas}" var="item">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
			</select>
	      	<select style="width:200px" name="sewageid" id="sewageid">
	      			<option value="-1">请选择站点</option>
			</select>
			<a id="searchbtn" href="#" class="easyui-linkbutton">查找</a>
			<!-- <a id="downloadexcel" href="#" class="easyui-linkbutton">导出报表</a> -->
		</div>
	</div>
   	
   	<div id="month" style="width:100%;">
   	<div id="chart1" style="float:left;height:200px;width:100%">
	</div>  
   	<div id="chart2" style="float:left;height:200px;width:100%">
	</div>  
   	<div id="chart3" style="float:left;height:200px;width:100%">
	</div>  
   	<div id="chart4" style="float:left;height:200px;width:100%">
	</div>  
   	<div id="chart5" style="float:left;height:200px;width:100%">
	</div>  
   	<div id="chart6" style="float:left;height:200px;width:100%">
	</div>  
   	<div id="chart7" style="float:left;height:200px;width:100%">
	</div>  
   	<div id="chart8" style="float:left;height:200px;width:100%">
	</div>

		<!--加药泵-->
	<div id="chart9" style="float:left;height:200px;width:100%">
	</div>
	</div>
   	
   	<div id="year" style="width:100%">
   	<div id="chart11" style="float:left;height:200px;width:100%">
	</div>  
   	<div id="chart12" style="float:left;height:200px;width:100%">
	</div>  
   	<div id="chart13" style="float:left;height:200px;width:100%">
	</div>  
   	<div id="chart14" style="float:left;height:200px;width:100%">
	</div>  
   	<div id="chart15" style="float:left;height:200px;width:100%">
	</div>  
   	<div id="chart16" style="float:left;height:200px;width:100%">
	</div>  
   	<div id="chart17" style="float:left;height:200px;width:100%">
	</div>  
   	<div id="chart18" style="float:left;height:200px;width:100%">
	</div>
		<!--加药泵-->
	<div id="chart19" style="float:left;height:200px;width:100%">
	</div>
	</div>

	
</body>
</html>


<script type="text/javascript">
$(document).ready(function(){
	$("#areaid").change(function(){ 
		getSewageByareaId();
	});
	$("#reporttype").change(function(){ 
		reporttypeChange();
	});
	$("#showForm").datagrid({"onLoadSuccess":function(data){
            if (data.total == 0) {
            	show_err_msg('暂无搜索结果');
            }
            //如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
            else $("#showForm").closest('div.datagrid-wrap').find('div.datagrid-pager').show();
	}});
	
	$.ajax({
		type : "POST",
		url : getContextPath()+"/report/getrundataXtoken.do",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		async : false,
		success : function(returndata) {
			for(var i = 0; i < returndata[1].length;i++){
				var name = returndata[1][i].field;
				if(name.substring(0,6) == "detect"){
					returndata[1][i].formatter = detectionfun;
				}
				if(name.substring(0,5) == "equip"){
					returndata[1][i].formatter = equipmentfun;
				}
			}
			$('#showForm').datagrid({
				columns:returndata
			});
		}
	});
	
	$.ajax({
		type : "POST",
		url : getContextPath()+"/report/getwaterdataXtoken.do",
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		async : false,
		success : function(returndata) {
			for(var i = 0; i < returndata[1].length;i++){
				var name = returndata[1][i].field;
				if(name.substring(0,6) == "detect"){
					returndata[1][i].formatter = detectionfun;
				}
				if(name.substring(0,5) == "equip"){
					returndata[1][i].formatter = equipmentfun;
				}
			}
			$('#watershowForm').datagrid({
				columns:returndata
			});
		}
	});
	
});

equipmentfun = function(value,row,index)
{

	if(typeof(value)=='undefined'){
		return "0";
	}
	if(typeof(value)!='undefined'){
		return value;
	}
}

detectionfun = function(value,row,index)
{
	if(typeof(value)=='undefined'){
		return "0.00";
	}
	if(typeof(value)!='undefined'){
		return value.toFixed(2);
	}
}

$('#showForm').datagrid({
	frozenColumns:[[
		{field:'sewagename',title:'污水站点',align:'center',width:100}
	]],
    idField:"id",
    fitColumns:true,
    toolbar: '#tb',
});
$('#watershowForm').datagrid({
	frozenColumns:[[
		{field:'sewagename',title:'污水站点',align:'center',width:100}
	]],
    idField:"id",
    fitColumns:true,
});

function reporttypeChange(){
	var reporttype = $("#reporttype").val();
	if(reporttype == 2){
		$("#testingtime").attr("onfocus","new WdatePicker({startDate:'%y-%M',dateFmt:'yyyy-MM',alwaysUseStartDate:true})");
		$("#year").hide();
		$("#month").show();
	}else if(reporttype == 3){
		$("#testingtime").attr("onfocus","new WdatePicker({startDate:'%y',dateFmt:'yyyy',alwaysUseStartDate:true})");
		$("#year").show();
		$("#month").hide();
	}
	
	
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

$("#searchbtn").click(function () {
 	if($("#sewageid").val() == -1 || $("#testingtime").val() == ""){
		show_err_msg('请您选择站点或者日期');
		return ;
	}
	var url = "";
	var reporttype = $("#reporttype").val();
	if(reporttype == 2){
		url = getContextPath()+"/report/getQuxianStatisticMonthByConditionAndSewageid.do";
	}else if(reporttype == 3){
		url = getContextPath()+"/report/getQuxianStatisticYearByConditionAndSewageid.do";
	}
	
	$("#showForm").datagrid({
		"url":url,
		"queryParams":{
			areaid:$("#areaid").val(),
			sewageid:($("#sewageid").val()),
			testingtime:$("#testingtime").val()
		}	
	});
	$("#showForm").datagrid("load");
	
	$("#watershowForm").datagrid({
		"url":url,
		"queryParams":{
			areaid:$("#areaid").val(),
			sewageid:($("#sewageid").val()),
			testingtime:$("#testingtime").val()
		}	
	});
	$("#watershowForm").datagrid("load");
	
	//获取信息
	if(reporttype == 2){
		ajaxgetinfo_month();
	}else if(reporttype == 3){
		ajaxgetinfo_year();
	}
});

/* $("#downloadexcel").click(function () {
 	if($("#sewageid").val() == -1 || $("#testingtime").val() == ""){
		show_err_msg('请您选择站点或者日期');
		return ;
	}

	var url = "";
	var reporttype = $("#reporttype").val();
	
	
	var exceltype = 1; //excel类型 1表示设备运行记录 2表示水质检测记，本jsp是设备运行记录
	if(reporttype == 1){
		url = getContextPath()+"/report/getexcelexportbyday.do?areaid="+$("#areaid").val()+"&testingtime="+$("#testingtime").val()+"&exceltype=1";
	}else if(reporttype == 2){
		url = getContextPath()+"/report/getexcelexportbymonth.do?areaid="+$("#areaid").val()+"&testingtime="+$("#testingtime").val()+"&exceltype=1";
	}else if(reporttype == 3){
		url = getContextPath()+"/report/getexcelexportbyyear.do?areaid="+$("#yearareaid").val()+"&testingtime="+$("#yeartestingtime").val()+"&exceltype=1";
	}
	window.open(url,'_self');
}); */
/*以下高度高度自适应*/
$('#showForm').datagrid('resize', {
	//width:function(){return document.body.clientWidth;},
	//height:function(){return document.body.clientHeight;},
});

function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
</script>


<script src="<%=basePath %>js/echarts/echarts.js"></script>
<script type="text/javascript">
//先来月报


//横轴坐标
window.xtokes = [];
//曝气机
window.mychart1;
//提升泵
window.mychart2;

//回流泵
window.mychart3;
//排水泵
window.mychart4;

window.mychart5;

window.mychart6;

window.mychart7;

window.mychart8;

//加药泵
window.mychart9;



//曝气机
window.mychart11;
//提升泵
window.mychart12;

//回流泵
window.mychart13;
//排水泵
window.mychart14;

window.mychart15;

window.mychart16;

window.mychart17;

window.mychart18;

//加药泵
window.mychart19;

function initxdata(){
   	var arr = [];
   	var now = new Date();
   	var len = 31;//now.getHours();
   	for(var i = 1;i<=len ; i++){
   		arr.push("-");
   	}
  	return arr;
}
function initxtoken(){
     var now = new Date();
     var res = [];
     var len = 31;//now.getHours();
     while (len > 0) {
        res.unshift(len);
        len = len - 1 ;
     }
     return res
}

function initxdatayear(){
   	var arr = [];
   	var now = new Date();
   	var len = 12;//now.getHours();
   	for(var i = 1;i<=len ; i++){
   		arr.push("-");
   	}
  	return arr;
}
function initxtokenyear(){
     var now = new Date();
     var res = [];
     var len = 12;//now.getHours();
     while (len > 0) {
        res.unshift(len);
        len = len - 1 ;
     }
     return res
}


$(document).ready(function(){
	initxtoken();
	chart1();
	chart2();
	chart3();
	chart4();
	chart5();
	chart6();
	chart7();
	chart8();
	chart9();
	
	chart11();
	chart12();
	chart13();
	chart14();
	chart15();
	chart16();
	chart17();
	chart18();
	chart19();
	
});
//非常重要，必须用onload 这样可以让 报表初始化成功
window.onload = function(){
	$("#year").hide();
};


function ajaxgetinfo_month(){
	//var temp = [];
	var sewageid = $("#sewageid").val();
	var data = "sewageid="+sewageid+"&testingtime="+$("#testingtime").val()+"&areaid="+$("#areaid").val();
	var postUrl = getContextPath()+"/report/getsewagerundataMonthPicBycondition.do?"+data;
	//var data = {"sewageid":sewageid,"testingtime":$("#testingtime").val(),"areaid":$("#areaid").val()};

	
	
  	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		//data:JSON.stringify(data),
		async:false,
		success:function(data){
		    var flag = false;
            if(data.length == 0){
           		show_err_msg('暂无搜索结果');
            }
			//不管哪个月都是31天
            for(var i = 1;i<= 31;i++){
     			flag = false;
            	for(var j = 0; j < data.length; j++){
            		if(i == (data[j].day)){ //天数是否相等

			        	var eq1no = data[j].equip1normaltime.toFixed(2);
			        	var eq1abno = data[j].equip1abnormaltime.toFixed(2);
			        	
			        	var eq2no = data[j].equip2normaltime.toFixed(2);
			        	var eq2abno = data[j].equip2abnormaltime.toFixed(2);
			        	
			        	var eq3no = data[j].equip3normaltime.toFixed(2);
			        	var eq3abno = data[j].equip3abnormaltime.toFixed(2);
			        	
			        	var eq4no = data[j].equip4normaltime.toFixed(2);
			        	var eq4abno = data[j].equip4abnormaltime.toFixed(2);

						//加药泵
						var eq9no = data[j].equip5normaltime.toFixed(2);
						var eq9abno = data[j].equip5abnormaltime.toFixed(2);

						var avg1 = data[j].detection1avg.toFixed(2);
						var max1 = data[j].detection1max.toFixed(2);
						var min1 = data[j].detection1min.toFixed(2);
						
						var avg2 = data[j].detection2avg.toFixed(2);
						var max2 = data[j].detection2max.toFixed(2);
						var min2 = data[j].detection2min.toFixed(2);
						
						var avg3 = data[j].detection3avg.toFixed(2);
						var max3 = data[j].detection3max.toFixed(2);
						var min3 = data[j].detection3min.toFixed(2);
						
						// avg4是液位 而液位不需要显示
						var avg5 = data[j].detection5avg.toFixed(2);
						var max5 = data[j].detection5max.toFixed(2);
						var min5 = data[j].detection5min.toFixed(2);
						


					    mychart1.addData([
					        [
					            0,        // 系列索引
					            eq1no, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart1.addData([
					        [
					            1,        // 系列索引
					            eq1abno, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    
					    mychart2.addData([
					        [
					            0,        // 系列索引
					            eq2no, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart2.addData([
					        [
					            1,        // 系列索引
					            eq2abno, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart3.addData([
					        [
					            0,        // 系列索引
					            eq3no, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart3.addData([
					        [
					            1,        // 系列索引
					            eq3abno, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart4.addData([
					        [
					            0,        // 系列索引
					            eq4no, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart4.addData([
					        [
					            1,        // 系列索引
					            eq4abno, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    
					    mychart5.addData([
					        [
					            0,        // 系列索引
					            avg1, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart5.addData([
					        [
					            1,        // 系列索引
					            max1, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart5.addData([
					        [
					            2,        // 系列索引
					            min1, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    
					    mychart6.addData([
					        [
					            0,        // 系列索引
					            avg2, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart6.addData([
					        [
					            1,        // 系列索引
					            max2, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart6.addData([
					        [
					            2,        // 系列索引
					            min2, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart7.addData([
					        [
					            0,        // 系列索引
					            avg3, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart7.addData([
					        [
					            1,        // 系列索引
					            max3, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart7.addData([
					        [
					            2,        // 系列索引
					            min3, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart8.addData([
					        [
					            0,        // 系列索引
					            avg5, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart8.addData([
					        [
					            1,        // 系列索引
					            max5, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart8.addData([
					        [
					            2,        // 系列索引
					            min5, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);

						mychart9.addData([
							[
								0,        // 系列索引
								eq9no, // 新增数据
								false,     // 新增数据是否从队列头部插入
								false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
							]
						]);
						mychart9.addData([
							[
								1,        // 系列索引
								eq9abno, // 新增数据
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
	 	         	mychart4.addData([
				        [
				            0,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
	 	         	mychart4.addData([
				        [
				            1,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
				    
				    mychart5.addData([
				        [
				            0,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
				    mychart5.addData([
				        [
				            1,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
				    mychart5.addData([
				        [
				            2,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
					    mychart6.addData([
					        [
					            0,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart6.addData([
					        [
					            1,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart6.addData([
					        [
					            2,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart7.addData([
					        [
					            0,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart7.addData([
					        [
					            1,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart7.addData([
					        [
					            2,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart8.addData([
					        [
					            0,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart8.addData([
					        [
					            1,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart8.addData([
					        [
					            2,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					mychart9.addData([
						[
							0,        // 系列索引
							'-', // 新增数据
							false,     // 新增数据是否从队列头部插入
							false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
						]
					]);
					mychart9.addData([
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


function ajaxgetinfo_year(){
	//var temp = [];
	var sewageid = $("#sewageid").val();
	var data = "sewageid="+sewageid+"&testingtime="+$("#testingtime").val()+"&areaid="+$("#areaid").val();
	var postUrl = getContextPath()+"/report/getsewagerundataYearPicBycondition.do?"+data;
	//var data = {"sewageid":sewageid,"testingtime":$("#testingtime").val(),"areaid":$("#areaid").val()};

	
	
  	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		//data:JSON.stringify(data),
		async:false,
		success:function(data){
		    var flag = false;
            if(data.length == 0){
           		show_err_msg('暂无搜索结果');
            }
			//一年12个月
            for(var i = 1;i<= 12;i++){
     			flag = false;
            	for(var j = 0; j < data.length; j++){
            		if(i == (data[j].month)){ //天数是否相等

			        	var eq1no = data[j].equip1normaltime.toFixed(2);
			        	var eq1abno = data[j].equip1abnormaltime.toFixed(2);
			        	
			        	var eq2no = data[j].equip2normaltime.toFixed(2);
			        	var eq2abno = data[j].equip2abnormaltime.toFixed(2);
			        	
			        	var eq3no = data[j].equip3normaltime.toFixed(2);
			        	var eq3abno = data[j].equip3abnormaltime.toFixed(2);
			        	
			        	var eq4no = data[j].equip4normaltime.toFixed(2);
			        	var eq4abno = data[j].equip4abnormaltime.toFixed(2);

						var eq9no = data[j].equip5normaltime.toFixed(2);
						var eq9abno = data[j].equip5abnormaltime.toFixed(2);

						var avg1 = data[j].detection1avg.toFixed(2);
						var max1 = data[j].detection1max.toFixed(2);
						var min1 = data[j].detection1min.toFixed(2);
						
						var avg2 = data[j].detection2avg.toFixed(2);
						var max2 = data[j].detection2max.toFixed(2);
						var min2 = data[j].detection2min.toFixed(2);
						
						var avg3 = data[j].detection3avg.toFixed(2);
						var max3 = data[j].detection3max.toFixed(2);
						var min3 = data[j].detection3min.toFixed(2);
						
						// avg4是液位 而液位不需要显示
						var avg5 = data[j].detection5avg.toFixed(2);
						var max5 = data[j].detection5max.toFixed(2);
						var min5 = data[j].detection5min.toFixed(2);
						


					    mychart11.addData([
					        [
					            0,        // 系列索引
					            eq1no, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart11.addData([
					        [
					            1,        // 系列索引
					            eq1abno, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    
					    mychart12.addData([
					        [
					            0,        // 系列索引
					            eq2no, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart12.addData([
					        [
					            1,        // 系列索引
					            eq2abno, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart13.addData([
					        [
					            0,        // 系列索引
					            eq3no, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart13.addData([
					        [
					            1,        // 系列索引
					            eq3abno, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart14.addData([
					        [
					            0,        // 系列索引
					            eq4no, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart14.addData([
					        [
					            1,        // 系列索引
					            eq4abno, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    
					    mychart15.addData([
					        [
					            0,        // 系列索引
					            avg1, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart15.addData([
					        [
					            1,        // 系列索引
					            max1, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart15.addData([
					        [
					            2,        // 系列索引
					            min1, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    
					    mychart16.addData([
					        [
					            0,        // 系列索引
					            avg2, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart16.addData([
					        [
					            1,        // 系列索引
					            max2, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart16.addData([
					        [
					            2,        // 系列索引
					            min2, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart17.addData([
					        [
					            0,        // 系列索引
					            avg3, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart17.addData([
					        [
					            1,        // 系列索引
					            max3, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart17.addData([
					        [
					            2,        // 系列索引
					            min3, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart18.addData([
					        [
					            0,        // 系列索引
					            avg5, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart18.addData([
					        [
					            1,        // 系列索引
					            max5, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart18.addData([
					        [
					            2,        // 系列索引
					            min5, // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
						mychart19.addData([
							[
								0,        // 系列索引
								eq9no, // 新增数据
								false,     // 新增数据是否从队列头部插入
								false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
							]
						]);
						mychart19.addData([
							[
								1,        // 系列索引
								eq9abno, // 新增数据
								false,     // 新增数据是否从队列头部插入
								false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
							]
						]);
					    flag = true;	
					    break;
            		}
            	}
            	if(flag == false){
	 	         	mychart11.addData([
				        [
				            0,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
	 	         	mychart11.addData([
				        [
				            1,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
	 	         	mychart12.addData([
				        [
				            0,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
	 	         	mychart12.addData([
				        [
				            1,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
	 	         	mychart13.addData([
				        [
				            0,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
	 	         	mychart13.addData([
				        [
				            1,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
	 	         	mychart14.addData([
				        [
				            0,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
	 	         	mychart14.addData([
				        [
				            1,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
				    
				    mychart15.addData([
				        [
				            0,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
				    mychart15.addData([
				        [
				            1,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
				    mychart15.addData([
				        [
				            2,        // 系列索引
				            '-', // 新增数据
				            false,     // 新增数据是否从队列头部插入
				            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
				        ]
				    ]);
					    mychart16.addData([
					        [
					            0,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart16.addData([
					        [
					            1,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart16.addData([
					        [
					            2,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart17.addData([
					        [
					            0,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart17.addData([
					        [
					            1,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart17.addData([
					        [
					            2,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart18.addData([
					        [
					            0,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart18.addData([
					        [
					            1,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					    mychart18.addData([
					        [
					            2,        // 系列索引
					            '-', // 新增数据
					            false,     // 新增数据是否从队列头部插入
					            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
					        ]
					    ]);
					mychart19.addData([
						[
							0,        // 系列索引
							'-', // 新增数据
							false,     // 新增数据是否从队列头部插入
							false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
						]
					]);
					mychart19.addData([
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
</script>