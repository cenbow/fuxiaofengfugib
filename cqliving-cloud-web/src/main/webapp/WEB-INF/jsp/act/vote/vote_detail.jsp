<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jsp/common/include.jsp" %>
<title>投票详情</title>
</head>
<body id="active">
  <c:if test="${not empty param.share }">
   <div class="share_top">
     <span openUrl="${appConfig.downLoadUrl }"> 通过${appInfo.name }客户端查看</span> <a href="javascript:;" id="close_a"><img src="${request.contextPath}/front/detail/images/close.png"/></a>
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
      <label><fmt:formatDate value="${actVoteDto.startTime}" pattern="yyyy/MM/dd HH:mm"/></label> -
      <label><fmt:formatDate value="${actVoteDto.endTime}" pattern="yyyy/MM/dd HH:mm"/></label>
      <a href="/act/act_content/${actVoteDto.actInfoId}.html" class="type_more">查看活动详情<img src="${request.contextPath}/front/detail/images/arrow_right.png"></a></p>
    </div>
  </div>

  <%-- <div class="active_banner">
    <div class="active_title">${actVoteDto.actVoteClassifyDtos[0].title }</div>
    <div class="active_memo">${actVoteDto.actVoteClassifyDtos[0].subject}</div>
  </div> --%>
      <!--投票多选-->
	  <div class="active_time"> 活动时间：
          <fmt:formatDate value="${actVoteDto.startTime}" pattern="yyyy/MM/dd HH:mm"/> -
		<fmt:formatDate value="${actVoteDto.endTime}" pattern="yyyy/MM/dd HH:mm"/>
      </div>
	  <div class="active_common">
	    <div class="active_common_name"> 
	      <span class="name_title"><span>投票</span></span>
	      <span class="name_memo">
	         <jsp:include page="${request.contextPath}/act/surpluse_vote.html?voteClassifyId=${actVoteDto.actVoteClassifyDtos[0].id}&psessionId=${param.sessionId}&ptoken=${param.token}"></jsp:include>
	      </span>
		    <a class="name_right" href="javascript:;">
	            <img src="${request.contextPath}/front/detail/images/share.png"/>分享
		      <%-- <c:if test="${actVoteDto.isShare eq 1}">
			           分享可增加投票<span class="name_color">${actVoteDto.shareAddTimes }</span>次
		      </c:if> --%>
	        </a> 
	     </div>
	    <!-- 投票详情 -->
	    <div id="vote_detail">
	       <jsp:include page="${request.contextPath}/act/vote_classify/${actVoteDto.actVoteClassifyDtos[0].id}.html"></jsp:include>
	    </div>
	  </div>
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
	
	var fun = new Object();
	function loginCallBack(){
		fun.loginCallBack();
	}
	
	//返回回调
	var backFn = new Object();
	function backCallFun(){
		backFn.fn();
	}
	
	require(['jquery','cqliving_ajax','/resource/js/business/act/vote/vote_detail.js'],function($,cqliving_ajax,vote_detail){
		
		fun.loginCallBack = function(){
			vote_detail.surplusVoteNum();
		};
		
		vote_detail.surplusVoteNum();
		
		backFn.fn = function(){
			
			var loadurl = "/act/vote_classify/${actVoteDto.actVoteClassifyDtos[0].id}.html";
			cqliving_ajax.load($("#vote_detail"),loadurl);
			vote_detail.surplusVoteNum();
		};
		
		$(".name_right").bind("click",function(){
			window.AppJsObj.share();
		});
		
		$("span[openUrl]").bind("click",function(){
			var $this = $(this);
			var openUrl = $this.attr("openUrl");
			location.href = openUrl;
	    });
		
	});
	
</script>