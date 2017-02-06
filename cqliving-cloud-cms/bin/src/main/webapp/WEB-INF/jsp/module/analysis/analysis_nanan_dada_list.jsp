<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="讲学赞积分统计" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="analysis_nanan_dada_list.html" id="analysis_nanan_dada_FormId">
	                    <div class="special-list">
	                    	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
		                            	<select name="search_EQ_analysisNananTimesId" id="search_EQ_analysisNananTimesId" class="form-control">
		                                	<option value="" >所有期数</option>
		                            		<c:forEach items="${analysisNananTimesList}" var="obj">
			                                	<option value="${obj.id}" <c:if test="${param.search_EQ_analysisNananTimesId eq obj.id}"> selected="selected"</c:if>>${obj.name}</option>
		                            		</c:forEach>
			                            </select>
	                        		</div>
                   				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_userTelephone" name="search_LIKE_userTelephone" value="${param.search_LIKE_userTelephone }" placeholder="用户手机" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_userName" name="search_LIKE_userName" value="${param.search_LIKE_userName }" placeholder="用户姓名" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                    		<!-- <div class="col-xs-6 col-sm-3"><div class="form-group"><div class="col-sm-12"></div></div></div> -->
                    		<div class="col-xs-6 col-sm-4 btn-search">
								<div class="form-group">
									<div class="col-sm-12">
										<button class="btn btn-primary btn-sm" type="button" id="searchButton">
											<i class="icon-search bigger-110"></i>查询
										</button>
										<button class="btn btn-sm" type="button" id="resetButton">
											<i class="icon-undo bigger-110"></i>重置
										</button>
										<button class="btn btn-sm btn-danger" type="button" id="detailDownload">
											<i class="icon-reply"></i>导出
										</button>
									</div>
								</div>
							</div>
                    	</div>
						<div class="col-xs-12">							
							<div class="well">
								<!--
								<cqliving-security2:hasPermission name="/module/analysis/analysis_nanan_dada_add.html">
									<button class="btn btn-sm btn-success" type="button" url="/module/analysis/analysis_nanan_dada_add.html?_model_=_model_" open-model="add" open-title="新增" onclick="javascript:void(0);"><i class="icon-plus"></i>新增</button>
									<%-- 跳转页面的方式打开 <button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='/module/analysis/analysis_nanan_dada_add.html'"><i class="icon-plus"></i>新增</button> --%>
								</cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/analysis/analysis_nanan_dada_delete_batch.html">
									<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/analysis/analysis_nanan_dada_delete_batch.html"><i class="icon-trash"></i>批量删除</button>
								</cqliving-security2:hasPermission>
								 <button class="btn btn-sm btn-primary" type="button"><i class="icon-mail-forward"></i>批量发布</button> -->
								<!-- <button class="btn btn-sm btn-primary" type="button"><i class="icon-tag"></i>加入专题</button>
								<button class="btn btn-sm btn-danger" type="button"><i class="icon-reply"></i>撤稿</button> -->
								<cqliving-security2:hasPermission name="/module/analysis/analysis_nanan_create_times.html">
									<div class="col-xs-6 col-sm-3">
										<div class="form-group">
											<div class="col-xs-12">
					                            <div class="input-group">
						                            <input id="createTimesBeginDate" type="hidden" />
						                            <input id="createTimesEndDate" type="hidden" />
						                            <input type="text" id="createTime" time_options='{"format":"YYYY-MM-DD", "timePicker": "false"}' readonly="true" value="<fmt:formatDate value="${search_GTE_createTime}" pattern="yyyy-MM-dd" /><c:if test="${not empty search_GTE_createTime}"> 至 </c:if><fmt:formatDate value="${search_LT_createTime}" pattern="yyyy-MM-dd" />" placeholder="选择生成期数时间"  class="form-control">
						                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
						                        </div>
				                       		</div>
		           						</div>
	           						</div>
	           						<button class="btn btn-primary btn-sm" type="button" id="createTimesBtn">
										<i class="icon-cog bigger-110"></i>生成期数
									</button>
								</cqliving-security2:hasPermission>
							</div>
						</div>
                	</form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="analysis_nanan_dada_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script type="text/javascript">
	require(["/resource/business/analysis/analysisNananDataList.js"], function(obj) {
		obj.init();
	});
</script>