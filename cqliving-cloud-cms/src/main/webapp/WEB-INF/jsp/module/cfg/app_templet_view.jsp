<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="栏目模板管理|/module/apptemplet/app_templet_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="查看" name="_breadcrumbs_3"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12  version-view">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
		        <div class="col-md-12 col-lg-8">
                    <c:if test="${not empty appList}">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="appId">客户端 </label>
                        <div class="col-sm-9">
							<c:forEach items="${appList}" var="app" varStatus="idx">
                            	<c:if test="${item.appId eq app.id}">
                            	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${app.name}"/>
                            	</c:if>
                            </c:forEach>
                        </div>
                    </div>
                    </c:if>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">模板类型 </label>
                        <div class="col-sm-9">
							<c:forEach items="${allTypes}" var="obj" varStatus="idx">
                                <c:if test="${obj.key eq item.type}"><input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${obj.value}"/></c:if>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">模板名称</label>
                        <div class="col-sm-9">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.name}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">模板CODE</label>
                        <div class="col-sm-9">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.templetCode}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">模板图片</label>
                        <div class="col-sm-9">
                        	 <span class="ace-thumbnails">
								<a href="${item.imageUrl}" data-rel="colorbox">
	                            	<img alt="模板图片" style="width:150px;height:150px;" src="${item.imageUrl}">
	                            </a>
							</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right">模板描述</label>
                        <div class="col-sm-9">
                        	<textarea disabled="disabled" class="col-xs-10 form-control col-sm-5" >${item.templetDesc}</textarea>
                        </div>
                    </div>
		            <div class="form-group">
						<div class=" col-sm-12">
							<div class="pull-right">
								<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/apptemplet/app_templet_list.html'">
									<i class="icon-undo bigger-110"></i>返回
								</button>
							</div>
						</div>
					</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
<script type="text/javascript">
require(['common_colorbox'], function(colorbox) {
	//1、图片查看
	colorbox.init();
});
</script>