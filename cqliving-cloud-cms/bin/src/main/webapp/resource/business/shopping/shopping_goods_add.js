require(['cqliving_ajax','cqliving_dialog','ZeroClipboard','myUploader','common_colorbox','ueditor'],
		function(cqliving_ajax,cqliving_dialog,zcl,myUploader,common_colorbox){
	window.ZeroClipboard = zcl;
	//列表图片
	var listUploaderOps = {
			url:"/common/upload.html?"+imgThumb,
			containerId:"list_img_upload",
			thumbContainerId:"list_img_thum",
			success:function(file,response){
				var imgPath = imgUrl+response.data.filePath;
				imgPath = cqliving_ajax.appendThumbSize(imgPath,imgWH);
				//将图片的引用修改为上传后的图片路径
				var $liImg = $("#list_img_thum ul li[id="+file.id+"] img").last();
				if($liImg.length<=0)$liImg = $("#list_img_thum ul li img").last();
				$liImg.attr("src",imgPath);
				$("input[name=listImageUrl]").val(imgPath);
			},
			error:function(file,reason){
				cqliving_dialog.error(reason);
			},
			isSingle:true,fileUrlPath:imgUrl,destroy:true,isclickView:true
		};
	
	//新闻内容多图上传
	var contentUploaderOps = {
			url:"/common/upload.html?"+imgThumb,
			containerId:"content_img_upload",
			thumbContainerId:"content_img_thum",
			success:function(file,response){
				var imgPath = imgUrl+response.data.filePath;
				    imgPath = cqliving_ajax.appendThumbSize(imgPath,imgWH);
				//将图片的引用修改为上传后的图片路径
				var $li = $("#content_img_thum ul li[id="+file.id+"]");
				$li.find("img").last().attr("src",imgPath);
			},
			error:function(file,reason){
				cqliving_dialog.error(reason);
			},
			isSingle:false,destroy:true,text:true,sort:true,isclickView:true,
			copy:true,upload:true,fileUrlPath:imgUrl,fileNumLimit:9
		};
	
	/**---------初始化----------*/
	myUploader.init(listUploaderOps);
	myUploader.init(contentUploaderOps);
	var ue = UE.getEditor("content_editor");
	ue.addListener('contentChange',function(){
		var content = ue.getContent();
		var contentTx = ue.getContentTxt();
		$("textarea[name=content]").val(content);
	});
	$("input[name=isFreeShipping]").bind("click",freeMailPost);
	$("input[name=isFreeShipping]:checked").click();
	//根据appid查找商品分类
	$("select[name=appId]").bind("change",findShopCategory);
	//保存商品
	$("#saveButton").bind("click",saveShoppingGoods);
	/**------初始化---------*/
	
	//包邮切换
	function freeMailPost(){
		var isFree = $(this).val();
		isFree  = parseInt(isFree,10);
		if(1 == isFree){//是包邮
			$(":input[name=freeShippingPrice],select[name=shippingFareTemplateId]").closest(".form-group").addClass("hidden");
		}else if(2 == isFree){
			$(":input[name=freeShippingPrice]").closest(".form-group").removeClass("hidden");
			$("select[name=shippingFareTemplateId]").closest(".form-group").addClass("hidden");
		}else{
			$("select[name=shippingFareTemplateId]").closest(".form-group").removeClass("hidden");
			$(":input[name=freeShippingPrice]").closest(".form-group").addClass("hidden");
		}
	}
	
	//获取商品图片
	function getContentImg(){
		var imgs = [];
		var $li = $("#content_img_thum ul li");
		if($li.length<=0)return imgs;
		$li.each(function(i,n){
			var $this = $(n);
            var shopImg = {};
            shopImg.sortNo = $this.find("input[name=imginput]").val();
            shopImg.id = $this.find("input[name=imgId]").val();
            shopImg.shoppingGoodsId = $this.find("input[name=shoppingGoodsId]").val();
            shopImg.status = $this.find("input[name=cmstatus]").val();
            shopImg.createTime = $this.find("input[name=createTime]").val();
            shopImg.description = $this.find("textarea[name=description]").val();
            shopImg.url = $this.find("img").attr("src");
            if(shopImg.description){
            	imgs.push(shopImg);
            }
		});
		return imgs;
	}
	
	function findShopCategory(){
		var $this = $(this);
		var appId = $this.val();
		var url = "/module/shopgoods/common/shop_categoy_appid.html";
		cqliving_ajax.ajaxOperate(url,null,{appId:appId},function(data,status){
			if(data.code>=0 && data.data && data.data.length>=1){
				var data = data.data;
				var html = "";
				for(var i=0,k=data.length;i<k;i++){
					var category = data[i];
					if(i == 0){
						html += '<option value="'+category.id+'" selected>'+category.name+'</option>';
						continue;
					}
					html += '<option value="'+category.id+'">'+category.name+'</option>';
				}
				$("select[name=categoryLevelOneId]").html(html);
			}else{
				cqliving_dialog.error("获取商品分类失败");
				$("select[name=categoryLevelOneId]").html("");
			}
			$("select[name=categoryLevelOneId]").trigger("chosen:updated");
		});
	}
	
	function saveShoppingGoods(){
		
		if(!$("#form1").valid())return;
		var contentImgs = getContentImg();
		if(!contentImgs || contentImgs.length<=0){
			cqliving_dialog.error("请上传商品图片,并输入图片描述");
			return;
		}
		var formData = $("#form1").serializeArray();
		formData = arrayToJson(formData);
		formData.shoppingImages = contentImgs;
		var priceStr = formData.price.toString().split(".");
		var freeShipStr = formData.freeShippingPrice.toString().split(".");
		if((priceStr.length>=2 && priceStr[1].length>=3) || 
				(freeShipStr.length>=2 && freeShipStr[1].length>=3)){
			cqliving_dialog.error("价格精确到分,小于等于两位小数,请重新输入");
			return;
		}
		formData.freeShippingPrice = formData.freeShippingPrice *100;
		formData.price = formData.price *100;
		var url = "/module/shopgoods/shop_goods_add.html";
		cqliving_ajax.ajaxOperate(url,null,{shoppingGoods:JSON.stringify(formData)},function(data,status){
			$("button[backRestoreParam]").click();
		});
	}
	
	function arrayToJson(arr){
		var json = {};
		if(!arr || arr.length<=0)return json;
		$.each(arr,function(i,n){
			if(n.name){
				json[n.name] = n.value || '';
			}
		});
		return json;
	}
	
});