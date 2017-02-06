<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="商铺分类管理" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="shop_category_list.html" id="shop_category_FormId">
		                    <div class="special-list">
		                    	<c:choose>
		                    		<c:when test="${empty appList}">
		                    			<input type="hidden" name="search_EQ_appId" id="shop_category_appid" value="${search_EQ_appId}" />
		                    		</c:when>
			                    	<c:otherwise>
				                    	<div class="col-xs-6 col-sm-3">
											<div class="form-group">
												<div class="col-sm-12">
					                            	<select class="chosen-select" data-placeholder="区县" name="search_EQ_appId" id="shop_category_appid">
						                                <c:forEach items="${appList}" var="obj">
						                                	<option value="${obj.id}"<c:if test="${param.search_EQ_appId eq obj.id}">selected="selected"</c:if>>${obj.name}</option>
						                                </c:forEach>
						                            </select>
				                        		</div>
		                    				</div>
										</div>
									</c:otherwise>
								</c:choose>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            <select name="search_EQ_typeId" id="search_EQ_typeId" class="form-control">
				                                <option value="">所有类型</option>
				                                <c:forEach items="${allTypes}" var="obj">
				                                	<option value="${obj.id}" appid="${obj.appId}" <c:if test="${param.search_EQ_typeId eq obj.id}">selected="selected"</c:if>>${obj.name}</option>
				                                </c:forEach>
				                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="分类名称" class="col-xs-12 form-control" />
		                        		</div>
                    				</div>
								</div>
								<div class="col-xs-6 col-sm-3"></div>
								<c:if test="${empty appList}">
									<div class="col-xs-6 col-sm-3"></div>
								</c:if>
	                    		<div class="col-xs-6 col-sm-3 btn-search">
									<div class="form-group">
										<div class="col-sm-12">
											<button class="btn btn-primary btn-sm" type="button" id="searchButton">
												<i class="icon-search bigger-110"></i>查询
											</button>
											<button class="btn btn-sm" type="button" id="resetButton" notinclude="select[name=search_EQ_appId]">
												<i class="icon-undo bigger-110"></i>重置
											</button>
										</div>
									</div>
								</div>
	                    	</div>
							<div class="col-md-12">							
								<div class="well">
									<cqliving-security2:hasPermission name="/module/shop_category/shop_category_add.html">
										<button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='/module/shop_category/shop_category_add.html'"><i class="icon-plus"></i>新增</button>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/shop_category/shop_category_delete_batch.html">
										<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/shop_category/shop_category_delete_batch.html"><i class="icon-trash"></i>批量删除</button>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/shop_category/clear_order_batch.html">
										<button class="btn btn-sm btn-danger" type="button" id="clear_sort_no_btn" style="display: none;"><i class="icon-remove"></i>清空排序</button>
									</cqliving-security2:hasPermission>
								</div>
							</div>
		                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="shop_category_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
<script type="text/javascript">
	require(["/resource/business/shop/shopCategoryList.js"], function(obj) {
		obj.init();
		$('body').tooltip({selector:'[data-rel=tooltip]'});
	});
</script>