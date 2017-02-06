define(['cloud.table.curd','chosen'], function(tableCurd){
	return {
		init: function(){
			tableCurd.initTableCrud();
			$(".chosen-select").chosen({search_contains:true});
		}
	};
});