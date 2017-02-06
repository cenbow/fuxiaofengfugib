package com.cqliving.cloud.online.account.manager.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.json.JSONUtils;
import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.account.dao.UserGovAuthenticationDao;
import com.cqliving.cloud.online.account.domain.UserGovAuthentication;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.domain.UserSmsLog;
import com.cqliving.cloud.online.account.manager.UserGovAuthenticationManager;
import com.cqliving.cloud.online.account.manager.UserSmsLogManager;
import com.cqliving.cloud.online.account.service.UserSessionService;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.DesUtil;
import com.cqliving.tool.common.util.IdcardUtil;
import com.cqliving.tool.common.util.RequestVerify;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.http.HttpClientUtil;

@Service("userGovAuthenticationManager")
public class UserGovAuthenticationManagerImpl extends EntityServiceImpl<UserGovAuthentication, UserGovAuthenticationDao> implements UserGovAuthenticationManager {
    
    @Autowired
    private UserSessionService userSessionService;
    @Autowired
    private UserSmsLogManager userSmsLogManager;
    private static final Logger logger = LoggerFactory.getLogger(UserGovAuthenticationManagerImpl.class);
    /**
     * 获取认证信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月29日上午9:44:17
     */
    @Override
    public UserGovAuthentication get(String token, String sessionId, Long appId) {
        UserSession userSession = userSessionService.getByToken(token).getData();
        if(null==userSession||null==userSession.getUserId()){
            throw new BusinessException(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
        }
        UserGovAuthentication user = this.getEntityDao().get(userSession.getUserId());
        if(null==user){
            throw new BusinessException(ErrorCodes.CommonErrorEnum.NOT_AUTHENTICATION.getCode(), "没有进行实名认证，请实名认证");
        }
        return user;
    }
    
    /**
     * 保存认证
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月29日上午10:49:04
     */
    @Override
    public void save(String token, String sessionId, Long appId, String name, String idCard, Byte sex, String nation,
            String phone,String captcha) {
        //校验身份证号码
        IdcardUtil.IDCardValidate(idCard);
        //校验手机号码
        phoneCheck(phone);
        //校验验证码
        captchaCheck(appId, phone, captcha);
        //获取我们DB认证信息
        UserGovAuthentication user = getAndSave(token, name, idCard, sex, nation, phone);
        //认证，获取openId
        String thirdOpenId = send(phone, idCard, name);
        //加密openId
        if(StringUtils.isNotBlank(thirdOpenId)){
            try{
                thirdOpenId = DesUtil.encrypt(thirdOpenId);
            }catch(Exception e){
                throw new BusinessException(-1, "加密过程出现异常，请更换手机号码重试!");
            }
        }else{
            throw new BusinessException(-1, "认证失败!");
        }
        user.setThirdOpenId(thirdOpenId);
        user.setIsAuthentication(UserGovAuthentication.ISAUTHENTICATION1);
        this.getEntityDao().update(user);
    }
    
    /**
     * 获取和保存
     * @Description 
     * <p>
     * 1、本地数据库获取一次认证信息，若不存在，创建一个认证对象，设置相关属性，保存DB并返回
     * <p>
     * 2、本地数据库存在，判断是否认证，未认证就覆盖基本信息并返回，已认证就抛出异常
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月4日下午1:58:26
     */
    private UserGovAuthentication getAndSave(String token, String name, String idCard, Byte sex, String nation,String phone){
        //获取认证信息
        UserSession userSession = userSessionService.getByToken(token).getData();
        if(null==userSession||null==userSession.getUserId()){
            throw new BusinessException(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
        }
        //获取当前认证的对象
        UserGovAuthentication user = this.getEntityDao().get(userSession.getUserId());
        if(null==user){
            //没有新增一个数据
            user = new UserGovAuthentication();
            user.setCreateTime(new Date());
        }else{
            if(UserGovAuthentication.ISAUTHENTICATION1.equals(user.getIsAuthentication())){
                throw new BusinessException(-1, "用户已认证！");
            }
        }
        //设置认证相关属性
        user.setUpdateTime(new Date());
        user.setName(name);
        user.setIdCard(idCard);
        user.setSex(sex);
        user.setNation(nation);
        user.setPhone(phone);
        if(null==user.getId()){
            user.setId(userSession.getUserId()); 
            user.setIsAuthentication(UserGovAuthentication.ISAUTHENTICATION0);
            this.getEntityDao().save(user);
        }
        return user;
    }
    
    /**
     * 向行政审批接口发送消息，进行认证
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月3日下午4:11:48
     */
    private String send(String phone,String idCard,String name){
        //签名密码
        String privateKey = PropertiesConfig.getString(PropertyKey.GOV_AUTH_KEY);
        if(StringUtils.isBlank(privateKey)){
            throw new BusinessException(-1, "环境有误，获取签名，请联系管理员！");
        }
        //设置签名内容
        Map<String, String> params = new HashMap<String,String>();
        params.put("phone", phone);
        params.put("idCard", idCard);
        params.put("name", name);
        params.put("passWord", idCard.toLowerCase().substring(idCard.length()-6, idCard.length()));
        //签名
        RequestVerify.sign(privateKey,params);
        String url = PropertiesConfig.getString(PropertyKey.GOV_AUTH_REGISTER);
        if(StringUtils.isBlank(url)){
            throw new BusinessException(-1, "环境有误，获取不到请求地址，请联系管理员！");
        }
        //请求第三方修改接口,获取openId
        String result = HttpClientUtil.sendPostRequest(url, params, null, null);
        if(StringUtils.isBlank(result)){
            throw new BusinessException(-1, "修改失败，调用行政审批大厅方修改不成功！");
        }
        logger.info("发送后得到的结果：    \n"+result);
        //处理返回结果
        @SuppressWarnings("unchecked")
        Map<String, Object> jsonResult = (Map<String, Object>) JSONUtils.parse(result);
        if (!((String)jsonResult.get("code")).equals("200")) {
            throw new BusinessException(-1, (String)jsonResult.get("error"));
        }
        
        return jsonResult.get("userID")+"";
    }
    
    /**
     * 校验手机号码（简单校验,只校验是11位数字）
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月29日上午11:08:20
     */
    private void phoneCheck(String phone){
        String REGEX_MOBILE = "^\\d{11}$";
        if(!Pattern.matches(REGEX_MOBILE, phone)){
            throw new BusinessException(-1,"手机号码不正确");
        }
    }
    
    /**
     * 校验验证码
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月29日上午11:07:23
     */
    private void captchaCheck(Long appId,String phone,String captcha){
        UserSmsLog userSmsLog = userSmsLogManager.getLastSms(phone,appId, UserSmsLog.TYPE5, UserSmsLog.STATUS0);
        if (userSmsLog == null || DateUtil.toDifferMinute(userSmsLog.getCreateTime(), DateUtil.now()) >= 30) {  //验证码已失效
            throw new BusinessException(
                    ErrorCodes.CommonErrorEnum.CAPTCHA_EXPIRIED.getCode(), 
                    ErrorCodes.CommonErrorEnum.CAPTCHA_EXPIRIED.getDesc());
        }
        String code = userSmsLog.getCaptcha();
        if (!captcha.equals(code)) {    //验证码不正确
            throw new BusinessException(
                    ErrorCodes.CommonErrorEnum.CAPTCHA_ERROR.getCode(), 
                    ErrorCodes.CommonErrorEnum.CAPTCHA_ERROR.getDesc());
        }
        //验证是否是接收验证码的手机号
        if (!(userSmsLog.getTelephone().equals(phone))) {
            throw new BusinessException(
                    ErrorCodes.SmsErrorEnum.TELEPHONE_ERROR.getCode(), 
                    ErrorCodes.SmsErrorEnum.TELEPHONE_ERROR.getDesc());
        }
        userSmsLog.setStatus(UserSmsLog.STATUS1);
        userSmsLogManager.update(userSmsLog);
    }
}
