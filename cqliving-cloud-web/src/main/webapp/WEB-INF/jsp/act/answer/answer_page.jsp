<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<style>
.activity_content .survey_radio label div {
	position: absolute;
	width: 1.4em;
	height: 1.4em;
	background: url(${request.contextPath }/front/detail/images/radio.png) no-repeat center;
	background-size: 100% 100%;
	top: .4em;
}
.activity_content .survey_radio label input[type="checkbox"]:checked+div {
	background: url(${request.contextPath }/front/detail/images/radio_check.png) no-repeat center;
	background-size: 100% 100%;
}
</style>
<div class="active_time">活动时间：${item.startTimeStr } 至 ${item.endTimeStr }</div>
	<div class="active_common">
		<div class="active_common_name">
			<span class="name_title"><span>答题</span></span><a class="name_right" onclick="answerShare();"><img src="${request.contextPath }/front/detail/images/share.png" />分享</a>
		</div>
		<div class="active_answer">
			<form action="" id="myForm">
				<input type="hidden" name="appId" id="appId" value="${appId }">
				<input type="hidden" name="sessionId" id="sessionId" value="${sessionId }">
				<input type="hidden" name="token" id="token" value="${token }">
				<input type="hidden" name="actTestId" value="${item.actTestId }">
				<input type="hidden" name="actInfoId" value="${item.actInfoId }">
				<input type="hidden" name="actInfoListId" value="${actInfoListId }">
				<input type="hidden" name="isLogin" value="${item.isLogin }">
				<c:set value="false" var="checkDisabled" />
				<c:if test="${not empty item.actCollectInfoList }">
					<c:forEach var="it" items="${item.actCollectInfoList }">
						<c:choose>
							<c:when test="${it.type == 2 }">
								<input type="hidden" name="optionIds" value="${it.actCollectInfoId }">
								<div class="activity_content">
									<div class="survey_radio" <c:if test="${it.isRequired == 1 }">required</c:if>>
										<div class="survey_title">${it.name }</div>
										<c:if test="${!checkDisabled }">
											<c:forEach var="tmp" items="${it.actCollectOptionList }" varStatus="index">
												<c:if test="${tmp.isDefault }"><c:set value="true" var="checkDisabled" /></c:if>
											</c:forEach>
										</c:if>
										<c:forEach var="tmp" items="${it.actCollectOptionList }" varStatus="index">
											<label>
												<input name="optionValues" class="input-radio-checkbox" id="tmpIndex_${it.actCollectInfoId }_${tmp.id }" type="checkbox" value="${tmp.id }" <c:if test="${tmp.isDefault }">checked</c:if> <c:if test="${checkDisabled }">disabled</c:if> >
												<div></div>
												<span>${tmp.value }</span>
											</label>
										</c:forEach>
									</div>
								</div>
							</c:when>
							<c:when test="${it.type == 3 }">
								<input type="hidden" name="optionIds" value="${it.actCollectInfoId }">
								<div class="activity_content">
									<div class="survey_checkbox" <c:if test="${it.isRequired == 1 }">required</c:if>>
										<div class="survey_title">${it.name }</div>
										<input id="tmpIndex_${it.actCollectInfoId }" type="hidden" name="optionValues" value="">
										<c:if test="${!checkDisabled }">
											<c:forEach var="tmp" items="${it.actCollectOptionList }" varStatus="index">
												<c:if test="${tmp.isDefault }"><c:set value="true" var="checkDisabled" /></c:if>
											</c:forEach>
										</c:if>
										<c:forEach var="tmp" items="${it.actCollectOptionList }" varStatus="index">
											<label>
												<input id="tmpIndex_${it.actCollectInfoId }_${tmp.id }" type="checkbox" class="input-checkbox" value="${tmp.id }" 
													<c:if test="${checkDisabled }">disabled</c:if> 
													<c:if test="${tmp.isDefault }">checked</c:if> 
													<c:if test="${not empty it.length && it.length > 0 }"> data-maxlength="${it.length}"</c:if> 
												/>
												<div></div>
												<span>${tmp.value }</span>
											</label>
										</c:forEach>
									</div>
								</div>
							</c:when>
							<c:when test="${it.type == 4 }">
								<input type="hidden" name="optionIds" value="${it.actCollectInfoId }">
								<div class="active_answer_select">
									<div class="answer_name">${it.name }</div>
									<c:if test="${!checkDisabled }">
										<c:forEach var="tmp" items="${it.actCollectOptionList }" varStatus="index">
											<c:if test="${tmp.isDefault }"><c:set value="true" var="checkDisabled" /></c:if>
										</c:forEach>
									</c:if>
									<select id="tmpIndex_${it.actCollectInfoId }" name="optionValues" <c:if test="${it.isRequired == 1 }">required</c:if> <c:if test="${checkDisabled }">disabled</c:if> >
										<option value="">请选择${it.name }</option>
										<c:forEach var="tmp" items="${it.actCollectOptionList }">
											<option value="${tmp.id }" <c:if test="${tmp.isDefault }">selected</c:if>>${tmp.value }</option>
										</c:forEach>
									</select>
								</div>
							</c:when>
							<c:otherwise>
								<input type="hidden" name="inputIds" value="${it.actCollectInfoId }">
								<div class="active_answer_list">
									<div class="answer_name">${it.name }</div>
									<c:if test="${!checkDisabled }">
										<c:set value="${not empty it.inputValue }" var="checkDisabled" />
									</c:if>
									<input id="tmpIndex_${it.actCollectInfoId }" type="text" name="inputValues" placeholder="请输入您的${it.name }" value="${it.inputValue }"
										<c:if test="${not empty it.length && it.length > 0 }"> maxlength="${it.length}"</c:if> 
										<c:if test="${it.isRequired == 1 }">required</c:if> 
										<c:if test="${checkDisabled}">disabled</c:if> 
									/>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
				</c:if>
				<c:if test="${empty item.userClassify && overdue}">
					<div class="btn_submit btn_click startAnswer">开始答题</div>
				</c:if>
				<c:if test="${not empty item.userClassify }">
					<c:if test="${item.userClassify.isFinish eq 1 }">
						<div class="answer_over_info">
							您上次答题分数<span>${item.userClassify.score }</span><label style="margin-left: 2px;">分，累计用时${item.userClassify.usedTime }分钟</label>
						</div>
						<div class="answer_btn" <c:if test="${!overdue }">align="center"</c:if>>
						<c:if test="${overdue }">
							<div class="btn_submit btn_click startAnswer">重新答题</div>
						</c:if>
							<div class="btn_submit btn_click" id="viewResult" onClick="redirectUrl('/act/answer/answer_question_list.html?sessionId=${sessionId}&token=${token }&classifyId=${item.userClassify.actTestClassifyId }&type=2')">查看结果</div>
						</div>
					</c:if>
					<c:if test="${item.userClassify.isFinish eq 0 && overdue}">
						<div class="answer_over_info">检测到您上次有未完成答卷，是否继续作答？</div>
						<div class="answer_btn">
							<div class="btn_submit btn_click" onclick="redirectUrl('/act/answer/answer_question_list.html?sessionId=${sessionId}&token=${token }&classifyId=${item.userClassify.actTestClassifyId }&type=3')">继续做答</div>
							<div class="btn_submit btn_click startAnswer">重新答题</div>
						</div>
					</c:if>
				</c:if>
				<input type="hidden" name="isUpdate" value="${checkDisabled }">
			</form>
		</div>
	</div>
<script type="text/javascript">
//是否回显
isCheckDisabled = '${checkDisabled}';
require([ '/resource/js/business/act/answer/answer.js' ], function(obj) {
	obj.init();
});
</script>