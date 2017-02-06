<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<%-- <div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="反馈管理|/module/userFeedback/user_feedback_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="查看详情" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content"> --%>
		<div class="row">
			<div class="col-xs-12  version-view">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">来源客户端</label>
                        <div class="col-sm-10">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.appName}">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">用户名称</label>
                        <div class="col-sm-10">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.name}">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">内容</label>
                        <div class="col-sm-10">
                            <c:if test="${not empty item.content}">
                            	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.content}">
                            </c:if>
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">状态</label>
                        <div class="col-sm-10">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${allStatuss[item.status]}">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">创建时间</label>
                        <div class="col-sm-10">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value='${item.createTime}' pattern='yyyy-MM-dd' />">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">审核人姓名</label>
                        <div class="col-sm-10">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.auditingtor}">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">审核时间</label>
                        <div class="col-sm-10">
                             <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value='${item.auditingTime}' pattern='yyyy-MM-dd' />">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">标题</label>
                        <div class="col-sm-10">
                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.title}">
                        </div>
                    </div>
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">回复内容</label>
                        <div class="col-sm-10">
<%--                         	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.replyContent}"> --%>
                        	<textarea rows="" cols="" readonly="readonly" class="col-xs-10 form-control col-sm-5" >${item.replyContent}</textarea>
                        </div>
                    </div>
                   	<c:if test="${not empty item.imageUrl}">
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">图片</label>
                        <div class="col-sm-10">
                       		<span class="ace-thumbnails">
								<a href="${item.imageUrl}" data-rel="colorbox">
                                	<img alt="" height="150 px;" width="150 px;" src="${item.imageUrl}">
                                </a>
                            </span>
                        </div>
                    </div>
                    </c:if>
		            <!-- <div class="form-group">
						<div class="col-sm-12">
							<div class="pull-right">
							<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/userFeedback/user_feedback_list.html'">
								<i class="icon-undo bigger-110"></i>返回
							</button>
						</div>
						</div>
					</div> -->
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	<!-- </div>/.page-content
</div> -->
<script type="text/javascript">
require(['common_colorbox'], function(colorbox) {
	//1、图片查看
	colorbox.init();
});
</script>