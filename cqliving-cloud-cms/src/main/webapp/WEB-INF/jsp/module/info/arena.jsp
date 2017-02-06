<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div id="arena_modal_form" class="modal" tabindex="-1">
    
    <input type="hidden" name="id" value="${surveyInfoDto.id }"/>
    <input type="hidden" name="informationContentId" value="${surveyInfoDto.informationContentId }"/>

	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">添加打擂</h4>
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
				                            <div class="col-sm-12 radio">
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitRateType" value="1" id="limitRateType1">
					                                <span class="lbl">限制</span>
					                                <input type="text" class="input-mini" name="limitRateTimes1" value="${surveyInfoDto.limitRateType eq 1 ? surveyInfoDto.limitRateTimes : ''}"/>
					                                <span class="lbl">次</span>
					                            </label>
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitRateType" value="2" id="limitRateType2">
					                                <span class="lbl">每天限制</span>
					                                <input type="text" class="input-mini" name="limitRateTimes2" value="${surveyInfoDto.limitRateType eq 2 ? surveyInfoDto.limitRateTimes : ''}"/>
					                                <span class="lbl">次</span>
					                            </label>
					                            <label class="radio-2">
					                                <input type="radio" class="ace" name="limitRateType" value="0" id="limitRateType0"><span class="lbl">无限制</span>
					                            </label>
					                        </div>
					                        
					                        <script type="text/javascript">
					                           document.getElementById("limitRateType${empty surveyInfoDto.limitRateType ? 1 : surveyInfoDto.limitRateType}").checked=true;
					                        </script>
				                        </td>
				                    </tr>
				                    
				                    <tr>
				                        <td>
				                            <div class="col-sm-12 checkbox">          
					                            <label>投票控制：</label>
					                            <label>
						                            <input type="checkbox"  ${surveyInfoDto.loggedStatus eq 0 ? 'checked' : ''}  class="ace ace-checkbox-2" name="loggedStatus" value="0" id="loggedStatus">
						                            <span class="lbl"> 允许匿名投票</span>
						                        </label>
					                        </div>
				                        </td>
				                    </tr>
				                    
				                    <tr>
				                        <td>
				                            <div class="col-sm-12">
						                                                      问题：
					                                <input type="text" class="ace form-control limited" name="question" value="${surveyInfoDto.surveyQuestionDtos[0].question}">
					                        </div>
				                        </td>
				                    </tr>
				                    
				                    <c:forEach items="${surveyInfoDto.surveyQuestionDtos[0].surveyQuestionAnswers}" var="ans" varStatus="vs">
				                       <tr>
				                        <td>
				                            <div class="col-sm-12">
						                           ${ans.isAffirmative eq 1 ? '正方':'反方'}：
						                           
						                           <input type="hidden" name="actValue${vs.count}" value="${ans.actValue}"/>
						                           <input type="hidden" name="answerid${vs.count}" value="${ans.id }"/>
						                           <input type="hidden" name="answercreateTime${vs.count}" value="<fmt:formatDate value='${ans.createTime }' pattern='yyyy-HH-dd HH:mm:ss'/>"/>
					                                <input type="text" class="ace form-control limited" name="answer${vs.count}" value="${ans.answer}">
					                                初始量（人次）：<input type="text" class="ace form-control limited" name="initValue${vs.count}" value="${ans.initValue }">
					                        </div>
				                        </td>
				                      </tr>
				                    </c:forEach>
				                    
				                    <c:if test="${empty  surveyInfoDto}">
					                    <tr>
					                        <td>
					                            <div class="col-sm-12">
							                                                      正方：
						                                <input type="text" class="ace form-control limited" name="answer1" value="">
						                                初始量（人次）：<input type="text" class="ace form-control limited" name="initValue1" value="">
						                        </div>
					                        </td>
					                    </tr>
					                    
					                    <tr>
					                        <td>
					                            <div class="col-sm-12">
							                                                      反方：
						                                <input type="text" class="ace form-control limited" name="answer2" value="">
						                                初始量（人次）：<input type="text" class="ace form-control limited" name="initValue2" value="">
						                        </div>
					                        </td>
					                    </tr>
				                    </c:if>
				                </tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div class="modal-footer">
				<button class="btn btn-sm btn-danger" data-dismiss="modal">
					<i class="icon-remove"></i>
					取消
				</button>

				<button class="btn btn-sm btn-success">
					<i class="icon-ok"></i>
					保存
				</button>
			</div>
		</div>
	</div>
</div><!-- PAGE CONTENT ENDS -->
