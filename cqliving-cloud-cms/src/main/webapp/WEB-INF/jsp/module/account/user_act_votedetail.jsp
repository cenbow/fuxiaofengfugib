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
                        <label class="col-sm-4 control-label no-padding-right" for="userActListId">用户参与活动集合表ID（user_act_list表主键）</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="userActListId" id="userActListId" type="text" value="${item.userActListId}" maxlength="19" placeholder="请输入用户参与活动集合表ID（user_act_list表主键）">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="actVoteClassifyId">活动投票分类表ID（act_vote_classify表主键）</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="actVoteClassifyId" id="actVoteClassifyId" type="text" value="${item.actVoteClassifyId}" maxlength="19" placeholder="请输入活动投票分类表ID（act_vote_classify表主键）">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="actVoteItemId">活动分类选项表ID（act_vote_item表主键）</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="actVoteItemId" id="actVoteItemId" type="text" value="${item.actVoteItemId}" maxlength="19" placeholder="请输入活动分类选项表ID（act_vote_item表主键）">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="userId">用户ID</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="userId" id="userId" type="text" value="${item.userId}" maxlength="19" placeholder="请输入用户ID">
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
                	<div class="form-group col-xs-12">
						<div class=" text-right">
							<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/account/user_act_votelist.html">
								<i class="icon-save bigger-110"></i>提交
							</button>
							&nbsp;
							<button class="btn btn-sm btn-primary" type="button" onclick="javascript:location.href='/module/account/user_act_votelist.html'">
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
                    userActListId : {
                    required: true,
                    number:true
                },
                    actVoteClassifyId : {
                    required: true,
                    number:true
                },
                    actVoteItemId : {
                    required: true,
                    number:true
                },
                    userId : {
                    required: true,
                    number:true
                },
                    createTime : {
                    required: true,
                    date:true
                }
            },
            messages: {
                userActListId : {
                    required: ' ',
                    number:' '
                },
                actVoteClassifyId : {
                    required: ' ',
                    number:' '
                },
                actVoteItemId : {
                    required: ' ',
                    number:' '
                },
                userId : {
                    required: ' ',
                    number:' '
                },
                createTime : {
                    required: ' ',
                    date:' '
                }
            }
        });
    });
});
</script>