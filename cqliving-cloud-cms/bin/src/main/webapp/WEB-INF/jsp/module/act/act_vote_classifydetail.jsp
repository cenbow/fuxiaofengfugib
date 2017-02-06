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
                        <label class="col-sm-4 control-label no-padding-right" for="actInfoId">活动ID（act_info表主键）</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="actInfoId" id="actInfoId" type="text" value="${item.actInfoId}" maxlength="19" placeholder="请输入活动ID（act_info表主键）">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="actInfoListId">活动集合表ID（act_info_list表主键）</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="actInfoListId" id="actInfoListId" type="text" value="${item.actInfoListId}" maxlength="19" placeholder="请输入活动集合表ID（act_info_list表主键）">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="actVoteId">活动投票表ID（act_vote表主键）</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="actVoteId" id="actVoteId" type="text" value="${item.actVoteId}" maxlength="19" placeholder="请输入活动投票表ID（act_vote表主键）">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="title">分类标题，不超过8个字</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="title" name="title" maxlength="20" placeholder="请输入分类标题，不超过8个字"  value="${item.title}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="subject">分类主题，不超过50个字 </label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="subject" name="subject" maxlength="100" placeholder="请输入分类主题，不超过50个字 "  value="${item.subject}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="sortNo">排序号</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="sortNo" id="sortNo" type="text" value="${item.sortNo}" maxlength="10" placeholder="请输入排序号">
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
							<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/act/act_vote_classifylist.html">
								<i class="icon-save bigger-110"></i>提交
							</button>
							&nbsp;
							<button class="btn btn-sm btn-primary" type="button" onclick="javascript:location.href='/module/act/act_vote_classifylist.html'">
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
                    actInfoId : {
                    required: true,
                    number:true
                },
                    actInfoListId : {
                    required: true,
                    number:true
                },
                    actVoteId : {
                    required: true,
                    number:true
                },
                    title : {
                    required: true
                },
                    subject : {
                    required: true
                },
                    sortNo : {
                    required: true,
                    number:true
                },
                    createTime : {
                    required: true,
                    date:true
                }
            },
            messages: {
                actInfoId : {
                    required: ' ',
                    number:' '
                },
                actInfoListId : {
                    required: ' ',
                    number:' '
                },
                actVoteId : {
                    required: ' ',
                    number:' '
                },
                title : {
                    required: ' '
                },
                subject : {
                    required: ' '
                },
                sortNo : {
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