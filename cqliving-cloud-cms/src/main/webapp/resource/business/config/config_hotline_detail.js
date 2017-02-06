define(['validator.bootstrap','cqliving_ajax','chosen'], function($,cqliving_ajax){
	return {
		init:loadInit
	};
	
	function loadInit(){
		//初始化APP选择列表框
		$(".chosen-select").chosen({search_contains:true});
		//加载分类
		loadType();
		//app下拉框change后要修改分类
		changeApp();
		//添加电话号码按钮
		addPhone();		
		//删除按钮事件
		delPhone();	
		//添加整数校验方法
		addJqueryValidate();		
    	//表单校验
		formValidate();
		//保存
		//save();
	};
	
	/**
	 * 修改app
	 */
	function changeApp(){
		$("body").on("change","#appId",function(){
			loadType();
		});
	}
	
	/**
	 * 加载分类
	 */
	function loadType(){
		var appId = $("#appId").val();
		if(appId){
			var url='/module/config/9/common/getByAppPage.html';
			$('#type-div').show();
			var data = {};
			data['appId'] = appId;
			data['isList'] = false;
			cqliving_ajax.load($('#type-div-detail'),url,data,function(){});
		}
	}
	
	/**
	 * 页面添加电话号码
	 */
	function addPhone(){
		$(".add-phone").on("click",function(){
			if($(".phone-mode").length<3){
				//克隆第一个电话号码
				var phoneDiv = $(".phone-mode").eq(0).clone(true);
				//清空电话号码
				phoneDiv.find(".phones").val("");
				//追加到电话区域
				$(".phones-div").append(phoneDiv);
				//移除灰色样式，添加红色样式
				$(".remove-phone-btn").removeClass("btn-default").addClass("btn-danger");
				//删除按钮移除disabled
				$(".remove-phone-btn").removeAttr("disabled");
				//隐藏添加按钮
				if($(".phone-mode").length==3){
					$(this).hide();
				}
			}
		});
	}
	
	/**
	 * 页面删除电话号码
	 */
	function delPhone(){
		$("body").on("click",".remove-phone-btn",function(){
			if($(".phone-mode").length>1){
				//删除
				$(this).closest(".phone-mode").remove();
				//唯一一个不允许删除
				if($(".phone-mode").length==1){
					$(".remove-phone-btn").attr("disabled","disabled");
					$(".remove-phone-btn").removeClass("btn-danger").addClass("btn-default");
				}
				//显示添加按钮
				$(".add-phone").show();
				$("#phone-error-tip").text("");
			}
		});
	}
	
	/**
	 * 添加jquery校验方法
	 */
	function addJqueryValidate(){
		$.validator.addMethod("positiveInteger", function(value, element){
			return this.optional(element) ||/^[1-9][0-9]*$/.test(value);
		}, "只能输入大于0的整数");
	}
	
	/**
	 * 表单校验
	 */
	function formValidate(){
		$("#form1").validate({
            rules: {
                    appId : {
                    required: true,
                    number:true
                },
                    hotlineTypeId : {
                    required: true,
                    number:true
                },
                    name : {
                    required: true
                },
                    sortNo : {
                    required: true,
                    positiveInteger:true
                }
            },
            messages: {
                appId : {
                    required: '请选择所属客户端'
                },
                hotlineTypeId : {
                    required: '请选择热线分类'
                },
                name : {
                    required: '请输入热线名称'
                },
                sortNo : {
                    required: '请输入排序号'
                }
            }
        });
	}
	
	/**
	 * 电话号码校验
	 * 校验通过，返回true,校验不通过，返回false
	 */
	/*function phoneValidate(){
		//校验成功与失败标记
		var flag = true;
//		var regPhone= /^(^0\d{2}-?\d{8}$)|(^0\d{3}-?\d{7}$)|(^\(0\d{2}\)-?\d{8}$)|(^\(0\d{3}\)-?\d{7}$)$/;
		var regPhone= /^((\d)+-)?(\d)+$/;
		$('.phones').each(function(){
			var val = $(this).val();
			if(!val){
				$("#phone-error-tip").text("请输入电话号码");
				flag = false
				return false;
			}else if(!regPhone.test(val)){
				$("#phone-error-tip").text("电话号码有误，格式：区号-座机号码或座机号码");
				flag = false;
				return false;
			}
		});
		if(flag){
			$(".phone-error-tip").text("");
		}
		return flag;
	}*/
	
	/**
	 * 保存
	 */
	/*function save(){
		$(".draft_save").on("click",function(){
    		//校验电话号码
    		var phoneCheck = phoneValidate();
    		//表单校验
    		var jthis = $(this);
    		var jform = $("#form1");
    		var formFlag = jform.valid();
    		console.log('form校验：'+formFlag+",电话校验："+phoneCheck);
			if(!formFlag || !phoneCheck){
				return false;
			}
			//提交后台
			var params = jform.serializeArray();
			var url = jthis.attr("url") ? jthis.attr("url") : jform.attr("action");
			url = url ? url : location.href;
			cqliving_ajax.ajaxOperate(url,jform,params,function(data,status){
				if(data.code >= 0){
					jthis.unbind("click");
					dialog.success(data.message?data.message:"保存成功",function(){
						if(jthis.attr("back_url")){
							// 点击查询按钮刷新列表页面
							$("#searchButton").trigger("click");
						}
					});
				}else{
					dialog.error(data.message?data.message:"保存失败");
				}
			});
    	});
	}*/
});