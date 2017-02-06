define(['validator.bootstrap', 'cloud.time.input', 'cloud.table.curd', 'ZeroClipboard', 'cqliving_dialog', 'cqliving_ajax', 'myUploader', 'common_colorbox', 'ueditor', 'ueditorConfig'], function($, timeInput, tableCurd, zcl, cq_dialog, cq_ajax, myUploader, colorbox){
	var map, ue, viewUploader, listViewUploader;
	return {
		init: function(){
			timeInput.initTimeInput();
			//绑定返回按钮事件
			tableCurd.bindBackRestoreParamButton();
			//初始化图片查看
			colorbox.init();
			
			initUploader();
			//表单验证
			formValidateInit();
			//初始化百度地图
			initBaiduMap();
			//绑定保存事件
			$('#saveButton').click(doSave);
			//是否外链单选按钮控制
			$('input[name=isLink]').change(isShowLinkUrl);
			//是景点才初始化
			if(sourceType == 1){
				//这个我也不懂,看着别人加我也加了。
				window.ZeroClipboard = zcl;
				
				//初始化百度编辑器
				ue = UE.getEditor("tourism_info_content",{textarea: 'content'});
			}
			
			//预览
			$('#previewBtn').click(previewFn);
		}
	}
	
	/** 初始化百度地图 */
	function initBaiduMap() {
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
	
	/**
	 * 初始化图片上传
	 */
	function initUploader(){
		//初始化列表图 只有一张
		viewUploader = myUploader.init({
			url: "/common/upload.html",
			containerId: "imageUrlBtn",
			thumbContainerId: "imageUrlList",
			fileUrlPath: imgUrl,
			success: function(file, response) {
				var imgPath = imgUrl + response.data.filePath;
				//将图片的引用修改为上传后的图片路径
				$("#imageUrlList ul li img").last().attr("src", imgPath);
			},
			error: function(file, reason) {
				cq_dialog.error(reason);
			},
			isSingle: true,
			destroy: true
		}, viewUploader);
		//初始化多张图片
		listViewUploader = myUploader.init({
			url: "/common/upload.html",
			containerId: "img_upload",
			thumbContainerId: "img_thum",
			fileUrlPath: imgUrl,
			fileNumLimit: 9,
			success: function(file, response) {
				var imgPath = imgUrl + response.data.filePath;
				//将图片的引用修改为上传后的图片路径
				$("#img_thum ul li[id=" + file.id + "] img").last().attr("src", imgPath);
			},
			error: function(file, reason) {
				cq_dialog.error(reason);
			},
			isSingle: false,
			destroy: true
		}, listViewUploader);
	}
	
	/**
	 * 控制是否显示外链地址
	 */
	function isShowLinkUrl(){
		if($('#isLink1').prop('checked')){
			$('#linkUrl').closest('.form-group').show();
			$('.linkControl').hide();
		}else{
			$('#linkUrl').closest('.form-group').hide();
			$('.linkControl').show();
			initUploader();
		}
	}
	//初始化表单验证
	function formValidateInit(){
		if(sourceType==TYPE1){
			//景点
			$("#form1").validate({
				ignore:"",
	            rules: {
	                name : {
	                    required: true
	                },
	                place : {
	                    required: true
	                },
	                lat : {
	                    required: true
	                },
	                lng : {
	                	required: true
	                }
	            },
	            messages: {
	            	name : {
	                    required: '请输入名称'
	                },
	                place : {
	                    required: '请输入所处位置'
	                },
	                lat : {
	                    required: '请选择坐标'
	                },
	                lng : {
	                	required: ' '
	                }
	            }
	        });
		}else{
			//专题
			$("#form1").validate({
	            rules: {
	                name : {
	                    required: true
	                },
	                description: {
	                	required: true
	                },
	                scenicRoute: {
	                	required: true
	                }
	            },
	            messages: {
	            	name : {
	                    required: '请输入名称'
	                },
	                description: {
	                	required: '请输入专题描述'
	                },
	                scenicRoute: {
	                	required: '请输入景点线路'
	                }
	            }
	        });
		}
	}
	
	/**
	 * 开始保存
	 */
	function doSave(){
		var me = $(this),
			myForm = $('#form1'),
			url = myForm.attr('action'),
			isLink = $('#isLink1').prop('checked'),
			linkUrl = $('#linkUrl').val(),
			lat = $('#map_lat').val(),
			lng = $('#map_lng').val(),
			place = $('#place').val(),
			imageUrlObj = $('#imageUrlList li.upload-imgs img:last'),
			imageUrl = '',
			imgs = $("#img_thum .upload-imgs img");
		
		if(imageUrlObj && imageUrlObj.length > 0){
			imageUrl =  imageUrlObj.attr('src');
		}
		if(!myForm.valid()){
			return;
		}
		if(!imageUrl && imageUrl == ''){
			cq_dialog.error('请选择列表图');
			return;
		}else{
			$('#imageUrl').val(imageUrl);
		}
		
		if(sourceType == TYPE1){//添加景点才验证
			//连接地址验证
			if(isLink && linkUrl == ''){
				cq_dialog.error('请输入链接地址', function(){
					$('#linkUrl').focus();
				});
				return;
			}
			
			if($('#isLink0').prop('checked')){
				var scenicRoute = $("#scenicRoute").val();
				if(!$.trim(scenicRoute)){
					cq_dialog.error('请输入景点线路');
					return;
				}
			}
			
			if(!isLink){//不是外链的时候验证
				if (!ue.getContent()) {
					cq_dialog.error("请输入景点介绍内容");
					return;
				}
			}
		}else if(sourceType == TYPE2){
			//新增需求by2016年11月11日 11:54:34，如果是专题时：所处位置和坐标要么都有，要么都没有
			if(place == '' && (lat != '' || lng != '')){
				cq_dialog.error('请输入所处位置');
				$('#place').focus();
				return ;
			}
			if(place != '' && (lat == '' || lng == '')){
				$('#suggestId').focus();
				cq_dialog.error('请在地图上标记坐标');
				return ;
			}
		}
		if(!isLink){//不是外链的时候验证
			//验证图片
			if (imgs.length <= 0) {
				cq_dialog.error('请上传' + $('#imagesLabel').html());
				return;
			}else{
				//设置图片地址
				var images = $("#images");
				images.val("");	//清空，避免重复数据
				imgs.each(function() {
					if (images.val()) {
						images.val(images.val() + "," + $(this).attr("src"));
					} else {
						images.val($(this).attr("src"));
					}
				});
			}
		}
		var params = myForm.serializeArray();
		cq_ajax.ajaxOperate(url, me, params, function(data,status){
			if(data.code >= 0){
				me.unbind("click");
				cq_dialog.success(data.message ? data.message: "保存成功", function(){
					if(me.attr("back_url")){
						$('#backButton').click();
					}
				});
			}else{
				cq_dialog.error(data.message?data.message:"保存失败");
			}
		});
	}
	
	/**
	 * 预览
	 */
	function previewFn(){
		var me = $(this),
			url = me.attr('url');
		cq_ajax.ajaxOperate(url, me, {}, function(data, status) {
			$("body").append(data);
		}, {dataType: "html"});
	}
});