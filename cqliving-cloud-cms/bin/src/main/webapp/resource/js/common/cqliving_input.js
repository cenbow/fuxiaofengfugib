/**
 * 表单控制
 */
define(function() {
	return {
		// 只能输入数字
		onlyNum : onlyNum
	}

	/** 只能输入数字 */
	function onlyNum() {
		$("body").on("keyup paste", ".only_num", function() {
			$(this).val($(this).val().replace(/\D/g, ""));
		});
	}

});