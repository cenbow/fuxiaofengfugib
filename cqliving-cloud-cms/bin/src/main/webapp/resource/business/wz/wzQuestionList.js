define(['cloud.table.curd','cloud.time.input', "cqliving_ajax", "cqliving_dialog", 'ZeroClipboard', 'validator.bootstrap', 'ueditor', 'ueditorConfig', 'chosen'], function(tableCurd,timeInput, cq_ajax, cqliving_dialog, zcl) {
	return {
		init: function(){
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			//初始化APP选择列表框
			$(".chosen-select").chosen({search_contains:true});
			//这个我也不懂,看着别人加我也加了。
			window.ZeroClipboard = zcl;
			//初始化百度编辑器
			uEditor = UE.getEditor("reply-content", {initialFrameHeight: 300, textarea: 'content', zIndex: 9999});
			
			//审核
			$(document).on('click', '.checkBtn', function(){
				var me = $(this),
					modalObj = $('#checkModal');
				modalObj.find('input[name=questionId]').val(me.attr('data-id'));
				modalObj.find('textarea[name=content]').val('');
				modalObj.modal('show');
			});
			//审核提交
			$('#checkModal .submitBtn').on('click', checkSubmit);
			
			//批量审核
			$('#checkBatchBtn').click(function(){
				var jCheckedIds = $('input:checkbox[class=ace]:checked');
				if(jCheckedIds.length == 0){
					//如果没有选择则提示用户
					cqliving_dialog.alert("请选择需要审核的记录");
					return false;
				}else{
					var ids = [];
					jCheckedIds.each(function(i,t){
						var id = $(t).attr("id");
						if(id){
							ids.push(id);
						}
					});
					
					var modalObj = $('#checkModal');
					modalObj.find('input[name=questionId]').val(ids);
					modalObj.modal('show');
				}
				//return false;
			});
			
			//转交
			$(document).on('click', '.transferBtn', function(){
				var me = $(this),
					div = $('#transferModal');
				div.find('input[name=questionId]').val(me.attr('data-id'));
				$('#selectAuditingSelect').val('');
				$('#auditingDepartment').val('');
				$('#description').val('');
				div.modal('show');
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
			
			//回复
			$(document).on('click', '.replyBtn', function(){
				var me = $(this),
					div = $('#replyModal'),
					questionId = me.attr('data-id');
				
				div.find('input[name=questionId]').val(questionId);
				
				cq_ajax.ajaxOperate('wz_question_reply.html', null, {questionId: questionId}, function(data){
					var question = data.data;
					if(data.code == 0){
						if(question.replyContent){
							uEditor.setContent(question.replyContent);
						}else{
							uEditor.setContent('');
						}
//						if(question.replyContent != ''){
//							div.find('textarea[name=content]').val(question.replyContent);
//						}
						div.modal('show');
					}else{
						cqliving_dialog.error('网络异常！');
					}
				}, {type: 'GET'});
			});
			//回复提交
			$('#replyModal .submitBtn').click(submitReply);
			
			//转交
			$(document).on('click', '.returnButton', function(){
				$('#returnModal').modal('show');
			});
			$('#returnModal .submitBtn').click(submitReturn);
			
			//发布
			$(document).on('click', '.buttonPublish', submitPublish);
			//批量发布
			$('#batchPublish').click(batchPublish);
			//下线
			$(document).on('click', '.offlineButton', function(){
				onOffLineSubmit('off', $(this).attr('data-id'), $(this));
			});
			//上线
			$(document).on('click', '.onlineButton', function(){
				onOffLineSubmit('on', $(this).attr('data-id'), $(this));
			});
			//删除
			bindDeleteButton();
		}
	};
	
	//审核提交
	function checkSubmit(){
		var me = $(this),
			checkStatus = me.attr('data-status'),//是通过或不通过
			divObj = $('#checkModal'),
			form = divObj.find('form'),
			content = '';
		
		divObj.find('input[name=checkStatus]').val(checkStatus);
		cq_ajax.ajaxOperate('wz_question_check.html','',form.serialize(), function(data){
    		if(data.code == 0){
    			divObj.modal('hide');
    			cqliving_dialog.success("保存成功！", function(){
    				tableCurd.reloadNotChangePageNo();
				});
    		}else{
    			cqliving_dialog.error(data.message);
            	return false;
    		}
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
    				tableCurd.reloadNotChangePageNo();
				});
    		}else{
    			cqliving_dialog.error(data.message);
            	return false;
    		}
    	});
	}
	
	//回复提交
	function submitReply(){
		var modal = $('#replyModal'),
			auditingDepartment = modal.find('input[name=auditingDepartment]').val(),
//			content = modal.find('textarea[name=content]').val(),
			content = uEditor.getContent(),
			form = modal.find('form');
		if(auditingDepartment == ''){
			cqliving_dialog.alert('请输入受理部门');
			return false;
		}
		if(content == ''){
			cqliving_dialog.alert('请输入回复内容');
			return false;
		}
		cq_ajax.ajaxOperate('wz_question_reply.html',modal.find('.submitBtn'),form.serialize(), function(data){
    		if(data.code == 0){
    			modal.modal('hide');
    			cqliving_dialog.success("保存成功！", function(){
    				tableCurd.reloadNotChangePageNo();
				});
    		}else{
    			cqliving_dialog.error(data.message);
            	return false;
    		}
    	});
	}
	
	//驳回
	function submitReturn(){
		var modal = $('#returnModal'),
			content = modal.find('textarea[name=content]').val(),
			form = modal.find('form');
		if(content == ''){
			cqliving_dialog.alert('请输入驳回理由');
			return false;
		}
		cq_ajax.ajaxOperate('wz_question_return.html',modal.find('.submitBtn'), form.serialize(), function(data){
			if(data.code == 0){
				modal.modal('hide');
				cqliving_dialog.success("保存成功！", function(){
					tableCurd.reloadNotChangePageNo();
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
			url = me.attr('url');
		cqliving_dialog.confirm('确认信息', '确认发布？', function(){
			cq_ajax.ajaxOperate(url, me, {}, function(data){
				if(data.code == 0){
					cqliving_dialog.success("保存成功！", function(){
						tableCurd.reloadNotChangePageNo();
					});
				}else{
					cqliving_dialog.error(data.message);
		        	return false;
				}
			});
		});
	}
	//批量审核
	function batchPublish(){
		var me = $(this),
			url = me.attr('url');
		var jCheckedIds = $('input:checkbox[class=ace]:checked');
		if(jCheckedIds.length == 0){
			//如果没有选择则提示用户
			cqliving_dialog.alert("请选择需要发布的记录");
			return false;
		}else{
			var ids = [];
			jCheckedIds.each(function(i,t){
				var id = $(t).attr("id");
				if(id){
					ids.push(id);
				}
			});
			
			cqliving_dialog.confirm('确认信息', '确定要批量发布所选吗？', function(){
				cq_ajax.ajaxOperate(url, me, {ids: ids}, function(data){
					if(data.code == 0){
						cqliving_dialog.success("保存成功！", function(){
							tableCurd.reloadNotChangePageNo();
						});
					}else{
						cqliving_dialog.error(data.message);
			        	return false;
					}
				});
			});
		}
	}
	
	//上线和下线
	function onOffLineSubmit(type, ids, thisObj){
		cqliving_dialog.confirm('确认信息', type=='on' ? '确定要上线吗？' : '确定要下线吗？', function(){
			cq_ajax.ajaxOperate(thisObj.attr('url'), thisObj, {ids: ids}, function(data){
				if(data.code == 0){
					cqliving_dialog.success("保存成功！", function(){
						tableCurd.reloadNotChangePageNo();
					});
				}else{
					cqliving_dialog.error(data.message);
		        	return false;
				}
			});
		});
	}
	//删除
	function bindDeleteButton(){
		$(".main-content").on('click' ,'.deleteButton', function(){
			var jThis = $(this);
			var url = jThis.attr("url");
			cqliving_dialog.confirm('操作确认','确定要删除该记录吗？',function(){
				cq_ajax.ajaxOperate(url,jThis,{},function(data,status){
					if(data.code >= 0){
						cqliving_dialog.success(data.message?data.message:"删除成功",function(){
							tableCurd.reloadNotChangePageNo();
						});
					}else{
						cqliving_dialog.error(data.message?data.message:"删除失败");
					}
				});
			});
		});
	}
});