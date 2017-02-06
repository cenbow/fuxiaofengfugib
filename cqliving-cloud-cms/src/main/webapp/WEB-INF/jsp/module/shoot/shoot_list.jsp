<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="摄影表列表" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="shoot_list.html" id="shoot_FormId">
	                    <div class="special-list">
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_title" name="search_LIKE_title" value="${param.search_LIKE_title }" placeholder="作品标题" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_type" id="search_EQ_type" class="form-control">
			                                <option value="">所有</option>
			                                <option value="1"<c:if test="${param.search_EQ_type eq '1'}"> selected="selected"</c:if>>小学1-3年级组</option>
			                                <option value="2"<c:if test="${param.search_EQ_type eq '2'}"> selected="selected"</c:if>>小学4-6年级组</option>
			                                <option value="3"<c:if test="${param.search_EQ_type eq '3'}"> selected="selected"</c:if>>初中组</option>
			                                <option value="4"<c:if test="${param.search_EQ_type eq '4'}"> selected="selected"</c:if>>高中组</option>
			                            </select>
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
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
									<div class="col-xs-12">
			                            <input type="text" id="search_LIKE_phone" name="search_LIKE_phone" value="${param.search_LIKE_phone }" placeholder="手机号" class="col-xs-12 form-control" />
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
			                            <div class="input-group">
				                            <input name="search_GTE_uploadTime" type="hidden" value="<fmt:formatDate value="${search_GTE_uploadTime}" pattern="yyyy-MM-dd HH:mm" />">
				                            <input name="search_LT_uploadTime" type="hidden" value="<fmt:formatDate value="${search_LT_uploadTime}" pattern="yyyy-MM-dd HH:mm" />">
				                            <input type="text" id="uploadTime" time_options='{"format":"YYYY-MM-DD HH:mm"}' readonly="true" value="<fmt:formatDate value="${search_GTE_uploadTime}" pattern="yyyy-MM-dd HH:mm" /><c:if test="${not empty search_GTE_uploadTime}"> 至 </c:if><fmt:formatDate value="${search_LT_uploadTime}" pattern="yyyy-MM-dd HH:mm" />" placeholder="上传时间"  class="form-control">
				                            <span class="input-group-addon"><i class="icon-calendar"></i></span>
				                        </div>
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
											<i class="icon-undo bigger-110"></i>清空
										</button>
									</div>
								</div>
							</div>
                    	</div>
						<div class="col-xs-12">							
						<div class="well">
						<cqliving-security2:hasPermission name="/module/shoot/shoot_add.html">
							<button class="btn btn-sm btn-success" type="button" onclick="javascript:location.href='/module/shoot/shoot_add.html'"><i class="icon-plus"></i>新增</button>
						</cqliving-security2:hasPermission>
						<cqliving-security2:hasPermission name="/module/shoot/shoot_delete_batch.html">
							<button class="btn btn-sm btn-danger" type="button" id="deleteBatchButton" url="/module/shoot/shoot_delete_batch.html"><i class="icon-trash"></i>批量删除</button>
						</cqliving-security2:hasPermission>
						<button class="btn btn-sm btn-primary" type="button"><i class="icon-mail-forward"></i>批量发布</button>
						<button class="btn btn-sm btn-warning" type="button"><i class="icon-download-alt"></i>导出</button>
						<button class="btn btn-sm btn-primary" type="button"><i class="icon-tag"></i>加入专题</button>
						<button class="btn btn-sm btn-danger" type="button"><i class="icon-reply"></i>撤稿</button>
					</div>
					</div>
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="shoot_list_page.jsp"></jsp:include>
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