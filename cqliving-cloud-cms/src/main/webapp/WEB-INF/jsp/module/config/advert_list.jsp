<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="广告图管理" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="advert_list.html" id="advert_FormId">
	                    <div class="special-list">
	                    	<c:if test="${not empty appList}">
							<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-sm-12">
										<select name="search_EQ_appId" id="search_EQ_appId" class="form-control chosen-select">
											<c:forEach items="${appList}" var="app">
			                                  <option value="${app.id}" <c:if test="${(param.search_EQ_appId eq app.id)or (empty param.search_EQ_appId and idx.first)}">selected</c:if>>${app.name}</option>
			                               </c:forEach>
										 </select>
	                        		</div>
                   				</div>
							</div>
							</c:if>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
			                                <option value="">所有状态</option>
			                                <option value="3"<c:if test="${param.search_EQ_status eq '3'}"> selected="selected"</c:if>>上线</option>
			                                <option value="88"<c:if test="${param.search_EQ_status eq '88'}"> selected="selected"</c:if>>下线</option>
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
						<cqliving-security2:hasPermission name="/module/advert/advert_add.html">
							<button class="btn btn-sm btn-success" type="button" url="/module/advert/advert_add.html?_model_=_model_" open-model="add" open-title="新增" onclick="javascript:void(0);"><i class="icon-plus"></i>新增</button>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/advert/on_line.html">
				        	<button class="btn btn-sm btn-primary on-out-batch" type="button" title="已上线的数据将自动过滤 " oper="1" url="/module/advert/on_line.html"><i class="icon-arrow-up"></i>批量上线</button>
				        </cqliving-security2:hasPermission>
				        <cqliving-security2:hasPermission name="/module/advert/out_line.html">
				        	<button class="btn btn-sm btn-danger on-out-batch" type="button" title="已下线的数据将自动过滤 " oper="2" url="/module/advert/out_line.html"><i class="icon-arrow-down"></i>批量下线</button>
				        </cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/advert/advert_delete_batch.html">
							<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/advert/advert_delete_batch.html"><i class="icon-trash"></i>批量删除</button>
						</cqliving-security2:hasPermission>
					</div>
					</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="advert_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script type="text/javascript">
var STATUS3 = ${STATUS3} ; 
var STATUS88 = ${STATUS88} ;
require(['/resource/business/config/advert_list.js'], function(list){
	list.init();
});
</script>