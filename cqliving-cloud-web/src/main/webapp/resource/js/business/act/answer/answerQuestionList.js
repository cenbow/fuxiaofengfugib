define(["jquery"], function($) {
	return {
		init: function(){
			//倒计时
			countdown();
			//处理单选的值
			$('input[type=radio]').on('change', checkRadio);
			//处理复选框的值
			$('input[type=checkbox]').on('change', checkCheckBox);
			$(".model_close").click(function(){$("#model_save").hide();});
		}
	};
	
	
	/**
	 * 处理单选的值
	 */
	function checkRadio(){
		var me = $(this),
			inputObj = me.closest('div').find('input[name=answerId]');
		inputObj.val(me.val());
	}
	
	/**
	 * 处理复选框的值
	 */
	function checkCheckBox(){
		var me = $(this),//当前checkbox
			checkedValue = me.val(),//当前checkbox的值
			checked = me.prop('checked'),//当前checkbox是选中还是取消选中
			inputObj = me.closest('div').find('input[name=answerId]'),
			inputValue = inputObj.val();
		
		if(checked){
			if(inputValue != '')
				inputValue += 'a';
			inputValue += checkedValue;
		}else{
			var vals = inputValue.split('a');
			var lastValue = '';
			$.each(vals, function(i, data){
				if(data != checkedValue){
					if(lastValue != '')
						lastValue += 'a';
					lastValue += data;
				}
			});
			inputValue = lastValue;
		}
		inputObj.val(inputValue);
	}
	

	/**
	 * 倒计时
	 */
	var totalSecond = 0;
	var setIntervalId;
	function countdown(){
		var divObj = $('#countdown');
		if(divObj.is('div')){
			totalTime = divObj.data('time');
			var str = '';
			if(!isNaN(totalTime)){
				totalSecond = totalTime*60;
				setIntervalId = setInterval(formatTime, 1000);
			}
		}
	}
	 function formatTime() {
		 if(totalSecond < 1 && setIntervalId){
			 clearInterval(setIntervalId);
			 //时间到了，自动提交
			 doSubmit(null);
		 }
		 var hh = parseInt(totalSecond/3600);
		 var mm = parseInt(totalSecond/60);
		 var ss = parseInt(totalSecond%60);
		 if((hh + '').length < 2)
			 hh = '0' + hh;
		 if((mm + '').length < 2)
			 mm = '0' + mm;
		 if((ss + '').length < 2)
			 ss = '0' + ss;
		 $('#countdown .highlight').html(hh + ':' + mm + ':' + ss);
		 totalSecond --;
	 }
});


/**
 * 答题提交
 */
function doSubmit(me){
	var myForm = $('#myForm');
	myForm.find('input[name=isFinish]').val(1);
	if(me){
		me = $(me);
		if(me.attr('submitting')){
			return;
		}
		me.attr('submitting',true);
	}
	//提交
	$.ajax({
		url: myForm.attr('action'), 
		data: myForm.serialize(), 
		dataType:"json",
		type:"post",
		async: true,
		success: function(data){
			if(data.code >= 0){
				var map = data.data
				var modal = $('#model');
				modal.find('.diff').html(map.diff);
				modal.find('.score').html(map.score);
				modal.show();
			}else{
				if(me){
					me.attr('submitting',false);
				}
				ZWY_ClOUD.alert(data.message ? data.message : '答题失败！');
			}
		},
 		error:function(){
			if(me){
				me.attr('submitting',false);
			}
 			ZWY_ClOUD.alert("网络异常！");
 		}
	});
}
/**
 * 答题不限时间的保存
 */
function doSave(typeFlag){
	var myForm = $('#myForm');
		myForm.find('input[name=isFinish]').val(0);
	if($('input:checked').length == 0){
		ZWY_ClOUD.alert('请选择答案。');
		return ;
	}
	$.ajax({
		url: myForm.attr('action'), 
		data: myForm.serialize(), 
		dataType:"json",
		type:"post",
		async: true,
		success: function(data){
			$('#model_save').hide();
			if(data.code >= 0){
				$(".message_info").show();
				if(typeFlag == 1){//需要返回
					setTimeout('$(".message_info").hide();toBack();', 3000); 
				}else{
					setTimeout('$(".message_info").hide();', 3000); 
				}
			}else{
				ZWY_ClOUD.alert(data.message ? data.message : '答题保存失败！');
			}
		},
 		error:function(){
 			ZWY_ClOUD.alert("网络异常！");
 		}
	});
}
/**
 * 返回 到答题详情页
 */
function doBack(){
	var pageType = $('#pageType').val();
	var mCss = $('#model').css('display');
	if(pageType == 2 || limitAnswerType == '1' || mCss == 'block'){//查看结果的返回直接返回了||已经提交答题了，在点击手机的返回
		toBack();
	}else{
		var modal = $('#model_save');
		modal.show();
	}
}

//返回、退出答题
function toBack(){
	var id = $('#actInfoListId').val()
//	location.href = '/act/answer/answer/'+id+'.html' + getBasicParams();
	url = '/act/answer/answer/'+id+'.html' + getBasicParams();
	redirectBackUrl(url);
}

//查看结果
function viewResultFn(){
	var id = $('#actTestClassifyId').val();
//	location.href = '/act/answer/answer_question_list.html' + getBasicParams() + '&classifyId=' + id + '&type=2';
	url = '/act/answer/answer_question_list.html' + getBasicParams() + '&classifyId=' + id + '&type=2';
	redirectUrl(url);
}
//获得参数
function getBasicParams(){
	var appId = $('#appId').val(),
		sessionId = $('#sessionId').val(),
		token = $('#token').val();
	return '?appId=' + appId + '&sessionId=' + sessionId + '&token=' + token;
}

