define(['validator.bootstrap','cloud.table.curd'], function($,tableCurd){
	return {
		init: function(){
			tableCurd.bindSaveButton();
			$('#form1').validate({
				onfocusout:false,
				onkeyup:false,
				onclick:false,
				focusInvalid:false,
				rules: {
					password: {
						required: true
					},
					confirmPwd: {
						required: true,
						equalTo: '#password'
					}
				},
				messages: {
					password: {
						required: '请输入密码'
					},
					confirmPwd: {
						required: '请重复密码',
						equalTo: '密码不一致，请重新输入'
					}
				}
			});
		}
	};
});