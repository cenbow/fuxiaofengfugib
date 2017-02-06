define(['validator.bootstrap','cloud.table.curd','chosen'], function($,tableCurd){
	return {
		init: function(){
			tableCurd.bindSaveButton();
			$(".chosen-select").chosen({search_contains:true});
			//表单校验
			$("#form1").validate({
	            rules: {
	                    appId : {
	                    required: true,
	                    number:true
	                },
	                    name : {
	                    required: true
	                }
	            },
	            messages: {
	                appId : {
	                    required: '请选择客户端',
	                    number:' '
	                },
	                name : {
	                    required: '请输入名称'
	                }
	            }
	        });
		}
	};
});