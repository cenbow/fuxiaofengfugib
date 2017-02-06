<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="订单列表|order_info_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="查看" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <!-- <form class="form-horizontal form"> -->
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
						<div class="profile-user-info profile-user-info-striped">
							<div class="profile-info-row">
								<div class="profile-info-name"> 订单状态 </div>
								<div class="profile-info-value">
									<span class="editable" id="username">${allPayforStatuss[item.payforStatus]}</span>
								</div>
							</div>
							<div class="hr"></div>
							<fieldset>
								<legend class="center">收货人信息</legend>
								<div class="profile-info-row">
									<div class="profile-info-name"> 收货人姓名 </div>
									<div class="profile-info-value">
										<span class="editable">${item.receiverName}</span>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name"> 收货人电话 </div>
									<div class="profile-info-value">
										<span class="editable">${item.receiverPhone}</span>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name"> 收货人地址 </div>
									<div class="profile-info-value">
										<i class="icon-map-marker light-orange bigger-110"></i>
										<span class="editable">${item.receiverAddress}</span>
									</div>
								</div>
							</fieldset>
							<div class="hr"></div>
							<fieldset>
								<legend class="center">物流信息</legend>
								<c:if test="${empty item.expressNo }">
									<div class="profile-info-row">
										<div class="profile-info-name"></div>
										<div class="profile-info-value">
											<span class="editable">还未发货</span>
											<c:if test="${item.payforStatus eq 2 }">
												<span class="editable editable-click deliverGoodsBtn" url="/module/order_info/order_info_deliver_goods.html?id=${item.id }&_model_=_model_">点击去发货</span>
											</c:if>
										</div>
									</div>
								</c:if>
								<c:if test="${not empty item.expressNo }">
									<div class="profile-info-row">
										<div class="profile-info-name"> 快递单号 </div>
										<div class="profile-info-value">
											<span class="editable">&nbsp;${item.expressNo}</span>
										</div>
									</div>
									<div class="profile-info-row">
										<div class="profile-info-name"> 快递公司</div>
										<div class="profile-info-value">
											<span class="editable">&nbsp;${item.expressCompany}</span>
										</div>
									</div>
								</c:if>
							</fieldset>
							<div class="hr"></div>
							<fieldset>
								<legend class="center">订单详情</legend>
								<div class="row">
									<div class="col-xs-12">
										<table class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>商品名称</th>
													<th>商品单价</th>
													<th>商品数量</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${goodsList }" var="it">
													<tr>
														<td>${it.goodsName }</td>
														<td>${it.goodsPrice }</td>
														<td>${it.quantity }</td>
														<td>
															<c:if test="${it.refundStatus eq 2 }">
																<cqliving-security2:hasPermission name="/module/order_info/order_info_refund.html">
																	<a class="green refundBtn" href="javascript:void(0);" url="/module/order_info/order_info_refund.html?id=${item.id }&goodsId=${it.goodsId}" data-rel="tooltip" data-original-title="确认退款" data-placement="top" data-type="1">
																		<i class="icon-ok bigger-130"></i>
																	</a>
																	<a class="red refundBtn" href="javascript:void(0);" url="/module/order_info/order_info_refund.html?id=${item.id }&goodsId=${it.goodsId}" data-rel="tooltip" data-original-title="拒绝退款" data-placement="top" data-type="2">
																		<i class="icon-undo bigger-130"></i>
																	</a>
									                            </cqliving-security2:hasPermission>
								                            </c:if>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name"> 订单编号 </div>
									<div class="profile-info-value">
										<span class="editable">${item.orderNo }</span>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name"> 运费 </div>
									<div class="profile-info-value">
										<span class="editable">${shippingFare }</span>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name"> 订单总额 </div>
									<div class="profile-info-value">
										<span class="editable">${totalMoney }</span>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name"> 下单时间 </div>
									<div class="profile-info-value">
										<span class="editable"><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
									</div>
								</div>
								<c:if test="${not empty payTime}">
								<div class="profile-info-row">
									<div class="profile-info-name"> 支付时间 </div>
									<div class="profile-info-value">
										<span class="editable">${payTime }</span>
									</div>
								</div>
								</c:if>
								<c:if test="${not empty deliverGoodsTime}">
									<div class="profile-info-row">
										<div class="profile-info-name"> 发货时间 </div>
										<div class="profile-info-value">
											<span class="editable">${deliverGoodsTime }</span>
										</div>
									</div>
								</c:if>
								<div class="profile-info-row">
									<div class="profile-info-name"> 订单备注 </div>
									<div class="profile-info-value">
										<span class="editable">
											<c:if test="${empty item.descn}">&nbsp;</c:if>
											${item.descn }
										</span>
									</div>
								</div>
							</fieldset>
						</div>
			            <c:if test="${empty _model_ }">
			            <div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/order_info/order_info_list.html'">
										<i class="icon-undo bigger-110"></i>返回
									</button>
								</div>
							</div>
						</div>
						</c:if>
					</div>
		        <!-- </form> -->
			</div>
		</div><!-- /.row --><!-- /.main-content -->
<c:if test="${empty _model_ }">
	</div><!-- /.page-content -->
</div>
</c:if>
