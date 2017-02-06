define(['validator.bootstrap','cqliving_ajax','chosen'], function($,cqliving_ajax){
	return {
		init:loadInit
	};
	
	function loadInit(){
		//初始化APP选择列表框
		$(".chosen-select").chosen({search_contains:true});
		if(TYPE2 == type){
			//加载商情分类
			loadShopType();
			//app下拉框change后要修改分类
			changeApp();
		}
		//添加整数校验方法
		addJqueryValidate();		
    	//表单校验
		formValidate();
	};
	
	/**
	 * 修改app
	 */
	function changeApp(){
		$("body").on("change","#appId",function(){
			loadShopType();
		});
	}
	
	/**
	 * 加载分类
	 */
	function loadShopType(){
		var appId = $("#appId").val();
		var url='/module/config_region/common/load_type_select.html';
		var data = {};
		if(appId){
			data['appId'] = appId;
		}
		data['shopTypeId'] = shopTypeId;
		cqliving_ajax.load($('#shopTypeDiv'),url,data,function(){});
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
                    name : {
                    required: true
                },
                    shopTypeId : {
                    required: true
                },
                    sortNo : {
                    positiveInteger:true
                }
            },
            messages: {
                appId : {
                    required: '请选择所属APP'
                },
                name : {
                    required: '请输入区域名称'
                },
                shopTypeId : {
                    required: '请选择商情分类'
                }
            }
        });
	}
});