<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="商铺类型管理|shop_type_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1">
		            <c:if test="${not empty item}">
		            	<input type="hidden" name="id" value="${item.id}" />
		            	<input type="hidden" name="appId" value="${item.appId}" />
		            </c:if>
		        	<div class="col-md-12 col-lg-8">
	                    <c:choose>
	                   		<c:when test="${empty appList}">
	                   			<input type="hidden" name="appId" id="shop_type_appid" value="${appId}" />
	                   		</c:when>
	                    	<c:otherwise>
	                   			<div class="form-group">
	               				 	<label class="col-sm-3 control-label no-padding-right" for="shop_type_appid">APP</label>
									<div class="col-sm-9">
		                            	<select class="chosen-select" data-placeholder="区县" name="appId" id="shop_type_appid">
			                                <c:forEach items="${appList}" var="obj">
			                                	<option value="${obj.id}"<c:if test="${param.search_EQ_appId eq obj.id}">selected="selected"</c:if>>${obj.name}</option>
			                                </c:forEach>
			                            </select>
	                        		</div>
	                 			</div>
							</c:otherwise>
						</c:choose>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="app_columns_id">栏目<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <select class="form-control select-bigger" name="appColumnsId" id="app_columns_id">
	                            	<c:forEach items="${columns}" var="obj">
	                            		<option value="${obj.id}" appid="${obj.appId}" <c:if test="${obj.id eq item.appColumnsId}">selected="selected"</c:if>>${obj.name}</option>
	                            	</c:forEach>
	                            </select>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="name">类型名称<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="name" name="name" maxlength="100" placeholder="请输入类型名称"  value="${item.name}">
	                        </div>
	                    </div>
<!-- 	                    <div class="form-group"> -->
<!-- 	                        <label class="col-sm-3 control-label no-padding-right" for="code">CODE<i class="text-danger">*</i></label> -->
<!-- 	                            <div class="col-sm-9"> -->
<%-- 	                            <input type="text" class="form-control" id="code" name="code" maxlength="20" placeholder="请输入CODE"  value="${item.code}"> --%>
<!-- 	                        </div> -->
<!-- 	                    </div> -->
	                	<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-success btn-sm draft_save" type="button" id="commonSaveButton" back_url="/module/shop_type/shop_type_list.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/shop_type/shop_type_list.html'">
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
	require(["/resource/business/shop/shopTypeDetail.js"], function(obj) {
		obj.init();
	});
</script>