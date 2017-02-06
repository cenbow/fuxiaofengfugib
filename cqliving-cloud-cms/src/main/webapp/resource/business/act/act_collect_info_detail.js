define(['validator.bootstrap','cloud.table.curd','cqliving_dialog','cqliving_ajax','chosen'], function($,tableCurd,dialog,cq_ajax){
	return {
		init:loadInit
	};
	
	function loadInit(){
		tableCurd.bindSaveButton();
		$(".chosen-select").chosen({search_contains:true});
		/**
    	 * 添加选项
    	 * 复制模板行，添加到选项末尾
    	 */
    	$(".add-option").on("click",function(){
    		$(".option-div").clone(true).removeAttr("style").removeClass("option-div").appendTo("#option-field");
    	});
    	
    	//删除选项
    	$(".del-option").on("click",function(){
    		$(this).closest(".option-tr").remove();
    	});
    	
    	//添加length校验，整数，范围0-500
    	$.validator.addMethod("lengthinteger", function(value, element) {
	 		var aint=parseInt(value);	
	 		return aint>=0 && (aint+"")==value && aint<=500;   
	 	}, "请输入小于500的正整数."); 
    	
    	//点击上传excel，触发文件框点击事件
    	$("#import-a").on("click",function(){
    		$("#excelFile").click();
    	});
    	
    	//下载按钮事件
    	$("#down-a").on("click",function(){
    		window.location.href = '/common/downTemp.html?tempName=ACT_SELECT_TEMPLATE.xlsx';
    	});
    	
    	//上传
    	upload();    	
    	//表单校验
    	formValidate();
    	//保存0
    	save();
    	
	}
	/**
	 * 导入回调
	 */
	function callback(data, status){
    	if(data.code >= 0){
    		if(data.data){
    			$.each(data.data,function(i,item){
    				var tr = $(".option-div").clone(true).removeAttr("style").removeClass("option-div");
    				var inp = tr.find(".value").eq(0);
    				if(inp){
    					inp.val(item.value);
    				}
    				tr.appendTo("#option-field");
    			});
    		}
    	}else{
    		var message = data.message;
    		if(message){
    			dialog.alert(message);
    		}else{
    			dialog.alert("导入失败，请刷新重试！");
    		}
    	}
    }
	
	/**
	 * 上传
	 */
	function upload(){
		$(document).off("change","#excelFile").on("change","#excelFile",function(){
			//校验文件后缀
			if($("#excelFile").val()){
				var config = {
						allowSuffix : [ "xls","xlsx"]
				};
				var allowUpload = cq_ajax.checkSuffix(config, "excelFile");
				if( !allowUpload){
					var permitSuffix=config.allowSuffix.join(",");
					var info = "请选择“"+permitSuffix+"”格式的文件!";
					dialog.alert(info);
					return;
				}
				var url = "/module/act/importExcel.html?time="+new Date().getTime()
				//上传
				cq_ajax.ajaxFileUpload(url,"excelFile",callback);
			}
		});
	}
	
	/**
	 * 表单校验
	 */
	function formValidate(){
		$("#form-act-cfg").validate({
        	ignore: "",
            rules: {
                    appId : {
                    required: true,
                    number:true
                },
                    name : {
                    required: true
                },
                    type : {
                    required: true
                }
                ,
                    length : {
                    required: true,
                    lengthinteger:true
                }
            },
            messages: {
                appId : {
                    required: '请选择客户端',
                    number:''
                },
                name : {
                    required: '请输入参数名称'
                },
                type : {
                    required: '请选择参数类型'
                }
                ,
                length : {
                    required: '请输入整数类型的限制，范围：0-500',
                    lengthinteger:'请输入小于500的正整数'
                }
            }
        });
	}
	
	/**
	 * 保存
	 */
	function save(){
    	$("#saveBtn").on("click",function(){
    		var flag = true;
    		//校验是否添加选项信息
    		if(TYPE1!=type && $('.value').not(":first").length<1){
    			$("#option-tip").text("请添加选项信息");
    			flag = false;
    		}else{
    			$("#option-tip").text("");
    		}
    		
    		//校验选项信息是否已经填写名称
    		$('.value').not(":first").each(function(){
    			var val = $(this).val();
    			if(!val){
    				$(this).closest(".option-tr").find("span").text("请输入选项名称");
    				flag = false;
    			}else if($.trim(val).length>50){
    				$(this).closest(".option-tr").find("span").text("选项名称最多50字");
    				flag = false;
    			}else{
    				$(this).closest(".option-tr").find("span").text("");
    			}
    		});    		
    		
    		//表单校验
    		var jthis = $(this);
    		var formFlag = $("#form-act-cfg").valid();
			if(!(formFlag && flag)){
				return false;
			}
			var jform = $("#form-act-cfg");
			//提交后台
			var params = jform.serializeArray();
			var url = jthis.attr("url") ? jthis.attr("url") : jform.attr("action");
			url = url ? url : location.href;
			cq_ajax.ajaxOperate(url,jform,params,function(data,status){
				if(data.code >= 0){
					jthis.unbind("click");
					dialog.success(data.message?data.message:"保存成功",function(){
						if(jthis.attr("back_url")){
							// 点击查询按钮刷新列表页面
							$("#searchButton").trigger("click");
							// 关闭弹出窗口
							$("#modal-form").modal("hide");
						}
					});
				}else{
					dialog.error(data.message?data.message:"保存失败");
				}
			});
    	});
	}
});