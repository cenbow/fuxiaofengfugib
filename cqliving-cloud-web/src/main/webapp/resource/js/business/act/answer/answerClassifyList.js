define(["jquery"], function($) {
	return {
		init: function(){
			var aheight=$("ul.answerType_list li img").height();
			$("ul.answerType_list li a").height(aheight);
		}
	};
	
});

function start(id, appId, sessionId, token){
	var url = '/act/answer/answer_question_list.html?classifyId=' + id + '&appId=' + appId + '&sessionId=' + sessionId + '&type=1';
	if(token != ''){
		url += '&token=' + token;
	}
	window.location.href = url;
}