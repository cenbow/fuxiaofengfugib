define(['validator.bootstrap','cloud.table.curd'], function($,tableCurd){
	return {
		init:loadInit
	};
	
	function loadInit(){
		tableCurd.bindSaveButton();
		formValidate();
	}

	
    function formValidate(){
        $("#form1").validate({
            rules: {
                    name : {
                    required: true
                }
            },
            messages: {
                name : {
                    required: '请输入名称'
                }
            }
        });
    }
});