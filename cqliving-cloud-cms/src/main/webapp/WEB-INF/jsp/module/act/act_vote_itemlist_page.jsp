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
                		<th>活动投票分类表ID（act_vote_classify表主键）</th>
                		<th>选项编号</th>
                		<th>选项标题，后台限制最多80个字</th>
                		<th>选项描述</th>
                		<th>实际量</th>
                		<th>初始量</th>
                		<th>选项图片</th>
                		<th>视频URL</th>
                		<th>内容,包含HTML标签的富文本内容</th>
                		<th>状态</th>
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
                		<td>${item.actVoteClassifyId}</td>
                    	<td>${item.number}</td>
                    	<td>${item.itemTitle}</td>
                    	<td>${item.itemDesc}</td>
                		<td>${item.actValue}</td>
                		<td>${item.initValue}</td>
                    	<td>${item.imageUrl}</td>
                    	<td>${item.videoUrl}</td>
                    	<td>${item.content}</td>
                        <td>
                        	<span class="label label-info">${allStatuss[item.status] }</span>
                        </td>
                		<td>${item.sortNo}</td>
                    	<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/act/act_vote_itemview.html">
								<button class="btn btn-xs btn-info" type="button" onclick="javascript:location.href='act_vote_itemview.html?id=${item.id }'">查看</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/act/act_vote_itemupdate.html">
								<button class="btn btn-xs btn-success" type="button" onclick="javascript:location.href='act_vote_itemupdate.html?id=${item.id }'">修改</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/act/act_vote_itemdelete.html">
								<button class="btn btn-xs btn-danger" type="button" id="deleteButton" url="act_vote_itemdelete.html?id=${item.id }">删除</button>
                            </cqliving-security2:hasPermission>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="act_vote_itemFormId" dataUrl="act_vote_itemlist.html" />
	</div>
</div>