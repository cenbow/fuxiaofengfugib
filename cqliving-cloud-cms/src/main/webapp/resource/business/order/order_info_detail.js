define(['validator.bootstrap', 'cloud.table.curd', 'cqliving_ajax', 'cqliving_dialog', 'myUploader', 'common_colorbox', "chosen"], function($, tableCurd, cq_ajax, cq_dialog, myUploader, colorbox){
	return {
		init: function(){
			tableCurd.bindSaveButton();
			colorbox.init();
			//初始化页面
			initPage();
		}
	};
	
	/**
	 * 初始化页面
	 */
	function initPage(){
		
		//设置自己的业务验证
		tableCurd.setValid(function(form){
			var shippingFare = $('#shippingFare').val();
			if(shippingFare < 0){
				cq_dialog.error('运费不能小于0');
				return false;
			}
			return form.valid();
		});
		$(function(){
	        $("#form1").validate({
	            rules: {
	            	shippingFare : {
	                    required: true,
	                    number:true
	                }
	            },
	            messages: {
	                appId : {
	                    required: '请输入运费',
	                    number:'请输入正确的运费'
	                }
	            }
	        });
	    });
	}
});