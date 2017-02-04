;define(function(require,exports,module){
	
	/**
	 * @param {string} message :提示语
	 * @param {function} fn:关闭弹层的回掉函数
	 * @param {int} closeDelay: 设定时间过后自动关闭弹窗，单位为秒
	 */
	exports.alert=function(message,fn,closeDelay){
		exports.close();
		var html = "";
		html+=" <div class=\"xp-wp-layer\"  style=\"width:300px;display:none;\">";
		html+=	"   <a href=\"javascript:void(0);\" class=\"xp-wp-close\">×</a>";
		html+=    "   <div class=\"xp-layer-cnt\">";
		html+=    "     <div class=\"find-back-layer\">";
		html+=    "        <p style=\"word-break:normal;\">";
		html+=    message;
		html+=    "    	</p>";
		html+=    "     </div>";
		html+=    "   </div>";
		html+=    " </div>";
		$("body").append(html);
		$('.xp-wp-layer').each(function(){//弹层定位
			var layerTop = $(window).scrollTop() + ($(window).height() - $(this).height() - 62)/2;
			var mgLeft = - (($(this).width()+62)/2);
			$(this).css({"top":layerTop,"margin-left":mgLeft});
		});
		$(".xp-wp-layer").show();
		$('.xp-wp-close').click(function(){
			$(this).parent().remove();
			fn ? fn() : "";
		});
		if(closeDelay && closeDelay>0){
			var _this = this
			setTimeout(function(){
				$('.xp-wp-close').parent().remove();
				fn ? fn() : "";
			},closeDelay)
		}
	};
	
	/**
	 * message :提示语
	 * fn:点击确定的回掉函数
	 */
	exports.confirm = function(message,fn){
		exports.close();
		var html ="";
		html +="<div class=\"xp-wp-layer\">";
		html +="<a href=\"javascript:;\" class=\"xp-wp-close\">×</a>";
		html +="<div class=\"xp-wp-cnt\">";
		html +="    <p>";
		html +=  message;
		html +="	</p>";
		html +="	<div class=\"xp-wp-ctr\">";
		html +="    	<a href=\"javascript:;\" class=\"btn-nor-04\">确定</a> <a href=\"javascript:;\" class=\"btn-nor-05\">取消</a>";
		html +="    </div>";
		html +=" </div>";
		html +="</div>";
		$("body").append(html);
		exports.show($('.xp-wp-layer'));
		
//		$('.xp-wp-layer').each(function(){//弹层定位
//			var layerTop = $(window).scrollTop() + ($(window).height() - $(this).height() - 62)/2;
//			var mgLeft = - (($(this).width()+62)/2);
//			$(this).css({"top":layerTop,"margin-left":mgLeft});
//		});
//		$(".xp-wp-layer").show();
		$('.xp-wp-close,.btn-nor-05').click(function(){
			$(".xp-wp-layer").remove();
		});
		$(".btn-nor-04").click(function(){
			$(".xp-wp-layer").remove();
			fn ? fn() : "";
		});
	};
	
	/**
	 * 自定义弹出框
	 * @param layerHtml String 镶嵌在弹出中的HTML代码
	 */
	exports.custom = function(layerHtml,callback){
		var html = '';
		html += ' <div class="xp-wp-layer"  style="width:500px;display:;">';
		html += ' 		<a href="javascript:;" class="xp-wp-close">×</a>';
		html += 		layerHtml;
		html += ' </div>';
		$('body').append(html);
		
		callback.call();
		
		$('.xp-wp-close').click(function(){
			$(".xp-wp-layer").remove();
		});
		return $('.xp-wp-layer');
	};
	
	/**
	 * 显示页面中的弹窗框
	 */
	exports.show = function(){
		var $xplayer = $('.xp-wp-layer');
		if($xplayer.length == 0) return;
		$xplayer.each(function(){
			var layerTop = $(window).scrollTop() + ($(window).height() - $(this).height() - 62)/2;
			var mgLeft = - (($(this).width()+62)/2);
			$(this).css({"top":layerTop,"margin-left":mgLeft});
		});
		$xplayer.show();
	};
	
	/**
	 * 关闭页面的弹出框
	 */
	exports.close = function(){
		var $xplayer = $('.xp-wp-layer');
		if($xplayer.length == 0) return;
		$xplayer.remove();
	}
	/**
	 * 参数传递是否必须：1：必须  0：不必须
	 * message :提示语（1）
	 * fn:点击确定的回掉函数（0）
	 * closeTime:延迟closeTime毫秒时间自动关闭弹层（0）
	 */
	exports.msgDialog = function(message,fn,closeTime){
		var html ="";
		html +="<div class=\"xp-wp-layer\">";
		//html +="<a href=\"javascript:;\" class=\"xp-wp-close\">×</a>";
		html +="<div class=\"xp-wp-cnt\">";
		html +="    <p>";
		html +=  message;
		html +="	</p>";
		html +="	<div class=\"xp-wp-ctr\">";
		html +="    	<a href=\"javascript:;\" class=\"btn-nor-04\">确定</a>";
		html +="    </div>";
		html +=" </div>";
		html +="</div>";
		$("body").append(html);
		exports.show($('.xp-wp-layer'));
		$(".btn-nor-04").click(function(){
			$(".xp-wp-layer").remove();
			fn ? fn() : "";
		});
		if(closeTime && closeTime>0){
			setTimeout(function(){
				exports.close();
				fn ? fn() : "";
			},closeTime);
		}
	};
	
	var layer;
	/*
	 * 打开异步调用时的加载层
	 */
	exports.openLoadingLayer = function(){
		if(!layer) layer = new popup();
		layer.openLoadingLayer();
	};
	
	/*
	 * 关闭异步调用时的加载层
	 */
	exports.closeLoadingLayer = function(){
		if(layer){
			layer.closeLoadingLayer();
		}
	}
	
	function popup(){
		//遮挡层对象ID
		var shieldingLayer = null;
		
		//当前打开的表单层和对话框的数量
		var layerNum = 0;
		
		//弹出框对象
		var msgBox = null;
		
		//加载层对象
		var loadingLayer = null;
		/*---------------------内部调用----------------------*/
		
		/*
		 * 初始化
		 */
		popup.prototype.initPopup = function() {
			if(this.shieldingLayer==null){
				this.shieldingLayer = document.createElement("DIV");
				this.shieldingLayer.id = "mbLayer";
				this.shieldingLayer.className = "mb_layer";
				this.shieldingLayer.style.display="none";
				document.body.appendChild(this.shieldingLayer);
			}
			if(this.msgBox==null){
				this.msgBox = document.createElement("DIV");
				this.msgBox.id = "msgBox";
				this.msgBox.className = "wp_layer";
				this.msgBox.style.display="none";
				this.msgBox.style.width="415px";
				this.msgBox.style.marginTop="-88px";
				this.msgBox.style.marginLeft="-157px";
				document.body.appendChild(this.msgBox);
			}
			if(this.loadingLayer==null){
				this.loadingLayer = document.createElement("DIV");
				this.loadingLayer.id = "loadingLayer";
				this.loadingLayer.className = "loading";
				this.loadingLayer.style.position = "absolute";
				this.loadingLayer.style.top = "50%";
				this.loadingLayer.style.left = "50%";
				this.loadingLayer.style.display="none";
				document.body.appendChild(this.loadingLayer);
			}
		};
		
		/*
		 * 打开遮挡层
		 */
		popup.prototype.openShieldingLayer = function (){
			layerNum++;
			this.shieldingLayer.style.zIndex = (100+(layerNum-1)*2);
			this.shieldingLayer.style.display = "";
		};
		
		/*
		 * 关闭遮挡层
		 */
		popup.prototype.closeShieldingLayer = function (){
			layerNum--;
			if(layerNum<=0){
				this.shieldingLayer.style.display = "none";
			}else{
				this.shieldingLayer.style.zIndex = (100+(layerNum-1)*2);
			}
		};
		
		/*---------------------外部调用----------------------*/
		
		
		/*
		 * 打开异步调用时的加载层
		 */
		popup.prototype.openLoadingLayer = function() {
			this.initPopup();
			this.openShieldingLayer();
			
			//this.loadingLayer.innerHTML="<i class='load_ico'></i><span class='load_txt'>加载中...</span>";
			this.loadingLayer.innerHTML="<img src='/front/dist/images/loading.gif' alt='加载中...' />";
			this.loadingLayer.style.zIndex = (101+(layerNum-1)*2);
			this.loadingLayer.style.display="";
		}
		
		/*
		 * 关闭异步调用时的加载层
		 */
		popup.prototype.closeLoadingLayer = function() {
			if(this.loadingLayer!=null){
				this.loadingLayer.style.display="none";
			}
			this.closeShieldingLayer();
		}
		
		/*
		 * 打开表单层
		 */
		popup.prototype.openFormLayer = function (formId){
			this.initPopup();
			this.openShieldingLayer();
			
			document.getElementById(formId).style.zIndex = (101+(layerNum-1)*2);
			document.getElementById(formId).style.display = "";
		};
		
		/*
		 * 关闭表单层
		 */
		popup.prototype.closeFormLayer = function (formId){
			document.getElementById(formId).style.display = "none";
			this.closeShieldingLayer();
		};
		
		/*
		 * 打开信息提示框
		 */
		popup.prototype.openInfoBox = function(title,msg,confirmScript) {
			this.initPopup();
			this.openShieldingLayer();
			
			if(title==null || title==''){
				title="信息";
			}
			this.msgBox.innerHTML=
				"<div class='lay_hd'> \
				<strong class='lay_tit'>"+title+"</strong> \
				<a href=\"javascript:popup.closeBox();\" class='lay_off' hidefocus='true'><i>&times;</i></a> \
				</div> \
				<div class='lay_bd'> \
				<div class='lay_msg'> \
				"+msg+" \
				</div> \
				</div> \
				<div class='lay_ft'> \
				<a href=\"javascript:popup.closeBox();"+confirmScript+";\" class='sbtn_01_min'><span>确定</span></a> \
				</div>";
			this.msgBox.style.zIndex = (101+(layerNum-1)*2);
			this.msgBox.style.display="";
		}
		
		/*
		 * 打开确认提示框
		 */
		popup.prototype.openConfirmBox = function(title,msg,confirmScript,cancelScript) {
			this.initPopup();
			this.openShieldingLayer();
			
			if(title==null || title==''){
				title="确认";
			}
			this.msgBox.innerHTML=
				"<div class='lay_hd'> \
				<strong class='lay_tit'>"+title+"</strong> \
				<a href=\"javascript:popup.closeBox();\" class='lay_off' hidefocus='true'><i>&times;</i></a> \
				</div> \
				<div class='lay_bd'> \
				<div class='lay_msg'> \
				"+msg+" \
				</div> \
				</div> \
				<div class='lay_ft'> \
				<a href=\"javascript:popup.closeBox();"+confirmScript+";\" class='sbtn_01_min'><span>确认</span></a> \
				<a href=\"javascript:popup.closeBox();"+cancelScript+";\" class='sbtn_04_min'><span>取消</span></a> \
				</div>";
			this.msgBox.style.zIndex = (101+(layerNum-1)*2);
			this.msgBox.style.display="";
		}
		
		/*
		 * 关闭提示框
		 */
		popup.prototype.closeBox = function() {
			if(this.msgBox!=null){
				this.msgBox.style.display="none";
			}
			this.closeShieldingLayer();
		}
	}
});