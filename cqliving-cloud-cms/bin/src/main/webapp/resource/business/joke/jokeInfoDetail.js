define(["validator.bootstrap", "cloud.table.curd", "cloud.time.input", "cqliving_dialog", "cqliving_ajax", "wenzi"], function($, tableCurd, timeInput, cqDialog, cq_ajax) {
	return {
		init: function() {
//			tableCurd.bindSaveButton();
			timeInput.initTimeInput();
			
			//增加字数超出验证
			$.validator.addMethod("wordsLimit", function(value, element, param) {
				return this.optional(element) || wordsLimit(element);
			}, "内容字数过多");
			
			//表单验证
			$("#form1").validate({
	            rules: {
	                    content : {
	                    required: true,
	                    wordsLimit: true
	                },
	                    onlineTime: {
	                    required: true,
	                    date: true
	                }
	            },
	            messages: {
	                content : {
	                    required: ' '
	                },
	                onlineTime : {
	                    required: ' ',
	                    date:' '
	                },
	            }
	        });
			
			//控制
			$("#form1").myWords({
				obj_opts: "#content",
				obj_Maxnum: 1000,	//要是只能输入500字
			    obj_Lnum: ".content_num"
			});
			 
			//保存
			$(".commonSaveButton").click(function() {
				$("#joke_info_status").val($(this).attr("statusval"));
				var jthis = $(this);
				var jform = jthis.parents("form");
				if (!jform.valid()) {
					return;
				}
				var params = jform.serializeArray();
				var url = jthis.attr("url") ? jthis.attr("url") : jform.attr("action");
				url = url ? url : location.href;
				cq_ajax.ajaxOperate(url, jform, params, function(data, status) {
					if (data.code >= 0) {
						jthis.unbind("click");
						cqDialog.success(data.message ? data.message : "保存成功", function() {
							if (jthis.attr("back_url")) {
								window.location.href = jthis.attr("back_url");
							}
						});
					} else {
						cqDialog.error(data.message ? data.message : "保存失败");
					}
				});
			});
		}
	};
	
	/** 验证内容长度 */
	function wordsLimit(element) {
		var flag = true;
		var text = $(element).next(".sns-num").text();
		var $em = $(element).next(".sns-num").find("em");
		var num = parseInt($em.text(), 10);
		if (text.indexOf("超出") != -1 || (num && num < 0)) {
			flag = false;
		}
		return flag;
	}
	
});