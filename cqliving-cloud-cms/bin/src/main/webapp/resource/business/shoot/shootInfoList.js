define(["cloud.table.curd", "cloud.time.input", "cqliving_dialog", "cqliving_ajax"], function(tableCurd, timeInput, cqDialog, cq_ajax) {
	return {
		init: function() {
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			
			//批量下线
			$("#offlineBatchButton").click(offlineBatch);
			//批量上线
			$("#onlineBatchButton").click(onlineBatch);
			//上线
			$("#table_content_page").on("click", ".online_btn", online);
			//下线
			$("#table_content_page").on("click", ".offline_btn", offline);
		}
	};
	
	/** 批量下线 */
	function offlineBatch() {
		var ids = new Array();
		var $cbx = $("#table_content_page tbody :checkbox:checked").closest("td[id]");
		//未选择任何记录
		if ($cbx.length <= 0) {
			cqDialog.error("请选择要下线的记录");
			return false;
		}
		$cbx.each(function(i, v) {
			ids.push($(v).attr("id"));
		});
		cqDialog.confirm("操作确认", "确定要下线吗？", function() {
			cq_ajax.ajaxOperate("/module/shoot/shoot_info_offline_batch.html", null, {"ids": ids}, function(data, status) {
				if (data.code >= 0) {
					cqDialog.success(data.message ? data.message : "操作成功");
					$("#searchButton").trigger("click");
				} else {
					cqDialog.error(data.message);
				}
			});
		});
		return false;
	}
	
	/** 批量上线 */
	function onlineBatch() {
		var ids = new Array();
		var $cbx = $("#table_content_page tbody :checkbox:checked").closest("td[id]");
		//未选择任何记录
		if ($cbx.length <= 0) {
			cqDialog.error("请选择要上线的记录");
			return false;
		}
		$cbx.each(function(i, v) {
			ids.push($(v).attr("id"));
		});
		cqDialog.confirm("操作确认", "确定要上线吗？", function() {
			cq_ajax.ajaxOperate("/module/shoot/shoot_info_online_batch.html", null, {"ids": ids}, function(data, status) {
				if (data.code >= 0) {
					cqDialog.success(data.message ? data.message : "操作成功");
					$("#searchButton").trigger("click");
				} else {
					cqDialog.error(data.message);
				}
			});
		});
		return false;
	}
	
	/** 上线 */
	function online() {
		var jThis = $(this);
		var url = jThis.attr("url");
		cqDialog.confirm("操作确认", "确定要上线吗？", function() {
			cq_ajax.ajaxOperate(url, jThis, {}, function(data, status) {
				if (data.code >= 0) {
					cqDialog.success(data.message ? data.message : "操作成功");
					$("#searchButton").trigger("click");
				} else {
					cqDialog.error(data.message);
				}
			});
		});
	}
	
	/** 下线 */
	function offline() {
		var jThis = $(this);
		var url = jThis.attr("url");
		cqDialog.confirm("操作确认", "确定要下线吗？", function() {
			cq_ajax.ajaxOperate(url, jThis, {}, function(data, status) {
				if (data.code >= 0) {
					cqDialog.success(data.message ? data.message : "操作成功");
					$("#searchButton").trigger("click");
				} else {
					cqDialog.error(data.message);
				}
			});
		});
	}
	
});