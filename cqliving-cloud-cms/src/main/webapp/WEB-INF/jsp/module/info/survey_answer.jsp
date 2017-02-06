<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div id="modal-form-add-answer" class="modal" tabindex="-1" data-backdrop="false" answerid="${answer.id }" quesid="${answer.questionId}">

    <input type="hidden" name="createTime" value="<fmt:formatDate value='${answer.createTime }' pattern='yyyy-HH-dd HH:mm:ss'/>"/>
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">添加选项</h4>
			</div>
			<div class="modal-body overflow-visible">
				<div class="row">
				  <form id="survey_item_form">
					<div class="col-xs-12 col-sm-12">
						<div class="form-group col-xs-12">
	                        <label class="col-sm-2 control-label no-padding-right" for="answer">选项描述</label>
	                        <div class="col-sm-8">
	                            <textarea class="form-control" name="answer" id="answer" type="text" maxlength="500" placeholder="请输入选项">${answer.answer }</textarea>
	                        </div>
	                    </div>
						
						<div class="form-group col-xs-12">
	                        <label class="col-sm-2 control-label no-padding-right" for="answerinitValue">初始参与量</label>
	                        <div class="col-sm-8">
	                            <input class="form-control" name="answerinitValue" id="answerinitValue" type="text" maxlength="10" placeholder="请输入选项" value="${answer.initValue }"/>
	                        </div>
	                    </div>
						
						<div class="col-sm-12 checkbox">
                            <label>
                                <input type="checkbox" class="ace ace-checkbox-2" name="answerType" value="1" id="answerType" ${answer.type eq 1 ? 'checked' :'' }><span class="lbl">用户自填答案</span>
                            </label>
                        </div>
						
						<div class="form-group col-xs-12">
							<label class="col-sm-2 control-label no-padding-right" for="initNum">添加图片</label>
	                        <div class="col-sm-8" id="answer_img_container">
	                            <input type="hidden" class="form-control" name="answer_img_url" value="${answer.imageUrl }">
	                            <i class="icon-cloud-upload"></i>
									上传图片
	                        </div>
						</div>
						
						<div class="form-group">
	                        <label class="col-xs-12 col-sm-3 col-md-2 control-label"></label>
	                        <div class="col-sm-9" id="answer_img_thum">
	                          <c:if test="${not empty  answer.imageUrl}">
								<ul class="ace-thumbnails">
								   <c:forEach items="${fn:split(answer.imageUrl,',')}" var="img">
								      <li>
										   <img alt="150x150" src="${img}" style="width:110px;height:110px;">
											<div class="tools tools-top">
												<a href="javascript:;"> <i class="icon-remove red"></i></a>
											</div>
										</li>
								   </c:forEach>
								</ul>
							  </c:if>
							</div>
	                    </div>
						
					</div>
				  </form>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-sm" data-dismiss="modal">
					<i class="icon-remove"></i>
					取消
				</button>
				<button class="btn btn-sm btn-primary">
					<i class="icon-ok"></i>
					确定
				</button>
			</div>
		</div>
	</div>
</div><!-- PAGE CONTENT ENDS -->
