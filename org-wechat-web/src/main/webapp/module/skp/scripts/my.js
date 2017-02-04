define(function(require, exports) {
	
	var accId;
	var wxShare = require("wxShare");
	
	exports.init = function(host) {
		//弹出层高度居中
		$(".pop_con").each(function() {
			$(this).css("margin-top", -$(this).height() / 2);
		});
		
		//分享
		wxShare.configShare($.parseJSON($("#wx_config").html()), host);
		
		accId = $("#acc_id").val();
		
		//从制作页面跳转过来时，展示分享弹层
		var src = $("#src").val();
		if (src == "mk") {
			showShareTips();
		}
		
		//关闭弹层
		$(".pop_layer, #share_popup, #btn_close_lucy").click(function() {
			closeLayer();
		});
		
		//邀请好友助力
		$("#btn_invite_help").click(function() {
			showShareTips();
		});
		
		//每日幸运测试
		$("#btn_lucy_test").click(function() {
			showLucy();
		});
		
		//幸运弹层确定，时尚排行榜
		$("#btn_go_rank, #btn_rank").click(function() {
			location.href = "/skp/" + accId + "/rk.html";
		});
	} 
	
	/** 关闭弹层 */
	function closeLayer() {
		$(".pop_layer, .pop-tip, .pop_con").hide();
	}
	
	/** 显示幸运弹层 */
	function showLucy() {
		$(".pop_layer, #lucy_popup").show();
	}
	
	/** 显示分享弹层 */
	function showShareTips() {
		$(".pop_layer, #share_popup").show();
	}
	
});