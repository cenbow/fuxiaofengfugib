define(["cloud.table.curd", "cloud.time.input", "cqliving_dialog", "cqliving_ajax"], function(tableCurd, timeInput, cqliving_dialog, cq_ajax) {
	return {
		init: function() {
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			
			//发送
			$("#table_content_page").on("click", ".send_btn", send);
		}
	};
	
	//发送
	function send() {
		var jThis = $(this);
		var url = jThis.attr("url");
		cqliving_dialog.confirm("操作确认", "确定要发送吗？", function() {
			cq_ajax.ajaxOperate(url, jThis, {}, function(data, status) {
				if (data.code >= 0) {
					cqliving_dialog.success(data.message ? data.message : "操作成功");
					$("#searchButton").trigger("click");
				} else {
					cqliving_dialog.error(data.message);
				}
			});
		});
	}
	
});