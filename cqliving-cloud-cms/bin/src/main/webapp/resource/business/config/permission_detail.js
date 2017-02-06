define(['validator.bootstrap','cqliving_dialog','cqliving_ajax'],function($,cqliving_dialog,cqliving_ajax){
	return {
		init: function(){
			$("#myTab").find("li a").eq(0).click();
			submitForm();
			checkedAll();
			//pCheck();
		}
	};
	
	/**
	 * form 表单提交
	 */
	function submitForm(){
		$("#commonSaveButton").click(function(){
			var validate = formValidate();
			if(validate){
				var params = $("#form1").serializeArray();
				console.log(params);
				var url = $("#form1").attr("action");
				cqliving_ajax.ajaxOperate(url,$("#form1"),params,function(data,status){
					if(data.code >= 0){
						$("#commonSaveButton").unbind("click");
						cqliving_dialog.success(data.message?data.message:"保存成功",function(){
							if($("#commonSaveButton").attr("back_url")){
								var url = $("#commonSaveButton").attr("back_url");
								window.location.href = url;
							}
						});
					}else{
						cqliving_dialog.error(data.message?data.message:"保存失败");
					}
				});
			}
		});		
	}
	
	/**
	 * 表单校验
	 */
	function formValidate(){
		var i = 0;
		var requestTimesIntervals = $(".requestTimesInterval");
		var requestTimesLimits = $(".requestTimesLimit");
		var requestTimesInterval="",requestTimesLimit="";
		var reg = /^[1-9]*[1-9][0-9]*$/;
		for (i = 0; i < requestTimesIntervals.length; i++) {
			requestTimesInterval = $(requestTimesIntervals[i]).val();
			if(requestTimesInterval && !reg.test(requestTimesInterval)){
				cqliving_dialog.error($(requestTimesLimits[i]).closest(".widget-main").find(".permissionName").val()+"下，请求次数间隔时间只能是整数")
				return false;
			}
			requestTimesLimit = $(requestTimesLimits[i]).val();
			if(requestTimesLimit && !reg.test(requestTimesLimit)){
				cqliving_dialog.error($(requestTimesLimits[i]).closest(".widget-main").find(".permissionName").val()+"下，请求限制次数只能是整数")
				return false;
			}
		}
		return true;
		
	}
	/**
	 * 全选/反选
	 */
	function checkedAll(){
		$(".tab-content").on("click",":input[type=checkbox][name=checked_all]",function(){
		   var $this = $(this);
		   var isChecked = $this.is(":checked");
		   var $box = $this.closest(".widget-box");
		   if(isChecked){
			   checkAllChildren($box);
		   }else{
			   disCheckChildren($box);
		   }
		});
	}
	
	/**
	 * 子集全选
	 */
	function checkAllChildren(box){
		if(box && box.length>=1){
	    	 box.find(":input[type=checkbox]").prop("checked",true);
		   }
	}
	
	/**
	 * 子集反选
	 */
	function disCheckChildren(box){
		if(box && box.length>=1){
			box.find(":input[type=checkbox]").prop("checked",false);
		}
	}
	
	/**
	 * 父节点选中处理
	 */
	function pCheck(){
		$(".tab-content").on("click",":input[type=checkbox][defaultName=resIds]",function(){
			var $this = $(this);
			var isChecked = $this.is(":checked");
			var $box = $this.closest(".widget-box");
			if(!isChecked){
				disCheckChildren($box);
			}
		});
	}
});