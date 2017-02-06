define(['cloud.table.curd','cloud.time.input','cqliving_dialog','cqliving_ajax','chosen'], function(tableCurd,timeInput,cqliving_dialog,cq_ajax){
	return {
		init: function(){
			tableCurd.initTableCrud();
			//初始化APP选择列表框
			$(".chosen-select").chosen({search_contains:true});
			auditingBatchClick();
			auditingClick();
			auditing();
		}
	};
	
	var ids = [];
	var goodsIds = [];
	
	function auditing(){
		$(".btn-auditing").on("click",function(){
			if(ids.length>0){
				var jThis = $(this);//操作按钮
				var url = '/module/order_evaluate/auditing.html';
				var status = jThis.attr('status');
				var auditingContent = $.trim($("#auditingContent").text());
				cq_ajax.ajaxOperate(url,jThis,{"ids":ids,"goodsIds":goodsIds,"status":status,"auditingContent":auditingContent},function(data,status){
					if(data.code >= 0){
						cqliving_dialog.success(data.message?data.message:"审核成功",function(){
							$("#searchButton").trigger("click");
						});
					}else{
						cqliving_dialog.error(data.message?data.message:"审核失败");
					}
				});
			}else{
				cqliving_dialog.error("请选择状态为保存的记录");
			}
		});
	}
	
	/**
	 * 点击批量审核按钮
	 */
	function auditingBatchClick(){
		$(".auditing-btn").on("click",function(){
			var jCheckedIds = $('input:checkbox[class=ace]:checked');
			if(jCheckedIds.length == 0){
				cqliving_dialog.error("请选择需要审核的记录");
			}else{
				$("#auditingContent").text('');
				ids = [];
				goodsIds = [];
				var noAuditing = $(this).attr("noAuditing");
				jCheckedIds.each(function(i,t){
					var id = $(t).attr("id");
					var status = $(t).attr('status');
					var goodsId = $(t).attr('goodsId');
					if(id&&status==noAuditing){
						ids.push(id);
						goodsIds.push(goodsId);
					}
				});
				if(ids.length>0){
					$('.auditing-btn-a').trigger("click");
				}else{
					cqliving_dialog.error("请选择状态为保存的记录");
				}
			}
		});
	}
	
	/**
	 * 点击单个审核按钮
	 */
	function auditingClick(){
		$('body').on("click",".auditing-one",function(){
			ids = [];
			goodsIds = [];
			$("#auditingContent").text('');
			var id = $(this).attr("eid");
			var goodsId = $(this).attr('goodsId');
			ids.push(id);
			goodsIds.push(goodsId);
			$('.auditing-btn-a').trigger("click");
		});
	}
});