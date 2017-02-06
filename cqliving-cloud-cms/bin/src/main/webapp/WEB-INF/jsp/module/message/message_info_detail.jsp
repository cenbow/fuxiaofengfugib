<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<c:if test="${empty _model_}">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="通知发布|message_info_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="${request.contextPaht}/module/message/message_info_add.html">
	            	<input type="hidden" name="appId" value="${session_user_obj.appId}" />
		            <c:if test="${not empty item}">
		            	<input type="hidden" name="id" value="${item.id}" />
		            </c:if>
		            <div class="col-md-12 <c:if test="${empty _model_}">col-lg-8</c:if>">
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="title">标题</label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="title" name="title" maxlength="100" placeholder="请输入标题"  value="${item.title}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="receiverType">接收人</label>
	                        <div class="col-sm-9 radio">
	                        	<c:forEach items="${allReceiverTypes}" var="obj">
	                        		<c:if test="${obj.key eq 1}">
			                            <label class="radio-2">
			                                <input type="radio" class="ace" name="receiverType" value="${obj.key}" id="receiverType${obj.key}"><span class="lbl">${obj.value}</span>
			                            </label>
	                        		</c:if>
	                        	</c:forEach>
	                            <script type="text/javascript">
	                                document.getElementById("receiverType${empty item ? 1 : item.receiverType}").checked=true;
	                            </script>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="sendType">发送类型</label>
	                        <div class="col-sm-9 radio">
	                        	<c:forEach items="${allSendTypes}" var="obj">
	<%--                         		<c:if test="${obj.key ne sendTypeSms}"> --%>
	                        		<c:if test="${obj.key eq 0}">
			                            <label class="radio-2" <c:if test="${obj.key eq 2}">style="display:none;"</c:if>>
			                                <input type="radio" class="ace" name="sendType" value="${obj.key}" id="sendType${obj.key}"><span class="lbl">${obj.value}</span>
			                            </label>
	                        		</c:if>
	                        	</c:forEach>
	                            <script type="text/javascript">
	                                document.getElementById("sendType${empty item ? 0 : item.sendType}").checked=true;
	                            </script>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="context">内容</label>
	                        <div class="col-sm-9">
	                        	<textarea rows="" cols="" class="form-control" id="context" name="context" maxlength="1000" placeholder="请输入内容">${item.context}</textarea>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="sendTime">发送时间</label>
	                        <div class="col-sm-9">
	                            <div class="input-prepend input-group">
	                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD HH:mm", "timePicker": true,"timePicker12Hour": false,"timePickerIncrement": 1}' name="sendTime" id="sendTime" readonly="readonly" value="<fmt:formatDate value="${dateNow}" pattern="yyyy-MM-dd HH:mm" />">
	                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
	                            </div>
	                        </div>
	                    </div>
	                    <c:choose>
		                    <c:when test="${not empty appList}">
			                    <div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="receiverAppId">区县</label>
			                        <div class="col-sm-9">
			                            <select multiple="" class="chosen-select" id="receiverAppId" name="receiverAppId" data-placeholder="选择区县" style="display: none;">
			                            	<c:forEach items="${appList}" var="obj">
												<option value="${obj.id}">${obj.name}</option>
			                            	</c:forEach>
										</select>
			                        </div>
			                    </div>
		                    </c:when>
		                    <c:otherwise>
		                    	<input type="hidden" name="receiverAppId" value="${session_user_obj.appId}" />
		                    </c:otherwise>
	                    </c:choose>
	                    <c:if test="${empty _model_}">
		                	<div class="form-group">
		                		<div class="col-sm-12">
									<div class="pull-right">
										<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/message/message_info_list.html">
											<i class="icon-save bigger-110"></i>保存
										</button>
										&nbsp;
										<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/message/message_info_list.html'">
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
<c:if test="${empty _model_}">
	</div><!-- /.page-content -->
</div>
</c:if>
<script type="text/javascript">
	require(["/resource/business/message/messageInfoDetail.js"], function(obj) {
		obj.init();
	});
</script>