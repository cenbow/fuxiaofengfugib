<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="区域表列表|config_region_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" 
		        action="/module/config_region/<c:if test="${TYPE1 eq item.type}">wz</c:if><c:if test="${TYPE2 eq item.type}">shop</c:if><c:if test="${TYPE10 eq item.type}">tourism</c:if>_config_region_${empty item.id ? 'add' : 'update'}.html">
			        
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
		            	<input type="hidden" name="id" value="${item.id}" />
		            	<input type="hidden" name="type" value="${item.type}" />
		            	<c:if test="${empty appList}">
		            		<input type="hidden" name="appId" value="${item.appId}" />
		            	</c:if>
	                    <c:if test="${not empty appList}">
		                    <div class="form-group">
		                        <label class="col-sm-3 control-label no-padding-right" for="appId">所属APP</label>
		                        <div class="col-sm-9">
		                            <select name="appId" id="appId" class="form-control chosen-select">
									   <c:forEach items="${appList}" var="app">
		                                  <option value="${app.id}" <c:if test="${(item.appId eq app.id) or (empty item.appId and idx.first)}">selected</c:if>>${app.name}</option>
		                               </c:forEach>
									</select>
		                        </div>
		                    </div>
                    	</c:if>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="name">区域名称<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="name" name="name" maxlength="50" placeholder="请输入区域名称"  value="${item.name}">
	                        </div>
	                    </div>
	                    <c:if test="${TYPE2 eq item.type}">
	                    	<div id="shopTypeDiv"></div>
	                    </c:if>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="sortNo">排序号</label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="sortNo" id="sortNo" type="text" value="${maxSortNo eq item.sortNo ? '' : item.sortNo}" maxlength="10" placeholder="请输入排序号">
	                        </div>
	                    </div>
	                	<c:if test="${empty _model_ }">
						</c:if>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
<c:if test="${empty _model_ }">
	</div><!-- /.page-content -->
</div>
</c:if>
<input type="hidden" id="shopTypeIdhide" value="${item.shopTypeId}"/>
<script type="text/javascript">
var TYPE2 = ${TYPE2};
var type = ${item.type};
var shopTypeId = $("#shopTypeIdhide").val();
require(['/resource/business/config/config_region_detail.js'],function(list){
	list.init();
});
</script>