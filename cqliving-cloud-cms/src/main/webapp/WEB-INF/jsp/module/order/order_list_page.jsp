<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12">
	<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
		<div class="table-responsive">
			<table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
				<thead>
                    <tr>
                		<th>时间</th>
                		<th>收入</th>
                		<th>支付渠道</th>
                		<th>余额</th>
                		<th>订单号</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                		<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                		<td>${item.moneyExport}</td>
                		<td><span class="label label-info">${allPayModes[item.payMode]}</span></td>
                		<td>${item.totalMoneyExport}</td>
                    	<td>${item.orderNo}</td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="order_FormId" dataUrl="order_list.html" />
	</div>
</div>