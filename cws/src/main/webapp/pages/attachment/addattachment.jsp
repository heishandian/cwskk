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

<%-- 	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/common_main.css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main_main.css" />
 	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/easyui/themes/icon.css"> --%>
	<%-- <script type="text/javascript" src="<%=basePath %>js/easyui/jquery.min.js"></script> --%>
	
	
	<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.2.min.js"></script>
	
	<script language="javascript" type="text/javascript" src="<%=basePath %>js/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>js/My97DatePicker/skin/WdatePicker.css">	

	<script type="text/javascript" src="<%=basePath %>js/timeformat/timeformattool.js"></script>
	
	
	<link rel="stylesheet" href="<%=basePath %>css/jqueryvalidation/screen.css" type="text/css"></link>
	<script type="text/javascript" src="<%=basePath %>js/jqueryvalidation/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jqueryvalidation/validationextend.js"></script>
	
	<style type="text/css" >
	.bar {
    	height: 18px;
    	background: green;
	}
	</style>

<body>
<form id='form' action="<%=basePath %>/system/addattachment.do">
	<!-- <input id="fileupload" type="file" name="files[]" data-url="addattachment.do" multiple></br> -->
	<input id="fileupload" type="file" ></br>
	<input type="submit" />
</form>

<p/>
<button/>
<div id="progress">
    <div class="bar" style="width: 0%;"></div>
</div>
<script type="text/javascript" src="<%=basePath %>js/jqueryFileUpload/vendor/jquery.ui.widget.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jqueryFileUpload/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jqueryFileUpload/jquery.fileupload.js"></script>
<script>

</script>
</body>
</html>
