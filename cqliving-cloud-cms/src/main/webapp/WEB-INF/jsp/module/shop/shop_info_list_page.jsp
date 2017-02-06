<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12">
	<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
		<div class="table-responsive">
			<table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
				<thead>
                    <tr>
                    	<th class="center">
							<label>
								<input type="checkbox" class="ace" />
								<span class="lbl"></span>
							</label>
						</th>
                		<th>店铺名称</th>
                		<th>所属区域</th>
                		<th>地址</th>
                		<th>电话</th>
                		<th>类型</th>
                		<th>状态</th>
                		<th>人均消费</th>
                    	<td>来源</td>
                    	<td>经度</td>
                    	<td>纬度</td>
                    	<td>用户手机号</td>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td class="center" sid="${item.id}">
							<label>
								<input type="checkbox" class="ace" id="${item.id}"/>
								<span class="lbl"></span>
							</label>
						</td>
                    	<td>${item.name}</td>
                    	<td>${item.regionName}</td>
                    	<td>${item.address}</td>
                    	<td>${item.telephone}</td>
                    	<td>${allTypeMap[item.typeId]}</td>
                        <td>
                        	<c:if test="${item.sourceType eq 1 }">
                        		<c:choose>
									<c:when test="${item.status eq 88}">
			                        	<span class="label label-danger">${allStatuss[item.status]}</span>
									</c:when>
									<c:when test="${item.status eq 1}">
			                        	<span class="label label-warning">${allStatuss[item.status]}</span>
									</c:when>
									<c:when test="${item.status eq 3}">
			                        	<span class="label label-success">${allStatuss[item.status]}</span>
									</c:when>
									<c:otherwise>
			                        	<span class="label label-info">${allStatuss[item.status]}</span>
									</c:otherwise>
	                        	</c:choose>
                        	</c:if>
                        	<c:if test="${item.sourceType eq 2 }">
                        		<c:choose>
									<c:when test="${item.status eq 88}">
			                        	<span class="label label-danger">${allAppStatuss[item.status]}</span>
									</c:when>
									<c:when test="${item.status eq -1}">
			                        	<span class="label label-danger auditReject">${allAppStatuss[item.status]}<p class="hide">${item.auditDesc }<p></span>
									</c:when>
									<c:when test="${item.status eq 1}">
			                        	<span class="label label-warning">${allAppStatuss[item.status]}</span>
									</c:when>
									<c:when test="${item.status eq 3}">
			                        	<span class="label label-success">${allAppStatuss[item.status]}</span>
									</c:when>
									<c:otherwise>
			                        	<span class="label label-info">${allAppStatuss[item.status]}</span>
									</c:otherwise>
	                        	</c:choose>
                        	</c:if>
                        </td>
                		<td><fmt:formatNumber type="currency" value="${item.price / 100}"></fmt:formatNumber></td>
                    	<td>
                    		<c:choose>
                    			<c:when test="${item.sourceType eq 1 }">
                    				<span class="label label-success">${allSourceTypes[item.sourceType] }</span>
                    			</c:when>
                    			<c:otherwise>
                    				<span class="label label-info">${allSourceTypes[item.sourceType] }</span>
                    			</c:otherwise>
                    		</c:choose>
                    	</td>
                    	<td>${item.lng }</td>
                    	<td>${item.lat }</td>
                    	<td>${item.createPhone }</td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                            	<c:if test="${item.topType eq 0 && item.status eq 3}">
	                            	<cqliving-security2:hasPermission name="/module/shop/shop_info_top.html">
		                        		<a class="blue top_btn" href="javascript:void(0);" url="/module/shop/shop_info_top.html?id=${item.id}" data-rel="tooltip" data-original-title="置顶" data-placement="top">
											<i class="icon-double-angle-up bigger-130"></i>
										</a>
									</cqliving-security2:hasPermission>
                            	</c:if>
                            	<c:if test="${item.topType eq 1 && item.status eq 3}">
	                            	<cqliving-security2:hasPermission name="/module/shop/shop_info_untop.html">
		                        		<a class="red untop_btn" href="javascript:void(0);" url="/module/shop/shop_info_untop.html?id=${item.id}" data-rel="tooltip" data-original-title="取消置顶" data-placement="top">
											<i class="icon-double-angle-down bigger-130"></i>
										</a>
									</cqliving-security2:hasPermission>
                            	</c:if>
                            	<c:if test="${empty item.recommendId}">
									<cqliving-security2:hasPermission name="/module/shop/recommend.html">
										<a class="blue recommend" href="javascript:void(0);" url="recommend.html?id=${item.id }&_model_=_model" open-model="update" open-title="推荐到首页" data-rel="tooltip" data-original-title="推荐到首页" data-placement="top">
											<i class="icon-level-up bigger-130"></i>
										</a>
									</cqliving-security2:hasPermission>
								</c:if>
								<cqliving-security2:hasPermission name="/module/shop/shop_info_update.html">
	                        		<a class="blue" href="shop_info_update.html?id=${item.id}" data-rel="tooltip" data-original-title="编辑" data-placement="top">
										<i class="icon-pencil bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
	                            
	                            
                            	<c:if test="${item.sourceType eq 2 && (item.status eq 1 || item.status eq -1) }">
	                            	<cqliving-security2:hasPermission name="/module/shop/shop_info_audit.html">
		                        		<a class="blue audit_btn" href="javascript:void(0);" url="/module/shop/shop_info_audit.html?id=${item.id}" data-status="${not empty item.lat && not empty item.lng }" data-reject-desc="${item.auditDesc }" data-rel="tooltip" data-original-title="审核" data-placement="top">
											<i class="icon-edit bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
                            	</c:if>
                            	
                            	<c:if test="${item.status ne 3 && !(item.sourceType eq 2 && item.status eq 1) && item.status ne -1}">
	                            	<cqliving-security2:hasPermission name="/module/shop/shop_info_online.html">
		                        		<a class="blue online_btn" href="javascript:void(0);" url="/module/shop/shop_info_online.html?id=${item.id}" data-rel="tooltip" data-original-title="上线" data-placement="top">
											<i class="icon-arrow-up bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
                            	</c:if>
                            	<c:if test="${item.status eq 3}">
	                            	<cqliving-security2:hasPermission name="/module/shop/shop_info_offline.html">
		                        		<a class="red offline_btn" href="javascript:void(0);" url="/module/shop/shop_info_offline.html?id=${item.id}" data-rel="tooltip" data-original-title="下线" data-placement="top">
											<i class="icon-arrow-down bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
                            	</c:if>
								<cqliving-security2:hasPermission name="/module/shop/shop_info_delete.html">
		                        		<a class="red" href="javascript:void(0);" id="deleteButton" url="shop_info_delete.html?id=${item.id}" data-rel="tooltip" data-original-title="删除" data-placement="top">
											<i class="icon-trash bigger-130"></i>
										</a>
	                            </cqliving-security2:hasPermission>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="shop_info_FormId" dataUrl="shop_info_list.html" />
	</div>
</div>