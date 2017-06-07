/*=======================================*/
/*	系统管理主页的js                           */
/*	@author coding云                        */
/*	2014-9-23 21:28:24                         */
/*=======================================*/

$(document).ready(function(){
	$(".logout").on('click',function(){
		logout();
    });
});

function logout(){
	window.location.href="/system/login/logout";
}


//左侧菜单切换
jQuery(document).on('click', '#lside .menubar li', function(elem) {
	var menu = jQuery(this);
	if (menu.is('.cascade')) {
		menu.toggleClass('open');
	}else {
		menu.closest('.menubar').find('li').removeClass('active');
		menu.closest('.menubar').find('.cascade').removeClass('open');
		menu.closest('.cascade').addClass('open');
		menu.addClass('active');
	} 
	var href = elem.attr("href");
	// HACK IE outline
	menu.find('a').blur();
	return false;
});
