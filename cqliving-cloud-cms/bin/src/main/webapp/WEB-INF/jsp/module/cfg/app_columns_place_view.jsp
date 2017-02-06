<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"></jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
                    <div class="form-group">
                        <label class="col-sm-1 control-label no-padding-right">客户端_ID</label>
                        <div class="col-sm-11">
                            <p class="form-control-static">
                                ${item.appId}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label no-padding-right">位置CODE</label>
                        <div class="col-sm-11">
                            <p class="form-control-static">
                                ${item.placeCode}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label no-padding-right">位置名称</label>
                        <div class="col-sm-11">
                            <p class="form-control-static">
                                ${item.placeName}
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label no-padding-right">位置描述</label>
                        <div class="col-sm-11">
                            <p class="form-control-static">
                                ${item.placeDesc}
                            </p>
                        </div>
                    </div>
		            <div class="form-group">
						<div class="col-sm-offset-4 col-sm-2 text-right">
							<button class="btn btn-sm" type="button" onclick="javascript:location.href='/module/place/app_columns_place_list.html'">
								<i class="icon-undo bigger-110"></i>返回
							</button>
						</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
