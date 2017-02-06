<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
		<meta content="${appInfo.name}-段子" itemprop="name"/>
		<meta content="${shootInfo.content}" itemprop="description"/>
		<meta content="http://images.cqliving.com/images/icon/${appInfo.id}.png" itemprop="image"/>
		<title>${appInfo.name}-段子</title>
	</head>
	<body>
		<div class="share_top" openurl="${openUrl}">通过${appInfo.name}客户端查看<a id="close_btn"><img src="/front/detail/images/close.png"/></a></div>
		<input type="hidden" id="joke_id" value="${jokeInfo.id}" />
		<div id="detail" class="detail_share">
  			<!--内容-->
  			<div class="detail_content"> 
    			<!--文本--> 
    			<p>${jokeInfo.content}</p>
    		</div>
  			<div class="detail_line"></div>
  			<div class="detail_comment_list">
  				<c:if test="${not empty jokeInfo.replyCount and jokeInfo.replyCount > 0}">
    				<div class="comment_list_title"><span>评论(${jokeInfo.replyCount})</span></div>
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
		require(["/resource/js/business/joke/jokeDetailShare.js"], function(obj) {
			obj.init();
		});
	</script>
</html>