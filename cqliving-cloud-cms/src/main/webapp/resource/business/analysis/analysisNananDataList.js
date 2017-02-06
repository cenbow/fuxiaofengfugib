define(["cloud.table.curd", "cloud.time.input", "cqliving_dialog", "cqliving_ajax"], function(tableCurd, timeInput, cqDialog, cq_ajax) {
	return {
		init: function() {
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			
			//生成期数
			$("#createTimesBtn").click(createTimes);
		}
	};
	
	/** 生成期数 */
	function createTimes() {
		//获取时间
		var beginDate = $("#createTimesBeginDate").val();
		var endDate = $("#createTimesEndDate").val();
		if (!beginDate || !endDate) {
			cqDialog.error("请选择生成期数的时间");
			return;
		}
		
		var url = "/module/analysis/analysis_nanan_create_times.html";
		var params = {
				"bd": beginDate,
				"ed": endDate
		};
		cq_ajax.ajaxOperate(url, null, params, function(data, status) {
			if (data.code >= 0) {
				cqDialog.success(data.message ? data.message : "生成成功", function() {
					//刷新页面
					location.href = "/module/analysis/analysis_nanan_dada_list.html";
				});
			} else {
				cqDialog.error(data.message ? data.message : "生成失败");
			}
		});
	}
	
});