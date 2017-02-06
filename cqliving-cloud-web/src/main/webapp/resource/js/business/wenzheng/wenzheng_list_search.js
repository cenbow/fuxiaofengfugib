define(["jquery", "scroll_base"], function($, scroll_base) {
	return {
		init: function(){
			$("#search_text").focus();
			//点击搜索
			$('#search_button').click(srearchFn);
			
			//获取浏览器高度
			var window_height = $(window).height();
			$(".nav_list_bg").height(window_height - 80);
			
			$("#search_text").on('click', function(){
				try {
					window.AppJsObj.goToTop();
				} catch (e) {
				}
			});
		}
	};
	
	function srearchFn(){
		loadData(true);
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
			url: 'list.html', 
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