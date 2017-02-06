<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=${baiduLbsKey}"></script>
<style>
.ace-thumbnails>li{
	width: 250px !important;
}
.ace-thumbnails img{
	width: 100% !important;
}
</style>
<c:if test="${empty _model_ }">
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="楼房信息表列表|building_info_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="查看" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content">
</c:if>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form">
		        	<div class='col-md-12 <c:if test="${empty _model_ }">col-lg-8</c:if>'>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">所属区域名称</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.regionName}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">楼盘名称</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.name}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="landmark">楼盘地标</label>
                            <div class="col-sm-9 form-group">
                            	<div class="col-sm-12">
                            		<span class="text-danger">地标请勿超过8个字，最多5个地标,主页显示第一个地标</span>
                            	</div>
                            	<c:set var="its" value="${fn:split(item.landmark, ',') }"/>
                            	<div class="col-sm-4 form-group">
                            		<div class="col-sm-12">
	                            		<input type="text" readonly="" class="form-control" name="landmark" maxlength="8" placeholder=""  value="${its[0] }">
	                            	</div>
                            	</div>
                            	<div class="col-sm-4 form-group">
                            		<div class="col-sm-12">
	                            		<input type="text" readonly="" class="form-control" name="landmark" maxlength="8" placeholder=""  value="${its[1] }">
	                            	</div>
                            	</div>
                            	<div class="col-sm-4 form-group">
                            		<div class="col-sm-12">
	                            		<input type="text" readonly="" class="form-control" name="landmark" maxlength="8" placeholder=""  value="${its[2] }">
	                            	</div>
                            	</div>
                            	<div class="col-sm-4 form-group">
                            		<div class="col-sm-12">
	                            		<input type="text" readonly="" class="form-control" name="landmark" maxlength="8" placeholder=""  value="${its[3] }">
	                            	</div>
                            	</div>
                            	<div class="col-sm-4 form-group">
                            		<div class="col-sm-12">
	                            		<input type="text" readonly="" class="form-control" name="landmark" maxlength="8" placeholder=""  value="${its[4] }">
	                            	</div>
                            	</div>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="address">楼盘地址</label>
                            <div class="col-sm-9">
	                            <input type="text" readonly="readonly" class="form-control" id="address" name="address" maxlength="200" placeholder=""  value="${item.address}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <div class="col-sm-9 col-sm-offset-3" style="height: 350px;">
	                        	<div id="baidu_map" style="height: 100%; border-color: #858585;"></div>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">类型</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${allTypes[item.type]}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="averageLow">楼盘面积</label>
                            <div class="col-sm-9">
                            	<input type="number" readonly="readonly" id="averageLow" name="averageLow" maxlength="22" placeholder=""  value="${item.averageLow}">
                            	<span>-</span>
                            	<input type="number" readonly="readonly" id="averageHigh" name="averageHigh" maxlength="22" placeholder=""  value="${item.averageHigh}">
                            	<span class="bigger-150">㎡</span>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="averagePrice">楼盘均价</label>
	                        <div class="col-sm-9">
	                            <input class="" readonly="readonly" name="averagePrice" id="averagePrice" type="number" value="${item.averagePrice/100}" maxlength="10" placeholder="">
	                            <span class="bigger-150">元/㎡</span>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="viewPrice">显示价格</label>
	                        <div class="col-sm-9 radio">
	                            <input name="viewPrice" readonly="readonly" id="viewPrice" type="number" value="${item.viewPrice}" maxlength="10" placeholder="">
	                            <span class="bigger-150">${allViewUnits[item.viewUnit] }</span>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="telephone">咨询电话</label>
                            <div class="col-sm-9">
	                            <input type="text" readonly="readonly" class="form-control" id="telephone" name="telephone" maxlength="50" placeholder=""  value="${item.telephone}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">楼盘标签</label>
                            <div class="col-sm-9 form-group">
                            	<div class="col-sm-12">
                            		<span class="text-danger">标签请勿超过5个字，最多3个标签</span>
                            	</div>
                            	<c:set var="its" value="${fn:split(item.buildingLabel, ',') }"/>
                            	<div class="col-sm-4 form-group">
                            		<div class="col-sm-12">
	                            		<input type="text" readonly="readonly" class="form-control" name="buildingLabel" maxlength="5" placeholder=""  value="${its[0]}">
	                            	</div>
                            	</div>
                            	<div class="col-sm-4 form-group">
                            		<div class="col-sm-12">
	                            		<input type="text" readonly="readonly" class="form-control" name="buildingLabel" maxlength="5" placeholder=""  value="${its[1]}">
	                            	</div>
                            	</div>
                            	<div class="col-sm-4 form-group">
                            		<div class="col-sm-12">
	                            		<input type="text" readonly="readonly" class="form-control" name="buildingLabel" maxlength="5" placeholder=""  value="${its[2]}">
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
		                                <input type="checkbox" disabled="disabled" class="ace" name="houseType" value="${it.key }" 
		                                	<c:forEach var="ht" items="${its }"><c:if test="${it.key eq ht }">checked="checked"</c:if></c:forEach>
		                                ><span class="lbl"> ${it.value }</span>
		                            </label>
								</c:forEach>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="structure">户型结构</label>
                            <div class="col-sm-9">
	                            <input type="text" readonly="readonly" class="form-control" id="structure" name="structure" maxlength="100" placeholder=""  value="${item.structure}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="innerArea">套内面积</label>
                            <div class="col-sm-9">
	                            <input type="text" readonly="readonly" class="form-control" id="innerArea" name="innerArea" maxlength="100" placeholder=""  value="${item.innerArea}">
	                        </div>
	                    </div>
	                    <div class="fieldset-form">
		                    <fieldset>
		                    	<legend>楼盘信息</legend>
		                    	<div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="buildingType">建筑类型</label>
		                            <div class="col-sm-9">
			                            <input type="text" readonly="readonly" class="form-control" id="buildingType" name="buildingType" maxlength="50" placeholder=""  value="${item.buildingType}">
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="ratio">容积率</label>
		                            <div class="col-sm-9">
			                            <input type="text" readonly="readonly" class="" id="ratio" name="ratio" maxlength="50" placeholder=""  value="${item.ratio}">
			                            <span class="bigger-150">%</span>
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="openTime">开盘时间</label>
		                            <div class="col-sm-9">
			                            <input type="text" readonly="readonly" class="form-control" id="openTime" name="openTime" maxlength="100" placeholder=""  value="${item.openTime}">
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="buildingYear">产权年限</label>
		                            <div class="col-sm-9">
			                            <input type="text" readonly="readonly" class="" id="buildingYear" name="buildingYear" maxlength="50" placeholder=""  value="${item.buildingYear}">
			                            <span class="bigger-150">年</span>
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="developer">开发商</label>
			                            <div class="col-sm-9">
			                            <input type="text" readonly="readonly" class="form-control" id="developer" name="developer" maxlength="100" placeholder=""  value="${item.developer}">
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="greeningRate">绿化率</label>
		                            <div class="col-sm-9">
			                            <input type="text" readonly="readonly" class="" id="greeningRate" name="greeningRate" maxlength="50" placeholder=""  value="${item.greeningRate}">
			                            <span class="bigger-150">%</span>
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="launchTime">交房时间</label>
			                            <div class="col-sm-9">
			                            <input type="text" readonly="readonly" class="form-control" id="launchTime" name="launchTime" maxlength="50" placeholder=""  value="${item.launchTime}">
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="propertyPrice">物业费</label>
			                            <div class="col-sm-9">
			                            <input type="text" readonly="readonly" class="form-control" id="propertyPrice" name="propertyPrice" maxlength="100" placeholder=""  value="${item.propertyPrice}">
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="renovationSituation">装修状况</label>
			                            <div class="col-sm-9">
			                            <input type="text" readonly="readonly" class="form-control" id="renovationSituation" name="renovationSituation" maxlength="100" placeholder=""  value="${item.renovationSituation}">
			                        </div>
			                    </div>
			                    <div class="form-group">
			                        <label class="col-sm-3 control-label no-padding-right" for="presalePermit">预售许可证</label>
			                            <div class="col-sm-9">
			                            <input type="text" readonly="readonly" class="form-control" id="presalePermit" name="presalePermit" maxlength="100" placeholder=""  value="${item.presalePermit}">
			                        </div>
			                    </div>
		                    </fieldset>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right" for="sortNo">排序号</label>
	                        <div class="col-sm-9">
	                            <input class="form-control" readonly="readonly" name="sortNo" id="sortNo" type="text" value="<c:if test="${item.sortNo ne defaultSortNo }">${item.sortNo }</c:if>" maxlength="10" placeholder="">
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label">轮播图</label>
	                        <div class="col-sm-9" id="listImageUrlList">
	                      		<c:if test="${not empty imageList1}">
	                           		<ul class="ace-thumbnails">
	                           			<c:forEach items="${imageList1}" var="img">
	                                 		<li class="upload-imgs">
	                                 			<a href="${img.url}" data-rel="colorbox">
			                                 		<img src="${img.url}"/>
	                                 			</a>
		                              		</li>
	                             		</c:forEach>
	                           		</ul>
	                          	</c:if>
	                        </div>
	                    </div>
	                    <div class="form-group linkControl">
	                        <label class="col-sm-3 control-label">户型图片</label>
	                        <div class="col-sm-9" id="imgagesThum">
	                      		<c:if test="${not empty imageList2}">
	                           		<ul class="ace-thumbnails">
	                             		<c:forEach items="${imageList2}" var="img">
	                                 		<li class="upload-imgs">
	                                 			<a href="${img.url}" data-rel="colorbox">
			                                 		<img src="${img.url}"/>
	                                 			</a>
								 		 		<div class="imgDesc">
								 		 			<input type="hidden" name="images" value="${img.url }">
								 		 			<input type="text" readonly="readonly" name="descType" value="${img.descType }" class="form-control" placeholder="">
								 		 			<input type="text" readonly="readonly" name="descArea" value="${img.descArea }" class="form-control" placeholder="">
								 		 		</div>
		                              		</li>
	                             		</c:forEach>
	                           		</ul>
	                          	</c:if>
	                        </div>
	                    </div>
	                    
	                    
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">状态</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${allStatuss[item.status]}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">创建时间</label>
	                        <div class="col-sm-9">
	                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">创建人名称</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.creator}">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">更新时间</label>
	                        <div class="col-sm-9">
	                        	<input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="<fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" />">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-3 control-label no-padding-right">更新人</label>
	                        <div class="col-sm-9">
                                <input readonly="" type="text" class="col-xs-10 form-control col-sm-5" value="${item.updator}">
	                        </div>
	                    </div>
			            <c:if test="${empty _model_ }">
			            <div class="form-group">
							<div class="col-sm-12">
								<div class="pull-right">
									<button class="btn btn-sm btn-danger" type="button" id="backBtn" backRestoreParam="backRestoreParam" back_url="/module/building/building_info_list.html">
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
require(['/resource/business/building/building_info_view.js'], function(obj){
	obj.init('${item.lat}', '${item.lng}');
});
</script>