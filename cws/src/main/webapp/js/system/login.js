/*=======================================*/
/*	登陆功能的js                           */
/*	@author coding云                        */
/*	2014-9-23 21:28:24                         */
/*=======================================*/
$(document).ready(function(){
	$("#submit_btn").on('click',function(){
		login();
    });
	// 按回车执行查询
	$("#loginusername").on("keypress", function(){
		var event = arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异 
		if (event.keyCode == 13) {
//			$("#loginForm").submit();
			login();
		} // 回车键的键值为13
	}); 
	$("#loginuserpwd").on("keypress", function(){
		var event = arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异 
		if (event.keyCode == 13) {
			login();
		} // 回车键的键值为13
	}); 
	$("#logincode").on("keypress", function(){
		var event = arguments.callee.caller.arguments[0]||window.event;//消除浏览器差异 
		if (event.keyCode == 13) {
			login();
		} // 回车键的键值为13
	}); 
	
	randomImg();
});

function login(){
	if($('#loginusername').val() == ''){
		show_err_msg('请填写账号');	
		$('#loginusername').focus();
	}else if($('#loginuserpwd').val() == ''){
		show_err_msg('请填写密码');
		$('#loginuserpwd').focus();
	}else if($('#logincode').val() == ''){
		show_err_msg('请填写验证码');
		$('#logincode').focus();
	}else{
		
		//ajax提交表单
		var btn = $("#submit_btn");
	    //btn.button('loading');
		//var datasent = $("#loginForm").serializeObject();
		var postdata= {"loginusername":$("input[name='loginusername']").val(),"loginuserpwd":$("input[name='loginuserpwd']").val(),"logincode":$("input[name='logincode']").val()};
		var contextPath = getContextPath();
		var postUrl = contextPath+"/system/loginCheck.do";
		$.ajax({
			type : "POST",
			url : postUrl,
			dataType : "json",
			contentType : "application/json;charset=utf-8",
			data : JSON.stringify(postdata),
			async : false,
			success : function(data) {
				if(data.key == "pass"){   //登录成功
					window.location.href=getContextPath()+"/system/index.do";
				}else if(data.key == "errorcode"){
					randomImg();   
					show_err_msg('验证码错误');
				}
				else if(data.key == "accounterror"){
					randomImg();  
					show_err_msg('账户不存在');
				}else if(data.key == "passworderror"){
					randomImg();  
					show_err_msg('密码错误');
				}
				$('#loginForm').reset();
			}
		});
	}
}

function randomImg() {
	var random = new Date().getTime();
	var contextPath = getContextPath();
	var postUrl = contextPath+"/system/getValidImage.do?type=syslogin&randomString="+random;
	$("#randomString").val(random);
	$("#authCodeImg").attr("src", postUrl);
}

function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
