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
	
	
	
	<link rel="stylesheet" href="<%=basePath %>css/jqueryvalidation/screen.css" type="text/css"></link>
	<script type="text/javascript" src="<%=basePath %>js/jqueryvalidation/jquery.validate.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jqueryvalidation/validationextend.js"></script>
	
	
	<link rel="stylesheet" type="text/css" href="<%=basePath %>css/ztree/zTreeStyle.css" />
	<script type="text/javascript" src="<%=basePath %>js/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/ztree/jquery.ztree.excheck-3.5.js"></script>
	
	<script type="text/javascript" src="<%=basePath %>js/tools/jquery.md5.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/tools/jquery.base64.js"></script>
	
	<script type="text/javascript" src="<%=basePath %>js/zeroClipboard/ZeroClipboard.min.js"></script>
	
<body>
<form id="submitform">
	<div class="result-wrap">
		<div class="config-items">
            <div class="result-content" style="width:100%;height:100%">
					<table class="search-tab" width="100%">
						<tr>
						  <th width="30%">发送地址：</th>
						  <td width="70%"><input id="postUrl" name="postUrl" type="text" style="width:60%" value="http://61.160.70.100:9580/nmc/rest/realstream?sign="/></td>
						</tr>
						<tr>
						  <th width="30%">password：</th>
						  <td width="70%"><input id="password" name="password" type="text" style="width:60%" value="admin54321"/></td>
						</tr>
						<tr>
						  <th width="30%">uid：</th>
						  <td width="70%"><input id="uid" name="uid" type="text" style="width:60%" value="admin"/></td>
						</tr>
						<tr>
						  <th width="30%">epid：</th>
						  <td width="70%"><input id="epid" name="epid" type="text" style="width:60%" value="wxsd"/></td>
						</tr>
						<tr>
						  <th width="30%">puid：</th>
						  <td width="70%"><input id="puid" name="puid" type="text" style="width:60%" value="201115200252708495"/></td>
						</tr>
						
						<tr>
							<th width="30%"></th>
							<td width="70%">
								<input class="btn" name="seachpoint" id="seachpoint" value="获取" type="submit" style="width:20%;height:30px" />
							</td>
						</tr>
						
						<tr>
						  <th width="30%">生成结果URL：</th>
						  <td width="70%"><input id="resulturl" name="resulturl" type="text" style="width:60%" value=""/>
						  				  <input class="btn" name="copy" id="copy" value="复制" type="button" style="width:8%;height:25px" data-clipboard-text="Copy Me!"/>
						  				 <!--  <button id="copy-button" data-clipboard-text="Copy Me!" title="Click to copy me.">Copy to Clipboard</button> -->
						  </td>
						</tr>
					</table>
				</div>
			</div>
		</div>
</form>
</body>
</html>

<script type="text/javascript">

$(document).ready(function(){
	//设置地区选择
	
	//61.160.70.100
	//9988
	//epid wxsd
	//用户账号 admin  admin
/* 	$("#copy").click(function(){

	});
	 */
	//var client = new ZeroClipboard( document.getElementById('copy') );
	var client = new ZeroClipboard($("#copy"));
	
	client.on( "copy", function (event) {
		var clipboard = event.clipboardData;
		var data =  $("#resulturl").val();
		event.clipboardData.setData('text/plain', data);
		alert("复制成功！");
	});
	
	
	$.validator.setDefaults({
		submitHandler: function() {
			var data = serializeForm($("#submitform"));
			delete data["seachpoint"];
			delete data["resulturl"];
			delete data["copy"];
			var postdata = JSON.stringify(data);

			
			var postUrl = getContextPath()+"/util/getVideoToken.do";
			$.ajax({
			type:"POST",
			url:postUrl,
			dataType:"json",
			contentType:"application/json;charset=utf-8",
			data:postdata,
			success:function(data){
					if(data.key == "pass"){
						$("#resulturl").val(data.data);
					}
				}
			});
			
		},

	});

	$("#submitform").validate({
		rules: {
			postUrl:{
				required:true
			},
			password: {
				required:true
			},
			uid: {
				required:true
			},
			epid: {
				required:true
			},
			puid: {
				required:true
			}
		},
		messages: {
			postUrl:{
				required:"不能为空"
			},
			password:{
				required:"不能为空"
			},
			uid: {
				required: "不能为空"
			},
			epid: {
				required: "不能为空"
			},
			puid: {
				required: "不能为空"
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

/* 	$("#test").click(function(){
	
			var postUrl = "http://61.160.70.100:9580/nmc/rest/realstream?sign=";
			var uid = "admin";
			var password = "admin";
			var epid = "wxsd";
			var puid = "201115200252708495";
			
			alert($.md5(password));
			
			var timestamp = Math.round(new Date().getTime()/1000);
			$.base64.utf8encode = true;
			var authorization = $.base64.btoa(epid+"_"+uid+"_"+timestamp);
			var sign = $.md5(epid+uid+$.md5(password).toUpperCase()+timestamp).toUpperCase();
			postUrl = postUrl + sign;
			var postdata = JSON.stringify({request:{puid:puid,idx:"0",videoformat:"rtmp",stream:"1"}});	
			
			$.ajax({
				type:"POST",
				url:postUrl,
				dataType:"jsonp",
				contentType:"application/json;charset=utf-8",
				data:postdata,

				headers: {
					"Accept":"application/json",
            		"Authorization":$.base64.btoa(epid+"_"+uid+"_"+timestamp)
        		},
				success:function(data){
					alert(data);
				}
			});
	
	}); */
</script>