package com.cqliving.cloud.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqliving.cloud.online.config.domain.ConfigText;
import com.cqliving.cloud.online.config.service.ConfigTextService;
/**
 * 
 * <p>Title:AppConnectUsController </p>
 * <p>Description:联系我们 </p>
 * <p>Company: 新华龙掌媒文化传播有限公司  </p>
 * @author huxiaoping 2016年7月25日上午9:57:32
 *
 */
@Controller
public class AppConnectUsController extends CommonController {
    private static final Logger logger = LoggerFactory.getLogger(AppConnectUsController.class);
    @Autowired
    private ConfigTextService configTextService;
    
    /**
     * 增加-查看
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月25日上午10:58:23
     */
    @RequestMapping(value ="connect_us")
    public String toConnectUs(HttpServletRequest request, Map<String, Object> map,Long appId){
        logger.info("请求联系我们开始");
        logger.debug("param appId is:"+appId);
        if(null==appId){
            map.put("errorInfo", "数据有误，客户端信息不完整,请联系管理员");
        }else{
            ConfigText text = configTextService.getByAppId(appId,ConfigText.TYPE2).getData();
            map.put("item", text);
            //logger.debug("查询数据为："+JSONUtils.toJSONString(text));
        }
        return "/connectus/contact_us";
    }
}
