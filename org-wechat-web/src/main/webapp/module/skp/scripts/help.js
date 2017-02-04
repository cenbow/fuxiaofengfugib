define(function(require, exports) {
	
	var accId;
	var qzAjax = require("qzAjax");
	var wxShare = require("wxShare");
	
	exports.init = function(host) {
		//弹出层高度居中
		$(".pop_con").each(function() {
			$(this).css("margin-top", -$(this).height() / 2);
		});
		
		//分享
		wxShare.configShare($.parseJSON($("#wx_config").html()), host);
		
		accId = $("#acc_id").val();
		
		//关闭弹层
		$(".pop_layer, .pop_con .close, .pop_con a").click(function() {
			$(".pop_layer, .pop_con").hide();
		});
		
		//我要拍摄
		$("#btn_index").click(function() {
			//去首页
			location.href = "/skp/" + accId + "/idx.html";
		});
		
		//时尚排行榜
		$("#btn_rank").click(function() {
			//去首页
			location.href = "/skp/" + accId + "/rk.html";
		});
		
		//助力
		$("#btn_help").click(function() {
			showHelped();
//			var url = "/skp/" + accId + "/hf.html";
//			var data = {
//					"f": $("#friend_id").val()
//			};
//			qzAjax.ajaxOperate(url, null, data, function(data) {
//				if (data.success) {
//					showHelpSucc();
//					//累加好友分数
//					var friend_score = parseInt($("#friend_score").html());
//					$("#friend_score").html(friend_score + parseInt($("#help_score").val()));
//				} else {
//					showHelped();
//				}
//			});
		});
	} 
	
	/** 显示助力成功弹层 */
	function showHelpSucc() {
		$(".pop_layer, #help_succ_popup").show();
	}
	
	/** 显示已助力弹层 */
	function showHelped() {
		$(".pop_layer, #help_fail_popup").show();
	}
	
});