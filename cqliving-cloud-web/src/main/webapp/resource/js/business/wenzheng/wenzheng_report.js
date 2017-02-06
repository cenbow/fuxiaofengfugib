define(["jquery", "scroll_base"], function($, scroll_base) {
	return {
		init: function(){
			$(document).on('touchstart', '.model_report .select_list_title a,.edit_bg', hideReportBgModal);
			$(document).on('touchstart', '.model_report .select_list_report li', function(){
				var me = $(this);
				if(me.hasClass('active')){
					me.removeClass('active');
				}else{
					var active = $('.model_report .select_list_report li.active');
					if(active.length == 3 || active.length > 3){
						ZWY_ClOUD.alert('最多只能选择3种举报类型');
						return ;
					}else{
						me.addClass('active');
					}
				}
			});
			//举报提交
			$(document).on('touchstart', '.model_report .select_list .btn_submit', reportFn);
		}
	};
	//隐藏举报窗口的方法
	function hideReportBgModal(){
		$(".model_report").hide();
		$("body").removeClass("hide_bg");
	}
	
	//举报提交
	function reportFn(){
		var selectObj = $('.model_report .select_list_report li.active');
		if(selectObj.length == 0){
			ZWY_ClOUD.alert('请选择举报类型');
			return ;
		}
		if(selectObj.length > 3){
			ZWY_ClOUD.alert('最多只能选择3种举报类型');
			return ;
		}
		var codes = [];
		selectObj.each(function(i, data){
			codes.push($(data).attr('data'));
		});
		$.ajax({
			url: 'report_save.html',
			data: {reportId: paramsObj.reportId, codes: codes, appId: paramsObj.appId, token: paramsObj.token, sessionId: paramsObj.sessionId},
			type: 'POST',
			dataType: 'json',
			success: function(data){
				if(data.code == 0){
					ZWY_ClOUD.alert('举报成功');
					hideReportBgModal();
				}else{
					ZWY_ClOUD.alert(data.message ? data.message : '举报失败');
				}
			},
			error: function(){
				ZWY_ClOUD.alert('网络错误');
			}
		});
	}
});

function clickReport(me){
	//先判断该用户是否已经举报过这个评论
	var commentId = $(me).attr('commentId');
	$.ajax({
		url: 'report_validate.html',
		data: {commentId : commentId, appId: paramsObj.appId, token: paramsObj.token, sessionId: paramsObj.sessionId},
		type: 'POST',
		dataType: 'json',
		success: function(data){
			if(data.code >= 0){
				if(data.data){
					ZWY_ClOUD.alert('您已经举报过了。');
				}else{
					//控制举报的modal窗口显示位置的问题
					$('.model_report').css('top', (_getScrollTop()+50) + 'px');
					$('.model_report .edit_bg').css('height', _getScrollHeight() + 'px');
					$('.model_report .edit_bg').css('position', 'inherit');
					
					paramsObj.reportId = commentId;
					$('.model_report .select_list_report li.active').removeClass('active');
					$(".model_report").show();
					$("body").addClass("hide_bg");
				}
			}else{
				ZWY_ClOUD.alert(data.message ? data.message : '无法举报');
			}
		},
		error: function(){
			ZWY_ClOUD.alert('网络错误');
		}
	});
}



//评论回复点击
function replyOnclick(me){
	var me = $(me),
		sourceId = me.attr('source-id'),
		commentId = me.attr('comment-id'),
		passiveReplyName = me.attr('passive-reply-name'),
		passiveReplyId = me.attr('passive-reply-id');
	/*try {
		//掉用原生的评论
		var option = {sourceId: sourceId, sourceType: 2, replyId: commentId, passiveReplyName: passiveReplyName, passiveReplyId: passiveReplyId};
//		ZWY_ClOUD.showComment(JSON.stringify(option));
		window.AppJsObj.appShowComment(JSON.stringify(option));
	} catch (e) {*/
		//没调用到原生的就用H5
		$(".edit_comment .edit_content input[name=commentId]").val(commentId);
		$(".edit_comment .edit_content input[name=sourceId]").val(sourceId);
		footerInputClick();
//	}
}
//弹出评论框
function footerInputClick(){
	$(".edit_comment").show();
    $("body").addClass("hide_bg");
	$(".edit_comment .edit_content textarea").focus();
}
//评论发送
function replySendOnClick(){
	var content = $('.edit_comment').find('textarea[name=content]').val(),
		commentId = $('.edit_comment').find('input[name=commentId]').val(),
		sourceId = $('.edit_comment').find('input[name=sourceId]').val();
	if(typeof content == 'undefined' || content == ''){
		ZWY_ClOUD.alert('请输入评论内容');
		return ;
	}
	$.ajax({
		url: 'wz_question_reply_save.html',
		type: 'POST',
		dataType: 'json',
		data: {id: sourceId, content: content, commentId: commentId, appId: paramsObj.appId, token: paramsObj.token, sessionId: paramsObj.sessionId},
		success: function(data){
			if(data.code == 0){
				ZWY_ClOUD.showMessage('评论成功');
				editBgOnClick();
				$('.commentTab li.active').click();
				setTimeout("reloadCurrentPage()", 1000);
			}else{
				ZWY_ClOUD.alert(data.message ? data.message : '评论失败');
			}
		},
		error: function(){
			ZWY_ClOUD.alert('网络错误');
		}
	});
}
//弹出窗隐藏
function editBgOnClick(){
	$(".edit_comment .edit_content input").val('');
	$(".edit_comment").hide();
	$("body").removeClass("hide_bg");
}



//获取滚动条当前的位置
function _getScrollTop(){
	var scrollTop = 0;
		if (document.documentElement && document.documentElement.scrollTop) {
		scrollTop = document.documentElement.scrollTop;
	}else if (document.body){
		scrollTop = document.body.scrollTop;
	}
	return scrollTop;
}
//获取文档完整的高度
function _getScrollHeight(){
	return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);
}