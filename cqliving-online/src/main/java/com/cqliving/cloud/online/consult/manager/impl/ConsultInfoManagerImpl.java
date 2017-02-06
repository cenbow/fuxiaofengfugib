package com.cqliving.cloud.online.consult.manager.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.basic.domain.Option;
import com.cqliving.basic.service.BasicService;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.dao.UserInfoDao;
import com.cqliving.cloud.online.account.dao.UserSmsLogDao;
import com.cqliving.cloud.online.account.domain.UserAccount;
import com.cqliving.cloud.online.account.domain.UserInfo;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.domain.UserSmsLog;
import com.cqliving.cloud.online.account.manager.UserAccountManager;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.consult.dao.ConsultInfoDao;
import com.cqliving.cloud.online.consult.domain.ConsultInfo;
import com.cqliving.cloud.online.consult.dto.ConsultInfoDto;
import com.cqliving.cloud.online.consult.manager.ConsultInfoManager;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;

@Service("consultInfoManager")
public class ConsultInfoManagerImpl extends EntityServiceImpl<ConsultInfo, ConsultInfoDao> implements ConsultInfoManager {
    
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private UserSessionManager userSessionManager;
    @Autowired
    private BasicService basicService;
    @Autowired
    private UserAccountManager userAccountManager;
    @Autowired
    private UserSmsLogDao userSmsLogDao;
    
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ConsultInfo.STATUS99, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(status, idList);
	}

	/**
     * 我要咨询列表(滚动分页)
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月29日下午3:20:16
     */
    @Override
    public ScrollPage<ConsultInfoDto> queryConsultScrollPage(ScrollPage<ConsultInfoDto> page,
            Map<String, Object> conditions, String sessionId, String token) {
       return this.getEntityDao().queryConsultScrollPage(page, conditions);
    }

    /**
     * 保存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月29日下午4:24:49
     */
    @Override
    @Transactional(value="transactionManager")
    public void saveConsultInfo(Long appId, String type, String content, String enterpriseName, String linkmanName,
            String linkmanPhone, String token, String sessionId,String captcha) {
        //游客校验
        UserInfo userInfo = checkTourist(sessionId,appId);
        //验证码
        checkCaptcha(appId, linkmanPhone, captcha);
        //创建对象
        ConsultInfo consult = createObj(appId, type, content, enterpriseName, linkmanName, linkmanPhone, userInfo);
        //保存
        save(consult);
    }
    
    /**
     * 创建对象
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月29日下午4:51:55
     */
    private ConsultInfo createObj(Long appId, String type, String content, String enterpriseName, String linkmanName,
            String linkmanPhone,UserInfo userInfo){
        ConsultInfo consult = new ConsultInfo();
        consult.setAppId(appId);
        consult.setType(type);
        List<Option> reportTypeList = basicService.getOptionListByType(Option.TYPECODE19);
        if(null!=reportTypeList&&reportTypeList.size()>0){
            for (Option option : reportTypeList) {
                if(type.trim().equals(option.getCode())){
                    consult.setTypeName(option.getName());
                    break;
                }
            }
            if(StringUtils.isBlank(consult.getTypeName())){
                throw new BusinessException(-1, "咨询类别不存在");
            }
        }else{
            throw new BusinessException(-1, "咨询类别不存在");
        }
        consult.setContent(content);
        consult.setEnterpriseName(enterpriseName);
        consult.setLinkmanName(linkmanName);
        consult.setLinkmanPhone(linkmanPhone);
        consult.setCreateTime(new Date());
        if(null!=userInfo){
            consult.setCreator(null!=userInfo?userInfo.getName():"默认");
            consult.setCreatorId(userInfo.getId());
        }else{
            throw new BusinessException(
                    ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), 
                    ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
        }
        consult.setStatus(ConsultInfo.STATUS1);
        return consult;
    }
    
    private void checkCaptcha(Long appId ,String linkmanPhone,String captcha){
        Map<String, Object> map = Maps.newHashMap();
        map.put("EQ_appId", appId);
        map.put("EQ_status", UserSmsLog.TYPE5);
        map.put("EQ_type", UserSmsLog.STATUS0);
        Map<String, Boolean> sortMap = Maps.newHashMap();
        sortMap.put("id", false);
        UserSmsLog smsLog= null;
        List<UserSmsLog> list = userSmsLogDao.query(map, sortMap);
        if (CollectionUtils.isNotEmpty(list)) {
            smsLog = list.get(0);
        }
        //验证码是否失效
        if (smsLog == null || DateUtil.toDifferMinute(smsLog.getCreateTime(), DateUtil.now()) >= 30) {  //验证码已失效
            throw new BusinessException(
                    ErrorCodes.CommonErrorEnum.CAPTCHA_EXPIRIED.getCode(), 
                    ErrorCodes.CommonErrorEnum.CAPTCHA_EXPIRIED.getDesc());
        }
        //验证码是否正确
        if(captcha.equals(smsLog.getCaptcha())){
            throw new BusinessException(
                    ErrorCodes.CommonErrorEnum.CAPTCHA_ERROR.getCode(), 
                    ErrorCodes.CommonErrorEnum.CAPTCHA_ERROR.getDesc());
        }
        
        Date now = DateUtil.now();
        //验证通过
        smsLog.setStatus(UserSmsLog.STATUS1);
        smsLog.setUseTime(now);
        userSmsLogDao.update(smsLog);
    }
    
    /**
     * 游客校验
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月17日上午11:48:33
     */
    private UserInfo checkTourist(String sessionId,Long appId) throws BusinessException{
        //查询用户
        UserSession userSession = userSessionManager.getTourist(sessionId);
        Long userId = -1l;
        if (userSession == null) { 
            UserAccount account = userAccountManager.createTourist(appId, sessionId);
            userId = account.getId();
        }else{
            userId = userSession.getUserId();
        }
        UserInfo user =  userInfoDao.get(userId);
        if(null==user){
            throw new BusinessException(
                    ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode(), 
                    ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
        }
        return user;
    }

    /**
     * 回复
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月1日下午2:38:57
     */
    @Override
    @Transactional(value="transactionManager")
    public void reply(ConsultInfo consult) {
        this.getEntityDao().reply(consult.getId(), new Date(), consult.getReplyUserName(), consult.getReplyUserId(), consult.getReplyContent());
    }
}
