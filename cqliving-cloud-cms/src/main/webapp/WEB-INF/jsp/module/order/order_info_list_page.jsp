<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12">
	<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
		<div class="table-responsive">
			<table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
				<thead>
                    <tr>
                    	<th class="center">
							<label>
								<input type="checkbox" class="ace" />
								<span class="lbl"></span>
							</label>
						</th>
                		<th>订单号</th>
                		<th>用户</th>
                		<th>下单时间</th>
                		<th>订单金额(元)</th>
                		<th>订单状态</th>
                		<th>收货人</th>
                		<th>收货电话</th>
                		<th>收货地址</th>
                		<th>快递公司</th>
                		<th>快递单号</th>
                		<th>付款帐号</th>
                		<th>支付渠道</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td class="center" id="${item.id}">
							<label>
								<input type="checkbox" class="ace" id="${item.id}"/>
								<span class="lbl"></span>
							</label>
						</td>
                		<td>${item.orderNo}</td>
                		<td>${item.userName}</td>
                		<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    	<td>${myfn:moneyFormat(item.orderAmount)}</td>
                    	<td>
                        	<span class="label 
                        	<c:choose>
                    			<c:when test="${item.payforStatus eq 2 }">label-danger</c:when>
                    			<c:when test="${item.payforStatus eq 3 }">label-warning</c:when>
                    			<c:when test="${item.payforStatus eq 4 }">label-purple</c:when>
                    			<c:when test="${item.payforStatus eq 5 }">label-default</c:when>
                    			<c:when test="${item.payforStatus eq 6 }">label-success</c:when>
                    			<c:otherwise>label-info</c:otherwise>
                    		</c:choose>
                        	">${allPayforStatuss[item.payforStatus] }</span>
                        </td>
                    	<td>${item.receiverName}</td>
                    	<td>${item.receiverPhone}</td>
                		<td>${item.receiverAddress}</td>
                    	<td>${item.expressCompany}</td>
                    	<td>${item.expressNo}</td>
                    	<td>${item.payAccount}</td>
                        <td>
                        	<span class="label label-info">${allPayModes[item.payMode] }</span>
                        </td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/order_info/order_info_view.html">
								<a class="blue" href="javascript:void(0);" url="order_info_view.html?id=${item.id }&_model_=_model_" open-width="900" open-model="view" open-title="详情" data-rel="tooltip" data-original-title="查看" data-placement="top">
									<i class="icon-search bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
                            <c:if test="${item.payforStatus eq 2 }">
                        	<cqliving-security2:hasPermission name="/module/order_info/order_info_deliver_goods.html">
								<a class="blue deliverGoodsBtn" href="javascript:void(0);" url="/module/order_info/order_info_deliver_goods.html?id=${item.id }&_model_=_model_" open-title="发货" data-rel="tooltip" data-original-title="发货" data-placement="top">
									<i class="fa fa-truck bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
                            </c:if>
                            <c:if test="${item.payforStatus ne 6 && item.refundStatus eq 2 }">
                        	<cqliving-security2:hasPermission name="/module/order_info/order_info_refund.html">
                        		<a class="blue refundBtn" href="javascript:void(0);" url="/module/order_info/order_info_refund.html?id=${item.id }&_model_=_model_" open-title="退款详情" data-rel="tooltip" data-original-title="退款详情" data-placement="top">
									<i class="fa icon-ok bigger-130"></i>
								</a>
								
								<%-- 
								
								<a class="green refundBtn" href="javascript:void(0);" url="/module/order_info/order_info_refund.html?id=${item.id }&_model_=_model_" data-rel="tooltip" data-original-title="确认退款" data-placement="top" data-type="1">
									<i class="icon-ok bigger-130"></i>
								</a>
								<a class="red refundBtn" href="javascript:void(0);" url="/module/order_info/order_info_refund.html?id=${item.id }&_model_=_model_" data-rel="tooltip" data-original-title="拒绝退款" data-placement="top" data-type="2">
									<i class="icon-undo bigger-130"></i>
								</a> --%>
                            </cqliving-security2:hasPermission>
                            </c:if>
                            <c:if test="${item.payforStatus eq 1 }">
                        	<cqliving-security2:hasPermission name="/module/order_info/order_info_cancel.html">
								<a class="red cancelBtn" href="javascript:void(0);" url="/module/order_info/order_info_cancel.html?id=${item.id }" data-rel="tooltip" data-original-title="取消订单" data-placement="top">
									<i class="icon-ban-circle bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
                        	<cqliving-security2:hasPermission name="/module/order_info/order_info_update.html">
								<a class="blue" href="javascript:void(0);" url="/module/order_info/order_info_update.html?id=${item.id }&_model_=_model_" open-model="update" open-title="修改" data-rel="tooltip" data-original-title="修改订单" data-placement="top" data-backdrop="static">
									<i class="icon-pencil bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
                            </c:if>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="order_info_FormId" dataUrl="order_info_list.html" />
	</div>
</div>