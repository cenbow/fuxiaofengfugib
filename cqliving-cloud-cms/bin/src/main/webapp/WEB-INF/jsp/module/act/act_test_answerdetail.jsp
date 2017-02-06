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
                        <label class="col-sm-4 control-label no-padding-right" for="actTestQuestionId">活动答题问题表ID（act_test_question表主键）</label>
                        <div class="col-sm-8">
                            <input class="form-control" name="actTestQuestionId" id="actTestQuestionId" type="text" value="${item.actTestQuestionId}" maxlength="19" placeholder="请输入活动答题问题表ID（act_test_question表主键）">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="answer">答案</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="answer" name="answer" maxlength="1,000" placeholder="请输入答案"  value="${item.answer}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="answerDesc">答案描述</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="answerDesc" name="answerDesc" maxlength="1,000" placeholder="请输入答案描述"  value="${item.answerDesc}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="imageUrl">答案图片，多个用,分隔</label>
                            <div class="col-sm-8">
                            <input type="text" class="form-control" id="imageUrl" name="imageUrl" maxlength="1,000" placeholder="请输入答案图片，多个用,分隔"  value="${item.imageUrl}">
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="type">答案类型</label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="type" value="0" id="type0"><span class="lbl"> 正常</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="type" value="1" id="type1"><span class="lbl"> 有其他输入</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("type${empty item ? 0 : item.type}").checked=true;
                            </script>
                        </div>
                    </div>
                    <div class="form-group col-xs-6">
                        <label class="col-sm-4 control-label no-padding-right" for="isRightAnswer">是否正确答案</label>
                        <div class="col-sm-8 radio">
                            <label class="radio-2">
                                <input type="radio" class="ace" name="isRightAnswer" value="0" id="isRightAnswer0"><span class="lbl"> 否</span>
                            </label>
                            <label class="radio-2">
                                <input type="radio" class="ace" name="isRightAnswer" value="1" id="isRightAnswer1"><span class="lbl"> 是</span>
                            </label>
                            <script type="text/javascript">
                                document.getElementById("isRightAnswer${empty item ? 0 : item.isRightAnswer}").checked=true;
                            </script>
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
							<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/act/act_test_answerlist.html">
								<i class="icon-save bigger-110"></i>提交
							</button>
							&nbsp;
							<button class="btn btn-sm btn-primary" type="button" onclick="javascript:location.href='/module/act/act_test_answerlist.html'">
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
                    actTestQuestionId : {
                    required: true,
                    number:true
                },
                    answer : {
                    required: true
                },
                    answerDesc : {
                    required: true
                },
                    imageUrl : {
                    required: true
                },
                    type : {
                    required: true
                },
                    isRightAnswer : {
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
                actTestQuestionId : {
                    required: ' ',
                    number:' '
                },
                answer : {
                    required: ' '
                },
                answerDesc : {
                    required: ' '
                },
                imageUrl : {
                    required: ' '
                },
                type : {
                    required: ' '
                },
                isRightAnswer : {
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