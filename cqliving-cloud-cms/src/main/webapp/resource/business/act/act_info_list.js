define(['cloud.table.curd','cloud.time.input','cqliving_dialog','cqliving_ajax'], function(tableCurd,timeInput,cqliving_dialog,cq_ajax){
	return {
		init:loadInit
	};
	
	function loadInit(){
		tableCurd.initTableCrud();
		timeInput.initTimeInput();
		// 单个上下线
		oneOnOut();
		// 批量上下线
		batchOnOut();
		//清空排序号
		cleacSortNo();
		//修改排序号
		updateSortNo();
	}
	
	/**
	 * 单个上下线
	 */
	function oneOnOut(){
		//按钮是追加进来的，需要用事件委托方式加载点击事件
		$('body').on('click','.on-out',function(){
			var jThis = $(this);
			var url = jThis.attr("url");
			var tip = jThis.attr('tip');
			cqliving_dialog.confirm('操作确认',tip,function(){
				cq_ajax.ajaxOperate(url,jThis,{},function(data,status){
					if(data.code >= 0){
						cqliving_dialog.success(data.message?data.message:"操作成功",function(){
							$("#searchButton").trigger("click");
						});
					}else{
						cqliving_dialog.error(data.message?data.message:"操作失败");
					}
				});
			});
		});
	}
	
	/**
	 * 批量上下线
	 */
	function batchOnOut(){
		//按钮本来就存在于页面
		$('.on-out-batch').on('click', function(){
			var jCheckedIds = $('input:checkbox[name=ace]:checked');
			if(jCheckedIds.length == 0){
				//如果没有选择商品则提示用户
				cqliving_dialog.alert("请选择需要操作的记录");
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
						if(!(status == STATUS88 || status == STATUS1)){
							ids.push(id);
						}
					}
				});
				if(ids.length>0){
					var jThis = $(this);//操作按钮
					var url = jThis.attr("url");
					//弹出确认对话框
					cqliving_dialog.confirm('操作确认',oper==1 ? "确定要批量上线吗？":"确定要批量下线吗？",function(){
						cq_ajax.ajaxOperate(url,jThis,{"ids":ids},function(data,status){
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
					cqliving_dialog.alert("请选择需要操作的记录");
				}
			}
		});
	}
	
	/**
	 * 清空排序号
	 */
	function cleacSortNo(){
		//clear_sort_no_btn页面本来就有
		$('#clear_sort_no_btn').on('click', function(){
			var jCheckedIds = $("input[name='ace']") ;
			var ids = [];
			var id;
			jCheckedIds.each(function(i,t){
				id = $(t).attr("id");
				if(id){
					ids.push(id);
				}
			});
			if(ids.length>0){
				var jThis = $(this);//操作按钮
				var url = jThis.attr("url");
				//弹出确认对话框
				cqliving_dialog.confirm('操作确认',"确定要清空排序号吗？",function(){
					cq_ajax.ajaxOperate(url,jThis,{"ids":ids},function(data,status){
						if(data.code >= 0){
							$("#searchButton").trigger("click");
						}else{
							cqliving_dialog.error(data.message?data.message:"清空排序号失败");
						}
					});
				});
			}else{
				cqliving_dialog.alert("无需要清空的记录");
			}
		});
	}
	
	/**
	 * 修改排序号
	 */
	function updateSortNo(){
		//only_num是追加进来的
		$('body').on('change','.only_num',function(){
			var jThis = $(this);
			var url = jThis.attr("url");
			var val = $(this).val();
			try{
				var re = /^[1-9][0-9]*$/ ;
		        var result=  re.test(val);
				if(!result){
					cqliving_dialog.error("排序号只能输入正整数");
					$(this).val('');
					return;
				}
			}catch(e){
				cqliving_dialog.error("排序号只能输入正整数");
				$(this).val('');
				return;
			}
			var id = $(this).attr("iid");
			cq_ajax.ajaxOperate(url,jThis,{"id":id,"sortNo":val},function(data,status){
				if(data.code >= 0){
					$("#searchButton").trigger("click");
				}else{
					cqliving_dialog.error(data.message?data.message:"操作失败");
				}
			});
		});
	}
});