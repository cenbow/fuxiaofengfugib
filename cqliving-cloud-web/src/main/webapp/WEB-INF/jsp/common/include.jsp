<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!-- basic styles -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0,height=device-height">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">  
<link href="${request.contextPath}/front/detail/css/main.css?v=v10" rel="stylesheet"/>
<link href="${request.contextPath}/front/detail/css/main_${empty appId ? 25 : appId}.css?v=v2" rel="stylesheet"/>
<!-- 字体文件，难得再创建新的，所以直接多引用一次 -->
<link href="${request.contextPath}/front/detail/css/main_25.css" rel="stylesheet"/>
<link href="${request.contextPath}/front/detail/css/font-awesome.min.css" rel="stylesheet"/>
<script src="${request.contextPath}/resource/js/require.js"></script>
<script src="${request.contextPath}/resource/js/config.js"></script>
<script type="text/javascript" src="/resource/js/common/zwyCloudApi.js?v=v3"></script>

<script type="text/javascript">

  require(['jquery'],function(){
	  $(function(){
		  limitImg();
	  });
  });
</script>