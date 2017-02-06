<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="订单列表" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="order_info_list.html" id="order_info_FormId">
	                    <div class="special-list">
	                    <c:if test="${fn:length(appList) > 1 }">
							<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
										<select name="search_EQ_appId" id="search_EQ_appId" class="form-control chosen-select" data-placeholder="请选择所属APP">
											<c:forEach items="${appList}" var="it">
												<option value="${it.id }" <c:if test="${param.search_EQ_appId eq it.id}">selected</c:if>>${it.name}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</c:if>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_payforStatus" id="search_EQ_payforStatus" class="form-control">
			                                <option value="">所有状态</option>
			                                <option value="1"<c:if test="${param.search_EQ_payforStatus eq '1'}"> selected="selected"</c:if>>待支付</option>
			                                <option value="2"<c:if test="${param.search_EQ_payforStatus eq '2'}"> selected="selected"</c:if>>待发货</option>
			                                <option value="3"<c:if test="${param.search_EQ_payforStatus eq '3'}"> selected="selected"</c:if>>待收货</option>
			                                <option value="4"<c:if test="${param.search_EQ_payforStatus eq '4'}"> selected="selected"</c:if>>待评价</option>
			                                <option value="5"<c:if test="${param.search_EQ_payforStatus eq '5'}"> selected="selected"</c:if>>已取消</option>
			                                <option value="6"<c:if test="${param.search_EQ_payforStatus eq '6'}"> selected="selected"</c:if>>已完成</option>
			                            </select>
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <div class="input-group">
				                            <input name="search_GTE_createTime" type="hidden" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd" />">
				                            <input name="search_LT_createTime" type="hidden" value="<fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd" />">
				                            <input type="text" id="createTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_createTime}"> 至 </c:if><fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd" />" placeholder="订单创建时间"  class="form-control">
				                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
				                        </div>
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_orderNo" name="search_LIKE_orderNo" value="${param.search_LIKE_orderNo }" placeholder="订单号" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_expressCompany" name="search_LIKE_expressCompany" value="${param.search_LIKE_expressCompany }" placeholder="快递公司" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_expressNo" name="search_LIKE_expressNo" value="${param.search_LIKE_expressNo }" placeholder="快递单号" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_userName" name="search_LIKE_userName" value="${param.search_LIKE_userName }" placeholder="购买人姓名" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_userPhone" name="search_LIKE_userPhone" value="${param.search_LIKE_userPhone }" placeholder="购买人手机号码" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_receiverAddress" name="search_LIKE_receiverAddress" value="${param.search_LIKE_receiverAddress }" placeholder="收货人地址" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_receiverName" name="search_LIKE_receiverName" value="${param.search_LIKE_receiverName }" placeholder="收货人姓名" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_receiverPhone" name="search_LIKE_receiverPhone" value="${param.search_LIKE_receiverPhone }" placeholder="收货人电话" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_payMode" id="search_EQ_payMode" class="form-control">
			                                <option value="">所有支付渠道</option>
			                                <option value="1"<c:if test="${param.search_EQ_payMode eq '1'}"> selected="selected"</c:if>>支付宝</option>
			                            </select>
	                        		</div>
                				</div>
							</div>
                    		<div class="col-xs-6 col-sm-3 btn-search pull-right">
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
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="order_info_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
<script type="text/javascript">
require(['/resource/business/order/order_info_list.js', '/resource/business/order/deliverGoods.js'], function(obj, deliverGoods){
	obj.init();
	deliverGoods.init();//发货
});
</script>