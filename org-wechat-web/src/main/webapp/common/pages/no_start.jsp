<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="ch">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta id="viewport" name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<meta content="telephone=no" name="format-detection" />
	<title>活动还未开始！</title>
	<style>
	        .banner_bg img {
	                width: 100%;
	                display: block;
	        }
	        img {
	                vertical-align: middle;
	                border: 0px none;
	        }
			.banner_bg b {
				position: absolute; left:50%; Top:35%; margin-top: -68px; z-index: 1; margin-left: -150px; width: 300px;text-align:left;font-size : 1em;color : #f00;
			}
	</style>
</head>
<body>
	<div class="banner_bg"><img src="/common/pages/images/no_start.png" /><br>
		<b>活动开始时间：<%=request.getSession().getAttribute("START_TIME")%></b>
	</div>
</body>
</html>