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
            }).controller('resourceDesigner', function($scope, $http){

                $scope.resources = ${resources};
                $scope.allColumnsTypes = ${allColumnsTypes};
                $scope.viewMap = ${viewMap};
                $scope.activeResource = {};
                $scope.copyActiveResource={};
                $scope.dialog={title:""};

                $scope.resTypes=[
//                     {title : "连接", value:"URL"},
//                     {title : "按钮", value:"BUTTON"}
                ]

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

                $scope.hasSubResourse = function(resc){
                	
                	if(resc && resc.subs && resc.subs.length>=1){
                		return true;
                	}
                    return false;                	
                }
                
                // 重置对象
                $scope.resetDialog = function (){
                    for(key in $scope.activeResource){
                        $scope.activeResource[key] = $scope.copyActiveResource[key];
                    }
                }
				// 查看
                $scope.showViewDialog = function(resc){
                	var url = "${pageContext.request.contextPath}/module/columns/app_columns_update.html";
                	url+="?id="+resc.id;
                	window.location.href=url;
                }

                //修改
                $scope.showDetailDialog = function(resc) {
                	var url = "${pageContext.request.contextPath}/module/columns/app_columns_update.html";
                	url+="?id="+resc.id;
                	window.location.href=url;
                };

                //增加菜单
                $scope.addColumns = function(resource) {
                	var pcode=resource.parentCode+"";
//                 		alert(pcode+"   "+pcode.split('.').length);
                	if(pcode&&pcode.split('.').length>=2){
                		cqliving_dialog.alert("只能增加到三级栏目");
                		return false;
                	}
                	var url = "${pageContext.request.contextPath}/module/columns/app_columns_add.html";
                	url+="?parentId="+resource.id;
                	url+="&parentCode="+resource.parentCode;
                	url+="&appId="+resource.appId;
                	window.location.href=url;
                };

                $scope.iteratesResc = function(rescs, callback, parent, level){
                    level = level ? 1 : level+1;
                    for(var j=0; j<rescs.length; j++){
                        var val = rescs[j];

                    	if(!val.subs){
                    		val.subs = [];
                    	}
                        
                        callback(val, j, parent, level);
                        if(val.subs.length>0){
                            $scope.iteratesResc(val.subs, callback, val, level);
                        }
                    }
                }

                $scope.saveResc = function(valid) {
                    if (valid == false) {
                        //console.log(valid);
                        return;
                    }
                    var resource = {
                        id : $scope.activeResource.id,
                        parentId : $scope.activeResource.parentId,
                        title : $scope.activeResource.title,
                        restype : $scope.activeResource.restype,
                        resString : $scope.activeResource.resString,
                        permissionValue : $scope.activeResource.permissionValue,
                        descn : $scope.activeResource.descn,
                        status : $scope.activeResource.status,
                        sortNum : $scope.activeResource.sortNum
                    };
                    if($scope.activeResource.id==""){
                    }else{
                    }
                    //console.log($scope.activeResource);
                    $('#dialog').modal('hide');
                }

                $scope.deleteResc = function(resource){
					var sub = resource.subs;
					if(sub){
                		cqliving_dialog.alert("非最底层栏目不能删除！");
                		return;
                	}
                	var jThis = $(this);
        			var url = '${pageContext.request.contextPath }/module/columns/app_columns_delete.html?id='+resource.id ;
        			cqliving_dialog.confirm('操作确认','确定要删除该记录吗？',function(){
        				cq_ajax.ajaxOperate(url,jThis,{},function(data,status){
        					if(data.code >= 0){
        						cqliving_dialog.success(data.message?data.message:"删除成功",function(){
        							$("#submitBtn").trigger("click");
        						});
//         						$("#searchButton").trigger("click");
								/* var appId = $('#search_EQ_appId').val();
								var url = '${pageContext.request.contextPath }/module/columns/app_columns_list.html';
								if(url){
									url += '?search_EQ_appId='+appId;
								}
        						window.location.href=url; */
        					}else{
        						cqliving_dialog.alert(data.message);
        					}
        				});
        			});
                }


                //排序
                $scope.treeOptions = {
                	accept:function(sourceNodeScope, destNodesScope, destIndex){
                		
                		var source = sourceNodeScope.resc;
                		var des = destNodesScope.resc;
                		if(source && des && source.parentCode.split(".").length == des.code.split(".").length){
                			return true;
                		}else{
                			return false;
                		}
                		
                	},
                    dragStop: function(event) {
                        var plist={ids:[], sortNums:[], parentIds:[]};
                        $scope.iteratesResc($scope.resources, function(resc, index, parent){
                            resc.sortNum=index+1;
                            resc.parentId=parent ? parent.id : 0;
                            plist.ids.push(resc.id);
                            plist.sortNums.push(resc.sortNum);
                            plist.parentIds.push(resc.parentId);
                            /* if(resc.subs){
                            	$scope.iteratesResc(resc.subs, function(sub, index, parent){
                                    sub.sortNum=index+1;
                                    sub.parentId=resc.id;
                                    plist.ids.push(sub.id);
                                    plist.sortNums.push(sub.sortNum);
                                    plist.parentIds.push(sub.parentId);
                                });
                            } */
                        });
                        $http.post('${pageContext.request.contextPath }/module/columns/update_sort.html',
                                $.param(plist)).success(
                                function(dat, status){
                                    if(dat.code<0){
                                        if(util.isAuthCode(dat.code)){
                                            util.message(dat.message, '', 'error');
                                        } else {
                                            util.message('移动栏目失败', '', 'error');
                                        }
                                    }else{
                                    	$("#searchButton").click();
                                    }
                                });
                    },
                    beforeDrag:function(sourceNodeScope){
                    	//顶级栏目不能移动
                    	var source = sourceNodeScope.resc;
                    	if(!source.parentId || source.parentId == 0){
                    		return false;
                    	}
                    	return true;
                    }
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
        <div ui-tree-handle class="tree-node tree-node-content angular-ui-tree-handle {{hasSubResourse(resc) ? 'group-title' : 'category-title'}}" rescId="{{resc.name}}">
            <a class="btn btn-success btn-xs" data-nodrag ng-click="toggle(this)"  ng-if="hasSubResourse(resc)"><span class="glyphicon" ng-class="{'glyphicon-chevron-right': collapsed, 'glyphicon-chevron-down': !collapsed}"></span></a>
                        栏目名称【{{resc.name}}】 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        栏目类型【{{allColumnsTypes[resc.columnsType]}}】 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <cqliving-security2:hasPermission name="/module/columns/app_columns_delete.html">
            	<a ng-if="resc.parentId!=0" title="删除" class="pull-right btn btn-danger btn-xs" data-nodrag ng-click="deleteResc(resc);"><i class="icon-trash bigger-130"></i></a>
            </cqliving-security2:hasPermission>
            <cqliving-security2:hasPermission name="/module/columns/app_columns_update.html">
            <a title="修改" class="pull-right btn btn-primary btn-xs" data-nodrag ng-click="showDetailDialog(resc);"><i class="icon-pencil bigger-130"></i></a>
            </cqliving-security2:hasPermission>
            <cqliving-security2:hasPermission name="/module/columns/app_columns_add.html">
            <a title="新增子栏目" class="pull-right btn btn-primary btn-xs" data-nodrag ng-click="addColumns(resc);"><span class="glyphicon glyphicon-plus"></span></a>
            </cqliving-security2:hasPermission>
            <cqliving-security2:hasPermission name="/module/columns/app_columns_view.html">
            <a title="查看" class="pull-right btn btn-primary btn-xs" data-nodrag ng-click="showViewDialog(resc);" style="margin-right: 8px;"><i class="icon-zoom-in bigger-130"></i></a>
            </cqliving-security2:hasPermission>
        </div>
        <ol ui-tree-nodes="" ng-model="resc.subs" ng-class="{hidden: collapsed}">
            <li ng-repeat="resc in resc.subs" ui-tree-node ng-include="'nodes_renderer.html'">
            </li>
        </ol>
    </script>
    <div class="clearfix">
        <div class="ng-scope ng-cloak" ng-controller="resourceDesigner">
            <div class="panel panel-default">
                <div class="panel-body" id="tree-root" ui-tree="treeOptions">
            		<a href="" class="btn btn-sm btn-primary" ng-click="collapseAll()">全部收缩</a>
                    <a href="" class="btn btn-sm btn-primary" ng-click="expandAll()">全部展开</a>
                    <ol ui-tree-nodes ng-model="resources">
                        <li ng-repeat="resc in resources" ui-tree-node ng-include="'nodes_renderer.html'"></li>
                    </ol>
                </div>
            </div>

        </div>
    </div>
    <!-- 错误信息 -->
    <div id="errorinfo"></div>
</div>
