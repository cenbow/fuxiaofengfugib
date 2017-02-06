<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="随手拍管理|/module/shoot/shoot_info_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="查看" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12  version-view">
				<%-- 详细 --%>
		        <form class="form-horizontal form" id="shoot_info_form">
		        	<input type="hidden" name="id" value="${item.id}" />
		        	<div class="col-md-12 col-lg-8">
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">内容</label>
	                        <div class="col-sm-9">
	                           	<textarea class="form-control textarea limited" rows="10" cols="" readonly="readonly">${item.content}</textarea>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                    	<label class="col-sm-3 control-label no-padding-right">图片</label>
	                    	<div class="col-sm-9">
	                    		<div class="row">
									<div class="col-xs-12">
										<div class="row-fluid">
											<ul class="ace-thumbnails">
	                    						<c:forEach items="${images}" var="obj">
													<li>
														<a href="${obj.imageUrl}" data-rel="colorbox">
															<img alt="150x150" src="${obj.imageUrl}" style="width: 150px; height: 150px;" />
															<div class="text">
																<div class="inner">${obj.description}</div>
															</div>
														</a>
													</li>
					                    		</c:forEach>
<!-- 				                    			<div class="col-sm-6 col-md-4 col-lg-3" style="padding-top: 12px; padding-bottom: 12px;"> -->
<!-- 				        							<div class="thumbnail"> -->
<%-- 				          								<img alt="" src="${obj.imageUrl}" style="height: 200px; width: 100%; display: block;"> --%>
<!-- 				          								<div class="caption"> -->
<%-- 				            								<p>${obj.description}</p> --%>
<!-- 				          								</div> -->
<!-- 				      	 							</div> -->
<!-- 				      							</div> -->
											</ul>
										</div>
									</div>
								</div>
	                    	</div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">发布状态</label>
	                        <div class="col-sm-9">
	                             <select name="status" id="status" class="form-control" placeholder="请选择所属类型">
	                          		<option value="2" <c:if test="${item.status eq 2}">selected="selected"</c:if>>待审核</option>
	                          		<option value="3" <c:if test="${item.status eq 3}">selected="selected"</c:if>>上线</option>
	                          		<option value="88" <c:if test="${item.status eq 88}">selected="selected"</c:if>>下线</option>
	                       		</select>
	                        </div>
	                    </div>
			            <div class="form-group">
							<div class="col-xs-12">
								<div class="pull-right">
									<cqliving-security2:hasPermission name="/module/shoot/shoot_info_view_save.html">
										<button class="btn btn-sm btn-success" type="button" id="save_status_btn">
											<i class="icon-save bigger-110"></i>保存
										</button>
									</cqliving-security2:hasPermission>
									<button class="btn btn-sm btn-danger" type="button" onclick="javascript:location.href='/module/shoot/shoot_info_list.html'">
										<i class="icon-undo bigger-110"></i>返回
									</button>
								</div>
							</div>
						</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
<script type="text/javascript">
	require(["/resource/business/shoot/shootInfoView.js"], function(obj) {
		obj.init();
	});
</script>