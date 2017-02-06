define(['jquery','cqliving_ajax','scroll_base'],function($,cqliving_ajax,scroll_base){
	
	$(".topic_nav ul li").bind("click tap",function(){
		var $this = $(this);
		$this.addClass("active").siblings().removeClass("active");
		var infoThemeId = $this.attr("themeid");
		var param = {
				infoThemeId:infoThemeId,
				noimg:noimg,appId:appId
		};
		$(".topic_list_append").nextAll().remove();
		/*if(!$(document).data("events")){
			$(document).data("events","scroll");
			$(document).scroll(function(){
				scrollCallBack();
			});
		}*/
		$("#more_div_btn").show();
		getInfoThemeInfo(param);
	});
	
	$(".topic_nav ul li").eq(0).click();
	
	function getInfoThemeInfo(params){
		var url = "/topic/sub.html";
		cqliving_ajax.ajaxOperate(url,".topic_nav",params,function(data,status){
			data = $.trim(data);
			
			var $list = $(".topic_list_append").parent().find(".topic_list");
			
			if($list.length<=0){
				$(".topic_list_append").after(data);
			}else{
				$(".topic_list_append").parent().find(".topic_list").last().after(data);
			}
			if(noSubInfo){
				/*$(document).unbind("scroll");
				$(document).data("events","");*/
				$("#more_div_btn").hide();
			}
			
		},{dataType:"html"});
	}
	
	$("#more_div_btn").bind("click",function(){
		var shareInput = $("#shareInput").val();
		if(shareInput){
			$("span[loadUrl]").click();
			return;
		}
		var param = getInfoThemeParam();
		getInfoThemeInfo(param);
	});
	
	/*$(document).scroll(function(){
		if(!$(document).data("events")){
			$(document).data("events","scroll");
		}
		scrollCallBack();
	});*/
	
	function scrollCallBack(){
		
        var viewBottom = scroll_base.getViewBottom();
		if(viewBottom <=700){
			var param = getInfoThemeParam();
			getInfoThemeInfo(param);
		}
	}
	
	function getInfoThemeParam(){
		
		var $list = $(".topic_list_append").parent().find(".topic_list").last();
		var infoThemeId = $(".topic_nav ul li").filter(".active").attr("themeid");
		var idValue = $list.attr("idValue");
		var sortNoValue = $list.attr("sortNoValue");
		var onlineTimeValue = $list.attr("onlineTimeValue");
		var infoClassifyId = $("#infoClassifyId").val();
		var param = {
				infoThemeId:infoThemeId,idValue:idValue,sortNoValue:sortNoValue,
				noimg:noimg,appId:appId,infoClassifyId:infoClassifyId,onlineTimeValue:onlineTimeValue
		};
		return param;
	}
	
});