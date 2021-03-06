<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
		<meta content="${obj.name}" itemprop="name"/>
		<meta content="${obj.description}" itemprop="description"/>
		<meta content="${obj.coverImg}" itemprop="image"/>
		<title>${obj.name}</title>
	</head>
	<body>
		<div class="share_top" openurl="${openUrl}">通过${appInfo.name}客户端查看<a id="close_btn"><img src="/front/detail/images/close.png"/></a></div>
		<input type="hidden" id="app_name" value="${appInfo.name}" />
		<input type="hidden" id="shop_id" value="${obj.id}" />
		<input type="hidden" id="shop_lat" value="${obj.lat}" />
		<input type="hidden" id="shop_lng" value="${obj.lng}" />
		<input type="hidden" id="shop_name" value="${obj.name}" />
		<input type="hidden" id="shop_address" value="${obj.address}" />
		<div id="business" class="detail_share">
  			<div class="bu_top">
				<c:choose>
					<c:when test="${not empty obj.coverImg}">
						<img id="cover_img" src="${obj.coverImg}" />
					</c:when>
					<c:otherwise>
						<div class="detail_pic_none btn_click"></div>
					</c:otherwise>
				</c:choose>
				<div class="bu_info_bg"></div>
				<div class="bu_info">
					<div class="bu_info_title">${obj.name}<c:if test="${not empty label}"><span>${label.name}</span></c:if></div>
					<div class="bu_info_memo">${obj.description}</div>
				</div>
				<div class="bu_pic_num_bg"></div>
				<div class="bu_pic_num">${shopImageCount}张</div>
			</div>
			<div class="bu_money">￥<fmt:formatNumber value="${obj.price / 100}" type="number" /></div>
			<div class="bu_info1">
				<div class="bu_info1_addr" id="shop_address_div">
					<img src="/front/detail/images/addr.png" />${obj.address}
				</div>
				<div class="bu_info1_phone">
					<a href="tel:${obj.telephone}">
						<img src="/front/detail/images/phone.png" />
					</a>
				</div>
			</div>
  			<div class="detail_line"></div>
  			<div class="detail_content">
				${obj.content}
			</div>
  			<div class="detail_line"></div>
  			<div class="detail_comment_list">
  				<c:if test="${not empty obj.replyCount and obj.replyCount > 0}">
    				<div class="comment_list_title"><span>评论(${obj.replyCount})</span></div>
   				</c:if>
    			<!-- 评论列表 -->
    			<ul id="comment_list"></ul>
  			</div>
  			<div class="share_link btn_click">
  				<a href="${openUrl}" style="color: #fff">${appInfo.name }里有更多精彩，去看看<img src="${request.contextPath }/front/detail/images/link.png"/></a>
  			</div>
		</div>
	</body>
	<script type="text/javascript">
		require(["/resource/js/business/shop/shopDetailShare.js"], function(obj) {
			obj.init();
		});
	</script>
</html>