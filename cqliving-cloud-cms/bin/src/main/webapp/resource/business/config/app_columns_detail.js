define(['validator.bootstrap','myUploader','cqliving_dialog','cqliving_ajax', 'common_colorbox'], function($,myUploader,cqliving_dialog,cqliving_ajax,colorbox){
	return {
		init: function(){
			//1、初始化
			init();
			//1、图片查看
			colorbox.init();
			//2、模板切换
			changeTemp();
			//3、删除上传的图片
			removeRemoveUpload();
		}
	};
	
	/**
	 * 模板切换
	 */
	function changeTemp(){
		$("body").on("change","#templetCode",function(){
			var opt = $('#templetCode>option:selected');
			var imgUrl = opt.attr("imgUrl");
			if(imgUrl){
				$('#img').show();
				$('#img').attr("src",imgUrl);
				$('#img').closest("a").attr("href",imgUrl);
			}else{
				$('#img').hide();
			}
			colorbox.init();
		});
	}
	
	/**
	 * 删除上传的图片
	 */
	function removeRemoveUpload(){
		$("body").on("click",".icon-remove",function(){
	    	$(this).parents("li").remove();
	    	//当图片全部删除完了，就要清空图片这个文本框
	    	var imgem = $("#upload-div").find('img');
			if(!imgem || imgem.length==0){
				$("#imageUrl").val("");
			}
	    });
	}
	
	/**
	 * 加载模板
	 * flag:切换标记
	 */
	function loadTemplet(columnsType,flag){
		var url='/module/columns/common/app_templet.html';
		var data = {};
		if(columnsType){
			data['columnsType'] = columnsType;
		}else{
			return false;
		}		
		if(columnsType==COLUMNSTYPE2){
			$('#templet-div').hide();
			return;
		}
		$('#templet-div').show();
		data['appId'] = appId ? appId :'';
		data['templetCode'] = templetCode ? templetCode : '';
		data['parentId'] = parentId ? parentId : '';
		data['columnsType'] = columnsType ? columnsType : '';
		data['flag'] = flag;
		cqliving_ajax.load($('#templet-div'),url,data,function(){
			colorbox.init();
		});
		
	}
	
    function init(){
    	//栏目类型
    	if(!columnsType){
    		columnsType = $('.columnsType').val();
    	}
    	//加载模板
    	loadTemplet(columnsType,false);
    	//栏目图片名称
    	var imgName = $('#imageName').val();
    	
    	//栏目2倍选中初始化
    	myUploader.init({
    		url:"/common/upload.html?imageName="+imgName+"2.png",
    		containerId:"img_upload48",
    		thumbContainerId:"imgView48",
    		extensions:"png",
    		height:"48px",
    		width:"48px",
    		fileUrlPath:imgUrl,
    		success:function(file,response){
    			var filePath = response.data.filePath;
    			if(filePath){
    				filePath = filePath.substring(0,filePath.lastIndexOf('.')-1);
    				$("#imageUrl").val(imgUrl+filePath);
    				$('#imgView48').find("a").eq(0).attr("href",imgUrl + filePath + "2.png");
    				$("#imgView48").find("li:last").find('img').eq(0).attr('src',imgUrl + filePath + "2.png");
    			}
    		},
    		error:function(file,reason){
    			cqliving_dialog.alert(reason);
    		},
    		isSingle:true
    	});
    	
    	//栏目2倍未选中初始化
    	myUploader.init({
    		url:"/common/upload.html?imageName="+imgName+"20.png",
    		containerId:"img_upload48-0",
    		thumbContainerId:"imgView48-0",
    		extensions:"png",
    		height:"48px",
    		width:"48px",
    		fileUrlPath:imgUrl,
    		success:function(file,response){
    			var filePath = response.data.filePath;
    			if(filePath){
    				filePath = filePath.substring(0,filePath.lastIndexOf('.')-2);
    				$("#imageUrl").val(imgUrl+filePath);
    				$('#imgView48-0').find("a").eq(0).attr("href",imgUrl + filePath + "20.png");
    				$("#imgView48-0").find("li:last").find('img').eq(0).attr('src',imgUrl + filePath + "20.png");
    			}
    		},
    		error:function(file,reason){
    			cqliving_dialog.alert(reason);
    		},
    		isSingle:true
    	});
    	
    	//栏目3倍选中初始化
    	myUploader.init({
    		url:"/common/upload.html?imageName="+imgName+"3.png",
    		containerId:"img_upload72",
    		thumbContainerId:"imgView72",
    		extensions:"png",
    		height:"72px",
    		width:"72px",
    		fileUrlPath:imgUrl,
    		success:function(file,response){
    			var filePath = response.data.filePath;
    			if(filePath){
    				filePath = filePath.substring(0,filePath.lastIndexOf('.')-1);
    				$("#imageUrl").val(imgUrl+filePath);
    				$('#imgView72').find("a").eq(0).attr("href",imgUrl + filePath + "3.png");
    				$("#imgView72").find("li:last").find('img').eq(0).attr('src',imgUrl + filePath + "3.png");
    			}
    		},
    		error:function(file,reason){
    			cqliving_dialog.alert(reason);
    		},
    		isSingle:true
    	});
    	
    	//栏目3倍未选中初始化
    	myUploader.init({
    		url:"/common/upload.html?imageName="+imgName+"30.png",
    		containerId:"img_upload72-0",
    		thumbContainerId:"imgView72-0",
    		extensions:"png",
    		height:"72px",
    		width:"72px",
    		fileUrlPath:imgUrl,
    		success:function(file,response){
    			var filePath = response.data.filePath;
    			if(filePath){
    				filePath = filePath.substring(0,filePath.lastIndexOf('.')-2);
    				$("#imageUrl").val(imgUrl+filePath);
    				$('#imgView72-0').find("a").eq(0).attr("href",imgUrl + filePath + "30.png");
    				$('#imgView72-0').find("li:last").find('img').eq(0).attr('src',imgUrl + filePath + "30.png");
    			}
    		},
    		error:function(file,reason){
    			cqliving_dialog.alert(reason);
    		},
    		isSingle:true
    	});
    	
    	/**
    	 *  url校验 
    	 */
    	function isURL(str_url){
    		//jquery validate 里的正则表达式
    		return /^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})).?)(?::\d{2,5})?(?:[/?#]\S*)?$/i.test(str_url);
    	}
    	
    	//提交
    	$("#commonSaveButton").click(function(){
    		var flag = true;
    		//根据栏目类型校验
    		var type=$('.columnsType').val();
    		if(type==2){
    			var columnsUrl = $("#columnsUrl").val();
    			if(!columnsUrl){
	    			$("#url-tip").text("请输入栏目URL");
	    			$("#columnsUrl").focus();
	    			flag = false;
    			}else{
    				$("#url-tip").text("");
    			}
    		}
    		var imgurl_now = $('#imageUrl').val(); 
    		//判断是否选择了栏目图标
    		if(imgurl_now){
    			//判断栏目图标是否与数据库中的一致
    			if(!(imgUrlDb == imgurl_now)){
    				//判断图片的张数是否有4张
    				var imgem = $("#upload-div").find('img');
    				if(imgem.length<4){
    					$("#img-span-tip").text("请上传栏目图标（共4张）");
    					flag = false;
    				}else{
    					$("#img-span-tip").text("");
    				}
    			}
    		}
    		
    		if(!$("#form1").valid()){
    			flag = false;
    		}
    		if(!flag){
    			return false;
    		}
    		var params = $("#form1").serializeArray();
			var url = $("#form1").attr("action");
			cqliving_ajax.ajaxOperate(url,$("#form1"),params,function(data,status){
				if(data.code >= 0){
					$("#commonSaveButton").unbind("click");
					cqliving_dialog.success(data.message?data.message:"保存成功",function(){
						if($("#commonSaveButton").attr("back_url")){
							var url = $("#commonSaveButton").attr("back_url");
							if(appId) {
								url += '?search_EQ_appId='+appId;
							}
							window.location.href = url;
						}
					});
				}else{
					cqliving_dialog.error(data.message?data.message:"保存失败");
				}
			});
    	});
    	
    	/**
    	 * 栏目类型切换
    	 */
    	$(".columnsType").change(function(){
    		var type=$(this).val();
    		if(type==COLUMNSTYPE0){
    			$("#wl").hide();
    			$("#myDefinition").show();
    		}else if(type==COLUMNSTYPE2){
    			$("#wl").show();
    			$("#myDefinition").hide();
    		}else{
    			$("#wl").show();
    			$("#myDefinition").hide();
    		}
    		loadTemplet(type,true);
    	});
    	
    	//数据校验
        $("#form1").validate({
        	ignore: "",
            rules: {
                    name : {
                    required: true
                },
                    status : {
                    required: true
                },
                	columnsType : {
                    required: true
                }
            },
            messages: {
                name : {
                    required: '请输入栏目名称'
                },
                status : {
                    required: '请选择前台是否显示'
                },
                columnsType : {
                    required: '请选择栏目类型'
                }
            }
        });
    }
});