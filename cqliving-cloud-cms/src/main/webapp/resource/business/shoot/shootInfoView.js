define(["cqliving_dialog", "cqliving_ajax", "common_colorbox"], function(cqDialog, cq_ajax, colorbox) {
	return {
		init: function() {
			colorbox.init();
			//保存随手拍状态
			$("#save_status_btn").click(save);
		}
	};
	
	/** 保存 */
	function save() {
		cqDialog.confirm("操作确认", "确定保存吗？", function() {
			cq_ajax.ajaxOperate("/module/shoot/shoot_info_view_save.html", null, $("#shoot_info_form").serialize(), function(data, status) {
				if (data.code >= 0) {
					cqDialog.success(data.message ? data.message : "保存成功", function() {
						window.location.href = "/module/shoot/shoot_info_list.html";
					});
				} else {
					cqDialog.error(data.message);
				}
			});
		});
	}
	
});