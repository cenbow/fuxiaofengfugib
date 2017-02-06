<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"></jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1">
		            <c:if test="${not empty item}">
		            <input type="hidden" name="id" value="${item.id}" />
		            </c:if>
                    
                    <div class="form-group">
                        <label class="col-sm-1 control-label no-padding-right" for="appId">客户端_ID</label>
                        <div class="col-sm-11">
                            <input class="form-control" name="appId" id="appId" type="text" value="${item.appId}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label no-padding-right" for="placeCode">位置CODE</label>
                        <div class="col-sm-11">
                            <input type="text" class="form-control" id="placeCode" name="placeCode" placeholder="请输入位置CODE"  value="${item.placeCode}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label no-padding-right" for="placeName">位置名称</label>
                        <div class="col-sm-11">
                            <input type="text" class="form-control" id="placeName" name="placeName" placeholder="请输入位置名称"  value="${item.placeName}">

                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label no-padding-right" for="placeDesc">位置描述</label>
                        <div class="col-sm-11">
                            <input type="text" class="form-control" id="placeDesc" name="placeDesc" placeholder="请输入位置描述"  value="${item.placeDesc}">

                        </div>
                    </div>
		            <div class="form-group">
						<div class="col-sm-offset-4 col-sm-2 text-right">
							<button class="btn btn-info btn-sm" type="submit" id="searchButton">
								<i class="icon-search bigger-110"></i>提交
							</button>
							&nbsp;
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

<script>
require(['validator.bootstrap'], function($){
    $(function(){
        $("#form1").validate({
            rules: {
                    appId : {
                    required: true,
                    number:true
                },
                    placeCode : {
                    required: true
                },
                    placeName : {
                    required: true
                },
                    placeDesc : {
                    required: true
                }
            },
            messages: {
                appId : {
                    required: ' ',
                    number:' '
                },
                placeCode : {
                    required: ' '
                },
                placeName : {
                    required: ' '
                },
                placeDesc : {
                    required: ' '
                }
            }
        });
    });
});
</script>