<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
		<meta content="yes" name="apple-mobile-web-app-capable">
		<meta content="black" name="apple-mobile-web-app-status-bar-style">
		<meta content="telephone=no" name="format-detection">
		<meta content="email=no" name="format-detection">
		<title>黄晓明送好礼</title>
		<!-- ${allDistrictTitle[district]} -->
		<style type="text/css">
			body{background: #f7931e;}
		</style>
		
	<script src="${request.contextPath}/common/scripts/lib/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
	<link href="/module/szc/bin-release/css/base.min.css?v=v5" type="text/css" rel="stylesheet" />
	<link href="/module/szc/bin-release/css/flop.min.css?v=v2" type="text/css" rel="stylesheet" />
	
	<script type="text/javascript">
	  var accId = "${accId}";
	</script>
</head>
	<body>
		<div class="flop-main">
		    <p class="cellphone-prompt">(请输入正确的号码以便接收中奖信息)</p>
			<div class="cellphone-number clearfix">
				<input type="text" name="phone" placeholder="输入手机号" value="${weixin_session_user.phone}">
				<a>确定</a>
			</div>
			<div class="see-rule">
				<a><查看规则></a>
			</div>
			
			<ul class="moving-gif">
				<li><img src="/module/szc/bin-release/images/1.gif"/></li>
				<li><img src="/module/szc/bin-release/images/2.gif"/></li>
				<li><img src="/module/szc/bin-release/images/3.gif"/></li>
				<li><img src="/module/szc/bin-release/images/4.gif"/></li>
				<li><img src="/module/szc/bin-release/images/5.gif"/></li>
				<li><img src="/module/szc/bin-release/images/6.gif"/></li>
			</ul>
		</div>
		
		<!--蒙层-->
		<div class="mask-layer" style="display:none"></div>
		
		<!--弹窗 style="display: none;" -->
		<div class="pop-ups pop_nanjing" style="display: none;">
			<div class="pop-ups-container">
				<a class="pop-ups-close"></a>
				<div class="pop-ups-text-1">
					<div class="pop-ups-row">
						<span>1、</span>
						奖品兑换时间：<br />
						2016年9月10日，10点—21点
					</div>
					<div class="pop-ups-row">
						<span>2、</span>
						奖品兑换地点：砂之船（南京）奥莱 2F 客服台
					</div>
					<div class="pop-ups-row">
						<span>3、</span>
						凭中奖短信领取核销，请勿泄露短信内容。
					</div>
				</div>
			</div>
		</div>
		
		<!--弹窗 style="display: none;" -->
		<div class="pop-ups pop_bishan" style="display: none;">
			<div class="pop-ups-container">
				<a class="pop-ups-close"></a>
				<div class="pop-ups-text-1">
					<div class="pop-ups-row">
						<span>1、</span>
						奖品兑换时间：<br />
						9月15日，13:00—16:00
					</div>
					<div class="pop-ups-row">
						<span>2、</span>
						奖品兑换地点：砂之船（璧山）奥莱罗马广场。
					</div>
					<div class="pop-ups-row">
						<span>3、</span>
						凭中奖短信领取核销，请勿泄露短信内容。
					</div>
				</div>
			</div>
		</div>
		
		<!--弹窗-->
		<div class="pop-ups pop_phone_err" style="display: none;">
			<div class="pop-ups-container">
				<a class="pop-ups-close"></a>
				<div class="pop-ups-text-2">
					先输入手机号,<br />
					中奖以后才能获取兑换码哦~
					~<br />
					<input class="pop-ups-num" type="text" name="firstname" placeholder="输入手机号" value="${weixin_session_user.phone}"><br />
					<a class="pop-ups-determine">确定</a>
				</div>
			</div>
		</div>
		
		<!--弹窗-->
		<div class="pop-ups pop_hefei" style="display: none;">
			<div class="pop-ups-container">
				<a class="pop-ups-close"></a>
				<div class="pop-ups-text-1">
					<div class="pop-ups-row">
						<span>1、</span>
						奖品兑换时间：<br />
						2016年9月10日  10：00—21：00
					</div>
					<div class="pop-ups-row">
						<span>2、</span>
						凭中奖兑换码至砂之船（合肥）奥莱1F中庭，在互动大屏输入手机号和密码
					</div>
					<div class="pop-ups-row">
						<span>3、</span>
						持打印好的礼品兑换券至商场1F MICHAEL KORS店铺旁礼品兑换处兑换礼品
					</div>
					<div class="pop-ups-row">
						<span>4、</span>
						每张兑换券限领取一份礼品
					</div>
				</div>
			</div>
		</div>
		
		<!--弹窗-->
		<div class="pop-ups luck_draw_pop" style="display: none;">
			<div class="pop-ups-container">
				<a class="pop-ups-close"></a>
				<div class="pop-ups-text-2">
					<img width="70" src="/module/szc/bin-release/images/img_5.jpg"/><br />
					<span class="text_1">晓明送给您“定制笔记本”</span><br />
					<span class="text_2">您的奖品兑换码是12345678</span><br />
					<span class="text_3">惊喜短信正在发送中</span><br />
					<span class="text_4">也可通过查看规则获悉兑奖详情哦~</span><br />
				</div>
			</div>
		</div>
		
		<!--/聊天模板-->
		<div id="wx_config" style="display: none;">${config}</div>
	</body>
	
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript" charset="utf-8"></script>
    <script src="${request.contextPath}/module/szc/wechat_share.js?v=v3" type="text/javascript" charset="utf-8"></script>
	
	<script type="text/javascript">
	    
	   var district = "${district}";
	   var sessionPhone = "${weixin_session_user.phone}";
	   $(function(){
		   
		   if(sessionPhone){
			   $(".cellphone-prompt,.cellphone-number").hide();
		   }
		   
		   //查看规则
		   $(".see-rule").bind("click",function(){
			   $(".mask-layer").show();
			   
			   if(district == 1){
				   $(".pop_hefei").eq(0).show();
			   }else if(district == 2){
				   $(".pop_nanjing").eq(0).show();
			   }else{
				   $(".pop_bishan").eq(0).show();
			   }
		   });
		   
		   //关闭规则弹层
		   $(".pop-ups-close").bind("click",function(){
			   $(this).closest(".pop-ups").hide();
			   $(".mask-layer").hide();
		   });
		   
		   $(":input[name=phone] + a").bind("click",function(){
			   
			   var phone = $(":input[name=phone]").val();
			   phone = $.trim(phone);
			   
			   if(!phone){
				   $(".mask-layer").show();
				   $(".pop_phone_err").eq(0).show();
				   return;
			   }
			   $(".cellphone-prompt,.cellphone-number").hide();
		   });
		   
		   $(".moving-gif img").bind("click",function(){
			   var phone = $(":input[name=phone]").val();
			   phone = $.trim(phone);
			   if(!phone){
				   phone = $(":input[name=firstname]").val();
				   phone = $.trim(phone);
				   if(!phone){
					   $(".mask-layer").show();
					   $(".pop_phone_err").eq(0).show();
					   return;
				   }
				   luckDraw(phone);
				   return;
			   }
			   luckDraw(phone);
		   });
		   
		   $(".pop-ups-determine").bind("click",function(){
			   
			   var $this = $(this);
			   var phone = $this.parent().find(":input").val();
			   phone = $.trim(phone);
			   if(!phone)return;
			   $this.closest(".pop-ups").hide();
			   $(".cellphone-prompt,.cellphone-number,.mask-layer").hide();
		   });
		   
		   function luckDraw(phone){
			   
			   var url = "/szc/${accId}/luck_draw.html";
			   $.ajax({
				   url:url,
				   data:{phone:phone,district:district},
				   dataType:"JSON",
				   type:"POST",
				   async:true,
				   success:function(result,status){
					   custom(result);
				   },
				   error:function(e){
					  alert(e);
				   }
			   });
		   }
		   
		   function custom(result){
			   $(".mask-layer").show();
			   var pop = $(".luck_draw_pop");
			   pop.show();
			   var img = pop.find("img");
			   var text1 = pop.find(".text_1");
			   var text2 = pop.find(".text_2");
			   var text3 = pop.find(".text_3");
			   var text4 = pop.find(".text_4");
			   if(result.code == -22222){//奖品已抽完
				   text1.text("啊喔～你的礼品被外星人抢走了");
			       text2.text("");text3.text("");text4.text("");
			       img.hide();
			       return;
			   }
               if(result.code == -33333){//已抽奖
            	   text1.text("黄晓明已经给你送过礼啦，");
			       text2.text("想要获取更多惊喜？");
			       text3.text("请前往【砂之船奥莱哦】~");text4.text("");
			       img.hide();
			       return;
			   }
			   if(result.code == -11111){//未开始
				   text1.text("抽奖未开始，请耐心等待");
			       text2.text("");text3.text("");text4.text("");
			       img.hide();   
			       return;
			   }
			   
			   var data = result.data.szcUserAward;
			   
			   if(data.awardCode.indexOf("NOT_AWARD")!=-1){
				   text1.text("啊喔～你的礼品被外星人抢走了");
			       text2.text("");text3.text("");text4.text("");
			       img.hide();   
				   return;
			   }
			   img.attr("img",data.awardImg);
			   img.show();
			   
			   text1.text("黄晓明送给您“"+data.awardName+"”");
		       text2.text("您的奖品兑换码是"+data.convertCode);
		       text3.text("惊喜短信正在发送中");
		       text4.text("也可通过查看规则获悉兑奖详情哦~");
		   }
		   
		   //分享配置
		   configShare();
	   })
	</script>
	
</html>
