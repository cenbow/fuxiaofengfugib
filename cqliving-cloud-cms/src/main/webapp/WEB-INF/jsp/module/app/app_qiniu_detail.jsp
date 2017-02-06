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
                        <label class="col-sm-4 control-label no-padding-right" for="appId">APP_ID</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="appId" id="appId" type="text" value="${item.appId}" maxlength="19" placeholder="请输入APP_ID">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="bucketName">七牛云服务资源名称</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="bucketName" name="bucketName" maxlength="50" placeholder="请输入七牛云服务资源名称"  value="${item.bucketName}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="domainTest">七牛提供的测试域名</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="domainTest" name="domainTest" maxlength="255" placeholder="请输入七牛提供的测试域名"  value="${item.domainTest}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="domainCustom">绑定的自定义域名</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="domainCustom" name="domainCustom" maxlength="255" placeholder="请输入绑定的自定义域名"  value="${item.domainCustom}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="status">状态</label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="3" id="status3"><span class="lbl"> 有效</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="99" id="status99"><span class="lbl"> 删除</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("status${empty item ? 3 : item.status}").checked=true;
                            </script>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="createTime">创建时间</label>
                            <div class="col-sm-8">
                            <div class="input-prepend input-group">
                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD"}' name="createTime" id="createTime" readonly="readonly" value="<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" />">
                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="creatorId">创建人ID</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="creatorId" id="creatorId" type="text" value="${item.creatorId}" maxlength="19" placeholder="请输入创建人ID">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="creator">创建人名称</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="creator" name="creator" maxlength="100" placeholder="请输入创建人名称"  value="${item.creator}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="updatorId">更新人ID</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="updatorId" id="updatorId" type="text" value="${item.updatorId}" maxlength="19" placeholder="请输入更新人ID">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="updator">更新人</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="updator" name="updator" maxlength="100" placeholder="请输入更新人"  value="${item.updator}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="updateTime">更新时间</label>
                            <div class="col-sm-8">
                            <div class="input-prepend input-group">
                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD"}' name="updateTime" id="updateTime" readonly="readonly" value="<fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd" />">
                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
                            </div>
                        </div>
                    </div>
                	<div class="form-group col-xs-12">
						<div class=" text-right">
							<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/app/app_qiniu_list.html">
								<i class="icon-save bigger-110"></i>提交
							</button>
							&nbsp;
							<button class="btn btn-sm btn-primary" type="button" onclick="javascript:location.href='/module/app/app_qiniu_list.html'">
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
                    appId : {
                    required: true,
                    number:true
                },
                    bucketName : {
                    required: true
                },
                    domainTest : {
                    required: true
                },
                    domainCustom : {
                    required: true
                },
                    status : {
                    required: true
                },
                    createTime : {
                    required: true,
                    date:true
                },
                    creatorId : {
                    required: true,
                    number:true
                },
                    creator : {
                    required: true
                },
                    updatorId : {
                    required: true,
                    number:true
                },
                    updator : {
                    required: true
                },
                    updateTime : {
                    required: true,
                    date:true
                }
            },
            messages: {
                appId : {
                    required: ' ',
                    number:' '
                },
                bucketName : {
                    required: ' '
                },
                domainTest : {
                    required: ' '
                },
                domainCustom : {
                    required: ' '
                },
                status : {
                    required: ' '
                },
                createTime : {
                    required: ' ',
                    date:' '
                },
                creatorId : {
                    required: ' ',
                    number:' '
                },
                creator : {
                    required: ' '
                },
                updatorId : {
                    required: ' ',
                    number:' '
                },
                updator : {
                    required: ' '
                },
                updateTime : {
                    required: ' ',
                    date:' '
                }
            }
        });
    });
});
</script>