define(['validator.bootstrap', 'cloud.table.curd', 'cqliving_ajax', 'cqliving_dialog', 'myUploader', 'common_colorbox', "chosen"], function($, tableCurd, cq_ajax, cq_dialog, myUploader, colorbox){
	return {
		init: function(){
			tableCurd.bindSaveButton();
			colorbox.init();
			//初始化APP选择列表框
			$(".chosen-select").chosen({search_contains:true});
			//改变app切换对应的类型
			$('#appId').on('change', appChange);
			//初始化页面
			initPage();
		}
	};
	
	/**
	 * 改变app切换对应的类型
	 */
	function appChange(){
		var val =$(this).val();
		var typeObj = $('#convenienceTypeId');
		typeObj.val('');
		typeObj.empty();
		typeObj.append('<option value="">无</option>');
		cq_ajax.ajaxOperate('/module/config/8/common/getByApp.html', null, {appId: val}, function(data){
			if(data.code >= 0){
				var typeList = data.data,
				len = typeList.length;
				for(var i = 0 ; i < len; i ++){
					typeObj.append('<option value="'+typeList[i].id+'">'+typeList[i].name+'</option>');
				}
			}
		});
	}
	
	/**
	 * 初始化页面
	 */
	function initPage(){
		myUploader.init({
			url:"/common/upload.html",
			fileUrlPath: imageUrl,
			containerId:"iconMinUrlDiv",
			thumbContainerId: "iconMinUrlList",
			isSingle: true,
			width: '80px',
			height: '80px',
			success:function(file,response){
				var imgPath = imageUrl + response.data.filePath;
				if($('#iconMinUrl').length > 0){
					$('#iconMinUrl').val(imgPath);
				}else{
					$('#iconMinUrlList .upload-imgs:last').append('<input type="hidden" name="iconMinUrl" id="iconMinUrl" value="'+imgPath+'">');
				}
				$("#iconMinUrlList ul li .topInput>a").last().attr("href", imgPath);
				$("#iconMinUrlList ul li img").last().attr("src", imgPath);
			},
			error:function(file,reason){
				cq_dialog.alert(reason);
			}
		});
		
		myUploader.init({
			url:"/common/upload.html",
			fileUrlPath: imageUrl,
			containerId:"iconMaxUrlDiv",
			thumbContainerId: "iconMaxUrlList",
			isSingle: true,
			width: '80px',
			height: '80px',
			success:function(file,response){
				var imgPath = imageUrl + response.data.filePath;
				if($('#iconMaxUrl').length > 0){
					$('#iconMaxUrl').val(imgPath);
				}else{
					$('#iconMaxUrlList .upload-imgs:last').append('<input type="hidden" name="iconMaxUrl" id="iconMaxUrl" value="'+imgPath+'">');
				}
				$("#iconMaxUrlList ul li .topInput>a").last().attr("href", imgPath);
				$("#iconMaxUrlList ul li img").last().attr("src", imgPath);
			},
			error:function(file,reason){
				cq_dialog.alert(reason);
			}
		});
		
		//设置自己的业务验证
		tableCurd.setValid(function(form){
			if($('#iconMinUrl').length == 0 || $('#iconMinUrl').val() == ''){
				cq_dialog.error('请上传便民图标48*48px图标');
				return false;
			}
			if($('#iconMaxUrl').length == 0 || $('#iconMaxUrl').val() == ''){
				cq_dialog.error('请上传便民图标72*72px图标');
				return false;
			}
			return form.valid();
		});
		$(function(){
	        $("#form1").validate({
	            rules: {
	                    appId : {
	                    required: true,
	                    number:true
	                },
	                    name : {
	                    required: true
	                },
	                    linkUrl : {
	                    required: true
	                },
	                    iconMinUrl : {
	                    required: true
	                },
	                    iconMaxUrl : {
	                    required: true
	                },
	                    sortNo : {
	                    required: true,
	                    number:true
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
	                linkUrl : {
	                    required: ' '
	                },
	                iconMinUrl : {
	                    required: ' '
	                },
	                iconMaxUrl : {
	                    required: ' '
	                },
	                sortNo : {
	                    required: ' ',
	                    number:' '
	                }
	            }
	        });
	    });
	}
});