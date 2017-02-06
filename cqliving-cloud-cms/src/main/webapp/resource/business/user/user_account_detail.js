define(['validator.bootstrap','cloud.table.curd','myUploader'], function($,tableCurd,myUploader){
	
	return {
		init: function(){
			tableCurd.bindSaveButton();
			
			$("body").on("click",".icon-remove",function(){
		    	$(this).parents("li").remove();
		    	$("#imgUrl").val("");
		    });
			
			myUploader.init({
				url:"/common/upload.html",
				containerId:"img_upload",
				thumbContainerId:"imgView",
				clickView:false,
				success:function(file,response){
					$("#imgUrl").val(imgHead + response.data.filePath);
				},
				error:function(file,reason){
					cqliving_dialog.alert(reason);
				},
				isSingle:true
			});
			
			$("#form1").validate({
	        	ignore: "",
	            rules: {
	                    userName : {
	                    required: true
	                },
	                    telephone : {
	                    required: true
	                },
	                    email : {
	                    required: true,
	                    email : true                    
	                },
	                	password : {
		                required: true
		            },
		            	appId : {
	                    required: true,
	                    number:true
	                },
	                	name : {
	                    required: true
	                },
	                    imgUrl : {
	                    required: true
	                },
	                    speciality : {
	                    required: true
	                }
	            },
	            messages: {
	                userName : {
	                    required: '请输入登录账号'
	                },
	                telephone : {
	                    required: '请输入手机号'
	                },
	                email : {
	                    required: '请输入邮箱',
	                    email:'请输入正确的邮箱'                    	
	                },
	                password : {
	                    required: '请输入密码'
	                },
	                appId : {
	                    required: '请选择来源客户端',
	                    number:' '
	                },
	                name : {
	                    required: '请输入用户姓名'
	                },
	                imgUrl : {
	                    required: '请选择用户头像'
	                },
	                speciality : {
	                    required: '请输入个性签名'
	                }
	            }
	        });
		}
	};
});