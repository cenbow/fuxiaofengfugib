define(["jquery", "cqliving_ajax", "scroll_base"], function($, cqlivingAjax, scroll_base) {
	return {
		init: function() {
			//关闭顶部提示
			$("#close_btn").click(closeTips);
			//加载评论数据
			loadCommentData(true);
			//去看看
			$("div[openurl]").click(goSeeSee);
			//点击地址打开地图
			$("#shop_address").click(function() {
				var lat = $("#shop_lat").val();
				var lng = $("#shop_lng").val();
				var content = encodeURI($.trim($("#shop_address").val()));
				var title = encodeURI($.trim($("#shop_name").val()));
				var appName = encodeURI($.trim($("#app_name").val()));
				var url = "http://api.map.baidu.com/marker?location=" + lat + "," + lng + "&title=" + title + "&content=" + content + "&output=html&src=" + appName;
				window.AppJsObj.redirectUrl(JSON.stringify({
					detailViewType: "9",
					url: url
				}));
			});
		}
	};
	
	/** 去看看 */
	function goSeeSee() {
		location.href = $(this).attr("openurl");
	}
	
	/** 关闭顶部提示 */
	function closeTips() {
		$(".share_top").hide();
	}
	
	/** 加载评论列表数据 */
	function loadCommentData(isFisrtPage) {
		var url = "/shop/loadCommentData.html";
		var data = {
				"i": $("#shop_id").val(),
				"l": $("li[replyid]:last").attr("replyid")
		};
		cqlivingAjax.ajaxOperate(url, null, data, function(data, status) {
			if ($.trim(data)) {
				canTrigger = true;	//有数据，允许触发滚动事件
				if (isFisrtPage) {
					$("#comment_list").html(data);
				} else {
					$("#comment_list").append(data);
				}
				//绑定滚动分页事件
				var hh = scroll_base.getClientHeight() * 0.5;
				$(window).scroll(function () {
					if ((scroll_base.getScrollTop() + scroll_base.getClientHeight()) >= (scroll_base.getScrollHeight() - hh)) {
						if (!canTrigger) {
							return;
						}
						canTrigger = false;	//阻止触发滚动事件
						loadCommentData(false);
					}
				});
			} else {	//无数据
				//无更多数据时，取消绑定滚动事件
				$(window).unbind("scroll");
			}
		}, {
			async: false,
			dataType: "html"
		}); 
	}
	
});