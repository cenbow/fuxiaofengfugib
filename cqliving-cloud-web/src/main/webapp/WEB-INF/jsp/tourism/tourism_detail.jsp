<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta content='${tourismInfo.name}' itemprop="name"/>
<meta content='${tourismInfo.synopsis}' itemprop="description"/>
<meta content="${imgList[0].url}" itemprop="image"/>
<meta name="format-detection" content="telephone=yes">
<title>${tourismInfo.name }</title>

<style type="text/css">
 .hidden{
    display:none;
 }
</style>

</head>
<body>
<div class="share_top" downUrl="${appConfig.downLoadUrl }"> 通过${appInfo.name }查看
   <a href="javascript:;"><img src="${request.contextPath}/front/detail/images/close.png"/></a> 
</div>
<div id="travel" class="detail_share">
  <div class="bu_top" id="pic_show"> <img src="${imgList[0].url}"/>
    <div class="bu_pic_num_bg ta_pic_num_bg"></div>
    <div class="bu_pic_num ta_pic_num">1/${fn:length(imgList) }</div>
  </div>
  <div class="bu_money ta_title">${tourismInfo.name }</div>
  <div class="bu_info1">
    <div class="bu_info1_addr ta_info1_addr" downUrl="${appConfig.downLoadUrl}">
        <img src="${request.contextPath}/front/detail/images/addr1.png" />${tourismInfo.place}
    </div>
  </div>
  
  <jsp:include page="${request.contextPath}/tourism/tourism_des_way/${tourismInfo.id}.html?share=share" />
  
  <div class="share_link btn_click">
   <a href="${appConfig.downLoadUrl}"> ${appInfo.name }里有更多精彩，去看看
   <img src="${request.contextPath }/front/detail/images/link.png"/></a>
</div>
</div>

  <div class="hidden" id="imgView">
    <c:forEach items="${imgList}" var="img">
       <img src="${img.url }" alt="" />
    </c:forEach>
  </div>

</body>
</html>

<script type="text/javascript">

require(['swiper'],function(){
		//分享
		$(".share_top a").click(function(){
			$(".share_top").hide();
			$("#travel").removeClass("detail_share");
		});	
	     
		$(".share_top,.ta_info1_addr").click(function(){
			window.location.href=$(this).attr("downUrl");
		});
	
		var imgList = [];
		$("#imgView img").each(function(i,n){
			var img = $(n).attr("src");
			if(img)imgList.push(img);
		});
		
		//图片展示
		 var pic_show = $.photoBrowser({
	        items: imgList,
	        onSlideChange: function(index) {
	          console.log(this, index);
	        },

	        onOpen: function() {
	          console.log("onOpen", this);
	        },
	        onClose: function() {
	          console.log("onClose", this);
	        }
	      });
	      $("#pic_show").click(function() {
	        pic_show.open();
	      });
});

</script>