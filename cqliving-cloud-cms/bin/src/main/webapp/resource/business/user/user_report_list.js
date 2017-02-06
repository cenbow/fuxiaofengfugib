define(['cloud.table.curd','cloud.time.input','cqliving_dialog','cqliving_ajax','chosen'], function(tableCurd,timeInput,cqliving_dialog,cq_ajax){
	return {
		init: function(){
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
			//初始化APP选择列表框
			$(".chosen-select").chosen({search_contains:true});
			$('body').tooltip({selector:'[data-rel=tooltip]'});
			//审核
			bindOpenModelAduitButton();
		}
	};
	
	//审核，列表数据操作项中的“修改”按钮 add by huxiaoping 20160929
	function bindOpenModelAduitButton(){
		$(".main-content").on('click' ,"a[open-model='aduit'],button[open-model='aduit']", function(){
			var options={
					title:$(this).attr("open-title") ? $(this).attr("open-title"):'审核',
							content:"",//没有可以不填写
							url:$(this).attr("url"),
							buttons: {
								aduit_pass: {  
									label: "举报属实",  
									callback: function (jthis,jModel,param) {
										aduit_pass(jthis,jModel,param);
									}
								},
								aduit_reject: {  
									label: "举报不实",  
									callback: function (jthis,jModel,param) {
										aduit_reject(jthis,jModel,param);
									}
								}
							}
			};
			cqliving_dialog.model_dialog(options);
		});
	}
	
	/**
	 * 审核不通过回调
	 */
	function aduit_reject(jthis,jModel,param){
		oper(jthis,jModel,param,$("#noPass").val());
	}
	
	/**
	 * 审核通过回调
	 */
	function aduit_pass(jthis,jModel,param){
		oper(jthis,jModel,param,$("#pass").val());
	}
	
	/**
	 * @param {jquery对象} jthis :点击模态对话框上当前按钮的对象
	 * @param {jquery对象} jModel :当前弹出的模态对象框对象
	 * @param {object对象} param :自己传进去的参数，这里只是透传回来
	 * @param {数字对象} aduitType :自己传进去的参数，标识审核通过不通过
	 */
	function oper(jthis,jModel,param,aduitType){
		var jform = $("#fo");
		$("#status").val(aduitType);
		var params = jform.serializeArray();
		var url = jform.attr("action");
		cq_ajax.ajaxOperate(url,jform,params,function(data,status){
			if(data.code >= 0){
				jthis.unbind("click");
				cqliving_dialog.success(data.message?data.message:"审核成功",function(){
					jModel.modal('hide');
					$("#searchButton").trigger("click");
				});
			}else{
				cqliving_dialog.error(data.message?data.message:"审核失败");
			}
		});
	}	
});