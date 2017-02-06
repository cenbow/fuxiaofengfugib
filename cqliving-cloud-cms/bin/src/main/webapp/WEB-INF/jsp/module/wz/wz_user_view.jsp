<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="子帐号列表|/module/wz/wz_user_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="子帐号查看" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1">
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">登录用户名</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" value="${item.username}" disabled="disabled" } >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">机构名称</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" value="${wzItem.orgName}" disabled="disabled" } >
                        </div>
                    </div>
                    <div class="form-group app_form_div">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">电子邮件</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" disabled="disabled" value="${item.email }">
                        </div>
                    </div>
                    <div class="form-group app_form_div">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">手机号</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" disabled="disabled" value="${item.mobile }">
                        </div>
                    </div>
                    <div class="form-group app_form_div">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">QQ</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" disabled="disabled" value="${item.qqCode }">
                        </div>
                    </div>
                    <div class="form-group app_form_div">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">职位</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" disabled="disabled" value="${item.position }">
                        </div>
                    </div>
                    <div class="form-group app_form_div">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">头像</label>
                        <div class="col-sm-9">
                        	<div class="ace-thumbnails">
	                        	<a href="${item.imgUrl }" data-rel="colorbox">
	                            	<img src="${item.imgUrl }" style="width:150px;height:150px;">
	                            </a>
                        	</div>
                        </div>
                    </div>
                    <div class="form-group app_form_div">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">描述</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" disabled="disabled" value="${item.descn }">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">状态</label>
                        <div class="col-sm-9">
                        
                            <label>
                                <input type="radio" class="ace" name="status" id="status0" value="0" disabled>
                                <span class="lbl"> 禁用</span>
                            </label>
                            <label>
                                <input type="radio" class="ace" name="status" id="status1" value="1" disabled>
                                <span class="lbl"> 启用</span>
                            </label>
                            
                            <script type="text/javascript">
                                document.getElementById("status${empty item ? 1 : item.status}").checked=true;
                            </script>

                        </div>
                    </div>
                	<div class="form-group">
						<div class="col-sm-11">
							<div class="pull-right">
								<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/wz/wz_user_list.html'">
									<i class="icon-undo bigger-110"></i>返回
								</button>
							</div>
						</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
<script type="text/javascript">
require(['common_colorbox'], function(colorbox){
	colorbox.init();
});
</script>