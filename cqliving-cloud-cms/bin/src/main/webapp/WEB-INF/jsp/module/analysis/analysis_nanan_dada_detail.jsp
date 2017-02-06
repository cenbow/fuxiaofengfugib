<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="南岸讲学赞栏目统计列表|analysis_nanan_dadalist.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/analysis/analysis_nanan_dada_${empty item.id ? 'add' : 'update'}.html">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
			            <c:if test="${not empty item}">
			            	<input type="hidden" name="id" value="${item.id}" />
			            </c:if>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="analysisNananTimesId">统计期数ID，表analysis_nanan_times的主键<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="analysisNananTimesId" id="analysisNananTimesId" type="text" value="${item.analysisNananTimesId}" maxlength="19" placeholder="请输入统计期数ID，表analysis_nanan_times的主键">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="userId">用户ID，表user_account的主键<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="userId" id="userId" type="text" value="${item.userId}" maxlength="19" placeholder="请输入用户ID，表user_account的主键">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="userTelephone">用户手机<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="userTelephone" name="userTelephone" maxlength="20" placeholder="请输入用户手机"  value="${item.userTelephone}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="userName">用户姓名<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="userName" name="userName" maxlength="50" placeholder="请输入用户姓名"  value="${item.userName}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="commentNum">评论数<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="commentNum" id="commentNum" type="text" value="${item.commentNum}" maxlength="10" placeholder="请输入评论数">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="praiseNum">点赞数<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="praiseNum" id="praiseNum" type="text" value="${item.praiseNum}" maxlength="10" placeholder="请输入点赞数">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="shareNum">分享数<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="shareNum" id="shareNum" type="text" value="${item.shareNum}" maxlength="10" placeholder="请输入分享数">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="score">分数=分享数*5+评论数*3+点赞数<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="score" id="score" type="text" value="${item.score}" maxlength="10" placeholder="请输入分数=分享数*5+评论数*3+点赞数">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="createTime">创建时间<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <div class="input-prepend input-group">
	                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD"}' name="createTime" id="createTime" readonly="readonly" value="<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" />">
	                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
	                            </div>
	                        </div>
	                    </div>
	                	<c:if test="${empty _model_ }">
	                	<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-info btn-sm draft_save view_button" type="button" data-target="#info_view_modal" role="button" data-toggle="modal">
										<i class="icon-eye-open bigger-110"></i>预览
									</button>
									&nbsp;
									<button class="btn btn-primary btn-sm push_save" type="button">
										<i class="icon-mail-forward bigger-110"></i>发布
									</button>
									&nbsp;
									<button class="btn btn-success btn-sm draft_save" type="button" id="commonSaveButton" back_url="/module/analysis/analysis_nanan_dada_list.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/analysis/analysis_nanan_dada_list.html'">
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
require(['cloud.time.input'], function(timeInput){
	timeInput.initTimeInput();
});
require(['validator.bootstrap','cloud.table.curd'], function($,tableCurd){
	tableCurd.bindSaveButton();
	
    $(function(){
        $("#form1").validate({
            rules: {
                    analysisNananTimesId : {
                    required: true,
                    number:true
                },
                    userId : {
                    required: true,
                    number:true
                },
                    userTelephone : {
                    required: true
                },
                    userName : {
                    required: true
                },
                    commentNum : {
                    required: true,
                    number:true
                },
                    praiseNum : {
                    required: true,
                    number:true
                },
                    shareNum : {
                    required: true,
                    number:true
                },
                    score : {
                    required: true,
                    number:true
                },
                    createTime : {
                    required: true,
                    date:true
                }
            },
            messages: {
                analysisNananTimesId : {
                    required: ' ',
                    number:' '
                },
                userId : {
                    required: ' ',
                    number:' '
                },
                userTelephone : {
                    required: ' '
                },
                userName : {
                    required: ' '
                },
                commentNum : {
                    required: ' ',
                    number:' '
                },
                praiseNum : {
                    required: ' ',
                    number:' '
                },
                shareNum : {
                    required: ' ',
                    number:' '
                },
                score : {
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