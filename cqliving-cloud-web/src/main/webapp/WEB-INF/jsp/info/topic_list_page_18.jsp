<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<c:forEach items="${subinfo }" var="sub">
  <c:choose>
    <c:when test="${sub.listViewType eq 0}">
       <!-- 纯文本 -->
	  <div class="topic_list topic_list_1" idValue="${sub.id}" sortNoValue="${sub.correSortNo}" onlineTimeValue="<fmt:formatDate value='${sub.onlineTime}' pattern='yyyy-MM-dd HH:mm:ss'/>">
	    <div class="topic_list_content">
	      <div class="content_title">
             <a href='javascript:window.AppJsObj.redirectUrl(JSON.stringify({id:"${sub.id}",sourceId:"${sub.informationId}",detailViewType:"${sub.contextType eq 1 ? 1 : 2}",sourceType:"1",commentType:"${sub.commentType}",url:"${myfn:getInformationUrl(sub.contextType,sub.type,sub.contentUrl,sub.id)}",shareUrl:"${myfn:getInformationUrl(sub.contextType,sub.type,sub.contentUrl,sub.id)}",shareTitle:"${myfn:base64(sub.shareTitle)}",title:"${myfn:base64(sub.title)}",synopsis:"${myfn:base64(sub.synopsis)}",shareImgUrl:""}));'>${not empty sub.listTitle ? sub.listTitle : sub.title}</a>
          </div>
	      <div class="content_info">
	       <c:forEach items="${fn:split(sub.infoLabel,',')}" var="infoLabel" varStatus="vs">
	         <c:if test="${vs.count eq 1 and not empty infoLabel}">
	          <span class="topic_label">${infoLabel }</span>
	         </c:if>
	       </c:forEach>   
	       ${myfn:dateformate(sub.onlineTime.time,'MM-dd HH:mm')}
	      <span class="topic_list_time reply_count">${sub.totalReplyCount }评论</span></div>
	    </div>
	  </div>
    </c:when>
    <c:when test="${sub.listViewType eq 1 }">
       <!-- 单图 -->
	 <div class="topic_list topic_list_2" idValue="${sub.id}" sortNoValue="${sub.correSortNo}" onlineTimeValue="<fmt:formatDate value='${sub.onlineTime}' pattern='yyyy-MM-dd HH:mm:ss'/>">
	    <div class="topic_list_pic" onclick='javascript:window.AppJsObj.redirectUrl(JSON.stringify({id:"${sub.id}",sourceId:"${sub.informationId}",detailViewType:"${sub.contextType eq 1 ? 1 : 2}",sourceType:"1",commentType:"${sub.commentType}",url:"${myfn:getInformationUrl(sub.contextType,sub.type,sub.contentUrl,sub.id)}",shareUrl:"${myfn:getInformationUrl(sub.contextType,sub.type,sub.contentUrl,sub.id)}",shareTitle:"${myfn:base64(sub.shareTitle)}",title:"${myfn:base64(sub.title)}",synopsis:"${myfn:base64(sub.synopsis)}",shareImgUrl:"${fn:split(sub.imgUrls,',')[0]}"}));'> 
              <c:forEach items="${fn:split(sub.imgUrls,',') }" var="img">  
		          <c:choose>
			         <c:when test="${not empty param.noimg}">
			            <!--图片占位-->
					    <div class="detail_pic_none detail_pic_none_4 btn_click" imgSrc="${img}">
						    <span> ${appInfo.name } </span>
						    <span class="detail_pic_none_click">点击查看图片</span>
					    </div>
					    <!--图片占位-->
			       </c:when>
			       <c:otherwise><img src="${img }"/> </c:otherwise>
			      </c:choose>
		     </c:forEach>
        </div>
	    <div class="topic_list_content">
	      <div class="content_title">
             <a href='javascript:window.AppJsObj.redirectUrl(JSON.stringify({id:"${sub.id}",sourceId:"${sub.informationId}",detailViewType:"${sub.contextType eq 1 ? 1 : 2}",sourceType:"1",commentType:"${sub.commentType}",url:"${myfn:getInformationUrl(sub.contextType,sub.type,sub.contentUrl,sub.id)}",shareUrl:"${myfn:getInformationUrl(sub.contextType,sub.type,sub.contentUrl,sub.id)}",shareTitle:"${myfn:base64(sub.shareTitle)}",title:"${myfn:base64(sub.title)}",synopsis:"${myfn:base64(sub.synopsis)}",shareImgUrl:"${fn:split(sub.imgUrls,',')[0]}"}));'>${not empty sub.listTitle ? sub.listTitle : sub.title}</a>
          </div>
	      <div class="content_info">
	      <c:forEach items="${fn:split(sub.infoLabel,',')}" var="infoLabel" varStatus="vs">
	         <c:if test="${vs.count eq 1 and not empty infoLabel}">
	          <span class="topic_label">${infoLabel }</span>
	         </c:if>
	       </c:forEach>   
	            
	      ${myfn:dateformate(sub.onlineTime.time,'MM-dd HH:mm')}<span class="topic_list_time reply_count">${sub.totalReplyCount }评论</span></div>
	    </div>
	  </div>
    </c:when>
    <c:when test="${sub.listViewType eq 2 or sub.listViewType eq 4 or 5 eq sub.listViewType or 6 eq sub.listViewType}">
       <!-- 大图 --><!-- 轮播-->
	  <div class="topic_list topic_list_1" idValue="${sub.id}" sortNoValue="${sub.correSortNo}" onlineTimeValue="<fmt:formatDate value='${sub.onlineTime}' pattern='yyyy-MM-dd HH:mm:ss'/>">
	    <div class="topic_list_content">
	      <div class="content_title">
            <a href='javascript:window.AppJsObj.redirectUrl(JSON.stringify({id:"${sub.id}",sourceId:"${sub.informationId}",detailViewType:"${sub.contextType eq 1 ? 1 : 2}",sourceType:"1",commentType:"${sub.commentType}",url:"${myfn:getInformationUrl(sub.contextType,sub.type,sub.contentUrl,sub.id)}",shareUrl:"${myfn:getInformationUrl(sub.contextType,sub.type,sub.contentUrl,sub.id)}",shareTitle:"${myfn:base64(sub.shareTitle)}",title:"${myfn:base64(sub.title)}",synopsis:"${myfn:base64(sub.synopsis)}",shareImgUrl:"${fn:split(sub.imgUrls,',')[0]}"}));'>${not empty sub.listTitle ? sub.listTitle : sub.title}</a>
           </div>
	      <div class="topic_list_bigpic" onclick='javascript:window.AppJsObj.redirectUrl(JSON.stringify({id:"${sub.id}",sourceId:"${sub.informationId}",detailViewType:"${sub.contextType eq 1 ? 1 : 2}",sourceType:"1",commentType:"${sub.commentType}",url:"${myfn:getInformationUrl(sub.contextType,sub.type,sub.contentUrl,sub.id)}",shareUrl:"${myfn:getInformationUrl(sub.contextType,sub.type,sub.contentUrl,sub.id)}",shareTitle:"${myfn:base64(sub.shareTitle)}",title:"${myfn:base64(sub.title)}",synopsis:"${myfn:base64(sub.synopsis)}",shareImgUrl:"${fn:split(sub.imgUrls,',')[0]}"}));'> 
	        <c:forEach items="${fn:split(sub.imgUrls,',') }" var="img">
		          <c:choose>
			         <c:when test="${not empty param.noimg}">
			            <!--图片占位-->
					    <div class="detail_pic_none detail_pic_none_3 btn_click" imgSrc="${img}">
						    <span> ${appInfo.name } </span>
						    <span class="detail_pic_none_click">点击查看图片</span>
					    </div>
					    <!--图片占位-->
			       </c:when>
			       <c:otherwise><img src="${img }"/> </c:otherwise>
			      </c:choose>
		     </c:forEach>
	      </div>
	      <div class="content_info">
	      <c:forEach items="${fn:split(sub.infoLabel,',')}" var="infoLabel" varStatus="vs">
	         <c:if test="${vs.count eq 1 and not empty infoLabel}">
	          <span class="topic_label">${infoLabel }</span>
	         </c:if>
	       </c:forEach>      
	      ${myfn:dateformate(sub.onlineTime.time,'MM-dd HH:mm')}
	      <span class="topic_list_time reply_count">${sub.totalReplyCount }评论 </span></div>
	    </div>
	  </div>
    </c:when>
    <c:when test="${sub.listViewType eq 3}">
       <!-- 多图 -->
	  <div class="topic_list topic_list_1" idValue="${sub.id}" sortNoValue="${sub.correSortNo}" onlineTimeValue="<fmt:formatDate value='${sub.onlineTime}' pattern='yyyy-MM-dd HH:mm:ss'/>">
	    <div class="topic_list_content">
	      <div class="content_title">
               <a href='javascript:window.AppJsObj.redirectUrl(JSON.stringify({id:"${sub.id}",sourceId:"${sub.informationId}",detailViewType:"${sub.contextType eq 1 ? 1 : 2}",sourceType:"1",contextType:"${sub.contextType}",commentType:"${sub.commentType}",url:"${myfn:getInformationUrl(sub.contextType,sub.type,sub.contentUrl,sub.id)}",shareUrl:"${myfn:getInformationUrl(sub.contextType,sub.type,sub.contentUrl,sub.id)}",shareTitle:"${myfn:base64(sub.shareTitle)}",title:"${myfn:base64(sub.title)}",synopsis:"${myfn:base64(sub.synopsis)}",shareImgUrl:"${fn:split(sub.imgUrls,',')[0]}"}));' target="_blank">${not empty sub.listTitle ? sub.listTitle : sub.title}</a>
         </div>
	      <ul>
	         <c:forEach items="${fn:split(sub.imgUrls,',') }" var="img">
	         <li onclick='javascript:window.AppJsObj.redirectUrl(JSON.stringify({id:"${sub.id}",sourceId:"${sub.informationId}",detailViewType:"${sub.contextType eq 1 ? 1 : 2}",sourceType:"1",commentType:"${sub.commentType}",url:"${myfn:getInformationUrl(sub.contextType,sub.type,sub.contentUrl,sub.id)}",shareUrl:"${myfn:getInformationUrl(sub.contextType,sub.type,sub.contentUrl,sub.id)}",shareTitle:"${myfn:base64(sub.shareTitle)}",title:"${myfn:base64(sub.title)}",synopsis:"${myfn:base64(sub.synopsis)}",shareImgUrl:"${fn:split(sub.imgUrls,',')[0]}"}));'>
		          <c:choose>
			         <c:when test="${not empty param.noimg}">
			            <!--图片占位-->
					    <div class="detail_pic_none detail_pic_none_4 btn_click" imgSrc="${img}">
						    <span> ${appInfo.name } </span>
						    <span class="detail_pic_none_click">点击查看图片</span>
					    </div>
					    <!--图片占位-->
			       </c:when>
			       <c:otherwise><a href="javascript:;"><img src="${img }"/></a></c:otherwise>
			     
			      </c:choose>
			  </li>
		      </c:forEach>
	      </ul>
	      <div class="content_info">
	        <c:forEach items="${fn:split(sub.infoLabel,',')}" var="infoLabel" varStatus="vs">
	         <c:if test="${vs.count eq 1 and not empty infoLabel}">
	          <span class="topic_label">${infoLabel }</span>
	         </c:if>
	       </c:forEach>      
	           ${myfn:dateformate(sub.onlineTime.time,'MM-dd HH:mm')}
	      <span class="topic_list_time reply_count"> ${sub.totalReplyCount }评论</span></div>
	    </div>
	  </div>
    </c:when>
    
  </c:choose>
</c:forEach>

<script type="text/javascript">
   var noSubInfo = "${noSubInfo}";
  
   require(['jquery'],function(){
	   var pic_width=$(".topic_list .topic_list_content ul li").width();
		var singal_pic_width=$(".topic_list .topic_list_pic").width();
		var big_pic_width=$(".topic_list .topic_list_content .topic_list_bigpic").width();
		$(".topic_list .topic_list_content ul li,.topic_list .topic_list_content ul").height(9*pic_width/14);
		$(".topic_list .topic_list_pic").height(9*singal_pic_width/14);
		$(".topic_list .topic_list_content .topic_list_bigpic").height(13*big_pic_width/23);
		
		//无图模式图片高度
		var pic_none_width1=$(".detail_pic_none_1").width();
		$(".detail_pic_none_1").height(pic_none_width1/2);
		
		var pic_none_width2=$(".detail_pic_none_2").width();
		$(".detail_pic_none_2").height(pic_none_width2/4);
		
		var pic_none_width4=$(".detail_pic_none_4").width();
		$(".detail_pic_none_4").height(9*pic_none_width4/14);
		
		var pic_none_width3=$(".detail_pic_none_3").width();
		$(".detail_pic_none_3").height(13*pic_none_width3/23);
		
		$(".topic_list_content").each(function(){
			var $this = $(this);
			var children = $this.children();
			var h = 10;
			for(var i=0;i<children.length;i++){
				h += children[i].offsetHeight;
			}
			$this.height(h);
		});
		
		 var img_height=$(".topic_list_2 .topic_list_pic img").height();
		$(".topic_list_2 .topic_list_content").height(img_height); 
		
		redirect();
		function redirect(){
			if(window.AppJsObj)return;
			window.AppJsObj = new Object();
			AppJsObj.redirectUrl = function(json){
				json = JSON.parse(json);
				if(json.shareUrl){
					location.href=json.shareUrl;
				}
			} 
		}
		
   });
</script>


