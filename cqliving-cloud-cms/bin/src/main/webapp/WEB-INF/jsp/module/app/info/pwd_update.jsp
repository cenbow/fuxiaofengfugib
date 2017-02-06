<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="客户端管理|/module/app/app_info_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="管理账户重置密码" name="_breadcrumbs_4"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1">
		            <input type="hidden" name="id" value="${item.id}" />
		            <div class="form-group">
                         <label class="col-sm-2 control-label no-padding-right">账号</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="username" name="username" value="${item.username}" placeholder="请输入管理用户账号" maxlength="50">
                        </div>
                    </div>
                    <div class="form-group">
                         <label class="col-sm-2 control-label no-padding-right">密码</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" id="password" name="password" placeholder="请输入管理用户密码" maxlength="50">
                        </div>
                    </div>
                    <div class="form-group">
                         <label class="col-sm-2 control-label no-padding-right">重复密码</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" id="confPwd" name="confPwd" placeholder="请输入管理用户密码" maxlength="50">
                        </div>
                    </div>
		            
		            <div class="form-group">
						<div class="col-sm-10">
							<div class="pull-right">
								<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/app/app_info_list.html">
									<i class="icon-save bigger-110"></i>提交
								</button>
								&nbsp;
								<button class="btn btn-sm btn-danger" type="button" onclick="javascript:window.location.href='/module/app/app_info_list.html'">
									<i class="icon-undo bigger-110"></i>返回
								</button>
							</div>
						</div>
					</div>
		        </form>
			</div>
		</div>
	</div>
</div>

<script>
require(['/resource/business/app/app_user.js'],function(appUser){
	appUser.init();
});
</script>