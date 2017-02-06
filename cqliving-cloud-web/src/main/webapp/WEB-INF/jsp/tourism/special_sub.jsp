<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<c:forEach items="${sumTourism }" var="tour">
   <div class="travel_topic_list">
    <div class="travel_topic_left"> <img src="${tour.imageUrl}"/> </div>
    <div class="travel_topic_right">
      <div class="travel_topic_title">${tour.name }</div>
      <div class="travel_topic_ticket">
        <c:if test="${not empty  tour.price}">
                    门票：${tour.price}
        </c:if>
      </div>
     <%--  <div class="travel_topic_ticket">
                 所处位置：${tour.place }
      </div> --%>
      <div class="travel_topic_distance">距离：
        <fmt:formatNumber value="${tour.distance / 1000}" maxFractionDigits="2"/>
      Km </div>
    </div>
  </div>
</c:forEach>