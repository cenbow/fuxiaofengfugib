<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ch">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>排行榜</title>
		<jsp:include page="common/include.jsp"></jsp:include>
		<script type="text/javascript">
			seajs.use("/module/skp/scripts/rank.js", function(obj) {
				var host = "${pageContext.request.requestURL}";
				var index = host.indexOf("/module");
				host = host.substr(0, index);
		    	obj.init(host);
			});
		</script>
	</head>
	<body>
		<div class="wrap2 clearfix">
			<h2 class="title"><i></i>奖品展示&nbsp;&nbsp;7月28日-8月3日 中午12:00</h2>
			<p class="jp-con">
				第一名奖品：<em>北京SKP 2000元购物卡</em>一张<br/>
				第二名奖品：<em>北京SKP 1500元购物卡一张</em><br/>
				第三名奖品：<em>北京SKP 1000元购物卡一张</em><br/>
				每日幸运奖品：<em>YSL 圣罗兰明彩亮颜粉底液 30ml</em><br/>
				兑换地点：北京市朝阳区大望桥东北角华贸中心内 <br/>
　　　　　北京SKP 4F卡务中心<br />
				奖品有限，排名相同者按上榜顺序为准哦</br>
				兑奖须知：<em>此次活动奖品不可叠加，上期中奖者如再次登上排行榜前三位，奖品发放按名次顺延</em>
			</p>
			<p class="jp-img">
				<img src="/module/skp/front/images/jp3.png" />
				<img src="/module/skp/front/images/jp2.png"/>
			</p>
			<h2 class="title"><i></i>个人分数</h2>
			<div class="clearfix">
				<table class="grade_tabs" cellpadding="0" cellspacing="0" border="0">
					<tr>
				    	<th>名次</th>
				    	<th>头像</th>
				    	<th>昵称</th>
				    	<th>分数</th>
				  	</tr>
					<c:forEach items="${list}" var="user" varStatus="i">
					  	<tr ${i.count > 3 ? 'class="bg"' : ''}>
						    <td>第${i.count}名</td>
						    <td><img src="${user.headImgUrl}" /></td>
						    <td>${user.nickName}</td>
						    <td>${user.totalScore}分</td>
					  	</tr>
					</c:forEach>
				</table>
			</div>
			<ul class="clearfix mt10">
  				<li><a id="btn_invite_help" href="javascript:void(0);" class="w md-btn blue-btn">邀请好友助力</a></li>
  				<li class="flex mt10">
    				<p class="flex-1 mr5"><a id="btn_winners_1" href="javascript:void(0);" class="w md-btn blue-btn">中奖用户</a></p>
    				<p class="flex-1 ml5"><a id="btn_back" href="javascript:void(0);" class="w md-btn blue-btn">返回</a></p>
  				</li>
			</ul>
		</div>
		<!--公共遮罩层-->
		<div class="pop_layer" style="display: none;"></div>
		<!-- 分享弹层 -->
		<div id="share_popup" class="pop-tip" style="display: none;">
			<p class="arrow"></p>
			<p class="tip-text">分享您的时尚大片<br/>让你的好友来为你助力吧</p>
		</div>
		<!-- 活动未结束弹层 -->
		<div id="unfinish" class="pop_con" style="display: none;">
			<span class="close"></span>
 			<p class="tc text-p">本期活动还未结束<br/>将于下周一12:00过后<br/>公布中奖名单</p>
 			<p class="tc btn-p"><a href="javascript:void(0);" class="sm-btn blue-btn">确定</a></p>
		</div>
		<div id="wx_config" style="display: none;">${config}</div>
		<input type="hidden" id="user_id" value="${userId}" />
		<input type="hidden" id="acc_id" value="${accId}" />
	</body>
</html>