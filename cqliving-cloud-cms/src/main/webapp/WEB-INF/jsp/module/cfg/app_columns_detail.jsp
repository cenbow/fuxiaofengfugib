<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="栏目管理|/module/columns/app_columns_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action=
			        <c:if test="${empty item.id}">"${pageContext.request.contextPath}/module/columns/app_columns_add.html"</c:if>
			        <c:if test="${not empty item.id}">"${pageContext.request.contextPath}/module/columns/app_columns_update.html"</c:if>
		        >
		        <div class="col-md-12 col-lg-8">
	            	<input type="hidden" name="id" value="${item.id}" />
	            	<input type="hidden" name="parentId" value="${item.parentId}" />
	            	<input type="hidden" name="parentCode" value="${item.parentCode}" />
                    <input name="appId" id="appId" type="hidden" value="${item.appId}">
                    <input type="hidden" id="imageName" name="imageName"  value="${item.imageName}">
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="columnsType">栏目类型</label>
                        <div class="col-sm-9">
                           	<select <c:if test="${item.parentId eq 0 or item.columnsType eq COLUMNSTYPE3}"> disabled="disabled" </c:if> name="columnsType" id="columnsType" class="form-control columnsType">
								<c:forEach items="${allColumnsTypes}" var="obj" varStatus="idx">
                                  <option value="${obj.key}" <c:if test="${obj.key eq item.columnsType or (empty item.columnsType and idx.first)}">selected</c:if>>${obj.value}</option>
                               </c:forEach>
							 </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-3 control-label no-padding-right" for="name">栏目名称</label>
                            <div class="col-sm-9">
                            <input type="text" class="form-control" id="name" name="name" maxlength="50" placeholder="请输入栏目名称"  value="${item.name}">
                        </div>
                    </div>
                    
                    
                    <div id="upload-div" >
	                    <div class="form-group">
	                      	<label class="col-sm-3 control-label no-padding-right" for="imageUrl">栏目图标选中(48*48px)</label>
	                        <div class="col-sm-9">
	                   			<div id="img_upload48" title="点击上传（选中48 px * 48 px）">
		                   			<i class="icon-cloud-upload"></i>
		                        </div>
		                        <div id="imgView48">
		                             <ul class="ace-thumbnails">
		                             <c:if test="${not empty item.imageUrl}">
		                                 <li style="width:48px;height:48px;">
											<a href="${item.imageUrl}2.png" data-rel="colorbox">
										 		<img alt="2倍选中" style="width:48px;height:48px;" src="${item.imageUrl}2.png">
										 	</a>
											<div class="tools tools-top">
												<a href="javascript:;">
													<i class="icon-remove red"></i>
												</a>
											</div>
										 </li>
									 </c:if>
		                             </ul>
		                        </div>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                      	<label class="col-sm-3 control-label no-padding-right" for="imageUrl">栏目图标未选中(48*48px)</label>
	                        <div class="col-sm-9">
	                   			<div id="img_upload48-0" title="点击上传（未选中48 px * 48 px）">
		                   			<i class="icon-cloud-upload"></i>
		                        </div>
		                        <div id="imgView48-0">
		                             <ul class="ace-thumbnails">
		                             <c:if test="${not empty item.imageUrl}">
		                                 <li style="width:48px;height:48px;" >
		                                 	<a href="${item.imageUrl}20.png" data-rel="colorbox">
										 	<img alt="2倍未选中" style="width:48px;height:48px;" src="${item.imageUrl}20.png">
										 	</a>
											<div class="tools tools-top">
												<a href="javascript:;">
													<i class="icon-remove red"></i>
												</a>
											</div>
										 </li>
									 </c:if>
		                             </ul>
		                        </div>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                      	<label class="col-sm-3 control-label no-padding-right" for="imageUrl">栏目图标选中(72*72px)</label>
	                        <div class="col-sm-9">
	                   			<div id="img_upload72" title="点击上传（72 px * 72 px）">
		                   			<i class="icon-cloud-upload"></i>
		                        </div>
		                        <div id="imgView72">
		                             <ul class="ace-thumbnails">
		                             <c:if test="${not empty item.imageUrl}">
		                                 <li style="width:72px;height:72px;">
		                                 	<a href="${item.imageUrl}3.png" data-rel="colorbox">
										 		<img alt="3倍选中" style="width:72px;height:72px;" src="${item.imageUrl}3.png">
										 	</a>
											<div class="tools tools-top">
												<a href="javascript:;">
													<i class="icon-remove red"></i>
												</a>
											</div>
										</li>
									 </c:if>
		                             </ul>
		                        </div>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                      	<label class="col-sm-3 control-label no-padding-right" for="imageUrl">栏目图标未选中(72*72px)</label>
	                        <div class="col-sm-9">
	                   			<div id="img_upload72-0" title="点击上传（72 px * 72 px）">
		                   			<i class="icon-cloud-upload"></i>
		                        </div>
		                        <div id="imgView72-0">
		                             <ul class="ace-thumbnails">
		                             <c:if test="${not empty item.imageUrl}">
		                                 <li style="width:72px;height:72px;">
										 	<a href="${item.imageUrl}30.png" data-rel="colorbox">
										 		<img alt="3倍未选中" style="width:72px;height:72px;" src="${item.imageUrl}30.png">
										 	</a>
											<div class="tools tools-top">
												<a href="javascript:;">
													<i class="icon-remove red"></i>
												</a>
											</div>
										</li>
									 </c:if>
		                             </ul>
		                        </div>
		                    	<span id="img-span-tip" style="color: red;"></span>
	                        </div>
	                    </div>
                        <input type="hidden" id="imageUrl" name="imageUrl" value="${item.imageUrl}">
                    </div>
<!--                     <div class="form-group"> -->
<!--                         <label class="col-sm-3 control-label no-padding-right" for="status">前台显示</label> -->
<!--                         <div class="col-sm-9"> -->
<%--                            	<select <c:if test="${item.parentId eq 0 or item.columnsType eq COLUMNSTYPE3}"> disabled="disabled" </c:if> name="status" id="status" class="form-control"> --%>
<%-- 								<c:forEach items="${allStatuss}" var="obj" varStatus="idx"> --%>
<%--                                   <option value="${obj.key}" <c:if test="${obj.key eq item.status or (empty item.status and idx.first)}">selected</c:if>>${obj.value}</option> --%>
<%--                                </c:forEach> --%>
<!-- 							 </select> -->
<!--                         </div>         -->
<!--                     </div> -->
                    <%-- <div id="wl" <c:if test="${empty item.columnsType or (not empty item.columnsType and item.columnsType!=COLUMNSTYPE2) or item.columnsType==COLUMNSTYPE3}"> style="display: none;" </c:if> > --%>
                    <div id="wl" <c:if test="${empty item.columnsType or (not empty item.columnsType and item.columnsType eq COLUMNSTYPE0)}"> style="display: none;" </c:if> >
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="columnsUrl">栏目URL</label>
	                        <div class="col-sm-9">
	                            <%-- <input type="text" <c:if test="${item.parentId eq 0 or item.columnsType eq COLUMNSTYPE3}"> disabled="disabled" </c:if> class="form-control" id="columnsUrl" name="columnsUrl" maxlength="255" placeholder="请输入栏目URL"  value="${item.columnsUrl}"> --%>
	                            <input type="text" <c:if test="${item.parentId eq 0}"> disabled="disabled" </c:if> class="form-control" id="columnsUrl" name="columnsUrl" maxlength="255" placeholder="请输入栏目URL"  value="${item.columnsUrl}">
	                            <span id="url-tip" style="color: red;"></span>
	                        </div>
	                    </div>
                    </div>
                    <div id="myDefinition" <c:if test="${(not empty item.columnsType and item.columnsType!=COLUMNSTYPE0) or item.columnsType==COLUMNSTYPE3}"> style="display: none;" </c:if>>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="commentType">允许评论</label>
	                        <div class="col-sm-9">
	                           	<select <c:if test="${item.parentId eq 0 or item.columnsType eq COLUMNSTYPE3}"> disabled="disabled" </c:if> name="commentType" id="commentType" class="form-control">
									<c:forEach items="${allCommentTypes}" var="obj" varStatus="idx">
	                                  <option value="${obj.key}" <c:if test="${obj.key eq item.commentType or (empty item.commentType and idx.first)}">selected</c:if>>${obj.value}</option>
	                               </c:forEach>
								 </select>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="validateType">评论是否需审核</label>
	                        <div class="col-sm-9">
	                           	<select <c:if test="${item.parentId eq 0 or item.columnsType eq COLUMNSTYPE3}"> disabled="disabled" </c:if> name="validateType" id="validateType" class="form-control">
									<c:forEach items="${allValidateTypes}" var="obj" varStatus="idx">
	                                  <option value="${obj.key}" <c:if test="${obj.key eq item.validateType or (empty item.validateType and idx.first)}">selected</c:if>>${obj.value}</option>
	                               </c:forEach>
								 </select>
	                        </div>
	                    </div>
	                    <%-- modify by yuwu 20160715未使用，暂时注释掉 --%>
	                    <%-- <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="viewDate">列表显示日期</label>
	                        <div class="col-sm-9">
	                           	<select <c:if test="${item.parentId eq 0 or item.columnsType eq COLUMNSTYPE3}"> disabled="disabled" </c:if> name="viewDate" id="viewDate" class="form-control">
									<c:forEach items="${allViewDates}" var="obj" varStatus="idx">
	                                  <option value="${obj.key}" <c:if test="${obj.key eq item.viewDate or (empty item.viewDate and idx.first)}">selected</c:if>>${obj.value}</option>
	                               </c:forEach>
								 </select>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="viewCountType">显示浏览数</label>
	                        <div class="col-sm-9">
	                           	<select <c:if test="${item.parentId eq 0 or item.columnsType eq COLUMNSTYPE3}"> disabled="disabled" </c:if> name="viewCountType" id="viewCountType" class="form-control">
									<c:forEach items="${allViewCountTypes}" var="obj" varStatus="idx">
	                                  <option value="${obj.key}" <c:if test="${obj.key eq item.viewCountType or (empty item.viewCountType and idx.first)}">selected</c:if>>${obj.value}</option>
	                               </c:forEach>
								 </select>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="viewReplyCount">显示评论数</label>
	                        <div class="col-sm-9">
	                           	<select <c:if test="${item.parentId eq 0 or item.columnsType eq COLUMNSTYPE3}"> disabled="disabled" </c:if> name="viewReplyCount" id="viewReplyCount" class="form-control">
									<c:forEach items="${allViewReplyCounts}" var="obj" varStatus="idx">
	                                  <option value="${obj.key}" <c:if test="${obj.key eq item.viewReplyCount or (empty item.viewReplyCount and idx.first)}">selected</c:if>>${obj.value}</option>
	                               </c:forEach>
								 </select>
	                        </div>
	                    </div> --%>
                    </div>
                    <div id="templet-div">
	                </div>
                	<div class="form-group">
						<div class=" col-sm-12">
							<div class="pull-right">
								<button class="btn btn-success btn-sm" type="button" id="commonSaveButton" back_url="/module/columns/app_columns_list.html">
									<i class="icon-save bigger-110"></i>提交
								</button>
								&nbsp;
								<button class="btn btn-sm btn-primary" type="button" onclick="javascript:location.href='/module/columns/app_columns_list.html'">
									<i class="icon-undo bigger-110"></i>返回
								</button>
							</div>
						</div>
					</div>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
	</div><!-- /.page-content -->
</div>

<script>
window.UEDITOR_HOME_URL = '/resource/components/ueditor/';
var imgUrl = '${imageUrl}';
var imgUrlDb = '${item.imageUrl}';
var columnsType = '${item.columnsType}';
var appId = '${item.appId}';

var COLUMNSTYPE0=${COLUMNSTYPE0};
var COLUMNSTYPE2=${COLUMNSTYPE2};
var COLUMNSTYPE3=${COLUMNSTYPE3};
var templetCode = '${item.templetCode}';
var parentId = '${item.parentId}';
var columnsType = '${item.columnsType}';

require(['/resource/business/config/app_columns_detail.js'],function(columns){
	columns.init();
});
</script>