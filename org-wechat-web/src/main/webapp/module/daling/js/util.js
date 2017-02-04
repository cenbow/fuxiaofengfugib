;function msgAlert(msg){
	$(".util_alert").remove();
	var html = $("#layerTemplate").html();
	$("body").append(html);
    $(".util_alert .text p").text(msg);
    $(".close_rule_btn").bind("touchend",function(){
    	$(this).closest(".util_alert").remove();
    });
};