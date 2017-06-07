jQuery.validator.addMethod("isMobile", function(value, element) { 
  var length = value.length; 
  var mobile = /^(((1[0-9][0-9]{1})|(15[0-9]{1}))+\d{8})$/; 
  return this.optional(element) || (length == 11 && mobile.test(value)); 
}, "请正确填写您的手机号码");


jQuery.validator.addMethod("checkedSewageIdExist", function(value, element) { 
	var postdata = JSON.stringify({"sewageid":value});
	var postUrl = getContextPath()+"/monitor/checkSewageIdExist.do";
	var result = false;
	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:postdata,
		async:false,
		success:function(data){
			if(data.key == "pass"){
				result = true;
			}
		}
	}); 
	  
	  return this.optional(element) || (result == true); 
}, "该Id已经存在");

jQuery.validator.addMethod("checkedLoginNameExist", function(value, element) { 
	var postdata = JSON.stringify({"loginusername":value});
	var postUrl = getContextPath()+"/users/ajaxcheckexist.do";
	var result = false;
	$.ajax({
		type:"POST",
		url:postUrl,
		dataType:"json",
		contentType:"application/json;charset=utf-8",
		data:postdata,
		async:false,
		success:function(data){
			if(data.key == "pass"){
				result = true;
			}
		}
	}); 
	  
	  return this.optional(element) || (result == true); 
}, "登录名已经存在");


jQuery.validator.addMethod("mustEnglish", function(value, element) { 
	var result = false;
	result = /^[A-Za-z]{1,11}$/i.test(value.trim());     
	  return this.optional(element) || (result == true); 
}, "登录名必须是字母");