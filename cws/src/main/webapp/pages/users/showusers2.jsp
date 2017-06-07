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

	<!-- 以下两个是必须的 -->
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath%>css/jqgrid/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="<%=basePath%>css/jqgrid/ui.jqgrid.css" />
	
	
	<!-- 一定要注意加载顺序 jquery loader jqgrid -->
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.2.min.js"></script>

 	<script src="<%=basePath%>js/i18n/grid.locale-cn.js" type="text/javascript"></script>
 	<script src="<%=basePath%>js/i18n/grid.base.js" type="text/javascript"></script>
 	<script src="<%=basePath%>js/i18n/grid.common.js" type="text/javascript"></script>
 	<script src="<%=basePath%>js/i18n/grid.formedit.js" type="text/javascript"></script>
 	<script src="<%=basePath%>js/i18n/grid.inlinedit.js" type="text/javascript"></script>
 	<script src="<%=basePath%>js/i18n/grid.celledit.js" type="text/javascript"></script>
 	<script src="<%=basePath%>js/i18n/grid.subgrid.js" type="text/javascript"></script>
 	<script src="<%=basePath%>js/i18n/grid.treegrid.js" type="text/javascript"></script>
 	<script src="<%=basePath%>js/i18n/grid.custom.js" type="text/javascript"></script>
 	<script src="<%=basePath%>js/i18n/grid.treegrid.js" type="text/javascript"></script>
 	<script src="<%=basePath%>js/i18n/grid.postext.js" type="text/javascript"></script>
 	<script src="<%=basePath%>js/i18n/grid.tbltogrid.js" type="text/javascript"></script>
 	<script src="<%=basePath%>js/i18n/grid.setcolumns.js" type="text/javascript"></script>
 	<script src="<%=basePath%>js/i18n/grid.import.js" type="text/javascript"></script>
 	<script src="<%=basePath%>js/i18n/jquery.fmatter.js" type="text/javascript"></script>
 	<script src="<%=basePath%>js/i18n/JsonXml.js" type="text/javascript"></script>
 	<script src="<%=basePath%>js/i18n/jquery.searchFilter.js" type="text/javascript"></script>
 	
	<script src="<%=basePath%>js/i18n/jquery.jqGrid.min.js" type="text/javascript"></script>	

<body>
<table id="dataGrid" ></table>
<div id="pager"></div>
<div id="pgtoolbar1"></div>
	<div id="tb" style="padding:5px;height:auto">
		<div>
			<a id="deleteUser" href="#" class="easyui-linkbutton">删选中地区</a>
			<a id="addUser" href="<%=basePath %>areas/gotoaddnewarea.do" class="easyui-linkbutton">增加区县</a>
			<input type="text">
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
$(document).ready(function(){
	$("#dataGrid").jqGrid({
	
	datatype: "json",//数据类型
	
	url:"/learnshiro/users/getuserdata.do",
	
	pager:"#pager",
	
	rowNum:5,
	
	rowList:[5,10],
	
	sortname:"id",
    viewrecords: true,
    sortorder: "asc",
    caption:"JSON Example",
    
    page:1,

	 colNames : [ 'Inv No', 'Date', 'Client'],
     colModel : [ 
                     {name : 'id',index : 'id',width : 55}, 
                     {name : 'loginname',index : 'invdate',width : 90}, 
                     {name : 'username',index : 'name asc, invdate',width : 100}
                   ],
                   
    toolbar : [ true, "top" ],
                   
	jsonReader : {  

		root:"rows",  

		page: "pageNumber",  

		total: "pageSize",  

		records: "total",
		
		repeatitems: false,

		cell: "cell",
		
		id: "id"
	}
	
	
	});
	
	
	  $("#t_dataGrid").append(
	  	$("#tb").html()
	  );
	doResize(); 
});


function doResize() { 
	var ss = getPageSize(); 
	$("#dataGrid").jqGrid('setGridWidth', ss.WinW-30).jqGrid('setGridHeight', ss.WinH-100); 
} 


function getPageSize() { 
	//http://www.blabla.cn/js_kb/javascript_pagesize_windowsize_scrollbar.html 
	var winW, winH; 
	if(window.innerHeight) {// all except IE 
		winW = window.innerWidth; 
		winH = window.innerHeight; 
	} else if (document.documentElement && document.documentElement.clientHeight) {// IE 6 Strict Mode 
		winW = document.documentElement.clientWidth; 
		winH = document.documentElement.clientHeight; 
	} else if (document.body) { // other 
		winW = document.body.clientWidth; 
		winH = document.body.clientHeight; 
	}  // for small pages with total size less then the viewport  
	return {WinW:winW, WinH:winH}; 
} 

</script>