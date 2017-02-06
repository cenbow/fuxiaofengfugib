<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<%-- <div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="举报管理|/module/userReport/user_report_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="详情" name="_breadcrumbs_3"/>
	</jsp:include>
	<div class="page-content"> --%>
		<div class="row">
			<div class="col-xs-12 version-view">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
		        <!-- <div class="col-md-12 col-lg-8"> -->
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">举报人姓名</label>
                        <div class="col-sm-10">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.name}">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">举报时间</label>
                        <div class="col-sm-10">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value='${item.createTime}' pattern='yyyy-MM-dd HH:mm:ss'/>">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">举报类型</label>
                        <div class="col-sm-10">
                    		<c:set var="report" value="" />
                    		<c:if test="${not empty item.reportCode}">
						         <c:forEach items="${fn:split(item.reportCode, ',')}" var="code" >
						                <c:forEach items="${reportTypeList}" var="obj">
				                    		<c:if test="${code eq obj.code}">
				                    			<c:set var="report" value="${report}${obj.name}&nbsp;"/>
				                    		</c:if>
				                    	</c:forEach>
						         </c:forEach>
							</c:if>
							<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${report} ">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">来源类型</label>
                        <div class="col-sm-10">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${allSourceTypes[item.sourceType]}">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">${allSTypeTitle[item.sourceType]}<c:if test="${TYPE1==item.operateType}">评论</c:if></label>
                        <div class="col-sm-10">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.source}">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">状态</label>
                        <div class="col-sm-10">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${allStatuss[item.status]}">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">审阅状态</label>
                        <div class="col-sm-10">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${allAuditingTypes[item.auditingType] }">
                        </div>
                    </div>
                    <c:if test="${not empty item.auditingtor}">
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">审核人姓名</label>
                        <div class="col-sm-10">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.auditingtor}">
                        </div>
                    </div>
                    </c:if>
                    <c:if test="${not empty item.auditingContent}">
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">审核描述</label>
                        <div class="col-sm-10">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.auditingContent}">
                        </div>
                    </div>
                    </c:if>
		            <!-- <div class="form-group col-xs-12">
						<div class="col-sm-12">
							<div class="pull-right">
								<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/userReport/user_report_list.html'">
									<i class="icon-undo bigger-110"></i>返回
								</button>
							</div>
						</div>
					</div> -->
					<!-- </div> -->
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
<!-- 	</div>/.page-content -->
<!-- </div> -->
