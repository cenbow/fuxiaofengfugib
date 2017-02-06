<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<style>
	.dd-handle button{margin: 0 5px 0 0;}
	/* .img_upload_btn{ width:100px !important; height:43px !important;} */
	.rootClass .question-type, .question-score, .right-answer{padding-left:50px;}
</style>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="活动列表|/module/act/act_infolist.html" name="_breadcrumbs_1" />
		<jsp:param value="活动管理|/module/act/act_infoadd.html?id=${actTest.actInfoId}" name="_breadcrumbs_2" />
		<jsp:param value="答题管理|/module/act/act_testupdate.html?actInfoId=${actTest.actInfoId }&actInfoListId=${actTest.actInfoListId }" name="_breadcrumbs_3" />
		<jsp:param value="问题管理" name="_breadcrumbs_4" />
	</jsp:include>
	
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
	        	<input type="hidden" id="actTestClassifyId" value="${actTestClassifyId }">
				<button class="btn btn-sm btn-success" type="button" id="addQuestionBtn"><i class="icon-plus"></i>添加问题</button>
				<button class="btn btn-sm btn-primary" type="button" id="expandQuestion" data-action="expand"><i class="icon-chevron-down"></i><span>收缩</span></button>
				<div class="rootClass" id="nestable">
					<ol class="dd-list" data-id="0">
						<%@include file="act_test_questionlist_page.jsp" %>
					</ol>
				</div>
				<div class="form-group col-xs-12 text-right">
					<div class="space-4"></div>
						<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/act/act_testupdate.html?id=${actTestId}'">
							<i class="icon-undo bigger-110"></i>返回
						</button>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 添加问题 -->
<div id="addQuestionModal" class="modal fade">
	<div class="modal-dialog">
	    <div class="modal-content" style="width: 650px;">
	        <form class="form-horizontal form" method="post">
	        	<input type="hidden" name="id" value="">
	        	<input type="hidden" name="actTestClassifyId" value="${actTestClassifyId }">
	        	<input type="hidden" name="sortNo" value="0">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	                <h3 class="ng-binding blue bigger">添加问题</h3>
	            </div>
	            <div class="modal-body">
	                <div class="form-group">
	                	<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 问题描述： </label>
						<div class="col-sm-9">
							<textarea placeholder="请输入问题描述，最多只能输入100个字符" class="form-control" rows="4" maxlength="100" name="question"></textarea>
						</div>
	                </div>
	                <div class="form-group hide">
	                	<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 添加图片： </label>
						<div class="col-sm-9">
							<div id="questionImgUpload" class="col-sm-4">
	                            <i class="icon-cloud-upload"></i>
								添加图片
							</div>
							<div class="col-sm-8" id="questionImgList"></div>
						</div>
	                </div>
	                <c:if test="${isSetScore eq 1 }">
		                <div class="form-group">
		                	<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 设置分值： </label>
							<div class="col-sm-9">
								<input type="number" name="score" class="form-control" min="0">
							</div>
		                </div>
	                </c:if>
	                <div class="form-group">
	                	<label class="col-sm-3 control-label no-padding-right" for="form-field-1">  </label>
						<div class="col-sm-9 radio">
							<label class="radio-2"> <input type="radio" class="ace type1" name="type" value="1" checked><span class="lbl">单选</span></label>
							<label class="radio-2"> <input type="radio" class="ace type2" name="type" value="2"><span class="lbl">多选</span></label>
							<label class="radio-2 hide"> <input type="radio" class="ace type3" name="type" value="3"><span class="lbl">文本</span></label>
						</div>
	                </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success btn-sm draft_save submitBtn"><i class="icon-save bigger-110"></i>确定</button>
                    <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal"><i class="icon-remove"></i>取消</button>
                </div>
            </form>
        </div>
	</div>
</div>

<!-- 添加答案 -->
<div id="addAnswerModal" class="modal fade">
	<div class="modal-dialog">
	    <div class="modal-content" style="width: 650px;">
	        <form class="form-horizontal form" method="post">
	        	<input type="hidden" name="id">
	        	<input type="hidden" name="actTestQuestionId">
	        	<input type="hidden" name="sortNo" value="0">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	                <h3 class="ng-binding blue bigger">添加答案</h3>
	            </div>
	            <div class="modal-body">
	            	 <div class="form-group">
	                	<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 答案： </label>
						<div class="col-sm-9">
							<input type="text" name="answer" class="form-control">
						</div>
	                </div>
	                <div class="form-group">
	                	<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 答案描述： </label>
						<div class="col-sm-9">
							<textarea placeholder="请输入问题描述，最多只能输入100个字符" name="answerDesc" class="form-control" rows="4" maxlength="100"></textarea>
						</div>
	                </div>
	                <div class="form-group hide">
	                	<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 添加图片： </label>
						<div class="col-sm-9">
							<div id="answerImgUpload" class="col-sm-4">
	                            <i class="icon-cloud-upload"></i>
								添加图片
							</div>
							<div class="col-sm-8" id="answerImgList"></div>
                        </div>
	                </div>
	                <div class="form-group">
	                	<label class="col-sm-3 control-label no-padding-right" for="form-field-1">  </label>
						<div class="checkbox">
							<label class="radio-2"> <input type="checkbox" class="ace isRightAnswer"><span class="lbl">设为正确答案</span><input type="hidden" name="isRightAnswer" value="0"></label>
						</div>
	                </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-success btn-sm draft_save submitBtn"><i class="icon-save bigger-110"></i>确定</button>
                    <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal"><i class="icon-remove"></i>取消</button>
                </div>
            </form>
        </div>
	</div>
</div>

<script type="text/javascript">
var imageUrl = '${imageUrl}';
require([ '/resource/business/act/actTestQuestionList.js' ], function(obj) {
	obj.init();
});
</script>