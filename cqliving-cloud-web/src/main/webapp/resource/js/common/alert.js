define(['jquery'],function(){
	
	var thisObj =  {
		
		alert:function(msg){
			var html = "<div id=\"msg_model\">";
				html += "	<div id=\"model-bg\"></div>";
				html += "	<div id=\"tishi\">";
				html += "		<div class=\"model-title\">提示</div>";
				html += "		<div class=\"model-content\">"+msg+"</div>";
				html += "		<div id=\"msg_model_btn\">确定</div>";
				html += "	</div>";
				html += "</div>";
				
			var $modal = $("#msg_model");
			if($modal.length>=1){
				$modal.find(".model-content").text(msg);
			}else{
				$("body").append(html);
			}
		}
	}
	
	$("body").on("click","#msg_model_btn",function(){
		var $this = $(this);
		$this.closest("#msg_model").remove();
	});
	
	window.alert = thisObj.alert;
	
	return thisObj;
});