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
<!--                 		<th>ID</th> -->
<!--                 		<th>客户端_ID</th> -->
                		<th>内容</th>
                		<th>登录名</th>
                		<th>昵称</th>
                		<th>回复量</th>
                		<th>点赞量</th>
                		<th>状态</th>
<!--                 		<th>摄影类型</th> -->
                		<th>创建时间</th>
<!--                 		<th>创建人</th> -->
<!--                 		<th>创建人姓名</th> -->
<!--                 		<th>更新时间</th> -->
<!--                 		<th>更新人ID</th> -->
<!--                 		<th>更新人</th> -->
<!--                 		<th>所处经度</th> -->
<!--                 		<th>所处纬度</th> -->
<!--                 		<th>所处位置</th> -->
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
<%--                 		<td>${item.id}</td> --%>
<%--                 		<td>${item.appId}</td> --%>
                    	<td title="${item.content}">${myfn:cutString(item.content, 20)}</td>
                		<td>${item.loginName}</td>
                		<td>${item.nickname}</td>
                		<td>${item.replyCount}</td>
                		<td>${item.priseCount}</td>
                        <td>
                        	<c:choose>
								<c:when test="${item.status eq 88}">
		                        	<span class="label label-danger">${allStatuss[item.status]}</span>
								</c:when>
								<c:when test="${item.status eq 2}">
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
<!--                         <td> -->
<%--                         	<span class="label label-info">${allShootTypes[item.shootType] }</span> --%>
<!--                         </td> -->
                    	<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
<%--                 		<td>${item.creatorId}</td> --%>
<%--                     	<td>${item.creator}</td> --%>
<%--                     	<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd" /></td> --%>
<%--                 		<td>${item.updatorId}</td> --%>
<%--                     	<td>${item.updator}</td> --%>
<%--                     	<td>${item.lng}</td> --%>
<%--                     	<td>${item.lat}</td> --%>
<%--                     	<td>${item.place}</td> --%>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
	                        	<cqliving-security2:hasPermission name="/module/shoot/shoot_info_view.html">
				                    <a class="light-blue" href="shoot_info_view.html?id=${item.id}" data-rel="tooltip" data-original-title="查看" data-placement="top">
										<i class="icon-search bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
	                            <c:if test="${item.status ne 3}">
									<cqliving-security2:hasPermission name="/module/shoot/shoot_info_online.html">
					                    <a class="blue online_btn" href="javascript:;" url="shoot_info_online.html?id=${item.id}" data-rel="tooltip" data-original-title="上线" data-placement="top">
											<i class="icon-arrow-up bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
	                            </c:if>
	                            <c:if test="${item.status eq 3}">
									<cqliving-security2:hasPermission name="/module/shoot/shoot_info_offline.html">
					                    <a class="red offline_btn" href="javascript:;" url="shoot_info_offline.html?id=${item.id}" data-rel="tooltip" data-original-title="下线" data-placement="top">
											<i class="icon-arrow-down bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
	                            </c:if>
								<cqliving-security2:hasPermission name="/module/shoot/shoot_info_delete.html">
					                <a class="red" href="javascript:;" url="shoot_info_delete.html?id=${item.id}" id="deleteButton" data-rel="tooltip" data-original-title="删除" data-placement="top">
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
     	<cqliving-frame:paginationAjax paramFormId="shoot_info_FormId" dataUrl="shoot_info_list.html" />
	</div>
</div>