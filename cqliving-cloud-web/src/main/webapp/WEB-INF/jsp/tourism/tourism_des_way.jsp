<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@include file="/WEB-INF/jsp/common/include.jsp" %>
<div class="detail_line"></div>
  <div class="travel_nav">
    <ul class="tabs">
      <li><a href="#tab1">景点介绍</a></li>
      <li><a href="#tab2">景点线路</a></li>
    </ul>
  </div>
  <div class="travel_contenter">
    <div id="tab1"  class="tab_content">
      <div class="travel_list"> 景区价格：<span>${tourismInfo.price}</span> </div>
      <div class="travel_list"> 开放时间：<span>${tourismInfo.timeOpen}</span> </div>
      <div class="travel_list"> 气候类型：<span>${tourismInfo.climateType}</span> </div>
      <div class="travel_list"> 联系电话：
      <span>${tourismInfo.telephone}</span>
      <c:choose>
        <c:when test="${not empty param.share }">
           <a href="${appConfig.downLoadUrl }"><img src="/front/detail/images/tel.png"/> </a>
        </c:when>
        <c:otherwise>
           <a href="tel:${tourismInfo.telephone}"><img src="/front/detail/images/tel.png"/> </a>
        </c:otherwise>
      </c:choose>
      </div>
      <div class="travel_list"> 地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;点：
         <span>${tourismInfo.address}</span> 
      </div>
      <div class="trave_info">
        <p><imgrp:img appName="${appInfo.name }" replace="${not empty param.noimg ? 1 : 0}" content="${tourismInfo.content}"/></p>
      </div>
    </div>
     <div id="tab2"  class="tab_content">
      <div class="trave_info">
      <p>${tourismInfo.scenicRoute}</p>
      </div>
     </div>
  </div>
  
  
  <script type="text/javascript">
  
  var docH = "500";
  
  require(['swiper'],function(){
		//选项卡切换
		$(".tab_content").hide(); 
	    $("ul.tabs li:first").addClass("active").show(); 
	    $(".tab_content:first").show();      
	    //On Click Event  
	    $("ul.tabs li").click(function() {  
	        $("ul.tabs li").removeClass("active"); //Remove any "active" class  
	        $(this).addClass("active"); //Add "active" class to selected tab  
	        $(".tab_content").hide(); //Hide all tab content  
	        var activeTab = $(this).find("a").attr("href"); //Find the rel attribute value to identify the active tab + content  
	        $(activeTab).fadeIn(); //Fade in the active content  
	        return false;  
	    });  
	    
	  //清楚p、h的宽度
	   clearWidth();
	   
	   docH = $(".travel_contenter").height()+$(".travel_nav").height()+$(".detail_line").height();
});
  
  function getDocHeight(){
	  return docH;
  }
  
  </script>