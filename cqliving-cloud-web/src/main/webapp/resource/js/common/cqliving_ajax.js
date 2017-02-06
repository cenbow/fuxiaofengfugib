/**
 * AJAX 操作工具
 */
define(['jquery'],function($){
	//var qzAlert = require('qzAlert');
	return {
		//后缀名是否符合规范
		checkSuffix : function(config,inputId){
			//获取上传文本框的值
			var fileValue=$("#"+inputId).val();
			if (fileValue == null || fileValue == '') {
				//popup.openInfoBox("提示", "请选择上传文件！");
				return false;
			}
			// 取后缀名
			var dd = fileValue.split(".");
			var suffix = dd[dd.length - 1].toLowerCase();
	
			//判断所传的文件后缀名是否在允许的文件后缀名中
			var _exist=$.inArray(suffix,config.allowSuffix);
			//如果是允许上传的文件后缀名
			if(_exist>-1){
				return true;
			}else{
				//把允许上传的文件后缀名连接成字符串
				var permitSuffix=config.allowSuffix.join(",");
				var info = "请选择“"+permitSuffix+"”格式的文件!";
				//alert("提示", info);
				// 清空上传控件中的值
				clearFile(inputId);
				return false;
			}
		},
		/**
		 * 重写jquery   load方法
		 */
		load:function(obj,url,dada,fn){
			$("body").append("<div class=\"modal-backdrop fade in\" id=\"loading-div\"><i class=\"icon-spinner icon-spin orange bigger-125\"></i></div>");
			$(obj).load(url ? addIsAjax(url)+"&isLoad=true" : "",dada ? dada : {},function(html){
				$("#loading-div").remove();
	            if(html==QZ_NOT_LOGIN || html==QZ_LOGIN_REPEAT){//未登录
	            	$(obj).html("");
	            	    alert("您尚未登录或登录时间过长,请重新登录!",function(){
	            		window.location.href = "/login.html";
					});
	                return false;
	            } else {
	                fn ? fn(html) : null;
	            }
			});
		},
	
	
		/**
		 * 公共ajax操作方法
		 * 默认异步执行，POST提交，返回Json。如果需要不同配置，请参照jquery.ajax方法的原生参数进行配置.
		 * @param {string} url 链接地址
		 * @param {string | object} 发出此请求方法的元素id或者元素，用于控制重复提交问题
		 * @param {object} data 传输数据
		 * @param {function} callbaxk 回调函数 
		 * @param {object}  args   可传入原生$.ajax(args)的参数  
		 */
		ajaxOperate : function(url,el,data, callback,args){
			if(el){
				el = $(el);
				if(el){
					if(el.attr('submitting')){return;}
					el.attr('submitting',true);
				}
			}
			
			url = addIsAjax(url);
			
			//默认异步调用
			var options = {
					url : url,
					type :"POST",
					dataType : "json", 
					cache : false,
					data : data,
					async: true
			};
			
			if(args){
				for(var x in args){
					options[x] = args[x];
				}
			}
			options.success = function(data, status){
				if(data==QZ_NOT_LOGIN || data==QZ_LOGIN_REPEAT){//未登录
					    alert("您尚未登录或登录时间过长,请重新登录!",function(){
	            		window.location.href = "/login.html";
					});
	            } else if(callback!=null){
				    callback(data, status);
		        }
			};
			options.error = function(data, status, e){
				if(args && args.error && $.isFunction(args.error)){
					args.error(data,status,e);
				}
			};
			options.complete = function(){
				if(el){el.removeAttr('submitting');}
				if(args && args.showMask){
					//qzAlert.closeLoadingLayer();
				}
				if(args && args.complete && $.isFunction(args.complete)){
					agrs.complete();
				}
			};
			if(args && args.showMask){
				//qzAlert.openLoadingLayer();
			}
			$.ajax(options);
		},
	
		/**
		 * 验证上传文件大小 
		 * @param {int} maxsize 允许上传的最大值
		 * @param {object} fileElementId 文件选择框的id属性
		 */
		checkFileSize : function(maxsize,fileElementId){
			maxsize = maxsize*1024*1024;
	//		var errMsg = "上传的附件文件不能超过2M！！！";
	//		var tipMsg = "您的浏览器暂不支持计算上传文件的大小，确保上传文件不要超过2M，建议使用IE、FireFox、Chrome浏览器。";
			var  browserCfg = {};
			var ua = window.navigator.userAgent;
			if (/msie/i.test(navigator.userAgent) && !window.opera){
				browserCfg.ie = true;
			}else if(ua.indexOf("Firefox")>=1){
				browserCfg.firefox = true;
			}else if(ua.indexOf("Chrome")>=1){
				browserCfg.chrome = true;
			}else if(ua.indexOf("Safari")>=1){
				browserCfg.safari = true;
			}else if(ua.indexOf("Opera")>=1){
				browserCfg.opera = true;
			}
			try{
			 	var obj_file = document.getElementById(fileElementId);
			 	if(obj_file.value==""){
			 		return false;
			 	}
			 	var filesize = 0;
			 	if(browserCfg.firefox || browserCfg.chrome || browserCfg.safari || browserCfg.opera){
			 		filesize = obj_file.files[0].size;
			 	}else if(browserCfg.ie){
			 		var tempimg = document.getElementById('tempimg');
			 		if(!tempimg){
			 			$('body').append('<img id="tempimg"/>');
			 			tempimg = document.getElementById('tempimg');
			 		}
			 		tempimg.dynsrc=obj_file.value;
			 		filesize = tempimg.fileSize;
			 	}else{
			 		return true;
			 	}
			 	if(filesize==-1){
			 		return true;
			 	}else if(filesize>maxsize){
			 		return false;
				}else{
			 		return true;
				}
			}catch(e){
				return true;
			}
		},
	
		/**
		 * 异步上传文件方法
		 * @param {string} url 链接地址
		 * @param {object} fileElementId 文件选择框的id属性
		 * @param {function} callbaxk 回调函数 
		 */
		ajaxFileUpload : function(url,fileElementId,callback){
			url = addIsAjax(url);
			if(false){
				//弹出遮罩层
			}
			// 执行上传文件操作的函数
			$.ajaxFileUpload({
				// 处理文件上传操作的服务器端地址
				url : url,
				secureuri : false, // 是否启用安全提交,默认为false
				fileElementId : fileElementId, // 文件选择框的id属性
				dataType : 'json', // 服务器返回的格式,可以是json或xml等
				type:'post',
				cache : false,
				success : function beforeCallback(data, status){
					if(data==QZ_NOT_LOGIN || data==QZ_LOGIN_REPEAT){//未登录
						alert("您尚未登录或登录时间过长,请重新登录!",function(){
		            		window.location.href = "/login.html";
						});
		                return false;
		            }else if(callback!=null){
					    callback(data, status);
					    if(false){
					    	//关闭遮罩层
					    }
		            }
				},
				error : function(data, status, e) { // 服务器响应失败时的处理函数
					window.location.href = "/error/500.html";
				},
				complete : function() { 
					if(false){
				    	//关闭遮罩层
				    }
				}
			});
		},
	
		/**
		 * 使用form表单提交数据
		 * @param {string} url 链接地址
		 * @param {object} args 参数
		 */
		standardPost : function(url,args){
			var $body = $(document.body),
		        $form = $("<form method='post'></form>"),
		        $input;
			$form.attr({"action":url});
		    $.each(args,function(key,value){
		    	$input = $("<input type='hidden'>");
		    	$input.attr({"name":key});
		    	$input.val(value);
		    	$form.append($input);
		    });
		
		    $form.appendTo(document.body);
		    $form.submit();
		    document.body.removeChild($form[0]);
		}
		
		 // Private array of chars to use
		 , uuid : function (len, radix) {
		    var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split(''), uuid = [], i;
		    radix = radix || chars.length;
		 
		    if (len) {
		      // Compact form
		      for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];
		    } else {
		      // rfc4122, version 4 form
		      var r;
		 
		      // rfc4122 requires these characters
		      uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
		      uuid[14] = '4';
		 
		      // Fill in random data.  At i==19 set the high bits of clock sequence as
		      // per rfc4122, sec. 4.1.5
		      for (i = 0; i < 36; i++) {
		        if (!uuid[i]) {
		          r = 0 | Math.random()*16;
		          uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
		        }
		      }
		    }
		    return uuid.join('');
		  }
	}
	
	//清空上传控件中的值
	function clearFile(inputId){
		var file = $('#'+inputId);
		var fileClone=file.clone();
		file.after(fileClone.val(""));
		file.remove(); 
	}
	
	/**
	 * private 
	 * 在请求后增加isAjax参数
	 */
	function addIsAjax(url){
		if(url.indexOf("?") < 0 ){
			url+="?isAjax=true";
		}else{
			url+="&isAjax=true";
			url+=("&rtime="+new Date().getTime());
		}
		
		return url;
	}
	
	/**
	 * 异步上传核心方法，使用jquery继承实现
	 */
	$.extend({
	    createUploadIframe: function(id, uri){
			//create frame
            var frameId = 'jUploadFrame' + id;
            
            if(window.ActiveXObject) {
                //var io = document.createElement('<iframe id="' + frameId + '" name="' + frameId + '" />');
                try {
                    io = document.createElement('<iframe name="' + frameId + '" id="' + frameId + '"></iframe>');
                } catch (e) {
                    io = document.createElement('iframe');
                    io.name = frameId;
                    io.id = frameId;
                }
                if(typeof uri== 'boolean'){
                    io.src = 'javascript:false';
                }
                else if(typeof uri== 'string'){
                    io.src = uri;
                }
            }
            else {
                var io = document.createElement('iframe');
                io.id = frameId;
                io.name = frameId;
            }
            io.style.position = 'absolute';
            io.style.top = '-1000px';
            io.style.left = '-1000px';

            document.body.appendChild(io);

            return io;			
	    },
	    createUploadForm: function(id, fileElementId){
			//create form	
			var formId = 'jUploadForm' + id;
			var fileId = 'jUploadFile' + id;
			var form = $('<form  action="" method="POST" name="' + formId + '" id="' + formId + '" enctype="multipart/form-data"></form>');	
			var oldElement = $('#' + fileElementId);
			var newElement = $(oldElement).clone();
			$(oldElement).attr('id', fileId);
			$(oldElement).before(newElement);
			$(oldElement).appendTo(form);
			//set attributes
			$(form).css('position', 'absolute');
			$(form).css('top', '-1200px');
			$(form).css('left', '-1200px');
			$(form).appendTo('body');		
			return form;
	    },

	    ajaxFileUpload: function(s) {
	        // TODO introduce global settings, allowing the client to modify them for all requests, not only timeout		
	        s = jQuery.extend({}, jQuery.ajaxSettings, s);
	        var id = s.fileElementId;        
			var form = jQuery.createUploadForm(id, s.fileElementId);
			var io = jQuery.createUploadIframe(id, s.secureuri);
			var frameId = 'jUploadFrame' + id;
			var formId = 'jUploadForm' + id;		
	        // Watch for a new set of requests
	        if ( s.global && ! jQuery.active++ )
			{
				jQuery.event.trigger( "ajaxStart" );
			}            
	        var requestDone = false;
	        // Create the request object
	        var xml = {};   
	        if ( s.global )
	            jQuery.event.trigger("ajaxSend", [xml, s]);
	        // Wait for a response to come back
	        var uploadCallback = function(isTimeout)
			{
				var io = document.getElementById(frameId);
	            try 
				{				
					if(io.contentWindow)
					{
						 xml.responseText = io.contentWindow.document.body?io.contentWindow.document.body.innerHTML:null;
	                	 xml.responseXML = io.contentWindow.document.XMLDocument?io.contentWindow.document.XMLDocument:io.contentWindow.document;
						 
					}else if(io.contentDocument)
					{
						 xml.responseText = io.contentDocument.document.body?io.contentDocument.document.body.innerHTML:null;
	                	xml.responseXML = io.contentDocument.document.XMLDocument?io.contentDocument.document.XMLDocument:io.contentDocument.document;
					}						
	            }catch(e)
				{
					jQuery.handleError(s, xml, null, e);
				}
	            if ( xml || isTimeout == "timeout") 
				{				
	                requestDone = true;
	                var status;
	                try {
	                    status = isTimeout != "timeout" ? "success" : "error";
	                    // Make sure that the request was successful or notmodified
	                    if ( status != "error" )
						{
	                        // process the data (runs the xml through httpData regardless of callback)
	                        var data = jQuery.uploadHttpData( xml, s.dataType );    
	                        // If a local callback was specified, fire it and pass it the data
	                        if ( s.success )
	                            s.success( data, status );
	    
	                        // Fire the global callback
	                        if( s.global )
	                            jQuery.event.trigger( "ajaxSuccess", [xml, s] );
	                    } else
	                        jQuery.handleError(s, xml, status);
	                } catch(e) 
					{
	                    status = "error";
	                    jQuery.handleError(s, xml, status, e);
	                }

	                // The request was completed
	                if( s.global )
	                    jQuery.event.trigger( "ajaxComplete", [xml, s] );

	                // Handle the global AJAX counter
	                if ( s.global && ! --jQuery.active )
	                    jQuery.event.trigger( "ajaxStop" );

	                // Process result
	                if ( s.complete )
	                    s.complete(xml, status);

	                jQuery(io).unbind();

	                setTimeout(function()
										{	try 
											{
												$(io).remove();
												$(form).remove();	
												
											} catch(e) 
											{
												jQuery.handleError(s, xml, null, e);
											}									

										}, 100);

	                xml = null;

	            }
	        };
	        // Timeout checker
	        if ( s.timeout > 0 ) 
			{
	            setTimeout(function(){
	                // Check to see if the request is still happening
	                if( !requestDone ) uploadCallback( "timeout" );
	            }, s.timeout);
	        }
	        try 
			{
	           // var io = $('#' + frameId);
				var form = $('#' + formId);
				$(form).attr('action', s.url);
				$(form).attr('method', 'POST');
				$(form).attr('target', frameId);
	            if(form.encoding)
				{
	                form.encoding = 'multipart/form-data';				
	            }
	            else
				{				
	                form.enctype = 'multipart/form-data';
	            }			
	            $(form).submit();

	        } catch(e) 
			{			
	            jQuery.handleError(s, xml, null, e);
	        }
	        if(window.attachEvent){
	            document.getElementById(frameId).attachEvent('onload', uploadCallback);
	        }
	        else{
	            document.getElementById(frameId).addEventListener('load', uploadCallback, false);
	        } 		
	        return {abort: function () {}};	

	    },

	    uploadHttpData: function( r, type ) {
	        var data = !type;
	        data = type == "xml" || data ? r.responseXML : r.responseText;
	        // If the type is "script", eval it in global context
	        if ( type == "script" )
	            jQuery.globalEval( data );
	        // Get the JavaScript object, if JSON is used.
	        if ( type == "json" )
	        	data = r.responseText;   
		    	var start = data.indexOf(">");   
		    	if(start != -1) {   
		    		var end = data.indexOf("<", start + 1);   
		    		if(end != -1) {   
		    			data = data.substring(start + 1, end);   
		    		}   
		    	}   
	            eval( "data = " + data );
	        // evaluate scripts within html
	        if ( type == "html" )
	            jQuery("<div>").html(data).evalScripts();
				//alert($('param', data).each(function(){alert($(this).attr('value'));}));
	        return data;
	    },
	    handleError: function( s, xhr, status, e ) 		{
	    	// If a local callback was specified, fire it
			if ( s.error ) {
				s.error.call( s.context || s, xhr, status, e );
			}

			// Fire the global callback
			if ( s.global ) {
				(s.context ? jQuery(s.context) : jQuery.event).trigger( "ajaxError", [xhr, s, e] );
			}
	   }
	});
});


