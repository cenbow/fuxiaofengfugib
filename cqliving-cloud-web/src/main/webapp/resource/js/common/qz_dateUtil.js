/**
 * Created by fuxiaofeng on 2014/12/12.
 */
;define(function(require,exports){
	
	/** 开发过程中的公共方法写在此处,如果是对外接口,使用exports.xx=function(){}的形式提供*/
	
	/** 日期格式字符串转日期类型 */
	exports.strToDate = function(str){
		return parseStrToDate(str);
	};
	/** 两个时间毫秒值之间的时间差,返回具体的相差的天 小时 分钟 秒数 */
	exports.countDown = function(bigTime,smallTime){
		return count_down(bigTime,smallTime);
	};
	/** 格式化时间,返回日期格式字符串 */
	exports.formatDate = function(date,format){
		return formatDate(date,format);
	};
	/** 时间单独的年月比较 返回true:则开始时间大于结束时间*/
	exports.beginBiggerThanEnd = function(beginYear,beginMonth,endYear,endMonth){
		var isBig = false;
		if(beginYear>endYear || (beginYear == endYear && beginMonth>endMonth)){
			isBig = true;
		}
		return isBig;
	};

	/** 增加年数 */
	exports.addYears = function(date,years){
		date.setYear(date.getFullYear()+years);
		return date;
	};
	
	/** 增加月数 */
	exports.addMonths = function(date,months){
		date.setMonth(date.getMonth()+months);
		return date;
	};
	
	/** 增加天数 */
	exports.addDays = function(date,days){
		return addDays(date,days);;
	};
	
	/** 增加小时数 */
	exports.addHours = function(date,hours){
		date.setHours(date.getHours()+hours);
		return date;
	};
	
	/** 增加分钟数 */
	exports.addMinutes = function(date,minutes){
		date.setMinutes(date.getMinutes()+minutes);
		return date;
	};

	exports.echoFormatDate= function(now,date){
	     return echoFormatDate(now,date);
	};
	
	exports.dateFormate = function(now,date){
		var str = "";
		var arr = date.split(".");//如果有小点就去小点
		date = arr[0];
		date = parseStrToDate(date);
		now = parseStrToDate(now);
		var todayY = now.getFullYear();
		var todayM = now.getMonth();
		var todayD = now.getDate();
		
		var dateY = date.getFullYear();
		var dateM = date.getMonth();
		var dateD = date.getDate();
		
		var yestoday = addDays(now,-1);
		var yestodayY = yestoday.getFullYear();
		var yestodayM = yestoday.getMonth();
		var yestodayD = yestoday.getDate();
		
		var bool = true;
		if(dateY==todayY && dateM==todayM && dateD==todayD){
			str += formatDate(date,"HH:mm");
			bool = false;
		}
		
		if(yestodayY==dateY&&dateM==yestodayM && dateD==yestodayD){
			str += "昨天";
			bool = false;
		}
		if(bool){
			if(todayY==dateY){
				str = formatDate(date,"MM-dd");
			}else{
				str = formatDate(date,"yyyy-MM-dd");
			}
		}
		return str;
	}
	
	function echoFormatDate(now,date){
		var str = "";
		var arr = date.split(".");//如果有小点就去小点
		date = arr[0];
		date = parseStrToDate(date);
		now = parseStrToDate(now);
		var todayY = now.getFullYear();
		var todayM = now.getMonth();
		var todayD = now.getDate();
		
		var dateY = date.getFullYear();
		var dateM = date.getMonth();
		var dateD = date.getDate();
		
		var yestoday = addDays(now,-1);
		var yestodayY = yestoday.getFullYear();
		var yestodayM = yestoday.getMonth();
		var yestodayD = yestoday.getDate();
		
		var bool = true;
		if(dateY==todayY && dateM==todayM && dateD==todayD){
			str += "今天"+ formatDate(date,"HH:mm:ss");
			bool = false;
		}
		
		if(yestodayY==dateY&&dateM==yestodayM && dateD==yestodayD){
			str += "昨天"+ formatDate(date,"HH:mm:ss");
			bool = false;
		}
		if(bool){
			if(todayY==dateY){
				str = formatDate(date,"MM-dd HH:mm:ss");
			}else{
				str = formatDate(date,"yyyy-MM-dd HH:mm:ss");
			}
		}
		return str;
	};
	
	function addDays(date,days){
		date.setDate(date.getDate()+days);
		return date;
	}
function formatDate(date,format) {
	var MONTH_NAMES=new Array('January','February','March','April','May','June','July','August','September','October','November','December','Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec');
	var DAY_NAMES=new Array('Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sun','Mon','Tue','Wed','Thu','Fri','Sat');
	function LZ(x) {return(x<0||x>9?"":"0")+x;};
	format=format+"";
	var result="";
	var i_format=0;
	var c="";
	var token="";
	var y=date.getYear()+"";
	var M=date.getMonth()+1;
	var d=date.getDate();
	var E=date.getDay();
	var H=date.getHours();
	var m=date.getMinutes();
	var s=date.getSeconds();
	var yyyy,yy,MMM,MM,dd,hh,h,mm,ss,ampm,HH,H,KK,K,kk,k;
	// Convert real date parts into formatted versions
	var value=new Object();
	if (y.length < 4) {y=""+(y-0+1900);}
	value["y"]=""+y;
	value["yyyy"]=y;
	value["yy"]=y.substring(2,4);
	value["M"]=M;
	value["MM"]=LZ(M);
	value["MMM"]=MONTH_NAMES[M-1];
	value["NNN"]=MONTH_NAMES[M+11];
	value["d"]=d;
	value["dd"]=LZ(d);
	value["E"]=DAY_NAMES[E+7];
	value["EE"]=DAY_NAMES[E];
	value["H"]=H;
	value["HH"]=LZ(H);
	if (H==0){value["h"]=12;}
	else if (H>12){value["h"]=H-12;}
	else {value["h"]=H;}
	value["hh"]=LZ(value["h"]);
	if (H>11){value["K"]=H-12;} else {value["K"]=H;}
	value["k"]=H+1;
	value["KK"]=LZ(value["K"]);
	value["kk"]=LZ(value["k"]);
	if (H > 11) { value["a"]="PM"; }
	else { value["a"]="AM"; }
	value["m"]=m;
	value["mm"]=LZ(m);
	value["s"]=s;
	value["ss"]=LZ(s);
	while (i_format < format.length) {
		c=format.charAt(i_format);
		token="";
		while ((format.charAt(i_format)==c) && (i_format < format.length)) {
			token += format.charAt(i_format++);
			}
		if (value[token] != null) { result=result + value[token]; }
		else { result=result + token; }
		}
	return result;
}
/**
 * @param str
 * @returns {Date}
 * @author fuxiaofeng 2014.11.6
 * @see 适合格式"2014-11-05 00:00:34"或者"2014-11-05"
 * @see 适合格式"2014/11/05 00:00:34"或者"2014/11/05"
 */
function parseStrToDate(str){
	var strs = str.split(" ");
	var str1 = strs[0];
	var str1s="";
	if(str.indexOf("-")!=-1){
		str1s = str1.split("-");
	}
	if(str.indexOf("/")!=-1){
		str1s = str1.split("/");
	}
	var yy = str1s[0];
	var MM = str1s[1]-1;
	var dd = str1s[2];
	var d = new Date();
	var HH=d.getHours();
	var mm=d.getMinutes();
	var ss=d.getMinutes();
	if(strs[1]){
		var str2 = strs[1];
		var str2s = str2.split(":");
		HH = str2s[0];
		mm = str2s[1];
		ss = str2s[2];
	}
	var date  = new Date(yy,MM,dd,HH,mm,ss);
	return date;
}

/**
 * @param endTime
 * @param time_now
 * @returns {___anonymous12983_13095}
 * @author fuxiaofeng 2014.11.6
 * 参数说明 : endTime:结束时间,time_now当前时间  二者均为毫秒值
 */
function count_down(endTime, time_now) {
	var int_day=0, int_hour=0, int_minute=0, int_second=0;
	var surplus_time = {
			surplus_day:int_day,
			surplus_hour:int_hour,
			surplus_minute:int_minute,
			surplus_second:int_second
	};
	var time_distance = endTime - time_now; // 时间差：活动结束时间减去当前时间
	if (time_distance >= 0) {
		// 相减的差数换算成天数
		int_day = Math.floor(time_distance / 86400000);
		time_distance -= int_day * 86400000;
		// 相减的差数换算成小时
		int_hour = Math.floor(time_distance / 3600000);
		time_distance -= int_hour * 3600000;
		// 相减的差数换算成分钟
		int_minute = Math.floor(time_distance / 60000);
		time_distance -= int_minute * 60000;
		// 相减的差数换算成秒数
		int_second = Math.floor(time_distance / 1000);
		// 判断小时小于10时，前面加0进行占位
		if(int_day < 10) int_day = "0"+int_day;
		if (int_hour < 10)
			int_hour = "0" + int_hour;
		// 判断分钟小于10时，前面加0进行占位
		if (int_minute < 10)
			int_minute = "0" + int_minute;
		// 判断秒数小于10时，前面加0进行占位
		if (int_second < 10)
			int_second = "0" + int_second;
		surplus_time.surplus_day = int_day;
		surplus_time.surplus_hour = int_hour;
		surplus_time.surplus_minute = int_minute;
		surplus_time.surplus_second = int_second;
		return surplus_time;
	}
	return surplus_time;
}
});