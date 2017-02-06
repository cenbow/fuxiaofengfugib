<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jsp/common/include.jsp" %>

<meta content="${infoDetail.title}${1 eq appInfo.id ? '-重庆APP' : ''}" itemprop="name"/>
<meta content="${infoDetail.synopsis }" itemprop="description"/>
<meta content="http://images.cqliving.com/images/icon/${appInfo.id }.png" itemprop="image"/>

<style type="text/css">
.hidden{
   display:none;
}
</style>
<title>${infoDetail.title}${1 eq appInfo.id ? '-重庆APP' : ''}</title>
</head>
<body>

<div class="hidden shareimg">
   <img src="http://images.cqliving.com/images/icon/${appInfo.id }.png"/>
</div>

<input type="hidden" name="appId" value="${appInfo.id}" id="appId"/>
<input type="hidden" name="share" value="${param.share}" id="shareInput"/>
<input type="hidden" name="infoClassifyId" value="${infoDetail.infoClassifyId }" id="infoClassifyId"/>
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

<div class="topic_pic"> 
      <c:choose>
         <c:when test="${not empty param.noimg}">
            <!--图片占位-->
		    <div class="detail_pic_none detail_pic_none_2  detail_pic_none_1 btn_click" imgSrc="${infoDetail.contentUrl}">
			    <span> ${appInfo.name } </span>
			    <span class="detail_pic_none_click">点击查看图片</span>
		    </div>
		    <!--图片占位-->
       </c:when>
       <c:otherwise><img src="${infoDetail.contentUrl }"/> </c:otherwise>
      </c:choose>
</div>
<div id="topic">
  <c:if test="${not empty infoDetail.synopsis }"><div class="topic_memo"> ${infoDetail.synopsis } </div></c:if>
  
  <div class="topic_nav">
    <ul>
      <c:forEach items="${infoThemes }" var="theme">
           <li themeid="${theme.id}"><a href="javascript:;">${theme.name}</a></li>
      </c:forEach>
    </ul>
  </div>
  
  <div class="topic_list_append"></div>
  
</div>
<div class="btn_click" style="clear:both;height:50px;"></div>
<div class="comment_list_more btn_click" id="more_div_btn" style="clear:both; margin-bottom:2em; width:96%;margin-left:1%;">点击查看更多</div>

<c:if test="${not empty param.share }">
	  <div class="detail_line"></div>
	   <%-- <div class="detail_zan">
	    <div class="detail_zan_img btn_click"></div>
	    <p>${infoDetail.praiseCount }</p>
	  </div> --%>
	  <%-- <div class="detail_comment_list">
	    <div class="comment_list_title"><span>评论(${infoDetail.replyCount})</span></div>
	    <ul>
	      <!-- 分页内容 -->
	    </ul>
	    <div class="comment_list_more btn_click">点击查看更多</div>
	  </div> --%>
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

</body>

<script  type="text/javascript">
  var noimg = "${param.noimg}";
  var appId = '${appInfo.id}';
  var synopsis = "";
  var loadUrl = "${appConfig.downLoadUrl}";
  var fontSizeObj = {};
  var viewObj = null;
  function backCallFun(infoclassifyId){
	  viewObj(infoclassifyId);
  }
  
  var sessionStr = '{token:"${param.token}",sessionId:"${param.sessionId}"}';
  function getToken(params){
	   sessionStr = params;
  }
  
  require(['cqliving_ajax','/resource/js/business/infomation/special_info.js?v=v1'],function(cqliving_ajax){
	  
	//无图模式图片高度
		var pic_none_width1=$(".detail_pic_none_1").width();
		$(".detail_pic_none_1").height(pic_none_width1/2);
		
		var pic_none_width2=$(".detail_pic_none_2").width();
		$(".detail_pic_none_2").height(3*pic_none_width2/4);
		
		var pic_none_width3=$(".detail_pic_none_3").width();
		$(".detail_pic_none_3").height(3*pic_none_width3/7);
	  
	  $("body").on("click",".detail_pic_none",function(){
			var $this = $(this);
			var imgSrc = $this.attr("imgSrc");
			$this.after("<img src='"+imgSrc+"'/>");
			$this.remove();
		});
	  
	  fontSizeObj.callBack = setUlWidth;
	  fontSizeObj.callBack();
	  function setUlWidth(fontSize){
		  var $li = $(".topic_nav ul li");
		  var liNum = $li.length;
		  if(liNum<=0)return;
		  var w = 10;
		  $li.each(function(i,n){
			  var tw = $(n).width();
			  w += tw;
		  });
		  if(fontSize)w += 50;
		  w += $li.length * 18;
		  $(".topic_nav ul").css("width",w+"px");
	  }
	   //获取数据
		var params = {
				infoClassifyId : '${infoDetail.id}',
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
		
		$(".comment_list_more").on("click",function(){
			params.isQuery = false;
			params.sortValue = $(".detail_comment_list ul li").last().attr("sortValue");
			
			getInfoCommentPage();
		});
		
		$("span[loadUrl],div[loadurl],img[loadurl]").bind("click",function(){
			var $this = $(this);
			var loadUrl = $this.attr("loadUrl");
			location.href = loadUrl;
		});
		
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
		
		viewObj = getViewNum;
		function getViewNum(infoClassifyId){
			var $infoDiv = $("div[idValue="+infoClassifyId+"]");
			if(!$infoDiv || $infoDiv.length<=0)return;
			var url = "${request.contextPath}/topic/info_view_num.html";
			cqliving_ajax.ajaxOperate(url,"",{infoClassifyId:infoClassifyId},function(data,status){
				$infoDiv.find(".view_span").html("浏览&nbsp;"+data.viewCount);
				$infoDiv.find(".reply_count").html(data.replyCount+"评论");
				$infoDiv.find(".reply_num").html("评论&nbsp;"+data.replyCount);
			});
		}
		
		//增加浏览量及保存访问记录
		addViewCount();
		function  addViewCount(){
			var url = "/topic/add_view_num.html";
			var params = {appId:"${appInfo.id}",infoClassifyId:"${infoDetail.infoClassifyId}",informationId:"${infoDetail.id}"};
			try{
				ZWY_ClOUD.getSessionToken("getToken");
				if(sessionStr){
					setTimeout(function(){
						var sessionObj = eval("("+sessionStr+")");
						params.sessionId = sessionObj.sessionId;
						params.token = sessionObj.token;
						cqliving_ajax.ajaxOperate(url,"",params,function(data,status){
						});
					},1000);
				}
			}catch(e){}
		}
		
		//微信分享
		try{
			  var shareObj = {
		              "appid": "",
		              "img_url": $(".shareimg img").attr("src"),
		              "img_width": "200",
		              "img_height": "200",
		              "link": window.location.href,
		              "desc": $.trim($(".topic_memo").text()),
		              "title": "${infoDetail.title}"
		       };
			  var appId = $(":input[name=appId]").val();
			  if(appId && 1 == appId){
				  shareObj.title = shareObj.title + "-重庆APP";
			  }
			  function shareFriend(){
				  WeixinJSBridge.invoke('sendAppMessage',shareObj, function(res) {
		              //_report('send_msg', res.err_msg);  // 这是回调函数，必须注释掉
		          })
			  }
			  function shareTimeline() {
		          WeixinJSBridge.invoke('shareTimeline',shareObj, function(res) {
		                 //_report('timeline', res.err_msg); // 这是回调函数，必须注释掉
		          });
		      }
		      function shareWeibo() {
		          WeixinJSBridge.invoke('shareWeibo',{
		              "content": shareObj.desc,
		              "url": shareObj.link,
		          }, function(res) {
		              //_report('weibo', res.err_msg);
		          });
		      }
			  // 当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
		      document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		          // 发送给好友
		          WeixinJSBridge.on('menu:share:appmessage', function(argv){
		              shareFriend();
		          });
		          // 分享到朋友圈
		          WeixinJSBridge.on('menu:share:timeline', function(argv){
		              shareTimeline();
		          });
		          // 分享到微博
		          WeixinJSBridge.on('menu:share:weibo', function(argv){
		              shareWeibo();
		          });
		      }, false);
		  }catch(e){}
		
  });
  
  function getJSPParams(){
	  
	 try{ window.AppJsObj.getSynopsis(synopsis);}catch(e){}
	  return synopsis;
  }
  
</script>

</html>
