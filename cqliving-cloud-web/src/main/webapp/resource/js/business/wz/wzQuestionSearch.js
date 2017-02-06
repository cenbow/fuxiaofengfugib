define(["jquery", "scroll_base", "cqalert"], function($, scroll_base, cqalert) {
	var paramsObj = {isAjaxPage: '1', isSearch: '1', appId: wzAppId, token: wzUserToken, sessionId: wzUserSessionId};
	return {
		init: function(){
			$("#search_text").focus();
			//点击搜索
			$('#search_button').click(srearchFn);
			
			//获取浏览器高度
			var window_height = $(window).height();
			$(".nav_list_bg").height(window_height - 80);
		}
	};
	
	function srearchFn(){
		loadData(true);
	}
	
	//加载更多
	function loadMoreFn(){
		var JLastObj = $("ul[custom_more] li:last");
		paramsObj.lastId = JLastObj.attr("data-id");
		
		paramsObj.isAjaxPage = "1";
		paramsObj.queryValue = $('#search_text').val();
		$.ajax({
			url: 'wz_question_list.html', 
			data: paramsObj, 
			dataType:"html",
			type:"post",
			success: function(data){
				$("ul[custom_more]").append(data);
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
	
	
	var canTrigger = false;
	function loadData(isFisrtPage){
		var JLastObj = $("ul[custom_more] li:last");
		if (!isFisrtPage) {
			paramsObj.lastId = JLastObj.attr("data-id");
			if (paramsObj.sortColumn) {
				paramsObj.lastValue = JLastObj.attr(paramsObj.sortColumn);
			}
		}
		paramsObj.queryValue = $('#search_text').val();
		
		$.ajax({
			url: 'wz_question_list.html', 
			data: paramsObj, 
			dataType:"html",
			type:"post",
			async: false,
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