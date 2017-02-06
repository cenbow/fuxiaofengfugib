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
                		<th>主键</th>
                		<th>APP_ID</th>
                		<th>七牛云服务资源名称</th>
                		<th>七牛提供的测试域名</th>
                		<th>绑定的自定义域名</th>
                		<th>状态</th>
                		<th>创建时间</th>
                		<th>创建人名称</th>
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
                		<td>${item.id}</td>
                		<td>${item.appId}</td>
                    	<td>${item.bucketName}</td>
                    	<td>${item.domainTest}</td>
                    	<td>${item.domainCustom}</td>
                        <td>
                        	<span class="label label-info">${allStatuss[item.status] }</span>
                        </td>
                    	<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></td>
                    	<td>${item.creator}</td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/app/app_qiniu_view.html">
								<button class="btn btn-xs btn-info" type="button" onclick="javascript:location.href='app_qiniu_view.html?id=${item.id }'">查看</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/app/app_qiniu_update.html">
								<button class="btn btn-xs btn-success" type="button" onclick="javascript:location.href='app_qiniu_update.html?id=${item.id }'">修改</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/app/app_qiniu_delete.html">
								<button class="btn btn-xs btn-danger" type="button" id="deleteButton" url="app_qiniu_delete.html?id=${item.id }">删除</button>
                            </cqliving-security2:hasPermission>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="app_qiniu_FormId" dataUrl="app_qiniu_list.html" />
	</div>
</div>