define(["jquery", "scroll_base", "cqliving_ajax"], function($, scroll_base, cq_ajax) {
	return {
		init: function(){
			try{
				ZWY_ClOUD.getSessionToken("setSessionToken");
			}catch(e){}
			//选项卡切换
			$('.commentTab').click(function(){
				var me = $(this);
				paramsObj.queryType = me.data('type');
				if(me.closest('li').hasClass('active')){
					return false;
				}
				reloadCurrentPage();
			});
			
			$('.commentTab li.active').click(function(){
				loadData(true);
			});
			
			//获取浏览器高度
			var window_height = $(window).height();
			$(".nav_list_bg").height(window_height - 80);
			loadData(true);
		}
	};
	
//	加载数据
	var canTrigger = false;
	function loadData(isFisrtPage){
		var JLastObj = $("ul[custom_more] li:last");
		if (!isFisrtPage) {
			paramsObj.lastId = JLastObj.attr("data-id");
		}else{
			paramsObj.lastId = null;
		}
		$.ajax({
			url: 'wenzheng_my_reply_list.html', 
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
//删除 这个删除提示框只能暂时应付，弹框需要另外好好优化哈。
function delFn(id){
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
		url: 'wenzheng_my_reply_del.html', 
		data: {id: delParamsObj.id, appId: paramsObj.appId, sessionId: paramsObj.sessionId, token: paramsObj.token}, 
		dataType:"json",
		type:"post",
		async: true,
		success: function(data){
			if(data.code == 0){
				setTimeout("reloadCurrentPage()", 1000);
				ZWY_ClOUD.showMessage('删除成功');
			}else{
				ZWY_ClOUD.alert(data.message ? data.message : '删除失败');
			}
		},
 		error:function(){
 			ZWY_ClOUD.showMessage('网络异常');
 		}
	});
}