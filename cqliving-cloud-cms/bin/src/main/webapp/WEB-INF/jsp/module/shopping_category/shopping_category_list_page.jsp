<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="col-xs-12 col-sm-12 col-lg-12">
    <script type="text/javascript">
        require(['angular', 'angular.ui.tree', 'jquery', 'underscore', 'util','cqliving_dialog','cqliving_ajax'], function(angular, tree, $, _, util,cqliving_dialog,cq_ajax){
            angular.module('app', ['ui.tree'], function($httpProvider) {
                <%-- angular 的 http post ， spring controller 不能正常接收参数， 通过 此方法扩展，在 post 用 jquery.param(json) 序列化 json 参数后可 controller 正常接收参数--%>
                // Use x-www-form-urlencoded Content-Type
                $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
                // 设置为ajax提交
                $httpProvider.defaults.headers.common['x-requested-with'] = 'XMLHttpRequest';
                /**
                 * The workhorse; converts an object to x-www-form-urlencoded serialization.
                 * @param {Object} obj
                 * @return {String}
                 */
                var param = function(obj) {
                    var query = '', name, value, fullSubName, subName, subValue, innerObj, i;

                    for(name in obj) {
                        value = obj[name];
                        if(value instanceof Array) {
                            for(i=0; i<value.length; ++i) {
                                subValue = value[i];
                                fullSubName = name + '[' + i + ']';
                                innerObj = {};
                                innerObj[fullSubName] = subValue;
                                query += param(innerObj) + '&';
                            }
                        }
                        else if(value instanceof Object) {
                            for(subName in value) {
                                subValue = value[subName];
                                fullSubName = name + '[' + subName + ']';
                                innerObj = {};
                                innerObj[fullSubName] = subValue;
                                query += param(innerObj) + '&';
                            }
                        }
                        else if(value !== undefined && value !== null)
                            query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
                    }

                    return query.length ? query.substr(0, query.length - 1) : query;
                };

                // Override $http service's default transformRequest
                $httpProvider.defaults.transformRequest = [function(data) {
                    return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
                }];
            }).controller('categoryDesigner', function($scope, $http){

                $scope.categoryList = ${categoryList};
                $scope.activeCategory = {};
                $scope.copyActiveCategory={};
                $scope.dialog={title:""};

                var getRootNodesScope = function() {
                    return angular.element(document.getElementById("tree-root")).scope();
                };

                $scope.collapseAll = function() {
                    var scope = getRootNodesScope();
                    scope.collapseAll();
                };

                $scope.expandAll = function() {
                    var scope = getRootNodesScope();
                    scope.expandAll();
                };

                $scope.hasSubCategory = function(category){
                	if(category && category.subs && category.subs.length>=1){
                		return true;
                	}
                    return false;                	
                }
                
                // 重置对象
                $scope.resetDialog = function (){
                    for(key in $scope.activeCategory){
                        $scope.activeCategory[key] = $scope.copyActiveCategory[key];
                    }
                }
                
                //刷新页面
                $scope.refreshTree = function (){
                	$("#shopping_category_FormId").submit();
                }
                
				// 查看
                $scope.showViewDialog = function(category){
                	var url = "${pageContext.request.contextPath}/module/shopping_category/shopping_category_update.html";
                	url+="?id="+category.id;
                	window.location.href=url;
                }
				
                /**
                 * 保存
                 */
                $scope.saveCategory = function(valid) {
                    if (valid == false) {
                        return;
                    }
                    var category = {
                        id : $scope.activeCategory.id,
                        parentId : $scope.activeCategory.parentId,
                        appId : $scope.activeCategory.appId,
                        level : $scope.activeCategory.level,
                        name : $scope.activeCategory.name
                    };                    
                    
                    if($scope.activeCategory.id==""){
                        //增加
                        $http.post('${pageContext.request.contextPath}/module/shopping_category/shopping_category_add.html',
                                $.param(category)).success(
                                function(dat, status){
                                    if(dat.code>=0) {
                                    	cqliving_dialog.success('增加商品分类成功',function(){
                                			$("#shopping_category_FormId").submit();
                                		});
                                    } else {
                                        if(util.isAuthCode(dat.code)){
                                            cqliving_dialog.error(dat.message,function(){
                                    			$("#shopping_category_FormId").submit();
                                    		});
                                        } else {
                                            cqliving_dialog.error('增加商品分类失败',function(){
                                    			$("#shopping_category_FormId").submit();
                                    		});
                                        }

                                    }
                                });
                    }else{
                        //修改
                        $http.post('${pageContext.request.contextPath}/module/shopping_category/shopping_category_update.html',
                                $.param(category)).success(
                                function(dat, status){
                                    if(dat.code<0) {
                                        if(util.isAuthCode(dat.code)){
                                        	cqliving_dialog.error(dat.message,function(){
                                    			$("#shopping_category_FormId").submit();
                                    		});
                                        } else {
                                            cqliving_dialog.error('编辑商品分类失败',function(){
                                    			$("#shopping_category_FormId").submit();
                                    		});
                                        }
                                    }else{
                                    	cqliving_dialog.success('编辑商品分类成功',function(){
                                			$("#shopping_category_FormId").submit();
                                		});
                                    }
                                    
                                });
                    }
                    $('#dialog').modal('hide');
                }
                
              	//打开操Dialog
                $scope.showDetailDialog = function(activeCategory) {
                    $scope.activeCategory=activeCategory;
                    if(activeCategory.id==""){
                        $scope.dialog.title="增加商品分类";
                    }else{
                        $scope.dialog.title="编辑商品分类";
                    }
                    $('#dialog').modal('show');
                };

                /* 
               	 * 增加菜单 
               	 */
                $scope.addCategory = function(category) {
                	if(category){
	                	var parentId=category.parentId;
	                	if(parentId!=0){
	                		cqliving_dialog.alert("只能增加到二级分类");
	                		return false;
	                	}
	                	var appid = category.appId;
	                	var parentId = category.id;
	                	var level = category.level;
	                	if(level){
	                		level = parseInt(level)+1;
	                	}else{
	                		level = 1;
	                	}
	        			$scope.activeCategory={
	                        id: "", //ID
	                        appId: appid, //客户端Id
	                        parentId:parentId,//父Id
	                        level:level
	                    };
                	}else{
                		var appid=$('#search_EQ_appId').val();
                		$scope.activeCategory={
  	                        id: "", //ID
  	                        appId: appid //客户端Id
    	                };
                	}
        			$scope.showDetailDialog($scope.activeCategory);
                };

                $scope.iteratesCategory = function(categorys, callback, parent, level){
                    level = level ? 1 : level+1;
                    for(var j=0; j<categorys.length; j++){
                        var val = categorys[j];

                    	if(!val.subs){
                    		val.subs = [];
                    	}
                        
                        callback(val, j, parent, level);
                        if(val.subs.length>0){
                            $scope.iteratesCategory(val.subs, callback, val, level);
                        }
                    }
                }

                $scope.delCategory = function(category){
                	
                	if(!confirm('将删除分类，'+(category.subs && category.subs.length > 0 ? '同时删除所有子分类，' : '') + '是否继续？ ')){
                        return;
                    }
                    var params={ids:[category.id]};
                    
                    if(category.subs){
                    	$scope.iteratesCategory(category.subs, function(dto){
                            params.ids.push(dto.id);
                        });
                    }
                    $http.post('${pageContext.request.contextPath }/module/shopping_category/shopping_category_delete.html',$.param(params)).success(
	                      function(dat, status){
	                          if(dat.code>=0){
	                        	  $("#shopping_category_FormId").submit();
	                          }else{
	                              if(dat.code){
	                            	  cqliving_dialog.error(dat.message);
	                              } else {
	                            	  cqliving_dialog.error("删除失败");
	                              }
	                          }
                      });
                }

				/**
				 * 递归查询有多少个子节点
				 */
                $scope.iteratesLi = function(li,i){
               		subLi = $(li).find(".angular-ui-tree-node");
               		if(subLi&&subLi.length>0){
               			i++;
               			var j ;
               			$.each(subLi, function(idx, li){
	               			j = i;
                    		j = $scope.iteratesLi(li,j);
                    		if(j>2){
                    			return false;
                    		}
            			});
               			if(j>2){
                			return j;
                		}
               		}
               		return i;
                }
				
                /**
                 * 排序
                 */
                $scope.treeOptions = {
                    dragStop: function(event) {
                    	var lis = $(".angular-ui-tree-node");
                    	var i = 1;
                    	var flag = false;
                    	$.each(lis, function(idx, li){
                    		i = 1;
                    		i = $scope.iteratesLi(li,i);
                    		if(i>2){
                    			flag = true;
                    			return false;
                    		}
            			});
                    	if(flag){
//                     		util.message('不能移动到2级分类下，请刷新页面！', '', 'error');
                    		cqliving_dialog.error("不能移动到2级分类下",function(){
                    			$("#shopping_category_FormId").submit();
                    		});
                    		return false;
                    	}
                        var plist={ids:[], sortNums:[], parentIds:[]};
                        $scope.iteratesCategory($scope.categoryList, function(category, index, parent){
                            category.sortNum=index+1;
                            category.parentId=parent ? parent.id : 0;
                            plist.ids.push(category.id);
                            plist.sortNums.push(category.sortNum);
                            plist.parentIds.push(category.parentId);
                        });
                        
                        $http.post('${pageContext.request.contextPath }/module/shopping_category/common/update_sort.html',
                                $.param(plist)).success(
                                function(dat, status){
                                    if(dat.code<0){
                                        if(util.isAuthCode(dat.code)){
                                            util.message(dat.message, '', 'error');
                                        } else {
                                            util.message('移动分类失败', '', 'error');
                                        }
                                    }else{
                                    	$("#searchButton").click();
                                    }
                                });
                    }
                /*,
                    beforeDrag:function(sourceNodeScope){
                    	//顶级栏目不能移动
                    	var source = sourceNodeScope.category;
                    	if(!source.parentId || source.parentId == 0){
                    		return false;
                    	}
                    	return true;
                    }*/
                };

            });
            angular.bootstrap(document, ['app']);
            $(function(){
                $('#dialog').modal({backdrop: 'static', keyboard: false, show: false});
            });
        });

    </script>

    <style>
        .btn {
            margin-right: 8px;
        }

        .angular-ui-tree-handle {
            background: #f8faff;
            border: 1px solid #dae2ea;
            color: #7c9eb2;
            padding: 6px 7px;
        }

        .angular-ui-tree-handle:hover {
            color: #438eb9;
            background: #f4f6f7;
            border-color: #dce2e8;
        }

        .angular-ui-tree-placeholder {
            background: #f0f9ff;
            border: 2px dashed #bed2db;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }
    </style>

    <script type="text/ng-template" id="nodes_renderer.html">
        <div ui-tree-handle class="tree-node tree-node-content angular-ui-tree-handle {{hasSubCategory(category) ? 'group-title' : 'category-title'}}" categoryId="{{category.name}}">
            <a class="btn btn-success btn-xs" data-nodrag ng-click="toggle(this)"  ng-if="hasSubCategory(category)"><span class="glyphicon" ng-class="{'glyphicon-chevron-right': collapsed, 'glyphicon-chevron-down': !collapsed}"></span></a>
                       {{category.name}} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <cqliving-security2:hasPermission name="/module/shopping_category/shopping_category_delete.html">
            	<a title="删除" class="pull-right btn btn-danger btn-xs" data-nodrag ng-click="delCategory(category);"><i class="icon-trash bigger-130"></i></a>
            </cqliving-security2:hasPermission>
            <cqliving-security2:hasPermission name="/module/shopping_category/shopping_category_update.html">
            <a title="修改" class="pull-right btn btn-primary btn-xs" data-nodrag ng-click="showDetailDialog(category);"><i class="icon-pencil bigger-130"></i></a>
            </cqliving-security2:hasPermission>
            
            <cqliving-security2:hasPermission name="/module/shopping_category/shopping_category_view.html">
            <a title="查看" class="pull-right btn btn-primary btn-xs" data-nodrag ng-click="showViewDialog(category);" style="margin-right: 8px;"><i class="icon-zoom-in bigger-130"></i></a>
            </cqliving-security2:hasPermission>
        </div>
        <ol ui-tree-nodes="" ng-model="category.subs" ng-class="{hidden: collapsed}">
            <li ng-repeat="category in category.subs" ui-tree-node ng-include="'nodes_renderer.html'">
            </li>
        </ol>
    </script>
    <div class="clearfix">
        <div class="ng-scope ng-cloak" ng-controller="categoryDesigner">
            <div class="panel panel-default">
                <div class="panel-body" id="tree-root" ui-tree="treeOptions">
            		<a href="" class="btn btn-sm btn-primary" ng-click="collapseAll()">全部收缩</a>
                    <a href="" class="btn btn-sm btn-primary" ng-click="expandAll()">全部展开</a>
                    <cqliving-security2:hasPermission name="/module/shopping_category/shopping_category_add.html">
	                    <a href="" class="btn btn-sm btn-success" ng-click="addCategory('');">新增分类</a>
					</cqliving-security2:hasPermission>
					<c:if test="${not empty categoryList and fn:length(categoryList)>2}">
	                    <ol ui-tree-nodes ng-model="categoryList">
	                        <li ng-repeat="category in categoryList" ui-tree-node ng-include="'nodes_renderer.html'"></li>
	                    </ol>
                    </c:if>
                </div>
            </div>
			<div id="dialog" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form class="form-horizontal form" method="post" id="form1" name="form1" ng-submit="saveCategory(form1.$valid)"  novalidate>
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true" ng-click="refreshTree();">×</button>
                                <h3 class="ng-binding">{{dialog.title}}</h3>
                            </div>
                            <div class="modal-body">
                            	<input type="hidden" name="id" value="${activeCategory.id}" />
				            	<input type="hidden" name="parentId" value="${activeCategory.parentId}" />
			                    <input name="appId" id="appId" type="hidden" value="${activeCategory.appId}">
			                    <input name="level" id="level" type="hidden" value="${activeCategory.level}">
			                    <div class="form-group">
			                        <label class="col-xs-12 col-sm-3 col-md-2 control-label">名称<i class="text-danger">*</i></label>
			                        <div class="col-sm-9" ng-class="{ 'has-error' : form1.title.$invalid && !form1.title.$pristine }">
                                        <input type="text" class="form-control"  id="name" name="name" maxlength="50" placeholder="请输入名称" required ng-model="activeCategory.name">
                                        <p ng-show="form1.title.$invalid && !form1.title.$pristine" class="help-block">名称必须输入.</p>
                                    </div>
			                    </div>
                            </div>
                            <div class="modal-footer">
                                <button type="submit" class="btn btn-primary" ng-disabled="form1.$invalid" >保存</button>
                                <button type="button" class="btn btn-sm btn-default" data-dismiss="modal" ng-click="refreshTree();">取消</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 错误信息 -->
    <div id="errorinfo"></div>
</div>
