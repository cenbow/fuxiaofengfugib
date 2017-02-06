<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="row">
	<div class="col-xs-12">
		<%-- 详细 --%>
        <form class="form-horizontal form" method="post" id="form1" action="/module/order_info/order_info_refund.html">
        	<c:forEach items="${orderRefunds }" var="item">
       			<input type="hidden" name="ids" value="${item.id }">
       			<input type="hidden" name="type" value="1">
       			<div class='col-md-12'>
	                 <div class="form-group">
	                     <label class="col-sm-3 control-label no-padding-right">商品名称</label>
	                     <div class="col-sm-9">
	                   		<div class="col-sm-10">
	                       		<input class="form-control" readonly="readonly" value="${item.goodsName }">
	                   		</div>
	                     </div>
	                 </div>
	                 <div class="form-group">
	                     <label class="col-sm-3 control-label no-padding-right">退款金额</label>
	                     <div class="col-sm-9">
	                   		<div class="col-sm-10">
	                       		<input class="form-control" readonly="readonly" value="" maxlength="19">
	                   		</div>
	                     </div>
	                 </div>
	                 <div class="form-group">
	                     <label class="col-sm-3 control-label no-padding-right">退款帐号</label>
	                     <div class="col-sm-9">
	                   		<div class="col-sm-10">
	                       		<input class="form-control" readonly="readonly" value="${item.userPayAccount }">
	                   		</div>
	                     </div>
	                 </div>
	                 <div class="form-group">
	                     <label class="col-sm-3 control-label no-padding-right">退款原因</label>
	                     <div class="col-sm-9">
	                   		<div class="col-sm-10">
	                       		<input class="form-control" readonly="readonly" value="${item.refundReason }">
	                   		</div>
	                     </div>
	                 </div>
	                 <div class="form-group">
	                     <label class="col-sm-3 control-label no-padding-right">拒绝原因</label>
	                     <div class="col-sm-9">
	                   		<div class="col-sm-10">
	                       		<textarea name="refuseReasons" class="form-control">${item.refuseReason }</textarea>
	                   		</div>
	                     </div>
	                 </div>
					<div class="hr"></div>
				</div>
        	</c:forEach>
        </form>
	</div>
</div><!-- /.row --><!-- /.main-content -->
<script type="text/javascript">
var imageUrl = '${imageUrl}';
require(['/resource/business/order/order_info_detail.js'], function(obj){
	obj.init();
});
</script>