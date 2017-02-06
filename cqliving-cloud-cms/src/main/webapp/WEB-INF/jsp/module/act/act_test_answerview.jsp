<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"></jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12  version-view">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">活动答题问题表ID（act_test_question表主键）</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.actTestQuestionId}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">答案</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.answer}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">答案描述</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.answerDesc}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">答案图片，多个用,分隔</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.imageUrl}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">答案类型</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.type}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">是否正确答案</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.isRightAnswer}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">排序号</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                ${item.sortNo}
                            </p>
                        </div>
                    </div>
                    <div class="form-group  col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right">创建时间</label>
                        <div class="col-sm-8">
                            <p class="form-control-static">
                                <fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" />
                            </p>
                        </div>
                    </div>
		            <div class="form-group col-xs-12">
						<div class="text-right">
							<button class="btn btn-sm btn-info" type="button" onclick="javascript:location.href='/module/act/act_test_answerlist.html'">
								<i class="icon-undo bigger-110"></i>返回
							</button>
						</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
