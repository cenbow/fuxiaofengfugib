<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<title>${session_app_info.name}管理系统</title>
		<meta name="description" content="overview &amp; stats" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/include.jsp"></jsp:include>
	</head>

	<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
					<div class="center login-title">
								<h1>
									<!-- <i class="icon-leaf green"></i> -->
									<!-- <span class="red">Ace</span> -->
									<span class="white">${session_app_info.name}管理系统</span>
								</h1>
								<!-- <h4 class="blue">&copy; Company Name</h4> -->
							</div>
						<div class="login-container">
							

							<div class="space-20"></div>
							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="icon-lightbulb green"></i>
												请输入你的帐户信息
											</h4>
											
											<div class="space-6"></div>
											<c:if test="${not empty loginError}">
						                    <div class="alert alert-danger" role="alert"><strong>登录失败：</strong>${loginError}</div>
						                    </c:if>
                    						<form action="/login.html" method="post" role="form">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input name="username" value="<cqliving-security2:principal />" type="text" class="form-control" placeholder="用户名" />
															<i class="icon-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input name="password" type="password" class="form-control" placeholder="密码" />
															<i class="icon-lock"></i>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
														<label class="inline">
															<input type="checkbox" checked="true" name="rememberMe" class="ace" />
															<span class="lbl"> 记住我</span>
														</label>
														<input name="token" value="7180e133" type="hidden">
													</div>
														
														<button type="submit" name="button" class="btn-block pull-right btn btn-primary">
															<i class="icon-key"></i>
															登录
														</button>

													<div class="space-4"></div>
												</fieldset>
											</form>
										</div><!-- /widget-main -->
									</div><!-- /widget-body -->
								</div><!-- /login-box -->
							</div><!-- /position-relative -->
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div>
		</div><!-- /.main-container -->

		<script type="text/javascript">
		require(['jquery.cookie'],function($){
			$(function(){
				$("form").submit(function(e){
					var username = $(":input[name=username]").val();
					var pwd = $(":input[name=password]").val();
					if(!username || !pwd)return false;
					
					if($('#rememberMe:checked').length == 1) {
						$.cookie('remember-username',$(':text[name="username"]').val());
					} else {
						$.cookie('remember-username',null);
					}
				});
			});
		});
		</script>
	</body>
</html>
