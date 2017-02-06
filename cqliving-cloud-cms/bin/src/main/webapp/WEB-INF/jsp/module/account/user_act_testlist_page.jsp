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
                		<th>活动答题问题表ID（act_test_question表主键）</th>
                		<th>活动答题答案ID（act_test_answer表主键）</th>
                		<th>用户ID</th>
                		<th>参与时间</th>
                		<th>答案内容</th>
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
                		<td>${item.actTestQuestionId}</td>
                		<td>${item.actTestAnswerId}</td>
                		<td>${item.userId}</td>
                    	<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></td>
                    	<td>${item.answerContent}</td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/account/user_act_testview.html">
								<button class="btn btn-xs btn-info" type="button" onclick="javascript:location.href='user_act_testview.html?id=${item.id }'">查看</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/account/user_act_testupdate.html">
								<button class="btn btn-xs btn-success" type="button" onclick="javascript:location.href='user_act_testupdate.html?id=${item.id }'">修改</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/account/user_act_testdelete.html">
								<button class="btn btn-xs btn-danger" type="button" id="deleteButton" url="user_act_testdelete.html?id=${item.id }">删除</button>
                            </cqliving-security2:hasPermission>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="user_act_testFormId" dataUrl="user_act_testlist.html" />
	</div>
</div>