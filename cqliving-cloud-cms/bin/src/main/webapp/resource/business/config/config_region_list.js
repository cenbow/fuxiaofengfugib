define(['cloud.table.curd','cqliving_ajax','cqliving_dialog','chosen'], function(tableCurd,cqliving_ajax,cqliving_dialog){
	return {
		init:loadInit
	};
	/**
	 * 初始化加载
	 */
	function loadInit(){
		tableCurd.initTableCrud();
		//初始化APP选择列表框
		$(".chosen-select").chosen({search_contains:true});
		//修改排序号
		updateSortNo();
		if(TYPE2==type){
			//app改变事件
			$("body").on("change","#search_EQ_appId",function(){
				loadType()
			});
			//加载分类
			loadType();
		}
	};
	
	/**
	 * 修改排序号
	 */
	function updateSortNo(){
		$('body').on('change','.only_num',function(){
			var val = $(this).val();
			var jThis = $(this);
			var url = jThis.attr("url");
			var old = $(this).next("input").val();
			try{
				if(val){
					var re = /^[1-9][0-9]*$/ ;
					var result=  re.test(val);
					if(!result){
						cqliving_dialog.error("排序号只能输入只能输入大于0的整数");
						$(this).val(old);
						return;
					}
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
//		if(appId){
		var url='/module/config_region/common/load_type_select.html';
		var data = {};
		data['appId'] = appId;
		data['isList'] = true;
		cqliving_ajax.load($('#type-div'),url,data,function(){});
//		}
	}
});