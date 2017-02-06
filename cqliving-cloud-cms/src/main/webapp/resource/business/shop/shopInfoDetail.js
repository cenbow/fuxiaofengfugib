define(["validator.bootstrap", "cloud.table.curd", "cloud.time.input", "cqliving_dialog", "cqliving_ajax", "ZeroClipboard", "myUploader", "common_colorbox", "ueditor", "ueditorConfig"], 
		function($, tableCurd, timeInput, cqliving_dialog, cq_ajax, zcl, myUploader, colorbox) {
	
	var ue;
	var map;
	var listViewUploader = ""; 
	var uploaderOptions = {
			url: "/common/upload.html",
			containerId: "img_upload",
			thumbContainerId: "img_thum",
			success: function(file, response) {
				var imgPath = imgUrl + response.data.filePath;
				//将图片的引用修改为上传后的图片路径
				$("#img_thum ul li[id=" + file.id + "] img").last().attr("src", imgPath);
				//处理封面设置按钮 
//				handleButton(file.id);
//				removeImg();
				if ($("#img_thum li.upload-imgs").length > 9) {	//最多9张图
					$("#img_thum li.upload-imgs:gt(8)").remove();
					return;
				}
			},
			error: function(file, reason) {
				cqliving_dialog.error(reason);
			},
//			removeCallback: removeImg,
			isSingle: false,
			destroy: true
	};
	
	return {
		init: function() {
			colorbox.init();
			tableCurd.bindSaveButton();
			timeInput.initTimeInput();
			window.ZeroClipboard = zcl;
			uploaderOptions.fileUrlPath = imgUrl;
			listViewUploader = myUploader.init(uploaderOptions, listViewUploader);
			
			//初始化百度编辑器
			ue = UE.getEditor("shop_info_content_editor");
			//表单验证
			validForm();
			//设为封面
//			$("#img_thum").on("click", ".cover_btn", setCover);
			//控制分类和区域下拉选项
			handleCategoryAndRegion();
			//切换分类
			$("#typeId").change(handleCategoryAndRegion);
			//初始化百度地图
			initBaiduMap();
			//提交表单
			$("#save_btn").click("1", submit);
			$("#online_btn").click("3", submit);
			//移除原有图片
			$(".icon-remove").closest("a").click(function() {
				$(this).closest(".upload-imgs").remove();
				removeImg();
			});
			//审核
			$('#audit_btn').click(auditWin);
			$("#audit_online_btn").click("4", submit);
			//根据地址定位
			pointByAddress($('#address').val());
		}
	};
	
	/** 提交表单 */
	function submit(e) {
		var type = e.data;
		var jthis = $(this);
		var jform = jthis.parents("form");
		var $imgs = $("#img_thum img");
		//验证图片
		if ($imgs.length <= 0) {
			cqliving_dialog.error("请上传店铺图片");
			return;
		} else {
			//设置图片地址
			var $imgs = $(":hidden[name=images]");
			$imgs.val("");	//清空，避免重复数据
			$(".upload-imgs img").each(function() {
				if ($imgs.val()) {
					$imgs.val($imgs.val() + "," + $(this).attr("src"));
				} else {
					$imgs.val($(this).attr("src"));
				}
			});
			//设置封面图地址(第一张图片)
			$(":hidden[name=coverImg]").val($(".upload-imgs img:first").attr("src"));
		}
		//验证坐标
		if (!$("#shop_lat").val() || !$("#shop_lng").val()) {
			cqliving_dialog.error("请选择店铺坐标");
			return;
		}
		//验证简介
		if (!ue.getContent()) {
			cqliving_dialog.error("请输入商品简介");
			return;
		} else {
			$("input[name=content]").val(ue.getContent());
		}
		//处理价格中的千分符
		$("#price").val($("#price").val().replace(",", ""));
		//验证表单
		if (!jform.valid()) {
			return;
		}
		var params = jform.serializeArray();
		params.push({"name": "status", "value": type});
		url = $(":hidden[name=id]").val() ? "/module/shop/shop_info_update.html" : "/module/shop/shop_info_add.html";
		cq_ajax.ajaxOperate(url, jform, params, function(data, status) {
			if (data.code >= 0) {
				cqliving_dialog.success(data.message ? data.message : "保存成功", function() {
					if (jthis.attr("back_url")) {
						window.location.href = jthis.attr("back_url");
					}
				});
			} else {
				cqliving_dialog.error(data.message?data.message:"保存失败");
			}
		});
	}
	
	/** 初始化百度地图 */
	function initBaiduMap() {
		//默认坐标：重庆大礼堂
		var lat = !$("#shop_lat").val() ? 29.568295 : $("#shop_lat").val();
		var lng = !$("#shop_lng").val() ? 106.559123 : $("#shop_lng").val();
		map = new BMap.Map("baidu_map");          // 创建地图实例  
		var point = new BMap.Point(lng, lat);  // 创建点坐标  
		map.centerAndZoom(point, 12);                 // 初始化地图，设置中心点坐标和地图级别 
		map.addControl(new BMap.NavigationControl());	//添加平移缩放控件
		map.enableScrollWheelZoom();		//启用滚轮放大缩小
		map.disableDoubleClickZoom();	//禁用双击放大
		map.addEventListener("click", handleMapClick);	//地图单击事件
		
		//编辑时，标记坐标
		if ($("#shop_lat").val() && $("#shop_lng").val()) {
			var marker = new BMap.Marker(new BMap.Point($("#shop_lng").val(), $("#shop_lat").val()), {enableDragging: true, raiseOnDrag: true});        // 创建标注    
			marker.addEventListener("dragend", function(e) {	//拖拽事件
				$("#shop_lat").val(e.point.lat);
				$("#shop_lng").val(e.point.lng);
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
				$("#shop_lat").val(searchPoint.lat);
				$("#shop_lng").val(searchPoint.lng);
				map.centerAndZoom(searchPoint, 16);
				var marker = new BMap.Marker(searchPoint, {enableDragging: true, raiseOnDrag: true});        // 创建标注    
				marker.addEventListener("dragend", function(e) {	//拖拽事件
					$("#shop_lat").val(e.point.lat);
					$("#shop_lng").val(e.point.lng);
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
		$("#shop_lat").val(e.point.lat);
		$("#shop_lng").val(e.point.lng);
		var marker = new BMap.Marker(e.point, {enableDragging: true, raiseOnDrag: true});        // 创建标注    
		marker.addEventListener("dragend", function(e) {	//拖拽事件
			$("#shop_lat").val(e.point.lat);
			$("#shop_lng").val(e.point.lng);
		});
		map.addOverlay(marker);
	}
	
	/** 控制分类和区域下拉选项 */
	function handleCategoryAndRegion() {
		var typeId = $("#typeId").val();
		if (!typeId) {
			return;
		}
		//先清空
		$("#categoryId option").remove();
		$("#regionCode option").remove();
		$("#categoryId, #regionCode").val("");
		//加入对应的选项
		$("#categoryId").append($("#category_option option[typeid=" + typeId + "]").clone());
		$("#regionCode").append($("#region_option option[typeid=" + typeId + "]").clone());
		//编辑时，回显数据
		if ($("#categoryId_hd").val() && $("#regionCode_hd").val()) {
			$("#categoryId option[value=" + $("#categoryId_hd").val() + "]").attr("selected", "selected");
			$("#regionCode option[value=" + $("#regionCode_hd").val() + "]").attr("selected", "selected");
		}
	}
	
	/** 处理设为封面按钮 */
//	function handleButton(id) {
//		var $lis = $("#img_thum li");
//		var $li = $("#" + id);
//		var $buttonHide = "<button class=\"btn btn-info btn-sm none cover_btn\">设为封面</button>";
//		var $buttonShow = "<button class=\"btn btn-info btn-sm cover_btn\">设为封面</button>";
//		if ($lis.length == 1) {	//首张图片，默认封面
//			$li.find("img").after($buttonHide);
//			$li.addClass("cover-img");
//		} else {
//			$li.find("img").after($buttonShow);
//		}
//	}
	
	/** 设为封面 */
//	function setCover() {
//		$("#img_thum li").removeClass("cover-img");
//		$(this).closest("li").addClass("cover-img");
//		$("button.none").removeClass("none");
//		$(this).addClass("none");
//		return false;
//	}
	
	/** 删除图片 */
//	function removeImg() {
//		var $cover = $("#img_thum li.cover-img");
//		if (!$cover.is("li")) {	//封面被删除，设置第一张图为封面
//			var $lis = $("#img_thum li").not(".cover-img");
//			if ($lis.length > 0) {
//				$($lis.get(0)).css("border-color", "green").addClass("cover-img").find(".cover_btn").addClass("none");
//			}
//		}
//	}
	
	/** 表单验证 */
	function validForm() {
		$("#form1").validate({
            rules: {
                appId : {
                    required: true,
                    number:true
                },
                columnsId : {
                    required: true,
                    number:true
                },
                typeId : {
                    required: true
                },
                categoryId : {
                    required: true
                },
                name : {
                    required: true
                },
                lat : {
                    required: true
                },
                lng : {
                    required: true
                },
                regionCode : {
                    required: true
                },
                regionName : {
                    required: true
                },
                address : {
                    required: true
                },
                coverImg : {
                    required: true
                },
                telephone : {
                    required: true
                },
                status : {
                    required: true
                },
                description : {
                    required: true
                },
                content : {
                    required: true
                },
                replyCount : {
                    required: true,
                    number:true
                },
                price : {
                    required: true,
                    number:true
                },
                createTime : {
                    required: true,
                    date:true
                },
                creatorId : {
                    required: true,
                    number:true
                },
                creator : {
                    required: true
                },
                updateTime : {
                    required: true,
                    date:true
                },
                updatorId : {
                    required: true,
                    number:true
                },
                updator : {
                    required: true
                }
            },
            messages: {
                appId : {
                    required: ' ',
                    number:' '
                },
                columnsId : {
                    required: ' ',
                    number:' '
                },
                name : {
                    required: ' '
                },
                lat : {
                    required: ' '
                },
                lng : {
                    required: ' '
                },
                regionCode : {
                    required: ' '
                },
                regionName : {
                    required: ' '
                },
                address : {
                    required: ' '
                },
                coverImg : {
                    required: ' '
                },
                telephone : {
                    required: ' '
                },
                status : {
                    required: ' '
                },
                description : {
                    required: ' '
                },
                content : {
                    required: ' '
                },
                replyCount : {
                    required: ' ',
                    number:' '
                },
                price : {
                    required: ' ',
                    number:' '
                },
                createTime : {
                    required: ' ',
                    date:' '
                },
                creatorId : {
                    required: ' ',
                    number:' '
                },
                creator : {
                    required: ' '
                },
                updateTime : {
                    required: ' ',
                    date:' '
                },
                updatorId : {
                    required: ' ',
                    number:' '
                },
                updator : {
                    required: ' '
                }
            }
        });
	}
	
	/** 审核 */
	function auditWin(){
		var me = $(this),
			url = me.attr('url');
		cqliving_dialog.model_dialog({
			title: '审核',
			id: 'audit_model_win',
			content: '<textarea class="form-control" name="content" rows="5" maxlength="500" placeholder="请输入审核不通过的原因">'+$('#auditDesc').val()+'</textarea>',
			buttons: {
				aduit_pass: {
					callback: function (jthis,jModel,param) {
						var content = $(jModel).find('textarea[name=content]').val();
						doAudit(jthis, jModel, 'pass', content, url);
					}
				},
				aduit_reject: {
					callback: function (jthis,jModel,param) {
						var content = $(jModel).find('textarea[name=content]').val();
						if(content == ''){
							cqliving_dialog.error('请输入不通过的原因');
							return ;
						}
						doAudit(jthis, jModel, 'reject', content, url);
					}
				},
				submit: {
					label: '保存并通过',
					callback: function (jThis, jModel, param){
						var content = $(jModel).find('textarea[name=content]').val();
						$('#auditDesc').val(content)
//						submit({data: 4, content: content});
						$('#audit_online_btn').trigger('click');
						jModel.modal('hide');
					}
				}
			}
		});
	}
	/** 执行审核 */
	function doAudit(jthis, jModel, status, content, url){
		var myForm = $('#form1'),
			id = myForm.find('input[name=id]').val();
		cq_ajax.ajaxOperate(url, jthis, {id: id, content: content, status: status}, function(data, status) {
			if (data.code >= 0) {
				jModel.modal('hide');
				cqliving_dialog.success(data.message ? data.message : "操作成功", function (){
					$('#backButton').trigger('click');
				});
			} else {
				cqliving_dialog.error(data.message);
			}
		});
	}
	
	/** 根据地址定位地图 **/
	function pointByAddress(address){
		var lat = $("#shop_lat").val();
		var lng = $("#shop_lng").val();
		if(lat != '' && lng != ''){//如果经度和纬度为空根据地址定位
			return ;
		}
		//alert("预备解析")
        var myGeo = new BMap.Geocoder();
        // 将地址解析结果显示在地图上,并调整地图视野
        myGeo.getPoint(address, function(point){
            //alert("开始解析")
            if(address){
                if (point) {
                    map.clearOverlays();    //清除覆盖物
                    map.centerAndZoom(point, 16);
                    map.addOverlay(new BMap.Marker(point));
                    
                    $("#map_lat").val(point.lat);
                    $("#map_lng").val(point.lng); 
                    
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
	
});