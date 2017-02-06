define(["jquery", "scroll_base", "cqliving_ajax"], function($, scroll_base, cq_ajax) {
	return {
		init: function(){
			//获取浏览器高度
			var window_height = $(window).height();
			$(".nav_list_bg").height(window_height - 80);
			loadData(true);
		}
	};

//	加载数据
	var canTrigger = false;
	function loadData(isFisrtPage){
		paramsObj.isAjaxPage = "1";
		var JLastObj = $("ul[custom_more] li:last");
		if (!isFisrtPage) {
			paramsObj.lastId = JLastObj.attr("data-id");
		}
		$.ajax({
			url: 'wenzheng_my_list.html', 
			data: paramsObj, 
			dataType:"html",
			type:"post",
			async: true,
			success: function(data){
				if(isFisrtPage){
					$("ul[custom_more]").html(data);
					if($("ul[custom_more]").find('li').length == 0){
						$('.none_status').show();
					}else{
						$('.none_status').hide();
					}
				}else{
					$("ul[custom_more]").append(data);
				}
				canTrigger = $(".isLastPage:last").val() == "false";	//有数据，允许触发滚动事件
				var hh = scroll_base.getClientHeight() * 0.5;
				$(window).scroll(function () {
					if ((scroll_base.getScrollTop() + scroll_base.getClientHeight()) >= (scroll_base.getScrollHeight() - hh)) {
						if (!canTrigger) {
							return;
						}
						canTrigger = false;	//阻止触发滚动事件
						loadData(false);
					}
				});
			},
     		error:function(){
     			$('.none_status').show();
     		}
		});
	}
});

var delParamsObj = {};
//调原生的对话框
function delOnClick(id){
	if(id == ''){
		ZWY_ClOUD.alert('无法删除');
		return false;
	}
	delParamsObj.id = id;
	ZWY_ClOUD.confirm(JSON.stringify({msg: '确定要删除吗？', sureFn: 'delSubmit'}));
}
//删除
function delSubmit(){
	$.ajax({
		url: 'wenzheng_my_del.html', 
		data: {id: delParamsObj.id, appId: paramsObj.appId, sessionId: paramsObj.sessionId, token: paramsObj.token}, 
		dataType:"json",
		type:"post",
		async: true,
		success: function(data){
			if(data.code == 0){
				ZWY_ClOUD.showMessage('删除成功');
				setTimeout("window.location.href = window.location.href;", 1000);
			}else{
				ZWY_ClOUD.alert(data.message ? data.message : '删除失败');
			}
		},
 		error:function(){
 			ZWY_ClOUD.showMessage('网络异常');
 		}
	});
}