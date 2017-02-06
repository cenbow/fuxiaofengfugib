<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="商情管理" name="_breadcrumbs_1"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="shop_info_list.html" id="shop_info_FormId">
						<!-- 防止 get 方式重新提交产生乱码 -->
						<input type="text" style="display: none;" />	
	                    <div class="special-list">
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
			                            	<input type="text" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="店铺名称" class="col-xs-12 form-control" />
	                        		</div>
                   				</div>
							</div>
							<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
		                            	<select name="search_EQ_typeId" id="search_EQ_typeId" class="form-control">
			                                <option value="">所有类型</option>
			                                <c:forEach items="${allTypes}" var="obj">
			                                	<option value="${obj.id}" <c:if test="${param.search_EQ_typeId eq obj.id}">selected="selected"</c:if>>${obj.name}</option>
			                                </c:forEach>
			                            </select>
	                        		</div>
                   				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
		                            	<select name="search_EQ_categoryId" id="search_EQ_categoryId" class="form-control">
			                                <option value="">所有分类</option>
<%-- 				                                <c:forEach items="${allCategories}" var="obj"> --%>
<%-- 				                                	<option value="${obj.id}" <c:if test="${param.search_EQ_categoryId eq obj.id}">selected="selected"</c:if>>${obj.name}</option> --%>
<%-- 				                                </c:forEach> --%>
			                            </select>
	                        		</div>
                   				</div>
							</div>
							<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
		                            	<select name="search_EQ_regionCode" id="search_EQ_regionCode" class="form-control">
			                                <option value="">所有区域</option>
<%-- 				                                <c:forEach items="${allRegion}" var="obj"> --%>
<%-- 				                                	<option value="${obj.code}" <c:if test="${param.search_EQ_regionCode eq obj.code}">selected="selected"</c:if>>${obj.name}</option> --%>
<%-- 				                                </c:forEach> --%>
			                            </select>
	                        		</div>
                   				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
		                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
			                                <option value="">所有状态</option>
		                                	<option value="-1"<c:if test="${param.search_EQ_status eq '-1'}"> selected="selected"</c:if>>审核未通过</option>
		                                	<option value="1"<c:if test="${param.search_EQ_status eq '1'}"> selected="selected"</c:if>>保存(待审核)</option>
		                                	<option value="3"<c:if test="${param.search_EQ_status eq '3'}"> selected="selected"</c:if>>已上线</option>
		                                	<option value="88"<c:if test="${param.search_EQ_status eq '88'}"> selected="selected"</c:if>>下线</option>
			                            </select>
	                        		</div>
                   				</div>
							</div>
                    		<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
		                            	<select name="search_EQ_sourceType" id="search_EQ_sourceType" class="form-control">
			                                <option value="">所有来源</option>
			                                <c:forEach var="it" items="${allSourceTypes }">
			                                	<option value="${it.key }"<c:if test="${param.search_EQ_sourceType eq it.key}"> selected="selected"</c:if>>${it.value }</option>
			                                </c:forEach>
			                            </select>
	                        		</div>
                   				</div>
							</div>
                    		<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
		                            	<input type="text" id="search_LIKE_createPhone" name="search_LIKE_createPhone" value="${param.search_LIKE_createPhone }" placeholder="用户手机号" class="col-xs-12 form-control" />
	                        		</div>
                   				</div>
							</div>
                    		<div class="col-xs-6 col-sm-3 btn-search pull-right">
								<div class="form-group">
									<div class="col-sm-12">
										<button class="btn btn-primary btn-sm" type="button" id="searchButton">
											<i class="icon-search bigger-110"></i>查询
										</button>
										<button class="btn btn-sm" type="button" id="resetButton">
											<i class="icon-undo bigger-110"></i>重置
										</button>
									</div>
								</div>
							</div>
                    	</div>	
                    	<div class="col-sm-12">					
							<div class="well">
								<cqliving-security2:hasPermission name="/module/shop/shop_info_add.html">
									<button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='/module/shop/shop_info_add.html'"><i class="icon-plus"></i>新增</button>
								</cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/shop/shop_info_offline_batch.html">
									<button class="btn btn-sm btn-danger" type="button" id="offlineBatchButton"><i class="icon-arrow-down bigger-130"></i>批量下线</button>
								</cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/shop/shop_info_delete_batch.html">
									<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/shop/shop_info_delete_batch.html"><i class="icon-trash"></i>批量删除</button>
								</cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/shop/shop_info_audit.html">
									<button class="btn btn-sm btn-primary" type="button" id="auditBatchButton" url="/module/shop/shop_info_audit.html"><i class="icon-edit"></i>批量审核</button>
								</cqliving-security2:hasPermission>
							</div>
						</div>	
                	</form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="shop_info_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
<div class="none">
	<div id="category_option">
		<c:forEach items="${allCategories}" var="obj">
       		<option value="${obj.id}" typeid="${obj.typeId}">${obj.name}</option>
       	</c:forEach>
      	</div>
      	<div id="region_option">
       	<c:forEach items="${allRegion}" var="obj">
       		<option value="${obj.code}" typeid="${obj.shopTypeId}">${obj.name}</option>
       	</c:forEach>
      	</div>
</div>

<script type="text/javascript">
	require(["/resource/business/shop/shopInfoList.js"], function(obj) {
		obj.init();
		$('body').tooltip({selector:'[data-rel=tooltip]'});
	});
</script>
<style>
	.none {display: none;} 
</style>
