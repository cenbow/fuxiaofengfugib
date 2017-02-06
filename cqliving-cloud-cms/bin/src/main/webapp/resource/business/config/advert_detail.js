define(['validator.bootstrap','cloud.table.curd','myUploader','cqliving_dialog','cqliving_ajax','cloud.time.input','common_colorbox','chosen'], function($,tableCurd,myUploader,cqliving_dialog,cq_ajax,timeInput, colorbox){
	return {
		init:initImgVersion
	};
	
	function initImgVersion(){
		colorbox.init();
		timeInput.initTimeInput();		
		tableCurd.bindSaveButton();
		//页面初始化
		pageInit();
		//初始化图片控件
		initImg();
		//删除图片
		removeImg();
		//表单验证
		formValidate();
	}
	
	/**
	 * 页面初始化
	 */
	function pageInit(){
		//设置App
		var appId = $("#appId").val();
		if(!appId){
			appId = $("#search_EQ_appId").val();
			$("#appId").val(appId);
		}
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
				$("#imageUrl").val(imgUrl+response.data.filePath);
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
        	$("#imageUrl").val('');
        });
	}
	
	/**
	 * 表单验证
	 */
	function formValidate(){
		$("#form1").validate({
			ignore: "",
            rules: {
                imageUrl : {
                    required: true
                },
                linkUrl : {
                    required: true
                }
            },
            messages: {
                imageUrl : {
                    required: '请上传图片'
                },
                linkUrl : {
                    required: '请填写广告链接地址'
                }
            }
        });
	}
});