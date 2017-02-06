define(['validator.bootstrap','cloud.time.input','cloud.table.curd','cqliving_ajax','cqliving_dialog','myQiNiu'], function($,timeInput,tableCurd,cqliving_ajax,cqliving_dialog,myQiniu){
	return {
		init:loadInit
	};
	
	function loadInit(){
		timeInput.initTimeInput();
		tableCurd.bindSaveButton();
		//上传到七牛云
		qiniuUpload()
    	//添加整数校验方法
    	addValidator()
    	//表单校验
		formValidate();
	}
	//上传到七牛云
	function qiniuUpload(){
		var appId = $("#appId").val();
		appId = appId?appId:'';
		cqliving_ajax.ajaxOperate("/module/info/common/qiniu_by_appid.html","#form1",{'appId':appId},function(data,status){
			if(!data.data){
				cqliving_dialog.error("没有文件资源");
				return;
			}
			var qiniuDomain = data.data.domainCustom ? data.data.domainCustom : data.data.domainTest;
			var qiniuUp =  myQiniu({
				 tokenUrl:"/common/qiniu/normal_token.html?appId="+appId,
				 domain:qiniuDomain,
				 browseBtn:"video_pickfiles",
				 isSingle:true,
				 filters: {
					 mime_types : [
					     { title : "video files", extensions : "apk,ipa" }
					 ]
				 },
				 key:function(up,file){
					 console.log(file.name);
					 return file.name;
					 
				 },
				 success:function(up,file,info){
					  var res = $.parseJSON(info);
					  var domain = qiniuUp.getOption('domain');
					    var url;
					    if (res.url) {
					        url = res.url;
					    } else {
					        url = domain + (res.key);
					        console.log(url);
					    }
					  $("#url").val(url);
				  }
			  });
		  });
	}
	
	//添加整数校验方法
	function addValidator(){
		$.validator.addMethod("positiveInteger", function(value, element){
			return this.optional(element) ||/^[1-9][0-9]*$/.test(value);
		}, "只能输入大于0的整数");
	}
	
	//表单校验
	function formValidate(){
		$("#form1").validate({
        	ignore: "",
            rules: {
            	appId : {
                    required: true
                },
            	name : {
                    required: true
                },
                    updateContext : {
                    required: true
                },
                    type : {
                    required: true
                },
                    url : {
                    required: true
                },
                    viewVersion : {
                    required: true
                },
                    minVersion : {
                    required: true,
                    number:true,
                    digits:true,
                    min:1
                },
                    publishTime : {
                    required: true
                },
                    vesionNo : {
                    required: true,
                    positiveInteger:true
                }
            },
            messages: {
            	appId : {
                    required: '请选择App'
                },
            	name : {
                    required: '请输入版本名称'
                },
                updateContext : {
                    required: '请输入版本升级说明'
                },
                type : {
                    required: '请选择客户端类型'
                },
                url : {
                    required: '请上传客户端'
                },
                viewVersion : {
                    required: '请输入显示版本号'
                },
                minVersion : {
                    required: '请输入最低支持版本号',
                    number:'最低版本号是数字类型',
                    digits:'最低版本号是正整数',
                    min:'最低版本号是1'
                },
                publishTime : {
                    required: '请选择发布时间'
                },
                vesionNo : {
                    required: '请填写版本号'
                },
            }
        });
	}
});