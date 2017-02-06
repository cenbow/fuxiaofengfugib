<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="活动管理|/module/act/act_infolist.html" name="_breadcrumbs_1"/>
	    <jsp:param value="推荐到首页" name="_breadcrumbs_3"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/act/recommend.html">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
		            	<input type="hidden" name="id" value="${item.id}" />
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="name">推荐到首页图片<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <div id="img_upload" title="点击上传">
	                   				<i class="icon-cloud-upload"></i>
	                        	</div>
	                        	<input type="hidden" id="recommendImageUrl" name="recommendImageUrl" value="${item.recommendImageUrl}">
	                        	<div id="imgView">
	                             <ul class="ace-thumbnails">
	                             <c:if test="${not empty item.recommendImageUrl}">
	                                 <li>
	                                 	<a href="${item.recommendImageUrl}" data-rel="colorbox">
									 		<img alt="150x150" style="width:150px;height:150px;" src="${item.recommendImageUrl}">
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
	                	<c:if test="${empty _model_ }">
	                	<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-success btn-sm draft_save" type="button" id="commonSaveButton" back_url="/module/act/act_infolist.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/act/act_infolist.html'">
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
require(['/resource/business/act/act_recommend.js'],function(recommend){
	recommend.init();
});
</script>