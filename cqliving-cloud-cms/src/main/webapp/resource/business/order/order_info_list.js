/**
 * 订单管理
 * @author DeweiLi
 */
define(['cloud.table.curd','cloud.time.input', 'cqliving_dialog', 'cqliving_ajax', 'chosen'], function(tableCurd,timeInput, cq_dialog, cq_ajax){
	return {
		init: function(){
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			//初始化APP选择列表框
			$(".chosen-select").chosen({search_contains:true});
			//确认退货
			$(document).on('click', '.refundBtn', refundFn);
			//取消订单
			$('#table_content_page').on('click', '.cancelBtn', cancelFn);
		}
	};
	
	/**
	 * 确认退货
	 */
	function refundFn(){
		var me = $(this),
			type = me.data('type'),
			url = me.attr('url');
		
		cq_dialog.model_dialog({
			backdrop: 'static',//控制点击背景关闭的问题
			keyboard: false,
			title: '退款详情',
			url: url,
			buttons: {
				/*ok: {
					callback: function(jThis, jModel, param){
						var content = $(jModel).find('textarea[name=content]').val();
						if(content == ''){
							cq_dialog.error('请输入拒绝原因');
							return ;
						}
						refundSure(url, jThis, {content: content, type: type}, jModel);
					}
				},*/
				aduit_pass: {
					label: '同意退款',
					callback: function(jThis, jModel, param){
						var form = jModel.find('form');
						form.find('input[name=type]').val(1);
						confirmSubmit(form, jModel);
					}
				},
				aduit_reject: {
					label: '拒绝退款',
					callback: function(jThis, jModel, param){
						var form = jModel.find('form');
						var refuseReason = $(form).find('textarea[name=refuseReason]');
						var validate = false;
						$.each(refuseReason, function(i, data){
							if($(data).val() == ''){
								validate = true;
							}
						});
						if(validate){
							cq_dialog.error('请输入拒绝原因');
							return;
						}
						form.find('input[name=type]').val(2);
						confirmSubmit(form, jModel);
					}
				}
			}
		});
		function confirmSubmit(form, jModel){
			var url = form.attr('action');
			var type = form.find('input[name=type]').val();
			var params = form.serialize();
			cq_ajax.ajaxOperate(url, me, params, function(data){
				var title = type == 1 ? '退款' : '拒绝退款';
				if(data.code >= 0){
					cq_dialog.success(data.message ? data.message : title + '成功', function(){
						if(jModel){
							jModel.modal('hide');
						}
						tableCurd.reloadNotChangePageNo();
					});
				}else{
					cq_dialog.error(data.message ? data.message : title + '发货失败');
				}
			});
		}
		
		
		return ;
		if(type == 1){//确认退货
			cq_dialog.confirm('确认退款', '是否确认退款', function(){
				refundSure(url, me, {type: type}, null);
			});
		}else if(type == 2){//拒绝退货
			cq_dialog.model_dialog({
				backdrop: 'static',//控制点击背景关闭的问题
				keyboard: false,
				title: '拒绝退款',
				content: '<textarea rows="6" class="form-control" name="content" placeholder="请输入拒绝类容" maxlength="500"></textarea>',
				buttons: {
					ok: {
						callback: function(jThis, jModel, param){
							var content = $(jModel).find('textarea[name=content]').val();
							if(content == ''){
								cq_dialog.error('请输入拒绝原因');
								return ;
							}
							refundSure(url, jThis, {content: content, type: type}, jModel);
						}
					}
				}
			});
		}
	}
	function refundSure(url, me, param, jModel){
		cq_ajax.ajaxOperate(url, me, param, function(data){
			var title = jModel == null ? '退款' : '拒绝退款';
			if(data.code >= 0){
				cq_dialog.success(data.message ? data.message : title + '成功', function(){
					if(jModel){
						jModel.modal('hide');
					}
					tableCurd.reloadNotChangePageNo();
				});
			}else{
				cq_dialog.error(data.message ? data.message : title + '发货失败');
			}
		});
	}
	
	/**
	 * 取消订单
	 */
	function cancelFn(){
		var me = $(this),
			url = me.attr('url');
		cq_dialog.confirm('取消订单确认', '确定要取消订单吗？', function(){
			cq_ajax.ajaxOperate(url, me, {}, function(data){
				if(data.code >= 0){
					cq_dialog.success(data.message ? data.message : '取消成功', function(){
						tableCurd.reloadNotChangePageNo();
					});
				}else{
					cq_dialog.error(data.message ? data.message : title + '取消失败');
				}
			});
		});
	}
});