<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
		<meta content="yes" name="apple-mobile-web-app-capable">
		<meta content="black" name="apple-mobile-web-app-status-bar-style">
		<meta content="telephone=no" name="format-detection">
		<meta content="email=no" name="format-detection">
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
	</head>
	<body>
	   <br><br>
		<table>
			<thead>
			    <tr>
				   <th colspan="7">
				        <span style="color:red;">${error_msg }</span>
						<form action="${request.contextPath}/check_verify.html" method="post">
						    <input type="text" name="verify" value="${param.verify}" placeholder="请输入核销密码"/>
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