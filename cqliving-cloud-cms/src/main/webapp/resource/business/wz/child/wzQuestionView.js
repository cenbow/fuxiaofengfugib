define(['jquery', 'cqliving_ajax', 'colorbox', 'cqliving_dialog', 'cloud.table.curd', 'ZeroClipboard', 'ueditor', 'ueditorConfig'], function($, cq_ajax, colorbox, cqliving_dialog, tableCurd, zcl) {
	return {
		init: function(){
			tableCurd.bindBackRestoreParamButton();
			
			//这个我也不懂,看着别人加我也加了。
			window.ZeroClipboard = zcl;
			//初始化百度编辑器
			uEditor = UE.getEditor("reply-content", {
				readonly: true,
				textarea: 'content'
			});
			
			//图片查看
			var colorbox_params = {
				reposition:true,
				scalePhotos:true,
				scrolling:false,
				previous:'<i class="icon-arrow-left"></i>',
				next:'<i class="icon-arrow-right"></i>',
				close:'&times;',
				current:'{current} of {total}',
				maxWidth:'100%',
				maxHeight:'100%',
				onOpen:function(){
					document.body.style.overflow = 'hidden';
				},
				onClosed:function(){
					document.body.style.overflow = 'auto';
				},
				onComplete:function(){
					$.colorbox.resize();
				}
			};
			$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
			$("#cboxLoadingGraphic").append("<i class='icon-spinner orange'></i>");//let's add a custom loading icon
			
			//回复
			$('#replyButtonSubmit').click(replyButtonSubmit);
			
			//删除
			$(".main-content").on('click' ,'#deleteButton', function(){
				var jThis = $(this);
				var url = jThis.attr("url");
				cqliving_dialog.confirm('操作确认','确定要删除该记录吗？',function(){
					cq_ajax.ajaxOperate(url,jThis,{},function(data,status){
						if(data.code >= 0){
							cqliving_dialog.success(data.message?data.message:"删除成功",function(){
								tableCurd.backRestoreParam('/module/wz/child/wz_question_list.html');
							});
						}else{
							cqliving_dialog.error(data.message?data.message:"删除失败");
						}
					});
				});
			});
			
			//提交
			$(".main-content").on('click' ,'.submitButton', function(){
				var jThis = $(this);
				var url = jThis.attr("url");
				cqliving_dialog.confirm('操作确认','确定要提交吗？',function(){
					cq_ajax.ajaxOperate(url,jThis,{},function(data,status){
						if(data.code >= 0){
							cqliving_dialog.success(data.message?data.message:"提交成功",function(){
								tableCurd.backRestoreParam('/module/wz/child/wz_question_list.html');
							});
						}else{
							cqliving_dialog.error(data.message?data.message:"提交失败");
						}
					});
				});
			});
			

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
			
			//驳回
			$('.returnButton').click(function(){
				$('#returnModal').modal('show');
			});
			$('#returnModal .submitBtn').click(submitReturn);
			//新增需求，发布之后的也可以保存
			$('#replyPublishButtonSubmit').click(savePublishFn);
		}
	};
	
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
    				tableCurd.backRestoreParam('/module/wz/child/wz_question_list.html');
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
						tableCurd.backRestoreParam('/module/wz/child/wz_question_list.html');
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
    				tableCurd.backRestoreParam('/module/wz/child/wz_question_list.html');
				});
    		}else{
    			cqliving_dialog.error(data.message);
            	return false;
    		}
    	});
	}
	
	//驳回提交
	function submitReturn(){
		var modal = $('#returnModal'),
			content = modal.find('textarea[name=content]').val(),
			form = modal.find('form');
		if(content == ''){
			cqliving_dialog.alert('请输入驳回理由。');
			return false;
		}
		
		cq_ajax.ajaxOperate('wz_question_return.html',modal.find('.submitBtn'),form.serialize(), function(data){
    		if(data.code == 0){
    			modal.modal('hide');
    			cqliving_dialog.success("保存成功！", function(){
    				tableCurd.backRestoreParam('/module/wz/child/wz_question_list.html');
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
		cq_ajax.ajaxOperate('/module/wz/child/common/publishAfterSaveReply.html',me,form.serialize(), function(data){
			if(data.code == 0){
				cqliving_dialog.success("保存成功！", function(){
    				tableCurd.backRestoreParam('/module/wz/child/wz_question_list.html');
				});
			}else{
				cqliving_dialog.error(data.message);
	        	return false;
			}
		});
	}
});