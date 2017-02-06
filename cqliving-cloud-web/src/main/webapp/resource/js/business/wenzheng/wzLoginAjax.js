define(["jquery"], function($) {
	return {
		init: function(){
			getSessionToken();
			
			//点击创建问政的时候验证是否登录
			$('#create').click(createFn);
		}
	};
	
	/**
	 * 获取session和token
	 */
	function getSessionToken(){
		try{
			ZWY_ClOUD.getSessionToken("setSessionToken");
		}catch(e){}
	}
	
	/**
	 * 是否需要登录验证
	 */
	function createFn(){
		var me = $(this),
			url = me.attr('url');
		//获取客户端登录信息
		getSessionToken();
		setTimeout(function(){
			if(paramsObj.appId != '' || paramsObj.token != ''){
				$.ajax({
					url: 'login_validate.html', 
					data: {appId: paramsObj.appId, token: paramsObj.token, sessionId: paramsObj.sessionId}, 
					dataType:"json",
					type:"post",
					success: function(data){
						if(data.code == 0){
							window.location.href = url;
						}else{
							ZWY_ClOUD.login();
						}
					},
		     		error:function(){
		     			ZWY_ClOUD.alert("网络错误");
		     		}
				});
			}else{
				window.location.href = url;
			}
		}, 500);
	
	}
});