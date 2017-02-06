define(['validator.bootstrap','myUploader','cqliving_dialog','cloud.table.curd','cqliving_ajax','common_colorbox'], function($,myUploader,dialog,tableCurd,cq_ajax,colorbox){
	return {
		init:init
	};
	
	function init(){
		tableCurd.bindSaveButton();
		//初始化图片控件
		initImg();
		//删除图片
		removeImg();
		//表单验证
		formValidate();
		//保存
		save();
		colorbox.init();
	}
	
	/**
	 * 图片初始化
	 */
	function initImg(){
		myUploader.init({
			url:"/common/upload.html",
			containerId:"img_upload",
			thumbContainerId:"imgView",
			fileUrlPath:$("#imgHead").val(),
			success:function(file,response){
				$("#imageUrl").val($("#imgHead").val()+response.data.filePath);
				$("#imgView").find("li:last").find('img').eq(0).attr('src',$("#imageUrl").val());
			},
			error:function(file,reason){
				dialog.alert(reason);
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
        	//删除图片路径
        	$("#imageUrl").val("");
        });
	}
	
	function save(){
		$("#saveBtn").on("click",function(){
    		var flag = true;
    		$('.smsContent').each(function(){
    			var content = $(this).val();
    			if(content){
    				if(content.length>50){
    					flag = false;
    					$("#sms").text("请输入短信模板长度不能大于50个字");
    					return false;
    				}
    				var no = (content.split('#')).length-1;
    				if(no!=1){
    					flag = false;
    					$("#sms").text("请输入短信模板,验证码后面必须并且只能包含一个#");
    					return false;
    				}
    				$("#sms").text("");
    			}else{
    				$("#sms").text("请输入短信模板,验证码后面必须并且只能包含一个#");
    				flag = false;
					return false;
    			}
    		});
    		
    		var jthis = $(this);
			var jform = jthis.parents("form");
			if(!(jform.valid()) || !flag){
				return;
			}
			var params = jform.serializeArray();
			var url = jthis.attr("url") ? jthis.attr("url") : jform.attr("action");
			url = url ? url : location.href;
			cq_ajax.ajaxOperate(url,jform,params,function(data,status){
				if(data.code >= 0){
					jthis.unbind("click");
					dialog.success(data.message?data.message:"保存成功",function(){
						if(jthis.attr("back_url")){
							window.location.href = jthis.attr("back_url");
						}
					});
				}else{
					dialog.error(data.message?data.message:"保存失败");
				}
			});
		});
	}
	function formValidate(){
		$.validator.addMethod("alnum", function(value, element){
			return this.optional(element) ||/^[a-zA-Z]+$/.test(value);
		}, "只能包括英文字母");
		
		$.validator.addMethod("notEqualTo", function(value, element,param) {
			return (value+".cqliving.com") != $(param).val();
			}, $.validator.format("两个元素值不能相同"));
    	/**
    	 * 表单验证
    	 */
        $("#form1").validate({
        	ignore: "",
            rules: {
                    name : {
                    required: true
                },
                    cmsName : {
                    required: true
                },
                    namePrefix : {
                    required: true
                },
                    logo : {
                    required: true
                },
                    cmsDomain : {
                    required: true,
                    notEqualTo:"#appDomain"
                },
                	smsCode : {
                    required: true,
                    number:true
                },
	                username : {
		                required: true
		        },
	                password : {
	                	required: true
		        },
		        	bucketName : {
                	required: true
                },
                	domainTest : {
	                required: true
		        },
		        	domainCustom : {
	                	required: true
		        },
		        	cityName : {
	            	required: true
	            },
	            	cityPhoneticize : {
                	required: true,
                	alnum:true
	            },smsSignature:{
	            	required: true
	            },baiduLbsKey:{
	            	required: true
	            },downLoadUrl:{
	            	required: true,
	            	url:true
	            }
            },
            messages: {
                name : {
                    required: '请输入客户端名称'
                },
                cmsName : {
                    required: '请输入客户端后台名称'
                },
                namePrefix : {
                    required: '请输入游客登录名'
                },
                logo : {
                    required: '请上传logo'
                },
                cmsDomain : {
                    required: '请输入后台域名',
                    notEqualTo:'后台域名不能和自定义域名相同'
                },
                smsCode : {
                    required: '请输入短信扩展码号',
                    number:'短信扩展码号是数字类型'
                },
	            username : {
	                required: '请输入管理用户账号'
		        },
	            password : {
	                required: '请输入管理用户密码'
		        },
		        bucketName : {
                	required: '请输入资源名称'
                },
                domainTest : {
	                required: '请输入测试域名'
		        },
		        domainCustom : {
	                	required: '请输入自定义域名'
		        },
		        cityName : {
	            	required: '请输入区域名称'
	            },
	            cityPhoneticize : {
                	required: '请输入区域代码',
                	alnum: '区域代码是英文字母'
	            },smsSignature:{
	            	required: '请输入短信签名'
	            },baiduLbsKey:{
	            	required: '请输入百度地图Key'
	            },downLoadUrl:{
	            	required: "请输入下载市场地址",
	            	url:"下载市场地址格式不对"
	            }
            }
        });
	}
});