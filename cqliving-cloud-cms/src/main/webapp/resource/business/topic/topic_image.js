define(['cqliving_ajax', 'cqliving_dialog', 'myUploader', 'common_colorbox'], function(cq_ajax, cq_dialog, myUploader, colorbox){
	return {
		init: function(){
			colorbox.init();
			myUploader.init({
				url:"/common/upload.html?thumb=720x360",
				fileUrlPath: imageUrl,
				containerId:"imageUrlDiv",
				thumbContainerId: "imageUrlList",
				isSingle: true,
				width: '80px',
				height: '80px',
				success:function(file,response){
					var imgPath = imageUrl + response.data.filePath,
						fileExtName = response.data.fileExtName,
						inputObj = $('#imageUrlList .upload-imgs input[name=imageUrl]');
					imgPath = imgPath.substring(0, imgPath.indexOf('.' + fileExtName)) + '_720x360.' + fileExtName;
					if(inputObj.length > 0){
						inputObj.val(imgPath);
					}else{
						$('#imageUrlList .upload-imgs').append('<input type="hidden" name="imageUrl" value="'+imgPath+'">');
					}
				},
				error:function(file,reason){
					cq_dialog.alert(reason);
				}
			});
		}
	};
});