<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<!-- <div> -->
<%-- 	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"> --%>
<%-- 		<jsp:param value="标签管理|/module/lable/info_lable_list.html" name="_breadcrumbs_1"/> --%>
<%-- 		<jsp:param value="详情" name="_breadcrumbs_2"/> --%>
<%-- 	</jsp:include> --%>
<!-- 	<div class="page-content"> -->
		<div class="row">
			<div class="col-xs-12  version-view">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">客户端</label>
                        <div class="col-sm-10 ">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.appName}"/>
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">名称</label>
                        <div class="col-sm-10 ">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.name}"/>
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">业务类型</label>
                        <div class="col-sm-10 ">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<c:forEach items="${allSourceTypes}" var="type"><c:if test="${item.sourceType eq type.key}">${type.value}</c:if></c:forEach>"/>
                        </div>
                    </div>
		            <!-- <div class="form-group col-xs-12">
						<div class="pull-right">
							<button class="btn btn-sm btn-danger popup-cancel-btn" type="button">
								<i class="icon-remove bigger-110"></i>关闭
							</button>
						</div>
					</div> -->
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
<!-- 	</div>/.page-content -->
<!-- </div> -->
