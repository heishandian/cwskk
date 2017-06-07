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
	<div class="result-wrap">
		<div class="config-items">
            <div class="result-content" style="width:100%;height:100%">
					<table class="search-tab" width="100%">

						<tr>
						  <th width="10%">原sql语句</th>
						  <td width="90%">
							  <script id="container" name="content" type="text/plain"><pre></pre></script>
							  <script type="text/javascript" src="<%=basePath %>js/UEditor/utf8-jsp/ueditor.config.js"></script>
							  <script type="text/javascript" src="<%=basePath %>js/UEditor/utf8-jsp/ueditor.all.js"></script>
							  <script type="text/javascript">

							  </script>
						  </td>
						</tr>

						<tr>
							<th width="10%"></th>
							<td width="90%">
								<input class="btn" name="seachpoint" id="seachpoint" value="获取" type="submit" style="width:20%;height:30px" />
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
</body>
</html>

<script type="text/javascript">
   var ue = UE.getEditor('container',{
	    toolbars: [
	        [
	            'fullscreen', 'source', '|','fontsize','insertcode', 
	        ]
	    ],
	    sourceEditorFirst:false,
		initialFrameWidth:800,
		initialFrameHeight:500,
		initialStyle:'p{line-height:1em; font-size: 10px; }'
	});
   function getContentTxt() {
       var arr = [];
       arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
       arr.push("编辑器的纯文本内容为：");
       //arr.push(UE.getEditor('container').getContentTxt());
       alert(arr.join("\n"));
   }
$(document).ready(function(){
	$("#seachpoint").click(function(){
		var txt = UE.getEditor('container').getContent();
		var content = JSON.stringify({'content':txt});
		$.ajax({
			type:"POST",
			url:getContextPath()+"/util/formatsql.do",
			dataType:"json",
			contentType:"application/json;charset=utf-8",
			data:content,
			success:function(data){
				if(data.key == "pass"){
					UE.getEditor('container').setContent(data.data);
				}
			}
		});
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
</script>