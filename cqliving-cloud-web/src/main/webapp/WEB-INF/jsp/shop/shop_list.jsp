<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<jsp:include page="/WEB-INF/jsp/common/include.jsp" />
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=${baiduLbsKey}"></script>
		<title>商情</title>
	</head>
	<body>
		<div id="nav">
			<ul>
				<input type="hidden" id="app_id" value="${appId}" />
				<input type="hidden" id="shop_type_id" value="${shopTypeId}" />
				<li class="nav_order">
					<a>
						<label class="shop_condition_label">排序</label>
						<i class="fa fa-sort-desc"></i>
					</a>
					<div class="nav_list">
						<div class="nav_list_bg"></div>
						<ul>
							<li class="active" val="d">离我最近</li>
							<li val="c">人气最高</li>
							<li val="r">热门火爆</li>
						</ul>
					</div>
				</li>
				<li class="nav_area">
					<a>
						<label class="shop_condition_label">区域</label>
						<i class="fa fa-sort-desc"></i>
					</a>
					<div class="nav_list">
						<div class="nav_list_bg"></div>
						<ul>
							<li rid="" class="active">全部</li>
							<c:forEach items="${regions}" var="obj" varStatus="i">
								<li rid="${obj.code}">${obj.name}</li>
							</c:forEach>
						</ul>
					</div>
				</li>
				<li class="nav_type">
					<a>
						<label class="shop_condition_label">分类</label>
						<i class="fa fa-sort-desc"></i>
					</a>
					<div class="nav_list">
						<div class="nav_list_bg"></div>
						<ul>
							<li tid="" class="active">全部</li>
							<c:forEach items="${categories}" var="obj" varStatus="i">
								<li tid="${obj.id}">${obj.name}</li>
							</c:forEach>
						</ul>
					</div>
				</li>
				<li class="nav_search">
					<a><img src="/front/detail/images/search.png"/></a>
<!--       				<div class="nav_list"> -->
<!--        					<div class="nav_list_bg"></div> -->
<!--       				</div> -->
   			 	</li>
			</ul>
		</div>
		<div id="search">
  			<input id="search_ipt" type="search" placeholder=" 输入关键字" />
  			<a id="search_btn">搜索</a> 
  		</div>
		<div class="bu_list"></div>
	</body>
	<script type="text/javascript">
		require(["/resource/js/business/shop/shopList.js"], function(obj) {
			obj.init();
		});
	</script>
</html>