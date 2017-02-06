<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="反馈配置管理|/module/user_feedback_config/to_save.html" name="_breadcrumbs_1"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
		        <form class="form-horizontal form" method="post" id="form1" action="/module/user_feedback_config/save.html">
	            	<input type="hidden" name="id" value="${item.id}" />
	            	<div class="col-md-12 col-lg-8">
		            	<c:if test="${not empty appList}">
		                    <div class="form-group">
		                        <label class="col-sm-3 control-label no-padding-right" for="appId">APP</label>
		                        <div class="col-sm-9">
		                            <select name="appId" id="appId" class="form-control">
									   <c:forEach items="${appList}" var="app">
		                                  <option value="${app.id}" <c:if test="${(appId eq app.id) or (empty appId and idx.first)}">selected</c:if>>${app.name}</option>
		                               </c:forEach>
									</select>
		                        </div>
		                    </div>
	                    </c:if>
		            	<c:if test="${empty appList}">
		            		<input type="hidden" id="appId" value="${appId}">
		            	</c:if>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="content">自动回复内容</label>
	                        <div class="col-sm-9">
	                            <textarea class="form-control" rows="5" cols="" id="content" name="content" placeholder="请输入自动回复内容" maxlength="500">${item.replyContent}</textarea>
	                        </div>
	                    </div>
	                	<div class="form-group col-xs-12">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/user_feedback_config/to_save.html">
										<i class="icon-save bigger-110"></i>提交
									</button>
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
require(['/resource/business/user/user_feedback_config.js'],function(config){
	config.init();
});
</script>