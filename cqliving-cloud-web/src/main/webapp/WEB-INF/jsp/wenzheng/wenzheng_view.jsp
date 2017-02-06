<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head lang="en">
	<jsp:include page="/WEB-INF/jsp/wenzheng/common/header.jsp"></jsp:include>
	<meta content="${question.title}" itemprop="name"/>
	<meta content="${question.content }" itemprop="description"/>
	<meta content="http://images.cqliving.com/images/icon/${appId }.png" itemprop="image"/>
	<title>${question.title }</title>
</head>
<body>
	<div>
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
					<div style="margin:5px;">
						<c:choose>
							<c:when test="${not empty param.noimg }">
								<div class="detail_pic_none btn_click" imgSrc="${img.imageUrl }">
									<span> ${appName } </span>
									<span class="detail_pic_none_click">点击查看图片</span>
								</div>
							</c:when>
							<c:otherwise>
								<img src="${img.imageUrl }">
							</c:otherwise>
						</c:choose>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="section">
			<div class="section_detail">
				<span>受理部门回复</span>
			</div>
			<div class="detail_content">
				<p>
					<c:if test="${question.status == -1 }">
						<imgrp:img appName="${appName }" replace="${not empty param.noimg ? 1 : 0}" content="${question.rejectContent }"/>
					</c:if>
					<c:if test="${question.status != -1 }">
						<imgrp:img appName="${appName }" replace="${not empty param.noimg ? 1 : 0}" content="${question.replyContent }"/>
					</c:if>
				</p>
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
	</div>
	<script type="text/javascript">
		require([ '/resource/js/common/noImgView.js' ], function(obj) {
			obj.init();
		});
	</script>
</body>
</html>