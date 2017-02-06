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
                		<th>用户参与活动集合表ID（user_act_list表主键）</th>
                		<th>活动投票分类表ID（act_vote_classify表主键）</th>
                		<th>活动分类选项表ID（act_vote_item表主键）</th>
                		<th>用户ID</th>
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
                		<td>${item.userActListId}</td>
                		<td>${item.actVoteClassifyId}</td>
                		<td>${item.actVoteItemId}</td>
                		<td>${item.userId}</td>
                    	<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/account/user_act_voteview.html">
								<button class="btn btn-xs btn-info" type="button" onclick="javascript:location.href='user_act_voteview.html?id=${item.id }'">查看</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/account/user_act_voteupdate.html">
								<button class="btn btn-xs btn-success" type="button" onclick="javascript:location.href='user_act_voteupdate.html?id=${item.id }'">修改</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/account/user_act_votedelete.html">
								<button class="btn btn-xs btn-danger" type="button" id="deleteButton" url="user_act_votedelete.html?id=${item.id }">删除</button>
                            </cqliving-security2:hasPermission>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="user_act_voteFormId" dataUrl="user_act_votelist.html" />
	</div>
</div>