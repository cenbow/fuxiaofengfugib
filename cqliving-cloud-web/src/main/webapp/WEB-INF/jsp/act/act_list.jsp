<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jsp/common/include.jsp"%>
<title>活动列表</title>
</head>
<body>
	<div id="header_dj">
		<h3>活动</h3>
	</div>
	<div class="wrap_phone">
		<div id="nav" class="active_nav">
			<ul>
				<li class="nav_order"><a><span>全部活动</span><i class="fa fa-sort-desc"></i></a>
					<div class="nav_list">
						<div class="nav_list_bg"></div>
						<ul>
							<li class="active">全部活动</li>
							<li data-val="1">未过期</li>
							<li data-val="0">已过期</li>
						</ul>
					</div>
				</li>
				<li class="nav_area"><span>全部类型</span><i class="fa fa-sort-desc"></i></a>
					<div class="nav_list">
						<div class="nav_list_bg"></div>
						<ul>
							<li class="active">全部类型</li>
							<c:forEach var="it" items="${allTypes }">
								<c:if test="${it.key ne 2 }">
									<li data-val="${it.key }">${it.value }</li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</li>
			</ul>
		</div>
		<div class="bu_list act_list"></div>
		<div id="noDataDiv">
			<img src="${request.contextPath }/front/detail/images/pic.png" class="status_pic"/>
			<div class="err_infos">活动正在策划中，敬请期待</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var basicObj = {appId : '${param.appId}', sessionId : '${param.sessionId}', token : '${param.token}'};
	var basicParamsStr = "";
	function setSessionToken(params){
		basicParamsStr = params;
	}
	require([ '/resource/js/business/act/act_list.js' ], function(obj) {
		obj.init();
	});
</script>
</html>
