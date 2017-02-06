define(["cloud.table.curd", "cloud.time.input", "cqliving_dialog", "cqliving_ajax", "common_treeview", "ace_ele", "chosen"], function(tableCurd, timeInput, cqliving_dialog, cq_ajax,common_treeview) {
	return {
		init: function() {
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			
			//初始化下拉多选控件
			$(".chosen-select").chosen({search_contains: true});
			//覆盖公共的重置方法
			$("#resetButton").off("click").click(function() {
				var paramForm = $(this).parents("form");
				paramForm.find(":input").not(":button, :submit, :reset, #info_classify_appid").val("").removeAttr("checked").removeAttr("selected");
			});
			//发布
			$("#table_content_page").on("click", ".publish_btn", publish);
			//下线
			$("#table_content_page").on("click", ".offline_btn", offline);
			$("#table_content_page").on("click", ".preview_btn", preview);
			//批量发布
			$("#publish_batch_btn").click(publishBatch);
			//批量删除
			$("#del_batch_btn").click(delBatch);
			//批量下线
			$("#offline_batch_btn").click(offlineBatch);
			//初始化栏目树
			initColumnTree();
			//查询条件切换APP
			$("select[id=info_classify_appid]").change(function() {
				//重新加载栏目树
				var appId = $(this).val();
				cq_ajax.ajaxOperate("/module/infoClassify/common/getColumns.html", null, {"appId": appId}, function(data, status) {
					var obj = JSON.parse(data.data);
					if (data.code >= 0 && obj) {
						//清空原来的选择
						$(":hidden[name=search_EQ_columnsId]").val("");
						$("input[name=columnsName]").val("");
						common_treeview.treeview("appcolumns_tree", obj.data, function(data) {
							$(":hidden[name=search_EQ_columnsId]").val(data.id);
							$("input[name=columnsName]").val(data.name);
							//主动查询
							$("#searchButton").click();
						});
						$("#searchButton").click();
					} else {
						cqDialog.error(data.message);
					}
				});
			});
		}
	};
	
	/** 预览 */
	function preview() {
		var jThis = $(this);
		var url = jThis.attr("url");
		cq_ajax.ajaxOperate(url, "body", {}, function(data, status) {
			$("body").append(data);
		}, {dataType: "html"});
	}
	
	/** 初始化栏目树 */
	function initColumnTree() {
		//ace tree  树形结构
		if (appColumns.data) {
			common_treeview.treeview("appcolumns_tree",appColumns.data,function(data){
				$(":hidden[name=search_EQ_columnsId]").val(data.id);
				$("input[name=columnsName]").val(data.name);
				$("body").click();
			});
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
	
	//批量发布
	function publishBatch() {
		var ids = new Array();
		var $cbx = $("#table_content_page tbody :checkbox:checked").closest("td[icid]");
		//未选择任何记录
		if ($cbx.length <= 0) {
			cqliving_dialog.error("请选择要发布的新闻");
			return false;
		}
		$cbx.each(function(i, v) {
			var status = parseInt($(v).attr("ss"));
			ids.push($(v).attr("icid"));
		});
		cqliving_dialog.confirm("操作确认", "确定要发布吗？", function() {
			cq_ajax.ajaxOperate("/module/infoClassify/info_classify_publish_batch.html", null, {"ids": ids}, function(data, status) {
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