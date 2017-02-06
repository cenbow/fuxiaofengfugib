define(['validator.bootstrap','cloud.table.curd','cloud.time.input','ZeroClipboard','cqliving_ajax','cqliving_dialog',
        'myUploader','moment','myQiNiu','Jcrop','bigautocomplete','common_treeview','ace_ele',
         'inputlimiter','chosen','ueditor','wenzi','bootstrap-colorpicker'], 
         function($,tableCurd,timeInput,zcl,cqliving_ajax,cqliving_dialog,myUploader,moment,myQiNiu,Jcrop,bigautocomplete,common_treeview){
	
	timeInput.initTimeInput();
	tableCurd.bindSaveButton();
	window.ZeroClipboard = zcl;
	
	var listViewUploader = "",contentUploader="";
	var appColumnsId = $(":input[name=columnsId]").val();
	var infoClassify_listViewType = $(":input[name=infoClassify_listViewType]").val();
	var info_templete_image_config = getListImageConfig({
			appColumnsId:appColumnsId,
			hisColumnsId:$(":input[name=hisColumnsId]").val()
			},infoClassify_listViewType);
	//新闻资源的临时数组对象,用于放多图,视频,文本之类的资源
	var tempAppResource = [];
	/** 图片上传  */
	//列表图片
	var uploaderOptions = {
			url:"/common/upload.html?imgsize=30",
			containerId:"img_upload",
			thumbContainerId:"img_thum",
			success:function(file,response){
				var imgPath = imgUrl+response.data.filePath;
				//将图片的引用修改为上传后的图片路径
				var $liImg = $("#img_thum ul li[id="+file.id+"] img").last();
				if($liImg.length<=0)$liImg = $("#img_thum ul li img").last();
				$liImg.attr("src",imgPath);
				$("#form1").attr("canSumbit","yes");
			},
			error:function(file,reason){
				cqliving_dialog.error(reason);
			},
			isSingle:true,fileUrlPath:imgUrl,destroy:true,fileNumLimit:3
		};
	
	//新闻内容多图上传
	var infoContentOptions = {
			url:"/common/upload.html?imgsize=50",
			containerId:"info_content_img_upload",
			thumbContainerId:"app_resource",
			success:function(file,response){
				var imgPath = imgUrl+response.data.filePath;
				//将图片的引用修改为上传后的图片路径
				var $li = $("#app_resource ul li[id="+file.id+"]");
				$li.find("img").last().attr("src",imgPath);
				$li.find("input[name=ar_name]").val(file.name);
			},
			error:function(file,reason){
				cqliving_dialog.error(reason);
			},
			isSingle:false,destroy:true,text:true,sort:true,
			copy:true,upload:true,fileUrlPath:imgUrl
		};
	
	var qiniuUploader = "";
	//,flv,avi,wmv,mpeg,mpg,mov,rmvb,mkv
	var qiniuOption = {
			  browseBtn:"pickfiles",
			  tokenUrl:"/common/qiniu/normal_token.html?type=mp3&appId=1",
			  domain:"",
			  container:"container",
			  dropEle:"container",
			  type:1,
			  isSingle:true,
			  filters: {
				  mime_types : [
				                { title : "audio files", extensions : "mp3" },
				                { title : "video files", extensions : "mp4" }
				              ]
			  },
			  success:function(up,file,info){
				  var res = $.parseJSON(info);
				  var domain = up.getOption('domain');
				    var url;
				    if (res.url) {
				        url = res.url;
				    } else {
				        url = domain + encodeURI(res.key);
				    }
				    $("#content_text_upload").next().find("a").attr("href",url);
				    $(":input[name=contentUrl]").val(url);
				    $(":input[name=va_url]").val("");
			        var $li = $("#app_resource li").last();
					var id = $li.attr("arid");
					var infoFileId = $li.find("input[name=infoFileId]").val();
					var type = $li.find("input[name=ar_type]").val();
				    var appResource = {
							id:id,sortNo:1,infoFileId:"",type:qiniuOption.type,
							fileUrl:url,description:file.name,name:file.name,status:3
					}
				    clearAppResource();
				    setAppResource(appResource);
				    $(":input[name=qiniuPersistentId]").val(res.persistentId);
				    $(":input[name=qiniuHash]").val(res.hash);
				    $(":input[name=qiniuKey]").val(res.key);
				    $(":input[name=qiniuDomain]").val(domain);
				    $(":input[name=originalName]").val(file.name);
				    $(":input[name=videoStatus]").val(3);
			  }
		  };
	
	function findQiniuDomainByAppId(appId){
		var domain = "";
		cqliving_ajax.ajaxOperate("common/qiniu_by_appid.html","",{appId:appId},function(data,status){
			 if(!data.data){
				 cqliving_dialog.error("没有文件资源");
				 return domain;
			 }
			 var qiniuDomain = data.data.domainCustom ? data.data.domainCustom : data.data.domainTest;
			 domain = qiniuOption.domain = qiniuDomain;
			 if(qiniuUploader)qiniuUploader.setOption("domain",domain);
			 return domain;
	   },{async:false});
		return domain;
	}
	
	//初始化百度编辑器
	function initUeditor(id){
		//百度编辑器
		var ue = UE.getEditor(id);
		return ue;
	}
	
    function setInfoListViewImg(){
		
    	var imgs = [];
		$("#img_thum ul li").each(function(i,n){
			var src = $(n).find("img").attr("src");
			if(src)imgs.push(src);
		});
		var infoClassifyListId = $(":input[name=infoClassifyListId]").val();
		//如果是复制新闻，则以前的列表图片ID不能用
		var news_copy = $(":input[name=news_copy]").val();
		if(news_copy && news_copy== 'news_copy'){
			infoClassifyListId = "";
		}
		var infoClassifyListJson={
				id:infoClassifyListId ? infoClassifyListId : "",
				imgTitle:"",
				imageUrl:imgs.join(","),
				sortNo:1
		}
		if(imgs && imgs.length>=1){
			$(":input[name=infoClassifyList]").val(JSON.stringify(infoClassifyListJson));
		}else{
			$(":input[name=infoClassifyList]").val("");
		}
	}
   
	//ace tree  树形结构
	if(appColumns.data){
		common_treeview.treeview("appcolumns_tree",appColumns.data,treeSelect);
	}
   
	function treeSelect(appColumnsDto){
		var commentType = appColumnsDto.commentType;
		var commentValidateType = appColumnsDto.validateType;
		$("#commentType"+commentType+",#commentValidateType"+commentValidateType+"").prop("checked",true);
		//历史图片规格
		var hisColumnsId = $(":input[name=hisColumnsId]").val();
		$(":input[name=columnsId]").val(appColumnsDto.id);
		$(":input[name=columnsName]").val(appColumnsDto.name);
		var listViewType = $(":input[name=listViewType]:checked").val();
		listViewType = parseInt(listViewType,10);
		//只有列表展示类型为有图的时候才查找图片规格
		info_templete_image_config = getListImageConfig({appColumnsId:appColumnsDto.id,hisColumnsId:hisColumnsId},listViewType);
		//特殊处理,treeview在执行该方法后会将树根显示出来
		setTimeout(function(){
			$("#form1 .dropdown-menu").hide();
		},1);
	}
	
	//列表展示类型
	$("#form1").on("click",":input[name=listViewType] + span",function(){
		var viewType = $(this).siblings().val();
		
		if(infoClassify_listViewType != viewType){
			$("#img_thum .ace-thumbnails li").remove();
		}
		if(0 == viewType){//无图
			$("#img_upload,#img_thum").closest(".form-group").hide();
		}else if(1 == viewType || 2 == viewType|| 4 == viewType || 5 == viewType || 6 == viewType){//单图 大图 轮播
			$("#img_upload,#img_thum").closest(".form-group").show();
			findImageConfigByListType(info_templete_image_config,viewType);
			uploaderOptions.isSingle = true;
		    listViewUploader = myUploader.init(uploaderOptions,listViewUploader);
		}else{//多图之类的
			$("#img_upload,#img_thum").closest(".form-group").show();
			uploaderOptions.isSingle=false;
			findImageConfigByListType(info_templete_image_config,viewType);
			listViewUploader = myUploader.init(uploaderOptions,listViewUploader);
		}
	});
	
	$("#form1 :input[name=listViewType]:checked").next().click();
	
	function getListImageConfig(params,listViewType){
		
		var imgConfig = "";
		//根据栏目获取图片规格
		var url = "common/info_templete_image_config.html";
		if(params){
			var _appId = $("select[name=appId]").val();
			if(!_appId)_appId = appId;
			params.appId = _appId;
		}
		if(!params.appColumnsId){
			return;
		}
		cqliving_ajax.ajaxOperate(url,"",params,function(data,status){
			if(data.code >= 0){
				info_templete_image_config = imgConfig = data.data;
				getImageConfigHtml(imgConfig,listViewType);
				$(".alert_appcolumns").text("");
				$("#form1").attr("canSumbit","yes");
				$("#form1 :input[name=listViewType]:checked").next().click();
			}else{
				cqliving_dialog.error(data.message ? data.message:"根据APPID查找列表图片规格失败");
			}
		},{async:false});
		return imgConfig;
	}
	
	//选择栏目获取对应列表图片显示规格
	function getImageConfigHtml(imgConfig,listViewType){
		
		var html = '<label class="radio-2">';
		    html +='<input type="radio" class="ace" name="listViewType" value="0">';
		    html += '<span class="lbl">无图</span></label>';
		if(!imgConfig || imgConfig.length<=0){
			html = html.replace('"0"','"0" checked');
			$(".listViewType_form_radio").html(html);
			return html;	
		}
		if(listViewType && 0 == parseInt(listViewType,10)){
			html = html.replace('"0"','"0" checked');
		}
		//1:单图,2:大图,3:多图,4:轮播
		for(var i=0,m=imgConfig.length;i<m;i++){
			var listType = parseInt(imgConfig[i].listType,10) +1;
			html += '<label class="radio-2">';
            html += '<input type="radio" class="ace" name="listViewType" value="'+listType+'" ';
            if((!listViewType && 1==listType) || listType == parseInt(listViewType,10)){
                html += 'checked';            	
            }
            html += '>';
            html +='<span class="lbl"> ';
            if(listType == 1){
            	html += '单图';
            }else if (listType == 2){
            	html += '大图';
            }else if (listType == 3){
            	html += '多图';
            }else if (listType == 4){
            	html += '轮播';
            }else if(5 == listType){
            	html += '窄图(专题用)';
            }else if(6 == listType){
            	html += '窄图带标题(专题用)';
            }
            html += '</span></label>';
		}
		$(".listViewType_form_radio").html(html);
		return html;
	}
	
	//查找图片规格并回显图片规格
	function findImageConfigByListType(imageConfigs,listType){
		var  imageConfig = "";
		listType = parseInt(listType,10) - 1;
		//获取规格及压缩方式
		if(!imageConfigs){
			//cqliving_dialog.error("未找到图片规格，请确保该图片规格已配置");
			//getImageConfigHtml();
			return imageConfig;
		}
		for(var i=0,m=imageConfigs.length;i<m;i++){
			imageConfig = imageConfigs[i];
			if(listType == imageConfig.listType){
				break;
			}
		}
		if(!imageConfig){
			cqliving_dialog.error("未找到图片规格，请确保该图片规格已配置");
			return imageConfig;
		}
		var text = "图片规格:";
		var limit_type = imageConfig.limitType;
		var news_copy=$(":input[name=news_copy]").val();
		if(limit_type == 0){
			text += (imageConfig.width+ "x"+imageConfig.hight + "或者按比例提供。");
			uploaderOptions.url = "/common/upload.html?imgsize=30&thumb=h_300,"+imageConfig.width;
		}else{
			text += (imageConfig.width + "x"+imageConfig.hight + " 固定尺寸。");
			uploaderOptions.url = "/common/upload.html?imgsize=30&thumb=h_300,"+imageConfig.width+"x"+imageConfig.hight;
		}
		//TODO 如果是复制新闻切规格不一样，则不压缩原图
		if(news_copy && $("#form1").attr("canSumbit")=="no"){//特殊处理(因为目前只有图片规格不一样的时候才会用这个属性)
			uploaderOptions.url = "/common/upload.html?nosize=nosize";
			uploaderOptions.clickView = false;
		}
		if(3 == listType+1){
			text += "<br>最多上传三张图片。";
		}
		if(4 == listType+1){
			text += "<br>轮播图一个栏目前端最多显示10条，在新闻列表设置排序后方显示。";
		}
		$("#img_upload").siblings().html(text);
		$(":input[name=listViewType]").each(function(){
			var thisType = $(this).val();
			if(0!=thisType && thisType == listType+1)$(this).prop("checked",true);
		});
		checkImgSize(imageConfig);
		return imageConfig;
	}
	
	//检查图片尺寸是否一致
	function checkImgSize(nowimageConfig){
		$(".alert_appcolumns").text("");
		$("#form1").attr("canSumbit","yes");
		 var flag = true;
		 if(!$(":input[name=news_copy]").val() || !nowimageConfig){
			 return flag;
		 }
		 var hisWidth = $(":input[name=listImgWidth]").val();
		 var hisHeight = $(":input[name=listImgHeight]").val();
		 if(!hisWidth || !hisHeight){
			 return flag;
		 }
		 hisWidth = parseInt(hisWidth,10);
		 hisHeight = parseInt(hisHeight,10);
		var nowImgWidth = parseInt(nowimageConfig.width,10);
		var nowImgHeight = parseInt(nowimageConfig.hight,10);
		 if(hisWidth != nowImgWidth || nowImgHeight != hisHeight){
			 $("#form1").attr("canSumbit","no");
			 $(".alert_appcolumns").text("注意：选择项目的列表图比例同原栏目不同，需裁剪图片或者重新上传");
			 return flag = false;
		 }
		return flag;
	}
	
	//内容审核
	$(":input[name=validateType]").bind("click",function(){
		
		var val = $(this).val();
		if(val == 1){
			$(".push_save").removeClass("hidden");
		}else{
			$(".push_save").addClass("hidden");
		}
		
	});
	
	
	$("input[name=addType]").next().bind("click",function(){
		var addType = $(this).siblings().val();
		
		if(addType == 1){//逐步增加
			$("input[name=topTime]").parents(".form-group").removeClass("hidden");
		}else{//一次添加
			$("input[name=topTime]").parents(".form-group").addClass("hidden");
			$("input[name=topTime]").val(0);
		}
		
	});
	
	//根据appId查询资讯标签,查询七牛的domain
	$("select[name=appId]").bind("change",function(){
		
		var thisVal = $(this).val();
		thisVal = parseInt(thisVal,10);
		var options = {
				url : "common/info_label_byappid.html",
				appId:thisVal
		};
		if(1== thisVal){
			$("input[name=isViewWechat]").closest(".form-group").removeClass("hidden");
		}else{
			$("input[name=isViewWechat]").closest(".form-group").addClass("hidden");
		}
		getInfoLabelByAppId(options);
		findQiniuDomainByAppId(thisVal);
		//查询栏目
		appColumnsByAppId(options.appId);
	});
	
	function appColumnsByAppId(appId){
		var url = "/module/info/common/appcolumn_appid.html";
		$('#appcolumns_tree').children().remove();
		cqliving_ajax.ajaxOperate(url,"",{appId:appId},function(data,status){
			//ace tree  树形结构
			if(data.data){
				common_treeview.treeview("appcolumns_tree",data.data,treeSelect);
			}
		});
	}
	
	var _appId = $("select[name=appId]").val();
	if(!_appId)_appId = appId;
	var options = {
			appId:_appId,
	        url :"common/info_label_byappid.html"
	};
	
	getInfoLabelByAppId(options);
	
	function getInfoLabelByAppId(options){
		cqliving_ajax.ajaxOperate(options.url,"body",{appId:options.appId},function(data,status){
             var infoLabels = data.data;
             if(status == 'success'){
            	 var infoLabelInput = $("#infoLabel").val().split(",");
            	 if(infoLabels){
                	 var html = "";
                	 for(var i=0,m=infoLabels.length;i<m;i++){
                		 var infoLabel = infoLabels[i];
                		 html += "<option value='"+infoLabel.name+"'";
                		 if(infoLabelInput.length>=0){
                			 for(var j=0,x=infoLabelInput.length;j<x;j++){
                				 var label = infoLabelInput[j];
                				 if(label && label==infoLabel.name){
                					 html +="  selected='true'";
                				 }
                			 }
                		 }
                		 html += ">"+infoLabel.name+"</option>";
                	 }
                	 $("select[name=infoLabel]").html(html);
                 }
            	 $("select[name=infoLabel]").chosen({max_selected_options:2});
            	 $("select[name=infoLabel]").trigger("chosen:updated");
             }
		});
	}
	
	var jcrop_api;
	//裁剪图片
	$("#img_thum").on("click","ul img",function(){
		if(!$(":input[name=news_copy]").val())return;
		var viewType = $(":input[name=listViewType]:checked").val();
		var imgConfig = findImageConfigByListType(info_templete_image_config,viewType);
		if(!imgConfig){
			 cqliving_dialog.error("未找到指定图片规格，请先配置图片规格");
			return;
		}
		//选框对象
		var imageCoordinate = {
			x:0,y:0,w:0,h:0,width:0,height:200,ratio:false
		}
		imageCoordinate.width = imgConfig.width;
		var text = "图片规格：";
		if(imgConfig.limitType == 1){
			imageCoordinate.height = imgConfig.hight;
			text += ("(固定尺寸),"+imageCoordinate.width + "x" + imageCoordinate.height);
		}else{
			imageCoordinate.ratio = true;
			text += ("(等比压缩),宽："+imageCoordinate.width);
		}
		$(".img_size").text(text);
		var $this = $(this);
		var imgSrc = $this.attr("src");
		var infoContentImgUrl = $(":input[name=infoClassifyList]").val();
		$("#list_viewimg_modal").modal("show");
		  var boundx,boundy,$preview = $('#preview-pane'),
		  $pcnt = $('#preview-pane .preview-container'),
		  $pimg = $('#preview-pane .preview-container img'),
		  xsize = $pcnt.width(),ysize = $pcnt.height();
		  $pimg.attr("src",imgSrc);
		  if(jcrop_api){
			  jcrop_api.setImage(imgSrc,function(){
				    jcrop_api = this;
				    var bounds = jcrop_api.getBounds();
				    boundx = bounds[0];
				    boundy = bounds[1];
				    jcrop_api.setOptions({
				    	onChange: updatePreview,
					    onSelect: updatePreview
				    });
			  });
		  }else{
			  $('#jcrop_div').attr("src",imgSrc);
			  $('#jcrop_div').Jcrop({
				    onChange: updatePreview,
				    onSelect: updatePreview,
				    aspectRatio: xsize / ysize,
				    boxWidth:600
				  },function(){
					jcrop_api = this;
				    var bounds = jcrop_api.getBounds();
				    boundx = bounds[0];
				    boundy = bounds[1];
				    $preview.appendTo(jcrop_api.ui.holder);
				  });
		  }
		  function updatePreview(c){
		    if (parseInt(c.w) > 0) {
		      var rx = xsize / c.w;
		      var ry = ysize / c.h;
		      $pimg.css({
		        width: Math.round(rx * boundx) + 'px',
		        height: Math.round(ry * boundy) + 'px',
		        marginLeft: '-' + Math.round(rx * c.x) + 'px',
		        marginTop: '-' + Math.round(ry * c.y) + 'px'
		      });
		    }
		    imageCoordinate.x = Math.round(c.x);
		    imageCoordinate.y = Math.round(c.y);
		    imageCoordinate.w = Math.round(c.w);
		    imageCoordinate.h = Math.round(c.h);
		  };
		  
		  //保存裁剪的图片
		  $("#list_viewimg_modal .modal-footer button").eq(1).unbind("click").bind("click",function(){
			  var url = "common/cut_image.html";
			  var param = $.extend({},imageCoordinate,{fileUrlPath:imgSrc});
			 cqliving_ajax.ajaxOperate(url,"#list_viewimg_modal",param,function(data,status){
				if(data.code >= 0){
					var newImageSrc = data.data;
					infoContentImgUrl = infoContentImgUrl.replace(imgSrc,newImageSrc);
					$this.attr("src",newImageSrc);
					$(":input[name=infoClassifyList]").val(infoContentImgUrl);
					$("#form1").attr("canSumbit","yes");
					$("#list_viewimg_modal").modal("hide");
				}else{
					cqliving_dialog.error(data.message ? data.message:"裁剪图片失败");
				}
			});
	     });
	});
	
	//是改变原来的内容类型contextType
	function isChangeContextType(nowContextType){
		var general_contextType = $(":input[name=general_contextType]").val();
		if(!nowContextType || !general_contextType){
			return false;
		}
		general_contextType = parseInt(general_contextType,10);
		if(general_contextType != nowContextType){
			return true;
		}
		return false;
	}
	
	//资讯内容类型切换
	$(":input[name=contextType]").next().bind("click",function(){
		var contextType = $(this).siblings().val();
		
		//{0:纯文本,1:多图,2:原创,3:外链,4:音频,5:视频}
		$("#app_resource").closest(".form-group").addClass("hidden");
		$(".contextType,.origion_news_content").addClass("hidden");
		$(".contextType").eq(contextType).removeClass("hidden");
		if(0 == contextType){
			var ue = initUeditor("infocontent_editor");
			ue.addListener('contentChange',function(){
				var content = ue.getContent();
				var contentTx = ue.getContentTxt();
				$("textarea[name=content]").val(content);
				$("textarea[name=contentText]").val(contentTx);
			});
		}else if(1 == contextType){//多图
			$("#app_resource").closest(".form-group").removeClass("hidden");
			contentUploader = myUploader.init(infoContentOptions,contentUploader);
		}else if(2 == contextType){//原创
			$(".origion_news_content").removeClass("hidden");
		}else if(4 == contextType || 5 == contextType){
			$(".contextType").eq(4).removeClass("hidden");
			var _appId = $("select[name=appId]").val();
			if(!_appId)_appId = appId;
			var strategyType = contextType == 4 ? 'mp3' : 'mp4';
			var type = contextType == 4 ? '2' : '1';
			$(":input[name=extensions]").val(strategyType);
			qiniuOption.tokenUrl="/common/qiniu/normal_token.html?type="+strategyType+"&appId="+_appId;
			qiniuOption.type = type;
			findQiniuDomainByAppId(appId);
			var audio = { title : "audio files", extensions : "mp3" };
			//,flv,avi,wmv,mpeg,mpg,mov,rmvb,mkv
            var video = { title : "video files", extensions : "mp4" };
			if(4 == contextType){
				qiniuOption.filters.mime_types = new Array(audio);
			}else{
				qiniuOption.filters.mime_types = new Array(video);
			}
			qiniuUploader = myQiNiu(qiniuOption);
		}
		//如果改变了内容类型，则移除资源
		if(isChangeContextType(contextType)){
			$("#app_resource").children().remove();
			$(".origion_news_content").html("");
			clearAppResource();//清除新闻资源
		}
		
	});
	
	//根据内容类型初始化显示
	if($(":input[name=contextType]:checked").val() == 2){//原创
		var infoId = $(":input[name=informationId]").val();
		getInfoContent({infoId:infoId});
	}	
	$(":input[name=contextType]:checked").next().click();
	window.scrollTo(0,0);
	
	//修改状态前先保存新闻
	$("#form1 button[status]").bind("click",function(){
		//视频栏目只能上传视频
		if(!limitColumnsVideoType())return;
		var status = $(this).attr("status");
		var isView = $(this).hasClass("view_button");
		if(!isView){
			$("#form1 :input[name=status]").val(status);
		}
		//将图片路径放入文本框中
		setInfoListViewImg();
		var listViewType = $(":input[name=listViewType]:checked").val();
		var infoClassifyList = $(":input[name=infoClassifyList]").val();
		if(listViewType && listViewType != 0 && !infoClassifyList){
			cqliving_dialog.error("请上传新闻列表图片");
			return;
		}
		//保存专题新闻
		if($("#form1 :input[name=type]").val() == 2){
			save_special_info(isView);
			return;
		}
		var contextType = $(":input[name=contextType]:checked").val();
		if(contextType == 0 ){//文本
			var content = $("#form1 textarea[name=content]").val();
			if(!content){
				cqliving_dialog.error("请输入新闻内容");
				return;
			}
			//保存
			saveInformation(status,isView);
		}else if (contextType == 1 ){//多图
			//获取内容图片及赋值到相应的文本框
			if(!setInfoContent6Images()) return;
			saveInformation(status,isView);
		}else if (contextType == 2 ){//原创
			var infoContent = $("#form1 .widget-container-span").length;
			if(infoContent<=0){
				cqliving_dialog.error("请添加原创新闻内容");
				return;
			}
			saveInformation(status,isView);
		}else if (contextType == 3 ){//外链
			var contentUrl = $("#form1 :input[name=contentUrl]").val();
			if(!contentUrl){
				cqliving_dialog.error("请输入外链地址");
				return;
			}
			//保存新闻
			saveInformation(status,isView);
		}else if(contextType == 4 || contextType == 5){//视频或者音频
			var audioUrl = $(":input[name=va_url]").val();
			if(audioUrl){//手工输入的要设置一下值
				var $li = $("#app_resource li").last();
				var id = $li.attr("arid");
				var infoFileId = $li.find("input[name=infoFileId]").val();
				var desc = $li.find("textarea[name=description]").val();//描述
				var infoFileId = $li.find("input[name=infoFileId]").val();;
				var name = $li.find("input[name=ar_name]").val();
			    var appResource = {
						id:id,sortNo:1,infoFileId:infoFileId,type:qiniuOption.type,
						fileUrl:audioUrl,description:desc,name:name,status:3
				}
			    clearAppResource();
			    setAppResource(appResource);
			}
			if(!audioUrl){
				audioUrl = tempAppResource[0].fileUrl;
				if(!audioUrl){
					cqliving_dialog.error("请上传" + (contextType == 4 ? '音频' : '视频'));
					return;
				}
			}
			$("#contentUrl").val(audioUrl);
			saveInformation(status,isView);
		}
	});
	
	function saveInformation(infostatus,isView){
		
		var listViewType = $(":input[name=listViewType]:checked").val();
		if($("#form1").attr("canSumbit") == "no" && listViewType != 0 ){
			cqliving_dialog.error("选择项目的列表图比例同原栏目不同，需裁剪图片或者重新上传");
			return;
		}
		if(!wordsLimit() || !$("#form1").valid())return;
		var viewWechat = isViewWechat();
		var inputWechat = $("input[name=isViewWechat]:checked").val();
		inputWechat = parseInt(inputWechat,10);
		if(1==inputWechat && 0 == viewWechat){//内容不允许
			var isHidden = $("input[name=isViewWechat]:checked").closest(".form-group").hasClass("hidden");
			if(isHidden){
				//保存普通新闻
				$("input[name=isViewWechat]").eq(0).prop("checked",true);
				saveInfo(infostatus,isView);
				return;
			}
			cqliving_dialog.confirm("警告","该新闻不符合推荐到微信小程序条件,确认保存吗？确认保存会自动按照正确方式保存",function(){
				//保存普通新闻
				$("input[name=isViewWechat]").eq(0).prop("checked",true);
				saveInfo(infostatus,isView);
			});
			return;
		}
		//保存普通新闻
		saveInfo(infostatus,isView);
	}
	
	function saveInfo(infostatus,isView){
		//专题新闻添加子新闻
		handleSpecialSubInfo();
		var params = $("#form1").serializeArray();
		var url = "info_add.html";
		cqliving_ajax.ajaxOperate(url,"#form1",params,function(data,status){
			if(data.code >= 0){
				var info_detail = data.data;
				var classifyId = data.sessionId;
				$(":input[name=informationId]").val(info_detail.id);
				$(":input[name=id]").val(classifyId);
				$(":input[name=infoClassifyId]").val(classifyId);
				$(":input[name=infoClassifyListId]").val(info_detail.infoClassifyListId);
				if(!isView){
					$("#form1 button[status]").unbind("click");
					$("a[backRestoreParam],button[backRestoreParam]").click();
				}else{
					viewInfoClassify();
				}
			}else{
				cqliving_dialog.error(data.message ? data.message:"保存失败");
			}
		},{async:false});
	}
	
	//原创新闻的内容及修改
	$(".main-container").on("click","a[origion_type$=_element]",function(){
		var $this = $(this);
		var url = $this.attr("data_url");
		var original_news_type = $this.attr("origion_type").replace("_element","");
		cqliving_ajax.load("#add_original_layer",url,{infoContentId:$this.attr("infocontentid")},function(){
			$("#modal-form").modal("hide");
			$("div[id$=_modal_form]").eq(0).modal({backdrop: 'static', keyboard: false, show: true});
			$("div[id$=_modal_form] button[data-dismiss]").bind("click",function(){
				$(this).closest("div[class=modal]").remove();
			});
			$("div[id$=_modal_form] .icon-ok").parent().bind("click",function(){
				var $form = $(".draft_save").closest("form");
				//将图片路径放入文本框中
				setInfoListViewImg();
				var listViewType = $(":input[name=listViewType]:checked").val();
				var infoClassifyList = $(":input[name=infoClassifyList]").val();
				if($form.attr("canSumbit") == "no" && listViewType != 0){
					cqliving_dialog.error("选择项目的列表图比例同原栏目不同，需裁剪图片或者重新上传");
					return;
				}
				if(!wordsLimit())return;
				if(!$form.valid()){
					cqliving_dialog.error("请完善新闻基本信息");
					return;
				};
				if(listViewType != 0 && !infoClassifyList){
					cqliving_dialog.error("请上传新闻列表图片");
					return;
				}
				//视频栏目只能上传视频
				if(!limitColumnsVideoType())return;
				var fnName = original_news_type+"_saveCheck";
				var canSave = window[fnName]();
				if(!canSave.flag){
					return;
				}
				var viewWechat = isViewWechat();
				var inputWechat = $("input[name=isViewWechat]:checked").val();
				inputWechat = parseInt(inputWechat,10);
				if(1==inputWechat && 0 == viewWechat){//内容不允许
					var isHidden = $("input[name=isViewWechat]:checked").closest(".form-group").hasClass("hidden");
					if(isHidden){
						//保存普通新闻
						$("input[name=isViewWechat]").eq(0).prop("checked",true);
						saveOriginNews(fnName,canSave,$form,original_news_type);
						return;
					}
					cqliving_dialog.confirm("警告","该新闻不符合推荐到微信小程序条件,确认保存吗？确认保存会自动按照正确方式保存",function(){
						//保存原创新闻
						$("input[name=isViewWechat]").eq(0).prop("checked",true);
						saveOriginNews(fnName,canSave,$form,original_news_type);
					});
					return;
				}
				//保存原创新闻
				saveOriginNews(fnName,canSave,$form,original_news_type);
			});
		});
	});
	
	//保存原创新闻
	function saveOriginNews(originalFnName,orginData,$form,original_news_type){
		handleSpecialSubInfo();
		clearAppResource();//清除新闻内容资源
		var params = $form.serializeArray();
		var url = "info_add.html";
		cqliving_ajax.ajaxOperate(url,$form,params,function(data,status){
			if(data.code >= 0){
				var info_detail = data.data;
				var classifyId = data.sessionId;
				$(":input[name=news_copy]").val("already");
				$(":input[name=informationId]").val(info_detail.id);
				$(":input[name=id]").val(classifyId);
				$(":input[name=infoClassifyId]").val(classifyId);
				$(":input[name=infoClassifyListId]").val(info_detail.infoClassifyListId);
				//保存
				originalFnName = original_news_type+"_save";
				window[originalFnName](orginData.survey_info,info_detail);
			}else{
				cqliving_dialog.error(data.message ? data.message:"保存失败");
			}
		});
	}
	
	function handleSpecialSubInfo(){
		//专题新闻添加子新闻
		var infoThemeSelect = $("select[name=infoThemeSelect]").val();
		if(infoThemeSelect){
			$(":input[name=news_copy]").val("add_special_sub");
			var specialId = $(":input[name=infoTheme]").val();
			var newInfoTheme = specialId + "," + infoThemeSelect;
			$(":input[name=infoTheme]").val(newInfoTheme);
		}
	}
	
	//添加原创类型前先判断当前新闻内容是否完整
	$(".add_info_content").on("click",function(){
		
		if(!limitColumnsVideoType())return;
		var $form = $(".draft_save").closest("form");
		//将图片路径放入文本框中
		setInfoListViewImg();
		var listViewType = $(":input[name=listViewType]:checked").val();
		var infoClassifyList = $(":input[name=infoClassifyList]").val();
		if($form.attr("canSumbit") == "no" && listViewType != 0){
			cqliving_dialog.error("选择项目的列表图比例同原栏目不同，需裁剪图片或者重新上传");
			return;
		}
		if(!wordsLimit()){
			return;
		}
		if(!$form.valid()){
			cqliving_dialog.error("请完善新闻基本信息");
			return;
		};
		if(listViewType != 0 && !infoClassifyList){
			cqliving_dialog.error("请上传新闻列表图片");
			return;
		}
		$("#modal-form").modal("show");
	});
	
	//检查图文保存
	window.image_text_saveCheck = function(){
		var data = {
				flag : true,
				survey_info : {
					id:$("#image_text_modal_form :input[name=id]").val(),
					appId:"",
					informationId:"",
					informationContentId:$("#image_text_modal_form :input[name=informationContentId]").val(),
					type:0,
					name:"",
					fileUrl:"",
					description:"",
					sortNo:1
				}
		}
		var $description = $("#image_text_modal_form textarea[name=description]");
		data.survey_info.description = $description.val();
		if(!$description.val()){
			data.flag = false;
			cqliving_dialog.error("请输入内容");
			return data;
		}
		return data;
		
	}
	
	window.image_text_save = function(appResourse,infodetail){
		appResourse.appId = infodetail.appId;
		appResourse.informationId=infodetail.id;
		var app = [];
		app.push(appResourse);
        var params = {appResourses:JSON.stringify(app)};
		var url = "common/save_app_resourse.html";
		cqliving_ajax.ajaxOperate(url,"#image_text_modal_form",params,function(data,status){
			if(data.code >=0){
				$(":input[name=multipleImgCount]").val(data.sessionId);
				getInfoContent({infoId:infodetail.id});
			}else{
				cqliving_dialog.error(data.message);
			}
		});
	}
	
	//多图
	window.images_saveCheck = function(){
		var images = [];
		var data = {
				flag : true,
				survey_info : ""
		}
		
		$("#images_modal_form .ace-thumbnails li").each(function(i,n){
			var $this = $(n);
			var description = $this.find("textarea").val();
			var name = $this.find(":input[name=name]").val();
			var fileUrl = $this.find(":input[name=fileUrl]").val();
			var id = $this.find(":input[name=id]").val();
			var sortNo = $this.find(":input[name=imginput]").val();
			if(!description){
				data.flag = false;
				cqliving_dialog.error("请输入图片描述");
				return data;
			}
			var imageObj = {
					id:id,
					appId:"",
					informationId:"",
					informationContentId:$("#images_modal_form :input[name=informationContentId]").val(),
					type:6,
					name:name,
					fileUrl:fileUrl,
					description:description,
					sortNo:sortNo
				}
			images.push(imageObj);
		});
		if(images.length<=0){
			data.flag = false;
			cqliving_dialog.error("请上传图片");
			return data;
		}
		data.survey_info=images;
		return data;
	}
	
	//多图
	window.images_save = function(images,infodetail){
		
		for(var i=0,m=images.length;i<m;i++){
			images[i].appId = infodetail.appId;
			images[i].informationId=infodetail.id;
		}
        var params = {appResourses:JSON.stringify(images)};
		var url = "common/save_app_resourse.html";
		cqliving_ajax.ajaxOperate(url,"#images_modal_form",params,function(data,status){
			if(data.code >=0){
				$(":input[name=multipleImgCount]").val(data.sessionId);
				getInfoContent({infoId:infodetail.id});
			}else{
				cqliving_dialog.error(data.message);
			}
		});
	}
	
	//音频保存检查
	window.audio_saveCheck = function(){
		var data = {
				flag : true,
				survey_info : {
					id:$("#audio_modal_form :input[name=id]").val(),
					appId:"",
					informationId:"",
					informationContentId:$("#audio_modal_form :input[name=informationContentId]").val(),
					type:2,
					name:"",
					fileUrl:"",
					description:"",
					sortNo:1
				}
		}
		var $name = $("#audio_modal_form :input[name=name]");
		var a_url = $("#audio_modal_form :input[name=a_url]").val();
		var $fileUrl = $("#audio_modal_form :input[name=fileUrl]");
		if(!a_url){
			a_url = $fileUrl.val();
		}
		$fileUrl.val(a_url);
		data.survey_info.name = $name.val();
		data.survey_info.fileUrl = a_url;
		if(!$name.val()){
			data.flag = false;
			cqliving_dialog.error("请输入文件名称");
			return data;
		}
		if(!a_url){
			data.flag = false;
			cqliving_dialog.error("请输入上传音频");
			return data;
		}
		return data;
		
	}
	
	//音频保存
	window.audio_save = function(appResourse,infodetail){
		appResourse.appId = infodetail.appId;
		appResourse.informationId=infodetail.id;
    	var qiniuPersistentId = $(":input[name=qiniuPersistentId]").val();
    	var qiniuHash = $(":input[name=qiniuHash]").val();
    	var qiniuKey = $(":input[name=qiniuKey]").val();
    	var qiniuDomain = $(":input[name=qiniuDomain]").val();
    	var originalName = $(":input[name=originalName]").val();
    	var extensions = $(":input[name=extensions]").val();
		var infoFileId = $("#audio_modal_form :input[name=infoFileId]").val();
		if(infoFileId)appResourse.infoFileId =infoFileId;
		var app = [];
		app.push(appResourse);
        var params = {appResourses:JSON.stringify(app),qiniuDomain:qiniuDomain,originalName:originalName,
        		qiniuPersistentId:qiniuPersistentId,qiniuHash:qiniuHash,qiniuKey:qiniuKey,extensions:extensions
        		};
		var url = "common/save_app_resourse.html";
		cqliving_ajax.ajaxOperate(url,"#audio_modal_form",params,function(data,status){
			if(data.code >=0){
				getInfoContent({infoId:infodetail.id});
			}else{
				cqliving_dialog.error(data.message);
			}
		});
	}
	
	
	//视频保存检查
	window.video_saveCheck = function(){
		
		var data = {
				flag : true,
				survey_info : {
					id:$("#video_modal_form :input[name=id]").val(),
					appId:"",
					informationId:"",
					informationContentId:$("#video_modal_form :input[name=informationContentId]").val(),
					type:1,
					name:"",
					fileUrl:"",
					description:"",
					sortNo:1
				}
		}
		var $name = $("#video_modal_form :input[name=name]");
		var $fileUrl = $("#video_modal_form :input[name=fileUrl]");
		var v_url = $("#video_modal_form :input[name=v_url]").val();
		if(!v_url){
			v_url = $fileUrl.val();
		}
		$fileUrl.val(v_url);
		data.survey_info.name = $name.val();
		data.survey_info.fileUrl = v_url;
		if(!$name.val()){
			data.flag = false;
			cqliving_dialog.error("请输入文件名称");
			return data;
		}
		if(!v_url){
			data.flag = false;
			cqliving_dialog.error("请输入上传视频");
			return data;
		}
		return data;
		
	}
	
	//视频保存
	window.video_save = function(appResourse,infodetail){
		appResourse.appId = infodetail.appId;
		appResourse.informationId=infodetail.id;
		
		var qiniuPersistentId = $(":input[name=qiniuPersistentId]").val();
    	var qiniuHash = $(":input[name=qiniuHash]").val();
    	var qiniuKey = $(":input[name=qiniuKey]").val();
    	var qiniuDomain = $(":input[name=qiniuDomain]").val();
    	var originalName = $(":input[name=originalName]").val();
    	var extensions = $(":input[name=extensions]").val();
    	var infoFileId = $("#video_modal_form :input[name=infoFileId]").val();
		if(infoFileId)appResourse.infoFileId =infoFileId;
		
		var app = [];
		app.push(appResourse);
        var params = {appResourses:JSON.stringify(app),qiniuDomain:qiniuDomain,originalName:originalName,
        		qiniuPersistentId:qiniuPersistentId,qiniuHash:qiniuHash,qiniuKey:qiniuKey,extensions:extensions};
        
		var url = "common/save_app_resourse.html";
		cqliving_ajax.ajaxOperate(url,"#video_modal_form",params,function(data,status){
			
			if(data.code >=0){
				getInfoContent({infoId:infodetail.id});
			}else{
				cqliving_dialog.error(data.message);
			}
			
		});
	}
	
	//检查投票是否可以保存
	window.vote_saveCheck = function (){

		var data = {
				flag: true,
				survey_info : {
						 id:"",
						 informationId:"",
						 informationContentId:"",
						 appId:"",
						 title:"",
						 type:1,
						 limitRateType:"",
						 limitRateTimes:"",
						 limitSingleType:"",
						 limitSingleTimes:"",
						 limitRuleType:"",
						 limitRuleTimes:"",
						 loggedStatus:1,
						 quesType:0
				  },
				  surveyAnswers:vote_answers
		}
		var $limitRateType = $("#vote_modal_form :input[name=limitRateType]:checked");
		var $limitSingleType = $("#vote_modal_form :input[name=limitSingleType]:checked");
		var $limitRuleType = $("#vote_modal_form :input[name=limitRuleType]:checked");
		var $title = $("#vote_modal_form :input[name=title]");
		var limitRateTimes = $limitRateType.siblings(":input").val();
		var limitSingleTimes = $limitSingleType.siblings(":input").val();
		var limitRuleTimes = $(":input[name=limitRuleTimes]").val();
		var $loggedStatus = $("#vote_modal_form :input[name=loggedStatus]");
		
		if($("#vote_modal_form #voteType6").is(":checked")){
			data.survey_info.quesType = 6;
		}
		data.survey_info.limitRateTimes = limitRateTimes;
		data.survey_info.limitSingleTimes = limitSingleTimes;
		data.survey_info.limitRuleTimes = limitRuleTimes;
		data.survey_info.limitRateType = $limitRateType.val();
		data.survey_info.limitSingleType = $limitSingleType.val();
		data.survey_info.limitRuleType = $limitRuleType.val();
		data.survey_info.title = $title.val();
		data.survey_info.id = $("#vote_modal_form :input[name=id]").val();
		data.survey_info.informationContentId = $("#vote_modal_form :input[name=informationContentId]").val();
		if($loggedStatus.is(":checked"))data.survey_info.loggedStatus=$loggedStatus.val();
		
		if(!$limitRateType.val()){
			cqliving_dialog.error("请选择投票频次");
			data.flag = false;
			return data;
		}
		if($limitRateType.val() != 0 && !limitRateTimes){
			cqliving_dialog.error("请输入次数");
			data.flag = false;
			return data;
		}
		if(!$limitSingleType.val()){
			cqliving_dialog.error("请选择单项限制");
			data.flag = false;
			return data;
		}
		if($limitSingleType.val() != 0 && !limitSingleTimes){
			cqliving_dialog.error("请输入单项投票次数");
			data.flag = false;
			return data;
		}
		if(!$limitRuleType.val()){
			cqliving_dialog.error("请选择投票规则");
			data.flag = false;
			return data;
		}
		if(!$title.val()){
			cqliving_dialog.error("请输入投票主题");
			data.flag = false;
			return data;
		}
		if(!vote_answers || vote_answers.length<=0){
			cqliving_dialog.error("请添加投票选项");
			data.flag = false;
			return data;
		}
		return data;
	
	}
	
	//保存投票
	window.vote_save = function (surveyInfo,infodetail){
		surveyInfo.appId = infodetail.appId;
		surveyInfo.informationId=infodetail.id;
		var params = $.extend({},surveyInfo,{surveyAnswers:JSON.stringify(vote_answers)});
		var url = "common/save_vote.html";
		cqliving_ajax.ajaxOperate(url,"#vote_modal_form",params,function(data,status){
			if(data.code >=0){
				getInfoContent({infoId:infodetail.id});
			}else{
				cqliving_dialog.error(data.message);
			}
		});
	}

	//调研保存检查
	window.survey_saveCheck=function(){
		var data = {
				flag:true,
				survey_info:{
					id:$("#survey_modal_form :input[name=id]").val(),
					informationContentId:$("#survey_modal_form :input[name=informationContentId]").val(),
					title:"",
					loggedStatus:0
				}
		};
		var $title = $("#survey_modal_form :input[name=title]");
		var $loggedStatus = $("#survey_modal_form :input[name=loggedStatus]");
		if($loggedStatus.is(":checked"))data.survey_info.loggedStatus=1;
		data.survey_info.title = $title.val();
		if(!$title.val()){
			cqliving_dialog.error("请输入调研主题");
			data.flag = false;
			return data;
		}
		var quest = $("#survey_modal_form .survey_item_div .widget-box").length;
		if(quest.length<=0){
			cqliving_dialog.error("请添加调研问题");
			data.flag = false;
			return data;
		}
        $("#survey_modal_form .survey_item_div .widget-box").each(function(i,n){
			var answerL = $(n).find(".alert-info").length;
			if(answerL<=0){
				cqliving_dialog.error("请添加问题答案");
				data.flag = false;
				return data;
			}
		});
		return data;
	}
	
	//获取内容
	function getInfoContent(params){
		var url = "common/origion_news_view.html";
		cqliving_ajax.load(".origion_news_content",url,params,function(html){
			//加载后就消掉所有弹出层
			$("div[id$=_modal_form]").modal("hide");
		});
	}
	
	//调研保存
	window.survey_save=function(surveyInfo,infodetail){
		surveyInfo.appId = infodetail.appId;
		surveyInfo.informationId=infodetail.id;
		var quesIds = [];
		$("#survey_modal_form .survey_item_div .widget-box").each(function(i,n){
			var quesId = $(n).attr("ques_id");
			quesIds.push(quesId);
		});
		
		var params = $.extend({},surveyInfo,{"questionIds[]":quesIds});
		var url = "common/save_survey.html";
		cqliving_ajax.ajaxOperate(url,"#survey_modal_form",params,function(data,status){
			
			if(data.code >=0){
				getInfoContent({infoId:infodetail.id});
			}else{
				cqliving_dialog.error(data.message);
			}
		});
	}
	
	var arena_answers = [];
	//擂台保存检查
	window.arena_saveCheck=function(){
		arena_answers = [];
		var data = {
				flag:true,
				survey_info:{
					id:$("#arena_modal_form :input[name=id]").val(),
					informationContentId:$("#arena_modal_form :input[name=informationContentId]").val(),
					title:"",
					loggedStatus:1,
					limitRateType:"",
					limitRateTimes:"",
				}
		}
		var $limitRateType = $("#arena_modal_form :input[name=limitRateType]:checked");
		var limitRateTimes = $limitRateType.siblings(":input").val();
		var $title = $("#arena_modal_form :input[name=question]");
		var $loggedStatus = $("#arena_modal_form :input[name=loggedStatus]");
        if($loggedStatus.is(":checked"))data.survey_info.loggedStatus=$loggedStatus.val();
        data.survey_info.title = $title.val();
        data.survey_info.limitRateType=$limitRateType.val();
        data.survey_info.limitRateTimes=limitRateTimes;
		if(!$limitRateType.val()){
			cqliving_dialog.error("请选择参加打擂限制");
			data.flag = false;
			return data;
		}
		if($limitRateType.val() != 0 && !limitRateTimes){
			cqliving_dialog.error("请输入次数");
			data.flag = false;
			return data;
		}
		if(!data.survey_info.title){
			cqliving_dialog.error("请输入打擂问题");
			data.flag = false;
			return data;
		}
		var $answer1 = $("#arena_modal_form :input[name=answer1]");
		var $answer2 = $("#arena_modal_form :input[name=answer2]");
		var actValue1 = $("#arena_modal_form :input[name=actValue1]").val();
		var actValue2 = $("#arena_modal_form :input[name=actValue2]").val();
		var initValue1 = $("#arena_modal_form :input[name=initValue1]").val();
		var initValue2 = $("#arena_modal_form :input[name=initValue2]").val();
		var answerid1 = $("#arena_modal_form :input[name=answerid1]").val();
		var answerid2 = $("#arena_modal_form :input[name=answerid2]").val();
		var answercreateTime1 = $("#arena_modal_form :input[name=answercreateTime1]").val();
		var createTime2 = $("#arena_modal_form :input[name=answercreateTime2]").val();
		if(!$answer1.val()){
			cqliving_dialog.error("请输入正方问题");
			data.flag = false;
			return data;
		}
		if(!$answer2.val()){
			cqliving_dialog.error("请输入反方问题");
			data.flag = false;
			return data;
		}
		arena_answers.push({
			  id:answerid1,
			  initValue: initValue1 ? initValue1 : 0,
		      actValue:actValue1 ? actValue1 : 0,
			  answer:$answer1.val(),
			  inputLimit:"-1",
			  createTime:answercreateTime1 ? answercreateTime1 : moment().format('YYYY-MM-DD HH:mm:ss'),
			  isAffirmative:1,
			  type:0,
			  sortNo:1
		});
		arena_answers.push({
			  id:answerid2,
			  initValue: initValue2 ? initValue2 : 0,
			  actValue:actValue2 ? actValue2 : 0,
			  answer:$answer2.val(),
			  inputLimit:"-1",
			  createTime:createTime2 ? createTime2 : moment().format('YYYY-MM-DD HH:mm:ss'),
			  isAffirmative:0,
			  type:0,
			  sortNo:2
		});
		return data;
	}
	
	//打擂保存
	window.arena_save=function(survey_info,infodetail){
		var url = "common/save_arena.html";
		survey_info.appId = infodetail.appId;
		survey_info.informationId=infodetail.id;
		var params = $.extend({},survey_info,{answers:JSON.stringify(arena_answers)});
		cqliving_ajax.ajaxOperate(url,"#arena_modal_form",params,function(data,status){
			if(data.code >=0){
				getInfoContent({infoId:infodetail.id});
			}else{
				cqliving_dialog.error(data.message);
			}
		});
	}
	
	
	/**  -----------专题新闻----------------*/
	$('.special_info_theme_div .cp2').colorpicker().on("changeColor",function(e){
		  $(e.target).closest('.widget-header').css("background",e.color.toHex());
	   });
	   //加内容
	   $(".special_info_theme_div").prev().find("a").bind("click",function(){
		   var html = $("#info_theme_template").html();
		   $(".special_info_theme_div").append(html);
		   //颜色选取
		   $('.special_info_theme_div .cp2').colorpicker().on("changeColor",function(e){
			  $(e.target).closest('.widget-header').css("background",e.color.toHex());
		   });
	   });
	   //上移
	   $(".special_info_theme_div").on("click",".infoTheme_move_up",function(){
			  var $this = $(this);
			  var $thisContainer = $this.closest(".widget-container-span");
			  $thisContainer.prev().before($thisContainer);
	   });
	  //下移
      $(".special_info_theme_div").on("click",".infoTheme_move_down",function(){
    	  var $this = $(this);
		  var $thisContainer = $this.closest(".widget-container-span");
		  $thisContainer.next().after($thisContainer);
	  });
	   
      //删除
      $(".special_info_theme_div").on("click",".theme_remove",function(){
    	 var $widgetbox = $(this).closest(".widget-box");
    	 
    	 var id = $widgetbox.find(":input[name=id]").val();
    	 if(!id){
    		 $widgetbox.parent().remove();
    		 return;
    	 }
    	 
    	 var url = "common/dele_info_theme.html";
    	 var params = {infoThemeId:id};
    	 
    	 cqliving_dialog.confirm("警告","确认删除该分类吗？删除后不能恢复",function(){
    		 cqliving_ajax.ajaxOperate(url,$widgetbox,params,function(data,status){
    	 			if(data.code >= 0){
    	 				$widgetbox.parent().remove();
    	 			}else{
    	 				cqliving_dialog.error(data.message ? data.message:"删除失败");
    	 			}
    	 	 });
    	 });
	  });
      
      /***  专题新闻图片上传 */
      /** 图片上传  */
      if(!$("#special_img_upload").closest("form-group").hasClass("hidden")){
    	  initSpecialUpload();
      }
      
      function initSpecialUpload(){
    	//专题图片
	  	var specialUploaderOptions = {
	  			url:"/common/upload.html?imgsize=50",
	  			containerId:"special_img_upload",
	  			thumbContainerId:"special_img_thum",
	  			success:function(file,response){
	  				var imgPath = imgUrl+response.data.filePath;
	  				//将图片的引用修改为上传后的图片路径
	  				var $liImg = $("#special_img_thum ul li[id="+file.id+"] img").last();
	  				if($liImg.length<=0)$liImg = $("#special_img_thum ul li img").last();
	  				$liImg.attr("src",imgPath);
	  				$(":input[name=contentUrl]").val(imgPath);
	  			},
	  			error:function(file,reason){
	  				cqliving_dialog.error(reason);
	  			},
	  			isSingle:true,
	  			destroy:true
	  		};
	      
	  	   myUploader.init(specialUploaderOptions);
    	  
      }
      
      
	//专题新闻保存检查
	function special_info_save_check(){
		var obj = {
				flag:true,
				data:[]
		}
		obj.flag = $("#form1").valid();
		if(!obj.flag){
			cqliving_dialog.error("请完善新闻信息");
			return obj;
		}
		var contentUrl = $(":input[name=contentUrl]").val();
		if(!contentUrl){
			obj.flag = false;
			cqliving_dialog.error("请添加专题图片");
			return obj;
		}
		var infoThemeLen = $(".special_info_theme_div .infoTheme_move_up").length;
		if(infoThemeLen <= 0 ){
			obj.flag = false;
			cqliving_dialog.error("请添加专题分类");
			return obj;
		}
       $(".special_info_theme_div .widget-box").each(function(i,n){
			var name = $(n).find(":input[name=name]").val();
			if(!name){
				obj.flag = false;
				cqliving_dialog.error("请输入专题分类名称");
				return obj;
			}
			var infoTheme = {
					id:$(n).find(":input[name=id]").val(),
					appId:"",
					infoClassifyId:"",
					name:name,
					color:$(n).find(":input[name=color]").val(),
					sortNo:i+1
			}
			obj.data.push(infoTheme);
		});
		return obj;
	}
	
	//专题新闻保存
	function save_special_info(isView){
		var infoTheme = special_info_save_check();
		if(!infoTheme.flag)return;
		$("#form1 :input[name=infoTheme]").val(JSON.stringify(infoTheme.data));
		var params = $("#form1").serializeArray();
		var url = "info_add.html";
		cqliving_ajax.ajaxOperate(url,"#form1",params,function(data,status){
			if(data.code >= 0){
				var info_detail = data.data;
				var classifyId = data.sessionId;
				$(":input[name=informationId]").val(info_detail.id);
				$(":input[name=id]").val(classifyId);
				$(":input[name=infoClassifyId]").val(classifyId);
				$(":input[name=infoClassifyListId]").val(info_detail.infoClassifyListId);
				if(!isView){
					$("#form1 button[status]").unbind("click");
					$("a[backRestoreParam],button[backRestoreParam]").click();
				}else{
					viewInfoClassify();
				}
			}else{
				cqliving_dialog.error(data.message ? data.message:"保存失败");
			}
		});
	}
	
	//视频栏目限制只能上传视频
	function limitColumnsVideoType(){
		var flag = true;
		if(info_templete_image_config && typeof info_templete_image_config === 'object' && info_templete_image_config.length>=1){
			var tempCode = info_templete_image_config[0].templetCode;
			var mimeTypes = qiniuOption.filters.mime_types;
			var contextType = $(":input[name=contextType]:checked").val();
				contextType = parseInt(contextType,10);
			if( tempCode == 'NEWS_VIDEO_PLF' && contextType != 5){
				cqliving_dialog.error("视频栏目，请上传视频");
				flag = false;
			}
		}
		return flag;
	}
	
	//清除新闻内容资源
	function clearAppResource(){
		tempAppResource = [];
		$("input[name=appResourses]").val("");
	}
	//新闻内容资源设值
	function setAppResource(obj){
		tempAppResource.push(obj);
		$("input[name=appResourses]").val(JSON.stringify(tempAppResource));
	}
	
	//新闻内容多图获取图片地址及图片说明及图片数量
	function setInfoContent6Images(){
		var flag = true;
		clearAppResource();//清除新闻内容资源
		var multipleImgCount = 0;
		$("#app_resource li").each(function(i,n){
			var desc = $(n).find("textarea[name=description]").val();//描述
			var id = $(n).attr("arid");
			var sortNo = $(n).find("input[name=imginput]").val();;
			var infoFileId = $(n).find("input[name=infoFileId]").val();;
			var imgSrc = $(n).find("img").attr("src");
			var name = $(n).find("input[name=ar_name]").val();
			if(!imgSrc){
				cqliving_dialog.error("请上传新闻内容图片");
				flag = false;
				return false;
			}
			if(!desc){
				cqliving_dialog.error("请输入新闻图片描述");
				flag = false;
				return false;
			}
            var appResource = {
					id:id,sortNo:sortNo,infoFileId:infoFileId,type:6,
					fileUrl:imgSrc,description:desc,name:name,status:3
			}
            setAppResource(appResource);
            multipleImgCount +=1;
		});
		$(":input[name=multipleImgCount]").val(multipleImgCount);
		return flag;
	}
	
	//新闻预览
	function viewInfoClassify(){
		var infoId = $(":input[name=infoClassifyId]").val();
		if(!infoId){
			cqliving_dialog.error("请完善新闻内容");
			return;
		}
		//专题新闻
		if($("#form1 :input[name=type]").val() == 2){
			var url = "/module/info/common/info_theme_byclassifyid.html";
			cqliving_ajax.load(".special_info_theme_div",url,{infoClassifyId:infoId},function(){
				$('.special_info_theme_div .cp2').colorpicker().on("changeColor",function(e){
					  $(e.target).closest('.widget-header').css("background",e.color.toHex());
				 });
			});
		}
		var url = "/module/info/common/info_view.html";
		cqliving_ajax.ajaxOperate(url,"body",{infoId:infoId},function(data,status){
			$("body").append(data);
		},{dataType:"html"});
	}
	
	//文本框文字限制
	$('.limited').inputlimiter({
		remText: '还能输入子数：%n ,',
		limitText: '最大允许长度 : %n.'
	});
	
	//搜索
	$("#infoSource").bigAutocomplete({
		url:"common/find_info_source.html",
		callback:function(data){},
		appId:appId,
		preFunc:function(config){
			config.appId = $("select[name=appId]").val() ? $("select[name=appId]").val() : appId;
		}
	});
	
	//字数限制，英文字数为准
   $("#form1").myWords({//输入框字数
        obj_opts:"#title",
        obj_Maxnum:100,//要是只能输入140个字  那这里就是280
        obj_Lnum:".title_num",
        callBack:function(wordnum,surplusNum){
        	if(wordnum>=25){
        		$(".title_num").css("color","red");
        	}else{
        		$(".title_num").css("color","");
        	}
        }
    });
	
   $("#form1").myWords({//输入框字数
       obj_opts:"#listTitle",
       obj_Maxnum:100,//要是只能输入140个字  那这里就是280
       obj_Lnum:".list_title_num",
       callBack:function(wordnum,surplusNum){
       	if(wordnum>=25){
       		$(".list_title_num").css("color","red");
       	}else{
       		$(".list_title_num").css("color","");
       	}
       }
   });
   
   if($("#specialTheme").length>=1){
	   $("#form1").myWords({//输入框字数
	       obj_opts:"#specialTheme",
	       obj_Maxnum:100,//要是只能输入140个字  那这里就是280
	       obj_Lnum:".special_theme_num",
	       callBack:function(wordnum,surplusNum){
	       	if(wordnum>=25){
	       		$(".special_theme_num").css("color","red");
	       	}else{
	       		$(".special_theme_num").css("color","");
	       	}
	       }
	   });
   }
   
  //字数限制，英文字数为准
   $("#form1").myWords({//输入框字数
        obj_opts:"#synopsis",
        obj_Maxnum:200,//要是只能输入140个字  那这里就是280
        obj_Lnum:".synopsis_num"
    });
   
	//检查字数是否超过规定
	function wordsLimit(){
		var flag = true;
		var $text = $(".sns-num");
		if($text && $text.length>=1){
			$text.each(function(i,n){
				var $this = $(n);
				var text = $this.text();
                var $em = $this.find("em");
                var wordnum = parseInt($.trim($em.text()),10);
                if((text.indexOf("超出")!=-1 && wordnum!=0) || (wordnum && wordnum<0)){
        			flag = false;
        			cqliving_dialog.error("字数已超出最大数量要求,请减少输入字数！");
        			return false;
        		}               	
			});
		}
		return flag;
	}
   
	//根据内容判断是否推荐到小程序
	function isViewWechat(){
		var isViewWechat = 1;
		var contextType = $("input[name=contextType]:checked").val();
		contextType = parseInt(contextType,10);
		if(3 == contextType){ //外链不能推荐
			return 0;
		}
		var type = $("input[name=type]").val();
		type = parseInt(type);
		if(2 == type){//专题不能推荐
			return 0;
		}
		var $origionType = $("#form1 a[info_content_type]");
		if(!$origionType || $origionType.length<=0){
			return 1;
		}
		$origionType.each(function(i,n){
			var $this = $(n);
			var contentType = $this.attr("info_content_type");
			contentType = parseInt(contentType,10);
			if(3==contentType||4==contentType||5==contentType){//打擂投票调研不能推荐
				isViewWechat = 0;
				return false;
			}
		});
		return isViewWechat;
	}
	
	$(".chosen-select").chosen({width:'300px',search_contains:true});
	
   //表单验证
    $("#form1").validate({
    	ignore:'',
        rules: {
        	columnsId:{
        		required: true
        	},
                title : {
                required: true
            },
            specialTheme:{
            	required: true
            },
                type : {
                required: true
            },
                plViewType : {
                required: true
            },
                commentType : {
                required: true
            },
                commentValidateType : {
                required: true
            },
            onlineTime:{
            	required: true
            },
                initCount : {
                required: true,
                number:true
            },
                addType : {
                required: true
            },
                topTime : {
                required: true,
                number:true
            },
                columnsId : {
                required: true,
                number:true
            },
                contextType : {
                required: true
            },
               listViewType:{
            	 required: true
            }
        },
        messages: {
        	columnsId:{
        		required: ' '
        	},
            title : {
                required: ' '
            },
            specialTheme:{
            	required: ' '
            },
            type : {
                required: ' '
            },
            plViewType : {
                required: ' '
            },
            commentType : {
                required: ' '
            },
            commentValidateType : {
                required: ' '
            },
            initCount : {
                required: ' ',
                number:' '
            },
            onlineTime:{
            	required: ' '
            },
            addType : {
                required: ' '
            },
            topTime : {
            	required: ' ',
                number:' '
            },
            columnsId : {
                required: ' ',
                number:' '
            },
            contextType : {
                required: ' '
            },
            listViewType:{
           	 required: ' '
           }
        }
    });
});