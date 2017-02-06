<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>${session_app_info.name}管理系统</title>
		<meta name="description" content="overview &amp; stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/include.jsp"></jsp:include>
	</head>
	
	<c:forEach items="${pageContext.request.cookies}" var="it"> 
		<c:if test="${it.name eq 'ace-skin' }">
			<c:set var="ace_skin" value="${it.value }"/>
		</c:if>
	</c:forEach>
	<body <c:if test="${not empty ace_skin && ace_skin ne 'default' }"> class="${ace_skin }"</c:if>>
		<tiles:insertAttribute name="header" />
		<!-- Header开始 -->
		<%-- <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/header.jsp"></jsp:include> --%>
		<!-- Header结束 -->
		
		<div class="main-container" id="main-container">
			<script type="text/javascript">
			   /* require(['ace'],function(ace){
			    	ace.settings.check('main-container' , 'fixed');
			   }); */
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						//try{ace.settings.check('sidebar' , 'fixed')}catch(e){console.log(e);}
					</script>

					<tiles:insertAttribute name="sidebar" />
					<!-- 上面4个按纽 开始 -->
					<%-- <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/sidebar.jsp"></jsp:include> --%>
					<!-- 上面4个按纽 结束 -->

					<!-- 菜单导航开始 -->
					<tiles:insertAttribute name="menu" />
					<%-- <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/menu_nav.jsp"></jsp:include> --%>
					<!-- 菜单导航结束 -->

					<script type="text/javascript">
						//try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
				</div>

				<!-- 内容块 开始 -->
				<tiles:insertAttribute name="body" />
				<%-- <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/index_common.jsp"></jsp:include> --%>
				<!-- 内容块 结束 -->
				
				<!-- 设置 开始 -->
				<tiles:insertAttribute name="setting" />
				<%-- <jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/setting.jsp"></jsp:include> --%>
				<!-- 设置 结束 -->
			
			<!-- 底部右下角向上箭头 开始 -->
			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
			
			<script type="text/javascript">
			   	require(['ace'],function(ace){
			    	ace.settings.check('main-container' , 'fixed');
			    	ace.settings.check('sidebar' , 'fixed');
			    	ace.settings.check('sidebar' , 'collapsed');
			    });
			</script>
			<!-- 底部右下角向上箭头 开始 -->
		</div><!-- /.main-container -->
	</body>
</html>