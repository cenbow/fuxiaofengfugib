<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="摄影表列表|shoot_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="查看" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
		        	<div class="col-md-12 col-lg-8">
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">作品标题</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.title}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">类型</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.type}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">类型名称</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.typeName}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">班级_id</label>
	                        <div class="col-sm-9">
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.clazzId}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">学校名称</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.schoolName}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">状态</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.status}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">手机号</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.phone}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">作品ID字符串,000001</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.numberStr}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">图片地址</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.imageUrl}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">作品描述</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.descri}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">上传时间</label>
	                        <div class="col-sm-9">
	                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value="${item.uploadTime}" pattern="yyyy-MM-dd" />">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">上传IP地址</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.ipAddr}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">原图片地址</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.imageSourceUrl}">
	                        </div>
	                    </div>
	                	<%-- <textarea readonly="" rows="10" class="form-control textarea limited" id="synopsis" name="synopsis" maxlength="600">textarea示例</textarea> --%>
			            <div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/shoot/shoot_list.html'">
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
