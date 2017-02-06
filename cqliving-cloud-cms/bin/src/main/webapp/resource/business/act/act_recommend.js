define(['validator.bootstrap','myUploader','cqliving_dialog','common_colorbox'], function($,myUploader,dialog,colorbox){
	return {
		init:loadInit
	};
	
	function loadInit(){
		formValidate();
    	initImg();
    	colorbox.init();
	};
	
	/**
	 * 表单校验
	 */
	function formValidate(){
    	$("#form1").validate({
        	ignore:"",
            rules: {
            	recommendImageUrl : {
                    required: true
                }
            },
            messages: {
            	recommendImageUrl : {
                    required: '请上传推荐到首页图片'
                }
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
			thumbContainerId:"imgView",
			fileUrlPath:imgUrl,
			success:function(file,response){
				$("#recommendImageUrl").val(imgUrl+response.data.filePath);
			},
			error:function(file,reason){
				dialog.error(reason);
			},
			isSingle:true
		});
	}
});