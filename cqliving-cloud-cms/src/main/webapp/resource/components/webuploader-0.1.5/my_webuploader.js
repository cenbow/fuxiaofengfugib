define('myUploader',function(require,exports){
	
	//依赖后置
	var webupload = require('webuploader');
	var colorbox = require('common_colorbox');
	var cqliving_dialog = require('cqliving_dialog');
	var cqliving_ajax = require('cqliving_ajax');
	
	var _options = "";
	
	exports.init=function(option,uploader){
		/*url,//上传请求地址
		containerId,//选择文件的按钮ID
		thumbContainerId,//图片存放容器ID
		success,//上传成功回调
		error,//上传失败回调
		completed,//上传完成回调
		isSingle:true//true:只上传一张,false:上传多张
		
		width:控制缩略图宽度，字符串如：80px 默认150
		height:控制缩略图高度，字符串如：80px 默认150
		fileNumLimit:最多能上传多少张图，注意与isSingle：false对应
		clickView:true//点击弹层查看图片
		
		*/
		_options = option;
		
		if(option.destroy && uploader){
			if(uploader)uploader.destroy();
		}
		
		var isclickView = true;
		if(typeof option.clickView == 'boolean' && !option.clickView){
			isclickView = false;
		}
		
		uploader = webupload.create({
			 // 选完文件后，是否自动上传。
		    auto: true,
		    // swf文件路径
		    swf: '/resource/components/webuploader-0.1.5/Uploader.swf',
		    // 文件接收服务端。
		    server: option.url,
		    // 选择文件的按钮。可选。
		    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
		    pick: '#'+option.containerId,
		    // 只允许选择图片文件。
		    accept: {
		        title: 'Images',
		        extensions: option.extensions ? option.extensions : 'gif,jpg,jpeg,bmp,png',
		        mimeTypes: 'image/jpg,image/jpeg,image/png,image/gif'
		    },
		    fileNumLimit: option.fileNumLimit,
		    thumb : {
		    	   // width: '150px',
		    	    // 图片质量，只有type为`image/jpeg`的时候才有效。
		    	    //quality: 70,
		    	    // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
		    	    //allowMagnify: true,
		    	    // 是否允许裁剪。
		    	    crop: true
		    	    // 为空的话则保留原有图片格式。
		    	    // 否则强制转换成指定的类型。
		    	   // type: 'image/jpeg'
		    },
		    fileSingleSizeLimit:1024*1024*10,//单个文件限制的大小(byte) //这里限制10M
		    duplicate:true,
		    compress:false
		});
		
		// 文件上传成功，给item添加成功class, 用样式标记上传成功。
		uploader.on( 'uploadSuccess', function( file,response ) {
			if(isclickView){
				var filePath = response.data.filePath;
				if (option.fileUrlPath) {
					filePath = option.fileUrlPath + filePath;
				}
				if($('#COLORBOX_'+file.id).length > 0){
					$('#COLORBOX_'+file.id).attr('href', filePath);
				}else{
					$('#' + option.thumbContainerId + ' .ace-thumbnails>li>a:last').attr('href', filePath);
				}
			}
			if(option.success && $.isFunction(option.success))option.success(file,response);
			if(isclickView){
				colorbox.init();
			}
		});

		// 文件上传失败，显示上传出错。
		uploader.on( 'uploadError', function( file,reason ) {
			if(option.error && $.isFunction(option.error))option.error(file,reason);
		});

		// 完成上传完了，成功或者失败，先删除进度条。
		uploader.on( 'uploadComplete', function( file ) {
			if(option.completed && $.isFunction(option.completed))option.completed(file);
		});
		
		uploader.on( 'beforeFileQueued', function( file ) {
			var fileExt = file.ext;
			var fileSize = file.size;
			if(fileExt && option.extensions &&  fileExt.toUpperCase() != option.extensions.toUpperCase()){
				cqliving_dialog.alert('图片只支持'+ option.extensions);
			}
			if(fileExt && fileExt.toUpperCase() != 'GIF' && fileExt.toUpperCase() != 'JPG'
				&& fileExt.toUpperCase() != 'JPEG'&& fileExt.toUpperCase() != 'BMP'	&& fileExt.toUpperCase() != 'PNG'){
				cqliving_dialog.alert('图片只支持gif,jpg,jpeg,bmp,png');
			}else if(fileSize && fileSize>1024*1024*10){
				cqliving_dialog.alert('图片限制在10M以内');
			}
			var limit = option.fileNumLimit;
			if(limit){
				var $li = $('#'+option.thumbContainerId).find('ul').find('li');
				if($li.length >= limit){
					cqliving_dialog.error('图片数量超出限制');
					return false;
				}
			}
		});
		
		uploader.on("filesQueued",function(files){
		    //排序
			sortObject(files,"name");
			for(var i in files){
				makeThumb(files[i]);
			}
		});
		
		// 当有文件添加进来的时候
		uploader.on( 'fileQueued', function( file ) {});
		
		$("body").on("click",".icon-remove",function(){
			$(this).closest("li").remove();
			uploader.trigger('fileDequeued');//处理计数的问题
		});
		
		function makeThumb(file){
			var $imgDiv = $('#'+option.thumbContainerId);
			var ulHtml = '<ul class="ace-thumbnails"></ul>';
			var	html = '<li id="'+file.id+'" class="upload-imgs"';
				if(option.height){
					html += ' style="width:'+option.width+';height:'+option.height+';"';
				}
				html += '>';
				if(option.sort){
                    html += '<input type="hidden" name="ar_name" value=""><input type="text" name="imginput" style="position:relative" placeholder="请输入图片排序号"/>';					
				}
				html += '<div class="topInput">';
				if(isclickView){
					html += '<a href="javascript:;" data-rel="colorbox" id="COLORBOX_'+file.id+'" >';
				}
				if(option.height){
					html += '<img alt="'+option.width+'x'+option.height+'" src="" style="width:'+option.width+';height:'+option.height+';">';
				}else{
					html += '<img alt="150x150" src="" style="height:150px;">';
				}
				if(isclickView){
					html += '</a>';
				}
				if(option.text){
					html += '<textarea style="width:150px;" placeholder="请输入描述" name="description"></textarea>';
				}
				html += '	<div class="tools tools-top">';
				html += '		<a href="javascript:;"><i class="icon-remove red"></i></a>';
				if(option.copy){
					html += '       <a href="javascript:;"><i class="icon-copy red"></i></a>';
				}
				if(option.upload){
					var fileId = file.id+cqliving_ajax.uuid(10,32);
					html += ' <a href="javascript:;"><input type="file" id="'+fileId+'" name="'+fileId+'"/><label>文件上传</label></a>';
				}
				html += '	</div>';
				html += '	</div></li>';
			var $ul = $imgDiv.find("ul");
			if($ul.length<1){
				$imgDiv.append(ulHtml);
			}
			$ul = $imgDiv.find("ul");
			var $li = $ul.find("li");
			if(option.isSingle){
				if($li.length<1)
					$ul.append(html);
        	}else{
        		$ul.append(html);
        	}
			//排序号
			if(option.sort){
				var sortNo = exports.getSortNo($ul)+1;
			    $ul.find("li input[name=imginput]").last().val(sortNo);
			}
		    // 创建缩略图
		    // 如果为非图片文件，可以不用调用此方法。
			uploader.makeThumb( file, function( error, src ) {
				var $img = $ul.find("li[id="+file.id+"] img").last();
				if(option.isSingle){
					$img = $ul.find("li img").last();
				}
		        if ( error ) {
		            $img.replaceWith('<span>不能预览</span>');
		            return;
		        }
		        //这里不能设置预览，防止覆盖图片上传后真正的图片路径
		        //$img.attr( 'src', src );
		        $img.parent().find('.icon-remove').unbind('click').bind("click",function(){
		        	var $rLi = $(this);
		        	if($.isFunction(option.removeCallback)) option.removeCallback(file,$rLi);
		        	$rLi.parents("li").remove();
		        });
		    }, 150, 150 );
		}
		
		return uploader;
	}
	
		$("body").on("click",".icon-copy",function(){
		    var $ul = $(this).closest("ul");
	    	var $UlLi  = $(this).closest("li");
	    	var $textarea = $UlLi.find("textarea");
	    	$copyLi = $UlLi.clone(true);
	    	$copyLi.find("textarea").val($textarea.val());
	    	var _uuid = cqliving_ajax.uuid(10,32);
	    	if(_options.upload && $copyLi.find(":input[type=file]").length<=0){
				var html = ' <a href="javascript:;"><input type="file" id="'+_uuid+'" name="'+_uuid+'"/><label>文件上传</label></a>';
				$copyLi.find(".tools-top").append(html);
			}else{
				$copyLi.find(":input[type=file]").attr("id",_uuid);
				$copyLi.find(":input[type=file]").attr("name",_uuid);
			}
	    	$copyLi.attr("id",_uuid+"_li");
	    	$copyLi.find(":input[name=id]").val("");
	    	$copyLi.find(":input[name=imginput]").val(exports.getSortNo($ul)+1);
	    	$ul.append($copyLi);
	  });
		
		$("body").on("change",".ace-thumbnails li .tools-top :input[type=file]",function(){
			var $this = $(this);
			var $img = $this.closest("li").find("img");
			var $name = $this.closest("li").find(":input[name=name]");
			var $fileUrl = $this.closest("li").find(":input[name=fileUrl]");
			var id = $this.attr("id");
			var url = "/common/upload.html";
			cqliving_ajax.ajaxFileUpload(url,id,function(data,status){
				var imgPath = imgUrl+data.data.filePath;
				$img.attr("src",imgPath);
				$fileUrl.val(imgPath);
				$name.val(data.data.fileRealName);
			});
		});
		
		//通过图片size和原图路径获取裁剪后的图片
		exports.appendSuffix = function(size,imgpath){
			if(!imgpath){
				return imgpath;
			}
			var pre = imgpath.lastIndexOf(".");
			var prePath = imgpath.substring(0,pre);
			prePath = prePath+"_"+size+imgpath.substring(pre);
			return prePath;
		}
		
		//获取给定图片Ul排序的最大的值
		exports.getSortNo = function ($ul){
			var sort = 0;
			$ul.find("li").each(function(i,n){
				var $li = $(n);
                var sortNo = $li.find(":input[name=imginput]").val();			
				if(sortNo && parseInt(sortNo,10)>=sort){
					sort = parseInt(sortNo,10);
				}
			});
           return parseInt(sort,10);			
		}
		
		 //排序对象，小的在前面，大的在后面
		 function sortObject(arrays,sortProperty){ 
			 function compareTo(ar1,ar2){
				 return ar1[sortProperty]>ar2[sortProperty];
			 }
			 arrays.sort(compareTo);
		 }
});