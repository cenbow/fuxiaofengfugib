<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="区情介绍" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/config_text/config_text_add.html">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
			            <c:if test="${not empty item}">
			            	<input type="hidden" name="id" value="${item.id}" />
			            </c:if>
	                    
	                    <div class="form-group ${defaultAppId eq session_user_obj.appId ? 'hidden' : ''}">
	                        <label class="col-sm-3 control-label no-padding-right" for="appId">所属APP<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <select name="appId" class="form-control chosen-select">
	                               <c:forEach items="${appInfos }" var="app">
	                                  <option value="${app.id }" <c:if test="${defaultAppId eq app.id}">selected</c:if>  >${app.name }</option>
	                               </c:forEach>
	                            </select>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="title">标题<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="title" name="title" maxlength="100" placeholder="请输入标题"  value="${item.title}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="publishTime">发布时间<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <div class="input-prepend input-group">
	                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD HH:mm:ss","timePicker": true,"timePicker12Hour": false,"timePickerIncrement":1}' name="publishTime" id="publishTime" readonly="readonly" value="<fmt:formatDate value="${empty item.publishTime ? dateNow : item.publishTime}" pattern="yyyy-MM-dd HH:mm:ss" />">
	                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="content">内容<i class="text-danger">*</i></label>
	                           <div class="col-sm-9">
	                             <textarea class="hidden" name="content">${item.content }</textarea>
	                             <script id="config_text_editor" type="text/plain">${item.content}</script>
	                           </div>
	                    </div>
	                	<c:if test="${empty _model_ }">
	                	<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-success btn-sm draft_save" type="button" id="commonSaveButton" back_url="/module/config_text/config_text_add.html?appId=${defaultAppId}">
										<i class="icon-save bigger-110"></i>保存
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

window.UEDITOR_HOME_URL = '/resource/components/ueditor/';

require(['cloud.time.input'], function(timeInput){
	timeInput.initTimeInput();
});
require(['validator.bootstrap','cloud.table.curd','/resource/business/config/config_text_add.js'], function($,tableCurd,configText){
	tableCurd.bindSaveButton();
	
	configText.init();
	
    $(function(){
        $("#form1").validate({
        	ignore:"",
            rules: {
                    appId : {
                    required: true,
                    number:true
                },
                    title : {
                    required: true
                },
                    publishTime : {
                    required: true
                },
                    content : {
                    required: true
                }
            },
            messages: {
                appId : {
                    required: ' ',
                    number:' '
                },
                title : {
                    required: ' '
                },
                publishTime : {
                    required: ' '
                },
                content : {
                    required: ' '
                }
            }
        });
    });
});
</script>