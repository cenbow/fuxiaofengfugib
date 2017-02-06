<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="活动投票分类选项表列表" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="act_vote_itemlist.html" id="act_vote_itemFormId">
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
				                            		<input type="text" id="search_LIKE_actVoteClassifyId" name="search_LIKE_actVoteClassifyId" value="${param.search_LIKE_actVoteClassifyId }" placeholder="活动投票分类表ID（act_vote_classify表主键）" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_number" name="search_LIKE_number" value="${param.search_LIKE_number }" placeholder="选项编号" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_itemTitle" name="search_LIKE_itemTitle" value="${param.search_LIKE_itemTitle }" placeholder="选项标题，后台限制最多80个字" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_itemDesc" name="search_LIKE_itemDesc" value="${param.search_LIKE_itemDesc }" placeholder="选项描述" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_actValue" name="search_LIKE_actValue" value="${param.search_LIKE_actValue }" placeholder="实际量" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_initValue" name="search_LIKE_initValue" value="${param.search_LIKE_initValue }" placeholder="初始量" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_imageUrl" name="search_LIKE_imageUrl" value="${param.search_LIKE_imageUrl }" placeholder="选项图片" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_videoUrl" name="search_LIKE_videoUrl" value="${param.search_LIKE_videoUrl }" placeholder="视频URL" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_content" name="search_LIKE_content" value="${param.search_LIKE_content }" placeholder="内容,包含HTML标签的富文本内容" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
						                                <option value="">所有</option>
						                                	<option value="2"<c:if test="${param.search_EQ_status eq '2'}"> selected="selected"</c:if>>转码中</option>
						                                	<option value="3"<c:if test="${param.search_EQ_status eq '3'}"> selected="selected"</c:if>>正常</option>
						                                	<option value="99"<c:if test="${param.search_EQ_status eq '99'}"> selected="selected"</c:if>>删除</option>
						                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_sortNo" name="search_LIKE_sortNo" value="${param.search_LIKE_sortNo }" placeholder="排序号" class="col-xs-12" />
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
							<cqliving-security2:hasPermission name="/module/act/act_vote_itemadd.html">
								<button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='/module/act/act_vote_itemadd.html'"><i class="icon-plus"></i>新增</button>
							</cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/act/act_vote_itemdelete_batch.html">
								<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/act/act_vote_itemdelete_batch.html"><i class="icon-remove"></i>批量删除</button>
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
					<jsp:include page="act_vote_itemlist_page.jsp"></jsp:include>
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