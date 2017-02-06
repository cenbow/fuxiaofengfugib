<%@page contentType="text/html; charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<script type="text/javascript">
/* $(function() {
	$(".submenu a").on("click",function(){
		$(".submenu li").removeClass("active");
		$(this).parent().addClass("active");
		$(this).parent().parent().parent().addClass("active").siblings().removeClass("active");
		var url = $(this).attr("url");
		$(".page-content").load(url, {limit: 25}, function(){
		
		});
	});
}); */
</script>

<ul class="nav nav-list">
	<c:forEach items="${authorisedMenus}" var="menu">
		<li class="${navPid eq menu.id ? 'open' : '' }">
			<a href="#" class="dropdown-toggle">
				<i class="${not empty menu.iconCls ? menu.iconCls :  'icon-list'}"></i>
				<span class="menu-text">${menu.title}</span>
	
				<b class="arrow icon-angle-down"></b>
			</a>
	
			<!-- <ul class="submenu" style="display: block;"> -->
			<ul class="submenu" style="${navPid eq menu.id ? 'display: block;' : '' }">
				<c:forEach items="${menu.children}" var="resc">
					<li class="${navLm eq resc.titleFirstSpell ? 'active' : '' }">
						<a href="${pageContext.request.contextPath }${resc.url}?navLm=${resc.titleFirstSpell}&navPid=${resc.pid}" url="${pageContext.request.contextPath }${resc.url}?lm=${resc.titleFirstSpell}">
							<i class="icon-double-angle-right"></i>
							${resc.title}
						</a>
					</li>
	            </c:forEach>
			</ul>
		</li>
	</c:forEach>
</ul><!-- /.nav-list -->


<div class="sidebar-collapse" id="sidebar-collapse">
	<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
</div>
