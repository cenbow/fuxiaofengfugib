<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="main-content">

	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp"></jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<form class="form-horizontal" role="form" action="info_classify_list.html" id="info_classifyFormId">
                    		<div class="form-group">
		                        <label class="col-sm-1 control-label no-padding-right" for="search_EQ_status">状态</label>
		                        <div class="col-sm-2">
	                            	<select name="search_EQ_status" id="search_EQ_status" class="form-control">
		                                <option value="">所有</option>
		                                <c:forEach items="${allStatus}" var="obj">
		                                	<option value="${obj.key}" <c:if test="${param.search_EQ_status eq obj.key}">selected="selected"</c:if>>${obj.value}</option>
		                                </c:forEach>
		                            </select>
		                        </div>
	                        	
		                        <label class="col-sm-1 control-label no-padding-right" for="search_GTE_onlineTime">上线时间</label>
		                        <div class="col-sm-2">
			                            <script type="text/javascript">
			                                /* require(["daterangepicker"], function($){
			                                    $(function(){
			                                        var elm = $("#onlineTime");
			                                        $(elm).daterangepicker({
			                                            startDate: $(elm).prev().prev().val(),
			                                            endDate: $(elm).prev().val(),
			                                            format: "YYYY-MM-DD",
			                                            showDropdowns: true
			                                        }, function(start, end){
			                                            $(elm).find(".date-title").html(start.toDateStr() + " 至 " + end.toDateStr());
			                                            $(elm).prev().prev().val(start.toDateStr());
			                                            $(elm).prev().val(end.toDateStr());
			                                        });
			                                    });
			                                });*/
			                            </script>
			                            <input name="beginOnlineTime" type="hidden" value="<fmt:formatDate value="${beginOnlineTime}" pattern="yyyy-MM-dd" />">
			                            <input name="endOnlineTime" type="hidden" value="<fmt:formatDate value="${endOnlineTime}" pattern="yyyy-MM-dd" />">
			                            <button class="btn btn-default daterange" id="onlineTime" type="button" data-original-title="" title=""><span class="date-title"><fmt:formatDate value="${beginOnlineTime}" pattern="yyyy-MM-dd" /> 至 <fmt:formatDate value="${endOnlineTime}" pattern="yyyy-MM-dd" /></span> <i class="fa fa-calendar"></i></button>
		                        </div>
                    		</div>
	                        	
		                    <div class="form-group">
								<div class="col-sm-offset-10 col-sm-2 text-right">
									<button class="btn btn-info btn-sm" type="button" id="searchButton">
										<i class="icon-search bigger-110"></i>查询
									</button>
									&nbsp;
									<button class="btn btn-sm" type="reset">
										<i class="icon-undo bigger-110"></i>重置
									</button>
								</div>
							</div>
							<div class="well">
								<cqliving-security2:hasPermission name="/module/infoClassify/info_classify_add.html">
						        <input type="button" class="btn btn-sm" value="新增" onclick="javascript:location.href='/module/infoClassify/info_classify_add.html'"></input>
						        </cqliving-security2:hasPermission>
								<button class="btn btn-sm">批量发布</button>
								<button class="btn btn-sm">批量下线</button>
								<button class="btn btn-sm">清空排序</button>
								<button class="btn btn-sm">撤稿</button>
								<button class="btn btn-sm">加入专题</button>
							</div>
		                </form>
					</div>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="info_classify_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script type="text/javascript">
require(['cloud.table.curd'], function(tableCurd){
	tableCurd.initTableCrud();
});
</script>