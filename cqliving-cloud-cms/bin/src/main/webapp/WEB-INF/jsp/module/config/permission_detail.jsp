<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
	  <jsp:param value="APP资源管理|/module/config/permission_list.html" name="_breadcrumbs_1"/>
	  <jsp:param value="修改" name="_breadcrumbs_3"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
		        <form class="form-horizontal form" method="post" id="form1" action="permission_update.html">
		        	<%-- appId --%>
		            <input type="hidden" name="id" value="${id}" />
                       <div class="form-group">
							<label class="col-xs-12 col-sm-3 col-md-2 control-label">资源授权</label>
							<div class="col-sm-9">
								<div class="tabbable">
									<ul class="nav nav-tabs" id="myTab">
										<c:forEach items="${types }" var="type" varStatus="vs">
										   <li resTypeId="${type.id}">
											<a data-toggle="tab" href="#tab_pane_resc_${type.id}">
												${type.name}
											</a>
										   </li>
										</c:forEach>
									</ul>
									<div class="tab-content">
										<!-- 资源内容 -->
										<c:forEach items="${types }" var="type" varStatus="typeIdx">
										   <div id="tab_pane_resc_${type.id}" class="tab-pane">
										   		<c:forEach items="${type.permissions}" var="permission" varStatus="idx">
											   		<div id="parent_tmp">
													   <div class="widget-box no-margin no-padding checkbox">
															<div class="widget-header widget-header-flat">
																<h4 class="smaller">
																	<label class="checkbox-2">
																		<input type="checkbox" value="${ISPERMISSION1}" class="ace" name="types[${typeIdx.index}].permissions[${idx.index}].isPermission" defaultName="resIds"<c:if test="${not empty permission.isPermission and permission.isPermission==ISPERMISSION1}"> checked="checked" </c:if> />
																		<span class="lbl">${permission.name}</span>
																		<%-- 前端资源id --%>
																		<input type="hidden" value="${permission.id}" name="types[${typeIdx.index}].permissions[${idx.index}].id"/>
																	</label>
																	<label class="checkbox-2">
																		<input type="checkbox" class="ace" name="checked_all"/><span class="lbl">全选</span>
																	</label>
																</h4>
															</div>
															<div id="child_temp">
															   <div class="widget-body">
																	<div class="widget-main">
																	<input type="hidden" class="permissionName" value="${permission.name}"/>
																	<label class="checkbox-2">
																	<input type="checkbox" value="${ISLOGIN1}" class="ace" name="types[${typeIdx.index}].permissions[${idx.index}].isLogin" <c:if test="${not empty permission.isLogin and permission.isLogin==ISLOGIN1}"> checked="checked" </c:if> /> <span class="lbl">必须登录</span>
																	</label>
																	<label class="checkbox-2">
																	<input type="checkbox" value="${ISSIGN1}" class="ace" name="types[${typeIdx.index}].permissions[${idx.index}].isSign" <c:if test="${not empty permission.isSign and permission.isSign==ISSIGN1}"> checked="checked" </c:if> /> <span class="lbl">签名验证</span>
																	</label>
																	<label class="checkbox-2">
																	<input type="checkbox" value="${ISSESSIONID1}" class="ace" name="types[${typeIdx.index}].permissions[${idx.index}].isSessionId" <c:if test="${not empty permission.isSessionId and permission.isSessionId==ISSESSIONID1}"> checked="checked" </c:if> /><span class="lbl">验证sessionId</span>
																	</label>
																	<label class="checkbox-2">
																	<input type="checkbox" value="${ISREQUESTTIMES1}" class="ace" name="types[${typeIdx.index}].permissions[${idx.index}].isRequestTimes" <c:if test="${not empty permission.isRequestTimes and permission.isRequestTimes==ISREQUESTTIMES1}"> checked="checked" </c:if> /><span class="lbl">控制请求次数</span>
																	</label>
																	<input type="text" class="requestTimesInterval" name="types[${typeIdx.index}].permissions[${idx.index}].requestTimesInterval" value="${permission.requestTimesInterval}" maxlength="10" placeholder="请求间隔分钟数"/>
																	<input type="text" class="requestTimesLimit" name="types[${typeIdx.index}].permissions[${idx.index}].requestTimesLimit" value="${permission.requestTimesLimit}" maxlength="10" placeholder="请求限制次数"/>
																	</div>
																</div>
															</div>
															<div class="space-6">
															</div>
														</div>
													</div>
										   		</c:forEach>
										   </div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
		            <div class="form-group">
						<div class="col-sm-11">
							<div class="pull-right">
								<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/config/permission_list.html">
									<i class="icon-save bigger-110"></i>提交
								</button>
								&nbsp;
								<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/config/permission_list.html'">
									<i class="icon-undo bigger-110"></i>返回
								</button>
							</div>
						</div>
					</div>
		        </form>
		    </div>
		</div>
	</div>
</div>


<script type="text/javascript">
require(['/resource/business/config/permission_detail.js'], function(detail){
	detail.init();
});
</script>

