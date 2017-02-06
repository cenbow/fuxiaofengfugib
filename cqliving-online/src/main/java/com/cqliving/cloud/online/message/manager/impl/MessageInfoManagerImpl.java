package com.cqliving.cloud.online.message.manager.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.app.dao.AppInfoDao;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.message.dao.MessageInfoDao;
import com.cqliving.cloud.online.message.dao.MessageReceiveDao;
import com.cqliving.cloud.online.message.domain.MessageInfo;
import com.cqliving.cloud.online.message.domain.MessageReceive;
import com.cqliving.cloud.online.message.dto.MessageInfoDto;
import com.cqliving.cloud.online.message.manager.MessageInfoManager;
import com.cqliving.cloud.online.security.dao.SysUserDao;
import com.cqliving.cloud.online.security.domain.SysUser;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.jpush.service.PushService;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;

@Service("messageInfoManager")
public class MessageInfoManagerImpl extends EntityServiceImpl<MessageInfo, MessageInfoDao> implements MessageInfoManager {
	
	@Autowired
	private AppInfoDao appInfoDao; 
	@Autowired
	private MessageInfoDao messageInfoDao;
	@Autowired
	private MessageReceiveDao messageReceiveDao;
	@Autowired
	private PushService pushService;
	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public PageInfo<MessageInfoDto> queryDtoForPage(PageInfo<MessageInfoDto> pageInfo, Map<String, Object> searchMap,
			Map<String, Boolean> sortMap) {
		return getEntityDao().queryDtoForPage(pageInfo, searchMap, sortMap);
	}

	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void send(Long id, Long userId, String userName) {
		MessageInfo obj = messageInfoDao.get(id);
		Byte sendType = obj.getSendType();
		if (MessageInfo.SENDTYPE0.equals(sendType) || MessageInfo.SENDTYPE1.equals(sendType)) {	//站内信、公告
			 //更新发送时间
			Date now = DateUtil.now();
			if (obj.getSendTime().after(now)) {	//如果发送时间比当前时间晚，则立即发送
				obj.setSendTime(now);
				obj.setUpdateTime(now);
				obj.setUpdator(userName);
				obj.setUpdatorId(userId);
				messageInfoDao.update(obj);
			}
		} else if (MessageInfo.SENDTYPE2.equals(sendType)) {	//推送
			//推送暂不实现定时推送
		} else if (MessageInfo.SENDTYPE3.equals(sendType)) {	//短信
			//TODO 唐涛 暂不实现
		}
	}

	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void add(MessageInfo messageInfo, Long userId, String userName) {
		Date now = DateUtil.now();
		Byte sendType = messageInfo.getSendType();
		//保存通知表
		messageInfo.setCreateTime(now);
		messageInfo.setCreator(userName);
		messageInfo.setCreatorId(userId);
		messageInfo.setStatus(MessageInfo.STATUS3);
		messageInfo.setUpdateTime(now);
		messageInfo.setUpdator(userName);
		messageInfo.setUpdatorId(userId);
		String[] appIdArray = messageInfo.getReceiverAppId().split(",");
		List<Long> appIdList = Lists.newArrayList();
		Long appId;
		for (String appIdStr : appIdArray) {
			appId = NumberUtils.toLong(appIdStr);
			appIdList.add(appId);
		}
		if (MessageInfo.SENDTYPE0.equals(sendType) || MessageInfo.SENDTYPE1.equals(sendType)) {	//站内信、公告
			messageInfoDao.save(messageInfo);
			//保存接收表
			List<SysUser> sysUsers = sysUserDao.getByAppIds(appIdList);
			MessageReceive messageReceive;
			for (SysUser sysUser : sysUsers) {
				messageReceive = new MessageReceive();
				messageReceive.setAppId(sysUser.getAppId());
				messageReceive.setMessageId(messageInfo.getId());
				messageReceive.setReceiverId(sysUser.getId());
				messageReceive.setSendType(messageInfo.getSendType());
				messageReceive.setStatus(MessageReceive.STATUS0);
				messageReceive.setUpdateTime(now);
				messageReceiveDao.save(messageReceive);
			}
		} else if (MessageInfo.SENDTYPE2.equals(sendType)) {	//推送
			messageInfo.setSendTime(now);	//推送暂都执行立即推送
			messageInfoDao.save(messageInfo);
			//每个App分别推送
			List<AppInfo> appInfos = appInfoDao.findByIds(appIdList);
			for (AppInfo appInfo : appInfos) {
				pushService.sendPush(appInfo.getJpushAppSecret(), appInfo.getJpushAppKey(), messageInfo.getContext());
			}
		} else if (MessageInfo.SENDTYPE3.equals(sendType)) {	//短信
			//TODO 唐涛 暂不实现
		}
	}
	
	/**
     * <p>Description: 分页查询站内信</p>
     * @author huxiaoping on 2016年5月9日
     * @param pageInfo
     * @param searchMap
     * @param sortMap
     * @return
     */
    @Override
    public PageInfo<MessageInfoDto> queryLetterForPage(PageInfo<MessageInfoDto> pageInfo, Map<String, Object> searchMap,
            Map<String, Boolean> sortMap){
        return this.getEntityDao().queryLetterForPage(pageInfo, searchMap, sortMap);
    }
    
    /**
     * <p>Description: 分页查询公告</p>
     * @author huxiaoping on 2016年5月13日
     * @param pageInfo
     * @param searchMap
     * @param sortMap
     * @return
     */
    @Override
    public PageInfo<MessageInfoDto> queryNoticeForPage(PageInfo<MessageInfoDto> pageInfo, Map<String, Object> searchMap,
            Map<String, Boolean> sortMap){
        return this.getEntityDao().queryNoticeForPage(pageInfo, searchMap, sortMap);
    }
    
    /**
     * <p>Description: 查询接收表站内信通过id</p>
     * @author huxiaoping on 2016年5月14日
     * @param pageInfo
     * @param searchMap
     * @param sortMap
     * @return
     */
    @Override
    public MessageInfoDto getById(Long id) {
       return this.getEntityDao().getById(id);
    }
}