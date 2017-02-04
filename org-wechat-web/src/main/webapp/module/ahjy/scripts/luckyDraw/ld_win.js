define(function(require, exports, module) {
	
	exports.init = function() {
		$("#peach").click(function(){
			
			$("#chatAudio")[0].play();
			
			$("#form1").submit();
		});
	};
});