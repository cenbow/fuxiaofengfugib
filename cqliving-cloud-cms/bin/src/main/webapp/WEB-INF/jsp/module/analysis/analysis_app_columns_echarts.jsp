<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
	  <jsp:param value="新闻栏目统计管理|/statistics/app_column/index.html" name="_breadcrumbs_1"/>
	  <jsp:param value="图表" name="_breadcrumbs_3"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12  version-view">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
                    <div class="form-group ${fn:length(apps) ge 2 ? '' : 'hidden'}" >
                        <label class="col-sm-2 control-label no-padding-right">所属客户端</label>
                        <div class="col-sm-8">
                            <select class="chosen-select" name="appId">
                               <c:forEach items="${apps}" var="app">
                                  <option value="${app.id }" ${param.appId eq app.id ? 'selected' : '' }>${app.name }</option>
                               </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">栏目</label>
                        <div class="col-sm-8">
                            <select class="chosen-select" name="colId" data-placeholder="请选择栏目">
                               <c:forEach items="${defaultAppColumn}" var="col">
                                  <option value="${col.id }" ${param.appColumnsId eq col.id ? 'selected' : '' }>${col.name }</option>
                               </c:forEach>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right"></label>
                        <div class="col-sm-8">
                             <strong>趋势图(按照天显示)</strong>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">用户类型</label>
                        <div class="col-sm-2">
                           <select class="select-param" name="userType">
                             <c:forEach items="${allUserType }" var="ut">
                                <option value="${ut.key}">${ut.value}</option>
                             </c:forEach>
                           </select>
                        </div>
                                              
                        <div class="col-sm-2">
                                                       日期:
                            <select class="select-param" name="dateStr">
                             <c:forEach items="${dayMap }" var="ut">
                                <option value="${ut.key}">${ut.value}</option>
                             </c:forEach>
                           </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-1 control-label no-padding-right"></label>
                        <div class="col-sm-11" id="echarts" style="width:800px;height:500px;">
                        </div>
                    </div>
                    
		            <div class="form-group">
		            	<div class="col-sm-10">
							<div class="pull-right">
								<button class="btn btn-sm btn-danger" type="button" backRestoreParam="backRestoreParam" back_url="/statistics/app_column/index.html">
									<i class="icon-undo bigger-110"></i>返回
								</button>
							</div>
						</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>
<script type="text/javascript">

var appColumnsId= "${param.appColumnsId}";

require(['cloud.table.curd','cqliving_ajax','echarts','chosen'], function(ctc,cqliving_ajax,echarts){
	 ctc.bindBackRestoreParamButton();
	 var myChart = echarts.init(document.getElementById('echarts'));

	 function getOption(params){
		 var url = "/statistics/app_column/common/echarts_option.html";
		 cqliving_ajax.ajaxOperate(url,'',params,function(data,status){
			 data = JSON.parse(data.data);
			 if(!data.series){
				 data.series = []
			 }
			 myChart.setOption(data);
		 });
	 }
	 
	 $(".select-param").on("change",function(){
		 var ci = $(":input[name=colId]").val();
		 var dateStr = $("select[name=dateStr]").val();
		 var userType = $("select[name=userType]").val();
		 var params = {
				 dateStr:dateStr,
				 userType:userType,
				 appColumnsId:ci
		 };
		 getOption(params);
	 });
	 
	 $(".chosen-select").chosen({search_contains:true});
	 
	 $("select[name=appId]").on("change",function(){
		 var appId = $(this).val();
		 getAppColum({appId:appId});
	 });
	 
	 $("select[name=colId]").on("change",function(){
		 $("select[name=dateStr]").change();
	 });
	 
	 function getAppColum(params){
		 var url = "/statistics/app_column/common/col_appid.html";
		 cqliving_ajax.ajaxOperate(url,'',params,function(data,status){
			 var cols = data.data;
			 var html = "";
			 if(cols){
				 for(var i=0,m=cols.length;i<m;i++){
					 var c = cols[i];
					 if(i == 0){
						 html += ("<option value='"+c.id+"' selected>"+c.name+"</option>");
					 }else{
						 html += ("<option value='"+c.id+"'>"+c.name+"</option>");
					 }
				 }
			 }
			 $("select[name=colId]").html(html);
			 $("select[name=colId]").trigger("chosen:updated");
		 });
	 }
	 
	 if(appColumnsId){
		 $("select[name=colId]").change();
	 }
	 
});
</script>