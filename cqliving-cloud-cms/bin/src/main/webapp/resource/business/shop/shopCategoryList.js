define(["cloud.table.curd", "cloud.time.input", "cqliving_dialog", "cqliving_ajax", "cqliving_input", "chosen"], 
		function(tableCurd, timeInput, cqDialog, cq_ajax, cqInput) {
	return {
		init: function() {
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			cqInput.onlyNum();
			handClearSortNoBtn();
			
			//初始化下拉多选控件
			$(".chosen-select").chosen({search_contains: true}); 
			$(".chosen-container-single").css("width", "100%");
			//控制清空排序按钮的可见性
			$("#searchButton").click(handClearSortNoBtn);
			//清空排序
			$("#clear_sort_no_btn").click(clearSortNo);
			//修改排序
			$("#table_content_page").on("blur", ".only_num", modifySortNo);
			//处理类型下拉数据
			handleTypeData();
			$("#shop_category_appid").change(handleTypeData);
			//覆盖公共的重置方法
			$("#resetButton").off("click").click(function() {
				$("#search_EQ_typeId, #search_LIKE_name").val("");
			});
		}
	};
	
	/** 处理类型下拉数据 */
	function handleTypeData() {
		//重置类型下拉框
		$("#search_EQ_typeId").val("");
		var appId = $("#shop_category_appid").val();	
		$("option[appid]").hide();
		$("option[appid=" + appId + "]").show();
	}
	
	/** 控制清空排序按钮的可见性 */
	function handClearSortNoBtn() {
		var typeId = $("#search_EQ_typeId").val();
		if (typeId) {
			$("#clear_sort_no_btn").show();
		} else {
			$("#clear_sort_no_btn").hide();
		}
	}
	
	/** 清空排序 */
	function clearSortNo() {
		var ids = new Array();
		var $cbx = $("#table_content_page tbody :checkbox:checked").closest("td[id]");
		//未选择任何记录
		if ($cbx.length <= 0) {
			cqDialog.error("请选择要清空排序的记录");
			return false;
		}
		$cbx.each(function(i, v) {
			ids.push($(v).attr("id"));
		});
		cqDialog.confirm("操作确认", "确定要清空排序吗？", function() {
			cq_ajax.ajaxOperate("/module/shop_category/clear_order_batch.html", null, {"ids": ids}, function(data, status) {
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
		cq_ajax.ajaxOperate("/module/shop_category/modify_sort_no.html", null, data, function(data, status) {
			if (data.code >= 0) {
				cqDialog.success(data.message ? data.message : "操作成功");
				$("#searchButton").trigger("click");
			} else {
				cqDialog.error(data.message);
			}
		}, {"async": false});
	}
	
});