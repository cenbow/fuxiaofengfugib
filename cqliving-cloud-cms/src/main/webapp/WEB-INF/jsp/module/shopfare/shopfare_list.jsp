<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="运费模板明细表列表" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="shopfare_list.html" id="shopfare_FormId">
	                    <div class="special-list">
                        	<div class="col-xs-6 col-sm-3  ${fn:length(allApps) le 1 ? 'hidden' : '' }">
								<div class="form-group">
									<div class="col-xs-12">
									    <select name="search_EQ_appId" id="search_EQ_appId" class="chosen-select">
									       <c:forEach items="${allApps}" var="app">
										       <option value="${app.id }" ${defaultAppId eq app.id ? 'selected' : '' }>${app.name }</option>
									       </c:forEach>
									    </select>
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="模板名称" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
			                                <option value="">所有</option>
			                                <c:forEach items="${allStatuss }" var="sta">
			                                   <option value="${sta.key }"  <c:if test="${param.search_EQ_status eq sta.key}"> selected="selected"</c:if>>${sta.value }</option>
			                                </c:forEach>
			                            </select>
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
						<div class="col-xs-12">							
						<div class="well">
						<cqliving-security2:hasPermission name="/module/shopfare/shopfare_add.html">
							<button class="btn btn-sm btn-success" type="button" url="/module/shopfare/shopfare_add.html" forwordSaveParam="forwordSaveParam"><i class="icon-plus"></i>新增</button>
						</cqliving-security2:hasPermission>
						<%-- <cqliving-security2:hasPermission name="/module/shopfare/shopfare_delete_batch.html">
							<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/shopfare/shopfare_delete_batch.html"><i class="icon-trash"></i>批量删除</button>
						</cqliving-security2:hasPermission> --%>
						<%-- <button class="btn btn-sm btn-primary" type="button"><i class="icon-mail-forward"></i>批量发布</button> --%>
					</div>
					</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="shopfare_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script type="text/javascript">
require(['cloud.table.curd','cloud.time.input','chosen'], function(tableCurd,timeInput){
	tableCurd.initTableCrud();
	timeInput.initTimeInput();
	$(".chosen-select").chosen({search_contains:true});
});
</script>