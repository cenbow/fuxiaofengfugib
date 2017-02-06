define(["cqliving_ajax", "cqliving_dialog","chosen"], function(cq_ajax, cqliving_dialog) {
	return {
		init: function() {
			//初始化APP选择列表框
			$(".chosen-select").chosen({search_contains:true});
			$('.chosen-select').change(selectAppChange);
			
			//单选按钮点击
			$('input[type=radio]').on('change', radioChange);
			//收集字段
			$(document).on('click', '.type-3-btn', type3Fn);
			$(document).on('click', 'input.isRequired', isRequiredFn);
			
			//保存
			$('#submitBtn').on('click', formSubmit);
		}
	};
	
	/**
	 * 收集字段处理
	 */
	function type3Fn(){
		var me = $(this),
			isDel = me.hasClass('btn-warning');
		//点击删除
		if(isDel){
			type3Remove(me);
		}else{
			type3Add(me);
		}
	}
	/**
	 * 添加
	 */
	function type3Add(me){
		var authorityId = me.parents('.input-group').find('input[name=authorityId]').val(),
//			parentDiv = me.parents('.form-group.parent-div'),
			parentDiv = $('div-id-' + authorityId),
			currentInput = me.parent().prev('input');
		
		//添加之前判断文本框是否有值，没有就不在添加
		if(currentInput.val() == ''){
			return false;
		}
		//把上一个文本框后面的按钮设置成删除样式
		me.removeClass('btn-primary').addClass('btn-warning');
		me.find('.icon-plus').removeClass('icon-plus').addClass('icon-minus');
		
		$('#div-id-' + authorityId).append(htmlCollect(authorityId));
		
		me.parents('.form-group.parent-div').find('.btn-primary').parent().prev('input[type=text]').focus()
	}
	/**
	 * 删除
	 * 删除的时候需要ajax验证这个标签是否被用过，如果已经使用是无法删除的。
	 */
	function type3Remove(me){
		var collectId = me.parent().prevAll('input[type=hidden]').val();
		if(collectId > 0){//需要验证数据库
			var url = '/module/wz/collectValidate.html';
			cq_ajax.ajaxOperate(url, me, {collentId: collectId}, function(data, status) {
				if(!data.data){
					me.parents('.div-collect').remove();
				}else{
					cqliving_dialog.error('已经使用，无法删除。');
				}
			});
		}else{//不需要验证，直接删除
			me.parents('.div-collect').remove();
		}
	}
	
	/**
	 * 选择是或否
	 */
	function radioChange(){
		var me = $(this),
			parentDiv = me.parents('.form-group'),
			id = parentDiv.data('id'),
			type = parentDiv.data('type'),
			divObj = $('#div-id-' + id);
		
		if(!(type == 2 || type == 3)){
			return ;
		}
		if(me.val() == 0){
			divObj.addClass('hide');
		}else if(me.val() == 1){
			divObj.removeClass('hide');
		}
	}
	
	function htmlCollect(authorityId){
		return '<div class="form-group div-collect">'+
			'<label class="col-xs-12 col-sm-3 col-md-2 control-label">收集信息名称</label>'+
			'<div class="col-sm-9 input-group">'+
				'<input type="hidden" value="'+ authorityId +'" name="authorityId"/>' + 
				'<input type="hidden" value="" name="collectInfoId"/>'+
				'<input type="text" placeholder="请输入内容" class="col-sm-10 input-mask-product" name="collectInfoName"/>'+
				'<label class="col-sm-2"><input type="checkbox" class="ace isRequired"><span class="lbl"> 必填</span><input type="hidden" name="isRequired" value="0"></label>'+
				'<span class="input-group-btn">'+
					'<button class="btn btn-xs btn-primary type-3-btn" type="button">'+
						'<i class="icon-plus"></i>'+
					'</button>'+
				'</span>'+
			'</div>'+
		'</div>';
	}
	
	/**
	 * 点击了是否必填
	 */
	function isRequiredFn(){
		var me = $(this);
		me.next().next().val(me.prop('checked') ? 1 : 0)
	}
	
	function formSubmit(){
		var me = $(this),
			div = $('.form-group.parent-div'),
			type2Obj = $('.div-type-2'),
			type3Obj = $('.div-type-3');
		var isError = false;
		$.each(div, function(i, data){
			if(!isError){
				isError = $(data).find('input[type=radio]:checked').length == 0;
			}
		});
		if(isError){
			cqliving_dialog.alert('有设置未选，请选择后提交。');
			return;
		}
		
		//如果type=2的选择是，那么内容必须不能为空
		var textareaObj = null;
		$.each(type2Obj, function(i, data){
			if($(data).find('input[type=radio]:checked').val() == 1 && $(data).find('textarea').val() == ''){
				textareaObj = $(data).find('textarea');
			}
		});
		if(textareaObj){
			cqliving_dialog.alert('请输入回复内容', function(){
				textareaObj.focus();
			});
			return;
		}
		//type=3的选择了是，那么收集内容不能为空
		var type3Value = false;
		$.each(type3Obj, function(i, data){
			var tmp = false;
			if($(data).find('input[type=radio]:checked').val() == 1){
				tmp = true;
				$.each($('#div-id-' + $(data).data('id')).find('input[type=text]'), function(x, obj){
					if(tmp && $(obj).val() != ''){
						tmp = false;
					}
				});
			}
			if(tmp && type3Value == false){
				type3Value = true;
			}
		});
		if(type3Value){
			cqliving_dialog.alert('收集信息至少要存在一个内容');
			return;
		}
		// 开始表单提交
		var myForm = $('#myForm');
		cq_ajax.ajaxOperate(myForm.attr('action'),me , myForm.serialize(), function(data, status) {
			if(data.code == 0){
				cqliving_dialog.success("保存成功！", function(){
					window.location.href = window.location.href;
				});
			}else{
				cqliving_dialog.error(data.message);
			}
		});
	}
	
	function selectAppChange(){
		location.href = '/module/wz/wz_authority_list.html?appId=' + $(this).find('option:selected').val();
	}
});