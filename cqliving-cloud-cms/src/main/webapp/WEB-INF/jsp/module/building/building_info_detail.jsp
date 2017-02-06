<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=${baiduLbsKey}"></script>
<style>
.ace-thumbnails>li{
	width: 250px !important;
}
.ace-thumbnails img{
	width: 100% !important;
	height:250px;
}
</style>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="楼房信息表列表|building_info_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item.id ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" id="form1" action="/module/building/building_info_${empty item.id ? 'add' : 'update'}.html">
		        	<div class='col-md-12 col-lg-8'>
			            <c:if test="${not empty item}">
			            	<input type="hidden" name="id" value="${item.id}" />
			            </c:if>
			            
			            <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="regionName">区域名称<i class="text-danger">*</i></label>
                            <div class="col-sm-9">
	                            <input type="hidden" name="regionName" id="regionName" value="${item.regionName }">
	                            <select class="chosen-select" data-placeholder="请选择区域" name="regionCode" id="regionCode">
	                                <c:forEach items="${allRegion}" var="it">
	                                	<option value="${it.code}" <c:if test="${item.regionCode eq it.code}">selected="selected"</c:if>>${it.name}</option>
	                                </c:forEach>
	                            </select>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="name">楼盘名称<i class="text-danger">*</i></label>
                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="buildingName" name="name" maxlength="100" placeholder="请输入楼盘名称 如 龙湖花园小区"  value="${item.name}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="landmark">楼盘地标</label>
                            <div class="col-sm-9 form-group">
                            	<div class="col-sm-12">
                            		<span class="">地标请勿超过8个字，最多5个地标,主页显示第一个地标</span>
                            	</div>
                            	<c:set var="its" value="${fn:split(item.landmark, ',') }"/>
                            	<div class="col-sm-4 form-group">
                            		<div class="col-sm-12">
	                            		<input type="text" class="form-control" name="landmark" maxlength="8" placeholder=""  value="${its[0] }">
	                            	</div>
                            	</div>
                            	<div class="col-sm-4 form-group">
                            		<div class="col-sm-12">
	                            		<input type="text" class="form-control" name="landmark" maxlength="8" placeholder=""  value="${its[1] }">
	                            	</div>
                            	</div>
                            	<div class="col-sm-4 form-group">
                            		<div class="col-sm-12">
	                            		<input type="text" class="form-control" name="landmark" maxlength="8" placeholder=""  value="${its[2] }">
	                            	</div>
                            	</div>
                            	<div class="col-sm-4 form-group">
                            		<div class="col-sm-12">
	                            		<input type="text" class="form-control" name="landmark" maxlength="8" placeholder=""  value="${its[3] }">
	                            	</div>
                            	</div>
                            	<div class="col-sm-4 form-group">
                            		<div class="col-sm-12">
	                            		<input type="text" class="form-control" name="landmark" maxlength="8" placeholder=""  value="${its[4] }">
	                            	</div>
                            	</div>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="address">楼盘地址<i class="text-danger">*</i></label>
                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="address" name="address" maxlength="200" placeholder="请输入详细地址"  value="${item.address}">
	                        </div>
	                    </div>
	                    <div class="form-group hide">
	                        <label class="col-sm-3 control-label no-padding-right" for="content">地图坐标</label>
	           				<div class="col-sm-9">
	                     		<input class="form-control" id="suggestId" type="text" placeholder="搜索并选择，或在地图上标记">
	                       	</div>
	                    </div>
	                    <div class="form-group">
	                        <input type="hidden" id="map_lat" name="lat" value="${item.lat}" />
	                        <input type="hidden" id="map_lng" name="lng" value="${item.lng}" />
	                        <div class="col-sm-9 col-sm-offset-3" style="height: 350px;">
	                        	<div id="baidu_map" style="height: 100%; border-color: #858585;"></div>
	                        </div>
	                    </div>
	                    <c:if test="${appId ne 1 }">
	                    <div class="form-group">
							<label class="col-sm-3 control-label no-padding-right">类型</label>
							<div class="col-sm-9">
								<select name="type" class="form-control" data-placeholder="请选择类型">
									<c:forEach items="${allTypes}" var="it">
										<option value="${it.key }" <c:if test="${item.type eq it.key}">selected</c:if> <c:if test="${empty item.type && it.key eq 1 && item.type ne it.key}">selected</c:if>>${it.value}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						</c:if>
						<c:if test="${appId eq 1 }">
							<input type="hidden" name="type" value="${empty item.type ? 1 : item.type}">
						</c:if>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="averageLow">楼盘面积</label>
                            <div class="col-sm-9">
                            	<input type="number" id="averageLow" name="averageLow" maxlength="22" placeholder="10"  value="${item.averageLow}">
                            	<span>-</span>
                            	<input type="number" id="averageHigh" name="averageHigh" maxlength="22" placeholder="100"  value="${item.averageHigh}">
                            	<span class="bigger-150">㎡</span>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="averagePrice">楼盘均价</label>
	                        <div class="col-sm-9">
	                            <input class="" name="averagePrice" id="averagePrice" type="number" value="${item.averagePrice}" maxlength="10" placeholder="只用于价格筛选">
	                            <span class="bigger-150">元/㎡</span>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="viewPrice">显示价格</label>
	                        <div class="col-sm-9 radio">
	                            <input name="viewPrice" id="viewPrice" type="number" value="${item.viewPrice}" maxlength="10" placeholder="用于价格展示">
	                            
	                            <c:forEach items="${allViewUnits }" var="it">
		                            <label class="radio-2">
		                                <input type="radio" class="ace" name="viewUnit" value="${it.key }" id="viewUnit${it.key }"><span class="lbl"> ${it.value }</span>
		                            </label>
	                            </c:forEach>
	                            <script type="text/javascript">
	                                document.getElementById("viewUnit${empty item ? 1 : item.viewUnit}").checked=true;
	                            </script>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="telephone">咨询电话<i class="text-danger">*</i></label>
                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="telephone" name="telephone" maxlength="50" placeholder="请输入咨询电话"  value="${item.telephone}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">楼盘标签</label>
                            <div class="col-sm-9 form-group">
                            	<div class="col-sm-12">
                            		<span class="">标签请勿超过5个字，最多3个标签</span>
                            	</div>
                            	<c:set var="its" value="${fn:split(item.buildingLabel, ',') }"/>
                            	<div class="col-sm-4 form-group">
                            		<div class="col-sm-12">
	                            		<input type="text" class="form-control" name="buildingLabel" maxlength="5" placeholder=""  value="${its[0]}">
	                            	</div>
                            	</div>
                            	<div class="col-sm-4 form-group">
                            		<div class="col-sm-12">
	                            		<input type="text" class="form-control" name="buildingLabel" maxlength="5" placeholder=""  value="${its[1]}">
	                            	</div>
                            	</div>
                            	<div class="col-sm-4 form-group">
                            		<div class="col-sm-12">
	                            		<input type="text" class="form-control" name="buildingLabel" maxlength="5" placeholder=""  value="${its[2]}">
	                            	</div>
                            	</div>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="houseType">户型</label>
                            <div class="col-sm-9 checkbox">
                            	<c:set var="its" value="${fn:split(item.houseType, ',') }"/>
								<c:forEach items="${allHouseTypes }" var="it">
		                            <label class="checkbox-2">
		                                <input type="checkbox" class="ace" name="houseType" value="${it.key }" 
		                                	<c:forEach var="ht" items="${its }"><c:if test="${it.key eq ht }">checked="checked"</c:if></c:forEach>
		                                ><span class="lbl"> ${it.value }</span>
		                            </label>
								</c:forEach>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="structure">户型结构</label>
                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="structure" name="structure" maxlength="100" placeholder="如 三居(套内43㎡) 二居(套内32㎡) 三居(套内43㎡)"  value="${item.structure}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="innerArea">套内面积</label>
                            <div class="col-sm-9">
	                            <input type="text" class="form-control" id="innerArea" name="innerArea" maxlength="100" placeholder="如 99999平方米"  value="${item.innerArea}">
	                        </div>
	                    </div>
	                    <div class="fieldset-form">
		                    <fieldset>
		                    	<legend>楼盘信息</legend>
		                    	<div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="buildingType">建筑类型</label>
		                            <div class="col-sm-9">
			                            <input type="text" class="form-control" id="buildingType" name="buildingType" maxlength="50" placeholder="请输入建筑类型 如 高层"  value="${item.buildingType}">
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="ratio">容积率</label>
		                            <div class="col-sm-9">
			                            <input type="text" class="" id="ratio" name="ratio" maxlength="50" placeholder="请输入容积率 如 3.92"  value="${item.ratio}">
			                            <span class="bigger-150">%</span>
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="openTime">开盘时间</label>
		                            <div class="col-sm-9">
			                            <input type="text" class="form-control" id="openTime" name="openTime" maxlength="100" placeholder="如 2016年6月18日三期5号楼已开盘"  value="${item.openTime}">
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="buildingYear">产权年限</label>
		                            <div class="col-sm-9">
			                            <input type="text" class="" id="buildingYear" name="buildingYear" maxlength="50" placeholder=" 请输入数字"  value="${item.buildingYear}">
			                            <span class="bigger-150">年</span>
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="developer">开发商</label>
			                            <div class="col-sm-9">
			                            <input type="text" class="form-control" id="developer" name="developer" maxlength="100" placeholder="请输入开发商名称"  value="${item.developer}">
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="greeningRate">绿化率</label>
		                            <div class="col-sm-9">
			                            <input type="text" class="" id="greeningRate" name="greeningRate" maxlength="50" placeholder="请输入数字"  value="${item.greeningRate}">
			                            <span class="bigger-150">%</span>
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="launchTime">交房时间</label>
			                            <div class="col-sm-9">
			                            <input type="text" class="form-control" id="launchTime" name="launchTime" maxlength="50" placeholder="请输入时间和内容描述 如预计X年X月交房"  value="${item.launchTime}">
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="propertyPrice">物业费</label>
			                            <div class="col-sm-9">
			                            <input type="text" class="form-control" id="propertyPrice" name="propertyPrice" maxlength="100" placeholder="如 5.00元/每平米·月"  value="${item.propertyPrice}">
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="renovationSituation">装修状况</label>
			                            <div class="col-sm-9">
			                            <input type="text" class="form-control" id="renovationSituation" name="renovationSituation" maxlength="100" placeholder="请输入装修状况 如 毛坯"  value="${item.renovationSituation}">
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="presalePermit">预售许可证</label>
			                            <div class="col-sm-9">
			                            <input type="text" class="form-control" id="presalePermit" name="presalePermit" maxlength="100" placeholder="请输入预售许可证号"  value="${item.presalePermit}">
			                        </div>
			                    </div>
		                    </fieldset>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="sortNo">排序号</label>
	                        <div class="col-sm-9">
	                            <input class="form-control" name="sortNo" id="sortNo" type="text" value="<c:if test="${item.sortNo ne defaultSortNo }">${item.sortNo }</c:if>" maxlength="10" placeholder="请输入排序号">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">轮播图<i class="text-danger">*</i></label>
	                        <div class="col-sm-9" id="listImageUrlBtn">
	                        	<input type="hidden" name="listImageUrl" id="listImageUrl" value="">
	                            <i class="icon-cloud-upload"></i>上传轮播图
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label"></label>
	                        <div class="col-sm-9" id="listImageUrlList">
	                      		<c:if test="${not empty imageList1}">
	                           		<ul class="ace-thumbnails">
	                           			<c:forEach items="${imageList1}" var="img">
	                                 		<li class="upload-imgs">
	                                 			<a href="${img.url}" data-rel="colorbox">
			                                 		<img src="${img.url}"/>
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
	                    <div class="form-group linkControl">
	                        <label class="col-sm-3 control-label no-padding-right">户型图片</label>
	                        <div class="col-sm-9" id="imagesBtn">
	                            <i class="icon-cloud-upload"></i>上传户型图片
	                        </div>
	                    </div>
	                    <div class="form-group linkControl">
	                        <label class="col-sm-3 control-label"></label>
	                        <div class="col-sm-9" id="imgagesThum">
	                      		<c:if test="${not empty imageList2}">
	                           		<ul class="ace-thumbnails">
	                             		<c:forEach items="${imageList2}" var="img">
	                                 		<li class="upload-imgs">
	                                 			<a href="${img.url}" data-rel="colorbox">
			                                 		<img src="${img.url}"/>
	                                 			</a>
		                                 		<div class="tools tools-top"> 
								          			<a href="javascript:void(0);"><i class="icon-remove red"></i></a> 
								 		 		</div>
								 		 		<div class="imgDesc">
								 		 			<input type="hidden" name="images" value="${img.url }">
								 		 			<input type="text" name="descType" value="${img.descType }" class="form-control" placeholder="输入户型 如一室一厅一卫一厨">
								 		 			<input type="text" name="descArea" value="${img.descArea }" class="form-control" placeholder="输入面积 如套内面积：34㎡">
								 		 		</div>
		                              		</li>
	                             		</c:forEach>
	                           		</ul>
	                          	</c:if>
	                        </div>
	                    </div>
	                    
	                	<div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<!-- <button class="btn btn-info btn-sm draft_save view_button" type="button" data-target="#info_view_modal" role="button" data-toggle="modal">
										<i class="icon-eye-open bigger-110"></i>预览
									</button>
									&nbsp; -->
									<button class="btn btn-primary btn-sm push_save hide" type="button">
										<i class="icon-mail-forward bigger-110"></i>发布
									</button>
									&nbsp;
									<button class="btn btn-success btn-sm draft_save" type="button" id="saveButton" back_url="/module/building/building_info_list.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" id="backBtn" backRestoreParam="backRestoreParam" back_url="/module/building/building_info_list.html">
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

<script type="text/javascript">
var imgUrl = "${imageUrl}";
require(['/resource/business/building/building_info_detail.js'], function(obj){
	obj.init();
});
</script>