define(['cloud.table.curd', 'cqliving_ajax', 'cqliving_dialog', "chosen"], function(tableCurd, cq_ajax, cq_dialog){
	return {
		init: function(){
			tableCurd.initTableCrud();
			//初始化APP选择列表框
			$(".chosen-select").chosen({search_contains:true});
			$(document).on('change', '.sortNo', sortNoChange);
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
		cq_ajax.ajaxOperate('/module/config/'+sourceType+'/config_common_type_sort.html', null, {id: id, sortNo: val}, function(data){
			$('#searchButton').click();
		});
	}
	
});