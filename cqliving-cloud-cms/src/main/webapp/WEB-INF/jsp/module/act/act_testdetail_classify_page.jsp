<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${not empty classifyList }">
	<c:forEach var="it" items="${classifyList }">
		<div class="showContent">
			<div class="showDiv">
				<input type="hidden" name="classifyId" value="${it.id }">
				<input type="hidden" name="sortNo" value="${it.sortNo }">
				<input type="hidden" name="isSetScore" value="${it.isSetScore }">
				<label class="col-sm-2 control-label ">分类标题：</label>
				<div class="col-xs-10 inline">
					<span class="help-inline col-xs-2">
						<span class="middle title">${it.title }</span>
					</span>
					<span class="help-inline col-xs-5">
						<label>内容：</label>
						<span class="middle content">${it.subject }</span>
					</span>
					<div class="col-xs-5">
						<button type="button" class="btn btn-minier btn-primary editBtn"><i class="icon-edit bigger-110"></i>编辑</button>
						<button type="button" class="btn btn-minier btn-primary sortBtn <c:if test="${actTest.type eq 1 }">hide</c:if>" data-val="up"><i class="fa fa-arrow-up"></i>上移</button>
						<button type="button" class="btn btn-minier btn-primary sortBtn <c:if test="${actTest.type eq 1 }">hide</c:if>" data-val="down"><i class="fa fa-arrow-down"></i>下移</button>
						<button type="button" class="btn btn-minier btn-danger delBtn <c:if test="${actTest.type eq 1 }">hide</c:if>"><i class="fa fa-remove"></i>删除</button>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</c:if>