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
<title>${item.title}</title>
</head>
<body id="active">
	<div class="share_top">
     <span openUrl="${openUrl }"> 通过${appName }客户端查看</span> <a href="javascript:;"><img src="${request.contextPath}/front/detail/images/close.png"/></a>
   </div>
	<div class="answer_top">
		<div class="active_banner">
			<c:if test="${not empty item.actImageUrl }">
				<img src="${item.actImageUrl }"/>
			</c:if>
			<div class="active_title">${item.title }</div>
			<div class="active_memo">${item.digest }</div>
		</div>
		<div class="active_time">活动时间：<fmt:formatDate value="${item.startTime }" pattern="yyyy-MM-dd HH:mm"/> 至 <fmt:formatDate value="${item.endTime }" pattern="yyyy-MM-dd HH:mm"/></div>
		
		<div class="active_common active_common_1">
			<div class="active_common_name">
				<span class="name_title"><span>活动内容</span></span>
			</div>
			<p>
				${item.content }
			</p>
		</div>
	</div>
	<div class="detail_line"></div>
	<div class="share_link btn_click">
	    <a href="${openUrl }"> ${appName }里有更多精彩，去看看<img src="${request.contextPath }/front/detail/images/link.png"/></a>
	</div>
<script type="text/javascript">
require([ 'jquery' ], function($) {
	$(".share_top a").click(function(){
		$(".share_top").hide();
		$("#detail").removeClass("detail_share");
	});
	$("span[openUrl]").bind("click",function(){
		var $this = $(this);
		var openUrl = $this.attr("openUrl");
		location.href = openUrl;
    });
});
</script>
</body>
</html>