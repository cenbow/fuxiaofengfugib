define(["jquery", "cqliving_ajax"], function($, cqlivingAjax) {
	return {
		init: function() {
			//点击封面图
			$("#cover_img").click(function() {
				window.AppJsObj.getPhotoImagePathArray($("#shop_image_urls").val());
			});
			
			//点击地址打开地图
			$("#shop_address_div").click(function() {
				var lat = $("#shop_lat").val();
				var lng = $("#shop_lng").val();
				var content = encodeURI($.trim($("#shop_address").val()));
				var title = encodeURI($.trim($("#shop_name").val()));
				var appName = encodeURI($.trim($("#app_name").val()));
				var url = "http://api.map.baidu.com/marker?location=" + lat + "," + lng + "&title=" + title + "&content=" + content + "&output=html&src=" + appName;
				window.AppJsObj.redirectUrl(JSON.stringify({
					detailViewType: "9",
					url: url
				}));
			});
		}
	};
	
});