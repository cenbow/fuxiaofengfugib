<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="ch">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<title>定制你的时尚大片</title>
		<jsp:include page="common/include.jsp"></jsp:include>
		<script type="text/javascript">
			seajs.use("/module/skp/scripts/index.js?v1", function(obj) {
				var host = "${pageContext.request.requestURL}";
				var index = host.indexOf("/module");
				host = host.substr(0, index);
		    	obj.init(host);
			});
		</script>
	</head>
	<body>
		<div class="home">
			<p class="btn-1">
				<a id="btn_hdxj" href="javascript:void(0);" class="sm-btn fl red-btn">活动细节</a>
				<a id="btn_skp" href="javascript:void(0);" class="sm-btn fr blue-btn">北京SKP</a>
			</p>
			<p class="btn-2"><a id="btn_psssdp" href="javascript:void(0);" class="w md-btn red-btn">制作我的时尚大片</a></p>
			<div class="btn-3">
				<ul class="flex mt20">
					<li class="flex-1 mr5"><a id="btn_grzy" href="javascript:void(0);" class="w md-btn blue-btn">个人主页</a></li>
					<li class="flex-1 ml5"><a id="btn_ssphb" href="javascript:void(0);" class="w md-btn blue-btn">时尚排行榜</a></li>
				</ul>
			</div>
		</div>
		<!--公共遮罩层-->
		<div class="pop_layer" style="display: none;"></div>
		<!--弹层-->
		<div id="no_page" class="pop_con" style="display: none;">
			<span class="close"></span>
 			<p class="tc text-p">对不起<br/>活动已结束！</p>
 			<p class="tc btn-p"><a id="btn_close_popup_1" href="javascript:void(0);" class="sm-btn blue-btn">确定</a></p>
		</div>
		<div id="had_page" class="pop_con" style="display: none;">
			<span class="close"></span>
 			<p class="tc text-p">对不起<br/>活动已结束<br/></p>
 			<p class="tc btn-p"><a id="btn_close_popup_2" href="javascript:void(0);" class="sm-btn blue-btn">确定</a></p>
		</div>
		<div id="wx_config" style="display: none;">${config}</div>
		<input type="hidden" id="user_id" value="${userId}" />
		<input type="hidden" id="acc_id" value="${accId}" />
	</body>
</html>