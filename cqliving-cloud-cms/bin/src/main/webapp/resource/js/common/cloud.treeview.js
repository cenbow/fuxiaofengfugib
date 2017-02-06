define(['treeview','jquery'],function(treeview){
	return {
		treeview:function(treeId,treeData,selectCallback){
			if(!treeData)return;
			if('string' == typeof treeData){
				treeData = eval("("+treeData+")");
			}
			$('#'+treeId).treeview({
				data: treeData,
				showIcon:true,
				selectedIcon:"glyphicon glyphicon-check",
				onNodeSelected: function(event, data) {
					$.isFunction(selectCallback) ? selectCallback(data) : "";
				},
				onNodeUnselected:function(event,data){
					$(":input[name$=columnsId]").eq(0).val("");
					$(":input[name*=columnsName]").val("");
				}
			});
		}
	};
});