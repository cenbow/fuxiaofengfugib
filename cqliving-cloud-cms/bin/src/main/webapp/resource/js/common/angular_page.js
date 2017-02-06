require(["angular"],function(){
	
	var pageModule = angular.module("pageModule",[]);
	pageModule.service("pageService",function(){
		
		this.pageSize = [2,10,20,50,100];
		this._scope = null;
		this._http = null;
		
		this.initAngularPage = function(scope,http){
			this._scope = scope;
			this._http = http;
			//初始化分页大小
			scope.pageSize = this.pageSize;
			//当前显示的指定页码大小
			scope.durePageNo = getDurePageNo(scope.pageInfo);
			//页码大小改变
			scope.changePageSize = changePageSize;
			//获取指定页数的数据
			scope.getPageInfoByPage = getPageInfoByPage;
		}
		
		function getDurePageNo(pageInfo){
			var page = pageInfo.totalPage;
			var pageNo = [];
			for(var i=1;i<page;i++){
				pageNo.push(i);
			}
			return pageNo;
		};
		
		//获取
		function changePageSize(pageSize){
			
		}
		
		//跳转到指定页数
		function getPageInfoByPage(p){
			
		};
		
		this.postData = function(params){
			
			$http.post({
				url:scope.dataUrl,
				config:{}
			}).success(function(){
				
			}).error(function(){
				
			});
			
		}
	});
	
});