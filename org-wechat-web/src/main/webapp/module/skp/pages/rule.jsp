<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ch">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>我的主页</title>
		<jsp:include page="common/include.jsp"></jsp:include>
		<script type="text/javascript">
			seajs.use("/module/skp/scripts/rule.js", function(obj) {
				var host = "${pageContext.request.requestURL}";
				var index = host.indexOf("/module");
				host = host.substr(0, index);
		    	obj.init(host);
			});
		</script>
	</head>
	<body>
		<div class="wrap2 clearfix">
			<h2 class="title"><i></i>活动时间：</h2>
			<p></p>
			<i>7月28日-8月24日，8月24日12:00时线上活动正式停止</i>
			<h2 class="title"><i></i>活动奖品：</h2>
			<p></p>
			<i>
				每周奖品：
				<br/>
				第一名奖品：北京SKP 2000元购物卡一张
				<br/>
				第二名奖品：北京SKP 1500元购物卡一张
				<br/>
				第三名奖品：北京SKP 1000元购物卡一张
				<br/>
				每日幸运奖品：
				<br/>
				第一周：YSL 圣罗兰明彩亮颜粉底液 30ml
				<br/>
				第二周：GUERLAIN 珍珠肌透白肤色提亮乳液 30ml
				<br/>
				第三周：LANCOME 水份缘舒缓晚霜 50ml
				<br/>
				第四周：TOM FORD 焕魅妆前乳 30ml
			</i>
			<h2 class="title"><i></i>活动方式：</h2>
			<p></p>
			<i>进入活动页面，点击拍摄时尚大片，拍摄或上传一张个人照片进
				入制作页面；选择不同主题的模板，添加饰品，不同饰品对应不
				同的时尚指数，生成时尚大片；照片生成后可获得基础时尚指数，
				分享至朋友圈，邀请好友助力增加时尚指数，每位好友每天助力
				一次，每次助力10分；个人时尚指数由基本分数加上好友助力分
				数组成。每个用户只能拍摄或上传一张照片，进行时尚指数排名，
				若连续拍摄，前一张照片及时尚指数会被清除，只保留最后一张
				照片的时尚指数进行排名
			</i>
			<h2 class="title"><i></i>中奖方式：</h2>
			<p></p>
			<i>时尚指数前十名用户将会进入排行榜，7月28日-8月24日期间，
				排行榜每周一12:00清除上周数据，获取上周前三名中奖用户。
				数据清除后点击“中奖用户”按钮，即可查看中奖名单；8月24
				日12:00线上活动结束排行榜数据静止。
			</i>
			<p></p>
			<i>排行榜每周前三名可获得由北京SKP提供的奖品一份；
				每日抽取一名幸运中奖用户，中奖名单将在北京SKP官方微信公
				布，回复“我要奖品”或点击微信下方菜单栏“我要奖品”即可
				查看中奖名单。
			</i>
			<h2 class="title"><i></i>奖品兑换：</h2>
			<p></p>
			<i>兑换时间：7月29日-9月5日（10:00-22:00）</i>
			<p></p>
			<i>兑换地点：北京市朝阳区大望桥东北角华贸中心内北京SKP 4F卡务中心</i>
			<div class="flex">
				<p class="flex-1">
					<a id="btn_back_to_idx" href="javascript:void(0);" class="w blue-btn md-btn">返回</a>
				</p>
			</div>
		</div>
<!-- 		<div class="wrap2 clearfix"> -->
<!-- 			<h2 class="title"><i></i>活动时间：</h2> -->
<!-- 			<p></p> -->
<!-- 			<h2 class="title"><i></i>活动方式：</h2> -->
<!-- 			<p></p> -->
<!-- 			<h2 class="title"><i></i>活动奖品：</h2> -->
<!-- 			<p></p> -->
<!-- 			<h2 class="title"><i></i>注意事项：</h2> -->
<!-- 			<p></p> -->
<!-- 			<div class="flex"> -->
<!-- 				<p class="flex-1"> -->
<!-- 					<a id="btn_back_to_idx" href="javascript:void(0);" class="w blue-btn md-btn">返回</a> -->
<!-- 				</p> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<div id="wx_config" style="display: none;">${config}</div>
		<input type="hidden" id="user_id" value="${userId}" />
		<input type="hidden" id="acc_id" value="${accId}" />
	</body>
</html>