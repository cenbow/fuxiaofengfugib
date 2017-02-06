<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jsp/common/include.jsp"%>
<title>区情介绍</title>
</head>
<body>
<div id="detail">
  <div class="detail_title">${confitText.title }</div>
  <div class="detail_info">发布时间： <fmt:formatDate value="${confitText.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </div>
  <!--内容-->
  <div class="detail_content"> 
    ${confitText.content }
  </div>
</div>
</body>
</html>
