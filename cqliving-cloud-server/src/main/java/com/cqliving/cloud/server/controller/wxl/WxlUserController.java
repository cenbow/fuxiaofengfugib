package com.cqliving.cloud.server.controller.wxl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cqliving.cloud.common.CacheConstant;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.interfacc.dto.WxlUserInfo;
import com.cqliving.redis.base.AbstractRedisClient;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.StringUtil;
import com.cqliving.tool.common.util.encrypt.AESUtil;
import com.cqliving.tool.common.util.http.HttpClientUtils;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2017年1月23日
 */
@Controller
@RequestMapping("wxlu")
public class WxlUserController {
    private static final Logger logger = LoggerFactory.getLogger(WxlUserController.class);

    @Autowired
    private AbstractRedisClient abstractRedisClient;
    
    private final static String wxAppId = "wxe0da7fe031b2eff7";

    /**
     * Title:微信小程序登录获取session_key等信息
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月22日
     * @param request
     * @param appId
     * @param code
     * @param userSession
     * @return 返回redis缓存的sessionKey名字
     */
    @ResponseBody
    @RequestMapping(value="getUserSession", method=RequestMethod.POST)
    public Response<String> getUserSession(HttpServletRequest request, @RequestParam Long appId, String code, String userSession){
        logger.debug("微信小程序登录维护接口："
                + String.format("appId=%d, code=%s", appId, code));
        //参数验证
        if(appId == null || (StringUtil.isEmpty(code) && StringUtil.isEmpty(userSession))){
            return new Response<String>(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
        }
        String domain = CacheConstant.getKey(CacheConstant.WECHATLITE_USER_SESSION_HASH, appId);
        String text = "";
        
        if(!StringUtil.isEmpty(userSession)){
            text = abstractRedisClient.getHSet(domain, userSession);
        }
        if(StringUtil.isEmpty(text)){//用户已经存在
            String secret = "e35ddf6bb418c3be9da9ad6d79549250";
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+wxAppId+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
            text = HttpClientUtils.get(url);
            if(StringUtil.isEmpty(text)){
                return new Response<String>(ErrorCodes.FAILURE, "获取session_key失败");
            }
            userSession = StringUtil.getUUID();//生成的第三方session
            //把获取的session_key和openid信息存redis缓存
            abstractRedisClient.setHSet(domain, userSession, text);
        }
        Response<String> rs = Response.newInstance();
        rs.setData(userSession);
        return rs;
    }
    
    /**
     * Title:
     * <p>Description:
     * 对微信小程序用户加密数据的解密
     * 检验数据的真实性，并且获取解密后的明文.
     * </p>
     * @author DeweiLi on 2017年1月23日
     * @param request
     * @param appId
     * @param encryptedData
     * @param iv
     * @param userSessionKey
     * @return
     */
    @ResponseBody
    @RequestMapping(value="getUserInfo", method=RequestMethod.POST)
    public Response<WxlUserInfo> decodeUserInfo(HttpServletRequest request, Long appId, String encryptedData, String iv, String userSessionKey){
        logger.debug("微信小程序用户加密数据的解密："
                + String.format("appId=%d, userSessionKey=%s, iv=%s, encryptedData=%s", appId, userSessionKey, iv, encryptedData));
        if(StringUtils.isBlank(encryptedData) || StringUtils.isBlank(iv) || iv.length() != 24 || StringUtils.isBlank(userSessionKey)){
            return new Response<WxlUserInfo>(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
        }
        //从缓存中获取
        String domain = CacheConstant.getKey(CacheConstant.WECHATLITE_USER_SESSION_HASH, appId);
        String text = abstractRedisClient.getHSet(domain, userSessionKey);
        if(StringUtil.isEmpty(text)){
            return new Response<WxlUserInfo>(ErrorCodes.FAILURE, "获取用户失败");
        }
        JSONObject obj = JSONObject.parseObject(text);
        String sessionKey = obj.getString("session_key");
        if(StringUtil.isEmpty(sessionKey) || sessionKey.length() != 24){
            return new Response<WxlUserInfo>(ErrorCodes.FAILURE, "session_key错误");
        }
        Response<WxlUserInfo> rs = Response.newInstance();
        try {
            byte[] resultByte = AESUtil.instance.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
            if(null != resultByte && resultByte.length > 0){
                String userInfo = new String(resultByte, "UTF-8");
                WxlUserInfo info = JSONObject.parseObject(userInfo, WxlUserInfo.class);
                String watermark = info.getWatermark();
                JSONObject obj1 = JSONObject.parseObject(watermark);
                if(!wxAppId.equals(obj1.getString("appid"))){//敏感数据归属appid，开发者可校验此参数与自身appid是否一致
                    rs.setCode(ErrorCodes.FAILURE);
                    rs.setMessage("appid不一致");
                }
                info.setWatermark(null);//不需要把appid传给前台
                rs.setData(info);
            }
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
