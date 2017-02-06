define(['webuploader','cqliving_ajax'],function(WebUploader,cq_ajax){
	
	var _uploader = "";
	var _option = "";
	
	 return function(option){
		
		 _option = option;
		//pickerId,save_btn,success,error
		 _uploader = new WebUploader.Uploader({
			// swf文件路径
		    swf:'/resource/components/webuploader-0.1.5/Uploader.swf',
			//swf: 'http://cdn.staticfile.org/webuploader/0.1.0/Uploader.swf',
			// 文件接收服务端。
		    //server: '/upload.html',
		    server: '/common/chunkedUpload.html',
		    auto:true,
		    // 选择文件的按钮。可选。
		    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
		    pick: "#"+option.pickerId,
		    chunked : true,
		    chunkSize : 10*1024*1024,//[可选] [默认值：5242880] 如果要分片，分多大一片？ 默认大小为5M
		    threads : 1,
		    formData : {guid:WebUploader.Base.guid()},
		    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
		    resize: false,
		    accept:{
		    	title: 'audio/video',
		        extensions: option.extensions ? option.extensions : 'mp3,mp4,rmvb,mkv,wmv',
		        mimeTypes: 'application/octet-stream,audio/*,video/*'
		    }
		});
		
		$("#"+option.save_btn).on("click",function(){
			_uploader.upload();
		});
		
		
        _uploader.on( 'beforeFileQueued', function( file ) {
			if($.isFunction(_option.beforeFileQueued))_option.beforeFileQueued(file);
		});
		
		// 当有文件被添加进队列的时候
		_uploader.on( 'fileQueued', function( file ) {
			$("#"+option.echoId).append( '<div id="' + file.id + '" class="item">' +
		        '<h4 class="info">' + file.name + '</h4>' +
		        '<p class="state">等待上传...</p>' +
		    '</div>' );
		});

		// 文件上传过程中创建进度条实时显示。
		_uploader.on( 'uploadProgress', function( file, percentage ) {
			
		    var $li = $( '#'+file.id ),
		        $percent = $li.find('.progress .progress-bar'),
		        $btn = $li.find("a");

		    // 避免重复创建
		    if ($percent.length<=0 ) {
		        $percent = $('<div class="progress progress-striped active">' +
		          '<div class="progress-bar" role="progressbar">' +
		          '</div>' +
		        '</div>').appendTo( $li ).find('.progress-bar');
		    }
            
		    if($btn.length<=0){
		    	$li.find("h4").append("<a href=\"javascript:;\" class=\"btn btn-sm btn-primary\">取消上传<a>");
		    }
		    
		    $li.find("a").unbind("click").bind("click",function(){
		    	_uploader.cancelFile(file);
		    	_uploader.removeFile(file,true);
		    	$li.remove();
		    	
		    });
		    
		    $li.find('p.state').text('上传中');

		    $percent.css( 'width', percentage * 100 + '%' );
		});

		
		_uploader.on( 'uploadSuccess', function( file ,response) {
		    $( '#'+file.id ).find('p.state').text('已上传');
		    $( '#'+file.id ).find("a").remove();
		   // $.isFunction(option.success) ? option.success(file,response) : ""; 
		    
		});

		_uploader.on( 'uploadError', function( file,reason ) {
		    $( '#'+file.id ).find('p.state').text('上传出错');
		    
		   // $.isFunction(option.error) ? option.error(file,reason) : ""; 
		});

		_uploader.on( 'uploadComplete', function( file ) {
		    $( '#'+file.id ).find('.progress').fadeOut();
		});
		
		
		/**
	     * method:before-send-file
	     * 在文件开始发送前做些异步操作，WebUploader会等待此异步操作完成后，开始发送文件。
	     * @author yuwu
	     * @param file File对象
	     */
		WebUploader.Uploader.register({
		    'before-send-file': 'preupload'
		}, {
			preupload : function(file) {
		        $('#uploadBtn').attr("disabled",true);
		       	var owner = this.owner;
		       	var deferred = WebUploader.Deferred(); 
		       	var blob = file.source.getSource();
		        //var fileMd5 = file.wholeMd5;
		        owner.md5File( file )           
		        .progress(function(percentage) {   // 及时显示进度             
		            var percent = parseInt(percentage * 100 ) + '%';
		            
		            var $li = $( '#'+file.id ),
			        $percent = $li.find('.progress .progress-bar');
				    // 避免重复创建
				    if ($percent.length<=0 ) {
				        $percent = $('<div class="progress progress-striped active">' +
				          '<div class="progress-bar" role="progressbar"></div></div>').appendTo( $li ).find('.progress-bar');
				    }
				    $li.find('p.state').text('文件扫描中：');
				    
				    $percent.css( 'width', percent );
			        console.log('文件扫描中:', percent);
		        })      
		        .then(function(fileMd5) {   // 完成 
		            file.wholeMd5 = fileMd5;
		            var url = "/common/checkFileMD5.html";
		            var paramData = {
	                    checkType : 'file',
	                	fileMD5Value : fileMd5,
	                    fileName : file.name
	                };
		            
		            cq_ajax.ajaxOperate(url,"body",paramData,function(data,status){
		            	if (data.code >= 0 && data.data.skip == "true") {//文件存在
		            		console.log("infoFileId=" + data.data.infoFileId);
		            		alert("文件已存在");
		            		$( '#'+file.id ).remove();
		            		owner.skipFile(file);
	                    }else{//文件不存在
	                        file.wholeMd5 = fileMd5;
	                        //每次分片都会带上此文件的MD5值
	                        _uploader.options.formData.fileMD5Value = fileMd5;
	                        file.chunkMd5s = data.data.chunkMd5s;  //如果后台已经有该文件的分片记录
	                    }
		            	//介绍此promise, webuploader接着往下走。
	                    deferred.resolve(true);
					});
		        });
		        return deferred.promise();
		    }
		});
		
		/**
	     * method:before-send
	     * 分片文件发送前检查
	     * @author yuwu
	     * @param file File对象
	     */
		WebUploader.Uploader.register({  
	        'before-send' : 'checkchunk'
	    }, {
	    	checkchunk : function(block) {
	            var me = this; 
	            var owner = this.owner;
	            var deferred = $.Deferred();
	            var chunkFile = block.blob;
	            var file = block.file;
	            var chunk = block.chunk;
	            var chunks = block.chunks;
	            var start = block.start;
	            var end = block.end;
	            var total = block.total;

	            file.chunks = chunks;           
	            if(chunks>1){ //文件大于chunksize分片上传
	            	owner.md5File(chunkFile)            
	                .progress(function(percentage) {
	                    //分片MD5计算可以不知道计算进度
	                })  
	                .then(function(chunkMd5) {
	                	owner.options.formData.chunkMd5 = chunkMd5
	                	block.chunkMd5 = chunkMd5;
	                    var chunkMd5s = file.chunkMd5s;
	                    var exists = false;
	                    if (typeof(chunkMd5s) == "undefined") { 
	                        exists = false;
	                    }  else{
	                        exists = chunkMd5s[chunk]?true:false;                   
	                    	//exists = chunkMd5 == chunkMd5s[chunk]?true:false;                   
	                    }
	                    if (exists) {
	                        deferred.reject();
	                    } else {                        
	                        deferred.resolve();
	                    }  
	                });
	            }else{//未分片文件上传
	                block.chunkMd5 = file.wholeMd5;
	                owner.options.formData.chunkMd5 = file.wholeMd5;
	                deferred.resolve();
	            }           
	            return deferred.promise();
	        }
	    });
		
		/**
	     * method:after-send-file
	     * 在所有分片都上传完毕后，且没有错误后request，用来做分片验证，此时如果promise被reject，当前文件上传会触发错误。
	     * @author yuwu
	     * @param file File对象
	     */
		WebUploader.Uploader.register({
	        'after-send-file' : 'chunkUploadFinish'
	    }, {
	        chunkUploadFinish : function(file) {
	            //var me = this; 
	            //var owner = this.owner;
	            var deferred = $.Deferred();
	            var fileMd5 = file.wholeMd5;            
	            var chunks = file.chunks;
	            if(chunks > 1){//向server发送文件合并请求，根据结果决定文件上传成功与否
	                $('#' + file.id).find('span.state').text("合并文件...");
	                file.wholeMd5 = fileMd5;
		            var url = "/common/fileMerge.html";
		            var paramData = {
	                	fileMD5Value : fileMd5,
	                    fileName : file.name,
	                    ext:file.ext  //文件扩展名称
	                };
		            cq_ajax.ajaxOperate(url,"",paramData,function(data,status){
		            	if (data.code >= 0) {
		            		console.log("infoFileId=" + data.data.infoFileId);
		            		$('#' + file.id).find('span.state').text("合并文件成功");
		            		
		            		$.isFunction(_option.success) ? _option.success(data,status) : "";
		            		
	                        deferred.resolve();
	                    }else{//文件不存在
	                    	$('#' + file.id).find('span.state').text("合并文件失败");
	                    	$.isFunction(_option.error) ? _option.error(data,status) : "";
	                    	
	                        deferred.reject();
	                    }
					});
	            }else{
	                deferred.resolve();
	            }           
	            return deferred.promise();
	        }
	    });
		
		return _uploader;
	}
	
});