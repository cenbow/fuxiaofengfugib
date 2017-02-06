<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<c:forEach items="${pageInfo.pageResults}" var="item">
	<li class="dd-item" data-id="${item.id }" data-sort-no="${item.sortNo }">
		<div class="dd-handle">
			${item.question }
			<span class="question-type"> ${allTypes[item.type] }</span>
			<c:if test="${isSetScore eq 1 }">
				<span class="question-score"><c:if test="${not empty item.score && item.score > 0 }">${item.score }分</c:if></span>
			</c:if>
			<c:set var="isHaveRight" value="0"/>
			<c:forEach var="answer" items="${item.answerList }">
				<c:if test="${not empty answer.isRightAnswer && answer.isRightAnswer == 1 }">
					<c:set var="isHaveRight" value="1"/>
				</c:if>
			</c:forEach>
			<c:if test="${isHaveRight eq 0 }"><span class="question-type" style="color: red;"> 请设置一个正确答案</span></c:if>
			<button class="btn btn-minier btn-danger pull-right delQuestionBtn" data-url="/module/act/common/act_test_questiondelete.html"><i class="bigger-120 icon-trash"></i>删除</button>
			<button class="btn btn-minier btn-primary pull-right editQuestionBtn"><i class="bigger-120 icon-edit"></i>编辑</button>
			<button class="btn btn-minier btn-success pull-right addAnswerBtn"><i class="bigger-120 icon-plus"></i>答案</button>
		</div>
		<ol class="dd-list" data-id="${item.id }">
			<c:forEach var="answer" items="${item.answerList }">
				<li class="dd-item" data-id="${answer.id }" data-sort-no="${answer.sortNo }">
					<div class="dd-handle">
						${answer.answer }
						<c:if test="${not empty answer.isRightAnswer && answer.isRightAnswer == 1 }"><span class="right-answer text-success">正确答案</span></c:if>
						<button class="btn btn-minier btn-danger pull-right delAnswerBtn" data-url="/module/act/common/act_test_answerdelete.html"><i class="bigger-120 icon-trash"></i>删除</button>
						<button class="btn btn-minier btn-primary pull-right editAnswerBtn"><i class="bigger-120 icon-edit"></i>编辑</button>
					</div>
				</li>
			</c:forEach>
		</ol>
	</li>
</c:forEach>