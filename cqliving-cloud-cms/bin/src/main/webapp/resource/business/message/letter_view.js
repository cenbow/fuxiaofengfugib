define(['validator.bootstrap','cqliving_ajax'], function($,cq_ajax){
	return {
		init: function(){
			if($("#readStatus-hidden").val()==0){
				//修改读取状态
				var ids = [];
				var id=$('.detail-form').attr('rid');
				ids.push(id);
				var url = "/module/message_receive/common/update_status.html";
				cq_ajax.ajaxOperate(url,null,{"ids":ids},function(data,status){});
			}
		}
	};
});
