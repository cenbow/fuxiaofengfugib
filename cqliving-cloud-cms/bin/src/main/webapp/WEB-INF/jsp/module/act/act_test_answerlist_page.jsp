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
                		<th>活动答题问题表ID（act_test_question表主键）</th>
                		<th>答案</th>
                		<th>答案描述</th>
                		<th>答案图片，多个用,分隔</th>
                		<th>答案类型</th>
                		<th>是否正确答案</th>
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
                		<td>${item.actTestQuestionId}</td>
                    	<td>${item.answer}</td>
                    	<td>${item.answerDesc}</td>
                    	<td>${item.imageUrl}</td>
                        <td>
                        	<span class="label label-info">${allTypes[item.type] }</span>
                        </td>
                        <td>
                        	<span class="label label-info">${allIsRightAnswers[item.isRightAnswer] }</span>
                        </td>
                		<td>${item.sortNo}</td>
                    	<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/act/act_test_answerview.html">
								<button class="btn btn-xs btn-info" type="button" onclick="javascript:location.href='act_test_answerview.html?id=${item.id }'">查看</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/act/act_test_answerupdate.html">
								<button class="btn btn-xs btn-success" type="button" onclick="javascript:location.href='act_test_answerupdate.html?id=${item.id }'">修改</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/act/act_test_answerdelete.html">
								<button class="btn btn-xs btn-danger" type="button" id="deleteButton" url="act_test_answerdelete.html?id=${item.id }">删除</button>
                            </cqliving-security2:hasPermission>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="act_test_answerFormId" dataUrl="act_test_answerlist.html" />
	</div>
</div>