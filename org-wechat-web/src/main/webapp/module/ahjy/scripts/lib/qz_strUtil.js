/**
 * Created by fuxiaofeng on 2014/12/12.
 */
;define(function(require,exports){
	
	var valid = require('qzValidate');
	/** 开发过程中的公共方法写在此处,如果是对外接口,使用exports.xx=function(){}的形式提供*/
	
	/** 是否为空:返回true为空 '0':不为空,0为空*/
	exports.isEmpty = function(str){
		return str ? false : true;
	};
	
	/** 替换全部符合的字符换 :如("1222452355","2","#")将2全部替换为'#'号 英文字母不区分大小写*/
	exports.replaceAllByReg = function(str,reg,rep){
		var reg = new RegExp(reg,"gi");
		return str.replace(reg,rep);
	};
	
	exports.filter = function(str){
		var newStr="";
		for(var i in str){
			switch (str.charAt(i)) {
			case '<':
				newStr+="&lt;";
				break;
			case '>':
				newStr+="&gt;";
				break;
			case '\'':
				newStr+="&#039;";
				break;
			default:
				newStr+=str.charAt(i);
			}
		}
		return newStr;
	}
	/** 字符串截取 */
	exports.cutStr = function(str,length){
		//截取的长度length以英文为标准,一个字符就是一个英文占位符,在计算截取长度时中文要计为两个字符;
		//js本身的length属性把中文英文均视为一个字符;
		//所以遍历字符串时,如果是中文,则截取的长度要减一,以便让js的length属性辨认
		for(var i=0;i<length;i++){//判断截取长度是否有中文,如果有中文,则将截取长度减一
			var temp = str.charAt(i);
			if(valid.validateChinese(temp)){
				length -=1;
			}
		}
		var newStr = str;
		if(newStr.length>=length){
			newStr = newStr.substring(0,length);
			newStr +="...";
		}
		return newStr;
	};
	
	/**
	 * 传入字符串，返回字符串长度，支持中文
	 * @param {string} str 要计算的字符串
	 */
	exports.strSize = function(str){
		var l = 0;
		if(str == undefined) return 0;
		if(typeof str == 'string'){
			if(str.length == 0) return 0;
			for(var i=0;i<str.length;i++){
				var temp = str.charAt(i);
				if(valid.validateChinese(temp)){
					l+=2;
				}else{
					l++;
				}
			}
			return l;
		}else{
			return 0;
		}
	};
	/**
	 * 字符串截取：按照中文计
	 * value:要截取的字符串
	 * length:要截取的中文长度
	 * escape:true(要过滤特殊字符)false(不过滤特殊字符)
	 */
	exports.truncateStr=function(value,length,escape){
	        var regExpArr = ["&amp;","&nbsp;","&lt;","&gt;","&#039;","&quot;"];
			var replaceArr = ["&"," ","<",">","'","\""];
			length = length*2;
			if(!escape){
				return cutString(value, length);
			}
			if(!value) return value;	
			var data = {
				startIndex:0,
				addLength:0
			};
			var temp = replaceChinese(value);
			for(var i=0 ; i< regExpArr.length ; i++){
				data.startIndex=0;
				getAddLength(temp,regExpArr[i],replaceArr[i],length,data);
			}
			return cutString(value, length+ data.addLength);
		};

	var replaceChinese=function(value){
			if(!value) return value;
			var r = "";
			for(var i=0;i<value.length;i++){
				if(valid.validateChinese(value.charAt(i))){//如果是中文则占用两个字符
					r+="00";
				}else{
					r+=value.charAt(i);
				}
			}
			return r;
		};

	var getAddLength=function(value,oldSubValue,newSubValue,paramLength,data){
			data.startIndex = value.indexOf(oldSubValue,data.startIndex);
			if(data.startIndex<0 || data.startIndex>=(paramLength+data.addLength)){
				return;
			}
			data.addLength=data.addLength+oldSubValue.length - newSubValue.length;
			data.startIndex = data.startIndex+oldSubValue.length;
			if(data.startIndex >= paramLength + data.addLength){
				return ;
			}
			arguments.callee(value,oldSubValue,newSubValue,paramLength,data);
	};
	
	var cutString = function(value, length) {
	      var k=0,i=0;//单位是字节
			for(;i<value.length && k<=length;i++){
				if(valid.validateChinese(value.charAt(i))){
					k+=2;
				}else{
					k++;
				}
			}
		return value.substr(0, i-1)+"...";
	};

	/**
	 * 验证字符串的长度（中文字节数）
	 * (str.charCodeAt(i) & 65280) != 0
	 */
	exports.validateLength = function(value) {
		var str = value;
		if (str == null) {
			return false;
		}
		var l = str.length;
		var Cblen = 0;//中文长度
		var Eblen = 0;//英文长度
		for (var i = 0; i < l; i++) {
			if (valid.validateChinese(value.charAt(i))) {//不是中文添加中文占位符
				Cblen++;
			}else{
			  Eblen++;
			}
		}
		return Cblen+Math.round(Eblen/2);
	};
	/**
	 * 验证输入长度返回剩余数
	 * 参数：thisInput：当前输入框  ； length：输入框最大字节数
	 */
	exports.showLength = function (thisInput,length){
		var thisval = $(thisInput).val();
		/*thisval = $.trim(thisval);
		$(thisInput).val(thisval);*/
		var len = 0;
		if(thisval){
			for(var i=0;i<thisval.length;i++){
				var temp = thisval.charAt(i);
				len++;
				//判断是不是中文
				if(valid.validateChinese(temp)){
					len = len+1;
				}
				if(len>length){
					$(thisInput).val(thisval.substring(0,i));
					return 0;
				}
			}
		}
		if(len<=length){
			return length-len;
		}
	};
});