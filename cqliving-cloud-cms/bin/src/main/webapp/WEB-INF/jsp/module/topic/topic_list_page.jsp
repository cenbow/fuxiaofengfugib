<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="col-xs-12">
	<div id="sample-table-2_wrapper" class="dataTables_wrapper" role="grid">
		<div class="table-responsive">
			<table id="sample-table-2" class="table table-striped table-bordered table-hover dataTable">
				<thead>
					<tr>
						<th class="center"><label> <input type="checkbox" class="ace" /> <span class="lbl"></span>
						</label></th>
						<th>话题名称</th>
						<th>状态</th>
						<th>是否置顶</th>
						<th>是否推荐到首页</th>
						<th>话题来源</th>
						<th>评论量</th>
						<th>审核时间</th>
						<th>是否已审核</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pageInfo.pageResults}" var="item">
						<tr>
							<td class="center" id="${item.id}">
								<label> 
									<input type="checkbox" class="ace" id="${item.id}" data-status="${item.status }" data-sourcetype="${item.sourceType }" />
									<span class="lbl"></span>
								</label>
							</td>
							<td>${item.name}</td>
							<td><span class="label 
							<c:choose>
								<c:when test="${item.status eq -1 }">label-danger</c:when>
								<c:when test="${item.status eq 1 }">label-warning</c:when>
								<c:when test="${item.status eq 3 }">label-success</c:when>
								<c:when test="${item.status eq 88 }">label-danger</c:when>
								<c:otherwise>label-info</c:otherwise>
							</c:choose>
							">
							<c:choose>
								<c:when test="${item.status eq 1 }">
									<c:if test="${item.sourceType eq 1 }">待审核</c:if>
									<c:if test="${item.sourceType eq 2 }">草稿</c:if>
								</c:when>
								<c:otherwise>${allStatuss[item.status] }</c:otherwise>
							</c:choose>
							</span></td>
							<td><span class="label 
							<c:choose>
								<c:when test="${item.isTop eq 1 }">label-success</c:when>
								<c:otherwise>label-info</c:otherwise>
							</c:choose>
							">${allIsTops[item.isTop] }</span></td>
							<td><span class="label 
							<c:choose>
								<c:when test="${item.isRecommend eq 1 }">label-success</c:when>
								<c:otherwise>label-info</c:otherwise>
							</c:choose>
							">${allIsRecommends[item.isRecommend] }</span></td>
							<td><span class="label label-info">${allSourceTypes[item.sourceType] }</span></td>
							<td>${item.replyCount}</td>
							<td><fmt:formatDate value="${item.auditTime}" pattern="yyyy-MM-dd HH:mm" /></td>
							<td><span class="label 
							<c:choose>
								<c:when test="${item.isAudit eq 1 }">label-success</c:when>
								<c:otherwise>label-info</c:otherwise>
							</c:choose>
							">${allIsAudits[item.isAudit] }</span></td>
							<td>
								<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
									<a class="blue" href="javascript:void(0);" url="/module/topic/common/topic_view.html?id=${item.id }&_model_=_model_" open-model="view" open-title="详情" data-rel="tooltip" data-original-title="查看" data-placement="top"> <i class="icon-search bigger-130"></i></a>
									<c:if test="${item.status eq 1 }">
										<cqliving-security2:hasPermission name="/module/topic/topic_update.html">
											<a class="blue" href="javascript:void(0);" url="/module/topic/topic_update.html?id=${item.id }&_model_=_model" open-model="update" open-title="修改" data-rel="tooltip" data-original-title="修改" data-placement="top"> <i class="icon-pencil bigger-130"></i></a>
										</cqliving-security2:hasPermission>
									</c:if>
									<c:if test="${item.sourceType eq 1 and item.status eq 1 }">
										<cqliving-security2:hasPermission name="/module/topic/topic_check.html">
											<a class="blue checkButton" href="javascript:void(0);" data-rel="tooltip" url="/module/topic/topic_check.html?id=${item.id }" data-original-title="审核" data-placement="top"> <i class="icon-edit bigger-130"></i></a>
										</cqliving-security2:hasPermission>
									</c:if>
									<c:if test="${item.sourceType eq 2 and item.status eq 1  }">
										<cqliving-security2:hasPermission name="/module/topic/topic_publish.html">
											<a class="blue publishButton" href="javascript:void(0);" data-rel="tooltip" url="/module/topic/topic_publish.html?id=${item.id }" data-original-title="发布" data-placement="top"> <i class="icon-mail-forward bigger-130"></i></a>
										</cqliving-security2:hasPermission>
									</c:if>
									<c:if test="${item.status eq 3 }">
										<cqliving-security2:hasPermission name="/module/topic/topic_online.html">
											<a class="red onlineDownButton" href="javascript:void(0);" data-rel="tooltip" url="/module/topic/topic_online.html?id=${item.id }" data-original-title="下线" data-placement="top"> <i class="icon-arrow-down bigger-130"></i></a>
										</cqliving-security2:hasPermission>
									</c:if>
									<c:if test="${item.status eq 88 }">
										<cqliving-security2:hasPermission name="/module/topic/topic_online.html">
											<a class="blue onlineUpButton" href="javascript:void(0);" data-rel="tooltip" url="/module/topic/topic_online.html?id=${item.id }" data-original-title="上线" data-placement="top"> <i class="icon-arrow-up bigger-130"></i></a>
										</cqliving-security2:hasPermission>
									</c:if>
									<c:if test="${item.status eq 3 }">
										<c:if test="${item.isTop eq 0 }">
											<cqliving-security2:hasPermission name="/module/topic/topic_top.html">
												<a class="blue topAndRecommendButton" href="javascript:void(0);" data-rel="tooltip"  modelurl="/module/topic/common/topic_image/${item.id }.html?_model_=_model_&type=1" url="/module/topic/topic_top.html?id=${item.id }" data-original-title="置顶" data-placement="top"> <i class="icon-double-angle-up bigger-130"></i></a>
											</cqliving-security2:hasPermission>
										</c:if>
										<c:if test="${item.isRecommend eq 0 }">
											<cqliving-security2:hasPermission name="/module/topic/topic_recommend.html">
												<a class="blue topAndRecommendButton" href="javascript:void(0);" data-rel="tooltip" modelurl="/module/topic/common/topic_image/${item.id }.html?_model_=_model_&type=2" url="/module/topic/topic_recommend.html?id=${item.id }" data-original-title="推荐到首页" data-placement="top"> <i class="icon-level-up bigger-130"></i></a>
											</cqliving-security2:hasPermission>
										</c:if>
										<c:if test="${item.isRecommend eq 1 }">
											<cqliving-security2:hasPermission name="/module/topic/topic_recommend.html">
												<a class="red cancelTopAndRecommendBtn" href="javascript:void(0);" data-rel="tooltip" url="/module/topic/topic_recommend.html?id=${item.id }" data-original-title="取消推荐到首页" data-placement="top"> <i class="icon-level-down bigger-130"></i></a>
											</cqliving-security2:hasPermission>
										</c:if>
									</c:if>
									 
									 <c:if test="${item.status eq 88 or item.status eq 1 }">
										<cqliving-security2:hasPermission name="/module/topic/topic_delete.html">
											<a class="red" href="javascript:void(0);" data-rel="tooltip" id="deleteButton" url="topic_delete.html?id=${item.id }" data-original-title="删除" data-placement="top"> <i class="icon-trash bigger-130"></i></a>
										</cqliving-security2:hasPermission>
									</c:if>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<cqliving-frame:paginationAjax paramFormId="topicFormId" dataUrl="topic_list.html" />
	</div>
</div>