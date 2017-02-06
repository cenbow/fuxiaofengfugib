define(['validator.bootstrap','cloud.table.curd','cqliving_ajax'], function($,tableCurd,cqliving_ajax){
	
	return {
		init: function(){
			//页面按钮绑定事件
			tableCurd.bindSaveButton();
			//表单校验
			formValidate();
			//修改app
			changeApp();
			//加载反馈配置回复信息
			loadConfig();
		}
	};
	
	/**
	 * 表单校验
	 */
	function formValidate(){
		$("#form1").validate({
            rules: {
                    replyContent : {
                    required: true
                },
                	appId : {
                    required: true
                }
            },
            messages: {
                replyContent : {
                    required: '请输入自动回复内容'
                },
                appId : {
                    required: '请选择App'
                }
            }
        });
	}
	
	/**
	 * 修改app
	 */
	function changeApp(){
		$("body").on("change","#appId",function(){
			loadConfig();
		});
	}
	
	/**
	 * 加载反馈配置
	 */
	function loadConfig(){
		var appId = $("#appId").val();
		if(appId){
			$("#content").val("");
			var url='/module/user_feedback_config/common/query_by_appId.html';
			var data = {};
			data['appId'] = appId;
			cqliving_ajax.ajaxOperate(url,null,data,function(data,status){
				if(data.code >= 0){
					if(data.data && data.data.content){
						$("#content").val(data.data.content);
					}
				}
			});
			var backUrl = '/module/user_feedback_config/to_save.html?appId='+appId;
			$("#commonSaveButton").attr("back_url",backUrl);
		}
	}
});