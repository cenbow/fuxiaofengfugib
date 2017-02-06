define(["daterangepicker"],function($){
	return {
		initTimeInput: function(){
			//1、绑定时间输入框
			initTimeInput();
		}
	};
	
	//1、绑定时间输入框
	function initTimeInput(){
		var inputTimes = $("input[time_options]");
		$.each(inputTimes,function(index,elm){
			var optionsTemp = $(elm).attr('time_options');
			//配置项
			var options = optionsTemp ? $.parseJSON(optionsTemp) : {};
			if(options && options.singleDatePicker && options.singleDatePicker){//单个时间条件
				//单个时间条件默认配置
				var defaultOptions={
					format:'YYYY-MM-DD',
					showDropdowns:true,
					singleDatePicker:false
				};
				//用配置项代替默认项
				$.extend(defaultOptions, options);
				
				$(elm).daterangepicker(defaultOptions);
			}else{//两个时间条件联动
				/**********初始化参数开始***********/
				//多个时间条件默认配置
				var defaultOptions={
					format:'YYYY-MM-DD HH:mm',
					showDropdowns:true,
					singleDatePicker:false,
					"timePicker": true,
		            "timePicker12Hour": false,
				    "timePickerIncrement": 1,
				    "opens": "right"
				};
				//用配置项代替默认项
				$.extend(defaultOptions, options);
				
				var startDate = $(elm).prev().prev().val();
	            var endDate = $(elm).prev().val();
	            if(startDate){
	            	//如果有开始时间才加进去
	            	defaultOptions['startDate'] = startDate;
	            }
				if(endDate){
					//如果有结束时间才加进去
					defaultOptions['endDate'] = endDate;
	            }
				/**********初始化参数结束***********/
				
				$(elm).daterangepicker(defaultOptions, function(start, end){
		            $(elm).val(start.format(defaultOptions.format) + " 至 " + end.format(defaultOptions.format));
		            $(elm).prev().prev().val(start.format(defaultOptions.format));
		            $(elm).prev().val(end.format(defaultOptions.format));
		        });
			}
		});
	}
});