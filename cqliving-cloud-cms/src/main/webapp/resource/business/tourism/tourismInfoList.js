define(["cloud.table.curd", "cloud.time.input", "cqliving_dialog", "cqliving_ajax", "cqliving_input", "chosen"], 
		function(tableCurd, timeInput, cqliving_dialog, cq_ajax, cqliving_input) {
	return {
		init: function() {
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			cqliving_input.onlyNum();
			//初始化下拉搜索控件
			$(".chosen-select").chosen({search_contains: true});
			
			//修改排序号
			updateSortNo();
			
			//控制区域下拉选项
			handleRegion();
			//切换App
			$("#search_EQ_appId").change(handleRegion);
			//覆盖公共的重置方法
			$("#resetButton").off("click").click(function() {
				$("#search_LIKE_name, #search_EQ_type, #search_EQ_regionCode, #search_EQ_status").val("");
			});
			
			//发布、下线
			$(".main-content").on('click', '.publishOrOfflineButton', publishOrOfflineFn);
			//批量发布、批量下线
			$('.publishOrOfflineBatch').click(publishOrOfflineBatch);
			//预览
			$(".main-content").on('click', '.previewButton', previewFn);
			
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
	
	/**
	 * 修改排序号
	 */
	function updateSortNo(){
		$('body').on('change','.only_num',function(){
			var jThis = $(this);
			var url = jThis.attr("url");
			var val = $(this).val();
			var old = $(this).next("input").val();
			if(val){
				try{
					var re = /^[1-9][0-9]*$/ ;
					var result=  re.test(val);
					if(!result){
						cqliving_dialog.error("排序号只能输入只能输入大于0的整数");
						$(this).val(old);
						return;
					}
				}catch(e){
					cqliving_dialog.error("排序号只能输入只能输入大于0的整数");
					$(this).val(old);
					return;
				}
			}
			var id = $(this).attr("iid");
			cq_ajax.ajaxOperate(url,jThis,{"id":id,"sortNo":val},function(data,status){
				if(data.code >= 0){
					$("#searchButton").trigger("click");
				}else{
					cqliving_dialog.error(data.message?data.message:"操作失败");
				}
			});
		});		
	}
	
	/**
	 * 发布、下线
	 * @author DeweiLi on 2016年8月25日
	 */
	function publishOrOfflineFn(){
		var me = $(this),
			url = me.attr('url'),
			title = me.attr('data-original-title');
		cqliving_dialog.confirm('操作确认','确定要' + title + '该记录吗？',function(){
			cq_ajax.ajaxOperate(url, me, {}, function(data,status){
				if(data.code >= 0){
					cqliving_dialog.success(data.message ? data.message  : title + "成功", function(){
						$("#searchButton").trigger("click");
					});
				}else{
					cqliving_dialog.error(data.message ? data.message : title + "失败");
				}
			});
		});
	}
	
	/**
	 * 批量发布、批量下线
	 * @author DeweiLi on 2016年8月25日
	 */
	function publishOrOfflineBatch(){
		var me = $(this),
			title = me.attr('title'),
			url = me.attr('url'),
			jCheckedIds = $('input:checkbox[class=ace]:checked');
		if(jCheckedIds.length == 0){
			//如果没有选择商品则提示用户
			cqliving_dialog.error("请选择需要" + title + "的记录");
			return ;
		}else{
			var ids = [];
			jCheckedIds.each(function(i,t){
				var id = $(t).attr("id");
				if(id){
					ids.push(id);
				}
			});
			if(ids.length>0){
				//弹出确认对话框
				cqliving_dialog.confirm('操作确认','确定要' + title + '这些记录吗？',function(){
					cq_ajax.ajaxOperate(url, me, {"ids":ids}, function(data,status){
						if(data.code >= 0){
							cqliving_dialog.success(data.message?data.message: title + "成功",function(){
								$("#searchButton").trigger("click");
							});
						}else{
							cqliving_dialog.error(data.message?data.message: title + "失败");
						}
					});
				});
			}else{
				cqliving_dialog.error("请选择需要" + title + "的记录");
			}
		}
	}
	
	/**
	 * 预览
	 */
	function previewFn(){
		var me = $(this),
			url = me.attr("url");
		cq_ajax.ajaxOperate(url, me, {}, function(data, status) {
			$("body").append(data);
		}, {dataType: "html"});
	}
	
});