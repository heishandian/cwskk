/*自定义校验*/

$.extend($.fn.validatebox.defaults.rules, {
	equals: {
		validator: function(value,param){
			return value == $(param[0]).val();
		},
		message: '两次输入的密码不相等不相等'
    }
});

$.extend($.fn.validatebox.defaults.rules, {     
    minLength: {     
        validator: function(value, param){   //value 为需要校验的输入框的值 , param为使用此规则时存入的参数  
            return value.length >= param[0];     
        },     
        message: '请输入最小{0}位字符.'    
    }     
});   
  
$.extend($.fn.validatebox.defaults.rules, {     
    maxLength: {     
        validator: function(value, param){     
            return param[0] >= value.length;     
        },     
        message: '请输入最大{0}位字符.'    
    }     
});   
  
$.extend($.fn.validatebox.defaults.rules, {     
    length: {     
        validator: function(value, param){     
            return value.length >= param[0] && param[1] >= value.length;     
        },     
        message: '请输入{0}-{1}位字符.'    
    }     
});   

// extend the 'equals' rule     
$.extend($.fn.validatebox.defaults.rules, {     
	   equaltopwd : {     
	        validator: function(value,param){
	            return value == $("#loginuserpwd").val(); 
	        },     
	        message: '两次 密码不相等.'    
	    }     
	}); 
  
$.extend($.fn.validatebox.defaults.rules, {     
    web : {     
        validator: function(value){     
            return /^(http[s]{0,1}|ftp):\/\//i.test($.trim(value));     
        },     
        message: '网址格式错误.'    
    }     
});   
              
$.extend($.fn.validatebox.defaults.rules, {     
   mobile : {     
        validator: function(value){
            return /^1[0-9]{10}$/i.test(value.trim());     
        },     
        message: '手机号码格式错误.'    
    }     
});

$.extend($.fn.validatebox.defaults.rules, {     
	   loginnamereg : {     
	        validator: function(value){
	            return /^[A-Za-z]{1,11}$/i.test(value.trim());     
	        },     
	        message: '登录名必须是字母.'    
	    }     
	});

function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}

$.extend($.fn.validatebox.defaults.rules, {     
	   checkexist : {     
	        validator: function(value){
	        	var postUrl = getContextPath()+"/users/ajaxcheckexist.do";
	        	var data = {loginusername:value};
	        	var postdata = JSON.stringify(data);
	        	var result;
	        	$.ajax({
	        		type:"POST",
	        		url:postUrl,
	        		dataType:"json",
	        		contentType:"application/json;charset=utf-8",
	        		data:postdata,
	        		async:false,
	        		success:function(data){
	        			result= data.key;   
	        		}
	        	});
	        	return result == "pass"	;
	        },     
	        message: '登录名已经存在.'    
	    }     
	});

$.extend($.fn.validatebox.defaults.rules, {     
	   checkpwdright : {     
	        validator: function(value){
	        	var postUrl = getContextPath()+"/users/ajaxcheckpwdright.do";
	        	var data = {loginuserpwd:$("#loginpwd").val()};
	        	var postdata = JSON.stringify(data);
	        	var result;
	        	$.ajax({
	        		type:"POST",
	        		url:postUrl,
	        		dataType:"json",
	        		contentType:"application/json;charset=utf-8",
	        		data:postdata,
	        		async:false,
	        		success:function(data){
	        			result= data.key;   
	        		}
	        	});
	        	return result == "pass"	;
	        },     
	        message: '输入的密码不正确.'    
	    }     
	});

/*$.extend($.fn.validatebox.defaults.rules, {     
	   allowupdateloginname : {     
	        validator: function(value){
	        	var postUrl = getContextPath()+"/users/ajaxallowupdateloginname.do";
	        	var data = {loginusername:value};
	        	var postdata = JSON.stringify(data);
	        	var result;
	        	$.ajax({
	        		type:"POST",
	        		url:postUrl,
	        		dataType:"json",
	        		contentType:"application/json;charset=utf-8",
	        		data:postdata,
	        		async:false,
	        		success:function(data){
	        			result= data.key;   
	        		}
	        		
	        	});
	        	return result == "pass"	;
	        },     
	        message: '登录名已经存在.'    
	    }     
	});*/

$.extend($.fn.validatebox.defaults.rules, {     
    mobilelength: {     
        validator: function(value, param){     
            return value.length == param[0];     
        },     
        message: '请输入11位数字.'    
    }     
});   
 
$.extend($.fn.validatebox.defaults.rules, {     
   date : {     
        validator: function(value){     
            return /^[0-9]{4}[-][0-9]{2}[-][0-9]{2}$/i.test($.trim(value));     
        },     
        message: '曰期格式错误,如2012-09-11.'    
    }     
});   
 
$.extend($.fn.validatebox.defaults.rules, {     
   email : {     
        validator: function(value){     
            return /^[a-zA-Z0-9_+.-]+\@([a-zA-Z0-9-]+\.)+[a-zA-Z0-9]{2,4}$/i.test($.trim(value));     
        },     
        message: '电子邮箱格式错误.'    
    }     
});   
 
$.extend($.fn.validatebox.defaults.rules, {     
   captcha : {     
        validator: function(value){     
            var data0 = false;  
            $.ajax({  
                type: "POST",async:false,  
                url:contextPath + "/json/valSimulation.action",  
                dataType:"json",  
                data:{"simulation":value},  
                async:false,  
                success: function(data){  
                    data0=data;  
                }  
            });  
              
           return data0;  
//          return /^[a-zA-Z0-9]{4}$/i.test($.trim(value));  
        },     
        message: '验证码错误.'    
    }     
});   
 
$.extend($.fn.validatebox.defaults.rules, {     
   txtName : {     
        validator: function(value,param){     
            var data0 = false;  
            if(value.length >= param[0] && param[1] >= value.length)  
            {  
                $.ajax({  
                    type: "POST",async:false,  
                    url:contextPath + "/json/valName.action",  
                    dataType:"json",  
                    data:{"txtName":value},  
                    async:false,  
                    success: function(data){  
                        data0=!data;  
                    }  
                });  
            }else{  
                param[2] = "请输入"+param[0]+"-"+param[1]+"位字符.";  
                return false;  
            }  
              
            param[2] = "用户名称已存在.";  
           return data0;  
        },     
        message: "{2}"   
    }     
});   


$.extend($.fn.validatebox.defaults.rules, {     
    mustnumber: {     
        validator: function(value){     
        	return /^[-\+]?\d+(\.\d+)?$/i.test(value.trim());       
        },     
        message: '必须是数字.'    
    }     
});

$.extend($.fn.validatebox.defaults.rules, {     
    mustintegernumber: {     
        validator: function(value){     
        	return /^(\d+)?$/i.test(value.trim());       
        },     
        message: '必须整数'    
    }     
});