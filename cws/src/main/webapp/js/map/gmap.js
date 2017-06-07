window.selectmakers = {};

window.mapmakers = {};

var detectionrate = 0;

var runrate = 0;

var withoutEletrict = 0;

var totalnum = 0;

//使用自己的图片
var normal = new BMap.Icon(getContextPath() + "/images/map/green-dot.png", new BMap.Size(26, 32));
//黄色水质异常
var abnorma1 = new BMap.Icon(getContextPath() + "/images/map/yellow-dot.png", new BMap.Size(26, 32));
//红色故障
var abnorma2 = new BMap.Icon(getContextPath() + "/images/map/red-dot.png", new BMap.Size(26, 32));
//蓝色断电断线
var abnorma3 = new BMap.Icon(getContextPath() + "/images/map/blue-dot.png", new BMap.Size(26, 32));
//紫色表示查找到的
var searched = new BMap.Icon(getContextPath() + "/images/map/purple-dot.png", new BMap.Size(26, 32));

var map;

//initmap方法 ajax返回的站点信息；
window.sewagedata;

$(document).ready(function(){
	initmap();
	
	$("#seachlist").on('click',function(){
		getsewagesbyareaid();
    });
	
	$("#seachpoint").on('click',function(){
		getsewagebyshortnameoryunyinid();
	});
	
	$("#showtruemap").on('click',function(){
		if( $("#showtruemap").is(':checked') == true){
			showmap(1);
		}else{
			showmap(0);
		}
	});
	
});

function showmap(token){
	if(token == 1){
		for(key in window.mapmakers){
			var item = window.mapmakers[key].item;
			var maker = window.mapmakers[key].maker;
        	if(item.isAbnormal == 0){
        		maker.setIcon(normal);
	    	}
	    	if(item.isAbnormal == 1){
	    		maker.setIcon(abnorma1);
	    		detectionrate = detectionrate + 1;
	    	}
	    	if(item.isAbnormal == 2){
	    		maker.setIcon(abnorma2);
	    		runrate = runrate + 1;
	    	}
	    	if(item.isAbnormal == 3){
	    		maker.setIcon(abnorma3);
	    		withoutEletrict = withoutEletrict +1;
	    	}
	    	
	    	totalnum = totalnum + 1;
		}
		$("#abnormalrate").text("设备故障率"+((runrate/totalnum)*100).toFixed(2)+"%");
		$("#waterrate").text("水质异常率"+((detectionrate/totalnum)*100).toFixed(2)+"%");
		$("#withoutElectric").text("断电断线率"+((withoutEletrict/totalnum)*100).toFixed(2)+"%");
	}else{
		for(key in window.mapmakers){
			var item = window.mapmakers[key].item;
			var maker = window.mapmakers[key].maker;
			maker.setIcon(normal);
		}
		$("#abnormalrate").text("设备故障率0.00%");
		$("#waterrate").text("水质异常率0.00%");
		$("#withoutElectric").text("断电断线率0.00%");
	}
	
}

function getsewagebyshortnameoryunyinid(){
	//不为空才进行搜索
	if($("#keywords").val().trim() != ""){
		var postdata = {"searchNameOrId":$("#keywords").val().trim()};
		
		var contextPath = getContextPath();
		
		var postUrl = contextPath+"/monitor/getSewagesBySearchNameOrId.do";
		
		var data = JSON.stringify(postdata);
		
		$.ajax({
			type : "POST",
			url : postUrl,
			dataType : "json",
			contentType : "application/json;charset=utf-8",
			data : data,
			async : false,
			beforeSend:loading,//执行ajax前执行loading函数.直到success 
			complete:offloading,
			success : function(data) {
				if(data.searchResult == ""){
					alert("没有相关站点")
				}				
				//设置为空
				window.selectmakers = {};
				//var myIcon = new BMap.Icon(getContextPath() + "/images/map/blue-dot.png", new BMap.Size(26, 32))
				
				//获取该地区下素有的站点并循环显示
		        $.each(data.searchResult,function(key,item){
		        	var aa = window.mapmakers[item.sewageid];
		        	var type = typeof(aa);
		        	if(type == "object"){
		        		var maker = window.mapmakers[item.sewageid].maker;
		        		var item = window.mapmakers[item.sewageid].item;
		        		maker.setIcon(searched);
		        		maker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
		        		
			        	var point = new BMap.Point(item.coordinatex,item.coordinatey);
			        	var sewageid = item.sewageid;
			        	showInfo(sewageid,point,map);
		        	}
		        });
			}
		});
	}
}

//选择地区名展示站点
function getsewagesbyareaid(){
	$("#showtruemap").attr("checked",false);

	//初始化为0 以防止重复计算
	detectionrate = 0;
	runrate = 0;
	withoutEletrict = 0;
	totalnum = 0;

	var postdata = {"areaid":$("#searchlist_areaid").val()};
	var contextPath = getContextPath();
	var postUrl = contextPath+"/monitor/getsewagesbyareaid.do";
	var data = JSON.stringify(postdata);
	$.ajax({
		type : "POST",
		url : postUrl,
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		data : data,
		async : false,
		beforeSend:loading,//执行ajax前执行loading函数.直到success 
		complete:offloading,
		success : function(data) {
			//获取地区经纬度，如果经纬度为零就采用 areaName
			var map = new BMap.Map("allmap");  // 创建Map实例
			if(data.coordinatey == 0 || data.coordinatex == 0){
				map.centerAndZoom(data.areaName,11);      // 采用名字
			}else{
				var point = new BMap.Point(data.coordinatey, data.coordinatex);  // 创建点坐标
				map.centerAndZoom(point, 11);     
			}
			map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
			//设置为空
			//window.selectmakers = [];
			window.mapmakers = {};
			//var myIcon = new BMap.Icon(getContextPath() + "/images/map/green-dot.png", new BMap.Size(26, 32))
			//获取该地区下素有的站点并循环显示
	        $.each(data.sewages,function(key,item){
	        	var point = new BMap.Point(item.coordinatex,item.coordinatey);
	        	
	        	//normal
	        	var maker = new BMap.Marker(point,{icon:normal});
	        	var sewageid = item.sewageid;
	        	maker.addEventListener("click", function(){
	        		showInfo(sewageid,point,map);
	        	});
	        	map.addOverlay(maker);            //增加点 
	        	window.mapmakers[item.sewageid] = {maker:maker,item:item};        	
	        });
		}
	});
}


//初始化进入全部地图
function initmap(){
	var mapConfigUrl = getContextPath() + "/monitor/getMapConfig.do";

	$.ajax({
		type : "POST",
		url : mapConfigUrl,
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		async : true,
		beforeSend:loading,//执行ajax前执行loading函数.直到success
		//complete:offloading,
		success : function(data) {

			// 百度地图API功能
			map = new BMap.Map("allmap");  // 创建Map实例
			if (data.useAreaName == true){
				map.centerAndZoom(data.areaName,data.zoom);      // 初始化地图,用城市名设置地图中心点
				map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
			}else{
				map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放00000,119.7053550000
				var point = new BMap.Point(data.longitude,data.latitude);  // 创建点坐标
				map.centerAndZoom(point, data.zoom);
			}

			//获取集合的地址
			var postUrl = getContextPath() + "/monitor/getallsewages.do";

			$.ajax({
				type : "POST",
				url : postUrl,
				dataType : "json",
				contentType : "application/json;charset=utf-8",
				async : true,
				//beforeSend:loading,//执行ajax前执行loading函数.直到success
				complete:offloading,
				success : function(data) {
					$.each(data,function(key,item){
						var point = new BMap.Point(item.coordinatex,item.coordinatey);
						var maker = new BMap.Marker(point,{icon:normal});
						var sewageid = item.sewageid;
						maker.addEventListener("click", function(){
							showInfo(sewageid,point,map);
						});
						map.addOverlay(maker);            //增加点
						window.mapmakers[item.sewageid] = {maker:maker,item:item};
					});
				}
			});
			//map.centerAndZoom("江都",11);      // 初始化地图,用城市名设置地图中心点
			//}else if($("#systemno").val() == 2){
			//	map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放00000,119.7053550000
			//	var point = new BMap.Point(120.476852, 31.6371);  // 创建点坐标
			//	map.centerAndZoom(point, 17);
			//}

		}
	});

}

function showInfo(sewageid,point,mappara){
	var postdata = {"sewageid":sewageid};
	
	var contextPath = getContextPath();
	
	var postUrl = contextPath+"/monitor/getlatestdetectiondata.do";
	
	var data = JSON.stringify(postdata);
	
	$.ajax({
		type : "POST",
		url : postUrl,
		dataType : "json",
		contentType : "application/json;charset=utf-8",
		data : data,
		async : false,
		success : function(data) {
			var html = data.message;
			var infoWindow = new BMap.InfoWindow(html);  // 创建信息窗口对象 
			mappara.openInfoWindow(infoWindow,point); //开启信息窗口
		}
	});
}


function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}

