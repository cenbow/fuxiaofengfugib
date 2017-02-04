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
	<title>核销登录</title>
	<meta name="Keywords" content="">
	<meta name="Description" content=""></head>

<style type="text/css">
	table {
		border-collapse: collapse;
		width: 100%;			
	}
	table, td, th {
		border: 1px solid gray;
	}

</style>
<body>
	<p>
		<form action="${request.contextPath}/zjchj_in/login.html" method="post">
			<div>
				业务员编号: <input type="text" name="fname" style="border: 1px solid gray;	width: 80px;line-height:25px;"/>
				密码: <input type="password" name="password" style="border: 1px solid gray;	width:  80px;line-height:25px;" />
			</div>
			<div><input type="submit" value="登录" /></div>
		</form>
		<br>
	<p>
</body>
</html>
