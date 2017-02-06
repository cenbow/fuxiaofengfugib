<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:forEach items="${infoThemes }" var="infoTheme">
  <div class="col-xs-10 widget-container-span padding150">
	<div style="opacity: 1;" class="widget-box collapsed form-group col-sm-7">
		<div class="widget-header" style="background:${infoTheme.color};">
		    <input type="hidden" name="id" value="${infoTheme.id }">
			<input type="text" name="name" value="${infoTheme.name }" placeholder="请输入分类名称">
			<div class="widget-toolbar  widget-toolbar-light">
				<a class="btn-sm infoTheme_move_up" href="javascript:;">上移</a> 
				<a class="btn-sm infoTheme_move_down" href="javascript:;">下移</a> 
				<a href="javascript:;" class="theme_remove"><i class="icon-remove"></i>删除</a>
				<div class="input-group colorpicker-component cp2"> 
				    <input type="text" value="#00AABB" name="color" class="form-control" readonly/> 
				   <span class="input-group-addon"><i></i></span> 
				</div>
			</div>
		</div>
	</div>
  </div>
</c:forEach>