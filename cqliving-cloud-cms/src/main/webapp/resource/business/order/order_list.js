define(['cloud.table.curd','cloud.time.input'], function(tableCurd,timeInput){
	return {
		init: function(){
			tableCurd.initTableCrud();
			timeInput.initTimeInput();
						
			/**
			 * 导出
			 */
			$('body').on("click","#export",function(){
				var url = $(this).attr("url");
				var param = $("#order_FormId").serialize();
				url += "?"+param;
				window.location.href=url;
			});
			
		}
	};
});