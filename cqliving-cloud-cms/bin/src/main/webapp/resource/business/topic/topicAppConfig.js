define(["cqliving_dialog", "cqliving_ajax", "myUploader", "common_colorbox", "chosen"], function(cqDialog, cq_ajax, myUploader, colorbox) {
	
	var listViewUploader = ""; 
	var uploaderOptions = {
			url: "/common/upload.html",
			containerId: "img_upload",
			thumbContainerId: "img_thum",
			success: function(file, response) {
				var imgPath = imgUrl + response.data.filePath;
				//将图片的引用修改为上传后的图片路径
				$("#img_thum ul li img").last().attr("src", imgPath);
			},
			error: function(file, reason) {
				cqliving_dialog.error(reason);
			},
			isSingle: true,
			destroy: true
	};
	
	return {
		init: function() {
			colorbox.init();
			uploaderOptions.fileUrlPath = imgUrl;
			listViewUploader = myUploader.init(uploaderOptions, listViewUploader);
			
			//初始化下拉多选控件
			$(".chosen-select").chosen({search_contains: true}); 
			$(".chosen-container-single").css("width", "100%");
			
			//保存配置
			$("#submitBtn").click(configSave);
			
			//切换APP
			$("#appId").change(function() {
				var appId = $(this).val();
				location.href = "/module/topic/topic_app_config.html?appId=" + appId;
			});
		}
	};
	
	/** 保存配置 */
	function configSave() {
		var $imgs = $("#img_thum img");
		//验证图片
		if ($imgs.length <= 0) {
			cqDialog.error("请上传话题默认图片");
			return;
		}
		$(":hidden[name=defaultImage]").val($imgs[0].src);
		cqDialog.confirm("操作确认", "确定保存设置吗？", function() {
			cq_ajax.ajaxOperate("/module/topic/topic_app_config_save.html", null, $("#myForm").serialize(), function(data, status) {
				if (data.code >= 0) {
					cqDialog.success(data.message ? data.message : "保存成功");
				} else {
					cqDialog.error(data.message);
				}
			});
		});
	}
	
});