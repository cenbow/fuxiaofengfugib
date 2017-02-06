<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"></jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12  version-view">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">客户端_ID</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.appId}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">模板CODE</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.templetCode}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">模板图片</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.imageUrl}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">模板类型</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.type}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">模版描述</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.templetDesc}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">模版名称</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.name}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">模版公有状态</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.commonType}
                            </p>
                        </div>
                    </div>
		            <div class="form-group col-xs-12">
						<div class="text-right">
							<button class="btn btn-sm btn-info" type="button" onclick="javascript:location.href='/module/act/act_templatelist.html'">
								<i class="icon-undo bigger-110"></i>返回
							</button>
						</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
