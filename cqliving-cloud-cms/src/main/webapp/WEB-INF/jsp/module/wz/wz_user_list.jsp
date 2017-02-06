<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="子帐号列表" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="wz_user_list.html" id="wz_user_FormId">
							<div class="hide"><input type="text"><!-- 解决表单只有一个text回车自动提交的问题 --></div>
		                    <div class="special-list">
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_orgName" name="search_LIKE_orgName" value="${param.search_LIKE_orgName }" placeholder="回复机构名称" class="col-xs-12 form-control" />
		                        		</div>
                    				</div>
								</div>
	                    		<div class="col-xs-6 col-sm-3 btn-search">
									<div class="form-group">
										<div class="col-sm-12" align="left">
											<button class="btn btn-primary btn-sm" type="button" id="searchButton">
												<i class="icon-search bigger-110"></i>查询
											</button>
											<button class="btn btn-sm" type="button" id="resetButton">
												<i class="icon-undo bigger-110"></i>重置
											</button>
										</div>
									</div>
								</div>
	                    	</div>	
	                    	<div class="col-sm-12">					
								<div class="well">
									<cqliving-security2:hasPermission name="/module/wz/wz_user_add.html">
										<button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='/module/wz/wz_user_add.html'"><i class="icon-plus"></i>新增</button>
									</cqliving-security2:hasPermission>
								</div>
							</div>
		                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="wz_user_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<div id="resetPasswordModal" class="modal fade">
	<div class="modal-dialog">
	    <div class="modal-content">
	        <form class="form-horizontal form" method="post" id="modifyPasswordForm" action="wz_user_modify_password.html">
	        	<input type="hidden" value="" name="id">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	                <h3 class="ng-binding">重置密码</h3>
	            </div>
	            <div class="modal-body">
	                <div class="form-group">
	                    <label class="col-xs-12 col-sm-4 col-md-3 control-label">机构名称</label>
	                    <div class="col-sm-9" >
	                        <input type="text" class="form-control" name="orgName" required disabled>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="col-xs-12 col-sm-4 col-md-3 control-label">输入新密码</label>
	                    <div class="col-sm-9" >
	                        <input type="password" class="form-control" id="password" name="password" placeholder="请输入输入新密码" required >
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="col-xs-12 col-sm-4 col-md-3 control-label">再次输入新密码</label>
	                    <div class="col-sm-9" >
	                        <input type="password" class="form-control" id="repassword" name="repassword" placeholder="请再次输入新密码" required >
	                    </div>
	                </div>
	
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success btn-sm" id="modifyPasswordButton"><i class="icon-save"></i>保存</button>
                    <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal"><i class="icon-remove"></i>取消</button>
                </div>
            </form>
        </div>
	</div>
</div>


<script type="text/javascript">
	require(['/resource/business/wz/wzUserList.js'], function(obj){
		obj.init();
		$('body').tooltip({selector:'[data-rel=tooltip]'});
	});
</script>