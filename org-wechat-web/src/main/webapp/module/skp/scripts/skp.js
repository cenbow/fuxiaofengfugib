define(function(require, exports) {

	var wxShare = require("wxShare");
	
	exports.init = function(host) {
		//分享
		wxShare.configShare($.parseJSON($("#wx_config").html()), host);
	}
	
});