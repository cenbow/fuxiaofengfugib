define(['chosen','ueditor','jquery','ZeroClipboard','cqliving_ajax','cqliving_dialog'],function(chosen,UE,$,zcl,cqliving_ajax,cqliving_dialog){
	
	window.ZeroClipboard = zcl;
	
	var init = {
			init:function(){
				//百度编辑器
				var ue = UE.getEditor("config_text_editor");
				ue.addListener('contentChange',function(){
					var content = ue.getContent();
					$("textarea[name=content]").val(content);
				});
				//下拉选择
				$(".chosen-select").chosen({search_contains:true});
				//appId选择
				$("select[name=appId]").bind("change",appIdSelect);
			}
	}
	
	function appIdSelect(){
		var $this = $(this);
		var appId = $this.val();
		window.location.href = "/module/config_text/config_text_add.html?appId="+appId;
	}
	
	
	return init;
});