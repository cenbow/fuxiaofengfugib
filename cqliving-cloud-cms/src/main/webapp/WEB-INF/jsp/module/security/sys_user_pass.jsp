<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"></jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
		        <form class="form-horizontal form" role="form" method="post" id="form1">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">登录用户名</label>
                        <div class="col-sm-9"> <span class="help-block">${item.username}</span></div>
                        <input type="hidden" name="id" value="${item.id}">
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">输入新登录密码</label>
                        <div class="col-sm-9">
                           <input type="password" name="newpassword" id="newpassword" value="" class="form-control" autocomplete="off" placeholder="输入新密码">
                           <span class="help-block">指定帐户的登录密码。</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">再次输入新登录密码</label>
                        <div class="col-sm-9">
                           <input type="password" name="newpassword2" value="" class="form-control" autocomplete="off" placeholder="确认密码">
                           <span class="help-block">再次输入指定帐户的登录密码。</span>
                        </div>
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label"></label>
                    </div>
		
		             <div class="form-group col-xs-12">
						<div class=" text-right">
							<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/security/sys_user_list.html">
								<i class="icon-save bigger-110"></i>提交
							</button>
							&nbsp;
							<button class="btn btn-sm btn-primary" type="button" onclick="javascript:location.href='/module/security/sys_user_list.html'">
								<i class="icon-undo bigger-110"></i>返回
							</button>
						</div>
					</div>
		        </form>
		      </div>
		    </div>
    </div>
</div>
<script>
require(['util', 'validator.bootstrap','cloud.table.curd'], function(util, $,curd){
	
	curd.bindSaveButton();
	
	$(function(){
		$('#form1').validate({
			onfocusout:false,
			onkeyup:false,
			onclick:false,
			focusInvalid:false,
			rules: {
				newpassword: {
					required: true,
					maxlength: 30
				},
				newpassword2: {
					required: true,
					equalTo: '#newpassword'
				}
			},
			messages: {
				newpassword: {
					required: ' ',
					maxlength: '最多只能输入{0}个字符'
				},
				newpassword2: {
					required: ' ',
					equalTo: '两次输入的密码必须要相同哟！'
				}
			}
		});
	});
});

</script>