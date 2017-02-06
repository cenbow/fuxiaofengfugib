define(["colorbox"],function(colorbox){
	return {
		init: function(){
			//图片查看
			var colorbox_params = {
				reposition:true,
				scalePhotos:true,
				scrolling:false,
				previous:'<i class="icon-arrow-left"></i>',
				next:'<i class="icon-arrow-right"></i>',
				close:'&times;',
				current:'{current} / {total}',
				maxWidth:'100%',
				maxHeight:'100%',
				xhrError: '此内容无法加载',
				imgError: '加载失败',
				onOpen:function(){
					document.body.style.overflow = 'hidden';
				},
				onClosed:function(){
					document.body.style.overflow = 'auto';
				},
				onComplete:function(){
					$.colorbox.resize();
				}
			};
			$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
			$("#cboxLoadingGraphic").append("<i class='icon-spinner orange'></i>");
		}
	}
});