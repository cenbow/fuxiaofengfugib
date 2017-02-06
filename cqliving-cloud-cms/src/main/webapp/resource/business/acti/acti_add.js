define(['ZeroClipboard','inputlimiter','chosen','ueditor','myQiNiu','cqliving_ajax','cqliving_dialog','wenzi','myUploader'],
		function(zcl,inputlimiter,chosen,ue,myQiNiu,cqliving_ajax,cqliving_dialog,wenzi,myUploader){
	
	/**---------初始化开始-----------*/
	window.ZeroClipboard = zcl;
	
	var ueditor = UE.getEditor("content_ueditor");
	
	ueditor.addListener('contentChange',function(){
		var content = ueditor.getContent();
		$("textarea[name=content]").val(content);
	});
	
	//字数限制，英文字数为准
   $("#form1").myWords({//输入框字数
        obj_opts:"#title",
        obj_Maxnum:48,//要是只能输入140个字  那这里就是280
        obj_Lnum:".sns-num"
    });
   
   $(".chosen-select").chosen({width:'300px',search_contains:true});
   
    //文本框文字限制
	$('.limited').inputlimiter({
		remText: '还可以输入字数：%n ,',
		limitText: '最大允许长度 : %n.'
	});
	
	var listUpOption = {
			url:"/common/upload.html",
			containerId:"list_img",
			thumbContainerId:"list_img_thum",
			success:function(file,response){
				var imgPath = imgUrl+response.data.filePath;
				//将图片的引用修改为上传后的图片路径
				var $liImg = $("#list_img_thum ul li[id="+file.id+"] img").last();
				if($liImg.length<=0)$liImg = $("#list_img_thum ul li img").last();
				$liImg.attr("src",imgPath);
				
			},
			error:function(file,reason){
				cqliving_dialog.alert(reason);
			},
			isSingle:true,fileUrlPath:imgUrl
		};
	//初始化列表图片上传按钮
	var listUploader = myUploader.init(listUpOption);
	
	//初始化内容上传图片按钮
	var contentUpOption = {
			url:"/common/upload.html",
			containerId:"content_img",
			thumbContainerId:"content_img_thum",
			success:function(file,response){
				var imgPath = imgUrl+response.data.filePath;
				//将图片的引用修改为上传后的图片路径
				var $liImg = $("#content_img_thum ul li[id="+file.id+"] img").last();
				if($liImg.length<=0)$liImg = $("#content_img_thum ul li img").last();
				$liImg.attr("src",imgPath);
			},
			error:function(file,reason){
				cqliving_dialog.alert(reason);
			},
			isSingle:true,fileUrlPath:imgUrl
		};
	var contentUploader = myUploader.init(contentUpOption);
	
	/**---------初始化结束-----------*/
	
	
	$("#linkurl_modal_form .icon-ok").parent().bind("click",function(){
		
		var linkUrl = $("#modal_linkUrl").val();
		if(!linkUrl){
			cqliving_dialog.error("请输入外链地址");
			return;
		}
		var showType = $("#linkurl_modal_form select[name=modal_showType]").val();
		if(!showType){
			cqliving_dialog.error("请选择外链显示类型");
			return;
		}
		$("#content_modal_form,#linkurl_modal_form").modal("hide");
		createActiList(linkUrl,2);
	});
	
	//公告
	$(".announcement").bind("click",function(){
		$("#content_modal_form").modal("hide");
		createActiList(null,1);
	});
	//激活
	$(".content_table tbody").on("click",".active",function(){
		var $this = $(this);
		var actInfoListIndex = $this.closest("tr").attr("actInfoListIndex");
		var status = $this.closest("tr").find(".status").val();
		active(actInfoListIndex,status);
	});
	//删除
	$(".content_table tbody").on("click",".btn-del",function(){
		var actInfoListIndex = $(this).closest("tr").attr("actInfoListIndex");
		deleteContent(actInfoListIndex);
	});
	
	
	//删除
	function deleteContent(actInfoListIndex){
		var $tr = $(".content_table tr[actInfoListIndex='"+actInfoListIndex+"']");
		$tr.find("td").eq(3).text("已删除");
		$tr.find(".status").val(99);
		$tr.find("td").eq(4).html("");
	}
	
	//激活或者取消激活
	function active(actInfoListIndex,status){
		
		var $tr = $(".content_table tr[actInfoListIndex='"+actInfoListIndex+"']");
		if(status == 3){
			$tr.find("td").eq(3).text("未激活");
			$tr.find(".status").val(1);
			$tr.find(".active").attr("data-original-title","激活").addClass("blue").removeClass("red").find("i").addClass("icon-mail-forward").removeClass("icon-arrow-down");
		}else{
			if(!canActive()){
				cqliving_dialog.error("一次只能激活一个活动");
				return;
			}
			var type = $tr.find('.type').val();
			if(type == 4){//答题
				var actInfoListId = $tr.find('.id').val();
				cqliving_ajax.ajaxOperate('/module/act/common/validate_null_answer.html', null, {actInfoListId: actInfoListId}, function(data){
					if(data.code >= 0){
						$tr.find("td").eq(3).text("已激活");
						$tr.find(".status").val(3);
						$tr.find(".active").attr("data-original-title","取消激活").addClass("red").removeClass("blue").find("i").removeClass("icon-mail-forward").addClass("icon-arrow-down");
					}else{
						cqliving_dialog.error(data.message ? data.message : '激活失败');
					}
				});
			}else{
				$tr.find("td").eq(3).text("已激活");
				$tr.find(".status").val(3);
				$tr.find(".active").attr("data-original-title","取消激活").addClass("red").removeClass("blue").find("i").removeClass("icon-mail-forward").addClass("icon-arrow-down");
			}
		}
	}
	
	//新增外链及公告
	function createActiList(val,type){
		
		var $trLast = $(".content_table tr[actInfoListIndex]").last();
		var listIndex = $trLast.attr("actInfoListIndex");
		if(!listIndex){
			listIndex=0;
		}else{
			listIndex = parseInt(listIndex,10);
			listIndex += 1;
		}
		var $trHtml = $("#tr_tmp tbody tr").clone(true);
		$trHtml.attr("actInfoListIndex",listIndex);
		var typeName = "公告";
		var isType2Edit = false;//外链的编辑
		$trHtml.find(".showType").val(type);
		if(type == 2){
			typeName = "外链";
			var showType = $("#linkurl_modal_form select[name=modal_showType]").val();
			$trHtml.find(".showType").val(showType);
			if($("#linkurl_modal_form").attr("edit"))isType2Edit = true;
		}else{
			$trHtml.find(".edit").remove();
		} 
		$trHtml.find("td").eq(2).text(typeName);
		$trHtml.find(".type").attr("name","actInfoList["+listIndex+"].type");
		$trHtml.find(".type").val(type);
		$trHtml.find(".showType").attr("name","actInfoList["+listIndex+"].showType");
		$trHtml.find(".linkUrl").attr("name","actInfoList["+listIndex+"].linkUrl");
		$trHtml.find(".linkUrl").val(val);
		$trHtml.find(".id").attr("name","actInfoList["+listIndex+"].id");
		$trHtml.find(".status").attr("name","actInfoList["+listIndex+"].status");
		if(isType2Edit){
			editLinkUrlSave();
		}else{
			$(".content_table tbody").append($trHtml);
		}
		//同步时间
		var startTime = $(":input[name=startTime]").val();
		var endTime = $(":input[name=endTime]").val();
		synchorDateTime(startTime,endTime);
	}
	
	//外链及公告的时间同步
	$(":input[name=startTime]").on('apply.daterangepicker', function(ev, picker) {
		var startTime = picker.startDate.format('YYYY-MM-DD HH:mm:ss');
		var endTime = $(":input[name=endTime]").val();
		synchorDateTime(startTime,endTime);
	});
	
	$(":input[name=endTime]").on('apply.daterangepicker', function(ev, picker) {
		var startTime = $(":input[name=startTime]").val();
		var endTime = picker.startDate.format('YYYY-MM-DD HH:mm:ss');
		synchorDateTime(startTime,endTime);
	});
	
	
	function synchorDateTime(beginTime,endTime){
		
		if(!beginTime || !endTime)return;
		$(".content_table tbody tr .type").each(function(i,n){
			var $this = $(n);
			var type = parseInt($this.val(),10);
			if(type ==1 || type== 2){
				var $tr = $this.closest("tr");
				$tr.find("td").eq(1).text(beginTime+"至"+endTime);
			}
		});
		
	}
	
	
	//提交前需要处理图片
	$("#commonSaveButton").bind("click",function(){
		if(!handleActImage())return;
	});
	
	function handleActImage(addVoteOrTest){
		
		var flag = true;
		var $contentImg = $("#content_img_thum ul li img");
		var $listImg = $("#list_img_thum ul li img");
		if($listImg.length<=0){
			cqliving_dialog.error("请上传列表图片");
			return flag = false;
		}
		if($contentImg.length<=0){
			cqliving_dialog.error("请上传活动图片");
			return flag = false;
		}
		if(!addVoteOrTest && !hasContent()){
			cqliving_dialog.error("请添加活动内容");
			return flag = false;
		}
		$(":input[name=listImageUrl]").val($listImg.eq(0).attr("src"));
		$(":input[name=actImageUrl]").val($contentImg.eq(0).attr("src"));
		$(":input[name=add_content]").val(3);
		return flag;
	}
	
	
	//一次只能激活一个
	function canActive(){
		var flag = true;
		var totalActive = 0;
		$(".content_table tbody tr .status").each(function(i,n){
			var $n = $(n);
			var status = $n.val();
			if(status == 3){
				totalActive +=1;
			}
		});
		if(totalActive>=1)flag = false;
		return flag;
	}
	
	//必须得有未删除的活动内容
    function hasContent(){
		var flag = true;
		var totalActive = 0;
		$(".content_table tbody tr .status").each(function(i,n){
			var $n = $(n);
			var status = $n.val();
			if(status != 99){
				totalActive +=1;
			}
		});
		if(totalActive<=0)flag = false;
		return flag;
	}
	
	$("#content_modal_form a[href_url]").bind("click",function(){
		
		if(!handleActImage(true) || !$("#form1").valid()){
			cqliving_dialog.error("请完善活动基本信息");
			return;
		}
		var jthis = $(this);
		var hrefUrl = jthis.attr("href_url");
		var params = $("#form1").serializeArray();
		var url = "act_infoupdate.html";
		cqliving_ajax.ajaxOperate(url,"#form1",params,function(data,status){
			if(data.code >= 0){
				jthis.unbind("click");
				window.location.href = hrefUrl+"?actInfoId="+data.data.id;
			}else{
				cqliving_dialog.error(data.message?data.message:"活动添加失败");
			}
		});
		
	});
	
	$("#linkurl_modal_form").on("show.bs.modal",function(e){
		
		var $target = $(e.relatedTarget);
		var $tr = $target.closest("tr");
		if($tr.length>=1){//修改
			var id = $tr.find(".id").val();
			var linkUrl = $tr.find(".linkUrl").val();
			var showType = $tr.find(".showType").val();
			$("#linkurl_modal_form :input[name=modal_linkUrl]").val(linkUrl);
			$("#linkurl_modal_form :input[name=modal_actListId]").val(id);
			$("#linkurl_modal_form select[name=modal_showType]").val(showType);
			
			$("#linkurl_modal_form").attr("edit","edit");
			$("#linkurl_modal_form").attr("actInfoListIndex",$tr.attr("actInfoListIndex"));
		}else{
			$("#linkurl_modal_form :input").val("");
			$("#linkurl_modal_form select[name=modal_showType]").val(2);
			$("#linkurl_modal_form").attr("edit","");
			$("#linkurl_modal_form").attr("actInfoListIndex","");
		}
		
	});
	
	//外链的编辑弹层保存
	function editLinkUrlSave(){
		var actInfoListIndex = $("#linkurl_modal_form").attr("actInfoListIndex");
		if(actInfoListIndex){
			var $tr = $(".content_table tr[actInfoListIndex="+actInfoListIndex+"]");
			$tr.find(".id").val($("#linkurl_modal_form :input[name=modal_actListId]").val());
			$tr.find(".linkUrl").val($("#linkurl_modal_form :input[name=modal_linkUrl]").val());
			$tr.find(".showType").val($("#linkurl_modal_form select[name=modal_showType]").val());
		}
		
	}
});