<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="活动投票表列表" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="act_votelist.html" id="act_voteFormId">
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
				                            		<input type="text" id="search_LIKE_actInfoListId" name="search_LIKE_actInfoListId" value="${param.search_LIKE_actInfoListId }" placeholder="活动集合表ID（act_info_list表主键）" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            	<select name="search_EQ_type" id="search_EQ_type" class="form-control">
						                                <option value="">所有</option>
						                                	<option value="1"<c:if test="${param.search_EQ_type eq '1'}"> selected="selected"</c:if>>普通投票</option>
						                                	<option value="2"<c:if test="${param.search_EQ_type eq '2'}"> selected="selected"</c:if>>分类投票</option>
						                            </select>
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
					                            	<select name="search_EQ_limitRateType" id="search_EQ_limitRateType" class="form-control">
						                                <option value="">所有</option>
						                                	<option value="0"<c:if test="${param.search_EQ_limitRateType eq '0'}"> selected="selected"</c:if>>无限制</option>
						                                	<option value="1"<c:if test="${param.search_EQ_limitRateType eq '1'}"> selected="selected"</c:if>>总数限制</option>
						                                	<option value="2"<c:if test="${param.search_EQ_limitRateType eq '2'}"> selected="selected"</c:if>>每日限制</option>
						                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_limitRateTimes" name="search_LIKE_limitRateTimes" value="${param.search_LIKE_limitRateTimes }" placeholder="数量" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            	<select name="search_EQ_limitSingleType" id="search_EQ_limitSingleType" class="form-control">
						                                <option value="">所有</option>
						                                	<option value="0"<c:if test="${param.search_EQ_limitSingleType eq '0'}"> selected="selected"</c:if>>无限制</option>
						                                	<option value="1"<c:if test="${param.search_EQ_limitSingleType eq '1'}"> selected="selected"</c:if>>总数限制</option>
						                                	<option value="2"<c:if test="${param.search_EQ_limitSingleType eq '2'}"> selected="selected"</c:if>>每日限制</option>
						                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_limitSingleTimes" name="search_LIKE_limitSingleTimes" value="${param.search_LIKE_limitSingleTimes }" placeholder="数量" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            	<select name="search_EQ_limitRuleType" id="search_EQ_limitRuleType" class="form-control">
						                                <option value="">所有</option>
						                                	<option value="0"<c:if test="${param.search_EQ_limitRuleType eq '0'}"> selected="selected"</c:if>>单选</option>
						                                	<option value="1"<c:if test="${param.search_EQ_limitRuleType eq '1'}"> selected="selected"</c:if>>多选</option>
						                                	<option value="2"<c:if test="${param.search_EQ_limitRuleType eq '2'}"> selected="selected"</c:if>>限制选多少项</option>
						                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_limitRuleTimes" name="search_LIKE_limitRuleTimes" value="${param.search_LIKE_limitRuleTimes }" placeholder="当limit_rule_type为1和0时，值默认为0但无效，当为限制值时有效" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            	<select name="search_EQ_loggedStatus" id="search_EQ_loggedStatus" class="form-control">
						                                <option value="">所有</option>
						                                	<option value="0"<c:if test="${param.search_EQ_loggedStatus eq '0'}"> selected="selected"</c:if>>否</option>
						                                	<option value="1"<c:if test="${param.search_EQ_loggedStatus eq '1'}"> selected="selected"</c:if>>是</option>
						                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            	<select name="search_EQ_isShare" id="search_EQ_isShare" class="form-control">
						                                <option value="">所有</option>
						                                	<option value="0"<c:if test="${param.search_EQ_isShare eq '0'}"> selected="selected"</c:if>>否</option>
						                                	<option value="1"<c:if test="${param.search_EQ_isShare eq '1'}"> selected="selected"</c:if>>是</option>
						                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_shareAddTimes" name="search_LIKE_shareAddTimes" value="${param.search_LIKE_shareAddTimes }" placeholder="分享后增加投票次数，当is_share=0时该值无效" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            	<select name="search_EQ_limitShareType" id="search_EQ_limitShareType" class="form-control">
						                                <option value="">所有</option>
						                                	<option value="0"<c:if test="${param.search_EQ_limitShareType eq '0'}"> selected="selected"</c:if>>无限制</option>
						                                	<option value="1"<c:if test="${param.search_EQ_limitShareType eq '1'}"> selected="selected"</c:if>>总数限制</option>
						                                	<option value="2"<c:if test="${param.search_EQ_limitShareType eq '2'}"> selected="selected"</c:if>>每日限制</option>
						                            </select>
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            		<input type="text" id="search_LIKE_limitShareTimes" name="search_LIKE_limitShareTimes" value="${param.search_LIKE_limitShareTimes }" placeholder="当limit_share_type为1和0时，值默认为0但无效，当为限制值时有效" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
				                            	<input type="text" id="search_LIKE_actTemplateCode" name="search_LIKE_actTemplateCode" value="${param.search_LIKE_actTemplateCode }" placeholder="模板CODE（act_template表里面的code）" class="col-xs-12" />
		                        		</div>
                    				</div>
								</div>
	                        	<div class="col-xs-6 col-sm-3">
									<div class="form-group">
										<div class="col-sm-12">
					                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
						                                <option value="">所有</option>
						                                	<option value="3"<c:if test="${param.search_EQ_status eq '3'}"> selected="selected"</c:if>>正常</option>
						                                	<option value="99"<c:if test="${param.search_EQ_status eq '99'}"> selected="selected"</c:if>>删除</option>
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
							<cqliving-security2:hasPermission name="/module/act/act_voteadd.html">
								<button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='/module/act/act_voteadd.html'"><i class="icon-plus"></i>新增</button>
							</cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/act/act_votedelete_batch.html">
								<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/act/act_votedelete_batch.html"><i class="icon-remove"></i>批量删除</button>
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
					<jsp:include page="act_votelist_page.jsp"></jsp:include>
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