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
	<div class="detail">
		<b class="return" onclick="javascript: history.back(-1);"><img src="${request.contextPath}/module/zjchj/front/images/rerutn.png"></b>
		<h3><img src="${request.contextPath}/module/zjchj/front/images/detail-tit.png"></h3>
		<div class="silder">
			<div class="hd">
				<ul>
					<li <c:if test="${tab eq 'rule'}">class="on"</c:if>><span><em>活动规则</em></span></li>
					<li <c:if test="${tab eq 'goods'}">class="on"</c:if>><span><em>爆款推荐</em></span></li>
					<li <c:if test="${tab eq 'my'}">class="on"</c:if>><span><em>我的奖品<i></i></em></span></li>
				</ul>
			</div>
			<div class="bd">
				<div class="rules">
					<div class="scroll">
						<div class="scroll-m">
							<dl>
								<dt>活动规则：</dt>
								<dd><b>1.</b>关注正佳广场微信公众号即可参与“正佳吃货节”；</dd>
								<dd><b>2.</b>在指定的餐饮商铺内消费“吃货爆款”菜品，并通过微信扫描消费小票下端的二维码，即可点亮“吃货爆款”；</dd>
								<dd><b>3.</b>集满指定数量的“吃货爆款”即可参与抽奖，赢取“吃货福利”；</dd>
								<dd><b>4.</b>每一款“吃货爆款”只能被点亮一次，重复点亮无效；</dd>
								<dd><b>5.</b>选择兑换福利后，不消耗对应的吃货爆款，可积累用于下一次抽奖；</dd>
								<dd><b>6.</b>活动时间：2016.11.1-12.17 <br>兑奖有效期：2016.11.1-12.31<br>兑奖地点：正佳广场一楼西北服务台</dd>
							</dl>
							<div class="goods-det-new">
								<div class="tit">吃货福利：</div>
								<div class="text">
									<div><span>点亮<strong>三个爆款菜品</strong>，</span><p>赢取乱打秀门票二张</p></div>
									<div><span>点亮<strong>五个爆款菜品</strong>，</span><p>赢取¥200美食卡一张<br>正佳海洋馆及乱打秀门票各二张</p></div>
									<div><span>点亮<strong>七个爆款菜品</strong>，</span><p>赢取¥2000美食卡一张。</p></div>
									<div><span>点亮<strong>二十个爆款菜品</strong>，</span><p>赢取¥5000美食卡一张</p></div>
								</div>
							</div>
							<%-- <div class="goods-det">
								<img src="${request.contextPath}/module/zjchj/front/images/goods-det.png">
								<div class="text">
									<div><span>点亮<strong>四个爆款菜品</strong>，</span><p>赢取飞扬电影票一张</p></div>
									<div><span>点亮<strong>五个爆款菜品</strong>，</span><p>赢取200元美食卡一张<br>正佳海洋馆及乱打秀门票各二张</p></div>
									<div><span>点亮<strong>六个爆款菜品</strong>，</span><p>赢取2000元美食卡一张。</p></div>
								</div>
							</div> --%>
							<dl>
								<dd>*本活动最终解释权归正佳广场所有</dd>
							</dl>
						</div>
					</div>
				</div>
				<div class="products">
					<div class="products-tit"><span>店铺名称</span><span>爆款菜单</span><span>店铺地址</span></div>
					<div class="scroll1">
						<div class="scroll1-m">
							<ul class="product1">
								<c:forEach items="${shopInfoDtos}" var="obj">
									<li>
										<div class="name"><img src="${request.contextPath}/module/zjchj/front/images/shop/${obj.shopLogo}"><strong>${obj.shopName}</strong></div>
										<div class="desc">
											<strong>爆款菜单:</strong>
											<!-- <span>南宋一品鸡/什锦老鸭煲/西施凤尾虾</span> -->
											<c:forEach items="${obj.goodsInfos}" var="g">
												<span>${g.name}</span>
											</c:forEach>
										</div>
										<div class="address"><strong>店铺地址：</strong><span>${obj.shopAddr}</span></div>
									</li>
								</c:forEach>
							</ul>
						</div>
						<div class="scroll1-bar"><i></i></div>
					</div>
				</div>
				<div class="my-goods">
					<i class="people"><img src="${request.contextPath}/module/zjchj/front/images/people.png"></i>
					<div class="scroll">
						<div class="scroll-m">
							<ul>
								<c:forEach items="${userAwards}" var="obj">
 									<c:choose>
 										<c:when test="${obj.level eq 4}">
 											<li class="last-good <c:if test="${isExpired}">gray</c:if>">
												<c:if test="${obj.isReward eq 1}">
													<img src="${request.contextPath}/module/zjchj/front/images/goods-use.png">
												</c:if>
												<c:if test="${isExpired}">
													<img src="${request.contextPath}/module/zjchj/front/images/goods-none.png">
												</c:if>
												<span class="level level${obj.level}"><img src="${request.contextPath}/module/zjchj/front/images/goods-bg${obj.level}.png"></span>
												<b>${levelMap[obj.level]}<br>吃货福利</b>
												<div>
													<strong><em>${obj.awardName}</em></strong>
													<c:if test="${obj.isReward eq 0 && !isExpired}">
														<span><em>兑奖码：${obj.rewardPwd}</em></span>
													</c:if>
													<p>兑奖期限：2016.11.1-<fmt:formatDate value="${expiryDate}" pattern="MM.dd" /></p>
													<p>兑奖地点：正佳广场一楼西北服务台</p>
												</div>
											</li>
										</c:when>
										<c:when test="${obj.isReward eq 1}">
											<li class="gray">
												<img src="${request.contextPath}/module/zjchj/front/images/goods-use.png">
												<span class="level level${obj.level}"><img src="${request.contextPath}/module/zjchj/front/images/goods-bg${obj.level}.png"></span>
												<b>${levelMap[obj.level]}<br>吃货福利</b>
												<div>
													<strong>${obj.awardName}</strong>
													<p>兑奖期限：2016.11.1-<fmt:formatDate value="${expiryDate}" pattern="MM.dd" /></p>
													<p>兑奖地点：正佳广场一楼西北服务台</p>
												</div>
											</li>
										</c:when>
										<c:when test="${isExpired}">
											<li class="gray">
												<img src="${request.contextPath}/module/zjchj/front/images/goods-none.png">
												<span class="level level${obj.level}"><img src="${request.contextPath}/module/zjchj/front/images/goods-bg${obj.level}.png"></span>
												<b>${levelMap[obj.level]}<br>吃货福利</b>
												<div>
													<strong>${obj.awardName}</strong>
													<p>兑奖期限：2016.11.1-<fmt:formatDate value="${expiryDate}" pattern="MM.dd" /></p>
													<p>兑奖地点：正佳广场一楼西北服务台</p>
												</div>
											</li>
										</c:when>
										<c:otherwise>
											<li>
												<span class="level level${obj.level}"><img src="${request.contextPath}/module/zjchj/front/images/goods-bg${obj.level}.png"></span>
												<b>${levelMap[obj.level]}<br>吃货福利</b>
												<div>
													<strong>${obj.awardName}</strong>
													<span><em>兑奖码：${obj.rewardPwd}</em></span>
													<p>兑奖期限：2016.11.1-<fmt:formatDate value="${expiryDate}" pattern="MM.dd" /></p>
													<p>兑奖地点：正佳广场一楼西北服务台</p>
												</div>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="no-data">弹窗提示!</div>
	<div class="no-html"><img src="${request.contextPath}/module/zjchj/front/images/data-no.png" alt="">对不起，没有数据</div>
	<div class="no-html"><img src="${request.contextPath}/module/zjchj/front/images/data-yes.png" alt="">成功</div>
	<div class="loding"><img src="${request.contextPath}/module/zjchj/front/images/loading.png" alt=""></div>
	<!-- share -->
	<div id="wx_config" style="display: none;">${config}</div>
</body>
<script src="${request.contextPath}/module/zjchj/front/js/libs/jquery.SuperSlide.2.1.1.js"></script>
<script type="text/javascript">
	var accId = "${accId}";

	jQuery(".silder").slide({trigger:"click"});
	$(function(){
		var topImg = new Image();  
		topImg.src = "${request.contextPath}/module/zjchj/front/images/detail-tit.png"; 
		if(topImg.complete){
		   $(".silder .bd>div").height($(window).height()-$(".detail h3").height()-72-55)
		}else{
		   topImg.onload = function(){
			   $(".silder .bd>div").height($(window).height()-$(".detail h3").height()-72-55)
		   };
		   topImg.onerror = function(){
		     window.alert('加载失败，请刷新');
		   };
		};  


		$(".scroll1-m").on("touchmove",function(){
			var num=$(".scroll1-m").scrollTop()/($(".product1").height()-$(".scroll1-m").height());
			console.log(num)
			$(".scroll1-bar i").css("top",num*100+"%")
		})
		$(".scroll1-m").on("touchend",function(){
			var num=$(".scroll1-m").scrollTop()/($(".product1").height()-$(".scroll1-m").height());
			console.log(num)
			$(".scroll1-bar i").css("top",num*100+"%")
		})
		$(".scroll1-m").on("scroll",function(){
			var num=$(".scroll1-m").scrollTop()/($(".product1").height()-$(".scroll1-m").height());
			console.log(num)
			$(".scroll1-bar i").css("top",num*100+"%")
		})



		// $(".scroll1-m").on("touchmove",function(){
		// 	var num=$(".scroll1-m").scrollTop()/($(".product2").height()-$(".scroll1-m").height());
		// 	console.log(num)
		// 	$(".scroll1-bar i").css("top",num*100+"%")
		// })
		// $(".scroll1-m").on("touchend",function(){
		// 	var num=$(".scroll1-m").scrollTop()/($(".product2").height()-$(".scroll1-m").height());
		// 	console.log(num)
		// 	$(".scroll1-bar i").css("top",num*100+"%")
		// })
		// $(".scroll1-m").on("scroll",function(){
		// 	var num=$(".scroll1-m").scrollTop()/($(".product2").height()-$(".scroll1-m").height());
		// 	console.log(num)
		// 	$(".scroll1-bar i").css("top",num*100+"%")
		// })
		
		configShare();

	})
</script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript" charset="utf-8"></script>
<script src="${request.contextPath}/module/zjchj/js/wechat_share.js?v=v4" type="text/javascript" charset="utf-8"></script>
</html>