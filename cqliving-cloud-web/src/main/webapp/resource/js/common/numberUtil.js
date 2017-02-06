define(['jquery'],function(){
	//获取数组中每个数字的在总数中的百分比
   return function(arr){
    	//强转成数字
    	var total = 0;
    	for(var i=0,m=arr.length;i<m;i++){
    		arr[i] = parseInt(arr[i],10);
    		total +=arr[i];
    	}
    	//计算
		var result = [];
		for(var i=0,m=arr.length;i<m;i++){
			var dataPer = "0%";
			if(total != 0){
			   dataPer = ((arr[i]/total).toFixed(6) * 100).toFixed(1) + "%";
			}
			result.push(dataPer);
    	}
		return result;
	};
});