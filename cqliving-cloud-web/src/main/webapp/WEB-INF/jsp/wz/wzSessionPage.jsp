<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="wzAppId" value="${sessionScope.session_wz_user_obj.appId }"/>
<c:set var="wzUserToken" value="${sessionScope.session_wz_user_obj.token }"/>
<c:set var="wzUserSessionId" value="${sessionScope.session_wz_user_obj.sessionCode }"/>

<input type="hidden" value="${wzAppId }" name="appId" id="wzAppId">
<input type="hidden" value="${wzUserToken }" name="token" id="wzUserToken">
<input type="hidden" value="${wzUserSessionId }" name="sessionId" id="wzUserSessionId">
<script type="text/javascript">
	
	var wzAppId = '${wzAppId }';
	var wzUserToken = '${wzUserToken }';
	var wzUserSessionId = '${wzUserSessionId }';
</script>