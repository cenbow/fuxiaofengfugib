<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="新闻查询" name="_breadcrumbs_1"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="classify_news_list.html" id="info_classify_FormId">
	                    <div class="special-list">
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
			                            <input type="text" id="search_EQ_id" name="search_EQ_id" value="${param.search_EQ_id}" placeholder="新闻ID" class="col-xs-12 form-control" />
	                        		</div>
                   				</div>
							</div>
                        	    
                        	    <div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
			                            <div class="input-group">
				                            <input name="search_GTE_onlineTime" type="hidden" value="<fmt:formatDate value="${search_GTE_onlineTime}" pattern="yyyy-MM-dd HH:mm" />">
				                            <input name="search_LT_onlineTime" type="hidden" value="<fmt:formatDate value="${search_LT_onlineTime}" pattern="yyyy-MM-dd HH:mm" />">
				                            <input type="text" placeholder="上线时间" id="onlineTime" class="form-control" readonly="true" time_options='{"format":"YYYY-MM-DD HH:mm"}' value="<fmt:formatDate value='${search_GTE_onlineTime}' pattern='yyyy-MM-dd HH:mm'/><c:if test='${not empty search_GTE_publishTime}'>至</c:if><fmt:formatDate value='${search_LT_onlineTime}' pattern='yyyy-MM-dd HH:mm'/>">
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
							<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_publish_batch.html">
								<button class="btn btn-sm btn-primary" type="button" id="publish_batch_btn"><i class="icon-mail-forward"></i>批量发布</button>
							</cqliving-security2:hasPermission>
							<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_offline_batch.html">
								<button class="btn btn-sm btn-danger" type="button" id="offline_batch_btn"><i class="icon-arrow-down"></i>批量下线</button>
							</cqliving-security2:hasPermission>
						</div>
						</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="classify_news_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script type="text/javascript">
    var appColumns = [];
	require(["/resource/business/infoClassify/infoClassifyList.js?v=v2"], function(list) {
		$('body').tooltip({selector: '[data-rel=tooltip]'});
		list.init();
	});
</script>