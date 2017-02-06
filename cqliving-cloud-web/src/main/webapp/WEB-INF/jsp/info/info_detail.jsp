<%@page contentType="text/html; charset=utf-8"%>
 <%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jsp/common/include.jsp" %>
<title>${infoDetail.title}${1 eq appInfo.id ? '-重庆APP' : ''}</title>

<meta content="${infoDetail.title}${1 eq appInfo.id ? '-重庆APP' : ''}" itemprop="name"/>
<meta content="${infoDetail.synopsis}" itemprop="description"/>
<meta content="http://images.cqliving.com/images/icon/${appInfo.id }.png" itemprop="image"/>

<style type="text/css">
.hidden{
   display:none;
}
</style>

</head>
<body>

<div class="hidden shareimg">
   <img src="http://images.cqliving.com/images/icon/${appInfo.id }.png"/>
</div>

<c:if test="${not empty param.share }">
    <c:choose>
        <c:when test="${1 eq appInfo.id}">
            <div class="cq_share_top" loadurl="${appConfig.downLoadUrl}">
               <img src="${request.contextPath}/front/detail/images/share_top.jpg" alt="" />
            </div>
        </c:when>
        <c:otherwise>
            <div class="share_top">
			    <span openUrl="${appConfig.downLoadUrl}" loadUrl="${param.loadUrl }">通过${appInfo.name }客户端查看</span><a href="javascript:;"><img src="${request.contextPath}/front/detail/images/close.png"/></a>
			</div>
        </c:otherwise>
    </c:choose>
</c:if>

<div id="detail" class="${not empty param.share ? 'detail_share' : '' }">

  <input type="hidden" value="${infoDetail.infoClassifyId}" name="infoclassifyid"/>
  <input type="hidden" value="${appInfo.id}" name="appId" id="appId"/>
  <input type="hidden" value="${infoDetail.id}" name="infoDetailId"/>
  <input type="hidden" value="${infoDetail.title}" name="title"/>
  
  <div class="detail_title">${infoDetail.title}</div>
  <div class="detail_info">
      <c:if test="${not empty  infoDetail.infoSource}"><span>${infoDetail.infoSource}</span></c:if>
      <c:choose>
        <c:when test="${appInfo.id eq 7 }">
             <!-- 武隆的日期要显示年份 -->
            ${myfn:dateformate(infoDetail.onlineTime.time,'yyyy-MM-dd HH:mm')}
        </c:when>
        <c:otherwise>
           ${myfn:dateformate(infoDetail.onlineTime.time,'MM-dd HH:mm')}
        </c:otherwise>
      </c:choose>
      <span class="detail_info_right">浏览量&nbsp;&nbsp;${myfn:getViewCount(infoDetail.viewCount)}</span>
   </div>
  <!--内容-->
  <div class="detail_content  ${infoDetail.contextType eq 2 ? 'hidden' : '' }" id="${infoDetail.contextType eq 2 ? 'normal_content' : 'detail_content' }"> 
    <!-- 根据内容类型不同来显示不同的内容 -->
    <!-- 新闻内容类型{0:纯文本,1:多图,2:原创,3:外链,4:音频,5:视频} -->
    <c:choose>
       <c:when test="${infoDetail.contextType eq 0}">
            <imgrp:img appName="${appInfo.name }" replace="${not empty param.noimg ? 1 : 0}" content="${infoDetail.content}"/>
       </c:when>
       <c:when test="${infoDetail.contextType eq 1}">
           <c:forEach items="${infoDetail.appResource}" var="ar">
			    
			    <c:choose>
			       <c:when test="${ar.type eq 1 }">
			          <!--视频-->
                      <video width="100%" controls="controls" preload="preload"><source src="${ar.fileUrl }">您不支持视频播放</video>
			       </c:when>
			       <c:when test="${ar.type eq 2 }">
			          <!--音频-->
                      <audio controls="controls"><source src="${ar.fileUrl }">您不支持音频播放 </audio>
			       </c:when>
			       <c:when test="${ar.type eq 6 and not empty param.noimg}">
			            <!--图片占位-->
					    <div class="detail_pic_none btn_click" imgSrc="${ar.fileUrl}">
						    <span> ${appInfo.name } </span>
						    <span class="detail_pic_none_click">点击查看图片</span>
					    </div>
					    <!--图片占位-->
					    <p> <imgrp:img appName="${appInfo.name }" replace="${not empty param.noimg ? 1 : 0}" content="${ar.description}"/> </p>
			       </c:when>
			       <c:when test="${ar.type eq 6 and empty param.noimg}">
			           <img src="${ar.fileUrl}"/>
			           <p> <imgrp:img appName="${appInfo.name }" replace="${not empty param.noimg ? 1 : 0}" content="${ar.description}"/> </p>
			       </c:when>
			       <c:otherwise>
			           <!--文本-->
			           <p> <imgrp:img appName="${appInfo.name }" replace="${not empty param.noimg ? 1 : 0}" content="${ar.description}"/> </p>
			       </c:otherwise>
			    </c:choose>
			    
			    
           </c:forEach>
       </c:when>
       <c:when test="${infoDetail.contextType eq 3}">
           <script type="text/javascript">
              location.href="${infoDetail.contentUrl}";
           </script>
       </c:when>
       <c:when test="${infoDetail.contextType eq 4}">
           <c:forEach items="${infoDetail.appResource}" var="ar">
               <!--音频-->
               <audio controls="controls"><source src="${ar.fileUrl }">您不支持音频播放 </audio>
           </c:forEach>
       </c:when>
       <c:when test="${infoDetail.contextType eq 5}">
           <c:forEach items="${infoDetail.appResource}" var="ar">
                <!--视频-->
             <video width="100%" controls="controls"><source src="${ar.fileUrl }">您不支持视频播放</video>
           </c:forEach>
       </c:when>
    </c:choose>
   </div>
 
 <!-- 原创 -->
  <c:if test="${infoDetail.contextType eq 2 }">
    <c:forEach items="${infoDetail.infocontents}" var="infoContent">
       <c:choose>
          <c:when test="${infoContent.type eq 3}">
             <!-- //投票 -->
              <c:forEach items="${infoContent.votes}" var="vot">
			    <c:choose>
			       <c:when test="${vot.surveyQuestionDtos[0].type eq 6}">
			           <!--图片投票-->
					  <div class="detail_activity">
					    <div  class="activity_title">投票主题：${vot.title}</div>
					    <div class="activity_content">
					      <ul class="vote_list" limitruletype="${vot.limitRuleType}" limitruletimes="${vot.limitRuleTimes }">
					        <c:forEach items="${vot.surveyQuestionDtos[0].surveyQuestionAnswers}" var="voteAns">
					            <li quesid="${vot.surveyQuestionDtos[0].id}" voteid="${vot.id}" ansid="${voteAns.id}"> 
						        <a class="vote_list_click vote_btn" >
						          <div class="vote_list_pic"> 
						          
						           <c:choose>
								       <c:when test="${not empty param.noimg }">
								           <!--图片占位-->
										    <div class="detail_pic_none btn_click" imgSrc="${voteAns.imageUrl}">
											    <span> ${appInfo.name } </span>
											    <span class="detail_pic_none_click">点击查看图片</span>
										    </div>
										    <!--图片占位-->
								       </c:when>
								       <c:otherwise>
								          <img src="${voteAns.imageUrl}"/> 
								       </c:otherwise>
								    </c:choose>
						             
						          <div class="vote_list_number_bg"></div>
						          <div class="vote_list_number">
						            <div class="vote_list_number_left">${voteAns.number}</div>
						            <div class="vote_list_number_right">
						            <span>
						            <c:forEach items="${answerNum}" var="ansnum">
						               <c:if test="${ansnum.key eq voteAns.id}">${ansnum.value}</c:if>
						            </c:forEach>
						            </span>票</div>
						          </div>
						          </div>
						          <div class="vote_list_title">${voteAns.answer}</div>
						          </a>
						          <div class="vote_list_btn ">
						            <button class="vote_btn btn_click">投票</button>
						          </div>
						        </li>
					        </c:forEach>
					      </ul>
					    </div>
					  </div>
			       </c:when>
			       <c:otherwise>
			            <div class="detail_activity">
						    <div  class="activity_title">投票主题：${vot.title}</div>
						    <div class="activity_content">
						      <ul class="survey_vote ${vot.limitRuleType eq 2  ? 'survey_checkbox' : '' }" limitruletype="${vot.limitRuleType}" limitruletimes="${vot.limitRuleTimes }">
						        <c:forEach items="${vot.surveyQuestionDtos[0].surveyQuestionAnswers }" var="ans">
						            <li quesid="${vot.surveyQuestionDtos[0].id}" voteid="${vot.id}">
							          <label>
							            <c:choose>
							               <c:when test="${vot.limitRuleType eq 2}">
							                  <input type="checkbox" name="ansid" value="${ans.id}"/>
							               </c:when>
							               <c:otherwise>
							                  <input type="radio" name="ansid" value="${ans.id}"/>
							               </c:otherwise>
							            </c:choose>
							          <div></div>
							          <span>${ans.answer }</span>
							          </label>
							          <div class="survey_vote_info">
							            <div class="vote_line"><span style="width:0%;"></span></div>
							            <div class="vote_num" vote_ansid="${ans.id}">
							              <c:forEach items="${answerNum}" var="ansnum">
							               <c:if test="${ansnum.key eq ans.id}">${ansnum.value}</c:if>
							              </c:forEach>  
							                    票（<span>0%</span>）</div>
							          </div>
							        </li>
						        </c:forEach>
						      </ul>
						      <div class="btn_submit btn_click"> 投票 </div>
						    </div>
						  </div>
			       </c:otherwise>
			    </c:choose>
			  </c:forEach>
          </c:when>
          <c:when test="${infoContent.type eq 4}">
             <!-- //打擂 -->
             <c:forEach items="${infoContent.arenas}" var="are">
				  <div class="detail_activity">
				    <div  class="activity_title">${are.title }</div>
				    <div class="activity_content">
				      <div class="arena_title">
				        <c:forEach items="${are.surveyQuestionDtos[0].surveyQuestionAnswers}" var="ans">
				            <div class="arena_title_${ans.isAffirmative eq 1 ? 'left' : 'right'}">
				             <img src="${request.contextPath}/front/detail/images/flag_${ans.isAffirmative eq 1 ? '1' : '2'}.png"/>
			                  ${ans.answer }  
				            </div>
				        </c:forEach>
				      </div>
				      <div class="arena_content">
				       
				        <c:forEach items="${are.surveyQuestionDtos[0].surveyQuestionAnswers}" var="ans">
				            <c:if test="${ans.isAffirmative eq 1}">
				               <div class="arena_content_left arena_content_1">
						          <div class="btn_click" ansid="${ans.id}" arenaid="${are.id }" quesid="${are.surveyQuestionDtos[0].id}"></div>
						          <p>正方</p>
						        </div>
				            </c:if>
				        </c:forEach>
				        
				        <div class="arena_content_line">
				          <div id="arena_line_1" style="width:0%;"></div>
				          <div id="arena_line_2" style="width:0%;"></div>
			               <c:forEach items="${are.surveyQuestionDtos[0].surveyQuestionAnswers}" var="ans">
			                  <c:if test="${ans.isAffirmative eq 1}">
			                  <p id="arena_num_1" ansid="${ans.id }">
			                    <c:forEach items="${answerNum}" var="ansnum">
					               <c:if test="${ansnum.key eq ans.id}">${ansnum.value}</c:if>
					            </c:forEach>  
			                  </p></c:if>
			                  <c:if test="${ans.isAffirmative eq 0}">
			                  <p id="arena_num_2" ansid="${ans.id }">
			                    <c:forEach items="${answerNum}" var="ansnum">
					               <c:if test="${ansnum.key eq ans.id}">${ansnum.value}</c:if>
					            </c:forEach>  
			                  </p></c:if>
			               </c:forEach>
				        </div>
				        
				        <c:forEach items="${are.surveyQuestionDtos[0].surveyQuestionAnswers}" var="ans">
				            <c:if test="${ans.isAffirmative eq 0}">
				                <div class="arena_content_right arena_content_1">
						          <div class="btn_click" ansid="${ans.id}" arenaid="${are.id }" quesid="${are.surveyQuestionDtos[0].id}"></div>
						          <p>反方</p>
						        </div>
				            </c:if>
				        </c:forEach>
				      </div>
				    </div>
				  </div>
			  </c:forEach>
          </c:when>
          <c:otherwise>
             <!-- //音视频文本多图等 -->
            <div class="detail_content" id="detail_content"> 
             <c:forEach items="${infoContent.appResource}" var="ar">
			    <c:choose>
			       <c:when test="${ar.type eq 1 }">
			          <!--视频-->
                      <video width="100%" controls="controls" preload="preload"><source src="${ar.fileUrl }">您不支持视频播放</video>
			       </c:when>
			       <c:when test="${ar.type eq 2 }">
			          <!--音频-->
                      <audio controls="controls"><source src="${ar.fileUrl }">您不支持音频播放 </audio>
			       </c:when>
			       <c:when test="${ar.type eq 6 and not empty param.noimg}">
			            <!--图片占位-->
					    <div class="detail_pic_none btn_click" imgSrc="${ar.fileUrl}">
						    <span> ${appInfo.name } </span>
						    <span class="detail_pic_none_click">点击查看图片</span>
					    </div>
					    <!--图片占位-->
					    <p> <imgrp:img appName="${appInfo.name }" replace="${not empty param.noimg ? 1 : 0}" content="${ar.description}"/> </p>
			       </c:when>
			       <c:when test="${ar.type eq 6 and empty param.noimg}">
			           <img src="${ar.fileUrl}"/>
			           <p> <imgrp:img appName="${appInfo.name }" replace="${not empty param.noimg ? 1 : 0}" content="${ar.description}"/> </p>
			       </c:when>
			       <c:otherwise>
			           <!--文本-->
			           <p> <imgrp:img appName="${appInfo.name }" replace="${not empty param.noimg ? 1 : 0}" content="${ar.description}"/> </p>
			       </c:otherwise>
			    </c:choose>
             </c:forEach>
            </div>
          </c:otherwise>
       </c:choose>
    </c:forEach>
  </c:if>
  
  <div class="detail_line"></div>
  <!--over-->
  <c:if test="${not empty param.share }">
	  
	  <c:if test="${not empty  infoClassifyCorreDto}">
		  <div class="detail_read">
		    <div class="detail_read_title"><img src="${request.contextPath}/front/detail/images/book.png"/><span>相关阅读</span></div>
		    <ul>
		      <c:forEach items="${infoClassifyCorreDto}" var="correinfo">
		           <li>
			        <div class="detail_read_list"> <a href="${request.contextPath}/info/detail/${correinfo.id}.html" target="_blank"> ${correinfo.title }  </a></div>
			        <div class="detail_read_list_left">评论<span>${correinfo.infoReplyCount }</span></div>
			        <div class="detail_read_list_right"> <fmt:formatDate value="${correinfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>  </div>
			      </li>
		      </c:forEach>
		    </ul>
		  </div>
	  </c:if>
	  
	   <%-- <div class="detail_zan">
	    <div class="detail_zan_img btn_click" onclick="javascript:location.href='${appConfig.downLoadUrl}';"></div>
	    <p>${infoDetail.praiseCount }</p>
	  </div> --%>
	  
	  <c:if test="${infoDetail.replyCount ge 1}">
		  <div class="detail_comment_list">
		    <div class="comment_list_title"><span>评论(${infoDetail.replyCount})</span></div>
		    <ul>
		      <!-- 分页内容 -->
		    </ul>
		    <!-- <div class="comment_list_more btn_click">点击查看更多</div> -->
		  </div>
	  </c:if>
  </c:if>

 <c:if test="${not empty param.share }">
      <c:choose>
          <c:when test="${1 eq appInfo.id }">
             <img loadurl="${appConfig.downLoadUrl}" src="${request.contextPath}/front/detail/images/share_bottom.jpg" class="cq_img_bottom"/>
          </c:when>
          <c:otherwise>
              <div class="share_link btn_click">
			      <a href="${appConfig.downLoadUrl}" loadurl="${param.loadUrl}"> ${appInfo.name }里有更多精彩，去看看<img src="${request.contextPath }/front/detail/images/link.png"/></a>
			  </div>
          </c:otherwise>
      </c:choose>
  </c:if>
</div>
<div id="temp_div" class="hidden"></div>

<div class="topic_memo hidden">${infoDetail.synopsis}</div>

</body>

</html>
<script type="text/javascript">

   //后台预览没有事件
   var noev = "${param.noev}";
   var share = "${param.share}";
   var synopsis = "";
   var sessionStr = '{token:"${param.token}",sessionId:"${param.sessionId}"}';
   var loadUrl = "${appConfig.downLoadUrl}";
   var hlwOldGuid = "${infoDetail.hlwOldGuid}";
   
   function getToken(params){
	   sessionStr = params;
   }
   
  require(["jquery","cqliving_ajax","${request.contextPath}/resource/js/business/infomation/info_detail.js"],function($,cqliving_ajax){
	//无图模式图片高度
	var pic_none_width=$(".detail_pic_none").width();
	$(".detail_pic_none").height(pic_none_width/2);
	//投票选项图片高度
	var vote_list_pic_width=$(".vote_list_pic").width();
	$(".vote_list_pic").height(3*vote_list_pic_width/4);
	
	//获取数据
	var params = {
			infoClassifyId : $(":input[name=infoclassifyid]").val(),
			informationId:"${infoDetail.id}",
			isQuery:true,
			sortValue:""
	}
	
	function getInfoCommentPage(){
		var url = "${request.contextPath}/info/comment.html";
		cqliving_ajax.ajaxOperate(url,"",params,function(html){
			if(params.isQuery){
				$(".detail_comment_list ul").html(html);
			}else{
				$(".detail_comment_list ul").append(html);
			}
		},{dataType:"html"});
	}
	
   if(share){
	   var appId = $(":input[name=appId]").val();
	   if(appId && appId == 1){
		   $("#detail").addClass("cq_detail_share");
	   }
	   getInfoCommentPage();
   }
   
	$(".comment_list_more").on("click",function(){
		params.isQuery = false;
		params.sortValue = $(".detail_comment_list ul li").last().attr("sortValue");
		
		getInfoCommentPage();
	});
	
	$(".detail_pic_none").on("click",function(){
		
		var $this = $(this);
		var imgSrc = $this.attr("imgSrc");
		$this.after("<img src='"+imgSrc+"'/>");
		$this.remove();
	});
	
	$(".share_top a").click(function(){
		$(".share_top").hide();
		$("#detail").removeClass("detail_share");
	});
	
	$(".detail_content img").on("click tap",function(){
		var contentImgArray = [];
		var $img = $(".detail_content img");
		var index = $img.index(this);
		$img.each(function(i,n){
			var $this = $(n);
			var	imgSrc = $this.attr("src");
			if(imgSrc)contentImgArray.push(imgSrc);
		});
		try{
			if(contentImgArray.length>=1){
				 window.AppJsObj.getPhotoImagePathArray(contentImgArray.join(","),index);
			}
		}catch(e){}
	});
	
	$("video").on("ended",function(e){
		 var de = document;
		 if (de.exitFullscreen) {
		     de.exitFullscreen();
	     } else if (de.mozCancelFullScreen) {
	       de.mozCancelFullScreen();
	     } else if (de.webkitCancelFullScreen) {
	       de.webkitCancelFullScreen();
	     }
	});
	
	$("a[loadurl],div[loadurl],img[loadurl]").bind("click",function(){
		
		var loadUrl = $(this).attr("loadurl");
		if(loadUrl){
			setTimeout(function(){
				location.href = loadUrl;
			},500);
		}
		
	});
	
	$("span[loadUrl]").bind("click",function(){
		
		var $this = $(this);
		var openUrl = $this.attr("openUrl");
		var loadUrl = $this.attr("loadUrl");
		location.href = openUrl;
		
		if(loadUrl){
			setTimeout(function(){
				location.href = loadUrl;
			},500);
		}
	});
	
	//清楚p、h的宽度
	clearWidth();
	
	window.initVAdio = function(){
		
		if($("video,audio").length<=0)return;
		
		$("video,audio").get(0).play();
		
		setTimeout(function(){
			$("video,audio").get(0).pause();
		},500);
	};
	window.initVAdio();
	
	function getSyno(){
	  var sy = "";
	  var text = $(".topic_memo").text();
	  if(text){
		  sy  = $.trim(text);
	  }
	  var imgSrc = "${infoDetail.listViewImg}";
	  if(imgSrc){
		  imgSrc = imgSrc.split(",")[0];
	  }else{
		  imgSrc="http://images.cqliving.com/images/icon/${appInfo.id}.png";
	  }
	  synopsis = "synopsis="+sy+",imgSrc="+imgSrc;
	  $("meta[itemprop=image]").attr("content",imgSrc);
	  $(".shareimg img").attr("src",appendSuffix(0,300,imgSrc));
	}
	getSyno();
	
	try{
		ZWY_ClOUD.getSessionToken("getToken");
	}catch(e){}
	
	//增加浏览量及保存访问记录
	setTimeout(function(){
		addViewCount();
	},1000);
	function  addViewCount(){
		var url = "/info/add_view_num.html";
		var params = {appId:"${appInfo.id}",infoClassifyId:"${infoDetail.infoClassifyId}",informationId:"${infoDetail.id}"};
		try{
			ZWY_ClOUD.getSessionToken("getToken");
				setTimeout(function(){
			      if(sessionStr){
					var sessionObj = eval("("+sessionStr+")");
					params.sessionId = sessionObj.sessionId;
					params.token = sessionObj.token;
					cqliving_ajax.ajaxOperate(url,"",params,function(data,status){
					});
				  }
				},1000);
		}catch(e){}
	}
	
	//华龙网抓稿的在p标签样式上加首行缩进
	hwlGuid();
	function hwlGuid(){
		if(hlwOldGuid){
			$(".detail_content p").each(function(i,n){
				var $this = $(n);
				var children = $this.children();
                if(children.length>=1){
                	return true;
                }				
				var text = $.trim($this.text());
				$this.text(text);
                $this.css("text-indent","2em");				
			});
		}
	}
	
	
  });
  
  function getJSPParams(){
	 try{ window.AppJsObj.getSynopsis(synopsis);}catch(e){}
	  return synopsis;
  }
  
</script>