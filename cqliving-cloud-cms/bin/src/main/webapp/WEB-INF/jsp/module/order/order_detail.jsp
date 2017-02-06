<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="订单收支记录表列表|order_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/order/order_${empty item.id ? 'add' : 'update'}.html">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
			            <c:if test="${not empty item}">
			            	<input type="hidden" name="id" value="${item.id}" />
			            </c:if>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="appId">客户端id<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="appId" id="appId" type="text" value="${item.appId}" maxlength="19" placeholder="请输入客户端id">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="orderId">订单id，表order_info的主键<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="orderId" id="orderId" type="text" value="${item.orderId}" maxlength="19" placeholder="请输入订单id，表order_info的主键">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="orderNo">订单号<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="orderNo" name="orderNo" maxlength="100" placeholder="请输入订单号"  value="${item.orderNo}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="money">金额（单位：分），有正负<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="money" id="money" type="text" value="${item.money}" maxlength="10" placeholder="请输入金额（单位：分），有正负">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="totalMoney">收支总金额（单位：分），把当前app之前所有收支记录的money相加，再加上本次money的值<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="totalMoney" id="totalMoney" type="text" value="${item.totalMoney}" maxlength="10" placeholder="请输入收支总金额（单位：分），把当前app之前所有收支记录的money相加，再加上本次money的值">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="payMode">支付渠道<i class="text-danger">*</i></label>
	                        <div class="col-sm-9 radio">
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="payMode" value="1" id="payMode1"><span class="lbl"> 支付宝</span>
	                            </label>
	                            <script type="text/javascript">
	                                document.getElementById("payMode${empty item ? 1 : item.payMode}").checked=true;
	                            </script>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="createTime">创建时间<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <div class="input-prepend input-group">
	                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD"}' name="createTime" id="createTime" readonly="readonly" value="<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" />">
	                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
	                            </div>
	                        </div>
	                    </div>
	                	<c:if test="${empty _model_ }">
	                	<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-info btn-sm draft_save view_button" type="button" data-target="#info_view_modal" role="button" data-toggle="modal">
										<i class="icon-eye-open bigger-110"></i>预览
									</button>
									&nbsp;
									<button class="btn btn-primary btn-sm push_save" type="button">
										<i class="icon-mail-forward bigger-110"></i>发布
									</button>
									&nbsp;
									<button class="btn btn-success btn-sm draft_save" type="button" id="commonSaveButton" back_url="/module/order/order_list.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/order/order_list.html'">
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

<script type="text/javascript">
require(['cloud.time.input'], function(timeInput){
	timeInput.initTimeInput();
});
require(['validator.bootstrap','cloud.table.curd'], function($,tableCurd){
	tableCurd.bindSaveButton();
	
    $(function(){
        $("#form1").validate({
            rules: {
                    appId : {
                    required: true,
                    number:true
                },
                    orderId : {
                    required: true,
                    number:true
                },
                    orderNo : {
                    required: true
                },
                    money : {
                    required: true,
                    number:true
                },
                    totalMoney : {
                    required: true,
                    number:true
                },
                    payMode : {
                    required: true
                },
                    createTime : {
                    required: true,
                    date:true
                }
            },
            messages: {
                appId : {
                    required: ' ',
                    number:' '
                },
                orderId : {
                    required: ' ',
                    number:' '
                },
                orderNo : {
                    required: ' '
                },
                money : {
                    required: ' ',
                    number:' '
                },
                totalMoney : {
                    required: ' ',
                    number:' '
                },
                payMode : {
                    required: ' '
                },
                createTime : {
                    required: ' ',
                    date:' '
                }
            }
        });
    });
});
</script>