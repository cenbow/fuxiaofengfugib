<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="资讯轮播图配置|info_slider_config_add.html" name="_breadcrumbs_1"/>
		<jsp:param value="编辑" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/info_slider_config/info_slider_config_add.html">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
		        		<c:if test="${not empty appList}">
				            <div class="form-group">
		                        <label class="col-sm-3 control-label no-padding-right" for="appId">所属APP</label>
		                        <div class="col-sm-9">
		                            <select name="appId" id="appId" class="form-control chosen-select">
		                               <c:forEach items="${appList}" var="app" varStatus="idx">
		                                  <option value="${app.id}" <c:if test="${(appId eq app.id) or (empty appId and idx.first)}">selected</c:if>>${app.name}</option>
		                               </c:forEach>
		                            </select>
		                        </div>
		                    </div>
	                    </c:if>
	                    <div id="info-div">
	                    </div>
	                	<c:if test="${empty _model_ }">
	                	<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-success btn-sm draft_save" type="button" id="saveBtn">
										<i class="icon-save bigger-110"></i>保存
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

<script type="text/javascript">

require(['/resource/business/info/info_slider_config_detail.js'], function(detail){
	detail.init();
});
</script>