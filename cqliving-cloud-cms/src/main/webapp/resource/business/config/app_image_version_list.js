define(['cloud.table.curd','cloud.time.input','cqliving_dialog','cqliving_ajax','chosen'], function(tableCurd,timeInput,cqliving_dialog,cq_ajax){
	
	return {
		init: function(){
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			//初始化APP选择列表框
			$(".chosen-select").chosen({search_contains:true});	
			startUsing();
			nonUse();
		}
	};
	
	//启用
	function startUsing(){
		$("body").on('click' ,'.startUsing', function(){
			var jThis = $(this);
			var url = jThis.attr("url");
			cqliving_dialog.confirm('操作确认','确定要将该记录上线吗？',function(){
				cq_ajax.ajaxOperate(url,jThis,{},function(data,status){
					if(data.code >= 0){
						cqliving_dialog.success(data.message?data.message:"上线成功",function(){
							$("#searchButton").trigger("click");
						});
					}else{
						cqliving_dialog.error(data.message?data.message:"上线失败");
					}
				});
			});
		});
	}
	
	//停用
	function nonUse(){
		$("body").on('click' ,'.nonUse', function(){
			var jThis = $(this);
			var url = jThis.attr("url");
			cqliving_dialog.confirm('操作确认','确定要将该记录下线吗？',function(){
				cq_ajax.ajaxOperate(url,jThis,{},function(data,status){
					if(data.code >= 0){
						cqliving_dialog.success(data.message?data.message:"下线成功",function(){
							$("#searchButton").trigger("click");
						});
					}else{
						cqliving_dialog.error(data.message?data.message:"下线失败");
					}
				});
			});
		});
	}
});