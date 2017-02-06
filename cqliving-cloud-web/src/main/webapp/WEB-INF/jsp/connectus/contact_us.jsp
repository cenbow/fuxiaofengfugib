<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jsp/common/include.jsp" %>
<link href="${request.contextPath}/front/detail/css/main_jlp.css" rel="stylesheet"/>
<title>联系我们</title>
</head>
<body>
	<div id="detail">
	  <div class="detail_content">
		  <c:if test="${not empty errorInfo}">${errorInfo}</c:if>
		  <c:if test="${empty errorInfo}">
		  	${item.content}
		  </c:if>
	  </div>
	</div>
</body>
</html>
<script type="text/javascript">
require(["jquery"],function($){
	$(function(){
		imgCtrl();
	});
	
	/**
	 * 图片控制
	 */
	function imgCtrl(){
		var $img = $("img");
		if($img.length>=1){
			$img.each(function(i,n){
				var $this = $(n);
				var w = $this.width();
				var maxWidth = document.body.offsetWidth;
				if(w && w>maxWidth){
				  $this.css("width","100%");
				  $this.css("height","auto");
				}
			});
		}
		var $map = $(".ueditor_baidumap");
		if($map.length>=1){
			$map.each(function(i,n){
				var $this = $(n);
				var w = $this.width();
				//获取预览框宽度
				var maxWidth = document.body.offsetWidth;
				//若预览框宽度未获取到或者图片宽度大于预览框宽度,将图片设置为90%宽度
				if((w && w>maxWidth) || !maxWidth){
				  $this.css("width","100%");
				  $this.css("height","auto");
				}
			});
		}
	}
});
</script>