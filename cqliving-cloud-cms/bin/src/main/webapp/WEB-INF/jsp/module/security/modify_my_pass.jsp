<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="修改密码" name="_breadcrumbs_1"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
		        <form class="form-horizontal form" method="post" id="form1" action="/module/security/common/modify_my_pass.html">
					<div class='col-md-12 col-lg-8'>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">原密码<i class="text-danger">*</i></label>
							<div class="col-sm-9">
								<input type="password" class="form-control" name="oldPassword" maxlength="50" placeholder="请输入原密码">
							</div>
						</div>
					</div>
					<div class='col-md-12 col-lg-8'>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">新密码<i class="text-danger">*</i></label>
							<div class="col-sm-9">
								<input type="password" class="form-control" name="password" id="password" maxlength="50" placeholder="请输入新密码">
							</div>
						</div>
					</div>
					<div class='col-md-12 col-lg-8'>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">确认密码<i class="text-danger">*</i></label>
							<div class="col-sm-9">
								<input type="password" class="form-control" name="rePassword" maxlength="50" placeholder="请再次输入新密码">
							</div>
						</div>
					</div>
					<div class='col-md-12 col-lg-8'>
						<div class="form-group pull-right">
							<div class="col-sm-9">
								<button class="btn btn-sm btn-success" type="button" id="saveBtn"><i class="icon-save bigger-110"></i>确认修改</button>
							</div>
						</div>
					</div>
		        </form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
require(['/resource/business/security/modify_my_pass.js'], function(obj){
	obj.init();
});
</script>