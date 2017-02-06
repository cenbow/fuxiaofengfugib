define(['cloud.table.curd','cqliving_dialog','cqliving_ajax'], function(tableCurd,cqliving_dialog,cq_ajax){
	return {
		init: function(){
			/**
			 * 审核
			 */
			$('body').on("click",".btn-auditing",function(){
				$(this).unbind("click");
				var status = $(this).attr("status");
				$("#status").val(status);
				var params = $("#fo").serializeArray();
				var url = $("#fo").attr("action");
				cq_ajax.ajaxOperate(url,$("#fo"),params,function(data,status){
					if(data.code >= 0){
						cqliving_dialog.success("审核成功",function(){
							var url = $("#fo").attr("back_url");
							window.location.href = url;
						});
					}else{
						cqliving_dialog.error(data.message?data.message:"审核失败");
					}
				});
			});
		}
	};
	
});