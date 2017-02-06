define(["jquery", "cqalert"], function($, cqalert) {
	return {
		init: function(){
			//点击创建问政的时候验证是否登录
			$('#create').click(function(){
				var me = $(this),
					url = me.attr('url')
				if(wzUserSessionId != '' || wzUserToken != ''){
					$.ajax({
						url: 'wz_login_validate.html', 
						data: {appId: wzAppId, token: wzUserToken, sessionId: wzUserSessionId, type: 1}, 
						dataType:"json",
						type:"post",
						success: function(data){
							if(data.code == 0){
								window.location.href = url;
							}else{
								window.AppJsObj.login();
							}
						},
			     		error:function(){
			     			alert("网络错误");
			     		}
						
					});
				}else{
					window.location.href = url;
				}
			});
		}
	};
});