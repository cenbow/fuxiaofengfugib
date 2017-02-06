<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="段子管理|joke_info_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1">
		        	<div class="col-md-12 col-lg-8">
			            <c:if test="${not empty item}">
			            	<input type="hidden" name="id" value="${item.id}" />
			            </c:if>
		            	<input type="hidden" name="appId" value="${session_user_obj.appId}" />
		            	<input type="hidden" name="status" id="joke_info_status" />
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="content">内容<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <textarea class="form-control textarea" id="content" name="content" maxlength="1000" rows="9" placeholder="请输入内容">${item.content}</textarea>
                            	<span class="sns-num content_num">还能输入<em>500</em>个字</span>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="onlineTime">上线时间<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <div class="input-prepend input-group">
	                                <input class="form-control" type="text" time_options='{"singleDatePicker":true, "format":"YYYY-MM-DD HH:mm", "timePicker": true, "timePicker12Hour": false,"timePickerIncrement":1}' name="onlineTime" id="onlineTime" readonly="readonly" value="<fmt:formatDate value="${empty item.onlineTime ? onlineTime : item.onlineTime}" pattern="yyyy-MM-dd HH:mm" />">
	                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
	                            </div>
	                        </div>
	                    </div>
	                	<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
										&nbsp;
									<button class="btn btn-success btn-sm draft_save commonSaveButton" url="/module/joke/joke_info_${not empty item ? 'update' : 'add'}.html" type="button" statusval="0" back_url="/module/joke/joke_info_list.html">
										<i class="icon-save bigger-110"></i>保存草稿
									</button>
									<cqliving-security2:hasPermission name="/module/joke/joke_info_${not empty item ? 'update' : 'add'}_save.html">
										&nbsp;
										<button class="btn btn-primary btn-sm add_save commonSaveButton" url="/module/joke/joke_info_${not empty item ? 'update' : 'add'}_save.html" type="button" statusval="1" back_url="/module/joke/joke_info_list.html">
											<i class="icon-edit bigger-110"></i>提交审核
										</button>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/joke/joke_info_${not empty item ? 'update' : 'add'}_publish.html">
										&nbsp;
										<button class="btn btn-primary btn-sm publish_save commonSaveButton" url="/module/joke/joke_info_${not empty item ? 'update' : 'add'}_publish.html" type="button" statusval="3" back_url="/module/joke/joke_info_list.html">
											<i class="icon-mail-forward bigger-110"></i>发布
										</button>
									</cqliving-security2:hasPermission>
									<cqliving-security2:hasPermission name="/module/joke/joke_info_${not empty item ? 'update' : 'add'}_offline.html">
										&nbsp;
										<button class="btn btn-sm btn-danger offline_save commonSaveButton" url="/module/joke/joke_info_${not empty item ? 'update' : 'add'}_offline.html" type="button" statusval="88" back_url="/module/joke/joke_info_list.html">
											<i class="icon-arrow-down bigger-110"></i>下线
										</button>
									</cqliving-security2:hasPermission>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/joke/joke_info_list.html'">
										<i class="icon-undo bigger-110"></i>返回
									</button>
								</div>
							</div>
						</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script type="text/javascript">
	require(["/resource/business/joke/jokeInfoDetail.js"], function(obj) {
		obj.init();
	});
</script>