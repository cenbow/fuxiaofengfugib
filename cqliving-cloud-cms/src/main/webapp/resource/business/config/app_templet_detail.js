define(['validator.bootstrap','cloud.table.curd','myUploader','cqliving_dialog','common_colorbox'], function($,tableCurd,myUploader,cqliving_dialog,colorbox){
	return {
		init: function(){
			//1、初始化
			load();
			//2、图片查看
			colorbox.init();
		}
	};
	
	/**
	 * 初始化
	 */
	function load(){
		//各个按钮绑定事件
		tableCurd.bindSaveButton();
		//图片上传控件初始化
		imgInit();
		//删除图片
		removeRemoveUpload();
		//表单校验
		formValidate();
	}
	
	//表单校验
	function formValidate(){
		$("#form1").validate({
        	ignore: "",
            rules: {
                    appId : {
                    required: true,
                    number:true
                },
                    templetCode : {
                    required: true
                },
                    imageUrl : {
                    required: true
	            },
	            	name : {
	            	required: true
		        }
            },
            messages: {
                appId : {
                    required: '请选择客户端',
                    number:' '
                },
                templetCode : {
                    required: '请填写模板code'
                },
                imageUrl : {
                    required: '请上传图片'
                },
            	name : {
	            	required: '请填写模板名称'
		        }
            }
        });
	}
	
	//图片上传控件初始化
	function imgInit(){
		myUploader.init({
			url:"/common/upload.html",
			containerId:"img_upload",
			thumbContainerId:"imgView",
			fileUrlPath:imageUrl,
			success:function(file,response){
				$("#imageUrl").val(imageUrl+response.data.filePath);
				$("#imgView").find("li:last").find('img').eq(0).attr('src',imageUrl+response.data.filePath);
			},
			error:function(file,reason){
				cqliving_dialog.alert(reason);
			},
			isSingle:true
		});
	}
	
	/**
	 * 删除上传的图片
	 */
	function removeRemoveUpload(){
		$("body").on("click",".icon-remove",function(){
	    	$(this).parents("li").remove();
	    	$("#imageUrl").val("");
	    });
	}
});