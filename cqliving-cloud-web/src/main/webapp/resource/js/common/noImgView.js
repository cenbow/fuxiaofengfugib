/**无图模式，点击查看图片**/
define(['jquery'],function(){
	var thisObj =  {
		init:function(el){
			var e = '.detail_pic_none';
			if(el){
				e = el;
			}
			$(e).on("click",function(){
				var me = $(this);
				var imgSrc = me.attr("imgSrc");
				me.after("<img src='"+imgSrc+"'/>");
				me.remove();
			});
			
		}
	}
	return thisObj;
});