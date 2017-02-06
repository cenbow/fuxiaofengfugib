<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="订单商品评价表列表|order_evaluate_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="查看" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">商品</label>
	                        <div class="col-sm-9">
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.shoppingGoodsName}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">评价用户</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.userName}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">评价内容</label>
	                        <div class="col-sm-9">
                                <textarea class="col-xs-10 form-control col-sm-5" rows="4" disabled="disabled">${item.content}</textarea>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">商品评分</label>
	                        <div class="col-sm-9">
								<div class="col-sm-9 stars">
									<span <c:if test="${not empty item.goodsScore and item.goodsScore ge 20}"> class="star_on" </c:if> ></span>
									<span <c:if test="${not empty item.goodsScore and item.goodsScore ge 40}"> class="star_on" </c:if>></span>
									<span <c:if test="${not empty item.goodsScore and item.goodsScore ge 60}"> class="star_on" </c:if>></span>
									<span <c:if test="${not empty item.goodsScore and item.goodsScore ge 80}"> class="star_on" </c:if>></span>
									<span <c:if test="${not empty item.goodsScore and item.goodsScore ge 100}"> class="star_on" </c:if>></span>
								</div>
	                        </div>
	                    </div>
                        <c:if test="${not empty item.imageUrls}">
		                    <div class="form-group">
		                        <label class="col-sm-3 control-label no-padding-right">评价图片<!-- 地址，最多9张，用逗号分开 --></label>
		                        <div class="col-sm-9">
							        <c:forEach items="${fn:split(item.imageUrls, ',')}" var="img" >
							                <img style="width:150px;height:150px;" src="${img}">
							        </c:forEach>
		                        </div>
		                    </div>
						</c:if>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">状态</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${allStatuss[item.status]}">
	                        </div>
	                    </div>
	                    <c:if test="${not empty item.aduitTime}">
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">审核时间</label>
	                        <div class="col-sm-9">
	                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value="${item.aduitTime}" pattern="yyyy-MM-dd" />">
	                        </div>
	                    </div>
	                    </c:if>
	                    <c:if test="${not empty item.aduitor}">
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">审核人</label>
	                        <div class="col-sm-9">
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.aduitor}">
	                        </div>
	                    </div>
	                    </c:if>
			            <c:if test="${empty _model_ }">
			            <div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/order_evaluate/order_evaluate_list.html'">
										<i class="icon-undo bigger-110"></i>返回
									</button>
								</div>
							</div>
						</div>
						</c:if>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
<c:if test="${empty _model_ }">
	</div><!-- /.page-content -->
</div>
</c:if>