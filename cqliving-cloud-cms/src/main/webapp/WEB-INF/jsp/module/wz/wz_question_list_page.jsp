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
                		<th>事件类型</th>
                		<th>状态</th>
                		<th>所属区域名称</th>
                		<th>标题</th>
                		<th>手机号</th>
                		<th>创建人</th>
                		<th>创建时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td class="center" id="${item.id}">
							<label>
								<input type="checkbox" class="ace" id="${item.id}"/>
								<span class="lbl"></span>
							</label>
						</td>
                        <td>
                        	${allTypes[item.type] }
                        </td>
                        <td>
                        	<span class="label 
	                        	<c:if test="${item.status == -1 }">label-danger</c:if>
	                        	<c:if test="${item.status == 2 }">label-yellow</c:if>
	                        	<c:if test="${item.status == 3 }">label-warning</c:if>
	                        	<c:if test="${item.status == 4 }">label-primary</c:if>
	                        	<c:if test="${item.status == 5 }">label-yellow</c:if>
	                        	<c:if test="${item.status == 6 }">label-danger</c:if>
	                        	<c:if test="${item.status == 7 }">label-success</c:if>
	                        	<c:if test="${!(item.status == 1 && item.status == 2 && item.status == 3 && item.status == 4 && item.status == 5 && item.status == 6 && item.status == 7) }">label-info</c:if>
                        	">${allStatuss[item.status] }</span>
                        </td>
                    	<td>${item.regionName}</td>
                    	<td>${item.title}</td>
                    	<td>${item.creatorPhone}</td>
                    	<td>${item.creator}</td>
                    	<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
	                        	<cqliving-security2:hasPermission name="/module/wz/wz_question_view.html">
	                        		<!-- 管理员--查看 -->
									<a class="blue" href="javascript:void(0);" url="/module/wz/wz_question_view.html?id=${item.id }" forwordSaveParam="forwordSaveParam" save-form-id="wz_question_FormId"  data-rel="tooltip" data-original-title="查看" data-placement="top">
										<i class="icon-search bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
	                            <c:if test="${item.status == 2 }">
		                            <cqliving-security2:hasPermission name="/module/wz/wz_question_check.html">
		                        		<a class="blue checkBtn" href="javascript:;" data-id="${item.id }" data-rel="tooltip" data-original-title="审核" data-placement="top">
											<i class="icon-edit bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
	                            </c:if>
	                            <c:if test="${item.status == 3 || item.status == 6 }">
		                            <cqliving-security2:hasPermission name="/module/wz/wz_question_reply.html">
		                            	<!-- 管理员--回复 -->
		                        		<a class="blue replyBtn" href="javascript:;" data-id="${item.id }" data-rel="tooltip" data-original-title="回复" data-placement="top">
											<i class="icon-comment-alt bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
	                            </c:if>
	                            <c:if test="${item.status == 5 }">
		                            <cqliving-security2:hasPermission name="/module/wz/wz_question_publish.html">
		                            	<!-- 管理员的发布 -->
		                        		<a class="blue buttonPublish" href="javascript:;" url="wz_question_publish.html?id=${item.id }" data-rel="tooltip" data-original-title="发布" data-placement="top">
											<i class="icon-mail-forward bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
	                            </c:if>
	                            <c:if test="${(fn:length(wzUserList) > 0 && item.status == 3) || item.status == 6 }">
		                            <cqliving-security2:hasPermission name="/module/wz/wz_question_transfer.html">
		                            	<!-- 管理员--转交 -->
		                        		<a class="blue transferBtn" href="javascript:;" data-id="${item.id }" data-rel="tooltip" data-original-title="转交" data-placement="top">
											<i class="icon-exchange bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
	                            </c:if>
	                            <c:if test="${!(item.status == 7 && !isAllowDel)}">
									<cqliving-security2:hasPermission name="/module/wz/wz_question_delete.html">
										<!-- 管理员--删除 -->
		                        		<a class="red deleteButton" href="javascript:;" url="wz_question_delete.html?id=${item.id }" data-rel="tooltip" data-original-title="删除" data-placement="top">
											<i class="icon-trash bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
	                            </c:if>
	                            <c:if test="${item.status == 7 }">
									<cqliving-security2:hasPermission name="/module/wz/wz_question_offline.html">
		                        		<a class="red offlineButton" href="javascript:;" data-id="${item.id }" url="wz_question_offline.html" data-rel="tooltip" data-original-title="下线" data-placement="top">
											<i class="icon-arrow-down bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
	                            </c:if>
	                            <c:if test="${item.status == 88 }">
									<cqliving-security2:hasPermission name="/module/wz/wz_question_online.html">
		                        		<a class="red onlineButton" href="javascript:;" data-id="${item.id }" url="wz_question_online.html" data-rel="tooltip" data-original-title="上线" data-placement="top">
											<i class="icon-check bigger-130"></i>
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
     	<cqliving-frame:paginationAjax paramFormId="wz_question_FormId" dataUrl="wz_question_list.html" />
	</div>
</div>
