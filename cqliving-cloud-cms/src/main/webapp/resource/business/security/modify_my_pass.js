define(['cqliving_ajax', 'cqliving_dialog', 'validator.bootstrap'], function(cq_ajax, cq_dialog){
	return {
		init: function(){
	        $("#form1").validate({
	            rules: {
	                oldPassword : {required: true},
			        password : {required: true},
			        rePassword : {required: true, equalTo: '#password'}
	            },
	            messages: {
	            	oldPassword : {required: '请输入原密码'},
	            	password : {required: '请输入新密码'},
	            	rePassword : {required: '再次输入新密码', equalTo: '两次密码输入不一致'}
	            }
	        });
	        
	        $('#saveBtn').click(function(){
	        	var me = $(this),
	        		myForm = $('#form1'),
	        		url = myForm.attr('action');
	        	if(!myForm.valid()){
	        		return;
	        	}
	        	cq_ajax.ajaxOperate(url,me,myForm.serialize(),function(data,status){
					if(data.code >= 0){
						me.unbind("click");
						cq_dialog.success(data.message?data.message:"修改成功，请用新密码重新登录",function(){
							window.location.href = '/logout.html';
						});
					}else{
						cq_dialog.error(data.message?data.message:"密码修改失败");
					}
				});
	        });
		}
	}
});