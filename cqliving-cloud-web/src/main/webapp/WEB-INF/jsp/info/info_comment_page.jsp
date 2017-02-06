<%@page contentType="text/html; charset=utf-8"%>
 <%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
 
<c:forEach items="${infocomments}" var="com">
   <li sortValue="${com.id }" replyUserId="${com.replyUserId}" id="${com.id }" appId="${com.appId }">
    <div class="comment_list_head">
      <div class="list_head">
        <c:choose>
           <c:when test="${com.type eq 1 }">
              <img src="/front/detail/images/default_head.png" />
           </c:when>
           <c:otherwise>
              <img src="${not empty com.imgUrl ? com.imgUrl : '/front/detail/images/default_head.png' }" />
           </c:otherwise>
        </c:choose>
      
      </div>
      <div class="list_info">
        <p class="info_name">
           <c:choose>
              <c:when test="${com.type eq 1 }">
                  ${com.anonymousName}
              </c:when>
              <c:otherwise>
                 ${com.nickname}
              </c:otherwise>
           </c:choose>
        
        </p>
        <p class="info_time"> <fmt:formatDate value="${com.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </p>
      </div>
      <div class="list_btn">
        <div class="list_btn_zan  list_btn_zan_r  btn_click"><span>${com.praise }</span><a></a></div>
      </div>
    </div>
    <div class="comment_list_content"> ${com.content } </div>
    
    <c:if test="${com.passiveRelpsyStatus eq 3 and not empty com.passiveReplyName}">
      <div class="comment_list_reply">回复<span class="comment_list_reply_user">${com.passiveReplyName }</span>的评论：${com.sourceTitle } </div>
    </c:if>
  </li>
</c:forEach>
 

  