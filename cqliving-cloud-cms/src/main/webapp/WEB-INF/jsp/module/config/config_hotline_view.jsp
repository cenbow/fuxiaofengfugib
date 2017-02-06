<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="热线列表|config_hotline_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="查看" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">所属APP</label>
	                        <div class="col-sm-9">
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.appName}">
	                        </div>
	                    </div>
	                    <c:if test="${not empty item.typeName}">
		                    <div class="form-group">
		                        <label class="col-sm-3 control-label no-padding-right">所属类型</label>
		                        <div class="col-sm-9">
		                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.typeName}">
		                        </div>
		                    </div>
	                    </c:if>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">热线名称</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.name}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">电话</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.phone}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">排序号</label>
	                        <div class="col-sm-9">
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.sortNo}">
	                        </div>
	                    </div>
			            <c:if test="${empty _model_ }">
			            <div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/config/config_hotline_list.html'">
										<i class="icon-undo bigger-110"></i>返回
									</button>
								</div>
							</div>
						</div>
						</c:if>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
<c:if test="${empty _model_ }">
	</div><!-- /.page-content -->
</div>
</c:if>