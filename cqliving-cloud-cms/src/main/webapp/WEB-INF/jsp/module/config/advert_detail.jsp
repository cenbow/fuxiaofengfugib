<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="广告图管理|advert_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/advert/advert_${empty item.id ? 'add' : 'update'}.html">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
			            <c:if test="${not empty item}">
			            	<input type="hidden" name="id" value="${item.id}" />
			            </c:if>
		            	<input type="hidden" name="appId" id="appId" value="${item.appId}" />
		            	<input type="hidden" name="showTime" id="showTime" value="2" />
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="imageUrl"><i class="text-danger">*</i>广告图</label>
	                        <div class="col-sm-9">
	                            <input type="hidden" id="imageUrl" name="imageUrl" value="${item.imageUrl}">
		                        <div class="col-sm-10">
		                   			<div id="img_upload" title="点击上传">
			                   			<i class="icon-cloud-upload"></i>
			                        </div>
			                        <div id="loadingView">
			                             <ul class="ace-thumbnails">
			                             <c:if test="${not empty item.imageUrl}">
			                                 <li>
			                                 	<a href="${item.imageUrl}" data-rel="colorbox">
											 		<img alt="150x150" style="width:150px;height:150px;" src="${item.imageUrl}">
											 	</a>
												<div class="tools tools-top">
													<a href="javascript:;">
														<i class="icon-remove red"></i>
														</a>
													</div>
												</li>
												</c:if>
			                             </ul>
			                        </div>
		                        </div>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="linkUrl">广告链接地址<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="linkUrl" name="linkUrl" maxlength="255" placeholder="请输入广告链接地址"  value="${item.linkUrl}">
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
									<button class="btn btn-success btn-sm draft_save" type="button" id="commonSaveButton" back_url="/module/advert/advert_list.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/advert/advert_list.html'">
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
var imgUrl = '${imageUrl}';
require(['/resource/business/config/advert_detail.js'], function(detail){
	detail.init();
});
</script>