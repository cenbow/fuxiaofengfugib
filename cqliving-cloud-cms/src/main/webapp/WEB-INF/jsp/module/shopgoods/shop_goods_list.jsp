<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="商品列表" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="shop_goods_list.html" id="shop_goods_FormId">
	                    <div class="special-list">
                        	<c:if test="${not empty appList}">
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
											<select name="search_EQ_appId" id="search_EQ_appId" class="form-control chosen-select">
				                                <option value="" >请选择所属APP</option>
												<c:forEach items="${appList}" var="app">
				                                  <option value="${app.id}" <c:if test="${param.search_EQ_appId eq app.id}">selected</c:if>>${app.name}</option>
				                               </c:forEach>
											 </select>
		                        		</div>
                    				</div>
								</div>
	                    	</c:if>
	                    	
	                    	<%-- 商品分类 --%>
                        	<div id="shopping-category-div">
	                		</div>
	                		
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
			                                <option value="">所有状态</option>
			                                <option value="1"<c:if test="${param.search_EQ_status eq '1'}"> selected="selected"</c:if>>保存</option>
			                                <option value="3"<c:if test="${param.search_EQ_status eq '3'}"> selected="selected"</c:if>>上线</option>
			                                <option value="88"<c:if test="${param.search_EQ_status eq '88'}"> selected="selected"</c:if>>下线</option>
			                            </select>
	                        		</div>
                				</div>
							</div>
							
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_isRecommemdIndex" id="search_EQ_isRecommemdIndex" class="form-control">
			                                <option value="">是否推荐到首页</option>
			                                <option value="1"<c:if test="${param.search_EQ_isRecommemdIndex eq '1'}"> selected="selected"</c:if>>是</option>
			                                <option value="0"<c:if test="${param.search_EQ_isRecommemdIndex eq '0'}"> selected="selected"</c:if>>否</option>
			                            </select>
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_isRecommendCarousel" id="search_EQ_isRecommendCarousel" class="form-control">
			                                <option value="">是否推荐到轮播</option>
			                                <option value="1"<c:if test="${param.search_EQ_isRecommendCarousel eq '1'}"> selected="selected"</c:if>>是</option>
			                                <option value="0"<c:if test="${param.search_EQ_isRecommendCarousel eq '0'}"> selected="selected"</c:if>>否</option>
			                            </select>
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="名称" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<c:if test="${empty appList}">
                        	<div class="col-xs-6 col-sm-3"></div>
                        	</c:if>
                        	<div class="col-xs-6 col-sm-3"></div>
                        	<div class="col-xs-6 col-sm-3"></div>
                        	<div class="col-xs-6 col-sm-3"></div>
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
						<div class="col-xs-12">							
						<div class="well">
						<cqliving-security2:hasPermission name="/module/shopgoods/shop_goods_add.html">
							<button class="btn btn-sm btn-success" type="button" url="/module/shopgoods/shop_goods_add.html"  forwordSaveParam="forwordSaveParam"><i class="icon-plus"></i>新增</button>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/shopgoods/on_line.html">
				        	<button class="btn btn-sm btn-primary on-out-batch" type="button" title="已发布的数据将自动过滤 " oper="1" url="/module/shopgoods/on_line.html"><i class="icon-arrow-up"></i>批量上线</button>
				        </cqliving-security2:hasPermission>
				        <cqliving-security2:hasPermission name="/module/shopgoods/out_line.html">
				        	<button class="btn btn-sm btn-danger on-out-batch" type="button" title="保存和已下线的数据将自动过滤 " oper="2" url="/module/shopgoods/out_line.html"><i class="icon-arrow-down"></i>批量下线</button>
				        </cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/shopgoods/shop_goods_delete_batch.html">
							<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/shopgoods/shop_goods_delete_batch.html"><i class="icon-trash"></i>批量删除</button>
						</cqliving-security2:hasPermission>
					</div>
					</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="shop_goods_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>


<script type="text/javascript">
var STATUS1 = ${STATUS1};
var STATUS3 = ${STATUS3} ; 
var STATUS88 = ${STATUS88} ;
require(['/resource/business/shopping/shop_goods_list.js'], function(list){
	list.init();
});
</script>