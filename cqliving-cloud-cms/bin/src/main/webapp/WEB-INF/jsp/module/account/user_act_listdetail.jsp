<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"></jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1">
		            <c:if test="${not empty item}">
		            	<input type="hidden" name="id" value="${item.id}" />
		            </c:if>
                    
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="userId">用户ID</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="userId" id="userId" type="text" value="${item.userId}" maxlength="19" placeholder="请输入用户ID">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="actInfoId">活动表ID</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="actInfoId" id="actInfoId" type="text" value="${item.actInfoId}" maxlength="19" placeholder="请输入活动表ID">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="actInfoListId">活动集合 表ID</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="actInfoListId" id="actInfoListId" type="text" value="${item.actInfoListId}" maxlength="19" placeholder="请输入活动集合 表ID">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="createTime">参与时间</label>
                            <div class="col-sm-8">
                            <div class="input-prepend input-group">
                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD"}' name="createTime" id="createTime" readonly="readonly" value="<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" />">
                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="ip">IP</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="ip" name="ip" maxlength="100" placeholder="请输入IP"  value="${item.ip}">
                        </div>
                    </div>
                	<div class="form-group col-xs-12">
						<div class=" text-right">
							<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/account/user_act_listlist.html">
								<i class="icon-save bigger-110"></i>提交
							</button>
							&nbsp;
							<button class="btn btn-sm btn-primary" type="button" onclick="javascript:location.href='/module/account/user_act_listlist.html'">
								<i class="icon-undo bigger-110"></i>返回
							</button>
						</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script type="text/javascript">
require(['cloud.time.input'], function(timeInput){
	timeInput.initTimeInput();
});
require(['validator.bootstrap','cloud.table.curd'], function($,tableCurd){
	tableCurd.bindSaveButton();
	
    $(function(){
        $("#form1").validate({
            rules: {
                    userId : {
                    required: true,
                    number:true
                },
                    actInfoId : {
                    required: true,
                    number:true
                },
                    actInfoListId : {
                    required: true,
                    number:true
                },
                    createTime : {
                    required: true,
                    date:true
                },
                    ip : {
                    required: true
                }
            },
            messages: {
                userId : {
                    required: ' ',
                    number:' '
                },
                actInfoId : {
                    required: ' ',
                    number:' '
                },
                actInfoListId : {
                    required: ' ',
                    number:' '
                },
                createTime : {
                    required: ' ',
                    date:' '
                },
                ip : {
                    required: ' '
                }
            }
        });
    });
});
</script>