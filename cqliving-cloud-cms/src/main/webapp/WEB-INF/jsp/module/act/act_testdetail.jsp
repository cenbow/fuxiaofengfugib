<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="活动列表|/module/act/act_infolist.html" name="_breadcrumbs_1" />
		<jsp:param value="活动管理|/module/act/act_infoadd.html?id=${actInfoId}" name="_breadcrumbs_2" />
		<jsp:param value="答题管理" name="_breadcrumbs_3" />
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail fieldset-form">
				<%-- 详细 --%>
				<form class="form-horizontal form" method="post" id="form1" action="<c:choose><c:when test="${not empty actTest }">/module/act/act_testupdate.html</c:when><c:otherwise>/module/act/act_testadd.html</c:otherwise></c:choose>">
					<input type="hidden" name="id" value="${actTest.id}" id="actTestId"/>
					<input type="hidden" name="actInfoId" id="actInfoId" value="${actInfoId }" />
					<input type="hidden" name="actInfoListId" id="actInfoListId" value="${actInfoListId }" />
					<fieldset>
						<legend>基本信息</legend>
						<div class="form-group">
							<label class="col-sm-2 control-label no-padding-right" for="type">类型</label>
							<div class="col-sm-9 radio" id="typeDiv">
								<label class="radio-2"> <input type="radio" id="type1" class="ace" name="type" value="1" checked><span class="lbl">普通答题</span></label>
								<label class="radio-2"> <input type="radio" id="type2" class="ace" name="type" value="2"><span class="lbl">分类答题</span></label>
								<script type="text/javascript">
									document.getElementById("type${empty actTest ? 1 : actTest.type}").checked = true;
								</script>
							</div>
						</div>
						<div class="form-group">
							<c:choose>
								<c:when test="${empty actTest.startTime  && not empty actInfo }">
									<c:set var="tmpTime" value="${actInfo.startTime }"/>
								</c:when>
								<c:otherwise>
									<c:set var="tmpTime" value="${actTest.startTime }"/>
								</c:otherwise>
							</c:choose>
							<label class="col-sm-2 control-label no-padding-right" for="startTime">活动开始时间</label>
							<div class="input-prepend input-group col-sm-9">
								<input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD HH:mm:ss","timePicker": true,"timePicker12Hour": false,"timePickerIncrement":1}' name="startTimeTmp" id="startTime" readonly="readonly" value="<fmt:formatDate value="${tmpTime}" pattern="yyyy-MM-dd HH:mm:ss" />">
								<span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label no-padding-right" for="endTime">活动结束时间</label>
							<div class="input-prepend input-group col-sm-9">
								<c:choose>
									<c:when test="${empty actTest.endTime  && not empty actInfo }">
										<c:set var="tmpTime" value="${actInfo.endTime }"/>
									</c:when>
									<c:otherwise>
										<c:set var="tmpTime" value="${actTest.endTime }"/>
									</c:otherwise>
								</c:choose>
								<input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD HH:mm:ss","timePicker": true,"timePicker12Hour": false,"timePickerIncrement":1}' name="endTimeTmp" id="endTime" readonly="readonly" value="<fmt:formatDate value="${tmpTime}" pattern="yyyy-MM-dd HH:mm:ss" />">
								<span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label no-padding-right" for="limitAnswerType">时间限制</label>
							<div class="col-sm-9 radio">
								<label class="radio-2">
									<input type="radio" class="ace" name="limitAnswerType" value="1" id="limitAnswerType1">
									<span class="lbl"> 限制答题时间</span>
									<input type="text" name="limitAnswerTimes" value="${actTest.limitAnswerTimes }" class="input-mini">
									<span>分钟</span>
								</label>
								
								<label class="radio-2">
									<input type="radio" class="ace" name="limitAnswerType" value="0" id="limitAnswerType0">
									<span class="lbl"> 不限制，允许中途离开并保存</span>
								</label>
								<script type="text/javascript">
									document.getElementById("limitAnswerType${empty actTest ? 0 : actTest.limitAnswerType}").checked = true;
								</script>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label no-padding-right" for="loggedStatus">是否登陆后才能操作</label>
							<div class="col-sm-9 radio">
								<label class="radio-2"> <input type="radio" class="ace" name="loggedStatus" value="0" id="loggedStatus0"><span class="lbl"> 否</span></label>
								<label class="radio-2"> <input type="radio" class="ace" name="loggedStatus" value="1" id="loggedStatus1"><span class="lbl"> 是</span></label>
								<script type="text/javascript">
									document.getElementById("loggedStatus${empty actTest ? 0 : actTest.loggedStatus}").checked = true;
								</script>
							</div>
						</div>
						<div class="form-group hide">
							<label class="col-sm-2 control-label no-padding-right">模板</label>
							<div class="col-sm-9 radio">
								<c:forEach var="it" items="${templateList }" varStatus="i">
									<label class="radio-2"> 
										<input type="radio" class="ace" name="actTemplateCode" value="${it.templetCode }" <c:if test="${i.index == 0 }">checked</c:if>>
										<span class="lbl ace-thumbnails">
											<a href="${it.imageUrl }" data-rel="colorbox">
												<img alt="${it.name }" src="${it.imageUrl }" style="width: 110px;height:110px;">
											</a>
										</span>
									</label>
								</c:forEach>
							</div>
						</div>
					</fieldset>
					
					<fieldset>
						<legend>用户信息</legend>
						<div class="col-sm-10">
							<label class="col-sm-2 control-label padding-right"><button type="button" class="btn btn-minier btn-primary editBtn" id="collentInfoEditBtn"><i class="icon-edit bigger-110"></i>编辑</button></label>
						</div>
						<div class="form-group" id="userCollentInfoDiv">
							<label class="col-xs-3"></label>
							<div class="content col-sm-9">
								<c:if test="${not empty actTestCollentList }">
									<c:forEach var="it" items="${actTestCollentList }" varStatus="index">
										<c:if test="${not empty collectInfoList }">
											<c:forEach var="tmp" items="${collectInfoList }">
												<c:if test="${tmp.id == it.actCollectInfoId }">
													<div>
														<label></label>
														<div class="checkbox col-xs-10">
															<label class="col-xs-5"><input type="checkbox" name="collectInfoIds" checked class="ace" value="${it.actCollectInfoId }"/><span class="lbl"> ${tmp.name }</span></label>
															<label class="col-xs-5"><input type="checkbox" class="ace collectInfoRequired" <c:if test="${it.isRequired == 1 }">checked</c:if>/><span class="lbl"> 必填</span><input type="hidden" name="collectInfoRequired" value="${it.isRequired }"></label>
														</div>
													</div>
													<c:if test="${index.index < fn:length(actTestCollentList)-1 }"><hr /></c:if>
												</c:if>
											</c:forEach>
										</c:if>
									</c:forEach>
								</c:if>
							</div>
						</div>
					</fieldset>
					
					<fieldset>
						<legend>分类信息</legend>
						<div class="form-group" id="classifyDiv">
							<%@include file="act_testdetail_classify_page.jsp" %>
						</div>
						<div class="col-sm-10">
							<label class="col-sm-2 control-label padding-right"><button type="button" class="btn btn-minier btn-primary <c:if test="${not empty classifyList && fn:length(classifyList) > 0 && actTest.type == 1 }">hide</c:if>" id="addClassifyBtn"><i class="fa fa-plus"></i>分类</button></label>
						</div>
					</fieldset>
					
					<div class="form-group col-xs-12 text-right">
						<div class="space-4"></div>
						<button class="btn btn-success btn-sm draft_save" type="button" id="actTestSaveButton" back_url="/module/act/act_testupdate.html">
							<i class="icon-save bigger-110"></i>保存
						</button> &nbsp;
						<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/act/act_infoadd.html?id=${actInfoId}'">
							<i class="icon-undo bigger-110"></i>返回
						</button>
					</div>
				</form>
			</div>
		</div>
		<!-- /.row -->
		<!-- /.main-content -->
	</div>
	<!-- /.page-content -->
</div>

<!-- 添加分类的临时DIV -->
<div class="hide" id="classifyAddDiv">
	<fieldset>
		<input type="hidden" class="classifyId" name="classifyId">
		<input type="hidden" class="sortNo" name="sortNo">
		<legend>分类编辑</legend>
		<label class="col-sm-1 control-label no-padding-right">分类标题：</label>
		<div class="col-xs-11 inline">
			<input type="text" class="col-xs-2 title" maxlength="10" placeholder="不超过10个字"/>
			<span class="col-xs-3">
				<label>内容：</label>
				<span class="middle content"></span>
			</span>
			<span class="col-xs-3">
				<label class="radio-2"> <input type="radio" class="ace" name="isSetScore" value="1" checked><span class="lbl">设置分值</span></label>
				<label class="radio-2"> <input type="radio" class="ace" name="isSetScore" value="0"><span class="lbl">不设置分值（显示正确率）</span></label>
			</span>
			<div class="col-xs-4">
				<button type="button" class="btn btn-minier btn-primary excelUploadBtn"><i class="fa fa-cloud-upload"></i>从excel导入</button>
				<button type="button" class="btn btn-minier btn-primary" onclick="javascript:window.location.href = '/common/downTemp.html?tempName=ACT_TEST_QUESTION.xlsx'"><i class="fa fa-cloud-download"></i>excel模板下载</button>
				<button type="button" class="btn btn-minier btn-primary classifyAddEditBtn"><i class="icon-edit bigger-110"></i>编辑</button>
				<button type="button" class="btn btn-minier btn-danger classifyAddDelBtn"><i class="fa fa-remove"></i>删除</button>
			</div>
		</div>
		<div class="text-center">
			<button type="button" class="btn btn-sm btn-success classifySureBtn"><i class="icon-save"></i>确定</button>
			<button type="button" class="btn btn-sm btn-default classifyCancelBtn"><i class="icon-remove"></i>取消</button>
		</div>
	</fieldset>
</div>

<!-- 分类添加成功的临时DIV -->
<div class="hide" id="classifyAddSuccessDiv">
	<div class="showDiv">
		<input type="hidden" name="classifyId">
		<input type="hidden" name="sortNo">
		<label class="col-sm-2 control-label "> 分类标题： </label>
		<div class="col-xs-10 inline">
			<span class="col-xs-2">
				<span class="middle title"></span>
			</span>
			<span class="col-xs-5">
				<label>内容：</label>
				<span class="middle content"></span>
			</span>
			<div class="col-xs-5">
				<button type="button" class="btn btn-minier btn-primary editBtn"><i class="icon-edit bigger-110"></i>编辑</button>
				<button type="button" class="btn btn-minier btn-primary sortBtn" data-val="up"><i class="fa fa-arrow-up"></i>上移</button>
				<button type="button" class="btn btn-minier btn-primary sortBtn" data-val="down"><i class="fa fa-arrow-down"></i>下移</button>
				<button type="button" class="btn btn-minier btn-danger delBtn"><i class="fa fa-remove"></i>删除</button>
			</div>
		</div>
	</div>
</div>

<!-- 收集信息列表 -->
<div id="collentInfoModal" class="modal fade">
	<div class="modal-dialog" style="width: 300px;">
	    <div class="modal-content" style="width: 300px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 class="ng-binding blue bigger">用户收集信息选择</h3>
            </div>
            <div class="modal-body">
            	<c:forEach var="it" items="${collectInfoList }">
            		<c:set var="tmpSelect" value=""/>
            		<c:if test="${not empty actTestCollentList }">
						<c:forEach var="tmp" items="${actTestCollentList }">
							<c:if test="${tmp.actCollectInfoId == it.id }">
								<c:set var="tmpSelect" value="checked"/>
							</c:if>
						</c:forEach>
					</c:if>
            		<label class="checkbox">
						<input type="checkbox" class="ace" value="${it.id }" data-text="${it.name }" ${tmpSelect }/>
						<span class="lbl"> ${it.name }</span>
					</label>
            	</c:forEach>
            </div>
			<div class="modal-footer">
			    <button type="button" class="btn btn-success btn-sm draft_save sureBtn"><i class="icon-save bigger-110"></i>确认</button>
			    <button type="button" class="btn btn-sm btn-danger" data-dismiss="modal"><i class="icon-remove"></i>取消</button>
			</div>
        </div>
	</div>
</div>

<input type="file" name="excelFile" id="excelFile" accept="xls,.xlsx" data-url="/module/act/common/act_test_excelupload.html" class="hide">

<script type="text/javascript">
	require([ '/resource/business/act/actTestDetail.js' ], function(obj) {
		obj.init();
	});
</script>