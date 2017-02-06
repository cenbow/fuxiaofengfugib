define(['cloud.table.curd', 'cqliving_ajax', 'cqliving_dialog', "chosen"], function(tableCurd, cq_ajax, cq_dialog){
	return {
		init: function(){
			tableCurd.initTableCrud();
			//初始化APP选择列表框
			$(".chosen-select").chosen({search_contains:true});
			//排序
			$(document).on('change', '.sortNo', sortNoChange);
			//发布
			$(document).on('click', '.publishBtn', publishFn);
			//取消发布
			$(document).on('click', '.publishCancelBtn', publishFn);
			//批量发布
			$('#batchPublishBtn').click(publishBatchFn);
			//新闻预览
			$("#table_content_page").on("click", ".preview_btn", preview);
		}
	};
	
	/**
	 * 排序
	 */
	function sortNoChange(){
		var val = $(this).val(),
			id = $(this).data('id');
		if(isNaN(val) || val == ''){
			return ;
		}
		cq_ajax.ajaxOperate('/module/config/'+sourceType+'/recommend_info_sort.html', null, {id: id, sortNo: val}, function(data){
			$('#searchButton').click();
		});
	}
	
	/**
	 * 发布、取消发布
	 */
	function publishFn(){
		var me = $(this),
			url = me.attr('url'),
			status = me.hasClass('publishBtn') ? 3 : me.hasClass('publishCancelBtn') ? 88 : 0,
			msgTitle = status == 88 ? '取消' : '';
		cq_dialog.confirm('操作确认','确定要' + msgTitle + '发布这条记录吗？',function(){
			cq_ajax.ajaxOperate(url, me, {status: status}, function(data){
				if(data.code >= 0){
					cq_dialog.success(msgTitle + '发布成功。', function(){
						$('#searchButton').click();
					});
				}else{
					cq_dialog.error(msgTitle + '发布失败。');
				}
			});
		});
	}
	
	/**
	 * 批量发布
	 */
	function publishBatchFn(){
		var jCheckedIds = $('input:checkbox[class=ace]:checked');
		if(jCheckedIds.length == 0){
			//如果没有选择商品则提示用户
			cq_dialog.alert("请选择需要发布的记录");
		}else{
			var ids = [];
			jCheckedIds.each(function(i,t){
				var id = $(t).attr("id");
				if(id){
					ids.push(id);
				}
			});
			if(ids.length>0){
				var jThis = $(this);//操作按钮
				var url = jThis.attr("url");
				//弹出确认对话框
				cq_dialog.confirm('操作确认','确定要批量发布这些记录吗？',function(){
					cq_ajax.ajaxOperate(url,jThis,{"ids":ids, status: 3},function(data,status){
						if(data.code >= 0){
							cq_dialog.success(data.message?data.message:"批量发布成功",function(){
								$("#searchButton").trigger("click");
							});
						}else{
							cq_dialog.error(data.message?data.message:"批量发布失败");
						}
					});
				});
			}else{
				cq_dialog.alert("请选择需要发布的记录");
			}
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
});