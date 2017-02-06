define(['validator.bootstrap','cloud.table.curd','chosen'], function($,tableCurd){
	return {
		init: function(){
			$(".chosen-select").chosen({search_contains:true});
			//1、初始化
			init();
		}
	};
	
	function init(){
		tableCurd.bindSaveButton();
		//添加校验方法
		addJqueryValidate();		
		//表单校验
		formValidate();
	}
	
	/**
	 * 添加jquery校验方法
	 */
	function addJqueryValidate(){
		$.validator.addMethod("positiveinteger", function(value, element) {
			var aint=parseInt(value);	
			return aint>0&& (aint+"")==value;   
		}, "请输入正整数."); 
	}
	/**
	 * 表单校验
	 */
	function formValidate(){
		$("#form1").validate({
            rules: {
                    name : {
                    required: true
                },
                    templetCode : {
                    required: true
                },
                    limitType : {
                    required: true
                },
                    width : {
                    required: true,
                    number:true,
                    positiveinteger:true
                },
                    hight : {
                    required: true,
                    number:true,
                    positiveinteger:true
                },
                    context : {
                    required: true
                },
                    listType : {
                    required: true
                }
            },
            messages: {
                name : {
                    required: '请输入模板名称'
                },
                templetCode : {
                    required: '请输入模板CODE'
                },
                limitType : {
                    required: '请选择压缩类型'
                },
                width : {
                    required: '请输入宽',
                    number:'宽为整数类型',
                    positiveinteger:'宽为正整数类型'
                },
                hight : {
                    required: '请输入高',
                    number:'高为整数类型',
                    positiveinteger:'高为正整数类型'
                },
                context : {
                    required: '请输入备注说明'
                },
                listType : {
                    required: '请选择列表图片类型'
                }
            }
        });
	}
});