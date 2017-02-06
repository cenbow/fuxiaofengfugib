package com.cqliving.cloud.online.account.manager.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.dao.UserActCollecInfoDao;
import com.cqliving.cloud.online.account.dao.UserActListDao;
import com.cqliving.cloud.online.account.domain.UserActCollecInfo;
import com.cqliving.cloud.online.account.domain.UserActList;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserActListManager;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.act.domain.ActTest;
import com.cqliving.cloud.online.act.manager.ActTestManager;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Lists;

@Service("userActListManager")
public class UserActListManagerImpl extends EntityServiceImpl<UserActList, UserActListDao> implements UserActListManager {

	@Autowired
	private ActTestManager actTestManager;
	@Autowired
	private UserSessionManager userSessionManager;
	@Autowired
	private UserActCollecInfoDao userActCollecInfoDao;
	
	@Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public Long save(Long appId, String sessionId, String token, Long actInfoId, Long actInfoListId, Long[] optionIds, String[] optionValues, Long[] inputIds, String[] inputValues, String ip) {
		Long actTestId = null;
		UserSession userSession = userSessionManager.get(sessionId, token);
		//获得答题的配置
		ActTest actTest = actTestManager.getByInfoList(actInfoId, actInfoListId);
		if(actTest == null){
			throw new BusinessException(-99, "要参与的活动已过期或者已被删除");
		}
		//验证是否需要登录
		if(StringUtils.isBlank(userSession.getToken()) && ActTest.LOGGEDSTATUS1.equals(actTest.getLoggedStatus())){
			throw new BusinessException(ErrorCodes.CommonErrorEnum.NOT_LOGIN.getCode(), ErrorCodes.CommonErrorEnum.NOT_LOGIN.getDesc());
		}
		actTestId = actTest.getId();
		
		Date date = new Date();
		UserActList userActList = null;
		List<UserActList> list = this.getEntityDao().findByActInfoListAndUserId(actInfoListId, userSession.getUserId());
		if(list != null && list.size() > 0){
			userActList = list.get(0);
//			this.getEntityDao().addJoinCount(userActList.getId(), date);//这里用这个为什么要报错。
			userActList.setJoinCount(userActList.getJoinCount() + 1);
			userActList.setUpdateTime(date);
			this.getEntityDao().update(userActList);
		}else{
			userActList = new UserActList();
			userActList.setUserId(userSession.getUserId());
			userActList.setActInfoId(actInfoId);
			userActList.setActInfoListId(actInfoListId);
			userActList.setCreateTime(date);
			userActList.setJoinCount(1);
			userActList.setUpdateTime(date);
			userActList.setIp(ip);
			this.save(userActList);
		}
		//处理收集信息的值
		List<UserActCollecInfo> userActCollectInfoList = Lists.newArrayList();
		UserActCollecInfo userActCollecInfo = null;
		
		String[] optionValue = null;
		Long optionValueId = null;
		//处理单选、多选、下拉的值
		if(optionIds != null && optionIds.length > 0 && optionValues != null && optionIds.length == optionValues.length){//检测数据的有效性
			int len = optionIds.length;
			for(int i = 0; i < len; i ++){
				optionValue = new String[]{null};
				if(StringUtils.isNotBlank(optionValues[i]) && !"null".equals(optionValues[i])){
					if(optionValues[i].indexOf("a") != -1){
						optionValue = optionValues[i].split("a");
					}else{
						optionValue[0] = optionValues[i];
					}
				}
				for(String str : optionValue){//处理多选值
					optionValueId = null;
					try {
						optionValueId = Long.parseLong(str);
					} catch (NumberFormatException e) {}
					userActCollecInfo = new UserActCollecInfo();
					userActCollecInfo.setActInfoListId(actInfoListId);
					userActCollecInfo.setActCollectInfoId(optionIds[i]);
					userActCollecInfo.setActCollectOptionId(optionValueId);//
					userActCollecInfo.setCreateTime(date);
					userActCollecInfo.setUserActListId(userActList.getId());
					userActCollecInfo.setUserId(userSession.getUserId());
					userActCollectInfoList.add(userActCollecInfo);
				}
			}
		}
		//处理输入框的值
		if(inputIds != null && inputIds.length > 0 && inputValues != null && inputIds.length == inputValues.length){//检测数据的有效性
			int len = inputIds.length;
			for(int i = 0; i < len; i ++){
				userActCollecInfo = new UserActCollecInfo();
				userActCollecInfo.setActInfoListId(actInfoListId);
				userActCollecInfo.setActCollectInfoId(inputIds[i]);
				userActCollecInfo.setValue(inputValues[i]);//
				userActCollecInfo.setCreateTime(date);
				userActCollecInfo.setUserActListId(userActList.getId());
				userActCollecInfo.setUserId(userSession.getUserId());
				userActCollectInfoList.add(userActCollecInfo);
			}
		}
		if(userActCollectInfoList.size() > 0){
			userActCollecInfoDao.saves(userActCollectInfoList);
		}
		return actTestId;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.manager.UserActListManager#findTotalByInfoListId()
	 */
	@Override
	public Long findTotalByInfoListId(Long actInfoListId) {
	
		return this.getEntityDao().findByActInfoListId(actInfoListId);
	}
}
