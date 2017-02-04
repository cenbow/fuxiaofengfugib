define(function(require, exports) {
	
	var wx = require("jweixin");
	
	/** 
	 * 注册分享 
	 * @param _config 微信分享配置 json 对象
	 * @param _title 分享的标题
	 * @param _link 分享的链接地址
	 * @param _description 分享的描述
	 * @param _imgUrl 分享的图标地址
	 * @param _succCallback 分享成功回调函数
	 */
	exports.configShare = function (_config, _title, _link, _description, _imgUrl, _succCallback) {
		wx.config(_config);
		
		wx.ready(function() {
			//分享给朋友
			wx.onMenuShareAppMessage({
				title : _title,
				desc : _description,
				link : _link,
				imgUrl : _imgUrl,
				trigger : function(res) {},
				success : _succCallback,
				cancel : function(res) {},
				fail : function(res) {}
			});
			
			//分享到朋友圈
			wx.onMenuShareTimeline({
				title : _title,
				link : _link,
				imgUrl : _imgUrl,
				trigger : function(res) {},
				success : _succCallback,
				cancel : function(res) {},
				fail : function(res) {}
			});
			wx.error(function(res) {});
		});
	}
});