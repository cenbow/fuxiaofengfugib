define(["cloud.table.curd", "cloud.time.input", "cqliving_dialog", "cqliving_ajax", "cqliving_input", "chosen"], 
		function(tableCurd, timeInput, cqliving_dialog, cq_ajax, cqliving_input) {
	return {
		init: function() {
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			cqliving_input.onlyNum();
			//初始化下拉搜索控件
			$(".chosen-select").chosen({search_contains: true});
			//修改排序
			$("#table_content_page").on("blur", ".only_num", modifySortNo);
			//批量发布
			$(".main-content").on('click' ,'#publush_batch,#offline_batch',publishBatch);
			//控制区域下拉选项
			handleRegion();
			//切换App
			$("#search_EQ_appId").change(handleRegion);
			//覆盖公共的重置方法
			$("#resetButton").off("click").click(function() {
				$("#search_LIKE_name, #search_EQ_type, #search_EQ_regionCode, #search_EQ_status").val("");
			});
		}
	};
	
	/** 控制区域下拉选项 */
	function handleRegion() {
		var appId = $("#search_EQ_appId").val();
		//先隐藏
		$("#search_EQ_regionCode option:gt(0)").hide();
		$("#search_EQ_regionCode").val("");
		if (appId) {
			//显示对应App的旅游区域
			$("#search_EQ_regionCode option:gt(0)[appid=" + appId + "]").show();
		}
	}
	
	/** 修改排序 */
	function modifySortNo() {
		
		var id = $(this).closest("tr").find("td:first").attr("id");
		var sortNo = $(this).val();
		var data = {
				"i": id,
				"s": sortNo
		};
		cq_ajax.ajaxOperate("/module/tourism/update_sort_no.html", null, data, function(data, status) {
			if (data.code >= 0) {
				cqliving_dialog.success(data.message ? data.message : "操作成功");
				$("#searchButton").trigger("click");
			} else {
				cqliving_dialog.error(data.message);
			}
		}, {"async": false});
	}
	
	//批量发布
	function publishBatch(){
		var jCheckedIds = $('tbody input:checkbox[class=ace]:checked');
		if(jCheckedIds.length == 0){
			//如果没有选择商品则提示用户
			cqliving_dialog.error("请选择需要操作的记录");
		}else{
			var ids = [];
			jCheckedIds.each(function(i,t){
				var id = $(t).val();
				if(id){
					ids.push(id);
				}
			});
			if(ids.length>0){
				var jThis = $(this);//操作按钮
				var url = jThis.attr("url");
				var jstatus = jThis.attr("status");
				var msg = jstatus == 3 ? '发布' : '下线';
				
				//弹出确认对话框
				cqliving_dialog.confirm('操作确认','确定要批量'+msg+'这些记录吗？',function(){
					cq_ajax.ajaxOperate(url,jThis,{"ids":ids,status:jstatus},function(data,status){
						if(data.code >= 0){
							cqliving_dialog.success(data.message?data.message:"批量"+msg+"成功",function(){
								$("#searchButton").trigger("click");
							});
						}else{
							cqliving_dialog.error(data.message?data.message:"批量"+msg+"失败");
						}
					});
				});
			}else{
				cqliving_dialog.error("请选择需要发布的记录");
			}
		}
	}
	
});