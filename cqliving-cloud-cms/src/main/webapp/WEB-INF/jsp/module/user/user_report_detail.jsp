<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="举报管理|/module/userReport/user_report_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1">
		            <c:if test="${not empty item}">
		            	<input type="hidden" name="id" value="${item.id}" />
		            </c:if>
                    
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="appId">来源APP</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="appId" id="appId" type="text" value="${item.appId}" maxlength="19" placeholder="请输入来源APP">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="sessionCode">会话code</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="sessionCode" name="sessionCode" maxlength="100" placeholder="请输入会话code"  value="${item.sessionCode}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="userId">评论人ID</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="userId" id="userId" type="text" value="${item.userId}" maxlength="19" placeholder="请输入评论人ID">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="name">举报人姓名</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="name" name="name" maxlength="100" placeholder="请输入举报人姓名"  value="${item.name}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="content">评论内容,预留，用户前台暂时无填写</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="content" name="content" maxlength="500" placeholder="请输入评论内容,预留，用户前台暂时无填写"  value="${item.content}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="reportCode">举报内容CODE,参照字典表</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="reportCode" name="reportCode" maxlength="20" placeholder="请输入举报内容CODE,参照字典表"  value="${item.reportCode}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="sourceType">举报来源类型</label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="sourceType" value="0" id="sourceType0"><span class="lbl"> 新闻</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="sourceType" value="1" id="sourceType1"><span class="lbl"> 评论</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("sourceType${empty item ? 0 : item.sourceType}").checked=true;
                            </script>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="sourceId">举报来源ID</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="sourceId" id="sourceId" type="text" value="${item.sourceId}" maxlength="19" placeholder="请输入举报来源ID">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="status">状态</label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="-1" id="status-1"><span class="lbl"> 举报不实</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="2" id="status2"><span class="lbl"> 待审核</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="3" id="status3"><span class="lbl"> 举报属实</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="status" value="99" id="status99"><span class="lbl"> 删除</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("status${empty item ? -1 : item.status}").checked=true;
                            </script>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="auditingType">审阅状态</label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="auditingType" value="0" id="auditingType0"><span class="lbl"> 未处理</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="auditingType" value="1" id="auditingType1"><span class="lbl"> 已处理</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("auditingType${empty item ? 0 : item.auditingType}").checked=true;
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
                        <label class="col-sm-4 control-label no-padding-right" for="auditingId">审核人ID</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="auditingId" id="auditingId" type="text" value="${item.auditingId}" maxlength="19" placeholder="请输入审核人ID">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="auditingtor">审核人姓名</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="auditingtor" name="auditingtor" maxlength="100" placeholder="请输入审核人姓名"  value="${item.auditingtor}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="auditingTime">审核时间</label>
                            <div class="col-sm-8">
                            <div class="input-prepend input-group">
                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD"}' name="auditingTime" id="auditingTime" readonly="readonly" value="<fmt:formatDate value="${item.auditingTime}" pattern="yyyy-MM-dd" />">
                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
                            </div>
                        </div>
                    </div>
                	<div class="form-group col-xs-12">
						<div class=" text-right">
							<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/userReport/user_report_list.html">
								<i class="icon-save bigger-110"></i>提交
							</button>
							&nbsp;
							<button class="btn btn-sm btn-primary" type="button" onclick="javascript:location.href='/module/userReport/user_report_list.html'">
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
                    sessionCode : {
                    required: true
                },
                    userId : {
                    required: true,
                    number:true
                },
                    name : {
                    required: true
                },
                    content : {
                    required: true
                },
                    reportCode : {
                    required: true
                },
                    sourceType : {
                    required: true
                },
                    sourceId : {
                    required: true,
                    number:true
                },
                    status : {
                    required: true
                },
                    auditingType : {
                    required: true
                },
                    createTime : {
                    required: true,
                    date:true
                },
                    auditingId : {
                    required: true,
                    number:true
                },
                    auditingtor : {
                    required: true
                },
                    auditingTime : {
                    required: true,
                    date:true
                }
            },
            messages: {
                appId : {
                    required: ' ',
                    number:' '
                },
                sessionCode : {
                    required: ' '
                },
                userId : {
                    required: ' ',
                    number:' '
                },
                name : {
                    required: ' '
                },
                content : {
                    required: ' '
                },
                reportCode : {
                    required: ' '
                },
                sourceType : {
                    required: ' '
                },
                sourceId : {
                    required: ' ',
                    number:' '
                },
                status : {
                    required: ' '
                },
                auditingType : {
                    required: ' '
                },
                createTime : {
                    required: ' ',
                    date:' '
                },
                auditingId : {
                    required: ' ',
                    number:' '
                },
                auditingtor : {
                    required: ' '
                },
                auditingTime : {
                    required: ' ',
                    date:' '
                }
            }
        });
    });
});
</script>