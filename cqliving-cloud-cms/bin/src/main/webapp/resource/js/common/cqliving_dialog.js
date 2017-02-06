define(['toastr'],function(toastr){
	return {
		//确认提示框
		confirm: confirm,
		//alert框
		alert: alert,
		
		//操作成功后的提示
		success : success,
		//操作失败后的提示
		error : error,
		//操作警告的提示
		warning : warning,
		//自定义弹层对话框
		model_dialog : model_dialog
	};
	/**
	 * 确认提示框
	 * fn:点击确认按钮要执行的方法
	 * title:确认提示框标题，默认为"确认操作"
	 * content:提示内容，默认为"确认删除吗？"
	 * param:回调函数需要的参数
	 * 
	 */
	function confirm(title,content,fn,param){
		var options={
			title: title?title:'操作确认',
			content:content ? content:"确认该操作吗？",//没有可以不填写
			buttons: {
				ok: {  
	                label: "确定",  
	                param: param,
	                callback: function (jthis,jModel,param) {
	                	$.isFunction(fn) ? fn(jthis,jModel,param) : "";
	                	jModel.modal('hide');
	                }
	            }
	        } 
		};
		model_dialog(options);
	}
	/**
	 * alert框
	 * content:要提示的信息
	 * fn:点击确认按钮要执行的方法
	 * param:回调函数需要的参数
	 * 
	 */
	function alert(content,fn,param){
		var options={
			title: '操作提示',
			content:content ? content:"",//没有可以不填写
			buttons: {
				cancel: {  
	                label: "关闭",  
	                param: param,
	                callback: function (jthis,jModel,param) {
	                	$.isFunction(fn) ? fn(jthis,jModel,param) : "";
	                }
	            }
	        } 
		};
		model_dialog(options);
	}
	
	/**
	 * 默认显示取消按钮，如果需要其它的按钮，请使用下面提供的按钮和样式，自己可以改样式。
	 * 如果用户传入默认buttons中不存在的按钮，则这些按钮都依次放在取消按钮的前面，默认按钮的前面
	 * 可以参考cloud.table.curd.js中的bindOpenModelAddButton方法使用
	 * @author yuwu 20160706
	 * @param {object} 弹出操作参数，见方法体里面的defaultOptions对象 
	 * options={
	 * 		id:'common_modal',//弹层默认DIV的ID,后面会加一个8位随机数
			title:'操作',
			content:"",//内容，如果url不为空，则该url会覆盖该参数的值
			url:"",//如果url不为空，则该url会覆盖上面content参数的值
			width:"",//对话框的高度，默认最少是700，少于700该值宽度依然是700
			buttons: {
	            cancel: {
					isShow: true,//是否则显该按钮，在调用时不用设置，只要添加了该按钮都会显示
	                label: "取消",//按钮名称
	                className: "btn btn-sm btn-danger",//按钮样式
	                iconClass: "icon-remove",//按钮汉字前的icon
	                data_dismiss: "modal",//点击取消自动关闭弹层
	                param : {},//回调函数中用户传递过来的参数
	                callback: function (jthis,jModel,param) {
	                	//jthis:在弹层上点击的当前按钮
	                	//jModel:当前弹层模态对话框
	                	//param:自己传进来的参数，这里不做任何修改透传回来
	                } //点击按钮后的回调操作
	            }  
	        } 
		};
	 */
	function model_dialog(options){
		var defaultOptions = getDefaultOptions();
		//用配置项代替默认项，深度拷贝替换
		$.extend(true,defaultOptions, options);
		//设置只要在options配置了的按钮都显示
		if(options.buttons){
			$.each(options.buttons,function(name,value){
				defaultOptions.buttons[name].isShow = true;
			});
		}
		
		//1、创建弹层，并设置弹层DIV的ID
		var $jmodal = getNewJModel(defaultOptions.id);
		//2、设置对话框的宽度
		setModelWidth($jmodal,defaultOptions.width);
		//3、设置标题
		setModelTitle($jmodal,defaultOptions.title);
		//4、设置按钮,绑定事件
		setBindButtonEven($jmodal,defaultOptions.buttons);
		//5、绑定bootstrap-modal自带的其它事件
		setBindOtherEven($jmodal);
		//6、如果有URL则请求URL填充内容，覆盖之前content的内容
		setUrlContext($jmodal,defaultOptions.content,defaultOptions.url);
		//7、当多次打开modal的时候，设置z-index
		setModelIndex($jmodal);
	};
	
	//1、获取一个新的弹层jquery对象
	function getNewJModel(id){
		var randomId = Math.ceil(Math.random()*10000000);
		var modalId = id + randomId;
		//获取弹层HTML
		var jhtml = $(getDialogDiv());
		//设置弹层ID
		jhtml.attr("id",modalId);
		$("body").append(jhtml);
		var jmodal = $("#" + modalId);
		return jmodal;
	}
	
	//2、设置弹层宽度
	function setModelWidth(jmodal,widthParam){
		var width = widthParam;
		if(width){
			width = (new String(width).indexOf("px") != -1) ? width : width + "px"; 
			jmodal.find(".modal-dialog").css("width",width);
		}
	}
	
	//3、设置标题
	function setModelTitle(jmodal,title){
		jmodal.find(".modal-title").html(title);
	}
	
	//4、设置按钮,绑定事件
	function setBindButtonEven(jmodal,buttons){
		var jfooter = jmodal.find(".modal-footer");
		jfooter.empty();
		var btnHtml = "<button type=\"button\" bind_name=\"\" class=\"\" data-dismiss=\"\"><i class=\"\"></i></button>";
		//var btnHtml = "<button type=\"button\" bind_name=\"ok\" class=\"btn btn-sm btn-danger\" data-dismiss=\"modal\"><i class=\"icon-remove\"></i>取消</button>";
		$.each(buttons,function(name,value){
			if(value.isShow){
				var jbtnHtml = $(btnHtml);
				//5.1设置按钮的样式、名称等其它属性
				jbtnHtml.attr("bind_name",name).attr("class",value.className).attr("data-dismiss",value['data_dismiss'] ? "modal":"").append(value.label);
				//5.2设置按钮前的图标
				jbtnHtml.find("i").attr("class",value.iconClass);
				
				//5.3将按钮插入到页面
				var jcancal = jfooter.find("button[bind_name='cancel']");
				if(jcancal.length > 0){//如果“取消”按钮已经存，则将按钮插在“取消”按钮前面
					jcancal.before(jbtnHtml);
				}else{
					jfooter.append(jbtnHtml);
				}
				//5.4绑定按钮事件
				jfooter.find("button[bind_name='"+name+"']").click(function(){
					//调用回调函数，且把当前事件对象和当前弹层对象及用户传递过来的参数传递过去
					value.callback && $.isFunction(value.callback) ? value.callback($(this),jmodal,value.param) : "";
				});
			}
		});
	}
	//5、绑定bootstrap-modal自带的其它事件
	function setBindOtherEven(jmodal){
		//绑定事件：当隐藏该模态窗口时，删除该对象。因为如果不删除，下一次弹出的位置和上一次隐藏的位置一样
		jmodal.on('hidden.bs.modal', function () {
			$(this).remove();
		});
	}
	
	//6、设置弹层的内容，如果有URL则请求URL填充内容，覆盖之前content的内容
	function setUrlContext(jmodal,content,url){
		//设置弹层的内容
		jmodal.find(".modal-body").html(content);
		//如果有URL则请求URL填充内容，覆盖之前content的内容
		if(url){
			jmodal.find(".modal-body").load(url,function(){
				if(jmodal.find("input[time_options]").length > 0){
					jmodal.removeAttr("tabindex");//如果有时间输入框，则取消键盘事件，否时间控件中的月、年、时、分不能选择
				}
				jmodal.modal("show");
			})
		}else{
			if(jmodal.find("input[time_options]").length > 0){
				jmodal.removeAttr("tabindex");//如果有时间输入框，则取消键盘事件，否时间控件中的月、年、时、分不能选择
			}
			jmodal.modal("show");  
		}
	}
	
	//7、当多次打开modal的时候，设置z-index
	function setModelIndex(jmodal){
		//不能用该方法绑定，第二次无效
		//jmodal.on('shown.bs.modal', function () {});
		//获取最后一个modal的z-index
		var z_index = $(".modal:visible").not(jmodal).last().css("z-index");
		if(z_index){
			jmodal.css("z-index",parseInt(z_index) + 10);
			jmodal.next().css("z-index",parseInt(z_index) + 5);
		}
	}
	
	/**
	 * 获取弹层HTML
	 */
	function getDialogDiv(){
		var html = "";
		html+="<div tabindex=\"-1\" class=\"modal\" id=\"common_modal\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">";
		html+="   <div class=\"modal-dialog\">";
		html+="      <div class=\"modal-content\">";
		html+="         <div class=\"modal-header\">";
		html+="            <button type=\"button\" class=\"close\" data-dismiss=\"modal\" ";
		html+="               aria-hidden=\"true\">×";
		html+="            </button>";
		html+="            <h4 class=\"modal-title\" id=\"myModalLabel\">";
		html+="            </h4>";
		html+="         </div>";
		html+="         <div class=\"modal-body\">";
		html+="         </div>";
		html+="         <div class=\"modal-footer\">";
		//html+="            <button type=\"button\" class=\"btn btn-success btn-sm draft_save\"><i class=\"icon-save\"></i>保存</button>";
		//html+="            <button type=\"button\" class=\"btn btn-sm btn-danger\" data-dismiss=\"modal\"><i class=\"icon-remove\"></i>取消</button>";
		html+="         </div>";
		html+="      </div><!-- /.modal-content -->";
		html+="   </div><!-- /.modal-dialog -->";
		html+="</div><!-- /.modal -->";
		return html;
	}
	
	/**
	 * 定义默认配置 
	 */
	function getDefaultOptions(){
		//默认配置
		var defaultOptions={
			id:"common_modal",//弹层的Div的ID
			title:'操作',
			content:"",//内容，如果url不为空，则该url会覆盖content
			url:"",//如果url不为空，则该url会覆盖content
			buttons: {
				aduit_submit: {
					isShow: false,
					label: "提交审核",  
	                className: "btn btn-primary btn-sm add_save",  
	                iconClass: "icon-edit bigger-110"
	            },
                aduit_pass: {
					isShow: false,
                	label: "审核通过",  
	                className: "btn btn-success btn-sm submitBtn",  
	                iconClass: "icon-ok bigger-110"
	            },
	            aduit_reject: {
					isShow: false,
	                label: "审核不通过",  
	                className: "btn btn-warning btn-sm submitBtn",  
	                iconClass: "icon-remove bigger-110"
	            },
	            submit: {
					isShow: false,
	            	label: "发布", 
	                className: "btn btn-primary btn-sm publish_save",  
	                iconClass: "icon-mail-forward bigger-110"
	            },
				ok: {
					isShow: false,
	                label: "确定",  
	                className: "btn btn-success btn-sm draft_save",  
	                iconClass: "icon-save"
	            },
	            cancel: {
					isShow: true,
	                label: "取消",
	                className: "btn btn-sm btn-danger",//按钮样式
	                iconClass: "icon-remove",//按钮汉字前的icon
	                data_dismiss: "modal",//点击取消自动关闭弹层
	                param : {},//回调函数中用户传递过来的参数
	                callback: function (jthis,jModel,param) {} //点击按钮后的回调操作
	            }  
	        } 
		};
		return defaultOptions;
	}
	
	/**
	 * 操作成功后一个回调信息
	 * @author yuwu 2016-05-30
	 * 具体参数参考：http://codeseven.github.io/toastr/demo.html
	 * info框
	 * message:要提示的信息
	 * callback:提示后的回调
	 * optionsOverride:参数配置信息
	 */
	function info(infoType, message, callback, optionsOverride){
		toastr.options = {
			  "closeButton": true,
			  "debug": false,
			  "newestOnTop": false,
			  "progressBar": false,
			  "positionClass": "toast-top-center",
			  "preventDuplicates": false,
			  "onclick": null,
			  "showDuration": "300",
			  "hideDuration": "1000",
			  "timeOut": "1000",
			  "extendedTimeOut": "1000",
			  "showMethod": "fadeIn",
			  "hideMethod": "fadeOut"
		};
		//订阅回调
		if(callback && $.isFunction(callback)){
			toastr["subscribe"](callback);
		}
		//提示信息
		toastr[infoType](message,'',optionsOverride);
	}
	
	function success(message, callback, optionsOverride){
		info('success',message, callback, optionsOverride);
	}
	function error(message, callback, optionsOverride){
		info('error',message, callback, optionsOverride);
	}
	function warning(message, callback, optionsOverride){
		info('warning',message, callback, optionsOverride);
	}
});