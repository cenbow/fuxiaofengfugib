<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div>
	<div class="row">
		<div class="col-xs-12  version-view">
			<%-- 详细 --%>
	        <form class="form-horizontal form">
                   <c:if test="${not empty appList}">
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">所属APP</label>
                        <div class="col-sm-10">
							<c:forEach items="${appList}" var="app">
                                 <c:if test="${item.appId eq app.id}">
					    			<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${app.name}"/>
                                 </c:if>
                            </c:forEach>
                        </div>
                    </div>
                   </c:if>
                   <div class="form-group col-xs-12">
                       <label class="col-sm-2 control-label no-padding-right">标题</label>
                       <div class="col-sm-10">
                           <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.title}"/>
                       </div>
                   </div>
                   <div class="form-group col-xs-12">
                       <label class="col-sm-2 control-label no-padding-right">广告图</label>
                       <div class="col-sm-10">
                       	<ul class="ace-thumbnails">
                       		<a href="${item.loadingUrl}" data-rel="colorbox">
						 		<img alt="150x150" style="width:150px;height:150px;" src="${item.loadingUrl}">
						 	</a>
					 	</ul>
                       </div>
                   </div>
				<c:if test="${not empty item.linkUrl}">
                    <div class="form-group col-xs-12">
                        <label class="col-sm-2 control-label no-padding-right">广告链接地址</label>
                        <div class="col-sm-10">
                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.linkUrl}"/>
                    	</div>
                    </div>
				</c:if>
                   <div class="form-group col-xs-12">
                       <label class="col-sm-2 control-label no-padding-right">客户端类型</label>
                       <div class="col-sm-10">
                       	<c:forEach items="${allTypes}" var="type" varStatus="idx">
                       		<c:if test="${item.type eq type.key}">
	                            <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${type.value}"/>
                       		</c:if>
                        </c:forEach>
                       </div>
                   </div>
	        </form>
		</div>
	</div>
</div>
<script type="text/javascript">
require(['common_colorbox'], function(colorbox){
	colorbox.init();
});
</script>