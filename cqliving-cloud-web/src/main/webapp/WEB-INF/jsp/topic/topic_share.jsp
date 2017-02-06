<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jsp/common/include.jsp" %>
<title>话题详情</title>
</head>
<body>
<div class="share_top">
通过${appInfo.name}客户端查看<a href="${appConfig.downLoadUrl}" target="_blank"><img src="/front/detail/images/close.png"/></a>
</div>
<div id="detail" class="detail_share topic_share">
  <div class="detail_title">#${topicInfo.name }#</div>

  <div class="detail_content"> 
     <c:forEach items="${topicImages }" var="ti">
        <img src="${ti.url }"/>
     </c:forEach>
    <p>${topicInfo.content }</p>
    <div class="topic_type">
    <ul>
       <c:forEach items="${topicTypes }" var="tp">
         <li>${tp.name }</li>
       </c:forEach>
    </ul>
    </div>
     </div>
  <div class="detail_line"></div>
  
  <div class="detail_comment_list">
    <div class="comment_list_title"><span>评论(${topicInfo.replyCount })</span></div>
    <ul>
      <!-- 评论数据 -->
      <jsp:include page="${request.contextPath}/topic_comment/${topicInfo.id}.html" />
    </ul>
    <div class="comment_list_more btn_click" downUrl="${appConfig.downLoadUrl}">打开${appInfo.name}查看全部评论</div>
  </div>
</div>
</body>
</html>
<script type="text/javascript">

   require(['jquery'],function(){
	   
	   $(".share_top a").click(function(){
			$(".share_top").hide();
			$("#detail").removeClass("detail_share");
	  });
	   
	   $(".btn_click[downUrl]").bind("click",function(){
		   window.open($(this).attr("downUrl"));
	   });
	   
   });
</script>
