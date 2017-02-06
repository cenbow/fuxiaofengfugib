<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="row">
	<div class="profile-user-info profile-user-info-striped">
		<div class="profile-info-row">
			<div class="profile-info-name"> 订单编号 </div>
			<div class="profile-info-value">
				<span class="editable" id="username"><c:if test="${empty item.orderNo}">&nbsp;</c:if>${item.orderNo }</span>
			</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name"> 收货人姓名 </div>
			<div class="profile-info-value">
				<span class="editable"><c:if test="${empty item.receiverName}">&nbsp;</c:if>${item.receiverName}</span>
			</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name"> 收货人电话 </div>
			<div class="profile-info-value">
				<span class="editable"><c:if test="${empty item.receiverPhone}">&nbsp;</c:if>${item.receiverPhone}</span>
			</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name"> 收货人地址 </div>
			<div class="profile-info-value">
				<i class="icon-map-marker light-orange bigger-110"></i>
				<span class="editable">${item.receiverAddress}</span>
			</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name"> 快递公司</div>
			<div class="profile-info-value">
				<input type="text" class="form-control" name="expressCompany" maxlength="50" placeholder="请输入快递公司"  value="">
			</div>
		</div>
		<div class="profile-info-row">
			<div class="profile-info-name"> 快递单号 </div>
			<div class="profile-info-value">
				<input type="text" class="form-control" name="expressNo" maxlength="100" placeholder="请输入快递单号"  value="">
			</div>
		</div>
	</div>
</div>