define(['validator.bootstrap', 'cqliving_ajax', 'cqliving_dialog', 'myUploader', 'common_colorbox', "chosen"], function($, cq_ajax, cq_dialog, myUploader, colorbox){
	return {
		init: function(){
			//初始化图片查看
			colorbox.init();
			//初始化APP选择列表框
			$(".chosen-select").chosen({search_contains:true});
			//初始化页面数据和插件
			initValidate();
			//绑定改变app事件
			$('#appId').on('change', changeApp);
		}
	};
	
	/**
	 * 初始化页面
	 */
	function initValidate(){
		//初始化上传图片
        myUploader.init({
			url:"/module/topic/common/upload.html",
			fileUrlPath: imageUrl,
			containerId:"imageUrlDiv",
			thumbContainerId: "imageUrlList",
			isSingle: false,
			width: '80px',
			height: '80px',
			fileNumLimit: 9,
			success:function(file,response){
				var imgPath = imageUrl + response.data.filePath;
				$('#imageUrlList .upload-imgs:last').append('<input type="hidden" name="imageUrl" value="'+imgPath+'">');
			},
			error:function(file,reason){
				cq_dialog.alert(reason);
			}
		});
		//初始表单验证
        $("#form1").validate({
            rules: {
                appId : {
                    required: true,
                    number:true
                },
                name : {
                    required: true
                },
                types : {
                    required: true
                },
                content : {
                    required: true
                }
            },
            messages: {
                appId : {
                    required: ' ',
                    number:' '
                },
                name : {
                    required: ' '
                },
                types : {
                    required: ' '
                },
                content : {
                    required: ' '
                }
            }
        });
    }
	
	/**
	 * 改变app 获得分类信息
	 */
	function changeApp(){
		var me = $(this),
			appId = me.val(),
			typesObj = $('#topicTypes'),
			url = '/module/config/7/common/getByApp.html';
		if(appId == ''){
			typesObj.html('');
			typesObj.trigger("chosen:updated");
			return ;
		}
		cq_ajax.ajaxOperate(url, null, {appId:appId}, function(data){
			if(data.code >= 0){
				var html = '';
				$.each(data.data, function(i, d){
					html += '<option value="'+d.id+'">'+d.name+'</option>';
				});
				typesObj.html(html);
				typesObj.trigger("chosen:updated");
			}else{
				cq_dialog.error(data.message ? data.message : '获取分类信息失败');
			}
		});
	}
});