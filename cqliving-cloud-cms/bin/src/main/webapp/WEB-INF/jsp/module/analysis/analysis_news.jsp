<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
	  <jsp:param value="新闻列表|/module/infoClassify/info_classify_list.html" name="_breadcrumbs_1"/>
	  <jsp:param value="新闻趋势统计" name="_breadcrumbs_3"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12  version-view">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
		            <input type="hidden" value="${infoDto.infoClassifyId}" class="col-xs-10 form-control col-sm-5" name="infoClassifyId">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">标题</label>
                        <div class="col-sm-8">
                            <input type="text" value="${infoDto.title}" class="col-xs-10 form-control col-sm-5" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">来源</label>
                        <div class="col-sm-8">
                            <input type="text" value="${infoDto.infoSource}" class="col-xs-10 form-control col-sm-5" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right">浏览量</label>
                        <div class="col-sm-8">
                            <input type="text" value="${infoDto.viewCount}" class="col-xs-10 form-control col-sm-5" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right"></label>
                        <div class="col-sm-8">
                             <strong>趋势图(按照小时显示)</strong>
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
								<button class="btn btn-sm btn-danger" type="button" backRestoreParam="backRestoreParam" back_url="/module/infoClassify/info_classify_list.html">
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
require(['cloud.table.curd','cqliving_ajax','echarts'], function(ctc,cqliving_ajax,echarts){
	ctc.bindBackRestoreParamButton();
	 var myChart = echarts.init(document.getElementById('echarts'));

	 function getOption(params){
		 var url = "/module/analysis/news/common/echarts_option.html";
		 cqliving_ajax.ajaxOperate(url,'',params,function(data,status){
			 data = JSON.parse(data.data);
			 if(!data.series){
				 data.series = []
			 }
			 myChart.setOption(data);
		 });
	 }
	 
	 $(".select-param").on("change",function(){
		 
		 var ci = $(":input[name=infoClassifyId]").val();
		 var dateStr = $("select[name=dateStr]").val();
		 var userType = $("select[name=userType]").val();
		 var params = {
				 dateStr:dateStr,
				 userType:userType,
				 search_EQ_infoClassifyId:ci
		 };
		 getOption(params);
		 
	 });
	 
	 $("select[name=dateStr]").change();
});
</script>