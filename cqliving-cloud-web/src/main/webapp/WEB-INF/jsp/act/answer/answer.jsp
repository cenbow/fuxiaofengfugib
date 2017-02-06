<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/WEB-INF/jsp/common/include.jsp" %>
<meta content="${item.title}" itemprop="name"/>
<meta content="${item.digest }" itemprop="description"/>
<c:if test="${not empty item.actImageUrls[0] }">
	<meta content="${item.actImageUrls[0] }" itemprop="image"/>
</c:if>
<c:if test="${empty item.actImageUrls[0] }">
	<meta content="http://images.cqliving.com/images/icon/${appId }.png" itemprop="image"/>
</c:if>
<title>答题</title>
<style type="text/css">
	.active_common_1 img{
		max-width:100% !important;
		width: 100% !important;
		height:auto !important;
	}
	.active_common_1 .detail_pic_none{
		height: 100px !important;
	} 
	.detail_pic_none {
		height: 100px;
	}
</style>
</head>
<body id="active">
	<div>
		<div class="active_banner">
			<c:set var="shareImg" value=""/>
			<c:if test="${not empty item.actImageUrls }">
				<c:forEach var="it" items="${item.actImageUrls }" varStatus="index">
					<c:if test="${index.index eq 0 }">
						<c:set var="shareImg" value="${it }"/>
					</c:if>
					<c:choose>
						<c:when test="${not empty param.noimg }">
							<div class="detail_pic_none btn_click" imgSrc="${it }">
								<span> ${appName } </span>
								<span class="detail_pic_none_click">点击查看图片</span>
							</div>
						</c:when>
						<c:otherwise>
							<img src="${it }"/>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:if>
			<div class="active_title">${item.title }</div>
			<div class="active_memo">${item.digest }
			</div>
		</div>
		<!--答题未填信息-->
		<div id="answer_content_div"></div>
		
		<div class="active_common active_common_1">
			<div class="active_common_name">
				<span class="name_title"><span>活动内容</span></span>
			</div>
			<p>
				<imgrp:img appName="${appName }" replace="${not empty param.noimg ? 1 : 0}" content="${item.content}"/>
			</p>
		</div>
	</div>
</body>
<script type="text/javascript">
	var tmpParamsObj = [],//临时保存用户每次点击开始答题之前的收集信息。
		isCheckDisabled = "false";//是否已经收集过信息了。在answer_page页面赋值
	require([ 'jquery' ], function($) {
		loginCallBack();
	});
	require([ '/resource/js/common/noImgView.js' ], function(obj) {
		obj.init();
	});
	var basicObj = {appId : '${param.appId}', sessionId : '${param.sessionId}', token : '${param.token}'};
	var sessionObj = "";
	function setSessionToken(params){
		sessionObj = eval("("+params+")");
		basicObj.sessionId = sessionObj.sessionId;
		basicObj.token = sessionObj.token;
		$('#sessionId').val(sessionObj.sessionId);
		$('#token').val(sessionObj.token);
	}
	function loginCallBack(){
		try{
			ZWY_ClOUD.getSessionToken("setSessionToken");
		}catch(e){}
		setTimeout(function(){
			var url = '/act/answer/answer/${actInfoListId}.html?appId=${param.appId}&sessionId=' + basicObj.sessionId + '&token=' + basicObj.token + '&isLoad=true';
			$('#answer_content_div').load(url, {}, function(html){
				//还原用户登录前输入的收集信息。解决用户登录之后还要输入收集信息的烦恼
				if(isCheckDisabled == "false"){//如果登录成功后用户还未收集过信息
					for(var i = 0; i < tmpParamsObj.length; i ++){
						var type = tmpParamsObj[i].type;
						if(type == 'text'){
							$('#' + tmpParamsObj[i].id).val(tmpParamsObj[i].value);
						}else if(type == 'radio'){
							$('#' + tmpParamsObj[i].id).prop("checked", tmpParamsObj[i].value);
						}else if(type == 'select'){
							$('#' + tmpParamsObj[i].id).val(tmpParamsObj[i].value);
						}else if(type == 'checkbox'){
							$('#' + tmpParamsObj[i].id).prop("checked", tmpParamsObj[i].value);
						}
					}
				}
			});
		}, 500);
	}
	
	function answerShare(){
		window.AppJsObj.share();
	}
	//跳转
	function redirectUrl(url){
		url = '${webPath}' + url;
		var obj = {url: url, detailViewType: 8};
		ZWY_ClOUD.redirectUrl(JSON.stringify(obj));
	}
</script>

</html>
