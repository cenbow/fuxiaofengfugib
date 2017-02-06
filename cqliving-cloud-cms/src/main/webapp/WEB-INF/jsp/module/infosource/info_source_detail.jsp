<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="资讯来源表列表|info_source_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/info/info_source_add.html">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
			            <input type="hidden" name="id" value="${item.id}" />
			            <input type="hidden" name="sortNo" value="${empty item.sortNo ? 2147483647 : item.sortNo}" />
	                    <div class="form-group  ${fn:length(allApps) le 1 ? 'hidden' : ''}">
	                        <label class="col-sm-3 control-label no-padding-right" for="appId">所属客户端<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <select name="appId" class="chosen-select" id="appId">
	                               <c:forEach items="${allApps }" var="app">
	                                  <option value="${app.id }"  ${item.appId eq app.id ? 'selected' : ''}>${app.name }</option>
	                               </c:forEach>
	                            </select>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="name">名称<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="name" name="name" maxlength="100" placeholder="请输入名称"  value="${item.name}">
	                        </div>
	                    </div>
	                	<c:if test="${empty _model_ }">
	                	<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-success btn-sm draft_save" type="button" id="commonSaveButton" back_url="/module/info/info_source_list.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" backRestoreParam="backRestoreParam"  back_url="/module/info/info_source_list.html">
										<i class="icon-undo bigger-110"></i>返回
									</button>
								</div>
							</div>
						</div>
						</c:if>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
<c:if test="${empty _model_ }">
	</div><!-- /.page-content -->
</div>
</c:if>

<script type="text/javascript">
require(['validator.bootstrap','cloud.table.curd','chosen'], function($,tableCurd){
	tableCurd.bindSaveButton();
	
	$(".chosen-select").chosen({search_contains:true});
	
    $(function(){
        $("#form1").validate({
            rules: {
                    appId : {
                    required: true
                },
                    name : {
                    required: true
                },
                    sortNo : {
                    required: true,
                    number:true
                }
            },
            messages: {
                appId : {
                    required: ' '
                },
                name : {
                    required: ' '
                },
                sortNo : {
                    required: ' ',
                    number:' '
                }
            }
        });
    });
});
</script>