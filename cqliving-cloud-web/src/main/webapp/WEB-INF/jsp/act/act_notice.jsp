<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jsp/common/include.jsp" %>
<meta content="${item.title}" itemprop="name"/>
<meta content="${item.digest }" itemprop="description"/>
<c:if test="${not empty item.actImageUrl }">
	<meta content="${item.actImageUrl }" itemprop="image"/>
</c:if>
<c:if test="${empty item.actImageUrl }">
	<meta content="http://images.cqliving.com/images/icon/${appId }.png" itemprop="image"/>
</c:if>
<title>公告</title>
<style type="text/css">
	.active_common_name .name_right img {
		top:.5em;
	}
	.active_common_1 p img{
		max-width:100% !important;
		height:auto !important;
	}
	/*处理富文本中的无图模式*/
	.active_common_1 .detail_pic_none{
		height: 100px !important;
	} 
	/*处理页面上的无图模式*/
	.detail_pic_none {
		height: 100px;
	}
</style>
</head>
<body id="active">
	<div>
		<div class="active_banner">
			<c:if test="${not empty item.actImageUrl }">
				<c:choose>
					<c:when test="${not empty param.noimg }">
						<div class="detail_pic_none btn_click" imgSrc="${item.actImageUrl }">
							<span> ${appName } </span>
							<span class="detail_pic_none_click">点击查看图片</span>
						</div>
					</c:when>
					<c:otherwise>
						<img src="${item.actImageUrl }"/>
					</c:otherwise>
				</c:choose>
			</c:if>
			<div class="active_title">${item.title }</div>
			<div class="active_memo">${item.digest }</div>
		</div>
		<div class="active_time">活动时间：<fmt:formatDate value="${item.startTime }" pattern="yyyy-MM-dd HH:mm"/> 至 <fmt:formatDate value="${item.endTime }" pattern="yyyy-MM-dd HH:mm"/></div>
		
		<div class="active_common active_common_1">
			<div class="active_common_name">
				<span class="name_title"><span>公告</span></span><a class="name_right" onclick="window.AppJsObj.share();"><img src="${request.contextPath }/front/detail/images/share.png" />分享</a>
			</div>
			<div class="active_common_name">
				<span class="name_title"><span>活动内容</span></span>
			</div>
			<p>
				<imgrp:img appName="${appName }" replace="${not empty param.noimg ? 1 : 0}" content="${item.content}"/>
			</p>
		</div>
	</div>
	<script type="text/javascript">
		require([ '/resource/js/common/noImgView.js' ], function(obj) {
			obj.init();
		});
	</script>
</body>
</html>