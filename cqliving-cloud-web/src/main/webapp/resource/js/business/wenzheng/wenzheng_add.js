
define(["jquery", "cqliving_ajax"], function($, cq_ajax){
	return {
		init: function(){
			initParams();
			//选择事件类型
			$(".add_pic ul li .add_right.type").click(function() {
				$("#modelTypeDiv").show();
				$("body").addClass("hide_bg");
			});
			//选择所属区域
			$(".add_pic ul li .add_right.region").click(function() {
				$("#modelRegionDiv").show();
				$("body").addClass("hide_bg");
			});
			//隐藏
			$(".model .select_list_title a,.edit_bg").click(function() {
				$(this).parents('.model').hide()
				$("body").removeClass("hide_bg");
			});
			//删除上传的图片
			$(document).on('touchstart', '.pic_up_list .delete_pic', function(){
				$(this).parent('.pic_up_list').remove()
			});
			
			//点击-事件类型
			$('#modelTypeDiv .select_list li').click(function(){
				var val = $(this).attr('value');
				$('#type').val(val);
				$(".add_pic ul li .add_right.type p").html($(this).html());
				$(".model .select_list_title a,.edit_bg").click();
			});
			//点击-所属区域
			$('#modelRegionDiv .select_list li').click(function(){
				var code = $(this).attr('value'),
					name = $(this).html();
				$('#regionCode').val(code);
				$('#regionName').val(name);
				$(".add_pic ul li .add_right.region p").html(name);
				$(".model .select_list_title a,.edit_bg").click();
			});
			
			//提交
			$('#submitBtn').click(doSave);
			
			//上传图片
			$(document).on('touchstart', '.select_pic', function(){
//				window.AppJsObj.selectPic('callbackUploadPic');
				ZWY_ClOUD.selectPic('callbackUploadPic');
			});
			
			$('input').on('click', function(){
				window.AppJsObj.goToTop();
			});
			/*$('textarea').on('click', function(){
				window.AppJsObj.goToTop();
			});*/
		}
	};
	
	/**
	 * 从客户端获取参数
	 */
	function initParams(){
		try{
			ZWY_ClOUD.getSessionToken("setSessionToken");
		}catch(e){}
	}
	/**
	 * 提交
	 */
	function doSave(){
		initParams();
		var me = $(this),
			myForm = $('#myForm'),
			reg = /^\s+|\s+$/g,
			appId = $('#appId').val();
		if(me.attr('submitting')){
			return false;
		}
		me.attr('submitting', true);
		if(myForm.find('input[name=type]').val() == ''){
			me.removeAttr('submitting');
			ZWY_ClOUD.alert('请选择事件类型');
			return false;
		}
		if(myForm.find('input[name=regionName]').val() == ''){
			me.removeAttr('submitting');
			ZWY_ClOUD.alert('请选择所属区域');
			return false;
		}
		var str = myForm.find('input[name=title]').val();
		if(str.replace(reg, '') == ''){
			me.removeAttr('submitting');
			ZWY_ClOUD.alert('请输入标题');
			return false;
		}
		str = myForm.find('textarea[name=content]').val();
		if(str.replace(reg, '') == ''){
			me.removeAttr('submitting');
			ZWY_ClOUD.alert('请输入描述内容');
			return false;
		}
		var collentValidate = false;
		var arrayObj = $('input[name=collectValue][required=true]');
		arrayObj.each(function(i, data){
			if($(data).val() == ''){
				collentValidate = true;
				ZWY_ClOUD.alert($(data).attr('placeholder'));
				$(data).focus();
				me.removeAttr('submitting');
				return false;
			}
		});
		if(collentValidate){
			me.removeAttr('submitting');
			return ;
		}
		setTimeout(function(){//延迟一下，等待获取客户端登录信息
			var sessionId = sessionObj.sessionId,
				token = sessionObj.token;
			if(token != '' || sessionId != ''){
				cq_ajax.ajaxOperate('/wenzheng/login_validate.html', null, {appId: appId, token: token, sessionId: sessionId}, function(data){
					if(data.code == 0){
						cq_ajax.ajaxOperate(myForm.attr('action'), null, myForm.serialize(), function(data){
							if(data.code == 0){
								ZWY_ClOUD.showMessage(data.data == 1 ? '问政提交成功，请等待审核通过。' : '问政提交成功');
								setTimeout("$('#header .btn_back').click();", 1000);
							}else{
								me.removeAttr('submitting');
								ZWY_ClOUD.alert(data.message ? data.message : '问政保存失败');
							}
						});
					}else{
						me.removeAttr('submitting');
						ZWY_ClOUD.login();
					}
				});
			}else{
				me.removeAttr('submitting');
				ZWY_ClOUD.login();
			}
		}, 500);
	}
});

/**
 * 上传图片原生回调
 * @param imgPath
 */
function callbackUploadPic(imgPath){
	var html = '<div class="pic_up_list">';
		html += '<input type="hidden" name="photos" value="'+imgPath+'"/>';
		html += '<span class="select_pic"></span>';
		html += '<span class="upload_pic"><img src="'+imgPath+'"/></span>';
		html += '<span class="delete_pic">删除</span>';
	html += '</div>';
	$('.pic_up div.pic_up_list:last').before(html)
}
