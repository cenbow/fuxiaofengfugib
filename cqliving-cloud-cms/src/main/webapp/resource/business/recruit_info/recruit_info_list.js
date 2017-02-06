define(['cloud.table.curd','cloud.time.input','cqliving_ajax','cqliving_dialog','chosen'], function(tableCurd,timeInput,cqliving_ajax,cqliving_dialog) {
	return {
		init: function(){
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			//初始化APP选择列表框
			$(".chosen-select").chosen({search_contains:true});
			
			//修改排序号
			updateSortNo();
			
			//单个上下线
			lineOper();
			
			//批量上下线
			lineOperBatch();
		}
	};
	
	/**
	 * 修改排序号
	 */
	function updateSortNo(){
		$('body').on('change','.only_num',function(){
			var jThis = $(this);
			var url = jThis.attr("url");
			var val = $(this).val();
			var old = $(this).next("input").val();
			if(val){
				try{
					var re = /^[1-9][0-9]*$/ ;
					var result=  re.test(val);
					if(!result){
						cqliving_dialog.error("排序号只能输入只能输入大于0的整数");
						$(this).val(old);
						return;
					}
				}catch(e){
					cqliving_dialog.error("排序号只能输入只能输入大于0的整数");
					$(this).val(old);
					return;
				}
			}
			var id = $(this).attr("iid");
			cqliving_ajax.ajaxOperate(url,jThis,{"id":id,"sortNo":val},function(data,status){
				if(data.code >= 0){
					$("#searchButton").trigger("click");
				}else{
					cqliving_dialog.error(data.message?data.message:"操作失败");
				}
			});
		});		
	}
	
	/**
	 * 单个上下线处理
	 */
	function lineOper(){
		$('body').on('click','.oper-one',function(){
			var jThis = $(this);
			var url = jThis.attr("url");
			var oper = jThis.attr("data-original-title");
			cqliving_dialog.confirm('操作确认','确定要将该记录'+oper+'吗？',function(){
				cqliving_ajax.ajaxOperate(url,jThis,{},function(data,status){
					if(data.code >= 0){
						cqliving_dialog.success(data.message?data.message:oper+"成功",function(){
							$("#searchButton").trigger("click");
						});
					}else{
						cqliving_dialog.error(data.message?data.message:oper+"失败");
					}
				});
			});
		});
	}
	
	/**
	 * 批量上下线操作
	 */
	function lineOperBatch(){
		$('.on-out-batch').on('click',function(){
			var jCheckedIds = $('input:checkbox[class=ace]:checked');
			if(jCheckedIds.length == 0){
				cqliving_dialog.alert("请选择需要操作的记录");
			}else{
				var ids = [];
				var oper = $(this).attr('status');
				jCheckedIds.each(function(i,t){
					var id = $(t).attr("id");
					var status = $(t).attr('status');
					if(id && oper==STATUS3){
						//上线
						if(!(status==STATUS3)){
							ids.push(id);
						}
					}else if(id && oper==STATUS88){
						//下线
						if(!(status == STATUS88 || status == STATUS1)){
							ids.push(id);
						}
					}
				});
				
				if(ids.length>0){
					var jThis = $(this);
					var url = jThis.attr("url");
					var operString = jThis.attr("oper");
					cqliving_dialog.confirm('操作确认','确定要'+operString+'吗？',function(){
						cqliving_ajax.ajaxOperate(url,jThis,{"ids":ids},function(data,status){
							if(data.code >= 0){
								cqliving_dialog.success(data.message?data.message:operString+"成功",function(){
									$("#searchButton").trigger("click");
								});
							}else{
								cqliving_dialog.error(data.message?data.message:operString+"失败");
							}
						});
					});
				}else{
					cqliving_dialog.alert("请选择需要操作的记录");
				}
			}
		});
	}
});