/**
 * 订单发货
 */
define(['cloud.table.curd','cloud.time.input', 'cqliving_dialog', 'cqliving_ajax', 'chosen'], function(tableCurd,timeInput, cq_dialog, cq_ajax){
	return {
		init: function(){
			//发货
			$(document).on('click', '.deliverGoodsBtn', deliverGoodsWin);
		}
	};
	
	/**
	 * 发货
	 */
	function deliverGoodsWin(){
		var me = $(this),
			url = me.attr('url');
		cq_dialog.model_dialog({
			id: 'deliver_goods_model',
			title: '确认发货',
			url: url,
			backdrop: 'static',//控制点击背景关闭的问题
			keyboard: false,
			buttons: {
				ok: {
					callback: function(jThis, jModel, param){
						var m = $(jModel),
							expressCompany = m.find('input[name=expressCompany]').val(),
							expressNo = m.find('input[name=expressNo]').val();
						if(expressCompany == '' || expressNo == ''){
							cq_dialog.error('请输入快递公司和快递单号');
							return ;
						}
						cq_ajax.ajaxOperate(url, jThis, {expressCompany:expressCompany, expressNo: expressNo}, function(data){
							if(data.code >= 0){
								cq_dialog.success(data.message ? data.message : '发货成功', function(){
									$('.modal').map(function(){
										$(this).modal('hide');
									});
									tableCurd.reloadNotChangePageNo();
								});
							}else{
								cq_dialog.error(data.message ? data.message : '发货失败');
							}
						});
					}
				}
			}
		});
	}
})