
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
<head>
    <title>Demo-playvideo</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
	
	<link rel="stylesheet" href="<%=basePath %>css/newVideo/mini-easyui/themes/icon.css" type="text/css"></link>
	<link rel="stylesheet" href="<%=basePath %>css/newVideo/mini-easyui/themes/gray/easyui.css" type="text/css"></link>
	<link rel="stylesheet" href="<%=basePath %>css/newVideo/demo.css" type="text/css"></link>
	
	<script type="text/javascript" src="<%=basePath %>js/newVideo/conf.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/newVideo/lib/plugin.iface.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/newVideo/lib/plugin.layer.js"></script>
	
	
	<script type="text/javascript" src="<%=basePath %>js/newVideo/json2.js"></script>
	
	<script type="text/javascript" src="<%=basePath %>css/newVideo/mini-easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>css/newVideo/mini-easyui/jquery.easyui.min.js"></script>
	
</head>
<body>
	<table>
		<tr>
			<td valign="top" >
				<div id="res_tree" class="easyui-tree" style="width:180px;height:310px;border:1px solid #bebebe;overflow:auto;"></div>
			</td>
			<td>
				<div id="windowbox" style="border:1px grey solid;width:400px;height:288px;margin-left:10px;"></div>
				<div id="windowtitle" style="border-bottom:1px grey solid;border-left:1px grey solid;border-right:1px grey solid;width:400px;line-height:20px;height:20px;margin-left:10px;"></div>
			</td>

		</tr>
		<tr>
			<td valign="top" align="center" style="line-height: 120%;">
				<input class="btn" name="seachpoint" id="seachpoint" value="获取" type="submit" style="width:20%;height:30px" />
				<input id="connect_btn" type="button" onclick="connect()" value="连接平台">
				<input id="disconnect_btn" type="button" onclick="disconnect()" style="display:none;" value="断开平台"><br /><br />
				<span id="playtoolbar" style="display:none;">
					<input id="close_btn" type="button" onclick="stop()" value="关闭视频"><br /><br />
					<input id="palyaudio_btn" type="button" onclick="playaudio()" value="播放音频"><br /><br />
					<input id="record_btn" type="button" onclick="record()" value="本地录像"><br /><br />
					<input id="sn<input_btn" type="button"  onclick="snap()" value="本地抓拍"><br /><br />
					<input id="turnup_btn" type="button"  onclick="ptzcontrol('turnup')" value="上">&nbsp;&nbsp;<input id="turndown_btn"  data-options="toggle:true" onclick="ptzcontrol('turndown')" value="下"><br /><br />
					<input id="turnleft_btn" type="button"  onclick="ptzcontrol('turnleft')" value="左">&nbsp;&nbsp;<input id="turnright_btn"  data-options="toggle:true" onclick="ptzcontrol('turnright')" value="左"><br />
				</span>
			</td>
			<td valign="top">
<!-- 				<div style="border:1px solid #bebebe;padding-top:2px;height:310px;width:550px;overflow:auto;">
					<ol style="text-indent:2em;text-align:left;" id="msg_bar">
					</ol>
				</div> -->
			</td>
		
		</tr>
	</table>
</body>
</html>
<script>
var connectId = null;

// 初始化插件对象，必须初始化成功后才可以调用所有接口
function load(){	
		//初始化插件
		try{
			var rv = P_LY.Init(new P_LY.Struct.InitParamStruct(
				true, 
				function(msg)
				{
					log(JSON.stringify(msg));
				}
			));
			if(rv.rv != P_Error.SUCCESS)
			{
				return false;
			}
			// 加载默认的连接参数			
	        return true;
		}
		catch(e){
			$("#msg_bar").html(e.name + ","+ e.message);
			return false;
		}
}

// 释放插件对象，网页退出必须释放插件，否则会出现浏览器异常的错误
function unload(){
	P_LY.UnLoad();
}
	
function connect(){
	
	//创建连接,并记录在P_LY.Connections中
	var ip = _cf.connParams.ip;
	var port = _cf.connParams.port;
	var epId = _cf.connParams.epId;
	var uid = _cf.connParams.uid;
	var pwd = _cf.connParams.pwd; 
	var bfix = _cf.connParams.bfix;

	// 构造连接对象
	var param = new P_IF.Struct.ConnParamStruct(ip,port,uid,epId,pwd,bfix || 0);
	log("登录参数:"+JSON.stringify(param));
			
	// 连接平台
	var conn = P_LY.Connect(param);
	
	if(conn.rv == P_Error.SUCCESS){
		connectId = conn.response;// 连接成功，返回connectId，此参数很重要，后面很多操作都需要
		log("连接成功:connectId="+connectId);
		$('#connect_btn').hide();
		$('#disconnect_btn').show();
		
		fetch_resource();
		
		// 开接收流状态事件
		P_LY.NCNotifyManager.Add(P_LY.Enum.NCObjectNotify.stream_status_notify,notify);

	}else{
		log(P_Error.Detail(conn.rv));
	}
}

function disconnect(){
	stop();
	var rv = P_LY.DisConnection(connectId);
	if(rv.rv == P_Error.SUCCESS){
		log("断开成功");		
		connectId = null;
		$('#connect_btn').show();
		$('#disconnect_btn').hide();
		$('#res_tree').tree('loadData',[]);
	}else{
		log(P_Error.Detail(rv.rv));
	}
}

function fetch_resource(){
	var offset = 0;
	var cnt = 100;// 这里demo只查询100个资源，实际应用，可以根据需求，如果返回的节点数等于要查询的数，说明可能还有资源，可以继续获取	
	var rv = P_LY. ForkResource(connectId,P_LY.Enum.ForkResourceLevel.nppForkPUInfo, offset,cnt, "","");
	//console.log(rv);
	var nodes = new Array();
	
	$('#res_tree').tree({
		onExpand:function(node){
			
			var childs = $(this).tree('getChildren', node.target);
			
			if (childs.length == 1) {
			    var l = node.children[0];
			    if (l.text == "正在查询资源……") {
					var rv = P_LY. ForkResource(connectId,P_LY.Enum.ForkResourceLevel.nppForkPUResourceInfo, 0,120, "",{puid:node.attributes.pu.puid,_HANDLE:node.id});
					if(rv.rv == P_Error.SUCCESS){
						var nodes = new Array();
						for(var j = 0;j < rv.response.length;j++){
							var pures = rv.response[j];
							
							// 这里只列出视频输入资源
							if(pures.type == P_LY.Enum.PuResourceType.VideoIn){
								nodes.push({id:pures._HANDLE,text:pures.name,attributes:{pu:node.attributes.pu,self:pures}});	
							}
							
						}
						
                        if (nodes.length > 0) {
                            // 删除那个load节点
                            for (var i = 0; i < childs.length; i++) {
                                $('#res_tree').tree('remove', childs[i].target);
                            }

                            $('#res_tree').tree('append', {
                                parent: node.target,
                                data: nodes
                            });
                        }
                        
					}else{
						l.text = "没有可用的资源";
					}
			    }
			}
		},
		onCollapse:function(node){
			//console.log(node)
		},
		onDblClick:function(node){
			var attr = node.attributes;
			if(attr.pu.online != "1"){
				log(pu.name+"不在线");
				return;
			}
			play(attr.pu,attr.self);
			return true;
		}
	});
	if(rv.rv == P_Error.SUCCESS){
		for(var i =0;i <rv.response.length;i++){
			var pu = rv.response[i];
			if(pu.modelType == "ENC" || pu.modelType == "WENC"){
				nodes.push({id:pu._HANDLE,text:pu.name,attributes:{pu:pu},state:"closed",children:[{text:'正在查询资源……'}]});
			}
		}
		if(nodes.length > 0){
			$('#res_tree').tree('loadData',nodes);
			
		}
	}else{
		log("没有获取资源,rv="+rv.rv);
	}
}

function play(pu,pures){
	// 窗口对象一定放到WindowContainers，这个请开发人员注意
	if(!P_LY.WindowContainers.get('windowbox')){
		P_LY.WindowContainers.set('windowbox',new P_LY.Struct.WindowContainerStruct('windowbox', P_LY.Enum.WindowType.VIDEO));
	}
	
	if(P_LY.WindowContainers.get('windowbox')){
		var window = P_LY.WindowContainers.get('windowbox').window;
		if(window != null){
			if(window.status.playvideoing){
				stop('windowbox');
			}
		}
		
		var wndEvent = new P_LY.Struct.WindowEventStruct();
		var rv  = P_LY.CreateWindow(connectId, 'windowbox', P_LY.Enum.WindowType.VIDEO,wndEvent);
			
		if(rv.rv != P_Error.SUCCESS)
		{
			log("创建窗口失败,rv"+rv.response);
			return false;
		}
		window = rv.response;
		
		window.customParams.cameraName = pures.name;
		P_LY.WindowContainers.get('windowbox').window = window;
		P_LY.WindowContainers.get('windowbox').description = pures;
		var operator = P_LY.PlayVideo(connectId, window, pu.puid, pures.idx);
		P_LY.ResizeWindowDimension(window,"100%","100%");
		if(operator.rv != P_Error.SUCCESS)
		{
			P_LY.ResizeWindowDimension(window,0,0);
			log("播放视频失败,rv ="+operator.rv+",response="+operator.response);
			return false;
		}
		$('#playtoolbar').show();
		log("正在播放视频"+pu.name);
	}
}


function stop(){
	if(!P_LY.WindowContainers.get('windowbox')){
		return;
	}
	log("关闭视频"+P_LY.WindowContainers.get('windowbox').window.customParams.cameraName);
	P_LY.StopVideo(P_LY.WindowContainers.get('windowbox').window);
	P_LY.ResizeWindowDimension(P_LY.WindowContainers.get('windowbox').window,0,0);
	$("#windowtitle").html("无视频");
	$('#playtoolbar').hide();
}

function playaudio(){
	
	if(P_LY.WindowContainers.get('windowbox')){
		var wnd = P_LY.WindowContainers.get('windowbox').window;
		if(wnd.containerId == "windowbox"){
			if(wnd.status.playvideoing){
				var paStop = P_LY.PlayAudio(wnd);
				if(wnd.status.playaudioing){
					log("播放音频");
				}else{
					log("停止音频")
				}
			}
		}
	}
}

function record(){
	var path = "C://测试";
	var wnd = P_LY.WindowContainers.get('windowbox').window;
	if(typeof wnd != "object"){
		return false;
	}
	
	if(wnd.containerId == "windowbox"){
		if(wnd.status.playvideoing){
			var path = "测试.avi";
			var rv = P_LY.LocalRecord(wnd,"d:/",path );
			if(wnd.status.recording){
					log("开始录像,保存路径为:"+path);
			}
			else{
					log("停止录像");
			}
		}
	}
}

function snap(){
	var path = "C:/";
	var wnd = P_LY.WindowContainers.get('windowbox').window;
	if(typeof wnd != "object"){
		return false;
	}
	if(wnd.containerId == "windowbox"){
		if(wnd.status.playvideoing){
			var rv = P_LY.Snapshot(wnd,path);
			log("抓拍成功,保存路径为:"+path);
		}
	}
}

function ptzcontrol(director){
	var wnd = P_LY.WindowContainers.get('windowbox').window;
	if(typeof wnd != "object"){
		return false;
	}
	
	var mode = P_LY.Enum.PTZDirection[director];
	
	if(typeof mode == "undefined"){
		mode = P_LY.Enum.PTZDirection.stopturn;
	}
	if($('#'+director+"_btn").length > 0){
		var opts = $('#'+director+"_btn").linkbutton('options');
		if(opts.selected == true){
			mode = P_LY.Enum.PTZDirection.stopturn;
		}
	}
	if(wnd.containerId == "windowbox"){
		if(wnd.status.playvideoing){
			var puid = wnd.params.puid || "";
			var idx = wnd.params.idx || "";
			var mode = mode || "";
			var operator = P_LY.PTZ.Control(connectId, puid, idx, mode);
			if(operator.rv != P_Error.SUCCESS){
				log("操作云台失败，错误码："+operator.rv);
			}
		}                                                                                                                                                                                                                                                                                                                          
	}
}

function reset(){
	$('#conn_form').form('reset');
}

function notify(notify){
	var container = P_LY.WindowContainers.get('windowbox');
	if(container){
		if(container.window.status.playvideoing){
			//console.log(container.window,notify);
			if(notify.eventName == "stream_status_notify" &&  notify._HANDLE == container.window.params.ivStreamHandle){
				var ivName = container.window.customParams.cameraName;
				var status = "";
				
				switch(Number(notify.status)){//-1表示流已断开，0表示正在缓冲，1表示正在播放,2表示正在下载，3表示下载完成
				case -1:
					status = "流已断开"
					break;
				case 0:
					status = "正在缓冲"
					break;
				case 1:
					status = "正在播放"
					break;
				}
				var _bite = function(bite){
					return (bite / 1000).toFixed(0) + "KB/s"; 
				}
				var bitrate = notify.keyData.bit_rate;
				var bite = _bite(bitrate)
				var framerate = notify.keyData.frame_rate;
				var frame = framerate;
				var recStr = "";
				if(container.window.status.recording){
					status += ",正在录像"
				}
				
				var audStr = "";
				if(container.window.status.playaudioing){
					status += ",正在播放声音"
				}
				
				var upaudStr = "";
				if(container.window.status.calling){
					status += ",正在喊话"
				}

				var talkStr ="";
				if(container.window.status.talking){
					status += ",正在对讲"
				}
				
				var fb = "帧率："+frame+"，码率："+bite;
				$("#windowtitle").html(ivName+","+ fb +","+ status);
				//log($("#windowtitle")[0].innerHTML);
			}
		}
	}
}

function log(str){
	var html = $("#msg_bar").html()+"<li><nobr>"+str+"</nobr></li>";
	$("#msg_bar").html(html);
}

if(window.attachEvent){
	window.attachEvent(
		"onload",
		function() {
			load();  
		} 
	);
	window.attachEvent(
		"onunload",
		function(){
			unload();
		}
	);
	window.attachEvent(
		"onbeforeunload",
		function(){
			unload();
		}
	);
}else{
	window.addEventListener (
        "load",
        function() {
			load();  
        },
        false
    );
	window.addEventListener (
        "unload",
        function() {
			unload();
        },
        false
    );
	window.addEventListener (
        "beforeunload",
        function() {
			unload();
        },
        false
    );
}

</script>
