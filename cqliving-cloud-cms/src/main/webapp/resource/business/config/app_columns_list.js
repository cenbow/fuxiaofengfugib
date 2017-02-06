define(['jquery','cqliving_dialog','cqliving_ajax','chosen'], function($,cqliving_dialog,cq_ajax){
	return {
		init:loadInit
	};
	
	function loadInit(){
		$(".chosen-select").chosen({search_contains:true});
		//跳转到新增页面
		toAdd()		
		//发布
		send();
		
		/**
		 * 区县app改变，刷新页面
		 */
		$("#search_EQ_appId").on('change',function(){
			$("#app_columns_FormId").submit();
		});
	}
	
	//跳转到新增页面
	function toAdd(){
		$("#addbtn").on("click",function(){
			var appid=$('#search_EQ_appId').val();
			var url = '/module/columns/app_columns_add.html';
			if(appid){
				url += '?appId='+appid ;
			}
			window.location.href = url;
		});
	}
	
	//发布
	function send(){
		$("#sendbtn").on('click',function(){
			var appid=$('#search_EQ_appId').val();
			var url = '/module/columns/send.html';
			if(appid){
				url += '?appId='+appid ;
			}
			var jThis = $(this);
			cqliving_dialog.confirm('操作确认','确定要发布吗？',function(){
				cq_ajax.ajaxOperate(url,jThis,{},function(data,status){
					if(data.code >= 0){
						cqliving_dialog.success(data.message?data.message:"发布成功",function(){
						});
					}else{
						cqliving_dialog.error(data.message?data.message:"发布失败");
					}
				});
			});
		});
	}
});