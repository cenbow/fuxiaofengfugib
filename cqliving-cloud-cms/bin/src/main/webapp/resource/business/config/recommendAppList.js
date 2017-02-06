define(["cloud.table.curd", "cloud.time.input", "cqliving_dialog", "cqliving_ajax", "cqliving_input", "chosen"], 
		function(tableCurd, timeInput, cqDialog, cq_ajax, cqInput) {
	return {
		init: function() {
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			cqInput.onlyNum();
			
			//初始化下拉多选控件
			$(".chosen-select").chosen({search_contains: true}); 
			$(".chosen-container-single").css("width", "100%");
			//覆盖公共的重置方法
			$("#resetButton").off("click").click(function() {
				//清空状态查询条件
				$("#search_EQ_status").val("");
			});
			//上线
			$("#table_content_page").on("click", ".online_btn", online);
			//下线
			$("#table_content_page").on("click", ".offline_btn", offline);
			//修改排序
			$("#table_content_page").on("blur", ".only_num", modifySortNo);
		}
	};
	
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
	
	/** 修改排序 */
	function modifySortNo() {
		var id = $(this).closest("tr").find("td:first").attr("id");
		var sortNo = $(this).val();
		var data = {
				"i": id,
				"s": sortNo
		};
		cq_ajax.ajaxOperate("/module/recommend_app/modify_sort_no.html", null, data, function(data, status) {
			if (data.code >= 0) {
				cqDialog.success(data.message ? data.message : "操作成功");
				$("#searchButton").trigger("click");
			} else {
				cqDialog.error(data.message);
			}
		}, {"async": false});
	}
	
});