<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script>

    require(['angular', 'angular.ui.tree', 'validator.bootstrap', 'underscore', 'util','cqliving_dialog'], function(angular, tree, $, _, util,cqliving_dialog){
    	
    		$("#form1").validate({
    	        rules: {
    	            roleName : {
    	                required: true
    	               }
    	        },
    	        messages: {
    	            roleName : {
    	                required: ' '
    	            }
    	        }
    	    });
    	
        angular.module('app', ['ui.tree']).controller('rescSelector', function($scope, $http){
                    $scope.rescs=${rescJson};
                    $scope.rescIds=[<c:forEach var="res" items="${item.rescs}" varStatus="status">${res.id}<c:if test="${fn:length(item.rescs)-1 > status.index}">,</c:if></c:forEach>];

                    $scope.iteratesResc = function(rescs, callback, parent, level){
                        level = level ? 1 : level+1;
                        for(var j=0; j<rescs.length; j++){
                            var val = rescs[j];
                            
                            if(!val.subResource)val.subResource=[];
                            
                            callback(val, j, parent, level);
                            if(val.subResource.length>0){
                                $scope.iteratesResc(val.subResource, callback, val, level);
                            }
                        }
                    }

                    $scope.iteratesResc($scope.rescs, function (val, index, parent) {
                        val.check=$scope.rescIds.indexOf(val.id)!=-1;
                    });

                    $scope.checkResc = function(resc){
                    	
                        if(resc.check==null || resc.check==false){
                        	
                            //选中当前节点及父节点
                            if($scope.rescIds.indexOf(resc.id)==-1) {
                                $scope.rescIds.push(resc.id);
                            }
                            resc.check=true;
                            if(resc.parentId!=0){
                                var pId=resc.parentId;
                                while(pId!=0) {
                                    $scope.iteratesResc($scope.rescs, function (val, index, parent) {
                                        if (val.id == pId) {
                                            if($scope.rescIds.indexOf(val.id)==-1) {
                                                $scope.rescIds.push(val.id);
                                            }

                                            val.check = true;
                                            pId = val.parentId;
                                        }
                                    });
                                }
                            }
                        }else{
                            //取消当前节点及子节点
                            $scope.rescIds = _.without($scope.rescIds, resc.id);
                            resc.check=false;
                            if(resc.subResource.length>=1) {
                                $scope.iteratesResc(resc.subResource, function (val) {
                                    val.check = false;
                                    $scope.rescIds = _.without($scope.rescIds, val.id);
                                });
                            }
                        }
                    }

        });
        angular.bootstrap(document.getElementById("ngapp"), ['app']);
    });
</script>

<style>
    .angular-ui-tree-handle {
        padding-top: 3px;
    }
</style>
<div class="main-content">
	<jsp:include page="${pageContext.request.contextPath}/WEB-INF/jsp/common_ace/breadcrumbs.jsp">
	  <jsp:param value="角色管理|/module/security/sys_role_list.html" name="_breadcrumbs_1"/>
	  <jsp:param value="${not empty item ? '修改' : '新增' }" name="_breadcrumbs_3"/>
	</jsp:include>
	<div class="page-content">
		<div class="row">
			<div class="col-xs-12 version-detail">
		        <form class="form-horizontal form" method="post" id="form1">
		            <c:if test="${not empty item}">
		            <input type="hidden" name="id" value="${item.id}" />
		            </c:if>
		            
	                    <div class="form-group">
	                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">角色名称</label>
	                        <div class="col-sm-9">
	                            <input type="text" class="form-control width-80" id="roleName" name="roleName" placeholder="请输入角色名称"  value="${item.roleName}" maxlength="16">
	
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">描述信息</label>
	                        <div class="col-sm-9">
	                            <textarea name="descn" rows="5" class="form-control width-80" id="descn" placeholder="请输入描述信息">${item.descn}</textarea>
	
	                        </div>
	                    </div>
	                    
	                    <input type="hidden" name="type" value="2">
	                    
	                    <div class="form-group   ${empty item and empty session_user_obj.appId ? '' : 'hidden'}">
	                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">所属APP</label>
	                        <div class="col-sm-9">
	                            <select name="appId" class="form-control width-80 chosen-select">
	                               <option value="">系统管理员</option>
	                               <c:forEach items="${allApps}" var="app">
	                                  <option value="${app.id }" <c:if test="${item.appId eq app.id or app.id eq session_user_obj.appId}">selected</c:if>  >${app.name}</option>
	                               </c:forEach>
	                            </select>
	                        </div>
	                    </div>
	                    <div class="form-group ng-scope ng-cloak" id="ngapp">
	                        <script type="text/ng-template" id="nodes_renderer.html">
                            <div ui-tree-handle class="tree-node tree-node-content">
                                <%--<a class="btn btn-success btn-xs" data-nodrag ng-click="toggle(this)"><span class="glyphicon" ng-class="{'glyphicon-chevron-right': collapsed, 'glyphicon-chevron-down': !collapsed}"></span></a>--%>
                                <a href="javascript:;" ng-click="checkResc(resc)" ng-class="{'btn-success' : resc.check, 'btn-default' : !resc.check}" class="btn btn-sm">{{resc.title}}</a>
                            </div>
                            <ol ui-tree-nodes="" ng-model="resc.subResource" ng-class="{hidden: collapsed}">
                                <li ng-repeat="resc in resc.subResource" ui-tree-node ng-include="'nodes_renderer.html'">
                                </li>
                            </ol>
                        </script>
	                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">拥有的资源</label>
	                        <div class="col-sm-9" ng-controller="rescSelector">
	                            <input type="hidden" name="resIds[]" ng-repeat="rescId in rescIds" ng-model="rescId" value="{{rescId}}" />
	                            <div class="panel-body" id="tree-root" ui-tree data-drag-enabled="false">
	                                <ol ui-tree-nodes ng-model="rescs">
	                                    <li ng-repeat="resc in rescs" ui-tree-node ng-include="'nodes_renderer.html'"></li>
	                                </ol>
	                            </div>
	                        </div>
	                    </div>
		            
		            <div class="form-group col-xs-12">
						<div class=" text-right">
							<button class="btn btn-success btn-sm commonSaveButton" type="button" back_url="/module/security/sys_role_list.html">
								<i class="icon-save bigger-110"></i>提交
							</button>
							&nbsp;
							<button class="btn btn-sm btn-primary" type="button" onclick="javascript:location.href='/module/security/sys_role_list.html'">
								<i class="icon-undo bigger-110"></i>返回
							</button>
						</div>
					</div>
		        </form>
		    </div>
		</div>
	</div>
</div>
<script type="text/javascript">

require(['chosen','cloud.table.curd'],function(c,curd){
	    
	   curd.initTableCrud();
	
	   $(".chosen-select").chosen({search_contains:true});
});
</script>

