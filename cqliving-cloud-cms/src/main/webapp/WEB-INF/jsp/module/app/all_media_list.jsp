<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="全媒体表列表" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="all_media_list.html" id="all_media_FormId"  onkeypress="if(event.keyCode==13||event.which==13){return false;}">
	                    <div class="special-list">
                        	
                        	<div class="col-xs-6 col-sm-3  ${not empty apps and fn:length(apps) ge 2 ? '' : 'hidden'}">
								<div class="form-group">
									<div class="col-xs-12">
									    <c:choose>
									       <c:when test="${not empty apps and fn:length(apps) ge 2}">
									          <select name="search_EQ_appId" class="chosen_select">
									            <c:forEach items="${apps}" var="app">
										            <option value="${app.id }"  ${param.search_EQ_appId eq app.id ? 'selected' : ''}>${app.name }</option>
									            </c:forEach>
									          </select>
									       </c:when>
									       <c:otherwise>
									          <input type="hidden" id="search_EQ_appId" name="search_EQ_appId" value="${apps[0].id}" placeholder="客户端_ID" class="col-xs-12 form-control" />
									       </c:otherwise>
									    </c:choose>
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_name" name="search_LIKE_name" value="${param.search_LIKE_name }" placeholder="全媒体名称" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_type" id="search_EQ_type" class="form-control">
			                                <option value="">所有</option>
			                                <option value="1"<c:if test="${param.search_EQ_type eq '1'}"> selected="selected"</c:if>>链接</option>
			                                <option value="2"<c:if test="${param.search_EQ_type eq '2'}"> selected="selected"</c:if>>弹层跳栏目</option>
			                                <option value="3"<c:if test="${param.search_EQ_type eq '3'}"> selected="selected"</c:if>>滚动跳栏目</option>
			                            </select>
	                        		</div>
                				</div>
							</div>
                    		<!-- <div class="col-xs-6 col-sm-3"><div class="form-group"><div class="col-sm-12"></div></div></div> -->
                    		<div class="col-xs-6 col-sm-3 btn-search">
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
						<div class="col-xs-12">							
						<div class="well">
						<cqliving-security2:hasPermission name="/module/all_media/all_media_add.html">
							<!-- <button class="btn btn-sm btn-success" type="button" url="/module/all_media/all_media_add.html?_model_=_model_" open-model="add" open-title="新增" onclick="javascript:void(0);"><i class="icon-plus"></i>新增</button> -->
							<button class="btn btn-sm btn-success" type="button" forwordSaveParam="forwordSaveParam" url="/module/all_media/all_media_add.html"><i class="icon-plus"></i>新增</button>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/all_media/all_media_delete.html">
							<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/all_media/common/all_media_delete_batch.html"><i class="icon-trash"></i>批量删除</button>
						</cqliving-security2:hasPermission>
					</div>
					</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="all_media_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>


<script type="text/javascript">
require(['cloud.table.curd','cloud.time.input','chosen'], function(tableCurd,timeInput){
	tableCurd.initTableCrud();
	timeInput.initTimeInput();
	$(".chosen_select").chosen({search_contains:true});//width:'300px',
	
	$("#resetButton").off("click").on("click",function(){
		var paramForm = $(this).closest("form");
		paramForm.find(":input[name=search_LIKE_name],select").not(':button, :submit, :reset')  
		 .val('')  
		 .removeAttr('checked')  
		 .removeAttr('selected');
	});
});
</script>