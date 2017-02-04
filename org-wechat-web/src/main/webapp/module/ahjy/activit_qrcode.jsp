<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/pages/taglibs.jsp"%>
<!doctype html>
<html lang="ch">
<head>
	<meta id="viewport" name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
	<title>艾赫金源</title>
</head>
<body>
	<div class="">
		<img class="" src="${activity_qrcode }" />
	</div>
	<input type="hidden" id="accId" name="accId" value="${accId}">
</body>
</html>