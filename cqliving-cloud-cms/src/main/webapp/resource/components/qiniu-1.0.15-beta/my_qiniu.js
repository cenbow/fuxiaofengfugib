;define(['moxie','plupload','qiniu','uiJs'],function(moxie,plupload,qiniu,uiJs){
	
	return function(option,qiniuUploader){
		
		//引入Plupload 、qiniu.js后
		var qiniuOption = {
				multi_selection:false,
		        runtimes: 'html5,flash,html4',    //上传模式,依次退化
		        browse_button: option.browseBtn,       //上传选择的点选按钮，**必需**
		        uptoken_url: option.tokenUrl,            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
		        // uptoken : '', //若未指定uptoken_url,则必须指定 uptoken ,uptoken由其他程序生成
		        unique_names: true, // 默认 false，key为文件名。若开启该选项，SDK为自动生成上传成功后的key（文件名）。
		        save_key: true,   // 默认 false。若在服务端生成uptoken的上传策略中指定了 `sava_key`，则开启，SDK会忽略对key的处理
		        domain: option.domain,   //bucket 域名，下载资源时用到，**必需**
		        get_new_uptoken: false,  //设置上传文件的时候是否每次都重新获取新的token
		        container: option.container,      //上传区域DOM ID，默认是browser_button的父元素，
		        max_file_size: '500mb',           //最大文件体积限制
		        flash_swf_url: '/resource/components/plupload-2.1.9/js/Moxie.swf',  //引入flash,相对路径
		        max_retries: 2,                   //上传失败最大重试次数
		        dragdrop: true,                   //开启可拖曳上传
		        drop_element: option.dropEle,        //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
		        chunk_size: '4mb',                //分块上传时，每片的体积
		        auto_start: true,                 //选择文件后自动上传，若关闭需要自己绑定事件触发上传
		        filters:option.filters,
		        init: {
		            'FilesAdded': function(up, files) {
		                $('table').show();
		                $('#success').hide();
		                
		                if(option.isSingle){//只保留最后一个
		                	var upFilesQue = up.files;
			                for(var i=0,m=upFilesQue.length;i<m;i++){
			                	if(i!=m-1){
			                		up.removeFile(upFilesQue[i]);
			                	}
			                }
			                $("#fsUploadProgress tr").remove();
			                var filesLast = files.pop();
			                files=[];
			                files.push(filesLast);
		                }
		                
		                plupload.each(files, function(file) {
		                    var progress = new FileProgress(file, 'fsUploadProgress');
		                    progress.setStatus("等待...");
		                    progress.bindUploadCancel(up);
		                });
		            },
		            'BeforeUpload': function(up, file) {
		                var progress = new FileProgress(file, 'fsUploadProgress');
		                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
		                /*if (up.runtime === 'html5' && chunk_size) {
		                    progress.setChunkProgess(chunk_size);
		                }*/
		            },
		            'UploadProgress': function(up, file) {
		                var progress = new FileProgress(file, 'fsUploadProgress');
		                var chunk_size = plupload.parseSize(this.getOption('chunk_size'));
		                progress.setProgress(file.percent + "%", file.speed, chunk_size);
		            },
		            'UploadComplete': function() {
		                $('#success').show();
		            },
		            'FileUploaded': function(up, file, info) {
		                var progress = new FileProgress(file, 'fsUploadProgress');
		                progress.setComplete(up, info);
		                if($.isFunction(option.success))option.success(up,file,info);
		            },
		            'Error': function(up, err, errTip) {
		            	
		                $('table').show();
		                var progress = new FileProgress(err.file, 'fsUploadProgress');
		                progress.setError();
		                
		                if(err.code == plupload.FILE_EXTENSION_ERROR){
		                	var mimeArray = up.getOption("filters").mime_types;
		                	var ext = [];
		                	for(var i=0,m=mimeArray.length;i<m;i++){
		                		var mime = mimeArray[i].extensions;
		                		ext.push(mime);
		                	}
		                	errTip = "文件验证错误，只支持格式"+ext.join(",");
		                }
		                
		                progress.setStatus(errTip);
		            }
		                // ,
		                // 'Key': function(up, file) {
		                //     var key = "";
		                //     // do something with key
		                //     return key
		                // }
		        }
		    };
		
		if(option.key){
			qiniuOption.key = option.key;
			qiniuOption.save_key = false;
			qiniuOption.unique_names = false;
		}
		
		qiniuUploader = Qiniu.uploader(qiniuOption);
		
		$("body").on("click",".progressContainer .progressCancel",function(){
			
			var $this = $(this);
			$this.closest("tr").remove();
			
		});
		
		return qiniuUploader;
	    // domain 为七牛空间（bucket)对应的域名，选择某个空间后，可通过"空间设置->基本设置->域名设置"查看获取
	    // uploader 为一个plupload对象，继承了所有plupload的方法，参考http://plupload.com/docs
	}
});