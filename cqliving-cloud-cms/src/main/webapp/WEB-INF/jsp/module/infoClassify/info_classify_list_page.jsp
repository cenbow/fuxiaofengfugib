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
                		<!-- <th>ID</th> -->
                		<th>标题</th>
                		<th>列表标题</th>
                		<!-- <th>专题主题</th> -->
                		<th width="60px">栏目</th>
                		<th width="70px">列表类型</th>
                		<th width="70px">内容类型</th>
                		<c:if test="${!isWxbRole }">
                		 <th width="60px">浏览量</th>
                		 <th width="60px">评论量</th>
                		</c:if>
                		<th width="90px">发布时间</th>
                		<th width="70px">发布状态</th>
                		<cqliving-security2:hasPermission name="/module/infoClassify/recommend_to_home.html">
                			<th>是否推荐到首页</th>
               			</cqliving-security2:hasPermission>
               			<!-- 
               			<c:if test="${appId eq 1}">
              				<th>自动抓稿</th>
               			</c:if>
               			 -->
            			<th width=60px">创建人</th>
            			<th width=60px">编辑人</th>
            			<th width="90px">编辑时间</th>
                		<c:if test="${not empty columnsId}">
	                		<th>排序号</th>
                		</c:if>
                        <th>操作</th>
                    </tr>
                </thead>
				<tbody>
				<c:forEach items="${pageInfo.pageResults}" var="item">
                    <tr>
                    	<td class="center" icid="${item.id}" ss="${item.status}" appid="${item.appId}" synopsis="${item.synopsis}">
							<label>
								<input type="checkbox" class="ace" name="infoid" value="${item.id}" appid="${item.appId}" infoType="${item.type}"/>
								<span class="lbl"></span>
							</label>
						</td>
                    	<%-- <td>${item.id}</td> --%>
                    	<td>${item.title}</td>
                    	<td>${item.listTitle}</td>
                    	<%-- <td>${item.specialTheme}</td> --%>
                		<td>${item.columnsName}</td>
                        <td>
                        	<span class="label label-info">${allListViewTypes[item.listViewType]}</span>
                        </td>
                        <td>
                        	<span class="label label-info">${empty allContextTypes[item.contextType] ? "专题" : allContextTypes[item.contextType]}</span>
                        </td>
                    <c:if test="${!isWxbRole }">
	                		<td>${item.viewCount}</td>
	                		<td>${item.replyCount}</td>
                		</c:if>
                    	<td><fmt:formatDate value="${item.onlineTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td>
                        	<c:choose>
								<c:when test="${item.status eq -1 or item.status eq 88}">
		                        	<span class="label label-danger">${allStatuss[item.status]}</span>
								</c:when>
								<c:when test="${item.status eq 1}">
		                        	<span class="label label-warning">${allStatuss[item.status]}</span>
								</c:when>
								<c:when test="${item.status eq 3}">
		                        	<span class="label label-success">${allStatuss[item.status]}</span>
								</c:when>
								<c:otherwise>
		                        	<span class="label label-info">${allStatuss[item.status]}</span>
								</c:otherwise>
                        	</c:choose>
                        </td>
                        <cqliving-security2:hasPermission name="/module/infoClassify/recommend_to_home.html">
	                        <td>
	                        	<c:choose>
									<c:when test="${item.isRecommend eq 1}">
			                        	<span class="label label-success">${allIsRecommends[item.isRecommend]}</span>
									</c:when>
									<c:otherwise>
			                        	<span class="label label-info">${allIsRecommends[item.isRecommend]}</span>
									</c:otherwise>
	                        	</c:choose>
	                        </td>
	                    </cqliving-security2:hasPermission>
	                    <%--
	                    <c:if test="${appId eq 1}">
               				<td>
	                        	<c:choose>
									<c:when test="${not empty item.hlwOldGuid}">
			                        	<span class="label label-success">是</span>
									</c:when>
									<c:otherwise>
			                        	<span class="label label-info">否</span>
									</c:otherwise>
	                        	</c:choose>
	                        </td>
               			</c:if>	 --%>
               			<td>${item.creator}</td>
               			<td>${item.updator}</td>
               			<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <c:if test="${not empty columnsId}">
                			<td><input type="text" class="only_num" value="${item.sortNo gt 100 ? '' : item.sortNo}" style="width: 42px;" maxlength="2" /></td>
                		</c:if>
                        <td>
                            <div class="visible-md visible-lg visible-sm hidden-xs action-buttons">
                          		<cqliving-security2:hasPermission name="/module/info/common/info_view.html">
                          				<a class="light-blue preview_btn" href="javascript:;" url="/module/info/common/info_view.html?infoId=${item.id}" data-rel="tooltip" data-original-title="预览" data-placement="top">
										<i class="icon-search bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
                            	<c:if test="${fn:contains(statusSave,item.status)}">
	                            	<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_publish.html">
                           				<a class="blue publish_btn" href="javascript:;" url="info_classify_publish.html?id=${item.id}" data-rel="tooltip" data-original-title="发布" data-placement="top">
											<i class="icon-mail-forward bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
                            	</c:if>
                            	<c:if test="${item.status eq statusOnline or item.status eq 1}">
 	                            	<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_offline.html">
  										<a class="red offline_btn" url="info_classify_offline.html?id=${item.id}" data-rel="tooltip" data-original-title="下线" data-placement="top" href="javascript:;">
											<i class="icon-arrow-down bigger-130"></i>
										</a>
 		                            </cqliving-security2:hasPermission>
                              	</c:if>
                            	
                            	<c:choose>
                            		<c:when test="${item.type eq typeNormal}">
                            			<cqliving-security2:hasPermission name="/module/info/info_add.html">
	                           				<a class="blue" url="/module/info/info_add.html?id=${item.id}" href="javascript:;" data-rel="tooltip" data-original-title="编辑" data-placement="top" forwordSaveParam="forwordSaveParam">
												<i class="icon-pencil bigger-130"></i>
											</a>
			                            </cqliving-security2:hasPermission>
                            		</c:when>
                            		<c:when test="${item.type eq typeSpecial}">
                            			<cqliving-security2:hasPermission name="/module/info/special_info_add.html">
	                           				<a class="blue" url="/module/info/special_info_add.html?id=${item.id}" href="javascript:;" data-rel="tooltip" data-original-title="编辑" data-placement="top" forwordSaveParam="forwordSaveParam">
												<i class="icon-pencil bigger-130"></i>
											</a>
			                            </cqliving-security2:hasPermission>
                            		</c:when>
                            	</c:choose>
	                            <c:if test="${item.status eq statusOnline and item.type ne typeSpecial}">
									<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_recommend.html">
                           				<a class="blue recommed_btn" href="javascript:;" data-toggle="modal" data-target="#app_modal" data-rel="tooltip" data-original-title="推荐到APP" data-placement="top">
											<i class="icon-share bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
	                            </c:if>
	                            <c:if test="${item.status eq statusOnline and item.sendStatus eq 0 and item.type ne typeSpecial}">
									<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_push.html">
                           				<a class="blue push_btn" href="javascript:void(0);" data-toggle="modal" data-target="#app_push_modal" data-rel="tooltip" data-original-title="推送" data-placement="top">
											<i class="icon-bullhorn bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
	                            </c:if>
	                            <c:if test="${item.type ne typeSpecial}">
		                            <cqliving-security2:hasPermission name="/module/info/info_copy.html">
                           				<a class="blue" href="javascript:;" url="/module/info/info_add.html?id=${item.id}&news_copy=news_copy" data-rel="tooltip" data-original-title="复制" data-placement="top" forwordSaveParam="forwordSaveParam">
											<i class="icon-copy bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
		                        </c:if>
								<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_delete.html">
                           			<a id="deleteButton" class="red" href="javascript:;" url="info_classify_delete.html?id=${item.id}&news_copy=news_copy" data-rel="tooltip" data-original-title="删除" data-placement="top">
										<i class="icon-trash bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
	                            <c:if test="${item.type eq 0}">
		                            <cqliving-security2:hasPermission name="/module/info/info_corretlation.html">
										<a href="javascript:;" data-target="#info_correlation_modal" class="blue" data-toggle="modal" data-id="${item.id}"  keywords="${item.keywords}" appid="${item.appId }" data-rel="tooltip" data-original-title="相关" data-placement="top">
											<i class="icon-link bigger-130"></i>
										</a>
		                            </cqliving-security2:hasPermission>
	                            </c:if>
	                            <c:if test="${item.status eq statusOnline }">
	                            	<c:if test="${item.isRecommend eq 0 }">
										<cqliving-security2:hasPermission name="/module/infoClassify/recommend_to_home.html">
											<a class="blue recommendToHomeButton" href="javascript:void(0);" url="/module/infoClassify/recommend_to_home.html?id=${item.id }" data-rel="tooltip" data-original-title="推荐到首页" data-placement="top"> <i class="icon-level-up bigger-130"></i></a>
										</cqliving-security2:hasPermission>
									</c:if>
	                            </c:if>
	                            <cqliving-security2:hasPermission name="/module/analysis/news/statistics.html">
                       				<a class="blue" href="javascript:;" url="/module/analysis/news/statistics.html?infoClassifyId=${item.id}" forwordSaveParam="forwordSaveParam" data-toggle="modal" data-rel="tooltip" data-original-title="图表" data-placement="top">
										<i class="glyphicon glyphicon-info-sign bigger-130"></i>
									</a>
	                            </cqliving-security2:hasPermission>
							</div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
			</table>
		</div>
     	<cqliving-frame:paginationAjax paramFormId="info_classify_FormId" dataUrl="info_classify_list.html" />
	</div>
</div>

<script type="text/javascript">
 require(["jquery"],function(){
	 
	 var appId = "${param.search_EQ_appId}";
	 var href = $("#special_pup").attr("href");
	 
	 if(href){
		 href = href.split("?")[0]+"?appId="+appId;
		 $("#special_pup").attr("href",href);
	 }
 });
</script>

