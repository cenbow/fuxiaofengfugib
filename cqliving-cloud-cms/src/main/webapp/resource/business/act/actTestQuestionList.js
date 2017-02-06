define(['jquery', 'cqliving_dialog', 'cqliving_ajax', 'myUploader', 'common_colorbox', 'jquery.nestable'], function($, cq_dialog, cq_ajax, myUploader, colorbox) {
	return {
		init: function(){
			colorbox.init();
			
			//初始化插件
			$('#nestable').nestable({maxDepth:2, rootClass: 'rootClass', noJumpDrag: true});
			//初始化插件--点击按钮控制
			$('.dd-handle button').on('mousedown', function(e){e.stopPropagation();});
			//初始化插件--更改排序
			$('#nestable').on('change', changeSortFn);
			
			//点击添加问题
			$('#addQuestionBtn').on('click', clickAddQuestionBtn);
			//点击编辑问题
			$(document).on('click', '.editQuestionBtn', clickEditQuestionBtn);
			//点击删除问题
			$(document).on('click', '.delQuestionBtn', clickDelQuestionBtn);
			//点击添加问题--保存
			$('#addQuestionModal .submitBtn').on('click', addQuestionSave);
			
			//点击添加答案
			$(document).on('click', '.addAnswerBtn', clickAddAnswerBtn);
			//点击编辑答案
			$(document).on('click', '.editAnswerBtn', clickEditAnswerBtn);
			//点击删除答案--和删除问题调同一个方法。
			$(document).on('click', '.delAnswerBtn', clickDelQuestionBtn);
			//点击添加答案--保存
			$('#addAnswerModal .submitBtn').on('click', addAnswerSave);
			//是否设为正确答案
			$('#addAnswerModal .isRightAnswer').on('change', function(){$(this).next().next().val($(this).prop('checked') ? 1 : 0);});
			
			//初始化上传控件
			initImageUploader();
			//点击展开和收缩
			$('#expandQuestion').on('click', expandQuestion);
		}
	};
	
	/**
	 * 更改排序
	 */
	function changeSortFn(){
		var me = $(this),
			list = $('#nestable').nestable('serialize'),
			actTestClassifyId =$('#actTestClassifyId').val();
		var questionList = [],
		answerList = [];
		for(var i = 0 ; i < list.length; i ++){
			questionList.push({classifyId: actTestClassifyId, id: list[i].id, sortNo: (i+1)});
			var children = list[i].children;
			for(var j = 0; j < children.length; j ++){
				answerList.push({actTestQuestionId: list[i].id, id: children[j].id, sortNo: (j+1)});
			}
		}
		cq_ajax.ajaxOperate('/module/act/common/act_test_questionsort.html', null, {questionList: JSON.stringify(questionList), answerList: JSON.stringify(answerList) }, function(data){
			if(data.code < 0){
				cq_dialog.error(data.message ? data.message : '排序失败。', function(){
					_refreshPage();
				});
			}
		});
	}
	
	/**
	 * 添加问题
	 */
	function clickAddQuestionBtn(){
		var modal = $('#addQuestionModal'),
			myForm = modal.find('form'),
			lastSortNo = $('#nestable>ol>li:last').data('sort-no');
		
		lastSortNo = isNaN(parseInt(lastSortNo)) ? 0 : parseInt(lastSortNo);
		lastSortNo = lastSortNo + 1;
		
		modal.find('.ng-binding').html('添加问题');
		myForm[0].reset();
		myForm.attr('action', '/module/act/common/act_test_questionadd.html');
		myForm.find('input[name=id]').val('');
		//初始化排序
		myForm.find('input[name=sortNo]').val(lastSortNo);
		$('#questionImgList').html('');
		modal.modal('show');
		_initModalUploadBtn('questionImgUpload');
	}

	/**
	 * 问题--保存
	 */
	function addQuestionSave(){
		var me = $(this),
			modal = $('#addQuestionModal'),
			myForm = modal.find('form'),
			url = myForm.attr('action'),
			reg = /^\s+|\s+$/g;
		
		var question = myForm.find('textarea[name=question]').val(),
			score = myForm.find('input[name=score]').val();
		if(question.replace(reg, '') == ''){
			cq_dialog.error('请输入问题描述。');
			return ;
		}
		if(typeof score != 'undefined' && (isNaN(score) || score == '' || parseInt(score) <= 0)){
			cq_dialog.error('请输入分值。');
			return ;
		}
		cq_ajax.ajaxOperate(myForm.attr('action'), me, myForm.serialize(), function(data){
			if(data.code >= 0){
				modal.modal('hide');
				cq_dialog.success('问题保存成功。', function(){_refreshPage();});
			}else{
				cq_dialog.error(data.message ? data.message : '问题保存失败。');
			}
		});
	}
	
	/**
	 * 编辑问题
	 */
	function clickEditQuestionBtn(){
		var me = $(this),
			id = me.parent().parent().data('id');
		if(id == '' || id < 1){
			cq_dialog.error('错误的操作。');
			return ;
		}
		cq_ajax.ajaxOperate('/module/act/common/act_test_questionupdate.html', me, {id: id}, function(data){
			if(data.code >= 0){
				var item = data.data,
					modal = $('#addQuestionModal'),
					myForm = modal.find('form');

				modal.find('.ng-binding').html('编辑问题');
				myForm.attr('action', '/module/act/common/act_test_questionupdate.html');
				myForm.find('input[name=id]').val(item.id);
				myForm.find('textarea[name=question]').val(item.question);
				myForm.find('input[name=sortNo]').val(item.sortNo);
				myForm.find('input[name=score]').val(item.score);
				myForm.find('input[name=type]').removeAttr('checked');
				myForm.find('input[name=type][value='+item.type+']').prop('checked' , true);
				
				modal.modal('show');
				_initModalUploadData('questionImgList', item.imageUrl);
				_initModalUploadBtn('questionImgUpload');
			}else{
				cq_dialog.error(data.message ? data.message : '查看问题失败。');
			}
		},{type: 'GET'});
	}
	
	/**
	 * 删除问题
	 */
	function clickDelQuestionBtn(){
		var me = $(this),
			id = me.parent().parent().data('id');
		if(id == '' || id < 1){
			cq_dialog.error('错误的操作。');
			return ;
		}
		cq_dialog.confirm('确认信息', '确定要删除吗？', function(){
			cq_ajax.ajaxOperate(me.data('url'), me, {id: id}, function(data){
				if(data.code >= 0){
					cq_dialog.success('删除成功。', function(){
						me.parent().parent().remove();
					});
				}else{
					cq_dialog.error(data.message ? data.message : '删除失败。');
				}
			});
		});
	}
	
	/**
	 * 添加答案
	 */
	function clickAddAnswerBtn(){
		var me = $(this),
			modal = $('#addAnswerModal'),
			myForm = modal.find('form'),
			questionId = me.parent().parent().data('id'),
			lastSortNo = me.parent().next().find('li:last').data('sort-no');
		
		lastSortNo = isNaN(parseInt(lastSortNo)) ? 0 : parseInt(lastSortNo);
		lastSortNo = lastSortNo + 1;
		
		modal.find('.ng-binding').html('添加答案');
		myForm[0].reset();
		myForm.attr('action', '/module/act/common/act_test_answeradd.html');
		myForm.find('input[name=actTestQuestionId]').val(questionId);
		myForm.find('input[name=id]').val('');
		//初始化排序
		myForm.find('input[name=sortNo]').val(lastSortNo);
		$('#answerImgList').html('');
		modal.modal('show');
		_initModalUploadBtn('answerImgUpload');
	}
	
	/**
	 * 答案--保存
	 */
	function addAnswerSave(){
		var me = $(this),
			modal = $('#addAnswerModal'),
			myForm = modal.find('form'),
			url = myForm.attr('action'),
			reg = /^\s+|\s+$/g;
		
		var answer = myForm.find('input[name=answer]').val();
		if(answer.replace(reg, '') == ''){
			cq_dialog.error('请输入答案。');
			return ;
		}
		cq_ajax.ajaxOperate(myForm.attr('action'), me, myForm.serialize(), function(data){
			if(data.code >= 0){
				modal.modal('hide');
				cq_dialog.success('保存成功。', function(){_refreshPage();});
			}else{
				cq_dialog.error(data.message ? data.message : '保存失败。');
			}
		});
	}
	
	/**
	 * 编辑答案
	 */
	function clickEditAnswerBtn(){
		var me = $(this),
			id = me.parent().parent().data('id');
		if(id == '' || id < 1){
			cq_dialog.error('错误的操作。');
			return ;
		}
		cq_ajax.ajaxOperate('/module/act/common/act_test_answerupdate.html', me, {id: id}, function(data){
			if(data.code >= 0){
				var item = data.data,
					modal = $('#addAnswerModal'),
					myForm = modal.find('form');
	
				modal.find('.ng-binding').html('编辑答案');
				myForm.attr('action', '/module/act/common/act_test_answerupdate.html');
				myForm.find('input[name=id]').val(item.id);
				myForm.find('input[name=actTestQuestionId]').val(item.actTestQuestionId);
				myForm.find('input[name=answer]').val(item.answer);
				myForm.find('textarea[name=answerDesc]').val(item.answerDesc);
				myForm.find('input[name=sortNo]').val(item.sortNo);
				
				myForm.find('input.isRightAnswer').prop('checked' , item.isRightAnswer == 1);
				myForm.find('input[name=isRightAnswer]').val(item.isRightAnswer);
				
				modal.modal('show');
				_initModalUploadData('answerImgList', item.imageUrl);
				_initModalUploadBtn('answerImgUpload');
			}else{
				cq_dialog.error(data.message ? data.message : '查看问题失败。');
			}
		},{type: 'GET'});
	}
	
	/**
	 * 初始化上传控件
	 */
	function initImageUploader(){
		//初始化问题上传图片上传控件
		myUploader.init({
			url:"/common/upload.html",
			fileUrlPath: imageUrl,
			containerId:"questionImgUpload",
			thumbContainerId: "questionImgList",
			isSingle: false,
			width: '80px',
			height: '80px',
			fileNumLimit: 3,
			success:function(file,response){
				var imgPath = imageUrl + response.data.filePath;
//				$('#questionImgList .upload-imgs:last img').attr('src', imgPath);
				$('#questionImgList .upload-imgs:last').append('<input type="hidden" name="imageUrl" value="'+imgPath+'">');
			},
			error:function(file,reason){
				cq_dialog.alert(reason);
			}
		});
		//初始化答案上传图片控件
		myUploader.init({
			url:"/common/upload.html",
			fileUrlPath: imageUrl,
			containerId:"answerImgUpload",
			thumbContainerId: "answerImgList",
			isSingle: false,
			width: '80px',
			height: '80px',
			fileNumLimit: 3,
			success:function(file,response){
				var imgPath = response.data.filePath;
//				$('#answerImgList .upload-imgs:last img').attr('src', imgPath);
				$('#answerImgList .upload-imgs:last').append('<input type="hidden" name="imageUrl" value="'+imgPath+'">');
			},
			error:function(file,reason){
				cq_dialog.alert(reason);
			}
		});
	}
	/**
	 * 初始化模态窗上传按钮
	 */
	function _initModalUploadBtn($id){
		if($id){
			var tmpDivObj = $("#"+$id+">div:nth-child(2)");
				tmpDivObj.css('left', '11px');
				tmpDivObj.css('width', '100px');
				tmpDivObj.css('height', '43px');
		}
	}
	
	/**
	 * 初始化模态窗图片数据 修改回显
	 * imagesStrs: 字符串图片路径，多个用“,”分割
	 */
	function _initModalUploadData($id, imagesStrs){
		if($id == '' || imagesStrs == '' || imagesStrs == null || imagesStrs == 'null'){
			return false;
		}
		var listDivObj = $('#' + $id),
			imgArray = imagesStrs.split(','),
			len = imgArray.length;
		var html = '<ul class="ace-thumbnails">';
		for(var i = 0; i < len; i ++){
			html += '<li id="WU_FILE_'+i+'" class="upload-imgs" style="width:80px;height:80px;">';
				html += '<a href="'+imgArray[i]+'" data-rel="colorbox">';
					html += '<img alt="80x80" src="'+imgArray[i]+'" style="width:80px;height:80px;">';
				html += '</a>';
				html += '<div class="tools tools-top">';
					html += '<a href="javascript:;" onclick="javascript:$(this).parent().parent().remove();"><i class="icon-remove red"></i></a>';
				html += '</div>';
				html += '<input type="hidden" name="imageUrl" value="'+imgArray[i]+'">';
			html += '</li>';
		}
		html += '</ul>';
		if(len > 0){
			listDivObj.html(html);
		}
		colorbox.init();
	}
	function _refreshPage(type){
		location.href = location.href;
	}
	
	/**
	 * 展开全部和收缩全部
	 */
	function expandQuestion(){
		var me = $(this);
		if(me.data('action') == 'expand'){
			$('#nestable').nestable('collapseAll');
			me.data('action', 'collapse');
			me.find('i').removeClass('icon-chevron-down').addClass('icon-chevron-right');
			me.find('span').text('展开');
		}else{
			$('#nestable').nestable('expandAll');
			me.data('action', 'expand');
			me.find('i').removeClass('icon-chevron-right').addClass('icon-chevron-down');
			me.find('span').text('收缩');
		}
	}
});
