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
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
	<head>
		<script type="text/javascript" src="<%=basePath %>js/video/jquery-1.8.2.min.js"></script>
		<%-- <script type="text/javascript" src="<%=basePath %>js/video/help.js"></script> --%>
		<script type="text/javascript" src="<%=basePath %>js/video/swfobject.js"></script>
		<script type="text/javascript">
		swfobject.registerObject("myId", "9.0.0", "expressInstall.swf");
		</script>
		<!-- <script type="text/javascript" src="help.js"></script> -->
	</head>
	<body>
<div id="Player"><!-- player should load here --></div>
<script language="javascript" type="text/javascript">
var flashvars = {
	'file':'plainFileNameWithNoExtension',
	//'streamer':'rtmp://61.160.70.100:10100/app/live?token=4b2ec966020411e685fbc81f66d85ed35b566a633655df7284ffab30873effd7',
	'streamer':'rtmp://61.160.70.100:10100/app/live?token=4c4d7c3e126411e685fbc81f66d85ed3fd2b7053f08a29b25383ac77a3d79c7c',
	'type':'rtmp',
	'rtmp.subscribe':'true',
	'autostart':'true'
};
var params = {
	'allowfullscreen':'true',
	'allowscriptaccess':'always',
	'bgcolor':'#19010F'
};
var attributes = {
	'id':'Player',
	'name':'Player'
};
swfobject.embedSWF('<%=basePath %>js/video/StrobeMediaPlayback.swf', 'Player', '640', '480', '9', 'expressInstall.swf', flashvars, params, attributes,callbackfun);
</script>

	<div id="playwindow">
		<div id="time">
		
		</div>
		<object width='640' height='377' id='StrobeMediaPlayback' name='StrobeMediaPlayback' type='application/x-shockwave-flash' classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000' >
			<param name='movie' value='<%=basePath %>js/video/StrobeMediaPlayback.swf' /> 
			<param name='quality' value='high' /> <param name='bgcolor' value='#000000' /> 
			<param name='allowfullscreen' value='true' /> 
			<!-- <param name='flashvars' value= '&src=rtmp://61.160.70.100:10100/app/live?token=4b2ec966020411e685fbc81f66d85ed35b566a633655df7284ffab30873effd7&autoHideControlBar=true&streamType=live&autoPlay=true&verbose=true'/> -->
			<param name='flashvars' value= '&src=rtmp://61.160.70.100:10100/app/live?token=4c4d7c3e126411e685fbc81f66d85ed3fd2b7053f08a29b25383ac77a3d79c7c&autoHideControlBar=true&streamType=live&autoPlay=true&verbose=true'/>
			<%-- <embed src='<%=basePath %>js/video/StrobeMediaPlayback.swf' width='640' height='377' id='StrobeMediaPlayback' quality='high' bgcolor='#000000' name='StrobeMediaPlayback' allowfullscreen='true' pluginspage='http://www.adobe.com/go/getflashplayer' flashvars='&src=rtmp://61.160.70.100:10100/app/live?token=4b2ec966020411e685fbc81f66d85ed35b566a633655df7284ffab30873effd7&autoHideControlBar=true&streamType=live&autoPlay=true&verbose=true' type='application/x-shockwave-flash'> --%> 
			<embed src='<%=basePath %>js/video/StrobeMediaPlayback.swf' width='640' height='377' id='StrobeMediaPlayback' quality='high' bgcolor='#000000' name='StrobeMediaPlayback' allowfullscreen='true' pluginspage='http://www.adobe.com/go/getflashplayer' flashvars='&src=rtmp://61.160.70.100:10100/app/live?token=4c4d7c3e126411e685fbc81f66d85ed3fd2b7053f08a29b25383ac77a3d79c7c&autoHideControlBar=true&streamType=live&autoPlay=true&verbose=true' type='application/x-shockwave-flash'>
			</embed>
		</object>
	</div>
	</body>
</html>

