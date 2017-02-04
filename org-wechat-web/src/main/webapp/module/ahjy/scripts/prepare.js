define(function(require, exports, module) {
	
	var accId;
	var activityId;
	var qzAjax = require("qzAjax");
	
	exports.init = function() {
//		accId = $("#acc_id").val();
		accId = 1;
		activityId = $("#aid").val();
		
		//倒计时 18 秒
		countdown($("#time").val()); 
		//轮询活动状态
		getActivityStatus();
		
		//直接开始
		$(".start-lnk").click(function() {
			var url = "/ahjy/" + accId + "/begin.html";
			qzAjax.ajaxOperate(url, null, {"a": activityId}, function(data, status) {
				if (data.success) {
					location.href = "/ahjy/" + accId + "/play/" + activityId + ".html";
				}
			});
		});
	}
	
	//倒计时
	function countdown(time) {
		if (time < 1) {
			//开摇
			var url = "/ahjy/" + accId + "/begin.html";
			qzAjax.ajaxOperate(url, null, {"a": activityId}, function(data, status) {
				if (data.success) {
					location.href = "/ahjy/" + accId + "/play/" + activityId + ".html";
				}
			});
		} else {
			$(".time b").html((time--) + "<em>s</em>");
			setTimeout(function() {
				countdown(time);
			}, 1000);
		}
	}
	
	//获取活动状态
	function getActivityStatus() {
		var url = "/ahjy/" + accId + "/as.html";
		qzAjax.ajaxOperate(url, null, {"a": activityId}, function(data, status) {
			if (data.data.status == 1) {	//已开始开始
				location.href = "/ahjy/" + accId + "/play/" + activityId + ".html";
			} else if (data.data.status == 2) {	//已结束
				location.href = "/ahjy/" + accId + "/gr/" + activityId + ".html";
			} else {
				setTimeout(function() {
					getActivityStatus();
				}, 1000);
			}
		});
	}
	
});