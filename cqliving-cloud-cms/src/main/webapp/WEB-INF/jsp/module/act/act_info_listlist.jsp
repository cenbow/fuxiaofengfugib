<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="活动集合表，一个活动包含列表" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="act_info_listlist.html" id="act_info_listFormId">
		                    <div class="form-list">
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_id" name="search_LIKE_id" value="${param.search_LIKE_id }" placeholder="ID" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_appId" name="search_LIKE_appId" value="${param.search_LIKE_appId }" placeholder="客户端_ID" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_actInfoId" name="search_LIKE_actInfoId" value="${param.search_LIKE_actInfoId }" placeholder="活动ID（act_info表主键）" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            	<select name="search_EQ_type" id="search_EQ_type" class="form-control">
						                                <option value="">所有活动类型</option>
						                                	<option value="1"<c:if test="${param.search_EQ_type eq '1'}"> selected="selected"</c:if>>公告</option>
						                                	<option value="2"<c:if test="${param.search_EQ_type eq '2'}"> selected="selected"</c:if>>外链</option>
						                                	<option value="3"<c:if test="${param.search_EQ_type eq '3'}"> selected="selected"</c:if>>投票</option>
						                                	<option value="4"<c:if test="${param.search_EQ_type eq '4'}"> selected="selected"</c:if>>答题</option>
						                                	<option value="5"<c:if test="${param.search_EQ_type eq '5'}"> selected="selected"</c:if>>报名</option>
						                                	<option value="6"<c:if test="${param.search_EQ_type eq '6'}"> selected="selected"</c:if>>问卷</option>
						                                	<option value="7"<c:if test="${param.search_EQ_type eq '7'}"> selected="selected"</c:if>>征集</option>
						                                	<option value="8"<c:if test="${param.search_EQ_type eq '8'}"> selected="selected"</c:if>>抽奖</option>
						                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
						                                <option value="">所有状态</option>
						                                	<option value="1"<c:if test="${param.search_EQ_status eq '1'}"> selected="selected"</c:if>>未激活</option>
						                                	<option value="3"<c:if test="${param.search_EQ_status eq '3'}"> selected="selected"</c:if>>已激活</option>
						                                	<option value="99"<c:if test="${param.search_EQ_status eq '99'}"> selected="selected"</c:if>>删除</option>
						                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_linkUrl" name="search_LIKE_linkUrl" value="${param.search_LIKE_linkUrl }" placeholder="外链地址" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            <div class="input-group input-group-sm">
						                            <input name="search_GTE_startTime" type="hidden" value="<fmt:formatDate value="${search_GTE_startTime}" pattern="yyyy-MM-dd" />">
						                            <input name="search_LT_startTime" type="hidden" value="<fmt:formatDate value="${search_LT_startTime}" pattern="yyyy-MM-dd" />">
						                            <input type="text" id="startTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_startTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_startTime}"> 至 </c:if><fmt:formatDate value="${search_LT_startTime}" pattern="yyyy-MM-dd" />" placeholder="活动开始时间"  class="form-control">
						                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
						                        </div>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            <div class="input-group input-group-sm">
						                            <input name="search_GTE_endTime" type="hidden" value="<fmt:formatDate value="${search_GTE_endTime}" pattern="yyyy-MM-dd" />">
						                            <input name="search_LT_endTime" type="hidden" value="<fmt:formatDate value="${search_LT_endTime}" pattern="yyyy-MM-dd" />">
						                            <input type="text" id="endTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_endTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_endTime}"> 至 </c:if><fmt:formatDate value="${search_LT_endTime}" pattern="yyyy-MM-dd" />" placeholder="活动结束时间"  class="form-control">
						                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
						                        </div>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            <div class="input-group input-group-sm">
						                            <input name="search_GTE_createTime" type="hidden" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd" />">
						                            <input name="search_LT_createTime" type="hidden" value="<fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd" />">
						                            <input type="text" id="createTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_createTime}"> 至 </c:if><fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd" />" placeholder="创建时间"  class="form-control">
						                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
						                        </div>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_creatorId" name="search_LIKE_creatorId" value="${param.search_LIKE_creatorId }" placeholder="创建人" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_creator" name="search_LIKE_creator" value="${param.search_LIKE_creator }" placeholder="创建人姓名" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            <div class="input-group input-group-sm">
						                            <input name="search_GTE_updateTime" type="hidden" value="<fmt:formatDate value="${search_GTE_updateTime}" pattern="yyyy-MM-dd" />">
						                            <input name="search_LT_updateTime" type="hidden" value="<fmt:formatDate value="${search_LT_updateTime}" pattern="yyyy-MM-dd" />">
						                            <input type="text" id="updateTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_updateTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_updateTime}"> 至 </c:if><fmt:formatDate value="${search_LT_updateTime}" pattern="yyyy-MM-dd" />" placeholder="更新时间"  class="form-control">
						                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
						                        </div>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_updatorId" name="search_LIKE_updatorId" value="${param.search_LIKE_updatorId }" placeholder="更新人ID" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_updator" name="search_LIKE_updator" value="${param.search_LIKE_updator }" placeholder="更新人" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                    		<div class="col-xs-6 col-sm-12">
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
							<cqliving-security2:hasPermission name="/module/act/act_info_listadd.html">
								<button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='/module/act/act_info_listadd.html'"><i class="icon-plus"></i>新增</button>
							</cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/act/act_info_listdelete_batch.html">
								<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/act/act_info_listdelete_batch.html"><i class="icon-remove"></i>批量删除</button>
							</cqliving-security2:hasPermission>
							<button class="btn btn-sm btn-primary" type="button"><i class="icon-mail-forward"></i>批量发布</button>
							<button class="btn btn-sm btn-primary" type="button"><i class="icon-tag"></i>加入专题</button>
							<button class="btn btn-sm btn-warning" type="button"><i class="icon-download"></i>批量下线</button>
							<button class="btn btn-sm btn-danger" type="button"><i class="icon-remove"></i>清空排序</button>
							<button class="btn btn-sm btn-danger" type="button"><i class="icon-arrow-down"></i>撤稿</button>
						</div>
		                </form>
					</div>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="act_info_listlist_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>


<script type="text/javascript">
require(['cloud.table.curd','cloud.time.input'], function(tableCurd,timeInput){
	tableCurd.initTableCrud();
	timeInput.initTimeInput();
});
</script>