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
                		<th>登录账号</th>
                		<th>用户姓名</th>
                		<th>手机号</th>
                		<c:if test="${not empty appList}">
                			<th>来源客户端</th>
                		</c:if>
                		<th>来源</th>
                		<th>最后登录时间</th>
                		<th>注册时间</th>
                		<th>状态</th>
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
                    	<td>${item.userName}</td>
                    	<td>${item.name}</td>
                    	<td>${item.telephone}</td>
                    	<c:if test="${not empty appList}">
                			<td>${item.appName}</td>
                		</c:if>
                		<td>${item.source}</td>
                    	<td><fmt:formatDate value="${item.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    	<td><fmt:formatDate value="${item.registTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    	<td><span class="label 
                    	<c:choose>
                    		<c:when test="${item.status eq 0 }">label-info</c:when>
                    		<c:when test="${item.status eq 1 }">label-danger</c:when>
                    		<c:otherwise>label-info</c:otherwise>
                    	</c:choose>
                    	">${allStatuss[item.status]}</span></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/userAccount/user_account_view.html">
								<a class="light-blue" href="user_account_view.html?id=${item.id}&appId=${item.appId}" data-rel="tooltip" data-original-title="查看" data-placement="top">
									<i class="icon-search bigger-130"></i>
								</a>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/userAccount/user_account_update.html">
								<c:if test="${TYPE2 ne item.type and not empty item.telephone}">
									<a class="blue" href="user_account_update.html?id=${item.id}&appId=${item.appId}" data-rel="tooltip" data-original-title="修改" data-placement="top">
										<i class="icon-pencil bigger-130"></i>
									</a>
								</c:if>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/userAccount/reset_pwd.html">
								<c:if test="${TYPE2 ne item.type and not empty item.telephone}">
									<a class="blue" href="reset_pwd.html?id=${item.id}" data-rel="tooltip" data-original-title="重置密码" data-placement="top">
										<i class="icon-lock bigger-130"></i>
									</a>
								</c:if>
                            </cqliving-security2:hasPermission>
                            <c:if test="${item.status eq 0}">
								<cqliving-security2:hasPermission name="/module/userAccount/user_account_delete.html">
									<a class="red operOneBtn" url="user_account_delete.html?id=${item.id}" href="javascript:;" data-rel="tooltip" tip="禁用" data-original-title="禁用" data-placement="top">
										<i class="icon-ban-circle bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
                            </c:if>
                            <c:if test="${item.status eq 1}">
								<cqliving-security2:hasPermission name="/module/userAccount/start_using.html">
									<a class="blue operOneBtn" url="start_using.html?id=${item.id}" href="javascript:;" data-rel="tooltip" tip="启用" data-original-title="启用" data-placement="top">
										<i class="icon-ok bigger-130"></i>
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
     	<cqliving-frame:paginationAjax paramFormId="user_account_FormId" dataUrl="user_account_list.html" />
	</div>
</div>