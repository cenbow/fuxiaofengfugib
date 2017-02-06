//require(['cloud.table.curd','cloud.time.input', "cqliving_ajax", "cqliving_dialog"], function(tableCurd,timeInput, cq_ajax, cqliving_dialog){
define(["cqliving_ajax", "cqliving_dialog", 'validator.bootstrap'], function(cq_ajax, cqliving_dialog) {
	return {
		init: function(){
			//审核
			$(document).on('click', '.checkBtn', function(){
				var me = $(this),
					modalObj = $('#checkModal');
				modalObj.find('input[name=questionId]').val(me.attr('data-id'));
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
				div.modal('show');
			});
			$('#transferModal .submitBtn').click(submitTransfer);
		}
	};
	
	
	function checkSubmit(){
		var me = $(this),
			checkStatus = me.attr('data-status'),//是通过或不通过
			divObj = $('#checkModal'),
			form = divObj.find('form');
		
		divObj.find('input[name=checkStatus]').val(checkStatus);
		
		cq_ajax.ajaxOperate('wz_question_check.html','',form.serialize(), function(data){
    		if(data.code == 0){
    			cqliving_dialog.success("保存成功！", function(){
					window.location.href = window.location.href;
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
    			cqliving_dialog.success("保存成功！", function(){
					window.location.href = window.location.href;
				});
    		}else{
    			cqliving_dialog.error(data.message);
            	return false;
    		}
    	});
	}
});