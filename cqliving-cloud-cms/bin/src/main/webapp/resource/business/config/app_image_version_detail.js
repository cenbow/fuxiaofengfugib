define(['validator.bootstrap','cloud.table.curd','myUploader','cqliving_dialog','cqliving_ajax','cloud.time.input','common_colorbox','chosen'], function($,tableCurd,myUploader,cqliving_dialog,cq_ajax,timeInput, colorbox){
	return {
		init:initImgVersion
	};
	
	function initImgVersion(){
		initPage();
		$(".chosen-select").chosen({search_contains:true});
		colorbox.init();
		timeInput.initTimeInput();		
		tableCurd.bindSaveButton();
		//初始化图片控件
		initImg();
		//删除图片
		removeImg();
		//表单验证
		formValidate();
		//App改变
		appChange();
	}
	
	/**
	 * 图片初始化
	 */
	function initPage(){
		//隐藏显示时间div
		$("#show-time-div").hide();
		if(appId==1){
			$("#show-time-div").show();
		}
	}
	
	/**
	 * App改变
	 */
	function appChange(){
		$("#appId").on("change",function(){
			var appId = $("#appId").val();
			if(appId==1){
				$("#show-time-div").show();
			}else{
				$("#show-time-div").hide();
			}
		});
	}
	
	/**
	 * 图片初始化
	 */
	function initImg(){
		myUploader.init({
			url:"/common/upload.html",
			containerId:"img_upload",
			thumbContainerId:"loadingView",
			fileUrlPath:imgUrl,
			success:function(file,response){
				$("#loadingUrl").val(imgUrl+response.data.filePath);
				$("#loadingView").find("li:last").find("input").remove();
				var html = '<input type="text" name="linkUrl" class="form-control link_url" placeholder="请输入广告链接地址"/>';
				$("#loadingView").find("li:last").append(html);
				$("#loadingView").find("li:last").find('img').eq(0).attr('src',imgUrl+response.data.filePath);
			},
			error:function(file,reason){
				cqliving_dialog.alert(reason);
			},
			isSingle:true
		});
	}
	
	/**
	 * 图片删除
	 */
	function removeImg(){
		$(".icon-remove").on("click",function(){
        	$(this).parents("li").remove();
        	$("#loadingUrl").val("");
        });
	}
	
	/**
	 * 表单验证
	 */
	function formValidate(){
		$("#form1").validate({
        	ignore: "",
            rules: {
                    appId : {
                    required: true,
                    number:true
                },
                    title : {
                    required: true
                },
                    type : {
                    required: true
                },
                loadingUrl : {
                    required: true
                },
                linkUrl : {
                	url: true
                },
                showTime : {
                    number:true,
                    digits:true
                }
            },
            messages: {
                appId : {
                    required: '请选择客户端',
                    number:' '
                },
                title : {
                    required: '请填写标题'
                },
                type : {
                    required: '请选择客户端类型'
                },
                loadingUrl : {
                    required: '请上传广告图'
                },
                linkUrl : {
                	url: '请填写正确的连接地址'
                },
                showTime : {
                	number:'请填写正整数',
                    digits:'请填写正整数'
                }
            }
        });
	}
});