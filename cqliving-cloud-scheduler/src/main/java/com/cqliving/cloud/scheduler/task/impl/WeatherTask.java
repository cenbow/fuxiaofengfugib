package com.cqliving.cloud.scheduler.task.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cqliving.cloud.online.app.domain.AppWeather;
import com.cqliving.cloud.online.app.service.AppWeatherService;
import com.cqliving.cloud.scheduler.task.TaskScheduler;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.http.HttpClientUtil;

/**
 * Title:获取天气预报
 * <p>Description:</p>
 * @author yuwu on 2016年5月25日
 */
@Component("weatherTask")
public class WeatherTask extends TaskScheduler{
	private static final Logger logger = LoggerFactory.getLogger(WeatherTask.class);
	
    //@Value("${order.expire.minute}")
    //private int orderExpireMinute;
    
    @Autowired
    private AppWeatherService appWeatherService;

    /**
	 * <p>Description:</p>
	 * @see com.cqliving.cloud.scheduler.task.TaskScheduler#execute()
	 * @author yuwu on 2016年5月25日
	 */
	@Override
	public void execute() {
		appWeatherService.syncWeather();
	}

	public static void main(String[] args){
		String url = "http://apis.baidu.com/heweather/weather/free?city=";
		url = url + "chongqing";
		Header[] headers = new Header[]{new BasicHeader("apikey","53bcbe379cfee59178404b256b6c5ef3")};
		String response = HttpClientUtil.sendGetRequest(url,null,headers);
		//转码，否则是乱码
		//response = new String(response.getBytes("iso-8859-1"));
		if(StringUtils.isNotBlank(response)){
			logger.info("请求结果{}",response);
	    	JSONObject json = JSONObject.parseObject(response);
	    	JSONArray list = json.getJSONArray("HeWeather data service 3.0");
	    	if(CollectionUtils.isNotEmpty(list)){
	    		AppWeather appWeather = new AppWeather();
	    		JSONObject weather = list.getJSONObject(0);
	    		JSONObject now = weather.getJSONObject("now");
	    		JSONObject cond = now.getJSONObject("cond");
	    		
	    		appWeather.setConditionCode(cond.getString("code"));
	    		appWeather.setConditionName(cond.getString("txt"));
	    		appWeather.setConditionIconUrl("");
	    		appWeather.setTmpNow(Integer.parseInt(now.getString("tmp")));
	    		
	    		JSONArray forecasts = weather.getJSONArray("daily_forecast");
	    		JSONObject forecastToday = forecasts.getJSONObject(0);
	    		JSONObject tmp = forecastToday.getJSONObject("tmp");
	    		appWeather.setTmpMax(Integer.parseInt(tmp.getString("max")));
	    		appWeather.setTmpMin(Integer.parseInt(tmp.getString("min")));
	    		appWeather.setWeatherTime(DateUtil.parse(forecastToday.getString("date"), DateUtil.FORMAT_YYYY_MM_DD));
	    	}
		}
	}
	
	//每个字段的意思：http://apistore.baidu.com/apiworks/servicedetail/478.html
/*	{
	    "HeWeather data service 3.0": [
	        {
	            "aqi": {
	                "city": {
	                    "aqi": "28", 
	                    "co": "0", 
	                    "no2": "18", 
	                    "o3": "81", 
	                    "pm10": "24", 
	                    "pm25": "15", 
	                    "qlty": "良", 
	                    "so2": "4"
	                }
	            }, 
	            "basic": {
	                "city": "北京", 
	                "cnty": "中国", 
	                "id": "CN101010100", 
	                "lat": "39.904000", 
	                "lon": "116.391000", 
	                "update": {
	                    "loc": "2016-05-25 10:51", 
	                    "utc": "2016-05-25 02:51"
	                }
	            }, 
	            "daily_forecast": [
	                {
	                    "astro": {
	                        "sr": "04:51", 
	                        "ss": "19:31"
	                    }, 
	                    "cond": {
	                        "code_d": "101", 
	                        "code_n": "100", 
	                        "txt_d": "多云", 
	                        "txt_n": "晴"
	                    }, 
	                    "date": "2016-05-25", 
	                    "hum": "14", 
	                    "pcpn": "0.0", 
	                    "pop": "28", 
	                    "pres": "1005", 
	                    "tmp": {
	                        "max": "30", 
	                        "min": "17"
	                    }, 
	                    "vis": "10", 
	                    "wind": {
	                        "deg": "261", 
	                        "dir": "南风", 
	                        "sc": "3-4", 
	                        "spd": "15"
	                    }
	                }, 
	                {
	                    "astro": {
	                        "sr": "04:50", 
	                        "ss": "19:32"
	                    }, 
	                    "cond": {
	                        "code_d": "100", 
	                        "code_n": "100", 
	                        "txt_d": "晴", 
	                        "txt_n": "晴"
	                    }, 
	                    "date": "2016-05-26", 
	                    "hum": "10", 
	                    "pcpn": "0.0", 
	                    "pop": "0", 
	                    "pres": "1010", 
	                    "tmp": {
	                        "max": "30", 
	                        "min": "16"
	                    }, 
	                    "vis": "10", 
	                    "wind": {
	                        "deg": "312", 
	                        "dir": "北风", 
	                        "sc": "4-5", 
	                        "spd": "20"
	                    }
	                }, 
	                {
	                    "astro": {
	                        "sr": "04:50", 
	                        "ss": "19:33"
	                    }, 
	                    "cond": {
	                        "code_d": "100", 
	                        "code_n": "100", 
	                        "txt_d": "晴", 
	                        "txt_n": "晴"
	                    }, 
	                    "date": "2016-05-27", 
	                    "hum": "9", 
	                    "pcpn": "0.0", 
	                    "pop": "0", 
	                    "pres": "1007", 
	                    "tmp": {
	                        "max": "32", 
	                        "min": "18"
	                    }, 
	                    "vis": "10", 
	                    "wind": {
	                        "deg": "202", 
	                        "dir": "南风", 
	                        "sc": "3-4", 
	                        "spd": "14"
	                    }
	                }, 
	                {
	                    "astro": {
	                        "sr": "04:49", 
	                        "ss": "19:33"
	                    }, 
	                    "cond": {
	                        "code_d": "101", 
	                        "code_n": "104", 
	                        "txt_d": "多云", 
	                        "txt_n": "阴"
	                    }, 
	                    "date": "2016-05-28", 
	                    "hum": "19", 
	                    "pcpn": "0.0", 
	                    "pop": "0", 
	                    "pres": "1003", 
	                    "tmp": {
	                        "max": "30", 
	                        "min": "19"
	                    }, 
	                    "vis": "10", 
	                    "wind": {
	                        "deg": "193", 
	                        "dir": "无持续风向", 
	                        "sc": "微风", 
	                        "spd": "5"
	                    }
	                }, 
	                {
	                    "astro": {
	                        "sr": "04:49", 
	                        "ss": "19:34"
	                    }, 
	                    "cond": {
	                        "code_d": "104", 
	                        "code_n": "100", 
	                        "txt_d": "阴", 
	                        "txt_n": "晴"
	                    }, 
	                    "date": "2016-05-29", 
	                    "hum": "8", 
	                    "pcpn": "0.0", 
	                    "pop": "2", 
	                    "pres": "1008", 
	                    "tmp": {
	                        "max": "30", 
	                        "min": "19"
	                    }, 
	                    "vis": "10", 
	                    "wind": {
	                        "deg": "346", 
	                        "dir": "北风", 
	                        "sc": "3-4", 
	                        "spd": "14"
	                    }
	                }, 
	                {
	                    "astro": {
	                        "sr": "04:48", 
	                        "ss": "19:35"
	                    }, 
	                    "cond": {
	                        "code_d": "100", 
	                        "code_n": "101", 
	                        "txt_d": "晴", 
	                        "txt_n": "多云"
	                    }, 
	                    "date": "2016-05-30", 
	                    "hum": "8", 
	                    "pcpn": "0.0", 
	                    "pop": "0", 
	                    "pres": "1004", 
	                    "tmp": {
	                        "max": "31", 
	                        "min": "20"
	                    }, 
	                    "vis": "10", 
	                    "wind": {
	                        "deg": "285", 
	                        "dir": "无持续风向", 
	                        "sc": "微风", 
	                        "spd": "2"
	                    }
	                }, 
	                {
	                    "astro": {
	                        "sr": "04:48", 
	                        "ss": "19:36"
	                    }, 
	                    "cond": {
	                        "code_d": "101", 
	                        "code_n": "100", 
	                        "txt_d": "多云", 
	                        "txt_n": "晴"
	                    }, 
	                    "date": "2016-05-31", 
	                    "hum": "11", 
	                    "pcpn": "0.0", 
	                    "pop": "0", 
	                    "pres": "1008", 
	                    "tmp": {
	                        "max": "33", 
	                        "min": "21"
	                    }, 
	                    "vis": "10", 
	                    "wind": {
	                        "deg": "64", 
	                        "dir": "南风", 
	                        "sc": "3-4", 
	                        "spd": "16"
	                    }
	                }
	            ], 
	            "hourly_forecast": [
	                {
	                    "date": "2016-05-25 10:00", 
	                    "hum": "20", 
	                    "pop": "1", 
	                    "pres": "1007", 
	                    "tmp": "28", 
	                    "wind": {
	                        "deg": "303", 
	                        "dir": "西北风", 
	                        "sc": "3-4", 
	                        "spd": "17"
	                    }
	                }, 
	                {
	                    "date": "2016-05-25 13:00", 
	                    "hum": "14", 
	                    "pop": "0", 
	                    "pres": "1006", 
	                    "tmp": "31", 
	                    "wind": {
	                        "deg": "276", 
	                        "dir": "西风", 
	                        "sc": "3-4", 
	                        "spd": "18"
	                    }
	                }, 
	                {
	                    "date": "2016-05-25 16:00", 
	                    "hum": "14", 
	                    "pop": "0", 
	                    "pres": "1004", 
	                    "tmp": "31", 
	                    "wind": {
	                        "deg": "232", 
	                        "dir": "西南风", 
	                        "sc": "3-4", 
	                        "spd": "19"
	                    }
	                }, 
	                {
	                    "date": "2016-05-25 19:00", 
	                    "hum": "18", 
	                    "pop": "0", 
	                    "pres": "1004", 
	                    "tmp": "28", 
	                    "wind": {
	                        "deg": "213", 
	                        "dir": "西南风", 
	                        "sc": "微风", 
	                        "spd": "16"
	                    }
	                }, 
	                {
	                    "date": "2016-05-25 22:00", 
	                    "hum": "25", 
	                    "pop": "0", 
	                    "pres": "1006", 
	                    "tmp": "26", 
	                    "wind": {
	                        "deg": "242", 
	                        "dir": "西南风", 
	                        "sc": "微风", 
	                        "spd": "13"
	                    }
	                }
	            ], 
	            "now": {
	                "cond": {
	                    "code": "101", 
	                    "txt": "多云"
	                }, 
	                "fl": "24", 
	                "hum": "26", 
	                "pcpn": "0", 
	                "pres": "1007", 
	                "tmp": "23", 
	                "vis": "10", 
	                "wind": {
	                    "deg": "330", 
	                    "dir": "西北风", 
	                    "sc": "4-5", 
	                    "spd": "22"
	                }
	            }, 
	            "status": "ok", 
	            "suggestion": {
	                "comf": {
	                    "brf": "较舒适", 
	                    "txt": "白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。"
	                }, 
	                "cw": {
	                    "brf": "较不宜", 
	                    "txt": "较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。"
	                }, 
	                "drsg": {
	                    "brf": "热", 
	                    "txt": "天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。"
	                }, 
	                "flu": {
	                    "brf": "少发", 
	                    "txt": "各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。"
	                }, 
	                "sport": {
	                    "brf": "较适宜", 
	                    "txt": "天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意防风。"
	                }, 
	                "trav": {
	                    "brf": "适宜", 
	                    "txt": "天气较好，温度稍高，幸好风稍大，会缓解稍热的天气。适宜旅游，可不要错过机会呦！"
	                }, 
	                "uv": {
	                    "brf": "中等", 
	                    "txt": "属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"
	                }
	            }
	        }
	    ]
	}
*/}
