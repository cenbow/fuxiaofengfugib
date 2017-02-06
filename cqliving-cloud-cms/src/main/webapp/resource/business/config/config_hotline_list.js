define(['validator.bootstrap','cloud.table.curd','cqliving_ajax','cqliving_dialog','chosen'], function($,tableCurd,cqliving_ajax,cqliving_dialog){
	return {
		init:loadInit
	};
	/**
	 * 初始化加载
	 */
	function loadInit(){
		//座机号码校验
		phoneValidator();
		tableCurd.initTableCrud();
		//初始化APP选择列表框
		$(".chosen-select").chosen({search_contains:true});
		//加载分类
		loadType();
		//app改变事件
		$("body").on("change","#search_EQ_appId",function(){
			loadType();
		});
		
		//修改排序号
		updateSortNo();
	};
	//座机号码校验
	function phoneValidator(){
		tableCurd.setValid(function(form){
			var flag = true;
			var regPhone= /^((\d)+-){0,1}(\d)+$/;
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
				$("#phone-error-tip").text("");
				flag = form.valid();
			}
			return flag;
		});
	}
	
	/**
	 * 修改排序号
	 */
	function updateSortNo(){
		
		$('body').on('change','.only_num',function(){
			var jThis = $(this);
			var url = jThis.attr("url");
			var val = $(this).val();
			var old = $(this).next("input").val();
			try{
				var re = /^[1-9][0-9]*$/ ;
		        var result=  re.test(val);
				if(!result){
					cqliving_dialog.error("排序号只能输入只能输入大于0的整数");
					$(this).val(old);
					return;
				}
			}catch(e){
				cqliving_dialog.error("排序号只能输入只能输入大于0的整数");
				$(this).val(old);
				return;
			}
			var id = $(this).attr("iid");
			cqliving_ajax.ajaxOperate(url,jThis,{"id":id,"sortNo":val},function(data,status){
				if(data.code >= 0){
					$("#searchButton").trigger("click");
				}else{
					cqliving_dialog.error(data.message?data.message:"操作失败");
				}
			});
		});		
	}
	
	/**
	 * 加载分类
	 */
	function loadType(){
		var appId = $("#search_EQ_appId").val();
		if(appId){
			var url='/module/config_hotline/common/load_type_select.html';
			$('#type-div').show();
			var data = {};
			data['appId'] = appId;
			cqliving_ajax.load($('#type-div'),url,data,function(){});
		}
	}
	
	/**
	 * 加载按钮事件
	 */
	/*function bindOpenModelDetailButton(){
		$("body").on('click' ,"button[open-model='detail-btn'],a[open-model='detail-btn']", function(){
			var options={
				title:$(this).attr("open-title") ? $(this).attr("open-title"):'修改',
				content:"",//没有可以不填写
				url:$(this).attr("url"),
				buttons: {
					ok: {  
		                label: "确定",  
		                callback: function (jthis,jModel,param) {
		                	callBack(jthis,jModel,param);
		                }
		            }
		        } 
			};
			cqliving_dialog.model_dialog(options);
		});
	}*/
	
	/**
	 * 电话号码校验
	 * 校验通过，返回true,校验不通过，返回false
	 */
	/*function phoneValidate(){
		debugger;
		//校验成功与失败标记
		var flag = true;
		var regPhone= /^((\d)+-){0,1}(\d)+$/;
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
			$("#phone-error-tip").text("");
		}
		return flag;
	}*/
	
	/**
	 * @param {jquery对象} jthis :点击模态对话框上当前按钮的对象
	 * @param {jquery对象} jModel :当前弹出的模态对象框对象
	 * @param {object对象} param :自己传进去的参数，这里只是透传回来
	 */
	/*function callBack(jthis,jModel,param){
		var jform = jthis.parents(".modal-content").find("form");
		//数据校验
		var phoneCheck = phoneValidate();
		var formFlag = jform.valid();
		//if(!jform.valid())return;
		//console.log('form校验：'+formFlag+",电话校验："+phoneCheck);
		//校验不通过，直接退出方法
		if(!formFlag || !phoneCheck){return false;}
		//表单序列化
		var params = jform.serializeArray();
		//获取请求路径
		var url = jthis.attr("url") ? jthis.attr("url") : jform.attr("action");
		if(!url){
			cqliving_dialog.error("该操作的请求址不能为空");
		}else{
			cqliving_ajax.ajaxOperate(url,jform,params,function(data,status){
				if(data.code >= 0){
					jthis.unbind("click");
					cqliving_dialog.success(data.message?data.message:"保存成功",function(){
						jModel.modal('hide');
						$("#searchButton").trigger("click");
					});
				}else{
					cqliving_dialog.error(data.message?data.message:"保存失败");
				}
			});
		}
	}*/
});