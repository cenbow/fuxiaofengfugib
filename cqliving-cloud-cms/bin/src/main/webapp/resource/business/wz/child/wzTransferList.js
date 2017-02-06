define(["cqliving_ajax", "cqliving_dialog", 'ZeroClipboard', 'validator.bootstrap', 'ueditor', 'ueditorConfig'], function(cq_ajax, cqliving_dialog, zcl) {
	return {
		init: function(){
			//这个我也不懂,看着别人加我也加了。
			window.ZeroClipboard = zcl;
			//初始化百度编辑器
			uEditor = UE.getEditor("reply-content", {initialFrameHeight: 300, textarea: 'content', zIndex: 9999});
			
			//转交
			$(document).on('click', '.transferBtn', function(){
				var me = $(this),
					div = $('#transferModal');
				div.find('input[name=id]').val(me.attr('data-id'));
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
					id = me.attr('data-id');
				
				div.find('input[name=id]').val(id);
				
				cq_ajax.ajaxOperate('wz_question_reply.html', null, {id: id}, function(data){
					var question = data.data;
					if(data.code == 0){
//						if(question.auditingDepartment != '')
//							div.find('input[name=auditingDepartment]').val(question.auditingDepartment);
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
			
			//驳回
			$(document).on('click', '.returnButton', function(){
				$('#returnModal').find('input[name=id]').val($(this).attr('data-id'));
				$('#returnModal').modal('show');
			});
			$('#returnModal .submitBtn').click(submitReturn);
			
			//发布
			$(document).on('click', '.buttonPublish', submitPublish);
			
			//提交
			$(".main-content").on('click' ,'.submitButton', function(){
				var jThis = $(this);
				var url = jThis.attr("url");
				cqliving_dialog.confirm('操作确认','确定要提交吗？',function(){
					cq_ajax.ajaxOperate(url,jThis,{},function(data,status){
						if(data.code >= 0){
							cqliving_dialog.success(data.message?data.message:"提交成功",function(){
								$('#searchButton').click();
							});
						}else{
							cqliving_dialog.error(data.message?data.message:"提交失败");
						}
					});
				});
			});
			
			//批量提交
			$('#batchSubmitButton').click(batchSubmitSubmit);
			//批量发布
			$('#batchPublishButton').click(batchPublishSubmit);
		}
	};
	
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
    				$('#searchButton').click();
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
    				$('#searchButton').click();
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
					$('#searchButton').click();
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
						$('#searchButton').click();
					});
				}else{
					cqliving_dialog.error(data.message);
		        	return false;
				}
			});
		});
	}
	
	//批量提交
	function batchSubmitSubmit(){
		var jCheckedIds = $('input:checkbox[class=ace]:checked');
		if(jCheckedIds.length == 0){
			//如果没有选择商品则提示用户
			cqliving_dialog.alert("请选择需要提交的记录");
		}else{
			var ids = [];
			jCheckedIds.each(function(i,t){
				var id = $(t).attr("id");
				if(id){
					ids.push(id);
				}
			});
			if(ids.length>0){
				var jThis = $(this);//操作按钮
				var url = jThis.attr("url");
				//弹出确认对话框
				cqliving_dialog.confirm('操作确认','确定要批量提交这些记录吗？',function(){
					cq_ajax.ajaxOperate(url,jThis,{"ids":ids},function(data,status){
						if(data.code >= 0){
							cqliving_dialog.success(data.message?data.message:"批量提交成功",function(){
								$("#searchButton").trigger("click");
							});
						}else{
							cqliving_dialog.error(data.message?data.message:"批量提交失败");
						}
					});
				});
			}else{
				cqliving_dialog.alert("请选择需要提交的记录");
			}
		}
	}
	
	//批量发布
	function batchPublishSubmit(){
		var jCheckedIds = $('input:checkbox[class=ace]:checked');
		if(jCheckedIds.length == 0){
			//如果没有选择商品则提示用户
			cqliving_dialog.alert("请选择需要发布的记录");
		}else{
			var ids = [];
			jCheckedIds.each(function(i,t){
				var id = $(t).attr("id");
				if(id){
					ids.push(id);
				}
			});
			if(ids.length>0){
				var jThis = $(this);//操作按钮
				var url = jThis.attr("url");
				//弹出确认对话框
				cqliving_dialog.confirm('操作确认','确定要批量发布这些记录吗？',function(){
					cq_ajax.ajaxOperate(url,jThis,{"ids":ids},function(data,status){
						if(data.code >= 0){
							cqliving_dialog.success(data.message?data.message:"批量发布成功",function(){
								$("#searchButton").trigger("click");
							});
						}else{
							cqliving_dialog.error(data.message?data.message:"批量发布失败");
						}
					});
				});
			}else{
				cqliving_dialog.alert("请选择需要发布的记录");
			}
		}
	}
});