<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav>
    <div class="row ajax_pagination">
		<div class="col-sm-6 col-xs-12">
			<label class="dataTables_info" id="sample-table-2_info">从
				{{pageInfo.currentPage * pageInfo.countOfCurrentPage}}至 
				{{pageInfo.currentPage * pageInfo.countOfCurrentPage + pageInfo.totalCount}} 共{{pageInfo.totalCount}}条
			</label>
			<label>&nbsp;&nbsp;&nbsp;</label>
			<span id="sample-table-2_length" class="dataTables_length">
				<label>每页 
				<select ng-model="pageInfo.countOfCurrentPage" ng-options="i for i in pageSize" ng-change="changePageSize(i)"></select>
					 条
				</label>
			</span>
		</div>
		<div class="col-sm-6 col-xs-12">
			<div class="dataTables_paginate paging_bootstrap">
				<ul class="pagination">
				
				    <!-- 跳转到第一页 -->
		        	<li class="prev" ng-if="pageInfo.currentPage >=2"><a href="javascript:void(0);" ng-click="getPageInfoByPage(1)"><i class="icon-double-angle-left"></i></a></li>
		        	<li class="prev disabled" ng-if="1>=pageInfo.currentPage"><a href="javascript:void(0);"><i class="icon-double-angle-left"></i></a></li>
			        
			        <!-- 点击页号跳转到指定页面 -->
			        <li  ng-repeat="for i in durePageNo" ng-class="{active:$index+1 == pageInfo.currentPage}">
			             <a href="javascript:void(0);" ng-if="$index+1 != pageInfo.currentPage" ng-click="getPageInfoByPage(1)">{{i}}</a>
			             <a href="javascript:void(0);" ng-if="$index+1 == pageInfo.currentPage">{{i}}</a>
			        </li>
			        
			        <!-- 跳转到最后一页 -->
		            <li class="next" ng-if="pageInfo.totalPage > pageInfo.currentPage"><a href="javascript:void(0);" ng-click="getPageInfoByPage(pageInfo.totalPage)"><i class="icon-double-angle-right"></i></a></li>
		            <li class="next disabled" ng-if="pageInfo.currentPage == pageInfo.totalPage"><a href="javascript:void(0);"><i class="icon-double-angle-right"></i></a></li>
				</ul>
			</div>
		</div>
	</div>
</nav>