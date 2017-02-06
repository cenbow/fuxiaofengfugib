define(['bootstrap','cqliving_ajax','cqliving_dialog'],function($,cqliving_ajax,cqliving_dialog){
	
	
	$("#info_correlation_modal").on("show.bs.modal",function(e){
		
		var $this = $(this);
		
		var $target = $(e.relatedTarget);
		
		var classifyId = $target.attr("data-id");
		var keywords = $target.attr("keywords");
		var appId = $target.attr("appid");
		var params = {
				infoClassifyId:classifyId,
				appId:appId,
				search_LIKE_keywords:keywords,
				search_LIKE_contentText:"",
				search_LIKE_title:""
		}
		
		var url = "/module/info/common/info_correlation_modal.html";
		
		cqliving_ajax.load($this.find(".modal-body"),url,params,function(){
			
			$("#info_correlation_modal_form :input[name=appId]").val(appId);
			$("#info_correlation_modal_form :input[name=infoClassifyId]").val(classifyId);
			
			$("#info_correlation_modal .search_btn_info_corr").unbind("click").bind("click",function(){
				var paramForm = $(this).closest("form");
				var params = paramForm.serializeArray();
				params = params ? params : [];
				//表示请求ajax分页
				var countOfCurrentPage = $(this).parents(".page-content").find(".ajax_pagination").find("select").val();
				params.push({"name" : 'p',	"value" : 'y' });
				//查询时把每页多少条数据参数带上
				params.push({"name" : 'countOfCurrentPage',	"value" : countOfCurrentPage });
				params.push({"name":"infoClassifyId","value":classifyId});
				params.push({"name":"appId","value":appId});
				cqliving_ajax.load("#info_correlation_load",paramForm.attr("action"),params,function(){});
				
			});
			
			//加入相关
			$("#info_correlation_load").on("click","table a[infoclassifyid]",function(){
				
				var refInfoClassifyId = $(this).attr("infoclassifyid");
				
				var params = {
						infoClassifyId : classifyId,
						refInfoClassifyId:refInfoClassifyId,
						appId:appId
				}
				
				var url = "/module/info/info_corretlation.html";
				
				cqliving_ajax.ajaxOperate(url,"#info_correlation_load",params,function(data,status){
					
					if(data.code>=0){
						//$target.click();
						getHadInfoCorreList(appId);
						$("#info_correlation_modal .search_btn_info_corr").click();
					}else{
						cqliving_dialog.error(data.message ? data.message : "加入相关失败");
					}
				});
			});
			
			//取消相关
			$("#info_corr").on("click","table a[infoclassifyid]",function(){
				
               var refInfoClassifyId = $(this).attr("infoclassifyid");
				
				var params = {
						infoClassifyId : classifyId,
						refInfoClassifyId:refInfoClassifyId
				}
				
				var url = "/module/info/common/info_cancel_corre.html";
				
				cqliving_ajax.ajaxOperate(url,"#info_corr",params,function(data,status){
					
					if(data.code>=0){
						//$target.click();
						getHadInfoCorreList(appId);
						$("#info_correlation_modal .search_btn_info_corr").click();
					}else{
						cqliving_dialog.error(data.message ? data.message : "取消相关失败");
					}
				});
				
			});
			
		});
	});
	
	
	function getHadInfoCorreList(appId){
		
		var url = "/module/info/common/had_correlation_modal.html";
		var param = $("#had_info_corr_form").serializeArray();
		param.push({"name":"appId","value":appId});
		cqliving_ajax.load($("#info_corr"),url,param);
		
	}
	
});