<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jsp/common/include.jsp" %>
<title>投票项详情</title>
</head>
<body>

<input type="hidden" name="appId" value="${param.appId }"/>
<input type="hidden" name="actVoteClassifyId" value="${actVoteItem.actVoteClassifyId }"/>
<input type="hidden" name="actVoteItemId" value="${actVoteItem.id}"/>
<input type="hidden" name="actInfoId" value="${param.actInfoId}"/>
<input type="hidden" name="actInfoListId" value="${param.actInfoListId}"/>
<input type="hidden" name="voteId" value="${param.voteId}"/>
<div id="vote_detail">
  <div class="detail_title"> ${actVoteItem.itemTitle } </div>
  
  <%-- <div class="detail_bottom">
	  <div class="detail_vote">
	    <button id="detail_btn">点击投票</button>
	    <p>已投<span>${actVoteItem.actValue + actVoteItem.initValue }</span>票</p>
	  </div>
 </div> --%>
  
  <c:if test="${not empty actVoteItem.videoUrl}">
     <div style="font-size:1.6em; padding-bottom:.5em;" class="detail_video">
      <video width="100%" controls="controls" src="${actVoteItem.videoUrl}" class="videoControl" style="opacity: 1;">您不支持视频播放 </video>
     </div>
  </c:if>
  
  <c:if test="${not empty  actVoteItem.imageUrl}">
	  <div id="detail_pic"> 
	    <c:choose>
	       <c:when test="${not empty param.noimg }">
	         <!--图片占位-->
		    <div class="detail_pic_none btn_click" imgSrc="${actVoteItem.imageUrl}">
			    <span> ${appInfo.name } </span>
			    <span class="detail_pic_none_click">点击查看图片</span>
		    </div>
	       </c:when>
	       <c:otherwise>
	          <img src="${actVoteItem.imageUrl }"/> 
	       </c:otherwise>
	    </c:choose>
	  </div>
  </c:if>
  <div id="detail_info">
    <imgrp:img appName="${appInfo.name }" replace="${not empty param.noimg ? 1 : 0}" content="${actVoteItem.content}"/>
  </div>
</div>
<div class="detail_bottom">
  <div class="detail_vote">
    <button id="detail_btn">点击投票</button>
    <p>已投<span>${actVoteItem.actValue + actVoteItem.initValue }</span>票</p>
  </div>
</div>
<script type="text/javascript" src="/resource/js/common/zwyCloudApi.js"></script>
</body>
   
   <script type="text/javascript">
   
       var canVote = "${canVote}";
      
		 function getToken(params){
			   
			   sessionStr = params;
			  
		   }
   
	   require(['jquery','cqliving_ajax','cqalert'],function($,cqliving_ajax,cqalert){
		   $(".detail_pic_none").on("click",function(){
				var $this = $(this);
				var imgSrc = $this.attr("imgSrc");
				$this.after("<img src='"+imgSrc+"'/>");
				$this.remove();
			});
		   
		   $(".detail_vote button").bind("click",function(){
				
			   if(canVote){
				   ZWY_ClOUD.alert(canVote);
				   return;
			   }
				saveUserVote();
				
			});
		   
		   function saveUserVote(){
				
				var params = {};
				
				try{
					ZWY_ClOUD.getSessionToken("getToken");
				}catch(e){params.sessionCode = "cqliving_custom_session_code";}
				
				setTimeout(function(){
					
					sessionStr = eval("("+sessionStr+")");
					params.sessionCode = sessionStr.sessionId;
					params.token = sessionStr.token;
					var arrProp = "itemIds[]",itemIds = [],$actVoteItemId = $(":input[name=actVoteItemId]");
					itemIds.push($actVoteItemId.val());
					params[arrProp] = itemIds;
					var $input = $actVoteItemId.siblings(":input[type=hidden]");
				    
					$input.each(function(i,n){
						var $n = $(n);
						var name = $n.attr("name");
						var value = $n.val();
						params[name] = value;
					});
					
					var url = "/act/user/vote.html";
					cqliving_ajax.ajaxOperate(url,"body",params,function(data,status){
						
						if(data.code>=0){
							addItemNum(1);
						}else if(data.code == -11111){
							//跳转到登录页
							window.AppJsObj.redirectLogin();
						}else{
							ZWY_ClOUD.alert(data.message);
						}
						
					});
					
				},500);
				
			}
		   
		   function addItemNum(num){
			   
			   if(!num)num = 0;
			   var $numSpan = $(".detail_bottom .detail_vote p span");
			   
			   if($numSpan.length>=1){
				   $numSpan.each(function(i,n){
					   
					   var $this = $(n);
					   var oldnum = parseInt($this.text(),10);
					   if(!oldnum)oldnum = 0;
					   oldnum += num;
					   $this.text(oldnum);
					   
				   });
			   }
		   }
		   
		 //清楚p、h的宽度
		 clearWidth();
		   
	   });
	</script>
</html>


