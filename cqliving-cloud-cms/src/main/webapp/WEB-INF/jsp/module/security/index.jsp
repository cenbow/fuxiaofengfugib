<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"></jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<div class="row" id="table_content_page">
					<div class="col-xs-12">
						<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
							<div class="table-responsive">
								<table id="" class="table table-striped table-bordered table-hover dataTable">
									<tbody>
									<c:if test="${not empty pageInfo and pageInfo.totalCount>0}">
									<tr s>
				                		<td colspan="2">系统公告(最新未读${pageInfo.totalCount}条)</td>
				                    </tr>
									<c:forEach items="${pageInfo.pageResults}" var="item">
					                    <tr rid="${item.rId}" class="detail-tr">
					                		<td><a href="javascript:;">${item.title}</a></td>
					                    	<td><a href="javascript:;"><fmt:formatDate value="${item.sendTime}" pattern="yyyy-MM-dd HH:mm:ss" /></a></td>
					                    </tr>
					                </c:forEach>
					                </c:if>
					                <c:if test="${empty pageInfo or empty pageInfo.totalCount or pageInfo.totalCount<1}">
									<tr>
				                    	<td>暂无公告</td>
				                    </tr>
				                    </c:if>
					                </tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
require(['/resource/business/security/index.js'],function(index){
	index.init();
});
</script>