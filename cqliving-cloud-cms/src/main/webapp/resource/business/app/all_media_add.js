require(['cqliving_ajax','cqliving_dialog','myUploader'],function(cqliving_ajax,cqliving_dialog,myUploader){
	
	/** 图片上传  */
	var two_uploader = {
			url:"/common/upload.html?imgsize=30&thumb=48x48",
			containerId:"two_img_upload",
			thumbContainerId:"two_img_thum",
			success:function(file,response){
				var imgPath = imgUrl+response.data.filePath;
				//将图片的引用修改为上传后的图片路径
				var $liImg = $("#tow_img_thum ul li[id="+file.id+"] img").last();
				if($liImg.length<=0)$liImg = $("#two_img_thum ul li img").last();
				$liImg.attr("src",imgPath);
				$("#minImageUrl").val(imgPath);
			},
			error:function(file,reason){
				cqliving_dialog.error(reason);
			},
			isSingle:true,fileUrlPath:imgUrl,destroy:true
		};
	
	var max_uploader = {
			url:"/common/upload.html?imgsize=30&thumb=72x72",
			containerId:"max_img_upload",
			thumbContainerId:"max_img_thum",
			success:function(file,response){
				var imgPath = imgUrl+response.data.filePath;
				//将图片的引用修改为上传后的图片路径
				var $liImg = $("#max_img_thum ul li[id="+file.id+"] img").last();
				if($liImg.length<=0)$liImg = $("#max_img_thum ul li img").last();
				$liImg.attr("src",imgPath);
				$("#maxImageUrl").val(imgPath);
			},
			error:function(file,reason){
				cqliving_dialog.error(reason);
			},
			isSingle:true,fileUrlPath:imgUrl,destroy:true
		};
	
	$(function(){
		myUploader.init(two_uploader);
		myUploader.init(max_uploader);
		$(":input[name=type]").bind("click",typeChange);
		$(":input[name=type]:checked").click();
		
		$("select[name=appId]").bind("change",changeAppId);
	});
	
	function typeChange(){
		var $this = $(this);
		var type = $this.val();
		var url = "/module/all_media/common/load_type_content.html";
		var param = {type:type,appId:$("#appId").val()};
		if(jsonMedia){
			if('string' == typeof jsonMedia){
				jsonMedia = eval("("+jsonMedia+")");
			}
			param.columnsId = jsonMedia.columnsId;
			param.linkUrl = jsonMedia.linkUrl;
		}
		cqliving_ajax.load($(".type_content"),url,param,function(){
			
		});
	}
	
	function changeAppId(){
		$(":input[name=type]:checked").click();
	}
	
});