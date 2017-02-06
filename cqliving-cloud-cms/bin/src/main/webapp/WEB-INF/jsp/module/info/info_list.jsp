<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"></jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="info_list.html" id="info_FormId">
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
				                            	<input type="text" id="search_LIKE_title" name="search_LIKE_title" value="${param.search_LIKE_title }" placeholder="资讯标题" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_infoLabel" name="search_LIKE_infoLabel" value="${param.search_LIKE_infoLabel }" placeholder="资讯标签,多个用,分隔，注意  前边也要带,号，例   ,1,2" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_imformationUrl" name="search_LIKE_imformationUrl" value="${param.search_LIKE_imformationUrl }" placeholder="资讯内容URL" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_synopsis" name="search_LIKE_synopsis" value="${param.search_LIKE_synopsis }" placeholder="新闻摘要" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            	<select name="search_EQ_type" id="search_EQ_type" class="form-control">
						                                <option value="">所有</option>
						                                	<option value="0"<c:if test="${param.search_EQ_type eq '0'}"> selected="selected"</c:if>>普通新闻</option>
						                                	<option value="2"<c:if test="${param.search_EQ_type eq '2'}"> selected="selected"</c:if>>主题新闻</option>
						                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_keywords" name="search_LIKE_keywords" value="${param.search_LIKE_keywords }" placeholder="关键字" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_infoSource" name="search_LIKE_infoSource" value="${param.search_LIKE_infoSource }" placeholder="来源网站，文字" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_plViewType" name="search_LIKE_plViewType" value="${param.search_LIKE_plViewType }" placeholder="平台可见类型" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            	<select name="search_EQ_commentType" id="search_EQ_commentType" class="form-control">
						                                <option value="">所有</option>
						                                	<option value="0"<c:if test="${param.search_EQ_commentType eq '0'}"> selected="selected"</c:if>>允许</option>
						                                	<option value="1"<c:if test="${param.search_EQ_commentType eq '1'}"> selected="selected"</c:if>>不允许</option>
						                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            	<select name="search_EQ_commentValidateType" id="search_EQ_commentValidateType" class="form-control">
						                                <option value="">所有</option>
						                                	<option value="0"<c:if test="${param.search_EQ_commentValidateType eq '0'}"> selected="selected"</c:if>>不需审核</option>
						                                	<option value="1"<c:if test="${param.search_EQ_commentValidateType eq '1'}"> selected="selected"</c:if>>需要审核</option>
						                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            	<select name="search_EQ_validateType" id="search_EQ_validateType" class="form-control">
						                                <option value="">所有</option>
						                                	<option value="0"<c:if test="${param.search_EQ_validateType eq '0'}"> selected="selected"</c:if>>无需审核</option>
						                                	<option value="1"<c:if test="${param.search_EQ_validateType eq '1'}"> selected="selected"</c:if>>需审核</option>
						                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_initCount" name="search_LIKE_initCount" value="${param.search_LIKE_initCount }" placeholder="初始阅读量" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            	<select name="search_EQ_addType" id="search_EQ_addType" class="form-control">
						                                <option value="">所有</option>
						                                	<option value="0"<c:if test="${param.search_EQ_addType eq '0'}"> selected="selected"</c:if>>一次添加</option>
						                                	<option value="1"<c:if test="${param.search_EQ_addType eq '1'}"> selected="selected"</c:if>>逐步添加</option>
						                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_topTime" name="search_LIKE_topTime" value="${param.search_LIKE_topTime }" placeholder="达到峰值时间，以秒为单位" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_viewCount" name="search_LIKE_viewCount" value="${param.search_LIKE_viewCount }" placeholder="资讯浏览量" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_replyCount" name="search_LIKE_replyCount" value="${param.search_LIKE_replyCount }" placeholder="资讯回复量" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            	<select name="search_EQ_sendStatus" id="search_EQ_sendStatus" class="form-control">
						                                <option value="">所有</option>
						                                	<option value="0"<c:if test="${param.search_EQ_sendStatus eq '0'}"> selected="selected"</c:if>>未推送</option>
						                                	<option value="1"<c:if test="${param.search_EQ_sendStatus eq '1'}"> selected="selected"</c:if>>已推送</option>
						                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_content" name="search_LIKE_content" value="${param.search_LIKE_content }" placeholder="内容,新闻的实际内容URL,对应生成后内容" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_contextText" name="search_LIKE_contextText" value="${param.search_LIKE_contextText }" placeholder="资讯内容的全文本，不带HTML标签的" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_classifyId" name="search_LIKE_classifyId" value="${param.search_LIKE_classifyId }" placeholder="原始栏目ID，为方便后续统计，新闻先归属其中一个栏目。" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            	<select name="search_EQ_contextType" id="search_EQ_contextType" class="form-control">
						                                <option value="">所有</option>
						                                	<option value="0"<c:if test="${param.search_EQ_contextType eq '0'}"> selected="selected"</c:if>>纯文本</option>
						                                	<option value="1"<c:if test="${param.search_EQ_contextType eq '1'}"> selected="selected"</c:if>>多图</option>
						                                	<option value="2"<c:if test="${param.search_EQ_contextType eq '2'}"> selected="selected"</c:if>>原创</option>
						                                	<option value="3"<c:if test="${param.search_EQ_contextType eq '3'}"> selected="selected"</c:if>>外链</option>
						                                	<option value="4"<c:if test="${param.search_EQ_contextType eq '4'}"> selected="selected"</c:if>>音频</option>
						                                	<option value="5"<c:if test="${param.search_EQ_contextType eq '5'}"> selected="selected"</c:if>>视频</option>
						                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            <div class="input-group input-group-sm">
						                            <input name="search_GTE_createTime" type="hidden" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd" />">
						                            <input name="search_LT_createTime" type="hidden" value="<fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd" />">
						                            <input type="text" id="createTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_publishTime}"> 至 </c:if><fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd" />" placeholder="创建时间"  class="form-control">
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
						                            <input type="text" id="updateTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_updateTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_publishTime}"> 至 </c:if><fmt:formatDate value="${search_LT_updateTime}" pattern="yyyy-MM-dd" />" placeholder="更新时间"  class="form-control">
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
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_auditingId" name="search_LIKE_auditingId" value="${param.search_LIKE_auditingId }" placeholder="审核人ID" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_auditingtor" name="search_LIKE_auditingtor" value="${param.search_LIKE_auditingtor }" placeholder="审核人姓名" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            <div class="input-group input-group-sm">
						                            <input name="search_GTE_auditingTime" type="hidden" value="<fmt:formatDate value="${search_GTE_auditingTime}" pattern="yyyy-MM-dd" />">
						                            <input name="search_LT_auditingTime" type="hidden" value="<fmt:formatDate value="${search_LT_auditingTime}" pattern="yyyy-MM-dd" />">
						                            <input type="text" id="auditingTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_auditingTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_publishTime}"> 至 </c:if><fmt:formatDate value="${search_LT_auditingTime}" pattern="yyyy-MM-dd" />" placeholder="审核时间"  class="form-control">
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
											<button class="btn btn-sm" type="reset">
												<i class="icon-undo bigger-110"></i>重置
											</button>
										</div>
									</div>
								</div>
	                    	</div>
							<div class="clear"></div>							
							<div class="well">
							<cqliving-security2:hasPermission
								name="/module/version/place_version_add.html">
								<button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='/module/info/info_add.html'"><i class="icon-plus"></i>新增</button>
							</cqliving-security2:hasPermission>
							<button class="btn btn-sm btn-primary"><i class="icon-mail-forward"></i>批量发布</button>
							<button class="btn btn-sm btn-primary"><i class="icon-tag"></i>加入专题</button>
							<button class="btn btn-sm btn-warning"><i class="icon-download"></i>批量下线</button>
							<button class="btn btn-sm btn-danger"><i class="icon-remove"></i>清空排序</button>
							<button class="btn btn-sm btn-danger"><i class="icon-arrow-down"></i>撤稿</button>
						</div>
		                </form>
					</div>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="info_list_page.jsp"></jsp:include>
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