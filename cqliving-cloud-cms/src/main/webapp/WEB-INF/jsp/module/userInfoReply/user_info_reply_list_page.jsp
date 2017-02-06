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
                		<th width="20%">标题</th>
                		<th width="20%">评论内容</th>
                		<th>类型</th>
                		<th>评论用户</th>
                		<th>审核时间</th>
                		<th>评论时间</th>
                		<c:if test="${sourceType eq SOURCETYPE1}">
                    		<th>手机号码</th>
                    	</c:if>
                		<th>状态</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td class="center" id="${item.id}">
							<label>
								<input type="checkbox" class="ace" id="${item.id}" status="${item.status}" sourceId="${item.sourceType == SOURCETYPE1 ? item.infoClassifyId : item.sourceId}"/>
								<span class="lbl"></span>
							</label>
						</td>
                    	<td width="20%">${item.title}</td>
                    	<td width="20%">${item.content}</td>
                    	<td>${allTypes[item.type]}</td>
                    	<td>${item.replyUser}</td>
                    	<td><fmt:formatDate value="${item.auditingTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    	<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    	<c:if test="${sourceType eq SOURCETYPE1}">
                    		<td>${item.telephone}</td>
                    	</c:if>
                    	<td>
                    		<span class="label 
                    		<c:choose>
                    			<c:when test="${item.status eq -1 }">label-danger</c:when>
                    			<c:when test="${item.status eq 2 }">label-warning</c:when>
                    			<c:when test="${item.status eq 3 }">label-success</c:when>
                    			<c:otherwise>label-info</c:otherwise>
                    		</c:choose>
                    		">
                    			${allStatuss[item.status]}
                    		</span>
                    	</td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
								<c:if test="${sourceType eq SOURCETYPE1}">
<%-- 									<c:if test="${item.newsType ne typeSpecial}"> --%>
	                           			<cqliving-security2:hasPermission name="/module/info/common/info_view.html">
	                           				<a class="light-blue preview_btn" href="javascript:;" url="/module/info/common/info_view.html?infoId=${item.infoClassifyId}" data-rel="tooltip" data-original-title="预览" data-placement="top">
												<i class="icon-search bigger-130"></i>
											</a>
			                            </cqliving-security2:hasPermission>
<%-- 	                           		</c:if> --%>
		                            <c:if test="${noAuditing eq item.status}">
										<cqliving-security2:hasPermission name="/module/user_info_reply/news_auditing.html">
											<a class="blue" id="auditing-one" url="/module/user_info_reply/news_auditing.html" href="javascript:;" data-rel="tooltip" data-original-title="审核" data-placement="top">
												<i class="icon-edit bigger-130"></i>
											</a>
			                            </cqliving-security2:hasPermission>
		                            </c:if>
									<cqliving-security2:hasPermission name="/module/user_info_reply/news_reply_delete.html">
										<a class="red" href="javascript:;" id="deleteButton" url="/module/user_info_reply/news_reply_delete.html?id=${item.id}" data-rel="tooltip" data-original-title="删除" data-placement="top">
											<i class="icon-trash bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
								</c:if>
								<c:if test="${sourceType eq SOURCETYPE2}">
		                            <c:if test="${noAuditing eq item.status}">
										<cqliving-security2:hasPermission name="/module/user_info_reply/wz_auditing.html">
											<a class="blue" id="auditing-one" url="/module/user_info_reply/wz_auditing.html" href="javascript:;" data-rel="tooltip" data-original-title="审核" data-placement="top">
												<i class="icon-edit bigger-130"></i>
											</a>
			                            </cqliving-security2:hasPermission>
		                            </c:if>
									<cqliving-security2:hasPermission name="/module/user_info_reply/wz_reply_delete.html">
										<a class="red" href="javascript:;" id="deleteButton" url="/module/user_info_reply/wz_reply_delete.html?id=${item.id}" data-rel="tooltip" data-original-title="删除" data-placement="top">
											<i class="icon-trash bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
								</c:if>
								<c:if test="${sourceType eq SOURCETYPE3}">
									<c:if test="${noAuditing eq item.status}">
										<cqliving-security2:hasPermission name="/module/user_info_reply/shop_auditing.html">
											<a class="blue" id="auditing-one" url="/module/user_info_reply/shop_auditing.html" href="javascript:;" data-rel="tooltip" data-original-title="审核" data-placement="top">
												<i class="icon-edit bigger-130"></i>
											</a>
			                            </cqliving-security2:hasPermission>
		                            </c:if>
									<cqliving-security2:hasPermission name="/module/user_info_reply/shop_reply_delete.html">
										<a class="red" href="javascript:;" id="deleteButton" url="/module/user_info_reply/shop_reply_delete.html?id=${item.id}" data-rel="tooltip" data-original-title="删除" data-placement="top">
											<i class="icon-trash bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
								</c:if>
								<c:if test="${sourceType eq SOURCETYPE4}">
									<c:if test="${noAuditing eq item.status}">
										<cqliving-security2:hasPermission name="/module/user_info_reply/shoot_auditing.html">
											<a class="blue" id="auditing-one" url="/module/user_info_reply/shoot_auditing.html" href="javascript:;" data-rel="tooltip" data-original-title="审核" data-placement="top">
												<i class="icon-edit bigger-130"></i>
											</a>
			                            </cqliving-security2:hasPermission>
		                            </c:if>
									<cqliving-security2:hasPermission name="/module/user_info_reply/shoot_reply_delete.html">
										<a class="red" href="javascript:;" id="deleteButton" url="/module/user_info_reply/shoot_reply_delete.html?id=${item.id}" data-rel="tooltip" data-original-title="删除" data-placement="top">
											<i class="icon-trash bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
								</c:if>
								<c:if test="${sourceType eq SOURCETYPE5}">
									<c:if test="${noAuditing eq item.status}">
										<cqliving-security2:hasPermission name="/module/user_info_reply/joke_auditing.html">
											<a class="blue" id="auditing-one" url="/module/user_info_reply/joke_auditing.html" href="javascript:;" data-rel="tooltip" data-original-title="审核" data-placement="top">
												<i class="icon-edit bigger-130"></i>
											</a>
			                            </cqliving-security2:hasPermission>
		                            </c:if>
									<cqliving-security2:hasPermission name="/module/user_info_reply/joke_reply_delete.html">
										<a class="red" href="javascript:;" id="deleteButton" url="/module/user_info_reply/joke_reply_delete.html?id=${item.id}" data-rel="tooltip" data-original-title="删除" data-placement="top">
											<i class="icon-trash bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
								</c:if>
								<c:if test="${sourceType eq SOURCETYPE6}">
									<c:if test="${noAuditing eq item.status}">
										<cqliving-security2:hasPermission name="/module/user_info_reply/act_auditing.html">
											<a class="blue" id="auditing-one" url="/module/user_info_reply/act_auditing.html" href="javascript:;" data-rel="tooltip" data-original-title="审核" data-placement="top">
												<i class="icon-edit bigger-130"></i>
											</a>
			                            </cqliving-security2:hasPermission>
		                            </c:if>
									<cqliving-security2:hasPermission name="/module/user_info_reply/act_reply_delete.html">
										<a class="red" href="javascript:;" id="deleteButton" url="/module/user_info_reply/act_reply_delete.html?id=${item.id}" data-rel="tooltip" data-original-title="删除" data-placement="top">
											<i class="icon-trash bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
								</c:if>
								<c:if test="${sourceType eq SOURCETYPE7}">
									<c:if test="${noAuditing eq item.status}">
										<cqliving-security2:hasPermission name="/module/user_info_reply/topic_auditing.html">
											<a class="blue" id="auditing-one" url="/module/user_info_reply/topic_auditing.html" href="javascript:;" data-rel="tooltip" data-original-title="审核" data-placement="top">
												<i class="icon-edit bigger-130"></i>
											</a>
			                            </cqliving-security2:hasPermission>
		                            </c:if>
									<cqliving-security2:hasPermission name="/module/user_info_reply/topic_reply_delete.html">
										<a class="red" href="javascript:;" id="deleteButton" url="/module/user_info_reply/topic_reply_delete.html?id=${item.id}" data-rel="tooltip" data-original-title="删除" data-placement="top">
											<i class="icon-trash bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
								</c:if>
								<c:if test="${sourceType eq SOURCETYPE10}">
									<c:if test="${noAuditing eq item.status}">
										<cqliving-security2:hasPermission name="/module/user_info_reply/tourism_auditing.html">
											<a class="blue" id="auditing-one" url="/module/user_info_reply/tourism_auditing.html" href="javascript:;" data-rel="tooltip" data-original-title="审核" data-placement="top">
												<i class="icon-edit bigger-130"></i>
											</a>
			                            </cqliving-security2:hasPermission>
		                            </c:if>
									<cqliving-security2:hasPermission name="/module/user_info_reply/tourism_reply_delete.html">
										<a class="red" href="javascript:;" id="deleteButton" url="/module/user_info_reply/tourism_reply_delete.html?id=${item.id}" data-rel="tooltip" data-original-title="删除" data-placement="top">
											<i class="icon-trash bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
								</c:if>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
		<c:if test="${sourceType eq SOURCETYPE1}">
	     	<cqliving-frame:paginationAjax paramFormId="user_info_reply_FormId" dataUrl="user_info_reply_list.html" />
		</c:if>
		<c:if test="${sourceType eq SOURCETYPE2}">
	     	<cqliving-frame:paginationAjax paramFormId="user_info_reply_FormId" dataUrl="wz_reply_list.html" />
		</c:if>
		<c:if test="${sourceType eq SOURCETYPE3}">
	     	<cqliving-frame:paginationAjax paramFormId="user_info_reply_FormId" dataUrl="shop_reply_list.html" />
		</c:if>
		<c:if test="${sourceType eq SOURCETYPE4}">
	     	<cqliving-frame:paginationAjax paramFormId="user_info_reply_FormId" dataUrl="shooot_reply_list.html" />
		</c:if>
		<c:if test="${sourceType eq SOURCETYPE5}">
	     	<cqliving-frame:paginationAjax paramFormId="user_info_reply_FormId" dataUrl="joke_reply_list.html" />
		</c:if>
		<c:if test="${sourceType eq SOURCETYPE6}">
	     	<cqliving-frame:paginationAjax paramFormId="user_info_reply_FormId" dataUrl="act_reply_list.html" />
		</c:if>
		<c:if test="${sourceType eq SOURCETYPE7}">
	     	<cqliving-frame:paginationAjax paramFormId="user_info_reply_FormId" dataUrl="topic_reply_list.html" />
		</c:if>
		<c:if test="${sourceType eq SOURCETYPE10}">
	     	<cqliving-frame:paginationAjax paramFormId="user_info_reply_FormId" dataUrl="tourism_reply_list.html" />
		</c:if>
	</div>
</div>