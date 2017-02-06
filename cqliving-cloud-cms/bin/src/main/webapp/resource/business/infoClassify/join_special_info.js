define(['jquery','cqliving_ajax','cqliving_dialog'],function($,cqliving_ajax,cqliving_dialog){
	
	$("body").on("click","#info_classify_load :input[type=radio]",function(){
		
		var $this = $(this);
		
		var url = "/module/info/common/info_theme_by_infoid.html";
		
		cqliving_ajax.load(".info_theme_div",url,{infoClassifyId:$this.val()},function(html){});
		
	});
	
	//保存相关
	$("body").on("click","#info_classify_modal .save_info_corre",function(){
		
		var infoThemeIds = [];
		$(".info_theme_checkbox :input[type=radio]:checked").each(function(i,n){
			infoThemeIds.push($(n).val());
		});
		
		if(infoThemeIds.length<=0){
			cqliving_dialog.error("请选择专题分类");
			return;
		}
		var refInfoClassifyId = $("#info_classify_modal :input[name=infoClassifyId]:checked").val();
		var infoClassifyIds = [];
		var appIds = [];
		$("#table_content_page :input[name=infoid]:checked").each(function(i,n){
			var $this = $(n);
			var type = $this.attr("infoType");
			if(type && type == 2){
			}else{//专题不能加入专题
				infoClassifyIds.push($this.val());
				appIds.push($this.attr("appid"));
			}
		});
		if(infoClassifyIds.length<=0){
			cqliving_dialog.error("请选择要加入专题的新闻");
			return;
		}
		
		var url = "/module/info/join_special_info.html";
		
		var params={
				infoThemeIds:infoThemeIds,
				infoClassifyIds:infoClassifyIds,
				appIds:appIds,
				refInfoClassId:refInfoClassifyId
		};
		
		cqliving_ajax.ajaxOperate(url,".save_info_corre",params,function(data,status){
			$("#info_classify_modal").modal("hide");
			
			if(data.code<0){
				cqliving_dialog.error(data.message ? data.message : "加入专题失败");
			}else{
				cqliving_dialog.success("已经将 待审核/待发布与已发布新闻加入专题。");
			}
			
		});
		
	});
	
	
	//搜索
	$("body").on("click","#info_classify_modal .search_btn_join_special",function(){
		
		var paramForm = $(this).closest("form");
		var params = paramForm.serializeArray();
		params = params ? params : [];
		//表示请求ajax分页
		var countOfCurrentPage = $(this).parents(".page-content").find(".ajax_pagination").find("select").val();
		params.push({"name" : 'p',	"value" : 'y' });
		//查询时把每页多少条数据参数带上
		params.push({"name" : 'countOfCurrentPage',	"value" : countOfCurrentPage });
		cqliving_ajax.load("#info_classify_load",paramForm.attr("action"),params,function(){});
	});
});