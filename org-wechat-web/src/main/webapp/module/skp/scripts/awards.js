define(function(require, exports) {

	var accId;
	var wxShare = require("wxShare");
	
	exports.init = function(host) {
		//分享
		wxShare.configShare($.parseJSON($("#wx_config").html()), host);
		
		accId = $("#acc_id").val();
		
		//兑奖
		$("a[isreward=0]").click(function() {
			var k = $(this).attr("batchid");
			location.href = "/skp/" + accId + "/rd/" + k + ".html";
		});
	}
	
});