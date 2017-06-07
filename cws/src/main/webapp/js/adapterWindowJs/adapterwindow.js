function adapter(){
	$('#showForm').datagrid('resize', {
		width:$(window).width()*0.984,
		height:$(window).height()*0.98
	});
}
$(window).resize(function() {
	adapter();
});

$(document).ready(function (){
	adapter();
});