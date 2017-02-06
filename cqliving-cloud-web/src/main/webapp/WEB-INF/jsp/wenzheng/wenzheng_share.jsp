<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head lang="en">
	<jsp:include page="/WEB-INF/jsp/wenzheng/common/header.jsp"></jsp:include>
	<meta content="${question.title}" itemprop="name"/>
	<meta content="${question.content }" itemprop="description"/>
	<meta content="http://images.cqliving.com/images/icon/${appId }.png" itemprop="image"/>
	<title>${question.title }</title>
</head>
<body id="index">
	<!-- <div id="header">
		<h1>问政分享详情</h1>
	</div> -->
	<div class="share_top">
     <span openUrl="${openUrl }"> 通过${appInName }客户端查看</span> <a href="javascript:;"><img src="${request.contextPath}/front/detail/images/close.png"/></a>
   </div>
	<div class="detail_share">
		<div class="section">
			<div class="detail_title">${question.title }</div>
			<div class="detail_infos">
				<p>提问人：${question.creator }</p>
				<p>受理时间：<fmt:formatDate value="${question.auditingTime }" pattern="yyyy-MM-dd HH:mm"/></p>
				<p>受理部门：${question.auditingDepartment }</p>
				<p>回复时间：<fmt:formatDate value="${question.replyTime }" pattern="yyyy-MM-dd HH:mm"/></p>
			</div>
			<div class="detail_content">
				<p>${question.content }</p>
				<c:forEach var="img" items="${imageList }">
					<div style="margin:5px;"><img src="${img.imageUrl }"></div>
				</c:forEach>
			</div>
		</div>
		<div class="section">
			<div class="section_detail">
				<span>受理部门回复</span>
			</div>
			<div class="detail_content">
				<p>${question.replyContent }</p>
			</div>
		</div>
		<div class="section">
			<div class="section_detail">
				<span>事件进度</span>
			</div>
			<ul>
				<c:if test="${question.status == 7 }">
					<li class="speed_info speed_over">
						<div>
							<img src="${pageContext.request.contextPath}/resource/images/wz/PIN-2.png" />已回复<span><fmt:formatDate value="${question.replyTime }" pattern="yyyy-MM-dd"/></span>
						</div>
						<div class="speed_line"></div>
					</li>
				</c:if>
				<c:if test="${question.status > 3  }">
					<li class="speed_info">
						<c:if test="${question.status == 7 }">
							<div class="speed_line"></div>
						</c:if>
						<div>
							<img src="${pageContext.request.contextPath}/resource/images/wz/dot.png" /> 已转交相关部门<span><fmt:formatDate value="${question.transferTime }" pattern="yyyy-MM-dd"/></span>
						</div>
						<div class="speed_line"></div>
					</li>
				</c:if>
				<c:if test="${question.status >= 3 }">
					<li class="speed_info">
						<c:if test="${question.status > 3  }">
							<div class="speed_line"></div>
						</c:if>
						<div>
							<img src="${pageContext.request.contextPath}/resource/images/wz/dot.png" />已受理<span><fmt:formatDate value="${question.auditingTime }" pattern="yyyy-MM-dd"/></span>
						</div>
					</li>
				</c:if>
			</ul>
		</div>
		<div class="section">
			<div class="section_detail">
				<span>评论</span><span class="section_detail_num">(${question.replyCount })</span>
			</div>
			<ul class="comment_list">
				<c:if test="${empty replyList || fn:length(replyList) eq 0 }">
					<div class="none_status" style="font-size:1em;">暂无信息</div>
				</c:if>
				<c:forEach var="item" items="${replyList }">
					<li data-id="${item.replyId }">
						<div class="comment_list_head">
							<div class="list_head">
								<c:if test="${empty item.headerImg }">
									<img src="${request.contextPath}/front/detail/images/default_head.png"/>
								</c:if>
								<c:if test="${not empty item.headerImg }">
									<img src="${item.headerImg }" />
								</c:if>
							</div>
							<div class="list_info">
								<p class="info_name">${item.userName }</p>
								<p class="info_time">${item.commentTime }</p>
							</div>
						</div>
						<div class="comment_list_content">
							<c:if test="${not empty item.passiveReplyName }">&nbsp;对&nbsp;<font style="color: #CC6600;">${item.passiveReplyName }</font>&nbsp;说:</c:if>
							${item.content }
						</div>
						<c:if test="${isSharePage }">
							<div class="comment_list_btn">
								<div class="btn_reply" commentId="${item.replyId }" sourceId="${id }" onclick="replyOnclick(${item.replyId }, ${id })">回复</div>
								<div class="btn_report" commentId="${item.replyId }">举报</div>
							</div>
						</c:if>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="detail_line"></div>
	<div class="share_link btn_click">
	    <a href="${openUrl }"> ${appInName }里有更多精彩，去看看<img src="${request.contextPath }/front/detail/images/link.png"/></a>
	</div>
</body>
</html>

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