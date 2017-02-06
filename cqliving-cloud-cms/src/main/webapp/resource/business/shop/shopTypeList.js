define(["cloud.table.curd", "cloud.time.input", "cqliving_dialog", "cqliving_ajax", "chosen"], 
		function(tableCurd, timeInput, cqDialog, cq_ajax, cqInput) {
	return {
		init: function() {
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			
			//初始化下拉多选控件
			$(".chosen-select").chosen({search_contains: true}); 
			$(".chosen-container-single").css("width", "100%");
			//覆盖公共的重置方法
			$("#resetButton").off("click").click(function() {
				$("#search_LIKE_name").val("");
			});
		}
	};
	
});