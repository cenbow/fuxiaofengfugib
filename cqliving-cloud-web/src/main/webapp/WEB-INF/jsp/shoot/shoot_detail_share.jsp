<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
		<meta content="${appInfo.name}-随手拍" itemprop="name"/>
		<meta content="${empty shootInfo.content ? '捕捉灵感一刻，分享生活故事。' : shootInfo.content}" itemprop="description"/>
		<meta content="${firstImage}" itemprop="image"/>
		<title>${appInfo.name}-随手拍</title>
	</head>
	<body>
		<div class="share_top" openurl="${openUrl}">通过${appInfo.name}客户端查看<a id="close_btn"><img src="/front/detail/images/close.png"/></a></div>
		<input type="hidden" id="shoot_id" value="${shootInfo.id}" />
		<div id="detail" class="detail_share">
  			<div class="photo_detail">
    			<div class="photo_head">
    				<c:choose>
    					<c:when test="${not empty userInfo.imgUrl}">
		    				<img src="${userInfo.imgUrl}"/>
    					</c:when>
    					<c:otherwise>
		    				<img src="${request.contextPath}/front/detail/images/default_head.png"/>
    					</c:otherwise>
    				</c:choose>
    			</div>
    			<div class="photo_name">${userInfo.name}</div>
    			<div class="photo_time">${shootInfo.createTimeStr}</div>
    			<div class="photo_content">${shootInfo.content}</div>
  			</div>
  			<!--内容-->
  			<div class="detail_content"> 
    			<!--文本--> 
    			<c:forEach items="${shootImages}" var="obj">
	    			<img src="${obj.imageUrl}"/>
	    			<p>${obj.description}</p>
    			</c:forEach>
    		</div>
  			<div class="detail_line"></div>
		  	<div class="detail_zan">
		    	<div class="detail_zan_img detail_zan_img_active btn_click"></div>
		    	<p>${shootInfo.priseCount}</p>
		  	</div>
  			<div class="detail_comment_list">
  				<c:if test="${not empty shootInfo.replyCount and shootInfo.replyCount > 0}">
	    			<div class="comment_list_title"><span>评论(${shootInfo.replyCount})</span></div>
  				</c:if>
    			<!-- 评论列表 -->
    			<ul id="comment_list"></ul>
  			</div>
  			<div class="share_link btn_click">
  				<a href="${openUrl}" style="color: #fff">${appInfo.name }里有更多精彩，去看看<img src="${request.contextPath }/front/detail/images/link.png"/></a>
  			</div>
		</div>
	</body>
	<script type="text/javascript">
		require(["/resource/js/business/shoot/shootDetailShare.js"], function(obj) {
			obj.init();
		});
	</script>
</html>