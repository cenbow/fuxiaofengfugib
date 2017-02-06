<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!-- <div> -->
<%-- 	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"> --%>
<%-- 		<jsp:param value="标签管理|/module/lable/info_lable_list.html" name="_breadcrumbs_1"/> --%>
<%-- 		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/> --%>
<%-- 	</jsp:include> --%>
<!-- 	<div class="page-content"> -->
		<div class="row">
			<div class="col-xs-12 version-detail fieldset-form">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="${action}">
		            <c:if test="${not empty item}">
		            	<input type="hidden" name="id" value="${item.id}" />
		            </c:if>
                    <c:if test="${not empty appList}">
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="appId">客户端</label>
                        <div class="col-sm-10">
                            <select name="appId" id="appId" class="form-control chosen-select">
								<c:forEach items="${appList}" var="app" varStatus="idx">
                                  <option value="${app.id}" <c:if test="${(item.appId eq app.id) or (empty item.appId and idx.first)}">selected</c:if>
                                  >${app.name}</option>
                               </c:forEach>
							 </select>
                        </div>
                    </div>
                   	</c:if>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="name">名称</label>
                            <div class="col-sm-10">
                            <input type="text" class="form-control" id="name" name="name" maxlength="100" placeholder="请输入名称"  value="${item.name}">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="sourceType">业务类型</label>
                        <div class="col-sm-10">
                            <select name="sourceType" id="sourceType" class="form-control">
								<c:forEach items="${allSourceTypes}" var="type" varStatus="idx">
                                  <option value="${type.key}" <c:if test="${(item.sourceType eq type.key) or (empty item.sourceType and idx.first)}">selected</c:if>
                                  >${type.value}</option>
                               </c:forEach>
							 </select>
                        </div>
                    </div>
                	<!-- <div class="form-group col-xs-12">
						<div class=" text-right">
							<button class="btn btn-success btn-sm" type="button" id="saveBtn" back_url="/module/lable/info_lable_list.html">
								<i class="icon-save bigger-110"></i>提交
							</button>
							&nbsp;
							<button class="btn btn-sm btn-default popup-cancel-btn" type="button">
								<i class="icon-undo bigger-110"></i>取消
							</button>
						</div>
					</div> -->
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
<!-- 	</div>/.page-content -->
<!-- </div> -->

<script type="text/javascript">
require(['/resource/business/info/info_lable_detail.js'],function(lable){
	lable.init();
});
</script>