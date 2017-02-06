require(['validator.bootstrap','cloud.table.curd','cqliving_dialog','cqliving_ajax','angular','chosen'], function($,tableCurd,cqliving_dialog,cqliving_ajax){
	tableCurd.bindSaveButton();
	$(".chosen-select").chosen({search_contains:true});
	
	angular.module("fareTmpModel",[]).filter("removeSubRegion",function(){
		return function(region){
			var newRegion = {
					 id:region.id,
					 code:region.code,
					 pcode:region.pcode,
					 hieraechy:region.hieraechy,
					 name:region.name,
					fullname:region.fullname,
					optionCode:region.optionCode
			};
			return newRegion;
		}
	}).controller("shopfareTemplateController",function($scope,$http){
		//运费模板
		$scope.fareTemplate=fareTemplateJson;
		//大区区域
		$scope.regionJson =regionJson;
		
		$scope.initData = function(){
			$scope.checkedRegion = [];
			$scope.dialogDetail = null;
			$scope.defaultFare = {
					appId:1,isDefault:1,status:3,sortNo:1
			};
			if($scope.fareTemplate){
				$scope.defaultFare = $scope.fareTemplate.tempDetails.shift();
			}else{
				$scope.fareTemplate = {
						appId:1,status:1,tempDetails:[]
				};
			}
		}
		//初始化
		$scope.initData();
		
        $scope.removeDetail = function(index){
        	$scope.fareTemplate.tempDetails.splice(index,1);
		}
		
        $scope.saveTmp = function(submit){
        	
        	if(!submit){
        		cqliving_dialog.error("请输入运费信息");return;
        	}
        	var template = $scope.fareTemplate;
        	var tempDetails = template.tempDetails;
            angular.forEach(template.tempDetails,function(tmpDetail,idx){
            	delete tmpDetail.$$hashKey;
            });
            //将默认的运费加到第一个
            template.tempDetails.unshift($scope.defaultFare);
        	var url = "shopfare_add.html";
        	cqliving_ajax.ajaxOperate(url,null,{fareTemplate:JSON.stringify(template)},function(data){
        		$("button[backRestoreParam]").click();
        	});
        }
        
		/*$scope.disCheckedRegion = function(region){
			$.each($scope.checkedRegion,function(index,cr){
				if(cr && region.id == cr.id){
					//移除选中的对象
					$scope.checkedRegion.splice(index,1);
					//return false;
				}
			});
			
			var subRegion = region.subRegion;
			if(subRegion && subRegion.length<=0){
				return;
			}
			$.each(subRegion,function(subindex,sub){
			    $.each($scope.checkedRegion,function(index,cr){
					if(cr && cr.id == sub.id){
						//移除选中的对象
						$scope.checkedRegion.splice(index,1);
					}
				});
			});
		}*/
		
		$scope.modelShow = function(tmpDetail){
			if(tmpDetail){
				$scope.dialogDetail = tmpDetail;
			}
			$("#add_dialog").modal("show");
		}
		
		$("#add_dialog").on("hide.bs.modal",function(){
			$scope.cancelChecked();
			$("ul.list-unstyled li input[type=checkbox]:checked").each(function(i,n){
				$(n).prop("checked",false);
			});
			statisticsCheckedNum();
		});
		
		$("#add_dialog").on("show.bs.modal",function(){
			//弹出框显示时，初始化选中的多选框
			if(!$scope.dialogDetail){
			     return;	
			}
			var regions = $scope.dialogDetail.regions;
			angular.forEach(regions,function(data,idx){
				$("ul.list-unstyled li input[type=checkbox]").each(function(i,n){
					var regionId = $(n).val();
					regionId = parseInt(regionId,10);
					if(data.regionId == regionId){
						$(n).prop("checked",true);
						//var checked = JSON.parse($(n).next().text());
						//$scope.checkedRegion.push(checked);
						return false;
					}
				});
			});
			statisticsCheckedNum();
		});
		
		$scope.changeArea  = function($this,area){//大区被选中则后面两级都选中
			if(isChecked($this)){
				checkedArea($this,true);
				/*angular.forEach(area.region,function(data,i){
					$scope.checkedRegion.push(data);
					angular.forEach(data.subRegion,function(sub,i){
						$scope.checkedRegion.push(sub);
					});
				});*/
			}else{
				checkedArea($this,false);
				/*angular.forEach(area.region,function(data,i){
					$scope.disCheckedRegion(data);
				});*/
			}
			statisticsCheckedNum($this);
		} 
		
		$scope.changeCheck = function($this,region){//
			
			if(!isChecked($this)){
				//$scope.disCheckedRegion(region);
				if(1 == region.hieraechy){//如果是父级，则取消本级及子集全部
					checkedAllRegion($this,false);
				}
			}else{//地区选中
				//$scope.checkedRegion.push(region);
			    if(1 == region.hieraechy){//如果是一级要加子级的
			    	checkedAllRegion($this,true);
			    	/*angular.forEach(region.subRegion,function(data,i){
						$scope.checkedRegion.push(data);
					});*/
			    }else{
			    	checkAllParent($this,true);
			    }
			}
			statisticsCheckedNum($this);
		}
		
		//是否选中
		function isChecked($this){
			return $this.checked;
		}
		
		function checkAllParent($this,isChecked){
			if(isChecked){
				var $this = angular.element($this);
				$this.closest(".ecity").find("input[type=checkbox]:first").prop("checked",true);
				$this.closest("li").find("input[type=checkbox]:first").prop("checked",true);
			}
		}
		
		//选中所有的同级及子集区域
		function checkedAllRegion($this,isChecked){
			
			var $this = angular.element($this);
			var $checkbox = $this.closest(".ecity").find("input[type=checkbox]");
			$checkbox.each(function(i,n){
				if(isChecked){
					$(n).prop("checked",true);
				}else{
					$(n).prop("checked",false);
				}
			});
		}
		
		function checkedArea($this,isChecked){
			var $this = angular.element($this);
			var $checkbox = $this.closest("li").find("input[type=checkbox]");
			$checkbox.each(function(i,n){
				if(isChecked){
					$(n).prop("checked",true);
				}else{
					$(n).prop("checked",false);
				}
			});
		}
		
		//计算选中的数量
		function statisticsCheckedNum($this){
			if($this){
				var $this = angular.element($this);
				var $li = $this.closest("li");
                $li.find(".ecity").each(function(i,n){
                	var $n = $(n);
					var checkNum = $n.find("input[type=checkbox]:checked").length;			
	                $n.find(".cityNumber").html("("+checkNum+")");
                });
			}else{
				$("ul.list-unstyled li").each(function(i,n){
					var $n = $(n);
					$n.find(".ecity").each(function(j,k){
						var $k = $(k);
						var checkNum = $k.find("input[type=checkbox]:checked").length;			
		                $k.find(".cityNumber").html("("+checkNum+")");
					});
				});
			}
		}
		
		//弹出框隐藏
		$scope.cancelChecked = function(){
			$scope.checkedRegion = [];//缓存的选中区域要清空
			$scope.dialogDetail = null;
		}
		
		$scope.addRegion = function(){
			
			var $checked = $("ul.list-unstyled li .provinceList input[type=checkbox]:checked");
			if($checked.length<=0){
				cqliving_dialog.error("请至少选择一个省市");
				return;
			}
			$checked.each(function(i,n){
				var checked = JSON.parse($(n).next().text());
				$scope.checkedRegion.push(checked);
			});
			if(!$scope.fareTemplate.tempDetails){
				$scope.fareTemplate.tempDetails = [];
			}
			var dialogDetail = {
					id:"",appId:$scope.fareTemplate.appId,fareTemplateId:"",regionNames:"",isDefault:0,
					baseFareCount:"",baseFare:"",addFareCount:"",addFare:"",regions:[],sortNo:1000000
			}
			if($scope.dialogDetail){
				dialogDetail = $scope.dialogDetail;
			}
			var detailRegions = [];
			var regionNames = [];
			angular.forEach($scope.checkedRegion,function(region,idx){
				var detailRegion = {
						id:"",appId:$scope.fareTemplate.appId,fareTemplateId:"",fareTemplateDetailId:"",
						regionName:region.name,regionId:region.code,regionLevel:region.hieraechy,isSubSelectedAll:1
				}
				regionNames.push(region.name);
				detailRegions.push(detailRegion);
			});
			dialogDetail.regions = detailRegions;
			dialogDetail.regionNames = regionNames.join(",");
			
			if(!$scope.dialogDetail){//新增
				$scope.fareTemplate.tempDetails.push(dialogDetail);
			}else{//修改
				angular.forEach($scope.fareTemplate.tempDetails,function(tmpdetail,idx){
					if(tmpdetail.$$hashKey == $scope.dialogDetail.$$hashKey){
						$scope.fareTemplate.tempDetails[idx] = $scope.dialogDetail;
					}
				});
				
			}
			$scope.cancelChecked();
			$("#add_dialog").modal("hide");
		}
	});
	
	angular.bootstrap(document,['fareTmpModel']);
	
	$('span.lbl').on('click',function(){
		var showCase =$(this);
		if($(showCase).children('.cityNumber').html()){
			$(showCase).parent('.checkbox-2').addClass('multiActive').siblings('.checkMore').show();
		}
	})
	$('.cityClose').on('click',function(){
		$('span.lbl').parent('.checkbox-2').removeClass('multiActive').siblings('.checkMore').hide();
	})
	
});