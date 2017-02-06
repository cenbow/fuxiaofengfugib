package com.cqliving.cloud.web.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cqliving.cloud.online.config.domain.ConfigLife;
import com.cqliving.cloud.online.config.service.ConfigLifeService;
import com.cqliving.tool.common.util.MD5BigFileUtil;
import com.cqliving.tool.common.util.date.DateUtil;

/**
 * Title:中国建设银行悦生活服务接口
 * Copyright (c) CQLIVING 2016
 * @author yuwu on 2016年6月16日
 */
@Controller
@RequestMapping(value = "/life")
public class LifeController{
	private static final Logger logger = LoggerFactory.getLogger(LifeController.class);
	//http://localhost:8080/life/toLife.html?PROV_CODE=500000&CITY_CODE=500101&BILL_TYPE=100&BILL_ITEM=01001
	@Autowired
	ConfigLifeService configLifeService;
	
	private static final String LIFE_VIEW = "/life/tip";
	private static final String SECURITY_KEY = "12345";//双方协定的保密字符串
	//查看
    @RequestMapping(value ="toLife")
    public String toLife(HttpServletRequest request, Map<String, Object> map,
            @RequestParam(required = false) Long appId,
            @RequestParam(required = false) Integer type
            ){
		String DATE = DateUtil.format(new Date(), DateUtil.FORMAT_YYYYMMDD);
    	String TIME = DateUtil.format(new Date(), DateUtil.HHMMSS);
    	if(appId == null || type == null){
    		logger.error("参数错误appId={}，type={}，",appId,type);
    		map.put("errorMessage", "参数错误");
			return LIFE_VIEW;
    	}
    	ConfigLife life = configLifeService.getByType(type).getData();
    	if(life == null){
    		logger.error("life对象为空");
    		map.put("errorMessage", "参数错误");
			return LIFE_VIEW;
    	}
    	StringBuilder md5Sb = new StringBuilder();
    	md5Sb.append(LifeParamEnum.MERCHANTID.getName()).append("=").append(LifeParamEnum.MERCHANTID.getValue());
    	md5Sb.append(LifeParamEnum.BRANCHID.getName()).append("=").append(LifeParamEnum.BRANCHID.getValue());
    	md5Sb.append(LifeParamEnum.MER_CHANNEL.getName()).append("=").append(LifeParamEnum.MER_CHANNEL.getValue());
    	md5Sb.append(LifeParamEnum.DATE.getName()).append("=").append(DATE);
    	md5Sb.append(LifeParamEnum.TIME.getName()).append("=").append(TIME);
    	md5Sb.append(SECURITY_KEY);//保存串
    	//String md5Str = "MERCHANTID=WQLJ50000000001BRANCHID=500000000MER_CHANNEL=0DATE=20160616TIME=08565512345";
    	//logger.info("加密参数：{}",md5Sb.toString());
		String MAC = MD5BigFileUtil.md5(md5Sb.toString());
    	
    	StringBuilder lifeUrl = new StringBuilder();
    	lifeUrl.append(LifeParamEnum.URL.getValue()).append("?")
    	.append(LifeParamEnum.CCB_IBSVersion.getName()).append("=").append(LifeParamEnum.CCB_IBSVersion.getValue())
    	.append("&").append(LifeParamEnum.SERVLET_NAME.getName()).append("=").append(LifeParamEnum.SERVLET_NAME.getValue())
    	.append("&").append(LifeParamEnum.TXCODE.getName()).append("=").append(LifeParamEnum.TXCODE.getValue())
    	.append("&").append(LifeParamEnum.MERCHANTID.getName()).append("=").append(LifeParamEnum.MERCHANTID.getValue())
    	.append("&").append(LifeParamEnum.BRANCHID.getName()).append("=").append(LifeParamEnum.BRANCHID.getValue())
    	.append("&").append(LifeParamEnum.MER_CHANNEL.getName()).append("=").append(LifeParamEnum.MER_CHANNEL.getValue());
    	
    	lifeUrl.append("&").append(LifeParamEnum.DATE.getName()).append("=").append(DATE)
    	.append("&").append(LifeParamEnum.TIME.getName()).append("=").append(TIME)
    	.append("&").append(LifeParamEnum.PROV_CODE.getName()).append("=").append(LifeParamEnum.PROV_CODE.getValue())
    	.append("&").append(LifeParamEnum.CITY_CODE.getName()).append("=").append(LifeParamEnum.CITY_CODE.getValue())
    	.append("&").append(LifeParamEnum.BILL_TYPE.getName()).append("=").append(life.getBillType())
    	.append("&").append(LifeParamEnum.BILL_ITEM.getName()).append("=").append(life.getBillItem())
    	.append("&").append("MAC").append("=").append(MAC);
    	logger.debug("访问地址lifeUrl={}",lifeUrl.toString());
    	//http://life.ccb.com/tran/WCCMainPlatV5?CCB_IBSVersion=V5&SERVLET_NAME=WCCMainPlatV5&TXCODE=520100&MERCHANTID=WQLJ50000000001&BRANCHID=500000000&MER_CHANNEL=0&DATE=20160616&TIME=202445&PROV_CODE=500000&CITY_CODE=500000&BILL_TYPE=100&BILL_ITEM=01011&MAC=0ca595c261fdbcd0d16f04c075afa26b
    	map.put("lifeUrl", lifeUrl.toString());
    	return LIFE_VIEW;
    }

    /*
	CCB_IBSVersion=V5
	&SERVLET_NAME=WCCMainPlatV5
	&TXCODE=520100
	&MERCHANTID=WQLJ50000000001----MERCHANTID：商户代码 (分行提供列表至商户)商户代码 (分行提供列表至商户)
	&BRANCHID=500000000----BRANCHID: 商户分行号 (9位，分行提供至商户)
	&MER_CHANNEL=0----MER_CHANNEL: 商户展现方式默认（0：手机1：PC）
	&DATE=20160616----DATEDATE: 日期戳(8位)，请求建行网站时的日期。
	&TIME=085655----TIME：时间戳(6位) ，请求建行网站时的时间。
	&PROV_CODE=500000----PROV_CODE: 悦生活缴费省份(6位，分行提供列表至商户) 500000
	&CITY_CODE=500000----CITY_CODE: 悦生活缴费城市(6位，分行提供列表至商户) 
	&BILL_TYPE=100----BILL_TYPE: 悦生活缴费大类(3位，分行提供列表至商户)  010	生活服务020	娱乐出行030	金融理财040	收款还款050	行政教育060	其他070	高校服务
	&BILL_ITEM=01001----BILL_ITEM: 悦生活缴费小类(5位，分行提供列表至商户)
	&MAC=734180cf4c50b32d0f001dfca0f4416b----加密串：MAC   32位 小写 原字符串（最后5位是保密串）：MERCHANTID=WQLJ50000000001BRANCHID=500000000MER_CHANNEL=0DATE=20160616TIME=08565512345
	*/
    public static enum LifeParamEnum {
		URL("URL", "http://life.ccb.com/tran/WCCMainPlatV5"),
		CCB_IBSVersion("CCB_IBSVersion", "V5"),
		SERVLET_NAME("SERVLET_NAME", "WCCMainPlatV5"),
		TXCODE("TXCODE", "520100"),
		MERCHANTID("MERCHANTID", "WQLJ50000000001"),
		BRANCHID("BRANCHID", "500000000"),
		MER_CHANNEL("MER_CHANNEL", "0"),
		PROV_CODE("PROV_CODE", "500000"),
		CITY_CODE("CITY_CODE", "500000"),
		BILL_TYPE("BILL_TYPE", ""),
		BILL_ITEM("BILL_ITEM", ""),
		DATE("DATE", ""),
		TIME("TIME", "");

		private String name;
		private String value;
		LifeParamEnum(String name, String value) {
			this.name = name;
			this.value = value;
		}
		public String getName() {
			return name;
		}
		public String getValue() {
			return value;
		}
	}
    
    public static void main(String args []){
    	String md5Str = "MERCHANTID=WQLJ50000000001BRANCHID=500000000MER_CHANNEL=0DATE=20160616TIME=08565512345";
    	String MAC = MD5BigFileUtil.md5(md5Str);
    	System.out.println(MAC);//734180cf4c50b32d0f001dfca0f4416b
    }
}
