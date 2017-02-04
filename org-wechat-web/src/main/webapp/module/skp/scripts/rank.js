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
		
		//返回
		$("#btn_back").click(function() {
			location.href = "/skp/" + accId + "/idx.html";
		});
		
		//邀请好友助力
		$("#btn_invite_help").click(function() {
			showShareTips();
		});
		
		//关闭弹层
		$(".pop_layer, #share_popup, #unfinish .close, #unfinish a").click(function() {
			closeLayer();
		});
		
		//中奖用户
		$("#btn_winners").click(function() {
			location.href = "/skp/" + accId + "/ws.html";
		});
		
		//第一期活动时点击中奖用户
		$("#btn_winners_1").click(function() {
			showUnfinish();
		});
	}
	
	/** 显示分享弹层 */
	function showShareTips() {
		$(".pop_layer, #share_popup").show();
	}
	
	/** 显示活动未结束弹层 */
	function showUnfinish() {
		$(".pop_layer, #unfinish").show();
	}
	
	/** 关闭弹层 */
	function closeLayer() {
		$(".pop_layer, .pop-tip, .pop_con").hide();
	}
	
});