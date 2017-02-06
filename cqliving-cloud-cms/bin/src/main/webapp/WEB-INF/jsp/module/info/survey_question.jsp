<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div id="modal-form-add-question" class="modal" tabindex="-1" data-backdrop="false"  quesid="${question.id}">

    <input type="hidden" name="createTime" value="<fmt:formatDate value='${question.createTime }' pattern='yyyy-HH-dd HH:mm:ss'/>"/>
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">添加投票项</h4>
			</div>
			<div class="modal-body overflow-visible">
				<div class="row">
				  <form id="survey_item_form">
					<div class="col-xs-12 col-sm-12">
						<div class="form-group col-xs-12">
	                        <label class="col-sm-2 control-label no-padding-right" for="initCount">问题描述</label>
	                        <div class="col-sm-8">
	                            <textarea class="form-control" name="question" id="question" type="text" maxlength="500" placeholder="请输入问题描述">${question.question }</textarea>
	                        </div>
	                    </div>
						<div class="form-group col-xs-12">
							<label class="col-sm-2 control-label no-padding-right" for="type">投票项</label>
	                        <div class="col-sm-8 radio">
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="ques_type" value="0" id="ques_type0"><span class="lbl"> 单选</span>
	                            </label>
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="ques_type" value="3" id="ques_type3"><span class="lbl"> 多选</span>
	                            </label>
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="ques_type" value="5" id="ques_type5"><span class="lbl"> 文本</span>
	                            </label>
	                        </div>
						</div>
						
						<script type="text/javascript">
                                document.getElementById("ques_type${empty question.type ? 0 : question.type}").checked=true;
                        </script>
                        
						<div class="form-group col-xs-12">
							<label class="col-sm-2 control-label no-padding-right" for="initNum">添加图片</label>
	                        <div class="col-sm-8" id="question_img_container">
	                            <input type="hidden" class="form-control" name="question_img_url" value="${question.images }">
	                            <i class="icon-cloud-upload"></i>
									上传问题图片
	                        </div>
						</div>
						
						<div class="form-group">
	                        <label class="col-xs-12 col-sm-3 col-md-2 control-label"></label>
	                        <div class="col-sm-9" id="question_img_thum">
	                          <c:if test="${not empty question.images}">
	                           <ul class="ace-thumbnails">
								   <c:forEach items="${fn:split(question.images,',')}" var="img">
								      <li>
										   <img alt="110x110" src="${img}" style="width:110px;height:110px;">
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