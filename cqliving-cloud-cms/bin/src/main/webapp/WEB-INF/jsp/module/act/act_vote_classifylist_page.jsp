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
                		<th>活动ID（act_info表主键）</th>
                		<th>活动集合表ID（act_info_list表主键）</th>
                		<th>活动投票表ID（act_vote表主键）</th>
                		<th>分类标题，不超过8个字</th>
                		<th>分类主题，不超过50个字 </th>
                		<th>排序号</th>
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
                		<td>${item.actInfoId}</td>
                		<td>${item.actInfoListId}</td>
                		<td>${item.actVoteId}</td>
                    	<td>${item.title}</td>
                    	<td>${item.subject}</td>
                		<td>${item.sortNo}</td>
                    	<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/act/act_vote_classifyview.html">
								<button class="btn btn-xs btn-info" type="button" onclick="javascript:location.href='act_vote_classifyview.html?id=${item.id }'">查看</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/act/act_vote_classifyupdate.html">
								<button class="btn btn-xs btn-success" type="button" onclick="javascript:location.href='act_vote_classifyupdate.html?id=${item.id }'">修改</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/act/act_vote_classifydelete.html">
								<button class="btn btn-xs btn-danger" type="button" id="deleteButton" url="act_vote_classifydelete.html?id=${item.id }">删除</button>
                            </cqliving-security2:hasPermission>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="act_vote_classifyFormId" dataUrl="act_vote_classifylist.html" />
	</div>
</div>