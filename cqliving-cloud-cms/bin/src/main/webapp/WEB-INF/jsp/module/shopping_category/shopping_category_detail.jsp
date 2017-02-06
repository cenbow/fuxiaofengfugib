<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="商品分类表列表|shopping_category_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/shopping_category/shopping_category_${empty item.id ? 'add' : 'update'}.html">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
		            	<input type="hidden" name="id" value="${item.id}" />
		            	<input type="hidden" name="parentId" value="${item.parentId}" />
	                    <input name="appId" id="appId" type="hidden" value="${item.appId}">
	                    <input name="level" id="level" type="hidden" value="${item.level}">
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="name">名称<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="name" name="name" maxlength="50" placeholder="请输入名称" value="${item.name}">
	                        </div>
	                    </div>
	                    <%-- <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="imgUrl">图片<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="imgUrl" name="imgUrl" maxlength="0" placeholder="请输入图片地址"  value="${item.imgUrl}">
	                        </div>
	                    </div> --%>
	                	<c:if test="${empty _model_ }">
	                	<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-success btn-sm draft_save" type="button" id="commonSaveButton" back_url="/module/shopping_category/shopping_category_list.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/shopping_category/shopping_category_list.html'">
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
require(['/resource/business/shopping/shopping_category_detail.js'], function(list){
	list.init();
});
</script>