<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="form-group">
    <label class="col-sm-3 control-label no-padding-right" for="templetCode">模板</label>
        <div class="col-sm-9">
        <c:if test="${not empty templetList}">
	      	<select <c:if test="${!flag and (item.parentId eq 0 or item.columnsType eq COLUMNSTYPE3)}"> disabled="disabled" </c:if> name="templetCode" id="templetCode" class="form-control">
			  <option  value="">请选择</option>
			  <c:forEach items="${templetList}" var="templet" varStatus="idx">
	             <option imgUrl="${templet.imageUrl}" value="${templet.templetCode}"
	               <c:if test="${item.templetCode eq templet.templetCode}">selected</c:if>
	              >${templet.name}</option>
	          </c:forEach>
			</select>
			<c:set var="imgflag" value="false"/>  
			<c:forEach items="${templetList}" var="templet" varStatus="idx">
				<c:if test="${item.templetCode eq templet.templetCode}">
					<c:set var="imgflag" value="true"/>
					<span class="ace-thumbnails">
						<a href="${templet.imageUrl}" data-rel="colorbox">
							<img id="img" width="300 px;" alt="模板图片" src="${templet.imageUrl}"> 
						</a>
					</span>
				</c:if>
		    </c:forEach>
		    <c:if test="${!imgflag}">
		    	<span class="ace-thumbnails">
			    	<a href="javascript:;" data-rel="colorbox">
			    		<img id="img" width="300 px;" alt="模板图片" style="display: none;">
			    	</a> 
		    	</span>
		    </c:if>
       </c:if>
       <span id="templet-tip" style="color: red;"></span>
    </div>
</div>