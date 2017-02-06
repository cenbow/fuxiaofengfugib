define(["cloud.table.curd", "cloud.time.input", "cqliving_dialog", "cqliving_ajax", "cqliving_input","common_treeview","chosen", "ace_ele"], 
		function(tableCurd, timeInput, cqDialog, cq_ajax, cqInput, common_treeview) {
	
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
			//覆盖公共的重置方法
			$("#resetButton").off("click").click(function() {
				var paramForm = $(this).parents("form");
				paramForm.find(":input").not(":button, :submit, :reset, #info_classify_appid").val("").removeAttr("checked").removeAttr("selected");
			});
			$('#chosen-multiple-style').on('click', function(e) {
				var target = $(e.target).find('input[type=radio]');
				var which = parseInt(target.val());
				if (which == 2) {
					$('#form-field-select-4').addClass('tag-input-style');
				} else {
					$('#form-field-select-4').removeClass('tag-input-style');
				}
			});
			$(".chosen-container-multi").css("width", "100%");
			
			//预览
			$("#table_content_page").on("click", ".preview_btn", preview);
			//发布
			$("#table_content_page").on("click", ".publish_btn", publish);
			//下线
			$("#table_content_page").on("click", ".offline_btn", offline);
			//批量发布
			$("#publish_batch_btn").click(publishBatch);
			//批量下线
			$("#offline_batch_btn").click(offlineBatch);
			//批量删除
			$("#del_batch_btn").click(delBatch);
			//清空排序
			$("#clear_sort_no_btn").click(clearSortNo);
			//修改排序
			$("#table_content_page").on("blur", ".only_num", modifySortNo);
			//推荐到APP对话框初始化
			$("#app_modal").on("show.bs.modal", recommendInit);
			//推荐到APP
			$("#recommend_ok_btn").click(recommendSave);
			//初始化栏目树
			initColumnTree();
			//推送对话框初始化
			$("#app_push_modal").on("show.bs.modal", pushInit);
			//移动新闻
			$("#change_app_column").on("show.bs.modal",changeClassifyColumn);
			//取消选中
			$("#table_content_page").on("click","table tbody input[name=infoid]",checkboxClick);
			//全选选中
			$("#table_content_page").on("click","table thead input[type=checkbox]",chooseAll);
			//确认移动
			$("#change_ok_btn").bind("click",moveOk);
			//确定推送
			$("#push_ok_btn").click(pushSave);
			//查询条件切换APP
			$("select[id=info_classify_appid]").change(function() {
				//重新加载栏目树
				var appId = $(this).val();
				cq_ajax.ajaxOperate("/module/infoClassify/common/getColumns.html", null, {"appId": appId}, function(data, status) {
					var obj = appColumns = JSON.parse(data.data);
					if (data.code >= 0 && obj) {
						//清空原来的选择
						$(":hidden[name=search_EQ_columnsId]").val("");
						$("input[name=columnsName]").val("");
						common_treeview.treeview("appcolumns_tree", obj.data, function(data) {
							$(":hidden[name=search_EQ_columnsId]").val(data.id);
							$("input[name=columnsName]").val(data.name);
							$(":hidden[name=columnId]").val(data.id);
							$("input[name=columnName]").val(data.name);
							//主动查询
							$("#searchButton").click();
							cacheInfoClassifyIds = [];
						});
						$("#searchButton").click();
						cacheInfoClassifyIds = [];
					} else {
						cqDialog.error(data.message);
					}
				});
			});
			
			$(document).on('click', '.recommendToHomeButton', recommendToHomeFn);
		}
	};
	
	/** 控制清空排序按钮的可见性 */
	function handClearSortNoBtn() {
		var columnsId = $(":hidden[name=search_EQ_columnsId]").val();
		if (columnsId) {
			$("#clear_sort_no_btn").show();
		} else {
			$("#clear_sort_no_btn").hide();
		}
	}
	
	/** 初始化栏目树 */
	function initColumnTree() {
		//tree  树形结构
		if (appColumns.data) {
			common_treeview.treeview("appcolumns_tree", appColumns.data, function(data) {
				$(":hidden[name=search_EQ_columnsId]").val(data.id);
				$("input[name=columnsName]").val(data.name);
				$(":hidden[name=columnId]").val(data.id);
				$("input[name=columnName]").val(data.name);
				//主动查询
				$("#searchButton").click();
				cacheInfoClassifyIds = [];
			});
		}
	}
	
	/** 预览 */
	function preview() {
		var jThis = $(this);
		var url = jThis.attr("url");
		cq_ajax.ajaxOperate(url, "body", {}, function(data, status) {
			$("body").append(data);
		}, {dataType: "html"});
	}
	
	/** 发布 */
	function publish() {
		var jThis = $(this);
		var url = jThis.attr("url");
		cqDialog.confirm("操作确认", "确定要发布吗？", function() {
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
	
	//下线
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
	
	/** 批量发布 */
	function publishBatch() {
		var ids = new Array();
		var $cbx = $("#table_content_page tbody :checkbox:checked").closest("td[icid]");
		//未选择任何记录
		if ($cbx.length <= 0) {
			cqDialog.error("请选择要发布的新闻");
			return false;
		}
		$cbx.each(function(i, v) {
			ids.push($(v).attr("icid"));
		});
		cqDialog.confirm("操作确认", "确定要发布吗？", function() {
			cq_ajax.ajaxOperate("/module/infoClassify/info_classify_publish_batch.html", null, {"ids": ids}, function(data, status) {
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
	
	/** 批量下线 */
	function offlineBatch() {
		var ids = new Array();
		var $cbx = $("#table_content_page tbody :checkbox:checked").closest("td[icid]");
		//未选择任何记录
		if ($cbx.length <= 0) {
			cqDialog.error("请选择要下线的新闻");
			return false;
		}
		$cbx.each(function(i, v) {
			ids.push($(v).attr("icid"));
		});
		cqDialog.confirm("操作确认", "确定要下线吗？", function() {
			cq_ajax.ajaxOperate("/module/infoClassify/info_classify_offline_batch.html", null, {"ids": ids}, function(data, status) {
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
	
	/** 清空排序 */
	function clearSortNo() {
		var ids = new Array();
		var $cbx = $("#table_content_page tbody :checkbox:checked").closest("td[icid]");
		//未选择任何记录
		if ($cbx.length <= 0) {
			cqDialog.error("请选择要清空排序的新闻");
			return false;
		}
		$cbx.each(function(i, v) {
			ids.push($(v).attr("icid"));
		});
		cqDialog.confirm("操作确认", "确定要清空排序吗？", function() {
			cq_ajax.ajaxOperate("/module/infoClassify/info_classify_clear_sort_no.html", null, {"ids": ids}, function(data, status) {
				if (data.code >= 0) {
					cqDialog.success(data.message ? data.message : "操作成功");
					$("#searchButton").trigger("click");
				} else {
					cqDialog.error(data.message);
				}
			});
		});
		return false;	//阻止事件冒泡
	}
	
	/** 修改排序 */
	function modifySortNo() {
		var id = $(this).closest("tr").find("td:first").attr("icid");
		var sortNo = $(this).val();
		var data = {
				"i": id,
				"s": sortNo
		};
		cq_ajax.ajaxOperate("/module/infoClassify/info_classify_modify_sort_no.html", null, data, function(data, status) {
			if (data.code >= 0) {
				cqDialog.success(data.message ? data.message : "操作成功");
				$("#searchButton").trigger("click");
			} else {
				cqDialog.error(data.message);
			}
		}, {"async": false});
	}
	
	/** 打开推荐 APP 选择框 */
	function recommendInit(e) {
		//设置资讯id
		var id = $(e.relatedTarget).closest("tr").find("td:first").attr("icid");
		$("#icid").val(id);
		//清空已选择的app
		$(".search-choice-close").click();
		//控制不能推荐到所属App
		//跪了，控制不了
	}
	
	/** 打开推送选择框 */
	function pushInit(e) {
		//设置资讯id
		var id = $(e.relatedTarget).closest("tr").find("td:first").attr("icid");
		$("#icid").val(id);
		//设置标题和摘要
//		var title = $(e.relatedTarget).closest("tr").find("td:eq(2)").text();
		var title = $(e.relatedTarget).closest("tr").find("td:first").attr("synopsis");	//推送的标题使用新闻的摘要 By Tangtao 2016-11-23
		$("#push_title").val(title);
//		$("#push_summary").val("点击查看详情");	//只填标题，不需要摘要 By Tangtao 2016-11-23
	}
	
	/** 推荐到 APP */
	function recommendSave() {
		var appIds = $("#recommendAppId").val();
		var id = $("#icid").val();
		if (!appIds) {
			cqDialog.error("请选择App");
			return;
		}
		var data = {
				"i": id,
				"a": appIds
		};
		cq_ajax.ajaxOperate("/module/infoClassify/info_classify_recommend.html", null, data, function(data, status) {
			if (data.code >= 0) {
				$("#app_modal").modal("hide");
				cqDialog.success(data.message ? data.message : "推荐成功");
				$("#searchButton").trigger("click");
			} else {
				cqDialog.error(data.message);
			}
		}, {"async": false});
	}
	
	/** 确定推送 */
	function pushSave() {
		var id = $("#icid").val();
		var data = {
				"i": id,
				"t": $("#push_title").val()
//				"s": $("#push_summary").val()	//只填标题，不需要摘要 By Tangtao 2016-11-23
		};
		cq_ajax.ajaxOperate("/module/infoClassify/info_classify_push.html", null, data, function(data, status) {
			if (data.code == 0) {
				cqDialog.success(data.message ? data.message : "推送成功");
				$("#searchButton").trigger("click");
			} else {
				cqDialog.error(data.message);
			}
		}, {"async": false});
		$("#app_push_modal").modal("hide");
	}
	
	//移动新闻
	function changeClassifyColumn(){
		if (appColumns.data) {
			var _appColumns = $.extend(true,{},appColumns);
			_appColumns.data.splice(0,1);
			common_treeview.treeview("app_column_tree", _appColumns.data, function(data) {
				$(":hidden[name=columnId]").val(data.id);
				$("input[name=columnName]").val(data.name);
			});
		}
	}
	
	//全选的选中与取消
	function chooseAll(){
		var $this = $(this);
		var isChecked = $this.is(":checked");
		var infoIds = [];
		$this.closest("table").find("tbody input[name=infoid]").each(function(i,n){
			var $n = $(n);
			infoIds.push(parseInt($n.val(),10));
        });
		if(isChecked){
			if(infoIds.length>=1){
				for(var i=0,k=infoIds.length;i<k;i++){
					cacheInfoClassifyIds.push(infoIds[i]);
				}
			}
			return;
		}
		if(cacheInfoClassifyIds && cacheInfoClassifyIds.length>=1){
			for(var i=0;i<cacheInfoClassifyIds.length;i++){
				var cacheInfoId = cacheInfoClassifyIds[i];
				if(infoIds.length>=1){
					for(var m=0,n=infoIds.length;m<n;m++){
					    var infoId = infoIds[m];
						if(infoId == cacheInfoId){
							cacheInfoClassifyIds.splice(i,1);
							i--;
							break;
						}
					}
				}
			}
		}
	}
	
	//选中与取消选中
	function checkboxClick(){
		var $this = $(this);
		var isChecked = $this.is(":checked");
		var infoId = parseInt($this.val(),10);
		if(isChecked){
			cacheInfoClassifyIds.push(infoId);
			return;
		}
		if(cacheInfoClassifyIds && cacheInfoClassifyIds.length>=1){
			for(var i=0;i<cacheInfoClassifyIds.length;i++){
				var cacheInfoId = cacheInfoClassifyIds[i];
				if(infoId == cacheInfoId){
					cacheInfoClassifyIds.splice(i,1);
					break;
				}
			}
		}
	}
	//确认移动
	function moveOk(){
		var $this = $(this);
		var columnId = $(":hidden[name=columnId]").val();
		var columnName = $("input[name=columnName]").val();
		if(!columnId){
			cqDialog.error("请选择要移入的栏目");
			return;
		}
		if(cacheInfoClassifyIds.length<=0){
			cqDialog.error("请选择要移动的新闻");
			return;
		}
		cqDialog.confirm("提示","确认将选择的"+cacheInfoClassifyIds.length+"条新闻移动到"+columnName+"栏目中么？",function(){
			var ids = uniqueArr(cacheInfoClassifyIds);
			var url="/module/infoClassify/change_classify_appcolumn.html";
			var param={
					infoClassifyIds:ids,
					appColumnsId:columnId
			};
			$("#change_app_column").modal("hide");
			cq_ajax.ajaxOperate(url,"#app_column_tree",param,function(data,status){
				if(data.code>=0){
					cacheInfoClassifyIds = [];
					cqDialog.success("移动栏目成功");
				}else{
					cqDialog.error(data.message ? data.message : "移动栏目失败");
				}
				$("#searchButton").trigger("click");
			});
		});
	}
	
	//数组去重
	function uniqueArr(arr) {
		var n = {},r=[];
		for(var i = 0; i < arr.length; i++) {
			if (!n[arr[i]]) {
				n[arr[i]] = true;
				r.push(arr[i]);
			}
		}
		return r;
	}
	
	/**
	 * 推荐到首页
	 */
	function recommendToHomeFn(){
		var me = $(this),
			url = me.attr('url');
		cqDialog.confirm('确认信息', '确定要推荐到首页吗？', function(){
			cq_ajax.ajaxOperate(url, me, {}, function(data){
				if(data.code >= 0){
					cqDialog.success('保存成功。', function(){
						$('#searchButton').click();
					});
				}else{
					cqDialog.error(data.message ? data.message : '推荐到首页失败');
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
			cqDialog.error("请选择要删除的新闻");
			return false;
		}
		$cbx.each(function(i, v) {
			ids.push($(v).attr("icid"));
		});
		cqDialog.confirm("操作确认", "确定要删除吗？", function() {
			cq_ajax.ajaxOperate("/module/infoClassify/info_classify_del_batch.html", null, {"ids": ids}, function(data, status) {
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
});