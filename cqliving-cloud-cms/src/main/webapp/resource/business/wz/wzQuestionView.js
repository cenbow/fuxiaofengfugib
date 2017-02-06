define(['jquery', 'cqliving_ajax', 'common_colorbox', 'cqliving_dialog', 'cloud.table.curd', 'ZeroClipboard', 'ueditor', 'ueditorConfig'], function($, cq_ajax, colorbox, cqliving_dialog, tableCurd, zcl) {
	return {
		init: function(){
			tableCurd.bindBackRestoreParamButton();
			colorbox.init();
			
			//这个我也不懂,看着别人加我也加了。
			window.ZeroClipboard = zcl;
			//初始化百度编辑器
			uEditor = UE.getEditor("reply-content", {
				readonly: true,
				textarea: 'content'
			});
			
			//审核
			$(document).on('click', '.checkBtn', function(){
				var me = $(this),
					modalObj = $('#checkModal');
				modalObj.find('input[name=questionId]').val(me.attr('data-id'));
				modalObj.modal('show');
			});
			//审核提交
			$('#checkModal .submitBtn').on('click', checkSubmit);
			
			//回复
			$('#replyButtonSubmit').click(replyButtonSubmit);
			
			//发布
			$(document).on('click', '.buttonPublish', submitPublish);
			
			//转交
			$(document).on('click', '.transferBtn', function(){
				$('#transferModal').modal('show');
			});
			//转交时改变受理部门
			$('#selectAuditingSelect').change(function(){
				if($(this).val() != ''){
					var text = $('#selectAuditingSelect option:selected').text();
					$('#auditingDepartment').val(text);
				}
			});
			//转交提交
			$('#transferModal .submitBtn').click(submitTransfer);
			
			//删除
			$(".main-content").on('click' ,'#deleteButton', function(){
				var jThis = $(this);
				var url = jThis.attr("url");
				cqliving_dialog.confirm('操作确认','确定要删除该记录吗？',function(){
					cq_ajax.ajaxOperate(url,jThis,{},function(data,status){
						if(data.code >= 0){
							cqliving_dialog.success(data.message?data.message:"删除成功",function(){
								tableCurd.backRestoreParam('/module/wz/wz_question_list.html');
							});
						}else{
							cqliving_dialog.error(data.message?data.message:"删除失败");
						}
					});
				});
			});
			//新增需求，发布之后的也可以保存
			$('#replyPublishButtonSubmit').click(savePublishFn);
		}
	};
	//审核提交
	function checkSubmit(){
		var me = $(this),
			checkStatus = me.attr('data-status'),//是通过或不通过
			divObj = $('#checkModal'),
			form = divObj.find('form'),
			content = divObj.find('textarea').val();
		
		divObj.find('input[name=checkStatus]').val(checkStatus);
		if(!content){
			cqliving_dialog.alert('请输入审核内容');
			return false;
		}
		cq_ajax.ajaxOperate('wz_question_check.html','',form.serialize(), function(data){
    		if(data.code == 0){
    			divObj.modal('hide');
    			cqliving_dialog.success("保存成功！", function(){
    				tableCurd.backRestoreParam('/module/wz/wz_question_list.html');
				});
    		}else{
    			cqliving_dialog.error(data.message);
            	return false;
    		}
    	});
		
	}
	
	//回复
	function replyButtonSubmit(){
		var me = $(this),
			div = $('#replyDiv'),
			auditingDepartment = div.find('input[name=auditingDepartment]').val(),
//			content = div.find('textarea[name=content]').val(),
			content = uEditor.getContent(),
			form = div.find('form');
		if(content == ''){
			cqliving_dialog.error('请输入回复内容');
			return ;
		}
		
		cq_ajax.ajaxOperate('wz_question_reply.html',me,form.serialize(), function(data){
    		if(data.code == 0){
    			cqliving_dialog.success("保存成功！", function(){
    				tableCurd.backRestoreParam('/module/wz/wz_question_list.html');
				});
    		}else{
    			cqliving_dialog.error(data.message);
            	return false;
    		}
    	});
	}
	
	//发布
	function submitPublish(){
		var me = $(this),
			id = me.attr('data-id'),
			url = me.attr('url');
		cqliving_dialog.confirm('确认信息', '确认发布？', function(){
			cq_ajax.ajaxOperate(url, me, {questionId: id}, function(data){
				if(data.code == 0){
					cqliving_dialog.success("保存成功！", function(){
						tableCurd.backRestoreParam('/module/wz/wz_question_list.html');
					});
				}else{
					cqliving_dialog.error(data.message);
		        	return false;
				}
			});
		});
	}
	
	//转交提交
	function submitTransfer(){
		var modal = $('#transferModal'),
			currentUserId = modal.find('select[name=currentUserId]').val(),
			auditingDepartment = modal.find('input[name=auditingDepartment]').val(),
			description = modal.find('textarea[name=description]').val(),
			form = modal.find('form');
		if(currentUserId == ''){
			cqliving_dialog.alert('请选择受理人');
			return false;
		}
		if(auditingDepartment == ''){
			cqliving_dialog.alert('请输入受理部门');
			return false;
		}
		cq_ajax.ajaxOperate('wz_question_transfer.html',modal.find('.submitBtn'),form.serialize(), function(data){
    		if(data.code == 0){
    			modal.modal('hide');
    			cqliving_dialog.success("保存成功！", function(){
    				tableCurd.backRestoreParam('/module/wz/wz_question_list.html');
				});
    		}else{
    			cqliving_dialog.error(data.message);
            	return false;
    		}
    	});
	}
	
	/**
	 * 发布后的保存
	 */
	function savePublishFn(){
		var me = $(this),
			div = $('#replyDiv'),
			auditingDepartment = div.find('input[name=auditingDepartment]').val(),
			content = div.find('textarea[name=content]').val(),
			form = div.find('form');
		if(auditingDepartment == ''){
			cqliving_dialog.error('请输入受理部门');
			return;
		}
		if(content == ''){
			cqliving_dialog.error('请输入回复内容');
			return;
		}
		cq_ajax.ajaxOperate('/module/wz/common/publishAfterSaveReply.html',me,form.serialize(), function(data){
			if(data.code == 0){
				cqliving_dialog.success("保存成功！", function(){
					tableCurd.backRestoreParam('/module/wz/wz_question_list.html');
				});
			}else{
				cqliving_dialog.error(data.message);
	        	return false;
			}
		});
	}
});