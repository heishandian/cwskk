<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="com.yaoli.common.CustomPropertyConfigurer"%>
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
<!-- <link rel="stylesheet" type="text/css" href="css/table_css.css" /> -->
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/common_main.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main_main.css" />
<link rel="stylesheet" href="<%=basePath %>css/jqueryeasyresponsivetabs/easy-responsive-tabs.css" type="text/css"/>

<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jqueryeasyresponsivetabs/easyResponsiveTabs.js"></script>
<%-- <script type="text/javascript" src="<%=basePath%>js/monitor/sewagemonitor.js"></script> --%>

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"/>
<!-- <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script> -->
<%-- <script type="text/javascript" src="<%=basePath%>js/jquery-ui.min.js"></script> --%>
<script type="text/javascript" src="<%=basePath%>js/monitor/t.js"></script>
<script type="text/javascript" src="<%=basePath%>js/monitor/ph.js"></script>
<script type="text/javascript" src="<%=basePath%>js/monitor/orp.js"></script>
<script type="text/javascript" src="<%=basePath%>js/monitor/do.js"></script>
<script type="text/javascript" src="<%=basePath%>js/monitor/flow.js"></script>

<script type="text/javascript" src="<%=basePath%>js/monitor/cod.js"></script>
<script type="text/javascript" src="<%=basePath%>js/monitor/an.js"></script>
<script type="text/javascript" src="<%=basePath%>js/monitor/ftu.js"></script>
<script type="text/javascript" src="<%=basePath%>js/monitor/tp.js"></script>
<script type="text/javascript" src="<%=basePath%>js/monitor/rh.js"></script>
<script type="text/javascript" src="<%=basePath%>js/monitor/ap.js"></script>

<script type="text/javascript" src="<%=basePath %>js/timeformat/timeformattool.js"></script>

<script type="text/javascript" src="<%=basePath%>js/spin/spin.js"></script>
<script type="text/javascript" src="<%=basePath%>js/spin/spinconf.js"></script>
<!-- 以下用于视频 -->

<script type="text/javascript" src="<%=basePath %>js/newVideo/conf.js"></script>
<script type="text/javascript" src="<%=basePath %>js/newVideo/lib/plugin.iface.js"></script>
<script type="text/javascript" src="<%=basePath %>js/newVideo/lib/plugin.layer.js"></script>


<script type="text/javascript" src="<%=basePath %>js/newVideo/json2.js"></script>
<script type="text/javascript" src="<%=basePath %>js/newVideo/playvedio.js"></script>

<body>
<%
	/*String systemno = map.get("systemno");*/
%>

<input type="hidden" id="systemno" value="<%--<%out.print(systemno);%>--%>">
<input type="hidden" id="vedioname" value="">
<input type="hidden" id="vediopuid" value="">
<div class="result-wrap">
	<div class="config-items">
		<!-- 需求65-->
		<div id="picBackground" class="result-content" style="background-image:url('<%=basePath %>images/map.jpg');width:100%;height:404px">
			<table class="search-tab" width="100%" >
				<tbody>
				<tr>
					<td width="20%">
						<select style="width:200px" name="searchlist_areaid" id="searchlist_areaid">
							<option value="-1">请选择区县</option>
							<c:forEach items="${areaList}" var="item">
								<option value="${item.id}">${item.name}</option>
							</c:forEach>
						</select>
					</td>
					<td width="20%">
						<select style="width:200px" name="searchlist_sewageid" id="searchlist_sewageid">
							<option value="-1">请选择水污水站</option>
						</select>
						<input type="hidden" name="sewageid" id="sewageid"  value="${sewageid}">
						<input type="hidden" name="areaid" id="areaid" value="${areaid}">
					</td>
					<td width="30%">运行状态：<label id="runStateM"></label></td>
					<td width="30%">最后更新时间:<label id="lastUpdatetime"></label></td>


				</tr>
				<tr>
					<td width="100%" height="30px" colspan="4"><h6  style="text-align:center"><p id="shortTitle"></p>微动力污水处理设施工艺流程图</h6></td>
				</tr>
				</tbody>
			</table>
			<div style="float:left;width:50%">
				<table class="search-tab1" style="margin:auto;background-color: #2ECCFA;text-align: center">
					<tbody >
					<tr>
						<td width="120px" >日处理水量</td>
						<td width="80px" ><label id="dayWater"></label></td>
						<td width="80px">m3</td>
					</tr>
					<tr>
						<td width="">日消减COD量</td>
						<td width=""><label id="reducecod"></label></td>
						<td width="">g</td>
					</tr>
					<tr>
						<td width="">日消减NH3-N量</td>
						<td width=""><label id="reducenh3n"></label></td>
						<td width="">g</td>
					</tr>
					<tr>
						<td width="">日消减总P量</td>
						<td width=""><label id="reducep"></label></td>
						<td width="">g</td>
					</tr>
					</tbody>
				</table>
			</div>

			<div style="float:left;width:50%">
				<table class="search-tab1" style="margin:auto;background-color: #2ECCFA;text-align: center">
					<tbody>
					<tr>
						<td width="120px">T</td>
						<td width="80px"><label id="detection1"></label></td>
						<td width="80px">&#8451;</td>
					</tr>
					<tr>
						<td width="">PH</td>
						<td width=""><label id="detection2"></label></td>
						<td width=""></td>
					</tr>

					<tr>
						<td width="">ORP</td>
						<td width=""><label id="detection3"></label></td>
						<td width="">mV</td>
					</tr>
					<tr>
						<td width="">DO</td>
						<td width=""><label id="detection5"></label></td>
						<td width="">mg/L</td>
					</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<div id="parentHorizontalTab">
	<ul class="resp-tabs-list hor_1">
		<li><a href="#tabs-1">T</a></li>
		<li><a href="#tabs-2">PH</a></li>
		<li><a href="#tabs-3">ORP</a></li>
		<li><a href="#tabs-5">DO</a></li>


		<li><a href="#tabs-6">流量</a></li>

		<li><a href="#tabs-0">视频监控</a></li>

	</ul>


	<div class="resp-tabs-container hor_1">
		<div id="tabs-1">
			<div id="chart1" style="height:300px;width:1000px">
			</div>
		</div>
		<div id="tabs-2">
			<div id="chart2" style="height:300px;width:1000px">
			</div>
		</div>
		<div id="tabs-3">
			<div id="chart3" style="height:300px;width:1000px">
			</div>
		</div>
		<div id="tabs-5">
			<div id="chart5" style="height:300px;width:1000px">
			</div>
		</div>





		<div id="tabs-6">
			<div id="chart6" style="height:300px;width:1000px">
			</div>
		</div>

		<div id="tabs-0">
			<div id="playwindow">
				<%--以下是视频代码--%>
				<%--		<table>
                                <tr>
                                    <td>
                                        <div id="windowbox" style="border:1px grey solid;width:500px;height:309px;margin-left:10px;"></div>
                                        <div id="windowtitle" style="border-bottom:1px grey solid;border-left:1px grey solid;border-right:1px grey solid;width:500;line-height:20px;height:20px;margin-left:10px;"></div>
                                    </td>
                                    <td valign="top" align="center" style="line-height: 120%;">
                                        <input id="connect_btn" type="button" onclick="connect()" value="连接平台">
                                        <input id="disconnect_btn" type="button" onclick="disconnect()" style="display:none;" value="断开平台"><br /><br />
                                        <span id="playtoolbar" style="display:none;">
                                            <input id="close_btn" type="button" onclick="stop()" value="关闭视频"><br /><br />
                                            <input id="palyaudio_btn" type="button" onclick="playaudio()" value="播放音频"><br /><br />

                                            <input id="stopzoom" type="button"  onclick="ptzcontrol('stopzoom')" value="停止缩放"><br /><br />
                                            <input id="magnify_btn" type="button" data-option="true" onclick="ptzcontrol('zoomin')" value="放大"><input id="shrink_btn" type="button" onclick="ptzcontrol('zoomout')" value="缩小"><br /><br />

                                            <input id="stopmove" type="button"  onclick="stopmove()" value="停止转动"><br /><br />
                                            <input id="turnup_btn" type="button" data-option="true" onclick="ptzcontrol('turnup')" value="上">&nbsp;&nbsp;<input id="turndown_btn" type="button" onclick="ptzcontrol('turndown')" value="下"><br /><br />
                                            <input id="turnleft_btn" type="button" data-option="true" onclick="ptzcontrol('turnleft')" value="左">&nbsp;&nbsp;<input id="turnright_btn" type="button" onclick="ptzcontrol('turnright')" value="右"><br /><br />

                                        </span>
                                    </td>
                                </tr>
                            </table>--%>
				<div id="time">

				</div>
				<object width='640' height='377' id='StrobeMediaPlayback' name='StrobeMediaPlayback' type='application/x-shockwave-flash' classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000' >
					<param name='movie' value='<%=basePath %>js/video/StrobeMediaPlayback.swf' />
					<param name='quality' value='high' /> <param name='bgcolor' value='#000000' />
					<param name='allowfullscreen' value='true' />
					<!-- <param id="yaoli1" name='flashvars' value=""/> -->
					<embed id="videourl" src='<%=basePath %>js/video/StrobeMediaPlayback.swf' width='640' height='377' id='StrobeMediaPlayback' quality='high' bgcolor='#000000' name='StrobeMediaPlayback' allowfullscreen='true' pluginspage='http://www.adobe.com/go/getflashplayer' flashvars='' type='application/x-shockwave-flash'>
					</embed>
				</object>
			</div>
		</div>

	</div>


</div>
<div id = "waiting"></div>
<script src="<%=basePath %>js/echarts/echarts.js"></script>
<script type="text/javascript">
	//横轴坐标
	window.xtokes = [];
	//温度
	window.mychart1;
	//ph
	window.mychart2;
	//orp
	window.mychart3;
	//do
	window.mychart5;


	//cod
	window.mychart10;

	//氨氮
	window.mychart11;


	window.mychart12;

	//总磷
	window.mychart13;

	window.mychart14;

	//气压，只用于无锡
	window.mychart99;


	$(document).ready(function(){
		$("#parentHorizontalTab").easyResponsiveTabs({
			type: 'default', //Types: default, vertical, accordion
			width: 'auto', //auto or any width like 600px
			fit: true, // 100% fit in a container
			tabidentify: 'hor_1'/* , // The tab groups identifier
			 activate: function(event) { // Callback function if tab is switched
			 var $tab = $(this);
			 var $info = $('#nested-tabInfo');
			 var $name = $('span', $info);
			 $name.text($tab.text());
			 $info.show();
			 } */
		});

		//$("#yaoli1").attr("value","&src=rtmp://61.160.70.100:10100/app/live?token=4c4d7c3e126411e685fbc81f66d85ed3fd2b7053f08a29b25383ac77a3d79c7c&autoHideControlBar=true&streamType=live&autoPlay=true&verbose=true");
		//这个最重要
		//$("#videourl").attr("flashvars","&src=rtmp://61.160.70.100:10100/app/live?token=4c4d7c3e126411e685fbc81f66d85ed3fd2b7053f08a29b25383ac77a3d79c7c&autoHideControlBar=true&streamType=live&autoPlay=true&verbose=true");
		//loading();
		$("#searchlist_areaid").change(function(){
			getSewageByareaId();
		});

		$("#searchlist_sewageid").change(function(){
			getSewageRunInfoBySewageId();
			//ajaxGet5Info();
		});
		initxtoken();
		setLabels();
		chart1();
		chart2();
		chart3();
		chart5();

		chart6();


	});


	//获取运行信息
	function getSewageRunInfoBySewageId(){
		var areaId = $("#searchlist_areaid").val();
		var sewageId = $("#searchlist_sewageid").val();

		if(areaId == -1 || sewageId == -1){
			return;
		}
		var postUrl = getContextPath() + "/monitor/ajaxGetSewageRunInfoSewageId.do";
		var postdata = {"sewageid":sewageId};

		$.ajax({
			type:"POST",
			url:postUrl,
			dataType:"json",
			contentType:"application/json;charset=utf-8",
			async:true,
			data:JSON.stringify(postdata),
			success:function(data){
				var returndata = data;
				$("label").each(function(){
					//
					var idname = $(this).attr("id");
					if(idname == 'lastUpdatetime'){
						var lastUpdatetimeValue = eval("returndata."+idname);
						if(typeof lastUpdatetimeValue === 'undefined'){
							$("#"+idname).html(eval("returndata.runStateM"));
						}else{
							$("#"+idname).html(new Date(eval("returndata."+idname)).format("yyyy-MM-dd"));
						}
					}else{
						$("#"+idname).html(eval("returndata."+idname));
					}

				});
				//$("#videourl").attr("flashvars","&src=""&autoHideControlBar=true&streamType=live&autoPlay=true&verbose=true");
				$("#videourl").attr("flashvars","&src="+returndata.videourl+"&autoHideControlBar=true&streamType=live&autoPlay=true&verbose=true");
				$("#vedioname").val(returndata.videourl);
				$("#vediopuid").val(returndata.videopuid);


//			lists.add(new ControlMethod(1,"AOF工艺"));
//			lists.add(new ControlMethod(2,"MBR膜工艺"));
//			lists.add(new ControlMethod(3,"SBR膜工艺"));
//			lists.add(new ControlMethod(4,"滴滤池"));
//			lists.add(new ControlMethod(5,"生物转盘"));
//			lists.add(new ControlMethod(6,"潜流湿地"));
//			lists.add(new ControlMethod(7,"其他"));

				var controlMethod = returndata.controlmethod;
				var picName = "";
				if(controlMethod == 2){
					picName = "2";
				}else if(controlMethod == 3){
					picName = "3";
				}else if(controlMethod == 4){
					picName = "4";
				}else if(controlMethod == 5) {
					picName = "5";
				}
//			else if(controlMethod == 6){
//				picName = "6";
//			}
				else{
					picName = "1";
				}
				$("#picBackground").css("background-image", "url(<%=basePath %>images/sewageMonitorBG/"+picName+".jpg)");
			}

		});

		getTrueCurveInfo();

	}

	//获取站点信息列表
	function getSewageByareaId(){
		var areaId = $("#searchlist_areaid").val();
		var postUrl = getContextPath()+"/monitor/ajaxgetsewagebyareaid.do";
		var data = {"areaid":areaId};

		$("#searchlist_sewageid").empty();

		$.ajax({
			type:"POST",
			url:postUrl,
			dataType:"json",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(data),
			async:false,
			success:function(data){
				$("#searchlist_sewageid").append("<option value='-1'>请选择水污水站</option>");
				$.each(data,function(key,item){
					var key = item.sewageid;
					var value= item.name;
					$("#searchlist_sewageid").append("<option value='"+key+"'>"+value+"</option>");
				});
			}
		});
	}

	//随机产生假的曲线图，目前只用于无锡站点
	function getFakeCurveInfo(){
		if($("#searchlist_sewageid").val() == -1){
			return;
		}


		//检查是否断电断线
		var testSewageid = $("#searchlist_sewageid").val();
		var testPostUrl = getContextPath()+"/monitor/getWithoutElectricBySewageid.do";
		var testdata = {"sewageid":testSewageid};
		$.ajax({
			type:"POST",
			url:testPostUrl,
			dataType:"json",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(testdata),
			async:false,
			success:function(data){
				if(data.key != "pass"){
					return;//如果断电断线，就没有数据
				}else {
					loading();
					//var temp = [];
					var sewageid = $("#searchlist_sewageid").val();
					if(sewageid == -1){
						//等待消失
						offloading();
						return;
					}

					var now = new Date();
					var len = now.getHours()+1;

					/*数据造假*/
					//温度
					var preT = null;
					//ph
					var prePH = null;
					//orp
					var preORP = null;
					//do
					var preDO = null;

					/*以下定义的三个变量 用于无锡系统中，站点监控模块，相关数值 的随机生成要求：为前一个数值的波动的10% */
					var preCod = null;
					var preAn = null;
					var preTp = null;

					//气压
					var preAp = null;

					//数据库中 00:00到00.59点表示1点的平均数据，一次类推
					for(var i = 1;i<= len;i++) {

						var de1 = (getRandomValueFor_Cod_An_TP(preT, 22, 20)).toFixed(2);
						var de2 = (getRandomValueFor_Cod_An_TP(prePH, 10, 6)).toFixed(2);
						var de3 = (getRandomValueFor_Cod_An_TP(preORP, 700, -700)).toFixed(2);
						var de5 = (getRandomValueFor_Cod_An_TP(preDO, 15, 2)).toFixed(2);

						//cod
						var de10 = (getRandomValueFor_Cod_An_TP(preCod, 50, 30)).toFixed(2);

						//氨氮
						var de11 = (getRandomValueFor_Cod_An_TP(preAn, 8, 3)).toFixed(2);

						//总磷
						var de13 = (getRandomValueFor_Cod_An_TP(preTp, 1, 0.4)).toFixed(2);

						//气压
						var de99 = (getRandomValueFor_Cod_An_TP(preAp, 0.2, 0.25)).toFixed(2);

						preCod = de10;
						preAn = de11;
						preTp = de13;
						preAp = de99;


						mychart1.addData([
							[
								0,
								de1,
								false,
								false
							]
						]);
						mychart2.addData([
							[
								0,
								de2,
								false,
								false
							]
						]);
						mychart3.addData([
							[
								0,
								de3,
								false,
								false
							]
						]);
						mychart5.addData([
							[
								0,
								de5,
								false,
								false
							]
						]);

						mychart10.addData([
							[
								0,
								de10,
								false,
								false
							]
						]);
						mychart11.addData([
							[
								0,
								de11,
								false,
								false
							]
						]);
						mychart13.addData([
							[
								0,
								de13,
								false,
								false
							]
						]);
						mychart99.addData([
							[
								0,
								de99,
								false,
								false
							]
						]);

					}

					//等待消失
					offloading();
				}
			}
		});

	}


	//这个方法是获取真实曲线数据
	function getTrueCurveInfo(){
		if($("#searchlist_sewageid").val() == -1){
			return;
		}
		loading();
		//var temp = [];
		var sewageid = $("#searchlist_sewageid").val();
		if(sewageid == -1){
			return;
		}
		var postUrl = getContextPath()+"/monitor/ajaxget5info.do";
		var data = {"sewageid":sewageid};

		$.ajax({
			type:"POST",
			url:postUrl,
			dataType:"json",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(data),
			async:true,
			success:function(data){
				var now = new Date();
				var len = now.getHours()+1;
				var flag = false;

				/*以下定义的三个变量 用于无锡系统中，站点监控模块，相关数值 的随机生成要求：为前一个数值的波动的10% */
				var preCod = null;
				var preAn = null;
				var preTp = null;

				//气压
				var preAp = null;

				//数据库中 00:00到00.59点表示1点的平均数据，一次类推
				for(var i = 1;i<= len;i++){
					flag = false;
					for(var j = 0; j < data.length; j++){
						if(i == (data[j].hourname + 1)){ //时间是否相等
							var hourname = data[j].hourname;
							var hourcount = data[j].hourcount;
							var de1 = (data[j].detection1/hourcount).toFixed(2);
							var de2 = (data[j].detection2/hourcount).toFixed(2);
							var de3 = (data[j].detection3/hourcount).toFixed(2);
							var de5 = (data[j].detection5/hourcount).toFixed(2);



							mychart1.addData([
								[
									0,
									de1,
									false,
									false
								]
							]);
							mychart2.addData([
								[
									0,
									de2,
									false,
									false
								]
							]);
							mychart3.addData([
								[
									0,
									de3,
									false,
									false
								]
							]);
							mychart5.addData([
								[
									0,
									de5,
									false,
									false
								]
							]);



							//这里要注意了，因为表示流量的是有detection8和detection6
							//如果detection8的值是-999，那么这个流量的实际值就是 detection6
							//如果detection8的值大于等于0，那么这个流量的实际值就是detection8

							var de6 = 0;
							if(data[j].detection8 < 0){
								de6 = (data[j].detection6/hourcount).toFixed(2);
							}else{
								de6 = (data[j].detection8/hourcount).toFixed(2);
							}

							mychart6.addData([
								[
									0,
									de6,
									false,
									false
								]
							]);


							flag = true;
							break;
						}
					}
					if(flag == false){
						mychart1.addData([
							[
								0,
								'-',
								false,
								false
							]
						]);
						mychart2.addData([
							[
								0,
								'-',
								false,
								false
							]
						]);
						mychart3.addData([
							[
								0,
								'-',
								false,
								false
							]
						]);
						mychart5.addData([
							[
								0,
								'-',
								false,
								false
							]
						]);

						mychart6.addData([
							[
								0,
								'-',
								false,
								false
							]
						]);
					}
				}
				//等待消失
				offloading();
			}
		});

		if($("#searchlist_sewageid").val() == -1){
			return;
		}
		//loading();
		//var temp = [];
		var sewageid = $("#searchlist_sewageid").val();
		if(sewageid == -1){
			return;
		}

		var postUrl = getContextPath()+"/monitor/getEveryMonthWater.do";
		var data = {"sewageid":sewageid};

		$.ajax({
			type:"POST",
			url:postUrl,
			dataType:"json",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify(data),
			async:true,
			success:function(data){
				data = data.data;
				var len = 31;
				var flag = false;

				//水量
				var flowTotal = 0;

				for(var i = 1;i<= len;i++){
					flag = false;

					for(var j = 0; j < data.length; j++){

						var day = parseInt(new Date(data[j].testingtime).format("dd"));

						if(i == day){ //时间是否相等
							var water = parseFloat((data[j].water).toFixed(2));

							flowTotal = flowTotal + water;

							mychart6.addData([
								[
									0,
									flowTotal,
									false,
									false
								]
							]);

							//}

							flag = true;
							break;
						}
					}
					if(flag == false){
						//if($("#systemno").val() == 1){//江都系统
						mychart6.addData([
							[
								0,
								flowTotal,
								false,
								false
							]
						]);
					}
				}
				//等待消失
				//offloading();
			}
		});

	}


	//将所有label标签全部设置成未选择
	function setLabels(){
		$("label").html("0");
		$("label[id='runStateM']").html("未选择");
		$("label[id='lastUpdatetime']").html("未选择");
	}

	function initxtoken(){
		var now = new Date();
		var res = [];
		var len = now.getHours()+1;
		while (len > 0) {
			var hours = now.getHours();       //获取当前小时数(0-23)
			res.unshift(hours);
			now.setHours(now.getHours()-1);
			len = len - 1 ;
		}
		return res
	}
	function initxdata(){
		var arr = [];
		var now = new Date();
		var len = now.getHours()+1;
		for(var i = 0;i<len ; i++){
			arr.push("-");
		}
		return arr;
	}

	function initxdata_Jiangdu_flow(){
		var arr = [];
		var now = new Date();
		var len = 31;
		for(var i = 1;i<=len ; i++){
			arr.push("-");
		}
		return arr;
	}
	function initxtoken_Jiangdu_flow(){
		var now = new Date();
		var res = [];
		var len = 31;
		while (len > 0) {
			res.unshift(len);
			len = len - 1 ;
		}
		return res
	}

	function getContextPath() {
		var pathName = document.location.pathname;
		var index = pathName.substr(1).indexOf("/");
		var result = pathName.substr(0,index+1);
		return result;
	}

	//生成随机值,upValue上限值，downValue下限值。
	function getRandomValueFor_Cod_An_TP(preValue,upValue,downValue){
		if(preValue == null){
			var returnValue = Math.random() * (upValue - downValue) + downValue;
			return returnValue;
		}else {
			preValue = parseFloat(preValue);
			var baifen = preValue / 10;
			var diff = baifen * Math.random();

			if(preValue+diff >= upValue){
				return (preValue - diff);
			}else if(preValue+diff <= downValue){
				return (preValue + diff);
			}else{
				var randomPostive = Math.random() > 0.5 ? 1:-1;
				return preValue + randomPostive*diff;
			}
		}
	}
</script>
</body>
<script>
	window.onload = function(){
		if($("#areaid").val()!=""){
			var temp_value = $("#areaid").val();
			$("select").children("option").each(function(){
				var value = $(this).val();
				if(temp_value == value){
					$(this).attr("selected","selected");
				}
			});

			$("#searchlist_areaid").trigger("change");

			if($("#sewageid").val()!=""){
				var temp_value = $("#sewageid").val();
				$("select").children("option").each(function(){
					var value = $(this).val();
					if(temp_value == value){
						$(this).attr("selected","selected");
					}
				});
			}
			getSewageRunInfoBySewageId();
		}
		offloading();
	}
</script>
</html>