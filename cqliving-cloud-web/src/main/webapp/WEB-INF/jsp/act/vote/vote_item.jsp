<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<input type="hidden" name="appId" value="${actVoteDto.appId}"/>
<input type="hidden" name="actInfoId" value="${actVoteDto.actInfoId}"/>
<input type="hidden" name="actInfoListId" value="${actVoteDto.actInfoListId}"/>
<input type="hidden" name="voteId" value="${actVoteDto.id}"/>
<input type="hidden" name="actVoteClassifyId" value="${voteClassifyDto.id }"/>

<c:choose>
     <c:when test="${voteClassifyDto.isImageVote eq 1}">
         <div class="detail_activity">
		     <%-- <div  class="activity_title">
		              投票主题：${voteClassifyDto.subject}
		     </div> --%>
		     <div class="activity_content">
		       <ul class="vote_list  vote_list_more">
		         <c:forEach items="${voteClassifyDto.actVoteItems }" var="voteItem">
		             <li  hasContent="${not empty  voteItem.content or not empty voteItem.videoUrl ? 'yes' : 'no' }"> 
		               <a class="vote_list_click vote_btn" href="javascript:;">
			            <div class="vote_list_pic" itemid="${voteItem.id}"> 
			               <c:choose>
						       <c:when test="${not empty param.noimg }">
						         <!--图片占位-->
							    <div class="detail_pic_none btn_click" imgSrc="${voteItem.imageUrl}">
								    <span> ${appInfo.name } </span>
								    <span class="detail_pic_none_click">点击查看图片</span>
							    </div>
						       </c:when>
						       <c:otherwise>
						          <img src="${voteItem.imageUrl }"/> 
						       </c:otherwise>
						    </c:choose>
			              <div class="vote_list_number_bg"></div>
			              <div class="vote_list_number">
			                <div class="vote_list_number_left">${voteItem.number}</div>
			                <div class="vote_list_number_right"><span>${voteItem.actValue + voteItem.initValue}</span>票</div>
			              </div>
			            </div>
			            <div class="vote_list_title" itemid="${voteItem.id}">
				              <p>${voteItem.itemTitle}</p>
				              <p>${voteItem.itemDesc}</p>
			            </div>
		            </a>
		              <c:choose>
		                 <c:when test="${actVoteDto.limitRuleType eq 1 }">
		                    <div class="survey_checkbox active_list_checkbox">
				              <label>
				              <input type="checkbox" name="actVoteItemId" value="${voteItem.id}"/>
				              <div></div>
				              <span>选择</span>
				              </label>
				            </div>
		                 </c:when>
		                 <c:otherwise>
		                    <div class="vote_list_btn ">
				              <button class="vote_btn btn_click" actVoteItemId="${voteItem.id}">投票</button>
				            </div>
		                 </c:otherwise>
		              </c:choose>
		          </li>
		         </c:forEach>
		       </ul>
		      <c:if test="${actVoteDto.limitRuleType eq 1}">
		        <div class="btn_submit btn_click img_vote"> 投票 </div>
		      </c:if>
		     </div>
	    </div>
     </c:when>
     <c:otherwise>
         <div class="detail_activity">
		     <div  class="activity_title">
		              投票主题：${voteClassifyDto.subject}
		     </div>
		     <div class="activity_content">
		       <ul class="${actVoteDto.limitRuleType eq 1 ? 'survey_checkbox' : ''} survey_vote ">
		         <c:forEach items="${voteClassifyDto.actVoteItems }" var="voteItem">
		            <li>
		            <label>
		            <input type="${actVoteDto.limitRuleType eq 1 ? 'checkbox' : 'radio'}" name="actVoteItemId" value="${voteItem.id }"/>
		            <div></div>
		            <span>
		               <c:choose>
		                  <c:when test="${not empty  voteItem.content or not empty voteItem.videoUrl}">
		                      <a href="${request.contextPath }/act/vote_item/${voteItem.id}.html?appId=${actVoteDto.appId }&actInfoId=${actVoteDto.actInfoId}&actInfoListId=${actVoteDto.actInfoListId}&voteId=${actVoteDto.id}">
				                 ${voteItem.itemTitle } 
				               </a> 
		                  </c:when>
		                  <c:otherwise>
		                     ${voteItem.itemTitle } 
		                  </c:otherwise>
		               </c:choose>
		            </span>
		            </label>
		            <div class="survey_vote_info">
		              <div class="vote_line"><span style="width:0%;"></span></div>
		              <div class="vote_num"><span>${voteItem.actValue + voteItem.initValue }</span>票(<span>0%</span>)</div>
		            </div>
		          </li>
		         </c:forEach>
		       </ul>
		       <div class="survey_vote_count">共0票</div>
		       <div class="btn_submit btn_click"> 投票 </div>
		     </div>
   		</div>
     </c:otherwise>
 </c:choose>

<script type="text/javascript">

	require(['jquery','/resource/js/business/act/vote/vote_detail.js'],function($,vote_detail){
		
			vote_detail.getVotePercent();
			vote_detail.initBind();
		
		   //投票选项图片高度
			var vote_list_pic_width=$(".vote_list_pic").width();
			$(".vote_list_pic").height(4*vote_list_pic_width/3);
			
			 $(".detail_pic_none").on("click",function(){
				var $this = $(this);
				var imgSrc = $this.attr("imgSrc");
				$this.after("<img src='"+imgSrc+"'/>");
				$this.remove();
			});
			 
			 $(".vote_list_pic").on("click","img",function(){
				 var itemid = $(this).closest(".vote_list_pic").attr("itemid");
				 
				 var has = $(this).closest("li").attr("hasContent");
				 
				 if(has && has=="yes"){
					 redirectItemDetail(itemid);
				 }
			 });
			 
		  $(".vote_list_title[itemid]").on("click","p",function(){
			  
			  var itemid = $(this).parent().attr("itemid");
			  
			  var has = $(this).closest("li").attr("hasContent");
				 
				 if(has && has=="yes"){
					 redirectItemDetail(itemid);
				 }
			  
		  });
			 
		  function redirectItemDetail(itemid){
			  location.href="/act/vote_item/"+itemid+".html?appId=${actVoteDto.appId }&actInfoId=${actVoteDto.actInfoId}&actInfoListId=${actVoteDto.actInfoListId}&voteId=${actVoteDto.id}";
		  }
		  
		//清楚p、h的宽度
		clearWidth();
	});
</script>

