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
                		<th>资讯标题</th>
                		<th>资讯标签,多个用,分隔，注意  前边也要带,号，例   ,1,2</th>
                		<th>资讯内容URL</th>
                		<th>新闻摘要</th>
                		<th>类型</th>
                		<th>关键字</th>
                		<th>来源网站，文字</th>
                		<th>平台可见类型</th>
                		<th>允许评论</th>
                		<th>评论需审核</th>
                		<th>内容需审核</th>
                		<th>初始阅读量</th>
                		<th>阅读量增加类型</th>
                		<th>达到峰值时间，以秒为单位</th>
                		<th>资讯浏览量</th>
                		<th>资讯回复量</th>
                		<th>推送状态</th>
                		<th>内容,新闻的实际内容URL,对应生成后内容</th>
                		<th>资讯内容的全文本，不带HTML标签的</th>
                		<th>原始栏目ID，为方便后续统计，新闻先归属其中一个栏目。</th>
                		<th>新闻内容类型</th>
                		<th>创建时间</th>
                		<th>创建人</th>
                		<th>创建人姓名</th>
                		<th>更新时间</th>
                		<th>更新人ID</th>
                		<th>更新人</th>
                		<th>审核人ID</th>
                		<th>审核人姓名</th>
                		<th>审核时间</th>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td class="center" id="${item.id}">
							<label>
								<input type="checkbox" class="ace" />
								<span class="lbl"></span>
							</label>
						</td>
                		<td>${item.id}</td>
                		<td>${item.appId}</td>
                    	<td>${item.title}</td>
                    	<td>${item.infoLabel}</td>
                    	<td>${item.imformationUrl}</td>
                    	<td>${item.synopsis}</td>
                        <td>
                        	<span class="label label-info">${allTypes[item.type] }</span>
                        </td>
                    	<td>${item.keywords}</td>
                    	<td>${item.infoSource}</td>
                    	<td>${item.plViewType}</td>
                        <td>
                        	<span class="label label-info">${allCommentTypes[item.commentType] }</span>
                        </td>
                        <td>
                        	<span class="label label-info">${allCommentValidateTypes[item.commentValidateType] }</span>
                        </td>
                        <td>
                        	<span class="label label-info">${allValidateTypes[item.validateType] }</span>
                        </td>
                		<td>${item.initCount}</td>
                        <td>
                        	<span class="label label-info">${allAddTypes[item.addType] }</span>
                        </td>
                		<td>${item.topTime}</td>
                		<td>${item.viewCount}</td>
                		<td>${item.replyCount}</td>
                        <td>
                        	<span class="label label-info">${allSendStatuss[item.sendStatus] }</span>
                        </td>
                    	<td>${item.content}</td>
                    	<td>${item.contextText}</td>
                		<td>${item.classifyId}</td>
                        <td>
                        	<span class="label label-info">${allContextTypes[item.contextType] }</span>
                        </td>
                    	<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></td>
                		<td>${item.creatorId}</td>
                    	<td>${item.creator}</td>
                    	<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd" /></td>
                		<td>${item.updatorId}</td>
                    	<td>${item.updator}</td>
                		<td>${item.auditingId}</td>
                    	<td>${item.auditingtor}</td>
                    	<td><fmt:formatDate value="${item.auditingTime}" pattern="yyyy-MM-dd" /></td>
                        <td>
                            <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
                        	<cqliving-security2:hasPermission name="/module/info/info_view.html">
								<button class="btn btn-xs btn-info" onclick="javascript:location.href='info_view.html?id=${item.id }'">查看</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/info/info_update.html">
								<button class="btn btn-xs btn-success" onclick="javascript:location.href='info_update.html?id=${item.id }'">修改</button>
                            </cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/info/info_delete.html">
								<button class="btn btn-xs btn-danger" id="deleteButton" url="info_delete.html?id=${item.id }">删除</button>
                            </cqliving-security2:hasPermission>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="info_FormId" dataUrl="info_list.html" />
	</div>
</div>