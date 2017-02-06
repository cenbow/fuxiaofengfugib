define(["validator.bootstrap", "cqliving_ajax", "cqliving_dialog", 'cloud.time.input','cloud.table.curd', 'common_colorbox'], function($, cq_ajax, cqliving_dialog, timeInput, tableCurd, colorbox) {
	return {
		init: function(){
			timeInput.initTimeInput();
			tableCurd.bindSaveButton();
			colorbox.init();
			//收集信息选择框
			$('#collentInfoEditBtn').click(function(){
				$('#collentInfoModal').modal('show');
			});
			
			$('#collentInfoModal .sureBtn').on('click', chooseCollectInfoFn);
			
			$("#form1").validate({
				 rules: {
					 startTimeTmp: {required: true},
					 endTimeTmp: {required: true}
				 },
				 messages: {
				 }
			});
			 //点击收集信息的时候必填复选框时，给隐藏的文本框设值
			 $(document).on('click', '.collectInfoRequired', function(){
				 $(this).siblings('input[name=collectInfoRequired]').val($(this).prop('checked') ? 1 : 0);
			 });
			 //点击保存
			 $('#actTestSaveButton').click(function(){
				 clickActTestSaveButton('/module/act/act_infoadd.html?id=' + $('#actInfoId').val(), null);
			 });
			 
			 
			 /**-------------分类管理-----------------**/
			//新增分类
			$('#addClassifyBtn').click(clickAddClassifyBtn);
			//编辑1
			$(document).on('click', '#classifyDiv .editBtn', clickEditBtn);
			//编辑2
			$(document).on('click', '#classifyDiv .classifyAddEditBtn', clickClassifyAddEditBtn);
			//上移、下移
			$(document).on('click', '#classifyDiv .sortBtn', clickSortBtn);
			//删除1
			$(document).on('click', '#classifyDiv .delBtn', clickDelBtn);
			//删除2
			$(document).on('click', '#classifyDiv .classifyAddDelBtn', clickClassifyAddDelBtn);
			
			//取消
			$(document).on('click', '.classifyCancelBtn', reset);
			//确定
			$(document).on('click', '.classifySureBtn', function(){clickSureBtn($(this));});
			//改变答题类型的时候切换是否需要多个分类信息
			$('#typeDiv input:radio').change(changeTypeFn);
			
			//excel上传
			$(document).on('click', '#classifyDiv .excelUploadBtn', function(){
				clickSureBtn($(this));
			});
			$(document).on('change', '#excelFile', excelUpload);
		}
	};
	
	//弹出框选择用户信息
	function chooseCollectInfoFn(){
		var chooseList = $('#collentInfoModal input:checkbox[class=ace]:checked'),
			oldList = $('#userCollentInfoDiv .content input:checkbox[class=ace]');
		$.each(chooseList, function(i, data){
			var tmp = true;
			$.each(oldList, function(j, oldData){
				if($(oldData).val() == $(data).val()){
					tmp = false;
				}
			});
			if(tmp){//去重
				_addCollect($(data).val(), $(data).data('text'));
			}
		});
		$('#collentInfoModal').modal('hide');
	}
	//添加收集信息到页面
	function _addCollect(id, value){
		var html = '<div>'+
			'<label></label>'+
			'<div class="checkbox col-xs-10">'+
				'<label class="col-xs-5"><input type="checkbox" name="collectInfoIds" checked class="ace" value="'+id+'"/><span class="lbl"> '+value+'</span></label>'+
				'<label class="col-xs-5"><input type="checkbox" class="ace collectInfoRequired" /><span class="lbl"> 必填</span><input type="hidden" name="collectInfoRequired" value="0"></label>'+
			'</div>'+
		'</div>',
		obj = $('#userCollentInfoDiv .content');
		if(obj.find('div').length > 0){
			html = '<hr />' + html; 
		}
		obj.append(html);
	}
	
	/**
	 * 保存
	 */
	function clickActTestSaveButton(returnUrl, classifyId){
		 var myForm = $('#form1');
		 if(!myForm.valid()){
			 return;
		 }
		 var limitAnswerTimes = $('input[name=limitAnswerTimes]').val();
		 if($('input[name=limitAnswerType]:checked').val() == 1 && (limitAnswerTimes == '' || limitAnswerTimes < 0 || limitAnswerTimes == 0)){
			 cqliving_dialog.error('请输入答题限制时间');
			 return ;
		 }
		 cq_ajax.ajaxOperate(myForm.attr('action'),myForm,myForm.serialize(),function(data,status){
			if(data.code >= 0){
				$('#actTestId').val(data.data);
				if(data.data != '' && parseInt(data.data) > 0){
					myForm.attr('action', '/module/act/act_testupdate.html');
				}
				if(returnUrl == 'excel'){
					fileClick(classifyId);
					return ;
				}
				cqliving_dialog.success(data.message?data.message:"保存成功",function(){
					if(returnUrl.indexOf('act_testupdate') > 0){
						returnUrl = returnUrl + '?id=' + data.data;
					}else{
						returnUrl = returnUrl + '&actTestId=' + data.data;
					}
					window.location.href = returnUrl;
				});
			}else{
				cqliving_dialog.error(data.message?data.message:"保存失败");
			}
		}, {async: false});
	 }
	
	/**----------------------分类管理------------------------**/
	//点击添加分类
	function clickAddClassifyBtn(){
		if($('#classifyDiv .showContent fieldset').length > 0){
			cqliving_dialog.error('请先保存正在编辑的分类')
			return ;
		}
		var html = $('#classifyAddDiv').html();
			html = '<div class="showContent">'+html+'</div>';
		reset();
		
		html = $(html);
		var sortNo = $('.showContent:last').find('input[name=sortNo]').val();
		if(sortNo){
			sortNo = parseInt(sortNo) + 1;
		}else{
			sortNo = 1;
		}
		html.find('input.sortNo').val(sortNo);
		
		$('#classifyDiv').append(html);
		
		//判断是普通答题还是分类答题
		if($('#typeDiv input:radio:checked').val() == 1){
			$(this).addClass('hide');
		}
	}
	//点击编辑1
	function clickEditBtn(){
		var showObj = $(this).parents('.showDiv');
		var addObj = $($('#classifyAddDiv').html());
		//获取值
		var title = showObj.find('span.title').html(),
			content = showObj.find('span.content').html(),
			classifyId = showObj.find('input[name=classifyId]').val(),
			isSetScore = showObj.find('input[name=isSetScore]').val(),
			sortNo = showObj.find('input[name=sortNo]').val();
		//设置值
		addObj.find('input.classifyId').val(classifyId);
		addObj.find('input.title').val(title);
		addObj.find('input.sortNo').val(sortNo);
		addObj.find('span.content').html(content);
		addObj.find('input[name=isSetScore][value='+isSetScore+']').prop('checked', true);
		
		reset();//去掉其他编辑状态
		showObj.hide();
		showObj.after(addObj);
	}
	//重置
	function reset(){
		$('#classifyDiv fieldset').remove();
		$('#classifyDiv .showDiv').show();
		$('#classifyDiv .showContent').each(function(i, data){
			if(($(data).html()).replace(/(^\s*)|(\s*$)/g, "") == ''){
				$(data).remove();
			}
		});
		$('#typeDiv input:radio').change();
		if($('#classifyDiv .showContent').length == 0){
			$('#addClassifyBtn').removeClass('hide');
		}
	}
	
	//点击了上移、下移的按钮
	function clickSortBtn(){
		var me = $(this),
			val = me.data('val'),
			selfDiv = me.parents('.showContent'),
			tmpDiv;
		if(val == 'up'){
			tmpDiv = selfDiv.prev();
		}else if(val == 'down'){
			tmpDiv = selfDiv.next();
		}else{
			return false;
		}
		if(!tmpDiv || tmpDiv.length == 0){
			return false;
		}
		//交换排序号
		var selfSort = selfDiv.find('input[name="sortNo"]').val(),
			tmpSort = tmpDiv.find('input[name="sortNo"]').val();
		selfDiv.find('input[name="sortNo"]').val(tmpSort);
		tmpDiv.find('input[name="sortNo"]').val(selfSort);
		//交换页面显示的位置
		if(val == 'up'){
			selfDiv.insertBefore(tmpDiv);
		}else if(val == 'down'){
			selfDiv.insertAfter(tmpDiv);
		}
	}
	
	//去编辑答题详细页面
	function clickClassifyAddEditBtn(){
		var me = $(this),
			myForm = $('form1'),
			fieldsetObj = me.closest('fieldset'),
			classifyId = fieldsetObj.find('input.classifyId').val();
		
		var type = me.hasClass('excelUploadBtn') ? 'excel' : 'edit';
		if(_isUpdate(type, classifyId)){//如果不是修改。继续下一步保存
			cqliving_dialog.confirm('确认信息', '只能保存当前页面后才能编辑，确定要继续吗？', function(){
				//先保存分类
				clickSureBtn(me);
			});
		}
	}
	
	
	//点击分类确定按钮
	function clickSureBtn(me){
		var parObj = {},
			isEditClick = false,//true：是点击编辑按钮， false：是点击的保存按钮
			fieldsetObj = me.closest('fieldset'),
			tmpObj = $($('#classifyAddSuccessDiv').html());
		
		//if(!fieldsetObj.is('fieldset')){// 是点击编辑按钮的时候
		if(me.hasClass('classifyAddEditBtn')){// 是点击编辑按钮的时候
			isEditClick = true;
			fieldsetObj = me.parent().parent().parent('fieldset');
		}
		var actTestId = 0;
		if($('#actTestId').length > 0){
			actTestId = $('#actTestId').val();
		}
		parObj.actTestId = actTestId;
		parObj.id = fieldsetObj.find('input.classifyId').val();
		parObj.sortNo = fieldsetObj.find('input.sortNo').val();
		parObj.title = fieldsetObj.find('input.title').val();
		parObj.subject = fieldsetObj.find('span.content').html();
		parObj.isSetScore = fieldsetObj.find('input[name=isSetScore]:checked').val();
		parObj.actInfoId = $('#actInfoId').val();
		parObj.actInfoListId = $('#actInfoListId').val();
		
		if(parObj.title == ''){
			cqliving_dialog.error('分类标题不能为空', function(){
				fieldsetObj.find('input.title').focus();
			});
			return false;
		}
		var type = me.hasClass('excelUploadBtn') ? 'excel' : 'edit';
		if(!me.hasClass('classifySureBtn') && !_isUpdate(type, parObj.id)){
			return ;
		}
		cq_ajax.ajaxOperate('/module/act/common/act_test_classifyadd.html', me, parObj, function(data){
			if(data.data != '' && data.data > 0){
				fieldsetObj.find('input.classifyId').val(data.data);
			}
			if(data.code >= 0){
				if(!me.hasClass('classifySureBtn') && !_isUpdate(type, data.data)){
					return ;
				}
				fieldsetObj.find('input.classifyId').val(data.data);
				if(isEditClick || type == 'excel'){
					//再保存当前页面的答题信息
					var returnUrl = '/module/act/common/act_test_questionlist.html?classifyId='+data.data;
					if(me.hasClass('excelUploadBtn')){//excel导入
						returnUrl = 'excel';
					}else if(me.hasClass('classifyAddEditBtn')){//编辑
						returnUrl = '/module/act/common/act_test_questionlist.html?classifyId='+data.data;
					}
					clickActTestSaveButton(returnUrl, data.data);
				}else{
					tmpObj.find('input[name=classifyId]').val(data.data);
					tmpObj.find('input[name=sortNo]').val(parObj.sortNo);
					tmpObj.find('.title').html(parObj.title);
					tmpObj.find('.content').html(parObj.subject);
					fieldsetObj.parent('.showContent').html(tmpObj);
				}
			}else{
				cqliving_dialog.error(data.message ? data.message : '保存失败');
			}
		}, {async: false});
	}
	
	function changeTypeFn(){
		if($('#typeDiv input:checked').val() == 1){
			if($('.showContent').length > 1){
				$('#typeDiv input').each(function(i, data){
					if($(data).val() != 1){
						$(data).prop('checked', 'checked');
					}else{
						$(data).removeAttr('checked');
					}
				});
				cqliving_dialog.error('您已经添加了多个分类');
				return false;
			}
			if($('.showContent').length > 0){
				$('#addClassifyBtn').addClass('hide');
			}
			$('.sortBtn').addClass('hide');
			$('.delBtn').addClass('hide');
		}else{
			$('#addClassifyBtn').removeClass('hide');
			$('.sortBtn').removeClass('hide');
			$('.delBtn').removeClass('hide');
		}
	}
	
	/**
	 * 删除1
	 */
	function clickDelBtn(){
		var me = $(this),
			showObj = me.parents('.showDiv'),
			classifyId = showObj.find('input[name=classifyId]').val();
		_delClassify(classifyId, me, function(){
			showObj.parent().remove();
			reset();
		});
	}
	/**
	 * 删除2
	 */
	function clickClassifyAddDelBtn(){
		var me = $(this),
			fieldsetObj = me.parent().parent().parent('fieldset'),
			classifyId = fieldsetObj.find('input.classifyId').val();
		_delClassify(classifyId , me, function(){
			fieldsetObj.parent().remove();
			reset();
		})
	}
	/**
	 * 删除
	 */
	function _delClassify(classifyId, el, fn){
		if(parseInt(classifyId) > 0){
			cq_ajax.ajaxOperate('/module/act/common/act_test_classifydelete.html', el, {id: classifyId}, function(data){
				if(data.code >= 0){
					cqliving_dialog.success('删除成功。');
					fn();
				}else{
					cqliving_dialog.error(data.message ? data.message : '删除失败！');
				}
			});
		}else{
			fn();
		}
	}
	
	function fileClick(classifyId){
		$('#excelFile').attr('data-classifyid', classifyId);
		$('#excelFile').click();
	}
	
	/**
	 * excel文件上传
	 */
	function excelUpload(){
		var me = $(this);
		var filePath = me.val();
		if(me.val()){
			var fileName = filePath.substring(filePath.lastIndexOf('\\') + 1);
			var config = {
    			allowSuffix : [ "xls","xlsx"]
    		};
    		var allowUpload = cq_ajax.checkSuffix(config, "excelFile");
    		if( !allowUpload){
    			var permitSuffix=config.allowSuffix.join(",");
    			cqliving_dialog.error("请选择“"+permitSuffix+"”格式的文件!");
    			return;
    		}
    		var classifyId = me.data('classifyid');
    		var url = me.data('url') + '?classifyId=' + classifyId + '&content='+fileName+'&time=' + new Date().getTime();
			cq_ajax.ajaxFileUpload(url, "excelFile", function(data){
				if(data.code >= 0){
					var actTestId = 0;
					if($('#actTestId').length > 0){
						actTestId = $('#actTestId').val();
					}
					$('.showContent>fieldset .content').html(fileName);
					confirm('提示信息', '上传成功, 是否查看', function(){
						window.location.href = '/module/act/common/act_test_questionlist.html?actTestId='+actTestId+'&classifyId=' + classifyId;
					}, function(){
						window.location.href = '/module/act/act_testupdate.html?id=' + actTestId;
					});
				}else{
					cqliving_dialog.alert(data.message ? data.message : '上传失败');
				}
			});
		}
	}
	
	/**
	 * 判断是不是修改
	 */
	function _isUpdate(type, classifyId){
		var actTestId = 0;
		if($('#actTestId').length > 0){
			actTestId = $('#actTestId').val();
		}
		if(actTestId != '' && parseInt(actTestId) > 0 && classifyId != '' && parseInt(classifyId) > 0){
			if(type == 'excel'){
				fileClick(classifyId);
			}else{
				window.location.href = '/module/act/common/act_test_questionlist.html?actTestId='+actTestId+'&classifyId=' + classifyId;
			}
			return false;
		}else{
			return true;
		}
	}
	
	
	/**
	 * 由于这里需要取消事件
	 */
	function confirm(title,content,fn,cancelFn){
		var options={
			title: title?title:'操作确认',
			content:content ? content:"确认该操作吗？",//没有可以不填写
			buttons: {
				ok: {  
	                label: "确定",  
	                callback: function (jthis,jModel) {
	                	$.isFunction(fn) ? fn(jthis,jModel) : "";
	                	jModel.modal('hide');
	                }
	            },
	            cancel: {
	            	label: "取消",
	                callback: function (jthis,jModel) {
	                	cancelFn();
	                	jModel.modal('hide');
	                }
	            }
	        } 
		};
		cqliving_dialog.model_dialog(options);
	}
	
});