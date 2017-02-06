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
                		<th>客户端_ID</th>
                		<th>活动ID（act_info表主键）</th>
                		<th>活动类型</th>
                		<th>状态</th>
                		<th>外链地址</th>
                		<th>活动开始时间</th>
                		<th>活动结束时间</th>
                		<th>创建时间</th>
                		<th>创建人</th>
                		<th>创建人姓名</th>
                		<th>更新时间</th>
                		<th>更新人ID</th>
                		<th>更新人</th>
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
                		<td>${item.actInfoId}</td>
                        <td>
                        	<span class="label label-info">${allTypes[item.type] }</span>
                        </td>
                        <td>
                        	<span class="label label-info">${allStatuss[item.status] }</span>
                        </td>
                    	<td>${item.linkUrl}</td>
                    	<td><fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd" /></td>
                    	<td><fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd" /></td>
                    	<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></td>
                		<td>${item.creatorId}</td>
                    	<td>${item.creator}</td>
                    	<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd" /></td>
                		<td>${item.updatorId}</td>
                    	<td>${item.updator}</td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/act/act_info_listview.html">
								<button class="btn btn-xs btn-info" type="button" onclick="javascript:location.href='act_info_listview.html?id=${item.id }'">查看</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/act/act_info_listupdate.html">
								<button class="btn btn-xs btn-success" type="button" onclick="javascript:location.href='act_info_listupdate.html?id=${item.id }'">修改</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/act/act_info_listdelete.html">
								<button class="btn btn-xs btn-danger" type="button" id="deleteButton" url="act_info_listdelete.html?id=${item.id }">删除</button>
                            </cqliving-security2:hasPermission>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="act_info_listFormId" dataUrl="act_info_listlist.html" />
	</div>
</div>