<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="列表|recommend_info_list.html" name="_breadcrumbs_1"/>
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
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${appInfoName}">
	                        </div>
	                    </div>
	                    <%-- <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">业务ID</label>
	                        <div class="col-sm-9">
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.sourceId}">
	                        </div>
	                    </div> --%>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">名称</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.sourceName}">
	                        </div>
	                    </div>
	                    <c:if test="${not empty item.imageUrl}">
	                    <div class="form-group">
	                       <label class="col-sm-3 control-label no-padding-right">图片</label>
	                       <div class="col-sm-9">
	                              <ul class="ace-thumbnails">
	                       			<a href="${item.imageUrl}" data-rel="colorbox">
	                               		<img alt="" src="${item.imageUrl}" style="width:80px;height:80px;">
	                               </a>
	                              </ul>
	                       </div>
	                   </div>
	                   </c:if>
	                   <c:if test="${not empty item.linkUrl}">
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">链接地址</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.linkUrl}">
	                        </div>
	                    </div>
	                    </c:if>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">排序号</label>
	                        <div class="col-sm-9">
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.sortNo}">
	                        </div>
	                    </div>
	                    <%-- <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">创建时间</label>
	                        <div class="col-sm-9">
	                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" />">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">创建人</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.creator}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">更新时间</label>
	                        <div class="col-sm-9">
	                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd" />">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">更新人</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.updator}">
	                        </div>
	                    </div> --%>
			            <c:if test="${empty _model_ }">
			            <div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/config/recommend_info_list.html'">
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
require(['common_colorbox'], function(colorbox){
	colorbox.init();
});
</script>