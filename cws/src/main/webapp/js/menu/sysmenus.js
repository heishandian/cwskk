function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
$(document).ready(function(){
	getmenus();
});

function getmenus(){
/*				var treeMenu = [
	            	{ level:1, name:"用户管理"},
	            	{ level:2, name:"用户列表", ico:"images/icon_default.gif",link:"user_list.html"},
	            	{ level:2, name:"角色管理"},
	            	{ level:3, name:"角色列表", ico:"images/icon_default.gif",link:"role_list.html"},
	            	{ level:3, name:"用户角色", ico:"images/icon_default.gif",link:"ur_list.html"},
	            	{ level:1, name:"信息管理"},
	            	{ level:2, name:"新闻管理", ico:"images/icon_default.gif",link:"news_list.html"},
	            	{ level:2, name:"公告管理", ico:"images/icon_default.gif",link:"bulletin_list.html"},
	            	{ level:1, name:"系统邮件", ico:"images/icon_default.gif"},
	            	{ level:1, name:"网络文件", ico:"images/icon_default.gif",link:"complaint_list.html"}
	            ];
	             //建立新树
	            tree = new dTree('tree');
	            tree.config.target = "MainFrame";
	            tree.config.useCookies = false;
	            var selNum = -1;
	            var link = "";
	            //根目录
	            tree.add(0,-1,'管理中心', null, null, null, '', '');
	            var count = 0;
	            var pLevelIdArray = new Array();
	            pLevelIdArray[1] = 0;
	            var currLevel = 1;
	            for (var i=0; i<treeMenu.length; i++) {
	            	var item = treeMenu[i];
	            	var itemLevel = item.level;
	            	pLevelIdArray[itemLevel+1] = ++count;
	            	if (item.link!=null && item.link!="") {
	            		if (item.ico!=null) {
	            			tree.add(count, pLevelIdArray[itemLevel], item.name, item.link, null, null, item.ico, item.ico);
	            		} else {
	            			tree.add(count, pLevelIdArray[itemLevel], item.name, item.link);
	            		}
	            	} else {
	            		if (item.ico!=null) {
	            			tree.add(count, pLevelIdArray[itemLevel], item.name, null, null, null, item.ico, item.ico);
	            		} else {
	            			tree.add(count, pLevelIdArray[itemLevel], item.name);
	            		}
	            	}
	            	if (item.select) {
	            		selNum = count;
	            		link = item.link;
	            	}
	            }
	            document.write(tree);
	            tree.openAll();
	            if (selNum != -1) {
	            	tree.openTo(selNum,true);
	            	top.document.frames["MainFrame"].location.href=link;
	            } */
	
	var contextPath = getContextPath();
	var postUrl = contextPath+"/system/getmenus.do";
	var treeMenu;
		
	$.ajax({
			type : "POST",
			url : postUrl,
			dataType : "json",
			contentType : "application/json;charset=utf-8",
			//data : JSON.stringify(postdata),
			async : false,
			success : function(data) {
				if(data.key == "pass"){  //登录成功
					
					treeMenu = data.data;
					/*var items = [];
					$.each(data.data, function(key, val) {
			            items.push('<li id="' + key + '">' + val.filename + '</li>');
			        });*/
					//alert("success");
					
  					tree = new dTree('tree');
  					tree.config.target = "MainFrame";
  					tree.config.useCookies = false;
  					var selNum = -1;
  					var link = "";
  					 //根目录
  					tree.add(0,-1,'管理中心', null, null, null, '', '');
  					var count = 0;
  					var pLevelIdArray = new Array();
  					pLevelIdArray[1] = 0;
  					var currLevel = 1;
  					for (var i=0; i<treeMenu.length; i++) {
  						var item = treeMenu[i];
  						var itemLevel = item.level;
  						pLevelIdArray[itemLevel+1] = ++count;
  						if (item.link!=null && item.link!="") {
  							if (item.ico!=null) {
  								tree.add(count, pLevelIdArray[itemLevel], item.name, item.link, null, null, item.ico, item.ico);
  							} else {
  								tree.add(count, pLevelIdArray[itemLevel], item.name, item.link);
  							}
  						} else {
  							if (item.ico!=null) {
  								tree.add(count, pLevelIdArray[itemLevel], item.name, null, null, null, item.ico, item.ico);
  							} else {
  								tree.add(count, pLevelIdArray[itemLevel], item.name);
  							}
  						}
  						if (item.select) {
  							selNum = count;
  							link = item.link;
  						}
  					}
  					//document.write(tree);
  					//$("#dtree").html(tree);
  					//$("#dtree").append(tree);
  					//这个地方有问题 期待后人解决
  					document.getElementById("dtree").innerHTML=dtree;
  					tree.openAll();
  					if (selNum != -1) {
  						tree.openTo(selNum,true);
  						top.document.frames["MainFrame"].location.href=link;
  					}
					
  				}else{
  					alert("登陆失败，请联系管理员");
  				}
  			}
  	});
	
}