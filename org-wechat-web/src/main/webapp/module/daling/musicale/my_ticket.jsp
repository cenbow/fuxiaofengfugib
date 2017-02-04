<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <%@include file="/common/head_meta.jsp"%>
    <title>大宁音乐季</title>
<body>
    <div id="audio_btn" class="video_exist rotate" style="display:none;">
         <!-- <audio loop="" src="images/bgm.mp3" id="media" autoplay="autoplay" preload=""></audio>  -->
    </div>
    <div class="rob-index">
        <div class="tb-mid">
            <div class="tit"><img src="/module/daling/front/images/rob-tit02.png"></div>
            <ul class="list01 my">
               <c:forEach items="${userTickets }" var="ut">
                  <li class="${ut.takeStatus eq 2 ? 'no' : '' }" 
                    <c:if test="${ut.takeStatus ne 2 }">
                      onclick="javascript:location.href='/musicale/${accId}/ticket_info.html?verifyCode=${ut.verifyCode}&musicaleId=${ut.musicaleId}';"
                    </c:if>
                  >    
                    <div class="img"><img src="${ut.imageUrl }"></div>
                    <div class="info">
                        <div class="name">${ut.ticketName }</div>
                        <div class="some"><span>音乐会门票</span></div>
                        <div class="time"><strong>演出时间：</strong><span>${ut.duration}</span></div>
                        <div class="icon"><i></i><i></i><i></i><i></i><i></i><i></i></div>
                    </div>
                  </li>
               </c:forEach>
            </ul>
        </div>
    </div>
</body>
<%@include file="/module/daling/common/include.jsp"%>
</html>