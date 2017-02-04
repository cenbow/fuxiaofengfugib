<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>核销认证</title>
		<style type="text/css">
			table {
				border-collapse: collapse;
				width: 100%;			
			}
			table, td, th {
				border: 1px solid gray;
			}
			.val {
				text-align: right;
			}
			img {
				width: 60px;
				height: 60px;
				border-radius: 50%;
				border: 1px solid #878686;
			}
		</style>
		
		<script type="text/javascript" src="${request.contextPath}/common/scripts/lib/jquery-1.11.3.min.js"></script>
		
	</head>
	<body>
	   <br><br>
		<table>
			<thead>
			    <tr>
				   <th colspan="7">
				        <span style="color:red;">${error_msg }</span>
						<form action="${request.contextPath}/jywtinf/settoken.html" id="clear_form" method="post">
						    <input type="text" name="pwdtoken" value="${param.pwdtoken}" placeholder="请输入核销密码"/>
							<input type="submit" value="核销认证" />
						</form>
					</th>
				</tr>
				
			</thead>
			<tbody>
			    
			</tbody>
		</table>
	</body>
	
</html>