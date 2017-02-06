<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="APP用户管理|/module/userAccount/user_account_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="重置密码" name="_breadcrumbs_3"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1">
		        <div class="col-md-12 col-lg-8">
	            	<input type="hidden" name="id" value="${item.id}" />
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="userName">登录账号</label>
                        <div class="col-sm-9 label-words">
                            ${item.userName}
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="password">密码</label>
                            <div class="col-sm-9">
                            <input type="password" class="form-control" id="password" name="password" maxlength="20" placeholder="请输入密码" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="password">重复密码</label>
                            <div class="col-sm-9">
                            <input type="password" class="form-control" id="confirmPwd" name="confirmPwd" maxlength="20" placeholder="请重复密码" >
                        </div>
                    </div>
                	<div class="form-group">
						<div class="col-sm-12">
							<div class="pull-right">
							<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/userAccount/user_account_list.html">
								<i class="icon-save bigger-110"></i>提交
							</button>
							&nbsp;
							<button class="btn btn-sm btn-primary" type="button" onclick="javascript:location.href='/module/userAccount/user_account_list.html'">
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
require(['/resource/business/user/reset_pwd.js'],function(reset_pwd){
	reset_pwd.init();
});
</script>