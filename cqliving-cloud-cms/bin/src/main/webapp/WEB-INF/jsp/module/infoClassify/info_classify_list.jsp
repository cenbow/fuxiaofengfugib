<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="新闻管理" name="_breadcrumbs_1"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="info_classify_list.html" id="info_classify_FormId">
	                    <div class="special-list">
	                    	<c:choose>
	                    		<c:when test="${empty appList}">
	                    			<input type="hidden" name="search_EQ_appId" id="info_classify_appid" value="${search_EQ_appId}" />
	                    		</c:when>
		                    	<c:otherwise>
			                    	<div class="col-xs-6 col-sm-3">
										<div class="form-group">
											<div class="col-sm-12">
				                            	<select class="chosen-select" data-placeholder="区县" name="search_EQ_appId" id="info_classify_appid">
					                                <c:forEach items="${appList}" var="obj">
					                                	<option value="${obj.id}"<c:if test="${param.search_EQ_appId eq obj.id}">selected="selected"</c:if>>${obj.name}</option>
					                                </c:forEach>
					                            </select>
			                        		</div>
	                    				</div>
									</div>
								</c:otherwise>
							</c:choose>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
										<div class="input-group">
											<input name=search_EQ_columnsId type="hidden" value="${columnsId}" />
											<input name="columnsName" id="columnsName" type="text" placeholder="请选择所属栏目"  class="col-xs-12 form-control dropdown-toggle" value="${columnsName}" />
											<span class="input-group-btn dropdown-toggle">
												<button class="btn btn-sm btn-primary" type="button">
													<span class="caret"></span>
												</button>
											</span>
											<ul class="dropdown-menu dropdown-menu-right" role="menu" style="width:100%; padding:0px;">
				                               <div class="dropdown-default" id="appcolumns_tree"></div>
				                            </ul>
										</div>
	                        		</div>
                   				</div>
							</div>
							<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_listViewType" id="search_EQ_listViewType" class="form-control">
			                                <option value="">所有列表类型</option>
		                                	<option value="4"<c:if test="${param.search_EQ_listViewType eq 4}"> selected="selected"</c:if>>轮播</option>
		                                	<option value="-4"<c:if test="${param.search_EQ_listViewType eq -4}"> selected="selected"</c:if>>无轮播</option>
		                                	<option value="0"<c:if test="${param.search_EQ_listViewType eq 0}"> selected="selected"</c:if>>无图</option>
		                                	<option value="1"<c:if test="${param.search_EQ_listViewType eq 1}"> selected="selected"</c:if>>单图</option>
		                                	<option value="2"<c:if test="${param.search_EQ_listViewType eq 2}"> selected="selected"</c:if>>大图</option>
		                                	<option value="3"<c:if test="${param.search_EQ_listViewType eq 3}"> selected="selected"</c:if>>多图</option>
		                                	<option value="5"<c:if test="${param.search_EQ_listViewType eq 5}"> selected="selected"</c:if>>窄图</option>
		                                	<option value="6"<c:if test="${param.search_EQ_listViewType eq 6}"> selected="selected"</c:if>>窄图带标题</option>
			                            </select>
	                        		</div>
                   				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_contextType" id="search_EQ_contextType" class="form-control">
			                                <option value="">所有内容类型</option>
			                                <c:forEach items="${allContextTypes}" var="obj">
			                                	<option value="${obj.key}"<c:if test="${param.search_EQ_contextType eq obj.key}"> selected="selected"</c:if>>${obj.value}</option>
			                                </c:forEach>
			                            </select>
	                        		</div>
                   				</div>
							</div>
							<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_type" id="search_EQ_type" class="form-control">
			                                <option value="">所有新闻类型</option>
			                                <c:forEach items="${allTypes}" var="obj">
			                                	<option value="${obj.key}"<c:if test="${param.search_EQ_type eq obj.key}"> selected="selected"</c:if>>${obj.value}</option>
			                                </c:forEach>
			                            </select>
	                        		</div>
                   				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_title" name="search_LIKE_title" value="${param.search_LIKE_title }" placeholder="新闻标题" class="col-xs-12 form-control" />
	                        		</div>
                   				</div>
							</div>
							<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_specialTheme" name="search_LIKE_specialTheme" value="${param.search_LIKE_specialTheme}" placeholder="专题主题" class="col-xs-12 form-control" />
	                        		</div>
                   				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
			                                <option value="">所有状态</option>
		                                	<option value="0" <c:if test="${param.search_EQ_status eq 0}">selected="selected"</c:if>>草稿</option>
		                                	<option value="1" <c:if test="${param.search_EQ_status eq 1}">selected="selected"</c:if>>待审核</option>
		                                	<option value="3" <c:if test="${param.search_EQ_status eq 3}">selected="selected"</c:if>>发布</option>
		                                	<option value="88" <c:if test="${param.search_EQ_status eq 88}">selected="selected"</c:if>>下线</option>
			                            </select>
	                        		</div>
                   				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
			                            <div class="input-group">
				                            <input name="search_GTE_onlineTime" type="hidden" value="<fmt:formatDate value="${search_GTE_onlineTime}" pattern="yyyy-MM-dd" />">
				                            <input name="search_LT_onlineTime" type="hidden" value="<fmt:formatDate value="${search_LT_onlineTime}" pattern="yyyy-MM-dd" />">
				                            <input type="text" id="onlineTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_onlineTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_publishTime}"> 至 </c:if><fmt:formatDate value="${search_LT_onlineTime}" pattern="yyyy-MM-dd" />" placeholder="上线时间"  class="form-control">
				                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
				                        </div>
	                        		</div>
                   				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_creator" name="search_LIKE_creator" value="${param.search_LIKE_creator}" placeholder="创建人" class="col-xs-12 form-control" />
	                        		</div>
                   				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_infoLabel" name="search_LIKE_infoLabel" value="${param.search_LIKE_infoLabel}" placeholder="标签" class="col-xs-12 form-control" />
	                        		</div>
                   				</div>
							</div>
							<c:if test="${empty appList}">
								<div class="col-xs-6 col-sm-3"></div>
							</c:if>
							<div class="col-xs-6 col-sm-3"></div>
							<div class="col-xs-6 col-sm-3"></div>
							<div class="col-xs-6 col-sm-3"></div>
                    		<div class="col-xs-6 col-sm-3 btn-search">
								<div class="form-group">
									<div class="col-sm-12">
										<button class="btn btn-primary btn-sm" type="button" id="searchButton">
											<i class="icon-search bigger-110"></i>查询
										</button>
										<button class="btn btn-sm" type="button" id="resetButton" notinclude="select[name=search_EQ_appId]">
											<i class="icon-undo bigger-110"></i>重置
										</button>
									</div>
								</div>
							</div>
                    	</div>
						<div class="clear"></div>
						<div class="col-xs-12">						
						<div class="well">
							<cqliving-security2:hasPermission name="/module/info/normal_info_add.html">
								<button class="btn btn-sm btn-success" type="button" url="/module/info/info_add.html" forwordSaveParam="forwordSaveParam">
									<i class="icon-plus"></i>添加新闻
								</button>
							</cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/info/info_add_video.html">
								<button class="btn btn-sm btn-success" type="button" url="/module/info/info_add.html?contextType=5" forwordSaveParam="forwordSaveParam">
									<i class="icon-plus"></i>添加视频新闻
								</button>
							</cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/info/info_add_audio.html">
								<button class="btn btn-sm btn-success" type="button" url="/module/info/info_add.html?contextType=4" forwordSaveParam="forwordSaveParam">
									<i class="icon-plus"></i>添加音频新闻
								</button>
							</cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_publish_batch.html">
								<button class="btn btn-sm btn-primary" type="button" id="publish_batch_btn"><i class="icon-mail-forward"></i>批量发布</button>
							</cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_offline_batch.html">
								<button class="btn btn-sm btn-danger" type="button" id="offline_batch_btn"><i class="icon-arrow-down"></i>批量下线</button>
							</cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_del_batch.html">
								<button class="btn btn-sm btn-danger" type="button" id="del_batch_btn"><i class="icon-trash"></i>批量删除</button>
							</cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_clear_sort_no.html">
								<button class="btn btn-sm btn-danger" type="button" id="clear_sort_no_btn" style="display: none;"><i class="icon-remove"></i>清空排序</button>
							</cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/info/join_special_info.html">
								<a href="/module/info/common/info_classify_modal.html?appId=${not empty param.search_EQ_appId ? param.search_EQ_appId : search_EQ_appId }" role="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#info_classify_modal" id="special_pup"><i class="icon-tag"></i>加入专题</a>
                            </cqliving-security2:hasPermission>
						</div>
						</div>	
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="info_classify_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
<div id="app_modal" class="modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">推荐至其他APP</h4>
			</div>
			<div class="modal-body overflow-visible">
				<div class="row">
					<div class="col-xs-12 col-sm-12 form-horizontal">
						<div class="form-group">
	                        <label class="col-sm-2 control-label padding-right" for="recommendAppId" style="text-align: right;">区县</label>
	                        <div class="col-sm-8">
	                        	<input type="hidden" id="icid" />
	                            <select multiple="" class="chosen-select" id="recommendAppId" name="recommendAppId" data-placeholder="选择区县" style="display: none;">
	                            	<c:forEach items="${appAllList}" var="obj">
										<option value="${obj.id}">${obj.name}</option>
									</c:forEach>
	                            </select>
	                        </div>
	                    </div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-sm btn-success" id="recommend_ok_btn">
					<i class="icon-ok"></i>确认
				</button>
				<button class="btn btn-sm btn-danger" data-dismiss="modal">
					<i class="icon-remove"></i>取消
				</button>
			</div>
		</div>
	</div>
</div>

<div id="app_push_modal" class="modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">推送</h4>
			</div>
			<div class="modal-body overflow-visible">
				<div class="row">
					<div class="col-xs-12 col-sm-12 form-horizontal">
                       	<input type="hidden" id="push_icid" />
						<div class="form-group">
	                        <label class="col-sm-2 control-label padding-right" for="push_title" style="text-align: right;">标题</label>
	                        <div class="col-sm-8">
	                        	<input type="text" id="push_title" class="form-control" maxlength="100" />
	                        </div>
	                    </div>
	                    <!-- 只填标题，不需要摘要 By Tangtao 2016-11-23 -->
						<!-- <div class="form-group">
	                        <label class="col-sm-2 control-label padding-right" for="push_summary" style="text-align: right;">摘要</label>
	                        <div class="col-sm-8">
	                        	<input type="text" id="push_summary" class="form-control" maxlength="100" />
	                        </div>
	                    </div> -->
                    </div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-sm btn-success" id="push_ok_btn">
					<i class="icon-ok"></i>确认
				</button>
				<button class="btn btn-sm btn-danger" data-dismiss="modal">
					<i class="icon-remove"></i>取消
				</button>
			</div>
		</div>
	</div>
</div>

<!-- 加入专题弹出层 -->
<div id="info_classify_modal" class="modal" tabindex="-1"></div>

<!-- 新闻相关弹出层 -->
<div id="info_correlation_modal" class="modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">新闻相关</h4>
			</div>
			<div class="modal-body overflow-visible">
				
			</div>
			<div class="modal-footer">
				<button class="btn btn-sm btn-danger" data-dismiss="modal">
					<i class="icon-remove"></i>取消
				</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var appColumns = ${appColumns};

	require(["/resource/business/infoClassify/infoClassifyList.js", "/resource/business/infoClassify/info_correlation.js?v=v1"], function(obj) {
		obj.init();
		
		$("#info_classify_FormId").on("click", ".dropdown-toggle,.dropdown-menu", function(e) {
			e.cancelBubble = true;
			e.stopPropagation();
			$(this).next().show();
			$("#info_classify_FormId .dropdown-menu").show();
		});
		
		$("body").bind("click", function(e) {
			var target = e.target;
			if (target.tagName.toLowerCase() == "li" || $(target).hasClass("expand-icon")) {
				return;
			}
			$("#info_classify_FormId .dropdown-toggle").not("#columnsName").next().hide();
		});
		
		$('body').tooltip({selector: '[data-rel=tooltip]'});
	});
</script>