
define(["jquery", "cqalert"], function($, cqalert){
	return {
		init: function(){
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
			//上传图片
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
			$('#submitBtn').click(function(){

				var myForm = $('#myForm');
				var reg = /^\s+|\s+$/g;
				if(myForm.find('input[name=type]').val() == ''){
					alert('请选择事件类型');
					return false;
				}
				if(myForm.find('input[name=regionName]').val() == ''){
					alert('请选择所属区域');
					return false;
				}
				var str = myForm.find('input[name=title]').val();
				if(str.replace(reg, '') == ''){
					alert('请输入标题');
					return false;
				}
				str = myForm.find('textarea[name=content]').val();
				if(str.replace(reg, '') == ''){
					alert('请输入描述内容');
					return false;
				}
				
				var collentValidate = false;
				var arrayObj = $('input[name=collectValue][required=true]');
				arrayObj.each(function(i, data){
					if($(data).val() == ''){
						collentValidate = true;
						alert($(data).attr('placeholder'));
						$(data).focus();
						return false;
					}
				});
				if(collentValidate){
					return ;
				}
				if(wzUserToken != '' || wzUserSessionId != ''){
					$.ajax({
						url: 'wz_login_validate.html', 
						data: {appId: wzAppId, token: wzUserToken, sessionId: wzUserSessionId, type: 1}, 
						dataType:"json",
						type:"post",
						success: function(data){
							if(data.code == 0){
								$.post(myForm.attr('action'), myForm.serialize(), function(data){
									if(data.code == 0){
										alert('问政保存成功');
										setTimeout("$('#header .btn_back').click();", 1000);
									}else{
										alert(data.message ? data.message : '问政保存失败');
									}
								}, "json");
							}else{
								window.AppJsObj.login();
							}
						},
			     		error:function(){
			     			alert("网络错误");
			     		}
					});
				}else{
					window.AppJsObj.login();
				}
			});
			
			//上传图片
			$(document).on('touchstart', '.select_pic', function(){
				window.AppJsObj.selectPic('callbackUploadPic');
			});
		}
	};
});

function callbackUploadPic(imgPath){
	var html = '<div class="pic_up_list">';
		html += '<input type="hidden" name="photos" value="'+imgPath+'"/>';
		html += '<span class="select_pic"></span>';
		html += '<span class="upload_pic"><img src="'+imgPath+'"/></span>';
		html += '<span class="delete_pic">删除</span>';
	html += '</div>';
	$('.pic_up div.pic_up_list:last').before(html)
}
