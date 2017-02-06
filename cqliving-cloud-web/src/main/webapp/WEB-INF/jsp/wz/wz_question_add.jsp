<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head lang="en">
	<jsp:include page="/WEB-INF/jsp/wz/common/header.jsp"></jsp:include>
	<title>我要问政</title>
</head>
<body>
<%@include file="wzSessionPage.jsp" %>
	<div id="header">
		<div class="btn_back" onClick="
			<c:choose>
				<c:when test="${empty userSession }">window.history.go(-1)</c:when>
				<c:when test="${not empty question.id }">location.href='wz_my_question_list.html?appId=${wzAppId }&sessionId=${wzUserSessionId }&token=${wzUserToken }'</c:when>
				<c:otherwise>location.href='wz_question_list.html?appId=${wzAppId }&sessionId=${wzUserSessionId }&token=${wzUserToken }'</c:otherwise>
			</c:choose>
		">
			<i class="fa fa-angle-left"></i>
		</div>
		<h1>我要问政</h1>
	</div>
	<div class="wrap">
		<form method="post" action="${contextPath }/wz/home/wz_question_add.html" id="myForm">
			<input type="hidden" value="${wzAppId }" name="appId">
			<input type="hidden" value="${wzUserToken }" name="token">
			<input type="hidden" value="${wzUserSessionId }" name="sessionId">

			<input type="hidden" value="${question.id }" name="id"/>
			<c:set var="regionCodeTmp" value="${question.regionCode }"/>
			<c:set var="regionNameTmp" value="${question.regionName }"/>
			
			<c:if test="${empty question.regionName}">
				<c:forEach var="item" items="${regionList }">
					<c:if test="${appRegionName == item.name }">
						<c:set var="regionCodeTmp" value="${item.code }"/>
						<c:set var="regionNameTmp" value="${item.name }"/>
					</c:if>
				</c:forEach>
			</c:if>
			<input type="hidden" name="regionCode" id="regionCode" value="${regionCodeTmp }" />
			<input type="hidden" name="regionName" id="regionName" value="${regionNameTmp }" />
			<c:set var="typeCode" value="${question.type }"/>
			<c:set var="typeName" value="${allTypes[question.type] }"/>
			<input type="hidden" name="type" id="type" value="${question.type }" />
			<div class="add_pic">
				<ul>
					<li>
						<div class="add_left">事件类型</div>
						<div class="add_right type">
							<a class="add_more">
								<p>
									<c:if test="${empty typeName }">选择事件类型</c:if>
									<c:if test="${not empty typeName }">${typeName }</c:if>
								</p>
							</a>
						</div>
					</li>
					<li>
						<div class="add_left">所属区域</div>
						<div class="add_right region">
							<a class="add_more">
								<p>
									<c:if test="${empty regionNameTmp }">请选择区域</c:if>
									<c:if test="${not empty regionNameTmp }">${regionNameTmp }</c:if>
								</p>
							</a>
						</div>
					</li>
				</ul>
				
				<div class="add_text">
					<input type="text" name="title" maxlength="20" placeholder="请输入标题在20字以内" value="${question.title }"/>
				</div>
				<div class="add_memo">
					<textarea name="content" placeholder="请输入事件描述">${question.content }</textarea>
				</div>
				
				<c:if test="${not empty collectList && fn:length(collectList) > 0}">
					<c:forEach var="item" items="${collectList }">
						<c:set value="" var="collectValue"/>
						<c:if test="${not empty collentListValue }">
							<c:forEach var="collect" items="${collentListValue }">
								<c:if test="${collect.collectInfoId == item.id }">
									<c:set value="${collect.value }" var="collectValue"/>
								</c:if>
							</c:forEach>
						</c:if>
						<div class="add_text">
							<input type="hidden" name="collectId" value="${item.id }">
							<input type="text" name="collectValue" placeholder="请输入${item.name }" value="${collectValue }" <c:if test="${item.isRequired == 1 }">required="true"</c:if>/>
						</div>
					</c:forEach>
				</c:if>
				
				<div class="pic_up">
					<div class="add_name">上传图片</div>
					<c:if test="${not empty imageList }">
						<c:forEach var="image" items="${imageList }">
							<div class="pic_up_list">
								<input type="hidden" name="photos" value="${image.imageUrl }"/>
								<span class="select_pic"></span>
								<span class="upload_pic"><img src="${image.imageUrl }"/></span>
								<span class="delete_pic">删除</span>
							</div>
						</c:forEach>
					</c:if>
					<div class="pic_up_list">
						<span class="select_pic"></span>
					</div>
				</div>
			</div>
			<div id="footer" class="add_footer">
				<button type="button" class="btn_add_complete btn_active btn" id="submitBtn">提交</button>
			</div>
		</form>
	</div>
	
	<!-- 事件类型 -->
	<div id="modelTypeDiv" class="model">
		<div class="edit_bg"></div>
		<div class="select_list">
			<div class="select_list_title">
				事件类型<a><img src="${pageContext.request.contextPath }/resource/images/wz/close.png" /></a>
			</div>
			<ul class="select_list_content">
				<c:forEach var="item" items="${allTypes }">
					<li value="${item.key }">${item.value }</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	
	<!-- 所属区域 -->
	<div id="modelRegionDiv" class="model">
		<div class="edit_bg"></div>
		<div class="select_list">
			<div class="select_list_title">
				所属区域<a><img src="${pageContext.request.contextPath }/resource/images/wz/close.png" /></a>
			</div>
			<ul class="select_list_content">
				<c:forEach var="item" items="${regionList }">
					<li value="${item.code }">${item.name }</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<script type="text/javascript">
	require(['${pageContext.request.contextPath}/resource/js/business/wz/wzQuestionAdd.js'], function(obj){
		obj.init();
	});
	</script>
</body>
</html>