<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="话题表列表|topiclist.html" name="_breadcrumbs_1"/>
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
	                        <label class="col-sm-3 control-label no-padding-right">客户端</label>
	                        <div class="col-sm-9">
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${appName}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">话题名称</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.name}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">话题内容</label>
	                        <div class="col-sm-9">
                                <textarea rows="6" class="form-control">${item.content}</textarea>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">话题列表图片</label>
	                        <div class="col-sm-9">
                                <ul class="ace-thumbnails">
                                	<a href="${item.listImageUrl }" data-rel="colorbox" class="cboxElement">
	                                	<img alt="" src="${item.listImageUrl }" style="width: 80px;height: 80px;">
	                                </a>
                                </ul>
	                        </div>
	                    </div>
	                    <c:if test="${item.isTop eq 1 }">
		                    <div class="form-group">
		                        <label class="col-sm-3 control-label no-padding-right">置顶图片</label>
		                        <div class="col-sm-9">
	                                 <ul class="ace-thumbnails">
	                                	<a href="${item.topImageUrl }" data-rel="colorbox" class="cboxElement">
		                                	<img alt="" src="${item.topImageUrl }" style="width: 80px;height: 80px;">
		                                </a>
	                                </ul>
		                        </div>
		                    </div>
	                    </c:if>
	                    <c:if test="${item.isRecommend eq 1 }">
		                    <div class="form-group">
		                        <label class="col-sm-3 control-label no-padding-right">推荐到首页图片</label>
		                        <div class="col-sm-9">
	                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.recommendImageUrl}">
	                                 <ul class="ace-thumbnails">
	                                	<a href="${item.recommendImageUrl }" data-rel="colorbox" class="cboxElement">
		                                	<img alt="" src="${item.recommendImageUrl }" style="width: 80px;height: 80px;">
		                                </a>
	                                </ul>
		                        </div>
		                    </div>
	                    </c:if>
	                    <c:if test="${not empty imageList && fn:length(imageList) gt 0 }">
		                    <div class="form-group">
		                        <label class="col-sm-3 control-label no-padding-right">话题图片</label>
		                        <div class="col-sm-9">
	                                <ul class="ace-thumbnails">
	                                	<c:forEach var="it" items="${imageList }">
	                                		<a href="${it.url }" data-rel="colorbox" class="cboxElement">
			                                	<img alt="" src="${it.url }" style="width: 80px;height: 80px;">
			                                </a>
	                                	</c:forEach>
	                                </ul>
		                        </div>
		                    </div>
	                    </c:if>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">审核时间</label>
	                        <div class="col-sm-9">
	                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value="${item.auditTime}" pattern="yyyy-MM-dd HH:mm:ss" />">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">创建时间</label>
	                        <div class="col-sm-9">
	                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">创建人名称</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.creator}">
	                        </div>
	                    </div>
			            <c:if test="${empty _model_ }">
			            <div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/topic/topiclist.html'">
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
require([ 'common_colorbox' ], function(colorbox) {
	colorbox.init();
});
</script>