define(['cloud.table.curd', 'cqliving_ajax', 'cqliving_dialog', "chosen"], function(tableCurd, cq_ajax, cq_dialog){
	return {
		init: function(){
			tableCurd.initTableCrud();
			//初始化APP选择列表框
			$(".chosen-select").chosen({search_contains:true});
			$(document).on('change', '.sortNo', sortNoChange);
			//appp改变
			$('#search_EQ_appId').on('change', changeApp);
			//重置
			bindReset();
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
		cq_ajax.ajaxOperate('/module/cfg/config_convenience_sort.html', null, {id: id, sortNo: val}, function(data){
			$('#searchButton').click();
		});
	}
	
	/**
	 * app改变
	 */
	function changeApp(){
		var me = $(this),
			appId = me.val(),
			configConvenienceTypeObj = $('#search_LIKE_convenienceTypeId'),
			url = '/module/config/8/common/getByApp.html';
		if(appId){
			cq_ajax.ajaxOperate(url, null, {appId:appId}, function(data){
				if(data.code >= 0){
					var html = '<option value="">所有分类</option>';
					$.each(data.data, function(i, d){
						html += '<option value="'+d.id+'">'+d.name+'</option>';
					});
					configConvenienceTypeObj.html(html);
					configConvenienceTypeObj.trigger("chosen:updated");
				}else{
					cq_dialog.error(data.message ? data.message : '获取分类信息失败');
				}
			});
		}else{
			configConvenienceTypeObj.html('<option value="">所有类型</option>');
			configConvenienceTypeObj.trigger("chosen:updated");
		}
	}
	/**
	 * 重置
	 */
	function bindReset(){
		$("#resetBtn").on("click",function(){
			var paramForm = $(this).parents("form");
			paramForm.find(":input").not(':button, :submit, :reset').val('').removeAttr('checked').removeAttr('selected');
			var configConvenienceTypeObj = $('#search_LIKE_convenienceTypeId');
			if($('#search_EQ_appId').length > 0){
				$('#search_EQ_appId').val('');
				$('#search_EQ_appId_chosen .chosen-single>span').html('所有app');
				configConvenienceTypeObj.html('<option value="">所有类型</option>');
				configConvenienceTypeObj.trigger("chosen:updated");
			}else{
				configConvenienceTypeObj.val('');
				$('#search_LIKE_convenienceTypeId_chosen .chosen-single>span').html('所有分类');
			}
		});
	}
	
});