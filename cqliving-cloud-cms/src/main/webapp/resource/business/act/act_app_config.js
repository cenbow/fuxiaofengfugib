define(["cqliving_dialog", "cqliving_ajax", "chosen"], function(cqDialog, cq_ajax) {
	return {
		init: function() {
			//初始化下拉多选控件
			$(".chosen-select").chosen({search_contains: true}); 
			$(".chosen-container-single").css("width", "100%");
			
			//保存配置
			$("#submitBtn").click(configSave);
			
			//切换APP
			$("#appId").change(function() {
				var appId = $(this).val();
				location.href = "/module/act/config.html?appId=" + appId;
			});
		}
	};
	
	/** 保存配置 */
	function configSave() {
		cqDialog.confirm("操作确认", "确定保存设置吗？", function() {
			cq_ajax.ajaxOperate("/module/act/config_save.html", null, $("#myForm").serialize(), function(data, status) {
				if (data.code >= 0) {
					cqDialog.success(data.message ? data.message : "保存成功");
				} else {
					cqDialog.error(data.message);
				}
			});
		});
	}
	
});