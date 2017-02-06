define(['validator.bootstrap','cloud.table.curd','cqliving_ajax','cqliving_dialog','chosen'], function($,tableCurd,cqliving_ajax,cqliving_dialog){
	return {
		init:loadInit
	};
	/**
	 * 初始化加载
	 */
	function loadInit(){
		tableCurd.initTableCrud();
		//初始化APP选择列表框
		$(".chosen-select").chosen({search_contains:true});
		
		//修改排序号
		updateSortNo();
		
		//上下线
		onOut();
		onOutBatch();
	};
	
	/**
	 * 上下线
	 */
	function onOut(){
		//按钮是追加进来的，需要用事件委托方式加载点击事件
		$('body').on('click','.on-out',function(){
			var jThis = $(this);
			var url = jThis.attr("url");
			var tip = jThis.attr('tip');
			var id = jThis.attr('recommendId');
			if(id){
				var ids = [];
				ids.push(id);
				cqliving_dialog.confirm('操作确认',tip,function(){
					cqliving_ajax.ajaxOperate(url,jThis,{"ids":ids},function(data,status){
						if(data.code >= 0){
							cqliving_dialog.success(data.message?data.message:"操作成功",function(){
								$("#searchButton").trigger("click");
							});
						}else{
							cqliving_dialog.error(data.message?data.message:"操作失败");
						}
					});
				});
			}else{
				cqliving_dialog.error("数据异常，请稍后重试!",function (){
					$("#searchButton").trigger("click");
				});
			}
		});
	}
	
	/**
	 * 批量上下线
	 */
	function onOutBatch(){
		$('.on-out-batch').on('click', function(){
			var jCheckedIds = $('input:checkbox[name=ace]:checked');
			if(jCheckedIds.length == 0){
				//如果没有选择商品则提示用户
				cqliving_dialog.error("请选择需要操作的记录");
			}else{
				var ids = [];
				var oper = $(this).attr('oper');
				jCheckedIds.each(function(i,t){
					var id = $(t).attr("id");
					var status = $(t).attr('status');
					//上线
					if(id && oper==1){
						if(!(status==STATUS3)){
							ids.push(id);
						}
					}else if(id && oper==2){
						if(!(status == STATUS88)){
							ids.push(id);
						}
					}
				});
				if(ids.length>0){
					var jThis = $(this);//操作按钮
					var url = jThis.attr("url");
					//弹出确认对话框
					cqliving_dialog.confirm('操作确认',oper==1 ? "确定要批量上线吗？":"确定要批量下线吗？",function(){
						cqliving_ajax.ajaxOperate(url,jThis,{"ids":ids},function(data,status){
							if(data.code >= 0){
								cqliving_dialog.success(data.message?data.message:"批量操作成功",function(){
									$("#searchButton").trigger("click");
								});
							}else{
								cqliving_dialog.error(data.message?data.message:"批量操作失败");
							}
						});
					});
				}else{
					cqliving_dialog.error("请选择需要操作的记录");
				}
			}
		});
	}
	
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
					if(val){
						var re = /^[1-9][0-9]*$/ ;
						var result=  re.test(val);
						if(!result){
							cqliving_dialog.error("排序号只能输入只能输入大于0的整数");
							$(this).val(old);
							return;
						}
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
});