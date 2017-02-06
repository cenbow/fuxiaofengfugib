define(['jquery','cqliving_ajax','cqalert','/resource/js/business/act/vote/vote_detail.js'],function($,cqliving_ajax,cqalert,vote_detail){
	
	$(".topic_nav ul li").bind("click",function(){
		
		var $this = $(this);
		$this.addClass("active").siblings().removeClass("active");
		var voteClassifyId = $this.find("a").attr("voteClassifyId");
		loadVoteItem(voteClassifyId);
		
	});
	
	$(".topic_nav ul li").eq(0).click();
	
	function loadVoteItem(voteClassifyId){
		
		var url = "/act/vote_classify/"+voteClassifyId+".html";
		
		cqliving_ajax.ajaxOperate(url,".topic_nav",{},function(data,status){
			
			$("#topic").nextAll().remove();
			$("#topic").after(data);
			
			vote_detail.getVotePercent();
			vote_detail.initBind();
		},{dataType:"html"});
	}
	
});