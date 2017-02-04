define(function(require, exports) {

	var accId;
	var wxShare = require("wxShare");
	
	exports.init = function(host) {
		//分享
		wxShare.configShare($.parseJSON($("#wx_config").html()), host);
		
		accId = $("#acc_id").val();
		
		//返回
		$("#btn_back_to_idx").click(function() {
			location.href = "/skp/" + accId + "/idx.html";
		});
	}
	
});