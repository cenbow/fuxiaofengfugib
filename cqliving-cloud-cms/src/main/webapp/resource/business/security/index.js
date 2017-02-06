define(['jquery'], function($){
	return {
		init: function(){
			$('body').on('click','.detail-tr',function(){
				var id=$(this).attr("rid");
				var url = "/module/message/letter_detail_view.html?id="+id;
				window.location.href=url;
			});
		}
	};
});
