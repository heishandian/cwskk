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
	<!-- 必须添加这个 -->
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<!-- <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=lro9rUlcpaFKtWd1F19DuPPs"></script> -->
	
	<!-- <link rel="stylesheet" type="text/css" href="css/default.css" /> -->
	<!-- <link rel="stylesheet" type="text/css" href="css/common.css" /> -->
	<!-- <link rel="stylesheet" type="text/css" href="css/table_css.css" /> -->
	
<%-- 	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/common_main.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main_main.css" />
	
    <script type="text/javascript" src="<%=basePath%>js/map/gmap.js"></script> --%>
	
	<!-- jqgrid -->
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath%>css/jqgrid/jquery-ui-1.7.1.custom.css" />
	
	<%-- <script src="<%=basePath%>js/i18n/grid.locale-cn.js" type="text/javascript"></script> --%>
	<!-- 一定要注意加载顺序 jquery loader jqgrid -->
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.2.min.js"></script>
	<%-- <script src="<%=basePath%>js/i18n/grid.loader.js" type="text/javascript"></script> --%>
	<script src="<%=basePath%>js/i18n/grid.locale-cn.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/i18n/jquery.jqGrid.min.js" type="text/javascript"></script>
	
	
	
	<style type="text/css">
	body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
<body>
	<table id="dataGrid"></table>
	<div class="search-content">
		<form action="#" method="post">
			<table class="search-tab">
				<tr>
					<!-- <th width="70">关键字:</th> -->
					<td><input class="common-text" placeholder="请输入地点简称或者运营号" name="keywords" id="keywords" value="" id="" type="text">
					</td>
					<td><input class="btn" name="seachpoint" id="seachpoint" value="搜索" type="button">
					</td>
					<td><select style="width:200px" name="searchlist_areaid" id="searchlist_areaid">
					<%--<c:forEach items="${allAreas}" var="item">
							<option value="${item.areaid}">${item.name}</option>
						</c:forEach> --%>
					</select>
					</td>
					<td><input class="btn" name="seachlist" id="seachlist" value="选定" type="button">
				</tr>
			</table>
		</form>
	</div>


</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
	$("#dataGrid").jqGrid({
	
	datatype: "local",//数据类型
	
	height:400,//高度
	
	width:900,
	
	colNames:['编号','日期', '客户', '数量','税金','总金额','备注'],//列名
	
	colModel:[
		
		{name:'id',index:'id', width:60, sorttype:"int"},
		
		{name:'invdate',index:'invdate', width:90, sorttype:"date"},
		
		{name:'name',index:'name', width:100},
		
		{name:'amount',index:'amount', width:80, align:"right",sorttype:"float"},
		
		{name:'tax',index:'tax', width:80, align:"right",sorttype:"float"},
		
		{name:'total',index:'total', width:80,align:"right",sorttype:"float"},
		
		{name:'note',index:'note', width:150, sortable:false}
		
		],
		
		multiselect: true,//支持多项选择
		
		caption: "jqgrid测试"//列表标题
	
	});
	
	var mydata = [
	
		{id:"1",invdate:"2007-10-01",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
		
		{id:"2",invdate:"2007-10-02",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
		
		{id:"3",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
		
		{id:"4",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
		
		{id:"5",invdate:"2007-10-05",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
		
		{id:"6",invdate:"2007-09-06",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"},
		
		{id:"7",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"},
		
		{id:"8",invdate:"2007-10-03",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"},
		
		{id:"9",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"}
	
	];//测试数据
	
	for(var i=0;i<=mydata.length;i++)
	
	$("#dataGrid").jqGrid('addRowData',i+1,mydata[i]);
	
});

</script>