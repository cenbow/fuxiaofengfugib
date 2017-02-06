define(["cloud.table.curd", "cqliving_dialog", "cqliving_ajax", "chosen"], function(tableCurd, cqliving_dialog, cq_ajax) {
	return {
		init: function() {
			tableCurd.initTableCrud();
			
			//初始化下拉多选控件
			$(".chosen-select").chosen({search_contains: true}); 
			$(".chosen-container-single").css("width", "100%");
			//下线
			$("#table_content_page").on("click", ".offline_btn", offline);
			$("#table_content_page").on("click", ".publish_btn", publish);
			//批量删除
			$("#del_batch_btn").click(delBatch);
			//批量下线
			$("#offline_batch_btn").click(offlineBatch);
			//覆盖公共的重置方法
			$("#resetButton").off("click").click(function() {
				$("#search_LIKE_title").val("");
			});
		}
	};
	
	//下线
	function offline() {
		var jThis = $(this);
		var url = jThis.attr("url");
		cqliving_dialog.confirm("操作确认", "确定要下线吗？", function() {
			cq_ajax.ajaxOperate(url, jThis, {}, function(data, status) {
				if (data.code >= 0) {
					cqliving_dialog.success(data.message ? data.message : "操作成功");
					$("#searchButton").trigger("click");
				} else {
					cqliving_dialog.error(data.message);
				}
			});
		});
	}
	
	/** 发布 */
	function publish() {
		var jThis = $(this);
		var url = jThis.attr("url");
		cqliving_dialog.confirm("操作确认", "确定要发布吗？", function() {
			cq_ajax.ajaxOperate(url, jThis, {}, function(data, status) {
				if (data.code >= 0) {
					cqliving_dialog.success(data.message ? data.message : "操作成功");
					$("#searchButton").trigger("click");
				} else {
					cqliving_dialog.error(data.message);
				}
			});
		});
	}
	
	//批量删除
	function delBatch() {
		var ids = new Array();
		var $cbx = $("#table_content_page tbody :checkbox:checked").closest("td[icid]");
		//未选择任何记录
		if ($cbx.length <= 0) {
			cqliving_dialog.error("请选择要删除的新闻");
			return false;
		}
		$cbx.each(function(i, v) {
			ids.push($(v).attr("icid"));
		});
		cqliving_dialog.confirm("操作确认", "确定要删除吗？", function() {
			cq_ajax.ajaxOperate("/module/infoClassify/info_classify_del_batch.html", null, {"ids": ids}, function(data, status) {
				if (data.code >= 0) {
					cqliving_dialog.success(data.message ? data.message : "操作成功");
					$("#searchButton").trigger("click");
				} else {
					cqliving_dialog.error(data.message);
				}
			});
		});
		return false;
	}
	
	//批量下线
	function offlineBatch() {
		var ids = new Array();
		var $cbx = $("#table_content_page tbody :checkbox:checked").closest("td[icid]");
		//未选择任何记录
		if ($cbx.length <= 0) {
			cqliving_dialog.error("请选择要下线的新闻");
			return false;
		}
		$cbx.each(function(i, v) {
			var status = parseInt($(v).attr("ss"));
			ids.push($(v).attr("icid"));
		});
		cqliving_dialog.confirm("操作确认", "确定要下线吗？", function() {
			cq_ajax.ajaxOperate("/module/infoClassify/info_classify_offline_batch.html", null, {"ids": ids}, function(data, status) {
				if (data.code >= 0) {
					cqliving_dialog.success(data.message ? data.message : "操作成功");
					$("#searchButton").trigger("click");
				} else {
					cqliving_dialog.error(data.message);
				}
			});
		});
		return false;
	}
	
});