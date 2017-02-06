<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="row">
	<div class="col-xs-12">
        <form class="form-horizontal form">
        	<div class='col-md-12'>
                   <div class="form-group">
                       <label class="col-sm-3 control-label no-padding-right">客户端</label>
                       <div class="col-sm-9">
                           <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${appName}">
                       </div>
                   </div>
                   <div class="form-group">
                       <label class="col-sm-3 control-label no-padding-right">便民类型</label>
                       <div class="col-sm-9">
                           <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${convenienceTypeName}">
                       </div>
                   </div>
                   <div class="form-group">
                       <label class="col-sm-3 control-label no-padding-right">便民名称</label>
                       <div class="col-sm-9">
                              <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.name}">
                       </div>
                   </div>
                   <div class="form-group">
                       <label class="col-sm-3 control-label no-padding-right">便民链接</label>
                       <div class="col-sm-9">
                              <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.linkUrl}">
                       </div>
                   </div>
                   <div class="form-group">
                       <label class="col-sm-3 control-label no-padding-right">排序号</label>
                       <div class="col-sm-9">
                           <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.sortNo}">
                       </div>
                   </div>
                   <div class="form-group">
                       <label class="col-sm-3 control-label no-padding-right">便民图标48*48px地址</label>
                       <div class="col-sm-9">
                              <ul class="ace-thumbnails">
                       			<a href="${item.iconMinUrl}" data-rel="colorbox">
                               		<img alt="" src="${item.iconMinUrl}" style="width:80px;height:80px;">
                               </a>
                              </ul>
                       </div>
                   </div>
                   <div class="form-group">
                       <label class="col-sm-3 control-label no-padding-right">便民图标72*72px地址</label>
                       <div class="col-sm-9">
                       	<ul class="ace-thumbnails">
                       		<a href="${item.iconMaxUrl}" data-rel="colorbox">
                               	<img alt="" src="${item.iconMaxUrl}" style="width:80px;height:80px;">
                               </a>
                              </ul>
                       </div>
                   </div>
			</div>
        </form>
	</div>
</div>
<script type="text/javascript">
require(['common_colorbox'], function(colorbox){
	colorbox.init();
});
</script>