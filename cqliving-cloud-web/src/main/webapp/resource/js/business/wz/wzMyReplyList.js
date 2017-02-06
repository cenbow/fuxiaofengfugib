define(["jquery", "scroll_base", "cqalert"], function($, scroll_base, cqalert) {
	var params = {isAjaxPage: 1, queryType: 1, appId: wzAppId, token: wzUserToken, sessionId: wzUserSessionId};
	return {
		init: function(){
			//选项卡切换
			$('.commentTab').click(function(){
				var me = $(this);
				$('#comment_tab li.active').removeClass('active');
				me.parents('li').addClass('active');
				
				$('#contentDiv ul').empty();
				params.queryType = me.data('type');
				params.lastId = null;
				loadData(true);
			});
			
			//获取浏览器高度
			var window_height = $(window).height();
			$(".nav_list_bg").height(window_height - 80);
			
			loadData(true);
			
			//删除
//			$(document).on('touchstart', '.btn_delete', delFn);
		}
	};
	

//	加载数据
	var canTrigger = false;
	function loadData(isFisrtPage){
		var JLastObj = $("ul[custom_more] li:last");
		if (!isFisrtPage) {
			params.lastId = JLastObj.attr("data-id");
		}else{
			params.lastId = null;
		}
		$.ajax({
			url: 'wz_my_reply_list.html', 
			data: params, 
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
	
	function loadDataFn(){
		$.ajax({
			url: 'wz_my_reply_list.html', 
			data: params, 
			dataType:"html",
			type:"post",
			async: true,
			success: function(data){
				$("ul[custom_more]").append(data);
				if($("ul[custom_more]").find('li').length > 0){
					$('.none_status').hide();
				}
 				if ($(".isLastPage:last").val() == "true") {
					$("#loadMoreBtn").hide();
				} else {
					$("#loadMoreBtn").show();
				}
			},
     		error:function(){
     			alert("网络错误");
     		}
		});
	}
	
	
});

//删除 这个删除提示框只能暂时应付，弹框需要另外好好优化哈。
function delFn(id){
	if(id == ''){
		alert('无法删除');
		return false;
	}
	$('#model_confirm').attr('data-id', id);
	function confirmHide(){
		$("#model_confirm").hide();
		$("body").removeClass("hide_bg");
	}
	$("#model_confirm").show();
	$("body").addClass("hide_bg");
	$("#model_confirm img").click(function(){
		confirmHide();
	});
	$("#model_confirm .btn_cancel").click(function(){
		confirmHide();
	});
}
//删除
function delSubmit(){
	$("#model_confirm").hide();
	$("body").removeClass("hide_bg");
	$.ajax({
		url: 'wz_my_reply_del.html', 
		data: {id: $('#model_confirm').attr('data-id')}, 
		dataType:"json",
		type:"post",
		success: function(data){
			if(data.code == 0){
				alert('删除成功');
				window.location.href = window.location.href;
			}else{
				alert(data.message ? data.message : '删除失败');
			}
		},
 		error:function(){
 			alert("网络错误");
 		}
	});
}