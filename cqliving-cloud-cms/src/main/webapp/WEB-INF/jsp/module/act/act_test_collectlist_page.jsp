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
                		<th>活动答题表ID（act_test表主键）</th>
                		<th>信息收集表ID（act_collect_info表主键）</th>
                		<th>是否必填</th>
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
                		<td>${item.id}</td>
                		<td>${item.actTestId}</td>
                		<td>${item.actCollectInfoId}</td>
                        <td>
                        	<span class="label label-info">${allIsRequireds[item.isRequired] }</span>
                        </td>
                    	<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/act/act_test_collectview.html">
								<button class="btn btn-xs btn-info" type="button" onclick="javascript:location.href='act_test_collectview.html?id=${item.id }'">查看</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/act/act_test_collectupdate.html">
								<button class="btn btn-xs btn-success" type="button" onclick="javascript:location.href='act_test_collectupdate.html?id=${item.id }'">修改</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/act/act_test_collectdelete.html">
								<button class="btn btn-xs btn-danger" type="button" id="deleteButton" url="act_test_collectdelete.html?id=${item.id }">删除</button>
                            </cqliving-security2:hasPermission>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="act_test_collectFormId" dataUrl="act_test_collectlist.html" />
	</div>
</div>