$(function(){
	$(".close").on("click",function(){
		$(".pup").hide();
	})
	if ($(".tb-mid").height()>=$(window).height()) {
		$(".rob-index").addClass("top")
	};
})