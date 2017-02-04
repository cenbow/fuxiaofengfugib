define(function(require, exports) {
	
	var accId;
	var qzAjax = require("qzAjax");
	var wxShare = require("wxShare");
	
	exports.init = function(host) {
		//分享
		wxShare.configShare($.parseJSON($("#wx_config").html()), host);
		
		accId = $("#acc_id").val();
		
		//弹出层高度居中
		$(".pop_con").each(function() {
			$(this).css("margin-top", -$(this).height() / 2);
		});
		
		//点击弹出层关闭
		$(".pop_con").click(function() {
			$(this).fadeOut(200);
			$(".pop_layer").hide();
		});
		
		//活动细节
		$("#btn_hdxj").click(function() {
			location.href = "/skp/" + accId + "/rule.html";
		});
		
		//拍摄时尚大片
		$("#btn_psssdp").click(function() {			
			$(".pop_layer, #no_page").show();
//			//是否有个人主页
//			var url = "/skp/" + accId + "/ho.html";
//			qzAjax.ajaxOperate(url, null, {}, function(data) {
//				if (data.success) {
//					//弹层提示已拍过，会清零
//					$(".pop_layer, #had_page").show();
//				} else {
//					//未拍过，去制作页面
//					location.href = "/skp/" + accId + "/game.html";
//				}
//			});
		});
		
		//个人主页
		$("#btn_grzy").click(function() {
			//是否有个人主页
			var url = "/skp/" + accId + "/ho.html";
			qzAjax.ajaxOperate(url, null, {}, function(data) {
				if (data.success) {
					location.href = "/skp/" + accId + "/my.html?src=idx";
				} else {
					//弹层提示未拍摄时尚大片
        			$(".pop_layer, #no_page").show();
				}
			});
		});
		
		//时尚排行榜
		$("#btn_ssphb").click(function() {
			location.href = "/skp/" + accId + "/rk.html";
		});
		
		//进入制作页面
		$("#btn_close_popup_2").click(function() {
			$(this).fadeOut(200);
			$(".pop_layer").hide();
			//location.href = "/skp/" + accId + "/game.html";
		});
		
		//SKP介绍
		$("#btn_skp").click(function() {
			location.href = "/skp/" + accId + "/desc.html";
		});
	}
	
});