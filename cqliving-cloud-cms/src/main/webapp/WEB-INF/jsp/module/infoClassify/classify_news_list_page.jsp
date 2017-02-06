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
                		<th>客户端</th>
                		<th>标题</th>
                		<th>列表标题</th>
                		<th width="60px">栏目</th>
                		<th width="70px">列表类型</th>
                		<th width="70px">内容类型</th>
                		<th width="90px">发布时间</th>
                		<th width="70px">发布状态</th>
            			<th width=60px">创建人</th>
            			<th width=60px">编辑人</th>
            			<th width="90px">编辑时间</th>
                		<c:if test="${not empty columnsId}">
	                		<th>排序号</th>
                		</c:if>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td class="center" icid="${item.id}" ss="${item.status}" appid="${item.appId}" synopsis="${item.synopsis}">
							<label>
								<input type="checkbox" class="ace" name="infoid" value="${item.id}" appid="${item.appId}" infoType="${item.type}"/>
								<span class="lbl"></span>
							</label>
						</td>
                    <td>${item.id}</td>
                    <td>
                      <c:forEach items="${appInfoDtos }" var="appInfo">
                         <c:if test="${appInfo.id eq item.appId }">${appInfo.name }</c:if>
                      </c:forEach>
                    </td>
                    	<td>${item.title}</td>
                    	<td>${item.listTitle}</td>
                		<td>${item.columnsName}</td>
                     <td>
                     	<span class="label label-info">${allListViewTypes[item.listViewType]}</span>
                     </td>
                     <td>
                     	<span class="label label-info">${empty allContextTypes[item.contextType] ? "专题" : allContextTypes[item.contextType]}</span>
                     </td>
                    	<td><fmt:formatDate value="${item.onlineTime}" pattern="yyyy-MM-dd HH:mm" /></td>
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
               			<td>${item.creator}</td>
               			<td>${item.updator}</td>
               			<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td>
                            <div class="visible-md visible-lg visible-sm hidden-xs action-buttons">
                          		<cqliving-security2:hasPermission name="/module/info/common/info_view.html">
                          				<a class="light-blue preview_btn" href="javascript:;" url="/module/info/common/info_view.html?infoId=${item.id}" data-rel="tooltip" data-original-title="预览" data-placement="top">
										<i class="icon-search bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
                            	<c:if test="${fn:contains(statusSave,item.status)}">
	                            	<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_publish.html">
                           				<a class="blue publish_btn" href="javascript:;" url="info_classify_publish.html?id=${item.id}" data-rel="tooltip" data-original-title="发布" data-placement="top">
											<i class="icon-mail-forward bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
                            	</c:if>
                            	<c:if test="${item.status eq statusOnline or item.status eq 1}">
 	                            	<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_offline.html">
  										<a class="red offline_btn" url="info_classify_offline.html?id=${item.id}" data-rel="tooltip" data-original-title="下线" data-placement="top" href="javascript:;">
											<i class="icon-arrow-down bigger-130"></i>
										</a>
 		                            </cqliving-security2:hasPermission>
                              	</c:if>
                            	    <c:choose>
                            	        <c:when test="${2 eq item.type}">
	                            			<cqliving-security2:hasPermission name="/module/info/special_info_add.html">
		                           				<a class="blue" url="/module/info/special_info_add.html?id=${item.id}&back_url=/module/infoClassify/classify_news_list.html" href="javascript:;" data-rel="tooltip" data-original-title="编辑" data-placement="top" forwordSaveParam="forwordSaveParam">
													<i class="icon-pencil bigger-130"></i>
												</a>
				                            </cqliving-security2:hasPermission>
	                            		</c:when>
	                            		<c:otherwise>
	                            			<cqliving-security2:hasPermission name="/module/info/info_add.html">
		                           				<a class="blue" url="/module/info/info_add.html?id=${item.id}&back_url=/module/infoClassify/classify_news_list.html" href="javascript:;" data-rel="tooltip" data-original-title="编辑" data-placement="top" forwordSaveParam="forwordSaveParam">
													<i class="icon-pencil bigger-130"></i>
												</a>
				                            </cqliving-security2:hasPermission>
	                            		</c:otherwise>
	                            		
                              	</c:choose>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="info_classify_FormId" dataUrl="classify_news_list.html" />
	</div>
</div>