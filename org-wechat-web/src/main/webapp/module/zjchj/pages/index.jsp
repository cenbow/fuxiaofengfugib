<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
	<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
	<meta HTTP-EQUIV="expires" CONTENT="0">
	<meta id="viewport" name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<title>正佳吃货节</title>
	<meta name="Keywords" content="">
	<meta name="Description" content=""></head>
	<script src="${request.contextPath}/module/zjchj/front/js/libs/jquery.1.8.2.min.js"></script>
	<script src="${request.contextPath}/module/zjchj/front/js/common.js"></script>
	<link href="${request.contextPath}/module/zjchj/front/css/animate.min.css" rel="stylesheet">
	<link href="${request.contextPath}/module/zjchj/front/css/common.css" rel="stylesheet">
	<link href="${request.contextPath}/module/zjchj/front/css/fn.css" rel="stylesheet">
<body>
	<div class="index">
		<div id="bag_btn" class="bag"><img src="${request.contextPath}/module/zjchj/front/images/bag1.png"></div>
		<div class="bg-img">
			<img src="${request.contextPath}/module/zjchj/front/images/index-bg.jpg">
			<div class="index-text"><img src="${request.contextPath}/module/zjchj/front/images/index-text.png"></div>
			<div class="eat-scroll">
				<ul class="eat-list">
					<c:forEach items="${goods}" var="obj">
						<li class="on">
							<div>
								<span class="front"><img src="${request.contextPath}/module/zjchj/front/images/eat-bg.png"><strong>吃货爆款</strong></span>
								<span class="back"><img src="${request.contextPath}/module/zjchj/front/images/foods/${obj.image}"></span>
								<img src="${request.contextPath}/module/zjchj/front/images/eat-bg.png">
							</div>
							<c:choose>
								<c:when test="${fn:length(obj.name) eq 7}"><p><strong>${obj.name}</strong></p></c:when>
								<c:when test="${fn:length(obj.name) gt 7}"><p><em>${obj.name}</em></p></c:when>
								<c:otherwise><p>${obj.name}</p></c:otherwise>
							</c:choose>
						</li>
					</c:forEach>
					<c:forEach begin="1" end="${unlightGoodsSize}" step="1">
						<li>
							<div>
								<span class="front"><img src="${request.contextPath}/module/zjchj/front/images/eat-bg.png"><strong>吃货爆款</strong></span>
								<span class="back"><img src="${request.contextPath}/module/zjchj/front/images/eat01.png"></span>
								<img src="${request.contextPath}/module/zjchj/front/images/eat-bg.png">
							</div>
							<p>快来点亮我</p>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="touch-tip"><img src="${request.contextPath}/module/zjchj/front/images/touch.png"></div>
			<div class="btn" id="fuli_btn_div">
				<div level="1" class="btn01 <c:if test="${goodsSize ge primaryFoodNum}">on</c:if>"><span><img src="${request.contextPath}/module/zjchj/front/images/btn01.png"></span></div>
				<div level="2" class="btn02 <c:if test="${goodsSize ge intermediateFoodNum}">on</c:if>"><span><img src="${request.contextPath}/module/zjchj/front/images/btn02.png"></span></div>
				<div level="3" class="btn03 <c:if test="${goodsSize ge advancedFoodNum}">on</c:if>"><span><img src="${request.contextPath}/module/zjchj/front/images/btn03.png"></span></div>
				<div level="4" class="btn04 <c:if test="${goodsSize ge extremeFoodNum}">on</c:if>"><span><img src="${request.contextPath}/module/zjchj/front/images/btn04.png"></span></div>
			</div>
		</div>		
	</div>
	<div class="goods-pup" <c:if test="${empty erweima_saoguola}">style="display: none;"</c:if>>
		<div class="mid">
			<div>
				<img src="${request.contextPath}/module/zjchj/front/images/goods-no.png">
				<a id="close_pup_btn" href="javascript:void(0);"><img src="${request.contextPath}/module/zjchj/front/images/goods-no-btn.png"></a>
			</div>
<!-- 			<a class="close"><img src="${request.contextPath}/module/zjchj/front/images/close-pup.png"></a> -->
		</div>
	</div>
	<div class="no-data">弹窗提示!</div>
	<div class="no-html"><img src="${request.contextPath}/module/zjchj/front/images/data-no.png" alt="">对不起，没有数据</div>
	<div class="no-html"><img src="${request.contextPath}/module/zjchj/front/images/data-yes.png" alt="">成功</div>
	<div class="loding"><img src="${request.contextPath}/module/zjchj/front/images/loading.png" alt=""></div>
	<!-- share -->
	<div id="wx_config" style="display: none;">${config}</div>
</body>
<script type="text/javascript">
	var accId = "${accId}";

	$(function(){
		//UI begin
// 		$(".eat-list li").on("click",function(){
// 			$(this).addClass("on")
// 		})

		var bagBg = null;
		var bagNum = 1;

		function bag() {
			bagNum++;
			if (bagNum % 2 == 1) {
				$(".bag img").eq(0).hide();
				$(".bag img").eq(1).show();
			} else {
				$(".bag img").eq(0).show();
				$(".bag img").eq(1).hide();
			};
			bagBg = setTimeout(function() {
				bag()
			}, 500)
		}
		// bag()
		//滚动到哪儿
// 		$(".eat-scroll").scrollTop(188)
		//UI end
		
		//share
		configShare();
		
		//锦囊
		$("#bag_btn").click(function() {
			//去活动规则
			location.href = "/zjchj/" + accId + "/detail/rule.html";
		});
		
		//点击图标
		$(".eat-list li").on("click",function(){
			if ($(this).hasClass("on")) {
				//去活动规则
				location.href = "/zjchj/" + accId + "/detail/rule.html";
			} else {
				//去爆款推荐
				location.href = "/zjchj/" + accId + "/detail/goods.html";
			}
		})
		
		//点击福利按钮
		$("#fuli_btn_div div[level]").click(function() {
			if (!$(this).hasClass("on")) {
				//去活动规则
				location.href = "/zjchj/" + accId + "/detail/rule.html";
			} else {
				var level = $(this).attr("level");
				location.href = "/zjchj/" + accId + "/fuli/" + level + ".html";
			}
		});
		
		//关闭弹层
		$("#close_pup_btn").click(function() {
			$(".goods-pup").hide();
		});
		
	});
</script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/module/zjchj/js/wechat_share.js?v=v4" type="text/javascript" charset="utf-8"></script>
</html>