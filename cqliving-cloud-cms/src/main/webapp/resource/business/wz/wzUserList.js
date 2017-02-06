define(["cqliving_ajax", "cqliving_dialog", 'cloud.table.curd', 'validator.bootstrap'], function(cq_ajax, cqliving_dialog, tableCurd) {
	return {
		init: function() {
			tableCurd.initTableCrud();
			
			//打开修改密码模态窗口
			$(document).on('click', '.resetPassButton', resetPassword);
			//修改密码提交
			$(document).on('click', '#modifyPasswordButton', modifySubmit);
			//重置修改密码的模态窗口
			$('#resetPasswordModal').on('hide.bs.modal', function(){
				$('#modifyPasswordForm')[0].reset();
			});
			
			$(function(){
				//修改密码表单验证
				$('#resetPasswordForm').validate({
					rules: {
						password: {
							required: true
						},
						repassword: {
							equalTo: '#repassword'
						}
					},
					message: {
						password: {
							required: true
						},
						repassword: {
		        			equalTo: '两次密码不一致'
		        		}
					}
				});
			})
		}
	};
	
	//重置密码
	function resetPassword(){
		var me = $(this),
			modalObj = $('#resetPasswordModal'),
			form = $('#modifyPasswordForm');
		form[0].reset();
		
		modalObj.find('input[name=id]').val(me.attr('data-id'));
		modalObj.find('input[name=orgName]').val(me.attr('data-orgName'));
		modalObj.modal('show');
	}
	//提交修改密码
	function modifySubmit(){
		var  me = $(this);
		var form = $('#modifyPasswordForm');
		cq_ajax.ajaxOperate(form.attr('action'),me , form.serialize(), function(data, status) {
			if(data.code == 0){
				$('#resetPasswordModal').modal('hide');
				cqliving_dialog.success("保存成功！");
			}else{
				cqliving_dialog.error(data.message);
			}
		});
	}
});
