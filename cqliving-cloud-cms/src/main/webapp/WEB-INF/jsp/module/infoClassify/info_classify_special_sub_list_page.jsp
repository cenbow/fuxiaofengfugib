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
                		<th>ID</th>
                		<th>标题</th>
                		<th>专题分类</th>
                		<th>浏览量</th>
                		<th>评论量</th>
                		<th>发布状态</th>
                		<th>发布时间</th>
                		<c:if test="${not empty themeId}">
	                		<th>排序号</th>
                		</c:if>
                		<th>创建人</th>
            			<th>编辑人</th>
            			<th>编辑时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td class="center" icid="${item.id}" cid="${item.correlationId}" synopsis="${item.synopsis}">
							<label>
								<input type="checkbox" class="ace" value="${item.id }" appid="${item.appId }" name="infoid" infoType="${item.type}"/>
								<span class="lbl"></span>
							</label>
						</td>
                    	<td>${item.id}</td>
                    	<td>${item.title}</td>
                		<td>${item.themeName}</td>
                		<td>${item.viewCount}</td>
                		<td>${item.replyCount}</td>
                        <td>
                        	<c:choose>
								<c:when test="${item.status eq -1 or item.status eq 88}">
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
                        </td>
                    	<td><fmt:formatDate value="${item.onlineTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                    	<c:if test="${not empty themeId}">
                    		<td><input type="text" class="only_num" value="${item.sortNo gt 100 ? '' : item.sortNo}" style="width: 42px;" maxlength="2" /></td>
                    	</c:if>
                    	<td>${item.creator}</td>
               			<td>${item.updator}</td>
               			<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                            	<c:if test="${fn:contains(statusSave,item.status)}">
	                            	<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_publish.html">
	                            	<%-- <cqliving-security2:hasPermission name="/module/infoClassify/info_correlation_publish.html"> --%>
										<a class="blue publish_btn" href="javascript:void(0);"  url="info_classify_publish.html?id=${item.id}" data-rel="tooltip" data-original-title="发布" data-placement="top">
										<%-- <a class="blue publish_btn" href="javascript:void(0);"  url="info_correlation_publish.html?id=${item.correlationId}" data-rel="tooltip" data-original-title="发布" data-placement="top"> --%>
											<i class="icon-mail-forward bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
                            	</c:if>
                            	<c:if test="${item.status eq statusOnline}">
	                            	<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_offline.html">
	                            	<%-- <cqliving-security2:hasPermission name="/module/infoClassify/info_correlation_offline.html"> --%>
										<a class="red offline_btn" href="javascript:void(0);"  url="info_classify_offline.html?id=${item.id}" data-rel="tooltip" data-original-title="下线" data-placement="top">
										<%-- <a class="red offline_btn" href="javascript:void(0);"  url="info_correlation_offline.html?id=${item.correlationId}" data-rel="tooltip" data-original-title="下线" data-placement="top"> --%>
											<i class="icon-arrow-down bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
                            	</c:if>
                            	<cqliving-security2:hasPermission name="/module/info/add_special_sub_info.html">
                            		<a class="blue" forwordSaveParam="forwordSaveParam" href="javascript:;" url="${request.contextPath}/module/info/add_special_sub_info.html?icid=${mid}&refInfoId=${item.id}&appId=${appId}" data-rel="tooltip" data-original-title="编辑" data-placement="top">
										<i class="icon-pencil bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
	                             <c:if test="${item.status eq statusOnline and item.sendStatus eq 0}">
									<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_push.html">
                           				<a class="blue push_btn" href="javascript:void(0);" data-toggle="modal" data-target="#app_push_modal" data-rel="tooltip" data-original-title="推送" data-placement="top">
											<i class="icon-bullhorn bigger-130"></i>
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
     	<cqliving-frame:paginationAjax paramFormId="info_classify_FormId" dataUrl="info_classify_list_special_sub.html"/>
	</div>
</div>