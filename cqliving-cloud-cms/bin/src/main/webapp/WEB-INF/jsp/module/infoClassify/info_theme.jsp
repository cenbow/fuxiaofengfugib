<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div>专题分类</div>
<div class="col-sm-8 radio info_theme_checkbox">
   <c:forEach items="${infoThemes }" var="infotheme">
      <label> 
		<input class="ace radio-2" type="radio" name="infoThemeId" value="${infotheme.id }" /> 
		<span class="lbl">${infotheme.name }</span>
	  </label>
   </c:forEach>
	 
</div>