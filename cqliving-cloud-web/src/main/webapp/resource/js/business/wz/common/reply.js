define(["jquery", "scroll_base", "cqalert"], function($, scroll_base, cqalert) {
	var paramsObj = {appId: wzAppId, token: wzUserToken, sessionId: wzUserSessionId};
	return {
		init: function(){
			//评论举报
			$(document).on('touchstart', '.comment_list_btn .btn_report', function(){
				//先判断该用户是否已经举报过这个评论
				var commentId = $(this).attr('commentId');
				$.ajax({
					url: 'wz_question_report_validate.html',
					data: {commentId : commentId, appId: wzAppId, token: wzUserToken, sessionId: wzUserSessionId},
					type: 'POST',
					dataType: 'json',
					success: function(data){
						if(data.code >= 0){
							if(data.data){
								alert('您已经举报过了。');
							}else{
								//控制举报的modal窗口显示位置的问题
								$('.model_report').css('top', (scroll_base.getScrollTop()+50) + 'px');
								$('.model_report .edit_bg').css('height', scroll_base.getScrollHeight() + 'px');
								$('.model_report .edit_bg').css('position', 'inherit');
								
								paramsObj.reportId = commentId;
								$('.model_report .select_list_report li.active').removeClass('active');
								$(".model_report").show();
								$("body").addClass("hide_bg");
							}
						}else{
							alert(data.message ? data.message : '无法举报');
						}
					},
					error: function(){
						alert('网络错误');
					}
				});
			});
			
			$(document).on('touchstart', '.model_report .select_list_title a,.edit_bg', hideReportBgModal);
			$(document).on('touchstart', '.model_report .select_list_report li', function(){
				var me = $(this);
				if(me.hasClass('active')){
					me.removeClass('active');
				}else{
					var active = $('.model_report .select_list_report li.active');
					if(active.length == 3 || active.length > 3){
						alert('最多只能选择3种举报类型');
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
			alert('请选择举报类型');
			return ;
		}
		if(selectObj.length > 3){
			alert('最多只能选择3种举报类型');
			return ;
		}
		var codes = [];
		selectObj.each(function(i, data){
			codes.push($(data).attr('data'));
		});
		$.ajax({
			url: 'wz_question_report_save.html',
			data: {reportId: paramsObj.reportId, codes: codes, appId: wzAppId, token: wzUserToken, sessionId: wzUserSessionId},
			type: 'POST',
			dataType: 'json',
			success: function(data){
				if(data.code == 0){
					alert('举报成功');
					hideReportBgModal();
				}else{
					alert(data.message ? data.message : '举报失败');
				}
			},
			error: function(){
				alert('网络错误');
			}
		});
	}
});
//评论回复点击
function replyOnclick(commentId, sourceId){
	$(".edit_comment .edit_content input[name=commentId]").val(commentId);
	$(".edit_comment .edit_content input[name=sourceId]").val(sourceId);
	footerInputClick();
}
//点击评论问政
function footerInputOnclick(sourceId){
	$(".edit_comment .edit_content input[name=sourceId]").val(sourceId);
	footerInputClick();
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
		alert('请输入评论内容');
		return ;
	}
	$.ajax({
		url: 'wz_question_reply_save.html',
		type: 'POST',
		dataType: 'json',
		data: {id: sourceId, content: content, commentId: commentId, appId: wzAppId, token: wzUserToken, sessionId: wzUserSessionId},
		success: function(data){
			if(data.code == 0){
				alert('评论成功');
				editBgOnClick();
				setTimeout('window.location.href = window.location.href;', 1000);
			}else{
				alert(data.message ? data.message : '评论失败');
			}
		},
		error: function(){
			alert('网络错误');
		}
	});
}
//弹出窗隐藏
function editBgOnClick(){
	$(".edit_comment .edit_content input").val('');
	$(".edit_comment").hide();
	$("body").removeClass("hide_bg");
}