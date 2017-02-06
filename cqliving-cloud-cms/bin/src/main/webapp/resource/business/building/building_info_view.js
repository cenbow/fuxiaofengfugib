
define(['cloud.table.curd', 'common_colorbox'], function(tableCurd, colorbox){
	return {
		init: function(lat, lng){
			//绑定返回按钮事件
			tableCurd.bindBackRestoreParamButton();
			initBaiduMap(lat, lng);
			colorbox.init();
		}
	};
	
	/** 初始化百度地图 */
	function initBaiduMap(lat, lng) {
		//默认坐标：重庆大礼堂
		map = new BMap.Map("baidu_map");          // 创建地图实例  
		var point = new BMap.Point(lng, lat);  // 创建点坐标  
		map.centerAndZoom(point, 12);                 // 初始化地图，设置中心点坐标和地图级别 
		map.addControl(new BMap.NavigationControl());	//添加平移缩放控件
		map.enableScrollWheelZoom();		//启用滚轮放大缩小
		map.disableDoubleClickZoom();	//禁用双击放大
		
		//编辑时，标记坐标
		var marker = new BMap.Marker(new BMap.Point(lng, lat), {enableDragging: true, raiseOnDrag: true});        // 创建标注    
		map.addOverlay(marker);
	}
});