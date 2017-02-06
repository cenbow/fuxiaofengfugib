define(["cloud.table.curd", "cloud.time.input", "cqliving_dialog", "cqliving_ajax", "cqliving_input", "common_treeview", "chosen"], 
		function(tableCurd, timeInput, cqliving_dialog, cq_ajax, cqInput, common_treeview) {
	return {
		init: function() {
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			cqInput.onlyNum();
			handClearSortNoBtn();
			
			//控制清空排序按钮的可见性
			$("#searchButton").click(handClearSortNoBtn);
			//初始化下拉多选控件
			$(".chosen-select").chosen({search_contains: true}); 
			$(".chosen-container-single").css("width", "100%");
			//覆盖公共的重置方法
			$("#resetButton").off("click").click(function() {
				var paramForm = $(this).parents("form");
				paramForm.find(":input").not(":button, :submit, :reset, [name=mid], [name=appId]").val("").removeAttr("checked").removeAttr("selected");
			});
			//发布
			$("#table_content_page").on("click", ".publish_btn", publish);
			//下线
			$("#table_content_page").on("click", ".offline_btn", offline);
			//批量下线
			$("#offline_batch_btn").click(offlineBatch);
			//清空排序
			$("#clear_sort_no_btn").click(clearSortNo);
			//修改排序
			$("#table_content_page").on("blur", ".only_num", modifySortNo);
			//移出专题对话框初始化
			$("#move_out_btn").click(moveOutInit);
			//移出专题确认
			$("#move_out_ok_btn").click(moveOut);
			//移出专题单选控件事件
			$("input[name=operateType]").click(function() {
				var type = $(this).val();
				if (type == "3") {
					$("#app_column_choose_div").show();
				} else {
					$("#app_column_choose_div").hide();
				}
			});
			//初始化栏目树
			initColumnTree();
			//推送对话框初始化
			$("#app_push_modal").on("show.bs.modal", pushInit);
			//确定推送
			$("#push_ok_btn").click(pushSave);
		}
	};
	
	/** 初始化栏目树 */
	function initColumnTree() {
		//tree  树形结构
		if (appColumns.data) {
			common_treeview.treeview("appcolumns_tree", appColumns.data, function(data) {
				$(":hidden[name=appColumnId]").val(data.id);
				$("input[name=columnsName]").val(data.name);
				//主动查询
//				$("#searchButton").click();
				$("#app_column_choose_div .dropdown-toggle").next().hide();
			});
		}
	}
	
	/** 控制清空排序按钮的可见性 */
	function handClearSortNoBtn() {
		var columnsId = $("#search_EQ_themeId").val();
		if (columnsId) {
			$("#clear_sort_no_btn").show();
		} else {
			$("#clear_sort_no_btn").hide();
		}
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
	
	/** 下线 */
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
	
	/** 批量下线 */
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
//			ids.push($(v).attr("icid"));
			ids.push($(v).attr("cid"));
		});
		cqliving_dialog.confirm("操作确认", "确定要下线吗？", function() {
			cq_ajax.ajaxOperate("/module/infoClassify/info_correlation_offline_batch.html", null, {"ids": ids}, function(data, status) {
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
	
	/** 清空排序 */
	function clearSortNo() {
		var ids = new Array();
		var $cbx = $("#table_content_page tbody :checkbox:checked").closest("td[cid]");
		//未选择任何记录
		if ($cbx.length <= 0) {
			cqliving_dialog.error("请选择要清空排序的新闻");
			return false;
		}
		$cbx.each(function(i, v) {
			ids.push($(v).attr("cid"));
		});
		cqliving_dialog.confirm("操作确认", "确定要清空排序吗？", function() {
			cq_ajax.ajaxOperate("/module/infoClassify/info_classify_clear_special_sort_no.html", null, {"ids": ids}, function(data, status) {
				if (data.code >= 0) {
					cqliving_dialog.success(data.message ? data.message : "操作成功");
					$("#searchButton").trigger("click");
				} else {
					cqliving_dialog.error(data.message);
				}
			});
		});
		return false;	//阻止事件冒泡
	}
	
	/** 修改排序 */
	function modifySortNo() {
		var id = $(this).closest("tr").find("td:first").attr("cid");
		var sortNo = $(this).val();
		var data = {
				"i": id,
				"s": sortNo
		};
		cq_ajax.ajaxOperate("/module/infoClassify/info_classify_modify_special_sort_no.html", null, data, function(data, status) {
			if (data.code >= 0) {
				cqliving_dialog.success(data.message ? data.message : "操作成功");
				$("#searchButton").trigger("click");
			} else {
				cqliving_dialog.error(data.message);
			}
		}, {"async": false});
	}
	
	/** 移出专题选择框 */
	function moveOutInit() {
		var ids = new Array();
		var cids = new Array();
		var $cbx = $("#table_content_page tbody :checkbox:checked").closest("td[icid]");
		//未选择任何记录
		if ($cbx.length <= 0) {
			cqliving_dialog.error("请选择要移出专题的新闻");
			return false;
		}
		$cbx.each(function(i, v) {
			ids.push($(v).attr("icid"));
			cids.push($(v).attr("cid"));
		});
		$("input[name=operateType]:first").click();
		$("#move_out_modal").modal("show");
		return false;
	}
	
	/** 移出专题确认 */
	function moveOut() {
		var type = $("input[name=operateType]:checked").val();
		if (type == "3") {
			//验证栏目选择
			var appColumnId = $("#appColumnId").val();
			if (!appColumnId) {
				cqliving_dialog.error("请选择栏目");
				return;
			}
		} 
		var ids = new Array();
		var cids = new Array();
		var $cbx = $("#table_content_page tbody :checkbox:checked").closest("td[icid][cid]");
		$cbx.each(function(i, v) {
			ids.push($(v).attr("icid"));
			cids.push($(v).attr("cid"));
		});
		var data = {
				"t": type,
				"i": ids,
				"c": cids,
				"a": appColumnId
		};
		cq_ajax.ajaxOperate("/module/infoClassify/info_classify_move_out.html", null, data, function(data, status) {
			if (data.code >= 0) {
				$("#move_out_modal").modal("hide");
				$("#searchButton").trigger("click");
				cqliving_dialog.success(data.message ? data.message : "操作成功");
			} else {
				cqliving_dialog.error(data.message);
			}
		}, {"async": false});
	}
	
	/** 打开推送选择框 */
	function pushInit(e) {
		//设置资讯id
		var id = $(e.relatedTarget).closest("tr").find("td:first").attr("icid");
		$("#icid").val(id);
		var title = $(e.relatedTarget).closest("tr").find("td:first").attr("synopsis");	//推送的标题使用新闻的摘要 By Tangtao 2016-11-23
		$("#push_title").val(title);
	}
	
	/** 确定推送 */
	function pushSave() {
		var id = $("#icid").val();
		var data = {
				"i": id,
				"t": $("#push_title").val()
		};
		cq_ajax.ajaxOperate("/module/infoClassify/info_classify_push.html", null, data, function(data, status) {
			if (data.code == 0) {
				cqliving_dialog.success(data.message ? data.message : "推送成功");
				$("#searchButton").trigger("click");
			} else {
				cqliving_dialog.error(data.message);
			}
		}, {"async": false});
		$("#app_push_modal").modal("hide");
	}
	
});