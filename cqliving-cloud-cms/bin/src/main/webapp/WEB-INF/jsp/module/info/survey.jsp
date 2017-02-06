<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div id="survey_modal_form" class="modal" tabindex="-1">
    <input type="hidden" name="id" value="${surveyInfoDto.id }"/>
    <input type="hidden" name="informationContentId" value="${surveyInfoDto.informationContentId }"/>
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<span class="blue bigger"><strong>添加调研</strong></span>
				<a class="btn blue btn-primary btn-sm survey_question_modal" href="javascript:;" role="button">
					<i class="icon-plus align-top"></i>
					添加问题
				</a>
			</div>
			<div class="modal-body overflow-visible">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
                        <div class="table-responsive">
							<table class="table table-striped table-bordered table-hover dataTable">
								<thead>
				                    <tr>
				                		<th>配置规则</th>
				                    </tr>
				                </thead>
								<tbody>
				                    
				                    <tr>
				                        <td>
				                            <div class="col-sm-12 checkbox">
						                                                      调研控制：
					                            <label>
					                                <input type="checkbox" class="ace ace-checkbox-2" name="loggedStatus" value="1" id="loggedStatus"  ${surveyInfoDto.loggedStatus eq 1 ? 'checked' : ''}><span class="lbl"> 允许匿名投票</span>
					                            </label>
					                        </div>
				                        </td>
				                    </tr>
				                    <tr>
				                        <td>
				                            <div class="col-sm-12">
						                                                      调研主题：
					                                <input type="text" class="ace form-control limited" name="title" value="${surveyInfoDto.title}">
					                        </div>
				                        </td>
				                    </tr>
				                    
				                    <tr>
				                       <td>
				                          <div class="col-xs-12 col-sm-12 widget-container-span ui-sortable survey_item_div">
				                          
				                              <c:forEach items="${surveyInfoDto.surveyQuestionDtos }" var="ques">
													<div style="opacity: 1;" ques_id="${ques.id }" class="widget-box collapsed">
														<div class="widget-header">
															<h5>阿斯顿发放的&nbsp;&nbsp;&nbsp;&nbsp; 单选</h5>
															<div class="widget-toolbar">
																<a href="#" data-action="collapse"><i class="icon-chevron-down"></i></a>
																<a class="add_answer_a" href="javascript:;"><i class="icon-plus"></i>答案</a>
																<a class="btn-sm question_edit_a" href="javascript:;">编辑</a>
																<a href="#" data-action="close"><i class="icon-remove"></i>删除</a>
															</div>
														</div>
														<div class="widget-body">
															<div class="widget-body-inner">
																<div class="widget-main">
																  <c:forEach items="${ques.surveyQuestionAnswers }" var="ans">
																     <div class="alert alert-info" answer_id="${ans.id }">
																		阿斯蒂芬&nbsp;&nbsp;&nbsp;&nbsp;初始量1111
																		<div class="widget-toolbar">
																			<a href="javascript:;" class="btn-sm answer_edit_a">编辑</a>
																			<a class="answer_delete_a" href="javascript:;"><i class="icon-remove"></i>删除</a>
																		</div>
																	 </div>
																  </c:forEach>
																</div>
															</div>
														</div>
													</div>
												</c:forEach>
				                          
										  </div>
				                       </td>
				                    </tr>
				                </tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div class="modal-footer">
				<button class="btn btn-sm" data-dismiss="modal">
					<i class="icon-remove"></i>
					取消
				</button>

				<button class="btn btn-sm btn-primary">
					<i class="icon-ok"></i>
					保存
				</button>
			</div>
		</div>
	</div>
</div><!-- PAGE CONTENT ENDS -->

<div class="add_survey_question_modal"></div>
<div class="add_survey_answer_modal"></div>

<script type="text/javascript">
  require(["/resource/business/info/info_survey.js?v=v1"]);
</script>
