<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="row">
	<div class="col-xs-12">
		<%-- 详细 --%>
        <form class="form-horizontal form" method="post" id="form1" action="/module/order_info/order_info_update.html">
        	<div class='col-md-12'>
        			<input type="hidden" name="id" value="${item.id}" />
                   <div class="form-group">
                       <label class="col-sm-3 control-label no-padding-right" for="appId">运费<i class="text-danger">*</i></label>
                       <div class="col-sm-9">
                       		<div class="col-sm-10">
	                           <input class="form-control" name="fare" id="shippingFare" type="number" value="${shippingFare}" maxlength="19" placeholder="请输入运费">
                       		</div>
                     		<span>元</span>
                       </div>
                   </div>
			</div>
        </form>
	</div>
</div><!-- /.row --><!-- /.main-content -->
