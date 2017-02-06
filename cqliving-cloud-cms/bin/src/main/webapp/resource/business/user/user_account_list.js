define(['cloud.table.curd','cloud.time.input','cqliving_dialog','cqliving_ajax','toastr','chosen'], function(tableCurd,timeInput,cqliving_dialog,cq_ajax,toastr){
	return {
		init: function(){
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			//初始化APP选择列表框
			$(".chosen-select").chosen({search_contains:true});
			$(".main-content").on('click' ,'.operBtn', function(){
				exe($(this));
			});
			//删除上传的图片
			oper();
		}
	};
	
	function exe(jThis){
		var jCheckedIds = $('input:checkbox[class=ace]:checked');
		var tip = jThis.attr("tip");
		if(jCheckedIds.length == 0){
			//如果没有选择商品则提示用户
			cqliving_dialog.alert("请选择需要"+tip+"的用户");
		}else{
			var ids = [];
			jCheckedIds.each(function(i,t){
				var id = $(t).attr("id");
				if(id){
					ids.push(id);
				}
			});
			if(ids.length>0){
				//var jThis = $(this);//操作按钮
				var url = jThis.attr("url");
				//弹出确认对话框
				cqliving_dialog.confirm('操作确认','确定要'+tip+'这些用户吗？',function(){
					cq_ajax.ajaxOperate(url,jThis,{"ids":ids},function(data,status){
						if(data.code >= 0){
							cqliving_dialog.success(data.message?data.message:tip+"成功",function(){
								$("#searchButton").trigger("click");
							});
						}else{
							cqliving_dialog.error(data.message?data.message:tip+"失败");
						}
					});
				});
			}else{
				cqliving_dialog.alert("请选择需要"+tip+"的用户");
			}
		}
	}
	
	/**
	 * 禁用/启用
	 */
	function oper(){
		$(".main-content").on('click' ,'.operOneBtn', function(){
			var jThis = $(this);
			var tip = jThis.attr("tip");
			var url = jThis.attr("url");
			cqliving_dialog.confirm('操作确认','确定要'+tip+'该用户吗？',function(){
				cq_ajax.ajaxOperate(url,jThis,{},function(data,status){
					if(data.code >= 0){
						cqliving_dialog.success(data.message?data.message:tip+"成功",function(){
							$("#searchButton").trigger("click");
						});
					}else{
						cqliving_dialog.error(data.message?data.message:(tip+"失败"));
					}
				});
			});
		});
	}
});