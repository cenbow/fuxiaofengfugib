<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="专题管理|/module/infoClassify/info_classify_list_special.html" name="_breadcrumbs_1"/>
		<jsp:param value="专题新闻列表" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="info_classify_list_special_sub.html" id="info_classify_FormId">
							<!-- 专题 id -->
							<input type="hidden" name="mid" value="${param.mid}" />
							<input type="hidden" name="appId" value="${param.appId}" />
		                    <div class="special-list">
		                    	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-xs-12">
			                            	<select class="chosen-select" data-placeholder="分类" name="search_EQ_themeId" id="search_EQ_themeId">
				                                <option value="">所有分类</option>
				                                <c:forEach items="${themeList}" var="obj">
				                                	<option value="${obj.id}"<c:if test="${param.search_EQ_themeId eq obj.id}">selected="selected"</c:if>>${obj.name}</option>
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
			                            	<select name="search_EQ_listViewType" id="search_EQ_listViewType" class="form-control">
				                                <option value="">所有类型</option>
				                                <c:forEach items="${allListViewTypes}" var="obj">
				                                	<option value="${obj.key}"<c:if test="${param.search_EQ_listViewType eq obj.key}">selected="selected"</c:if>>${obj.value}</option>
				                                </c:forEach>
				                            </select>
		                        		</div>
                    				</div>
								</div>
								<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-xs-12">
			                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
				                                <option value="">所有状态</option>
				                                <c:forEach items="${allStatuss}" var="obj">
				                                	<c:if test="${obj.key lt statusDeleted}">
					                                	<option value="${obj.key}"<c:if test="${param.search_EQ_status eq obj.key}">selected="selected"</c:if>>${obj.value}</option>
				                                	</c:if>
				                                </c:forEach>
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
								<div class="col-xs-6 col-sm-3"></div>
								<div class="col-xs-6 col-sm-3"></div>
								<div class="col-xs-6 col-sm-3"></div>
								<div class="col-xs-6 col-sm-3 btn-search">
<!-- 	                    		<div class="col-xs-6 col-sm-12"> -->
									<div class="form-group">
										<div class="col-sm-12">
											<button class="btn btn-primary btn-sm" type="button" id="searchButton">
												<i class="icon-search bigger-110"></i>查询
											</button>
											<button class="btn btn-sm" type="button" id="resetButton">
												<i class="icon-undo bigger-110"></i>重置
											</button>
										</div>
									</div>
								</div>
	                    	</div>
							<div class="clear"></div>							
							<div class="well">
								<cqliving-security2:hasPermission name="/module/info/add_special_sub_info.html">
									<button type="button" class="btn btn-sm btn-success" url="/module/info/add_special_sub_info.html?icid=${param.mid}&appId=${param.appId}" forwordSaveParam="forwordSaveParam"><i class="icon-plus"></i>添加子新闻</button>
	                            </cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_move_out.html">
									<button type="button" class="btn btn-sm btn-primary" id="move_out_btn"><i class="icon-mail-forward"></i>移出专题</button>
								</cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_offline_batch.html">
								<%-- <cqliving-security2:hasPermission name="/module/infoClassify/info_correlation_offline_batch.html"> --%>
									<button type="button" class="btn btn-sm btn-danger" id="offline_batch_btn"><i class="icon-arrow-down"></i>批量下线</button>
								</cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_clear_special_sort_no.html">
									<button type="button" class="btn btn-sm btn-danger" id="clear_sort_no_btn" style="display: none;"><i class="icon-remove"></i>清空排序</button>
								</cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/info/join_info_modal.html">
									<a href="/module/info/join_info_modal.html?icid=${param.mid }&appId=${param.appId}" role="button" class="btn btn-sm btn-info" data-toggle="modal" data-target="#join_info_modal"><i class="icon-tag"></i>加入新闻</a>
	                            </cqliving-security2:hasPermission>
	                            <cqliving-security2:hasPermission name="/module/info/join_special_info.html">
									<a href="/module/info/common/info_classify_modal.html?appId=${param.appId}" role="button" class="btn btn-sm btn-primary" data-toggle="modal" data-target="#info_classify_modal" id="special_pup"><i class="icon-tag"></i>加入专题</a>
	                            </cqliving-security2:hasPermission>
							</div>
		                </form>
					</div>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="info_classify_special_sub_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
<div id="move_out_modal" class="modal" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="blue bigger">移出专题</h4>
			</div>
			<div class="modal-body overflow-visible">
				<div class="row">
					<div class="col-xs-12 col-sm-12">
						<div class="form-group">
	                        <div class="col-sm-12">
	                        	<input type="hidden" id="icid" />
	                        	<input type="hidden" id="cid" />
	                        	<div class="col-sm-12 radio">
		                            <label class="radio-2">
		                                <input type="radio" class="ace" name="operateType" value="1" checked="checked"><span class="lbl">恢复栏目显示</span>
		                            </label>
		                            <label class="radio-2">
		                                <input type="radio" class="ace" name="operateType" value="2"><span class="lbl">下线</span>
		                            </label>
		                            <label class="radio-2">
		                                <input type="radio" class="ace" name="operateType" value="3"><span class="lbl">重新选取栏目</span>
	                                </label>
		                        </div>
	                        </div>
	                    </div>
	                    <div class="form-group" style="display: none;" id="app_column_choose_div">
	                        <div class="col-sm-12">
	                        	<!-- <input type="text" id="appColumnId" name="appColumnId" value="" placeholder="选择栏目（点了没反应是吧？这就对了）" class="col-xs-12" /> -->
	                            <%-- <select multiple="" class="chosen-select" id="recommendAppId" name="recommendAppId" data-placeholder="选择区县" style="display: none;">
	                            	<c:forEach items="${appList}" var="obj">
										<option value="${obj.id}">${obj.name}</option>
									</c:forEach>
	                            </select> --%>
								<div class="input-group">
									<input name=appColumnId id="appColumnId" type="hidden" />
									<input name="columnsName" type="text" placeholder="请选择所属栏目"  class="col-xs-12 form-control" />
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
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-sm btn-primary" id="move_out_ok_btn">
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

<!-- 加入新闻弹出层 -->
<div id="join_info_modal" class="modal" tabindex="-1"></div>
<!-- 加入专题弹出层 -->
<div id="info_classify_modal" class="modal" tabindex="-1"></div>
<div id="info_correlation_modal" class=""></div>
<script type="text/javascript">
	var appColumns = ${appColumns};

	require(["/resource/business/infoClassify/infoClassifySpecialSubList.js"], function(obj) {
		obj.init();
		
		$("#app_column_choose_div").on("click", ".dropdown-toggle,.dropdown-menu", function(e) {
			e.cancelBubble = true;
			e.stopPropagation();
			$(this).next().show();
		});
		
// 		$("body").bind("click", function(e) {
// 			var target = e.target;
// 			if (target.tagName.toLowerCase() == "li" || $(target).hasClass("expand-icon")) {
// 				return;
// 			}
// 			$("#app_column_choose_div .dropdown-toggle").next().hide();
// 		});
		
		$("body").tooltip({selector: "[data-rel=tooltip]"});
	});
</script>