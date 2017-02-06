<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="breadcrumbs" id="breadcrumbs">
	<script type="text/javascript">
		try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
	</script>

	<ul class="breadcrumb">
		<li>
			<i class="icon-home home-icon"></i>
			<a href="/module/security/index.html">首页</a>
		</li>
		<c:forEach var="_index" begin="1" end="5" step="1">
			<c:set var="tempBreadcrumbs" value="${'_breadcrumbs_'}${_index}"></c:set>
			<c:set var="str1" value="${fn:split(param[tempBreadcrumbs],'|')}"></c:set>
			<c:if test="${fn:length(str1) eq 1 && str1[0] != '' }">
				<li class="active">${str1[0] }</li>
			</c:if>
			<c:if test="${fn:length(str1) eq 2 }">
				<li><a href="${str1[1] }">${str1[0] }</a></li>
			</c:if>
		</c:forEach>
		<!-- <li>
			<i class="icon-home home-icon"></i>
			<a href="/module/security/index.html">首页</a>
		</li>
		<li class="active">用户管理</li> -->
	</ul><!-- .breadcrumb -->

	<%/*<div class="nav-search" id="nav-search">
		<form class="form-search">
			<span class="input-icon">
				<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
				<i class="icon-search nav-search-icon"></i>
			</span>
		</form>
	</div> */%>
</div>
