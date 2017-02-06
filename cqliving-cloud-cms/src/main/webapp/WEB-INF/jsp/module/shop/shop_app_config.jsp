<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="商情配置" name="_breadcrumbs_1"/>
	</jsp:include>
	
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<form class="form-horizontal" role="form" action="/module/shop/shop_app_config_save.html" id="myForm">
					<div class="col-md-12 col-lg-8">
						<c:if test="${not empty appInfos}">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">所属APP</label>
								<div class="col-sm-9">
									<select class="chosen-select" data-placeholder="区县" name="appId" id="appId">
		                                <c:forEach items="${appInfos}" var="obj">
		                                	<option value="${obj.id}" <c:if test="${appId eq obj.id}">selected="selected"</c:if>>${obj.name}</option>
		                                </c:forEach>
		                            </select>
								</div>
							</div>
						</c:if>
						<c:forEach items="${configMap}" var="obj">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">${obj.value.name}</label>
								<div class="col-sm-9 radio">
									<label class="radio-2">
		                                <input type="radio" class="ace" name="shopConfig_${obj.key}" value="0" <c:if test="${obj.value.value eq 0}">checked="checked"</c:if>><span class="lbl"> 否</span>
		                            </label>
		                            <label class="radio-2">
		                                <input type="radio" class="ace" name="shopConfig_${obj.key}" value="1" <c:if test="${obj.value.value eq 1}">checked="checked"</c:if>><span class="lbl"> 是</span>
		                            </label>
								</div>
							</div>
						</c:forEach>
						<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<cqliving-security2:hasPermission name="/module/shop/shop_app_config_save.html">
										<button class="btn btn-success btn-sm" type="button" id="submitBtn">
											<i class="icon-save bigger-110"></i>保存设置
										</button>
									</cqliving-security2:hasPermission>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	require(['/resource/business/shop/shopAppConfig.js'], function(obj) {
		obj.init();
	});
</script>