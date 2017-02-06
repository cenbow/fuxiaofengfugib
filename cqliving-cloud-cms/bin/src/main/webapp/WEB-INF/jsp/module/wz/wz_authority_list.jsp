<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="问政权限" name="_breadcrumbs_1"/>
	</jsp:include>
	
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<form class="form-horizontal" role="form" action="/module/wz/wz_authority_save.html" id="myForm">
					<c:if test="${not empty appList }">
						<div class="form-group <c:if test="${fn:length(appList) eq 1 }">hide</c:if>">
							<label class="col-xs-12 col-sm-3 col-md-2 control-label">所属APP</label>
							<div class="col-sm-9">
								<select name="appId" id="appId" class="form-control chosen-select" data-placeholder="请选择所属APP">
	                               <c:forEach items="${appList}" var="it">
	                                  <option value="${it.id }" <c:if test="${appId eq it.id }">selected</c:if>  >${it.name}</option>
	                               </c:forEach>
		                        </select>
							</div>
						</div>
					</c:if>
					<c:if test="${empty appList }">
						<input type="hidden" name="appId" value="${appId }">
					</c:if>
					<c:if test="${fn:length(list) > 0 }">
						<c:forEach items="${list}" var="item">
							<c:set value="" var="typeValue"/>
							<c:set var="wzAppAuthorityDto" value="${item.wzAppAuthorityDto }"/>
							<c:if test="${wzAppAuthorityDto != null }">
								<c:set var="typeValue" value="${wzAppAuthorityDto.value }"/>
							</c:if>
							<div class="form-group parent-div div-type-${item.type }" data-id="${item.id }" data-type="${item.type }">
								<label class="col-xs-12 col-sm-3 col-md-2 control-label">${item.description }</label>
								<div class="col-sm-9 radio">
									<label class="radio-2">
		                                <input type="radio" class="ace" name="${item.id }" value="0" <c:if test="${typeValue == 0 }">checked="checked"</c:if>><span class="lbl"> 否</span>
		                            </label>
		                            <label class="radio-2">
		                                <input type="radio" class="ace" name="${item.id }" value="1" <c:if test="${typeValue == 1 }">checked="checked"</c:if>><span class="lbl"> 是</span>
		                            </label>
								</div>
							</div>
							<c:if test="${item.type == 2 || item.type == 3}">
									<c:set var="collectInfoList" value="${item.wzAppAuthorityDto.collectInfoList }"/>
									<div id="div-id-${item.id }" <c:if test="${typeValue != 1 }">class="hide"</c:if>>
										<c:if test="${not empty collectInfoList}">
											<c:forEach var="collectInfo" items="${collectInfoList }">
												<div class="form-group div-collect">
													<c:if test="${item.type == 3 }">
														<label class="col-xs-12 col-sm-3 col-md-2 control-label">收集信息名称</label>
														<div class="col-sm-9 input-group">
															<input type="hidden" value="${item.id }" name="authorityId"/>
															<input type="hidden" value="${collectInfo.id }" name="collectInfoId"/>
															<input type="text" placeholder="请输入内容" class="col-sm-10 input-mask-product" name="collectInfoName" value="${collectInfo.name }"/>
															<label class="col-sm-2"><input type="checkbox" class="ace isRequired" <c:if test="${collectInfo.isRequired == 1 }">checked</c:if>><span class="lbl"> 必填</span><input type="hidden" name="isRequired" value="${collectInfo.isRequired }"></label>
															<span class="input-group-btn">
																<button class="btn btn-xs btn-warning type-3-btn btn-input" type="button">
																	<i class="icon-minus"></i>
																</button>
															</span>
														</div>
													</c:if>
													<c:if test="${item.type == 2 }">
														<label class="col-xs-12 col-sm-3 col-md-2 control-label">回复内容</label>
														<div class="col-sm-9">
															<input type="hidden" value="${item.id }" name="authorityId"/>
															<input type="hidden" value="${collectInfo.id }" name="collectInfoId"/>
															<input type="hidden" name="isRequired" value="0">
															<textarea class="form-control" placeholder="" name="collectInfoName">${collectInfo.name }</textarea>
														</div>
													</c:if>
												</div>
											</c:forEach>
										</c:if>
										<c:if test="${empty collectInfoList && item.type == 2 }">
											<div class="form-group">
												<label class="col-xs-12 col-sm-3 col-md-2 control-label">回复内容</label>
												<c:if test="${item.type == 2 }">
													<div class="col-sm-9">
														<input type="hidden" value="${item.id }" name="authorityId"/>
														<input type="hidden" value="" name="collectInfoId"/>
														<input type="hidden" name="isRequired" value="0">
														<textarea class="form-control" placeholder="最多只能输入50个字符" name="collectInfoName" maxlength="50"></textarea>
													</div>
												</c:if>
											</div>
										</c:if>
										<c:if test="${item.type == 3 }">
											<div class="form-group div-collect">
												<label class="col-xs-12 col-sm-3 col-md-2 control-label">收集信息名称</label>
												<div class="col-sm-9 input-group">
													<input type="hidden" value="${item.id }" name="authorityId"/>
													<input type="hidden" value="" name="collectInfoId"/>
													<input type="text" placeholder="请输入内容" class="col-sm-10 input-mask-product" name="collectInfoName"/>
													<label class="col-sm-2"><input type="checkbox" class="ace isRequired"><span class="lbl"> 必填</span><input type="hidden" name="isRequired" value="0"></label>
													<span class="input-group-btn">
														<button class="btn btn-xs btn-primary type-3-btn btn-input" type="button">
															<i class="icon-plus"></i>
														</button>
													</span>
												</div>
											</div>
										</c:if>
									</div>
								</c:if>
						</c:forEach>
						<div class="form-group col-sm-11">
							<div class="text-right">
								<button class="btn btn-success btn-sm" type="button" id="submitBtn">
									<i class="icon-save bigger-110"></i>
										保存设置
								</button>
							</div>
						</div>
					</c:if>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
require(['/resource/business/wz/wzAuthorityList.js'], function(obj){
	obj.init();
});
</script>