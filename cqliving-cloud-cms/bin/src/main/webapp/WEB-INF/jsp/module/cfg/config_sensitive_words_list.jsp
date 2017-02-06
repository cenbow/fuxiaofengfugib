<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="敏感词管理|/module/sensitiveWords/config_sensitive_words_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="列表" name="_breadcrumbs_5"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="config_sensitive_words_list.html" id="config_sensitive_words_FormId">
	                    <div class="special-list">
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
			                            	<input type="text" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="敏感词" class="col-xs-12 form-control" />
	                        		</div>
                   				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
				                            	<select name="search_EQ_type" id="search_EQ_type" class="col-xs-12 form-control">
					                                <option value="">所有类型</option>
					                                	<option value="1"<c:if test="${param.search_EQ_type eq '1'}"> selected="selected"</c:if>>注册敏感词</option>
					                                	<option value="2"<c:if test="${param.search_EQ_type eq '2'}"> selected="selected"</c:if>>普通敏感词</option>
					                                	<option value="3"<c:if test="${param.search_EQ_type eq '3'}"> selected="selected"</c:if>>短信下发敏感词</option>
					                            </select>
	                        		</div>
                   				</div>
							</div>
							<c:if test="${not empty appList}">
									<div class="col-xs-6 col-sm-3">
										<div class="form-group">
											<div class="col-sm-12">
		                            			<select name="search_EQ_appId" id="search_EQ_appId" class="form-control chosen-select">
		                            				<option value="">所有APP</option>
													<c:forEach items="${appList}" var="app">
					                                  <option value="${app.id}" <c:if test="${param.search_EQ_appId eq app.id}">selected</c:if>>${app.name}</option>
					                               </c:forEach>
												 </select>
			                        		</div>
	                    				</div>
									</div>
								</c:if>
                    		<div class="col-xs-6 col-sm-3 btn-search">
								<div class="form-group pull-left">
									<div class="col-sm-12">
										<input type="text" style="display: none;"/>										
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
						<div class="col-xs-12">							
							<div class="well">
								<cqliving-security2:hasPermission name="/module/sensitiveWords/config_sensitive_words_add.html">
									<button class="btn btn-sm btn-success" type="button" url="/module/sensitiveWords/config_sensitive_words_add.html?_model_=_model_" open-model="add" open-title="新增" onclick="javascript:void(0);"><i class="icon-plus"></i>新增</button>
								</cqliving-security2:hasPermission>
								<cqliving-security2:hasPermission name="/module/sensitiveWords/config_sensitive_words_delete_batch.html">
									<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/sensitiveWords/config_sensitive_words_delete_batch.html"><i class="icon-remove"></i>批量删除</button>
								</cqliving-security2:hasPermission>
							</div>
						</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="config_sensitive_words_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script type="text/javascript">
require(['cloud.table.curd','chosen'], function(tableCurd){
	tableCurd.initTableCrud();
	$('body').tooltip({selector:'[data-rel=tooltip]'});
	$(".chosen-select").chosen({search_contains:true});
});
</script>