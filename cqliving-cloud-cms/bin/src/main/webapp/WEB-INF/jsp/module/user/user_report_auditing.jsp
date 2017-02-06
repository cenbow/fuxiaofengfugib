<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<%-- <div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="举报管理|/module/userReport/user_report_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="审核" name="_breadcrumbs_4"/>
	</jsp:include>
	<div class="page-content"> --%>
		<div class="row">
			<div class="col-xs-12  version-view">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="fo" action="/module/userReport/auditing.html" back_url="/module/userReport/user_report_list.html">
		        <input type="hidden" name="id" value="${item.id}"/>
		        <input type="hidden" name="status" id="status" value="${item.status}"/>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">举报人姓名</label>
                        <div class="col-sm-10">
                        	<input readonly="readonly" type="text" class="form-control" value="${item.name}">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">举报类型</label>
                        <div class="col-sm-10">
                    		<c:if test="${not empty item.reportCode}">
                    			<c:set var="report" value="" />
							         <c:forEach items="${fn:split(item.reportCode, ',')}" var="code" >
							                <c:forEach items="${reportTypeList}" var="obj">
					                    		<c:if test="${code eq obj.code}">
					                    			<c:set var="report" value="${report}${obj.name}&nbsp;"/>
					                    		</c:if>
					                    	</c:forEach>
							         </c:forEach>
							</c:if>
                    		<input readonly="readonly" type="text" class="form-control" value="${report}">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">来源类型</label>
                        <div class="col-sm-10">
                        	<input readonly="readonly" type="text" class="form-control" value="${allSourceTypes[item.sourceType]}">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">${allSTypeTitle[item.sourceType]}<c:if test="${TYPE1==item.operateType}">评论</c:if></label>
                        <div class="col-sm-10">
                        	<input readonly="readonly" type="text" class="form-control" value="${item.source}">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">审核描述</label>
                        <div class="col-sm-10">
                        	<textarea class="form-control" rows="5" cols="" id="auditingContent" name="auditingContent" placeholder="请输入审核描述" maxlength="250"></textarea>
                        </div>
                    </div>
		            <%-- <div class="form-group col-xs-12">
						<div class="col-sm-10">
							<div class="pull-right">
								<button class="btn btn-sm btn-primary btn-auditing" type="button" status="${pass}">
									<i class="icon-ok"></i>
									举报属实
								</button>
								<button class="btn btn-sm btn-danger btn-auditing" type="button" status="${noPass}">
									<i class="icon-remove"></i>
									举报不实
								</button>
								<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/userReport/user_report_list.html'">
									<i class="icon-undo bigger-110"></i>返回
								</button>
							</div>
						</div>
					</div> --%>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	<!-- </div>
</div> -->
<!-- <script type="text/javascript">
require(['/resource/business/user/user_report_auditing.js'],function(report_auditing){
	report_auditing.init();
});
</script> -->