/**
 * 手机置业新增、修改
 */
define(['validator.bootstrap','cloud.table.curd', 'cqliving_dialog', 'cqliving_ajax', 'myUploader', 'common_colorbox', 'chosen'], function($, tableCurd, cq_dialog, cq_ajax, myUploader, colorbox){
	var map, viewUploader, listViewUploader;
	return {
		init: function(){
			//绑定返回按钮事件
			tableCurd.bindBackRestoreParamButton();
			//初始化图片查看
			colorbox.init();
			//初始化上传图片控件
			initUploader();
			//初始化下拉搜索控件
			$(".chosen-select").chosen({search_contains: true});
			
			//地图初始化
			initBaiduMap();
			//绑定楼盘名称改变定位地址
			$('#buildingName').on('blur', buildingNameBlur);
			//修改赋值坐标
			//$('#buildingName').trigger('blur');
			
			//初始化页面表单验证
			formValidateInit();
			//绑定保存按钮
			$('#saveButton').click(doSave);
		}
	}
	
	/** 初始化百度地图 */
	function initBaiduMap(address) {
		//默认坐标：重庆大礼堂
		var lat = !$("#map_lat").val() ? 29.568295 : $("#map_lat").val();
		var lng = !$("#map_lng").val() ? 106.559123 : $("#map_lng").val();
		map = new BMap.Map("baidu_map");          // 创建地图实例  
		var point = new BMap.Point(lng, lat);  // 创建点坐标  
		map.centerAndZoom(point, 12);                 // 初始化地图，设置中心点坐标和地图级别 
		map.addControl(new BMap.NavigationControl());	//添加平移缩放控件
		map.enableScrollWheelZoom();		//启用滚轮放大缩小
		map.disableDoubleClickZoom();	//禁用双击放大
		map.addEventListener("click", handleMapClick);	//地图单击事件
		
        if(address && address != ''){
        	pointByAddress(address);
        }
        
		//编辑时，标记坐标
		if ($("#map_lat").val() && $("#map_lng").val()) {
			var marker = new BMap.Marker(new BMap.Point($("#map_lng").val(), $("#map_lat").val()), {enableDragging: true, raiseOnDrag: true});        // 创建标注    
			marker.addEventListener("dragend", function(e) {	//拖拽事件
				$("#map_lat").val(e.point.lat);
				$("#map_lng").val(e.point.lng);
			});
			map.addOverlay(marker);
		}
		
		//自动完成控件
		var ac = new BMap.Autocomplete({   
			"input": "suggestId", 
			"location" : map
		});
		ac.addEventListener("onconfirm", function(e) { 
			var local = new BMap.LocalSearch(map, {onSearchComplete: function(result) {
				//在第一个结果点上添加标记
				var searchPoint = local.getResults().getPoi(0).point;
				map.clearOverlays();	//清除覆盖物
				$("#map_lat").val(searchPoint.lat);
				$("#map_lng").val(searchPoint.lng);
				map.centerAndZoom(searchPoint, 16);
				var marker = new BMap.Marker(searchPoint, {enableDragging: true, raiseOnDrag: true});        // 创建标注    
				marker.addEventListener("dragend", function(e) {	//拖拽事件
					$("#map_lat").val(e.point.lat);
					$("#map_lng").val(e.point.lng);
				});
				map.addOverlay(marker);
			}});
			//搜索选择的结果
			local.search($("#suggestId").val());
		});
	}
	
	/** 地图点击事件函数 */
	function handleMapClick(e) {
		map.clearOverlays();	//清除覆盖物
		$("#map_lat").val(e.point.lat);
		$("#map_lng").val(e.point.lng);
		var marker = new BMap.Marker(e.point, {enableDragging: true, raiseOnDrag: true});        // 创建标注    
		marker.addEventListener("dragend", function(e) {	//拖拽事件
			$("#map_lat").val(e.point.lat);
			$("#map_lng").val(e.point.lng);
		});
		map.addOverlay(marker);
	}

	/** 根据地址定位地图 **/
	function pointByAddress(address){
		//alert("预备解析")
        var myGeo = new BMap.Geocoder();
        // 将地址解析结果显示在地图上,并调整地图视野
        myGeo.getPoint(address, function(point, obj){
            //alert("开始解析")
            if(address){
                if (point) {
                    map.clearOverlays();    //清除覆盖物
                    map.centerAndZoom(point, 16);
                    map.addOverlay(new BMap.Marker(point));
                    
                    $("#map_lat").val(point.lat);
                    $("#map_lng").val(point.lng); 
                    $('#address').val(obj.address);
                    
                }else{
                    cq_dialog.error('您选择地址没有解析到结果!');
                    map.clearOverlays();    //清除覆盖物
                    var point = new BMap.Point(106.559123,29.568295);  // 创建点坐标  
                    map.centerAndZoom(point, 16);                 // 初始化地图，设置中心点坐标和地图级别 
                    map.addOverlay(new BMap.Marker(point));
                }
            }
        }, "重庆市");
	}
	
	/** 输入楼盘名称，根据区域加楼盘名称定位到地图 **/
	function buildingNameBlur(){
		var me = $(this),
			name = me.val(),
			regionName = $('#regionCode option:selected').text();
		var address = regionName + name;
		if(name != '')
			pointByAddress(address);
	}
	
	/**
	 * 初始化图片上传
	 */
	function initUploader(){
		//初始化列表图 
		viewUploader = myUploader.init({
			url: "/common/upload.html",
			containerId: "listImageUrlBtn",
			thumbContainerId: "listImageUrlList",
			fileUrlPath: imgUrl,
			fileNumLimit: 9,
			success: function(file, response) {
				var imgPath = imgUrl + response.data.filePath;
				//将图片的引用修改为上传后的图片路径
//				$("#listImageUrlList ul li img").last().attr("src", imgPath);
				$("#listImageUrlList ul li[id=" + file.id + "] img").last().attr("src", imgPath);
			},
			error: function(file, reason) {
				cq_dialog.error(reason);
			},
			isSingle: false,
			destroy: true
		}, viewUploader);
		//初始化多张图片
		listViewUploader = myUploader.init({
			url: "/common/upload.html",
			containerId: "imagesBtn",
			thumbContainerId: "imgagesThum",
			fileUrlPath: imgUrl,
			fileNumLimit: 9,
			success: function(file, response) {
				var imgPath = imgUrl + response.data.filePath;
				//将图片的引用修改为上传后的图片路径
				$("#imgagesThum ul li[id=" + file.id + "] img").last().attr("src", imgPath);
				var inputHtml = '<div class="imgDesc">';
					inputHtml += '<input type="hidden" name="images" value="'+imgPath+'">';
					inputHtml += '<input type="text" name="descType" class="form-control" placeholder="输入户型 如一室一厅一卫一厨">';
					inputHtml += '<input type="text" name="descArea" class="form-control" placeholder="输入面积 如套内面积：34㎡">';
					inputHtml += '</div>';
				$.each($("#imgagesThum ul li"), function(i, d){
					if($(d).find('.imgDesc').length == 0){
						$(d).append(inputHtml);
					}
				});
			},
			error: function(file, reason) {
				cq_dialog.error(reason);
			},
			isSingle: false,
			destroy: true
		}, listViewUploader);
	}
	
	/**
	 * 初始化表单验证
	 */
	function formValidateInit(){
		$("#form1").validate({
            rules: {
            	regionCode : {required: true},
                name : {required: true},
                address: {required: true},
                telephone: {required: true}
            },
            messages: {
            	regionCode : {required: '请选择区域'},
                name : {required: '请输入楼盘名称'},
                address: {required: '请输入楼盘地址'},
                telephone: {required: '请输入咨询电话'}
            }
        });
	}

	/**
	 * 开始保存
	 */
	function doSave(){
		var me = $(this),
			myForm = $('#form1'),
			url = myForm.attr('action'),
			listImageUrl = '',
			listImageUrlObj = $('#listImageUrlList img');
		if(listImageUrlObj.length > 0){
			$.each(listImageUrlObj, function(i, d){
				if(listImageUrl != ''){
					listImageUrl += ','
				}
				listImageUrl += $(d).attr('src');
			});
		}
		if(listImageUrl == ''){
			cq_dialog.error('请上传轮播图');
			return;
		}else{
			$('#listImageUrl').val(listImageUrl);
		}
		//区域名称处理
		$('#regionName').val($('#regionCode option:selected').text());
		if(!myForm.valid()){
			return ;
		}
		var params = myForm.serializeArray();
		cq_ajax.ajaxOperate(url, me, params, function(data,status){
			if(data.code >= 0){
				me.unbind("click");
				cq_dialog.success(data.message ? data.message: "保存成功", function(){
					if(me.attr("back_url")){
						$('#backBtn').trigger('click');
					}
				});
			}else{
				cq_dialog.error(data.message?data.message:"保存失败");
			}
		});
	}
	
});
