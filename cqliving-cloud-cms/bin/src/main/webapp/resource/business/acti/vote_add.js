define(['ZeroClipboard','inputlimiter','chosen','ueditor','myQiNiu','cqliving_ajax','cqliving_dialog','wenzi','myUploader','common_colorbox'],
		function(zcl,inputlimiter,chosen,ue,myQiNiu,cqliving_ajax,cqliving_dialog,wenzi,myUploader,common_colorbox){
	
	/**---------初始化开始-----------*/
	window.ZeroClipboard = zcl;
	//当前操作的投票分类
	var $tr = "";
	
    //文本框文字限制
	$('.limited').inputlimiter({
		remText: '还可以输入字数：%n,',
		limitText: '最大允许长度 : %n.'
	});
	
	$("#vote_item_modal :input[name=itemTitle]").inputlimiter({
		remText: '还可以输入字数：%n,',
		limitText: '最大允许长度 : %n.',
		boxId:"limit_item_title"
	});
	
	$("#vote_item_modal :input[name=itemDesc]").inputlimiter({
		remText: '还可以输入字数：%n,',
		limitText: '最大允许长度 : %n.',
		boxId:"limit_item_desc"
	});
	
	//投票类型切换
	$("#form1 :input[name=type][type=radio]").bind("click",function(){
		var $this = $(this);
		if($this.val() == 2){
			$(".vote_classify_title,.vote_classify_add,.del_vote_classify").removeClass("hidden");
		}else{
			initVoteClassify();
			$(".vote_classify_title,.vote_classify_add,.del_vote_classify").addClass("hidden");
		}
	});
	
	$("#form1 :input[name=type][type=radio]:checked").click();
	
	//投票频次控制
	$(":input[name=limitRateType]").bind("click",function(){
		
		var $this = $(this);
		var limitRateType = $this.val();
		$("#limitSingleType"+limitRateType+"").prop("checked",true);
		
		if(limitRateType != 0){//0无限制
			$(".limitSingleTimesDiv").removeClass("hidden");
			$(".limitSingleTypeDiv").addClass("hidden");
		}else{
			$(".limitSingleTimesDiv").addClass("hidden");
			$(".limitSingleTypeDiv").removeClass("hidden");
		}
		
	});
	
	$(":input[name=limitRateType]:checked").click();
	
	$(":input[name=limitSingleType]").bind("click",function(){
		
		var $this = $(this);
		var limitSingleType = $this.val();
		if(limitSingleType != 0){
			$(".limitSingleTimesDiv").removeClass("hidden");
		}else{
			$(".limitSingleTimesDiv").addClass("hidden");
		}
	});
	
	$(":input[name=limitSingleType]:checked").click();
	
	//添加类型
	$(".vote_classify_add").bind("click",function(){
		addVoteClassify();
	});
	
	//删除分类
	$("#form1").on("click",".del_vote_classify",function(){
		var $this = $(this);
		$this.closest("tr").remove();
	});
	
	//投票项弹层save
	$("#vote_item_modal").on("click",".modal-footer button:first",function(){
		
		if(!$("#vote_item_form").valid()){
			cqliving_dialog.error("请完善投票信息");
			return;
		}
		//如果选择了图片投票，则必须上传图片
		var isImageVote = $tr.find(":input[defaultName=isImageVote]:checked").val();
		var imageUrl = $(this).closest("#vote_item_modal").find(":input[name=imageUrl]").val();
		if(isImageVote && isImageVote == 1 && !imageUrl){
			cqliving_dialog.error("图片投票，请上传投票项图片");
			return;
		}
		
		var uuid = $(this).closest("#vote_item_modal").attr("uuid");
		addVoteItem(uuid,$tr);
		
		$("#vote_item_modal").modal("hide");
		
	});
	
	//添加新投票项
	$("#form1").on("click",".add_vote_item",function(){
		var uuid = cqliving_ajax.uuid(10,32);
		$("#vote_item_modal").attr("uuid",uuid);
	});
	
	//编辑投票项
	$("#form1").on("click","tr .widget-box .vote_item_edit",function(){
		
		var $this = $(this);
		var $widgetBox = $this.closest(".widget-box");
		var uuid = $widgetBox.attr("uuid");
		$("#vote_item_modal").attr("uuid",uuid);
		
		$("#vote_item_modal").attr("edit","edit");
		//取值
		var formValue = {};
		$widgetBox.find(":input,textarea").each(function(i,n){
			var $n = $(n),value = $n.val(),name = $n.attr("defaultName");
			formValue[name] = value;
		});
		
		//设值
		setModalFormValue(formValue);
	});
	
	
	$("#vote_item_modal").on("show.bs.modal",function(e){
		var $target = $(e.relatedTarget);
		$tr = $target.closest("tr");
		var edit = $("#vote_item_modal").attr("edit");
		var ueditor = UE.getEditor("content_ueditor",{zIndex:1200});
		if(!edit){
			//$("#vote_item_form").get(0).reset();
			resetForm();
			ueditor.ready(function() {
				ueditor.setContent("");
			});
			$("#vote_item_form #item_img_thum ul").html("");
			$("#success,#vote_item_modal .qiniu_table").hide();
		}
		$("#vote_item_modal").attr("edit","");
		
		ueditor.addListener('contentChange',function(){
			var content = ueditor.getContent();
			$("#vote_item_modal textarea[name=content]").val(content);
		});
		//初始化百度图片上传
		initWebUploader();
		//初始化七牛上传
		initQiniu();
	});
	
	$(".vote_save").bind("click",function(){
		
		var jthis= $(this);
		
		//保存前需要检查
		if(!saveCheck())return;
		//处理数据
		handleActVoteDto();
		var params = $("#form1").serializeArray();
		var url = "/module/act/act_vote_save.html";
		var actInfoId = $("#form1 :input[name=actInfoId]").val();
		cqliving_ajax.ajaxOperate(url,"#form1",params,function(data,status){
			if(data.code >= 0){
				jthis.unbind("click");
				cqliving_dialog.success(data.message ? data.message : "保存成功",function(){
					window.location.href = "/module/act/act_infoadd.html?id="+actInfoId;
				});
			}else{
				cqliving_dialog.error(data.message ? data.message : "保存失败");
			}
		});
	});
	
	var listUploader = "";
	
	function initWebUploader(){
		
		if(listUploader)return;
		
		var listUpOption = {
				url:"/common/upload.html",
				containerId:"item_img",
				thumbContainerId:"item_img_thum",
				success:function(file,response){
					var imgPath = imgUrl+response.data.filePath;
					//将图片的引用修改为上传后的图片路径
					var $liImg = $("#item_img_thum ul li[id="+file.id+"] img").last();
					if($liImg.length<=0)$liImg = $("#item_img_thum ul li img").last();
					$liImg.attr("src",imgPath);
					
					$("#vote_item_form :input[name=imageUrl]").val(imgPath);
				},
				error:function(file,reason){
					cqliving_dialog.alert(reason);
				},
				isSingle:true,fileUrlPath:imgUrl
			};
		//初始化列表图片上传按钮
		listUploader = myUploader.init(listUpOption);
	}
	
	var qiniuUp = "";
	function initQiniu(){
		
		if(qiniuUp)return;
		
		var _appId = $(":input[name=appId]").val();
		  cqliving_ajax.ajaxOperate("/module/info/common/qiniu_by_appid.html","#vote_item_modal",{appId:_appId},function(data,status){
			  
			  if(!data.data){
				  cqliving_dialog.error("没有文件资源");
				  return;
			  }
			 var qiniuDomain = data.data.domainCustom ? data.data.domainCustom : data.data.domainTest;
			  //,flv,avi,wmv,mpeg,mpg,mov,rmvb,mkv
			 qiniuUp =  myQiNiu({
				  browseBtn:"video_pickfiles",
				  tokenUrl:"/common/qiniu/normal_token.html?type=mp4&appId="+_appId,
				  domain:qiniuDomain,
				  container:"video_container",
				  dropEle:"video_container",
				  filters: {
					  mime_types : [
					                { title : "video files", extensions : "mp4" }
					              ]
				  },
				  isSingle:true,
				  success:function(up,file,info){
					  var res = $.parseJSON(info);
					  var domain = up.getOption('domain');
					    var url;
					    if (res.url) {
					        url = res.url;
					    } else {
					        url = domain + encodeURI(res.key);
					    }
					    $(":input[name=qiniuPersistentId]").val(res.persistentId);
					    $(":input[name=qiniuDomain]").val(domain);
					    $(":input[name=extensions]").val("mp4");
					    $("#vote_item_form :input[name=status]").val(3);
					    $("#vote_item_form :input[name=videoUrl]").val(url);
					    
					    $("#vote_item_form :input[name=qiniuHash]").val(res.hash);
					    $("#vote_item_form :input[name=qiniuKey]").val(res.key);
					    $("#vote_item_form :input[name=originalName]").val(file.name);
				  }
			  });
		  });
	}
	
	
	//添加投票项到投票中
	function addVoteItem(uuid,$tr){
		
		var $replace = $tr.find(".widget-box[uuid="+uuid+"]");
		var $newItem = setVoteItem(uuid);
		if($replace.length>=1){
			$replace.before($newItem);
			$replace.remove();
		}else{
			$tr.find(".widget-span").append($newItem);
		}
		
	}
	
	//投票项弹出层显示时设置表单的值
	function setModalFormValue(formValue){
		$("#vote_item_form :input,#vote_item_form textarea").each(function(i,n){
			var $this = $(n);
			var name = $this.attr("name");
			$this.val(formValue[name]);
		});
		
		if(formValue.imageUrl){
			var html = '<li class="upload-imgs" id="WU_FILE_0">';
			    html += '<a href="'+formValue.imageUrl+'" data-rel="colorbox">';
			html += '<img style="width:150px;height:150px;" src="'+formValue.imageUrl+'" alt="150x150">';
			html += '</a>';
			html += '<div class="tools tools-top"><a href="javascript:;"><i class="icon-remove red"></i></a></div></li>';
		    $("#vote_item_form #item_img_thum ul").html(html);
		}else{
			$("#vote_item_form #item_img_thum ul").html("");
		}
		if(formValue.videoUrl){
			var $a = $("#fsUploadProgress .info > div a");
			
			if($a.length<=0){
				var str = "<tr><td colspan='3'><div><strong>Link:</strong><a href=" + formValue.videoUrl + " target='_blank' > " + formValue.videoUrl + "</a></div></td></tr>";
				$("#fsUploadProgress").html(str);
			}
			$a.attr("href",formValue.videoUrl);
			$a.text(formValue.videoUrl);
			$("#success,#vote_item_modal .qiniu_table").show();
		}else{
			$("#success,#vote_item_modal .qiniu_table").hide();
		}
		var content = $("#vote_item_form textarea[name=content]").val();
		var ueditor = UE.getEditor("content_ueditor",{zIndex:1200});
		ueditor.ready(function(){
			ueditor.setContent(content);
		});
		
		common_colorbox.init();
	}
	
	function resetForm(){
		$("#vote_item_form :input,#vote_item_form textarea").each(function(i,n){
			var $this = $(this);
			var value = $this.val('');
		});
	}
	
	
	//投票项弹层关闭取值并重置form，返回模板对象
	function setVoteItem(uuid){
		
		var $newItem = $("#vote_item_temp .widget-box").clone();
		$("#vote_item_form :input,#vote_item_form textarea").each(function(i,n){
			var $this = $(this);
			var value = $this.val();
			var name = $this.attr("name");
		    $newItem.find(":input[defaultName="+name+"]").val(value);
		});
		var number = $newItem.find(":input[defaultName=number]").val();
		var initValue = $newItem.find(":input[defaultName=initValue]").val();
		var itemTitle = $newItem.find(":input[defaultName=itemTitle]").val();
		
		var text = number +"－－－－－－－－－"+ itemTitle +"－－－－－－－－－"+ initValue;
		
		$newItem.find(".widget-header h6").text(text);
		$newItem.attr("uuid",uuid);
		
		return $newItem;
	}
	
	//组装数据
	function handleActVoteDto(){
		
		var $tr = $("#form1 .vote_classify_title").closest("tr");
		
		$tr.each(function(i,n){
			
			var $n = $(n);
			var classifyPrefix = "actVoteClassifyDtos["+i+"].";
			//处理分类
			var $classifyTitle = $n.find(".classify_title");
			var $classifyId =  $classifyTitle.siblings(".id");
			var $classifySubject =  $n.find(".subject");
			var $createTime = $classifyTitle.siblings(".createTime");
			var $sortNo = $classifyTitle.siblings(".sortNo");
			var $imageVote = $n.find(":input[defaultName=isImageVote]");
	        $classifyTitle.attr("name",classifyPrefix+"title");		
			$classifySubject.attr("name",classifyPrefix+"subject");
			$classifyId.attr("name",classifyPrefix+"id");
			$createTime.attr("name",classifyPrefix+"createTime");
			$imageVote.attr("name",classifyPrefix+"isImageVote");
			var sortNo = i + 1;
			$sortNo.val(sortNo);
			$sortNo.attr("name",classifyPrefix+"sortNo");
			
			//处理分类下的投票项
			var index = 0;
			$n.find(".widget-span .widget-box").each(function(j,m){
				
				var $m = $(m);
				var actVoteItemPrefix = classifyPrefix+"actVoteItems["+index+"].";
				
				if($m.find(":input[defaultName=sortNo]").length<=0){
					return true;
				}
				index +=1;
				$m.find(":input[defaultName=sortNo]").val(index);
				$m.find(":input,textarea").each(function(k,b){
					var $b = $(b);
					var name = $b.attr("defaultName");
					$b.attr("name",actVoteItemPrefix+name);
				});
			});
		});
	}
	
	function saveCheck(){
		
		var flag = true;
		
		if(!$("#form1").valid())return flag = false;
		
		/*if(!$(":input[name=actTemplateCode]").val()){
			cqliving_dialog.error("请选择投票模板");
			return flag = false;
		}*/
		
		//投票频次限制
		var limitRateType = $("#form1 :input[name=limitRateType]:checked").val();
		var limitRateTimes = $("#form1 :input[name=limitRateTimes]").val();
		//投票单项限制
		var limitSingleType = $("#form1 :input[name=limitSingleType]:checked").val();
		var limitSingleTimes = $("#form1 :input[name=limitSingleTimes]").val();
		//投票项类型限制
		var limitRuleType = $("#form1 :input[name=limitRuleType]:checked").val();
		var limitRuleTimes = $("#form1 :input[name=limitRuleTimes]").val();
		
		/*var isShare = $("#form1 :input[name=isShare]:checked").val();
		var shareAddTimes = $("#form1 :input[name=shareAddTimes]").val();
		
		var limitShareType = $("#form1 :input[name=limitShareType]:checked").val();
		var limitShareTimes = $("#form1 :input[name=limitShareTimes]").val();*/
		
		if(parseInt(limitRateType,10) !=0  && (!limitRateTimes || parseInt(limitRateTimes,10) <= 0)){
			cqliving_dialog.error("请输入限制投票次数且不小于0");
			return flag = false;
		}
		if(parseInt(limitSingleType,10) !=0  && (!limitSingleTimes || parseInt(limitSingleTimes,10) <= 0)){
			cqliving_dialog.error("请输入投票单项限制次数且不小于0");
			return flag = false;
		}
		if(parseInt(limitRuleType,10) !=0  && parseInt(limitRuleTimes,10) <= 0){
			cqliving_dialog.error("投票多选项数必须不小于0");
			return flag = false;
		}
		/*if(parseInt(isShare,10) !=0  && (!shareAddTimes || parseInt(shareAddTimes,10) <= 0)){
			cqliving_dialog.error("请输入分享后增加次数且次数不小于0");
			return flag = false;
		}*/
		/*if(parseInt(isShare,10) !=0  && parseInt(limitShareType,10) !=0  && (!limitShareTimes || parseInt(limitShareTimes,10) <= 0)){
			cqliving_dialog.error("请输入分享次数限制且次数不小于0");
			return flag = false;
		}*/
		
		//分类标题处理
		var $tr = $("#form1 .vote_classify_title").closest("tr");
		
		if($tr.length<=0){
			cqliving_dialog.error("投票标题不能为空");
			return flag = false;
		}
		
		var voteType = $("#form1 :input[name=type]:checked").val();
		
		$tr.each(function(i,n){
			var $n = $(n);
			var $subject = $n.find(".subject");
			var $classifyTitle = $n.find(".classify_title");
			if(!$subject.val()){
				flag = false;
				cqliving_dialog.error("投票标题不能为空");
				return false;
			}
			
			if(parseInt(voteType,10) == 2 && !$classifyTitle.val()){
				flag = false;
				cqliving_dialog.error("投票分类标题不能为空");
				return false;
			}
			
			//投票项不能为空
			var itemLen = $n.find(".widget-box .vote_item_edit").length;
			
			if(itemLen<=0){
				flag = false;
				cqliving_dialog.error("投票项不能为空");
				return false;
			}
			
			if(!checkImgVote($n)){
				flag = false;
				cqliving_dialog.error("图片投票请上传投票项的图片");
				return false;
			}
		});
		return flag;
	}
	
	function addVoteClassify(){
		
		var $newtr = $("#classify_temp_div tr").last().clone();
		$newtr.find(":input,textarea").val("");
		$newtr.find(":input[defaultName=isImageVote]").val("1");
		$(".vote_classify_add").before($newtr);
		
	}
	
	//检查分类投票的图片投票类型(是否是图片投票)
	function checkImgVote($voteClassify){
		
		var flag = true;
		if(!$voteClassify || $voteClassify.length<=0)return flag = true;
		var $imgVote = $voteClassify.find(":input[defaultName=isImageVote]");
		var isChecked = $imgVote.is(":checked");
		if(!isChecked)return flag = true;
		
		var $item = $voteClassify.find(".widget-box");
		if($item.length <=0 )return flag = false;
		
		$item.each(function(i,n){
			var $n = $(n);
			var $img = $n.find(":input[defaultName=imageUrl]");
			if(!$img || $img.length<=0)return;
			img =  $.trim($img.val());
            if(!img)return flag = false;			
		});
		return flag;
	}
	
	//初始化非分类投票
	function initVoteClassify(){
		
		//分类标题处理
		var $tr = $("#form1 .vote_classify_title").closest("tr");
	    if($tr.length<=0){
	    	addVoteClassify();
	    }else{
	    	$tr.each(function(i,n){
	    		if(i != 0)$(n).remove();
	    	});
	    }
	}
});