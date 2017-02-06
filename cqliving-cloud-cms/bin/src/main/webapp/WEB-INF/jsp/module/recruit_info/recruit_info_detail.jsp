<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="人才招聘表列表|recruit_info_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/recruit_info/recruit_info_${empty item.id ? 'add' : 'update'}.html">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
		            	<input type="hidden" name="id" value="${item.id}" />
		            	<c:if test="${not empty appList }">
		                    <div class="form-group">
		                        <label class="col-sm-3 control-label no-padding-right" for="appId">所属APP<i class="text-danger">*</i></label>
		                        <div class="col-sm-9">
		                            <select name="appId" id="appId" class="form-control chosen-select">
										<c:forEach items="${appList}" var="app" varStatus="idx">
		                                  <option value="${app.id}" <c:if test="${(item.appId eq app.id) or (empty item.appId and idx.first)}">selected</c:if>
		                                  >${app.name}</option>
		                               </c:forEach>
									 </select>
		                        </div>
		                    </div>
	                    </c:if>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="publicTime">发布日期<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <div class="input-prepend input-group">
	                                <input class="form-control" type="text" time_options='{"singleDatePicker":true,"format":"YYYY-MM-DD"}' name="publicTime" id="publicTime" readonly="readonly" value="<fmt:formatDate value="${item.publicTime}" pattern="yyyy-MM-dd" />">
	                                <span class="add-on input-group-addon"><i class="icon-calendar bigger-110"></i></span>
	                            </div>
	                        </div>
	                    </div>
	                    	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="position">招聘职位<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="position" name="position" maxlength="100" placeholder="请输入招聘职位"  value="${item.position}">
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="description">职位描述<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <%-- <input type="text" class="form-control" id="description" name="description" maxlength="1,000" placeholder="请输入职位描述"  value="${item.description}"> --%>
	                            <textarea class="form-control" rows="3" cols="" id="description" name="description" placeholder="请输入职位描述" maxlength="500">${item.description}</textarea>
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="entLabel">标签<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="entLabel" name="entLabel" maxlength="50" placeholder="请输入标签"  value="${item.entLabel}">
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="numberPeople">招聘人数<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="numberPeople" name="numberPeople" maxlength="100" placeholder="请输入招聘人数"  value="${item.numberPeople}">
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="education">学历<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <select id="education" name="education" placeholder="请选学历" value="${item.education}" class="form-control">
								   <c:forEach items="${educationList}" var="obj" varStatus="idx">
	                                  <option value="${obj.code}" <c:if test="${item.education eq obj.code }">selected</c:if> >${obj.name}</option>
	                               </c:forEach>
								 </select>
	                        </div>
	                    </div>
	                    	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="workmode">工作性质<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <select id="workmode" name="workmode" placeholder="请选工作性质" value="${item.workmode}" class="form-control">
								   <c:forEach items="${workmodeList}" var="obj" varStatus="idx">
	                                  <option value="${obj.code}" <c:if test="${item.workmode eq obj.code }">selected</c:if>>${obj.name}</option>
	                               </c:forEach>
								 </select>
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="salary">职位月薪<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <select id="salary" name="salary" placeholder="请选月薪" value="${item.salary}" class="form-control">
								   <c:forEach items="${salaryList}" var="obj" varStatus="idx">
	                                  <option value="${obj.code}" <c:if test="${item.salary eq obj.code }">selected</c:if>>${obj.name}</option>
	                               </c:forEach>
								 </select>
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="name">企业名称<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="name" name="name" maxlength="100" placeholder="请输入企业名称"  value="${item.name}">
	                        </div>
	                    </div>	                    
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="nature">企业性质<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <select id="nature" name="nature" placeholder="请选择企业性质" value="${item.nature}" class="form-control">
								   <c:forEach items="${natureList}" var="obj" varStatus="idx">
	                                  <option value="${obj.code}" <c:if test="${item.nature eq obj.code }">selected</c:if>>${obj.name}</option>
	                               </c:forEach>
								 </select>
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="scale">企业规模<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <select id="scale" name="scale" placeholder="请选企业规模" value="${item.scale}" class="form-control">
								   <c:forEach items="${scaleList}" var="obj" varStatus="idx">
	                                  <option value="${obj.code}" <c:if test="${item.scale eq obj.code }">selected</c:if>>${obj.name}</option>
	                               </c:forEach>
								 </select>
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="telephone">联系电话<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="telephone" name="telephone" maxlength="100" placeholder="请输入联系电话"  value="${item.telephone}">
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="address">联系地址<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="address" name="address" maxlength="100" placeholder="请输入联系地址"  value="${item.address}">
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="synopsis">企业简介<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <%-- <input type="text" class="form-control" id="synopsis" name="synopsis" maxlength="0" placeholder="请输入企业简介"  value="${item.synopsis}"> --%>
	                            <textarea class="form-control" rows="5" cols="" id="synopsis" name="synopsis" placeholder="请输入企业简介" maxlength="2000">${item.synopsis}</textarea>
	                        </div>
	                    </div>	                    
	                    
	                    <%-- <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="sortNo">排序号<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="sortNo" id="sortNo" type="text" value="${item.sortNo}" maxlength="10" placeholder="请输入排序号">
	                        </div>
	                    </div> --%>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" >企业照片</label>
	                        <div class="col-sm-9">
	                   			<div id="img_upload" title="点击上传">
		                   			<i class="icon-cloud-upload"></i>
		                        </div>
		                        <div id="imgView">
		                             <ul class="ace-thumbnails">
		                             <c:if test="${not empty imageList}">
		                             	<c:forEach items="${imageList}" var="obj" varStatus="idx">
		                                 <li class="upload-imgs">
		                                 	<a href="${obj.url}" data-rel="colorbox">
									 			<img alt="150x150" style="width:150px;height:150px;" src="${obj.url}">
										 	</a>
				                            <input type="hidden" name="urls" value="${obj.url}">
											<div class="tools tools-top">
												<a href="javascript:;">
													<i class="icon-remove red"></i>
													</a>
												</div>
										 </li>
		                                </c:forEach>
									 </c:if>
		                             </ul>
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
									<button class="btn btn-success btn-sm draft_save" type="button" id="commonSaveButton" back_url="/module/recruit_info/recruit_info_list.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/recruit_info/recruit_info_list.html'">
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
var imageUrl = '${imageUrl}';
require(['/resource/business/recruit_info/recruit_info_detail.js'],function(recruit){
	recruit.init();
});
</script>