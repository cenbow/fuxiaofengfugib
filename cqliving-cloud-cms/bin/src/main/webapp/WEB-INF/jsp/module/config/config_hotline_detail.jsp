<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="热线列表|config_hotline_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/config_hotline/config_hotline_${empty item.id ? 'add' : 'update'}.html">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
			            <c:if test="${not empty item}">
			            	<input type="hidden" name="id" value="${item.id}" />
			            </c:if>
	                    
	                    <c:if test="${not empty appList}">
		                    <div class="form-group">
		                        <label class="col-sm-3 control-label no-padding-right" for="appId">所属APP</label>
		                        <div class="col-sm-9">
		                            <select name="appId" id="appId" class="form-control chosen-select">
									   <c:forEach items="${appList}" var="app">
		                                  <option value="${app.id}" <c:if test="${(item.appId eq app.id) or (empty item.appId and idx.first)}">selected</c:if>>${app.name}</option>
		                               </c:forEach>
									</select>
		                        </div>
		                    </div>
                    	</c:if>
                    	<c:if test="${empty appList}">
                    		<input type="hidden" id="appId" value="${appId}"/>
                    	</c:if>
                    	<div id="type-div-detail"></div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="name">热线名称<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="name" name="name" maxlength="50" placeholder="请输入热线名称"  value="${item.name}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="sortNo">排序号<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="sortNo" id="sortNo" type="text" value="${item.sortNo}" maxlength="3" placeholder="请输入排序号">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="phone">电话<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                        	<button type="button" class="btn btn-sm btn-success add-phone" <c:if test="${(not empty item.phone) and (fn:length(fn:split(item.phone, ','))>=3)}"> style="display: none;" </c:if>><i class="icon-plus"></i>添加</button>
	                            <span style="color: #d16e6c;" id="phone-error-tip">
	                            	<c:if test="${(not empty item.phone) and (fn:length(fn:split(item.phone, ','))>=3)}">
	                            		最多只能添加三个电话！
	                            	</c:if>
	                            </span>
	                        </div>
	                    </div>
	                    <div class="phones-div">
	                    <c:if test="${empty item.phone}">
	                    <div class="form-group phone-mode">
	                        <label class="col-sm-3"></label>
							<div class="col-sm-9">
								<div class="input-group">
									<input name="phones" type="text" placeholder="请输入电话号码" class="col-xs-12 form-control phones">
									<span class="input-group-btn dropdown-toggle">
										<button class="btn btn-sm btn-default remove-phone-btn" type="button" disabled="disabled">
											<span class="icon-minus" ></span>
										</button>
									</span>
								</div>
							</div>
						</div>
						</c:if>
						<c:if test="${not empty item.phone}">
							<c:forEach items="${fn:split(item.phone, ',')}" var="str">
								<div class="form-group phone-mode">
			                        <label class="col-sm-3"></label>
									<div class="col-sm-9">
										<div class="input-group">
											<input name="phones" type="text" placeholder="请输入电话号码" value="${str}" class="col-xs-12 form-control phones">
											<span class="input-group-btn dropdown-toggle">
												<button class="btn btn-sm ${fn:length(fn:split(item.phone, ','))==1 ? 'btn-default' : 'btn-danger'} remove-phone-btn" type="button" <c:if test="${fn:length(fn:split(item.phone, ','))==1}"> disabled="disabled" </c:if> >
													<span class="icon-minus" ></span>
												</button>
											</span> 
										</div>
									</div>
								</div>
                            </c:forEach>
						</c:if>
						</div>
	                	<c:if test="${empty _model_ }">
	                	<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-success btn-sm draft_save" type="button" id="commonSaveButton" back_url="/module/config/config_hotline_list.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/config/config_hotline_list.html'">
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
require(['/resource/business/config/config_hotline_detail.js'],function(detail){
	detail.init();
});
</script>