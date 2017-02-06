define(["jquery"], function($) {
	return {
		init: function() {
			//调用客户端js，打开定位设备
			setTimeout(function() {
				window.AppJsObj.openLocationService();
			}, 1000);
		}
	};
	
});