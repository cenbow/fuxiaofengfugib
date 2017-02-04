define(function(require, exports) {
	
	var wx = require("jweixin");
	var qzAjax = require("qzAjax");
	
	/** 注册分享 */
	exports.configShare = function (_config, host) {
		wx.config(_config);
		
		var accId = $("#acc_id").val();
		var userId = $("#user_id").val();
		var shareTitle;
		var shareDesc;
		var shareLink;
		
		//查询用户是否已经拍过时尚大片
		var url = "/skp/" + accId + "/ho.html";
		qzAjax.ajaxOperate(url, null, {}, function(data) {
			if (data.success) {
				shareTitle = "北京SKP提示你，快为好友的时尚大片助力吧！";
				shareDesc = "北京SKP美妆世界盛大开幕，助力好友为TA加油吧！";
				shareLink = host + "/skp/" + accId + "/fd/" + userId + ".html";
			} else {
				shareTitle = "定制您的时尚大片，北京SKP惊喜送不停！";
				shareDesc = "北京SKP美妆世界盛大开幕，快来拍摄时尚大片吧！";
				shareLink = host + "/skp/" + accId + "/idx.html";
			}
		});
		
		var shareImgUrl = host + "/module/skp/front/images/share.jpg";
		wx.ready(function() {
			//TODO tt 分享给朋友
			wx.onMenuShareAppMessage({
				title : shareTitle,
				desc : shareDesc,
				link : shareLink,
				imgUrl : shareImgUrl,
				trigger : function(res) {},
				success : function(res) {
					//关闭分享提示弹层
					$(".pop_layer, .pop-tip").hide();
				},
				cancel : function(res) {},
				fail : function(res) {}
			});
			
			//TODO tt 分享到朋友圈
			wx.onMenuShareTimeline({
				title : shareTitle,
				link : shareLink,
				imgUrl : shareImgUrl,
				trigger : function(res) {},
				success : function(res) {
					//关闭分享提示弹层
					$(".pop_layer, .pop-tip").hide();
				},
				cancel : function(res) {},
				fail : function(res) {}
			});
			wx.error(function(res) {});
		});
	}
});