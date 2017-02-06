define(["cloud.table.curd", "cloud.time.input", "cqliving_dialog", "cqliving_ajax", "common_treeview", "chosen", "ace_ele"], 
		function(tableCurd, timeInput, cqDialog, cq_ajax, common_treeview) {
	return {
		init: function() {
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			
			//初始化下拉多选控件
			$(".chosen-select").chosen({search_contains: true}); 
			$(".chosen-container-single").css("width", "100%");
			
			//发布到APP
			$("#publish_ok_btn").click(publish);
			//发布到APP对话框初始化
			$("#app_column_modal").on("show.bs.modal", publishInit);
			//忽略
			$("#table_content_page").on("click", ".ignore_btn", ignore);
			//初始化栏目树
			initColumnTree();
		}
	};
	
	/** 初始化栏目树 */
	function initColumnTree() {
		//ace tree  树形结构
		if (appColumns.data) {
			common_treeview.treeview("appcolumns_tree",appColumns.data,function(data){
				$(":hidden[name=appColumnId]").val(data.id);
				$("input[name=columnsName]").val(data.name);
				$("body").click();
			});
		}
	}
	
	/** 忽略 */
	function ignore() {
		var jThis = $(this);
		var url = jThis.attr("url");
		cqDialog.confirm("操作确认", "确定要忽略吗？", function() {
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
	
	/** 打开发布到 APP 选择框 */
	function publishInit(e) {
		//设置资讯 id 和推荐 id
		var id = $(e.relatedTarget).closest("tr").find("td:first").attr("icid");
		$("#icid").val(id);
		var commid = $(e.relatedTarget).closest("tr").find("td:first").attr("commid");
		$("#commid").val(commid);
	}
	
	/** 发布到 APP */
	function publish() {
		var appColumnId = $("#appColumnId").val();
		var id = $("#icid").val();
		var commid = $("#commid").val();
		if (!appColumnId) {
			cqDialog.error("请选择栏目");
			return;
		}
		var data = {
				"i": id,
				"c": commid,
				"a": appColumnId
		};
		cq_ajax.ajaxOperate("/module/infoClassify/info_classify_publish_to_column.html", null, data, function(data, status) {
			if (data.code >= 0) {
				$("#app_column_modal").modal("hide");
				$("#searchButton").trigger("click");
				cqDialog.success(data.message ? data.message : "操作成功");
			} else {
				cqDialog.error(data.message);
			}
		}, {"async": false});
	}
	
});