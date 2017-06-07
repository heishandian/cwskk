// JavaScript Document
//支持Enter键登录
		document.onkeydown = function(e){
			if($(".bac").length==0)
			{
				if(!e) e = window.event;
				if((e.keyCode || e.which) == 13){
					var obtnLogin=document.getElementById("submit_btn")
					obtnLogin.focus();
				}
			}
		}

    	$(function(){
			//提交表单
			$('#submit_btn').click(function(){
				//show_loading();
				//var myReg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; //邮件正则
//				if($('#loginusername').val() == ''){
//					show_err_msg('请填写账号');	
//					$('#loginusername').focus();
//				}else if($('#loginuserpwd').val() == ''){
//					show_err_msg('请填写密码');
//					$('#loginuserpwd').focus();
//				}else{
//					//ajax提交表单，#login_form为表单的ID。 如：$('#login_form').ajaxSubmit(function(data) { ... });
//					//show_msg('登录成功咯！  正在为您跳转...','/');	
//				}
			});
		});