/**
 * 表单控制
 */
define(function() {
	return {
		//只能输入数字
		onlyNum : onlyNum,
		//只能输入指定范围数字
		onlyNumRange : onlyNumRange
	}

	/** 只能输入数字 */
	function onlyNum() {
		$("body").on("keyup paste", ".only_num", function() {
			$(this).val($(this).val().replace(/\D/g, ""));
		});
	}
	
	/** 只能输入指定范围数字 */
	function onlyNumRange(begin, end) {
		$("body").on("keyup paste", ".only_num", function() {
			var value = $(this).val().replace(/\D/g, "");
			if ($.isNumeric(value)) {
				if ($.isNumeric(begin) && value < begin) {
					value = begin;
				} else if ($.isNumeric(end) && value > end) {
					value = end;
				} 
			}
			$(this).val(value);
		});
	}

});