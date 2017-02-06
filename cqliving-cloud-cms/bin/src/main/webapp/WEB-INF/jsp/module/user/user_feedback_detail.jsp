<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!-- <div class="main-content"> -->
	<%-- <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="反馈管理|/module/userFeedback/user_feedback_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="回复" name="_breadcrumbs_3"/>
	</jsp:include> --%>
<!-- 	<div class="page-content"> -->
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/userFeedback/reply.html">
	            	<input type="hidden" name="id" value="${item.id}" />
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right" for="replyContent">回复</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="5" cols="" id="replyContent" name="replyContent" placeholder="请输入回复" maxlength="500">${item.replyContent}</textarea>
                        </div>
                    </div>
                	<!-- <div class="form-group col-xs-12">
						<div class="col-sm-12">
							<div class="pull-right">
								<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/userFeedback/user_feedback_list.html">
									<i class="icon-save bigger-110"></i>提交
								</button>
								&nbsp;
								<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/userFeedback/user_feedback_list.html'">
									<i class="icon-undo bigger-110"></i>返回
								</button>
							</div>
						</div>
					</div> -->
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
<!-- 	</div>/.page-content -->
<!-- </div> -->

<script type="text/javascript">
require(['cloud.time.input'], function(timeInput){
	timeInput.initTimeInput();
});
require(['validator.bootstrap','cloud.table.curd'], function($,tableCurd){
	tableCurd.bindSaveButton();
	
    $(function(){
        $("#form1").validate({
            rules: {
                    replyContent : {
                    required: true
                }
            },
            messages: {
                replyContent : {
                    required: '请输入回复'
                }
            }
        });
    });
});
</script>