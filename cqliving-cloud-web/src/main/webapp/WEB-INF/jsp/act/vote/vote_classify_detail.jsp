<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jsp/common/include.jsp" %>
<title>投票详情</title>
</head>
<body>

<c:if test="${not empty param.share }">
   <div class="share_top">
     <span openUrl="${appConfig.downLoadUrl }"> 通过${appInfo.name }客户端查看</span> 
     <a href="javascript:;" id="close_a"><img src="${request.contextPath}/front/detail/images/close.png"/></a>
   </div>
</c:if>

<div>
  <div class="vote_top">
    <div class="vote_pic"> 
       <c:choose>
	       <c:when test="${not empty param.noimg }">
	         <!--图片占位-->
		    <div class="detail_pic_none btn_click" imgSrc="${actInfo.actImageUrl}">
			    <span> ${appInfo.name } </span>
			    <span class="detail_pic_none_click">点击查看图片</span>
		    </div>
	       </c:when>
	       <c:otherwise>
	          <img src="${actInfo.actImageUrl}"/>
	       </c:otherwise>
	    </c:choose>
    </div>
    <div class="vote_total_bg"></div>
    <div class="vote_total">
      <p>共有<span class="joinNum">${joinTotal}</span>人参加</p>
      <p style="margin-top:5px;">
	      <fmt:formatDate value="${actVoteDto.startTime}" pattern="yyyy-MM-dd HH:mm"/> -
		  <fmt:formatDate value="${actVoteDto.endTime}" pattern="yyyy-MM-dd HH:mm"/>
      <a class="type_more" href="/act/act_content/${actInfo.id }.html">查看活动详情<img src="${request.contextPath}/front/detail/images/arrow_right.png"/></a></p>
    </div>
  </div>
  
  <div id="topic">
    <div class="topic_nav">
	  <ul>
	    <c:forEach items="${actVoteDto.actVoteClassifyDtos }" var="voteClassify">
	       <li><a voteClassifyId="${voteClassify.id }" href="javascript:;">${voteClassify.title }</a></li>
	    </c:forEach>
	  </ul>
	</div>
 </div>
   <!-- 投票分类详情 -->
</div>

<c:if test="${not empty param.share }">
	  <div class="detail_line"></div>
	  <div class="share_link btn_click">
	      <a href="${appConfig.downLoadUrl }"> ${appInfo.name }里有更多精彩，去看看<img src="${request.contextPath }/front/detail/images/link.png"/></a>
	  </div>
</c:if>

</body>
</html>
<script type="text/javascript">
  
  var canVote = "${canVote}";
  
  var sessionStr = "";
	function getToken(params){
		   
		   sessionStr = params;
	}
  
	//返回回调
	var backCallFn = new Object();
	function backCallFun(){
		backCallFn.fn();
	}
	
  require(['/resource/js/business/act/vote/vote_classify_detail.js'],function(){
	  
	  backCallFn.fn = function(){
		  $(".topic_nav ul li").eq(0).click();
	  };
	  
	  function setUlWidth(){
		  var $li = $(".topic_nav ul li");
		  var liNum = $li.length;
		  if(liNum<=0)return;
		  
		  $li = $li.first();
		  var w = $li.width()+50;
		  w = w * liNum;
		  
		  $(".topic_nav ul").css("width",w);
	  }
	  setUlWidth();
	  
	  $("span[openUrl]").bind("click",function(){
			var $this = $(this);
			var openUrl = $this.attr("openUrl");
			location.href = openUrl;
	  });
  });

</script>