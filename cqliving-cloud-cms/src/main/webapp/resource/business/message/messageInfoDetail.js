define(["validator.bootstrap", "cloud.table.curd", "cloud.time.input", "cqliving_dialog", "cqliving_ajax", "chosen"], function($, tableCurd, timeInput, cqliving_dialog, cq_ajax) {
	return {
		init: function() {
			tableCurd.bindSaveButton();
			timeInput.initTimeInput();
			
			//表单验证
			validForm();
			//处理联动的表单
			handleFormElement();
			
			//初始化下拉多选控件
			$(".chosen-select").chosen({search_contains: true}); 
			$(".chosen-container-multi").css("width", "100%");
		}
	};
	
	/** 处理联动的表单 */
	function handleFormElement() {
		$("input[name=receiverType]").click(function() {
			var type = $("input[name=receiverType]:checked").val();
			if (type == "0") {	//前端用户
				$("#sendType2").click().closest("label").show();	
				$("#sendType0, #sendType1").closest("label").hide();	
			} else if (type == "1") {	//后端用户
				var sendType = $("input[name=sendType]:checked").val();
				if (sendType == "2") {
					$("#sendType0").click();	
				}
				$("#sendType0").closest("label").show();	
				$("#sendType1").closest("label").show();	
				$("#sendType2").closest("label").hide();	
			}
		});
	}
	
	/** 表单验证 */
	function validForm() {
		$("#form1").validate({
            rules: {
                    appId : {
                    required: true,
                    number:true
                },
                    title : {
                    required: true
                },
                    sendTime : {
                    required: true
                },
                    context : {
                    required: true
                },
                    receiverType : {
                    required: true
                },
                    sendType : {
                    required: true
                },
                    status : {
                    required: true
                },
                    createTime : {
                    required: true
                },
                    creatorId : {
                    required: true,
                    number:true
                },
                    creator : {
                    required: true
                },
                    updateTime : {
                    required: true
                },
                    updatorId : {
                    required: true,
                    number:true
                },
                    updator : {
                    required: true
                },
                    receiverAppId : {
                    required: true
                }
            },
            messages: {
                appId : {
                    required: ' ',
                    number:' '
                },
                title : {
                    required: ' '
                },
                sendTime : {
                    required: ' '
                },
                context : {
                    required: ' '
                },
                receiverType : {
                    required: ' '
                },
                sendType : {
                    required: ' '
                },
                status : {
                    required: ' '
                },
                createTime : {
                    required: ' '
                },
                creatorId : {
                    required: ' ',
                    number:' '
                },
                creator : {
                    required: ' '
                },
                updateTime : {
                    required: ' '
                },
                updatorId : {
                    required: ' ',
                    number:' '
                },
                updator : {
                    required: ' '
                },
                receiverAppId : {
                    required: ' '
                }
            }
        });
	}
	
});