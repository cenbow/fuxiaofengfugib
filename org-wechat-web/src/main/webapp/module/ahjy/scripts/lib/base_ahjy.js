define(function(require, exports, module) {
	
	var wxShare = require("wxShare");
	
	/** 
	 * 注册分享 
	 * @param succCallback 分享成功回调函数
	 */
	exports.configShare = function (shareCallback) {
		var accId = $("#acc_id").val();
		//分享
		var s_config = $.parseJSON($("#share_config").html());
		var s_title = $("#share_title").val();
		var s_link = $("#share_link").val();
		var s_description = $("#share_desc").val(); 
		var s_imgUrl = $("#share_img").val(); 
		wxShare.configShare(s_config, s_title, s_link, s_description, s_imgUrl, shareCallback);
	}
	
});