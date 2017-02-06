
define(['cloud.table.curd', 'cloud.time.input', 'cqliving_dialog', 'cqliving_ajax', 'chosen'], function(tableCurd, timeInput, cq_dialog, cq_ajax){
	return {
		init: function(){
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			//初始化下拉搜索控件
			$(".chosen-select").chosen({search_contains: true});
			//发布、下线
			$('#table_content_page').on('click', '.onlineBtn', onlineFn);
			//批量删除
			$('#deleteBatchBtn').on('click', deleteBatchFn);
			//批量发布、下线
			$('.batchOnlineBtn').on('click', batchOnlineFn);
			//修改排序
			$("#table_content_page").on("change", ".only_num", modifySortNo);
		}
	}
	
	function _refreshPage(){
		tableCurd.reloadNotChangePageNo();
	}
	/**
	 * 发布、下线
	 */
	function onlineFn(){
		var me = $(this),
			url = me.attr('url'),
			title = me.data('original-title');
		cq_dialog.confirm(title + '确认', '确定要' + title + '？', function(){
			cq_ajax.ajaxOperate(url, me, {}, function(data){
				if(data.code >= 0){
					cq_dialog.success(title + '成功。', function(){
						_refreshPage();
					});
				}else{
					cq_dialog.error(data.msg ? data.msg : title + '失败。');
				}
			});
		});
	}
	
	/**
	 * 批量发布、下线
	 */
	function batchOnlineFn(){
		var me = $(this),
			url = me.attr('url'),
			title = me.text();
		
		var jCheckedIds = $('input:checkbox[class=ace]:checked');
		if(jCheckedIds.length == 0){
			//如果没有选择商品则提示用户
			cq_dialog.error("请选择需要" + title + "的记录");
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
				cq_dialog.confirm('操作确认','确定要' + title + '这些记录吗？',function(){
					cq_ajax.ajaxOperate(url,me,{"ids":ids},function(data,status){
						if(data.code >= 0){
							cq_dialog.success(data.message?data.message: title + "成功",function(){
								_refreshPage();
							});
						}else{
							cq_dialog.error(data.message?data.message: title + "失败");
						}
					});
				});
			}else{
				cq_dialog.error("请选择需要" + title + "的记录");
			}
		}
	}
	
	/**
	 * 批量删除，因为发布的不能直接删除
	 */
	function deleteBatchFn(){
		var me = $(this),
			url = me.attr('url'),
			title = me.text();
		
		var jCheckedIds = $('input:checkbox[class=ace]:checked');
		if(jCheckedIds.length == 0){
			//如果没有选择商品则提示用户
			cq_dialog.error("请选择需要" + title + "的记录");
		}else{
			var ids = [];
			jCheckedIds.each(function(i,t){
				var id = $(t).attr("id");
				var status = $(t).attr('status');
				if(status != 3){
					if(id){
						ids.push(id);
					}
				}
			});
			if(ids.length>0){
				//弹出确认对话框
				cq_dialog.confirm('操作确认','确定要' + title + '这些记录吗？',function(){
					cq_ajax.ajaxOperate(url,me,{"ids":ids},function(data,status){
						if(data.code >= 0){
							cq_dialog.success(data.message?data.message: title + "成功",function(){
								_refreshPage();
							});
						}else{
							cq_dialog.error(data.message?data.message: title + "失败");
						}
					});
				});
			}else{
				cq_dialog.error("请选择需要" + title + "的记录");
			}
		}
	}
	
	/** 修改排序 */
	function modifySortNo() {
		var me = $(this),
			id = me.closest("tr").find("td:first").attr("id"),
			sortNo = me.val(),
			reg = /^[0-9]*$/;
		if(!reg.test(sortNo)){
			me.val('');
			return;
		}
		var data = {
				"i": id,
				"s": sortNo
		};
		cq_ajax.ajaxOperate("/module/building/building_info_sort.html", null, data, function(data, status) {
			if (data.code >= 0) {
//				cq_dialog.success(data.message ? data.message : "操作成功");
				_refreshPage();
			} else {
				cq_dialog.error(data.message);
			}
		}, {"async": false});
	}
	
});