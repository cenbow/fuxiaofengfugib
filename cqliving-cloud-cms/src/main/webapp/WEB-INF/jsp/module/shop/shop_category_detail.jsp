<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"></jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1">
   		            <c:if test="${not empty item}">
		            	<input type="hidden" name="id" value="${item.id}" />
		            	<input type="hidden" name="appId" value="${item.appId}" />
		            </c:if>
               		<div class="col-md-12 col-lg-8">
			        	<c:choose>
	                   		<c:when test="${empty appList}">
	                   			<input type="hidden" name="appId" id="shop_category_appid" value="${appId}" />
	                   		</c:when>
	                    	<c:otherwise>
                    			<div class="form-group">
                   				 	<label class="col-sm-3 control-label no-padding-right" for="shop_category_appid">APP</label>
									<div class="col-sm-9">
		                            	<select class="chosen-select" data-placeholder="区县" name="appId" id="shop_category_appid">
			                                <c:forEach items="${appList}" var="obj">
			                                	<option value="${obj.id}"<c:if test="${param.search_EQ_appId eq obj.id}">selected="selected"</c:if>>${obj.name}</option>
			                                </c:forEach>
			                            </select>
	                        		</div>
                   				</div>
							</c:otherwise>
						</c:choose>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="typeId">所属类型</label>
	                        <div class="col-sm-9">
	                             <select name="typeId" id="typeId" class="form-control">
	                                <c:forEach items="${allTypes}" var="obj">
	                                	<option value="${obj.id}" appid="${obj.appId}" <c:if test="${item.typeId eq obj.id}">selected="selected"</c:if>>${obj.name}</option>
	                                </c:forEach>
	                            </select>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="name">分类名称</label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="name" name="name" maxlength="20" placeholder="请输入分类名称"  value="${item.name}">
	                        </div>
	                    </div>
	                	<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/shop_category/shop_category_list.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/shop_category/shop_category_list.html'">
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
	require(["/resource/business/shop/shopCategoryDetail.js"], function(obj) {
		obj.init();
	});
</script>