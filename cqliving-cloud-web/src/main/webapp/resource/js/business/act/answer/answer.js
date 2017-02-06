define(["jquery", 'cqliving_ajax'], function($, cq_ajax) {
	return {
		init: function(){
			//投票选项图片高度
			var vote_list_pic_width=$(".vote_list_pic").width();
			$(".vote_list_pic").height(3*vote_list_pic_width/4);
			
			$('.input-checkbox').on('change', checkboxClick);
			//单选框的样式，复选框的type
			$('.input-radio-checkbox').on('change', radioCheckboxClick);
			
			//开始答题、重新答题
			$('.startAnswer').on('touchstart', startAnswer);
		}
	};
	
	/**
	 * 因为多个单选的时候只能选择一个选项，所以样式是单选样式，但input的type是checkbox。手动处理单选。
	 */
	function radioCheckboxClick(){
		var me = $(this);
		me.closest('.survey_radio').find('.input-radio-checkbox:checked').prop('checked', false);
		me.prop('checked', true);
	}
	/**
	 * 点击checkbox
	 */
	function checkboxClick(){
		var me = $(this),//当前checkbox
			checkedValue = me.val(),//当前checkbox的值
			checked = me.prop('checked'),//当前checkbox是选中还是取消选中
			input = me.closest('div').find('input[name=optionValues]'),
			maxlength = me.data('maxlength'),
			inputValue = input.val(),
			inputValues = inputValue == '' ? [] : inputValue.split('a');
		
		//验证长度
		if(!isNaN(maxlength)){
			var selectLength = me.closest('div').find('input[type=checkbox]:checked').length;
			if(selectLength > maxlength){
				me.prop('checked', false);
				ZWY_ClOUD.alert('最多只能选择' + maxlength + '项');
				return ;
			}
		}
		var lastValue = '';
		if(checked){//选中
			inputValues.push(checkedValue);
		}else{//取消选中
			var val = [];
			$.each(inputValues, function(i, data){
				if(data != checkedValue)
					val.push(data);
			});
			inputValues = val;
		}
		$.each(inputValues, function(i, data){
			if(lastValue != '')
				lastValue += 'a';
			lastValue += data;
		});
		input.val(lastValue);
	}
	
	/**
	 * 
	 */
	function jumpToNextpage(){
		var myForm = $('#myForm'),
			appId = myForm.find('input[name=appId]').val(),
			sessionId = myForm.find('input[name=sessionId]').val(),
			token = myForm.find('input[name=token]').val(),
			actInfoId = myForm.find('input[name=actInfoId]').val(),
			actInfoListId = myForm.find('input[name=actInfoListId]').val(),
			actTestId = myForm.find('input[name=actTestId]').val();
		var url = '/act/answer/answer_classify_list.html?appId=' + appId + '&sessionId=' + sessionId + '&type=1&actInfoId=' + actInfoId + '&actTestId=' + actTestId + '&actInfoListId=' + actInfoListId;
		if(token != ''){
			url += '&token=' + token;
		}
//		window.location.href = url;
		redirectUrl(url);
	}
	/**
	 * 开始答题
	 */
	function startAnswer(){
		tmpParamsObj = [];
		var myForm = $('#myForm');
		//先保存用等登录前输入的信息，以免登陆回来后要重新输入内容
		var inputs = myForm.find('[id^=tmpIndex_]');
		$.each(inputs, function(){
			var type = $(this).attr('type');
			if(type == 'text'){
				tmpParamsObj.push({type: type, id: $(this).attr('id'), value: $(this).val()});
			}else if(type == 'radio'){
				tmpParamsObj.push({type: type, id: $(this).attr('id'), value: $(this).prop('checked')});
			}else if(type == 'select'){
				tmpParamsObj.push({type: type, id: $(this).attr('id'), value: $(this).val()});
			}else if(type == 'checkbox'){
				tmpParamsObj.push({type: type, id: $(this).attr('id'), value: $(this).prop('checked')});
			}
		});
		var me = $(this);
		try{
			ZWY_ClOUD.getSessionToken("setSessionToken");
		}catch(e){}
		
		setTimeout(function(){
			myForm = $('#myForm');
			var requiredDiv = myForm.find('div[required]'),//单选、多选类型
				requiredSelect = myForm.find('select[required]'),//下拉类型
				requiredInput = myForm.find('input[required]'),//文本类型
				token = myForm.find('input[name=token]').val(),
				isLogin = myForm.find('input[name=isLogin]').val();
			if(isLogin == '1' && token == ''){
				ZWY_ClOUD.login();
				return;
			}
			if(myForm.find('input[name=isUpdate]').val() == 'true'){//是修改
				jumpToNextpage();
				return ;
			}
			
			//验证是否必填
			var validateResult = '';
			requiredDiv.each(function(i, data){
				if($(data).find('input:checked').length == 0){
					if(validateResult != '')
						validateResult += ",";
					validateResult += $(data).find('.survey_title').html();
				}
			});
			requiredSelect.each(function(i, data){
				if($(data).val() == ''){
					if(validateResult != '')
						validateResult += ",";
					validateResult += $(data).parent().find('.answer_name').html();
				}
			});
			requiredInput.each(function(i, data){
				if($(data).val() == ''){
					if(validateResult != '')
						validateResult += ",";
					validateResult += $(data).parent().find('.answer_name').html();
				}
			});
			if(validateResult != ''){
				ZWY_ClOUD.alert('请选择或输入 '+validateResult+ '。');
				return ;
			}
			//提交
			cq_ajax.ajaxOperate('/act/answer/answer_collect_save.html', me, myForm.serialize(), function(data){
				if(data.code == 999992){//需要登录后才能继续
					ZWY_ClOUD.login();
				}else if(data.code >= 0){
					myForm.find('input[name=actTestId]').val(data.data);
					myForm.find('input[name=isUpdate]').val('true');
					jumpToNextpage();
				}else{
					ZWY_ClOUD.alert(data.message ? data.message : '答题失败！');
				}
			});
		}, 500);
	}
});