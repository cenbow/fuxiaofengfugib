define(['jquery','cqliving_ajax','cqliving_dialog',"chosen"],function($,cqliving_ajax,cqliving_dialog){
	
	//保存
	$("body").on("click","#join_info_modal .join_info",function(){
		
		var infoClassifyIds = [];
		$("#info_join_load tbody :input[type=checkbox]:checked").each(function(i,n){
			infoClassifyIds.push($(n).val());
		});
		
		var appId = $("#info_join_load tbody :input[type=checkbox]:checked").eq(0).attr("appid");
		
		if(infoClassifyIds.length<=0){
			cqliving_dialog.error("请选择要加入专题的新闻");
			return;
		}
		
		var infoThemeId = $("select[name=infoThemeId]").val();
		var url = "/module/info/join_info.html";
		
		var params={
				infoClassifyIds:infoClassifyIds,
				refInfoClassifyId:$(":input[name=mid]").val(),
				appId:appId,
				infoThemeId:infoThemeId
		};
		
		cqliving_ajax.ajaxOperate(url,".join_info",params,function(data,status){
			$("#join_info_modal").modal("hide");
			$("#searchButton").click();
			if(data.code<0){
				cqliving_dialog.error(data.message ? data.message : "加入专题失败");
			}
			
		});
		
	});
	
	
	//搜索
	$("body").on("click","#join_info_modal .search_btn_join_info",function(){
		
		var paramForm = $(this).closest("form");
		var params = paramForm.serializeArray();
		params = params ? params : [];
		//表示请求ajax分页
		var countOfCurrentPage = $(this).parents(".page-content").find(".ajax_pagination").find("select").val();
		params.push({"name" : 'p',	"value" : 'y' });
		//查询时把每页多少条数据参数带上
		params.push({"name" : 'countOfCurrentPage',	"value" : countOfCurrentPage });
		cqliving_ajax.load("#info_join_load",paramForm.attr("action"),params,function(){});
	});
	
	$("body").on("click","#join_info_modal thead :input[type=checkbox]",function(){
		
		var $this = $(this);
		
		if($this.is(":checked")){
			disSelectAll();
		}else{
			selectAll();
		}
		
	});
	
	function selectAll(){
		$("#info_join_load tbody :input[type=checkbox]").each(function(i,n){
			$(n).prop("checked",false);
		});
	}
	
	function disSelectAll(){
		$("#info_join_load tbody :input[type=checkbox]").each(function(i,n){
			$(n).prop("checked",true);
		});
	}
	
});