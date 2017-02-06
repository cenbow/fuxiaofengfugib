define(['validator.bootstrap','cloud.table.curd','myUploader','cqliving_dialog','cqliving_ajax','cloud.time.input','common_colorbox','chosen'], function($,tableCurd,myUploader,cqliving_dialog,cq_ajax,timeInput, colorbox){
	return {
		init:initImgVersion
	};
	
	function initImgVersion(){
		colorbox.init();
		timeInput.initTimeInput();		
		tableCurd.bindSaveButton();
		//初始化图片控件
		initImg();
		//删除图片
		removeImg();
		//表单验证
		formValidate();
	}
	
	/**
	 * 图片初始化
	 */
	function initImg(){
		myUploader.init({
			//229px*355px
			//url:"/common/upload.html",
			url:"/common/upload.html?"+imgThumb,
			containerId:"img_upload",
			thumbContainerId:"loadingView",
			fileUrlPath:imgUrl,
			success:function(file,response){
				//地址要改成压缩后的地址
				var realPath = imgUrl+response.data.filePath;
				realPath = myUploader.appendSuffix(imgSize,realPath);
				$("#imageUrl").val(realPath);
				$("#loadingView").find("li:last").find('img').eq(0).attr('src',realPath);
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