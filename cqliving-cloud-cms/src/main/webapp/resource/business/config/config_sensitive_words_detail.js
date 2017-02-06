define(['validator.bootstrap','cloud.table.curd','chosen'], function($,tableCurd){
	return {
		init: function(){
			tableCurd.bindSaveButton();
			$(".chosen-select").chosen({search_contains:true});
			$("#form1").validate({
	            rules: {
	                    name : {
	                    required: true
	                },
	                    type : {
	                    required: true
		            },
		            	appId : {
		            	required: true
		            }
	            },
	            messages: {
	                name : {
	                    required: '请输入敏感词'
	                },
	                type : {
	                    required: '请选择敏感词类型'
	                },
		            	appId : {
		            	required: '请选择App'
		            }
	            }
	        });
		}
	};
});