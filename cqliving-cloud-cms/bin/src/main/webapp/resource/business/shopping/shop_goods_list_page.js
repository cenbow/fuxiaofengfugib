define(['validator.bootstrap'], function($){
	return {
		init:loadInit
	};
	/**
	 * 初始化加载
	 */
	function loadInit(){
		var shoppingCategoryId = $("#search_EQ_shoppingCategoryId").val();
		if(shoppingCategoryId){
			$(".sort-td").show();
		}else{
			$(".sort-td").hide();
		}
	};
});