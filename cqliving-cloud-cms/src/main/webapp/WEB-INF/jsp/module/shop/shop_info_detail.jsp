<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=${baiduLbsKey}"></script>
<style>
	.none {display: none;}
</style>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="商情管理|/module/shop/shop_info_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1">
		        	<input type="hidden" name="appId" value="${session_user_obj.appId}" />
		            <c:if test="${not empty item}">
		            	<input type="hidden" name="id" value="${item.id}" />
		            	<input type="hidden" id="auditDesc" name="auditDesc" value="${item.auditDesc }"/>
		            </c:if>
		            <div class="col-md-12 col-lg-8">
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="name">店铺名称</label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="name" name="name" maxlength="100" placeholder="请输入店铺名称"  value="${item.name}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="typeId">所属类型</label>
	                            <div class="col-sm-9">
	                            <select name="typeId" id="typeId" class="form-control" placeholder="请选择所属类型">
	                               	<c:forEach items="${allTypes}" var="obj">
	                               		<option value="${obj.id}" <c:if test="${item.typeId eq obj.id}">selected="selected"</c:if>>${obj.name}</option>
	                               	</c:forEach>
	                           	</select>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="categoryId">所属分类</label>
	                            <div class="col-sm-9">
	                            <input type="hidden" id="categoryId_hd" value="${item.categoryId}" />
	                            <select name="categoryId" id="categoryId" class="form-control" placeholder="请选择所属类型">
	                               	<c:forEach items="${allCategories}" var="obj">
	                               		<option value="${obj.id}" typeid="${obj.typeId}" <c:if test="${item.categoryId eq obj.id}">selected="selected"</c:if>>${obj.name}</option>
	                               	</c:forEach>
	                           	</select>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="regionCode">所属区域</label>
	                            <div class="col-sm-9">
	                            <input type="hidden" id="regionCode_hd" value="${item.regionCode}" />
	                            <select name="regionCode" id="regionCode" class="form-control" placeholder="请选择所属区域">
	                               	<c:forEach items="${allRegion}" var="obj">
	                               		<option value="${obj.code}" typeid="${obj.shopTypeId}" <c:if test="${item.regionCode eq obj.code}">selected="selected"</c:if>>${obj.name}</option>
	                               	</c:forEach>
	                           	</select>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="infoLabelId">标签</label>
	                            <div class="col-sm-9">
	                            <input type="hidden" id="infoLabelId_hd" value="${item.infoLabelId}" />
	                            <select name="infoLabelId" id="infoLabelId" class="form-control" placeholder="请选择标签">
                               		<option value="">无标签</option>
	                               	<c:forEach items="${infoLabels}" var="obj">
	                               		<option value="${obj.id}" <c:if test="${item.infoLabelId eq obj.id}">selected="selected"</c:if>>${obj.name}</option>
	                               	</c:forEach>
	                           	</select>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="address">地址</label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="address" name="address" maxlength="200" placeholder="请输入地址"  value="${item.address}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="telephone">电话</label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="telephone" name="telephone" maxlength="50" placeholder="请输入电话"  value="${item.telephone}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="description">描述</label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="description" name="description" maxlength="100" placeholder="请输入描述"  value="${item.description}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="price">人均消费（元）</label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="price" id="price" type="text" value="<c:if test="${not empty item}"><fmt:formatNumber value="${item.price / 100}" type="number" /></c:if>" maxlength="10" placeholder="请输入人均消费" />
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">店铺图片</label>
	                        <div class="col-sm-9" id="img_upload">
		                        <input type="hidden" name="coverImg" />
		                        <input type="hidden" name="images" value="" />
	                            <i class="icon-cloud-upload"></i>上传店铺图片
	                            <span class="sns-num synopsis_num">最多9张图片</span>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label"></label>
	                        <div class="col-sm-9" id="img_thum">
	                      		<c:if test="${not empty images}">
	                           		<ul class="ace-thumbnails">
	                             		<c:forEach items="${images}" var="img">
	                                 		<li class="upload-imgs">
	                                 			<a href="${img.url}" data-rel="colorbox">
			                                 		<img alt="150x150" src="${img.url}" style="width:150px;height:150px;" />
	                                 			</a>
		                                 		<div class="tools tools-top"> 
								          			<a href="javascript:void(0);"><i class="icon-remove red"></i></a> 
								 		 		</div>
		                              		</li>
	                             		</c:forEach>
	                           		</ul>
	                          	</c:if>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="content">地图坐标</label>
	           				<div class="col-sm-9">
	                     		<input class="form-control" name="price" id="suggestId" type="text" maxlength="10" placeholder="搜索并选择，或在地图上标记">
	                       	</div>
	                    </div>
	                    <div class="form-group">
	                        <input type="hidden" id="shop_lat" name="lat" value="${item.lat}" />
	                        <input type="hidden" id="shop_lng" name="lng" value="${item.lng}" />
	                        <div class="col-sm-9 col-sm-offset-3" style="height: 250px;">
	                        	<div id="baidu_map" style="height: 100%; border-color: #858585;"></div>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="content">商品简介</label>
	                        <div class="col-sm-9">
	                           	<textarea class="hidden" name="content">${item.content}</textarea>
	                            <script id="shop_info_content_editor" type="text/plain">${item.content}</script>
	                        </div>
	                    </div>
	                	<div class="form-group">
							<div class=" col-sm-12">
								<div class="pull-right">
									<c:if test="${not empty item && item.sourceType eq 2 && (item.status eq -1 || item.status eq 1)}">
										<cqliving-security2:hasPermission name="/module/shop/shop_info_audit.html">
											<button class="btn btn-primary btn-sm" id="audit_btn" type="button" url="/module/shop/shop_info_audit.html">
												<i class="icon-edit bigger-110"></i>审核
											</button>
											<button class="hide" id="audit_online_btn" type="button" back_url="/module/shop/shop_info_list.html">审核并保存，只做一个跳板使用</button>
											&nbsp;
										</cqliving-security2:hasPermission>
									</c:if>
									<button class="btn btn-success btn-sm" id="save_btn" type="button" back_url="/module/shop/shop_info_list.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
									<c:if test="${empty item}">
										<button class="btn btn-primary btn-sm" id="online_btn" type="button" back_url="/module/shop/shop_info_list.html">
											<i class="icon-arrow-up bigger-110"></i>上线
										</button>
									</c:if>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" id="backButton" onclick="javascript:location.href='/module/shop/shop_info_list.html'">
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
	<div class="none">
		<div id="category_option">
			<c:forEach items="${allCategories}" var="obj">
	       		<option value="${obj.id}" typeid="${obj.typeId}">${obj.name}</option>
	       	</c:forEach>
       	</div>
       	<div id="region_option">
	       	<c:forEach items="${allRegion}" var="obj">
	       		<option value="${obj.code}" typeid="${obj.shopTypeId}">${obj.name}</option>
	       	</c:forEach>
       	</div>
	</div>
</div>
<script type="text/javascript">
	var imgUrl = "${imageUrl}";
	window.UEDITOR_HOME_URL = "/resource/components/ueditor/";
	
	require(["/resource/business/shop/shopInfoDetail.js"], function(obj) {
		obj.init();
	});
</script>
</body>
</html>