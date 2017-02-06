package com.cqliving.cloud.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.constant.PropertyKey;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年7月28日
 */
@Controller
public class AppOtherController {
	
	private final static String DJ_WEIFABU = "/other/dj_wfb";
	
	@RequestMapping(value="dj_wfb")
    public String djWeifabu(HttpServletRequest request, Map<String, Object> map){
		String webPath = PropertiesConfig.getString(PropertyKey.DJ_WEIFABU);
		map.put("imgPath", webPath);
		return DJ_WEIFABU;
	}
}
