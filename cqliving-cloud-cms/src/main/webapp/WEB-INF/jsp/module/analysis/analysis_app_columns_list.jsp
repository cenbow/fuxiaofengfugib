<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="资讯栏目统计表列表" name="_breadcrumbs_1"/>
	</jsp:include>

	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<!-- PAGE CONTENT BEGINS -->
				<div class="row">
					<!-- PAGE CONTENT BEGINS -->
					<form class="form-horizontal" role="form" action="/statistics/app_column/index.html" id="analysis_app_columns_FormId">
	                    <div class="special-list">
                        	<div class="col-xs-6 col-sm-3 ${fn:length(apps) ge 2 ? '' : 'hidden' }">
								<div class="form-group">
									<div class="col-xs-12">
									    <select name="search_EQ_appId" id="search_EQ_appId" class="chosen-select">
									        <c:forEach items="${apps }" var="app">
									           <option value="${app.id }"  ${param.search_EQ_appId eq app.id ? 'selected' : '' }>${app.name }</option>
									        </c:forEach>
									    </select>
			                            <%-- <input type="text" id="search_LIKE_appId" name="search_LIKE_appId" value="${param.search_LIKE_appId }" placeholder="app_info表ID" class="col-xs-12 form-control" /> --%>
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
									    <select name="search_EQ_appColumnsId" id="search_EQ_appColumnsId" class="chosen-select" data-placeholder="请选择栏目">
										    <option value="">全部栏目</option>
									        <c:forEach items="${defaultAppColumn }" var="appcol">
									           <option value="${appcol.id }" ${param.search_EQ_appColumnsId eq appcol.id ? 'selected' : '' }>${appcol.name }</option>
									        </c:forEach>
									    </select>
			                           <%--  <input type="text" id="search_LIKE_appColumnsId" name="search_LIKE_appColumnsId" value="${param.search_LIKE_appColumnsId }" placeholder="资讯栏目表ID，表app_columns的主键" class="col-xs-12 form-control" /> --%>
	                        		</div>
                				</div>
							</div>
                        	<div class="col-xs-6 col-sm-3">
								<div class="form-group">
									<div class="col-xs-12">
		                            	<select name="search_EQ_userType" id="search_EQ_userType" class="form-control">
			                                <option value="">所有</option>
			                                <option value="0"<c:if test="${param.search_EQ_userType eq '0'}"> selected="selected"</c:if>>微信</option>
			                                <option value="1"<c:if test="${param.search_EQ_userType eq '1'}"> selected="selected"</c:if>>微博</option>
			                                <option value="2"<c:if test="${param.search_EQ_userType eq '2'}"> selected="selected"</c:if>>QQ</option>
			                                <option value="3"<c:if test="${param.search_EQ_userType eq '3'}"> selected="selected"</c:if>>注册用户</option>
			                                <option value="4"<c:if test="${param.search_EQ_userType eq '4'}"> selected="selected"</c:if>>其它</option>
			                                <option value="9"<c:if test="${param.search_EQ_userType eq '9'}"> selected="selected"</c:if>>全部用户</option>
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
	                </form>
				</div><!-- /.row -->
				<div class="row" id="table_content_page">
					<jsp:include page="analysis_app_columns_list_page.jsp"></jsp:include>
				</div>
			</div><!-- /.col -->
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>


<script type="text/javascript">

var columnsId = "${param.search_EQ_appColumnsId}";

require(['cloud.table.curd','cloud.time.input','cqliving_ajax','cqliving_dialog','chosen'], function(tableCurd,timeInput,cqliving_ajax,cqliving_dialog){
	tableCurd.initTableCrud();
	timeInput.initTimeInput();
	$(".chosen-select").chosen({search_contains:true});
	
	$("select[name=search_EQ_appId]").off("change").on("change",function(){
		 var appId = $(this).val();
		 getAppColum({appId:appId});
	});
	
	$("select[name=search_EQ_appId]").change();
	
	function getAppColum(params){
		 var url = "/statistics/app_column/common/col_appid.html";
		 cqliving_ajax.ajaxOperate(url,'',params,function(data,status){
			 var cols = data.data;
			 var html = "<option value=''>全部栏目</option>";
			 if(cols){
				 for(var i=0,m=cols.length;i<m;i++){
					 var c = cols[i];
					 if(c.id == columnsId){
						 html += ("<option value='"+c.id+"' selected>"+c.name+"</option>");
					 }else{
						 html += ("<option value='"+c.id+"'>"+c.name+"</option>");
					 }
				 }
			 }
			 $("select[name=search_EQ_appColumnsId]").html(html);
			 $("select[name=search_EQ_appColumnsId]").trigger("chosen:updated");
		 });
	 }
	
	//重置按钮重新绑定
	$("#resetButton").off("click").on("click",function(){
		var paramForm = $(this).closest("form");
		paramForm.find("select[name=search_EQ_appColumnsId],select[name=search_EQ_userType]").not(':button, :submit, :reset')  
		 .val('')  
		 .removeAttr('checked')  
		 .removeAttr('selected');
		 $("select[name=search_EQ_appColumnsId]").trigger("chosen:updated");
	});
});
</script>