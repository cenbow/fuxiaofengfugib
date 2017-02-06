define(['jquery','cqliving_dialog','cqliving_ajax','chosen'], function($,cqliving_dialog,cq_ajax){
	return {
		init:loadInit
	};
	
	function loadInit(){
		$(".chosen-select").chosen({search_contains:true});
		/**
		 * 区县app改变，刷新页面
		 */
		$("#search_EQ_appId").on('change',function(){
			$("#shopping_category_FormId").submit();
		});
	}
});