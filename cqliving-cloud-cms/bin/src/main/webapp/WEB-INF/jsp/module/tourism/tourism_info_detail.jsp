<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=${baiduLbsKey}"></script>
<c:if test="${item.isLink eq 1 }">
	<style type="text/css">
		.linkControl {
			display: none;
		}
	</style>
</c:if>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="旅游表列表|/module/tourism/tourism_info_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}${allTypes[sourceType] }" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/tourism/tourism_info_${empty item.id ? 'add/' : 'update'}${empty item.id ? sourceType : ''}.html">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
		        		<input type="hidden" name="appId" value="${appId}" />
			            <c:if test="${not empty item}">
			            	<input type="hidden" name="id" value="${item.id}" />
			            </c:if>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="name">名称<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="name" name="name" maxlength="100" placeholder="请输入名称"  value="${item.name}">
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
	                        <label class="col-sm-3 control-label no-padding-right" for="isLink">是否外链<i class="text-danger">*</i></label>
	                        <div class="col-sm-9 radio">
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="isLink" value="1" id="isLink1"><span class="lbl"> 是</span>
	                            </label>
	                            <label class="radio-2">
	                                <input type="radio" class="ace" name="isLink" value="0" id="isLink0"><span class="lbl"> 否</span>
	                            </label>
	                            <script type="text/javascript">
	                                document.getElementById("isLink${empty item ? 0 : item.isLink}").checked=true;
	                            </script>
	                        </div>
	                    </div>
	                    <div class="form-group" <c:if test="${item.isLink ne 1 }">style="display: none;"</c:if>>
	                        <label class="col-sm-3 control-label no-padding-right" for="linkUrl">外链地址<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="linkUrl" name="linkUrl" maxlength="255" placeholder="请输入外链地址"  value="${item.linkUrl}">
	                        </div>
	                    </div>
	                    <c:if test="${sourceType eq TYPE1 }">
		                    <div class="form-group linkControl">
		                        <label class="col-sm-3 control-label no-padding-right" for="price">景区价格</label>
		                            <div class="col-sm-9">
		                            <input type="text" class="form-control" id="price" name="price" maxlength="50" placeholder="请输入景区价格"  value="${item.price}">
		                        </div>
		                    </div>
		                    <div class="form-group linkControl">
		                        <label class="col-sm-3 control-label no-padding-right" for="timeOpen">开放时间</label>
		                            <div class="col-sm-9">
		                            <input type="text" class="form-control" id="timeOpen" name="timeOpen" maxlength="50" placeholder="请输入开放时间"  value="${item.timeOpen}">
		                        </div>
		                    </div>
		                    <div class="form-group linkControl">
		                        <label class="col-sm-3 control-label no-padding-right" for="climateType">气候类型</label>
		                            <div class="col-sm-9">
		                            <input type="text" class="form-control" id="climateType" name="climateType" maxlength="50" placeholder="请输入气候类型"  value="${item.climateType}">
		                        </div>
		                    </div>
		                    <div class="form-group linkControl">
		                        <label class="col-sm-3 control-label no-padding-right" for="telephone">联系电话</label>
		                            <div class="col-sm-9">
		                            <input type="text" class="form-control" id="telephone" name="telephone" maxlength="50" placeholder="请输入联系电话,最多三个用逗号分隔"  value="${item.telephone}">
		                        </div>
		                    </div>
		                    <div class="form-group linkControl">
		                        <label class="col-sm-3 control-label no-padding-right" for="address">地点</label>
		                            <div class="col-sm-9">
		                            <input type="text" class="form-control" id="address" name="address" maxlength="50" placeholder="请输入地点"  value="${item.address}">
		                        </div>
		                    </div>
	                    </c:if>
	                    <c:if test="${sourceType eq TYPE2 }">
		                    <div class="form-group linkControl">
		                        <label class="col-sm-3 control-label no-padding-right" for="description">专题描述<i class="text-danger">*</i></label>
	                            <div class="col-sm-9">
	                            	<textarea rows="6" class="form-control" id="description" name="description" placeholder="请输入专题描述">${item.description}</textarea>
		                        </div>
		                    </div>
	                    </c:if>
	                    <%-- <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="sortNo">排序号<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="sortNo" id="sortNo" type="number" value="${item.sortNo}" maxlength="10" placeholder="请输入排序号">
	                        </div>
	                    </div> --%>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="imageUrl">列表图<i class="text-danger">*</i></label>
	                        <div class="col-sm-9" id="imageUrlBtn">
		                        <input type="hidden" name="imageUrl" id="imageUrl" value="" />
	                            <i class="icon-cloud-upload"></i>上传列表图
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label"></label>
	                        <div class="col-sm-9" id="imageUrlList">
	                      		<c:if test="${not empty item.imageUrl}">
	                           		<ul class="ace-thumbnails">
                                 		<li class="upload-imgs">
                                 			<a href="${item.imageUrl}" data-rel="colorbox">
		                                 		<img alt="150x150" src="${item.imageUrl}" style="width:150px;height:150px;" />
                                 			</a>
	                                 		<div class="tools tools-top"> 
							          			<a href="javascript:void(0);"><i class="icon-remove red"></i></a> 
							 		 		</div>
	                              		</li>
	                           		</ul>
	                          	</c:if>
	                        </div>
	                    </div>
	                    <div class="form-group linkControl">
	                        <label class="col-sm-3 control-label no-padding-right" id="imagesLabel">${allTypes[sourceType] }图片<i class="text-danger">*</i></label>
	                        <div class="col-sm-9" id="img_upload">
		                        <input type="hidden" name="images" value="" id="images"/>
	                            <i class="icon-cloud-upload"></i>上传${allTypes[sourceType] }图片
	                        </div>
	                    </div>
	                    <div class="form-group linkControl">
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
	                        <label class="col-sm-3 control-label no-padding-right" for="synopsis">摘要</label>
	                        <div class="col-sm-9">
	                            <textarea rows="6" cols="" class="form-control" id="synopsis" name="synopsis" placeholder="请输入摘要，不能超过200个字符" maxlength="200">${item.synopsis}</textarea>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="place">所处位置<c:if test="${TYPE1 eq sourceType}"><i class="text-danger">*</i></c:if></label>
	                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="place" name="place" maxlength="100" placeholder="请输入所处位置"  value="${item.place}">
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="content">地图坐标<c:if test="${TYPE1 eq sourceType}"><i class="text-danger">*</i></c:if></label>
	           				<div class="col-sm-9">
	                     		<input class="form-control" id="suggestId" type="text" placeholder="搜索并选择，或在地图上标记">
	                       	</div>
	                    </div>
	                    <div class="form-group">
	                        <div class="col-sm-9 col-sm-offset-3" style="height: 250px;">
	                        	<div id="baidu_map" style="height: 100%; border-color: #858585;"></div>
	                        </div>
	                    	<div class="col-sm-12">
	                    		<label class="col-sm-3 control-label no-padding-right"></label>
		                        <div class="col-sm-9">
			                        <input type="hidden" id="map_lat" name="lat" value="${item.lat}" />
			                        <input type="hidden" id="map_lng" name="lng" value="${item.lng}" />
		                        </div>
		                    </div>
	                    </div>
	                    <c:if test="${sourceType eq TYPE1 }">
	                    	<div class="form-group linkControl">
		                        <label class="col-sm-3 control-label no-padding-right" for="scenicRoute">景点线路<i class="text-danger">*</i></label>
		                        <div class="col-sm-9">
		                            <textarea rows="6" cols="" class="form-control" id="scenicRoute" name="scenicRoute" placeholder="请输入景点线路">${item.scenicRoute}</textarea>
		                        </div>
		                    </div>
		                    <div class="form-group linkControl">
		                        <label class="col-sm-3 control-label no-padding-right" for="content">景点介绍内容<i class="text-danger">*</i></label>
		                        <div class="col-sm-9">
		                            <script id="tourism_info_content" type="text/plain">${item.content}</script>
		                        </div>
		                    </div>
	                    </c:if>
	                	<c:if test="${empty _model_ }">
	                	<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<c:if test="${not empty item.id }">
										<button class="btn btn-info btn-sm draft_save view_button" id="previewBtn" type="button" url="/module/tourism/common/tourism_info_view.html?id=${item.id }&_model_=_model_"  open-title="预览" onclick="javascript:void(0);">
											<i class="icon-eye-open bigger-110"></i>预览
										</button>
										&nbsp;
									</c:if>
									<button class="btn btn-success btn-sm draft_save" type="button" id="saveButton" back_url="/module/tourism/tourism_info_list.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" id="backButton" backRestoreParam="backRestoreParam" back_url="/module/tourism/tourism_info_list.html">
										<i class="icon-undo bigger-110"></i>返回
									</button>
								</div>
							</div>
						</div>
						</c:if>
					</div>
		        </form>
			</div>
		</div><!-- /.row --><!-- /.main-content -->
<c:if test="${empty _model_ }">
	</div><!-- /.page-content -->
</div>
</c:if>

<script type="text/javascript">
var imgUrl = "${imageUrl}",
	sourceType = "${sourceType}",
	TYPE1 = '${TYPE1}',
	TYPE2 = '${TYPE2}';
window.UEDITOR_HOME_URL = "/resource/components/ueditor/";
require(['/resource/business/tourism/tourism_info_detail.js'], function(obj){
	obj.init();
});
</script>