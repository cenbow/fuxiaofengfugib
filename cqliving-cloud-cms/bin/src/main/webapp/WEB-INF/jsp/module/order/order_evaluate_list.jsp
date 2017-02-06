<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="评论审核管理" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="order_evaluate_list.html" id="order_evaluate_FormId">
	                    <div class="special-list">
                        	<c:if test="${not empty appList}">
		                    	<div class="form-list">
		                        	<div class="col-xs-6 col-sm-3">
										<div class="form-group">
											<div class="col-sm-12">
												<select name="search_EQ_appId" id="search_EQ_appId" class="form-control chosen-select">
													<c:forEach items="${appList}" var="app">
					                                  <option value="${app.id}" <c:if test="${(param.search_EQ_appId eq app.id)or (empty param.search_EQ_appId and idx.first)}">selected</c:if>>${app.name}</option>
					                               </c:forEach>
												 </select>
			                        		</div>
	                    				</div>
									</div>
		                    	</div>
	                    	</c:if>
	                    	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
			                                <option value="">所有</option>
			                                <option value="-1"<c:if test="${param.search_EQ_status eq '-1'}"> selected="selected"</c:if>>审核不通过</option>
			                                <option value="1"<c:if test="${param.search_EQ_status eq '1'}"> selected="selected"</c:if>>保存</option>
			                                <option value="3"<c:if test="${param.search_EQ_status eq '3'}"> selected="selected"</c:if>>审核通过</option>
			                            </select>
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_shoppingGoodsName" name="search_LIKE_shoppingGoodsName" value="${param.search_LIKE_shoppingGoodsName}" placeholder="商品" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_userId" name="search_LIKE_userName" value="${param.search_LIKE_userName }" placeholder="用户" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_content" name="search_LIKE_content" value="${param.search_LIKE_content}" placeholder="内容" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
							
							<div class="col-xs-6 col-sm-3"></div>
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
								<cqliving-security2:hasPermission name="/module/order_evaluate/order_evaluate_delete_batch.html">
									<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/order_evaluate/order_evaluate_delete_batch.html"><i class="icon-trash"></i>批量删除</button>
								</cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/order_evaluate/auditing.html">
									<button title="已审核过的数据将被忽略" class="btn btn-sm btn-success auditing-btn" url="/module/order_evaluate/auditing.html" type="button" noAuditing="${STATUS1}" ><i class="icon-edit"></i>审核</button>
									<a style="display: none;" class="btn btn-sm btn-success auditing-btn-a" href="#modal-form" role="button" data-toggle="modal" url="/module/order_evaluate/auditing.html">
										<i class="align-top bigger-125"></i>审核
									</a>
								</cqliving-security2:hasPermission>
							</div>
						</div>
	                </form>
				</div>
				<div class="row" id="table_content_page">
					<jsp:include page="order_evaluate_list_page.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="modal-form" class="modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">评论审核</h4>
			</div>
			<div class="modal-body overflow-visible">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<div class="form-group col-xs-12">
	                        <div class="col-sm-12">
	                        	<textarea class="form-control limited" rows="3" name="auditingContent" id="auditingContent" maxlength="255" placeholder="请输入审核描述"></textarea>
	                        </div>
                    	</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-sm btn-success btn-auditing" status="${STATUS3}">
					<i class="icon-ok"></i>
					审核通过
				</button>
				<button class="btn btn-sm btn-warning btn-auditing" status="${STATUS_1}">
					<i class="icon-ok icon-remove"></i>
					审核不通过
				</button>
				<button class="btn btn-sm btn-danger" data-dismiss="modal">
					<i class="icon-remove"></i>
					取消
				</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
require(['/resource/business/order/order_evaluate_list.js'], function(list){
	list.init();
});
</script>