<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="人才招聘表列表|recruit_info_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="查看" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">所属APP</label>
	                        <div class="col-sm-9">
								<c:forEach items="${appList}" var="app" varStatus="idx">
	                               <c:if test="${item.appId eq app.id}">
		                           		<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${app.name}">
	                               </c:if>
	                            </c:forEach>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">发布日期</label>
	                            <div class="col-sm-9">
	                            <div class="input-prepend input-group">
	                            	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value="${item.publicTime}" pattern="yyyy-MM-dd" />">
	                            </div>
	                        </div>
	                    </div>
	                    	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">招聘职位</label>
	                            <div class="col-sm-9">
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.position}">
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">职位描述</label>
	                            <div class="col-sm-9">
	                            <textarea class="col-xs-10 form-control col-sm-5" rows="3" cols="" id="description" disabled="disabled" name="description" placeholder="请输入职位描述" maxlength="500">${item.description}</textarea>
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">标签</label>
	                            <div class="col-sm-9">
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.entLabel}">
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">招聘人数</label>
	                            <div class="col-sm-9">
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.numberPeople}">
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="education">学历</label>
	                            <div class="col-sm-9">
							    <c:forEach items="${educationList}" var="obj" varStatus="idx">
                                   <c:if test="${item.education eq obj.code}">
			                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${obj.name}">
                                   </c:if>
                                </c:forEach>
	                        </div>
	                    </div>
	                    	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="workmode">工作性质</label>
	                        <div class="col-sm-9">
	                        	<c:forEach items="${workmodeList}" var="obj" varStatus="idx">
                                   <c:if test="${item.workmode eq obj.code}">
			                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${obj.name}">
                                   </c:if>
                                </c:forEach>
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="salary">职位月薪</label>
	                        <div class="col-sm-9">
	                        	<c:forEach items="${salaryList}" var="obj" varStatus="idx">
                                   <c:if test="${item.salary eq obj.code}">
			                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${obj.name}">
                                   </c:if>
                                </c:forEach>
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="name">企业名称</label>
	                        <div class="col-sm-9">
			                    <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.name}">
	                        </div>
	                    </div>	                    
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="nature">企业性质</label>
	                        <div class="col-sm-9">
	                        	<c:forEach items="${natureList}" var="obj" varStatus="idx">
                                   <c:if test="${item.nature eq obj.code}">
			                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${obj.name}">
                                   </c:if>
                                </c:forEach>
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="scale">企业规模</label>
	                        <div class="col-sm-9">
	                            <c:forEach items="${scaleList}" var="obj" varStatus="idx">
                                   <c:if test="${item.scale eq obj.code}">
			                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${obj.name}">
                                   </c:if>
                                </c:forEach>
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="telephone">联系电话</label>
	                        <div class="col-sm-9">
			                    <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.telephone}">
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="address">联系地址</label>
	                        <div class="col-sm-9">
			                    <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.address}">
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="synopsis">企业简介</label>
	                            <div class="col-sm-9">
	                            <textarea class="col-xs-10 form-control col-sm-5" rows="5" cols="" disabled="disabled">${item.synopsis}</textarea>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" >企业照片</label>
	                            <div class="col-sm-9">
			                        <div>
			                             <ul class="ace-thumbnails">
			                             <c:if test="${not empty imageList}">
			                             	<c:forEach items="${imageList}" var="obj" varStatus="idx">
			                                 <li class="upload-imgs">
			                                 	<a href="${obj.url}" data-rel="colorbox">
											 		<img alt="150x150" style="width:150px;height:150px;" src="${obj.url}">
											 	</a>
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
require(['common_colorbox'], function(colorbox){
	colorbox.init();
});
</script>