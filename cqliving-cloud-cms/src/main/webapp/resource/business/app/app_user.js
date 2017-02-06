define(['validator.bootstrap','cqliving_dialog','cloud.table.curd'], function($,dialog,tableCurd){
	return {
		init:function(){
			tableCurd.bindSaveButton();
			/**
	    	 * 表单验证
	    	 */
	        $("#form1").validate({
	            rules: {
	                username : {
		                required: true
		            },password : {
		                required: true
		            },
		            confPwd: {
						required: true,
						equalTo: '#password'
					}
	            },
	            messages: {
	                username : {
	                	required: '请输入管理用户账号'
		            },password : {
	                	required: '请输入管理用户密码'
		            },confPwd: {
						required: '请再次输入密码',
						equalTo: '两次输入的密码必须要相同哟！'
					}
	            }
	        });
		}
	};
});