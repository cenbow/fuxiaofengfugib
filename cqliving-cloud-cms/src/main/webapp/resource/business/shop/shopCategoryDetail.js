define(["validator.bootstrap", "cloud.table.curd", "cloud.time.input", "cqliving_dialog", "cqliving_ajax", "cqliving_input", "chosen"], 
		function($, tableCurd, timeInput, cqDialog, cq_ajax, cqInput) {
	return {
		init: function() {
			timeInput.initTimeInput();
			tableCurd.bindSaveButton();
			
			//表单验证
			formValidInit();
			//初始化下拉多选控件
			$(".chosen-select").chosen({search_contains: true}); 
			$(".chosen-container-single").css("width", "100%");
			//新增时，处理类型下拉数据
			if ($(":hidden[name=id]").length <= 0) {
				handleTypeData();
				$("#shop_category_appid").change(handleTypeData);
			}
		}
	};
	
	/** 表单验证 */
	function formValidInit() {
		$("#form1").validate({
            rules: {
                    typeId : {
                    required: true,
                },
                    name : {
                    required: true
                },
            },
            messages: {
                typeId : {
                    required: '请选择所属类型'
                },
                name : {
                    required: '请输入分类名称'
                },
            }
        });
	}
	
	/** 处理类型下拉数据 */
	function handleTypeData() {
		//重置类型下拉框
		$("#typeId").val("");
		var appId = $("#shop_category_appid").val();	
		$("option[appid]").hide();
		$("option[appid=" + appId + "]").show();
	}
	
});