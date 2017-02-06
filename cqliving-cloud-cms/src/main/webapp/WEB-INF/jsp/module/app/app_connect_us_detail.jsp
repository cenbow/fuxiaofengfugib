<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="联系我们|to_add.html" name="_breadcrumbs_1"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/app_connect_us/save.html">
	            	<input type="hidden" name="id" value="${item.id}" />
		        	<div class='col-md-12 col-lg-8'>
	                    <c:if test="${not empty appList}">
		                    <div class="form-group">
		                        <label class="col-sm-3 control-label no-padding-right" for="appId">所属<i class="text-danger">*</i></label>
		                        <div class="col-sm-9">
		                            <select name="appId" id="appId" class="form-control chosen-select">
									   <c:forEach items="${appList}" var="app" varStatus="idx">
		                                  <option value="${app.id}" <c:if test="${(appId eq app.id) or (empty appId and idx.first)}">selected</c:if>>${app.name}</option>
		                               </c:forEach>
									</select>
		                        </div>
		                    </div>
	                    </c:if>
		            	<c:if test="${empty appList}">
		            		<input type="hidden" id="appId" value="${appId}">
		            	</c:if>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="content">内容<i class="text-danger">*</i></label>
                            <div class="col-sm-9">
	                            <textarea class="hidden" id="content" name="content">${item.content}</textarea>
	                            <script id="content_editor" type="text/plain"><p>8989898</p></script>
	                        </div>
	                    </div>
	                	<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/app_connect_us/to_add.html">
										<i class="icon-save bigger-110"></i>提交
									</button>
									<button class="btn btn-success btn-sm" type="button" id="preview">
										<i class="icon-search bigger-130"></i>预览
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
<a style="display: none;" class="btn btn-sm btn-success preview-form-a" href="#preview-form" role="button" data-toggle="modal"></a>
<!-- 预览弹层 -->
<div id="preview-form" class="modal" tabindex="-1">
	<div class="modal-dialog preview-dialog" >
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="light-grey bigger">联系我们</h4>
			</div>
			<div class="modal-body overflow-visible">
			
			</div>
			<div class="modal-footer">
				<button class="btn btn-sm btn-danger" data-dismiss="modal">
					<i class="icon-remove"></i>
					关闭
				</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">

require(['bootstrap'],function(){
	   $("#info_view_modal .preview-dialog .modal-body").css("overflow","visible");
	   $("#info_view_modal").modal("show");
	   $("#info_view_modal").on("hidden.bs.modal",function(){
		   $("#info_view_modal").remove();
	   });
});

window.UEDITOR_HOME_URL = '/resource/components/ueditor/';
var ue ;
require(['/resource/business/app/app_connect_us_detail.js'],function(config){
	config.init();
});
</script>