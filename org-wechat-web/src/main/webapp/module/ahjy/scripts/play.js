define(function(require, exports, module) {
	
	var accId;
	var activityId;
	var qzAjax = require("qzAjax");
	
	var SHAKE_THRESHOLD = 250;
	var last_update = 0;
	var x, y, z, last_x, last_y, last_z;
	
	exports.init = function() {
//		accId = $("#acc_id").val();
		accId = 1;
		activityId = $("#aid").val();
		
		//倒计时 30 秒
//		countdown(30); 
		//轮询活动状态
		getActivityStatus();
		//模拟摇手机
//		simulate();
		initShake();
	}
	
	function initShake() {
		if (window.DeviceMotionEvent) {
			window.addEventListener('devicemotion', devicemotionHandler, false);
		} else {
			alert('不支持重力感应');
		}
	}
	
	function devicemotionHandler(ed) {
		var acceleration = ed.accelerationIncludingGravity;
		var curTime = new Date().getTime();
		var diffTime = curTime - last_update;
		if (diffTime > 1000) {
			last_update = curTime;
			x = acceleration.x;
			y = acceleration.y;
			z = acceleration.z;
			var speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
			if (speed > SHAKE_THRESHOLD) {
				doShake(speed);
			}
			last_x = x;
			last_y = y;
			last_z = z;
		}
	}
	
	function doShake(speed) {
		$("#chatAudio")[0].play();
		var url = "/ahjy/" + accId + "/addScore.html";
		var data = {
				"a": activityId,
				"s": parseInt(speed)
		}
		qzAjax.ajaxOperate(url, null, data, function(data, status) {
		}, {async: false});
	}
	
	//倒计时
//	function countdown(time) {
//		if (time < 1) {
//			//游戏结束
//			var url = "/ahjy/" + accId + "/finish.html";
//			qzAjax.ajaxOperate(url, null, {"a": activityId}, function(data, status) {
//				location.href = "/ahjy/" + accId + "/gr/" + activityId + ".html";
//			});
//		} else {
//			setTimeout(function() {
//				countdown(--time);
//			}, 1000);
//		}
//	}
	
	//获取活动状态
	function getActivityStatus() {
		var url = "/ahjy/" + accId + "/as.html";
		qzAjax.ajaxOperate(url, null, {"a": activityId}, function(data, status) {
			if (data.data.status == 2) {	//已结束
				location.href = "/ahjy/" + accId + "/gr/" + activityId + ".html";
			} else {
				setTimeout(function() {
					getActivityStatus();
				}, 1000);
			}
		});
	}
	
	//模拟摇手机得分
//	function simulate() {
//		var score = parseInt(Math.random() * 10);
//		var url = "/ahjy/" + accId + "/addScore.html";
//		var data = {
//				"a": activityId,
//				"s": score
//		}
//		qzAjax.ajaxOperate(url, null, data, function(data, status) {
//			setTimeout(function() {
//				simulate();
//			}, 1000);
//		});
//	}
	
});