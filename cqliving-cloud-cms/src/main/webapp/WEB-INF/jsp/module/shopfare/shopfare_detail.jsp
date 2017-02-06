<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<style type="text/css">
.width-10{
   width:150px;
}
.width_70{
   width:70px;
}
</style>

<div class="main-content" ng-controller="shopfareTemplateController">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
		<jsp:param value="运费模板明细表列表|shopfare_list.html" name="_breadcrumbs_1"/>
		<jsp:param value="${empty item ? '新增' : '编辑'}" name="_breadcrumbs_2"/>
	</jsp:include>
	<div class="page-content" ng-cloak>
		<div class="row">
			<div class="col-xs-12">
				<%-- 详细 --%>
		        <form class="form-horizontal form" method="post" name="form1" ng-submit="saveTmp(form1.$valid)"  novalidate>
		        	<div class='col-md-12'>
	                    <div class="form-group  ${not empty item or  fn:length(allApps) le 1 ? 'hidden' : ''}">
	                        <label class="col-sm-2 control-label no-padding-right" for="appId">所属客户端<i class="text-danger">*</i></label>
	                        <div class="col-sm-9">
	                            <select name="appId" id="appId" class="chosen-select" ng-model="fareTemplate.appId">
							       <c:forEach items="${allApps}" var="app">
								       <option value="${app.id }" ${defaultAppId eq app.id ? 'selected' : '' }>${app.name }</option>
							       </c:forEach>
							    </select>
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="name">模板名称<i class="text-danger">*</i></label>
	                        <div class="col-sm-9" ng-class="{ 'has-error' : form1.name.$invalid }">
	                            <input class="form-control width-50" name="name" ng-model="fareTemplate.name" type="text" value="{{fareTemplate.name}}" maxlength="19" placeholder="请输入运费模板名称">
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="">默认运费<i class="text-danger">*</i></label>
	                        <div class="col-sm-9" ng-class="{'has-error':form1.baseFareCount.$invalid || form1.baseFare.$invalid || form1.addFareCount.$invalid || form1.addFare.$invalid}">
	                            <input class="width-10" name="baseFareCount"  type="text" value="{{defaultFare.baseFareCount}}" maxlength="19" placeholder="请输入首件数量" ng-model="defaultFare.baseFareCount" required>
	                                                      件内<input class="width-10" name="baseFare" type="text" value="{{defaultFare.baseFare}}" maxlength="19" placeholder="请输入首件运费" ng-model="defaultFare.baseFare" required>
	                                               元,每增加 <input class="width-10" name="addFareCount" type="text" value="{{defaultFare.addFareCount}}" maxlength="19" placeholder="请输入续件数量" ng-model="defaultFare.addFareCount" required>
	                                                   增加   <input class="width-10" name="addFare" type="text" value="{{defaultFare.addFare}}" maxlength="19" placeholder="请输入续件运费" ng-model="defaultFare.addFare" required>
	                                                    元
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                        <label class="col-sm-2 control-label no-padding-right" for="fareTemplateId">新增运费</label>
	                        <div class="col-sm-9">
	                            <button class="btn btn-primary btn-xs" type="button" ng-click="modelShow()">添加指定区域运费</button>
	                        </div>
	                    </div>
	                    
	                    <div class="form-group">
	                       <label class="col-sm-2 control-label no-padding-right" for="fareTemplateId">运费<i class="text-danger">*</i></label>
	                       <div class="col-sm-9">
		                    <table class="table table-striped table-bordered table-hover">
									<thead class="thin-border-bottom">
										<tr>
					                		<th class="width-90">运送到</th>
					                		<th>首件(件)</th>
					                		<th>运费</th>
					                		<th>续件(件)</th>
					                		<th>运费</th>
					                		<th class="width-40">操作</th>
				                        </tr>
									</thead>
									<tbody>
					                    <tr ng-repeat="detail in fareTemplate.tempDetails">
					                    	<td>{{detail.regionNames}}<button type="button" class="btn btn-primary btn-xs" role="button" ng-click="modelShow(detail)">编辑</button> </td>
					                		<td ng-class="{'has-error':form1.baseFareCount{{$index}}.$invalid}"><input type="text" class="width_70"  name="baseFareCount{{$index}}" value="{{detail.baseFareCount}}" placeholder="请输入首件数量" ng-model="detail.baseFareCount"  required/></td>
					                		<td ng-class="{'has-error':form1.baseFare{{$index}}.$invalid}"><input type="text" class="width_70" name="baseFare{{$index}}" value="{{detail.baseFare}}" placeholder="请输入首件运费" ng-model="detail.baseFare"  required/> </td>
					                		<td ng-class="{'has-error':form1.addFareCount{{$index}}.$invalid}"><input type="text" class="width_70"  name="addFareCount{{$index}}" value="{{detail.addFareCount}}" placeholder="请输入续件数量" ng-model="detail.addFareCount"  required/> </td>
					                		<td ng-class="{'has-error':form1.addFare{{$index}}.$invalid}"><input type="text" class="width_70" name="addFare{{$index}}" value="{{detail.addFare}}" placeholder="请输入续件运费" ng-model="detail.addFare"  required/> </td>
					                		<td>
					                        	<a href="javascript:;" class="red" ng-click="removeDetail($index)"> 删除 </a>
					                        </td>
					                    </tr>
									</tbody>
							</table>
						  </div>
	                    </div>
	                	<div class="form-group">
							<div class="col-sm-12">
							    <label class="col-sm-9 control-label no-padding-right"></label>
								<div class="col-sm-2">
									<button class="btn btn-success btn-sm" type="botton" back_url="/module/shopfare/shopfare_list.html">
										<i class="icon-save bigger-110"></i>保存
									</button>
									&nbsp;
									<button class="btn btn-sm btn-danger" type="button" backRestoreParam="backRestoreParam" back_url="/module/shopfare/shopfare_list.html">
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
<!-- 增加及修改 -->
<div id="add_dialog" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal form" method="post">
				<div class="form-group">
					<div class="col-sm-12">
						<div class="tabbable">
							<div class="multiselectForm">
								<div id="content" class="widget-box no-margin no-padding tab-pane">
									<div class="widget-header widget-header-flat">
										<h4 class="smaller">选择地区</h4>
									</div>
									<div class="widget-body">
										<div class="widget-main">
											<ul class="list-unstyled">
												<li ng-repeat="r in regionJson">
													<div class="ecity gcity">
														<span class="checkbox-2">
															<input type="checkbox" value="{{r.code}}" ng-click="changeArea($event.target,r)"/><b class="lbl">{{r.name}}</b>
														</span>
													</div>
													<div class="provinceList">
														<div class="ecity" ng-repeat="region in r.region">
															<span class="checkbox-2">
																<input type="checkbox" value="{{region.code}}" ng-click="changeCheck($event.target,region)">
																<textarea class="hidden">{{region | removeSubRegion}}</textarea>
																<span class="lbl">{{region.name}}<span class="cityNumber">(0)</span>
																<b class="arrow icon-angle-down"></b></span>
															</span>
															<div class="checkMore">
																<span class="checkbox-2" ng-repeat="subr in region.subRegion">
																   <input type="checkbox" value="{{subr.code}}" ng-click="changeCheck($event.target,subr)">
																   <textarea class="hidden">{{subr | removeSubRegion}}</textarea>
																   <span class="lbl">{{subr.name}}</span>
																</span>
																<br />
																<a class="btn btn-minier btn-danger cityClose">关闭</a>
															</div>
														</div>
													</div>
												</li>
											</ul>
										</div>
									</div>
									<div class="space-6"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
        </div>
       <div class="modal-footer">
           <button type="button" class="btn btn-sm btn-primary" ng-click="addRegion()">保存</button>
           <button type="button" class="btn btn-sm btn-default" data-dismiss="modal">取消</button>
       </div>
    </div>
</div>
</div>

<script type="text/javascript">
 var fareTemplateJson = ${itemJson};
 var regionJson = ${optionDtosJson};
 require(['/resource/business/shopping/shopping_fare_template.js?v=v3'],function(fare){
	 
 });
</script>