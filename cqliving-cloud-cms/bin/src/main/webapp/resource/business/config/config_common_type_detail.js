define(['validator.bootstrap', 'cloud.table.curd', 'cqliving_dialog', 'myUploader', 'common_colorbox'], function($, tableCurd, cq_dialog, myUploader, colorbox){
	return {
		init: function(){
			tableCurd.bindSaveButton();
			colorbox.init();
			//初始化APP选择列表框
			$(".chosen-select").chosen({search_contains:true});
			//初始化页面
			initPage();
		}
	};
	
	function initPage(){
		myUploader.init({
			url:"/common/upload.html",
			fileUrlPath: imageUrl,
			containerId:"imageUrlDiv",
			thumbContainerId: "imageUrlList",
			isSingle: true,
			width: '80px',
			height: '80px',
			success:function(file,response){
				var imgPath = imageUrl + response.data.filePath;
				if($('#imageUrl').length > 0){
					$('#imageUrl').val(imgPath);
				}else{
					$('#imageUrlList .upload-imgs:last').append('<input type="hidden" name="imageUrl" id="imageUrl" value="'+imgPath+'">');
				}
			},
			error:function(file,reason){
				cq_dialog.alert(reason);
			}
		});
		
		$("#form1").validate({
			rules : {
				appId : {
					required : true,
					number : true
				},
				name : {
					required : true
				}
			},
			messages : {
				appId : {
					required : ' ',
					number : ' '
				},
				name : {
					required : ' '
				}
			}
		});
	}
});