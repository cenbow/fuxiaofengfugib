<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jsp/common/include.jsp" %>
<title>${title }</title>
</head>
<body>
<body id="active">
	<div id="header_dj">
		<c:if test="${actTest.limitAnswerType eq 1 }">
			<a href="javascript:;" onclick="toBack()"></a>
		</c:if>
		<c:if test="${actTest.limitAnswerType ne 1 }">
			<a href="javascript:;" onclick="doBack()"></a>
		</c:if>
		<h3>${title }</h3>
		<c:if test="${actTest.limitAnswerType ne 1 && type ne 2}">
			<div class="btn_save_top btn_click" onclick="doSave(0)">保存</div>
		</c:if>
	</div>
	<c:choose>
		<c:when test="${type eq 2 }">
			<div class="answer_hasinfo"><span>您的分数<span class="highlight">${item.score }</span>分，累计用时${item.useTime }分钟，答错<span class="highlight">${item.errorCount }</span>题</span> </div>
		</c:when>
		<c:when test="${actTest.limitAnswerType eq 1 }">
			<div class="answer_hasinfo" id="countdown" data-time="${actTest.limitAnswerTimes }">
				<img src="${request.contextPath}/front/detail/images/time.png" /><span>答题时间还剩下<span class="highlight"></span></span>
			</div>
		</c:when>
	</c:choose>
	
	<div id="answer" class="answer_1">
		<div class="detail_activity">
			<div class="activity_content">
				<input type="hidden" id="actInfoListId" value="${actTest.actInfoListId }">
				<input type="hidden" id="pageType" value="${type }">
				<form action="/act/answer/answer_question_save.html" method="POST" id="myForm">
					<input type="hidden" name="appId" id="appId" value="${actTest.appId }">
					<input type="hidden" name="sessionId" id="sessionId" value="${sessionId }">
					<input type="hidden" name="token" id="token" value="${token }">
					<input type="hidden" name="startTime" value="${item.startTime }">
					<input type="hidden" name="actTestClassifyId" id="actTestClassifyId" value="${classifyId }">
					<input type="hidden" name="isFinish" value="1">
					<div class="activity_title">${actInifoTitle }</div>
					
					<c:forEach var="it" items="${item.questionList }" varStatus="index">
						<c:set var="questionTitle" value="${index.index + 1 }.${it.question }"/>
						<c:if test="${it.type == 1 }">
							<div class="survey_radio">
								<div class="survey_title <c:if test="${type eq 2 && !it.isRight}"> error</c:if>">${questionTitle }</div>
								<c:set var="userAnswerId" value=""/>
								<c:forEach var="tmp" items="${it.answerList }">
									<c:if test="${type eq 2 && tmp.isRightAnswer eq 1 }">
										<c:set var="userAnswerId" value="${tmp.id }"/>
									</c:if>
									<c:if test="${type eq 3 && tmp.userAnswer }">
										<c:set var="userAnswerId" value="${tmp.id }"/>
									</c:if>
									<label class="<c:if test="${type eq 2 && !it.isRight && tmp.isRightAnswer eq 1}"> error</c:if>">
										<input type="radio" name="radio${it.questionId }" value="${tmp.id }" <c:if test="${type eq 2 }">disabled</c:if> <c:if test="${userAnswerId eq tmp.id }">checked</c:if>/>
										<div></div>
										<span>${tmp.answer }</span>
									</label>
								</c:forEach>
								<input type="hidden" name="questionId" value="${it.questionId }">
								<input type="hidden" name="answerId" value="${userAnswerId }">
							</div>
						</c:if>
						<c:if test="${it.type == 2 }">
							<div class="survey_checkbox">
								<div class="survey_title <c:if test="${type eq 2 && !it.isRight}"> error</c:if>">${questionTitle }</div>
								<c:set var="userAnswerId" value=""/>
								<c:forEach var="tmp" items="${it.answerList }">
									<c:if test="${type eq 2 && tmp.isRightAnswer eq 1 }">
										<c:set var="userAnswerId" value="${tmp.id }"/>
									</c:if> <c:if test="${type eq 3 && tmp.userAnswer }">
										<c:set var="userAnswerId" value="${tmp.id }"/>
									</c:if>
									
									<label class="<c:if test="${type eq 2 && !it.isRight && tmp.isRightAnswer eq 1}"> error</c:if>">
										<input type="checkbox" value="${tmp.id }" <c:if test="${type eq 2 }">disabled</c:if> <c:if test="${userAnswerId eq tmp.id }">checked</c:if> />
										<div></div>
										<span>${tmp.answer }</span>
									</label>
								</c:forEach>
								<input type="hidden" name="questionId" value="${it.questionId }">
								<input type="hidden" name="answerId" value="${userAnswerId }">
							</div>
						</c:if>
					</c:forEach>
					<c:if test="${type ne 2}">
						<div class="btn_submit btn_click" onclick="doSubmit(this)">提交</div>
					</c:if>
				</form>
			</div>
		</div>
	</div>
	<div id="model">
		<div class="model_bg"></div>
		<div class="model_content">
			<h3>您的分数</h3>
			<h1>
				<span class="score"></span><label style="margin-left: 2px;">分</label>
			</h1>
			<p>
				<img src="${request.contextPath}/front/detail/images/answer.png" />累计用时<span class="diff">0</span>分钟
			</p>
			<div class="answer_btn">
				<div class="btn_submit btn_click" onclick="toBack();">退出答题</div>
				<div class="btn_submit btn_click" onclick="viewResultFn();">
					查看结果
				</div>
			</div>
		</div>
	</div>
	<!--返回保存提示 -->
	<div id="model_save">
		<div class="model_close"></div>
		<div class="model_bg"></div>
		<div class="model_content">
			<div class="model_content_info" style="text-align:center;">答案未提交，请保存后离开<br>以便后续继续作答</div>
			<div class="model_content_info1" style="text-align:center;">确认离开？</div>
			<div class="model_btn"> <a class="btn_save" href="javascript:;" onclick="doSave(1);">保存</a> <a  href="javascript:;" onclick="toBack();">离开</a> </div>
		</div>
	</div>
	<!--文字提示-->
	<div class="message_info">
		当前作答已保存成功
	</div>
</body>
<script type="text/javascript">
	var limitAnswerType = '${actTest.limitAnswerType}';
	require([ '/resource/js/business/act/answer/answerQuestionList.js' ], function(obj) {
		obj.init();
	});
	//跳转
	function redirectUrl(url){
		window.location.href = url;
	}
	function redirectBackUrl(url){
		window.AppJsObj.answerGoBack();
	}
</script>
</html>