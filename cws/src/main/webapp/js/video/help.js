$(document).ready(function(){
	var height = $("#playwindow").height();
	var width = $("#playwindow").width();
	console.log(height);
	console.log(width);

	$("#time").css("position","absolute");
	$("#time").css("top",40);
	$("#time").css("left",300);
	$("#time").fadeIn();
	disptime();
});
function disptime(){
	var today = new Date(); 
	var hh = today.getHours(); 
	var mm = today.getMinutes();
	var ss = today.getSeconds();

	document.getElementById("time").innerHTML= hh+":"+mm+":"+ss;

	var myTime=setTimeout("disptime()",1000);
}